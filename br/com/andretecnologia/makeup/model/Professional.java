package br.com.andretecnologia.makeup.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Professional {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private long id;
private String name;
private String email;
private String mobile;

public Professional() {
	
}

public Professional (String name) {
	this.name = name;
}

public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}


}
