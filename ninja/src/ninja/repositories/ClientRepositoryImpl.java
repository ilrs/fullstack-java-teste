package ninja.repositories;

import javax.persistence.EntityManager;

import ninja.models.Client;

public class ClientRepositoryImpl
    extends Repository<Client, Long>
    implements ClientRepository {

	public ClientRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}
}
