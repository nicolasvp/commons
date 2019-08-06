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
	
	public Config() {
		super();
	}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Integer getPhraseType() {
		return phraseType;
	}

	public void setPhraseType(int phraseType) {
		this.phraseType = phraseType;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Boolean isActivatePlugin() {
		return activatePlugin;
	}

	public void setActivatePlugin(boolean activatePlugin) {
		this.activatePlugin = activatePlugin;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
