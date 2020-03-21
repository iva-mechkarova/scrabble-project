package ketspoon;


import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;



public class UI extends Application {
	public final Image bg = new Image(getClass().getResourceAsStream("/resources/t.png"));
	
	
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("UI.fxml"));
	        Parent root = loader.load();

	        primaryStage.setTitle("Scrabble");
	        primaryStage.setScene(new Scene(root, 1000, 830));
	        primaryStage.show();
	        
	        Controller controller = loader.getController();
	        
	        controller.initGame();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}



//public class UI extends Application {
//	public static final int WIDTH=15;
//	public static final int HEIGHT=15;
//	
//	Pool pool = new Pool();
//	Frame frame1 = new Frame(pool);
//	Frame frame2 = new Frame(pool);
//	Player player1 = new Player(frame1, "");
//	Player player2 = new Player(frame2, "");
//	
//	GridPane grid = new GridPane();
//	
//	Button endTurn = new Button("END TURN");
//	Button helpButton = new Button("HELP");
//	Button exchangeButton = new Button("EXCHANGE");
//	Button passButton = new Button("PASS");
//	Button quitButton = new Button("QUIT");
//	VBox buttonBox = new VBox(20, endTurn, helpButton, exchangeButton, passButton, quitButton);
//	HBox playerInfoBox = new HBox(10);
//	
//	HBox root = new HBox(grid, buttonBox, playerInfoBox);
//	Scene scene = new Scene(root,1000,820);
//		
//	public void initBoard() {	
//		Board scrabbleBoard = new Board();
//		Square currentSquare;
//		for (int i = 0; i < WIDTH; i++) {
//			for (int j = 0; j < HEIGHT; j++) {
//				currentSquare = scrabbleBoard.gameBoard.get(scrabbleBoard.coordinateToIndex(i, j));
//				scrabbleBoard.gameBoard.get(currentSquare.getSquareIndex()).setSquareButton(new Button());
//				currentSquare.getSquareButton().setGraphic(new ImageView(currentSquare.getSquareImage()));
//				currentSquare.getSquareButton().setPadding(new Insets(0,0,0,0));
//				grid.add(currentSquare.getSquareButton(),j,i);
//				
//				currentSquare.getSquareButton().setOnAction(new EventHandler<ActionEvent>() {
//				    @Override public void handle(ActionEvent e) {
//				        Button button = (Button) e.getSource();
//				        button.setGraphic(new ImageView("/resources/tripleWord.png"));
//				    }
//				});;
//			}
//		}
//	}
//	
//	public void initButtons()
//	{
//		buttonBox.setAlignment(Pos.TOP_RIGHT);
//		endTurn.setPrefHeight(45);
//		endTurn.setPrefWidth(230);
//		endTurn.setMinSize(50, 10);	
//		endTurn.isWrapText();
//		endTurn.setStyle("-fx-font-size: 15pt;"
//				+ "-fx-font-weight: bold;"
//				+ "-fx-border-color: #177a76;"
//				+ "-fx-background-color: #23B2AC;");
//		
//		endTurn.setOnMouseEntered(e -> endTurn.setStyle("-fx-font-size: 15pt;"
//				+ "-fx-font-weight: bold;"
//				+ "-fx-border-color: #177a76;"
//				+ "-fx-background-color: #c9fffd;"));
//		
//		endTurn.setOnMouseExited(e -> endTurn.setStyle("-fx-font-size: 15pt;"
//				+ "-fx-font-weight: bold;"
//				+ "-fx-border-color: #177a76;"
//				+ "-fx-background-color: #23B2AC;"));
//		
//		
//		helpButton.setPrefHeight(45);
//		helpButton.setPrefWidth(230);
//		helpButton.setMinSize(50, 10);
//		helpButton.setStyle("-fx-font-size: 15pt;"
//				+ "-fx-font-weight: bold;"
//				+ "-fx-border-color: #177a76;"
//				+ "-fx-background-color: #23B2AC;");
//		
//		helpButton.setOnMouseEntered(e -> helpButton.setStyle("-fx-font-size: 15pt;"
//				+ "-fx-font-weight: bold;"
//				+ "-fx-border-color: #177a76;"
//				+ "-fx-background-color: #c9fffd;"));
//		
//		helpButton.setOnMouseExited(e -> helpButton.setStyle("-fx-font-size: 15pt;"
//				+ "-fx-font-weight: bold;"
//				+ "-fx-border-color: #177a76;"
//				+ "-fx-background-color: #23B2AC;"));
//		
//		helpButton.setOnAction(e -> displayHelp());
//		
//		exchangeButton.setPrefHeight(45);
//		exchangeButton.setPrefWidth(230);
//		exchangeButton.setMinSize(50, 10);
//		exchangeButton.setStyle("-fx-font-size: 15pt;"
//				+ "-fx-font-weight: bold;"
//				+ "-fx-border-color: #177a76;"
//				+ "-fx-background-color: #23B2AC;");
//		
//		exchangeButton.setOnMouseEntered(e -> exchangeButton.setStyle("-fx-font-size: 15pt;"
//				+ "-fx-font-weight: bold;"
//				+ "-fx-border-color: #177a76;"
//				+ "-fx-background-color: #c9fffd;"));
//		
//		exchangeButton.setOnMouseExited(e -> exchangeButton.setStyle("-fx-font-size: 15pt;"
//				+ "-fx-font-weight: bold;"
//				+ "-fx-border-color: #177a76;"
//				+ "-fx-background-color: #23B2AC;"));
//		
//		passButton.setPrefHeight(45);
//		passButton.setPrefWidth(230);
//		passButton.setMinSize(50, 10);
//		passButton.setStyle("-fx-font-size: 15pt;"
//				+ "-fx-font-weight: bold;"
//				+ "-fx-border-color: #177a76;"
//				+ "-fx-background-color: #23B2AC;");
//		
//		passButton.setOnMouseEntered(e -> passButton.setStyle("-fx-font-size: 15pt;"
//				+ "-fx-font-weight: bold;"
//				+ "-fx-border-color: #177a76;"
//				+ "-fx-background-color: #c9fffd;"));
//		
//		passButton.setOnMouseExited(e -> passButton.setStyle("-fx-font-size: 15pt;"
//				+ "-fx-font-weight: bold;"
//				+ "-fx-border-color: #177a76;"
//				+ "-fx-background-color: #23B2AC;"));
//		
//		quitButton.setPrefHeight(45);
//		quitButton.setPrefWidth(230);
//		quitButton.setMinSize(50, 10);
//		quitButton.setStyle("-fx-font-size: 15pt;"
//				+ "-fx-font-weight: bold;"
//				+ "-fx-border-color: #177a76;"
//				+ "-fx-background-color: #23B2AC;");
//		
//		quitButton.setOnMouseEntered(e -> quitButton.setStyle("-fx-font-size: 15pt;"
//				+ "-fx-font-weight: bold;"
//				+ "-fx-border-color: #177a76;"
//				+ "-fx-background-color: #c9fffd;"));
//		
//		quitButton.setOnMouseExited(e -> quitButton.setStyle("-fx-font-size: 15pt;"
//				+ "-fx-font-weight: bold;"
//				+ "-fx-border-color: #177a76;"
//				+ "-fx-background-color: #23B2AC;"));
//		
//		quitButton.setOnAction(e -> Platform.exit());
//		
//	}
//	
//	public static void displayHelp()
//	{
//		Stage helpWindow = new Stage();	      
//		helpWindow.initModality(Modality.APPLICATION_MODAL);
//		helpWindow.setTitle("Help");  
//		
//		Button closeHelpButton = new Button("CLOSE");
//		closeHelpButton.setMaxSize(200, 20);
//		closeHelpButton.setMinSize(200, 40);
//		
//		closeHelpButton.setStyle("-fx-font-size: 15pt;"
//				+ "-fx-font-weight: bold;"
//				+ "-fx-border-color: #177a76;"
//				+ "-fx-background-color: #23B2AC;");
//		
//		closeHelpButton.setOnMouseEntered(e -> closeHelpButton.setStyle("-fx-font-size: 15pt;"
//				+ "-fx-font-weight: bold;"
//				+ "-fx-border-color: #177a76;"
//				+ "-fx-background-color: #c9fffd;"));
//		
//		closeHelpButton.setOnMouseExited(e -> closeHelpButton.setStyle("-fx-font-size: 15pt;"
//				+ "-fx-font-weight: bold;"
//				+ "-fx-border-color: #177a76;"
//				+ "-fx-background-color: #23B2AC;"));
//		
//		closeHelpButton.setOnAction(e -> helpWindow.close());
//		
//		Text helpTextTitle = new Text("Placing a word:");
//		helpTextTitle.setStyle("-fx-font-size: 15pt;"
//				+ "-fx-font-weight: bold;");
//		
//		Text helpTextBody = new Text("1. Select a tile from your rack.\n"
//				+ "2. Click on any of the playable squares on the board to place it there i.e. first tile must be placed in the middle "
//				+ "of the board and all other words must be connected to already existing tiles on the board.\n"
//				+ "3. Select the next letter of the word you are placing from your rack.\n"
//				+ "4. Select one of the playable squares on the board to place it there and keep doing this with the rest "
//				+ "of the letters of the word you wish to place.\n");
//		helpTextBody.setStyle("-fx-font-size: 10pt;");
//		
//		Text helpTextPass = new Text("Passing your turn:");
//		helpTextPass.setStyle("-fx-font-size: 15pt;"
//				+ "-fx-font-weight: bold;");
//		
//		Text helpTextPassBody = new Text("Rather than exchange tiles, you can also pass your turn and take a zero score. "
//				+ "This is your only option if there are six or fewer tiles remaining in the pool. "
//				+ "If all of the players pass twice in succession, the game ends.");
//		helpTextPassBody.setStyle("-fx-font-size: 10pt;");
//		
//		Text helpTextExchange = new Text("Exchanging tiles:");
//		helpTextExchange.setStyle("-fx-font-size: 15pt;"
//				+ "-fx-font-weight: bold;");
//		
//		Text helpTextExchangeBody = new Text("You may swap one to seven tiles instead of playing a word on your turn. "
//				+ "You can only do this if at least seven tiles are still in the pool. "
//				+ "To do this first click on the exchange button and then click on the tiles that you wish to exchange one by one.");
//		helpTextExchangeBody.setStyle("-fx-font-size: 10pt;");
//		
//		Text helpEndTurn = new Text("Ending turn:");
//		helpEndTurn.setStyle("-fx-font-size: 15pt;"
//				+ "-fx-font-weight: bold;");
//		
//		Text helpEndTurnBody = new Text("Once you are finished placing a word OR selecting tiles to exchange, click"
//				+ " on the end turn button.");
//		helpEndTurnBody.setStyle("-fx-font-size: 10pt;");
//		
//		VBox layout = new VBox(10);
//		ScrollPane scrollPane = new ScrollPane(layout);
//		scrollPane.setFitToHeight(true);
//		scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
//		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
//
//		      
//		layout.getChildren().addAll(helpTextTitle, helpTextBody, helpTextPass, helpTextPassBody, helpTextExchange, 
//				helpTextExchangeBody, helpEndTurn, helpEndTurnBody, closeHelpButton);
//		      
//		layout.setAlignment(Pos.CENTER);
//		      
//		Scene scene1 = new Scene(scrollPane, 600, 450);
//		      
//		helpWindow.setScene(scene1);
//		
//		helpTextBody.wrappingWidthProperty().bind(scene1.widthProperty().subtract(15));
//		helpTextPassBody.wrappingWidthProperty().bind(scene1.widthProperty().subtract(15));
//		helpTextExchangeBody.wrappingWidthProperty().bind(scene1.widthProperty().subtract(15));
//		helpEndTurnBody.wrappingWidthProperty().bind(scene1.widthProperty().subtract(15));
//		
//		helpWindow.showAndWait();
//	       
//	}
//	
//	public void initPlayerOne()
//	{
//		Stage playerNameWindow = new Stage();	      
//		playerNameWindow.initModality(Modality.APPLICATION_MODAL);
//		playerNameWindow.setTitle("Player 1");  
//		
//		Label playerNameLabel = new Label("Enter player 1's name:");
//		playerNameLabel.setStyle("-fx-font-size: 10pt;"
//				+ "-fx-font-weight: bold;");
//		TextField playerName = new TextField();
//		
//		Button doneButton = new Button("DONE");
//		doneButton.setMaxSize(150, 20);
//		doneButton.setMinSize(200, 40);
//		
//		doneButton.setStyle("-fx-font-size: 15pt;"
//				+ "-fx-font-weight: bold;"
//				+ "-fx-border-color: #177a76;"
//				+ "-fx-background-color: #23B2AC;");
//		
//		doneButton.setOnMouseEntered(e -> doneButton.setStyle("-fx-font-size: 15pt;"
//				+ "-fx-font-weight: bold;"
//				+ "-fx-border-color: #177a76;"
//				+ "-fx-background-color: #c9fffd;"));
//		
//		doneButton.setOnMouseExited(e -> doneButton.setStyle("-fx-font-size: 15pt;"
//				+ "-fx-font-weight: bold;"
//				+ "-fx-border-color: #177a76;"
//				+ "-fx-background-color: #23B2AC;"));
//		
//		doneButton.setOnAction(e -> {
//			player1.setName(playerName.getText());
//			playerNameWindow.close();
//		});
//		
//		VBox layout = new VBox(10);
//		ScrollPane scrollPane = new ScrollPane(layout);
//		scrollPane.setFitToHeight(true);
//		scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
//		scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
//
//		      
//		layout.getChildren().addAll(playerNameLabel, playerName, doneButton);
//		      
//		layout.setAlignment(Pos.CENTER);
//		      
//		Scene scene1 = new Scene(scrollPane, 240, 110);
//		      
//		playerNameWindow.setScene(scene1);
//
//		playerNameWindow.showAndWait();
//	       
//	}
//	
//	public void initPlayerTwo()
//	{
//		Stage playerNameWindow = new Stage();	      
//		playerNameWindow.initModality(Modality.APPLICATION_MODAL);
//		playerNameWindow.setTitle("Player 2");  
//		
//		Label playerNameLabel = new Label("Enter player 2's name:");
//		playerNameLabel.setStyle("-fx-font-size: 10pt;"
//				+ "-fx-font-weight: bold;");
//		TextField playerName = new TextField();
//		
//		Button doneButton = new Button("DONE");
//		doneButton.setMaxSize(150, 20);
//		doneButton.setMinSize(200, 40);
//		
//		doneButton.setStyle("-fx-font-size: 15pt;"
//				+ "-fx-font-weight: bold;"
//				+ "-fx-border-color: #177a76;"
//				+ "-fx-background-color: #23B2AC;");
//		
//		doneButton.setOnMouseEntered(e -> doneButton.setStyle("-fx-font-size: 15pt;"
//				+ "-fx-font-weight: bold;"
//				+ "-fx-border-color: #177a76;"
//				+ "-fx-background-color: #c9fffd;"));
//		
//		doneButton.setOnMouseExited(e -> doneButton.setStyle("-fx-font-size: 15pt;"
//				+ "-fx-font-weight: bold;"
//				+ "-fx-border-color: #177a76;"
//				+ "-fx-background-color: #23B2AC;"));
//		
//		doneButton.setOnAction(e -> {
//			player2.setName(playerName.getText());
//			playerNameWindow.close();
//		});
//		
//		VBox layout = new VBox(10);
//		ScrollPane scrollPane = new ScrollPane(layout);
//		scrollPane.setFitToHeight(true);
//		scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
//		scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
//
//		      
//		layout.getChildren().addAll(playerNameLabel, playerName, doneButton);
//		      
//		layout.setAlignment(Pos.CENTER);
//		      
//		Scene scene1 = new Scene(scrollPane, 240, 110);
//		      
//		playerNameWindow.setScene(scene1);
//
//		playerNameWindow.showAndWait();
//	       
//	}
//	
//	public void displayPlayerInfo()
//	{
//		Label playerOneName = new Label(player1.getName());
//		Label playerTwoName = new Label(player2.getName());
//		playerInfoBox.getChildren().addAll(playerOneName, playerTwoName);
//	}
//	
//	@Override
//	public void start(Stage stage) {
//		try {
//			initPlayerOne();
//			initPlayerTwo();
//			initBoard();
//			initButtons();
//			displayPlayerInfo();
//			
//			stage.setTitle("Scrabble by Iva Mechkarova, Morgan Collins and Evan Willis");
//			stage.setScene(scene);
//			stage.show();
//			
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static void main(String[] args) {
//		launch(args);
//	}
//	
//}