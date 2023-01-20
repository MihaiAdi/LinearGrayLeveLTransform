package packWork;
import java.awt.image.BufferedImage;

public class Buffer {
	
	BufferedImage image;
	boolean available;

	public Buffer(){
		this.image = null;
		this.available = false;
	}
	
	public synchronized BufferedImage getImage(){
		while(!this.available){
			try{
				wait();
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		this.available = false;
		notifyAll();
		return image;
	}
	
	public synchronized void put(BufferedImage image){
		while(this.available){
			try{
				wait();
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		this.image = image;
		this.available = true;
		notifyAll();
	}	
}
