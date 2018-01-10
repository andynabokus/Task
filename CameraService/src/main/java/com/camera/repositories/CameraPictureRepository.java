package com.camera.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.camera.enities.Camera;
import com.camera.enities.CameraPicture;


public interface CameraPictureRepository extends  JpaRepository<CameraPicture, Long> {
	
	public CameraPicture findTopByCameraIdOrderByPicNameAsc(Long id);
	
	public CameraPicture findTopByCameraIdOrderByPicNameDesc(Long id);
	
	public CameraPicture findByCamera(Camera camera);

	
}
