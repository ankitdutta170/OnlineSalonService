package com.cg.trg.boot.salon.service;

import java.util.List;
import com.cg.trg.boot.salon.bean.SalonService;

public interface ISalonService {
	
	//1-For adding a salonservice
	public SalonService addService(SalonService salonService);
	
	//2-For removing a salonservice
	public SalonService removeService(long id);
	
	//3-For updating a salonservice
	public SalonService updateService(long id, SalonService salonService);
	
	//4-For getting data of a salonservice
	public SalonService getService(long id);
	
	//5-For getting data of all salonservices
	public List<SalonService> getAllServices();
	
	//6-For getting count of appointments for a particular service(implemented as Business Method)
	public int getCountOfAppointmentsOfServices(long id);
	
	public SalonService getSalonServiceByName(String name);
	
}
