package com.credit.tiaa.util;

import org.junit.Assert;
import org.junit.Test;

public class ValidatorTest {

    @Test(expected = NumberFormatException.class)
    public void shouldThrowNumberFormatExceptionForNumberOfCardsAsString() throws Exception {
        String cardType = "VISA";
        String cardNumber = "two";

        Validator.validateInput(cardType, cardNumber);
    }

    @Test
    public void shouldReturnFalseForInvalidCardType(){
        String cardType = "Test";
        String cardNumber = "1";

        boolean actual = Validator.validateInput(cardType, cardNumber);

        Assert.assertFalse(actual);
    }

    @Test
    public void shouldReturnTrueForValidCardType(){
        String cardType = "VISA";
        String cardNumber = "1";

        boolean actual = Validator.validateInput(cardType, cardNumber);

        Assert.assertTrue(actual);
    }

    @Test
    public void shouldReturnFalseForInvalidLengthOfCardNumber() throws Exception {
        String cardType = "VISA";
        String cardNumber = "test";

        Validator validator = new Validator();
        boolean actual = validator.validateCC(cardNumber, cardType);

        Assert.assertFalse(actual);
    }

    @Test
    public void shouldReturnFalseForInvalidStartDigitOfCardNumber() throws Exception {
        String cardType = "VISA";
        String cardNumber = "1234567891234";

        Validator validator = new Validator();
        boolean actual = validator.validateCC(cardNumber, cardType);

        Assert.assertFalse(actual);
    }

}