package com.camera.enities;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="pictures")
public class CameraPicture {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", unique=true, nullable=false)
	private long id;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="piclocid", nullable=false)
	private PictureLocation picLocation;
	
	@Column(name="picname")
	private String picName;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cameraid", nullable=false)
	private Camera camera;
	
	public CameraPicture() {}

	public PictureLocation getPicLocation() {
		return picLocation;
	}

	public void setPicLocation(PictureLocation picLocation) {
		this.picLocation = picLocation;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}