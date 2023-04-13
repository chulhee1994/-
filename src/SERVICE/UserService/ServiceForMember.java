package SERVICE.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import DAO.UserDao.UserDao__;
import Main.Main;
import VO.PackageVo;
import VO.ReservationVO;
import VO.User_AccountVo;


public class ServiceForMember { // �������� Ȩ������ ���� �� ������ �ϴ� Ŭ����. dao���� ���� ����� Ui�� ����.
	Scanner sc = new Scanner(System.in);
	private String id;
	private String pwd;
	public String packname;
	public User_AccountVo meberVO = new User_AccountVo();
	Main main = new Main();

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public List loginSession() throws SQLException {
		Scanner sc = new Scanner(System.in);
		UserDao__ dao = new UserDao__();
		
		System.out.println("�������������������������α���â��������������������������");
		System.out.println("ID �Է�:");
		id = sc.nextLine();
		System.out.println("��й�ȣ �Է�:");
		pwd = sc.nextLine();
		System.out.println("������������������������������������������������������������");
		
		return dao.login(id, pwd);

	} // �α��� ����view ��

	
	public List session() throws SQLException {
		Scanner sc = new Scanner(System.in);
		UserDao__ dao = new UserDao__();
		List<User_AccountVo> list = new ArrayList<>();
		User_AccountVo vo = new User_AccountVo();
		String select;
		
		try {
		System.out.println();
		System.out.println("��������������ȸ���ܦ���������������������");
		System.out.println("1.��Ű�� �˻��� ����");
		System.out.println("2.ȸ�� ���� ��ȸ �� ����");
		System.out.println("3.���� ��Ȳ �� ���");
		System.out.println("4.�α׾ƿ�");
		System.out.println("��������������������������������������������");
		select = sc.nextLine();

		
		if (select.equals("1")) {
			return ReservationSession();
		} 
		
		else if (select.equals("2")) {
			
			System.out.println("��������ȸ�� ���� ��ȸ �� �����ܦ�����");
			System.out.println("��й�ȣ�� �Է����ּ���.:");
			String pwd = sc.nextLine();
			
			list = dao.serchingMember(pwd); // ȸ�� ���� ��ȸ�� ���� �޼���� �̵�
			System.out.println();
			
			
		} else if (select.equals("3")) {
			return MemberReserLookup();
			
		} else if (select.equals("4")) {
			return null;
			
		} else {
			System.out.println("�߸��� �Է� �� �Դϴ�.");
			return session();
		}
		
		} catch(Exception e) {
			System.out.println("�� �� �Է��ϼ���.");
			return null;
		}
		
		return null; 
	} // sessiong ������

	
	public List ReservationSession() throws SQLException { // ��Ű�� ��ȸ�� ���� ����Ʈ ��� ����
		Scanner sc = new Scanner(System.in);
		UserDao__ dao = new UserDao__();
		PackageVo packvo = new PackageVo();
		List<PackageVo> packlist = new ArrayList<>();
		packlist = dao.AllpacakgeSerching();
		
		try {
		System.out.println("���������������� �Խ����� ��Ű�� ��Ϧ���������������������������������������������������������������������������������������������");
		for(int i=0; i<packlist.size(); i++) {
		packvo=packlist.get(i);
		System.out.print("��ǰ��ȣ:"+packvo.getPackno());
		System.out.print("  "+" ��Ű�� �̸�:"+packvo.getPackname());
		System.out.print("  "+" ��Ű�� ����:"+packvo.getPackprice());
		System.out.print("  "+" ����:"+packvo.getPackplan());
		System.out.print("  "+" ������:"+packvo.getTourist_spot()+"\n");
		System.out.println("������������������������������������������������������������������������������������������������������������������������������������������");
		System.out.println();
		  } // for��
		System.out.println("����>���Ͻô� ��Ű���� �����ϼ���!����������");
		System.out.println("1.��Ű�� �̸����� �˻�");
		System.out.println("2.������ �̸����� �˻�");
		System.out.println("3.ȸ�������� ���ư���");
		System.out.println("������������������������������������������������������");
		String select1 = sc.nextLine();
		
		// ���ǹ� ����
		if(select1.equals("1")) {
		System.out.println("��Ű�� �̸��� �Է��ϼ���:");
		String packname = sc.nextLine();
		System.out.println("����������������������������������������������������������������������������������");
		
		packlist = dao.serchingPack(packname); // ������ ���� ������ ��Ű�� ������ ����Ʈ�� ��´�.
		
		for (int i = 0; i < packlist.size(); i++){
			packvo = packlist.get(i);
			System.out.println("��Ű�� �̸�:����"+packvo.getPackname()+"����");
			System.out.println("����:����"+packvo.getPackprice()+"����");
			System.out.println("����:����"+packvo.getPackplan()+"����");
			System.out.println("��� ����:����"+packvo.getStarting_point()+"����");
			System.out.println("���� ����:����"+packvo.getDestination()+"����");
			System.out.println("��� ��¥:����"+packvo.getDeparture_time()+"����");
			System.out.println("���� ��¥:����"+packvo.getArrival_time()+"����");
			} // for��
		
		  	System.out.println("��������������������������������������������������������������������������������");
		  	System.out.println("�������� ��ǰ�� ���� �Ͻðڽ��ϱ�?������");
		  	System.out.println("1.���� �˻��� ��ǰ�� �����Ѵ�.");
		  	System.out.println("2.��Ű�� �˻������� ���ư���.");
		  	System.out.println("3.ȸ�������� ���ư���.");
		  	System.out.println("����������������������������������������������������");
		  	
		  	String select2 = sc.nextLine(); // ������ �̸����� �˻��� 2���� �Է������� �Է� ���� ���� select2
		  	
		if(select2.equals("1")) {
		   dao.insertReser(packname, id, pwd);
			
		}  else if(select2.equals("2")) {
			return ReservationSession();
			
		
		}  else if(select2.equals("3")) {
			return session();
		
		}  else { // ������ �� 1,2,3�� ����� �ٽ� ���� ���ư���.
			System.out.println();
			System.out.println("�߸� �Էµ� �� �Դϴ�.");
			return packlist = dao.serchingPack(packname);
		}
		
		
		} else if(select1.equals("2")) { // 2�� ���� ������ �̸����� �˻� ������ �ֽ� select1�̴�.
			PackageVo vo = new PackageVo();
			packlist=dao.touristsopt(); // ���� Ȱ��ȭ�Ǿ� �ִ� �������� �����༭ ����ڰ� ������������ �˻��� �� �ְ� ���ݴϴ�.
			
			System.out.println("���� Ȱ��ȭ �� ������ �Դϴ�."); // ���� Ȱ��ȭ�� �������� �����ݴϴ�.
			for(int i=0; i<packlist.size(); i++) {
				vo = packlist.get(i);
				System.out.println(vo.getTourist_spot());
			} // for�� 
			
			System.out.println("���� Ȱ��ȭ�� ������ �̸��� ������ �Է����ּ���:");
			/* System.out.println("'�̸�-�̸�'������ �߰��� �������� �߰��ؼ� �־ �Է��ؾ��մϴ�."); */
			String touristSerching = sc.nextLine();
			/* String touristSerching_pattern = "[��-�R]{5}"; */
			packlist=dao.touristsoptSerching(touristSerching);
			System.out.println("�������� �˻� ����");
			
			for(int i=0; i<packlist.size(); i++) {
				vo = packlist.get(i);
				System.out.println("��Ű�� �̸�:����"+packvo.getPackname()+"����");
				System.out.println("����:����"+packvo.getPackprice()+"����");
				System.out.println("����:����"+packvo.getPackplan()+"����");
				System.out.println("��� ����:����"+packvo.getStarting_point()+"����");
				System.out.println("���� ����:����"+packvo.getDestination()+"����");
				System.out.println("��� ��¥:����"+packvo.getDeparture_time()+"����");
				System.out.println("���� ��¥:����"+packvo.getArrival_time()+"����");
				System.out.println("������:����"+packvo.getTourist_spot()+"����");
			}// for�� �������� �˻� ��ȸ 
			
			/*if(Pattern.matches(touristSerching_pattern, touristSerching))  {*/
				 // �������� �˻��� ã�� ��Ű�� ������ list�� ��´�.
				
			/*
			 * } else { System.out.println("�˻� ��Ŀ� �°� �Է��ϼ���."); return ReservationSession();
			 * }
			 */			
			System.out.println();
		  	System.out.println("�������������� ��ǰ�� ���� �Ͻðڽ��ϱ�?����������");
		  	System.out.println("1.���� �˻��� ��ǰ�� �����Ѵ�.");
		  	System.out.println("2.��Ű�� �˻������� ���ư���.");
		  	System.out.println("3.ȸ�������� ���ư���.");
		  	System.out.println("����������������������������������������������������������");
		  	
		  	String select3 = sc.nextLine(); // select3 �ָ�
			
		  	if(select3.equals("1")) {
		  		
		  	return packlist = dao.touristResrvationInsert(touristSerching, id, pwd);
		  	
		  	} else if(select3.equals("2")) {

		  	return ReservationSession();
		  		
		  	} else if(select3.equals("3")) {
		  		
		  	return session();
		  	
		  	}
		  	
		  	else {
		  	System.out.println("�߸� �Է��� ���Դϴ�.");
		  	
		  	return ReservationSession();
		  	}
		  	
		} else if(select1.equals("3")) { // ���� selcet1�̹Ƿ�  ��ó���� �Է¹��� �����̱⿡ ���� �Ͻʽô�.
			return session();
			
		} else {
			System.out.println("�߸� �Է��� ���Դϴ�.");
	  		return ReservationSession();
		}
		
		} catch (Exception e) {
			System.out.println("����ġ ���� ����");
			return session();
		}
		return packlist;
	} // ��Ű�� ��ȸ�� ���� ����	
		
		
	public List MemberReserLookup() throws SQLException { // ȸ���� ������ ��Ű�� ��ȸ�� ���
		Scanner sc = new Scanner(System.in);
		UserDao__ dao = new UserDao__();
		ReservationVO vo = new ReservationVO();
		List<ReservationVO> list = new ArrayList<>();
		List<User_AccountVo> list2 = new ArrayList<>();
	
		System.out.println("����������ȸ������ ������ ��ǰ�� ��ȸ�ϴ� �ܦ���������");
		System.out.println("1.���� ��ȸ �� ���");
		System.out.println("2.ȸ�������� ���ư���");
		String select1 = sc.nextLine();
	
	if(select1.equals("1")) {
	
		System.out.println("��й�ȣ�� �Է����ּ���:");
		String pwd = sc.nextLine();
		System.out.println();
		
		list=dao.ReserLookup(pwd); // ��й�ȣ�� �Է������� ������ ��Ű���� �������� �޼����Դϴ�.
		for(int i=0; i<list.size(); i++) {
		vo=list.get(i);
		System.out.println("����������������Ȳ������������������");
		System.out.println("ȸ������ �����ȣ:"+vo.getReserno());
		System.out.println("��Ű�� ��ȣ:"+vo.getPackno());
		System.out.println("�����Ͻ� ȸ������ ID:"+vo.getId());
		System.out.println("���� ��Ű�� �̸�:"+vo.getPackname());
		} // for��
	
	   System.out.println("��������������������������������������������������");
	   System.out.println("1.ȸ���������� ���ư���");
	   System.out.println("2.��������ϱ�");
	   System.out.println("��������������������������������������������������");
	   String select2 = sc.nextLine();
	
	if(select2.equals("1")) {
	return session(); // ȸ�������� ���ư��ϴ�.
		
	} else if(select2.equals("2")) {
		System.out.println("�����Ͻ� ���� ��Ű���� ���Ź�ȣ�� �Է��ϼ���.");
		int reserno = sc.nextInt();
		dao.DeleteResr(pwd, reserno);
	} else {
		System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		return session();
	}
		
	} else if(select1.equals("2")) {
		return session();
		
	} else { // select1 ������ ��� ���� ���� else
		System.out.println("�߸��� �� �Դϴ�.");
		return session();
	}
	
	return list;
	
	} // ������ȸ �� �޼����� ��
} // ���� Ŭ������