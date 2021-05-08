package com.cg.trg.boot.salon.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.trg.boot.salon.bean.Appointment;
import com.cg.trg.boot.salon.bean.SalonService;
import com.cg.trg.boot.salon.exceptions.AppointmentNotFoundException;
import com.cg.trg.boot.salon.exceptions.NoDataException;
import com.cg.trg.boot.salon.exceptions.SalonServiceNotFoundException;
import com.cg.trg.boot.salon.service.ISalonServiceImpl;

@RestController
@RequestMapping("salonservices")
@CrossOrigin(origins = "http://localhost:4200")
public class SalonServiceController {

	@Autowired
    ISalonServiceImpl service;


	@PostMapping
	public ResponseEntity<String> saveSalonService(@RequestBody SalonService salonservice,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("username");
		System.out.println("*******************" + userName + "*************************");
		System.out.println("*******************" + userId + "*************************");
		SalonService saveSalonService = service.addService(salonservice);
		if(saveSalonService != null) {
			return new ResponseEntity<String>("SalonService saved successfully", HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("SalonService failed to save", HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("{aid}")
    public ResponseEntity<String> removeSalonService(@PathVariable("aid") long id,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("username");
		System.out.println("*******************" + userName + "*************************");
		System.out.println("*******************" + userId + "*************************");
		SalonService deleteSalonService = service.removeService(id);
		if(deleteSalonService != null) {
			return new ResponseEntity<String>("SalonService successfully deleted", HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("SalonService failed to delete", HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/update/{sid}")
    public ResponseEntity<String> updateSalonService(@PathVariable("sid")long id, @RequestBody SalonService salonservice,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("username");
		System.out.println("*******************" + userName + "*************************");
		System.out.println("*******************" + userId + "*************************");
		SalonService updatedSalonService = service.updateService(id, salonservice);
		if(updatedSalonService != null) {
			return new ResponseEntity<String>("SalonService successfully updated", HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("SalonService failed to delete", HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping
	public String updatemployee( @RequestBody SalonService salonservice,HttpServletRequest request) {
		//validateToken(request);
		if (service.update(salonservice))
			return "Salon Service data successfully updated";
		else
			throw new SalonServiceNotFoundException("Update", "Salon Service with Id " + salonservice.getServiceId() + " to update not found");
	}
	
	 @GetMapping("/count/{id}")
	    public ResponseEntity<String> getCountOfAppointmentsOfService(@PathVariable("id")long id,HttpServletRequest request) {
	    	SalonService salonService = service.getService(id);
	    	return new ResponseEntity<String>("No of Appointments for "+salonService.getServiceName()+" is: "+service.getCountOfAppointmentsOfServices(id),HttpStatus.OK);
	    }
	 
	@GetMapping("{aid}")
	public ResponseEntity<?> getSalonService(@PathVariable("aid")long id,HttpServletRequest request){
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("username");
		System.out.println("*******************" + userName + "*************************");
		System.out.println("*******************" + userId + "*************************");
		SalonService salonservice = service.getService(id);
		if(salonservice == null) {
			throw new SalonServiceNotFoundException("Request", "SalonService with service id:"+id+"not found");
		}
		return new ResponseEntity<SalonService>(salonservice,HttpStatus.OK);
	}
	
	@GetMapping
		public ResponseEntity<List<SalonService>> getAllSalonServices(HttpServletRequest request){
			HttpSession session = request.getSession();
			String userId = (String) session.getAttribute("userId");
			String userName = (String) session.getAttribute("username");
			System.out.println("*******************" + userName + "*************************");
			System.out.println("*******************" + userId + "*************************");
		List<SalonService>services = service.getAllServices();
		if(services.size() == 0) {
			throw new NoDataException("No SalonServices saved in database");
		}
		return new ResponseEntity<List<SalonService>>(services, HttpStatus.OK);
		
	}

    
    @GetMapping("/count/{id}")
    public ResponseEntity<String> getCountOfAppointmentsOfService(@PathVariable("id")long id,HttpServletRequest request) {
    	SalonService salonService = service.getService(id);
    	return new ResponseEntity<String>("No of Appointments for "+salonService.getServiceName()+" is: "+service.getCountOfAppointmentsOfServices(id),HttpStatus.OK);
    }
    @GetMapping("/name/{serviceName}")
    public ResponseEntity<SalonService> getServiceByName(@PathVariable("serviceName")String serviceName,HttpServletRequest request) {
    	SalonService salonService = service.getSalonServiceByName(serviceName);
    	return new ResponseEntity<SalonService>(salonService,HttpStatus.OK);
    }

}
