package com.microservices.commons.models.entity.phrases;

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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name="authors")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "phrases"})
public class Author implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	@NotEmpty(message="The field name must not be empty")
	@Size(min=1, max=100, message="The field name must have between {min} and {max} characters")
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="author", cascade=CascadeType.ALL)
	private List<Phrase> phrases;
	
	@Column(name="created_at")
	@Temporal(TemporalType.DATE)
	private Date createdAt;

	public Author(Long id, String name, Date createdAt) {
		this.id = id;
		this.name = name;
		this.createdAt = createdAt;
	}
	
	public Author(String name, Date createdAt) {
		this.name = name;
		this.createdAt = createdAt;
	}

	// Set current date for createdAt field
	@PrePersist
	public void prePersist() {
		createdAt = new Date();
	}

	@Override
	public String toString() {
		return "Author{" +
				"id=" + id +
				", name='" + name + '\'' +
				", phrases=" + phrases +
				", createdAt=" + createdAt +
				'}';
	}
}
