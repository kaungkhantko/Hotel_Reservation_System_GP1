package com.mainview;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;


public class PopUpRoomController implements Initializable{
	
	
	
	//************* Other Variables********//
	    @FXML private Spinner<Integer> extraBedSpinner;
	    @FXML private Spinner<Integer> PersonSpinner;
	    @FXML private Button cancel;
	    @FXML private Button add;
	    static boolean flag = false;
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
	
    	
    	CustomerTable.setExtraBed(extraBedSpinner.getValue());
    	CustomerTable.setPersonPerRoom(PersonSpinner.getValue());
    	
    	addTotalCharges();
    	addAllValues();
    	
    	
    	//************************ Inserting into DB **********************//
    	//sqlINSERT.insertPopUpValues(CustomerTable.getExtraBed(), CustomerTable.getPersonPerRoom());
 	    add.getScene().getWindow().hide();
    	//*****************************************************************//
	    
	    
	    
	    
	    
	    //********************* Change Scene **********************//
		   Parent p = FXMLLoader.load(getClass().getResource("Reserve.fxml") );
		   Scene pop_up_scene= new Scene (p);
		   Main.primaryStage.setScene(pop_up_scene);
		
    }
    //******************** Action Events ***********************//    
	    
	    
	    
	    
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			
			addSpinnerValues();
			
		}
		
		
		
		
		//********************* Getter & Setter Methods***************//
		 public  Spinner<Integer> getExtraBedSpinner() {
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
		 private void addSpinnerValues() {
	    extraBedSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 3));
	    PersonSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5));
	      }
		 public void addTotalCharges() {
			 
			 totalCostForExtraBed = extraBedSpinner.getValue() * 10000;
			 totalCharges = totalCostForExtraBed + Room.getStaticCost();
			 
		 }
		 public void addAllValues() {
			ReserveController.Reservedata.add(new RoomTemp(Room.getStaticRoomNo(), Room.getStaticRoomType(), Room.getStaticCost(), extraBedSpinner.getValue(),PersonSpinner.getValue(),
					ExtraBedCost, RoomsController.stringDateIn, RoomsController.stringDateOut, totalCharges ));
			 
		 }
   //*********************************************************// 

		 
}
