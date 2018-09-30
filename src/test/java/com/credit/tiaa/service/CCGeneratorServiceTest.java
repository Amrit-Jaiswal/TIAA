package com.credit.tiaa.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.util.Set;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
@PrepareForTest({CCGeneratorService.class})
public class CCGeneratorServiceTest {
    private String ccType = "VISA";
    private int numberOfCards = 2;

    @InjectMocks
    private CCGeneratorService ccGeneratorService;

    @Test
    public void generateCCNumber() throws Exception {
        Set<String> generateCCNumber = ccGeneratorService.generateCCNumber(ccType, numberOfCards);
        Assert.assertEquals(2, generateCCNumber.size());
    }

}