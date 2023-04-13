package DAO.AdminDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DAO.Abs_Dao;
import DAO.AirplaneDAO.TravelDao;
import SERVICE.AriplaneService.TravelService;
import VO.PackageVo;
import VO.ReservationVO;
import VO.TravelVo;

public class PackageDao extends Abs_Dao {
	Scanner sc = new Scanner(System.in);
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	String query;
	
	TravelDao travel = new TravelDao();
	TravelService travelService = new TravelService();
	
	private void connDB() { 
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			System.out.println("UserDao.connDB()����");
			
		}
	}//connDB()
	
	public List<PackageVo> Select() {
	connDB();
	List package_list = new ArrayList();
	query = "select * from package";
	try {
		stmt = conn.createStatement();
		rs = stmt.executeQuery(query);
		if(rs.next()) {
			
		while(rs.next()) {
		PackageVo vo = new PackageVo();
		vo.setPackno(rs.getString(1));
		vo.setPackname(rs.getString(2));
		vo.setPackprice(rs.getString(3));
		vo.setPackplan(rs.getString(4));
		vo.setStarting_point(rs.getString(5));
		vo.setDestination(rs.getString(6));
		vo.setDeparture_time(rs.getString(7));
		vo.setArrival_time(rs.getString(8));
		vo.setTourist_spot(rs.getString(9));
		package_list.add(vo);
		}
	}else {
	}
	}catch (SQLException e) {
		e.printStackTrace();
	}
		return package_list;
		
	}

	
	public List<ReservationVO> Insert(){
		connDB();
		
		String packplan = null;
		String starting_point = null;
		String destination = null;
		String departure_time = null;
		String arrival_time = null;
		System.out.println("������������������������������������������������������");
		System.out.println("������ ���");
		System.out.println("������������������������������������������������������");

		List<TravelVo> Travel =travelService.Select();
		
		System.out.println("����� �������� ��ȣ�� �Է����ּ���.");
		int pick = Integer.parseInt(sc.nextLine());
		for(int i =0; i< Travel.size(); i++) {
			if(pick==Travel.get(i).getTravel_no()) {
				System.out.println("�����ϴ�.");
				 packplan = Travel.get(i).getPlan();
				 starting_point = Travel.get(i).getStarting();
				 destination = Travel.get(i).getDestination();
				 arrival_time = Travel.get(i).getArrival_Time().substring(0, 16).replace('-', '.');
				 departure_time =Travel.get(i).getDeparture_Time().substring(0, 16).replace('-', '.');
				 System.out.println(departure_time);
				
			}
		}
		System.out.println("��Ű�� ��ȣ �Է� ����(etp00~000):");
		String packno = sc.nextLine();
		System.out.println("��Ű�� �̸� �Է�:");
		String packname = sc.nextLine();
		System.out.println("��Ű�� ���� �Է� ����(000��):");
		String packprice = sc.nextLine();
		System.out.println("��Ű�� ������ �Է�  ����(ex�Ϻ�-����ī ���..:");
		String tourist_spot = sc.nextLine();
		
		String query = "insert into package values(?,?,?,?,?,?,to_date(?,'YYYY.MM.dd HH24:MI'),to_date(?,'YYYY.MM.dd HH24:MI'),?)";
		System.out.println(departure_time);
		try {
			
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, packno);
			pstmt.setString(2, packname);
			pstmt.setString(3, packprice);
			pstmt.setString(4, packplan);
			pstmt.setString(5, starting_point);
			pstmt.setString(6, destination);
			pstmt.setString(7, departure_time); 
			pstmt.setString(8, arrival_time); 
			pstmt.setString(9, tourist_spot);
			
			
			
			int count = pstmt.executeUpdate();
			if(count !=0) {
			System.out.println("��Ű�� ����� �Ϸ��߽��ϴ�.");
				
			}else {
				System.out.println("����Ű ��Ͽ� �����ϼ̽��ϴ�.");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	public void Delete(int no) {
	query = "delete from package where packno=?";
	try {
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, no);
		int count =pstmt.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	}
	
	public void Update() {
		
		
	}
	
}
