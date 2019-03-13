package br.com.andretecnologia.makeup.test;

import static org.junit.Assert.assertSame;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;

import br.com.andretecnologia.makeup.factory.MakeupFactory;
import br.com.andretecnologia.makeup.log.MakeupLog;
import br.com.andretecnologia.makeup.model.Professional;

public class ProfessionalTest {
	@Test
	public void ShouldPersistOneProfessional() throws IOException {
		
		Professional professional = new Professional("Name");
		
		EntityManager em = new MakeupFactory().getEntityManager();
		em.getTransaction().begin();
		em.persist(professional);
		em.flush();
		System.out.println("Nome : " + professional.getName());
		{
			Query query = em.createQuery("select p from Professional p where p.name=:param");
			query.setParameter("param", professional.getName());
			Professional professionalDB = (Professional) query.getSingleResult();
			assertSame(professional, professionalDB);
		}
		
		professional.setName("Andre");
		em.merge(professional);
		em.flush();
		System.out.println("Nome : " + professional.getName());
		em.remove(professional);
		em.close();
		
		new MakeupLog().setLog("ProfessionalTest", "Executou um teste");				
		
	}
}

	
		/*EntityManagerFactory emf = Persistence.createEntityManagerFactory("makeup");
		EntityManager em = emf.createEntityManager();
		
		Professional professional1 = new Professional("andre");
		
		//ja esta sendo passado no construtor por que ao menos o nome é obrigatorio
		//professional1.setName("Andre");
		professional1.setEmail("dedemac@me.com");
		professional1.setMobile("19989501551");
		
		em.getTransaction().begin();		
		em.persist(professional1);
		em.getTransaction().commit();
		em.close();
		
		new MakeupLog().setLog("ProfessionalTest", "Executou um teste");
	}

}*/
