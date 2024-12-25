package com.immutable.request.controller;

import com.immutable.request.accounts.agent.CustodianDAO;

public class CustodianFactory implements AccountFactory<CustodianDAO> {
    public CustodianDAO create(CustodianDAO createEntity){
        return new CustodianDAO.Builder().build();
    }
}
