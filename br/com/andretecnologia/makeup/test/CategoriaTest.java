package br.com.andretecnologia.makeup.test;

import static org.junit.Assert.assertSame;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;

import br.com.andretecnologia.makeup.factory.MakeupFactory;
import br.com.andretecnologia.makeup.log.MakeupLog;
import br.com.andretecnologia.makeup.model.Categoria;

public class CategoriaTest {

	@Test
	public void ShouldPersistOneCategory() throws IOException {
		
		Categoria categoria = new Categoria("Categoria Test");
		EntityManager em = new MakeupFactory().getEntityManager();
		em.getTransaction().begin();
		em.persist(categoria);
		em.flush();
		System.out.println("Descricao : " + categoria.getDescricao());
		Query query = em.createQuery("Select c from Categoria c where c.descricao=:param");
		query.setParameter("param", categoria.getDescricao());
		Categoria categoriaDB = (Categoria) query.getSingleResult();
		assertSame(categoria, categoriaDB);
		categoria.setDescricao("Nova");
		em.merge(categoria);
		em.flush();
		System.out.println("Descricao : " + categoria.getDescricao());
		em.remove(categoria);
		em.close();
		
		new MakeupLog().setLog("CategoriaTest", "Executou um teste");
	}

}
