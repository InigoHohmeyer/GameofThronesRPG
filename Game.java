import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by inigohohmeyer on 6/23/16.
 */

public class Game implements Runnable {
	private Display display;
	public int width, height;
	private boolean running = false;
	private Thread thread;
	private BufferStrategy bs;
	private Graphics g;
	public Game(String title, int width, int height){
		this.width = width ;
		this.height = height;
		display = new Display(title,width,height);

	}
	private void init(){


	}
	private void update(){

	}
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Draw Here

		g.fillRect(0,0,width,height);

		//End Drawing
		bs.show();
		g.dispose();
	}
		public void run(){
		init();
			while(running){
				update();
				render();
			}
			stop();
}
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();


	}
	public synchronized void stop(){
		if(running)
			return;
		running = false;
		try{
			thread.join();
		} catch (InterruptedException e){
			e.printStackTrace();
		}


	}
}
