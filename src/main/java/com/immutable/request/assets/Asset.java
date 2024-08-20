package com.immutable.request.assets;

import com.immutable.request.accounts.UserDAO;

import java.util.Map;
import java.util.Objects;

public class Asset {
    String assetType;
    String blockChain;
    long assetPrice;
    String assetAddress;
    long assetId;
    public Asset(Builder builder){
        this.assetAddress = builder.assetAddress;
        this.assetPrice=builder.assetPrice;
        this.assetType=builder.assetType;
        this.blockChain=builder.blockChain;
        this.assetId = builder.assetId;
    }

    public long getAssetPrice() {
        return assetPrice;
    }

    public String getAssetAddress() {
        return assetAddress;
    }

    public String getAssetType() {
        return assetType;
    }

    public String getBlockChain() {
        return blockChain;
    }

    public long getAssetId() {
        return assetId;
    }

    public  static  class  Builder{
        String assetType;
        String blockChain;
        long assetPrice;
        String assetAddress;
        long assetId;

        public Builder setAssetAddress(String assetAddress) {
            this.assetAddress = assetAddress;
            return this;
        }

        public Builder setAssetPrice(long assetPrice) {
            this.assetPrice = assetPrice;
            return this;
        }

        public Builder setBlockChain(String blockChain) {
            this.blockChain = blockChain;
            return this;
        }

        public Builder setAssetType(String assetType) {
            this.assetType = assetType;
            return this;
        }

        public Builder setAssetId(long assetId) {
            this.assetId = assetId;
            return this;
        }

        public Asset build(){
            return  new Asset(this);
        }
    }
    public String toString() {
        return "Asset{" +
                "assetType='" + assetType + '\'' +
                ", blockChain='" + blockChain + '\'' +
                ", assetPrice=" + assetPrice +
                ", assetAddress='" + assetAddress + '\'' +
                '}';
    }
    public int hashCode() {
        return Objects.hash(assetType, blockChain, assetPrice, assetAddress);
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // Same reference
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false; // Null or different class
        }
        Asset other = (Asset) obj;
        return assetPrice == other.assetPrice &&
                Objects.equals(assetType, other.assetType) &&
                Objects.equals(blockChain, other.blockChain) &&
                Objects.equals(assetAddress, other.assetAddress);
    }
}
