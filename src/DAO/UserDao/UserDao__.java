package DAO.UserDao;

import java.security.Provider.Service;
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

import SERVICE.UserService.ServiceForMember;
import VO.PackageVo;
import VO.ReservationVO;
import VO.TravelVo;
import VO.User_AccountVo;


public class UserDao__ {

	static String driver = "oracle.jdbc.driver.OracleDriver";
	static String url = "jdbc:oracle:thin:@localhost:1521:xe"; // ��ġ xe ���� �̸�
	static String user = "travel";
	static String password = "1234";

	Connection conn;
	Statement stmt;
	ResultSet rs;
	PreparedStatement pstmt;

	private void connDB() {
		// 1. ����̹� �ε�
		try {
			Class.forName(driver);
			/* System.out.println("Oracle ����̹� �ε� ����"); */

			// 2.����Ŭ ���� ����

			conn = DriverManager.getConnection(url, user, password);
			/* System.out.println("����Ŭ�� ���� ����"); */

			// 3. ���� ���� ��ü ����
			stmt = conn.createStatement();
			/* System.out.println("statement ��ü ����"); */

		} catch (Exception e) {

			e.printStackTrace();
		}

	} // connDB

	// ���̵� �н����� �Է��� �α����� Ȱ��ȭ�ϴ� �޼��� *
	public List login(String userid, String userpwd) {
		connDB();
		try {
		ServiceForMember service = new ServiceForMember();
		List<User_AccountVo> list = new ArrayList();
		User_AccountVo vo = new User_AccountVo();
		String _id = userid;
		String _pwd = userpwd;
		
		String query = "select * from user_table where id=? and pwd=?";

			
		pstmt = conn.prepareStatement(query);

		pstmt.setString(1, _id);
		pstmt.setString(2, _pwd);

		rs = pstmt.executeQuery();

		if (rs.next()) {
			if (rs.getString("id").equals(_id) && rs.getString("pwd").equals(_pwd)) {
				vo.setID(rs.getString("id"));
				vo.setName(rs.getString("name"));
				System.out.println("ȯ���մϴ�:'"+vo.getName()+"'��");
				return  service.session();
				
			
		    	} else {
			      System.out.println("���̵� Ȥ�� ��й�ȣ�� �� �� �Ǿ����ϴ�.");
			      return service.loginSession();
			      }
		
		  } else {
			rs.close();
			stmt.close();
			conn.close();
			System.out.println("���̵� Ȥ�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			return service.loginSession();
		  }

	 } catch (SQLException e) {
	   System.out.println("����ġ ���� ���� �߻�");
	   return null;
	   }
			
	} // �α��� �޼���
	
	// ȸ���ܿ��� ��й�ȣ�� �Է½� �ڽ��� ������ ��ȸ�ϰ� ������ ������ ��� �޼��� *
	public List serchingMember(String pwd) {
		connDB();
		Scanner sc = new Scanner(System.in);
		List<User_AccountVo> list = new ArrayList<>();
		ServiceForMember service = new ServiceForMember();
		String query = "select * from user_table where pwd=?";
		try {
			pstmt=conn.prepareStatement(query); // ������ ������
			pstmt.setString(1, pwd);
			rs=pstmt.executeQuery();
		if(rs.next()) {
			if(rs.getString("pwd").equals(pwd)) {
				User_AccountVo vo = new User_AccountVo();
				vo.setID(rs.getString("id"));
				vo.setPWD(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
				vo.setCitizen_Number(rs.getString("citizen_number"));
				vo.setUser_Tel(rs.getString("tell"));
				vo.setEmail(rs.getString("email"));
				vo.setAge(rs.getInt("age"));
				list.add(vo);
			
			for (int i = 0; i < list.size(); i++) { // ���� ���
				vo = list.get(i);
				System.out.println("����ȸ����������������");
				System.out.println("���̵�:" + vo.getID());
				System.out.println("�̸�:" + vo.getName());
				System.out.println("�ֹε�Ϲ�ȣ:" + vo.getCitizen_Number());
				System.out.println("��ȭ��ȣ:" + vo.getUser_Tel());
				System.out.println("�̸���:"+ vo.getEmail());
				System.out.println("����:" + vo.getAge());
				System.out.println("����������������������������");
			} // ȸ�� ���� ��ȸ for��
			
			System.out.println("ȸ�������� �����Ͻðڽ��ϱ�? Y/N"); // for�� ��
			String select1 = sc.nextLine();
			if (select1.equals("Y") || select1.equals("y") ) { // ����ڰ� Y�� �Է��ϸ�
				System.out.println("�����������Ͻ� ������ �������ּ���.��������");
				System.out.println("1.��ȭ��ȣ");
				System.out.println("2.�̸���");
				System.out.println("3.��ȭ��ȣ �� �̸��� ���ü���");
				System.out.println("4.ȸ�������� ���ư���");
				System.out.println("����������������������������������������������������");
				String select2 = sc.nextLine();
				
			if(select2.equals("1")) { // ��ȭ ��ȣ ������ ���� 1���� �Է��ϸ�
			String query2 = "update user_table set tell=? where id=?"; // ������ �غ��ϰ�
			System.out.println("ȸ������ ���� ��ȭ��ȣ��: "+"'"+vo.getUser_Tel()+"'"+"�Դϴ�. �����Ͻ� ��ȭ��ȣ�� �Է����ּ���.");
			System.out.println("��ȭ��ȣ ���� ��'010-XXXX-XXXX(������ ����)'���� ��Ŀ� �°� �Ͻʽÿ�. ->");
			String Editell = sc.nextLine();
			String tell_pattern = "^\\d{3}-\\d{4}-\\d{4}$"; // ��ȭ��ȣ 3�ڸ�-4�ڸ�-4�ڸ�
			
			if(Pattern.matches(tell_pattern, Editell)) {
				pstmt=conn.prepareStatement(query2);
				pstmt.setString(1, Editell); // ������ ��ȸ�ؼ� ������ vo id�� pstmt�� set�� �Է��ϰ�
				pstmt.setString(2, vo.getID());
				pstmt.executeUpdate();
				System.out.println("��������������ȭ��ȣ�� �Է��Ͻ� "+Editell+"�� ������ �Ϸ� �Ǿ����ϴ٦�����������");
				return service.session();
			} else {
				System.out.println("��Ŀ� �°� �ٽ� �Է��Ͻʽÿ�.");
				return serchingMember(vo.getPWD());
			}
			
			
			} else if(select2.equals("2")) { // 1�� ������
			String query3 = "update user_table set email=? where id=?";
			System.out.println("ȸ������ ���� �̸��� �ּҴ�:"+vo.getEmail()+"�Դϴ�. �����Ͻ� �̸��� �ּҸ� �Է����ּ���.");
			System.out.println("�̸��� ��Ħ�'oooo@naver.com'��@�� �����ؼ� �Է��� ���ּž� �մϴ�. ->");
			String Editemail = sc.nextLine();
			String email_pattern = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$"; // �� ��Ŀ� �°� �Է��� �ؾ��մϴ�..
			
			if(Pattern.matches(email_pattern,Editemail)) {
				pstmt=conn.prepareStatement(query3);
				pstmt.setString(1, Editemail);
				pstmt.setString(2, vo.getID());
				pstmt.executeUpdate();
				System.out.println("�������������̸����� �Է��Ͻ� "+Editemail+"�� ������ �Ϸ� �Ǿ����ϴ٦�����������");
				return service.session();	
			} else {
				System.out.println("��Ŀ� �°� �ٽ� �Է��Ͻʽÿ�.");
				return serchingMember(vo.getPWD());
			}
			
			
		} else if(select2.equals("3")) { // 2 ��������
				String query4 = "update user_table set tell=? , email=? where id=?";
				System.out.println("ȸ������ ���� ��ȭ��ȣ��:"+vo.getUser_Tel()+", �̸�����:"+vo.getEmail()+"�Դϴ�.");
				System.out.println("�����Ͻ� ��ȭ��ȣ�� �̸��� �ּҸ� �Է����ּ���");
				System.out.println("�����Ͻ� ��ȭ��ȣ��  ��'010-XXXX-XXXX(������ ����)'���� �°�,�̸�������'oooo@naver.com'��@�� �����ؼ� �Է��ϼ���");
				String tell_pattern = "^\\d{3}-\\d{4}-\\d{4}$";
				String email_pattern = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$";
			
				System.out.println("��ȭ��ȣ:");
				String allEditTell = sc.nextLine();
				System.out.println("�̸���:");
				String allEditemail = sc.nextLine();
			
			if(Pattern.matches(tell_pattern, allEditTell)&&(Pattern.matches(email_pattern,allEditemail))) {
				pstmt=conn.prepareStatement(query4);
				pstmt.setString(1, allEditTell);
				pstmt.setString(2, allEditemail);
				pstmt.setString(3, vo.getID());
				pstmt.executeUpdate();
				System.out.println("�����Է��Ͻ� ��ȭ��ȣ����"+allEditTell+"���� �Է��� �̸��Ϧ���"+allEditemail+"������ ������ �Ϸ��߽��ϴ�.��");
				return service.session();
				} else {
					System.out.println("�������������Է��Ͻ� ��ȭ��ȣ�� �̸����� ��Ŀ� �°� �ۼ����� �ʾҽ��ϴ�.�ٽ� �Է����ּ���.������������");
					return serchingMember(vo.getPWD());
					}
			} else if(select2.equals("4")) {
				return service.session();
			
			} else {
				System.out.println("������ ��� ���Դϴ�. �ٽ� �Է��ϼ���.");
				return serchingMember(vo.getPWD());
			}
			
			
			
		} else if(select1.equals("N") || select1.equals("n")) { // ȸ������ ���� Y/N if ������
			System.out.println("ȸ�������� ���ư��ϴ�.");
			return service.session();// N �ƴϿ� ȸ�������� ����
		} else {
			System.out.println("������ ��� ���Դϴ�. �ٽ� �Է��ϼ���.");
			return serchingMember(vo.getPWD());
		}
			
	}	else {
		System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�. �ٽ� �Է��ϼ���.");
		return service.session();
	}
			} // rs
			rs.close();
			pstmt.close();
			conn.close();
	} catch (SQLException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			System.out.println("����ġ ���� ������ �߻��߽��ϴ�.");
			return null; // �޼��� �ߴ� ù Ȩ�Ǵ����� �̵�
	}
		return list;
			
	} // ��ȸ �� ���� �޼���
	
	
	public List<User_AccountVo> membrInfo(String pwd) {
		connDB();
		List<User_AccountVo> list = new ArrayList<>();
		String query = "select * from user_table where pwd=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, pwd);
			rs=pstmt.executeQuery();
		while(rs.next()) {
			if(rs.getString("pwd").equals(pwd)) {
				User_AccountVo vo = new User_AccountVo();
				vo.setPWD(rs.getString("pwd"));
				list.add(vo);
				
			} else {
				System.out.println("�߸��� ��й�ȣ �Է�");
			}
		} // rs
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	// ��Ű�� �˻�- ��Ű�� �̸����� ã��, �̸� �ܿ� ����,�������� �پ��� ������ε� �˻� �� �� �ְ� �޼��� �߰� ����
	public List<PackageVo> serchingPack(String _packname) throws SQLException {
		connDB();
		ServiceForMember service = new ServiceForMember();
		List<PackageVo> list = new ArrayList<>();
		// ��Ű�������� ������ �ۼ�
		String query = "select * from package where packname=?";
		pstmt=conn.prepareStatement(query);
		pstmt.setString(1, _packname);
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			System.out.println("��Ű�� �̸� �˻� ����");
			PackageVo vo = new PackageVo();
			vo.setPackno(rs.getString("packno"));
			vo.setPackname(rs.getString("packname"));
			vo.setPackprice(rs.getString("packprice"));
			vo.setPackplan(rs.getString("packplan"));
			vo.setStarting_point(rs.getString("starting_point"));
			vo.setDestination(rs.getString("destination"));
			vo.setDeparture_time(rs.getString("departure_time"));
			vo.setArrival_time(rs.getString("arrival_time"));
			list.add(vo);
			
			rs.close();
			stmt.close();
			conn.close();
		} else {
			System.out.println("�������� �ʴ� ��Ű�� �Դϴ�. �ٽ� �˻��ϼ���.");
			return service.ReservationSession(); // sql ����Ű ���̺��� �˻� �� �� ���� ���̸� �ٽ� ���� �������� ����������.
		}
		return list; // �˻��� ��Ű�� ������ ���� ����Ʈ ���� �غ�
	} // �˻� �޼��� ��
	
	
	public List<PackageVo> information(String _packname) {  
															
		connDB();
		List<PackageVo> list = new ArrayList<>();
		ServiceForMember service = new ServiceForMember();
		String query = "select * from package where packname=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, _packname);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String packno = rs.getString("packno");
				String packname = rs.getString("packname");
				String packprice = rs.getString("packprice");
				String packplan = rs.getString("packplan");
				String starting_point = rs.getString("starting_point");
				String destination = rs.getString("destination");
				String departure_time = rs.getString("departure_time");
				String arrival_time = rs.getString("arrival_time");

				PackageVo vo = new PackageVo();
				vo.setPackno(packno);
				vo.setPackname(packname);
				vo.setPackprice(packprice);
				vo.setPackplan(packplan);
				vo.setStarting_point(starting_point);
				vo.setDestination(destination);
				vo.setDeparture_time(departure_time);
				vo.setArrival_time(arrival_time);

				list.add(vo);

				
			}
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
	// ��Ű�� �̸����� �˻��� ���ſ� ����ϴ� �޼���
	public List insertReser(String packname,String id,String pwd) {
		connDB();
		try {
		ServiceForMember service = new ServiceForMember();
		Scanner sc = new Scanner(System.in);
		System.out.println("====����Ȯ�� ������ ���� ���̵� ��й�ȣ�� �ٽ� �Է����ּ���.====");
		System.out.println("���̵�:");
		id=sc.nextLine();
		System.out.println("��й�ȣ:");
		pwd=sc.nextLine();
		
		String query1 = "select * from user_table where id=? and pwd=?";
		pstmt=conn.prepareStatement(query1);
		pstmt.setString(1, id);
		pstmt.setString(2, pwd);
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			if(rs.getString("id").equals(id) && rs.getString("pwd").equals(pwd)) { // ��ġ�ϸ�
			System.out.println("��������!!");
			String query3 = "select * from package where packname=?"; // ��Ű�� ���̺���
			 pstmt=conn.prepareStatement(query3);
			 pstmt.setString(1,packname);
			 rs=pstmt.executeQuery();
			 System.out.println("������ ���Ƚ��ϴ�!!");
			 
			 	if(rs.next()) {
			 		if(rs.getString("packname").equals(packname)) { // ��Ű�� �̸��� ��ġ�ϸ�
			 			PackageVo vo = new PackageVo();
			 			vo.setPackno(rs.getString("packno"));
			 			vo.setPackname(rs.getString("packname"));
			 			System.out.println("�ѹ�ȣ�� �̸��� ã�ҽ��ϴ�!!");
			 			
			 			String query2 = "insert into reservation values(reserno_seq.nextval,?,?,?,?)"; // ���� ���̺� �μ�Ʈ�� �����͸� �غ��ϰ�
			 			pstmt=conn.prepareStatement(query2);
			 			pstmt.setString(1, vo.getPackno());
			 			pstmt.setString(2, id);
			 			pstmt.setString(3, pwd);
			 			pstmt.setString(4, vo.getPackname());
			 			pstmt.executeUpdate(); // ���� ���̺� �÷��� ������ , packno fk , id fk , pwd fk �����̶� �ڵ尡 �����������ϴ�.
					
			 			System.out.println("���Ű� �Ϸ�Ǿ����ϴ�.!!");
			 			return service.session(); // �ٽ� ȸ�� �������� �̵�
			 			} // ��Ű�� �̸� Ȯ��  if
			 		} // ���̺� �˻� rs
				} else { // else ���� Ȯ�� �α��� ����
					System.out.println("���̵� �����ȣ ������ �����Ͽ� ȸ��â���� ���ư��ϴ�.");
					return service.session();
				}
			} rs.close();
			  stmt.close();
			  conn.close(); // id pwd ������ ���� rs.next ������

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("���� ����");
		}
		return null;
		
	} // ���� �޼��� ��

	
	public List<User_AccountVo> membrInfo(String id, String pwd) {
		connDB();
		List<User_AccountVo> list = new ArrayList<>();
		String query = "select * from user_table where id=? and pwd=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				id = rs.getString("id");
				pwd = rs.getNString("pwd");
				String name = rs.getString("name");
				String citizen_number = rs.getString("citizen_number");
				String tell = rs.getString("tell");
				String email = rs.getString("email");
				int age = rs.getInt("age");

				User_AccountVo vo = new User_AccountVo();
				vo.setID(id);
				vo.setPWD(pwd);
				vo.setName(name);
				vo.setCitizen_Number(citizen_number);
				vo.setUser_Tel(tell);
				vo.setEmail(email);
				vo.setAge(age);

				list.add(vo);
			}
			
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	} // �޼��� ��
	
	// ��ü ��Ű�� ��ǰ�� �����ִ� �޼����Դϴ�. //��Ű�� �˻� �� ������ ����ڰ� ������ ���� �ľ��� ���� 
	public List<PackageVo> AllpacakgeSerching(){
		connDB();
		List<PackageVo> list = new ArrayList<>();
		String query = "select * from package";
		
		try {
			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				PackageVo vo = new PackageVo();
				vo.setPackno(rs.getString("packno"));
				vo.setPackname(rs.getString("packname"));
				vo.setPackprice(rs.getString("packprice"));
				vo.setPackplan(rs.getString("packplan"));
				vo.setStarting_point(rs.getString("starting_point"));
				vo.setDestination(rs.getString("destination"));
				vo.setDeparture_time(rs.getString("departure_time"));
				vo.setArrival_time(rs.getString("arrival_time"));
				vo.setTourist_spot(rs.getString("Tourist_spot"));
				list.add(vo);
			}
			
			rs.close();
			stmt.close();
			conn.close();// rs
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	// ��Ű�� ������ ��ü ��Ȳ�� �����ִ� �޼��� ������ ���� �޼���
	public List<PackageVo> touristsopt(){ 
		connDB();
		List<PackageVo> list = new ArrayList<>();
		String query = "select tourist_spot from package";
		
		try {
			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				PackageVo vo= new PackageVo();
				vo.setTourist_spot(rs.getString("tourist_spot"));
				list.add(vo);
			}
			
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	// ������ �̸����� ��Ű�� ��ǰ�� ã�� �޼��� �Դϴ�. *
	public List<PackageVo> touristsoptSerching(String touristSerching) { 
		connDB();
		List<PackageVo> list = new ArrayList<>();
		ServiceForMember service = new ServiceForMember();
		String query = "select * from package where tourist_spot=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, touristSerching);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("tourist_spot").equals(touristSerching)) {
				PackageVo vo = new PackageVo();
				vo.setPackname(rs.getString("packname"));
				vo.setPackprice(rs.getString("packprice"));
				vo.setPackplan(rs.getString("packplan"));
				vo.setStarting_point(rs.getString("starting_point"));
				vo.setDestination(rs.getString("destination"));
				vo.setDeparture_time(rs.getString("departure_time"));
				vo.setArrival_time(rs.getString("arrival_time"));
				vo.setTourist_spot(rs.getString("Tourist_spot"));
				list.add(vo);
				
				}
		} else {
			System.out.println("�ش� �������� �������� �ʽ��ϴ�. Ȱ��ȭ �� �������� �˻����ּ���.");
			return service.ReservationSession();
		}	
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	} // �޼��� ��
	
	
	// �������� �˻��� ��Ű���� �����ϴ� �޼����Դϴ�.
	public List<PackageVo> touristResrvationInsert(String touristSerching,String id,String pwd) {
		connDB();
		try {
		ServiceForMember service = new ServiceForMember();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("====����Ȯ�� ������ ���� ���̵� ��й�ȣ�� �ٽ� �Է����ּ���.====");
		System.out.println("���̵�:");
		id=sc.nextLine();
		System.out.println("��й�ȣ:");
		pwd=sc.nextLine();
		
		String query1 = "select * from user_table where id=? and pwd=?";
		pstmt=conn.prepareStatement(query1);
		pstmt.setString(1, id);
		pstmt.setString(2, pwd);
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			if(rs.getString("id").equals(id) && rs.getString("pwd").equals(pwd)) { // ��ġ�ϸ�
				System.out.println("��������!!");
				String query3 = "select * from package where Tourist_spot=?"; // ��Ű�� ���̺���
			
				pstmt=conn.prepareStatement(query3);
				pstmt.setString(1,touristSerching);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					if(rs.getString("Tourist_spot").equals(touristSerching)) { // ������(������) ��ġ�ϸ�
					 PackageVo vo = new PackageVo();
					 vo.setPackno(rs.getString("packno"));
					 vo.setPackname(rs.getString("packname"));
					 
					 System.out.println("��Ű�� ��ȣ ã�� ����!!");
					 String query2 = "insert into reservation values(reserno_seq.nextval,?,?,?,?)"; // ���� ���̺� �μ�Ʈ�� �����͸� �غ��ϰ�
					
					 pstmt=conn.prepareStatement(query2);
					 pstmt.setString(1, vo.getPackno());
					 pstmt.setString(2, id);
					 pstmt.setString(3, pwd);
					 pstmt.setString(4, vo.getPackname());
					 pstmt.executeUpdate(); // ���� ���̺� �÷��� ������ , packno fk , id fk , pwd fk �����̶� �ڵ尡 �����������ϴ�.
					
					System.out.println("���Ű� �Ϸ�Ǿ����ϴ�.!!");
					return service.session(); // �ٽ� ȸ�� �������� �̵�
					
				 } else {
					 System.out.println("��� �������� �������� �ʽ��ϴ�.���� ������ �������� �˻����ּ���.");// ��Ű�� �̸� Ȯ��  if
					 return service.ReservationSession();
				 }
					
			 } // ���̺� �˻� rs
				
		} else { // else ���� Ȯ�� �α��� ����
			System.out.println("���̵� ��й�ȣ�� ���� �ʽ��ϴ�..");
			return service.session();
		}
			} else {
				System.out.println("���̵� �����ȣ ������ �����Ͽ� ȸ��â���� ���ư��ϴ�.");
				return service.session();
			}
			
		rs.close();
		stmt.close();
		conn.close();
		
		} catch (SQLException e) {
			System.out.println("�� �� ���� ������ ���� ������ �ߴ��մϴ�.");
			return null;
			
		}
		return null;
		
	} // ���� �޼��� ��
	
	
	// ��й�ȣ�� �Է¹ް� ������Ȳ�� �����ִ� �޼���
	public List<ReservationVO> ReserLookup(String pwd) { 
		connDB();
		ServiceForMember service = new ServiceForMember();
		ReservationVO vo = new ReservationVO();
		List<ReservationVO> list = new ArrayList<>();
		String query = "select * from reservation where pwd=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, pwd);
			rs=pstmt.executeQuery();
		if(rs.next()) {
			if(rs.getString("pwd").equals(pwd)) {
				System.out.println("������ȸ����");
				vo.setReserno(rs.getInt("reserno"));
				vo.setPackno(rs.getString("packno"));
				vo.setId(rs.getString("id"));
				vo.setPwd(rs.getString("pwd"));
				vo.setPackname(rs.getString("packname"));
				list.add(vo);
			
			} else{
				System.out.println("�߸��� �н����� �Դϴ�.");// �Է¹��� pwd ���̺� pwd ��
				return service.session();
			}
		
		} else {
			System.out.println("��Ű���� ���� ���� �ʽ��ϴ�.");
			return service.MemberReserLookup();
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
		} catch (SQLException e) {
			System.out.println("ġ���� ����");
			return null;
		}
		return list;
	}
	
	
	// ���Ź�ȣ�� �Է��� ��Ҹ� �ϴ� �޼���
	public List<ReservationVO> DeleteResr(String pwd,int reserno) {
		connDB();
		ServiceForMember service = new ServiceForMember();
		String query = "select * from reservation where reserno=? and pwd=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, reserno);
			pstmt.setString(2, pwd);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getInt("reserno")==reserno && rs.getString("pwd").equals(pwd)) {
					String delete = "delete from reservation where reserno=?"; // ���� ���̺� ������ �ϴ� ������ ������.
					pstmt=conn.prepareStatement(delete);
					pstmt.setInt(1, reserno);
					pstmt.executeUpdate();
					System.out.println("������ ��Ű���� ����߽��ϴ�");
					return service.session();	
				}
				
			} else {
			  System.out.println("�ش� �����ȣ�� �������� �ʽ��ϴ�."); // rs
			  return service.MemberReserLookup();
			} // else ��
			
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("�߸��� �Է� ��");
			try {
				return service.session();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} // ĳġ ��
		return null;
		
	} // ���� ��� �޼��� ������
	
	public List DeletePack() {
		connDB();
		Scanner sc = new Scanner(System.in);
		System.out.println("������ ������ ��ȣ�� �Է��ϼ���.");
		String packno = sc.nextLine();
		
		String query = "select * from package";
		try {
			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			
		if(rs.next()) {
			if(rs.getString("packno").equals(packno)) {
				String delete = "delete from package where packno=?";
				pstmt=conn.prepareStatement(delete);
				pstmt.setString(1, packno);
				pstmt.executeQuery();
				System.out.println("�ش� ��Ű���� �����߽��ϴ�!!");
			
			} else {
				System.out.println("���� ���� �ʴ� ������ ��ȣ �Դϴ�.");
				return DeletePack();
			}
			
		} // rs
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;//������ �� ���� null������ �߽��ϴ�. �ʿ信 ���� ���� ���� ���� �Ͻø� �� �� �����ϴ�.;
		
	} // �޼���
		
		
			
			
	
	
	
	/*
	 * public List DeletePack1() { connDB(); Scanner sc = new Scanner(System.in);
	 * String packno;
	 * 
	 * 
	 * 
	 * 
	 * System.out.println("�����Ͻ� ��Ű���� ��ȣ�� �Է����ּ���:"); packno=sc.nextLine();
	 * 
	 * String query = "delete from package where packno=?"; try {
	 * pstmt=conn.prepareStatement(query); pstmt.setString(1, packno);
	 * pstmt.executeQuery(); System.out.println("��Ű���� ���� �߽��ϴ�.!!");
	 * 
	 * } catch (SQLException e) { System.out.println("�߸��� sql�� �Դϴ�.");
	 * e.printStackTrace(); }
	 * 
	 * 
	 * return null; // ������ �� ���� null������ �߽��ϴ�. �ʿ信 ���� ���� ���� ���� �Ͻø� �� �� �����ϴ�. }
	 */
	
	
	
	
} // class