package com.immutable.request.token;

import com.dependencies.jedis.IJedis;
import com.dependencies.utils.ResponseSchema;
import com.immutable.request.assets.IAssetsHandler;
import com.immutable.request.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/token")
@CrossOrigin
public class TokenDAO implements IAssetsHandler<Token> {
    private final IJedis redis;

    @Autowired
    public TokenDAO(@Qualifier("jedisImx") IJedis redis) {
        this.redis = redis;
    }

    @Override
    @PostMapping(value = "/createtoken", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSchema<Token>> create(@RequestBody Token token) {
        if (token.getTokenId() == null || token.getTokenId().isBlank()) {
            return ResponseSchema.respond(null, HttpStatus.BAD_REQUEST, "tokenId is required");
        }
        redis.setByString(token.getTokenId(), Formatter.toJSON(token));
        Token created = Formatter.toObject(redis.getByString(token.getTokenId()), Token.class);
        return ResponseSchema.respond(created, HttpStatus.CREATED, "createToken");
    }

    @Override
    @PutMapping(value = "/updatetoken", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSchema<Token>> update(@RequestParam String tokenId, @RequestBody Token token) {
        String existing = redis.getByString(tokenId);
        if (existing == null) {
            return ResponseSchema.respond(null, HttpStatus.NOT_FOUND, "Token not found");
        }
        redis.setByString(tokenId, Formatter.toJSON(token));
        Token updated = Formatter.toObject(redis.getByString(tokenId), Token.class);
        return ResponseSchema.respond(updated, HttpStatus.OK, "updateToken");
    }

    @Override
    @GetMapping(value = "/gettoken", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSchema<Token>> get(@RequestParam String tokenId) {
        String tokenJson = redis.getByString(tokenId);
        if (tokenJson == null) {
            return ResponseSchema.respond(null, HttpStatus.NOT_FOUND, "Token not found");
        }
        Token token = Formatter.toObject(tokenJson, Token.class);
        return ResponseSchema.respond(token, HttpStatus.OK, "getToken");
    }

    @Override
    @DeleteMapping(value = "/deletetoken", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSchema<Token>> delete(@RequestParam String tokenId) {
        String existing = redis.getByString(tokenId);
        if (existing == null) {
            return ResponseSchema.respond(null, HttpStatus.NOT_FOUND, "Token not found");
        }
        redis.del(tokenId);
        return ResponseSchema.respond(null, HttpStatus.OK, "deleteToken");
    }
}
