package com.immutable.request.accounts.agent;

import com.dependencies.jedis.IJedis;
import com.dependencies.jedis.JedisImx;
import com.immutable.request.utils.Formatter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/agents")@CrossOrigin
public class CustodianDAOImpl {
    public  Boolean CustodianAccessibility(){
        return  true;
    }

    @PostMapping("/createCustodian")@CrossOrigin
    public  String  createCustodian(@RequestBody CustodianDAO.Builder createCustodian){
        return "ok";
    }
    @PutMapping("/updateCustodian")@CrossOrigin
    public  String updateCustodian(@RequestBody CustodianDAO.Builder updateCustodian){
        return "ok";
    }
    @DeleteMapping("/deleteCustodian")@CrossOrigin
    public  String  deleteCustodian(@RequestParam  long governmentId){
        return "ok";
    }
    @GetMapping(value = "/getCustodian")@CrossOrigin
    public  String getCustodian(@RequestParam long governmentId){
        return "ok";
    }
}
