package ketspoon;

public class TestPlayer {
	
	public static void main(String[] args) {
		Pool testPool = new Pool();
		System.out.println("Pool size:"+testPool.poolSize());
		
		
		Frame p1Frame = new Frame(testPool);
		Player p1= new Player(p1Frame, "PLAYER1");
		
		System.out.println(p1.toString());
		p1Frame.displayFrame();
		System.out.println("Pool size:"+testPool.poolSize());
		
		Frame p2Frame = new Frame(testPool);
		Player p2= new Player(p2Frame, "PLAYER2");
		
		System.out.println(p2.toString());
		p2Frame.displayFrame();
		System.out.println("Pool size:"+testPool.poolSize());
		
		TestDisplay testFrame= new TestDisplay();
		
		testFrame.endGame();
//		testFrame.displayFrame(p1Frame,p1,);
//		testFrame.displayFrame(p2Frame,p2);
	}
}
