package ninja.repositories;

import java.util.List;

import ninja.models.Order;

public interface OrderRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(Order entity);
	
	Order update(Order entity);
	
	void destroy(Order entity);
	
	Order find(Long id);
	
	List<Order> findAll();

}
