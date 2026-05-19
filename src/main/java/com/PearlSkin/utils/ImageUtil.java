package com.PearlSkin.utils;

import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class ImageUtil {

    public static String uploadImage(Part imagePart) {
        String fileName = imagePart.getSubmittedFileName();
        if (fileName == null || fileName.isEmpty()) {
            return null;
        }

        String extension = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        if (!extension.equals(".jpg") && !extension.equals(".jpeg") && !extension.equals(".png")) {
            return null;
        }

        String uniqueName = LocalDateTime.now().toString().replace(":", "-") + "_" + fileName;

        String uploadPath = System.getProperty("user.home") + File.separator + "PearlSkin-uploads";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        try {
            imagePart.write(uploadPath + File.separator + uniqueName);
            return uniqueName;
        } catch (IOException e) {
            System.out.println("Error uploading image: " + e.getMessage());
            return null;
        }
    }
    public static void deleteImage(String imagePath) {
        if (imagePath == null || imagePath.isEmpty()) {
            return;
        }
        if (imagePath.equals("static/images/book.png")) {
            return;
        }
        String uploadPath = System.getProperty("user.home") + File.separator + "learning-logs-uploads";
        File file = new File(uploadPath + File.separator + imagePath);
        if (file.exists()) {
            file.delete();
        }
    }
}
