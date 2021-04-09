package com.cg.trg.boot.salon.dao;

import java.util.List;


import com.cg.trg.boot.salon.bean.Billing;

public interface IBillingRepository {
	public Billing addOrder(Billing bill);
	public Billing removeOrder(long id);
	public Billing  updateOrder(long id, Billing bill);
	public Billing  getOrderDetails(long id);
	public List<Billing> getAllOrders(); 
	
}
