package spring.boot.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.boot.entity.Category;
import spring.boot.entity.Product;
import spring.boot.payload.response.CategoryResponse;
import spring.boot.payload.response.MessageResponse;

import spring.boot.repository.CategoryRepository;


@CrossOrigin(origins = "http://localhost:8100")
@RestController
@RequestMapping("/api")


public class CategoryController{
	
	
	 @Autowired
	 CategoryRepository categoryRepository;
	 
	 
	 @GetMapping("/categories")
	 //@PreAuthorize(" hasRole('MODERATOR') or hasRole('ADMIN')")
	  public ResponseEntity<List<Category>> getAllCategories() 
	 {
	    try {
	      List<Category> categories = new ArrayList<Category>();

	    
	    	  categoryRepository.findAll().forEach(categories::add);
	     
	      if (categories.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(categories, HttpStatus.OK );
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	 
	// @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	 @PostMapping("/categories")
	  public ResponseEntity<?> createCategory(@RequestBody Category category ) {
	    try {
          Category C = new Category(category.getName(),
	      category.getProducts());
          
          if (categoryRepository.existsByName(category.getName()))
          {
  			return ResponseEntity.badRequest().body(new MessageResponse("Error: Name is already in exist!"));
  		}
          
          categoryRepository.save(C) ;
	
	    	
          return ResponseEntity.ok(new MessageResponse("Category created successfully!"));
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	    }
	   
	  }
	 
	 
	 
	 @GetMapping("/categories/{name}")
	  public ResponseEntity<?> findByname(@PathVariable("name")String name) {
	    try {
	      Optional<Category> category = categoryRepository.findByname(name);

	      if (category.isEmpty()){
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(category.get(), HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    }
	  }
	 
	 
	 
	 
	 @GetMapping("/categorie/{name}")
	  public ResponseEntity<?> getProductsByName(@PathVariable("name")String name) {
	    try {
	      Optional<Category> category = categoryRepository.findByname(name);
	      
	      List<Product> productData = new ArrayList <Product>();
	      
	      
		    for (Product dto : category.get().getProducts()) {
		    	
		    	
		    	productData.add(dto);

		    	
		    }
	      


	      if (category.isEmpty()){
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(productData, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    }
	  }
	 
	 
	 
	 
	 
	 @GetMapping("/categoriess")
	 //@PreAuthorize(" hasRole('MODERATOR') or hasRole('ADMIN')")
	  public ResponseEntity<?> getAllCategoriesName() 
	 {
	    try {
	      List<Category> categories =categoryRepository.findAll();

	    
	    	
	    	  List<CategoryResponse> categorie = new ArrayList<CategoryResponse>() ;
	    	  CategoryResponse categoryresponse ;
	 		
			    for (Category dto : categories) {
			    	categoryresponse = new CategoryResponse(dto.getId() , dto.getName()) ; 
			    	categorie.add(categoryresponse);
			       
		        }
			    
	     
	      if (categories.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(categorie, HttpStatus.OK );
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	 
	
	
		
		 
	 }
