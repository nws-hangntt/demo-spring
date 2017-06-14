package com.example.service;

import java.util.List;

import com.example.model.Product;

/**
 * The Interface ProductService.
 */
public interface ProductService {

 /**
  * Find.
  *
  * @param filter the filter
  * @return the product
  */
 public List<Product> find(String filter);

 /**
  * Find one.
  *
  * @param id the id
  * @return the product
  */
 public Product findOne(int id);
 
 /**
  * Find all.
  *
  * @return the product
  */
 public List<Product> findAll();

 
 /**
  * Save.
  *
  * @param product the product
  */
 public void save(Product product);

 /**
  * Delete.
  *
  * @param id the id
  */
 public void delete(int id);
}