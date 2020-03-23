package ketspoon;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;

public class Pool {
	
	private ArrayList<Tile> gamePool; /* Array list to store tiles in pool */
	public ArrayList<Tile> tileTable; /* Array list to store all tiles */
	
	public Pool() {
		
		gamePool = new ArrayList<>(); 
		tileTable = new ArrayList<>(); 
		
		/* Creating the tile objects and adding them to the tile table */
		Tile A = new Tile('A',1,new Image(getClass().getResourceAsStream("/resources/Letters/A.png")));   /**/   tileTable.add(A);
		Tile B = new Tile('B',3,new Image(getClass().getResourceAsStream("/resources/Letters/B.png")));   /**/   tileTable.add(B);
		Tile C = new Tile('C',3,new Image(getClass().getResourceAsStream("/resources/Letters/C.png")));   /**/   tileTable.add(C);   
		Tile D = new Tile('D',2,new Image(getClass().getResourceAsStream("/resources/Letters/D.png")));   /**/   tileTable.add(D);
		Tile E = new Tile('E',1,new Image(getClass().getResourceAsStream("/resources/Letters/E.png")));   /**/   tileTable.add(E);
		Tile F = new Tile('F',4,new Image(getClass().getResourceAsStream("/resources/Letters/F.png")));   /**/   tileTable.add(F);
		Tile G = new Tile('G',2,new Image(getClass().getResourceAsStream("/resources/Letters/G.png")));   /**/   tileTable.add(G);
		Tile H = new Tile('H',4,new Image(getClass().getResourceAsStream("/resources/Letters/H.png")));   /**/   tileTable.add(H);
		Tile I = new Tile('I',1,new Image(getClass().getResourceAsStream("/resources/Letters/I.png")));   /**/   tileTable.add(I);
		Tile J = new Tile('J',8,new Image(getClass().getResourceAsStream("/resources/Letters/J.png")));   /**/   tileTable.add(J);
		Tile K = new Tile('K',5,new Image(getClass().getResourceAsStream("/resources/Letters/K.png")));   /**/   tileTable.add(K);
		Tile L = new Tile('L',1,new Image(getClass().getResourceAsStream("/resources/Letters/L.png")));   /**/   tileTable.add(L);
		Tile M = new Tile('M',3,new Image(getClass().getResourceAsStream("/resources/Letters/M.png")));   /**/   tileTable.add(M);
		Tile N = new Tile('N',1,new Image(getClass().getResourceAsStream("/resources/Letters/N.png")));   /**/   tileTable.add(N);
		Tile O = new Tile('O',1,new Image(getClass().getResourceAsStream("/resources/Letters/O.png")));   /**/   tileTable.add(O);
		Tile P = new Tile('P',3,new Image(getClass().getResourceAsStream("/resources/Letters/P.png")));   /**/   tileTable.add(P);
		Tile Q = new Tile('Q',10,new Image(getClass().getResourceAsStream("/resources/Letters/Q.png")));  /**/   tileTable.add(Q);
		Tile R = new Tile('R',1,new Image(getClass().getResourceAsStream("/resources/Letters/R.png")));   /**/   tileTable.add(R);
		Tile S = new Tile('S',1,new Image(getClass().getResourceAsStream("/resources/Letters/S.png")));   /**/   tileTable.add(S);
		Tile T = new Tile('T',1,new Image(getClass().getResourceAsStream("/resources/Letters/T.png")));   /**/   tileTable.add(T);
		Tile U = new Tile('U',1,new Image(getClass().getResourceAsStream("/resources/Letters/U.png")));   /**/   tileTable.add(U);
		Tile V = new Tile('V',4,new Image(getClass().getResourceAsStream("/resources/Letters/V.png")));   /**/   tileTable.add(V);
		Tile W = new Tile('W',4,new Image(getClass().getResourceAsStream("/resources/Letters/W.png")));   /**/   tileTable.add(W);
		Tile X = new Tile('X',8,new Image(getClass().getResourceAsStream("/resources/Letters/X.png")));   /**/   tileTable.add(X);
		Tile Y = new Tile('Y',4,new Image(getClass().getResourceAsStream("/resources/Letters/Y.png")));   /**/   tileTable.add(Y);
		Tile Z = new Tile('Z',10,new Image(getClass().getResourceAsStream("/resources/Letters/Z.png")));  /**/   tileTable.add(Z);
		Tile blank = new Tile('+',0,new Image(getClass().getResourceAsStream("/resources/Letters/BLANK.png")));   /**/ tileTable.add(blank);
		
		defaultPool();
	}
	
	/* this method is used to initialize the pool and to reset the pool */
	public void defaultPool() {
		int poolLetterCount[] = {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1,2}; /* number of letter in pool in order A-Z + Blank */
		for(int i=0; i<tileTable.size();i++) {
			for(int j=0; j<poolLetterCount[i]; j++) {
				gamePool.add(tileTable.get(i));
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