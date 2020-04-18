import java.util.ArrayList;


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
    
    private int[] TILE_VALUE = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
    
    public static ArrayList<Word> allPossibleCombo= new ArrayList<Word>();
    public static ArrayList<Word> realWords= new ArrayList<Word>();
    
    private Square[][] boardCopy;
    private ArrayList<Word> verticalWords = new ArrayList<Word>();
    private ArrayList<Word> horizontalWords = new ArrayList<Word>();

    Bot0(PlayerAPI me, OpponentAPI opponent, BoardAPI board, UserInterfaceAPI ui, DictionaryAPI dictionary) {
        this.me = me;
        this.opponent = opponent;
        this.board = board;
        this.info = ui;
        this.dictionary = dictionary;
        turnCount = 0;
    }

    public String getCommand() {
    	setMyLetter(me.getFrameAsString().replaceAll(", ", "").replaceAll("\\]", "").replaceAll("\\[", "").replace("_", ""));
        String command = "";
        
        if(turnCount==0) {
        	command = "NAME Ketspoon";
        }
        else if(board.isFirstPlay()&&turnCount>0) {
        	distinctPermutn(myLetter, "",8,8);
        	smallerSizeCombinations();
        	realWordFilter();
        	command = "H8 A "+realWords.get(0).getLetters();
        }
        else {
        	command="PASS";
        }

        if(turnCount!=0) {
        	realWords.clear();
            allPossibleCombo.clear();
        }
        
        /*getHorizontalWords();
        getVerticalWords();
        for(Word word : horizontalWords)
        {
        	System.out.println("Horizontal: " + word);
        }
        
        for(Word word : verticalWords)
        {
        	System.out.println("Vertical: " + word);
        }*/
        
        turnCount++;
        return command;
    }
    
	public String getMyLetter() {
		return myLetter;
	}

	public void setMyLetter(String myLetter) {
		this.myLetter = myLetter;
	}
	
	public void realWordScores() {
		//int letterValue = TILE_VALUE[-realWords.get(0).getLetter(0)-'A'];
		//System.out.println(letterValue);
	}
	
	public void realWordFilter() {
		for (int i = 0; i < allPossibleCombo.size(); i++) {
			realWords.add(allPossibleCombo.get(i));
			if(!dictionary.areWords(realWords))
				realWords.remove(realWords.size()-1);
		}
	}
	
	public void smallerSizeCombinations() {
		int x = allPossibleCombo.size();
		for (int i = allPossibleCombo.get(allPossibleCombo.size()-1).getLetters().length(); i > 2; i--) {
			int size = allPossibleCombo.size();
			for (int j = size-x; j < size; j++) {
				allPossibleCombo.add(new Word(0, 0, true, removeLastChar(allPossibleCombo.get(j).getLetters())));
			}
		}
	}
	
	private String removeLastChar(String str) {
	    return str.substring(0, str.length() - 1);
	}
	
	public void distinctPermutn(String str, String ans, int x,int y) {
		if (str.length() == 0) { 
			allPossibleCombo.add(new Word(x, y, true, ans));
			return; 
		} 
		boolean alpha[] = new boolean[26]; 
		for (int i = 0; i < str.length(); i++) { 
			char ch = str.charAt(i); 
			String ros = str.substring(0, i) + 
				str.substring(i + 1); 
			if (alpha[ch - 'A'] == false) 
			distinctPermutn(ros, ans + ch,x,y); 
			alpha[ch - 'A'] = true; 
		} 
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
						horizontalWords.add(word);
					}
					else if (j<14)
					{
						if(!boardCopy[i][j+1].isOccupied()) //If the square beside it isn't occupied then we have found the whole word
						{
							word = new Word(i, j, true, wordString);
							horizontalWords.add(word);
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
							horizontalWords.add(word);
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
						verticalWords.add(word);
					}
					else if (j<14)
					{
						if(!boardCopy[j+1][i].isOccupied())
						{
							word = new Word(j, i, false, wordString);
							verticalWords.add(word);
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
							verticalWords.add(word);
						}
					}
				}
			}	
		}	
	}
}
