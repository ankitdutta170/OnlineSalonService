package com.cg.trg.boot.salon.service;


import com.cg.trg.boot.salon.bean.Card;


public interface ICardService {
	public Card addCard(Card card);
	public Card removeCard(long cardId);
	public Card updateCard(long cardId, Card card);
	public Card getCardDetails(long cardId);
}
