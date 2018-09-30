package com.credit.tiaa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name="CreditCard")
public class CreditCardDetails {

    @Id
    @GeneratedValue
    private int slNo;
    private String cardNumber;
    private String cardType;
    private LocalDate date;

    public CreditCardDetails(String cardNumber, String cardType, LocalDate date) {
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.date = date;
    }

    public int getSlNo() {
        return slNo;
    }

    public void setSlNo(int slNo) {
        this.slNo = slNo;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
