package com.immutable.request.controller;

import com.immutable.request.accounts.CustodianIMX;

public class CustodianFactory implements AccountFactory<CustodianIMX> {
    public  CustodianIMX create(CustodianIMX createEntity){
        return new CustodianIMX.Builder().build();
    }
}
