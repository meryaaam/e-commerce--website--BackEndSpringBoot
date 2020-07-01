package spring.boot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import spring.boot.entity.Image;
import spring.boot.entity.Product;


public interface ImageRepository  extends JpaRepository<Image, Long> {
	
	Optional<Image> findByName(String name);
	
	//List<Image> findByProductId(Long productId);
	Optional<Image> findFirstByProductId(Long productId);

	List<Image> findAllByProductId(Long productId);
	//Optional<Image> findByProductId(Long productId);

	List<Image> findByProduct(Product product);
	
	
	List<Image> findByIdAndProductId(Long Id , Long PID) ;
	
//	Optional <Image> findByProduct(Product product);
	
	
	
	  //@Query("select * from Images i , products p where i.product_id = p.id ")
	//  List<Image> findByIdAndPId(Long Id , Long PID);
	}

