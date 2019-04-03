package com.myRetail.controller;

import com.myRetail.Exception.ExceptionControllerAdvice;
import com.myRetail.model.ExceptionResponse;
import com.myRetail.model.Product;
import com.myRetail.model.ProductPrice;
import com.myRetail.model.ProductTable;
import com.myRetail.service.GetProductDetailService;
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
import org.springframework.http.ResponseEntity;
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
@WebMvcTest(value= GetProductByIdController.class, secure = false)
public class TestController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetProductDetailService getProductDetailService;


    @Test
    public void getProductByIdTest() throws Exception
        {
            Product product = new Product(1500,"Macbook",new ProductPrice(new BigDecimal(1200),"USD"));
            Mockito.when(getProductDetailService.getProductDetail(Mockito.anyLong())).thenReturn(new ResponseEntity<Product>(product, HttpStatus.OK));
            RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/1501");
            MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
            String expected =" {\"id\":1500,\"name\":\"Macbook\",\"current_price\":{\"value\": 1200,\"currency_code\":\"USD\"}}";
            JSONAssert.assertEquals(expected,mvcResult.getResponse().getContentAsString(),true);


    }

    @Test
    public void getProductByInvalidIdTest() throws Exception
    {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/1501a");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().is4xxClientError()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(),response.getStatus());



    }








}
