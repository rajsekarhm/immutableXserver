package com.immutable.request.accounts;

import java.util.Objects;

public class CustodianDAO extends UserDAO {
    private long orgId;
    private long agentId;
    private  CustodianDAO(CustodianDAO.Builder builder){
        super(builder);
        this.orgId = builder.orgId;
        this.agentId = builder.agentId;
    }
    public  long getOrgId(){
        return  orgId;
    }
    public  long getagentId(){
        return  agentId;
    }
    public static class Builder extends  UserDAO.Builder {
         long orgId;
         long agentId;

        public Builder setUserName(String firstName) {
            this.firstName = firstName;
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

        public Builder setPhoneNumber(String phoneNumber) {
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

        public Builder setagentId(long _agentId){
            this.agentId = _agentId;
            return this;
        }
        public  CustodianDAO build(){
            return  new CustodianDAO(this);
        }
    }

    @Override
    public String toString() {
        return "CustodianDAO{" +
                "userName='" + getFirstName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", isAgent=" + getIsAgent() +
                ", phoneNumber=" + getPhoneNumber() +
                ", edition='" + getEdition() + '\'' +
                ", isAuthForBuyAndSell=" + getIsAuthForBuyAndSell() +
                ", governmentID=" + getGovernmentID() +
                ", orgId=" + orgId +
                ", agentId=" + agentId +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orgId, agentId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustodianDAO)) return false;
        if (!super.equals(o)) return false;
        CustodianDAO that = (CustodianDAO) o;
        return orgId == that.orgId && agentId == that.agentId;
    }
}
