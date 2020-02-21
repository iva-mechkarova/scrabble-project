package ketspoon;

public class Square {
	
	private Tile squaresTile;
	private char squaresChar;
	private int squareType;
	private boolean playableSquare;
	private boolean playedSquare;
	
	
	public Square(int type,char c,boolean playeable) {
		squaresTile=null;
		squaresChar=c;
		squareType=type;
		playableSquare=playeable;
		playedSquare=false;
	}

	public char getSquaresChar() {
		return squaresChar;
	}


	public void setSquaresChar(char squaresChar) {
		this.squaresChar = squaresChar;
	}
	
}
