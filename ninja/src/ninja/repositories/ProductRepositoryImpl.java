package ninja.repositories;

import javax.persistence.EntityManager;

import ninja.models.Product;

public class ProductRepositoryImpl
    extends Repository<Product, Long>
    implements ProductRepository {

	public ProductRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}
}
