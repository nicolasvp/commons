package com.microservices.commons.models.entity.phrases;

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
@Table(name="images")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "phrase"})
public class Image implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	@NotEmpty(message="can't be empty")
	@Size(min=1, max=20, message="must have between 1 and 20 characters")
	private String name;
	
    @OneToOne(fetch=FetchType.LAZY, mappedBy = "image")
    private Phrase phrase;
    
	@Column(name="created_at")
	@Temporal(TemporalType.DATE)
	private Date createdAt;

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
				", phrase=" + phrase +
				", createdAt=" + createdAt +
				'}';
	}
}
