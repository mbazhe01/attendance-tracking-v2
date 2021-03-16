package com.tweedy.sboot.thymeleaf.image;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import lombok.Data;

@Data
public class ResizeImage {

	private int IMG_WIDTH;
	private int IMG_HEIGHT;
	private Path source;
	private Path target;

	public ResizeImage(int width, int height, Path source, Path target) {
		IMG_WIDTH = width;
		IMG_HEIGHT = height;
		this.source = source;
		this.target = target;
	}
	
	public void resizeAndWrite() throws IOException {
		try (InputStream is = new FileInputStream(source.toFile())) {
            resize(is, target, IMG_WIDTH, IMG_HEIGHT);
        } catch(Exception ex) {
        	System.out.println(ex.getMessage());
        }

	}

	public void resize(InputStream input, Path target, int width, int height) throws IOException {
		
		BufferedImage originalImage = ImageIO.read(input);

		/**
		 * SCALE_AREA_AVERAGING SCALE_DEFAULT SCALE_FAST SCALE_REPLICATE SCALE_SMOOTH
		 */
		Image newResizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

		String s = target.getFileName().toString();
		String fileExtension = s.substring(s.lastIndexOf(".") + 1);

		// we want image in png format
		try {
			ImageIO.write(convertToBufferedImage(newResizedImage), fileExtension, target.toFile());
			int rtn =0;
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}

	public BufferedImage convertToBufferedImage(Image img) {

		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}

		// Create a buffered image with transparency
		BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		Graphics2D graphics2D = bi.createGraphics();
		graphics2D.drawImage(img, 0, 0, null);
		graphics2D.dispose();

		return bi;
	}

}
