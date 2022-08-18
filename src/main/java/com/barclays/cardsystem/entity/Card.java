package com.barclays.cardsystem.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Card {
	@Id
	@Column(name="card_id")
	private Integer cardId;
	private String cardNumber;
	private LocalDate expiryDate;
	@Column(name="cust_id")
	private Integer custId;
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(cardId, cardNumber, custId, expiryDate);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		return Objects.equals(cardId, other.cardId) && Objects.equals(cardNumber, other.cardNumber)
				&& Objects.equals(custId, other.custId) && Objects.equals(expiryDate, other.expiryDate);
	}
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	

}
