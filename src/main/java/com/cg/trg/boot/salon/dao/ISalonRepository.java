package com.cg.trg.boot.salon.dao;

import java.util.List;

import com.cg.trg.boot.salon.bean.SalonService;

public interface ISalonRepository {
	public SalonService addService(SalonService salonService);
	public SalonService removeService(long id);
	public SalonService updateService(long id, SalonService salonService);
	public SalonService getService(long id);
	public List<SalonService> getAllServices();
	public List<SalonService> getServiceByPrice();
	public List<SalonService> getServiceByDuration();
	
	
}
