package com.cg.trg.boot.salon.service;

import java.util.List;
import com.cg.trg.boot.salon.bean.SalonService;

public interface ISalonService {
	public SalonService addService(SalonService salonService);
	public SalonService removeService(long id);
	public SalonService updateService(long id, SalonService salonService);
	public SalonService getService(long id);
	public List<SalonService> getAllServices();
	public int getCountOfAppointmentsOfServices(long id);
}
