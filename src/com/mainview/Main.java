package com.mainview;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application{
	
	@Override
	public void start(Stage stage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Reserve.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Hotel Reservation System");
			
			stage.setOnCloseRequest(event -> {
				
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Close confirmation");
				alert.setContentText("Are you sure do you want to close?");
				
				alert.showAndWait().ifPresent(type -> {
					if(type == ButtonType.CANCEL) {
						event.consume(); 
					}
				});
			});
			
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
