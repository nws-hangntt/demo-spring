package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Product;

/**
 * The Interface ProductRepository.
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {
 
 /**
  * Find by product name.
  *
  * @param productName the product name
  * @return the list
  */
 List<Product> findByProductName(String productName);
 
}