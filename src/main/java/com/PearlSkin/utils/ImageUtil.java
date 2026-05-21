package com.PearlSkin.utils;

import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Utility class for handling image file uploads for PearlSkin.
 *
 * Stores images in:
 * ~/PearlSkin-uploads/
 */
public class ImageUtil {

    /**
     * Uploads an image file and returns stored filename.
     */
    public static String uploadImage(Part imagePart) {

        String fileName = imagePart.getSubmittedFileName();

        if (fileName == null || fileName.isEmpty()) {
            return null;
        }

        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex == -1) {
            return null;
        }

        String extension = fileName.substring(dotIndex).toLowerCase();

        if (!extension.equals(".jpg")
                && !extension.equals(".jpeg")
                && !extension.equals(".png")) {
            return null;
        }

        String uniqueName =
                LocalDateTime.now().toString().replace(":", "-")
                        + "_" + fileName;

        String uploadPath = System.getProperty("user.home")
                + File.separator + "PearlSkin-uploads";

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

    /**
     * Deletes an uploaded image safely.
     */
    public static void deleteImage(String imagePath) {

        if (imagePath == null || imagePath.isEmpty()) {
            return;
        }

        // keep default image safe
        if (imagePath.equals("static/images/cleanser.png")) {
            return;
        }

        String uploadPath = System.getProperty("user.home")
                + File.separator + "PearlSkin-uploads";

        File file = new File(uploadPath + File.separator + imagePath);

        try {
            if (!file.getCanonicalPath().startsWith(
                    new File(uploadPath).getCanonicalPath())) {
                return;
            }
        } catch (IOException e) {
            return;
        }

        if (file.exists()) {
            file.delete();
        }
    }
}