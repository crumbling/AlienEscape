
public class Display implements Observer{
	SerialPortHandle sph;
	
	public Display(SerialPortHandle sph, AE ds){
		ds.registerDisplay(this);
		this.sph = sph;
	}
	
	public void update(Object o) {
		sph.writeLine((byte)((Combination)o).col);
		sph.writeLine((byte)((Combination)o).dir);
		((Combination)o).print();

	}


}
