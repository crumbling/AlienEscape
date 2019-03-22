
public interface Subject {
	void registerDisplay(Observer o);
	void registerObserver(Observer o);
	void removeObsever(Observer o);
	void removeDisplays(Observer o);
	void notifyObservers(char c);
	void notifyDisplays(Combination c);
}
