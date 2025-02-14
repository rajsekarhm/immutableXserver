package com.immutable.request.accounts.agent;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/agents")@CrossOrigin
public class CustodianDAO {
    public  Boolean CustodianAccessibility(){
        return  true;
    }

    @PostMapping("/createCustodian")@CrossOrigin
    public  String  createCustodian(@RequestBody Custodian.Builder createCustodian){
        return "ok";
    }
    @PutMapping("/updateCustodian")@CrossOrigin
    public  String updateCustodian(@RequestBody Custodian.Builder updateCustodian){
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
