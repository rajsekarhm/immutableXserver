package com.immutable.request.accounts;

import java.util.Objects;

public class CustodianDAO extends UserDAO {
    private long orgId;
    private long securityId;
    private  CustodianDAO(CustodianDAO.Builder builder){
        super(builder);
        this.orgId = builder.orgId;
        this.securityId = builder.securityId;
    }
    public  long getOrgId(){
        return  orgId;
    }
    public  long getSecurityId(){
        return  securityId;
    }
    public static class Builder extends  UserDAO.Builder {
         long orgId;
         long securityId;

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setIsAgent(Boolean isAgent) {
            this.isAgent = isAgent;
            return this;
        }

        public Builder setPhoneNumber(Long phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setEdition(String edition) {
            this.edition = edition;
            return this;
        }

        public Builder setIsAuthForBuyAndSell(Boolean isAuthForBuyAndSell) {
            this.isAuthForBuyAndSell = isAuthForBuyAndSell;
            return this;
        }

        public Builder setGovernmentID(Long governmentID) {
            this.governmentID = governmentID;
            return this;
        }

        public  Builder setOrgId(long _orgId){
            this.orgId = _orgId;
            return  this;
        }

        public Builder setSecurityId(long _securityId){
            this.securityId = _securityId;
            return this;
        }
        public  CustodianDAO build(){
            return  new CustodianDAO(this);
        }
    }

    @Override
    public String toString() {
        return "CustodianDAO{" +
                "userName='" + getUserName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", isAgent=" + getIsAgent() +
                ", phoneNumber=" + getPhoneNumber() +
                ", edition='" + getEdition() + '\'' +
                ", isAuthForBuyAndSell=" + getIsAuthForBuyAndSell() +
                ", governmentID=" + getGovernmentID() +
                ", orgId=" + orgId +
                ", securityId=" + securityId +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orgId, securityId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustodianDAO)) return false;
        if (!super.equals(o)) return false;
        CustodianDAO that = (CustodianDAO) o;
        return orgId == that.orgId && securityId == that.securityId;
    }
}
