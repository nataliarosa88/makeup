package br.com.andretecnologia.makeup.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class SchedulingHeader {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private Customer customer;

	@OneToOne
	private Professional professional;
	private LocalDate date;
	private LocalTime time;
	
	@OneToMany
	private List<SchedulingLine> lines = new ArrayList<>();
	
	public void addLine(SchedulingLine line) {
		lines.add(line);
		line.setSchedulingHeader(this);
	}

	public void removeLine(SchedulingLine line) {
		lines.remove(line);
		line.setDescription(null);
	}


	public SchedulingHeader() {

	}
	
	public SchedulingHeader(Customer customer, Professional professional) {
		this.customer = customer;
		this.professional = professional;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<SchedulingLine> getLines() {
		return lines;
	}

	public void setLines(List<SchedulingLine> lines) {
		this.lines = lines;
	}
	
	
	public long getId() {
		return id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Professional getProfessional() {
		return professional;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getFormattedDate() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return date.format(formatter);
	}

	public String getFormattedTime() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
		return time.format(formatter);
	}

}
