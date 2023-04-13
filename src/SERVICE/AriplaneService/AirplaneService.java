package SERVICE.AriplaneService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import DAO.AirplaneDAO.AirplaneAccountDao;
import DAO.AirplaneDAO.AirplaneDao;
import VO.AirplaneAccountVo;

public class AirplaneService {
	Connection conn;
	AirplaneDao dao = new AirplaneDao();
	Scanner sc = new Scanner(System.in);
	AirplaneAccountVo vo;
	
	
	AirplaneAccountService AccountService = new AirplaneAccountService();;
	TravelService TravelService = new TravelService();
	AirplaneDao airplanedao = new AirplaneDao();
	
	
	private AirplaneAccountVo login() {
	List<AirplaneAccountVo> Airplane_list = dao.Airplane_list();
	AirplaneAccountVo vo = new AirplaneAccountVo();
	try {
		System.out.println("���̵� �Է����ּ���.>>");
		String id = sc.nextLine();
		System.out.println("��й�ȣ�� �Է����ּ���.>>");
		String pwd = sc.nextLine();
		
		for(int i =0; i<Airplane_list.size(); i++) {
			if(Airplane_list.get(i).getID().equals(id) && 
			   Airplane_list.get(i).getPWD().equals(pwd)) {
				vo = Airplane_list.get(i);
				
				
				System.out.println("�α�����.....");
				Thread.sleep(2000);
				
			}
			
		}
	} catch (Exception e) {
	System.out.println("login() ����");
	}
	return vo;
	
	}//login()
	
	public void Main() {
		AirplaneAccountVo Account = login();
		boolean loof = true;
		while(loof) {
		System.out.println("������������������������������������������������������");
		System.out.println("0.�α׾ƿ�");
		System.out.println("1.������ ���/����/����");
		System.out.println("2.����� ���� ����");
		System.out.println("3.����� ���� ���");
		System.out.println("������������������������������������������������������");
		System.out.println("�۾��� �������ּ���.>>");
		
		int choice = Integer.parseInt(sc.nextLine());
		
		switch(choice) {
		
		case 0:
			System.out.println("�α׾ƿ� �ϼ̽��ϴ�.");
			loof = false;
			break;
		
		case 1:
			//������ �ű� ���
			TravelService.main(Account);
			break;

		case 2:
			//����� ���� ����
			AccountService.main(Account);
			break;
		case 3:
			Select(Account);
			break;
		}
		
		}
		
	}//Airplane_Main()
	
	public void Select(AirplaneAccountVo vo) {
	System.out.println("������������������������������������������������������");
	System.out.printf("\t%s \t%s \t%s \t%s \t%s\n","���̵�","��й�ȣ","�װ���","��ȭ��ȣ","�ڵ�");
	System.out.println("������������������������������������������������������");
	System.out.printf("\t%s",vo.getID());
	System.out.printf("\t%s",vo.getPWD());
	System.out.printf("\t%s",vo.getAir_Name());
	System.out.printf("\t%s",vo.getAir_Tel());
	System.out.printf("\t%s\n",vo.getAir_code());
	}
	
	
	public void insertAirplane() {
		airplanedao.insertAirplane();
	}
	

	
	
	
}//class
