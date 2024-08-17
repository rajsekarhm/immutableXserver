package com.immutable.request.controller;

import com.immutable.request.accounts.CustodianDAO;
import com.immutable.request.accounts.UserDAO;

public class CustodianFactory implements AccountFactory<CustodianDAO> {
    public CustodianDAO create(CustodianDAO createEntity){
        return new CustodianDAO.Builder().build();
    }
}
