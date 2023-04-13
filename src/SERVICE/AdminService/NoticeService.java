package SERVICE.AdminService;

import java.text.SimpleDateFormat;


import java.util.List;
import java.util.Scanner;

import DAO.AdminDAO.AdminDao;
import DAO.AdminDAO.NoticeDao;
import VO.NoticeVo;

public class NoticeService {
	Scanner sc = new Scanner(System.in);
	AdminDao Admindao = new AdminDao();
	NoticeDao NoticeDao = new NoticeDao();
	
	
	NoticeVo vo;
	public void main() {
		
	System.out.println("������������������������������������������������������");
	System.out.println("0.�ڷ� ����");
	System.out.println("1.�������� ���");
	System.out.println("2.�������� ���");
	System.out.println("3.�������� ����");
	System.out.println("4.�������� ����");
	System.out.println("������������������������������������������������������");
	System.out.println("�۾��� �������ּ���.>>");
	int choice = Integer.parseInt(sc.nextLine());
	
	
	switch(choice){
		
	case 0:
		System.out.println("���� ȭ������ ���ư��ϴ�.");
		break;
	case 1:
		Select();
		break;
		
	case 2:
		Insert();
		break;
		
	case 3:
		Update();
		break;
		
	case 4:
		Delete();
		break;
	}
	
	
	}//Notice_Main()
	
	public void Main_Notice() {
			List<NoticeVo> NoticeList = NoticeDao.Notice_List();
			SimpleDateFormat sDate = new SimpleDateFormat("YYYY.MM.dd");
			System.out.println("��������������������������������������������������������");
			System.out.println("��          �� �� �� ��               ��");
			System.out.println("��������������������������������������������������������");
			//System.out.println("������������������������������������������������������");
			System.out.printf("%-5s \t%-5s \t\t%10s\n" , "��ȣ","����","��¥");
			System.out.println("������������������������������������������������������");
			for(int i =0; i<NoticeList.size(); i++) {
			System.out.printf("%-5s", NoticeList.get(i).getNotice_No());
			System.out.printf("\t%-5s" ,NoticeList.get(i).getTitle());
			//System.out.printf("\t%-10s\n",sDate.format(NoticeList.get(i).getNotice_date()));
			System.out.printf("\t\t%-10s\n",NoticeList.get(i).getNotice_date() );
			//System.out.printf("%10s\n",NoticeList.get(i).getNotice_date());
			System.out.println("������������������������������������������������������");
		
	}
	}//Main_Notice()


	private void Select() {
		List<NoticeVo> NoticeList = NoticeDao.Notice_List();
		SimpleDateFormat sDate = new SimpleDateFormat("YYYY.MM.dd");
		System.out.println("��������������������������������������������������������");
		System.out.println("��          �� �� �� ��               ��");
		System.out.println("��������������������������������������������������������");
		System.out.println("������������������������������������������������������");
		System.out.printf("%-5s \t%-5s \t%-25s \t%s\n" , "��ȣ","����","����","��¥");
		System.out.println("������������������������������������������������������");
		for(int i =0; i<NoticeList.size(); i++) {
		System.out.printf("%-7s", NoticeList.get(i).getNotice_No());
		System.out.printf("%-10s" ,NoticeList.get(i).getTitle());
		System.out.printf("%-15s" ,NoticeList.get(i).getContent());
		//System.out.printf("%10s\n",sDate.format(NoticeList.get(i).getNotice_date()));
		System.out.printf("%1s\n",NoticeList.get(i).getNotice_date());
		System.out.println("������������������������������������������������������");
			
		}
	}//Notice_List()
	
	private void Insert() {
	Select();
	vo = new NoticeVo();
	vo.setNotice_No("notice_seq.nextval");
	System.out.println("������ �Է����ּ���.");
	String title = sc.nextLine();
	vo.setTitle(title);
	System.out.println("������ �Է����ּ���.");
	String content = sc.nextLine();
	vo.setContent(content);
	NoticeDao.Insert(vo);
	}
	
	private void Delete() {
	Select();
	System.out.println("������ ���������� ��ȣ�� �Է����ּ���.");
	
	
	try {
		int Notice_no = Integer.parseInt(sc.nextLine());
		NoticeDao.Delete(Notice_no);
	} catch (Exception e) {
	}
	}
	
	private void Update() {
		Select();
		System.out.println("������ ���������� ��ȣ �������ּ���.");
		int Notice_no = Integer.parseInt(sc.nextLine());
		NoticeDao.update(Notice_no);
		
	
	}//Update


	
	
	
	
	
	
	
	
	
}//class
