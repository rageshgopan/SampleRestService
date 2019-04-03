package com.myRetail.controller;

import com.myRetail.model.Product;
import com.myRetail.model.ProductPrice;
import com.myRetail.model.ProductTable;
import com.myRetail.service.GetProductDetailService;
import com.myRetail.service.UpdateProductPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UpdateProductController {

    @Autowired
    private UpdateProductPriceService updateProductPriceService;

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT, produces = {"application/json; charset=utf-8"}, consumes = {"application/json; charset=utf-8"})
    @ResponseBody
    public ResponseEntity<Product> updateProductPrice(@PathVariable long id, @Valid @RequestBody Product payload) {

        final ProductTable producTable = updateProductPriceService.updateProductPrice(id, payload.getCurrentPrice());

        return new ResponseEntity<Product>(new Product(id,payload.getName(),new ProductPrice(producTable.getPrice(),producTable.getCurrencyCode())), HttpStatus.ACCEPTED);


    }
}
