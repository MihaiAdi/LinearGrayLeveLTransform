package packWork;

import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Image  implements ImageInterface{
	protected BufferedImage image;
	private int width;
	private int height;
	
	public Image(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	public Image(String path, int width, int height){
		try{
			File file = new File(path);
			this.image = ImageIO.read(file);
		}catch (IOException e){
			e.printStackTrace();
		}
		
		
		
		if (image.getWidth() != width || image.getHeight() != height){
			System.out.println("Dimensiunile nu corespund");
			this.image = null;
			this.width = 0;
			this.height = 0;
		}
		else{
			this.width = image.getWidth();
            this.height = image.getHeight();
			
			System.out.println("Citirea imaginii s-a desfasurat cum ne asteptam!");
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	

}
