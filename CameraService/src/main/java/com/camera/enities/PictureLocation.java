package com.camera.enities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="pictlocation")
public class PictureLocation {
	
	@Id()
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="piclocation")
	private String picLocation;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cameraid", nullable=false)
	private Camera camera;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPicLocation() {
		return picLocation;
	}

	public void setPicLocation(String picLocation) {
		this.picLocation = picLocation;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	

}
