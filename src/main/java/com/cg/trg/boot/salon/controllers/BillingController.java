package com.cg.trg.boot.salon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.cg.trg.boot.salon.service.BillingServiceImpl;

@RestController
@RequestMapping("bill")
public class BillingController { 
	@Autowired
	BillingServiceImpl service1;
	
	    }


