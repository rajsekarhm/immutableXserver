package com.immutable.request.accounts;

import com.dependencies.jedis.IJedis;
import com.dependencies.jedis.JedisImx;
import com.immutable.request.assets.AssetDAO;
import com.immutable.request.token.TokenDAO;
import com.immutable.request.utils.Formatter;
import java.util.List;

public class UserDTO {
    UserDAO userDetails;
    List<AssetDAO> assets;
    List<TokenDAO> tokens;
    IJedis jedis = new JedisImx();

    public UserDTO getUserDetails(String userId){
        UserDTO _userDTO = new UserDTO();
        UserDAO userDetails = Formatter.toObject(jedis.getByString(userId),UserDAO.class);
        List<String>  currentUserTokens = userDetails.tokenIds;
        List<String>  currentUserAssets = userDetails.assetIds;
        currentUserAssets.forEach( id -> assets.add(Formatter.toObject(jedis.getByString(id),AssetDAO.class)));
        currentUserTokens.forEach( id -> tokens.add(Formatter.toObject(jedis.getByString(id),TokenDAO.class)));
        return  _userDTO;
    }
}
