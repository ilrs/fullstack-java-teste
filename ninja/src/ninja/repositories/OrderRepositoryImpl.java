package ninja.repositories;

import javax.persistence.EntityManager;

import ninja.models.Order;

public class OrderRepositoryImpl
    extends Repository<Order, Long>
    implements OrderRepository {

	public OrderRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}
}
