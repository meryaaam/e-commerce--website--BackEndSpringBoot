package spring.boot.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.boot.model.Product;



public interface ProductRepository  extends JpaRepository<Product, Long> {
	
	List<Product> findByname(String name) ; 
	List<Product> findBycreatedBy(Long x) ; 
	//List<Product> findBycreatedAt(Date x) ; 
	List<Product> findBycat(String cat) ; 
	
	List<Product> findByPublish(Boolean pub) ; 
	

}
