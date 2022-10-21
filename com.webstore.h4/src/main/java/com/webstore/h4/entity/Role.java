package com.webstore.h4.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")

@Entity
@Table(name="roles")
public class Role implements Serializable{
	@Id
	private String id;
	private String name;
	@JsonIgnore
	@OneToMany(mappedBy = "role",fetch = FetchType.EAGER)
	private List<Authority> auth;
	public Role() {

	}
	public Role(String id, String name, List<Authority> auth) {

		this.id = id;
		this.name = name;
		this.auth = auth;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Authority> getAuth() {
		return auth;
	}
	public void setAuth(List<Authority> auth) {
		this.auth = auth;
	}
	
	
}
