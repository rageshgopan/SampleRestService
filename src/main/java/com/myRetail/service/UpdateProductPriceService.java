package com.myRetail.service;

import com.myRetail.Exception.GenericException;
import com.myRetail.Exception.ProductNotFoundException;
import com.myRetail.dao.ProductPriceDao;
import com.myRetail.model.ProductPrice;
import com.myRetail.model.ProductTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductPriceService {

    @Autowired
    private ProductPriceDao productPriceDao;

    public ProductTable updateProductPrice(long id, ProductPrice productPrice) {
        ProductTable productTable = productPriceDao.findPriceById(id);
        if (productTable == null) {
            throw new ProductNotFoundException("Product not found for this product id");
        }

        final ProductTable updatedProductTable = new ProductTable(id, productPrice.getPrice(), productPrice.getCurrencyCode());


        try {
            return productPriceDao.save(updatedProductTable);
        } catch (Exception e) {
            throw new GenericException(e.getMessage());
        }


    }

}
