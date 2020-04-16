package ketspoon;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Main extends Application {

    public static void main(String[] args) throws FileNotFoundException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        UserInterface ui = new UserInterface();
        ui.displayStage(primaryStage);
    }

}

