package com.barclays.cardsystem.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.cardsystem.dto.CardDTO;
import com.barclays.cardsystem.dto.CustomerDTO;
import com.barclays.cardsystem.exception.CardSystemException;
import com.barclays.cardsystem.repository.cardRepository;
import com.barclays.cardsystem.service.CardSystemService;
import com.barclays.cardsystem.utility.cardstodelete;


@RestController
@RequestMapping(value = "/cardsystem")
public class CardSystemAPI {
	  @Autowired  
	    JdbcTemplate jdbc; 
	  @Autowired
	  private cardRepository cardrepository;
	  @Autowired
	  CardSystemService cardSystemService;
	  @Autowired
		private Environment environment;
	  

	@GetMapping(value = "/customers/{customerId}")
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Integer customerId) throws CardSystemException {
		CustomerDTO customer = cardSystemService.getCustomerDetails(customerId);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	@PostMapping(value = "/customers")
	public ResponseEntity<String> addCustomer(@RequestBody CustomerDTO customer) throws CardSystemException {
		Integer customerId = cardSystemService.addCustomer(customer);
		String successMessage = environment.getProperty("API.INSERT_SUCCESS") + customerId;
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}
	@DeleteMapping(value="/customers/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Integer customerId) throws CardSystemException{
	      cardSystemService.deleteCustomer(customerId);
	      String successMessage = "USER DELETED SUCCESSFULLY";
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
		
	}
	
	@DeleteMapping(value="/cards/{customerId}")
	public ResponseEntity<String> deleteCardOfExistingCustomer(@PathVariable Integer customerId,@RequestBody cardstodelete cardIdsToDelete) throws CardSystemException{
		cardSystemService.deleteCardOfExistingCustomer(customerId, cardIdsToDelete.getCardsToDelete());
		 String successMessage = "CARD DELETED SUCCESSFULLY";
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
	@PostMapping(value = "/cards/{customerId}")
	public ResponseEntity<String> issueCardToExistingCustomer(@PathVariable Integer customerId,@RequestBody CardDTO cardDTO) throws CardSystemException {
		 cardSystemService.issueCardToExistingCustomer(customerId, cardDTO);
		//String successMessage = environment.getProperty("API.INSERT_SUCCESS") + customerId;
		 String successMessage = "CARDS ISSUED SUCCESSFULLY TO USER "+customerId.toString();
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}
	
}
