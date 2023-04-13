package SERVICE.AdminService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DAO.AdminDAO.PackageDao;
import SERVICE.AriplaneService.TravelService;
import VO.PackageVo;
import VO.ReservationVO;

public class PackageService  {
	Scanner sc = new Scanner(System.in);
	TravelService travelservice = new TravelService();
	PackageDao packagedao = new PackageDao();
	public void main() {
		boolean loof = true;
		while(loof) {
		System.out.println("������������������������������������������������������");
		System.out.println("0.�ڷ� ����");
		System.out.println("1.��Ű�� ���");
		System.out.println("2.��Ű�� ���");
		System.out.println("3.��Ű�� ����");
		System.out.println("4.��Ű�� ����");
		System.out.println("������������������������������������������������������");
		int choice = Integer.parseInt(sc.nextLine());
		switch(choice) {
		case 0:
			System.out.println("���� �������� ���ư��ϴ�.");
			loof = false;
			break;
		case 1:
			Select();
			break;
		case 2:
			//Insert();
			packagedao.Insert();
			break; 
//		case 3:
//			Update();
//			break;
		case 4:
			Delete();
			break;
		default:
			System.out.println("�߸� �����ϼ̽��ϴ�.");
		
		}
	}
	}//Package_main()

	
	public void Select() {
		List<PackageVo> package_list = packagedao.Select();
		
		if(package_list.size()!=0) {
			
		
		
		System.out.printf("%s  \t%s \t%s \t%s \t%s \t%s \t%s \t\t\t%s \t\t\t%s\n","��ȣ","�̸�","����","��ȹ","�����","������","��� �ð�","���� �ð�", "�װ���");
		System.out.println("������������������������������������������������������");
		for(int i=0; i<package_list.size(); i++) {
		System.out.printf("%s",package_list.get(i).getPackno());
		System.out.printf("\t%s",package_list.get(i).getPackname());
		System.out.printf("\t%s",package_list.get(i).getPackprice());
		System.out.printf("\t%s",package_list.get(i).getPackplan());
		System.out.printf("\t%s",package_list.get(i).getStarting_point());
		System.out.printf("\t%s",package_list.get(i).getDestination());
		System.out.printf("\t%s",package_list.get(i).getDeparture_time().substring(0,16));
		System.out.printf("\t%s",package_list.get(i).getArrival_time().substring(0,16));
		System.out.printf("\t%s\n",package_list.get(i).getTourist_spot());
		System.out.println("������������������������������������������������������");
		
		}
	}else {
		System.out.println("��Ű���� �����ϴ�.");
	}
	
	}


	
	public void Delete() {
	travelservice.Select();
	System.out.println("������ ��ĳ���� ��ȣ�� �Է����ּ���.");
	//sString no = sc.nextLine();
	int no = Integer.parseInt(sc.nextLine());
	packagedao.Delete(no);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
