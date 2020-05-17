package com.microservices.commons.models.entity.users;

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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="configuration")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "user"})
public class Config implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", referencedColumnName="id")
	private User user;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="language_id", referencedColumnName="id")
	private Language language;
	
	@Column(name="phrase_type")
	private int phraseType;
	
	@Column(name="activate_plugin")
	private boolean activatePlugin;
	
	@Column(name="created_at")
	private Date createdAt;

	public Config(User user, Language language, int phraseType, boolean activatePlugin, Date createdAt) {
		this.user = user;
		this.language = language;
		this.phraseType = phraseType;
		this.activatePlugin = activatePlugin;
		this.createdAt = createdAt;
	}

	// Set current date for createdAt field
	@PrePersist
	public void prePersist() {
		createdAt = new Date();
	}

	@Override
	public String toString() {
		return "Config{" +
				"id=" + id +
				", user=" + user +
				", language=" + language +
				", phraseType=" + phraseType +
				", activatePlugin=" + activatePlugin +
				", createdAt=" + createdAt +
				'}';
	}
}
