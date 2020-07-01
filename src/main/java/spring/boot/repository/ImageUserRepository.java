package spring.boot.repository;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.boot.entity.ImageUser;


public interface ImageUserRepository extends JpaRepository<ImageUser, Long> {
	
	Optional<ImageUser> findByName(String name);
   Optional<ImageUser> findByUserId(Long UserId);

	Optional<ImageUser> findByUser(User user);
	
	
	List<ImageUser> findByIdAndUserId(Long Id , Long UID) ;

	}