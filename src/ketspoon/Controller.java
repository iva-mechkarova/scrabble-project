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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {
	private Player player1;
    private Player player2;
    private Frame frame1;
    private Frame frame2;
    private Player currentPlayer;
    private Board scrabbleBoard;
    private Pool pool;
    ArrayList<Tile> currentWord;
    private int gameState; 
    
    private final int CAN_SELECT_FROM_RACK=0;
    private final int CAN_PLACE_ON_BOARD=1;
    private final int EXCHANGING = 2;
    
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
	
	
	Tile currentSelectedTile;
	
	private EventHandler<ActionEvent> createHandler(Square s ) {
	    return event -> handler(s);
	}
	private void handler (Square s){
		if(!s.isPlayedSquare()&&gameState==CAN_PLACE_ON_BOARD) {
			scrabbleBoard.addTileToSquare(s.getSquareIndex(), currentSelectedTile);
			scrabbleBoard.possiblePlays(s.getSquareIndex(), currentPlayer.playerFrame, currentWord);
			gameState=CAN_SELECT_FROM_RACK;
		}
	}
	
	public void initGame() {	
		scrabbleBoard = new Board();
		pool = new Pool();
		frame1 = new Frame(pool);
		frame2 = new Frame(pool);
		currentWord = new ArrayList<>(); 
		player1=new Player(frame1, "test");
		player1=new Player(frame2, "test1");
		currentPlayer=player1;
		
		initFrame();
		initSquares();
		initButtons();
		
		updateFrameVisual();
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
		    	if(gameState==CAN_SELECT_FROM_RACK || gameState==EXCHANGING) {
		    		currentSelectedTile=currentPlayer.playerFrame.getLettersInFrame().get(0);
			    	frameButton0.setVisible(false);
			    	currentWord.add(currentSelectedTile);
			    	
			    	if(gameState==EXCHANGING) {
			    		pool.addTileToPool(currentSelectedTile);
			    		
						exchangeButton.setOnAction(new EventHandler<ActionEvent>() 
						{
							 @Override public void handle(ActionEvent e) 
							 { 
								 for(int i=0; i<currentWord.size(); i++)
								 {
									 currentPlayer.playerFrame.removeTile(currentWord.get(i));
								 }
								 
								 currentPlayer.playerFrame.fillFrame(pool);
								 gameState = CAN_SELECT_FROM_RACK;
								 updateFrameVisual();
							 }
						});;
			    	}
			    	else {
			    		gameState=CAN_PLACE_ON_BOARD;
			    	}		    	
		    	}
		    }
		});;
		
		frameButton1.setOnAction(new EventHandler<ActionEvent>() {
			 @Override public void handle(ActionEvent e) {
			    	if(gameState==CAN_SELECT_FROM_RACK || gameState==EXCHANGING) {
			    		currentSelectedTile=currentPlayer.playerFrame.getLettersInFrame().get(1);
				    	frameButton1.setVisible(false);
				    	currentWord.add(currentSelectedTile);
				    	
				    	if(gameState==EXCHANGING) {
				    		pool.addTileToPool(currentSelectedTile);
				    		
							exchangeButton.setOnAction(new EventHandler<ActionEvent>() 
							{
								 @Override public void handle(ActionEvent e) 
								 { 
									 for(int i=0; i<currentWord.size(); i++)
									 {
										 currentPlayer.playerFrame.removeTile(currentWord.get(i));
									 }
									 
									 currentPlayer.playerFrame.fillFrame(pool);
									 gameState = CAN_SELECT_FROM_RACK;
									 updateFrameVisual();
								 }
							});;
				    	}
				    	else {
				    		gameState=CAN_PLACE_ON_BOARD;
				    	}		
			    	}
			    }
			});;
		
		frameButton2.setOnAction(new EventHandler<ActionEvent>() {
			 @Override public void handle(ActionEvent e) {
			    	if(gameState==CAN_SELECT_FROM_RACK || gameState==EXCHANGING) {
			    		currentSelectedTile=currentPlayer.playerFrame.getLettersInFrame().get(2);
				    	frameButton2.setVisible(false);
				    	currentWord.add(currentSelectedTile);
				    	
				    	if(gameState==EXCHANGING) {
				    		pool.addTileToPool(currentSelectedTile);
				    		
							exchangeButton.setOnAction(new EventHandler<ActionEvent>() 
							{
								 @Override public void handle(ActionEvent e) 
								 { 
									 for(int i=0; i<currentWord.size(); i++)
									 {
										 currentPlayer.playerFrame.removeTile(currentWord.get(i));
									 }
									 
									 currentPlayer.playerFrame.fillFrame(pool);
									 gameState = CAN_SELECT_FROM_RACK;
									 updateFrameVisual();
								 }
							});;
				    	}
				    	else {
				    		gameState=CAN_PLACE_ON_BOARD;
				    	}	
			    	}
			    }
			});;
		
		frameButton3.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	if(gameState==CAN_SELECT_FROM_RACK || gameState==EXCHANGING) {
		    		currentSelectedTile=currentPlayer.playerFrame.getLettersInFrame().get(3);
			    	frameButton3.setVisible(false);
			    	currentWord.add(currentSelectedTile);
			    	
			    	if(gameState==EXCHANGING) {
			    		pool.addTileToPool(currentSelectedTile);
			    		
						exchangeButton.setOnAction(new EventHandler<ActionEvent>() 
						{
							 @Override public void handle(ActionEvent e) 
							 { 
								 for(int i=0; i<currentWord.size(); i++)
								 {
									 currentPlayer.playerFrame.removeTile(currentWord.get(i));
								 }
								 
								 currentPlayer.playerFrame.fillFrame(pool);
								 gameState = CAN_SELECT_FROM_RACK;
								 updateFrameVisual();
							 }
						});;
			    	}
			    	else {
			    		gameState=CAN_PLACE_ON_BOARD;
			    	}	
		    	}
		    }
		});;
		
		frameButton4.setOnAction(new EventHandler<ActionEvent>() {
			 @Override public void handle(ActionEvent e) {
			    	if(gameState==CAN_SELECT_FROM_RACK || gameState==EXCHANGING) {
			    		currentSelectedTile=currentPlayer.playerFrame.getLettersInFrame().get(4);
				    	frameButton4.setVisible(false);
				    	currentWord.add(currentSelectedTile);
				    	
				    	if(gameState==EXCHANGING) {
				    		pool.addTileToPool(currentSelectedTile);
				    		
							exchangeButton.setOnAction(new EventHandler<ActionEvent>() 
							{
								 @Override public void handle(ActionEvent e) 
								 { 
									 for(int i=0; i<currentWord.size(); i++)
									 {
										 currentPlayer.playerFrame.removeTile(currentWord.get(i));
									 }
									 
									 currentPlayer.playerFrame.fillFrame(pool);
									 gameState = CAN_SELECT_FROM_RACK;
									 updateFrameVisual();
								 }
							});;
				    	}
				    	else {
				    		gameState=CAN_PLACE_ON_BOARD;
				    	}		
			    	}
			    }
			});;
		
		frameButton5.setOnAction(new EventHandler<ActionEvent>() {
			 @Override public void handle(ActionEvent e) {
			    	if(gameState==CAN_SELECT_FROM_RACK || gameState==EXCHANGING) {
			    		currentSelectedTile=currentPlayer.playerFrame.getLettersInFrame().get(5);
				    	frameButton5.setVisible(false);
				    	currentWord.add(currentSelectedTile);
				    	
				    	if(gameState==EXCHANGING) {
				    		pool.addTileToPool(currentSelectedTile);
				    		
							exchangeButton.setOnAction(new EventHandler<ActionEvent>() 
							{
								 @Override public void handle(ActionEvent e) 
								 { 
									 for(int i=0; i<currentWord.size(); i++)
									 {
										 currentPlayer.playerFrame.removeTile(currentWord.get(i));
									 }
									 
									 currentPlayer.playerFrame.fillFrame(pool);
									 gameState = CAN_SELECT_FROM_RACK;
									 updateFrameVisual();
								 }
							});;
				    	}
				    	else {
				    		gameState=CAN_PLACE_ON_BOARD;
				    	}	
			    	}
			    }
			});;

		frameButton6.setOnAction(new EventHandler<ActionEvent>() {
			 @Override public void handle(ActionEvent e) {
			    	if(gameState==CAN_SELECT_FROM_RACK || gameState==EXCHANGING) {
			    		currentSelectedTile=currentPlayer.playerFrame.getLettersInFrame().get(6);
				    	frameButton6.setVisible(false);
				    	currentWord.add(currentSelectedTile);
				    	
				    	if(gameState==EXCHANGING) {
				    		pool.addTileToPool(currentSelectedTile);
				    		
							exchangeButton.setOnAction(new EventHandler<ActionEvent>() 
							{
								 @Override public void handle(ActionEvent e) 
								 { 
									 for(int i=0; i<currentWord.size(); i++)
									 {
										 currentPlayer.playerFrame.removeTile(currentWord.get(i));
									 }
									 
									 currentPlayer.playerFrame.fillFrame(pool);
									 gameState = CAN_SELECT_FROM_RACK;
									 updateFrameVisual();
								 }
							});;	
				    	}
				    	else {
				    		gameState=CAN_PLACE_ON_BOARD;
				    	}	
			    	}
			    }
			});;
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
		
		if(gameState!=EXCHANGING)
		{
			exchangeButton.setText("EXCHANGE");
			
			exchangeButton.setOnAction(new EventHandler<ActionEvent>() 
			{
				 @Override public void handle(ActionEvent e) 
				 {
					 exchangeButton.setText("CONFIRM");
					 gameState = EXCHANGING;
				 }
			});;
		}
	}
	
	public void initButtons()
	{	
		playWordButton.setStyle("-fx-font-size: 10pt;"
		+ "-fx-font-weight: bold;"
		+ "-fx-border-color: #177a76;"
		+ "-fx-background-color: #23B2AC;");
	
		playWordButton.setOnMouseEntered(e -> playWordButton.setStyle("-fx-font-size: 10pt;"
			+ "-fx-font-weight: bold;"
			+ "-fx-border-color: #177a76;"
			+ "-fx-background-color: #c9fffd;"));
	
		playWordButton.setOnMouseExited(e -> playWordButton.setStyle("-fx-font-size: 10pt;"
			+ "-fx-font-weight: bold;"
			+ "-fx-border-color: #177a76;"
			+ "-fx-background-color: #23B2AC;"));
		
		exchangeButton.setStyle("-fx-font-size: 10pt;"
		+ "-fx-font-weight: bold;"
		+ "-fx-border-color: #177a76;"
		+ "-fx-background-color: #23B2AC;");

		exchangeButton.setOnMouseEntered(e -> exchangeButton.setStyle("-fx-font-size: 10pt;"
				+ "-fx-font-weight: bold;"
				+ "-fx-border-color: #177a76;"
				+ "-fx-background-color: #c9fffd;"));
		
		exchangeButton.setOnMouseExited(e -> exchangeButton.setStyle("-fx-font-size: 10pt;"
				+ "-fx-font-weight: bold;"
				+ "-fx-border-color: #177a76;"
				+ "-fx-background-color: #23B2AC;"));
		
		exchangeButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			 @Override public void handle(ActionEvent e) 
			 {
				 exchangeButton.setDisable(false);
				 exchangeButton.setText("CONFIRM");
				 gameState = EXCHANGING;
			 }
		});;
		
		endTurnButton.setStyle("-fx-font-size: 15pt;"
		+ "-fx-font-weight: bold;"
		+ "-fx-border-color: #177a76;"
		+ "-fx-background-color: #23B2AC;");

		endTurnButton.setOnMouseEntered(e -> endTurnButton.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;"
				+ "-fx-border-color: #177a76;"
				+ "-fx-background-color: #c9fffd;"));

		endTurnButton.setOnMouseExited(e -> endTurnButton.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;"
				+ "-fx-border-color: #177a76;"
				+ "-fx-background-color: #23B2AC;"));
		
		endTurnButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			 @Override public void handle(ActionEvent e) 
			 {
				 currentPlayer = currentPlayer == player1 ? player2 : player1;				 
				 updateFrameVisual();
			 }
		});;
		
		helpButton.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;"
				+ "-fx-border-color: #177a76;"
				+ "-fx-background-color: #23B2AC;");
		
		helpButton.setOnMouseEntered(e -> helpButton.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;"
				+ "-fx-border-color: #177a76;"
				+ "-fx-background-color: #c9fffd;"));
		
		helpButton.setOnMouseExited(e -> helpButton.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;"
				+ "-fx-border-color: #177a76;"
				+ "-fx-background-color: #23B2AC;"));
		
		helpButton.setOnAction(e -> displayHelp());
		
		passButton.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;"
				+ "-fx-border-color: #177a76;"
				+ "-fx-background-color: #23B2AC;");
		
		passButton.setOnMouseEntered(e -> passButton.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;"
				+ "-fx-border-color: #177a76;"
				+ "-fx-background-color: #c9fffd;"));
		
		passButton.setOnMouseExited(e -> passButton.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;"
				+ "-fx-border-color: #177a76;"
				+ "-fx-background-color: #23B2AC;"));
		
		passButton.setOnAction(e -> displayPassWindow());
		
		quitButton.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;"
				+ "-fx-border-color: #177a76;"
				+ "-fx-background-color: #23B2AC;");
		
		quitButton.setOnMouseEntered(e -> quitButton.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;"
				+ "-fx-border-color: #177a76;"
				+ "-fx-background-color: #c9fffd;"));
		
		quitButton.setOnMouseExited(e -> quitButton.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;"
				+ "-fx-border-color: #177a76;"
				+ "-fx-background-color: #23B2AC;"));
		
		quitButton.setOnAction(e -> Platform.exit());
		
	}
	
	public static void displayHelp()
	{
		Stage helpWindow = new Stage();	      
		helpWindow.initModality(Modality.APPLICATION_MODAL);
		helpWindow.setTitle("Help");  
		
		Button closeHelpButton = new Button("CLOSE");
		closeHelpButton.setMaxSize(200, 20);
		closeHelpButton.setMinSize(200, 40);
		
		closeHelpButton.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;"
				+ "-fx-border-color: #177a76;"
				+ "-fx-background-color: #23B2AC;");
		
		closeHelpButton.setOnMouseEntered(e -> closeHelpButton.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;"
				+ "-fx-border-color: #177a76;"
				+ "-fx-background-color: #c9fffd;"));
		
		closeHelpButton.setOnMouseExited(e -> closeHelpButton.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;"
				+ "-fx-border-color: #177a76;"
				+ "-fx-background-color: #23B2AC;"));
		
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
		      
		Scene scene1 = new Scene(scrollPane, 600, 450);
		      
		helpWindow.setScene(scene1);
		
		helpTextBody.wrappingWidthProperty().bind(scene1.widthProperty().subtract(15));
		helpTextPassBody.wrappingWidthProperty().bind(scene1.widthProperty().subtract(15));
		helpTextExchangeBody.wrappingWidthProperty().bind(scene1.widthProperty().subtract(15));
		helpEndTurnBody.wrappingWidthProperty().bind(scene1.widthProperty().subtract(15));
		
		helpWindow.showAndWait();
	       
	}
	
	public void displayPassWindow()
	{
		Stage passWindow = new Stage();	      
		passWindow.initModality(Modality.APPLICATION_MODAL);
		passWindow.setTitle("Passing turn");  
		
		Button yesButton = new Button("YES");
		yesButton.setMaxSize(80, 40);
		yesButton.setMinSize(80, 40);
		
		yesButton.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;"
				+ "-fx-border-color: #177a76;"
				+ "-fx-background-color: #23B2AC;");
		
		yesButton.setOnMouseEntered(e -> yesButton.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;"
				+ "-fx-border-color: #177a76;"
				+ "-fx-background-color: #c9fffd;"));
		
		yesButton.setOnMouseExited(e -> yesButton.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;"
				+ "-fx-border-color: #177a76;"
				+ "-fx-background-color: #23B2AC;"));
		
		yesButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			 @Override public void handle(ActionEvent e) 
			 {
				 passWindow.close();
				 currentPlayer = currentPlayer == player1 ? player2 : player1;				 
				 updateFrameVisual();
			 }
		});;
		
		Button noButton = new Button("NO");
		noButton.setMaxSize(80, 40);
		noButton.setMinSize(80, 40);
		
		noButton.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;"
				+ "-fx-border-color: #177a76;"
				+ "-fx-background-color: #23B2AC;");
		
		noButton.setOnMouseEntered(e -> noButton.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;"
				+ "-fx-border-color: #177a76;"
				+ "-fx-background-color: #c9fffd;"));
		
		noButton.setOnMouseExited(e -> noButton.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;"
				+ "-fx-border-color: #177a76;"
				+ "-fx-background-color: #23B2AC;"));
		
		noButton.setOnAction(e -> passWindow.close());
		
		HBox buttonsHbox = new HBox(10);
		buttonsHbox.getChildren().addAll(yesButton, noButton);
		
		Text passText = new Text("Are you sure you want to pass your turn?");
		passText.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;");
		
		VBox layout = new VBox(10);	      
		layout.getChildren().addAll(passText, buttonsHbox);		      
		layout.setAlignment(Pos.CENTER);
		      
		Scene scene1 = new Scene(layout, 220, 200);      
		passWindow.setScene(scene1);
		passText.wrappingWidthProperty().bind(scene1.widthProperty().subtract(15));
		passWindow.showAndWait();
		
	}
}