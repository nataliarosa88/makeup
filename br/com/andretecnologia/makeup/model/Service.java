package br.com.andretecnologia.makeup.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Service {
	
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private long id;
private String description;
@OneToOne
private Categoria categoria;

public Service(String description,  Categoria categoria) {
	this.description = description;
	this.categoria = categoria;
}

public Categoria getCategoria() {
	return categoria;
}
public void setCategoria(Categoria categoria) {
	this.categoria = categoria;
}

public void setDescription(String description) {
	this.description = description;
}

public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getDescription() {
	return description;
}



}
