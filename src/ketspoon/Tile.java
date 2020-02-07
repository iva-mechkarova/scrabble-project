package ketspoon;

public class Tile {
	
	private char tileLetter;
	private int tileValue;
	
	public Tile(char letter, int value) {
		this.tileLetter=letter;
		this.tileValue=value;
	}

	public int getTileValue() {
		return tileValue;
	}

	public char getTileLetter() {
		return tileLetter;
	}
	
	public String toString() {
	    return getTileLetter()+ "("+getTileValue()+")";
	} 
}
