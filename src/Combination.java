 public class Combination {
	public char col; 
	public char dir;
	
	public Combination(char c, char d) {
		this.col= c;
		this.dir= d;
	}
	
	public Boolean compare(Combination c) {
		if ((this.col == c.col) && (this.dir == c.dir))
			return true;
		else
			return false;
	}
	
	public void print() {
		System.out.println("colour: " + col + " direction: " + dir);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void read(String i1, String i2) {

		if (i1.charAt(0) == 'l'){
			this.col = i1.charAt(1);
			this.dir = i2.charAt(1);
		}
		else{
			this.dir = i1.charAt(1);
			this.col = i2.charAt(1);
		}
	}
}


