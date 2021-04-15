package com.cg.trg.boot.salon.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.trg.boot.salon.bean.Card;
import com.cg.trg.boot.salon.bean.Payment;
import com.cg.trg.boot.salon.dao.ICardRepository;

@SpringBootTest
public class CardServiceTest {
	@Autowired
	CardImpl cardService;
	@MockBean
	ICardRepository cardRepository;

	Card card = new Card(100, "Visa", "123456", LocalDate.of(2026, 8, 25), 356);
	Payment payment = new Payment( "Card", "Paid", card);

	@Test
	@DisplayName("Test for adding Card")
	public void addCardTest() {
	Mockito.when(cardRepository.save(card)).thenReturn(card);
		assertEquals(card, cardService.addCard(card));
	}

	@Test
	@DisplayName("Test for Deleteing card by Id")
	public void deleteCardTest() {
		int cardId = 1;
		cardService.removeCard(cardId);

		Mockito.verify(cardRepository, times(1)).deleteById((long) cardId);
	}

	@Test
	@DisplayName("Test for Updating Card")
	public void updateCardTest() {
		Optional<Card> newCard = cardRepository.findById(1L);
		
		if (newCard.isPresent()) {
			newCard.get().setCardName("Visa");
			Mockito.doReturn(cardRepository.save(newCard.get()));
			assertEquals(card, newCard);
		}
		
	}

	@Test
	@DisplayName("Test for retrive Card Details By id")
	public void getPaymentDetailsTest() {

		Mockito.when(cardRepository.findById(100L)).thenReturn(java.util.Optional.ofNullable(card));

	}
}
