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

        public Builder setAgentId(long _agentId){
            this.agentId = _agentId;
            return this;
        }
        public  CustodianDAO build(){
            return  new CustodianDAO(this);
        }
    }
}
