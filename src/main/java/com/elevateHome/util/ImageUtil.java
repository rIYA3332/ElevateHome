package com.elevateHome.util;

import java.io.*;

import java.util.UUID;
import jakarta.servlet.http.Part;

public class ImageUtil {

	public static String saveImage(Part filePart, String uploadDir) throws IOException {
		if (uploadDir == null) {
			throw new IOException("Upload directory path is null. Check configuration.");
		}

		String fileName = extractFileName(filePart);
		if (fileName == null || fileName.isEmpty()) {
			return null;
			
		}
		// Validating file type
		String contentType = filePart.getContentType();
		if (!contentType.equals("image/jpeg") && !contentType.equals("image/png")) {
			throw new IOException("Only JPEG and PNG images are allowed.");
		}

		// Generating unique filename with extension
		String fileExtension = fileName.substring(fileName.lastIndexOf("."));
		String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

		File uploadPath = new File(uploadDir);
		System.out.println("Saving to: " + uploadPath.getAbsolutePath());

		if (!uploadPath.exists()) {
			uploadPath.mkdirs(); 
		}

		try (InputStream input = filePart.getInputStream();
				FileOutputStream output = new FileOutputStream(new File(uploadDir, uniqueFileName))) {
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = input.read(buffer)) != -1) {
				output.write(buffer, 0, bytesRead);
			}
		}

		return uniqueFileName;
	}

	private static String extractFileName(Part part) {
		String contentDisposition = part.getHeader("content-disposition");
		if (contentDisposition == null)
			return null;

		for (String cd : contentDisposition.split(";")) {
			if (cd.trim().startsWith("filename")) {
				return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
}
