package com.immutable.request.assets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Asset {
     String associatedUser;
     String assetId;
     String symbol;
     String assetURI;
     long value;
     String assetAddress;
     boolean isValidated;
     boolean isForSale;
     boolean isFungible;

    public Asset() {}

    @JsonCreator
    public Asset(
            @JsonProperty("assetId") String assetId,
            @JsonProperty("symbol") String symbol,
            @JsonProperty("assetURI") String assetURI,
            @JsonProperty("value") long value,
            @JsonProperty("assetAddress") String assetAddress,
            @JsonProperty("isValidated") boolean isValidated,
            @JsonProperty("associatedUser") String associatedUser,
            @JsonProperty("isForSale") boolean isForSale,
            @JsonProperty("isFungible") boolean isFungible
    ) {
        this.assetId = assetId;
        this.symbol = symbol;
        this.assetURI = assetURI;
        this.value = value;
        this.assetAddress = assetAddress;
        this.isValidated = isValidated;
        this.associatedUser = associatedUser;
        this.isForSale = isForSale;
        this.isFungible = isFungible;
    }

    public Asset(Asset.Builder asset){
        this.assetId = asset.assetId;
        this.symbol = asset.symbol;
        this.assetURI = asset.assetURI;
        this.value = asset.value;
        this.assetAddress = asset.assetAddress;
        this.isValidated = asset.isValidated;
        this.associatedUser = asset.associatedUser;
        this.isForSale = asset.isForSale;
        this.isFungible = asset.isFungible;
    }

    public String getAssetId() {
        return assetId;
    }

    public long getValue() {
        return value;
    }

    public String getAssetAddress() {
        return assetAddress;
    }

    public String getAssetURI() {
        return assetURI;
    }

    public String getAssociatedUser() {
        return associatedUser;
    }

    public String getSymbol() {
        return symbol;
    }

    public  static  class  Builder{
        String associatedUser;
        String assetId;
        String symbol;
        String assetURI;
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

        public Builder setAssetId(String assetId) {
            this.assetId = assetId;
            return this;
        }

        public Builder setAssetURI(String assetURI) {
            this.assetURI = assetURI;
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

        public Asset _build(){
            return  new Asset(this);
        }

        public Asset build(){
            return  new Asset(this.assetId,this.symbol,this.assetURI,this.value,this.assetAddress,this.isValidated,this.associatedUser,this.isForSale,this.isFungible);
        }
    }
}
