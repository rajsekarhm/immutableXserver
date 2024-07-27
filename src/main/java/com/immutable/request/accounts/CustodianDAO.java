package com.immutable.request.accounts;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustodianDAO extends UserDAO {
    long orgId;
    long securityId;
    static  public  class  Builder{
        public  CustodianDAO build(){
            return new CustodianDAO();
        }
    }
}
