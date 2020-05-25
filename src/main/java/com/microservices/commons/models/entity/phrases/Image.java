package com.microservices.commons.models.entity.phrases;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
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
@Table(name="images")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "phrases"})
public class Image implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	@NotEmpty(message="The field name must not be empty")
	@Size(min=1, max=20, message="The field name must have between {min} and {max} characters")
	private String name;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="image", cascade=CascadeType.ALL)
	private List<Phrase> phrases;
    
	@Column(name="created_at")
	@Temporal(TemporalType.DATE)
	private Date createdAt;

	public Image(Long id, String name, Date createdAt) {
		this.id = id;
		this.name = name;
		this.createdAt = createdAt;
	}

	public Image(String name, Date createdAt) {
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
		return "Image{" +
				"id=" + id +
				", name='" + name + '\'' +
				", phrase=" + phrases +
				", createdAt=" + createdAt +
				'}';
	}
}
