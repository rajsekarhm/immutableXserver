package com.immutable.request.accounts.agent;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.immutable.request.accounts.user.User;

public class Custodian extends User {
    private long orgId;
    private long agentId;

    public Custodian() {}

    private Custodian(Custodian.Builder builder){
        super(builder);
        this.orgId = builder.orgId;
        this.agentId = builder.agentId;
    }

    @JsonCreator
    public Custodian(
            @JsonProperty("orgId") long orgId,
            @JsonProperty("agentId") long agentId
    ) {
        this.orgId = orgId;
        this.agentId = agentId;
    }

    public long getOrgId(){
        return orgId;
    }
    public long getAgentId(){
        return agentId;
    }

    public static class Builder extends User.Builder {
        long orgId;
        long agentId;

        public Builder setUserName(String firstName) {
            super.setFirstName(firstName);
            return this;
        }

        @Override
        public Builder setEmail(String email) {
            super.setEmail(email);
            return this;
        }

        @Override
        public Builder setIsAgent(Boolean isAgent) {
            super.setIsAgent(isAgent);
            return this;
        }

        @Override
        public Builder setPhoneNumber(String phoneNumber) {
            super.setPhoneNumber(phoneNumber);
            return this;
        }

        @Override
        public Builder setEdition(String edition) {
            super.setEdition(edition);
            return this;
        }

        @Override
        public Builder setIsAuthForBuyAndSell(Boolean isAuthForBuyAndSell) {
            super.setIsAuthForBuyAndSell(isAuthForBuyAndSell);
            return this;
        }

        @Override
        public Builder setGovernmentID(Long governmentID) {
            super.setGovernmentID(governmentID);
            return this;
        }

        public Builder setOrgId(long _orgId){
            this.orgId = _orgId;
            return this;
        }

        public Builder setAgentId(long _agentId){
            this.agentId = _agentId;
            return this;
        }

        public Custodian build(){
            return new Custodian(this);
        }
    }
}
