package SERVICE.AdminService;

import java.util.ArrayList;
import java.util.List;
import DAO.AdminDAO.*;

import java.util.Scanner;

import VO.User_AccountVo;

public class UserService {
	Scanner sc = new Scanner(System.in);
	UserDao UserDao = new DAO.AdminDAO.UserDao();
	
	public void main() {
	System.out.println("������������������������������������������������������");
	System.out.println("0.�ڷΰ���");
	System.out.println("1.���̵� �˻�");
	System.out.println("2.�̸� �˻�");
	System.out.println("3.�ֹε�� ��ȣ �˻�");
	System.out.println("4.���� �˻�");
	System.out.println("5.��ȭ��ȣ �˻�");
	System.out.println("6.�̸��� �˻�");
	System.out.println("������������������������������������������������������");
	System.out.println("�۾��� �������ּ���.>>");
	int choice = Integer.parseInt(sc.nextLine());
	Select_Where(choice);
	
	}//User_main()
	
	public void Select() {
	List<User_AccountVo> UserList = UserDao.User_List();
	if(UserList.size()!=0) {
	System.out.println("������������������������������������������������������");
	System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s\n", "���̵�","�̸�","�ֹε�Ϲ�ȣ","����","��ȭ��ȣ","�̸���");
	System.out.println("������������������������������������������������������");

	for(int i =0; i<UserList.size(); i++) {
		System.out.printf(" %-10s ",UserList.get(i).getID());
		System.out.printf(" %-10s ",UserList.get(i).getName());
		System.out.printf(" %-20s ",UserList.get(i).getCitizen_Number());
		System.out.printf(" %-10d ",UserList.get(i).getAge());
		System.out.printf(" %-10s ",UserList.get(i).getUser_Tel());
		System.out.printf(" %-10s\n ",UserList.get(i).getEmail());	
		System.out.println("������������������������������������������������������");
	}
	System.out.println("��" + UserList.size() + "���� �˻� �Ǿ����ϴ�.");
	}
}//User_All_List
	
	public void Select_Where(int choice) {
		
		if(choice !=0) {
	List<User_AccountVo> User_Search_List =UserDao.User_Search_List(choice);
	
	if(User_Search_List.size()!=0) {
	System.out.println("������������������������������������������������������");
	System.out.printf("%s %s %s %s %s %s %s\n", "���̵�","��й�ȣ","�̸�","�ֹε�Ϲ�ȣ","����","��ȭ��ȣ","�̸���");
	System.out.println("������������������������������������������������������");

	for(int i =0; i<User_Search_List.size(); i++) {
		System.out.printf("%s ",User_Search_List.get(i).getID());
		System.out.printf("%s ",User_Search_List.get(i).getPWD());
		System.out.printf(" %s ",User_Search_List.get(i).getName());
		System.out.printf(" %s ",User_Search_List.get(i).getCitizen_Number());
		System.out.printf(" %d ",User_Search_List.get(i).getAge());
		System.out.printf(" %s ",User_Search_List.get(i).getUser_Tel());
		System.out.printf(" %s\n ",User_Search_List.get(i).getEmail());	
	}
	System.out.println("������������������������������������������������������");
	System.out.println("�� " + User_Search_List.size() + "���� �˻� �Ǿ����ϴ�.");
	
		}
	}
	}//User_Search_List(int choice)

	
	

	
	
	
	
}//class
