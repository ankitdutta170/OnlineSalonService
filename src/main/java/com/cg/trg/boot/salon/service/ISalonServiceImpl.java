package com.cg.trg.boot.salon.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;


import com.cg.trg.boot.salon.bean.SalonService;
import com.cg.trg.boot.salon.dao.ISalonRepository;

public class ISalonServiceImpl implements ISalonService {
	ISalonServiceImpl id = new ISalonServiceImpl();

	@Autowired
	ISalonRepository repository;
	@Override
	public SalonService addService(SalonService salonService) {
	repository.save(salonService);
		return salonService;
	}

	@Override
	public SalonService removeService(long id) {
		Optional<SalonService> salonServiceToBeDeleted = repository.findById(id);
		repository.deleteById(id);
		return null;
	}

	@Override
	public SalonService updateService(long id, SalonService salonService) {
		if(repository.existsById(id)) {
			SalonService salonServiceToBeUpdated = repository.findById(id).get();
			repository.save(salonService);
			return salonServiceToBeUpdated;
		}
		return null;
		
	}

	@Override
	public SalonService getService(long id) {
		Optional<SalonService> salonService= repository.findById(id);
		if(salonService.isPresent()) {
			return salonService.get();
		}
		else {
		return null;
		}
		
	}

	@Override
	public List<SalonService> getAllServices() {
		List<SalonService> salonService = repository.findAll();
		return salonService;
		
	}

	

	

	
	
}