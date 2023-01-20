package packWork;

public  class Timer {
	private long lastTime;
	
	public Timer(){
		this.lastTime = 0;
	}
	
	public void Set(){
		lastTime = System.currentTimeMillis();
	}
	
	public double GetSeconds(){
		return (System.currentTimeMillis() - lastTime) / 1000.0;
	}
	
	public long GetMillis(){
		return System.currentTimeMillis() - lastTime;
	}
}
