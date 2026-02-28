package com.immutable.request.token;

public class TokenDTO {
    String userId;
    String tokenId;
    String walletAddress;

    public String getUserId() {
        return userId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}


