package com.cg.trg.boot.salon.service;


import java.time.LocalDate;
import java.util.List;

import com.cg.trg.boot.salon.bean.Card;


public interface ICardService {
	public Card addCard(Card card);
	public Card removeCard(long cardId);
	public Card updateCard(long cardId, Card card);
	public Card getCardDetails(long cardId);
	public List<Card> getCardByName(String cardName);
	}
