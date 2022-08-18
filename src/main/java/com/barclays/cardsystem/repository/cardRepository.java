package com.barclays.cardsystem.repository;

import org.springframework.data.repository.CrudRepository;

import com.barclays.cardsystem.entity.Card;
import com.barclays.cardsystem.entity.Customer;

public interface cardRepository extends CrudRepository<Card, Integer>{

}
