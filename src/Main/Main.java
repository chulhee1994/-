package Main;

import java.sql.SQLException;
import java.util.Scanner;

import DAO.UserDao.UserDao_;
import SERVICE.AdminService.AdminService;
import SERVICE.AdminService.NoticeService;
import SERVICE.AriplaneService.AirplaneService;
import SERVICE.UserService.ServiceForMember;

public class Main {
		
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ServiceForMember user = new ServiceForMember();
		UserDao_ user_ = new UserDao_();
		
		AdminService AdminService = new AdminService();
		AirplaneService AirplaneService = new AirplaneService();
		NoticeService NoticeService = new NoticeService();
		
	
		
		//��ü ���� ������ ���� �װ��� ��ü ����
		
		boolean loof = true;
		while(loof){
		//��������
		NoticeService.Main_Notice();
		//����
		AdminService.sign();
		System.out.println("������������������������������������������������������");
		System.out.println("0.����");
		System.out.println("1.������ �α���");
		System.out.println("2.���� ȸ������");
		System.out.println("3.���� �α���");
		System.out.println("4.���� ���̵�/��й�ȣ ã��");
		System.out.println("5.�װ��� ȸ������");
		System.out.println("6.�װ��� �α���");
		System.out.println("7.�װ��� ���̵�/��й�ȣ ã��");
		System.out.println("������������������������������������������������������");
		System.out.println("�۾��� �������ּ���.>>");
		
		
		try {
			int choice = Integer.parseInt(sc.nextLine());
			//�Է��� ����  �޴��� ���ڿ� �ٸ���� �ݺ��� ó������ ���ư� (0,1,2,3,4,5,6,7)�� ��츸 ����
			//�Է��� ���� �Ǽ����̳� ������ ���ڿ� Ÿ���� ��� tryĳġ�� �̿� ó������ ���ư�
			if( (choice==0) || (choice==1) || (choice==2) || (choice==3) || (choice==4) || (choice==5) || (choice==6) || (choice==7) )   {
				
				switch(choice) {
				case 0:
					loof = false;
					System.out.println("���α׷��� ���� �Ǿ����ϴ�.");
					break;
				case 1:
					AdminService.Main();
					break;
				case 2:
					user_.insertUser();
					
					break;
				case 3:
					user.loginSession();
					break;
				case 4:
					//���� ���̵� ��й�ȣ ã�� �޼��� 1.���̵� ã�� 2.��й�ȣ ã��
					
					System.out.println("������������������������������������������������������");
					System.out.println("0.����");
					System.out.println("1.���̵� ã��");
					System.out.println("2.��й�ȣ ã��");
					System.out.println("�۾��� �������ּ���.>>");
					System.out.println("������������������������������������������������������");
					int find = Integer.parseInt(sc.nextLine());
					if( (find==0) ||(find==1) || (find ==2) ) {
					switch(find){
						
					case 0:
					System.out.println("���� �׸����� ���ư����ϴ�.");
					break;
					
					case 1:
						user_.searchID();//���̵� ã�� �޼���
					break;
					
					case 2:
						user_.searchPWD();//��й�ȣ ã�� �޼���
					break;
					}//find switch
					}else {
						System.out.println("�߸� �Է��ϼ̽��ϴ�.");
					}
					break;
				case 5:
					AirplaneService.insertAirplane();
					break;
					
				case 6:
					//�װ��� �α��� 
					AirplaneService.Main();
					break;
					
				case 7:
					//�װ��� ���̵� ��й�ȣ ã�� �޼��� �װ��� ������ȣ �Է�
					//System.out.println("�װ��� ����");
					break;
			}	
			}else {
			System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				
		}		
		}catch (Exception e) {
			System.out.println("�߸� �Է��ϼ̽��ϴ�.");
		}
			
		
		
		
		
		
		
		
		}
	}//main method
}//class