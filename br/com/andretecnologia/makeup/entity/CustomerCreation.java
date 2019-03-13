package br.com.andretecnologia.makeup.entity;

import javax.persistence.EntityManager;

import br.com.andretecnologia.makeup.factory.MakeupFactory;
import br.com.andretecnologia.makeup.model.Customer;

public class CustomerCreation {

	public static void main(String[] args) {
		EntityManager em = new MakeupFactory().getEntityManager();
		em.getTransaction().begin();

		Customer c = new Customer();
		c.setName("andre");
		
		em.persist(c);
		em.getTransaction().commit();
		em.remove(c);
		em.close();
		
		
	}

}
