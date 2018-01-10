package com.camera.controllers;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.camera.answerwrappers.FileInfo;
import com.camera.constants.CameraConstants;
import com.camera.enities.Camera;
import com.camera.enities.CameraPicture;
import com.camera.enities.PictureLocation;
import com.camera.repositories.CameraPictureRepository;
import com.camera.repositories.CameraRepository;
import com.camera.repositories.PictureLocationRepo;
import com.camera.utils.ApplicationUtils;

@RestController
public class CameraPictureController {

	@Autowired
	private CameraPictureRepository cameraPicRepo;

	@Autowired
	private PictureLocationRepo picLocRepo;

	@Autowired
	private CameraRepository cameraRepo;

	@PostMapping(value = "/picupload/{cameraId}", headers = ("content-type=multipart/*"))
	public ResponseEntity<FileInfo> upload(@RequestParam("file") MultipartFile inputFile,
			@PathVariable("cameraId") String cameraId) {

		if (!inputFile.isEmpty()) {
			
			FileInfo fileInfo = new FileInfo();
			HttpHeaders headers = new HttpHeaders();
			String originalFilename = inputFile.getOriginalFilename();
			String pictureName = ApplicationUtils.getPictureName();
			fileInfo.setFileName(pictureName);
			fileInfo.setFileSize(inputFile.getSize());
			headers.add("File Uploaded Successfully - ", originalFilename);
			
			Camera camera = cameraRepo.findByName(cameraId);

			if (camera == null) {
				String pholderLocation = CameraConstants.coreFolder + File.separator + cameraId + File.separator;
				camera = new Camera();
				camera.setName(cameraId);
				new File(pholderLocation).mkdir();
				PictureLocation newPicLocation = new PictureLocation();
				newPicLocation.setPicLocation(pholderLocation);
				newPicLocation.setCamera(camera);
				try {
					ApplicationUtils.savePicture(inputFile, pholderLocation, pictureName);

					CameraPicture cameraPic = new CameraPicture();
					cameraPic.setCamera(camera);
					cameraPic.setPicLocation(newPicLocation);
					cameraPic.setPicName(pictureName);

					cameraPicRepo.save(cameraPic);

					return new ResponseEntity<FileInfo>(fileInfo, headers, HttpStatus.OK);
				} catch (Exception e) {
					return new ResponseEntity<FileInfo>(HttpStatus.BAD_REQUEST);
				}

			} else {
				PictureLocation picLocation = picLocRepo.findByCamera(camera);
				String pathToPic = picLocation.getPicLocation();
				try {
					ApplicationUtils.savePicture(inputFile, pathToPic, pictureName);
					CameraPicture cameraPic = new CameraPicture();
					cameraPic.setCamera(camera);
					cameraPic.setPicLocation(picLocation);
					cameraPic.setPicName(pictureName);
					cameraPicRepo.save(cameraPic);
					return new ResponseEntity<FileInfo>(fileInfo, headers, HttpStatus.OK);
				} catch (Exception e) {
					return new ResponseEntity<FileInfo>(HttpStatus.BAD_REQUEST);
				}
			}
		} else {
			return new ResponseEntity<FileInfo>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/camera/{cameraId}")
	public @ResponseBody ResponseEntity<byte[]> findPictureByCameraId(@PathVariable("cameraId") String cameraId) {

		Camera camera = cameraRepo.findByName(cameraId);
		CameraPicture camPic = cameraPicRepo.findByCamera(camera);
		byte[] picture = ApplicationUtils
				.getPictureInBytes(camPic.getPicLocation().getPicLocation() + camPic.getPicName());
		ResponseEntity<byte[]> responseEntity;

		if (picture != null) {

			responseEntity = ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(picture);
		} else {
			responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return responseEntity;
	}

}
