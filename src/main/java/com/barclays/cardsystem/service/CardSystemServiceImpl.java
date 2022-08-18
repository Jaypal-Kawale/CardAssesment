package com.barclays.cardsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barclays.cardsystem.dto.CardDTO;
import com.barclays.cardsystem.dto.CustomerDTO;
import com.barclays.cardsystem.entity.Card;
import com.barclays.cardsystem.entity.Customer;
import com.barclays.cardsystem.exception.CardSystemException;
import com.barclays.cardsystem.repository.CardSystemRepository;
import com.barclays.cardsystem.repository.cardRepository;



@Service(value = "customerService")
@Transactional
public class CardSystemServiceImpl implements CardSystemService {
    @Autowired
    private cardRepository cardrepository; 
	@Autowired
	private CardSystemRepository cardSystemRepository;
	@Override
	public CustomerDTO getCustomerDetails(Integer customerId) throws CardSystemException {
		Optional<Customer> optional = cardSystemRepository.findById(customerId);
		Customer customer = optional.orElseThrow(() -> new CardSystemException("Service.INVALID_CUSTOMERID"));
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(customer.getCustomerId());
		customerDTO.setName(customer.getName());
		customerDTO.setEmailId(customer.getEmailId());
		customerDTO.setDateOfBirth(customer.getDateOfBirth());
		List<Card>cards=new ArrayList<>();
		cards.addAll(customer.getCards());
		List<CardDTO>carddtos=new ArrayList<>();
		cards.forEach(c->{
			CardDTO c1=new CardDTO();
			c1.setCardId(c.getCardId());
			c1.setCardNumber(c.getCardNumber());
			c1.setExpiryDate(c.getExpiryDate());
			carddtos.add(c1);
		});
	
		
		return customerDTO;
			
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer addCustomer(CustomerDTO customerDTO) throws CardSystemException {
		// TODO Auto-generated method stub
		Customer customer = new Customer();
		customer.setCustomerId(customerDTO.getCustomerId());
		customer.setDateOfBirth(customerDTO.getDateOfBirth());
		customer.setEmailId(customerDTO.getEmailId());
		customer.setName(customerDTO.getName());
		List<CardDTO>c=new ArrayList<>();
		c.addAll(customerDTO.getCards());
		
		List<Card>cards=new ArrayList<>();
		
		c.forEach(c1->{
			Card c2=new Card();
			c2.setCardId(c1.getCardId());
			c2.setCardNumber(c1.getCardNumber());
			c2.setExpiryDate(c1.getExpiryDate());
			cards.add(c2);
		});
		customer.setCards(cards);
		cardSystemRepository.save(customer);
		return customer.getCustomerId();
	}

	@Override
	public void issueCardToExistingCustomer(Integer customerId, CardDTO cardDTO) throws CardSystemException {
		Card card=new Card();
		card.setCardId(cardDTO.getCardId());
		card.setCardNumber(cardDTO.getCardNumber());
		card.setExpiryDate(cardDTO.getExpiryDate());
		card.setCustId(customerId);
		cardrepository.save(card);
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCustomer(Integer customerId) throws CardSystemException {
		// TODO Auto-generated method stub
		Optional<Customer> optional = cardSystemRepository.findById(customerId);
		Customer customer = optional.orElseThrow(() -> new CardSystemException("Service.INVALID_CUSTOMERID"));
		cardSystemRepository.delete(customer);
		
	}

	@Override
	public void deleteCardOfExistingCustomer(Integer customerId, List<Integer> cardIdsToDelete)
			throws CardSystemException {
		
		cardIdsToDelete.forEach(x->{
			Optional<Card>optional = cardrepository.findById(x);
			try {
				Card c = optional.orElseThrow(() -> new CardSystemException("Service.INVALID_CUSTOMERID"));
				cardrepository.delete(c);
				System.out.print("Cards Deleted Successfully");
				
			} catch (CardSystemException e) {
				// TODO Auto-generated catch block
				System.out.print("error"+e.toString());
				e.printStackTrace();
			}
			
		});		
		
		// TODO Auto-generated method stub
		
	}
	
	

}
