import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Bot0 implements BotAPI {

    // The public API of Bot must not change
    // This is ONLY class that you can edit in the program
    // Rename Bot to the name of your team. Use camel case.
    // Bot may not alter the state of the game objects
    // It may only inspect the state of the board and the player objects

    private PlayerAPI me;
    private OpponentAPI opponent;
    private BoardAPI board;
    private UserInterfaceAPI info;
    private DictionaryAPI dictionary;
    private int turnCount;
    
    private String myLetter;
    
    public static ArrayList<Word> allPossibleCombo=new ArrayList<Word>();
    
    public static ArrayList<Word> realPlayableWords= new ArrayList<Word>();
    public static ArrayList<Integer> realPlayableWordsScore= new ArrayList<Integer>();
    
    private Square[][] boardCopy;
    private ArrayList<Word> wordsOnBoard = new ArrayList<Word>();

    Bot0(PlayerAPI me, OpponentAPI opponent, BoardAPI board, UserInterfaceAPI ui, DictionaryAPI dictionary) {
        this.me = me;
        this.opponent = opponent;
        this.board = board;
        this.info = ui;
        this.dictionary = dictionary;
        turnCount = 0;
    }

    public String getCommand() {
    	String direction = null;
    	List<String> list =null;
    	try {
    		list = Files.readAllLines( new File( "csw.txt" ).toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	setMyLetter(me.getFrameAsString().replaceAll(", ", "").replaceAll("\\]", "").replaceAll("\\[", "").replace("_", ""));
        String command = "";
        
        if(turnCount==0) {
        	command = "NAME Ketspoon";
        }
        else if(board.isFirstPlay()&&turnCount>0) {
        	findrealPlayableWords("",myLetter, list,7,7,true);
        	realPlayableWordscores();
        	int realWordIndex=realPlayableWordsScore.indexOf(Collections.max(realPlayableWordsScore));
        	command = "H8 A "+realPlayableWords.get(realWordIndex).getLetters();
        }
        
        
        else {
        	getVerticalWords();
        	getHorizontalWords();
        	for (int i = 0; i < wordsOnBoard.size(); i++) {
        		findrealPlayableWords(wordsOnBoard.get(i).getLetters(),myLetter,list,wordsOnBoard.get(i).getRow(),wordsOnBoard.get(i).getColumn(),wordsOnBoard.get(i).isHorizontal());
			}
        	
        	realPlayableWordscores();
        	int realWordIndex=realPlayableWordsScore.indexOf(Collections.max(realPlayableWordsScore));

        	int row = realPlayableWords.get(realWordIndex).getRow();
        	int col = realPlayableWords.get(realWordIndex).getFirstColumn();
        	
        	int rowOffset=0;
        	int colOffset=0;
        	
        	char letterOnBoard=boardCopy[row][col].getTile().getLetter();
        	
        	if(realPlayableWords.get(realWordIndex).isVertical()) {
        		direction="D";
        		rowOffset=realPlayableWords.get(realWordIndex).getLetters().indexOf(letterOnBoard);
        	}
        	
        	else {
        		direction="A";
        		colOffset=realPlayableWords.get(realWordIndex).getLetters().indexOf(letterOnBoard);
        	}
     
        	char colChar=(char)(col+65-colOffset);
            command = colChar+""+(row+1-rowOffset)+" "+direction+" "+realPlayableWords.get(realWordIndex).getLetters();
        }
        realPlayableWords.clear();
        realPlayableWordsScore.clear();
        allPossibleCombo.clear();
        wordsOnBoard.clear();
        turnCount++;
        return command;
    }
    
    
	public String getMyLetter() {
		return myLetter;
	}

	public void setMyLetter(String myLetter) {
		this.myLetter = myLetter;
	}
	
	public void realPlayableWordscores() {
		int[] allLetterValues = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
		getBoardCopy();
		
		for (int realWordIndex = 0; realWordIndex < realPlayableWords.size(); realWordIndex++) {
			int wordScore=0,tripleWord=0,doubleWord=0,rowOffset=0,colOffset=0;
			int row = realPlayableWords.get(realWordIndex).getRow();
	    	int col = realPlayableWords.get(realWordIndex).getFirstColumn();

	    	if(!board.isFirstPlay()) {
	    		char letterOnBoard=boardCopy[row][col].getTile().getLetter();
		    	
		    	if(realPlayableWords.get(realWordIndex).isVertical())
		    		rowOffset=realPlayableWords.get(realWordIndex).getLetters().indexOf(letterOnBoard);
		    	else
		    		colOffset=realPlayableWords.get(realWordIndex).getLetters().indexOf(letterOnBoard);
	    	}
	    	
	    	int letterRow=row-rowOffset;
	    	int letterCol=col-colOffset;
	    	
	    	for (int i = 0; i <realPlayableWords.get(realWordIndex).getLetters().length(); i++) {
	    		int letterValue=allLetterValues[realPlayableWords.get(realWordIndex).getLetter(i)-'A'];
	    		if(letterRow==row && letterCol==col) {
	    			wordScore+=letterValue;
	    		}
	    		else {
	    			if(boardCopy[letterRow][letterCol].isDoubleLetter())
	    				wordScore+=letterValue*2;
	    			else if(boardCopy[letterRow][letterCol].isTripleLetter())
	    				wordScore+=letterValue*2;
	    			else
	    				wordScore+=letterValue;
	    			if(boardCopy[letterRow][letterCol].isDoubleWord())
	    				doubleWord++;
	    			if(boardCopy[letterRow][letterCol].isTripleWord())
	    				tripleWord++;
	    		}
	    		if(realPlayableWords.get(realWordIndex).isVertical()) {
	    			letterRow++;
	        	}
	    		else
	        		letterCol++;
	    		
			}
	    	if(doubleWord>0)
	    		wordScore*=2;
	    	if (tripleWord>0)
	    		wordScore*=3;
	    	realPlayableWordsScore.add(wordScore);
		}
	}
	
	private void findrealPlayableWords(String wordOnBoard,String myLetters, List<String> list,int row,int col,boolean horizontal )
    {
        int[] freq = toFreq(wordOnBoard+myLetters);
        for (String s:list)
        {
            int[] freqIn = toFreq(s);
            if ( matches(freq,freqIn))
            	if(wordOnBoard.equals("") || (s.contains(wordOnBoard) && !s.equals(wordOnBoard))) {
            		if(!board.isFirstPlay()) {
            			getBoardCopy();
            			int rowOffset=0;
                    	int colOffset=0;
                    	boolean validCross=true;
                    	char letterOnBoard=boardCopy[row][col].getTile().getLetter();
                    	if(horizontal) 
                    	{
                    		colOffset=s.indexOf(letterOnBoard);
                    		if(col-colOffset>=0 && col-colOffset+s.length()-1<=14) 
                    		{
                    			for (int i = col-colOffset,j=0; i < col-colOffset+s.length(); i++,j++) {
                    				/*if the letter in the occupied squares do not match the letters of the playable word then 
                    				 * the word is not playable */
                    				if (boardCopy[row][i].isOccupied() && s.charAt(j)!=boardCopy[row][i].getTile().getLetter())
										validCross=false;
								}
                    			if(validCross)
                    				realPlayableWords.add(new Word(row, col, horizontal, s));
                    		}
                    	}
            			if(!horizontal) 
            			{
                    		rowOffset=s.indexOf(letterOnBoard);
                    		if(row-rowOffset>=0 && row-rowOffset+s.length()-1<=14) 
                    		{
                    			for (int i = row-rowOffset,j=0; i < row-rowOffset+s.length(); i++,j++) {
                    				/*if the letter in the occupied squares do not match the letters of the playable word then 
                    				 * the word is not playable*/
                    				if (boardCopy[i][col].isOccupied() && s.charAt(j)!=boardCopy[i][col].getTile().getLetter())
										validCross=false;
								}
                    			if(validCross)
                    				realPlayableWords.add(new Word(row, col, horizontal, s));	
                    		}
                    	}
            		}
            		else
            			realPlayableWords.add(new Word(row, col, horizontal, s));
            	}
        }
    }
	
    private boolean matches( int[] freq, int[] freqIn )
    {
        for (int i = 0; i < 26; i++)
        {
            if ( freq[i] == 0 && freqIn[i]>0)
                return false;
            else if (freq[i] < freqIn[i])
                return false;

        }
        return true;
    }
    private int[] toFreq( String string )
    {
        int[] freq = new int[26];
        for (char c : string.toCharArray())
        {
            if ((c-'A')>=0 && (c-'A')< 26)
                freq[c - 'A']++;
        }
        return freq;
    }
	
	
	
	/**Method to get a copy of the current state of the board*/
	public void getBoardCopy()
	{
		boardCopy = new Square[15][15]; 
		for (int row = 0; row < 15; row++) 
		{ 
			for (int col = 0; col < 15; col++) 
			{ 
				boardCopy[row][col] = board.getSquareCopy(row,col);
			} 
		}
	}
	
	/**Method to get an array list of the current horizontal words on the board*/
	public void getHorizontalWords()
	{
		Word word; //Variable to store each word
		getBoardCopy();
		
		/*Iterate through each square of the board, checking to see if any squares are occupied*/
		for(int i=0; i<15; i++)
		{
			for(int j=0; j<15; j++)
			{
				if(boardCopy[i][j].isOccupied())
				{	
					String wordString = boardCopy[i][j].getTile().toString();
					if(j==14) //If it starts in the last column then the word only consists of one letter
					{
						word = new Word(i, j, true, wordString);
						wordsOnBoard.add(word);
					}
					else if (j<14)
					{
						if(!boardCopy[i][j+1].isOccupied()) //If the square beside it isn't occupied then we have found the whole word
						{
							word = new Word(i, j, true, wordString);
							wordsOnBoard.add(word);
						}
						else //Else we need to find all of the letters of the word
						{
							int col = j; //Stores column position of first letter of word
							j+=1;
							//Keep looping while there are occupied squares to the right of the occupied square to find the whole word
							while(j<15 && boardCopy[i][j].isOccupied())
							{
								wordString += boardCopy[i][j].getTile().toString();
								j++;
							}
							
							word = new Word(i, col, true, wordString);
							wordsOnBoard.add(word);
						}
					}
				}
			}	
		}	
	}
	
	/**Method to get an array list of the current vertical words on the board*/
	public void getVerticalWords()
	{
		Word word; //Variable to store each word
		getBoardCopy();
		
		/*Iterate through each square of the board, checking to see if any squares are occupied*/
		for(int i=0; i<15; i++)
		{
			for(int j=0; j<15; j++)
			{
				if(boardCopy[j][i].isOccupied()) //If the square beside it isn't occupied then we have found the whole word
				{	
					String wordString = boardCopy[j][i].getTile().toString();
					if(j==14) //If it starts in the last column then the word only consists of one letter
					{
						word = new Word(j, i, false, wordString);
						wordsOnBoard.add(word);
					}
					else if (j<14)
					{
						if(!boardCopy[j+1][i].isOccupied())
						{
							word = new Word(j, i, false, wordString);
							wordsOnBoard.add(word);
						}
						else //Else we need to find all of the letters of the word
						{
							int row = j; //Stores row position of first letter of word
							j+=1;	
							//Keep looping while there are occupied squares below the occupied square to find the whole word
							while(j<15 && boardCopy[j][i].isOccupied())
							{
								wordString += boardCopy[j][i].getTile().toString();
								j++;
							}
							
							word = new Word(row, i, false, wordString);
							wordsOnBoard.add(word);
						}
					}
				}
			}	
		}	
	}
}
