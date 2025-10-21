package com.mulitpleDB.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mulitpleDB.mysql.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
