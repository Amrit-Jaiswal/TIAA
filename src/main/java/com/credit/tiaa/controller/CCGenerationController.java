package com.credit.tiaa.controller;

import com.credit.tiaa.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.credit.tiaa.util.Validator.validateInput;

@RestController
public class CCGenerationController {

    @Autowired
    private CreditCardService creditCardService;

    @GetMapping("/creditcard")
    public ResponseEntity<?> generateCCNumber(
            @RequestParam("ccType")String ccType,
            @RequestParam("ccNumbers")String ccNumbers) throws Exception {

        if(!validateInput(ccType, ccNumbers)){
            return new ResponseEntity<String>("Invalid input", HttpStatus.BAD_REQUEST);
        }

        creditCardService.processCreditCard(ccType, Integer.parseInt(ccNumbers));
        return new ResponseEntity<String>("Request accepted", HttpStatus.ACCEPTED);
    }
}
