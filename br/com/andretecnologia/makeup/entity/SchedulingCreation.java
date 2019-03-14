package br.com.andretecnologia.makeup.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.andretecnologia.makeup.factory.MakeupFactory;
import br.com.andretecnologia.makeup.model.Categoria;
import br.com.andretecnologia.makeup.model.Customer;
import br.com.andretecnologia.makeup.model.Professional;
import br.com.andretecnologia.makeup.model.SchedulingHeader;
import br.com.andretecnologia.makeup.model.SchedulingLine;
import br.com.andretecnologia.makeup.model.Service;

@SuppressWarnings("unchecked")
public class SchedulingCreation {

	private static Professional professional;
	private static Customer customer;
	private static Categoria categoria;
	private static Service service;
	private static SchedulingHeader schedulingHeader;
	
	private static String parameterCustomerName;
	private static String parameterProfessionalName;
	private static String parameterCategoriaDescription;
	private static String parameterServiceDescription;
	
	
	public SchedulingCreation() {
		
	}
	
	public SchedulingCreation(String customerName, String professionalName, String categoriaDescription, String serviceDescription) throws Exception {
		parameterCustomerName = customerName;
		parameterProfessionalName = professionalName;
		parameterCategoriaDescription = categoriaDescription;
		parameterServiceDescription = serviceDescription;
		
		main(null);
		
		
	}
	
	public static void main(String[] args) throws Exception {

		EntityManager em = new MakeupFactory().getEntityManager();

		em.getTransaction().begin();
		createCustomer(em);
		em.getTransaction().commit();

		em.getTransaction().begin();
		createProfessional(em);
		em.getTransaction().commit();

		em.getTransaction().begin();
		createSchedulingHeader(em);
		em.getTransaction().commit();

		em.getTransaction().begin();
		createCategoria(em);
		em.getTransaction().commit();

		em.getTransaction().begin();
		createService(em);
		em.getTransaction().commit();

		em.getTransaction().begin();
		createSchedulingLines(em);
		em.getTransaction().commit();

		em.getTransaction().begin();
		listSheduling(em);
		em.getTransaction().commit();
		em.close();
	}

	public static void listSheduling(EntityManager em) {
		Query query = em.createQuery("select sh from SchedulingHeader sh");
		List<SchedulingHeader> listOfSchedulingHeader = query.getResultList();
		for (SchedulingHeader sh : listOfSchedulingHeader) {
			System.out.println(" \n \n \n  \n \n \n");
			System.out.print(sh.getId() + " ");
			System.out.print(sh.getCustomer().getName() + " ");
			System.out.print(sh.getProfessional().getName() + " ");
			System.out.print(sh.getFormattedDate() + " ");
			System.out.println(sh.getFormattedTime());
			List<SchedulingLine> lines = sh.getLines();
			for (SchedulingLine sl : lines) {
				System.out.print("       " + sl.getLineNumber() + " ");
				System.out.print("       " + sl.getService().getCategoria().getDescricao() + " ");
				System.out.println("       " + sl.getService().getDescription() + " ");
			}

		}

	}

	private static void createSchedulingLines(EntityManager em) {

		SchedulingLine sl1 = new SchedulingLine();

		sl1.setLineNumber(1L);
		sl1.setService(service);
		em.persist(sl1);

		schedulingHeader.addLine(sl1);

		em.persist(schedulingHeader);

	}

	private static void createSchedulingHeader(EntityManager em) {

		schedulingHeader = new SchedulingHeader(customer, professional);
		schedulingHeader.setDate(LocalDate.now());
		schedulingHeader.setTime(LocalTime.now());

	}

	private static void createProfessional(EntityManager em) {

		professional = new Professional();
		

		Query queryProfessional = em.createQuery("select p from Professional p where p.name = :parameter");
		queryProfessional.setParameter("parameter", parameterProfessionalName);

		if (queryProfessional.getFirstResult() == 0) {
			professional.setName(parameterProfessionalName);
		}

		List<Professional> listOfProfessional = queryProfessional.getResultList();
		for (Professional p : listOfProfessional) {
			professional = p;
		}
		em.persist(professional);
	}

	private static void createCategoria(EntityManager em) {

		categoria = new Categoria();
		

		Query queryCategoria = em.createQuery("select c from Categoria c where c.descricao = :parameter");
		queryCategoria.setParameter("parameter", parameterCategoriaDescription);

		if (queryCategoria.getFirstResult() == 0) {
			categoria.setDescricao(parameterCategoriaDescription);
		}

		List<Categoria> listOfCategoria = queryCategoria.getResultList();
		for (Categoria c : listOfCategoria) {
			categoria = c;
		}
		em.persist(categoria);

	}

	private static void createService(EntityManager em) {

		service = new Service();
		

		Query queryService = em.createQuery("select s from Service s where s.description = :parameter");
		queryService.setParameter("parameter", parameterServiceDescription);

		if (queryService.getFirstResult() == 0) {
			service.setDescription(parameterServiceDescription);
			service.setCategoria(categoria);
		}

		List<Service> listOfService = queryService.getResultList();
		for (Service s : listOfService) {
			service = s;
		}
		em.persist(service);

	}

	private static void createCustomer(EntityManager em) {
		customer = new Customer();
		

		Query queryCustomer = em.createQuery("select c from Customer c where c.name = :parameter");
		queryCustomer.setParameter("parameter", parameterCustomerName);

		if (queryCustomer.getFirstResult() == 0) {
			customer.setName(parameterCustomerName);
		}

		List<Customer> listOfCustomer = queryCustomer.getResultList();
		for (Customer c : listOfCustomer) {
			customer = c;
		}

		em.persist(customer);
	}

}