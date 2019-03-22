import java.io.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class Song implements Runnable{
	String name;
	AdvancedPlayer ap;
	Thread t ;
	
	public Song(String name){
		this.name = name;
		t = new Thread(this);
		t.start();
	}
	
	public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		t.stop();
		
	}
	
	public void run(){
		try{
		FileInputStream fis = new FileInputStream(name);
		ap  = new AdvancedPlayer(fis);
		ap.play();
		Thread.sleep(100);
		} catch (JavaLayerException e){
			e.printStackTrace();
		} catch (InterruptedException e){
			e.printStackTrace();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
}
