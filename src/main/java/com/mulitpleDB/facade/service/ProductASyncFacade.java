package com.mulitpleDB.facade.service;

import java.util.concurrent.CompletableFuture;

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
public class ProductASyncFacade {

  private final SqlProductService sqlService;
  private final MongoProductService mongoService;

  @Autowired
  public ProductASyncFacade(SqlProductService sqlProductService, MongoProductService mongoService) {
    this.sqlService = sqlProductService;
    this.mongoService = mongoService;
  }

  public ProductDetailDTO getProducts(int id) {

    CompletableFuture<Product> sqlProductFuture = getsqlProduct(id);
    CompletableFuture<com.mulitpleDB.mongodb.model.Product> mongoProductFuture = getmongoProduct(id);

    CompletableFuture.allOf(sqlProductFuture, mongoProductFuture);

    Product sqlProduct = sqlProductFuture.join();
    com.mulitpleDB.mongodb.model.Product mongoProduct = mongoProductFuture.join();

    ProductDetailDTO detailDTO = new ProductDetailDTO(sqlProduct.getId(), sqlProduct.getName(),
        sqlProduct.getCategory(), mongoProduct.getPrice(), mongoProduct.getDescription());

    return detailDTO;
  }

  private CompletableFuture<Product> getsqlProduct(int id) {
    return CompletableFuture.supplyAsync(() -> sqlService.getProduct(id));
  }

  private CompletableFuture<com.mulitpleDB.mongodb.model.Product> getmongoProduct(int id) {
    return CompletableFuture.supplyAsync(() -> mongoService.getProduct(id));
  }

}
