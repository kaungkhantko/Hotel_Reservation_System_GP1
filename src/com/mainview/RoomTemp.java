package com.mainview;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RoomTemp {
		
		public SimpleIntegerProperty RoomNo = new SimpleIntegerProperty();
		public SimpleStringProperty RoomType = new SimpleStringProperty();
		public SimpleIntegerProperty Cost = new SimpleIntegerProperty(); //Room Cost
		public SimpleStringProperty DateIn = new SimpleStringProperty();
		public SimpleStringProperty DateOut = new SimpleStringProperty();
		public SimpleIntegerProperty ExtraBed = new SimpleIntegerProperty();// number of extraBed
		public SimpleStringProperty BedValue = new SimpleStringProperty(); //cost for extra bed
		public SimpleIntegerProperty NoOfPeople= new SimpleIntegerProperty();
		public SimpleIntegerProperty TotalCharges = new SimpleIntegerProperty();
		
		
		//********************* Constructor *************************//
		public RoomTemp(int roomNo, String roomType, int cost, int extraBed ,String bedValue, 
				String dateIn, String dateOut,  int totalCharges) {
			super();
			RoomNo.set(roomNo);
			RoomType.set(roomType);
			Cost.set(cost);
			ExtraBed.set(extraBed);
			DateIn.set(dateIn);
			DateOut.set(dateOut);
			BedValue.set(bedValue);
			TotalCharges.set(totalCharges);
		}
		//**************************************************************//
		
		
		
		
	
		//******************* Getter & Setter Methods ***************//
		public int getRoomNo() {
			return RoomNo.get();
		}
		public void setRoomNo(int RoomNo) {
			this.RoomNo.set(RoomNo);
		}

		
		public String getRoomType() {
			return RoomType.get();
		}
		public void setRoomTempType(String RoomType) {
			this.RoomType.set(RoomType);
		}
		
		public void setRoomType(String roomType) {
			this.RoomType.set(roomType);
		}
		

		public int getCost() {
			return Cost.get();
		}
		public void setCost(int cost) {
		this.Cost.set(cost);
	}
		

		public String getDateIn() {
			return DateIn.get();
		}
		public void setDateIn(String dateIn) {
			this.DateIn.set(dateIn);
		}
	
		
		public String getDateOut() {
			return DateOut.get();
		}
		public void setDateOut(String dateOut) {
		this.DateOut.set(dateOut); }
		
		
		public int getExtraBed() {
			return ExtraBed.get();
		}
		public void setExtraBed(int extraBed) {
			this.ExtraBed.set(extraBed);
		}

		
		public String getBedValue() {
			return BedValue.get();
		}
		public void setBedValue(String bedValue) {
		this.BedValue.set(bedValue); }
		
		
		public int getNoOfPeople() {
			return NoOfPeople.get();
		}
		public void setNoOfPeople(int noOfPeople) {
					this.NoOfPeople.set(noOfPeople);
				}
				//******************************************************************//	
		
		
		public int getTotalCharges() {
			return TotalCharges.get();
		}
		public void setTotalCharges(int totalCharges) {
			this.TotalCharges.set(totalCharges);
		}
		//***************************************************************//
	}


