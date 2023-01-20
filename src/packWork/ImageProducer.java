package packWork;

public class ImageProducer extends ThreadedImage implements Runnable{
	
	public ImageProducer(String fileName, int width, int height, Buffer buffer){

		super(fileName, width, height, buffer);
		
		if(super.thread == null)
			super.thread = new Thread(this);
		
		this.buffer = buffer;
	}
	
	@Override
	public void run(){
		for(int i = 0; i < 4; i++){
			  int x = (i % 2) * (getWidth() / 2);
	            int y = (i / 2) * (getHeight() / 2);
	            int w = getWidth() / 2;
	            int h = getHeight() / 2;
			
			buffer.put(
				image.getSubimage(x, y, w, h)
				);
			
			System.out.println("S-a trimis sfertul " + (i + 1) + " din imagine!");	
			sleep(1000);
		}
	}
}
