package com.immutable.request.accounts;

//import com.immutable.authentication.AuthenticationUser;
//import com.immutable.authorization.AuthorizationUser;
import com.immutable.request.entities.CustodianDB_Handlers;
import com.immutable.request.entities.Entities;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

public class CustodianDAOImpl {
    public  Boolean CustodianAccessibility(){
        return  true;
    }

    @RequestMapping("/createCustodian")
    public  String  createCustodian(@RequestBody UserDAO.Builder createCustodian){
        CustodianDB_Handlers custodianDB = new CustodianDB_Handlers();
        custodianDB.createEntities();
        return "ok";
    }
    @RequestMapping("/updateCustodian")
    public  String updateCustodian(@RequestBody UserDAO.Builder updateCustodian){
        CustodianDB_Handlers custodianDB = new CustodianDB_Handlers();
        custodianDB.updateEntities();
        return "ok";
    }
    @RequestMapping("/deleteCustodian")
    public  String  deleteCustodian(@RequestBody  Long securityId){
        CustodianDB_Handlers custodianDB = new CustodianDB_Handlers();
        custodianDB.deleteEntities();
        return "ok";
    }
    @RequestMapping(value = "/getCustodian")
    public  String getCustodian(@RequestBody Long securityId){
        CustodianDB_Handlers custodianDB = new CustodianDB_Handlers();
        custodianDB.getEntities();
        return "ok";
    }
}
