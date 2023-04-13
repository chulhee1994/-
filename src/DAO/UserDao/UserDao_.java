package DAO.UserDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import DAO.Abs_Dao;
import SERVICE.UserService.ServiceForMember;
import VO.User_AccountVo;

public class UserDao_ extends Abs_Dao {
	Scanner sc = new Scanner(System.in);
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	String query;
	ResultSet rs;
	
	private  void connDB() { 
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			System.out.println("connDB()����");
			
		}
	}//connDB()
	
	
	public List<User_AccountVo> Id(User_AccountVo account) {
	connDB();
	query = "select * from user_table where user_id =? && name =?";
	
	try {
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, account.getID());
		pstmt.setString(2, account.getName());
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
		while(rs.next()) {
		List<User_AccountVo>user_list = new ArrayList<User_AccountVo>();
			
		}
		}else {
			System.out.println("ã���� ���̵� �����ϴ�.");
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
		
	}
	
	
	//ȸ������
	public void insertUser() {
		try {
			Scanner sc = new Scanner(System.in);
			connDB();
			
			System.out.println("���̵� �Է����ּ���");
			System.out.println("ID�� ������ ������, _������ Ư������ �Ұ�, 5~12�����ϸ� �����մϴ�.");
			String id = sc.next();
			String idpattern = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$";
			boolean idresult = Pattern.matches("^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$", id);
			
			System.out.println("�Է��Ͻ� ���̵�� "+id+"�Դϴ�.");

			String id1 = null;
			while(idresult!=true) {   //�Է� ���� ID�� ���Ͽ� �ȸ����� ���� �� ���� �ݺ������� �Է°� �޽��ϴ�. false�� �ݺ���
				System.out.println("�ùٸ��� ���� ���̵��Դϴ�.");
				System.out.println("���̵� �ٽ� �Է����ּ���");
				id1 = sc.next();

				if(Pattern.matches(idpattern, id1)) {
					idresult=true;
					
					System.out.println("��밡���� id�Դϴ�.");
					System.out.println("�Է��Ͻ� ���̵��"+id1+"�Դϴ�.");
					break;   //idresult ���� true�� �ݺ��� Ż��
				}
			}

			
		    	System.out.println("��й�ȣ�� �Է����ּ���");
	    		System.out.println("(�ҹ���,����,Ư������ ������ 8~15�ڸ� �����մϴ�.)");
	    		String pwd = sc.next();
	    		String pwdpattern = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$";   // ��й�ȣ �ҹ���, ����, Ư������ ������ 8~15��
	    		boolean pwdresult = Pattern.matches("^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$", pwd);
	    		
	    		String pwd1 = null;
	    		while(pwdresult!=true) {
	    			System.out.println("�ùٸ��� ���� ��й�ȣ�Դϴ�");		//�Է� ���� pwd�� ���Ͽ� �ȸ����� �´� ���� �Է��� �� ���� �ݺ����� �����ϴ�.
	    			System.out.println("��й�ȣ�� �ٽ� �Է����ּ���");
	    			pwd1 = sc.next();								//���Ͽ� ������ pwd�� ���Ͽ� �ȸ����� pwd1 ������ ���� �޽��ϴ�.
	    		
	    		if(Pattern.matches(pwdpattern, pwd1)){
	    			pwdresult=true;
	    			System.out.println("�ùٸ� ��й�ȣ�Դϴ�.");		
	    			break;
	    		}
	    	}    	
		    	
		    	System.out.println("�̸��� �Է����ּ���");
		    	String name = sc.next();
		    	
		    	System.out.println("�ֹε�Ϲ�ȣ�� �Է����ּ���");
		    	System.out.println("����. xxxxxx-xxxxxxx (������)����");
		    	String citizen_number = sc.next();
		    	String citznumpattern = "\\d{6}\\-[1-4]\\d{6}";     //�ֹε�Ϲ�ȣ xxxxxx-xxxxxx
		    	boolean cinumresult = Pattern.matches("\\d{6}\\-[1-4]\\d{6}", citizen_number);
		    	
		    	String citizen_number1 = null;
		    	while(cinumresult!=true) {
		    		System.out.println("�ùٸ��� ���� �ֹι�ȣ�Դϴ�");	//�Է� ���� citizen_number�� ���Ͽ� �ȸ����� �´� ���� �Է��� �� ���� �ݺ����� �����ϴ�.
		    		System.out.println("�ֹι�ȣ�� �ٽ� �Է����ּ���");
		    		citizen_number1 = sc.next();
		    		
		    	if(Pattern.matches(citznumpattern, citizen_number1)) {	////���Ͽ� ������ citizen_number�� ���Ͽ� �ȸ����� citizen_number1 ������ ���� �޽��ϴ�.
		    		cinumresult=true;
		    		System.out.println("�ùٸ� �ֹι�ȣ�Դϴ�.");
		    		break;
		    	}
		    	
		    }
		    	
		    	
		    	System.out.println("���̸� �Է����ּ���");
		    	int age = sc.nextInt();
		    	
		    	
		    	System.out.println("��ȭ��ȣ�� �Է����ּ���. ");
		    	System.out.println("��ȭ��ȣ ��) xxx-xxxx-xxxx(������ ��� ����) ");
		    	String User_Tel = sc.next();
		    	String tel_pattern = "^\\d{3}-\\d{4}-\\d{4}$";     //��ȭ��ȣ 3�ڸ�-4�ڸ�-4�ڸ��Դϴ�
		    	boolean telresult = Pattern.matches("^\\d{3}-\\d{4}-\\d{4}$", User_Tel);
		    	
		    	String User_Tel1 = null;
		    	while(telresult!=true) {
		    		System.out.println("�ùٸ��� ���� ��ȭ��ȣ�Դϴ�");
		    		System.out.println("��ȭ��ȣ�� �ٽ� �Է����ּ���");
		    		User_Tel1 = sc.next();
		    	
		    	if(Pattern.matches(tel_pattern, User_Tel1)) {
		    		telresult=true;
		    		System.out.println("�ùٸ� ��ȭ��ȣ�Դϴ�");
		    		break;
		    	}
		    }

		    	
		    	System.out.print(" �̸��� �ּҸ� �Է����ּ���.");
		    	System.out.println(" ��) abc@naver.com(@����) ");
		    	String email = sc.next();
		    	String email_pattern = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$";
		    	boolean emailresult = Pattern.matches("^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$", email);
		    	
		    	String email1 = null;
		    	while(emailresult!=true) {
		    		System.out.println("�ùٸ��� ���� �̸����Դϴ�.");
		    		System.out.println("�̸��� �ּҸ� �ٽ� �Է����ּ���");
		    		email1 = sc.next();
		    	
		    	
		    	if(Pattern.matches(email_pattern, email1))  {
		    		emailresult=true;
		    		System.out.println("��� ������ �̸����Դϴ�");
		    		break;
		    	}
		    }	
		    	
		    	
	            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO user_table VALUES(?,?,?,?,?,?,?)");
//			    System.out.println("PrepareStatement ���� ����");

	            if(Pattern.matches(idpattern, id)){	
	            	pstmt.setString(1, id);
				} else {
	            	pstmt.setString(1, id1);
	            }
	            
	            

	            if(Pattern.matches(pwdpattern, pwd)){	
	            	pstmt.setString(2, pwd);
				} else {
	            	pstmt.setString(2, pwd1);
	            }
	            
	            pstmt.setString(3,name);

	            
	            if(Pattern.matches(citznumpattern, citizen_number)){	
	            	pstmt.setString(4, citizen_number);
				} else {
	            	pstmt.setString(4, citizen_number1);
	            }
	            
	            
	            if(Pattern.matches(tel_pattern, User_Tel)) {
	            	pstmt.setString(5, User_Tel);
	            } else {
	            	pstmt.setString(5, User_Tel);
	            }

	            if(Pattern.matches(email_pattern, email)) {
	            	pstmt.setString(6, email);
	            } else {
	            	pstmt.setString(6, email1);
	            }
	            
	            pstmt.setInt(7, age);
	            
	                

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

	
	
	
		//���̵� ã�� �޼���
		public void searchID() {
			Scanner sc = new Scanner(System.in);
			List<User_AccountVo> list = new ArrayList<>();
			ServiceForMember user = new ServiceForMember();
			String sql = "select id from user_table where name = ? and citizen_number = ?";
			
			try {
			
				System.out.println("���̵� ã�⸦ �����ϼ̽��ϴ�.");
	            System.out.println("-----�̸��� �Է��ϼ���.-----");
	            String name = sc.nextLine();
	            System.out.println("-----�ֹι�ȣ�� �Է��ϼ���.-----");
	            String citizen_number = sc.nextLine();
	           /* System.out.println("ȸ���� ���̵�� : " + id + "�Դϴ�.");*/
				
				connDB();
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, citizen_number);
				rs=pstmt.executeQuery();
				
			if(rs.next()) {
				if(rs.getString("name").equals(name) && (rs.getString("citizen_number")).equals(citizen_number)) {
					User_AccountVo vo = new User_AccountVo();
					vo.setName(rs.getString("name"));
					vo.setCitizen_Number(rs.getString(citizen_number));
					vo.setID(rs.getString("id"));
					list.add(vo);
					
					System.out.println("�ش� ������ ���̵� ã�ҽ��ϴ�:"+vo.getID());
					/* return searchPWD(); */
					
			    	} else {
					System.out.println("�ش� �̸��� �ֹι�ȣ�� ã�� �� �ִ� ���̵� �������� �ʽ��ϴ�.");
					
			     	}
			
			  } // rs
		
			}  catch (SQLException e) {
			System.out.println("�߸��� sql���� ���Ƚ��ϴ�.");
			e.printStackTrace();
		  }    
		
		} // ���̵� ã�� �޼��� ��		
	
	            
	    // ��й�ȣ ã�� �޼���        	
		public void searchPWD() {
			Scanner sc = new Scanner(System.in);
			List<User_AccountVo> list = new ArrayList<>();
			System.out.println("----��й�ȣ ã���Դϴ�.----");
			System.out.println("���̵� �Է��ϼ���:");
			String id = sc.nextLine();
			System.out.println("�ֹι�ȣ�� �Է��ϼ���.");
			String citizen_number = sc.nextLine();
			
			try {
				connDB();
			
				String sql = "select pwd from user_table where id=? and citizen_number=?";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, id); //���̵�
	            pstmt.setString(2, citizen_number); // �ֹι�ȣ
	            rs = pstmt.executeQuery();
	            if(rs.next()) {
	            	if(rs.getString("id").equals(id) && (rs.getString("citizen_number")).equals(citizen_number)) { // �� ������ ������ ȸ�� ���̺��� ��й�ȣ�� �����´�
	            		User_AccountVo vo = new User_AccountVo();
	            		vo.setPWD(rs.getString("pwd"));
	            		list.add(vo);
	            		System.out.println("�ش� ������ ��й�ȣ�� ã�ҽ��ϴ�:"+vo.getPWD());
	            
	            	} else {
	            		System.out.println("�ش� ���̵�� �ֹε�Ϲ�ȣ�� ��й�ȣ�� ã�� ���� �����ϴ�.");
	            		
	            	}
	            
	          
	            } // rs
	         
	         
			} catch (SQLException e) {
				System.out.println("�߸��� sql���� ���Ƚ��ϴ�.");
				e.printStackTrace();
			  }    
	
			
		
		} // ��й�ȣ ã�� �޼��� ��

	
	
	
}
