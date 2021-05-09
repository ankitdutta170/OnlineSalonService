package com.cg.trg.boot.salon.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.trg.boot.salon.bean.Address;
import com.cg.trg.boot.salon.bean.Appointment;
import com.cg.trg.boot.salon.bean.Card;
import com.cg.trg.boot.salon.dao.ICardRepository;

@Service
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
	@Override
	public Card updateCard(long cardId, Card card) {
		if(repository.existsById(cardId)) {
			Card cardToBeUpdated = repository.findById(cardId).get();
			repository.save(card);
			return cardToBeUpdated;
		}
		return null;
	}
	@Override
	public Card getCardDetails(long cardId) {
		java.util.Optional<Card> card= repository.findById(cardId);
		if(card.isPresent()) {
			return card.get();
		}
		else {
		return null;
		}
	}
	@Override
	public List<Card> getCardByName(String cardName) {
		
		return repository.getCardByName(cardName);
	}
	@Override
	public List<Card> getAllCard() {
	
		List<Card> card = repository.findAll();
		return card;
		
	}
	@Override
	public boolean update(Card card) {
		if (repository.existsById(card.getCardId())) {
			repository.save(card);
			return true;
		}
		return false;
	}
}
