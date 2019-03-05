package br.com.andretecnologia.makeup.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.andretecnologia.makeup.model.Customer;

public class CustomerTest {
	public static void main(String[] args) {
		/**
		 * Preparing the entity manager factory (E.g. Customer, Professionals etc)
		 */
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("makeup");
		EntityManager em = emf.createEntityManager();
		
		/**
		 * For example values comming from the frontend
		 */
		String cpfThatComesFromtheFrontEnd = "102030";
		String nameThatComesFromtheFrontEnd = "cris";
		String emailThatComesFromtheFrontEnd = "cris@cris.com";
		String telefoneThatComesFromtheFrontEnd = "1933854789";
		
		/**
		 * Instantiating the entity Customer
		 */
		Customer customer1 = new Customer();
		/**
		 * Setting values to the attributes of the Class Customer [Creating the object named customerX]
		 */
		customer1.setCpf(cpfThatComesFromtheFrontEnd);
		customer1.setName(nameThatComesFromtheFrontEnd);
		customer1.setEmail(emailThatComesFromtheFrontEnd);
		customer1.setTelefone(telefoneThatComesFromtheFrontEnd);

		/**
		 * Starting DB Transactions
		 */
		em.getTransaction().begin();		
		em.persist(customer1);
		em.getTransaction().commit();
		em.close();
	}
}
