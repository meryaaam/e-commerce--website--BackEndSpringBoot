package spring.boot.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import spring.boot.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);

	List<User> findByusername(String username);
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	// List<User> findByLastname(String lastname);
	
	//Optional<User> findByname(String name) ; 

}