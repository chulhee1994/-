package SERVICE.AriplaneService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DAO.AirplaneDAO.TravelDao;
import VO.AirplaneAccountVo;
import VO.TravelVo;

public class TravelService   {
	Scanner sc = new Scanner(System.in);
	VO.TravelVo TravelVo = new TravelVo();
	TravelDao TravelDao = new TravelDao();
	
	String query;
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public void main(AirplaneAccountVo account) {
		boolean loof =true;
		while(loof) {
		System.out.println("������������������������������������������������������");
		System.out.println("0.�ڷ� ����");
		System.out.println("1.������ ���");
		System.out.println("2.������ ���");
		System.out.println("3.������ ����");
		System.out.println("4.������ ����");
		System.out.println("������������������������������������������������������");
		System.out.println("�۾��� �������ּ���.>>");
		int choice = Integer.parseInt(sc.nextLine());
		
		switch(choice) {
		
		case 0:
			loof=false;
			System.out.println("���� �������� ���ư��ϴ�");
			break;
		case 1:
			Select();
			break;
		case 2:
			Insert(account);
			break;
		case 3:
			Update(account);
			break;
		case 4:
			Delete(account);
			break;
		}
		
		}
	}
	
	
	public List Select() {
	List<TravelVo> Travel_List = TravelDao.Travel_List();
	//SimpleDateFormat sDate = new SimpleDateFormat("YYYY.MM.dd HH24:MI");
	SimpleDateFormat sDate = new SimpleDateFormat("YYYY.MM.dd");
	System.out.printf("%s \t%s \t%s \t%s \t%s \t\t\t%s \t\t\t%s\n","��ȣ","�����","������","�װ���","��߽ð�","�����ð�","���೯¥");
	System.out.println("������������������������������������������������������");

	for(int i =0; i<Travel_List.size(); i++) {
	System.out.printf("%s",Travel_List.get(i).getTravel_no());
	System.out.printf("\t%s",Travel_List.get(i).getStarting());
	System.out.printf("\t%s",Travel_List.get(i).getDestination());
	System.out.printf("\t%s",Travel_List.get(i).getAirplane_name());
	System.out.printf("\t%s",Travel_List.get(i).getDeparture_Time().substring(0, 16));
	System.out.printf("\t%s",Travel_List.get(i).getArrival_Time().substring(0,16));
//	System.out.println(sDate.format(Travel_List.get(i).getDeparture_Time()));
//	System.out.println(sDate.format(Travel_List.get(i).getArrival_Time()));
	System.out.printf("\t%s\n",Travel_List.get(i).getPlan());
	//System.out.println(sDate.format(Travel_List.get(i).getPlan()) );
	}
	System.out.println("������������������������������������������������������");
	return Travel_List;

	}//Travel_List()

	
	public void Select_Where(AirplaneAccountVo account) {
	List<TravelVo> Travel_List = TravelDao.Select(account);
	SimpleDateFormat sDate = new SimpleDateFormat("YYYY.MM.dd");

	System.out.printf("%s %s %s %s %s %s\n","�����","������","�װ���","��߽ð�","�����ð�","���೯¥");
	System.out.println("������������������������������������������������������");
	for(int i =0; i<Travel_List.size(); i++) {
	System.out.println(Travel_List.get(i).getStarting());
	System.out.println(Travel_List.get(i).getDestination());
	System.out.println(Travel_List.get(i).getAirplane_name());
	System.out.println(sDate.format(Travel_List.get(i).getDeparture_Time()));
	System.out.println(sDate.format(Travel_List.get(i).getArrival_Time()));
	System.out.println(Travel_List.get(i).getPlan());
	}
	System.out.println("������������������������������������������������������");
	}
	



	public void Insert(AirplaneAccountVo account) {
	TravelVo vo = new TravelVo();
	System.out.println("������� �Է����ּ���.>>");
	String starting = sc.nextLine();
	vo.setStarting(starting);
	
	System.out.println("�������� �Է����ּ���.>>");
	String destination = sc.nextLine();
	vo.setDestination(destination);
	
	vo.setAirplane_name(account.getAir_Name());
	System.out.println("ex)2023.12.20 06:30");
	System.out.println("��߽ð��� �Է����ּ���.");
	
	String depature_time =sc.nextLine();
	vo.setDeparture_Time(depature_time);
	
	System.out.println("ex)2023.12.20 06:30");
	System.out.println("�����ð��� �Է����ּ���.");
	String arrival_time = sc.nextLine();
	vo.setArrival_Time(arrival_time);
	
	
	
	System.out.println("���������� �Է����ּ���.");
	String plan = sc.nextLine();
	vo.setPlan(plan);
	TravelDao.Insert(vo);
	}


	public void Delete(AirplaneAccountVo account) {
		Select_Where(account);
	
	}


	public void Update(AirplaneAccountVo account) {
		
	}


	
	
	
}//class
