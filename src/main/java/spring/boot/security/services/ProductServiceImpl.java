package spring.boot.security.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.boot.entity.Product;
import spring.boot.exception.ResourceNotFoundException;

import spring.boot.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(long id) {
        return productRepository
          .findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
    
}