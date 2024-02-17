package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {

	public BufferedImage scaleImage(BufferedImage original, int width, int heigth) {
		
		BufferedImage scaledImage = new BufferedImage(width, heigth, original.getType());
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(original, 0, 0, width, heigth, null);
		g2.dispose();
		
		return scaledImage;
	}
	
	public UtilityTool() {
		// TODO Auto-generated constructor stub
	}

}
