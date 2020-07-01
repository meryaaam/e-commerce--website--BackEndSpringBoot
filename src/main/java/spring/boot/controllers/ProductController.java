package spring.boot.controllers;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

import spring.boot.entity.Category;
import spring.boot.entity.Image;
import spring.boot.entity.Product;
import spring.boot.payload.request.ProductInfo;
import spring.boot.payload.request.ProductRequest;
import spring.boot.payload.response.ImageResponse;
import spring.boot.payload.response.ImagesResponse;
import spring.boot.payload.response.MessageResponse;
import spring.boot.payload.response.ProductResponse;
import spring.boot.payload.response.ProductsResponse;
import spring.boot.repository.CategoryRepository;
import spring.boot.repository.ImageRepository;
import spring.boot.repository.ProductRepository;


@CrossOrigin(origins = "http://localhost:8100")
@RestController
@RequestMapping("/api")



public class ProductController {
	
	 @Autowired
	  ProductRepository productRepository;
	 
	 @Autowired
	 CategoryRepository categoryRepository;
	 
		@Autowired
		ImageRepository imageRepository;
		
	
	 @GetMapping("/products")
	 //@PreAuthorize(" hasRole('MODERATOR') or hasRole('ADMIN')")
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
	 
	 @GetMapping("/products/image")
	 //@PreAuthorize(" hasRole('MODERATOR') or hasRole('ADMIN')")
	  public ResponseEntity<?> getAllProductswithImages() 
	 {
		 try {
		      List<Product> products = productRepository.findAll();
		      ImagesResponse imageresponse ;
		      List<ImagesResponse> Response = new ArrayList<ImagesResponse>() ;
		      List<Image> img = new ArrayList<>();
		      Image Img ;
		      for (Product pi: products)
		      {
		    		List<Image> image = imageRepository.findAllByProductId(pi.getId()) ;
		    		 for (Image dto : image) {
						    Img = new Image(dto.getName() , dto.getType(),decompressBytes(dto.getPicByte()) , dto.getProduct()  ) ;
						    img.add(Img) ;	
					        }
		    	  imageresponse = new ImagesResponse(pi.getName(),
		    			  pi.getPrice(),
		    			  pi.getQt() , 
		    			
		    			  pi.getAverageStar() ,
		    			  pi.getCreatedAt() ,
		    			  pi.getCreatedBy() , img);
		    			  
		    	  Response.add(imageresponse);
		    	  
		      }
             
		      
		      if (products.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }

		      return new ResponseEntity<>(Response, HttpStatus.OK );
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
	
	 
	 
	 @GetMapping("/products/i")
	 //@PreAuthorize(" hasRole('MODERATOR') or hasRole('ADMIN')")
	  public ResponseEntity<?> getAllProductswithImage() 
	 {
		 try {
		      List<Product> products = productRepository.findAll();
		      ImagesResponse imageresponse ;
		      List<ImagesResponse> Response = new ArrayList<ImagesResponse>() ;
		      List<Image> img = new ArrayList<>();
		      Image Img ;
		      for (Product pi: products)
		      {
		    		Optional<Image> image = imageRepository.findFirstByProductId(pi.getId()) ;
		    		//Image i = pi.getImages().iterator().next();
		    		Image i = image.get();
						    Img = new Image(i.getName() , i.getType(),decompressBytes(i.getPicByte()) , i.getProduct()  ) ;
						    img.add(Img) ;	
					        
		    	  imageresponse = new ImagesResponse(pi.getName(),
		    			  pi.getPrice(),
		    			  pi.getQt() , 
		    		
		    			  pi.getAverageStar() ,
		    			  pi.getCreatedAt() ,
		    			  pi.getCreatedBy() , img);
		    			  
		    	  Response.add(imageresponse);
		    	  
		      }
             
		      
		      if (products.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }

		      return new ResponseEntity<>(Response, HttpStatus.OK );
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }

	 /*
	 @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	 @PostMapping("/products")
	  public ResponseEntity<Product> createProduct(@RequestBody Product product ) {
	    try {
	    	
	   
	    	
	   Product P = new Product(product.getName(),
	        		  product.getPrice(),
	        		  product.getQt() , 
	        		  product.getCat() ,
	        		  product.getAverageStar() ,
	        	
	        		  product.getCreatedAt() ,
	        		  product.getCreatedBy() , 
	        		  false ,
	        		  
	        		  product.getDesc()  
	        
	        		);
	    	productRepository.save(P) ;
	    	
	
	
			
	
	    	
	      return new ResponseEntity<>(P, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	    }
	   
	  }
	 */
	 
	 @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	 @PostMapping("/products")
	  public ResponseEntity<?> AddProduct(@RequestBody  ProductRequest pi ) {
	    try {	    	  
	
	    	if (! (categoryRepository.existsByName(pi.getCategory()) ) ) {
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse("Error: Category Not Found"));
			}
	    	else {
	    	Product P = new Product(
	    			  pi.getName(),
	    			  pi.getPrice(),
	    			  pi.getQt() , 
	    			 
	    			  pi.getAverageStar() ,
	    			  pi.getCreatedAt() ,
	    			  pi.getCreatedBy() 
	        		);

          //  Optional <Category> c = categoryRepository.findByname(pi.getCategory()) ;
	    	Category cat = categoryRepository.findByname(pi.getCategory()).get() ;
            P.setCategory(cat);

	   productRepository.save(P) ; 	
	      return new ResponseEntity<>(P, HttpStatus.CREATED);
	    }} catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
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
	    	
	    	_Product.setAverageStar(product.getAverageStar());
	    	_Product.setCreatedAt(product.getCreatedAt());
	    	_Product.setDesc(product.getDesc());
	    	
	    
	
	    	
	      return new ResponseEntity<>(productRepository.save(_Product), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	 
	 
	 
	// @PreAuthorize(" hasRole('MODERATOR') or hasRole('ADMIN')")
	 @GetMapping("/products/name={name}")
	  public ResponseEntity<List<Product>> findByname(@PathVariable("name")String name) {
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
	 
	/* @GetMapping("/products/{id}")
	  public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
	    Optional<Product> productData = productRepository.findById(id);

	    if (productData.isPresent()) {
	      return new ResponseEntity<>(productData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	 */
	 @GetMapping("/products/IDF={id}")
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
	 
	 
	/* @GetMapping("/products/category/{cat}")
	 //@PreAuthorize(" hasRole('MODERATOR') or hasRole('ADMIN')")
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
		*/
	 
	 @GetMapping("/products/publish/{p}")
	 //@PreAuthorize(" hasRole('MODERATOR') or hasRole('ADMIN')")
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
	 
	 
	 
	 
	 
	 //retourne les information de produit avec image et category 
	 @GetMapping("/product/{id}")
	  public ResponseEntity<?> getProduct(@PathVariable("id") long id) {
		
	    Optional<Product> productData = productRepository.findById(id);
	    Optional <Category> cat = categoryRepository.findByProductsId(id) ;
	    String name = cat.get().getName();
	 
	    ProductResponse pr = new ProductResponse(productData.get() ,name ) ;
	    if (productData.isPresent()) {
	      return new ResponseEntity<>(pr, HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	 
	 
	 
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
	 
		public static byte[] compressBytes(byte[] data) {
			Deflater deflater = new Deflater();
			deflater.setInput(data);
			deflater.finish();

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			byte[] buffer = new byte[1024];
			while (!deflater.finished()) {
				int count = deflater.deflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			try {
				outputStream.close();
			} catch (IOException e) {
			}
			System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

			return outputStream.toByteArray();
		}
		
		public static byte[] decompressBytes(byte[] data) {
			Inflater inflater = new Inflater();
			inflater.setInput(data);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			byte[] buffer = new byte[1024];
			try {
				while (!inflater.finished()) {
					int count = inflater.inflate(buffer);
					outputStream.write(buffer, 0, count);
				}
				outputStream.close();
			} catch (IOException ioe) {
			} catch (DataFormatException e) {
			}
			return outputStream.toByteArray();
		}
		
		
		
		
}
