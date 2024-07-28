package com.immutable.request.accounts;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustodianDAO extends UserDAO {
    long orgId;
    long securityId;
    public static class Builder {
        CustodianDAO custodianDAO;
        public  CustodianDAO build(){
            this.custodianDAO =  new CustodianDAO();
            return  custodianDAO;
        }

    }
}
