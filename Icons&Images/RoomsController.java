package com.mainview;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RoomsController extends ReserveController implements Initializable {
			
	
			
	
	
	//************* Other Variables********//
		@FXML private ComboBox<String> RoomTypeCombo;
		@FXML private DatePicker DateInTBox;
	    @FXML private DatePicker DateOutTBox;
	    @FXML private TextField loelyktok;
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
    
	    
	    
    public static int i;
    int maximum;
    LocalDate roomDateOut;
	LocalDate roomDateIn;
	final DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
	String stringDateIn;
	String stringDateOut;
	LocalDate dateIn;
	LocalDate dateOut;
	String chosenType;
    
    
    
    @FXML private ObservableList<Room> data;
    @FXML private ObservableList<Room> Roomdata;
    @FXML private ObservableList<Room> Fetchdata;
    
    
   
    String availability="";
    String sql_all = "Select Room_Type.RoomTypeID, Room.RoomNo, Room_Type.RoomType, Room_Type.Cost, Room_Type.NumberOfBeds, Room_Type.ExtraBeds, Reserved_Room.CheckInStatus, Reserved_Room.CheckOutStatus, Reserved_Room.CheckInDate, Reserved_Room.CheckOutDate\n" + 
			"From Room_Type " + 
			"INNER JOIN Room " + 
			"On Room.RoomTypeID = Room_Type.RoomTypeID " + 
			"INNER JOIN Reserved_Room " + 
			"On Reserved_Room.RoomNo = Room.RoomNo "+
			"WHERE Reserved_Room.CheckOutStatus=0 ";
	
	
  
	 
	//*************************** Show Side Bar *************************//
	    @FXML void goCustomerListPage(ActionEvent event) throws IOException {
			Parent home_page_parent = FXMLLoader.load(getClass().getResource("CustomerList.fxml") );
			Scene home_page_scene = new Scene (home_page_parent);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();
			
	    }
	    @FXML void goReservationPage(ActionEvent event) throws IOException {
	    	Parent home_page_parent = FXMLLoader.load(getClass().getResource("Reserve.fxml") );
			Scene home_page_scene = new Scene (home_page_parent);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();
	
	    }
	    @FXML void goRoomsPage(ActionEvent event) throws IOException {
	    	Parent home_page_parent = FXMLLoader.load(getClass().getResource("Rooms.fxml") );
			Scene home_page_scene = new Scene (home_page_parent);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();
	
	    }
	    @FXML void goSetting(ActionEvent event) throws IOException {
			Parent home_page_parent = FXMLLoader.load(getClass().getResource("AccountSetting.fxml") );
			Scene home_page_scene = new Scene (home_page_parent);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();
		
	    }
	    @FXML void goSignOut(ActionEvent event) throws IOException {
		Parent home_page_parent = FXMLLoader.load(getClass().getResource("Login.fxml") );
		Scene home_page_scene = new Scene (home_page_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(home_page_scene);
		app_stage.show();
	
    }
    //*********************************************************************//
    
	    
	    
    
    
    
    //******************** Action Events ************************//
	    @FXML
	    void Search(ActionEvent event) throws SQLException, ParseException {
		   
		   	stringDateIn=(DateInTBox.getValue()).format(formatter); 
			stringDateOut=(DateOutTBox.getValue()).format(formatter);
			dateIn = LocalDate.parse(stringDateIn, formatter);
			dateOut =  LocalDate.parse(stringDateOut, formatter);		
		    i=1;
		    roomTable.getItems().clear();
		    setCellTable();
		    data = FXCollections.observableArrayList();
		    loadData(sql_all);
		   
	    }
    //*********************************************************//	
 
    
    
    
	@Override public void initialize(URL location, ResourceBundle resources) {
		DateInTBox.setValue(LocalDate.now());
		DateOutTBox.setValue(LocalDate.now());
		fillComboBox();
		setCellTable();
		
		Roomdata = FXCollections.observableArrayList();
		Fetchdata = FXCollections.observableArrayList();
		
		//Pop up window appearing
		roomTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
		@Override public void handle(MouseEvent event) {
			
			if(event.getClickCount() >=2) { 
				try {
					fetchData();
				} catch (IOException e) {
					e.printStackTrace();
				}	
		    	System.out.println("Fetched selected item");
				FXMLLoader Loader = new FXMLLoader();
				Loader.setLocation(getClass().getResource("PopUpRoom.fxml"));
			
			try {
				Loader.load();
				
			} catch(IOException ex) {
				Logger.getLogger(CustomerListController.class.getName()).log(Level.SEVERE,null,ex);
				
			}
			
			Parent p = Loader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene(p));
			stage.show(); 
		
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
			if (chosenType != null)
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
	    }
	    public void fetchData() throws IOException {
	    	
	    	RoomsController roomsObj = new RoomsController();
	        Room r = roomTable.getSelectionModel().getSelectedItem();	
	    	if(roomTable.getSelectionModel().getSelectedItem() != null) {
	    		
	    		loelyktok.setText(r.getRoomType());
	    		r.testing = r.getRoomType();
	    		System.out.println(r.testing);
	    		
	    	} else {
	    		System.out.println("Fetched Null");
	    	}
	    }
	    
	    public void checkAvailability() {
	    	if(roomDateOut.isBefore(dateOut) && roomDateOut.isAfter(dateIn)) {
				availability = "Not Available";
			}
			else
				availability = "Available";
	    }
	 //*********************************************************//  
	
	
}

