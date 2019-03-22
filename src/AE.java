import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;



public class AE implements Subject, Runnable{
	ArrayList panels;
	ArrayList gloves;
	SerialPortHandle sph;
	char[] colours = {'r', 'y', 'g', 'b'};
	char[] dir = {'X','Y','Z'};
	ArrayList combinations = new ArrayList();
	Combination cin, cout = new Combination('o','o');
	int j, round = 1;
	Random r = new Random();
	String i1, i2;
	int flag = 0;
	Song bgm;
	Difficulty diff;
	EasyD ed = new EasyD();
	MediumD md = new MediumD();
	HardD hd = new HardD();
	
	public AE(SerialPortHandle sph){
		panels = new ArrayList();
		gloves = new ArrayList();
		this.sph = sph;
		bgm = new Song("Coldplay - A L I E N S (Instrumental).mp3");
	}
	
	public void makeEasy(){
		diff = ed;
	}
	
	public void makeMedium(){
		diff = md;
	}
	
	public void makeHard(){
		diff = hd;
	}
	
	public void registerDisplay(Observer o) {
		panels.add(o);
	}

	public void registerObserver(Observer o) {
		gloves.add(o);
	}

	public void removeDisplays(Observer o) {
		int i = panels.indexOf(o);
		if (i >= 0) panels.remove(o);
	}
	
	public void removeObsever(Observer o) {
		int i = gloves.indexOf(o);
		if (i >= 0) gloves.remove(o);
	}

	public void notifyDisplays(Combination c) {
		for(int i=0; i<panels.size(); i++) {
			Observer o = (Observer)panels.get(i);
			o.update(c);
			}
	}

	public void notifyObservers(char c) {
		for(int i=0; i<gloves.size(); i++) {
			Gloves o = (Gloves)gloves.get(i);
			o.update(c);
		}
	}
	
	private void readObservers(Combination comp) {
		for(int i=0; i<gloves.size(); i++) {
			Gloves o = (Gloves)gloves.get(i);
			char resp = o.comp(comp);
			notifyObservers(resp);
			if (resp == 'W'){
				flag = 1;
				round = 1;
			}
			}
	}
	
	public void set_difficulty() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		System.out.println("Choose Your Level");
		try {
			Thread.sleep(2000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		notifyObservers('D');
		//sph.writeLine((byte)'D');

		Song_wav gameover = new Song_wav("Recording_7.wav");
		String di = sph.readLine();

		System.out.println(di + "1");
		if (di.charAt(0) == 'e')
			this.makeEasy();
		
		else if (di.charAt(0) == 'm')
			this.makeMedium();
		
		else if (di.charAt(0) == 'h')
			this.makeHard();
		try {
			Thread.sleep(2000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void start_gameplay(){
		{
			flag = 0;
			cin = new Combination(colours[r.nextInt(4)], dir[r.nextInt(3)]);
			combinations.add(cin);
			
			for (int j = 0; j <combinations.size(); j++) {
				cin = (Combination)combinations.get(j);
				notifyDisplays((Combination)combinations.get(j));
				try {
					Thread.sleep(1000);	
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void initiate_player_input(){
		System.out.println("Your turn");
		diff.difficulty();
	}
	
	public void compare_player_inputs(){
			System.out.print("Combination " + (j+1) + ":");
			try {
				Thread.sleep(2000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			notifyObservers('G');
			readObservers((Combination)combinations.get(j));	

	}
	
	public void next_stage(){
		try {
			Song_wav gameover = new Song_wav("smb_stage_clear.wav");
		} catch (UnsupportedAudioFileException | IOException
				| LineUnavailableException e1) {
			e1.printStackTrace();
		}
		try{
			Thread.sleep(5000);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("Congratulations, moving to next round");
		round++;
	}
	
	public void end_game(){
		try {
			bgm.stop();
			Song_wav gameover = new Song_wav("smb_gameover.wav");
		} catch (UnsupportedAudioFileException | IOException
				| LineUnavailableException e1) {
			e1.printStackTrace();
		}
		try{
			Thread.sleep(5000);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("Game over");

	}
	
	public void AlienEscape() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
				System.out.println("Welcome to Alien Escape");
				set_difficulty();
				while(true){
				start_gameplay();
				initiate_player_input();
				for (j = 0; j <combinations.size(); j++){
					compare_player_inputs();
					diff.difficulty();
				}
				if (flag == 0) {
					next_stage();
					
				}
				else {
					end_game();
				}
				}
		
	}
	
	public void run(){
		{
		try {
			AlienEscape();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		}
	}
}


	

