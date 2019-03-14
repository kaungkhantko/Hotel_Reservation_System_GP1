
	package com.mainview;

	import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

	public class ReserveController implements Initializable {
		

	//************* Other Variables********//
	    @FXML private TextField cName;
		@FXML private TextField cNRC;
	    @FXML private TextField cPhNo1;
	    @FXML  private TextField cPhNo2;
	    @FXML private TextField cEmail;
	    @FXML private CheckBox ImmediateCheckInBtn;
  //******************************************//
	    
    int selectedIndex, rowCount;
    public  int CIDindex, RIDindex;
    public String formattedTime;
	SQLinsertcopy sqlInsert = new SQLinsertcopy();
	CustomerTable cL = new CustomerTable();
	int checkInStatus = 0;
	int checkOutStatus = 0;

    //************ Table Column Variables ************//
	    @FXML private TableView<RoomTemp> reserveList;
	    @FXML private TableColumn<?, ?> ReserveColRoomNo;
	    @FXML private TableColumn<?, ?> ReserveColRoomType;
	    @FXML private TableColumn<?, ?> ReserveColCost;
	    @FXML private TableColumn<?, ?> ReserveColExtraBed;
	    @FXML private TableColumn<?, ?> ReserveColBedValue;
	    @FXML private TableColumn<?, ?> ReserveColPeople;
	    @FXML private TableColumn<?, ?> ReserveColCinDate;
	    @FXML private TableColumn<?, ?> ReserveColCoutDate;
	    @FXML private TableColumn<?, ?> ReserveColTotal;
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
	
		CustomerTable cL = new CustomerTable();
		
		cL.setcName(cName.getText());
		cL.setcNRC(cNRC.getText());
		cL.setcPhNo1(cPhNo1.getText());
		cL.setcPhNo2(cPhNo2.getText());
		cL.setcEmail(cEmail.getText());
		
    	System.out.println(cL.getcName());
    	System.out.println(cL.getNRC());
    	System.out.println(cL.getcPhNo1());
    	System.out.println(cL.getcPhNo2());
    	System.out.println(cL.getcEmail());
    	
    
	   	 Alert alert = new Alert(AlertType.INFORMATION);
	     alert.setTitle("Information Dialog");
	     alert.setHeaderText(null);
	     alert.setContentText("Customer has been added ");
	     alert.showAndWait();
	    	
	 	sqlInsert.insertCInfo(cL.getcName(), cL.getNRC(), cL.getcPhNo1(), cL.getcPhNo2(), cL.getcEmail() );
    	getAllRooms();
    	 
		cName.clear();
		cNRC.clear();
		cPhNo1.clear();
		cPhNo2.clear();
		cEmail.clear();
		reserveList.getItems().clear();
		Reservedata.clear();
		ImmediateCheckInBtn.setSelected(false);
		
		
}
	    @FXML public void addRoom(ActionEvent event) throws IOException {
	    	Parent home_page_parent = FXMLLoader.load(getClass().getResource("Rooms.fxml") );
			Scene home_page_scene = new Scene (home_page_parent);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();

	    }
	    @FXML public void cancelRoom(ActionEvent event) {
	    	selectedIndex = reserveList.getSelectionModel().getSelectedIndex();
	    	System.out.println(selectedIndex);
	    	reserveList.getItems().remove(reserveList.getSelectionModel().getSelectedItem());
	    	if (selectedIndex >= 0)
	    		Reservedata.remove(selectedIndex);
	    	if (selectedIndex < 0)
	    		Reservedata.clear();
	    }
    //********************************************************************//

	    
	    
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			
			System.out.println("Reserve Initializable");
			reserveList.setEditable(false);
			loadRoomData();
			setReserveCellTable();
			rowCount = reserveList.getItems().size(); // size = 8;
			System.out.println("Row Count: " + rowCount);
		
			
			getCurrentTime();
			loadCID();
			//loadRID();
	}		

			
		//**********************Other Methods*******************//
		public void setReserveCellTable() {
			
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
		public void loadRoomData() {
			reserveList.getItems().clear();
			reserveList.getItems().addAll(Reservedata);			   	
			}
		
	    public void loadCID() {
	    	
	    	String CID_query = "SELECT CustomerID " + 
	    					   " FROM  Customer " + 
	    					   " WHERE CustomerID = (SELECT MAX(CustomerID) FROM Customer);";
	    	
	    	try(Connection conn = SqliteConnection.Connector();
	    		PreparedStatement pstmt = conn.prepareStatement(CID_query);
	    		ResultSet rs = pstmt.executeQuery();) {
	    		
	    		CIDindex = rs.getInt("CustomerID");
	    		System.out.println("CustomerID" + CIDindex);
	    		
	    	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				
			}
	    }
	    public void loadRID() {
	    	
	    	String RID_query = " SELECT ReservationID FROM Reservation_Details WHERE ReservationID = (SELECT MAX(ReservationID)  FROM Reservation_Details); ";
		
	    	
	    	try(Connection conn = SqliteConnection.Connector();
		    		PreparedStatement pstmt = conn.prepareStatement(RID_query);
		    		ResultSet rs = pstmt.executeQuery();) {
		    		
		    		RIDindex = rs.getInt("ReservationID");
		    		System.out.println(RIDindex);
		    		
		    	} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		    	}
	    }	
	    public  void getCurrentTime() {
	        Calendar cal = Calendar.getInstance();
	        Date date=cal.getTime();
	        DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd hh:mm a");
	        formattedTime = dateFormat.format(date);
	        System.out.println("Current time : "+ formattedTime);
	    }
	
	    public void getAllRooms() throws SQLException {
	    	
	    	if(ImmediateCheckInBtn.isSelected()==true)
	    		checkInStatus = 1;
	    	else
	    		checkInStatus = 0;
	    	
	    	if(rowCount > 1) {
	    		
    			System.out.println("Row Count:" + rowCount);
    			
    			//insertRoomInfo2(); 
    			
    			for(int a =0; a <= rowCount-1; a++) { // a < 1
    				
    				RoomTemp rt2 = reserveList.getItems().get(a);
        			
        			Reservedata.get(a).setRoomNo(rt2.getRoomNo());
        			Reservedata.get(a).setExtraBed(rt2.getExtraBed());
        			Reservedata.get(a).setDateIn(rt2.getDateIn());
        			Reservedata.get(a).setDateOut(rt2.getDateOut());
        			Reservedata.get(a).setNoOfPeople(rt2.getNoOfPeople());
        			
    				System.out.println(Reservedata.get(a).getRoomNo());
    				System.out.println(Reservedata.get(a).getExtraBed());
    				System.out.println(Reservedata.get(a).getDateIn());
    				System.out.println(Reservedata.get(a).getDateOut());
    				System.out.println(Reservedata.get(a).getNoOfPeople());
    				System.out.println();
    				
    				sqlInsert.insertCID(CIDindex, formattedTime);
        		}
    			
    			insertRoomInfo2();
        
	    	} else if ( (rowCount -1) == 0)  { 
	    				
	    			System.out.println("Row Count:" + rowCount);
	    			
	    			RoomTemp rt2 = reserveList.getItems().get(rowCount-1);
	    			
	    			Reservedata.get(0).setRoomNo(rt2.getRoomNo());
	    			Reservedata.get(0).setExtraBed(rt2.getExtraBed());
	    			Reservedata.get(0).setDateIn(rt2.getDateIn());
	    			Reservedata.get(0).setDateOut(rt2.getDateOut());
	    			Reservedata.get(0).setNoOfPeople(rt2.getNoOfPeople());
	    			
	    			System.out.println(Reservedata.get(rowCount -1 ).getRoomNo());
    				System.out.println(Reservedata.get(rowCount -1).getExtraBed());
    				System.out.println(Reservedata.get(rowCount -1).getDateIn());
    				System.out.println(Reservedata.get(rowCount -1).getDateOut());
    				System.out.println(Reservedata.get(rowCount -1).getNoOfPeople());
	    	    	
	    	    	sqlInsert.insertRoomInfo( rt2.getExtraBed(), rt2.getNoOfPeople(), rt2.getRoomNo(), rt2.getDateIn(), rt2.getDateOut(), checkInStatus, checkOutStatus);
	    	    	sqlInsert.insertCID(CIDindex, formattedTime);
	  
	    }
	}
	   
		public void insertRoomInfo2() {
			
			  String insertRoomQuery = "INSERT INTO Reserved_Room(RoomNo, ExtraBeds, CheckInDate, CheckOutDate, NoOfPeople ) VALUES(?,?,?,?,?)";
		        
		        try (Connection conn =  SqliteConnection.Connector();
		             PreparedStatement pstmt2 = conn.prepareStatement(insertRoomQuery);)
		            {	
		        		for(int a =0; a <= rowCount-1; a++) {
		        			
		        			pstmt2.setInt(1, Reservedata.get(a).getRoomNo());
		        			pstmt2.setInt(2, Reservedata.get(a).getExtraBed());
		        			pstmt2.setString(3, Reservedata.get(a).getDateIn());
		        			pstmt2.setString(4, Reservedata.get(a).getDateOut());
		        			pstmt2.setInt(5, Reservedata.get(a).getNoOfPeople());
		        			pstmt2.execute();
		        		
		        		}
		                
		            } catch (SQLException e) { System.out.println(e.getMessage());}
		}
	    
	    //*******************************************************//
}
	

