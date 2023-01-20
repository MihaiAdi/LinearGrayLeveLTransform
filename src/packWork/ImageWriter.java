package packWork;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class ImageWriter extends Image implements Runnable{
	private DataInputStream in;
	private Timer timer;

	private Thread thread;
	private String path;
	
	public ImageWriter(String path, int width, int height, PipedInputStream pipeIn){
		super(width, height);
		
		this.in = new DataInputStream(pipeIn);
		this.path = path;
		this.timer = new Timer();
		this.thread = new Thread(this);
		}

	public void run(){
		image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		timer.Set();
		// Setez pixel cu pixel imaginea noua
		int prevProgress = -1;
		for (int x = 0; x < getWidth(); x++){
			for(int y = 0; y < getHeight(); y++){
				try{
					int pixel = in.readInt();
					image.setRGB(x, y, pixel);
				}
				catch(IOException e){
					e.printStackTrace();
				}
				
				int progress = 100 * (x * getHeight() + y) / getWidth() / getWidth();
				if(progress != prevProgress && progress % 25 == 0){
					System.out.println("A fost primit sfertul " + (progress + 25)/25 + " din imagine pentru a fi scrisa!");
					prevProgress = progress;
					sleep(1000);
				}
			}
		}
		
		System.out.println("A fost primita toata imaginea pentru a fi scrisa!");
		System.out.println("Primirea datelor pentru a fi scrise s-a executat in: " + timer.GetSeconds() + " secunde.");
		
		timer.Set();
		try{
		    File file = new File(path);
		    ImageIO.write(image, "bmp", file);
		    System.out.println("Scrierea imaginii s-a terminat cu bine!");
		}catch(IOException e){
			e.fillInStackTrace();
		}
		System.out.println("Scrierea imaginii a durat: " + timer.GetSeconds() + " secunde.");
	}
	
	public void start(){
		thread.start();
	}

	public void sleep(int duration){
		try{
			thread.sleep(duration);
		}
		catch (InterruptedException e){
			
		}
	}
}
