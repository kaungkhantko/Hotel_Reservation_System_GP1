package com.mainview;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.scene.control.DatePicker;


public class CustomerTable {
	
	private String cName;
	private String cPhNo1;
	private String cPhNo2;
	private String cNRC;
	private String cEmail;
	
	private static int personPerRoom;
	private int roomNo;
	private static int extraBed;
	private LocalDate reservedTime;
	private static String dateIn;
	private static String dateOut;
	
	
	public CustomerTable() {}

	public CustomerTable(String cName, String cPhNo1, String cPhNo2, String cNRC, String cEmail) {
		super();
		this.cName = cName;
		this.cPhNo1 = cPhNo1;
		this.cPhNo2 = cPhNo2;
		this.cNRC = cNRC;
		this.cEmail = cEmail;
	}
	public CustomerTable(int personPerRoom, int extraBed) {
		super();
		this.personPerRoom = personPerRoom;
		this.extraBed = extraBed;
	}



	public String getcName() {
		return cName;
	}
	
	public void setcName(String cName) {
		this.cName = cName;
	}
	
	public String getcPhNo1() {
		return cPhNo1;
	}
	
	public void setcPhNo1(String cPhNo1) {
		this.cPhNo1 = cPhNo1;
	}
	
	public String getNRC() {
		return cNRC;
	}
	
	public void setcNRC(String cNRC) {
		this.cNRC = cNRC;
	}

	public String getcEmail() {
		return cEmail;
	}

	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}

	public String getcPhNo2() {
		return cPhNo2;
	}

	public void setcPhNo2(String cPhNo2) {
		this.cPhNo2 = cPhNo2;
	}

	public LocalDate getReservedTime() {
		return reservedTime;
	}

	public void setReservedTime(LocalDate reservedTime) {
		this.reservedTime = reservedTime;
	}

	public static String getDateIn() {
		return dateIn;
	}

	public static void setDateIn(String DateIn) {
		dateIn = DateIn;
	}

	public static String getDateOut() {
		return dateOut;
	}

	public static void setDateOut(String DateOutTBox) {
		dateOut = DateOutTBox;
	}

	public static int getExtraBed() {
		return extraBed;
	}

	public static void setExtraBed(int ExtraBed) {
		extraBed = ExtraBed;
	}

	public static int getPersonPerRoom() {
		return personPerRoom;
	}

	public static void setPersonPerRoom(int PersonByRoom) {
		personPerRoom = PersonByRoom;
	}

	public String getcNRC() {
		return cNRC;
	}
	
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
}	
	
		

