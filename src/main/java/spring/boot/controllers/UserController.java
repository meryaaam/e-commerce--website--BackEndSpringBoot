package spring.boot.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; 
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.boot.entity.Product;
import spring.boot.entity.User;
import spring.boot.payload.response.JwtResponse;
import spring.boot.repository.UserRepository;
import spring.boot.security.jwt.JwtUtils;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/")
public class UserController {
	
	@Autowired
	AuthenticationManager authenticationManager;


	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	UserRepository userRepository;

	
	@Autowired
	PasswordEncoder encoder;

	 @PreAuthorize("  hasRole('ADMIN')")
	 @GetMapping("users")
	  public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String Username) 
	 {
	    try {
	      List<User> User = new ArrayList<User>();

	      if (Username == null)
	    	  userRepository.findAll().forEach(User::add);
	      else
	    	  userRepository.findByusername(Username).forEach(User::add);

	      if (User.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(User, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	
	 @PreAuthorize(" hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('USER') ")
	 @DeleteMapping("/users/{id}")
	  public ResponseEntity<HttpStatus> deleteUserByID(@PathVariable("id") long id) {
	    try {
	    	userRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    }
	 }
	    
	 @PreAuthorize(" hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('USER') ")
	    @GetMapping("/users/{id}")
		  public ResponseEntity<?> getUserById(@PathVariable("id") long id) {
	  
		    Optional<User> userData = userRepository.findById(id) ;
		  
		 
		    
		    if (userData.isPresent()) {
		    	 return new ResponseEntity<>(userData, HttpStatus.OK);
						
					
					
		    } else {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		  }
	    
	 @PreAuthorize("  hasRole('ADMIN')")
		 @DeleteMapping("/users")
		  public ResponseEntity<HttpStatus> deleteAllUsers() {
		    try {
		    	userRepository.deleteAll();
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    } catch (Exception e) {
		      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		    }

		  }
		

		 
		 
		 @PreAuthorize(" hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('USER') ")
		 @PutMapping("/users/{id}")
		  public ResponseEntity<?> updateUsers(@PathVariable("id") long id, @RequestBody User user) {
		    Optional<User> UserData = userRepository.findById(id);

		    if (UserData.isPresent()) {
		    	User _User = UserData.get();
		    	_User.setEmail(user.getEmail());
		    	//_User.setPassword((encoder.encode(user.getPassword() )) );
		    	_User.setFirstname(user.getFirstname() ) ; 
		    	_User.setLastname(user.getLastname());
		    	_User.setBirthday(user.getBirthday());
		    	_User.setAdr(user.getAdr());
		    	 
		    	_User.setTlf(user.getTlf());
		    	
		      return new ResponseEntity<>(userRepository.save(_User), HttpStatus.OK);
		    } else {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		  }
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
}
