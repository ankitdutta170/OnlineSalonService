package com.cg.trg.boot.salon.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cg.trg.boot.salon.bean.SalonService;
import com.cg.trg.boot.salon.exceptions.NoDataException;
import com.cg.trg.boot.salon.exceptions.SalonServiceNotFoundException;
import com.cg.trg.boot.salon.service.ISalonServiceImpl;

@RestController
@RequestMapping("salonservices")
public class SalonServiceController {

	@Autowired
    ISalonServiceImpl service;


	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public String saveSalonService(SalonService salonservice) {
		SalonService saveSalonService = service.addService(salonservice);
		if(saveSalonService != null) {
			return "Service successfully added";
		}
		else
			return "Failed to add service";
	}
	
	@GetMapping("salon/{aid}")
	public ResponseEntity<?> getSalonService(@PathVariable("aid")long id){
		SalonService service1 = service.getService(id);
		if(service1 == null) {
			throw new SalonServiceNotFoundException("Request", "Salon Service with appointment id:"+id+"not found");
		}
		return new ResponseEntity<SalonService>(service1, HttpStatus.OK);
	}
	@GetMapping
	public List<SalonService> getAllServices(){
		List<SalonService>services = service.getAllServices();
		if(services.size() == 0) {
			throw new NoDataException("No Service saved in database");
		}
		return services;
		
	}
    
    @PutMapping("/update/{sid}")
	public String updateSalonService(@PathVariable("sid")long id, @RequestBody SalonService salonservice) {
		SalonService updatedSalonService = service.updateService(id, salonservice);
		if(updatedSalonService != null) {
			return "SalonService succesfully updated";
		}
		else
			return "SalonService failed to update";
	}
    @DeleteMapping("{aid}")
	public String removeSalonService(@PathVariable("aid") long id) {
		SalonService deleteSalonService = service.removeService(id);
		if(deleteSalonService != null) {
			return "SalonService successfully deleted";
		}
		else
			return "SalonService failed to delete";
	}
    @GetMapping("/count/{id}")
    public String getCountOfAppointmentsOfService(@PathVariable("id")long id) {
    	SalonService salonService = service.getService(id);
    	return "No of Appointments for "+salonService.getServiceName()+" is: "+service.getCountOfAppointmentsOfServices(id);
    }
}
