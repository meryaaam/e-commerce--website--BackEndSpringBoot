package spring.boot.repository;

import org.springframework.data.repository.CrudRepository;

import spring.boot.entity.OrderProduct;
import spring.boot.entity.OrderProductPK;



public interface OrderProductRepository extends CrudRepository<OrderProduct, OrderProductPK> {
}
