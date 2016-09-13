package ninja.repositories;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class Repository<T, I extends Serializable> {

	protected final EntityManager entityManager;
	protected final Class<T> clazz;

	/**
	 * @param entityManager
	 */
	protected Repository(EntityManager entityManager) {
		this.entityManager = entityManager;

		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

		this.clazz = clazz;
	}

	/**
	 * Store the object on the database
	 * 
	 * @param entity
	 */
	public void create(T entity) {
		entityManager.persist(entity);
	}

	/**
	 * Update the object on the database
	 * 
	 * @param entity
	 * @return
	 */
	public T update(T entity) {
		return entityManager.merge(entity);
	}

	/**
	 * Delete the object from the database
	 * 
	 * @param entity
	 */
	public void destroy(T entity) {
		entityManager.remove(entity);
	}

	/**
	 * Retrieve the object from the database
	 * 
	 * @param id
	 * @return
	 */
	public T find(I id) {
		return entityManager.find(clazz, id);
	}

	/**
	 * Retrieve all the objects from the database
	 * 
	 * @return
	 */
	public List<T> findAll() {
		Query query = entityManager.createQuery("from " + clazz.getName());

		@SuppressWarnings("unchecked")
		List<T> resultList = query.getResultList();

		return resultList;
	}
}