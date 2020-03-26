package ketspoon;


public class Player
{
	private String playerName ;    
	private int playerScore;       
	
	Frame playerFrame;
	
	
	public Player(Frame frame, String name)
	{
		this.playerFrame=frame;
		this.playerName = name;
	}
	
	public void updateScore(int score)
	{
		playerScore+=score;
	}

	public void resetData()  //Allows the player data to be reset//
	{
		playerName = null;
		playerScore = 0;
	}
	
	public void setName(String name) //Allows the name of the player to be set//
	{
		this.playerName = name;
	}
	
	public String getName() //Allows access to a players name//
	{
		return playerName;
	}
	
	public int getScore() //Allows access to a players score//
	{
		return playerScore;
	}
	
	public String toString() //Allows display of a players name//
	{
		return "PLAYER NAME:"+playerName+"\nSCORE:"+playerScore+"\nFrame:"+playerFrame.displayFrame();
	}

}
