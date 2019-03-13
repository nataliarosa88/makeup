package br.com.andretecnologia.makeup.test;

import static org.junit.Assert.assertSame;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;

import br.com.andretecnologia.makeup.factory.MakeupFactory;
import br.com.andretecnologia.makeup.log.MakeupLog;
import br.com.andretecnologia.makeup.model.Categoria;
import br.com.andretecnologia.makeup.model.Service;

public class ServiceTest {
	@Test
	public void ShouldPersistOneService() throws IOException {
		
		Categoria categoria = new Categoria("Categoria Cabelereiros");
		Service service = new Service("Serviço hair style", categoria);
		
		EntityManager em = new MakeupFactory().getEntityManager();
		em.getTransaction().begin();
		em.persist(categoria);
		em.persist(service);
		em.flush();
		System.out.println("Description : " + service.getDescription());
		{
			Query query = em.createQuery("select s from Service s where s.description=:param");
			query.setParameter("param", service.getDescription());
			Service serviceDB = (Service) query.getSingleResult();
			assertSame(service, serviceDB);
		}
		service.setDescription("new");
		em.merge(service);
		em.flush();
		System.out.println("Description : " + service.getDescription());
		em.remove(service);
		em.close();
		
		new MakeupLog().setLog("ServiceTest", "Executou um teste");
	}
}
