package VO;

import java.sql.Time;
import java.util.Date;

public class TravelVo {
	private int travel_no;
	private String starting; //�����
	private String destination; //������ 
	private String airplane_name;	//�װ���
	private String departure_Time;	//��� �ð�
	private String arrival_Time;		//���� �ð�
	private String plan;				//��� ����
	public int getTravel_no() {
		return travel_no;
	}
	public void setTravel_no(int travel_no) {
		this.travel_no = travel_no;
	}
	public String getStarting() {
		return starting;
	}
	public void setStarting(String starting) {
		this.starting = starting;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getAirplane_name() {
		return airplane_name;
	}
	public void setAirplane_name(String airplane_name) {
		this.airplane_name = airplane_name;
	}
	public String getDeparture_Time() {
		return departure_Time;
	}
	public void setDeparture_Time(String departure_Time) {
		this.departure_Time = departure_Time;
	}
	public String getArrival_Time() {
		return arrival_Time;
	}
	public void setArrival_Time(String arrival_Time) {
		this.arrival_Time = arrival_Time;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	
	
	
	
	
	
	
	
	
	
}
