package com.credit.tiaa.service;

import com.credit.tiaa.model.CreditCardDetails;
import com.credit.tiaa.repository.CCRepository;
import com.credit.tiaa.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;


@Service
public class CreditCardService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardService.class);

    @Autowired
    private CCGeneratorService ccGeneratorService;

    @Autowired
    private  CCRepository ccRepository;

    @Autowired
    private Validator validator;

    @Async
    public void processCreditCard(String ccType, int numberOfCards){
        //generate cc number
        Set<String> cardNumbers = ccGeneratorService.generateCCNumber(ccType, numberOfCards);

        //validate cc number
        cardNumbers.stream()
                .forEach(cardNumber -> {
                    if (validator.validateCC(cardNumber, ccType)){
                        ccRepository.save(new CreditCardDetails(cardNumber, ccType.toUpperCase(), LocalDate.now()));
                    }
                });
         LOGGER.info("Process Completed");
    }
}
