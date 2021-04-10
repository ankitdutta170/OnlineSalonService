package com.cg.trg.boot.salon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.trg.boot.salon.bean.Billing;
import com.cg.trg.boot.salon.dao.IBillingRepository;
@Service
public class BillingServiceImpl implements IBillingService {
	@Autowired
	IBillingRepository repository;

	@Override
	public Billing addBill(Billing bill) {
		// TODO Auto-generated method stub
		repository.save(bill);
		return bill;
		
	}

	@Override
	public Billing removeBill(long id) {
		// TODO Auto-generated method stub
		Optional<Billing> billToBeDeleted = repository.findById(id);
		repository.deleteById(id);
		
		if(billToBeDeleted.isPresent()) {
			return billToBeDeleted.get();
		}
		else {
			return null;
		}
	}

	@Override
	public Billing updateBill(long id, Billing bill) {
		// TODO Auto-generated method stub
		if(repository.existsById(id)) {
			Billing billToBeUpdated = repository.findById(id).get();
			repository.save(bill);
			return billToBeUpdated;
		}
		return null;
	}

	@Override
	public Billing getBillDetails(long id) {
		// TODO Auto-generated method stub
		Optional<Billing> bill= repository.findById(id);
		if(bill.isPresent()) {
			return bill.get();
		}
		else {
		return null;
		}
	}

	@Override
	public List<Billing> getAllBills() {
		// TODO Auto-generated method stub
		List<Billing> bill = repository.findAll();
		return bill;
		
	}

	
}
