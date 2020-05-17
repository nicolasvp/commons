package com.microservices.commons.models.entity.delivery;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="likes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Like implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	//@ManyToOne(fetch=FetchType.LAZY)
	//@JoinColumn(name="user_id")
	@Column(name="user_id")
	@NotNull(message="can't be empty")
	private Long userId;
	
	@NotNull(message="can't be empty")
	@Column(name="phrase_id")
	private Long phraseId;
	
	@Column(name="created_at")
	private Date createdAt;

	public Like(Long userId, Long phraseId, Date createdAt) {
		this.userId = userId;
		this.phraseId = phraseId;
		this.createdAt = createdAt;
	}

	// Set current date for createdAt field
	@PrePersist
	public void prePersist() {
		createdAt = new Date();
	}

	@Override
	public String toString() {
		return "Like{" +
				"id=" + id +
				", userId=" + userId +
				", phraseId=" + phraseId +
				", createdAt=" + createdAt +
				'}';
	}
}
