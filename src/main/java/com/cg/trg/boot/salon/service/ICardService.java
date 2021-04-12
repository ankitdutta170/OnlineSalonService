package com.cg.trg.boot.salon.service;

import java.time.LocalDate;

import com.cg.trg.boot.salon.bean.Card;


public interface ICardService {
	public Card addCard(Card card);
	public Card removeCard(long cardId);
}
