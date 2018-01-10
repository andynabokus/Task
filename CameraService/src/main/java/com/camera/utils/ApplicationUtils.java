package com.camera.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class ApplicationUtils {

	public static byte[] getPictureInBytes(String picLocation) {

		File file = new File(picLocation);
		byte[] picture = null;
		try {
			picture = Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			file.exists();
		}
		return picture;

	}

	public static void savePicture(MultipartFile file, String picLocation, String picName) throws IOException {

		if (file.isEmpty()) {
			return;
		}

		byte[] bytes = file.getBytes();
		Path path = Paths.get(picLocation + picName);
		Files.write(path, bytes);

	}

	public static String getPictureName() {
		
		Date timePicture = new Date();
		DateFormat yearMonthDate = new SimpleDateFormat("YYYY-MM-DD");
		DateFormat time = new SimpleDateFormat("HH:mm:ss");
		String pictureName = yearMonthDate.format(timePicture) + " " + time.format(timePicture) + ".jpg";
		return pictureName;
	
	}

}
