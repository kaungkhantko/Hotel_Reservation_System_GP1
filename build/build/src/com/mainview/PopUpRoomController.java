package com.mainview;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopUpRoomController implements Initializable{
	
	
	
	//************* Other Variables********//
	    @FXML private Spinner<Integer> extraBedSpinner;
	    @FXML private Spinner<Integer> PersonSpinner;
	    @FXML private Button cancel;
	    @FXML private Button add;
	//******************************************//
	    
	    
	    
	    
	//***************Variables for Extra Bed Charges *****//
		static int totalCostForExtraBed = 0, totalCharges = 0;
	    String ExtraBedCost = "x 10,000";
	//***************************************************//
    
	    
	    
	    
    //******************** Action Events ***********************//
	    @FXML public void popUpCancel(ActionEvent event) {
	    	   Stage stage = (Stage) cancel.getScene().getWindow();
	  	       stage.close();
	    }
	    @FXML public void popUpadd(ActionEvent event) throws SQLException, IOException {
       	
	    SQLinsert sqlINSERT = new SQLinsert();
    	CustomerTable cL = new CustomerTable(0, 0);
	
    	
    	cL.setExtraBed(extraBedSpinner.getValue());
    	cL.setPersonPerRoom(PersonSpinner.getValue());
    	
    	System.out.println();
    	addTotalCharges();
    	addAllValues();
    	
    	
    	
    	//************************ Inserting into DB **********************//
    	sqlINSERT.insertPopUpValues(cL.getExtraBed(), cL.getPersonPerRoom());
 	   add.getScene().getWindow().hide();
	    
    	//*****************************************************************//
	    
	    
	   // extraBedSpinner.getScene().getWindow().hide();
	    
	    
	    
	    //********************* Change Scene **********************//
	    
	    
	    
	    
	    //**************** Method 1*****************//
	    
	/*	Stage stage = new Stage();
	    FXMLLoader Loader = new FXMLLoader();
		Loader.setLocation(getClass().getResource("Reserve.fxml"));
	    
	    Parent reserve_page = Loader.load();
		Scene reserve_scene= new Scene (reserve_page);
		
		ReserveController controller = Loader.getController();
		
		stage.setScene(reserve_scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	*/
		
	    	//**************** Method 2 *****************//
//    	Parent reserve_page = FXMLLoader.load(getClass().getResource("Reserve.fxml") );
//		Scene reserve_page_scene = new Scene (reserve_page);
//		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//		app_stage.setScene(reserve_page_scene);
//		//app_stage.initModality(Modality.APPLICATION_MODAL);
//		app_stage.show();
////		
//		
		
	    		//**************** Method 3*****************//
		   
		   Parent p = FXMLLoader.load(getClass().getResource("Reserve.fxml") );
		   Scene pop_up_scene= new Scene (p);
		   Main.primaryStage.setScene(pop_up_scene);
		 	    
	   
	
	
		//**********************************************************//
		
		
		
		
    }
	//**********************************************************//
	  
	    
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			
			System.out.println("Pop up Initializable");
			addSpinner();
			
		}
		
		
		//********************* Getter & Setter Methods***************//
		 public Spinner<Integer> getExtraBedSpinner() {
			return extraBedSpinner;
		}
		 public void setExtraBedSpinner(Spinner<Integer> extraBedSpinner) {
			this.extraBedSpinner = extraBedSpinner;
		}
		
		 public Spinner<Integer> getPersonSpinner() {
			return PersonSpinner;
		}
		 public void setPersonSpinner(Spinner<Integer> personSpinner) {
			this.PersonSpinner = personSpinner;
		}
	//********************* ************************s***************//
		 
		 
		 
		 
    //********************** Other Methods *******************//  
		 private void addSpinner() {
		//adding spinner value
	    extraBedSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 3));
	    PersonSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5));
	      }
		 public void addTotalCharges() {
			 
			 totalCostForExtraBed = extraBedSpinner.getValue() * 10000;
			 totalCharges = totalCostForExtraBed + Room.getCost();
			 
		 }
		 public void addAllValues() {
			 
			ReserveController.Reservedata.add(new RoomTemp(Room.getRoomNo(), Room.getRoomType(), Room.getCost(), extraBedSpinner.getValue(),
					ExtraBedCost, RoomsController.NSAstringDateIn, RoomsController.NSAstringDateOut, totalCharges ));
			//	System.out.println("Inside popup controller : " + ReserveController.Reservedata.isEmpty());
			 
		 }
   //*********************************************************// 

		 
}
