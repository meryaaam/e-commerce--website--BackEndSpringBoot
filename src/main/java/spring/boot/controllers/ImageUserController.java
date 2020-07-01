package spring.boot.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import spring.boot.entity.Image;
import spring.boot.entity.ImageUser;
import spring.boot.entity.Product;
import spring.boot.entity.User;
import spring.boot.repository.ImageRepository;
import spring.boot.repository.ImageUserRepository;
import spring.boot.repository.UserRepository;
@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping(path = "user/image")
public class ImageUserController {
	
	@Autowired
	ImageUserRepository imageRepository;
	
	
	@Autowired
	UserRepository userRepository;
	
	
	@PostMapping("/upload")
	public BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {

		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		ImageUser img = new ImageUser(file.getOriginalFilename(), file.getContentType(),
				compressBytes(file.getBytes()));
		imageRepository.save(img);
		return ResponseEntity.status(HttpStatus.OK);
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
		
		@GetMapping(path = { "/{uid}" })
		 public ResponseEntity <Image> getPImages(@PathVariable("uid") long uid)  {

			 Optional <ImageUser> retrievedImage = imageRepository.findByUserId(uid);
			 

			  if (retrievedImage.isPresent()) {
				Image img = new Image(retrievedImage.get().getName(), retrievedImage.get().getType(),
						decompressBytes(retrievedImage.get().getPicByte()));
				
				  return new ResponseEntity<>(img,  HttpStatus.OK);
		}
			  else {
			      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			    }
			  }
		
		
		
		
		@PostMapping("/{id}")
		public ResponseEntity<?> createPwithImage(@PathVariable("id") long PID,@RequestParam("imageFile") MultipartFile file ) throws IOException {

			// Product p = new Product() ;
		    
		    Optional<User> userData = userRepository.findById(PID);
		    
		    if (userData.isPresent()) {
		    		
		    		
		    	User _User = userData.get();
		    		
			System.out.println("Original Image Byte Size - " + file.getBytes().length);
			ImageUser img = new ImageUser(file.getOriginalFilename(), file.getContentType(),
					compressBytes(file.getBytes()));
			
			img.setUser(_User);
			imageRepository.save(img);
			
			return new ResponseEntity<>("Image Created successfully", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		    }}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
