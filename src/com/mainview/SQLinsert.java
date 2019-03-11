package com.mainview;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SQLinsert{
	
	private static Connection connect() {
		
        String url = "jdbc:sqlite:Group1Project.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
 
	public void insertCinfo( String name, String NRC, String Ph1, String Ph2, String Email) throws SQLException {
		
		ResultSet resultSet = null;
		
	    String insertQuery = "INSERT INTO Customer(CustomerName,NRC,PhoneNumber1,PhoneNumber2,Email) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(insertQuery))
        {
	  	        pstmt.setString(1, name);
	  	        pstmt.setString(2, NRC);
	  	        pstmt.setString(3, Ph1);
	  	        pstmt.setString(4, Ph2);
	  	        pstmt.setString(5, Email);
	  	        
	  	        Alert alert = new Alert(AlertType.INFORMATION);
	  	        alert.setTitle("Information Dialog");
	  	        alert.setHeaderText(null);
	  	        alert.setContentText("Customer has been added ");
	  	        alert.showAndWait();
	  	        
                pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	 
	public void insertRoominfo(String dateIN, String dateOUT) throws SQLException {
		
		ResultSet resultSet = null;
	    String insertQuery = "INSERT INTO Reserved_Room(CheckInDate, CheckOutDate) VALUES(?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) 
        {
	  	        pstmt.setString(1, dateIN);
	  	        pstmt.setString(2, dateOUT);
                pstmt.executeUpdate();
                
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	public void insertPopUpValues(int extraBed, int person) throws SQLException {
		
		ResultSet resultSet = null;
	    String insertQuery = "INSERT INTO Reserved_Room(ExtraBeds, NoOfPeople) VALUES(?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) 
        {
	  	        pstmt.setInt(1, extraBed);
	  	        pstmt.setInt(2, person);
                pstmt.executeUpdate();
                
                Alert alert = new Alert(AlertType.INFORMATION);
	  	        alert.setTitle("Information Dialog");
	  	        alert.setHeaderText(null);
	  	        alert.setContentText("Added Successfully ");
	  	        alert.showAndWait();
                
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	 
    public static void main(String[] args) throws SQLException {
    	connect();
    }

}
	