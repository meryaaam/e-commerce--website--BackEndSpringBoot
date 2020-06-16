package spring.boot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.boot.model.Image;
import spring.boot.model.Product;


public interface ImageRepository  extends JpaRepository<Image, Long> {
	
	Optional<Image> findByName(String name);
	
	//List<Image> findByProductId(Long productId);
	
	Optional<Image> findByProductId(Long productId);

	List<Image> findByProduct(Product product);
	
	
	List<Image> findByIdAndProductId(Long Id , Long PID) ;
	
//	Optional <Image> findByProduct(Product product);
	
	
	
	  //@Query("select * from Images i , products p where i.product_id = p.id ")
	//  List<Image> findByIdAndPId(Long Id , Long PID);
	}

