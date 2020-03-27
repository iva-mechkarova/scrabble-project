
package ketspoon;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller {
	private Player player1;
    private Player player2;
    private Frame frame1;
    private Frame frame2;
    private Player currentPlayer;
    private Board scrabbleBoard;
    private Pool pool;
    
    private int turnScore=0;
    private int prevScore;
    
    ArrayList<Tile> currentLetters;
    ArrayList<Tile> currentWord;
    String currentWordString="";
    
    String lastPlay;
    ArrayList<Tile> prevLetters;
    
    Tile currentSelectedTile;
    private int gameState;
    private boolean placingWord=false;
    
    private final int START_TURN=0;
    private final int CAN_SELECT_FROM_RACK=1;
    private final int CAN_PLACE_ON_BOARD=2;
    private final int EXCHANGING = 3;
    private final int MUST_END_TURN=4;
    

	@FXML
	GridPane grid;
	@FXML
	Button frameButton0;
	@FXML
	Button frameButton1;
	@FXML
	Button frameButton2;
	@FXML
	Button frameButton3;
	@FXML
	Button frameButton4;
	@FXML
	Button frameButton5;
	@FXML
	Button frameButton6;
	@FXML
	Button playWordButton;
 	@FXML
	Button exchangeButton;
	@FXML
	Button endTurnButton;
	@FXML
	Button helpButton;
	@FXML 
	Button passButton;
	@FXML
	Button quitButton;
	@FXML
	Button challengeButton;
	@FXML
	Label currentPlayerName;
	@FXML
	Label playerOneInfo;
	@FXML
	Label playerTwoInfo;
	@FXML
	Label wordString;
	@FXML
	Label poolSize;

	private EventHandler<ActionEvent> createHandler(Square s ) {
	    return event -> handler(s);
	}
	private void handler (Square s){
		if(!s.isPlayedSquare()&&gameState==CAN_PLACE_ON_BOARD) {
			scrabbleBoard.addTileToSquare(s.getSquareIndex(), currentSelectedTile);
			scrabbleBoard.possiblePlays(s.getSquareIndex(), currentLetters);
			currentSelectedTile.setTileSquareIndex(s.getSquareIndex());
			gameState=CAN_SELECT_FROM_RACK;
			updateButtons();
		}
	}
	
	/*Initialize the game*/
	public void initGame() {	
		scrabbleBoard = new Board();
		pool = new Pool();
		frame1 = new Frame(pool);
		frame2 = new Frame(pool);
		poolSize.setText("Pool:"+pool.poolSize());
		currentLetters = new ArrayList<>(); 
		prevLetters = new ArrayList<Tile>();
		currentWord = new ArrayList<Tile>();
		player1=new Player(frame1, "Player1");
		player2=new Player(frame2, "Player2");
		currentPlayer=player1;
		gameState=START_TURN;
	

		initFrame();
		initSquares();
		updateFrameVisual();
		initButtons();
		updateButtons();
		initPlayerOne();
		initPlayerTwo();
		displayPlayerInfo();
	}

	/*The following initializes the squares of the board*/
	public void initSquares() {
		Square currentBoardSquare;
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				currentBoardSquare = scrabbleBoard.gameBoard.get(scrabbleBoard.coordinateToIndex(i, j));
				currentBoardSquare.getSquareButton().setGraphic(new ImageView(currentBoardSquare.getSquareImage())); //Adds the image of each square
				currentBoardSquare.getSquareButton().setPadding(new Insets(0,0,0,0));
				grid.add(currentBoardSquare.getSquareButton(),j,i);
				currentBoardSquare.getSquareButton().setOnAction(createHandler(currentBoardSquare));
			}
		}
		scrabbleBoard.updatePlayableSquares();
	}

	/*The following initializes the current player's frame*/
	public void initFrame() {
		
		/*Each of the following 7 event listeners are the same but need to be applied to each tile of the frame*/
		frameButton0.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	if(gameState==CAN_SELECT_FROM_RACK || gameState==EXCHANGING || gameState==START_TURN) {
		    		currentSelectedTile=currentPlayer.playerFrame.getLettersInFrame().get(0);
			    	frameButton0.setVisible(false);
			    	currentLetters.add(currentSelectedTile);
			    	if(gameState!=EXCHANGING) {
			    		gameState=CAN_PLACE_ON_BOARD;
			    		placingWord=true;
			    		updateButtons();
			    	}
		    	}
		    }
		});;
		
		frameButton1.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	if(gameState==CAN_SELECT_FROM_RACK || gameState==EXCHANGING || gameState==START_TURN) {
		    		currentSelectedTile=currentPlayer.playerFrame.getLettersInFrame().get(1);
			    	frameButton1.setVisible(false);
			    	currentLetters.add(currentSelectedTile);
			    	if(gameState!=EXCHANGING) {
			    		gameState=CAN_PLACE_ON_BOARD;
			    		placingWord=true;
			    		updateButtons();
			    	}
		    	}
		    }
		});;

		frameButton2.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	if(gameState==CAN_SELECT_FROM_RACK || gameState==EXCHANGING || gameState==START_TURN) {
		    		currentSelectedTile=currentPlayer.playerFrame.getLettersInFrame().get(2);
			    	frameButton2.setVisible(false);
			    	currentLetters.add(currentSelectedTile);
			    	if(gameState!=EXCHANGING) {
			    		gameState=CAN_PLACE_ON_BOARD;
			    		placingWord=true;
			    		updateButtons();
			    	}
		    	}
		    }
		});;
		
		frameButton3.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	if(gameState==CAN_SELECT_FROM_RACK || gameState==EXCHANGING || gameState==START_TURN) {
		    		currentSelectedTile=currentPlayer.playerFrame.getLettersInFrame().get(3);
			    	frameButton3.setVisible(false);
			    	currentLetters.add(currentSelectedTile);
			    	if(gameState!=EXCHANGING) {
			    		gameState=CAN_PLACE_ON_BOARD;
			    		placingWord=true;
			    		updateButtons();
			    	}
		    	}
		    }
		});;
		
		frameButton4.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	if(gameState==CAN_SELECT_FROM_RACK || gameState==EXCHANGING || gameState==START_TURN ) {
		    		currentSelectedTile=currentPlayer.playerFrame.getLettersInFrame().get(4);
			    	frameButton4.setVisible(false);
			    	currentLetters.add(currentSelectedTile);
			    	if(gameState!=EXCHANGING) {
			    		gameState=CAN_PLACE_ON_BOARD;
			    		placingWord=true;
			    		updateButtons();
			    	}
		    	}
		    }
		});;
		
		frameButton5.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	if(gameState==CAN_SELECT_FROM_RACK || gameState==EXCHANGING || gameState==START_TURN) {
		    		currentSelectedTile=currentPlayer.playerFrame.getLettersInFrame().get(5);
			    	frameButton5.setVisible(false);
			    	currentLetters.add(currentSelectedTile);
			    	if(gameState!=EXCHANGING) {
			    		gameState=CAN_PLACE_ON_BOARD;
			    		placingWord=true;
			    		updateButtons();
			    	}
		    	}
		    }
		});;
		
		frameButton6.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	if(gameState==CAN_SELECT_FROM_RACK || gameState==EXCHANGING || gameState==START_TURN) {
		    		currentSelectedTile=currentPlayer.playerFrame.getLettersInFrame().get(6);
			    	frameButton6.setVisible(false);
			    	currentLetters.add(currentSelectedTile);
			    	if(gameState!=EXCHANGING) {
			    		gameState=CAN_PLACE_ON_BOARD;
			    		placingWord=true;
			    		updateButtons();
			    	}
		    	}
		    }
		});;
	}
	
	/*This method initializes the buttons*/
	public void initButtons() {	
		/*This action ensures that the game state changes to exchanging when exchange is clicked*/
		/*It checks to see if game state is already in exchanging state - if it is then remove the tiles and add them back to the pool*/
		exchangeButton.setOnAction(new EventHandler<ActionEvent>() {
			 @Override public void handle(ActionEvent e) {
				 if(gameState==EXCHANGING) {
					 for(int i=0; i<currentLetters.size(); i++) {
						 currentPlayer.playerFrame.removeTile(currentLetters.get(i)); //Removes tiles from player's frame
						 pool.addTileToPool(currentLetters.get(i)); //Adds the tiles back to the pool
					 }
					 currentPlayer.playerFrame.fillFrame(pool);
					 gameState=MUST_END_TURN; //Change game state as player must now end their turn
					 exchangeButton.setText("EXCHANGE");
					 currentLetters.clear();
					 updateFrameVisual(); //Display the frame with the new tiles which have been added
					 updateButtons(); //Update which buttons should be disabled/enabled
				 }
				 else {
					 exchangeButton.setText("CONFIRM"); //If exchanging is clicked for the first time then change game state to EXCHANGING
					 gameState = EXCHANGING;
					 updateButtons();
				 }
			 }
		});;
		
		
		
		playWordButton.setOnAction(new EventHandler<ActionEvent>() {
			 @Override public void handle(ActionEvent e) {
				 getFullWord();
				 placingWord=false;
				 for (int i = 0; i < currentLetters.size(); i++) {
					 currentPlayer.playerFrame.removeTile(currentLetters.get(i));
				 }
				 currentPlayer.playerFrame.fillFrame(pool);
				 calculateScore();
				 updateFrameVisual();
				 wordString.setText(currentWordString);
				 poolSize.setText("Pool:"+pool.poolSize());
				 gameState=MUST_END_TURN;
				 updateButtons();
				 displayPlayerInfo();
			 }
		});;
				
		helpButton.setOnAction(e -> displayHelp());
		passButton.setOnAction(e -> displayPassWindow());
		quitButton.setOnAction(e -> displayQuitWindow());
		challengeButton.setOnAction(e -> displayChallengeWindow());
		
		endTurnButton.setOnAction(new EventHandler<ActionEvent>() {
			 @Override public void handle(ActionEvent e) {
				 placingWord=false;
				 gameState=START_TURN;
				 switchPlayer();
				 updateGameData();
				 updateButtons();
			 }
		});;
	}
	
	public void updateGameData() {
		prevScore=turnScore;
		turnScore=0;
		prevLetters.clear();
		for (int i = 0; i < currentLetters.size(); i++)
			prevLetters.add(currentLetters.get(i));
		currentLetters.clear(); //Removes all elements from the currentLetters list
		currentWord.clear();
	    lastPlay=currentWordString;
		currentWordString="";
		
		updateFrameVisual(); //Calling this method ensures the current player's frame is displayed
		scrabbleBoard.updatePlayableSquares(); //This updates the playable squares on the board
		displayPlayerInfo(); //This updates the visual of the players' current scores
	}
	
	/*This method switches which player's turn it is*/
	public void switchPlayer() { 
		if(currentPlayer==player1)
			currentPlayer=player2;
		else 
			currentPlayer=player1;
	}
	
	/*This method gets the word that was just placed by the user*/
	public void getFullWord(){
		int startIndex=currentSelectedTile.getTileSquareIndex();
		int endIndex=currentSelectedTile.getTileSquareIndex();

		
		if (scrabbleBoard.getDirection()==Board.HORIZONTAL) {
			while(scrabbleBoard.gameBoard.get(startIndex).getSquareIndex()%15!=0 && scrabbleBoard.gameBoard.get(startIndex-1).isPlayedSquare()) {
				startIndex--;
			}
			while((scrabbleBoard.gameBoard.get(endIndex).getSquareIndex()+1)%15!=0 && scrabbleBoard.gameBoard.get(endIndex+1).isPlayedSquare()) {
				endIndex++;
			}
			
			for (int i = startIndex; i <= endIndex; i++) {
				currentWord.add(scrabbleBoard.gameBoard.get(i).getSquaresTile());
				currentWordString+=scrabbleBoard.gameBoard.get(i).getSquaresTile().getTileLetter();
			}
		}
		
		if (scrabbleBoard.getDirection()==Board.VERTICAL) {
			while(scrabbleBoard.gameBoard.get(startIndex).getSquareIndex()-15>=0 && scrabbleBoard.gameBoard.get(startIndex-15).isPlayedSquare()) {
				startIndex-=15;
			}
			while((scrabbleBoard.gameBoard.get(endIndex).getSquareIndex())+15<225 && scrabbleBoard.gameBoard.get(endIndex+15).isPlayedSquare()) {
				endIndex+=15;
			}
			
			for (int i = startIndex; i <= endIndex; i+=15) {
				currentWord.add(scrabbleBoard.gameBoard.get(i).getSquaresTile());
				currentWordString+=scrabbleBoard.gameBoard.get(i).getSquaresTile().getTileLetter();
			}
		}
	}
	
	/*This method updates which buttons should be disabled in each game state*/
	/*It makes it easier for the user to know which buttons can be clicked at that time*/
	public void updateButtons() 
	{
		/*If player is in the middle of placing a word, gameState changes from
		CAN_PLACE_ON_BOARD to CAN_SELECT_FROM_RACK so we need to deal with this separately*/
		if(placingWord)
		{
			/*If you are placing a word, you can't exchange, pass or end turn*/
			exchangeButton.setDisable(true);
			passButton.setDisable(true);
			endTurnButton.setDisable(true);
			challengeButton.setDisable(true);
			//If you have selected a tile to place then you must place it before playing word
			if(gameState==CAN_PLACE_ON_BOARD)
			{
				playWordButton.setDisable(true);
			}
			else
			{
				playWordButton.setDisable(false); 
			}
		}
		else
		{
			switch(gameState)
			{
			//At start of turn you can't end turn or play word
			case START_TURN:
				endTurnButton.setDisable(true);
				playWordButton.setDisable(true);
				passButton.setDisable(false);
				exchangeButton.setDisable(false);
				challengeButton.setDisable(prevLetters.isEmpty());
				break;
			//At the end of turn your only move is to end turn
			case MUST_END_TURN:
				exchangeButton.setDisable(true);
				passButton.setDisable(true);
				playWordButton.setDisable(true);
				endTurnButton.setDisable(false);
				break;
			//If choosing tiles to exchange you cannot pass, end turn or play word
			case EXCHANGING:
				passButton.setDisable(true);
				endTurnButton.setDisable(true);
				playWordButton.setDisable(true);
				challengeButton.setDisable(true);
				break;
			default:
				exchangeButton.setDisable(false);
				passButton.setDisable(false);
				endTurnButton.setDisable(false);
				playWordButton.setDisable(false);
				break;
			}
		}
	}
	
	public void calculateScore(){
		boolean tripleWord=false;
		boolean doubleWord=false;
		
		for (int i = 0; i < currentWord.size(); i++) {
			turnScore+=currentWord.get(i).getTileValue();
		}
		for (int i = 0; i < currentLetters.size(); i++) {
			tripleWord=scrabbleBoard.gameBoard.get(currentLetters.get(i).getTileSquareIndex()).getSquareType()==Board.TRIPLEWORD;
			doubleWord=scrabbleBoard.gameBoard.get(currentLetters.get(i).getTileSquareIndex()).getSquareType()==Board.DOUBLEWORD;
			
			if(scrabbleBoard.gameBoard.get(currentLetters.get(i).getTileSquareIndex()).getSquareType()==Board.TRIPLELETTER) 
				turnScore+=(currentLetters.get(i).getTileValue()*2);
			
			if(scrabbleBoard.gameBoard.get(currentLetters.get(i).getTileSquareIndex()).getSquareType()==Board.DOUBLELETTER) 
				turnScore+=currentLetters.get(i).getTileValue();
		}
		if(tripleWord) 
			turnScore=turnScore*3;
		if(doubleWord)
			turnScore=turnScore*2;
		
		currentPlayer.updateScore(turnScore);
	}

	/*Method to update the frame that is displayed*/
	public void updateFrameVisual() {
		/*The following displays the current user's frame*/
		if(currentPlayer.playerFrame.getLettersInFrame().size()>0) {
			frameButton0.setGraphic(new ImageView(currentPlayer.playerFrame.getLettersInFrame().get(0).getTileImage()));
			frameButton0.setVisible(true);
		}
		else
			frameButton0.setVisible(false);
		
		if(currentPlayer.playerFrame.getLettersInFrame().size()>1) {
			frameButton1.setGraphic(new ImageView(currentPlayer.playerFrame.getLettersInFrame().get(1).getTileImage()));
			frameButton1.setVisible(true);
		}
		else
			frameButton1.setVisible(false);
		
		if(currentPlayer.playerFrame.getLettersInFrame().size()>2) {
			frameButton2.setGraphic(new ImageView(currentPlayer.playerFrame.getLettersInFrame().get(2).getTileImage()));
			frameButton2.setVisible(true);
		}
		else
			frameButton2.setVisible(false);
		
		if(currentPlayer.playerFrame.getLettersInFrame().size()>3) {
			frameButton3.setVisible(true);
			frameButton3.setGraphic(new ImageView(currentPlayer.playerFrame.getLettersInFrame().get(3).getTileImage()));
		}
		else
			frameButton3.setVisible(false);
		
		if(currentPlayer.playerFrame.getLettersInFrame().size()>4) {
			frameButton4.setGraphic(new ImageView(currentPlayer.playerFrame.getLettersInFrame().get(4).getTileImage()));
			frameButton4.setVisible(true);
		}
		else
			frameButton4.setVisible(false);
		
		if(currentPlayer.playerFrame.getLettersInFrame().size()>5) {
			frameButton5.setGraphic(new ImageView(currentPlayer.playerFrame.getLettersInFrame().get(5).getTileImage()));
			frameButton5.setVisible(true);
		}
		else
			frameButton5.setVisible(false);
		
		if(currentPlayer.playerFrame.getLettersInFrame().size()>6) {
			frameButton6.setGraphic(new ImageView(currentPlayer.playerFrame.getLettersInFrame().get(6).getTileImage()));
			frameButton6.setVisible(true);
		}
		else
			frameButton6.setVisible(false);
	}

	/*Method to display window when help is clicked*/
	public void displayHelp()
	{
		Stage helpWindow = new Stage();	      
		helpWindow.setTitle("Help");  
		
		Button closeHelpButton = new Button("CLOSE");
		closeHelpButton.setMaxSize(200, 20);
		closeHelpButton.setMinSize(200, 40);
		
		/*Close window when button is clicked*/
		closeHelpButton.setOnAction(e -> helpWindow.close());
		
		/*The following is all of the text for help*/
		Text helpTextTitle = new Text("Placing a word:");
		helpTextTitle.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;");
		
		Text helpTextBody = new Text("1. Select a tile from your rack.\n"
				+ "2. Click on any of the playable squares on the board to place it there i.e. first tile must be placed in the middle "
				+ "of the board and all other words must be connected to already existing tiles on the board.\n"
				+ "3. Select the next letter of the word you are placing from your rack.\n"
				+ "4. Select one of the playable squares on the board to place it there and keep doing this with the rest "
				+ "of the letters of the word you wish to place.\n");
		helpTextBody.setStyle("-fx-font-size: 10pt;");
		
		Text helpTextPass = new Text("Passing your turn:");
		helpTextPass.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;");
		
		Text helpTextPassBody = new Text("Rather than exchange tiles, you can also pass your turn and take a zero score. "
				+ "This is your only option if there are six or fewer tiles remaining in the pool. "
				+ "If all of the players pass twice in succession, the game ends.");
		helpTextPassBody.setStyle("-fx-font-size: 10pt;");
		
		Text helpTextExchange = new Text("Exchanging tiles:");
		helpTextExchange.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;");
		
		Text helpTextExchangeBody = new Text("You may swap one to seven tiles instead of playing a word on your turn. "
				+ "You can only do this if at least seven tiles are still in the pool. "
				+ "To do this first click on the exchange button and then click on the tiles that you wish to exchange one by one."
				+ " When you are finished click on confirm to see your frame updated with the new tiles.");
		helpTextExchangeBody.setStyle("-fx-font-size: 10pt;");
		
		Text helpEndTurn = new Text("Ending turn:");
		helpEndTurn.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;");
		
		Text helpEndTurnBody = new Text("Once you are finished placing a word OR exchanging tiles, click"
				+ " on the end turn button.");
		helpEndTurnBody.setStyle("-fx-font-size: 10pt;");
		
		/*Add scrollbar just in case the text doesn't fit or if user makes window smaller*/
		VBox layout = new VBox(10);
		ScrollPane scrollPane = new ScrollPane(layout);
		scrollPane.setFitToHeight(true);
		scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);

		      
		layout.getChildren().addAll(helpTextTitle, helpTextBody, helpTextPass, helpTextPassBody, helpTextExchange, 
				helpTextExchangeBody, helpEndTurn, helpEndTurnBody, closeHelpButton);
		      
		layout.setAlignment(Pos.CENTER);
		      
		Scene scene = new Scene(scrollPane, 600, 450);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		      
		helpWindow.setScene(scene);
		/*Wrap text so that resizing the window moves the text as well so that you can always see it*/
		helpTextBody.wrappingWidthProperty().bind(scene.widthProperty().subtract(15));
		helpTextPassBody.wrappingWidthProperty().bind(scene.widthProperty().subtract(15));
		helpTextExchangeBody.wrappingWidthProperty().bind(scene.widthProperty().subtract(15));
		helpEndTurnBody.wrappingWidthProperty().bind(scene.widthProperty().subtract(15));
		
		helpWindow.showAndWait();
	       
	}
	
	/*Method to display window when pass is clicked to ask user if they are sure*/
	public void displayPassWindow()
	{
		Stage passWindow = new Stage();	      
		passWindow.setTitle("Passing turn");  
		
		Button yesButton = new Button("YES");
		yesButton.setMaxSize(80, 40);
		yesButton.setMinSize(80, 40);
		
		/*If user clicks yes switch player and close window*/
		yesButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			 @Override public void handle(ActionEvent e) 
			 {
				 passWindow.close();
				 currentLetters.clear();
				 switchPlayer();
				 updateGameData();
				 updateButtons();
			 }
		});;
		
		Button noButton = new Button("NO");
		noButton.setMaxSize(80, 40);
		noButton.setMinSize(80, 40);
		
		noButton.setOnAction(e -> passWindow.close()); //If user selects no then just close the window
		
		HBox buttonsHbox = new HBox(10);
		buttonsHbox.getChildren().addAll(yesButton, noButton);
		
		Text passText = new Text("Are you sure you want to pass your turn?");
		passText.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;");
		
		VBox layout = new VBox(10);	      
		layout.getChildren().addAll(passText, buttonsHbox);		      
		layout.setAlignment(Pos.CENTER);
		      
		Scene scene = new Scene(layout, 220, 200);  
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); //Import css style sheet
		
		passWindow.setScene(scene);
		passText.wrappingWidthProperty().bind(scene.widthProperty().subtract(15));
		passWindow.showAndWait();	
	}
	
	/*Method to initialize player one's name*/
	public void initPlayerOne()
	{
		Stage playerNameWindow = new Stage();	      
		playerNameWindow.setTitle("Player 1");  

		/*Prompt user to enter name*/
		Label playerNameLabel = new Label("Enter player 1's name:");
		playerNameLabel.setStyle("-fx-font-size: 10pt;"
				+ "-fx-font-weight: bold;");
		TextField playerName = new TextField();

		/*Submit name using this button*/
		Button doneButton = new Button("DONE");
		doneButton.setMaxSize(150, 20);
		doneButton.setMinSize(200, 40);

		/*When done button is clicked, parse input from user and set it as player 2's name*/
		doneButton.setOnAction(e -> {
			player1.setName(playerName.getText());
			playerNameWindow.close();
		});

		/*Add scroll box just in case user makes window smaller*/
		VBox layout = new VBox(10);
		ScrollPane scrollPane = new ScrollPane(layout);
		scrollPane.setFitToHeight(true);
		scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);


		layout.getChildren().addAll(playerNameLabel, playerName, doneButton);

		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(scrollPane, 240, 110);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); //Import css style sheet

		playerNameWindow.setScene(scene);

		playerNameWindow.showAndWait();

	}

	/*Method to initialize player two's name*/
	public void initPlayerTwo()
	{
		Stage playerNameWindow = new Stage(); 	      
		playerNameWindow.setTitle("Player 2");  

		/*Prompt user to enter name*/
		Label playerNameLabel = new Label("Enter player 2's name:");
		playerNameLabel.setStyle("-fx-font-size: 10pt;"
				+ "-fx-font-weight: bold;");
		TextField playerName = new TextField();

		/*Submit name using this button*/
		Button doneButton = new Button("DONE");
		doneButton.setMaxSize(150, 20);
		doneButton.setMinSize(200, 40);

		/*When done button is clicked, parse input from user and set it as player 2's name*/
		doneButton.setOnAction(e -> {
			player2.setName(playerName.getText());
			playerNameWindow.close();
		});

		/*Add scroll box just in case user makes window smaller*/
		VBox layout = new VBox(10);
		ScrollPane scrollPane = new ScrollPane(layout);
		scrollPane.setFitToHeight(true);
		scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);


		layout.getChildren().addAll(playerNameLabel, playerName, doneButton);

		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(scrollPane, 240, 110);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); //Import css style sheet

		playerNameWindow.setScene(scene);
		playerNameWindow.showAndWait(); 
	}
	
	/*Method to display window to ask user if they are sure they wish to challenge*/
	public void displayChallengeWindow()
	{
		Stage challengeWindow = new Stage();	      
		challengeWindow.setTitle("Challenge");  
		
		Button yesButton = new Button("YES");
		yesButton.setMaxSize(80, 40);
		yesButton.setMinSize(80, 40);
		
		/*If user clicks yes then challenge word and close window*/
		yesButton.setOnAction(e -> {challengeWindow.close();switchPlayer();updateGameData();updateButtons();});
		
		Button noButton = new Button("NO");
		noButton.setMaxSize(80, 40);
		noButton.setMinSize(80, 40);
		
		noButton.setOnAction(e -> {challengeWindow.close();challageValid();updateButtons();}); //If user selects no then just close the window
		
		HBox buttonsHbox = new HBox(10);
		buttonsHbox.getChildren().addAll(yesButton, noButton);
		
		Text challengeText = new Text("Is "+lastPlay+" a real word?");
		challengeText.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;");
		
		VBox layout = new VBox(10);	      
		layout.getChildren().addAll(challengeText, buttonsHbox);		      
		layout.setAlignment(Pos.CENTER);
		      
		Scene scene = new Scene(layout, 220, 200);  
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); //Import css style sheet
		
		challengeWindow.setScene(scene);
		challengeText.wrappingWidthProperty().bind(scene.widthProperty().subtract(15));
		challengeWindow.showAndWait();		
	}
	
	public void challageValid() {
		
		switchPlayer();
		currentPlayer.updateScore(-prevScore);
		for (int i = 6; i > 7-prevLetters.size()-1; i--) {		
			pool.addTileToPool(currentPlayer.playerFrame.getLettersInFrame().get(i));
			currentPlayer.playerFrame.getLettersInFrame().remove(i);
		}
		Square resetSquare=null;
		
		for(Tile t :prevLetters) {
			currentPlayer.playerFrame.getLettersInFrame().add(t);
			resetSquare=scrabbleBoard.gameBoard.get(t.getTileSquareIndex());
			resetSquare.setSquaresTile(null);
			resetSquare.setPlayedSquare(false);
			resetSquare.getSquareButton().setGraphic(new ImageView(resetSquare.getSquareImage()));
		}
		for(Square b: scrabbleBoard.gameBoard) {
			if(b.getSquareIndex()!=112)
				b.setPlayableSquare(false);
		}
		poolSize.setText("Pool:"+pool.poolSize());
		
		switchPlayer();updateGameData();updateButtons();
	}
	
	
	
	/*Method to display window to ask user if they are sure they wish to quit the game*/
	public void displayQuitWindow()
	{
		Stage quitWindow = new Stage();	      
		quitWindow.setTitle("Quit");  
		
		Button yesButton = new Button("YES");
		yesButton.setMaxSize(80, 40);
		yesButton.setMinSize(80, 40);
		
		/*If user clicks yes then close window and terminate the program*/
		yesButton.setOnAction(e -> {quitWindow.close();Platform.exit();});
		
		Button noButton = new Button("NO");
		noButton.setMaxSize(80, 40);
		noButton.setMinSize(80, 40);
		
		noButton.setOnAction(e -> quitWindow.close()); //If user selects no then just close the window
		
		HBox buttonsHbox = new HBox(10);
		buttonsHbox.getChildren().addAll(yesButton, noButton);
		
		Text challengeText = new Text("Are you sure you want to quit the game?");
		challengeText.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;");
		
		VBox layout = new VBox(10);	      
		layout.getChildren().addAll(challengeText, buttonsHbox);		      
		layout.setAlignment(Pos.CENTER);
		      
		Scene scene = new Scene(layout, 260, 200);  
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); //Import css style sheet
		
		quitWindow.setScene(scene);
		challengeText.wrappingWidthProperty().bind(scene.widthProperty().subtract(15));
		quitWindow.showAndWait();		
	}

	/*Method to display player name and score and prompt the user who's turn it is*/
	public void displayPlayerInfo()
	{
		currentPlayerName.setText(currentPlayer.getName());
		playerOneInfo.setText(player1.getName() + ": " + player1.getScore());
		playerTwoInfo.setText(player2.getName() + ": " + player2.getScore());
	}
}
