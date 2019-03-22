import jssc.SerialPort;
import jssc.SerialPortException;

/*
 Author: Mr. Suresh Radder
 Date: 14 Oct, 2017
 */

public class SerialPortHandle {
	SerialPort sp;
	String path;

	public SerialPortHandle(String path) {
		super();
		this.sp = new SerialPort(path);
		this.path = path;
		try {
			sp.openPort();
			sp.setParams(9600, 8, 1, 0);
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// Open serial port

	}

	public void writeLine(byte out) {
			try {
				sp.writeByte(out);

			} catch (SerialPortException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	public String readLine() {
		StringBuffer string = new StringBuffer();
		boolean quit = false;
		while (!quit) {
			byte[] buffer;
			try {
				buffer = sp.readBytes(1);
				// Read 1 bytes from serial port
				if (buffer[0] != 13) {
					string.append((char) (buffer[0]));
				}
				if (buffer[0] == 13) {
					// Read the following 10 character
					sp.readBytes(1);
					quit = true;
				}

			} catch (SerialPortException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return string.toString();
	}

}

