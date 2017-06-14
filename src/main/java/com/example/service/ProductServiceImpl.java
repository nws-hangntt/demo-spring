package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Product;
import com.example.repository.ProductRepository;

/**
 * The Class ProductServiceImpl.
 */
@Service("productSevice")
public class ProductServiceImpl implements ProductService {
	
	/** The product repository. */
	@Autowired
	private ProductRepository productRepository;

	/* (non-Javadoc)
	 * @see com.example.service.ProductService#find(java.lang.String)
	 */
	@Override
	public List<Product> find(String filter) {
		return productRepository.findByProductName(filter);
	}
	
	/* (non-Javadoc)
	 * @see com.example.service.ProductService#findOne(int)
	 */
	@Override
	public Product findOne(int id) {
		return productRepository.findOne(id);
	}


	/* (non-Javadoc)
	 * @see com.example.service.ProductService#findAll()
	 */
	@Override
	public List<Product> findAll() {
		return (List<Product>) productRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.example.service.ProductService#save(com.example.model.Product)
	 */
	@Override
	public void save(Product product) {
		product.setProductCompany(product.getProductCompany());
		product.setProductTitle(product.getProductTitle());
		product.setProductName(product.getProductName());
		product.setProductEndDate(product.getProductEndDate());
		product.setProductStartDate(product.getProductStartDate());
		product.setProductGenre(product.getProductGenre());
		product.setProductNumber(product.getProductNumber());
		product.setProductStatus(product.getProductStatus());
		product.setId(product.getId());
		productRepository.save(product);
	}

	/* (non-Javadoc)
	 * @see com.example.service.ProductService#delete(int)
	 */
	@Override
	public void delete(int id) {
		productRepository.delete(id);
	}

}