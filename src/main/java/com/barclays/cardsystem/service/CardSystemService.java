package com.barclays.cardsystem.service;

import java.util.List;

import com.barclays.cardsystem.dto.CardDTO;
import com.barclays.cardsystem.dto.CustomerDTO;
import com.barclays.cardsystem.exception.CardSystemException;

public interface CardSystemService {
	public CustomerDTO getCustomerDetails(Integer customerId) throws CardSystemException;
	public Integer addCustomer(CustomerDTO customerDTO) throws CardSystemException ;
	public void issueCardToExistingCustomer(Integer customerId, CardDTO cardDTO) throws CardSystemException;
	public void deleteCustomer(Integer customerId) throws CardSystemException;
	public void deleteCardOfExistingCustomer(Integer customerId, List<Integer> cardIdsToDelete) throws CardSystemException;

}
