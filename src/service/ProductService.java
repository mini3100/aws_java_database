package service;

import entity.Product;
import repository.ProductRepository;

public class ProductService {
	
	private ProductRepository productRepository;
	private static ProductService instance;
	
	private ProductService() {
		productRepository = ProductRepository.getInstance();
	}
	
	public static ProductService getInstance() {
		if(instance == null) {
			instance = new ProductService();
		}
		return instance;
	}
	
	public boolean registerProduct(Product product) {
		return productRepository.saveProduct(product) > 0;
	}
	
}
