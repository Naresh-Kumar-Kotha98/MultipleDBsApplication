package com.mulitpleDB.mongodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulitpleDB.mongodb.model.Product;
import com.mulitpleDB.mongodb.repository.ProductMongoRepository;

@Service
public class MongoProductService {
  
  private final ProductMongoRepository productRepository;

  @Autowired
  public MongoProductService(ProductMongoRepository productRepository) {
    this.productRepository = productRepository;
  }
  
  public Product saveProduct(Product product) {
    return productRepository.save(product);
  }
  
  public Product getProduct(int id) {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return productRepository.findById(id).orElse(new Product());
  }
  
}
