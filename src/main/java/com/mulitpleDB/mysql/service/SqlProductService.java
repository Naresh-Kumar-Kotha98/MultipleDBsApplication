package com.mulitpleDB.mysql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulitpleDB.mysql.model.Product;
import com.mulitpleDB.mysql.repository.ProductRepository;

@Service
public class SqlProductService {
  
  private final ProductRepository productRepository;

  @Autowired
  public SqlProductService(ProductRepository productRepository) {
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
