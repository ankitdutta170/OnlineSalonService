package com.cg.trg.boot.salon.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.cg.trg.boot.salon.bean.Payment;


public interface IPaymentRepository extends JpaRepository<Payment, Long> {
	/*
	 * public Payment addPayment(Payment payment); public Payment removePayment(long
	 * id); public Payment updatePayment(long id, Payment payment); public Payment
	 * getPaymentDetails(long id); public List<Payment> getAllPaymentDetails();
	 */
//	@Query("select payment from Payment payment where type = :'cash'")
//	public List<Payment> getPaymentByCash(@Param("type")String type);
//	@Query("select payment from Payment payment where type = :'card'")
//	public List<Payment> getPaymentByCard(@Param("type")String type);
//	@Query("select payment from Payment payment where status = :'success'")
//	public List<Payment> getPaymentBySuccess(@Param("type")String status);
//	@Query("select payment from Payment payment where type = :'pending'")
//	public List<Payment> getPaymentByPending(@Param("type")String status);
//	

}
	


