package com.cg.trg.boot.salon.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.trg.boot.salon.bean.Billing;
@Repository
public interface IBillingRepository extends JpaRepository<Billing, Long>{
	@Query("select billing from Billing billing where user_id_fk=:id")
	public List<Billing> getAllBillsForCustomer(@Param("id")long id);
	/*
	 * public Billing addOrder(Billing bill); public Billing removeOrder(long id);
	 * public Billing updateOrder(long id, Billing bill); public Billing
	 * getOrderDetails(long id); public List<Billing> getAllOrders();
	 */
	
}
