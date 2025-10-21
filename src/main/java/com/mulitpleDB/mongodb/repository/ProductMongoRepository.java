package com.mulitpleDB.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mulitpleDB.mongodb.model.Product;

@Repository
public interface ProductMongoRepository extends MongoRepository<Product, Integer> {

}
