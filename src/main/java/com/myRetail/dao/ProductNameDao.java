package com.myRetail.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myRetail.Exception.GenericException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductNameDao {

    @Value("${externalProductAPI}")
    private String endPoint;


    public String GetProductNameById(long id) {


        final RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> httpMessageConverterList = new ArrayList<>();
        httpMessageConverterList.add(new StringHttpMessageConverter());
        restTemplate.setMessageConverters(httpMessageConverterList);
        final ResponseEntity<String> httpResponse = restTemplate.getForEntity(endPoint, String.class, id);
        final String responseString = httpResponse.getBody();


        try {
            final ObjectMapper mapper = new ObjectMapper();

            final JsonNode root = mapper.readTree(responseString);
            return root.get("product").get("item").get("product_description").get("title").textValue();
        } catch (IOException | RuntimeException ex) {
            throw new GenericException(ex.getMessage());
        }


    }


}
