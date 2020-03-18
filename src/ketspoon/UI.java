package ketspoon;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;



public class UI extends Application {
	public static final int WIDTH=15;
	public static final int HEIGHT=15;
	
	GridPane grid = new GridPane();
	Scene scene = new Scene(grid,1000,820);
		
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
	
	@Override
	public void start(Stage stage) {
		try {
			initBoard();
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