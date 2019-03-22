
public class Gloves implements Observer, Command{
	SerialPortHandle sph;
	String i1, i2;
	char c =' ';
	
	public Gloves(SerialPortHandle sph, AE ds){
		this.sph = sph;
		ds.registerObserver(this);
		i1 = null;
		i2 = null;
	}
	
	public void update(Object o) {
		

		char c = (char)o;
		
		if ((c == 'D') || (c == 'G'))
			execute();
		else
			sph.writeLine((byte)c);

	}

	public char comp(Object o) {
		Combination cout = new Combination('o','o');
		i1 = sph.readLine();
		System.out.print(i1);
		
		i2 = sph.readLine();
		System.out.println(i2);

		cout.read(i1, i2);
		cout.print();
		
		if (cout.compare((Combination)o)) {
			System.out.println("Correct");
			return 'C';
		}
		else {

			System.out.println("Wrong");
			return 'W';
		}
	}

	public void execute() {
		sph.writeLine((byte)c);

	}

}
