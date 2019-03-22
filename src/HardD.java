
public class HardD implements Difficulty{

	public HardD() {

	}


	public void difficulty() {
		try {
			Thread.sleep(2500);	
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
	
	
}
