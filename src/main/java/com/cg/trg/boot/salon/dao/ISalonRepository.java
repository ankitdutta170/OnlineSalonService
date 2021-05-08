package com.cg.trg.boot.salon.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.trg.boot.salon.bean.SalonService;

public interface ISalonRepository extends JpaRepository<SalonService, Long> {
	
	/*
	 * public SalonService addService(SalonService salonService); public
	 * SalonService removeService(long id); public SalonService updateService(long
	 * id, SalonService salonService); public SalonService getService(long id);
	 * public List<SalonService> getAllServices(); public List<SalonService>
	 * getServiceByDuration();
	 */
	public SalonService findByServiceName(String name);
}
