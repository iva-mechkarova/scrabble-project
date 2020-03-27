package ketspoon;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Board {
	public static final int NUMBEROFSQUARES=225;
	
	public final Image plainSquare = new Image(getClass().getResourceAsStream("/resources/plainSquare.png"));
	public final Image doubleLetter = new Image(getClass().getResourceAsStream("/resources/doubleLetter.png"));
	public final Image doubleWord = new Image(getClass().getResourceAsStream("/resources/doubleWord.png"));
	public final Image tripleLetter = new Image(getClass().getResourceAsStream("/resources/tripleLetter.png"));
	public final Image tripleWord= new Image(getClass().getResourceAsStream("/resources/tripleWord.png"));
	public final Image centerSquare= new Image(getClass().getResourceAsStream("/resources/centerSquare.png"));
	private int direction;
	
	public static final int HORIZONTAL=0;
	public static final int VERTICAL=1;
	public static final int ONESQUARE=3;
	
	
	public static final int DOUBLEWORD=0;	
	public static final int DOUBLELETTER=1;
	public static final int TRIPLEWORD=2;
	public static final int TRIPLELETTER=3;
	public static final int NORMAL=9;
	public static final int CENTER=8;
	
	
	private Square previousSquare; //Stores previously played square during turn
	
	public ArrayList<Square> gameBoard;

	public Board(){
		initializeBoard();
	}
	
	public void initializeBoard() {
		gameBoard = new ArrayList<>();
		for (int i = 0; i < NUMBEROFSQUARES; i++) { /* cases for each square type */
			switch (i) {
			case 16:case 28:case 32:case 42:case 48:case 56:case 64:case 70:case 154:case 160:case 168:case 176:case 182:case 192:case 196:case 208:
			{gameBoard.add(new Square(i,DOUBLEWORD,doubleWord,false));break;}
			
			case 3:case 11:case 36:case 38:case 45:case 52:case 59:case 92:case 96:case 98:case 102:case 108:case 116:case 122:case 126:case 128:case 132:case 165:case 172:case 179:case 186:case 188:case 213:case 221:
			{gameBoard.add(new Square(i,DOUBLELETTER,doubleLetter,false));break;}
			
			case 0:case 7:case 14:case 105:case 119:case 210:case 217:case 224:
			{gameBoard.add(new Square(i,TRIPLEWORD,tripleWord,false));break;}
			
			case 20:case 24:case 76:case 80:case 84:case 88:case 136:case 140:case 144:case 148:case 200:case 204:
			{gameBoard.add(new Square(i,TRIPLELETTER,tripleLetter,false));break;}
			
			case 112:
			{gameBoard.add(new Square(i,CENTER,centerSquare,true));break;}
				
			default:
				gameBoard.add(new Square(i,NORMAL,plainSquare,false));
				break;
			}
		}
	}
	
//	public void displayBoard() {
//		System.out.println("-------------------------------------------------------------");
//		for (int i = 0; i < NUMBEROFSQUARES; i++) {
//			System.out.print("| "+gameBoard.get(i).getSquaresChar()+" ");
//			if((i+1)%15==0) /* prints a line every 15 squares */
//				System.out.println("| "+i
//						/15+"\n-------------------------------------------------------------");
//		}
//		System.out.println("  0   1   2   3   4   5   6   7   8   9  10  11  12  13  14 ");
//	}
	
	public int coordinateToIndex(int x,int y) { /* converts a coordinate to array list index*/
		return (15*x)+y;
	}
	
	public void addTileToSquare(int x, Tile t) {     /*method to add a tile to a coordinate*/
		gameBoard.get(x).setSquaresTile(t); 
		gameBoard.get(x).setSquaresChar(t.getTileLetter());
		gameBoard.get(x).getSquareButton().setGraphic(new ImageView(t.getTileImage()));
		gameBoard.get(x).setPlayedSquare(true);
		updatePlayableSquares();
	}
	
	public void updateSquareDisabled() {
		for (int i = 0; i < NUMBEROFSQUARES; i++) {
			if(gameBoard.get(i).isPlayableSquare()&&!gameBoard.get(i).isPlayedSquare())
				gameBoard.get(i).getSquareButton().setDisable(false);
			
			if(!gameBoard.get(i).isPlayableSquare()&&!gameBoard.get(i).isPlayedSquare())
				gameBoard.get(i).getSquareButton().setDisable(true);	
		}
	}
	
	public void updatePlayableSquares() {    /*makes squares in the four positions around a played square available */
		for(Square currentSquare:gameBoard) {
			if(currentSquare.isPlayedSquare()) {
				
				if(currentSquare.getSquareIndex()+1>=0 && currentSquare.getSquareIndex()+1 < 225) {
					Square right = gameBoard.get(currentSquare.getSquareIndex()+1);
					if(right.getSquareIndex()%15!=0)
					{
						gameBoard.get(right.getSquareIndex()).setPlayableSquare(true);
						gameBoard.get(right.getSquareIndex()).getSquareButton().setDisable(false);	
					}	
				}
				
				if(currentSquare.getSquareIndex()+15>=0 && currentSquare.getSquareIndex()+15 < 225) {
					Square down = gameBoard.get(currentSquare.getSquareIndex()+15);
					gameBoard.get(down.getSquareIndex()).setPlayableSquare(true);
					gameBoard.get(down.getSquareIndex()).getSquareButton().setDisable(false);
					
				}
				
				if(currentSquare.getSquareIndex()-1>=0 && currentSquare.getSquareIndex()-1 < 225) {
					Square left = gameBoard.get(currentSquare.getSquareIndex()-1);
					if(left.getSquareIndex()%15!=14)
					{
						gameBoard.get(left.getSquareIndex()).setPlayableSquare(true);
						gameBoard.get(left.getSquareIndex()).getSquareButton().setDisable(false);
					}
					
				}
				
				if(currentSquare.getSquareIndex()-15>=0 && currentSquare.getSquareIndex()-15 < 225) {
					Square up = gameBoard.get(currentSquare.getSquareIndex()-15);
					gameBoard.get(up.getSquareIndex()).setPlayableSquare(true);
					gameBoard.get(up.getSquareIndex()).getSquareButton().setDisable(false);
					
				}

			}
		}
		updateSquareDisabled();
	}

	
	/*Method to change playable squares once player starts placing letters*/
	/*i.e. after first letter is placed only squares around this are playable and after second letter
	 is placed the direction of the word is decided*/
	/*The index of the played square is passed in and the player's frame*/
	public void possiblePlays(int index, ArrayList<Tile> placedTiles)
	{
		/*Sets all squares on board to not playable*/
		for(Square currentSquare:gameBoard)
		{
			currentSquare.setPlayableSquare(false);
			
		}
		
		//Get the played square from the index
		Square playedSquare = gameBoard.get(index); 
		
		//There is a max of 4 possible plays once a square has been placed
		//These variables store these plays
		Square right; 
		Square left;
		Square up;
		Square down;

		/*If exactly one tile has been placed, we'll have 4 squares to pick from for the next placement so we need right & left options*/
		/*If the index is less than absolute 15 then we must be going in a horizontal direction so we have right & left plays*/
		if(placedTiles.size()==1 || Math.abs(index - getPreviousSquare().getSquareIndex())<15)
		{
			direction = HORIZONTAL;
			
			//Initialize right & left variables as the squares to the right and left of the played square
			right = gameBoard.get(playedSquare.getSquareIndex()); 
			left = gameBoard.get(playedSquare.getSquareIndex());
			
			/*Check if the right square is a played square, if it is we need to check if the square to the right of that one is
			a playable square as we cannot place a tile on a played square*/
			int i =0;
			int differenceRight = 15-playedSquare.getSquareIndex()%15;
			while(right.isPlayedSquare() && i<=differenceRight)
			{
				if(playedSquare.getSquareIndex()+i <225) {
					right = gameBoard.get(playedSquare.getSquareIndex()+i);
					i++;
				}
				else
					break;
			}
			//If we have found a playable square, set it to playable
			if(i>=0 && i<=differenceRight) 
				right.setPlayableSquare(true);
			
			/*Check if the left square is a played square, if it is we need to check if the square to the left of that one is
			a playable square as we cannot place a tile on a played square*/
			i = 0;
			int differenceLeft = playedSquare.getSquareIndex()%15+1;
			while(left.isPlayedSquare()  && i<=differenceLeft)
			{
				if(playedSquare.getSquareIndex()-i >=0) {
					left = gameBoard.get(playedSquare.getSquareIndex()-i);
					i++;
				}
				else
					break;
			}
			
			//If we have found a playable square, set it to playable
			if(i>=0 && i<=differenceLeft) 	
				left.setPlayableSquare(true);	
		}
		
		/*If exactly one tile has been placed, we'll have 4 squares to pick from for the next placement so we need up & down options*/
		/*If the index is greater than absolute 15 then we must be going in a vertical direction so we have up & down plays*/
		if(placedTiles.size()==1 || Math.abs(index - getPreviousSquare().getSquareIndex())>=15)
		{
			direction = VERTICAL;
			//Initialize up & down variables as the squares above and below the played square
			up = gameBoard.get(playedSquare.getSquareIndex());
			down = gameBoard.get(playedSquare.getSquareIndex());
			/*Check if the above square is a played square, if it is we need to check if the square above that one is
			a playable square as we cannot place a tile on a played square*/
			int i = 0;
			while(up.isPlayedSquare())
			{
				if(playedSquare.getSquareIndex()-i >=0) {
					up = gameBoard.get(playedSquare.getSquareIndex()-i);
					i+=15;	
				}
				else
					break;
			}
			//If we have found a playable square, set it to playable
			if(i>15 && i<15*15) 
				up.setPlayableSquare(true);
			
			/*Check if the below square is a played square, if it is we need to check if the square below that one is
			a playable square as we cannot place a tile on a played square*/
			i = 0;
			while(down.isPlayedSquare())
			{
				if(playedSquare.getSquareIndex()+i <225) {
					down = gameBoard.get(playedSquare.getSquareIndex()+i);
					i+=15;
				}
				else
					break;
			}
			//If we have found a playable square, set it to playable
			if(i>15 && i<15*15) 
				down.setPlayableSquare(true);	
		}
		updateSquareDisabled(); //Call method to update squares i.e. if square is playable it'll appear blank 
		setPreviousSquare(gameBoard.get(index)); //Set previous square to the played square
	}
	
	/*Mutator method for previousSquare*/
	public void setPreviousSquare(Square prev)
	{
		previousSquare = prev;
	}
	
	/*Accessor method for previousSquare*/
	public Square getPreviousSquare()
	{
		return previousSquare;
	}

	public int getDirection() {
		return direction;
	}

}



