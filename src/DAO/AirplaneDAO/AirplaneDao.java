package DAO.AirplaneDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import DAO.Abs_Dao;
import VO.AirplaneAccountVo;

public class AirplaneDao extends Abs_Dao{

	Scanner sc = new Scanner(System.in);
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	String query;
	AirplaneAccountVo vo;
	
	String Airplane_INSERT = "insert into airplane values(?, ?, ?,?)";
	
	protected  void connDB() { 
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			System.out.println("connDB()����");
			
		}
	}//connDB()
	
	public List<AirplaneAccountVo> Airplane_list(){
		connDB();
		List<AirplaneAccountVo> Airplane_list = new ArrayList<AirplaneAccountVo>();
		
		try {
		stmt = conn.createStatement();
		query = "select * from airplane_table";
		rs = stmt.executeQuery(query);
		
		while(rs.next()) {
			vo =  new AirplaneAccountVo();
			vo.setID(rs.getString("id"));
			vo.setPWD(rs.getString("pwd"));
			vo.setAir_Name(rs.getString("air_name"));
			vo.setAir_Tel(rs.getString("air_tel"));
			vo.setAir_code(rs.getString("air_code"));
			
			Airplane_list.add(vo);
		}
		} catch (Exception e) {
			System.out.println("Airplane_list() ����");
		}
		return Airplane_list;
	}//Airplane_list()
	
	//�װ��� ȸ������
		public void insertAirplane() {
			
		
			try {
				
				System.out.print("���̵� �Է��ϼ��� : ");
				String id = sc.nextLine();
				System.out.println("id"+id);
				
				System.out.println("��й�ȣ�� �Է��ϼ���.");
				String pwd = sc.nextLine();

				
				System.out.print("�װ��� ��ȣ�� �Է����ּ���");  //db���� ȸ����ȣ �⺻Ű�� ó��
				System.out.println("(���ڸ� �Է� �����մϴ�)");
				String air_code = sc.next();
				String Nopattern = "^[0-9]*$";	//���ڸ� �Է°���
				boolean Noresult = Pattern.matches("^[0-9]*$", air_code);
				
				System.out.println("�Է��Ͻ� �װ��� �ڵ�� "+air_code+"�Դϴ�.");
				
				
				String air_No1 = null;
				while(Noresult!=true) {  //false�� �ݺ���
					System.out.println("�ùٸ��� ���� ȸ����ȣ�Դϴ�.");
					System.out.println("ȸ����ȣ�� �ٽ� �Է����ּ���");
					air_No1 = sc.next();
					
					if(Pattern.matches(Nopattern, air_No1)) {
						Noresult=true;
						
						System.out.println("��밡���� �װ��� ��ȣ�Դϴ�.");
						System.out.println("�Է��Ͻ� ���̵��"+air_No1+"�Դϴ�.");
						break;
					}
				}
				
				
				
				System.out.println("�װ��� �̸��� �Է����ּ���");
				String air_Name = sc.nextLine();
				
				
				System.out.println("��ȭ��ȣ�� �Է����ּ���.");
				System.out.println("��ȭ��ȣ ���� xxx-xxxx-xxxx(������ ��� ����) ");
				String air_Tel = sc.nextLine();	
				String airTel_pattern = "^\\d{3}-\\d{4}-\\d{4}$";
				boolean airTelresult = Pattern.matches("^\\d{3}-\\d{4}-\\d{4}$", air_Tel); 
				
				String air_Tel1 =null;
				while(airTelresult!=true) {
					System.out.println("�ùٸ��� ���� ��ȭ��ȣ�Դϴ�");
					System.out.println("��ȭ��ȣ�� �ٽ� �Է����ּ���");
					air_Tel1 = sc.nextLine();
				
					
					
				if(Pattern.matches(airTel_pattern, air_Tel1)) {
					airTelresult=true;
					System.out.println("�ùٸ� ��ȭ��ȣ�Դϴ�");
					break;
				}
				}
				

				

				
				
				
				
				
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO AIRPLANE_TABLE VALUES(?,?,?,?,?)");
				
				pstmt.setString(1, id);
				pstmt.setString(2, pwd);
				pstmt.setString(3, air_Name);
				pstmt.setString(4, air_Tel);	
				pstmt.setString(5, air_code);

		
				int result = pstmt.executeUpdate();
				if(result == 1) {
					System.out.println("ȸ������ �Ǿ����ϴ�.");
				} else if(result == 0) {
					System.out.println("ȸ�����Կ� �����Ͽ����ϴ�.");
				}
				
				
	   		}catch(SQLException e) {

	   			e.printStackTrace();

	   		}catch(Exception e) {

	   			e.printStackTrace();

	   		}finally {
	   			
	   		}
		}
	

}//class
