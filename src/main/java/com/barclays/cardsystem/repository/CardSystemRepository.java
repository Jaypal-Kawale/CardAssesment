package com.barclays.cardsystem.repository;

import org.springframework.data.repository.CrudRepository;

import com.barclays.cardsystem.entity.Customer;



public interface CardSystemRepository extends CrudRepository<Customer, Integer>{

}
