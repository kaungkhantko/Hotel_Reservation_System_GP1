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
 
	public void insertAllInfo( String name, String NRC, String Ph1, String Ph2, String Email, Integer extraBed, Integer person,int roomNo,
			String dateIN, String dateOUT) throws SQLException {
		
		
	    String insertCustomerQuery = "INSERT INTO Customer(CustomerName,NRC,PhoneNumber1,PhoneNumber2,Email) VALUES(?,?,?,?,?)";
	    String insertRoomQuery = "INSERT INTO Reserved_Room(RoomNo, ExtraBeds, CheckInDate, CheckOutDate, NoOfPeople ) VALUES(?,?,?,?,?)";
	    
	    
        try (Connection conn = this.connect();
            PreparedStatement pstmt1 = conn.prepareStatement(insertCustomerQuery);)
        {
	  	        pstmt1.setString(1, name);
	  	        pstmt1.setString(2, NRC);
	  	        pstmt1.setString(3, Ph1);
	  	        pstmt1.setString(4, Ph2);
	  	        pstmt1.setString(5, Email);
                pstmt1.execute();
                
                System.out.println("C info added");
                
        } catch (SQLException e) { System.out.println(e.getMessage()); }
        
        
        
        try (Connection conn = this.connect();
             PreparedStatement pstmt2 = conn.prepareStatement(insertRoomQuery);)
            {	
        	
        		pstmt2.setInt(1, roomNo);
        		pstmt2.setInt(2, extraBed);
	        	pstmt2.setString(3, dateIN);
		        pstmt2.setString(4, dateOUT);
		        pstmt2.setInt(5, person);
                pstmt2.execute();
                    
                System.out.println("Date in/out info added");
                
            } catch (SQLException e) { System.out.println(e.getMessage());}
	}    
	
	public void insertCID (int CID) {
		
		   String insertIDQuery = "INSERT INTO Reservation_Details (CustomerID) VALUES(?);";
		    
	        try (Connection conn = this.connect();
	            PreparedStatement pstmt = conn.prepareStatement(insertIDQuery);)
	        {
		  	        pstmt.setInt(1, CID);
	                pstmt.execute();
	                
	                System.out.println("CID added");
	                
	        } catch (SQLException e) { System.out.println(e.getMessage()); }
	}
	
	public void insertRID (int RID, int roomNO) {
		
		   String insertIDQuery = "UPDATE Reserved_Room SET ReservationID = ? WHERE	RoomNo = ?";
		    
	        try (Connection conn = this.connect();
	            PreparedStatement pstmt = conn.prepareStatement(insertIDQuery);)
	        {
		  	        pstmt.setInt(1, RID);
		  	        pstmt.setInt(2, roomNO);
	                pstmt.execute();
	                
	                System.out.println("RID added");
	                
	        } catch (SQLException e) { System.out.println(e.getMessage()); }
		
	}
	
	
	public static void main(String[] args) throws SQLException {
	    	connect();
	    }

	}
        

	