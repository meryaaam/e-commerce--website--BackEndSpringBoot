package spring.boot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.boot.entity.Category;
import spring.boot.entity.Product;



public interface CategoryRepository extends JpaRepository<Category, Long> {
	Optional<Category> findByname(String name) ; 
	
	Boolean existsByName(String name);
	Optional<Category>findByProductsId(Long productId);
	//List<Product> findProductsByByName(String name ) ;
}
