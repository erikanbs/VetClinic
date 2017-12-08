package com.ekholabs.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PetDto {

	private Integer id;

	private String name;

	private String category;

	private String breed;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthday;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date deathday;

	private String cause;

	private Integer ownerId;

	private String ownerDescription;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getDeathday() {
		return deathday;
	}

	public void setDeathday(Date deathday) {
		this.deathday = deathday;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerDescription() {
		return ownerDescription;
	}

	public void setOwnerDescription(String ownerDescription) {
		this.ownerDescription = ownerDescription;
	}

}
