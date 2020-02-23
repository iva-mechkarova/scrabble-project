package ketspoon;

import java.util.ArrayList;

public class Frame
{
	private ArrayList<Tile> lettersInFrame; //Stores the letters that each player has in their frame
	private final int FRAME_SIZE = 7; //Final int to store the size of the frame
	
	//Constructor for Frame
	public Frame(Pool pool) 
	{
		lettersInFrame = new ArrayList<>();
		fillFrame(pool);
	}
	
	//Allows a frame to be refilled from the pool
	public void fillFrame(Pool pool)
	{
		int lettersToAdd = FRAME_SIZE - lettersInFrame.size();
		for(int i=0; i<lettersToAdd; i++)
		{
			lettersInFrame.add(pool.drawTilePool());
		}	
	}
	
	//Allows access to the letters in the frame
	public ArrayList<Tile> getLettersInFrame()
	{
		return lettersInFrame;
	}
	
	//Allows letters to be removed from a frame and returns the letter that was removed
	public Tile removeTile(Tile letter)
	{
		return lettersInFrame.remove(lettersInFrame.indexOf(letter));
	}
	
	//Allows a check to be made if letters are in the frame
	public boolean checkLetter(Tile letter)
	{
		return lettersInFrame.contains(letter);
	}
	
	//Allows a check to be made to see if the frame is empty
	public boolean frameIsEmpty()
	{
		return lettersInFrame.isEmpty();
	}
	
	//Allows a frame to be displayed
	public String displayFrame()
	{
		return lettersInFrame.toString();
	}	
	
}
