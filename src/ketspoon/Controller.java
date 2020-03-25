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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
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
    ArrayList<Tile> currentLetters;
    ArrayList<Tile> currentWord;
    String currentWordString="";
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
	



	private EventHandler<ActionEvent> createHandler(Square s ) {
	    return event -> handler(s);
	}
	private void handler (Square s){
		if(!s.isPlayedSquare()&&gameState==CAN_PLACE_ON_BOARD) {
			scrabbleBoard.addTileToSquare(s.getSquareIndex(), currentSelectedTile);
			scrabbleBoard.possiblePlays(s.getSquareIndex(), currentPlayer.playerFrame, currentLetters);
			currentSelectedTile.setTileSquareIndex(s.getSquareIndex());
			gameState=CAN_SELECT_FROM_RACK;
		}
	}
	
	public void initGame() {	
		scrabbleBoard = new Board();
		pool = new Pool();
		frame1 = new Frame(pool);
		frame2 = new Frame(pool);
		currentLetters = new ArrayList<>(); 
		currentWord = new ArrayList<Tile>();
		player1=new Player(frame1, "test");
		player2=new Player(frame2, "test1");
		currentPlayer=player1;
		gameState=START_TURN;

		initFrame();
		initSquares();
		updateFrameVisual();
		initButtons();
	}

	public void initSquares() {
		Square currentBoardSquare;
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				currentBoardSquare = scrabbleBoard.gameBoard.get(scrabbleBoard.coordinateToIndex(i, j));
				currentBoardSquare.getSquareButton().setGraphic(new ImageView(currentBoardSquare.getSquareImage()));
				currentBoardSquare.getSquareButton().setPadding(new Insets(0,0,0,0));
				grid.add(currentBoardSquare.getSquareButton(),j,i);
				currentBoardSquare.getSquareButton().setOnAction(createHandler(currentBoardSquare));
			}
		}
		scrabbleBoard.updatePlayableSquares();
	}

	public void initFrame() {
		frameButton0.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	if(gameState==CAN_SELECT_FROM_RACK || gameState==EXCHANGING || gameState==START_TURN) {
		    		currentSelectedTile=currentPlayer.playerFrame.getLettersInFrame().get(0);
			    	frameButton0.setVisible(false);
			    	currentLetters.add(currentSelectedTile);
			    	if(gameState==EXCHANGING)
			    		pool.addTileToPool(currentSelectedTile);
			    	else
			    	{
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
			    	if(gameState==EXCHANGING)
			    		pool.addTileToPool(currentSelectedTile);
			    	else
			    	{
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
			    	if(gameState==EXCHANGING)
			    		pool.addTileToPool(currentSelectedTile);
			    	else
			    	{
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
			    	if(gameState==EXCHANGING)
			    		pool.addTileToPool(currentSelectedTile);
			    	else
			    	{
			    		gameState=CAN_PLACE_ON_BOARD;
			    		placingWord=true;
			    		updateButtons();
			    	}
		    	}
		    }
		});;
		
		frameButton4.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	if(gameState==CAN_SELECT_FROM_RACK || gameState==EXCHANGING) {
		    		currentSelectedTile=currentPlayer.playerFrame.getLettersInFrame().get(4);
			    	frameButton4.setVisible(false);
			    	currentLetters.add(currentSelectedTile);
			    	if(gameState==EXCHANGING)
			    		pool.addTileToPool(currentSelectedTile);
			    	else
			    	{
			    		gameState=CAN_PLACE_ON_BOARD;
			    		placingWord=true;
			    		updateButtons();
			    	}
		    	}
		    }
		});;
		
		frameButton5.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	if(gameState==CAN_SELECT_FROM_RACK || gameState==EXCHANGING) {
		    		currentSelectedTile=currentPlayer.playerFrame.getLettersInFrame().get(5);
			    	frameButton5.setVisible(false);
			    	currentLetters.add(currentSelectedTile);
			    	if(gameState==EXCHANGING)
			    		pool.addTileToPool(currentSelectedTile);
			    	else
			    	{
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
			    	if(gameState==EXCHANGING)
			    		pool.addTileToPool(currentSelectedTile);
			    	else
			    	{
			    		gameState=CAN_PLACE_ON_BOARD;
			    		placingWord=true;
			    		updateButtons();
			    	}
		    	}
		    }
		});;
	}
	
	
	public void initButtons() {	
		exchangeButton.setOnAction(new EventHandler<ActionEvent>() {
			 @Override public void handle(ActionEvent e) {
				 if(gameState==EXCHANGING) {
					 for(int i=0; i<currentLetters.size(); i++) {
						 currentPlayer.playerFrame.removeTile(currentLetters.get(i));
					 }
					 currentLetters.clear();
					 gameState=MUST_END_TURN;
					 currentPlayer.playerFrame.fillFrame(pool);
					 exchangeButton.setText("EXCHANGE");
					 updateFrameVisual();
					 updateButtons();
				 }
				 else {
					 exchangeButton.setText("CONFIRM");
					 gameState = EXCHANGING;
					 updateButtons();
				 }
			 }
		});;
		
		playWordButton.setOnAction(new EventHandler<ActionEvent>() {
			 @Override public void handle(ActionEvent e) {
				 getFullWord();
				 placingWord=false;
				 gameState=MUST_END_TURN;
				 updateButtons();
			 }
		});;
				
		helpButton.setOnAction(e -> displayHelp());
		passButton.setOnAction(e -> displayPassWindow());
		quitButton.setOnAction(e -> Platform.exit());
		
		endTurnButton.setOnAction(new EventHandler<ActionEvent>() {
			 @Override public void handle(ActionEvent e) {
				 placingWord=false;
				 gameState=START_TURN;
				 switchPlayer();
				 updateButtons();
			 }
		});;
	}
	
	public void switchPlayer() {
		if(currentPlayer==player1)
			currentPlayer=player2;
		else 
			currentPlayer=player1;
		updateFrameVisual();
	}
	
	public void getFullWord(){
		int startIndex=currentSelectedTile.getTileSquareIndex();
		int endIndex=currentSelectedTile.getTileSquareIndex();
		
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
		System.out.println(currentWordString);
	}
	
	public void updateButtons() 
	{
		if(placingWord)
		{
			exchangeButton.setDisable(true);
			passButton.setDisable(true);
		}
		else if(gameState==MUST_END_TURN)
		{
			exchangeButton.setDisable(true);
			passButton.setDisable(true);
			playWordButton.setDisable(true);
			endTurnButton.setDisable(false);
		}
		else if(gameState==EXCHANGING)
		{
			passButton.setDisable(true);
			endTurnButton.setDisable(true);
			playWordButton.setDisable(true);
		}
		else
		{
			exchangeButton.setDisable(false);
			passButton.setDisable(false);
			endTurnButton.setDisable(false);
			playWordButton.setDisable(false);
		}
	}

	public void updateFrameVisual() {
		frameButton0.setGraphic(new ImageView(currentPlayer.playerFrame.getLettersInFrame().get(0).getTileImage()));
		frameButton1.setGraphic(new ImageView(currentPlayer.playerFrame.getLettersInFrame().get(1).getTileImage()));
		frameButton2.setGraphic(new ImageView(currentPlayer.playerFrame.getLettersInFrame().get(2).getTileImage()));
		frameButton3.setGraphic(new ImageView(currentPlayer.playerFrame.getLettersInFrame().get(3).getTileImage()));
		frameButton4.setGraphic(new ImageView(currentPlayer.playerFrame.getLettersInFrame().get(4).getTileImage()));
		frameButton5.setGraphic(new ImageView(currentPlayer.playerFrame.getLettersInFrame().get(5).getTileImage()));
		frameButton6.setGraphic(new ImageView(currentPlayer.playerFrame.getLettersInFrame().get(6).getTileImage()));

		frameButton0.setVisible(true);
		frameButton1.setVisible(true);
		frameButton2.setVisible(true);
		frameButton3.setVisible(true);
		frameButton4.setVisible(true);
		frameButton5.setVisible(true);
		frameButton6.setVisible(true);
	}
	
	public void displayHelp()
	{
		Stage helpWindow = new Stage();	      
		helpWindow.setTitle("Help");  
		
		Button closeHelpButton = new Button("CLOSE");
		closeHelpButton.setMaxSize(200, 20);
		closeHelpButton.setMinSize(200, 40);
		
		closeHelpButton.setOnAction(e -> helpWindow.close());
		
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
		
		helpTextBody.wrappingWidthProperty().bind(scene.widthProperty().subtract(15));
		helpTextPassBody.wrappingWidthProperty().bind(scene.widthProperty().subtract(15));
		helpTextExchangeBody.wrappingWidthProperty().bind(scene.widthProperty().subtract(15));
		helpEndTurnBody.wrappingWidthProperty().bind(scene.widthProperty().subtract(15));
		
		helpWindow.showAndWait();
	       
	}
	
	public void displayPassWindow()
	{
		Stage passWindow = new Stage();	      
		passWindow.setTitle("Passing turn");  
		
		Button yesButton = new Button("YES");
		yesButton.setMaxSize(80, 40);
		yesButton.setMinSize(80, 40);
		
		yesButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			 @Override public void handle(ActionEvent e) 
			 {
				 passWindow.close();
				 switchPlayer();
			 }
		});;
		
		Button noButton = new Button("NO");
		noButton.setMaxSize(80, 40);
		noButton.setMinSize(80, 40);
		
		noButton.setOnAction(e -> passWindow.close());
		
		HBox buttonsHbox = new HBox(10);
		buttonsHbox.getChildren().addAll(yesButton, noButton);
		
		Text passText = new Text("Are you sure you want to pass your turn?");
		passText.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;");
		
		VBox layout = new VBox(10);	      
		layout.getChildren().addAll(passText, buttonsHbox);		      
		layout.setAlignment(Pos.CENTER);
		      
		Scene scene = new Scene(layout, 220, 200);  
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		passWindow.setScene(scene);
		passText.wrappingWidthProperty().bind(scene.widthProperty().subtract(15));
		passWindow.showAndWait();
		
	}
}
