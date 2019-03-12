package com.mainview;

import java.time.LocalDate;

public class CustomerTable {
	
	private String cName;
	private String cPhNo1;
	private String cPhNo2;
	private String cNRC;
	private String cEmail;
	
	private int personPerRoom;
	private int extraBed;
	private LocalDate reservedTime;
	private String dateIn;
	private String dateOut;
	
	public CustomerTable (String cName, String cPhNo1, String cPhNo2, String cNRC, String cEmail,int personPerRoom, int roomNo, int extraBed,LocalDate reservedTime, String name, String dateIn, String dateOut, boolean checkOutStatus)
	{
		super();
		this.cName = cName;
		this.cNRC = cNRC;
		this.cPhNo1 = cPhNo1;
		this.cPhNo2 = cPhNo2;
		this.cEmail = cEmail;
		this.reservedTime = reservedTime;
		this.dateIn = dateIn;
		this.dateOut = dateOut;
		this.extraBed = extraBed;
		this.personPerRoom = personPerRoom;
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

	public String getDateIn() {
		return dateIn;
	}

	public void setDateIn(String dateIn) {
		this.dateIn = dateIn;
	}

	public String getDateOut() {
		return dateOut;
	}

	public void setDateOut(String dateOutTBox) {
		this.dateOut = dateOutTBox;
	}

	public int getExtraBed() {
		return extraBed;
	}

	public void setExtraBed(int extraBed) {
		this.extraBed = extraBed;
	}

	public int getPersonPerRoom() {
		return personPerRoom;
	}

	public void setPersonPerRoom(int personByRoom) {
		this.personPerRoom = personByRoom;
	}

	public String getcNRC() {
		return cNRC;
	}
}	
	
		

