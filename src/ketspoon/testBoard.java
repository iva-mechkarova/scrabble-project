
package ketspoon;


public class testBoard {

	public static void main(String[] args) {
		Board testBoard = new Board();

		
		Pool testPool = new Pool();
		testBoard.displayBoard();
		
		
		Frame p1Frame = new Frame(testPool);
		Player p1= new Player(p1Frame, "PLAYER1");
		
		TestDisplay testFrame= new TestDisplay();
		testFrame.endGame();
		testFrame.displayFrame(p1Frame,p1,testBoard);
	}

}
