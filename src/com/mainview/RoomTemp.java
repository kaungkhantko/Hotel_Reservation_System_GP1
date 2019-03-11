package com.mainview;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RoomTemp {
		
		public SimpleIntegerProperty RoomNo = new SimpleIntegerProperty();
		public SimpleStringProperty RoomType = new SimpleStringProperty();
		public SimpleIntegerProperty Cost = new SimpleIntegerProperty();
		public SimpleStringProperty dateIn = new SimpleStringProperty();
		public SimpleStringProperty dateOut = new SimpleStringProperty();
		public SimpleIntegerProperty ExtraBed = new SimpleIntegerProperty();
		public SimpleIntegerProperty NoOfPeople= new SimpleIntegerProperty();
	
		
		
	//	public SimpleStringProperty extraBed = new SimpleStringProperty();
	//	public SimpleStringProperty NoOfPeople= new SimpleStringProperty();
		

		//public RoomTemp() {};
		
		//********************* Constructors *************************//
		
		public RoomTemp (int RoomTempNo, String RoomTempType, int cost)
		{
			super();
			this.RoomNo.set(RoomTempNo);
			this.RoomType.set(RoomTempType);
			this.Cost.set(cost);
		}
		public RoomTemp(int value, int value2) {
			super();
			ExtraBed.set(value);
			NoOfPeople.set(value2);
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
			return dateIn.get();
		}
		public void setDateIn(String dateIn) {
			this.dateIn.set(dateIn);
		}
	
		
		public String getDateOut() {
			return dateOut.get();
		}
		public void setDateOut(String dateOut) {
		this.dateOut.set(dateOut); }
		
		
		public int getExtraBed() {
			return ExtraBed.get();
		}
		public void setExtraBed(int extraBed) {
			this.ExtraBed.set(extraBed);
		}

		public int getNoOfPeople() {
			return NoOfPeople.get();
		}
		public void setNoOfPeople(int noOfPeople) {
					this.NoOfPeople.set(noOfPeople);
				}
				//******************************************************************//	
		//***************************************************************//
	}


