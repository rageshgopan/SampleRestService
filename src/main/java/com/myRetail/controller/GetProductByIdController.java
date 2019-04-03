package com.myRetail.controller;

import com.myRetail.model.Product;
import com.myRetail.service.GetProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RestController
public class GetProductByIdController {


    @Autowired
    private GetProductDetailService getProductDetailService;

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        return getProductDetailService.getProductDetail(id);

    }


}
