package com.immutable.request.accounts;

//import com.immutable.authentication.AuthenticationUser;
//import com.immutable.authorization.AuthorizationUser;
import com.dependencies.jedis.IJedis;
import com.dependencies.jedis.JedisImx;
import com.google.gson.Gson;
import com.immutable.request.entities.CustodianDB_Handlers;
import com.immutable.request.entities.Entities;
import com.immutable.request.utils.Formatter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/agents")
public class CustodianDAOImpl {
    public  Boolean CustodianAccessibility(){
        return  true;
    }

    @RequestMapping("/createCustodian")
    public  String  createCustodian(@RequestBody CustodianDAO.Builder createCustodian){
        IJedis redis = new JedisImx();
        redis.setByString(Long.toString(createCustodian.governmentID), Formatter.toJSON(createCustodian));
//        CustodianDB_Handlers custodianDB = new CustodianDB_Handlers();
//        custodianDB.createEntities();
        return "ok";
    }
    @RequestMapping("/updateCustodian")
    public  String updateCustodian(@RequestBody CustodianDAO.Builder updateCustodian){
          IJedis redis = new JedisImx();
          redis.setByString(Long.toString(updateCustodian.governmentID), Formatter.toJSON(updateCustodian));
//        CustodianDB_Handlers custodianDB = new CustodianDB_Handlers();
//        custodianDB.updateEntities();
        return "ok";
    }
    @RequestMapping("/deleteCustodian")
    public  String  deleteCustodian(@RequestParam  long governmentId){
//        CustodianDB_Handlers custodianDB = new CustodianDB_Handlers();
//        custodianDB.deleteEntities();
        return "ok";
    }
    @RequestMapping(value = "/getCustodian")
    public  String getCustodian(@RequestParam long governmentId){
//        CustodianDB_Handlers custodianDB = new CustodianDB_Handlers();
//        custodianDB.getEntities();
        IJedis jedis = new JedisImx();
        return jedis.getByString(Long.toString(governmentId));
    }
}
