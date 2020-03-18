package ketspoon;
import java.awt.event.ActionListener;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;



public class UI extends Application {
	public static final int WIDTH=15;
	public static final int HEIGHT=15;
	
	GridPane grid = new GridPane();
	
	Button endTurn = new Button("END TURN");
	Button helpButton = new Button("HELP");
	Button exchangeButton = new Button("EXCHANGE");
	Button passButton = new Button("PASS");
	Button quitButton = new Button("QUIT");
	VBox buttonBox = new VBox(20, endTurn, helpButton, exchangeButton, passButton, quitButton);
	
	HBox root = new HBox(grid, buttonBox);
	Scene scene = new Scene(root,1000,820);
		
	public void initBoard() {	
		Board scrabbleBoard = new Board();
		Square currentSquare;
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				currentSquare = scrabbleBoard.gameBoard.get(scrabbleBoard.coordinateToIndex(i, j));
				scrabbleBoard.gameBoard.get(currentSquare.getSquareIndex()).setSquareButton(new Button());
				currentSquare.getSquareButton().setGraphic(new ImageView(currentSquare.getSquareImage()));
				currentSquare.getSquareButton().setPadding(new Insets(0,0,0,0));
				grid.add(currentSquare.getSquareButton(),j,i);
				
				currentSquare.getSquareButton().setOnAction(new EventHandler<ActionEvent>() {
				    @Override public void handle(ActionEvent e) {
				        Button button = (Button) e.getSource();
				        button.setGraphic(new ImageView("/resources/tripleWord.png"));
				    }
				});;
			}
		}
	}
	
	public void initButtons()
	{
		buttonBox.setAlignment(Pos.TOP_RIGHT);
		endTurn.setPrefHeight(45);
		endTurn.setPrefWidth(230);
		endTurn.setMinSize(50, 10);	
		endTurn.isWrapText();
		endTurn.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;"
				+ "-fx-border-color: #177a76;"
				+ "-fx-background-color: #23B2AC;");
		
		endTurn.setOnMouseEntered(e -> endTurn.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;"
				+ "-fx-border-color: #177a76;"
				+ "-fx-background-color: #c9fffd;"));
		
		endTurn.setOnMouseExited(e -> endTurn.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;"
				+ "-fx-border-color: #177a76;"
				+ "-fx-background-color: #23B2AC;"));
		
		
		helpButton.setPrefHeight(45);
		helpButton.setPrefWidth(230);
		helpButton.setMinSize(50, 10);
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
		
		exchangeButton.setPrefHeight(45);
		exchangeButton.setPrefWidth(230);
		exchangeButton.setMinSize(50, 10);
		exchangeButton.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;"
				+ "-fx-border-color: #177a76;"
				+ "-fx-background-color: #23B2AC;");
		
		exchangeButton.setOnMouseEntered(e -> exchangeButton.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;"
				+ "-fx-border-color: #177a76;"
				+ "-fx-background-color: #c9fffd;"));
		
		exchangeButton.setOnMouseExited(e -> exchangeButton.setStyle("-fx-font-size: 15pt;"
				+ "-fx-font-weight: bold;"
				+ "-fx-border-color: #177a76;"
				+ "-fx-background-color: #23B2AC;"));
		
		passButton.setPrefHeight(45);
		passButton.setPrefWidth(230);
		passButton.setMinSize(50, 10);
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
		
		quitButton.setPrefHeight(45);
		quitButton.setPrefWidth(230);
		quitButton.setMinSize(50, 10);
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
		
		Text helpText = new Text(10,50, "Help:");
		VBox layout = new VBox(10);
		     
		      
		layout.getChildren().addAll(helpText, closeHelpButton);
		      
		layout.setAlignment(Pos.CENTER);
		      
		Scene scene1 = new Scene(layout, 300, 250);
		      
		helpWindow.setScene(scene1);
		      
		helpWindow.showAndWait();
	       
	}
	
	@Override
	public void start(Stage stage) {
		try {
			initBoard();
			initButtons();
			
			stage.setTitle("Scrabble by Iva Mechkarova, Morgan Collins and Evan Willis");
			stage.setScene(scene);
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}