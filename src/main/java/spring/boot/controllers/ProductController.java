package spring.boot.controllers;




import java.io.ByteArrayOutputStream;
import java.io.IOException;
//import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.

bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;


import spring.boot.model.Product;

import spring.boot.repository.ProductRepository;


@CrossOrigin(origins = "http://localhost:8100")
@RestController
@RequestMapping("/api")



public class ProductController {
	
	 @Autowired
	  ProductRepository productRepository;
	 
	 private byte[] bytes;
	 
	 
	
	 @GetMapping("/products")
	 @PreAuthorize(" hasRole('MODERATOR') or hasRole('ADMIN')")
	  public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String name) 
	 {
	    try {
	      List<Product> products = new ArrayList<Product>();

	      if (name == null)
	    	  productRepository.findAll().forEach(products::add);
	      else
	    	  productRepository.findByname(name).forEach(products::add);

	      if (products.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(products, HttpStatus.OK );
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	 @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	 @PostMapping("/products")
	  public ResponseEntity<Product> createProduct(@RequestBody Product product ) {
	    try {
	    	
	   
	    	
	   Product P = new Product(product.getName(),
	        		  product.getPrice(),
	        		  product.getQt() , 
	        		  product.getCat() ,
	        		  product.getAverageStar() ,
	        		  product.getImage() , 
	        		  product.getCreatedAt() ,
	        		  product.getCreatedBy() , 
	        		  false ,
	        		  0 ,
	        		  product.getDesc() 
	        
	        		);
	    	productRepository.save(P) ;
	    	
	
	
			
	
	    	
	      return new ResponseEntity<>(P, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	    }
	   
	  }
	 
	// @PostMapping("/upload")
	//	public void uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		//	this.bytes = file.getBytes();
		//}
	 
	 @PreAuthorize(" hasRole('ADMIN')")
	 @DeleteMapping("/products")
	  public ResponseEntity<HttpStatus> deleteAllProducts() {
	    try {
	    	productRepository.deleteAll();
	    	
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT );
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    }

	  }
	
	 @PreAuthorize(" hasRole('MODERATOR') or hasRole('ADMIN')")
	 @PutMapping("/products/{id}")
	  public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product ) {
	    Optional<Product> productData = productRepository.findById(id);

	    if (productData.isPresent()) {
	    	Product _Product = productData.get();
	    	_Product.setName(product.getName());
	    	_Product.setPrice(product.getPrice());
	    	_Product.setQt(product.getQt());
	    	_Product.setCat(product.getCat());
	    	_Product.setAverageStar(product.getAverageStar());
	    	_Product.setImage(product.getImage());
	   
	    	_Product.setCreatedAt(product.getCreatedAt());
	    	
	    	
	    
	    	//_Product.setPicByte(product.getPicByte() );
	    	
	      return new ResponseEntity<>(productRepository.save(_Product), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	 
	 
	 
	 @PreAuthorize(" hasRole('MODERATOR') or hasRole('ADMIN')")
	 @GetMapping("/products/name")
	  public ResponseEntity<List<Product>> findByname(String name) {
	    try {
	      List<Product> products = productRepository.findByname(name);

	      if (products.isEmpty()){
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(products, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    }
	  }
	 
	 //@PreAuthorize(" hasRole('MODERATOR') or hasRole('ADMIN')")


	 
	 @PreAuthorize("  hasRole('ADMIN')")
	 @DeleteMapping("/products/{id}")
	  public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) {
	    try {
	    	productRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    }
	  }
	 
	 @GetMapping("/products/{id}")
	  public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
	    Optional<Product> productData = productRepository.findById(id);

	    if (productData.isPresent()) {
	      return new ResponseEntity<>(productData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	 
	 @GetMapping("/products/F/{id}")
	 @PreAuthorize(" hasRole('MODERATOR') or hasRole('ADMIN')")
	  public ResponseEntity<List<Product>> getFProduct(@PathVariable("id") long id) 
	 {
	    try {
	 
	       List<Product> products = productRepository.findBycreatedBy(id) ;
	     

	      if (products.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(products, HttpStatus.OK );
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  
		}
	 
	 
	 @GetMapping("/products/category/{cat}")
	 @PreAuthorize(" hasRole('MODERATOR') or hasRole('ADMIN')")
	  public ResponseEntity<List<Product>> getcategory(@PathVariable("cat") String cat) 
	 {
	    try {
	 
	       List<Product> products = productRepository.findBycat(cat) ;
	     

	      if (products.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(products, HttpStatus.OK );
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  
		}
	 
	 @GetMapping("/products/publish/{p}")
	 @PreAuthorize(" hasRole('MODERATOR') or hasRole('ADMIN')")
	  public ResponseEntity<List<Product>> getpublishproduct(@PathVariable("p") boolean p) 
	 {
	    try {
	 
	       List<Product> products = productRepository.findByPublish(p) ;
	     

	      if (products.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(products, HttpStatus.OK );
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  
		}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 @PreAuthorize(" hasRole('MODERATOR') or hasRole('ADMIN')")
	 @PutMapping("/products/publish/{id}")
	  public ResponseEntity<Product> publishproduct(@PathVariable("id") long id, @RequestBody Product product ) {
	    Optional<Product> productData = productRepository.findById(id);

	    if (productData.isPresent()) {
	    	Product _Product = productData.get();
	    	_Product.setPublish(product.isPublish());
	   
	    	
	    
	    	//_Product.setPicByte(product.getPicByte() );
	    	
	      return new ResponseEntity<>(productRepository.save(_Product), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
