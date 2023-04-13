package DAO.AdminDAO;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DAO.Abs_Dao;
import VO.NoticeVo;
import VO.User_AccountVo;

public class NoticeDao extends Abs_Dao {
	
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	String id;
	String pwd;
	String query;
	Scanner sc = new Scanner(System.in);
	AdminDao AdminDao = new AdminDao();
	NoticeVo Notice_VO;
	
	private void connDB() { 
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			System.out.println("NoticeDao.connDB()����");
			
		}
	}//connDB()
	
	
	
	
	
	public List<NoticeVo> Notice_List() {
		connDB();
		List<NoticeVo> Notice_List = new ArrayList<NoticeVo>();
		try {
			stmt = conn.createStatement();
			query = "select * from notice_table order by notice_no desc";
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				Notice_VO = new NoticeVo();
				Notice_VO.setNotice_No(rs.getString("notice_no"));
				Notice_VO.setTitle(rs.getString("title"));
				Notice_VO.setContent(rs.getString("content"));
				Notice_VO.setNotice_date(rs.getDate("notice_date"));
				
				Notice_List.add(Notice_VO);
			}
			
		} catch (Exception e) {
		System.out.println("Notice_List() ����");
		}
		
		return Notice_List;
	}//Notice_List()
	





	public void Insert(NoticeVo vo) {
	connDB();
	try {

		query = "insert into notice_table(notice_no,title,content) values(notice_seq.nextval,?,?)";
		//System.out.println(query);
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		int count = pstmt.executeUpdate();
		
		System.out.println(count+"���� ������Ʈ �Ǿ����ϴ�.");
	} catch (SQLException e) {
		System.out.println("Insert()����");
	}
		
		Notice_List();
	}//Insert()
	
	public void Delete(int Notice_no) {
	connDB();
	Notice_List();
	query = "delete from notice_table where notice_no = ?";
	try {
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, Notice_no);
		if(pstmt.executeUpdate()==1){  
		System.out.println("���� ���� �ϼ̽��ϴ�.");	
		}else {
		System.out.println("���� ���� �ϼ̽��ϴ�.");
		}
	} catch (SQLException e) {
		System.out.println("Delete()����");
	}
	}
	
	public void update(int Notice_no) {
		connDB();
		
		query = "update notice_table set ";
		boolean loof = true;
		while(loof) {
		System.out.println("������������������������������������������������������");
		System.out.println("0.�ڷ� ����");
		System.out.println("1.���� ����");
		System.out.println("2.���� ����");
		System.out.println("������������������������������������������������������");
		int choice = Integer.parseInt(sc.nextLine());
		switch(choice) {
		case 0:
			loof = false;
			System.out.println("���� �������� ���ư��ϴ�.");
			break;
		case 1:
			System.out.println("������ ���������� ������ �Է����ּ���.");
			String title = sc.nextLine();
			if(title.trim().isEmpty() || title ==null) {
			System.out.println("���� �Ҽ� �����ϴ�.");
			}
			else {
				query += "title = '";
				query += title+"' where notice_no =" +Notice_no;
				System.out.println(query);
				
				try {
					rs = stmt.executeQuery(query);
					if(rs.next()) {
						System.out.println("���� �Ǿ����ϴ�.");
					}else {
						System.out.println("�������� �ʾҽ��ϴ�.");
					}
				} catch (Exception e) {
					System.out.println("update()����");
				}
			}
			break;
		case 2:
			System.out.println("������ ���������� ������ �Է����ּ���.");
			String content = sc.nextLine();
			if(content.trim().isEmpty() || content == null) {
			System.out.println("���� �Ҽ� �����ϴ�.");
			}else {
				query += "content = '";
				query += content+"' where notice_no =" +Notice_no;
				System.out.println(query);
				
				
				try {
					rs = stmt.executeQuery(query);
					if(rs.next()) {
						System.out.println("���� �Ǿ����ϴ�.");
					}else {
						System.out.println("�������� �ʾҽ��ϴ�.");
					}
				} catch (Exception e) {
					System.out.println("update()����");
				}
			}
			break;
			
		default:
			System.out.println("�߸� �����ϼ̽��ϴ�.");
		}
		
		
	
		}
	}//update()
	
	
	
	
}//class
