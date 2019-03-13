package br.com.andretecnologia.makeup.entity;

import javax.persistence.EntityManager;

import br.com.andretecnologia.makeup.factory.MakeupFactory;
import br.com.andretecnologia.makeup.model.Professional;

public class ProfessionalCreation {

	public static void main(String[] args) {
		
		EntityManager em = new MakeupFactory().getEntityManager();
		em.getTransaction().begin();		
		
		Professional p = new Professional();
		p.setName("natalia");
		
		em.persist(p);
		em.getTransaction().commit();
		em.close();
			

	}

}
