package com.cg.trg.boot.salon.controllers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

import com.cg.trg.boot.salon.bean.Appointment;
import com.cg.trg.boot.salon.bean.Card;
import com.cg.trg.boot.salon.exceptions.CardNotFoundException;
import com.cg.trg.boot.salon.exceptions.EmptyDataException;
import com.cg.trg.boot.salon.service.CardImpl;

@RestController
@RequestMapping("/cards")
@CrossOrigin(origins = "http://localhost:4200")
public class CardController {
	@Autowired
	private CardImpl repository;
	
	@PostMapping
	public ResponseEntity<String> addCard(@RequestBody Card card,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("username");
		Card no = repository.addCard(card);
		if (no != null) {
			return new ResponseEntity<String>("Card added successfull",HttpStatus.OK);
		} else
			return new ResponseEntity<String>("Card adding Faild successfull",HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeCard(@PathVariable(value = "id") long cardId,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("username");
		System.out.println("*******************" + userName + "*************************");
		System.out.println("*******************" + userId + "*************************");

		Card cardDetails = repository.getCardDetails(cardId);
		if (cardDetails == null) {
			throw new CardNotFoundException("Request", "Card with CardId id:" + cardId + "not found");
		} else {
			repository.removeCard(cardId);
			return new ResponseEntity<Card>(cardDetails, HttpStatus.OK);
		}
	}
	@PutMapping("update/{id}")
	public ResponseEntity<String> updateCard(@PathVariable(value = "id") long cardId, @RequestBody Card card,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("username");
		System.out.println("*******************" + userName + "*************************");
		System.out.println("*******************" + userId + "*************************");
		Card check = repository.getCardDetails(cardId);
		if(check==null) {
			throw new CardNotFoundException("Request", "Card with CardId id:" + cardId + "not found");
		}
		else {
			repository.updateCard(cardId, card);
			return new ResponseEntity<String>("Card Updated successfull",HttpStatus.OK);
			
		}
		
	}
		@GetMapping("/{id}")
		public ResponseEntity<?> getCardDetails(@PathVariable(value = "id") long cardId,HttpServletRequest request) {
			HttpSession session = request.getSession();
			String userId = (String) session.getAttribute("userId");
			String userName = (String) session.getAttribute("username");
			System.out.println("*******************" + userName + "*************************");
			System.out.println("*******************" + userId + "*************************");
			Card check = repository.getCardDetails(cardId);
			if (check==null) {
				throw new CardNotFoundException("Request", "Card with payment id: " + cardId + " not found");
			}
			return new ResponseEntity<Card>(check, HttpStatus.OK);
		}
//		@GetMapping("/{name}")
//		public List<Card> getPaymentByStatus(@PathVariable("name") String cardName,HttpServletRequest request){
//			HttpSession session = request.getSession();
//			String userId = (String) session.getAttribute("userId");
//			String userName = (String) session.getAttribute("username");
//			System.out.println("*******************" + userName + "*************************");
//			System.out.println("*******************" + userId + "*************************");
//			List<Card> card=repository.getCardByName(cardName);
//			if(card.size()==0)
//				throw new CardNotFoundException("Request", "Card not found");
//			else
//				return card;
//		}
		@GetMapping
		public ResponseEntity<List<Card>> getAllCards(HttpServletRequest request){
			
			System.out.println("Sontroller is called");
			List<Card> cards = repository.getAllCard();
		
			if(cards.size() == 0) {
				throw new EmptyDataException("No Cards saved in database");
			}
			return new ResponseEntity<List<Card>>(cards, HttpStatus.OK);
			
		}
		
		
}
