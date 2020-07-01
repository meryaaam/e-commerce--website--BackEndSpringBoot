package spring.boot.repository;

import org.springframework.data.repository.CrudRepository;

import spring.boot.entity.Order;



public interface OrderRepository extends CrudRepository<Order, Long> {
}