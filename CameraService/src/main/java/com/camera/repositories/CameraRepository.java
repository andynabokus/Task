package com.camera.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camera.enities.Camera;

public interface CameraRepository extends JpaRepository<Camera, Long> {
	
	public Camera findById(Long id);
	
	public Camera findByName(String name);

}
