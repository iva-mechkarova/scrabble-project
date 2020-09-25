package scrabble;

import javafx.scene.image.Image;

public class Tile {
	
	private char tileLetter;
	private int tileValue;
	private Image tileImage;
	private int TileSquareIndex;
	
	public Tile(char letter, int value, Image i) {
		this.tileLetter=letter;
		this.tileValue=value;
		tileImage=i;	
		
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

	public Image getTileImage() {
		return tileImage;
	}

	public int getTileSquareIndex() {
		return TileSquareIndex;
	}

	public void setTileSquareIndex(int tileSquareIndex) {
		TileSquareIndex = tileSquareIndex;
	}

}
