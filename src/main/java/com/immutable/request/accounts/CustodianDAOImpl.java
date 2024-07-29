package com.immutable.request.accounts;

//import com.immutable.authentication.AuthenticationUser;
//import com.immutable.authorization.AuthorizationUser;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

public class CustodianDAOImpl {
    public  Boolean CustodianAccessibility(){
        return  true;
    }

    @RequestMapping("/createCustodian")
    public  String  createCustodian(@RequestBody UserDAO.Builder createCustodian){
        return "ok";
    }
    @RequestMapping("/updateCustodian")
    public  String updateCustodian(@RequestBody UserDAO.Builder updateCustodian){
        return "ok";
    }
    @RequestMapping("/deleteCustodian")
    public  String  deleteCustodian(@RequestBody  Long securityId){
        return "ok";
    }
    @RequestMapping(value = "/getCustodian")
    public  String getCustodian(@RequestBody Long securityId){
        return "ok";
    }
}
