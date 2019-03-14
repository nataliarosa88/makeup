package br.com.andretecnologia.makeup.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class SchedulingLine{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long lineNumber;
	
	
	@OneToOne
	private Service service;
	
    @ManyToOne
	private SchedulingHeader schedulingHeader;
	
	public SchedulingHeader getSchedulingHeader() {
		return schedulingHeader;
	}

	public void setSchedulingHeader(SchedulingHeader schedulingHeader) {
		this.schedulingHeader = schedulingHeader;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchedulingLine )) return false;
        return getId() != null && getId().equals(((SchedulingLine) o).getId());
    }
    @Override
    public int hashCode() {
        return 31;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(Long lineNumber) {
		this.lineNumber = lineNumber;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

}
