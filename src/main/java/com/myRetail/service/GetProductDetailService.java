package com.myRetail.service;

import com.myRetail.Exception.GenericException;
import com.myRetail.Exception.ProductNotFoundException;
import com.myRetail.dao.ProductNameDao;
import com.myRetail.dao.ProductPriceDao;
import com.myRetail.model.Product;
import com.myRetail.model.ProductPrice;
import com.myRetail.model.ProductTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class GetProductDetailService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductNameDao productNameDao;

    @Autowired
    private ProductPriceDao productPriceDao;

    public ResponseEntity<Product> getProductDetail(long id) {
        try {
            CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> productNameDao.GetProductNameById(id));
            CompletableFuture<ProductTable> completableFuture2 = CompletableFuture.supplyAsync(() -> productPriceDao.findPriceById(id));
            CompletableFuture<Product> combinedFuture = completableFuture1.
                    thenCombine(completableFuture2, (name, productTable) -> {
                        if(productTable ==null) {
                            LOGGER.info("Product price not available in database");
                            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "not found");
                        }
                        ProductPrice productPrice = new ProductPrice(productTable.getPrice(), productTable.getCurrencyCode());
                        return new Product(id, name, productPrice);
                    });
            return new ResponseEntity<Product>(combinedFuture.get(), HttpStatus.OK);
        } catch (InterruptedException | ExecutionException e) {

            if (e.getCause() instanceof HttpClientErrorException) {
                LOGGER.info("Invalid parameters/Product not found");
                throw new ProductNotFoundException("Product not found for this product id");

            } else
                LOGGER.error(e.getMessage());
                throw new GenericException(e.getMessage());

        }

       /*final String name =  productNameDao.GetProductNameById(id);
       final ProductTable productTable = productPriceDao.findPriceById(id);
       final ProductPrice productPrice = new ProductPrice(productTable.getPrice(),productTable.getCurrencyCode());
       return new Product(id,name,productPrice);*/


    }

}
