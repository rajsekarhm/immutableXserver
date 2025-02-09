package com.immutable.request.controller;

import com.immutable.request.accounts.agent.Custodian;

public class CustodianFactory implements AccountFactory<Custodian> {
    public Custodian create(Custodian createEntity){
        return new Custodian.Builder().build();
    }
}
