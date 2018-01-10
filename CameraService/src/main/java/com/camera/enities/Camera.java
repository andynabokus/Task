package com.camera.enities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cameras")
public class Camera {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	@Column(name="name")
	private String name;

	
	public Camera() {}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
