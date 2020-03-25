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



