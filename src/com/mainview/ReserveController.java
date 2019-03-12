
	package com.mainview;

	import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
	    @FXML private TableColumn<?, ?> ReserveColBedValue;
	    @FXML private TableColumn<?, ?> ReserveColPeople;
	    @FXML private TableColumn<?, ?> ReserveColCinDate;
	    @FXML private TableColumn<?, ?> ReserveColCoutDate;
	    @FXML  private TableColumn<?, ?> ReserveColTotal;
  //***************************************************//

		    
		    
	    
		@FXML public static ObservableList<RoomTemp> Reservedata =  FXCollections.observableArrayList();
	    
		
		
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
		CustomerTable cL = new CustomerTable();
		
		
		cL.setcName(cName.getText());
		cL.setcNRC(cNRC.getText());;
		cL.setcPhNo1(cPhNo1.getText());
		cL.setcPhNo2(cPhNo2.getText());
		cL.setcEmail(cEmail.getText());;
		
		cL.setRoomNo(Room.getStaticRoomNo());
		
    	cL.setDateIn(RoomsController.NSAstringDateIn);
    	cL.setDateOut(RoomsController.NSAstringDateOut);
    	
    	
    	System.out.println(cL.getcName());
    	System.out.println(cL.getNRC());
    	System.out.println(cL.getcPhNo1());
    	System.out.println(cL.getcPhNo2());
    	System.out.println(cL.getcEmail());
    	
    	System.out.println(cL.getRoomNo());
    	System.out.println(cL.getExtraBed());
    	System.out.println(cL.getPersonPerRoom());
    	System.out.println(cL.getDateIn());
    	System.out.println(cL.getDateOut()); 
    	
    	
		cName.clear();
		cNRC.clear();
		cPhNo1.clear();
		cPhNo2.clear();
		cEmail.clear();
		reserveList.getItems().clear();
		
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Customer has been added ");
        alert.showAndWait();
        
		
	    //inserting into db
		sqlInsert.insertCinfo(cL.getcName(), cL.getNRC(), cL.getcPhNo1(), cL.getcPhNo2(), cL.getcEmail());
		sqlInsert.insertPopUpValues(cL.getExtraBed(), cL.getPersonPerRoom());
		sqlInsert.insertRoominfo(cL.getRoomNo(),cL.getDateIn(), cL.getDateOut());
		
}
	    @FXML public void addRoom(ActionEvent event) throws IOException {
	    	Parent home_page_parent = FXMLLoader.load(getClass().getResource("Rooms.fxml") );
			Scene home_page_scene = new Scene (home_page_parent);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();

	    }
	    @FXML public void cancelRoom(ActionEvent event) {
	    	reserveList.getItems().remove(reserveList.getSelectionModel().getSelectedItem());
	    	Reservedata.remove(reserveList.getSelectionModel().getSelectedIndex());
	    }
    //********************************************************************//

	    
	    
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			loadData();
			setCellTable2();
		}
		
		//**********************Other Methods*******************//
		public void setCellTable2() {
			
			ReserveColRoomNo.setCellValueFactory(new PropertyValueFactory<>("RoomNo"));
			ReserveColRoomType.setCellValueFactory(new PropertyValueFactory<>("RoomType"));
			ReserveColCost.setCellValueFactory(new PropertyValueFactory<>("Cost"));
			ReserveColExtraBed.setCellValueFactory(new PropertyValueFactory<>("ExtraBed"));
			ReserveColBedValue.setCellValueFactory(new PropertyValueFactory<>("BedValue"));
			ReserveColPeople.setCellValueFactory(new PropertyValueFactory<>("NoOfPeople"));
			ReserveColCinDate.setCellValueFactory(new PropertyValueFactory<>("DateIn"));
			ReserveColCoutDate.setCellValueFactory(new PropertyValueFactory<>("DateOut"));
			ReserveColTotal.setCellValueFactory(new PropertyValueFactory<>("TotalCharges"));
		}
		public void loadData() {
			reserveList.getItems().clear();
			reserveList.getItems().addAll(Reservedata);			   	
			}
		public void getAllValues() {
			
		}
		
		//*******************************************************//
		
	}



