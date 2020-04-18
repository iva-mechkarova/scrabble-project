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

    Bot0(PlayerAPI me, OpponentAPI opponent, BoardAPI board, UserInterfaceAPI ui, DictionaryAPI dictionary) {
        this.me = me;
        this.opponent = opponent;
        this.board = board;
        this.info = ui;
        this.dictionary = dictionary;
        turnCount = 0;
    }

    public String getCommand() {
    	setMyLetter(me.getFrameAsString().replaceAll(", ", "").replaceAll("\\]", "").replaceAll("\\[", "").replace("\\_", ""));
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
}
