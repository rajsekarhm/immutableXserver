package com.immutable.request.assets;

public class AssetDAO {
    String tokenId;
    String symbol;
    String tokenURI;
    long value;
    String assetAddress;
    String ownerAddress;
    Boolean isValidated;

    public  AssetDAO(String _tokenId,
    String _symbol,
    String _tokenURI,
    long _value,
    String _assetAddress,
    String _ownerAddress,
    Boolean _isValidated){
        this.tokenId = _tokenId;
        this.symbol =_symbol;
        this.tokenURI = _tokenURI;
        this.value = _value;
        this.assetAddress =_assetAddress;
        this.ownerAddress = _ownerAddress;
        this.isValidated  = _isValidated;

    }
    public AssetDAO(AssetDAO.Builder asset){
        this.assetAddress = asset.assetAddress;
        this.symbol = asset.symbol;
        this.tokenURI = asset.tokenURI;
        this.tokenId = asset.tokenId;
        this.ownerAddress = asset.ownerAddress;
        this.value = asset.value;
    }

    public Boolean getValidated() {
        return isValidated;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public String getAssetAddress() {
        return assetAddress;
    }

    public long getValue() {
        return value;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getTokenId() {
        return tokenId;
    }

    public String getTokenURI() {
        return tokenURI;
    }

    public  static  class  Builder{
        String tokenId;
        String symbol;
        String tokenURI;
        long value;
        String assetAddress;
        String ownerAddress;
        Boolean isValidated;

        public Builder setAssetAddress(String assetAddress) {
            this.assetAddress = assetAddress;
            return this;
        }

        public Builder setOwnerAddress(String ownerAddress) {
            this.ownerAddress = ownerAddress;
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

        public Builder setValidated(Boolean validated) {
            isValidated = validated;
            return this;
        }

        public AssetDAO _build(){
            return  new AssetDAO(this);
        }

        public  AssetDAO build(){
            return  new AssetDAO(this.tokenId,this.symbol,this.tokenURI,this.value,this.assetAddress,this.ownerAddress,this.isValidated);
        }
    }
}
