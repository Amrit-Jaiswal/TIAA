package com.credit.tiaa.service;

import com.credit.tiaa.constants.CardType;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CCGeneratorService {

    private Random random = new Random(System.currentTimeMillis());

    public Set<String> generateCCNumber(String ccType, int numberOfCards){
        CardType[] cardTypes = CardType.values();
        Set<String> cardNumbers = new HashSet<>();

        Optional<CardType> cardTypeOptional = Arrays.stream(cardTypes)
                .filter(cardType -> cardType.name().equalsIgnoreCase(ccType))
                .findFirst();

        if(cardTypeOptional.isPresent()){
            CardType cardType = cardTypeOptional.get();
            for (int i=0; i<numberOfCards; i++){
                cardNumbers.add(generate(cardType.getStrtDigit(), cardType.getLength()));
            }
        }
        return cardNumbers;
    }

    private String generate(String startNumber, String length) {
        int randomNumberLength = Integer.parseInt(length) - (startNumber.length() + 1);

        StringBuilder builder = new StringBuilder(startNumber);
        for (int i = 0; i < randomNumberLength; i++) {
            int digit = this.random.nextInt(10);
            builder.append(digit);
        }

        // Luhn algorithm to generate the check digit.
        int checkDigit = this.getCheckDigit(builder.toString());
        builder.append(checkDigit);

        return builder.toString();
    }

    private int getCheckDigit(String number) {
        int sum = 0;
        for (int i = 0; i < number.length(); i++) {

            int digit = Integer.parseInt(number.substring(i, (i + 1)));

            if ((i % 2) == 0) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
        }
        int mod = sum % 10;
        return ((mod == 0) ? 0 : 10 - mod);
    }
}
