package com.microservices.commons.models.entity.phrases;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
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
@Table(name="phrases")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Phrase implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="The field body must not be empty")
	@Size(min=1, max=200, message="The field body must have between {min} and {max} characters")
	private String body;
	
	//@NotNull(message="can't be empty")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="author_id", referencedColumnName = "id")
	private Author author;
	
	//@NotNull(message="can't be empty")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="type_id", referencedColumnName = "id")
	private Type type;
	
	//@NotNull(message="can't be empty")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="image_id", referencedColumnName="id")
	private Image image;
	
	@Column(name="likes_counter")
	private Long likesCounter;
	
	@Column(name="created_at")
	@Temporal(TemporalType.DATE)
	private Date createdAt;

	public Phrase(Long id, String body, Long likesCounter, Type type, Author author, Image image){
		this.id = id;
		this.body = body;
		this.likesCounter = likesCounter;
		this.type = type;
		this.author = author;
		this.image = image;
	}
	
	public Phrase(String body, Author author, Type type,Image image, Long likesCounter, Date createdAt) {
		this.body = body;
		this.author = author;
		this.type = type;
		this.image = image;
		this.likesCounter = likesCounter;
		this.createdAt = createdAt;
	}

	// Set current date for createdAt field
	@PrePersist
	public void prePersist() {
		createdAt = new Date();
	}

	@Override
	public String toString() {
		return "Phrase{" +
				"id=" + id +
				", body='" + body + '\'' +
				", author=" + author +
				", type=" + type +
				", image=" + image +
				", likesCounter=" + likesCounter +
				", createdAt=" + createdAt +
				'}';
	}
}
