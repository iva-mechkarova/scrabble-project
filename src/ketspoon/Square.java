 package ketspoon;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class Square {
	
	private int squareIndex;
	private Tile squaresTile;
	private char squaresChar;
	private int squareType;
	private boolean playableSquare;
	private boolean playedSquare;
	private Button squareButton;
	private Image squareImage;
	
	
	public Square(int index,int type,Image i,boolean playeable) {
		squareIndex=index;
		squaresTile=null;
		squareImage=i;
		squareType=type;
		playableSquare=playeable;
		playedSquare=false;
		squareButton=new Button();
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

	public Button getSquareButton() {
		return squareButton;
	}

	public void setSquareButton(Button squareButton) {
		this.squareButton = squareButton;
	}

	public Image getSquareImage() {
		return squareImage;
	}

	public void setSquareImage(Image squareImage) {
		this.squareImage = squareImage;
	}
	
}
