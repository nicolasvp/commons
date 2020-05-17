package com.microservices.commons.models.entity.users;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="languages")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "config"})
public class Language implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	@Size(min=1, max=20, message="must have between 1 and 20 characters")
	@NotEmpty(message="can't be empty")
	private String name;
	
	@OneToOne(fetch=FetchType.LAZY)
    private Config config;
	
	@Column(name="created_at")
	private Date createdAt;

	public Language(String name, Config config, Date createdAt) {
		this.name = name;
		this.config = config;
		this.createdAt = createdAt;
	}

	// Set current date for createdAt field
	@PrePersist
	public void prePersist() {
		createdAt = new Date();
	}

	@Override
	public String toString() {
		return "Language{" +
				"id=" + id +
				", name='" + name + '\'' +
				", config=" + config +
				", createdAt=" + createdAt +
				'}';
	}
}
