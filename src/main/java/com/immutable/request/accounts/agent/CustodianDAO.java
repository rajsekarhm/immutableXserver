package com.immutable.request.accounts.agent;

import com.dependencies.utils.ResponseSchema;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/agents")
@CrossOrigin
public class CustodianDAO {

    @GetMapping("/accessibility")
    public ResponseEntity<ResponseSchema<Boolean>> custodianAccessibility() {
        return ResponseSchema.respond(true, HttpStatus.OK, "accessible");
    }

    @PostMapping(value = "/createcustodian", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSchema<Custodian>> createCustodian(@RequestBody Custodian custodian) {
        // TODO: implement persistence
        return ResponseSchema.respond(custodian, HttpStatus.CREATED, "createCustodian");
    }

    @PutMapping(value = "/updatecustodian", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSchema<Custodian>> updateCustodian(@RequestBody Custodian custodian) {
        // TODO: implement persistence
        return ResponseSchema.respond(custodian, HttpStatus.OK, "updateCustodian");
    }

    @DeleteMapping(value = "/deletecustodian", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSchema<Void>> deleteCustodian(@RequestParam long governmentId) {
        // TODO: implement persistence
        return ResponseSchema.respond(null, HttpStatus.OK, "deleteCustodian");
    }

    @GetMapping(value = "/getcustodian", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSchema<Custodian>> getCustodian(@RequestParam long governmentId) {
        // TODO: implement persistence
        return ResponseSchema.respond(null, HttpStatus.NOT_FOUND, "Custodian not found");
    }
}
