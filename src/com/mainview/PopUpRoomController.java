package com.mainview;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
import javafx.stage.Stage;

public class PopUpRoomController implements Initializable{
	
	
	
	//************* Other Variables********//
	    @FXML private Spinner<Integer> extraBedSpinner;
	    @FXML private Spinner<Integer> PersonSpinner;
	    @FXML private Button cancel;
	    @FXML private Button add;
	//******************************************//
	    
	    
	    
    
    //******************** Action Event ***********************//
	    @FXML public void popUpCancel(ActionEvent event) {
	    	   Stage stage = (Stage) cancel.getScene().getWindow();
	  	       stage.close();
	    }
	    @FXML public void popUpadd(ActionEvent event) throws SQLException, IOException {
       	
	    SQLinsert sqlINSERT = new SQLinsert();
    	CustomerTable cL = new CustomerTable(null, null, null, null, null, 0, 0, 0, null, null, null, null, false);
	
    	
    	cL.setExtraBed(extraBedSpinner.getValue());
    	cL.setPersonPerRoom(PersonSpinner.getValue());
    	
    	
    	addSpinnerValues();
    	
    	
    	
    	//************************ Inserting into DB **********************//
    	sqlINSERT.insertPopUpValues(cL.getExtraBed(), cL.getPersonPerRoom());
 	    Stage stage = (Stage) add.getScene().getWindow();
	    stage.close();
    	//*****************************************************************//
	    
	    
	    
	    
	    //********************* Change Scene**********************//
	    Parent p = FXMLLoader.load(getClass().getResource("Reserve.fxml"));
	    Scene pop_up_scene = new Scene (p);
	    Main.primaryStage.setScene(pop_up_scene);
		//**********************************************************//
    }
	    
   //*********************************************************//	
    
	    
    
	  
		@Override
		public void initialize(URL location, ResourceBundle resources) {
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
		 public void addSpinnerValues() {
			 
			 System.out.println(extraBedSpinner.getValue());
			 System.out.println(PersonSpinner.getValue());
			 
			 ReserveController.Reservedata.add(new RoomTemp( extraBedSpinner.getValue(), PersonSpinner.getValue() ) ) ;
		 }
	 //*********************************************************// 
			
		
}
