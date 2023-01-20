package packWork;

import java.awt.image.BufferedImage;

public class ThreadedImage extends Image {
	protected Thread thread;
	protected Buffer buffer;
	
	public ThreadedImage(int width, int height, Buffer buffer){
		super(width, height);
		this.buffer = buffer;
	}
	
	public ThreadedImage(String path, int width, int height, Buffer buffer) {
		super(path, width, height);
		this.buffer = buffer;
	}
	
	public void start(){
		thread.start();
	}
	
	// Functia pentru procesarea imaginii
	public void linearGrayscaleLevelTransform(BufferedImage image, int quarter){
		for(int i = 0; i < getHeight() / 2; i++){
			for(int j = 0; j < getWidth() / 2; j++){
				  int p = image.getRGB(j, i);
	                this.image.setRGB((quarter % 2) * (getWidth() / 2) + j,
							(quarter / 2) * (getHeight() / 2) + i, 255- p);	
			}
		}
	}
	
	public void sleep(int duration){
		try{
			thread.sleep(duration);
		}
		catch (InterruptedException e){
			
		}
	}
	
}
