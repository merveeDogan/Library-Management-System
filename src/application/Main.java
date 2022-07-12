package application;
	
import Code.Library;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;


public class Main extends Application {
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Library.syncData();
			Parent root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest(event -> {
				event.consume();
				ExitProgram(primaryStage);	
			});
		} catch(Exception e) {
		}
	}
	
public void ExitProgram(Stage stage){	
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setHeaderText("You're about to exit!");
		alert.setContentText("Are you sure you want to close the program?");
		
		if (alert.showAndWait().get() == ButtonType.OK){			
			stage.close();
		} 
	}


	public static void main(String[] args) {
		launch(args);
	}
}
