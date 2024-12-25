package com.immutable.request.accounts.agent;

import com.immutable.request.accounts.user.UserDAO;

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
            this.setFirstName(firstName);
            return this;
        }

        public Builder setEmail(String email) {
            this.setEmail(email);
            return this;
        }

        public Builder setIsAgent(Boolean isAgent) {
            this.setIsAgent(isAgent);
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.setPhoneNumber(phoneNumber);
            return this;
        }

        public Builder setEdition(String edition) {
            this.setEdition(edition);
            return this;
        }

        public Builder setIsAuthForBuyAndSell(Boolean isAuthForBuyAndSell) {
            this.setIsAuthForBuyAndSell(isAuthForBuyAndSell);
            return this;
        }

        public Builder setGovernmentID(Long governmentID) {
            this.setGovernmentID(governmentID);
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
