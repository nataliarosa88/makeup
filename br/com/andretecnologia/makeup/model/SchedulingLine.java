package br.com.andretecnologia.makeup.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SchedulingLine{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long lineNumber;
	
	
	//OneToOne
	//trocar para objeto service get setter
	private String  description;
	
    @ManyToOne
	private SchedulingHeader schedulingHeader;
	
	public SchedulingHeader getSchedulingHeader() {
		return schedulingHeader;
	}

	public void setSchedulingHeader(SchedulingHeader schedulingHeader) {
		this.schedulingHeader = schedulingHeader;
	}
//fazer um set service
	public void setDescription(String description) {
		this.description = description;
	}
	//getSErvice Service(tipo)
	public String getDescription() {
		return description;
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

}
