package com.cg.trg.boot.salon.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cg.trg.boot.salon.bean.Card;
import com.cg.trg.boot.salon.exceptions.CardNotFoundException;
import com.cg.trg.boot.salon.service.CardImpl;

@RestController
public class CardController {
	@Autowired
	private CardImpl repository;
	Logger log = LoggerFactory.getLogger(PaymentController.class);

	@PostMapping("/addcard")
	public String addCard(@RequestBody Card card) {
		log.info("addCard() has been invoked");
		Card no = repository.addCard(card);
		if (no != null) {
			log.info("Card added successfully");
			return "Card added successfull";
		} else {
			log.info("Card adding failed");
			return "Card adding Failed";
		}
	}

	@DeleteMapping("/card/{id}")
	public ResponseEntity<?> removeCard(@PathVariable(value = "id") long cardId) {
		log.info("removeCard() has been invoked");

		Card cardDetails = repository.getCardDetails(cardId);
		if (cardDetails == null) {
			log.info("Card with CardId id: " + cardId + " not found");
			throw new CardNotFoundException("Request", "Card with CardId id:" + cardId + "not found");
		} else {
			log.info("Card with CardId id:" + cardId + "Deleted");
			repository.removeCard(cardId);
			return new ResponseEntity<Card>(cardDetails, HttpStatus.OK);
		}
	}

	@PutMapping("/card/update/{id}")
	public String updateCard(@PathVariable(value = "id") long cardId, Card card) {
		log.info("updateCard() has been invoked");
		Card check = repository.getCardDetails(cardId);
		if (check == null) {
			log.info("Card with CardId id: " + cardId + " not found");
			throw new CardNotFoundException("Request", "Card with CardId id:" + cardId + "not found");
		} else {
			log.info("Card with CardId id: " + cardId + " Updated");
			repository.updateCard(cardId, card);
			return "Payment Successfully Updated";

		}

	}

	@GetMapping("/card/details/{id}")
	public ResponseEntity<?> getCardDetails(@PathVariable(value = "id") long cardId) {
		log.info("getCardDetails() has been invoked");
		Card check = repository.getCardDetails(cardId);
		if (check == null) {
			log.info("Card with CardId id: " + cardId + " not found");
			throw new CardNotFoundException("Request", "Card with payment id: " + cardId + " not found");
		}
		else {
			log.info("Card details with CardId id: " + cardId);
			return new ResponseEntity<Card>(check, HttpStatus.OK);
		}
	}
}
