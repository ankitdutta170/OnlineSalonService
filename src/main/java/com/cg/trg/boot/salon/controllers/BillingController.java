package com.cg.trg.boot.salon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cg.trg.boot.salon.service.BillingServiceImp;

@RestController
@RequestMapping("bill")
public class BillingController { 
	@Autowired
	BillingServiceImp service1;
	
	    @GetMapping(value = "{eid}", produces = { org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
	            org.springframework.http.MediaType.APPLICATION_XML_VALUE })
	    public ResponseEntity<?> getEmployee(@PathVariable("eid") int BillId) {
	        Employee e = service1.getEmployee(BillId);
	    }

}
