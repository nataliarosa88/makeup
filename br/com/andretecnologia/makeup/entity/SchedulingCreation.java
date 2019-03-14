package br.com.andretecnologia.makeup.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.andretecnologia.makeup.factory.MakeupFactory;
import br.com.andretecnologia.makeup.model.Customer;
import br.com.andretecnologia.makeup.model.Professional;
import br.com.andretecnologia.makeup.model.SchedulingHeader;
import br.com.andretecnologia.makeup.model.SchedulingLine;

@SuppressWarnings("unchecked")
public class SchedulingCreation  {

	private static Professional professional;
	private static Customer customer;
	//private static Ser.....
	private static SchedulingHeader schedulingHeader;
	
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
		createSchedulingLines(em);
		em.getTransaction().commit();

		em.getTransaction().begin();
		listSheduling(em);
		em.getTransaction().commit();
		em.close();
	}

	private static void listSheduling(EntityManager em) {
		Query query = em.createQuery("select sh from SchedulingHeader sh");

		List<SchedulingHeader> listOfSchedulingHeader = query.getResultList();
		for (SchedulingHeader sh : listOfSchedulingHeader) {
			System.out.print(sh.getId()+" ");
			System.out.print(sh.getCustomer().getName()+" ");
			System.out.print(sh.getProfessional().getName()+" ");
			System.out.print(sh.getFormattedDate() + " ");
			System.out.println(sh.getFormattedTime());
			List<SchedulingLine> lines = sh.getLines();
			
			
			
			
			for (SchedulingLine sl : lines) {
				System.out.print("       " + sl.getLineNumber()+" ");
				
				//getService().getDescription();
				System.out.println("       " + sl.getDescription());
			}
			
		}		
		
	}

	private static void createSchedulingLines(EntityManager em) {
		
		SchedulingLine sl1 = new SchedulingLine();
		
		sl1.setLineNumber(1L);
		//....setService(service)
		sl1.setDescription("maquiagem");
		em.persist(sl1);
		
		SchedulingLine sl2 = new SchedulingLine();
		sl2.setLineNumber(2L);
		sl2.setDescription("sobrancelha");
		em.persist(sl2);
		
		
		SchedulingLine sl3 = new SchedulingLine();
		sl3.setLineNumber(3L);
		sl3.setDescription("cilios");
		em.persist(sl3);
		
		schedulingHeader.addLine(sl1);
		schedulingHeader.addLine(sl2);
		schedulingHeader.addLine(sl3);
		
		em.persist(schedulingHeader);
		
	}

	private static void createSchedulingHeader(EntityManager em) {
		
		schedulingHeader = new SchedulingHeader(customer, professional);
		schedulingHeader.setDate(LocalDate.now());
		schedulingHeader.setTime(LocalTime.now());
		
	}

	private static void createProfessional(EntityManager em) {
		
		professional = new Professional();
		String parameterProfessionalName = "natalia";
		
		Query queryProfessional = em.createQuery("select p from Professional p where p.name = :parameter");
		queryProfessional.setParameter("parameter", parameterProfessionalName);
		
		if(queryProfessional.getFirstResult()==0) {
			professional.setName(parameterProfessionalName);
		}
		
		List<Professional> listOfProfessional = queryProfessional.getResultList();
		for (Professional p : listOfProfessional) {
			professional = p;
		}
		em.persist(professional);
	}

	
	
	//criar metodo createservice....
	
	
	private static void createCustomer(EntityManager em) {
		customer = new Customer();
		String parameterCustomerName = "andre";
		
		Query queryCustomer = em.createQuery("select c from Customer c where c.name = :parameter");
		queryCustomer.setParameter("parameter", parameterCustomerName);
		
		if(queryCustomer.getFirstResult()==0) {
			customer.setName(parameterCustomerName);
		}
		
		List<Customer> listOfCustomer = queryCustomer.getResultList();
		for (Customer c : listOfCustomer) {
			customer = c;
		}
		
		em.persist(customer);
	}

	}


