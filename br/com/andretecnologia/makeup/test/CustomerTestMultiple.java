package br.com.andretecnologia.makeup.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.andretecnologia.makeup.model.Customer;

public class CustomerTestMultiple {
	public static void main(String[] args) {
		/**
		 * Preparing the entity manager factory (E.g. Customer, Professionals etc)
		 */
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("makeup");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Customer[] customer = new Customer[100];

		for(int i=0;i < customer.length;++i) {
			
			customer[i] = new Customer();
			
			customer[i].setCpf("cpf"+i);
			customer[i].setName(" name"+i);
			customer[i].setEmail(" email"+i);
			customer[i].setTelefone("telefone" + i);
			/**
			 * Starting DB Transactions
			 */
					
			em.persist(customer[i]);
		}
		
		em.getTransaction().commit();
		em.close();
	}
}
