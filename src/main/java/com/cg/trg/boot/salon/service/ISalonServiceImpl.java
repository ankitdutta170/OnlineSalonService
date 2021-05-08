package com.cg.trg.boot.salon.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.trg.boot.salon.bean.SalonService;
import com.cg.trg.boot.salon.dao.IAppointmentRepository;
import com.cg.trg.boot.salon.dao.ISalonRepository;

@Service
public class ISalonServiceImpl implements ISalonService {
	
	@Autowired
	IAppointmentRepository appointmentRepository;

	@Autowired
	ISalonRepository repository;
	
	//1-For adding a salon service
	@Override
	public SalonService addService(SalonService salonService) {
	repository.save(salonService);
		return salonService;
	}

	//2-For removing a salon service
	@Override
	public SalonService removeService(long id) {
		Optional<SalonService> salonServiceToBeDeleted = repository.findById(id);
		repository.deleteById(id);
		if(salonServiceToBeDeleted.isPresent()) {
			return salonServiceToBeDeleted.get();
		}
		else {
			return null;
		}
	}

	//3-For updating a salon service
	@Override
	public SalonService updateService(long id, SalonService salonService) {
		if(repository.existsById(id)) {
			SalonService salonServiceToBeUpdated = repository.findById(id).get();
			repository.save(salonService);
			return salonServiceToBeUpdated;
		}
		return null;
	}

	//4-For getting data of a salon service
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

	//5-For getting data of all salon services
	@Override
	public List<SalonService> getAllServices() {
		List<SalonService> salonService = repository.findAll();
		return salonService;
	}

	//6-For getting count of appointments for a particular service(implemented as Business Method)
	@Override
	public int getCountOfAppointmentsOfServices(long id) {
		// TODO Auto-generated method stub
		return appointmentRepository.getCountofAppointmentsOfService(id).size();
	}


	@Override
	public SalonService getSalonServiceByName(String name) {
		// TODO Auto-generated method stub
		return repository.findByServiceName(name);

	
	@Override
	public boolean update(SalonService salonservice) {
		if (repository.existsById(salonservice.getServiceId())) {
			repository.save(salonservice);
			return true;
		}
		return false;

	}
}
