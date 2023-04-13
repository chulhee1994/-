package SERVICE.AriplaneService;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.security.auth.login.AccountException;

import DAO.AirplaneDAO.AirplaneAccountDao;
import DAO.AirplaneDAO.AirplaneDao;
import VO.AirplaneAccountVo;

public class AirplaneAccountService {
	Scanner sc= new Scanner(System.in);
	String query;
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	AirplaneAccountDao AirplaneAccountDao = new AirplaneAccountDao();
	
	public void main(AirplaneAccountVo vo) {
		AirplaneAccountVo Account = vo;
		boolean loof = true;
		while(loof) {
		System.out.println("������������������������������������������������������");
		System.out.println("0.�ڷΰ���");
		System.out.println("1.�װ��� ��й�ȣ ����");
		System.out.println("2.�װ��� �̸� ����");
		System.out.println("3.�װ��� ��ȭ��ȣ ����");
		System.out.println("������������������������������������������������������");
		System.out.println("�۾��� �������ּ���.>>");
		int choice = Integer.parseInt(sc.nextLine());
		
		switch(choice) {
		
		case 0:
		loof = false;
		System.out.println("���� ȭ������ ���ư��ϴ�.");
		break;
		
		case 1:
		pwd(Account);
		break;	
		
		case 2:
		name(Account);
		break;
		
		case 3:
		tel(Account);
		break;	
		}
		
		}
	}
	
	private void pwd(AirplaneAccountVo vo) {
		System.out.println("�����Ͻ� ��й�ȣ�� �Է����ּ���.");
		String pwd = sc.nextLine();
		
		if(pwd.trim().isEmpty()||pwd == null) {
			System.out.println("���� �Ҽ� �����ϴ�.");
		}else {
		vo.setPWD(pwd);
		AirplaneAccountDao.pwd(vo);
		}
	}
	
	private void name(AirplaneAccountVo vo) {
	System.out.println("�����Ͻ� �װ��� �̸��� �Է����ּ���.>>");
	String Air_name = sc.nextLine();
	if(Air_name.trim().isEmpty()||Air_name == null) {
		System.out.println("���� �Ҽ� �����ϴ�.");
	}else {
	vo.setAir_Name(Air_name);
	AirplaneAccountDao.name(vo);
	}
	}
	
	private void tel(AirplaneAccountVo vo) {
	System.out.println("�����Ͻ� �װ��� ��ȭ��ȣ�� �Է����ּ���.");
	
	String air_tel = sc.nextLine();
	if(air_tel.trim().isEmpty()||air_tel == null) {
		System.out.println("���� �Ҽ� �����ϴ�.");
	}else {
	vo.setAir_Tel(air_tel);
	AirplaneAccountDao.tel(vo);
	}
	}
	
}//class
