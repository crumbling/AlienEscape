import java.io.*;

import javax.sound.sampled.*;


public class Song_wav implements Runnable{
	File yourFile;
    AudioInputStream stream;
    AudioFormat format;
    DataLine.Info info;
    Clip clip;
    Thread t;
    
    public Song_wav(String name) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
    	yourFile = new File(name);
    	stream = AudioSystem.getAudioInputStream(yourFile);
	    format = stream.getFormat();
	    info = new DataLine.Info(Clip.class, format);
	    clip = (Clip) AudioSystem.getLine(info);
	    clip.open(stream);
	    t = new Thread(this);
	    t.start();
    }
    
    public void run(){
    	clip.start();
    }
}
