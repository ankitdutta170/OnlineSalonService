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
	        if (e == null)
	            throw new EmployeeNotFoundException("Request","Employee with employee id:" + empId + "not found");

	 

	        return new ResponseEntity<Employee>(e, HttpStatus.OK);

	 

	    }

	 

	    @GetMapping
	    public List<Employee> getAllEmployee() {
	        List<Employee> list = service.getAll();
	        if(list.size() == 0)
	            throw new EmptyDataException("No Employees in database");
	        return list;
	    }

	 

	    @PostMapping(consumes = { org.springframework.http.MediaType.APPLICATION_JSON_VALUE})
	    public String saveEmployee(@Valid @RequestBody Employee e) {
	        service.add(e);
	        return "Employee data successfully saved";

	 

	    }

	 

	    @PostMapping("{eid}/{name}/{salary}")
	    public String updateEmployee(@PathVariable("eid") int empId, @PathVariable("name") String name,
	            @PathVariable("salary") double salary) {
	        Employee emp = new Employee();
	        emp.setEmpId(empId);
	        emp.setName(name);
	        emp.setSalary(salary);
	        if (service.update(emp)) {
	            return "Employee Data successfully updated";
	        } else {
	            return "Employee Data is duplicate";
	        }
	    }

	 

	    @PatchMapping("{eid}/{salary}")
	    public String updateSalary(@PathVariable("eid") int empId, @PathVariable("salary") double salary) {
	        Employee e = service.getEmployee(empId);
	        if (e == null) {
	            return "Employee with salary not found";
	        }
	        e.setSalary(salary);
	        service.update(e);
	        return "Employee updated successfully";
	    }

	 

	    @PutMapping
	    public String updateEmployee(@Valid Employee e) {
	        if (service.update(e)) {
	            return "Employee Data successfully updated";
	        } else {
	            throw new EmployeeNotFoundException("Update","Employee with id"+e.getEmpId()+"not found");
	        }
	    }

	 

	    @DeleteMapping("{eid}")
	    public String deleteEmployee(@PathVariable("eid") int empId) {
	        if (service.delete(empId)) {
	            return "Employee data deleted successfully";

	 

	        } else {
	            throw new EmployeeNotFoundException("Delete","Employee with id"+empId+" to delete not found");
	        }
	    }
	

}
