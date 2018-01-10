package com.camera.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.camera.enities.Camera;
import com.camera.enities.PictureLocation;

public interface PictureLocationRepo extends JpaRepository<PictureLocation, Long> {
	
	public PictureLocation findByCamera(Camera camera);

}
