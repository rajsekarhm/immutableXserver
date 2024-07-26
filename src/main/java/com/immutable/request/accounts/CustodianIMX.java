package com.immutable.request.accounts;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustodianIMX extends  UserIMX{
    long orgId;
    long securityId;
     public static class Builder {
         CustodianIMX custodianIMX;
        public  CustodianIMX build(){
            this.custodianIMX =  new CustodianIMX();
            return  custodianIMX;
        }

    }
}
