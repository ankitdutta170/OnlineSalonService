package com.cg.trg.boot.salon.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.trg.boot.salon.bean.Card;


public interface ICardRepository extends JpaRepository<Card, Long>{

	

}
