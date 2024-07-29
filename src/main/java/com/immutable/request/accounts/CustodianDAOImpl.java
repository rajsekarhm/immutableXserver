package com.immutable.request.accounts;

import com.immutable.authentication.AuthenticationUser;
import com.immutable.authorization.AuthorizationUser;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

public class CustodianDAOImpl {
    public  Boolean CustodianAccessibility(){
        return  true;
    }
    @RequestMapping("/createCustodian")
    public  void  createCustodian(@RequestBody CustodianDAO createCustodian){

    }
    @RequestMapping("/updateCustodian")
    public  void updateCustodian(@RequestBody CustodianDAO updateCustodian){

    }
    @RequestMapping("/deleteCustodian")
    public  void  deleteCustodian(@RequestBody CustodianDAO deleteCustodian){

    }
    @RequestMapping(value = "/getCustodian")
    public  void getCustodian(@RequestBody CustodianDAO getUser){

    }
}
