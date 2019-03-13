package br.com.andretecnologia.makeup.test;

import static org.junit.Assert.assertSame;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;

import br.com.andretecnologia.makeup.factory.MakeupFactory;
import br.com.andretecnologia.makeup.log.MakeupLog;
import br.com.andretecnologia.makeup.model.Customer;

public class CustomerTest {
	@Test
	public void ShouldPersistOneCustomer() throws IOException {
		
		EntityManager em = new MakeupFactory().getEntityManager();
		Customer customerModel = new Customer("02495426174", "Natalia", "nataliag1988@gmail.com", "1991409562");
		em.getTransaction().begin();
		em.persist(customerModel);
		em.flush();
		System.out.println("Name : " + customerModel.getName());
		{
			Query query = em.createQuery("select c from Customer c where c.name=:param");
			query.setParameter("param", customerModel.getName());
			Customer customerDB = (Customer) query.getSingleResult();
			assertSame(customerModel, customerDB);
			customerModel.setName("Nataly");
		}
		em.merge(customerModel);
		em.flush();
		{
			Query query = em.createQuery("select c from Customer c where c.name=:param");
			query.setParameter("param", customerModel.getName());
			Customer customerDB = (Customer) query.getSingleResult();
			assertSame(customerModel, customerDB);
		}
		System.out.println("Name : " + customerModel.getName());
		em.remove(customerModel);
		em.close();
		
		new MakeupLog().setLog("CustomerTest", "Executou um teste");
	}
}
