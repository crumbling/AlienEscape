import java.util.ArrayList;
import java.io.*;

import javax.sound.sampled.*;

import java.util.Random;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class AlienEscapeMain {
	public static void main(String[] args) throws SerialPortException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		//setup
		SerialPortHandle sph = new SerialPortHandle("/dev/tty.usbserial-A700eDJO");
		AE ds = new AE(sph);
		Gloves g = new Gloves(sph, ds);
		Display d = new Display(sph, ds);
		Thread t = new Thread(ds);
		t.start();
	}
}
