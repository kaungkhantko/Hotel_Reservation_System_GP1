package com.mainview;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class CustomerListController implements Initializable {
	
	String sql_all = "SELECT *"
          		+ " FROM Customer"
          		+ " INNER JOIN Reservation_Details"
          		+ " ON Customer.CustomerID = Reservation_Details.CustomerID"
          		+ " INNER JOIN Reserved_Room"
          		+ " ON Reservation_Details.ReservationID = Reserved_Room.ReservationID";
	
	//************* Other Variables********//
		@FXML private TextField NameTBox;
	    @FXML private TextField PhNoTBox;
	    @FXML private TextField RoomNoTBox;
    
	    @FXML private CheckBox check_BookedList;
	    @FXML private CheckBox check_OldList;
	    @FXML private CheckBox check_CurrentList;
   //********************************************//

    
    
    //*************Table Column Variables *************//
		@FXML private TableView<Customer> list;
	    @FXML private TableColumn<?, ?> ColumnCNo;
	    @FXML private TableColumn<?, ?> ColumnRoomNo;
	    @FXML private TableColumn<?, ?> ColumnCname;
	    @FXML private TableColumn<?, ?> ColumnPhNo1;
	    @FXML private TableColumn<?, ?> ColumnReservationTime;
	    @FXML private TableColumn<?, ?> ColumnCheckInD;
	    @FXML private TableColumn<?, ?> ColumnCheckOutD;
	    @FXML private TableColumn<?, ?> ColumnStatus;
	    @FXML private TableColumn<?, ?> ColumnActualCheckOutD;
    //*************************************************//
    
	int i=1;
	String name;
    String phNo;
	String roomNo;
	String dateIn, dateOut;
	String status;
	Customer selectedCustomer;
	String check;
	
	ContextMenu contextMenu = new ContextMenu();
	MenuItem checkIn = new MenuItem("Check In");
	MenuItem checkOut = new MenuItem("Check Out");
	
	public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    
    @FXML
    private ObservableList<Customer> Customerdata;
    
    
    
    
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
	    @FXML  private void goSetting(ActionEvent event) throws IOException {
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
    

	    
	    
	 //******************** Action Event ************************//
    
	    @FXML void Search(ActionEvent event) {
	    	
	    	i = 1;
	    	list.getItems().clear();
	    	setCellTable();
			Customerdata = FXCollections.observableArrayList();
			
			if (check_OldList.isSelected()==true)
				addOldList();
			if (check_CurrentList.isSelected()==true)
				addCurrentList();
			if (check_BookedList.isSelected()==true)
				addBookedList();
    }
	    @FXML void Reset(ActionEvent event) {
	    	Reset();
	    }
	 //********************************************************************//
	    
	    
 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Reset();
		
		contextMenu.getItems().addAll(checkIn, checkOut);
		contextMenu.setAutoHide(true);
		
		
		list.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() 
		{
			@Override
			public void handle(MouseEvent mouseClick)
			{
				if(mouseClick.getButton() == MouseButton.SECONDARY)
					list.setContextMenu(contextMenu);
			}
		});
		
		checkIn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e) 
			{
				selectedCustomer = list.getSelectionModel().getSelectedItem();
				check = "Check In";
				Update();
			}
		});
		
		checkOut.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e) 
			{
				selectedCustomer = list.getSelectionModel().getSelectedItem();
				check = "Check Out";
				Update();
			}
		});
	}
	
	public void Update() {
		String sql = "UPDATE Reserved_Room ";
		if(check == "Check In")
			sql = sql.concat("SET CheckInStatus = 1 ");
		if(check == "Check Out")
			sql = sql.concat("SET CheckOutStatus = 1 ");
		sql = sql.concat("WHERE RoomNo = " + selectedCustomer.getRoomNo() + " AND CheckInDate = ? AND CheckOutDate = ?");
		PreparedStatement preparedStatement = null;
		Connection c = null;
		
		try
		{
			c = SqliteConnection.Connector();
			preparedStatement = c.prepareStatement(sql);
			//System.out.println(selectedCustomer.roomNo.toString());
			System.out.println(selectedCustomer.dateInTemp.format(formatter));
			System.out.println(selectedCustomer.dateOutTemp.format(formatter));
			//preparedStatement.setString(1, selectedCustomer.roomNo.toString());
			preparedStatement.setString(1, selectedCustomer.dateInTemp.format(formatter));
			preparedStatement.setString(2, selectedCustomer.dateOutTemp.format(formatter));
			preparedStatement.execute();
		}      
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		finally 
		{
			if(preparedStatement!=null)
				try {
					c.close();
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
}

	//********************** Other Methods *******************//  
	    private void setCellTable() {
			
	    	ColumnCNo.setCellValueFactory(new PropertyValueFactory<>("customerNo"));
			ColumnRoomNo.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
			ColumnReservationTime.setCellValueFactory(new PropertyValueFactory<>("reservedTime"));
			ColumnCname.setCellValueFactory(new PropertyValueFactory<>("name"));
			ColumnPhNo1.setCellValueFactory(new PropertyValueFactory<>("phoneNumber1"));
			ColumnCheckInD.setCellValueFactory(new PropertyValueFactory<>("dateIn"));
			ColumnCheckOutD.setCellValueFactory(new PropertyValueFactory<>("dateOut"));
			ColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
			ColumnActualCheckOutD.setCellValueFactory(new PropertyValueFactory<>("actualDateOut"));
			
		}
		
private void loadData(String sql_input) {
			
			
			String sql = sql_input;
			name = "'%" + NameTBox.getText() + "%'";
			roomNo = "'%" + RoomNoTBox.getText() + "%'";
			phNo = "'%" + PhNoTBox.getText() + "%'";
			
			if (NameTBox.getText().trim().isEmpty()) {}
			else
				sql = sql.concat(" AND CustomerName LIKE " + name);
			
			if (PhNoTBox.getText().trim().isEmpty()) {}
			else
				sql = sql.concat(" AND PhoneNumber1 LIKE " + phNo);
		
			if (RoomNoTBox.getText().trim().isEmpty()) {}
			else
				sql = sql.concat(" AND RoomNo = " + roomNo);
			
			sql = sql.concat(" ORDER BY CustomerName");
			
		   	try(Connection c = SqliteConnection.Connector();
		   	PreparedStatement preparedStatement = c.prepareStatement(sql);
		   	ResultSet rs = preparedStatement.executeQuery();)
		   	{
		   		while(rs.next())
		   	    {
		   			if(rs.getInt("CheckInStatus") == 1 && rs.getInt("CheckOutStatus") == 1)
		   				status = "Checked Out";
		   			if(rs.getInt("CheckInStatus") == 1 && rs.getInt("CheckOutStatus") == 0)
		   				status = "Checked In";
		   			if(rs.getInt("CheckInStatus") == 0 && rs.getInt("CheckOutStatus") == 0)
		   				status = "Booked";
		   	    Customerdata.add(new Customer(i,
		   	    rs.getInt("RoomNo"),
		   	    rs.getString("ReservedTime"),
		   	    rs.getString("CustomerName"),
		   	    rs.getInt("PhoneNumber1"),
		   	    rs.getString("CheckInDate"),
		   	    rs.getString("CheckOutDate"),
		   	    rs.getString("ActualCheckOutDate"),
		   	    status));
		   	    i++;
		   	    }
		   	}
		   	        
		   	catch (SQLException ex){
		   	ex.printStackTrace();
			}
		   	     list.setItems(Customerdata);
		
		}
		
		private void addOldList() {
			String sql_oldList = sql_all.concat(" WHERE CheckInStatus = TRUE AND CheckOutStatus = TRUE");
			loadData(sql_oldList);
		}
		
		private void addBookedList() {
			String sql_bookedList = sql_all.concat(" WHERE CheckInStatus = FALSE AND CheckOutStatus = FALSE");
			loadData(sql_bookedList);
		}
		
		private void addCurrentList() {
			String sql_currentList = sql_all.concat(" WHERE CheckInStatus = TRUE AND CheckOutStatus = FALSE");
			loadData(sql_currentList);
		}
		
		void Reset() {
			NameTBox.setText("");
	    	PhNoTBox.setText("");
	    	RoomNoTBox.setText("");
	    	
	    	check_OldList.setSelected(false);
	    	check_CurrentList.setSelected(false);
	    	check_BookedList.setSelected(false);
	    	
	    	i=1;
			setCellTable();
			Customerdata = FXCollections.observableArrayList();
			loadData(sql_all);	
		}
		
   //*********************************************************************//





}
