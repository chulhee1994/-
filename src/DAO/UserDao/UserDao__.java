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
	static String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 위치 xe 서비스 이름
	static String user = "travel";
	static String password = "1234";

	Connection conn;
	Statement stmt;
	ResultSet rs;
	PreparedStatement pstmt;

	private void connDB() {
		// 1. 드라이버 로딩
		try {
			Class.forName(driver);
			/* System.out.println("Oracle 드라이버 로딩 성공"); */

			// 2.오라클 연결 성공

			conn = DriverManager.getConnection(url, user, password);
			/* System.out.println("오라클에 연결 성공"); */

			// 3. 쿼리 관리 객체 생성
			stmt = conn.createStatement();
			/* System.out.println("statement 객체 생성"); */

		} catch (Exception e) {

			e.printStackTrace();
		}

	} // connDB

	// 아이디 패스워드 입력해 로그인을 활성화하는 메서드 *
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
				System.out.println("환영합니다:'"+vo.getName()+"'님");
				return  service.session();
				
			
		    	} else {
			      System.out.println("아이디 혹은 비밀번호가 잘 못 되었습니다.");
			      return service.loginSession();
			      }
		
		  } else {
			rs.close();
			stmt.close();
			conn.close();
			System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다.");
			return service.loginSession();
		  }

	 } catch (SQLException e) {
	   System.out.println("예기치 못한 오류 발생");
	   return null;
	   }
			
	} // 로그인 메서드
	
	// 회원단에서 비밀번호를 입력시 자신의 정보를 조회하고 수정할 권한을 얻는 메서드 *
	public List serchingMember(String pwd) {
		connDB();
		Scanner sc = new Scanner(System.in);
		List<User_AccountVo> list = new ArrayList<>();
		ServiceForMember service = new ServiceForMember();
		String query = "select * from user_table where pwd=?";
		try {
			pstmt=conn.prepareStatement(query); // 쿼리를 날리고
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
			
			for (int i = 0; i < list.size(); i++) { // 정보 출력
				vo = list.get(i);
				System.out.println("──회원정보─────");
				System.out.println("아이디:" + vo.getID());
				System.out.println("이름:" + vo.getName());
				System.out.println("주민등록번호:" + vo.getCitizen_Number());
				System.out.println("전화번호:" + vo.getUser_Tel());
				System.out.println("이메일:"+ vo.getEmail());
				System.out.println("나이:" + vo.getAge());
				System.out.println("──────────────");
			} // 회원 정보 조회 for문
			
			System.out.println("회원정보를 수정하시겠습니까? Y/N"); // for문 끝
			String select1 = sc.nextLine();
			if (select1.equals("Y") || select1.equals("y") ) { // 사용자가 Y를 입력하면
				System.out.println("───수정하실 내용을 선택해주세요.────");
				System.out.println("1.전화번호");
				System.out.println("2.이메일");
				System.out.println("3.전화번호 및 이메일 동시수정");
				System.out.println("4.회원단으로 돌아가기");
				System.out.println("──────────────────────────");
				String select2 = sc.nextLine();
				
			if(select2.equals("1")) { // 전화 번호 수정을 원해 1번을 입력하면
			String query2 = "update user_table set tell=? where id=?"; // 쿼리를 준비하고
			System.out.println("회원님의 현재 전화번호는: "+"'"+vo.getUser_Tel()+"'"+"입니다. 변경하실 전화번호를 입력해주세요.");
			System.out.println("전화번호 예시 ─'010-XXXX-XXXX(하이픈 포함)'─이 양식에 맞게 하십시오. ->");
			String Editell = sc.nextLine();
			String tell_pattern = "^\\d{3}-\\d{4}-\\d{4}$"; // 전화번호 3자리-4자리-4자리
			
			if(Pattern.matches(tell_pattern, Editell)) {
				pstmt=conn.prepareStatement(query2);
				pstmt.setString(1, Editell); // 위에서 조회해서 저장한 vo id를 pstmt에 set에 입력하고
				pstmt.setString(2, vo.getID());
				pstmt.executeUpdate();
				System.out.println("──────전화번호를 입력하신 "+Editell+"로 수정이 완료 되었습니다──────");
				return service.session();
			} else {
				System.out.println("양식에 맞게 다시 입력하십시오.");
				return serchingMember(vo.getPWD());
			}
			
			
			} else if(select2.equals("2")) { // 1번 스코프
			String query3 = "update user_table set email=? where id=?";
			System.out.println("회원님의 현재 이메일 주소는:"+vo.getEmail()+"입니다. 변경하실 이메일 주소를 입력해주세요.");
			System.out.println("이메일 양식─'oooo@naver.com'─@를 포함해서 입력을 해주셔야 합니다. ->");
			String Editemail = sc.nextLine();
			String email_pattern = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$"; // 이 양식에 맞게 입력을 해야합니다..
			
			if(Pattern.matches(email_pattern,Editemail)) {
				pstmt=conn.prepareStatement(query3);
				pstmt.setString(1, Editemail);
				pstmt.setString(2, vo.getID());
				pstmt.executeUpdate();
				System.out.println("──────이메일을 입력하신 "+Editemail+"로 수정이 완료 되었습니다──────");
				return service.session();	
			} else {
				System.out.println("양식에 맞게 다시 입력하십시오.");
				return serchingMember(vo.getPWD());
			}
			
			
		} else if(select2.equals("3")) { // 2 번스코프
				String query4 = "update user_table set tell=? , email=? where id=?";
				System.out.println("회원님의 현재 전화번호는:"+vo.getUser_Tel()+", 이메일은:"+vo.getEmail()+"입니다.");
				System.out.println("변경하실 전화번호와 이메일 주소를 입력해주세요");
				System.out.println("변경하실 전화번호는  ─'010-XXXX-XXXX(하이픈 포함)'─에 맞게,이메일은─'oooo@naver.com'─@를 포함해서 입력하세요");
				String tell_pattern = "^\\d{3}-\\d{4}-\\d{4}$";
				String email_pattern = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$";
			
				System.out.println("전화번호:");
				String allEditTell = sc.nextLine();
				System.out.println("이메일:");
				String allEditemail = sc.nextLine();
			
			if(Pattern.matches(tell_pattern, allEditTell)&&(Pattern.matches(email_pattern,allEditemail))) {
				pstmt=conn.prepareStatement(query4);
				pstmt.setString(1, allEditTell);
				pstmt.setString(2, allEditemail);
				pstmt.setString(3, vo.getID());
				pstmt.executeUpdate();
				System.out.println("──입력하신 전화번호──"+allEditTell+"─및 입력한 이메일──"+allEditemail+"──로 수정을 완료했습니다.─");
				return service.session();
				} else {
					System.out.println("──────입력하신 전화번호나 이메일이 양식에 맞게 작성되지 않았습니다.다시 입력해주세요.──────");
					return serchingMember(vo.getPWD());
					}
			} else if(select2.equals("4")) {
				return service.session();
			
			} else {
				System.out.println("범위를 벗어난 값입니다. 다시 입력하세요.");
				return serchingMember(vo.getPWD());
			}
			
			
			
		} else if(select1.equals("N") || select1.equals("n")) { // 회원정보 수정 Y/N if 스코프
			System.out.println("회원단으로 돌아갑니다.");
			return service.session();// N 아니요 회원단으로 복귀
		} else {
			System.out.println("범위를 벗어난 값입니다. 다시 입력하세요.");
			return serchingMember(vo.getPWD());
		}
			
	}	else {
		System.out.println("비밀번호가 일치하지 않습니다. 다시 입력하세요.");
		return service.session();
	}
			} // rs
			rs.close();
			pstmt.close();
			conn.close();
	} catch (SQLException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			System.out.println("예상치 못한 오류가 발생했습니다.");
			return null; // 메서드 중단 첫 홈피단으로 이동
	}
		return list;
			
	} // 조회 및 수정 메서드
	
	
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
				System.out.println("잘못된 비밀번호 입력");
			}
		} // rs
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	// 패키지 검색- 패키지 이름으로 찾기, 이름 외에 가격,여행지등 다양한 방식으로도 검색 할 수 있게 메서드 추가 예정
	public List<PackageVo> serchingPack(String _packname) throws SQLException {
		connDB();
		ServiceForMember service = new ServiceForMember();
		List<PackageVo> list = new ArrayList<>();
		// 패키지명으로 쿼리문 작성
		String query = "select * from package where packname=?";
		pstmt=conn.prepareStatement(query);
		pstmt.setString(1, _packname);
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			System.out.println("패키지 이름 검색 성공");
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
			System.out.println("존재하지 않는 패키지 입니다. 다시 검색하세요.");
			return service.ReservationSession(); // sql 패지키 테이블에서 검색 할 수 없는 값이면 다시 예매 세션으로 보내버린다.
		}
		return list; // 검색한 패키지 정보를 담을 리스트 리턴 준비
	} // 검색 메서드 끝
	
	
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

	
	// 패키지 이름으로 검색후 예매에 사용하는 메서드
	public List insertReser(String packname,String id,String pwd) {
		connDB();
		try {
		ServiceForMember service = new ServiceForMember();
		Scanner sc = new Scanner(System.in);
		System.out.println("====예매확정 보안을 위해 아이디 비밀번호를 다시 입력해주세요.====");
		System.out.println("아이디:");
		id=sc.nextLine();
		System.out.println("비밀번호:");
		pwd=sc.nextLine();
		
		String query1 = "select * from user_table where id=? and pwd=?";
		pstmt=conn.prepareStatement(query1);
		pstmt.setString(1, id);
		pstmt.setString(2, pwd);
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			if(rs.getString("id").equals(id) && rs.getString("pwd").equals(pwd)) { // 일치하면
			System.out.println("검증성공!!");
			String query3 = "select * from package where packname=?"; // 패키지 테이블에서
			 pstmt=conn.prepareStatement(query3);
			 pstmt.setString(1,packname);
			 rs=pstmt.executeQuery();
			 System.out.println("쿼리를 날렸습니다!!");
			 
			 	if(rs.next()) {
			 		if(rs.getString("packname").equals(packname)) { // 패키지 이름이 일치하면
			 			PackageVo vo = new PackageVo();
			 			vo.setPackno(rs.getString("packno"));
			 			vo.setPackname(rs.getString("packname"));
			 			System.out.println("팩번호와 이름을 찾았습니다!!");
			 			
			 			String query2 = "insert into reservation values(reserno_seq.nextval,?,?,?,?)"; // 예매 테이블에 인설트할 데이터를 준비하고
			 			pstmt=conn.prepareStatement(query2);
			 			pstmt.setString(1, vo.getPackno());
			 			pstmt.setString(2, id);
			 			pstmt.setString(3, pwd);
			 			pstmt.setString(4, vo.getPackname());
			 			pstmt.executeUpdate(); // 예매 테이블 컬럼이 시퀸스 , packno fk , id fk , pwd fk 형식이라 코드가 난잡해졌습니다.
					
			 			System.out.println("예매가 완료되었습니다.!!");
			 			return service.session(); // 다시 회원 세션으로 이동
			 			} // 패키지 이름 확인  if
			 		} // 테이블 검색 rs
				} else { // else 에매 확인 로그인 실패
					System.out.println("아이디 비빌번호 인증이 실패하여 회원창으로 돌아갑니다.");
					return service.session();
				}
			} rs.close();
			  stmt.close();
			  conn.close(); // id pwd 인증을 위한 rs.next 스코프

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("예매 실패");
		}
		return null;
		
	} // 예매 메서드 끝

	
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
	} // 메서드 끝
	
	// 전체 패키지 상품을 보여주는 메서드입니다. //패키지 검색 및 예매전 사용자가 빠르게 정보 파악을 위해 
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
	
	// 패키지 여행지 전체 현황을 보여주는 메서드 여행지 공지 메서드
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
	
	
	// 여행지 이름으로 패키지 상품을 찾는 메서드 입니다. *
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
			System.out.println("해당 여행지는 존재하지 않습니다. 활성화 된 여행지를 검색해주세요.");
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
		
	} // 메서드 끝
	
	
	// 여행지로 검색한 패키지를 예매하는 메서드입니다.
	public List<PackageVo> touristResrvationInsert(String touristSerching,String id,String pwd) {
		connDB();
		try {
		ServiceForMember service = new ServiceForMember();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("====예매확정 보안을 위해 아이디 비밀번호를 다시 입력해주세요.====");
		System.out.println("아이디:");
		id=sc.nextLine();
		System.out.println("비밀번호:");
		pwd=sc.nextLine();
		
		String query1 = "select * from user_table where id=? and pwd=?";
		pstmt=conn.prepareStatement(query1);
		pstmt.setString(1, id);
		pstmt.setString(2, pwd);
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			if(rs.getString("id").equals(id) && rs.getString("pwd").equals(pwd)) { // 일치하면
				System.out.println("검증성공!!");
				String query3 = "select * from package where Tourist_spot=?"; // 패키지 테이블에서
			
				pstmt=conn.prepareStatement(query3);
				pstmt.setString(1,touristSerching);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					if(rs.getString("Tourist_spot").equals(touristSerching)) { // 여행지(관광지) 일치하면
					 PackageVo vo = new PackageVo();
					 vo.setPackno(rs.getString("packno"));
					 vo.setPackname(rs.getString("packname"));
					 
					 System.out.println("패키지 번호 찾기 성공!!");
					 String query2 = "insert into reservation values(reserno_seq.nextval,?,?,?,?)"; // 예매 테이블에 인설트할 데이터를 준비하고
					
					 pstmt=conn.prepareStatement(query2);
					 pstmt.setString(1, vo.getPackno());
					 pstmt.setString(2, id);
					 pstmt.setString(3, pwd);
					 pstmt.setString(4, vo.getPackname());
					 pstmt.executeUpdate(); // 예매 테이블 컬럼이 시퀸스 , packno fk , id fk , pwd fk 형식이라 코드가 난잡해졌습니다.
					
					System.out.println("예매가 완료되었습니다.!!");
					return service.session(); // 다시 회원 세션으로 이동
					
				 } else {
					 System.out.println("헤당 여행지는 존재하지 않습니다.현재 개설된 여행지로 검색해주세요.");// 패키지 이름 확인  if
					 return service.ReservationSession();
				 }
					
			 } // 테이블 검색 rs
				
		} else { // else 에매 확인 로그인 실패
			System.out.println("아이디나 비밀번호가 맞지 않습니다..");
			return service.session();
		}
			} else {
				System.out.println("아이디 비빌번호 인증이 실패하여 회원창으로 돌아갑니다.");
				return service.session();
			}
			
		rs.close();
		stmt.close();
		conn.close();
		
		} catch (SQLException e) {
			System.out.println("알 수 없는 오류로 인해 서버를 중단합니다.");
			return null;
			
		}
		return null;
		
	} // 예매 메서드 끝
	
	
	// 비밀번호를 입력받고 예매현황을 보여주는 메서드
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
				System.out.println("예매조회성공");
				vo.setReserno(rs.getInt("reserno"));
				vo.setPackno(rs.getString("packno"));
				vo.setId(rs.getString("id"));
				vo.setPwd(rs.getString("pwd"));
				vo.setPackname(rs.getString("packname"));
				list.add(vo);
			
			} else{
				System.out.println("잘못된 패스워드 입니다.");// 입력받은 pwd 테이블 pwd 비교
				return service.session();
			}
		
		} else {
			System.out.println("패키지가 존재 하지 않습니다.");
			return service.MemberReserLookup();
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
		} catch (SQLException e) {
			System.out.println("치명적 오류");
			return null;
		}
		return list;
	}
	
	
	// 예매번호를 입력해 취소를 하는 메서드
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
					String delete = "delete from reservation where reserno=?"; // 예약 테이블 삭제를 하는 쿼리를 날린다.
					pstmt=conn.prepareStatement(delete);
					pstmt.setInt(1, reserno);
					pstmt.executeUpdate();
					System.out.println("예매한 패키지를 취소했습니다");
					return service.session();	
				}
				
			} else {
			  System.out.println("해당 예약번호가 존재하지 않습니다."); // rs
			  return service.MemberReserLookup();
			} // else 끝
			
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("잘못된 입력 값");
			try {
				return service.session();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} // 캐치 끝
		return null;
		
	} // 예매 취소 메서드 스코프
	
	public List DeletePack() {
		connDB();
		Scanner sc = new Scanner(System.in);
		System.out.println("삭제할 패지지 번호를 입력하세요.");
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
				System.out.println("해당 패키지를 삭제했습니다!!");
			
			} else {
				System.out.println("존재 하지 않는 패지지 번호 입니다.");
				return DeletePack();
			}
			
		} // rs
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;//구조를 잘 몰라서 null값으로 했습니다. 필요에 따라 리턴 값을 변경 하시면 될 것 같습니다.;
		
	} // 메서드
		
		
			
			
	
	
	
	/*
	 * public List DeletePack1() { connDB(); Scanner sc = new Scanner(System.in);
	 * String packno;
	 * 
	 * 
	 * 
	 * 
	 * System.out.println("삭제하실 패키지의 번호를 입력해주세요:"); packno=sc.nextLine();
	 * 
	 * String query = "delete from package where packno=?"; try {
	 * pstmt=conn.prepareStatement(query); pstmt.setString(1, packno);
	 * pstmt.executeQuery(); System.out.println("패키지를 삭제 했습니다.!!");
	 * 
	 * } catch (SQLException e) { System.out.println("잘못된 sql문 입니다.");
	 * e.printStackTrace(); }
	 * 
	 * 
	 * return null; // 구조를 잘 몰라서 null값으로 했습니다. 필요에 따라 리턴 값을 변경 하시면 될 것 같습니다. }
	 */
	
	
	
	
} // class