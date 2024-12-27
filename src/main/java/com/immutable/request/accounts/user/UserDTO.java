package com.immutable.request.accounts.user;

import com.dependencies.jedis.IJedis;
import com.dependencies.jedis.JedisImx;
import com.immutable.request.assets.AssetDAO;
import com.immutable.request.token.TokenDAO;
import com.immutable.request.utils.Formatter;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    UserDAO user;
    List<AssetDAO> assets = new ArrayList<>();
    List<TokenDAO> tokens =  new ArrayList<>();

    public UserDAO getUser() {
        return user;
    }

    public void setUser(UserDAO user) {
        this.user = user;
    }

    public List<AssetDAO> getAssets() {
        return assets;
    }

    public void setAssets(List<AssetDAO> assets) {
        this.assets = assets;
    }

    public List<TokenDAO> getTokens() {
        return tokens;
    }

    public void setTokens(List<TokenDAO> tokens) {
        this.tokens = tokens;
    }

    public UserDTO getUserDetails(String userId){
        IJedis jedis = new JedisImx();
        UserDTO _userDTO = new UserDTO();
        UserDAO userDetails = Formatter.toObject(jedis.getByString(userId),UserDAO.class);
        List<String>  currentUserTokens = userDetails.tokenIds;
        List<String>  currentUserAssets = userDetails.assetIds;
        _userDTO.user = userDetails;
        currentUserAssets.forEach( id -> _userDTO.assets.add(Formatter.toObject(jedis.getByString(id),AssetDAO.class)));
        currentUserTokens.forEach( id -> _userDTO.tokens.add(Formatter.toObject(jedis.getByString(id),TokenDAO.class)));
        return  _userDTO;
    }
}
