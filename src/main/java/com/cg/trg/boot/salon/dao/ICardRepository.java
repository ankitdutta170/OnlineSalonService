package com.cg.trg.boot.salon.dao;



import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.trg.boot.salon.bean.Card;


public interface ICardRepository extends JpaRepository<Card, Long>{

	@Query("select card from Card card where cardName = :cardName")
	public List<Card> getCardByName(@Param("cardName")String cardName);

}
