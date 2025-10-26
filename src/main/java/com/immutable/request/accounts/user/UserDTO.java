package com.immutable.request.accounts.user;

import com.immutable.request.assets.Asset;
import com.immutable.request.token.Token;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    User user;
    List<Asset> assets = new ArrayList<>();
    List<Token> tokens =  new ArrayList<>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

}
