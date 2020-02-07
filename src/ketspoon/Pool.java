package ketspoon;

import java.util.ArrayList;
import java.util.Random;

public class Pool {
	
	private ArrayList<Tile> gamePool; /* Array list to store tiles in pool */
	public ArrayList<Tile> tileTable; /* Array list to store all tiles */
	
	public Pool() {
		
		gamePool = new ArrayList<>(); 
		tileTable = new ArrayList<>(); 
		
		/* Creating the tile objects and adding them to the tile table */
		Tile A = new Tile('A',1);   /**/   tileTable.add(A);
		Tile B = new Tile('B',3);   /**/   tileTable.add(B);
		Tile C = new Tile('C',3);   /**/   tileTable.add(C);   
		Tile D = new Tile('D',2);   /**/   tileTable.add(D);
		Tile E = new Tile('E',1);   /**/   tileTable.add(E);
		Tile F = new Tile('F',4);   /**/   tileTable.add(F);
		Tile G = new Tile('G',2);   /**/   tileTable.add(G);
		Tile H = new Tile('H',4);   /**/   tileTable.add(H);
		Tile I = new Tile('I',1);   /**/   tileTable.add(I);
		Tile J = new Tile('J',8);   /**/   tileTable.add(J);
		Tile K = new Tile('K',5);   /**/   tileTable.add(K);
		Tile L = new Tile('L',1);   /**/   tileTable.add(L);
		Tile M = new Tile('M',3);   /**/   tileTable.add(M);
		Tile N = new Tile('N',1);   /**/   tileTable.add(N);
		Tile O = new Tile('O',1);   /**/   tileTable.add(O);
		Tile P = new Tile('P',3);   /**/   tileTable.add(P);
		Tile Q = new Tile('Q',10);  /**/   tileTable.add(Q);
		Tile R = new Tile('R',1);   /**/   tileTable.add(R);
		Tile S = new Tile('S',1);   /**/   tileTable.add(S);
		Tile T = new Tile('T',1);   /**/   tileTable.add(T);
		Tile U = new Tile('U',1);   /**/   tileTable.add(U);
		Tile V = new Tile('V',4);   /**/   tileTable.add(V);
		Tile W = new Tile('W',4);   /**/   tileTable.add(W);
		Tile X = new Tile('X',8);   /**/   tileTable.add(X);
		Tile Y = new Tile('Y',4);   /**/   tileTable.add(Y);
		Tile Z = new Tile('Z',10);  /**/   tileTable.add(Z);
		Tile blank = new Tile('-',0);   /**/ tileTable.add(blank);
		
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
}