package com.cg.trg.boot.salon.service;

import java.util.List;


import com.cg.trg.boot.salon.bean.Billing;


public interface IBillingService {
	public Billing addBill(Billing bill);
	public Billing removeBill(long id);
	public Billing  updateBill(long id, Billing bill);
	public boolean update(Billing bill);
	public Billing  getBillDetails(long id);
	public List<Billing> getAllBills(); 

}
