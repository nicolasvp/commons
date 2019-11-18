package com.microservices.commons.models.entity.delivery;

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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.microservices.commons.models.entity.users.User;

@Entity
@Table(name="history")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class History implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	//@ManyToOne(fetch=FetchType.LAZY)
	//@JoinColumn(name="user_id")
	@Column(name="user_id")
	@NotNull(message="can't be empty")
	private Long userId;

	@Column(name="phrase_id")
	@NotNull(message="can't be empty")
	private Long phraseId;
	
	@Column(name="created_at")
	private Date createdAt;
	
	public History() {
		super();
	}

	public History(Long userId, Long phraseId, Date createdAt) {
		this.userId = userId;
		this.phraseId = phraseId;
		this.createdAt = createdAt;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Long getPhraseId() {
		return phraseId;
	}

	public void setPhraseId(Long phraseId) {
		this.phraseId = phraseId;
	}
}
