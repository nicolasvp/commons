package com.microservices.commons.models.entity.users;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.microservices.commons.models.entity.delivery.Favorite;
import com.microservices.commons.models.entity.delivery.History;
import com.microservices.commons.models.entity.delivery.Like;

@Entity
@Table(name="users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "likes", "history", "favorities"})
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="can't be empty")
	@Size(min=1, max=50, message="must have between 1 and 50 characters")
	private String name;
	
	@Column(name="last_name")
	@NotEmpty(message="can't be empty")
	@Size(min=1, max=50, message="must have between 1 and 50 characters")
	private String lastName;
	
	@Column(unique=true)
	@NotEmpty(message="can't be empty")
	@Size(min=1, max=20, message="must have between 1 and 20 characters")
	private String username;
	
	@Column(unique=true)
	@NotEmpty(message="can't be empty")
	@Size(min=1, max=30, message="must have between 1 and 30 characters")
	private String email;
	
	@NotEmpty(message="can't be empty")
	@Size(min=1, max=100, message="must have between 1 and 100 characters")
	private String password;
	
	private Boolean enabled;
	
	@Column(name="created_at")
	private Date createdAt;
	
	@OneToOne(mappedBy="user", fetch=FetchType.LAZY)
    private Config config;
    
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="rol_id")
	private Rol rol;
	
	public User() {
		super();
	}

	public User(String name, String lastName, String username, String email, String password, Date createdAt, Config config, Rol rol) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.createdAt = createdAt;
		this.config = config;
		this.rol = rol;
	}

	// Set current date for createdAt field
	@PrePersist
	public void prePersist() {
		createdAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
