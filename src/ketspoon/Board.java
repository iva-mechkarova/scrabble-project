package ketspoon;

import java.util.ArrayList;

public class Board {
	public static final int NUMBEROFSQUARES=225;
	
	public static final int DOUBLEWORD=0;	
	public static final int DOUBLELETTER=1;
	public static final int TRIPLEWORD=2;
	public static final int TRIPLELETTER=3;
	public static final int NORMAL=9;
	private Square previousSquare;
	
	public ArrayList<Square> gameBoard;

	public Board(){
		initializeBoard();
	}
	
	public void initializeBoard() {
		gameBoard = new ArrayList<>();
		for (int i = 0; i < NUMBEROFSQUARES; i++) { /* cases for each square type */
			switch (i) {
			case 16:case 28:case 32:case 42:case 48:case 56:case 64:case 70:case 154:case 160:case 168:case 176:case 182:case 192:case 196:case 208:
			{gameBoard.add(new Square(i,DOUBLEWORD,'0',false));break;}
			
			case 3:case 11:case 36:case 38:case 45:case 52:case 59:case 92:case 96:case 98:case 102:case 108:case 116:case 122:case 126:case 128:case 132:case 165:case 172:case 179:case 186:case 188:case 213:case 221:
			{gameBoard.add(new Square(i,DOUBLELETTER,'1',false));break;}
			
			case 0:case 7:case 14:case 90:case 104:case 210:case 217:case 224:
			{gameBoard.add(new Square(i,TRIPLEWORD,'2',false));break;}
			
			case 20:case 24:case 76:case 80:case 84:case 88:case 136:case 140:case 144:case 148:case 200:case 204:
			{gameBoard.add(new Square(i,TRIPLELETTER,'3',false));break;}
			
			case 112:
			{gameBoard.add(new Square(i,NORMAL,'*',true));break;}
				
			default:
				gameBoard.add(new Square(i,NORMAL,'-',false));
				break;
			}
		}
	}
	
	public void displayBoard() {
		System.out.println("-------------------------------------------------------------");
		for (int i = 0; i < NUMBEROFSQUARES; i++) {
			System.out.print("| "+gameBoard.get(i).getSquaresChar()+" ");
			if((i+1)%15==0) /* prints a line every 15 squares */
				System.out.println("| "+i
						/15+"\n-------------------------------------------------------------");
		}
		System.out.println("  0   1   2   3   4   5   6   7   8   9  10  11  12  13  14 ");
	}
	
	public int coordinateToIndex(int x,int y) { /* converts a coordinate to array list index*/
		return (15*x)+y;
	}
	
	public void addTileToSquare(int x, int y, Tile t) {     /*method to add a tile to a coordinate*/
		
		/*If tile is out of bounds then need to exit method*/
		if(isOutOfBounds(x,y))
		{
			return;
		}
		
		/*If invalid placement is attempted then need to exit method*/
		if(!isValidPlacement(x,y))
		{
			return;
		}	

		gameBoard.get(coordinateToIndex(x, y)).setSquaresTile(t); 
		gameBoard.get(coordinateToIndex(x, y)).setSquaresChar(t.getTileLetter());
		gameBoard.get(coordinateToIndex(x, y)).setPlayedSquare(true);
		updatePlayableSquares();
	}
	
	public void updateSquareChars() {
		for (int i = 0; i < NUMBEROFSQUARES; i++) {
			if(gameBoard.get(i).isPlayableSquare()&&gameBoard.get(i).getSquareType()==9&&!gameBoard.get(i).isPlayedSquare())
				gameBoard.get(i).setSquaresChar(' ');
			
			if(!gameBoard.get(i).isPlayableSquare()&&gameBoard.get(i).getSquareType()==9&&!gameBoard.get(i).isPlayedSquare())
				gameBoard.get(i).setSquaresChar('-');	
		}
	}
	
	public void updatePlayableSquares() {    /*makes squares in the four positions around a played square available */
		for(Square currentSquare:gameBoard) {
			if(currentSquare.isPlayedSquare()) {
				Square right = gameBoard.get(currentSquare.getSquareIndex()+1);
				Square left = gameBoard.get(currentSquare.getSquareIndex()-1);
				Square up = gameBoard.get(currentSquare.getSquareIndex()+15);
				Square down = gameBoard.get(currentSquare.getSquareIndex()-15);

				gameBoard.get(right.getSquareIndex()).setPlayableSquare(true);
				gameBoard.get(left.getSquareIndex()).setPlayableSquare(true);
				gameBoard.get(up.getSquareIndex()).setPlayableSquare(true);
				gameBoard.get(down.getSquareIndex()).setPlayableSquare(true);
			
			}
		}
		updateSquareChars();
	}

	
	/*Method to check if coordinates are out of bounds*/
	public boolean isOutOfBounds(int x, int y)
	{
		if(x > 14 || x < 0 || y > 14 || y < 0)
		{
			System.out.println("Coordinates (" + x + ", " + y + ") are out of bounds. Please select coordinates which are in bounds of the board.");
			return true;
		}	
		
		return false;
	}
	
	/*Method to check if the tile is a playable square.*/
	/*I.e. first tile must be placed in middle, all other tiles must be placed connecting to existing words on board*/
	public boolean isValidPlacement(int x, int y)
	{
		if(isOutOfBounds(x,y))
		{
			return false;
		}
		
		if(gameBoard.get(coordinateToIndex(x, y)).isPlayedSquare())
		{
			System.out.println("Cannot place tiles on top of eachother");
			return false;
		}
		
		if(!gameBoard.get(coordinateToIndex(x, y)).isPlayableSquare())
		{
			System.out.println("Cannot place tile here");
			System.out.println("First tile must be placed in middle of board and all other tiles must connect to already existing words on board");
			return false;
		}
		
		
		return true;
	}
	
	public void possiblePlays(int index, Frame playersFrame)
	{
		for(Square currentSquare:gameBoard)
		{
			currentSquare.setPlayableSquare(false);
			
		}
		
		Square playedSquare = gameBoard.get(index);
		
		Square right;
		Square left;
		Square up;
		Square down;

		if(playersFrame.getRemainingTurns()==7 || Math.abs(index - getPreviousSquare().getSquareIndex())<15)
		{
			
			right = gameBoard.get(playedSquare.getSquareIndex());
			left = gameBoard.get(playedSquare.getSquareIndex());
			
			int i = 0;
			while(right.isPlayedSquare() && i<=15)
			{
				i++;
				right = gameBoard.get(playedSquare.getSquareIndex()+i);
			}
			
			if(i>0 && i<15) 
				right.setPlayableSquare(true);
			
			i = 0;
			while(left.isPlayedSquare()  && i<=15)
			{
				i++;
				left = gameBoard.get(playedSquare.getSquareIndex()-i);
			}
			
			if(i>0 && i<15) 	
				left.setPlayableSquare(true);	
		}
		
		if(playersFrame.getRemainingTurns()==7 || Math.abs(index - getPreviousSquare().getSquareIndex())>=15)
		{
			up = gameBoard.get(playedSquare.getSquareIndex());
			down = gameBoard.get(playedSquare.getSquareIndex());
			
			int i = 15;
			while(up.isPlayedSquare() && i<=15*15)
			{
				up = gameBoard.get(playedSquare.getSquareIndex()+i);
				i+=15;
			}
			
			if(i>15 && i<15*15) 
				up.setPlayableSquare(true);
			
			i = 15;
			while(down.isPlayedSquare()  && i<=15*15)
			{
				down = gameBoard.get(playedSquare.getSquareIndex()-i);
				i+=15;
			}
			
			if(i>15 && i<15*15) 
				down.setPlayableSquare(true);
				
		}


		updateSquareChars();
		setPreviousSquare(gameBoard.get(index));
		playersFrame.decrementTurn();
	}
	
	public void setPreviousSquare(Square prev)
	{
		previousSquare = prev;
	}
	
	public Square getPreviousSquare()
	{
		return previousSquare;
	}

}



