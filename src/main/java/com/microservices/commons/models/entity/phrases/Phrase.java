package com.microservices.commons.models.entity.phrases;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
	
	@NotEmpty(message="can't be empty")
	@Size(min=1, max=200, message="must have between 1 and 200 characters")
	private String body;
	
	@NotNull(message="can't be empty")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="author_id")
	private Author author;
	
	@NotNull(message="can't be empty")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="type_id")
	private Type type;
	
	@NotNull(message="can't be empty")
	@OneToOne(fetch=FetchType.LAZY)
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
