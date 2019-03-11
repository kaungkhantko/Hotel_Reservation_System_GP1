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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CustomerListKKK implements Initializable {
	String sql_all = "SELECT Reserved_Room.RoomNo, Customer.CustomerName, Customer.PhoneNumber1, Customer.NRC, "
          		+ " Reservation_Details.ReservedTime, Reserved_Room.CheckInDate, Reserved_Room.CheckOutDate"
          		+ " FROM Customer"
          		+ " INNER JOIN Reservation_Details"
          		+ " ON Customer.CustomerID = Reservation_Details.CustomerID"
          		+ " INNER JOIN Reserved_Room"
          		+ " ON Reservation_Details.ReservationID = Reserved_Room.ReservationID";
	int i;
	
	@FXML
    private TextField NameTBox;

    @FXML
    private TextField PhNoTBox;

    @FXML
    private TextField RoomNoTBox;
    
	@FXML
    private TableView<Customer> list;
    
    @FXML
    private TableColumn<?, ?> ColumnCNo;

    @FXML
    private TableColumn<?, ?> ColumnRoomNo;

    @FXML
    private TableColumn<?, ?> ColumnCname;

    @FXML
    private TableColumn<?, ?> ColumnPhNo1;

    @FXML
    private TableColumn<?, ?> ColumnReservationTime;

    @FXML
    private TableColumn<?, ?> ColumnCheckInD;

    @FXML
    private TableColumn<?, ?> ColumnCheckOutD;
    
    @FXML
    private ObservableList<Customer> data;
    
    @FXML
    private CheckBox check_BookedList;
    
    @FXML
    private CheckBox check_OldList;
    
    @FXML
    private CheckBox check_CurrentList;
    
    String name;
	String phNo;
	String roomNo;

    @FXML
    public void Search(ActionEvent event) {
    	i = 1;
    	list.getItems().clear();
    	setCellTable();
		data = FXCollections.observableArrayList();
		if (check_OldList.isSelected()==true)
			addOldList();
		if (check_CurrentList.isSelected()==true)
			addCurrentList();
		if (check_BookedList.isSelected()==true)
			addBookedList();
    }
    
    @FXML
    void Reset(ActionEvent event) {
		setCellTable();
		data = FXCollections.observableArrayList();
		loadData(sql_all);	
    }
	
    @FXML
    void goCustomerListPage(ActionEvent event) throws IOException {
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
    void goRoomsPage(ActionEvent event) throws IOException {
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
		NameTBox.setText("");
		RoomNoTBox.setText("");
		PhNoTBox.setText("");
		i = 1;
		setCellTable();
		data = FXCollections.observableArrayList();
		loadData(sql_all);	
	}


    private void setCellTable() {
		
    	ColumnCNo.setCellValueFactory(new PropertyValueFactory<>("customerNo"));
		ColumnRoomNo.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
		ColumnReservationTime.setCellValueFactory(new PropertyValueFactory<>("reservedTime"));
		ColumnCname.setCellValueFactory(new PropertyValueFactory<>("name"));
		ColumnPhNo1.setCellValueFactory(new PropertyValueFactory<>("phoneNumber1"));
		ColumnCheckInD.setCellValueFactory(new PropertyValueFactory<>("dateIn"));
		ColumnCheckOutD.setCellValueFactory(new PropertyValueFactory<>("dateOut"));
		
	}
	
	private void loadData(String sql_input) {
		String sql = sql_input;
		name = "'" + NameTBox.getText() + "'";
		roomNo = "'" + RoomNoTBox.getText() + "'";
		phNo = "'" + PhNoTBox.getText() + "'";
		if (NameTBox.getText().trim().isEmpty()) {}
		else
			sql = sql.concat(" AND CustomerName = " + name);
		
		if (PhNoTBox.getText().trim().isEmpty()) {	
		}
		else
			sql = sql.concat(" AND PhoneNumber1 = " + phNo);
		
		if (RoomNoTBox.getText().trim().isEmpty()) {}
		else
			sql = sql.concat(" AND RoomNo = " + roomNo);
	   	try(Connection c = SqliteConnection.Connector();
	   	PreparedStatement preparedStatement = c.prepareStatement(sql);
	   	ResultSet rs = preparedStatement.executeQuery();)
	   	{
	   		while(rs.next())
	   	    {
	   	    data.add(new Customer(i,
	   	    rs.getInt("RoomNo"),
	   	    rs.getString("ReservedTime"),
	   	    rs.getString("CustomerName"),
	   	    rs.getInt("PhoneNumber1"),
	   	    rs.getString("CheckInDate"),
	   	    rs.getString("CheckOutDate")));
	   	    i++;
	   	    }
	   	}
	   	        
	   	catch (SQLException ex){
	   	ex.printStackTrace();
		} 
	   	     list.setItems(data);
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
}
