package com.credit.tiaa.service;

import com.credit.tiaa.model.CreditCardDetails;
import com.credit.tiaa.repository.CCRepository;
import com.credit.tiaa.util.Validator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(MockitoJUnitRunner.class)
@PrepareForTest({CreditCardService.class})
public class CreditCardServiceTest {

    @InjectMocks
    private CreditCardService mockCreditCardService;

    @Mock
    private CCGeneratorService mockCcGeneratorService;

    @Mock
    private CCRepository mockCcRepository;

    @Mock
    private Validator mockValidator;

    @Mock
    private CreditCardDetails mockCreditCardDetails;

    private String ccType = "test";
    private int numberOfCards = 1;
    private Set<String> dummyCardNumbers = Stream.of("123", "456")
            .collect(Collectors.toCollection(HashSet::new));

    @Test
    public void processCreditCard() throws Exception {
        when(mockCcGeneratorService.generateCCNumber(ccType, numberOfCards)).thenReturn(dummyCardNumbers);
        when(mockValidator.validateCC(anyString(), eq(ccType))).thenReturn(false);

        mockCreditCardService.processCreditCard(ccType, numberOfCards);

        Mockito.verify(mockCcGeneratorService,times(1)).generateCCNumber(ccType,numberOfCards);
    }

}