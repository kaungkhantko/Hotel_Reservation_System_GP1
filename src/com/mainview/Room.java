package com.mainview;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Room {
		
		public SimpleIntegerProperty rowNumber = new SimpleIntegerProperty();
		public SimpleIntegerProperty roomNo = new SimpleIntegerProperty();
		public SimpleStringProperty roomType = new SimpleStringProperty();
		public SimpleIntegerProperty cost = new SimpleIntegerProperty();
		public SimpleIntegerProperty maximum = new SimpleIntegerProperty();
		public SimpleStringProperty availability = new SimpleStringProperty();
		public SimpleStringProperty dateIn = new SimpleStringProperty();
		public SimpleStringProperty dateOut = new SimpleStringProperty();
		
		public SimpleIntegerProperty extraBed = new SimpleIntegerProperty();
		public SimpleIntegerProperty NoOfPeople= new SimpleIntegerProperty();
		
		
		
		
		
		//public SimpleStringProperty actualCheckOut = new SimpleStringProperty();
		//private boolean checkOutStatus;
		
		//public Room() {};
		public Room (int rowNumber, int roomNo, String roomType, int cost, int maximum, String availability)
		{
			super();
			this.rowNumber.set(rowNumber);
			this.roomNo.set(roomNo);
			this.roomType.set(roomType);
			this.cost.set(cost);
			this.maximum.set(maximum);
			this.availability.set(availability);
		}
		

	//******************* Getter & Setter Methods ***************//
		public int getRowNumber() {
			return rowNumber.get();
		}
		public void setRowNumber(int rowNumber) {
			this.rowNumber.set(rowNumber);
		}

		
		public int getRoomNo() {
			return roomNo.get();
		}
		public void setRoomNo(int roomNo) {
			this.roomNo.set(roomNo);
		}

		
		public String getRoomType() {
			return roomType.get();
		}
		public void setRoomType(String roomType) {
			this.roomType.set(roomType);
		}

		
		public int getCost() {
			return cost.get();
		}
		public void setCost(int cost) {
			this.cost.set(cost);
		}

		
		public int getMaximum() {
			return maximum.get();
		}
		public void setMaximum(int maximum) {
			this.maximum.set(maximum);
		}
		
		public int getNoOfPerson() {
			return NoOfPeople.get();
	}
		public void setNoOfPerson(int noOfPerson) {
		this.NoOfPeople.set(noOfPerson);
	}

		
		public String getAvailability() {
			return availability.get();
		}
		public void setAvailability(String availability) {
			this.availability.set(availability);
		}

		
		public int getExtraBed() {
			return extraBed.get();
		}
		public void setExtraBed(int extraBed) {
		this.extraBed.set(extraBed);
		}


		public int getNoOfPeople() {
			return NoOfPeople.get();
		}
		public void setNoOfPeople(int noOfPeople) {
				this.NoOfPeople.set(noOfPeople);
			}
	//******************************************************************//	
		
}		
			

	


