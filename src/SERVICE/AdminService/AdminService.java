package SERVICE.AdminService;

import java.text.SimpleDateFormat;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DAO.AdminDAO.AdminDao;
import DAO.AdminDAO.UserDao;
import VO.AdminAccountVo;
import VO.NoticeVo;
import VO.User_AccountVo;

public class AdminService {
	Scanner sc = new Scanner(System.in);
	AdminDao dao = new AdminDao();
	UserDao UserDAO = new UserDao();
	
	
	
	String id;
	String pwd;
	AdminAccountVo vo;
	
	
	UserService User = new UserService();
	NoticeService Notice = new NoticeService();
	PackageService Package = new PackageService();
	
	public void sign() {
		System.out.println("��������������������������������������������������������");
		System.out.println("��          �� �� �� ��               ��");
		System.out.println("��������������������������������������������������������");
	}
	
	private AdminAccountVo login() {
		List<AdminAccountVo> Admin_List = dao.Admin_List();
		
		
		try {
			System.out.println("���̵� �Է����ּ���.>>");
			id = sc.nextLine();
			System.out.println("��й�ȣ�� �Է����ּ���.>>");
			pwd =sc.nextLine();
			
			for(int i =0; i<Admin_List.size(); i++) {
				
				if(Admin_List.get(i).getID().equals(id) && Admin_List.get(i).getPWD().equals(pwd)) {
					
					
					System.out.println("�α�����.....");
					//Thread.sleep(2000);
					vo = Admin_List.get(i);
				}
				
			}
			
		} catch (Exception e) {
			System.out.println("�߸� �Է��ϼ̽��ϴ�.");
		}
		return vo;
		
	}//login

	
	
	
	
	//���� �α��ν� ȭ�� �޼��� 
	public void Main() {
		
		AdminAccountVo Account = login();
		System.out.println(Account.getID() +"���� �α��� �߽��ϴ�.");
		boolean loof = true;
		while(loof) {
			System.out.println("������������������������������������������������������");
		System.out.println("0.�α׾ƿ�");
		System.out.println("1.ȸ������ ��ü ���");
		System.out.println("2.ȸ������ �˻� ���");
		System.out.println("3.�������� ����/����/����");
		System.out.println("4.��Ű�� ����/����/����");
		System.out.println("������������������������������������������������������");
		System.out.println("�۾��� �������ּ���.>>");
		int choice = Integer.parseInt(sc.nextLine());
		
		switch(choice) {
		
		case 0:
			loof = false;
			System.out.println("�α׾ƿ� �ϼ̽��ϴ�.");
			break;
		case 1:
			User.Select();
			break;
		case 2:
			User.main();
			break;

		case 3:
			Notice.main();
			break;

		case 4:
			Package.main();
			break;
		
		}
		}//while
		
	}//Admin_Menu()
	


	
	
	



	

	

	
	
	
	
	
	
	
}//class
