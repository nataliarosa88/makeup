package br.com.andretecnologia.makeup.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.andretecnologia.makeup.model.Service;

public class ServiceTest {
public static void main(String[] args) {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("makeup");
	EntityManager em = emf.createEntityManager();
	
	Service service1 = new Service("hair style");
	//service1.setDescription("makeup");
	
	em.getTransaction().begin();		
	em.persist(service1);
	em.getTransaction().commit();
	em.close();
}
}
