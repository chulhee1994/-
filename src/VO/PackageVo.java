package VO;

public class PackageVo { // ������Ű�� ������ ���� ���̺��Դϴ�.
	
	private String packno; // ��Ű�� �⺻Ű�� ���� ��ȣ�Դϴ� Ÿ��:varchar2
	private String packname; // ��Ű�� ��Ī�Դϴ�. Ÿ��:varchar2
	private String packprice; // �����Դϴ�. 00������ ���� �;� varchar2�� �غý��ϴ�.
	private String packplan; // ���� �����Դϴ�. ex) 2�� 3�� Ÿ��:varchar2
	private String starting_point; //��� ����Դϴ�. Ÿ��:varchar2
	private String destination; // �������Դϴ�. Ÿ��:varchar2
	private String departure_time; // ��� ���ϰ� �ð��Դϴ�. Ÿ��: date
	private String arrival_time; // ���� ���ϰ� �ð��Դϴ�. Ÿ��: date
	private String tourist_spot;
	public String getPackno() {
		return packno;
	}
	public void setPackno(String packno) {
		this.packno = packno;
	}
	public String getPackname() {
		return packname;
	}
	public void setPackname(String packname) {
		this.packname = packname;
	}
	public String getPackprice() {
		return packprice;
	}
	public void setPackprice(String packprice) {
		this.packprice = packprice;
	}
	public String getPackplan() {
		return packplan;
	}
	public void setPackplan(String packplan) {
		this.packplan = packplan;
	}
	public String getStarting_point() {
		return starting_point;
	}
	public void setStarting_point(String starting_point) {
		this.starting_point = starting_point;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDeparture_time() {
		return departure_time;
	}
	public void setDeparture_time(String departure_time) {
		this.departure_time = departure_time;
	}
	public String getArrival_time() {
		return arrival_time;
	}
	public void setArrival_time(String arrival_time) {
		this.arrival_time = arrival_time;
	}
	public String getTourist_spot() {
		return tourist_spot;
	}
	public void setTourist_spot(String tourist_spot) {
		this.tourist_spot = tourist_spot;
	}
	
	
	
	
	

}
