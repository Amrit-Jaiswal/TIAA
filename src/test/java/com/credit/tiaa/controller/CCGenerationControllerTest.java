package com.credit.tiaa.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@Ignore
@WebMvcTest(CCGenerationController.class)
public class CCGenerationControllerTest {
    @Autowired
    private MockMvc mockMvc;

 /*   @MockBean
    private CreditCardService mockCreditCardService;*/

    @Test
    public void generateCCNumber() throws Exception {
        /*PowerMockito.mockStatic(Validator.class);
        when(Validator.validateInput(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
        doNothing().when(mockCreditCardService).processCreditCard(Mockito.anyString(),Mockito.anyInt());
*//*
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/ccType=master&ccNumbers=1").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());*/
    }
}