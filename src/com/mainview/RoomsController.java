package com.mainview;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RoomsController implements Initializable {
			
	
	
	//************* Other Variables********//
		@FXML private ComboBox<String> RoomTypeCombo;
		@FXML private DatePicker DateInTBox;
	    @FXML private DatePicker DateOutTBox;
	    @FXML private Button resetBtn;

   //******************************************//
    
	    
	    
    
    //***********Table Column Variables*****************//
	    @FXML private TableView<Room> roomTable;
	    @FXML private TableColumn<?, ?> columnNo;
		@FXML private TableColumn<?, ?> columnRoomNo;
	    @FXML private TableColumn<?, ?> columnRoomType;
	    @FXML private TableColumn<?, ?> columnCost;
	    @FXML private TableColumn<?, ?> columnMaximum;
	    @FXML private TableColumn<?, ?> columnAvailability;
	    
    //***************************************************//
	 
 
    @FXML private ObservableList<Room> data;
    
    
    
    
	    //*********************** Variables for Data Base******************//
	    public static int i;
	    int maximum;
	    String availability="";
	    String sql_all = "Select Room.RoomNo, Room_Type.RoomType, Room_Type.Cost, Room_Type.NumberOfBeds, Room_Type.ExtraBeds, Reserved_Room.CheckInStatus, Reserved_Room.CheckOutStatus, Reserved_Room.CheckInDate, Reserved_Room.CheckOutDate\n" + 
				"From Room_Type " + 
				"INNER JOIN Room " + 
				"On Room.RoomTypeID = Room_Type.RoomTypeID " + 
				"INNER JOIN Reserved_Room " + 
				"On Reserved_Room.RoomNo = Room.RoomNo " +
				"WHERE Reserved_Room.CheckOutStatus=0 "; 
	    //****************************************************************//
	    
		
		
		
	
    //*********************** Variables for Date ****************************//
		final DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
		final DateTimeFormatter NSAformatter= DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		LocalDate roomDateOut, roomDateIn;
		static String stringDateIn, stringDateOut;
		String NSAstringDateIn, NSAstringDateOut;
		
		LocalDate dateIn, dateOut;
		String chosenType;
		Room r = new Room();
    //********************************************************************//    
    

	
  
	 
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
    
    
    
    
    //******************** Action Events ************************//
    	@FXML public void Search(ActionEvent event) throws SQLException {
		   
	    //Converting LocalDate   to a user specific format - dd/MM/yyyy
	    
    	   stringDateIn=(DateInTBox.getValue()).format(formatter); 
    	   stringDateOut=(DateOutTBox.getValue()).format(formatter);
    	   
    	   NSAstringDateIn = (DateInTBox.getValue().format(NSAformatter));
    	   NSAstringDateOut = (DateOutTBox.getValue().format(NSAformatter));
    	   
    	   
    	   
    	   dateIn = LocalDate.parse(stringDateIn, formatter);
    	   dateOut =  LocalDate.parse(stringDateOut, formatter);
    	   
		   i=1;
		   roomTable.getItems().clear();
		   
		   setCellTable();
		   data = FXCollections.observableArrayList();
		   loadData(sql_all);
	   
    }
    //*********************************************************//	
 
    
    
    	
      //*********************Initialize**********************//
    	@Override public void initialize(URL location, ResourceBundle resources) {
		
        DateInTBox.setValue(LocalDate.now());
    	DateOutTBox.setValue(LocalDate.now());
		fillComboBox();
		setCellTable();
		
		
		
		
		//Pop up window appearing
		roomTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
		@Override public void handle(MouseEvent event) {
			
			if(event.getClickCount() >=2) { 
				
		    	 FXMLLoader Loader = new FXMLLoader();
				 Loader.setLocation(getClass().getResource("PopUpRoom.fxml"));
			
				 try { Loader.load();} 
				 catch(IOException ex) { Logger.getLogger(CustomerListController.class.getName()).log(Level.SEVERE,null,ex);}
				 Parent p = Loader.getRoot();
				 Stage stage = new Stage();
				 stage.setScene(new Scene(p));
				 stage.setTitle("Extra Information");
				 stage.show();  
				
				 try { fetchData();} 
			     catch (IOException e) { e.printStackTrace();}	
				
		    	 System.out.println("Fetched selected item");
		    	

				}	
			
			}
	  });
 }
	
	
	
	
	  //********************** Other Methods *******************//
	    private void setCellTable() {
			
			columnNo.setCellValueFactory(new PropertyValueFactory<>("rowNumber"));
			columnRoomNo.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
			columnRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
			columnCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
			columnMaximum.setCellValueFactory(new PropertyValueFactory<>("maximum"));
			columnAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
			
		}
		private void loadData(String sql_input) {
			
			String sql = sql_input;
			chosenType = RoomTypeCombo.getValue();
			
			if (chosenType != "Any")
				sql = sql.concat("AND RoomType = "+ "'" + chosenType + "'");
			sql = sql.concat("GROUP BY Room.RoomNo\n ORDER BY Reserved_Room.CheckOutDate DESC ");

			
		   	try(Connection c = SqliteConnection.Connector();
		   	PreparedStatement preparedStatement = c.prepareStatement(sql);
		   	ResultSet rs = preparedStatement.executeQuery();)
		   	{
		   		while(rs.next())
		   	    {
		   			maximum = rs.getInt("NumberOfBeds") + rs.getInt("ExtraBeds");
		   			roomDateOut = LocalDate.parse(rs.getString("CheckOutDate"), formatter);
					checkAvailability();
		   			data.add(new Room(i,
		   					rs.getInt("RoomNo"),
		   					rs.getString("RoomType"),
		   					rs.getInt("Cost"),
		   					maximum,
		   					availability));
		   			i++;
		   	    }
		   	}
		   	        
		   	catch (SQLException ex){
		   	ex.printStackTrace();
			} 
		   	     roomTable.setItems(data);
		}
	    public void fillComboBox() {
	    	RoomTypeCombo.getItems().add("Any");
	    	    String sql  = "SELECT RoomType from Room_Type";
		   	       try(Connection c = SqliteConnection.Connector();
		   	    	   PreparedStatement preparedStatement = c.prepareStatement(sql);
		   	    	   ResultSet rs = preparedStatement.executeQuery();)
		   	       {
		   	           while(rs.next())
		   	        	   
		   	           {
		   	        	 String s = rs.getString("RoomType");
		   	        	 RoomTypeCombo.getItems().add(s);
		   	           }
		   	       }
		   	        
		   	       catch (SQLException ex){
		   	    	   	 ex.printStackTrace();
			       }
		   	    RoomTypeCombo.setValue("Any");
	    }
	    public void fetchData() throws IOException {
	    	
	         r.staticCost = roomTable.getSelectionModel().getSelectedItem().cost;
	         r.staticRoomNo = roomTable.getSelectionModel().getSelectedItem().roomNo;
	         r.staticRoomType = roomTable.getSelectionModel().getSelectedItem().roomType;
	         
	    	if(roomTable.getSelectionModel().getSelectedItem() != null) {
		    	
	    	} else { System.out.println("Fetched Null"); }

	   }
	    public void checkAvailability() {
	    	
	    	if(roomDateOut.isBefore(dateOut) && roomDateOut.isAfter(dateIn)) {
				availability = "Not Available";
			}
			else
				availability = "Available";
	    }
	    
	    public void reset() {
	    	i=1;
	    	roomTable.getItems().clear();
	    	
	        DateInTBox.setValue(LocalDate.now());
	    	DateOutTBox.setValue(LocalDate.now());
	    	
	    	RoomTypeCombo.setValue("Any");
	    	
	    	stringDateIn=(DateInTBox.getValue()).format(formatter); 
	    	stringDateOut=(DateOutTBox.getValue()).format(formatter);
	    	   
	    	dateIn = LocalDate.parse(stringDateIn, formatter);
	    	dateOut =  LocalDate.parse(stringDateOut, formatter);
	    	loadData(sql_all);
	    }

	    //*********************************************************//  
	
	   
	
}

