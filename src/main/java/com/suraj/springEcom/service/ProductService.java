package com.suraj.springEcom.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.suraj.springEcom.model.Product;
import com.suraj.springEcom.repo.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo productRepo;
	
	public List<Product> getProduct(){
		return productRepo.findAll();
	}

	public Product getProductById(int id) {
		return productRepo.findById(id).orElse(null);
	}

	public Product addOrUpdateProduct(Product product, MultipartFile image) throws IOException {
		
		product.setImageName(image.getOriginalFilename());
		product.setImageType(image.getContentType());
		product.setImageData(image.getBytes());
		
		return productRepo.save(product);
	}

	public void deleteProduct(int id) {
		productRepo.deleteById(id);
	}

	public List<Product> searchProducts(String keyword) {
		return productRepo.searchProducts(keyword);
	}
	

}
