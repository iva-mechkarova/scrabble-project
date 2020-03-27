package ketspoon;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;

public class Pool {
	
	private ArrayList<Tile> gamePool; /* Array list to store tiles in pool */
	public ArrayList<Tile> tileTable; /* Array list to store all tiles */
	
	public Pool() {
		gamePool = new ArrayList<>(); 
		defaultPool();
	}
	
	/* this method is used to initialize the pool and to reset the pool */
	public void defaultPool() {
		int poolLetterCount[] = {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1,2}; /* number of letter in pool in order A-Z + Blank */
		for(int i=0; i<27;i++) {
			for(int j=0; j<poolLetterCount[i]; j++) {
				switch (i) {
				case 0 : gamePool.add(new Tile('A',1,new Image(getClass().getResourceAsStream("/resources/Letters/A.png"))));break;  
				case 1 : gamePool.add(new Tile('B',3,new Image(getClass().getResourceAsStream("/resources/Letters/B.png"))));break;   
				case 2 : gamePool.add(new Tile('C',3,new Image(getClass().getResourceAsStream("/resources/Letters/C.png"))));break;   
				case 3 : gamePool.add(new Tile('D',2,new Image(getClass().getResourceAsStream("/resources/Letters/D.png"))));break; 
				case 4 : gamePool.add(new Tile('E',1,new Image(getClass().getResourceAsStream("/resources/Letters/E.png"))));break;   
				case 5 : gamePool.add(new Tile('F',4,new Image(getClass().getResourceAsStream("/resources/Letters/F.png"))));break;   
				case 6 : gamePool.add(new Tile('G',2,new Image(getClass().getResourceAsStream("/resources/Letters/G.png"))));break;   
				case 7 : gamePool.add(new Tile('H',4,new Image(getClass().getResourceAsStream("/resources/Letters/H.png"))));break;  
				case 8: gamePool.add(new Tile('I',1,new Image(getClass().getResourceAsStream("/resources/Letters/I.png"))));break;   
				case 9 : gamePool.add(new Tile('J',8,new Image(getClass().getResourceAsStream("/resources/Letters/J.png"))));break;   
				case 10 : gamePool.add(new Tile('K',5,new Image(getClass().getResourceAsStream("/resources/Letters/K.png"))));break;   
				case 11 : gamePool.add(new Tile('L',1,new Image(getClass().getResourceAsStream("/resources/Letters/L.png"))));break;  
				case 12 : gamePool.add(new Tile('M',3,new Image(getClass().getResourceAsStream("/resources/Letters/M.png"))));break; 
				case 13 : gamePool.add(new Tile('N',1,new Image(getClass().getResourceAsStream("/resources/Letters/N.png"))));break;
				case 14 : gamePool.add(new Tile('O',1,new Image(getClass().getResourceAsStream("/resources/Letters/O.png"))));break;
				case 15: gamePool.add(new Tile('P',3,new Image(getClass().getResourceAsStream("/resources/Letters/P.png"))));break; 
				case 16 : gamePool.add(new Tile('Q',10,new Image(getClass().getResourceAsStream("/resources/Letters/Q.png"))));break;
				case 17 : gamePool.add(new Tile('R',1,new Image(getClass().getResourceAsStream("/resources/Letters/R.png"))));break;
				case 18 : gamePool.add(new Tile('S',1,new Image(getClass().getResourceAsStream("/resources/Letters/S.png"))));break; 
				case 19 : gamePool.add(new Tile('T',1,new Image(getClass().getResourceAsStream("/resources/Letters/T.png"))));break; 
				case 20 : gamePool.add(new Tile('U',1,new Image(getClass().getResourceAsStream("/resources/Letters/U.png"))));break;  
				case 21 : gamePool.add(new Tile('V',4,new Image(getClass().getResourceAsStream("/resources/Letters/V.png"))));break;  
				case 22 : gamePool.add(new Tile('W',4,new Image(getClass().getResourceAsStream("/resources/Letters/W.png"))));break;  
				case 23 : gamePool.add(new Tile('X',8,new Image(getClass().getResourceAsStream("/resources/Letters/X.png"))));break;  
				case 24 : gamePool.add(new Tile('Y',4,new Image(getClass().getResourceAsStream("/resources/Letters/Y.png"))));break;   
				case 25 : gamePool.add(new Tile('Z',10,new Image(getClass().getResourceAsStream("/resources/Letters/Z.png"))));break; 
				case 26 : gamePool.add(new Tile('+',0,new Image(getClass().getResourceAsStream("/resources/Letters/BLANK.png"))));break; 
				default:
					;
				}
			}
		}	
	}
	
	/* this method returns size of the pool */
	public int poolSize() {
		return gamePool.size();
	}
	
	/* this method returns true when the pool is empty */
	public boolean poolEmpty() {
		return gamePool.isEmpty();
	}
	
	/* this method removes a random tile from the pool form 0 to the pool size and returns the tiles it removes */
	public Tile drawTilePool() {
		Random rnd = new Random();
		return gamePool.remove(rnd.nextInt(poolSize()));
	}
	
	/* this method adds tiles which are being exchanged back into the pool */
	public void addTileToPool(Tile exchangedTile) 
	{
		gamePool.add(exchangedTile);
	}
}