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
import com.microservices.commons.models.entity.users.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Document(collection = "history")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class History {

	@Id
	private String id;

	//@ManyToOne(fetch=FetchType.LAZY)
	//@JoinColumn(name="user_id")
	@Column(name="user_id")
	@NotNull(message="The field user must not be null")
	private Long userId;

	@Column(name="phrase_id")
	@NotNull(message="The field phrase must not be null")
	private Long phraseId;
	
	@Column(name="created_at")
	private Date createdAt;

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

	@Override
	public String toString() {
		return "History{" +
				"id=" + id +
				", userId=" + userId +
				", phraseId=" + phraseId +
				", createdAt=" + createdAt +
				'}';
	}
}
