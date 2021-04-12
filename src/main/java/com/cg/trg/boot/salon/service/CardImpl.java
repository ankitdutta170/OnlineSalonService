package com.cg.trg.boot.salon.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.trg.boot.salon.bean.Card;
import com.cg.trg.boot.salon.bean.Payment;
import com.cg.trg.boot.salon.dao.ICardRepository;


public class CardImpl implements ICardService {
	
	@Autowired
	ICardRepository repository;
	@Override
	public Card addCard(Card card) {
		repository.save(card);
		return card;
	}
	public Card removeCard(long cardId) {
		java.util.Optional<Card> cardToBeDeleted =  repository.findById(cardId);
		repository.deleteById(cardId);
		
		if(cardToBeDeleted.isPresent()) {
			return cardToBeDeleted.get();
		}
		else {
			return null;
		}
	}
}
