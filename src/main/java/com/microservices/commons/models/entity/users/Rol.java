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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="roles")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "user"})
public class Rol implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	@NotEmpty(message="can't be empty")
	@Size(min=1, max=20, message="must have between 1 and 100 characters")
	private String name;
	
	@NotEmpty(message="can't be empty")
	@Size(min=1, max=300, message="must have between 1 and 300 characters")
	private String description;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="rol", cascade=CascadeType.ALL)
    private List<User> user;
	
	@Column(name="created_at")
	private Date createdAt;

	public Rol(String name, String description, Date createdAt) {
		this.name = name;
		this.description = description;
		this.createdAt = createdAt;
	}

	// Set current date for createdAt field
	@PrePersist
	public void prePersist() {
		createdAt = new Date();
	}

	@Override
	public String toString() {
		return "Rol{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", user=" + user +
				", createdAt=" + createdAt +
				'}';
	}
}
