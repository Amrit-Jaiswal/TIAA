package com.credit.tiaa.util;

import com.credit.tiaa.constants.CardType;
import com.credit.tiaa.model.CreditCardDetails;
import com.credit.tiaa.repository.CCRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class Validator {
    private static final Logger LOGGER = LoggerFactory.getLogger(Validator.class);

    @Autowired
    private CCRepository ccRepository;

    public static boolean validateInput(String ccType, String ccNumber){
        CardType[] cardTypes = CardType.values();
        try {
            Integer.parseInt(ccNumber);
        }catch (NumberFormatException ex){
            throw new NumberFormatException("Invalid input for card numbers");
        }

        Optional<CardType> cardTypeOptional = Arrays.stream(cardTypes)
                .filter(cardType -> ccType.equalsIgnoreCase(cardType.name()))
                .findAny();
        return cardTypeOptional.isPresent();
    }

    public boolean validateCC(String cardNumber, String ccType){
        //length validation
        if(!cardNumberLengthValidation(cardNumber, ccType)){
            LOGGER.error("Length validation failed for card number : {}", cardNumber);
            return false;
        }

        //starting digit validation
        if (!startDigitValidation(cardNumber, ccType)){
            LOGGER.error("Start digit validation failed for card type : {}", ccType);
            return false;
        }

        //existing card number validation
        if (!existingCardValidation(cardNumber)){
            LOGGER.info("card number already exist in database");
            return false;
        }

        return true;
    }

    private boolean cardNumberLengthValidation(String cardNumber, String ccType){
        if(ccType.equalsIgnoreCase(CardType.VISA.name())){
            return cardNumber.length() == Integer.parseInt(CardType.VISA.getLength());
        }else if(ccType.equalsIgnoreCase(CardType.MASTER.name())){
            return cardNumber.length() == Integer.parseInt(CardType.MASTER.getLength());
        }else if(ccType.equalsIgnoreCase(CardType.AMERICAN_EXPRESS.name())){
            return cardNumber.length() == Integer.parseInt(CardType.AMERICAN_EXPRESS.getLength());
        }else if(ccType.equalsIgnoreCase(CardType.DISCOVER.name())){
            return cardNumber.length() == Integer.parseInt(CardType.DISCOVER.getLength());
        }
        return false;
    }

    private boolean startDigitValidation(String cardNumber, String ccType){
        if(ccType.equalsIgnoreCase(CardType.VISA.name())){
            return cardNumber.startsWith(CardType.VISA.getStrtDigit());
        }else if(ccType.equalsIgnoreCase(CardType.MASTER.name())){
            return cardNumber.startsWith(CardType.MASTER.getStrtDigit());
        }else if(ccType.equalsIgnoreCase(CardType.AMERICAN_EXPRESS.name())){
            return cardNumber.startsWith(CardType.AMERICAN_EXPRESS.getStrtDigit());
        }else if(ccType.equalsIgnoreCase(CardType.DISCOVER.name())){
            return cardNumber.startsWith(CardType.DISCOVER.getStrtDigit());
        }
        return false;
    }

    private boolean existingCardValidation(String cardNumber){
        List<CreditCardDetails> creditCardDetails = ccRepository.findByCardNumber(cardNumber);
        return creditCardDetails.size() == 0;
    }
}
