package com.immutable.request.accounts.user;

import com.dependencies.jedis.IJedis;
import com.dependencies.utils.ResponseSchema;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.immutable.authentication.AuthResponse;
import com.immutable.authentication.AuthUser;
import com.immutable.authentication.CookieUtil;
import com.immutable.authentication.DefaultPassWordAuth;
import com.immutable.authentication.JwtService;
import com.immutable.request.assets.Asset;
import com.immutable.request.token.Token;
import com.immutable.request.utils.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin
public class UserDAO {
    private final IJedis redis;
    private final JwtService jwtService;

    private static final long COOKIE_MAX_AGE = 24 * 60 * 60 * 2; // 48 hours in seconds

    @Autowired
    public UserDAO(@Qualifier("jedisImx") IJedis redis, JwtService jwtService) {
        this.redis = redis;
        this.jwtService = jwtService;
    }

    @GetMapping("/accessibility")
    public ResponseEntity<ResponseSchema<Boolean>> userAccessibility() {
        return ResponseSchema.respond(true, HttpStatus.OK, "accessible");
    }

    /**
     * POST /api/v1/user/createuser
     * Registers a new user, issues a JWT, and sets the auth-token cookie.
     *
     * Response body: { data: { user: {...}, token: "eyJ..." }, status: 201, message: "created" }
     * Set-Cookie: auth-token=eyJ...; HttpOnly; Secure; SameSite=Strict; Path=/; Max-Age=86400
     */
    @PostMapping(value = "/createuser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSchema<AuthResponse>> createUser(
            @RequestBody User user,
            HttpServletResponse response) throws IOException {
        // Validate required fields BEFORE persisting
        if (user.getsecurityId() == null) {
            return ResponseSchema.respond(null, HttpStatus.BAD_REQUEST, "securityId is mandatory");
        }
        if (user.password == null || user.password.isBlank()) {
            return ResponseSchema.respond(null, HttpStatus.BAD_REQUEST, "password is mandatory");
        }

        String hashedPassword = DefaultPassWordAuth.register(user.securityId.toString(), user.password);
        if (hashedPassword == null) {
            return ResponseSchema.respond(null, HttpStatus.CONFLICT, "User with this securityId is already registered");
        }
        user.password = hashedPassword;
        user.tokenIds = (user.tokenIds != null) ? user.tokenIds : new HashSet<>();
        user.assetIds = (user.assetIds != null) ? user.assetIds : new HashSet<>();

        redis.setByString(user.getsecurityId().toString(), Formatter.toJSON(user));
        User newUser = Formatter.toObject(redis.getByString(user.getsecurityId().toString()), User.class);

        // Generate JWT and set cookie
        String displayName = (newUser.getFirstName() != null ? newUser.getFirstName() : "") +
                " " + (newUser.getLastName() != null ? newUser.getLastName() : "");
        String role = Boolean.TRUE.equals(newUser.getIsAgent()) ? "custodian" : "user";
        String token = jwtService.generateToken(newUser.getsecurityId().toString(), displayName.trim(), role);
        CookieUtil.addAuthCookie(response, token, COOKIE_MAX_AGE);

        AuthResponse authResponse = new AuthResponse(newUser, token);
        return ResponseSchema.respond(authResponse, HttpStatus.CREATED, "created");
    }

    @PutMapping(value = "/updateuser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSchema<User>> updateUser(@RequestBody User user) {
        if (user.securityId == null) {
            return ResponseSchema.respond(null, HttpStatus.BAD_REQUEST, "securityId is mandatory");
        }
        String existing = redis.getByString(user.securityId.toString());
        if (existing == null) {
            return ResponseSchema.respond(null, HttpStatus.NOT_FOUND, "User not found");
        }

        redis.setByString(user.securityId.toString(), Formatter.toJSON(user));
        User getUser = Formatter.toObject(redis.getByString(user.getsecurityId().toString()), User.class);
        return ResponseSchema.respond(getUser, HttpStatus.OK, "updated");
    }

    @DeleteMapping(value = "/deleteuser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSchema<Void>> deleteUser(@RequestParam String securityId) {
        String existing = redis.getByString(securityId);
        if (existing == null) {
            return ResponseSchema.respond(null, HttpStatus.NOT_FOUND, "User not found");
        }
        redis.del(securityId);
        return ResponseSchema.respond(null, HttpStatus.OK, "deleted");
    }

    @GetMapping(value = "/getuser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSchema<UserDTO>> getUser(@RequestParam String securityId) {
        String userJson = redis.getByString(securityId);
        if (userJson == null) {
            return ResponseSchema.respond(null, HttpStatus.NOT_FOUND, "No user found for the given securityId");
        }
        User userDetails = Formatter.toObject(userJson, User.class);
        UserDTO userDTO = new UserDTO();
        userDTO.setUser(userDetails);

        List<String> currentUserTokens = (userDetails.tokenIds == null) ? new ArrayList<>() : new ArrayList<>(userDetails.tokenIds);
        List<String> currentUserAssets = (userDetails.assetIds == null) ? new ArrayList<>() : new ArrayList<>(userDetails.assetIds);

        currentUserAssets.forEach(id -> {
            String assetJson = redis.getByString(id);
            if (assetJson != null) {
                userDTO.getAssets().add(Formatter.toObject(assetJson, Asset.class));
            }
        });
        currentUserTokens.forEach(id -> {
            String tokenJson = redis.getByString(id);
            if (tokenJson != null) {
                userDTO.getTokens().add(Formatter.toObject(tokenJson, Token.class));
            }
        });

        return ResponseSchema.respond(userDTO, HttpStatus.OK, "get");
    }

    /**
     * PUT /api/v1/user/auth
     * Authenticates a user, issues a JWT, and sets the auth-token cookie.
     *
     * Response body: { data: { user: {...}, token: "eyJ..." }, status: 200, message: "Authenticated" }
     * Set-Cookie: auth-token=eyJ...; HttpOnly; Secure; SameSite=Strict; Path=/; Max-Age=86400
     */
    @PutMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSchema<AuthResponse>> authUser(
            @RequestBody AuthUser auth,
            HttpServletResponse response) throws IOException {
        if (auth.getSecurityId() == null || auth.getPassword() == null) {
            return ResponseSchema.respond(null, HttpStatus.BAD_REQUEST, "securityId and password are required");
        }
        if (!DefaultPassWordAuth.login(auth.getSecurityId(), auth.getPassword())) {
            return ResponseSchema.respond(null, HttpStatus.UNAUTHORIZED, "User or Security ID mismatch");
        }

        // Fetch user from Redis for the response
        String userJson = redis.getByString(auth.getSecurityId());
        User user = (userJson != null) ? Formatter.toObject(userJson, User.class) : null;

        // Generate JWT and set cookie
        String displayName = "";
        String role = "user";
        if (user != null) {
            displayName = ((user.getFirstName() != null ? user.getFirstName() : "") +
                    " " + (user.getLastName() != null ? user.getLastName() : "")).trim();
            role = Boolean.TRUE.equals(user.getIsAgent()) ? "custodian" : "user";
        }
        String token = jwtService.generateToken(auth.getSecurityId(), displayName, role);
        CookieUtil.addAuthCookie(response, token, COOKIE_MAX_AGE);

        AuthResponse authResponse = new AuthResponse(user, token);
        return ResponseSchema.respond(authResponse, HttpStatus.OK, "Authenticated");
    }

    /**
     * POST /api/v1/user/logout
     * Clears the auth-token cookie.
     *
     * Set-Cookie: auth-token=; Max-Age=0; HttpOnly; Secure; SameSite=Strict; Path=/
     */
    @PostMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSchema<Void>> logout(HttpServletResponse response) {
        CookieUtil.clearAuthCookie(response);
        return ResponseSchema.respond(null, HttpStatus.OK, "Logged out successfully");
    }

    @PutMapping(value = "/addasset", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSchema<User>> addNewAsset(@RequestParam String securityId, @RequestBody Map<String, String> asset) throws IOException {
        String userDetails = redis.getByString(securityId);
        if (userDetails == null) {
            return ResponseSchema.respond(null, HttpStatus.NOT_FOUND, "User not found");
        }
        User user = Formatter.convertToObject(userDetails, User.class);
        user.tokenIds = (user.tokenIds != null) ? user.tokenIds : new HashSet<>();
        user.assetIds = (user.assetIds != null) ? user.assetIds : new HashSet<>();

        String id = asset.get("assetId");
        if (id == null || id.isBlank()) {
            return ResponseSchema.respond(user, HttpStatus.BAD_REQUEST, "assetId is required");
        }
        if (redis.getByString(id) == null) {
            return ResponseSchema.respond(user, HttpStatus.NOT_FOUND, "Asset does not exist");
        }
        if (user.assetIds.contains(id)) {
            return ResponseSchema.respond(user, HttpStatus.CONFLICT, "Asset already assigned to user");
        }

        user.assetIds.add(id);
        redis.setByString(Long.toString(user.securityId), Formatter.toJSON(user));
        User updatedUser = Formatter.toObject(redis.getByString(Long.toString(user.securityId)), User.class);
        return ResponseSchema.respond(updatedUser, HttpStatus.OK, "assetAdded");
    }

    @PutMapping(value = "/addtoken", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSchema<User>> addNewToken(@RequestParam String securityId, @RequestBody Map<String, String> token) throws JsonProcessingException {
        String userDetails = redis.getByString(securityId);
        if (userDetails == null) {
            return ResponseSchema.respond(null, HttpStatus.NOT_FOUND, "User not found");
        }

        String id = token.get("tokenId");
        if (id == null || id.isBlank()) {
            return ResponseSchema.respond(null, HttpStatus.BAD_REQUEST, "tokenId is required");
        }

        User user = Formatter.convertToObject(userDetails, User.class);
        user.tokenIds = (user.tokenIds != null) ? user.tokenIds : new HashSet<>();
        user.assetIds = (user.assetIds != null) ? user.assetIds : new HashSet<>();

        if (redis.getByString(id) == null) {
            return ResponseSchema.respond(user, HttpStatus.NOT_FOUND, "Token does not exist");
        }

        user.tokenIds.add(id);
        redis.setByString(Long.toString(user.securityId), Formatter.toJSON(user));
        User updatedUser = Formatter.toObject(redis.getByString(Long.toString(user.securityId)), User.class);
        return ResponseSchema.respond(updatedUser, HttpStatus.OK, "tokenAdded");
    }

    @PutMapping(value = "/removeasset", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSchema<User>> removeAsset(@RequestParam String securityId, @RequestBody Map<String, String> asset) throws IOException {
        String userDetails = redis.getByString(securityId);
        if (userDetails == null) {
            return ResponseSchema.respond(null, HttpStatus.NOT_FOUND, "User not found");
        }

        User user = Formatter.convertToObject(userDetails, User.class);
        String id = asset.get("assetId");
        if (id == null || id.isBlank()) {
            return ResponseSchema.respond(user, HttpStatus.BAD_REQUEST, "assetId is required");
        }

        if (user.assetIds == null || !user.assetIds.contains(id)) {
            return ResponseSchema.respond(user, HttpStatus.NOT_FOUND, "Asset not found on user");
        }

        user.assetIds.remove(id);
        redis.setByString(Long.toString(user.securityId), Formatter.toJSON(user));
        User updatedUser = Formatter.toObject(redis.getByString(Long.toString(user.securityId)), User.class);
        return ResponseSchema.respond(updatedUser, HttpStatus.OK, "assetRemoved");
    }
}
