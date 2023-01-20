package packWork;

import java.io.*;
import java.awt.image.BufferedImage;

public class ImageConsumer extends ThreadedImage implements Runnable{
	private DataOutputStream out;
	private Timer timer;
	
	public ImageConsumer(int width, int height, Buffer buffer,  PipedOutputStream pipeOut){
		super(width, height, buffer);
		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		this.timer = new Timer();
		this.out = new DataOutputStream(pipeOut);
		if(super.thread == null){
			super.thread = new Thread(this);
	}
		this.buffer = buffer;
	}
	
	public void run(){
		timer.Set();
		
		for(int i = 0; i < 4; i++){
			BufferedImage image = buffer.getImage();
			linearGrayscaleLevelTransform(image, i);
			System.out.println("A fost procesat sfertul " + (i + 1) + " din imaginea trimisa ca input!");
			sleep(1000);
		}
		
		System.out.println("Procesarea imaginii a durat: " + timer.GetSeconds() + " secunde.");
		
		
		for (int x = 0; x < getWidth(); x++){
			for(int y = 0; y < getHeight(); y++){
				try{
					out.writeInt(image.getRGB(x, y));
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		
	}
	
}
