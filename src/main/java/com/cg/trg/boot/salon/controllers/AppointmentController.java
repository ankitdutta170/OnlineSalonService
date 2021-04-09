package com.cg.trg.boot.salon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.trg.boot.salon.service.AppointmentServiceImpl;

@RestController
@RequestMapping("appointments")
public class AppointmentController {
	
	@Autowired
	AppointmentServiceImpl service;
	
	
}
