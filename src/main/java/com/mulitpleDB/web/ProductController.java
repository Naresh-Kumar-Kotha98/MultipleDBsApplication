package com.mulitpleDB.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mulitpleDB.dto.ProductDetailDTO;
import com.mulitpleDB.facade.service.ProductASyncFacade;
import com.mulitpleDB.facade.service.ProductSyncFacade;
import com.mulitpleDB.mongodb.service.MongoProductService;
import com.mulitpleDB.mysql.model.Product;
import com.mulitpleDB.mysql.service.SqlProductService;

@RestController
@RequestMapping(value="/products")
public class ProductController {
  
  private final SqlProductService sqlService;
  private final MongoProductService mongoService;
  private final ProductSyncFacade syncFacadeService;
  private final ProductASyncFacade asyncFacadeService;
  
  @Autowired
  public ProductController(SqlProductService sqlProductService, MongoProductService mongoService,
      ProductSyncFacade syncFacadeService, ProductASyncFacade asyncFacadeService) {
    this.sqlService = sqlProductService;
    this.mongoService = mongoService;
    this.syncFacadeService = syncFacadeService;
    this.asyncFacadeService = asyncFacadeService;
  }
  
  @PostMapping("/mongo")
  public ResponseEntity<com.mulitpleDB.mongodb.model.Product> saveProductSQL(@RequestBody com.mulitpleDB.mongodb.model.Product product) {
    com.mulitpleDB.mongodb.model.Product savedProduct = mongoService.saveProduct(product);
    return new ResponseEntity<com.mulitpleDB.mongodb.model.Product>(savedProduct, HttpStatus.CREATED);
  }
  
  @PostMapping("/mysql")
  public ResponseEntity<Product> saveProductMongo(@RequestBody Product product) {
    Product savedProduct = sqlService.saveProduct(product);
    return new ResponseEntity<Product>(savedProduct, HttpStatus.CREATED);
  }
  
  @GetMapping("/mongo/{id}")
  public ResponseEntity<com.mulitpleDB.mongodb.model.Product> getMongoProductById(@PathVariable int id) {
    com.mulitpleDB.mongodb.model.Product product = mongoService.getProduct(id);
    return new ResponseEntity<com.mulitpleDB.mongodb.model.Product>(product, HttpStatus.OK);
  }
  
  @GetMapping("/mysql/{id}")
  public ResponseEntity<Product> getSqlProductById(@PathVariable int id) {
    Product product = sqlService.getProduct(id);
    return new ResponseEntity<Product>(product, HttpStatus.OK);
  }
  
  @GetMapping("/sync/{id}")
  public ResponseEntity<ProductDetailDTO> getProductBySync(@PathVariable int id) {
    ProductDetailDTO productDTO = syncFacadeService.getProducts(id);
    return new ResponseEntity<ProductDetailDTO>(productDTO, HttpStatus.OK);
  }
  
  @GetMapping("/async/{id}")
  public ResponseEntity<ProductDetailDTO> getProductByASync(@PathVariable int id) {
    ProductDetailDTO productDTO = asyncFacadeService.getProducts(id);
    return new ResponseEntity<ProductDetailDTO>(productDTO, HttpStatus.OK);
  }
}
