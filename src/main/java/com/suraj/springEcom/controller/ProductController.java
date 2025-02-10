package com.suraj.springEcom.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.suraj.springEcom.model.Product;
import com.suraj.springEcom.service.ProductService;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/product")
	public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile image){
		Product savedProduct = null;
		try {
			 savedProduct = productService.addOrUpdateProduct(product, image);
			 return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
		} catch (IOException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/products")
	public List<Product> getProduts() {
		return productService.getProduct();
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id) {
		Product product = productService.getProductById(id);
		
		if (product != null) {
			return new ResponseEntity<>(product, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/product/{id}/image")
	public ResponseEntity<byte[]> getImageByProductId(@PathVariable int id) {
		
		Product product = productService.getProductById(id);
		
		if (product != null) {
			return new ResponseEntity<>(product.getImageData(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("product/{id}")
	public ResponseEntity<String> UpdateProduct(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile image) {
		Product updateProduct = null;
		try {
			updateProduct = productService.addOrUpdateProduct(product, image);
			return new ResponseEntity<>("Updated", HttpStatus.CREATED);
		} catch (IOException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id){
		Product product = productService.getProductById(id);
		if(product != null) {
			productService.deleteProduct(id);
			return new ResponseEntity<String>("Deleted", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/product/search")
	public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
		List<Product> products = productService.searchProducts(keyword);
		System.out.println("Searcing for "+ keyword);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	
}
