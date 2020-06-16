package spring.boot.controllers;


import java.awt.print.Pageable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import spring.boot.model.Image;
import spring.boot.model.Product;
import spring.boot.repository.ImageRepository;
import spring.boot.repository.ProductRepository;

@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping(path = "image")
public class ImageController {
	
	
	
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
    ProductRepository productRepository;


	@PostMapping("/upload")
	public BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {

		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		Image img = new Image(file.getOriginalFilename(), file.getContentType(),
				compressBytes(file.getBytes()));
		imageRepository.save(img);
		return ResponseEntity.status(HttpStatus.OK);
	}
	

	@GetMapping(path = { "/get/{imageName}" })
	public Image getImage(@PathVariable("imageName") String imageName) throws IOException {

		final Optional<Image> retrievedImage = imageRepository.findByName(imageName);
		Image img = new Image(retrievedImage.get().getName(), retrievedImage.get().getType(),
				decompressBytes(retrievedImage.get().getPicByte()));
		return img;
	}
	
	
	/*@GetMapping("/product")
	 public ResponseEntity <List<Image>>getpwithImages()   {
		
		
		 List<Image> image = new ArrayList<Image>()  ; 
		 
		
		 
		 List<Image> retrievedImage = imageRepository.findAll();
		 
		  for(int i = 1 ; i < retrievedImage.size(); i++) {
			 
			
		Image img = new Image(retrievedImage.get(i).getName(), retrievedImage.get(i).getType(),
				decompressBytes(retrievedImage.get(i).getPicByte()) , retrievedImage.get(i).getProduct());
		
		image.add(img) ;
		
		  }
	      return new ResponseEntity<>(image,  HttpStatus.OK);
	}
	
	*/
	
	@GetMapping(path = { "/{pid}" })
	 public ResponseEntity <Image> getPImages(@PathVariable("pid") long pid)  {

		 Optional <Image> retrievedImage = imageRepository.findByProductId(pid);
		 

		  if (retrievedImage.isPresent()) {
			Image img = new Image(retrievedImage.get().getName(), retrievedImage.get().getType(),
					decompressBytes(retrievedImage.get().getPicByte()));
			
			  return new ResponseEntity<>(img,  HttpStatus.OK);
	}
		  else {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		  }
	
	

		
		@PostMapping("/product/{id}")
		public BodyBuilder createPwithImage(@PathVariable("id") long PID,@RequestParam("imageFile") MultipartFile file ) throws IOException {

			// Product p = new Product() ;
		    
		    Optional<Product> productData = productRepository.findById(PID);
		    
		    if (productData.isPresent()) {
		    		
		    		
		    	Product _Product = productData.get();
		    		
			System.out.println("Original Image Byte Size - " + file.getBytes().length);
			Image img = new Image(file.getOriginalFilename(), file.getContentType(),
					compressBytes(file.getBytes()));
			
			img.setProduct(_Product);
			imageRepository.save(img);
			
			return ResponseEntity.status(HttpStatus.OK);
		}else {
		      return ResponseEntity.status(HttpStatus.NOT_FOUND);
		    }}
		
		
		
		
		
		
	/* @GetMapping("/posts/{postId}/comments")
		    public <List<Image>> getAllImagesByProductId(@PathVariable (value = "PId") Long PId ){
		       return imageRepository.findByProductId(PId);
		   }
		
		
		
		
		
		*/
		
		
		
		
		
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
		
		
		// uncompress the image bytes before returning it to the angular application
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
			
			
			
			
		
			 @DeleteMapping("/{id}")
			  public ResponseEntity<HttpStatus> deleteImage(@PathVariable("id") long id) {
			    try {
			    	productRepository.deleteById(id);
			      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			    } catch (Exception e) {
			      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
			    }
			  }
		
		
		
			 @PutMapping("/{id}")
			  public BodyBuilder updateImage(@PathVariable("id") long id, @RequestParam("imageFile") MultipartFile file)throws IOException {
			    Optional<Image> ImageData = imageRepository.findById(id);

			    if (ImageData.isPresent()) {
			    	
			    	Image _Image = ImageData.get();
			 	_Image.setName(file.getOriginalFilename());
			    	_Image.setType(file.getContentType());
			    	_Image.setPicByte(compressBytes(file.getBytes()  ) );

			    	
			    	imageRepository.save(_Image) ;
			      return  ResponseEntity.status(HttpStatus.OK);
			    } else {
			    	return  ResponseEntity.status(HttpStatus.NOT_FOUND);
			    }
			  }
		
		
		
	}
