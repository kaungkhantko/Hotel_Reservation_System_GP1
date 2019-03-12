//package com.mainview;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//
//public class LoginController implements Initializable {
//
//   
//    
//    
//    @FXML 
//    private void SignIn(ActionEvent event) throws IOException {
//		Parent home_page_parent = FXMLLoader.load(getClass().getResource("Rooms.fxml") );
//		Scene home_page_scene = new Scene (home_page_parent);
//		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//		app_stage.setScene(home_page_scene);
//		app_stage.show();
//		
//    }
//    
//	@Override
//	public void initialize(URL location, ResourceBundle resources) {
//	
//		}
//		
//	}
//

package com.mainview;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane; 
import javafx.stage.Stage;

	public class LoginController implements Initializable { 
		
	public LoginModel loginModel = new LoginModel();
	
	@FXML
	private Label isConnected;
	
	@FXML
	private TextField txtUsername;
	
	@FXML
	private TextField txtPassword;
	
    @FXML
    private Button signBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		if (loginModel.isDbConnected()) { 
				isConnected.setText("Connected");
		} else{
				isConnected.setText("Not Connected");
			 }
		
		
		signBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

				try {
					if (loginModel.isLogin(txtUsername.getText(), txtPassword.getText())) {
						Parent home_page_parent = FXMLLoader.load(getClass().getResource("Rooms.fxml") );
						Scene home_page_scene = new Scene (home_page_parent);
						Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
						app_stage.setScene(home_page_scene);
						app_stage.show();
						
						
						isConnected.setText("username and password is correct"); 

					} else {
						isConnected.setText("Incorrect username and password");
						}
				} catch (SQLException e1) {
					isConnected.setText("Incorrect username and password"); 							
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
	}
	
	
	public void Login (ActionEvent event) { 
		try {
			 txtPassword.setOnKeyPressed(e -> {
				 if(e.getCode() == KeyCode.ENTER) {
					 
						try {
							if (loginModel.isLogin(txtUsername.getText(), txtPassword.getText())) {
								Parent home_page_parent = FXMLLoader.load(getClass().getResource("Rooms.fxml") );
								Scene home_page_scene = new Scene (home_page_parent);
								Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
								app_stage.setScene(home_page_scene);
								app_stage.show();
								
								
								isConnected.setText("username and password is correct"); 

							} else {
								isConnected.setText("Incorrect username and password");
								}
						} catch (SQLException e1) {
							isConnected.setText("Incorrect username and password"); 							
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				 }
			 });
				
			} finally {
		}
	}
	
	
	
}

