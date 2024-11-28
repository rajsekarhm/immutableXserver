package com.immutable.request.assets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.kafka.common.protocol.types.Field;

public class TokenDAO {
    private String walletAddress;
    private String numberOfToken;
    private String symbol;
    private String tokenName;
    private String tokenId;

    public TokenDAO(){}
    @JsonCreator
    public TokenDAO(@JsonProperty("walletAddress") String _walletAddress,
                    @JsonProperty("numberOfToken") String _numberOfToken,
                    @JsonProperty("symbol") String _symbol,
                    @JsonProperty("tokenName") String _tokenName,
                    @JsonProperty("tokenId") String _tokenId){
        this.numberOfToken= _numberOfToken;
        this.symbol = _symbol;
        this.walletAddress = _walletAddress;
        this.tokenName = _tokenName;
        this.tokenId = _tokenId;
    }
    public TokenDAO(Builder builder) {
        this.walletAddress = builder.walletAddress;
        this.numberOfToken = builder.numberOfToken;
        this.symbol = builder.symbol;
        this.tokenName = builder.tokenName;
        this.tokenId = builder.tokenId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public String getNumberOfToken() {
        return numberOfToken;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getTokenName() {
        return tokenName;
    }

    public static class Builder {
        private String walletAddress;
        private String numberOfToken;
        private String symbol;
        private String tokenName;
        private String tokenId;

        public Builder() {}

        public Builder setTokenId(String tokenId) {
            this.tokenId = tokenId;
            return this;
        }

        public Builder setWalletAddress(String walletAddress) {
            this.walletAddress = walletAddress;
            return this;
        }

        public Builder setNumberOfToken(String numberOfToken) {
            this.numberOfToken = numberOfToken;
            return this;
        }

        public Builder setSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder setTokenName(String tokenName) {
            this.tokenName = tokenName;
            return this;
        }

        public TokenDAO build() {
            return new TokenDAO(this);
        }
    }
}