package com.myRetail.controller;

import com.myRetail.model.ProductPrice;
import com.myRetail.model.ProductTable;
import com.myRetail.service.UpdateProductPriceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UpdateProductController.class,secure = false)
public class TestUpdatePriceController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UpdateProductPriceService updateProductPriceService;

    @Test
    public void updateProductTest() throws Exception
    {
        ProductTable productTable = new ProductTable(1500,new BigDecimal(1100),"USD");
        Mockito.when(updateProductPriceService.updateProductPrice(Mockito.anyLong(),Mockito.any(ProductPrice.class))).thenReturn(productTable);
        String input =" {\"id\":1500,\"name\":\"Macbook\",\"current_price\":{\"value\": 1200,\"currency_code\":\"USD\"}}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/products/1500").accept(MediaType.APPLICATION_JSON_UTF8).content(input).contentType(MediaType.APPLICATION_JSON_UTF8);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.ACCEPTED.value(),response.getStatus());
        String expected =" {\"id\":1500,\"name\":\"Macbook\",\"current_price\":{\"value\": 1100,\"currency_code\":\"USD\"}}";
        JSONAssert.assertEquals(expected,mvcResult.getResponse().getContentAsString(),true);

    }

    @Test
    public void updateProductTestWithInvalidData() throws Exception
    {
        String input =" {\"id\":1500,\"name\":\"Macbook\",\"current_price\":{\"value\": 1200,\"currency_code\":null}}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/products/1500").accept(MediaType.APPLICATION_JSON_UTF8).content(input).contentType(MediaType.APPLICATION_JSON_UTF8);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().is4xxClientError()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(),response.getStatus());


    }
}
