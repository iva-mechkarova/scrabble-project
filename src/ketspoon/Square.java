package ketspoon;

public class Square {
	
	private int squareIndex;
	private Tile squaresTile;
	private char squaresChar;
	private int squareType;
	private boolean playableSquare;
	private boolean playedSquare;
	
	public Square(int index,int type,char c,boolean playeable) {
		squareIndex=index;
		setSquaresTile(null);
		squaresChar=c;
		setSquareType(type);
		setPlayableSquare(playeable);
		setPlayedSquare(false);
	}

	public char getSquaresChar() {
		return squaresChar;
	}


	public void setSquaresChar(char squaresChar) {
		this.squaresChar = squaresChar;
	}

	public Tile getSquaresTile() {
		return squaresTile;
	}

	public void setSquaresTile(Tile squaresTile) {
		this.squaresTile = squaresTile;
	}

	public boolean isPlayableSquare() {
		return playableSquare;
	}

	public void setPlayableSquare(boolean playableSquare) {
		this.playableSquare = playableSquare;
	}

	public boolean isPlayedSquare() {
		return playedSquare;
	}

	public void setPlayedSquare(boolean playedSquare) {
		this.playedSquare = playedSquare;
	}

	public int getSquareIndex() {
		return squareIndex;
	}

	public void setSquareIndex(int squareIndex) {
		this.squareIndex = squareIndex;
	}

	public int getSquareType() {
		return squareType;
	}

	public void setSquareType(int squareType) {
		this.squareType = squareType;
	}
	
}
