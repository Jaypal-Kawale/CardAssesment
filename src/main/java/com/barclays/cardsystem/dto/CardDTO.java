package com.barclays.cardsystem.dto;

import java.time.LocalDate;

public class CardDTO {
	private Integer cardId;
	private String cardNumber;
	private LocalDate expiryDate;
	private Integer custId;
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
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
	public Integer getCustId() {
		return custId;
	}
	@Override
	public String toString() {
		return "CardDTO [cardId=" + cardId + ", cardNumber=" + cardNumber + ", expiryDate=" + expiryDate + ", custId="
				+ custId + "]";
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	

}
