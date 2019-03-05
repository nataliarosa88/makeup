package br.com.andretecnologia.makeup.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.andretecnologia.makeup.model.Professional;

public class ProfessionalTest {

	public static void main(String[] args) {
	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("makeup");
		EntityManager em = emf.createEntityManager();
		
		Professional professional1 = new Professional();
		
		professional1.setName("Andre");
		professional1.setEmail("dedemac@me.com");
		professional1.setMobile("19989501551");
		
		em.getTransaction().begin();		
		em.persist(professional1);
		em.getTransaction().commit();
		em.close();
		

	}

}
