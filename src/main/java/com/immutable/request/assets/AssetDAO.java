package com.immutable.request.assets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AssetDAO {
    private String associatedUser;
    private String tokenId;
    private String symbol;
    private String tokenURI;
    private long value;
    private String assetAddress;
    private boolean isValidated;
    private boolean isForSale;
    private boolean isFungible;

    public AssetDAO() {}

    @JsonCreator
    public AssetDAO(
            @JsonProperty("tokenId") String tokenId,
            @JsonProperty("symbol") String symbol,
            @JsonProperty("tokenURI") String tokenURI,
            @JsonProperty("value") long value,
            @JsonProperty("assetAddress") String assetAddress,
            @JsonProperty("isValidated") boolean isValidated,
            @JsonProperty("associatedUser") String associatedUser,
            @JsonProperty("isForSale") boolean isForSale,
            @JsonProperty("isFungible") boolean isFungible
    ) {
        this.tokenId = tokenId;
        this.symbol = symbol;
        this.tokenURI = tokenURI;
        this.value = value;
        this.assetAddress = assetAddress;
        this.isValidated = isValidated;
        this.associatedUser = associatedUser;
        this.isForSale = isForSale;
        this.isFungible = isFungible;
    }

    public AssetDAO(AssetDAO.Builder asset){
        this.tokenId = asset.tokenId;
        this.symbol = asset.symbol;
        this.tokenURI = asset.tokenURI;
        this.value = asset.value;
        this.assetAddress = asset.assetAddress;
        this.isValidated = asset.isValidated;
        this.associatedUser = asset.associatedUser;
        this.isForSale = asset.isForSale;
        this.isFungible = asset.isFungible;
    }

    public String getTokenId() {
        return tokenId;
    }

    public  static  class  Builder{
        String associatedUser;
        String tokenId;
        String symbol;
        String tokenURI;
        long value;
        String assetAddress;
        boolean isValidated;
        boolean isForSale;
        boolean isFungible;

        public Builder setAssetAddress(String assetAddress) {
            this.assetAddress = assetAddress;
            return this;
        }

        public Builder setForSale(boolean forSale) {
            isForSale = forSale;
            return this;
        }

        public Builder setIsFungible(boolean isFungible) {
            this.isFungible = isFungible;
            return this;
        }

        public Builder setAssociatedUser(String associatedUser) {
            this.associatedUser = associatedUser;
            return this;
        }

        public Builder setSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder setTokenId(String tokenId) {
            this.tokenId = tokenId;
            return this;
        }

        public Builder setTokenURI(String tokenURI) {
            this.tokenURI = tokenURI;
            return this;
        }

        public Builder setValue(long value) {
            this.value = value;
            return this;
        }

        public Builder setValidated(boolean validated) {
            isValidated = validated;
            return this;
        }

        public AssetDAO _build(){
            return  new AssetDAO(this);
        }

        public  AssetDAO build(){
            return  new AssetDAO(this.tokenId,this.symbol,this.tokenURI,this.value,this.assetAddress,this.isValidated,this.associatedUser,this.isForSale,this.isFungible);
        }
    }
}
