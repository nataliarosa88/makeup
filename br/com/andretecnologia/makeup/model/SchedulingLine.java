package br.com.andretecnologia.makeup.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SchedulingLine {
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SchedulingHeader getSchedulingHeader() {
		return schedulingHeader;
	}

	public void setSchedulingHeader(SchedulingHeader schedulingHeader) {
		this.schedulingHeader = schedulingHeader;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String  description;
	
    @ManyToOne(fetch = FetchType.LAZY)
	private SchedulingHeader schedulingHeader;
    
    
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchedulingLine )) return false;
        return id != null && id.equals(((SchedulingLine) o).id);
    }
    @Override
    public int hashCode() {
        return 31;
    }



}
