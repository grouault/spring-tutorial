package fr.exagone.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Note: on utilise pas lombok
 * - car on besoin de deserialiser sans mettre le password dans le flux JSon.
 * - cela necessite une configuration specifique : @JsonIgnore
 * 
 * @author grouault
 *
 */
@Entity
public class AppUser {

	public AppUser() {
		// TODO Auto-generated constructor stub
	}
	
	public AppUser(Long id, String username, String password, Collection<AppRole> roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	@Id @GeneratedValue
	private Long id;
	
	@Column(unique=true)
	private String username;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<AppRole> roles = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonSetter
	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<AppRole> getRoles() {
		return roles;
	}

	public void setRoles(Collection<AppRole> roles) {
		this.roles = roles;
	}
	
}
