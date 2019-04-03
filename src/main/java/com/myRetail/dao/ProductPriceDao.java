package com.myRetail.dao;


import com.myRetail.model.ProductTable;
import org.springframework.data.cassandra.repository.CassandraRepository;


public interface ProductPriceDao extends CassandraRepository<ProductTable, Long> {

    public ProductTable findPriceById(long id);


}
