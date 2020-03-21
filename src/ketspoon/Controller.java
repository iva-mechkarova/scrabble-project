package ketspoon;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

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
		    	if(gameState==CAN_SELECT_FROM_RACK) {
		    		currentSelectedTile=currentPlayer.playerFrame.getLettersInFrame().get(0);
			    	frameButton0.setVisible(false);
			    	currentWord.add(currentSelectedTile);
			    	gameState=CAN_PLACE_ON_BOARD;
		    	}
		    }
		});;
		
		frameButton1.setOnAction(new EventHandler<ActionEvent>() {
			 @Override public void handle(ActionEvent e) {
			    	if(gameState==CAN_SELECT_FROM_RACK) {
			    		currentSelectedTile=currentPlayer.playerFrame.getLettersInFrame().get(1);
				    	frameButton1.setVisible(false);
				    	currentWord.add(currentSelectedTile);
				    	gameState=CAN_PLACE_ON_BOARD;
			    	}
			    }
			});;
		
		frameButton2.setOnAction(new EventHandler<ActionEvent>() {
			 @Override public void handle(ActionEvent e) {
			    	if(gameState==CAN_SELECT_FROM_RACK) {
			    		currentSelectedTile=currentPlayer.playerFrame.getLettersInFrame().get(2);
				    	frameButton2.setVisible(false);
				    	currentWord.add(currentSelectedTile);
				    	gameState=CAN_PLACE_ON_BOARD;
			    	}
			    }
			});;
		
		frameButton3.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	if(gameState==CAN_SELECT_FROM_RACK) {
		    		currentSelectedTile=currentPlayer.playerFrame.getLettersInFrame().get(3);
			    	frameButton3.setVisible(false);
			    	currentWord.add(currentSelectedTile);
			    	gameState=CAN_PLACE_ON_BOARD;
		    	}
		    }
		});;
		
		frameButton4.setOnAction(new EventHandler<ActionEvent>() {
			 @Override public void handle(ActionEvent e) {
			    	if(gameState==CAN_SELECT_FROM_RACK) {
			    		currentSelectedTile=currentPlayer.playerFrame.getLettersInFrame().get(4);
				    	frameButton4.setVisible(false);
				    	currentWord.add(currentSelectedTile);
				    	gameState=CAN_PLACE_ON_BOARD;
			    	}
			    }
			});;
		
		frameButton5.setOnAction(new EventHandler<ActionEvent>() {
			 @Override public void handle(ActionEvent e) {
			    	if(gameState==CAN_SELECT_FROM_RACK) {
			    		currentSelectedTile=currentPlayer.playerFrame.getLettersInFrame().get(5);
				    	frameButton5.setVisible(false);
				    	currentWord.add(currentSelectedTile);
				    	gameState=CAN_PLACE_ON_BOARD;
			    	}
			    }
			});;

		frameButton6.setOnAction(new EventHandler<ActionEvent>() {
			 @Override public void handle(ActionEvent e) {
			    	if(gameState==CAN_SELECT_FROM_RACK) {
			    		currentSelectedTile=currentPlayer.playerFrame.getLettersInFrame().get(6);
				    	frameButton6.setVisible(false);
				    	currentWord.add(currentSelectedTile);
				    	gameState=CAN_PLACE_ON_BOARD;
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
	}
}
