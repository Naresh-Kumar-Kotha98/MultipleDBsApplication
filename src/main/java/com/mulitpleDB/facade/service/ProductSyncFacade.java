package com.mulitpleDB.facade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.mulitpleDB.dto.ProductDetailDTO;
import com.mulitpleDB.mongodb.service.MongoProductService;
import com.mulitpleDB.mysql.model.Product;
import com.mulitpleDB.mysql.service.SqlProductService;

@Service
public class ProductSyncFacade {
  
  private final SqlProductService sqlService;
  private final MongoProductService mongoService;
  
  @Autowired
  public ProductSyncFacade(SqlProductService sqlProductService, MongoProductService mongoService) {
    this.sqlService = sqlProductService;
    this.mongoService = mongoService;
  }

  public ProductDetailDTO getProducts(int id) {
    
    com.mulitpleDB.mongodb.model.Product mongoProduct = mongoService.getProduct(id);
    
    Product sqlProduct = sqlService.getProduct(id);
    
    ProductDetailDTO detailDTO= new ProductDetailDTO(sqlProduct.getId(), sqlProduct.getName(), sqlProduct.getCategory(),
        mongoProduct.getPrice(), mongoProduct.getDescription());
    
    return detailDTO;
  }

}
