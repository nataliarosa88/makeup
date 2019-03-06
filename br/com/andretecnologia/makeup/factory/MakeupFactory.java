package br.com.andretecnologia.makeup.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MakeupFactory {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("makeup");
	
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
