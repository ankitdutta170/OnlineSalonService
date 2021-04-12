package com.cg.trg.boot.salon.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.trg.boot.salon.bean.PaymentCash;

public interface IPaymentCashRepository extends JpaRepository<PaymentCash, Long>{

}
