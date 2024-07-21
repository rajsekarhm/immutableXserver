package com.immutable.request.accounts;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustodianIMX extends  UserIMX{
    long orgId;
    long securityId;
   public CustodianIMX(){
   }
}
