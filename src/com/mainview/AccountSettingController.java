package com.mainview;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AccountSettingController implements Initializable {

    @FXML
    private AnchorPane rootPane;
	
    @FXML
    private Button ReserveBtn;

    @FXML
    private Button RoomsBtn;

    @FXML
    private Button CListBtn;
    
    
    @FXML 
    private void goCustomerListPage(ActionEvent event) throws IOException {
		Parent home_page_parent = FXMLLoader.load(getClass().getResource("CustomerList.fxml") );
		Scene home_page_scene = new Scene (home_page_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(home_page_scene);
		app_stage.show();
		
    }
    


    @FXML
    private void goReservationPage(ActionEvent event) throws IOException {
    	Parent home_page_parent = FXMLLoader.load(getClass().getResource("Reserve.fxml") );
		Scene home_page_scene = new Scene (home_page_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(home_page_scene);
		app_stage.show();

    }

    @FXML
    private void goRoomsPage(ActionEvent event) throws IOException {
    	Parent home_page_parent = FXMLLoader.load(getClass().getResource("Rooms.fxml") );
		Scene home_page_scene = new Scene (home_page_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(home_page_scene);
		app_stage.show();

    }
    
    @FXML 
    private void goSetting(ActionEvent event) throws IOException {
		Parent home_page_parent = FXMLLoader.load(getClass().getResource("AccountSetting.fxml") );
		Scene home_page_scene = new Scene (home_page_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(home_page_scene);
		app_stage.show();
	
    }
    
    @FXML 
    private void goSignOut(ActionEvent event) throws IOException {
		Parent home_page_parent = FXMLLoader.load(getClass().getResource("Login.fxml") );
		Scene home_page_scene = new Scene (home_page_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(home_page_scene);
		app_stage.show();
	
    }
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		}
		
	}

