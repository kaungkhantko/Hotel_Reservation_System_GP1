package com.mainview;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Customer {
	
	public SimpleIntegerProperty customerNo = new SimpleIntegerProperty();
	public SimpleIntegerProperty roomNo = new SimpleIntegerProperty();
	public SimpleStringProperty reservedTime = new SimpleStringProperty();
	public SimpleStringProperty name = new SimpleStringProperty();
	public SimpleIntegerProperty phoneNumber1 = new SimpleIntegerProperty();
	public LocalDate dateInTemp;
	public LocalDate dateOutTemp;
	public SimpleStringProperty dateIn = new SimpleStringProperty();
	public SimpleStringProperty dateOut = new SimpleStringProperty();
	
	public SimpleStringProperty testing = new SimpleStringProperty();
	DateTimeFormatter formatterTemp = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	final DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd-MM-yyyy");  
	

	
	public Customer (int customerNo, int roomNo, String reservedTime, String name, int phoneNumber1, String dateIn, String dateOut)
	{
		super();
		this.customerNo.set(customerNo);
		this.roomNo.set(roomNo);
		this.reservedTime.set(reservedTime);
		this.name.set(name);
		this.phoneNumber1.set(phoneNumber1);
		this.dateInTemp = LocalDate.parse(dateIn, formatterTemp);
		this.dateOutTemp = LocalDate.parse(dateOut, formatterTemp);
		this.dateIn.set(dateInTemp.format(formatter));
		this.dateOut.set(dateOutTemp.format(formatter));
	}

	public Customer(String testing) {
		super();
		this.testing.set(testing);
	}



    //*********** Getter & Setter Methods *********/
		public int getCustomerNo() {
			return customerNo.get();
		}
		public void getCustomerNo(int customerNo) {
			this.customerNo.set(customerNo);
		}
	
		
		public int getRoomNo() {
			return roomNo.get();
		}
		public void setRoomNo(int roomNo) {
			this.roomNo.set(roomNo);
		}
	
		
		public String getReservedTime() {
			return reservedTime.get();
		}
		public void setReservedTime(String reservedTime) {
			this.reservedTime.set(reservedTime);
		}
	
		
		public String getName() {
			return name.get();
		}
		public void setName(String name) {
			this.name.set(name);
		}
	
		
		public int getPhoneNumber1() {
			return phoneNumber1.get();
		}
		public void setPhoneNumber1(int phoneNumber1) {
			this.phoneNumber1.set(phoneNumber1);
		}
		
		public String getDateIn() {
			return dateIn.get();
		}
		public void setDateIn(String dateIn) {
			this.dateIn.set(dateIn);
		}
		
		public String getDateOut() {
			return dateOut.get();
		}
		public void setDateOut(String dateOut) {
			this.dateOut.set(dateOut);
		}

	}