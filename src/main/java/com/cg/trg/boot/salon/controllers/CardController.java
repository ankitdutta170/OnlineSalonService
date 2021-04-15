package com.cg.trg.boot.salon.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.trg.boot.salon.bean.Card;
import com.cg.trg.boot.salon.bean.Payment;
import com.cg.trg.boot.salon.exceptions.CardNotFoundException;
import com.cg.trg.boot.salon.exceptions.PaymentNotFound;
import com.cg.trg.boot.salon.service.CardImpl;

@CrossOrigin
@RestController
@RequestMapping("card")
public class CardController {
	@Autowired
	private CardImpl repository;
	
	@PostMapping("/addcard")
	public String addCard(@RequestBody Card card) {
		Card no = repository.addCard(card);
		if (no != null) {
			return "Card added successfull";
		} else
			return "Card adding Failed";
	}

	@DeleteMapping("/card/delete/{id}")
	public ResponseEntity<?> removeCard(@PathVariable(value = "id") long cardId) {

		Card cardDetails = repository.getCardDetails(cardId);
		if (cardDetails == null) {
			throw new CardNotFoundException("Request", "Card with CardId id:" + cardId + "not found");
		} else {
			repository.removeCard(cardId);
			return new ResponseEntity<Card>(cardDetails, HttpStatus.OK);
		}
	}
	@PutMapping("/card/update/{id}")
	public String updateCard(@PathVariable(value = "id") long cardId, @RequestBody Card card) {
		Card check = repository.getCardDetails(cardId);
		if(check==null) {
			throw new CardNotFoundException("Request", "Card with CardId id:" + cardId + "not found");
		}
		else {
			repository.updateCard(cardId, card);
			return "Card Successfully Updated";
			
		}
		
	}
		@GetMapping("/card/details/{id}")
		public ResponseEntity<?> getCardDetails(@PathVariable(value = "id") long cardId) {
			Card check = repository.getCardDetails(cardId);
			if (check==null) {
				throw new CardNotFoundException("Request", "Card with payment id: " + cardId + " not found");
			}
			return new ResponseEntity<Card>(check, HttpStatus.OK);
		}
		@GetMapping("/card/name/{name}")
		public List<Card> getPaymentByStatus(@PathVariable("name") String cardName){
			List<Card> card=repository.getCardByName(cardName);
			if(card.size()==0)
				throw new CardNotFoundException("Request", "Card not found");
			else
				return card;
		}
		
		
}
