package com.cg.trg.boot.salon.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.trg.boot.salon.bean.Address;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Long> {

}
