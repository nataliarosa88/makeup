package br.com.andretecnologia.makeup.test;

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
public class SchedulingHeaderTest {

	public static void main(String[] args) {
		EntityManager em = new MakeupFactory().getEntityManager();
		em.getTransaction().begin();
		
		String parameterCustomerName = "andre";
		String parameterProfessionalName = "natalia";
		
		Customer customer = new Customer();
		
		Query queryCustomer = em.createQuery("select c from Customer c where c.name = :parameter");
		queryCustomer.setParameter("parameter", parameterCustomerName);
		
		if(queryCustomer.getFirstResult()==0) {
			customer.setName(parameterCustomerName);
		}
		
		List<Customer> listOfCustomer = queryCustomer.getResultList();
		for (Customer c : listOfCustomer) {
			customer = c;
		}

		Professional professional = new Professional();

		Query queryProfessional = em.createQuery("select p from Professional p where p.name = :parameter");
		queryProfessional.setParameter("parameter", parameterProfessionalName);
		
		if(queryProfessional.getFirstResult()==0) {
			professional.setName(parameterProfessionalName);
		}
		
		List<Professional> listOfProfessional = queryProfessional.getResultList();
		for (Professional p : listOfProfessional) {
			professional = p;
		}
		em.persist(customer);
		em.persist(professional);
		
		SchedulingHeader schedulingHeader = new SchedulingHeader(customer, professional);
		schedulingHeader.setDate(LocalDate.now());
		schedulingHeader.setTime(LocalTime.now());
		
		
		Categoria categoria = new Categoria("beleza");
		em.persist(categoria);
		Service service = new Service("mascara", categoria);
		em.persist(service);
		
		
		SchedulingLine sl1 = new SchedulingLine();
		sl1.setService(service);
		em.persist(sl1);
		

		
		schedulingHeader.addLine(sl1);

		
		em.persist(schedulingHeader);
		

		em.getTransaction().commit();
		
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
				System.out.println("       " + sl.getService().getDescription());
			}
			
		}		
		em.close();
	}
}
