
	package com.mainview;

	import java.io.IOException;
	import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
	import javafx.fxml.FXML;
	import javafx.fxml.FXMLLoader;
	import javafx.fxml.Initializable;
	import javafx.scene.Node;
	import javafx.scene.Parent;
	import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

	public class ReserveController implements Initializable {
		

	//************* Other Variables********//
	    @FXML private TextField cName;
		@FXML private TextField cNRC;
	    @FXML private TextField cPhNo1;
	    @FXML  private TextField cPhNo2;
	    @FXML private TextField cEmail;
  //******************************************//
    

    //************ Table Column Variables ************//
	    @FXML private TableView<RoomTemp> reserveList;
	    @FXML private TableColumn<?, ?> ReserveColRoomNo;
	    @FXML private TableColumn<?,?> ReserveColRoomType;
	    @FXML private TableColumn<?, ?> ReserveColCost;
	    @FXML private TableColumn<?, ?> ReserveColExtraBed;
	    @FXML private TableColumn<?, ?> ReserveColPeople;
	//  @FXML private TableColumn<?, ?> ReserveColCinDate;
	//  @FXML private TableColumn<?, ?> ReserveCoutDate;
	  //@FXML  private TableColumn<Room, ?> ReserveColTotal;
  //***************************************************//

		    
		    
	    
		@FXML public static ObservableList<RoomTemp> Reservedata;
	    
		  
		  
	    
	    //******************** Getter & Setter Methods************//

		//**************************************************************//
		
		
		
	  //*************************** Show Side Bar *************************//
	    @FXML private void goCustomerListPage(ActionEvent event) throws IOException {
	    	Parent home_page_parent = FXMLLoader.load(getClass().getResource("CustomerList.fxml") );
			Scene home_page_scene = new Scene (home_page_parent);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();
	    }
	    @FXML private void goReservationPage(ActionEvent event) throws IOException {
	    	Parent home_page_parent = FXMLLoader.load(getClass().getResource("Reserve.fxml") );
			Scene home_page_scene = new Scene (home_page_parent);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();
	    }
	    @FXML private void goRoomsPage(ActionEvent event) throws IOException {
	    	Parent home_page_parent = FXMLLoader.load(getClass().getResource("Rooms.fxml") );
			Scene home_page_scene = new Scene (home_page_parent);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();
	    }
	    @FXML private void goSetting(ActionEvent event) throws IOException {
			Parent home_page_parent = FXMLLoader.load(getClass().getResource("AccountSetting.fxml") );
			Scene home_page_scene = new Scene (home_page_parent);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();
	    }
	    @FXML private void goSignOut(ActionEvent event) throws IOException {
			Parent home_page_parent = FXMLLoader.load(getClass().getResource("Login.fxml") );
			Scene home_page_scene = new Scene (home_page_parent);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();
	    }
	  //*********************************************************************//
	  
	    
	    
    //************************** Action Events ***************************//
		@FXML public void confirm_db(ActionEvent event) throws SQLException {
	
    //**********Receiving data from UI and Inserting into DB********//
	
			SQLinsert sqlInsert = new SQLinsert();
			
	CustomerTable cL = new CustomerTable(null, null, null, null, null, 0, 0, 0, null, null, null, null, false);

	cL.setcName(cName.getText());
	cL.setcNRC(cNRC.getText());;
	cL.setcPhNo1(cPhNo1.getText());
	cL.setcPhNo2(cPhNo2.getText());
	cL.setcEmail(cEmail.getText());;
	
	cName.clear();
	cNRC.clear();
	cPhNo1.clear();
	cPhNo2.clear();
	cEmail.clear();
	
	//inserting into db
	sqlInsert.insertCinfo(cL.getcName(), cL.getNRC(), cL.getcPhNo1(), cL.getcPhNo2(), cL.getcEmail());
}
	    @FXML public void addRoom(ActionEvent event) throws IOException {
	    	Parent home_page_parent = FXMLLoader.load(getClass().getResource("Rooms.fxml") );
			Scene home_page_scene = new Scene (home_page_parent);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();

	    }
	    @FXML public void cancelRoom(ActionEvent event) {
	    }
    //********************************************************************//

	    
	    
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			
			Reservedata = FXCollections.observableArrayList();
			loadData();
			setCellTable2();
		}
		
		
		
		//**********************Other Methods*******************//
			public void setCellTable2() {
				
				System.out.println("Got into Reserve ");
				ReserveColRoomNo.setCellValueFactory(new PropertyValueFactory<>("RoomNo"));
				ReserveColRoomType.setCellValueFactory(new PropertyValueFactory<>("RoomType"));
				ReserveColCost.setCellValueFactory(new PropertyValueFactory<>("Cost"));
				ReserveColExtraBed.setCellValueFactory(new PropertyValueFactory<>("ExtraBed"));
				ReserveColPeople.setCellValueFactory(new PropertyValueFactory<>("NoOfPeople"));
//				ReserveCoutDate.setCellValueFactory(new PropertyValueFactory<>("dateOut"));
			}
			private void loadData() {
			   	reserveList.setItems(Reservedata);			   	
			}
			//*******************************************************//
		
	}



