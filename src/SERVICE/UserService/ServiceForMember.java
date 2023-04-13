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


public class ServiceForMember { // 褒韓瞳檣 �事靺攽� 憮綠蝶 箔 羲й擊 ж朝 贗楚蝶. dao縑憮 虜萇 晦棟擊 Ui諦 唸м.
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
		
		System.out.println("式式式式式式式式式式式式煎斜檣璽式式式式式式式式式式式式式");
		System.out.println("ID 殮溘:");
		id = sc.nextLine();
		System.out.println("綠塵廓�� 殮溘:");
		pwd = sc.nextLine();
		System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		
		return dao.login(id, pwd);

	} // 煎斜檣 撮暮view 部

	
	public List session() throws SQLException {
		Scanner sc = new Scanner(System.in);
		UserDao__ dao = new UserDao__();
		List<User_AccountVo> list = new ArrayList<>();
		User_AccountVo vo = new User_AccountVo();
		String select;
		
		try {
		System.out.println();
		System.out.println("式式式式式式式�蛾矕雃’’’’’’’’’’�");
		System.out.println("1.ぬ酈雖 匐儀婁 蕨衙");
		System.out.println("2.�蛾� 薑爾 褻�� 塽 熱薑");
		System.out.println("3.蕨衙 ⑷�� 塽 鏃模");
		System.out.println("4.煎斜嬴醒");
		System.out.println("式式式式式式式式式式式式式式式式式式式式式式");
		select = sc.nextLine();

		
		if (select.equals("1")) {
			return ReservationSession();
		} 
		
		else if (select.equals("2")) {
			
			System.out.println("式式式式�蛾� 薑爾 褻�� 塽 熱薑欽式式式");
			System.out.println("綠塵廓�ㄧ� 殮溘п輿撮蹂.:");
			String pwd = sc.nextLine();
			
			list = dao.serchingMember(pwd); // �蛾� 薑爾 褻�號� 熱薑 詭憮萄煎 檜翕
			System.out.println();
			
			
		} else if (select.equals("3")) {
			return MemberReserLookup();
			
		} else if (select.equals("4")) {
			return null;
			
		} else {
			System.out.println("澀跤脹 殮溘 高 殮棲棻.");
			return session();
		}
		
		} catch(Exception e) {
			System.out.println("澀 覬 殮溘ж撮蹂.");
			return null;
		}
		
		return null; 
	} // sessiong 蝶囀Щ

	
	public List ReservationSession() throws SQLException { // ぬ酈雖 褻�號� 蕨衙 餌檜お 氬渡 撮暮
		Scanner sc = new Scanner(System.in);
		UserDao__ dao = new UserDao__();
		PackageVo packvo = new PackageVo();
		List<PackageVo> packlist = new ArrayList<>();
		packlist = dao.AllpacakgeSerching();
		
		try {
		System.out.println("式式式式式式⑷營 啪衛醞檣 ぬ酈雖 跡煙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		for(int i=0; i<packlist.size(); i++) {
		packvo=packlist.get(i);
		System.out.print("鼻ヶ廓��:"+packvo.getPackno());
		System.out.print("  "+" ぬ酈雖 檜葷:"+packvo.getPackname());
		System.out.print("  "+" ぬ酈雖 陛問:"+packvo.getPackprice());
		System.out.print("  "+" 橾薑:"+packvo.getPackplan());
		System.out.print("  "+" 罹ч雖:"+packvo.getTourist_spot()+"\n");
		System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		System.out.println();
		  } // for僥
		System.out.println("式式>錳ж衛朝 ぬ酈雖蒂 蕨衙ж撮蹂!式式式式式");
		System.out.println("1.ぬ酈雖 檜葷戲煎 匐儀");
		System.out.println("2.罹ч雖 檜葷戲煎 匐儀");
		System.out.println("3.�蛾矕傱虞� 給嬴陛晦");
		System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		String select1 = sc.nextLine();
		
		// 褻勒僥 衛濛
		if(select1.equals("1")) {
		System.out.println("ぬ酈雖 檜葷擊 殮溘ж撮蹂:");
		String packname = sc.nextLine();
		System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		
		packlist = dao.serchingPack(packname); // 蘭葬蒂 陳溥 陛螳螞 ぬ酈雖 薑爾蒂 葬蝶お縑 氬朝棻.
		
		for (int i = 0; i < packlist.size(); i++){
			packvo = packlist.get(i);
			System.out.println("ぬ酈雖 檜葷:式式"+packvo.getPackname()+"式式");
			System.out.println("陛問:式式"+packvo.getPackprice()+"式式");
			System.out.println("橾薑:式式"+packvo.getPackplan()+"式式");
			System.out.println("轎嫦 雖薄:式式"+packvo.getStarting_point()+"式式");
			System.out.println("紫雜 雖薄:式式"+packvo.getDestination()+"式式");
			System.out.println("轎嫦 陳瞼:式式"+packvo.getDeparture_time()+"式式");
			System.out.println("紫雜 陳瞼:式式"+packvo.getArrival_time()+"式式");
			} // for僥
		
		  	System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		  	System.out.println("式式式獄 鼻ヶ擊 蕨衙 ж衛啊蝗棲梱?式式式");
		  	System.out.println("1.⑷營 匐儀и 鼻ヶ擊 蕨衙и棻.");
		  	System.out.println("2.ぬ酈雖 匐儀欽戲煎 給嬴除棻.");
		  	System.out.println("3.�蛾矕傱虞� 給嬴除棻.");
		  	System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式");
		  	
		  	String select2 = sc.nextLine(); // 罹ч雖 檜葷戲煎 匐儀檣 2廓擊 殮溘ц擊陽 殮溘 嫡擊 滲熱 select2
		  	
		if(select2.equals("1")) {
		   dao.insertReser(packname, id, pwd);
			
		}  else if(select2.equals("2")) {
			return ReservationSession();
			
		
		}  else if(select2.equals("3")) {
			return session();
		
		}  else { // 雖薑脹 高 1,2,3擊 慇橫釭賊 棻衛 嬪煎 給嬴除棻.
			System.out.println();
			System.out.println("澀跤 殮溘脹 高 殮棲棻.");
			return packlist = dao.serchingPack(packname);
		}
		
		
		} else if(select1.equals("2")) { // 2廓 摹鷗 罹ч雖 檜葷戲煎 匐儀 滲熱貲 輿衛 select1檜棻.
			PackageVo vo = new PackageVo();
			packlist=dao.touristsopt(); // ⑷營 �側瘓音Ц� 氈朝 罹ч雖蒂 爾罹醜憮 餌辨濠陛 罹ч雖蒂爾堅 匐儀й 熱 氈啪 п鄹棲棻.
			
			System.out.println("⑷營 �側瘓� 脹 罹ч雖 殮棲棻."); // ⑷營 �側瘓音� 罹ч雖蒂 爾罹鄹棲棻.
			for(int i=0; i<packlist.size(); i++) {
				vo = packlist.get(i);
				System.out.println(vo.getTourist_spot());
			} // for僥 
			
			System.out.println("嬪曖 �側瘓音� 罹ч雖 檜葷擊 摹鷗п 殮溘п輿撮蹂:");
			/* System.out.println("'檜葷-檜葷'衝戲煎 醞除縑 ж檜Ъ擊 蹺陛п憮 厥橫憮 殮溘п撿м棲棻."); */
			String touristSerching = sc.nextLine();
			/* String touristSerching_pattern = "[陛-鼇]{5}"; */
			packlist=dao.touristsoptSerching(touristSerching);
			System.out.println("罹ч雖煎 匐儀 撩奢");
			
			for(int i=0; i<packlist.size(); i++) {
				vo = packlist.get(i);
				System.out.println("ぬ酈雖 檜葷:式式"+packvo.getPackname()+"式式");
				System.out.println("陛問:式式"+packvo.getPackprice()+"式式");
				System.out.println("橾薑:式式"+packvo.getPackplan()+"式式");
				System.out.println("轎嫦 雖薄:式式"+packvo.getStarting_point()+"式式");
				System.out.println("紫雜 雖薄:式式"+packvo.getDestination()+"式式");
				System.out.println("轎嫦 陳瞼:式式"+packvo.getDeparture_time()+"式式");
				System.out.println("紫雜 陳瞼:式式"+packvo.getArrival_time()+"式式");
				System.out.println("罹ч雖:式式"+packvo.getTourist_spot()+"式式");
			}// for僥 罹ч雖煎 匐儀 褻�� 
			
			/*if(Pattern.matches(touristSerching_pattern, touristSerching))  {*/
				 // 罹ч雖煎 匐儀п 瓊擎 ぬ酈雖 薑爾蒂 list縑 氬朝棻.
				
			/*
			 * } else { System.out.println("匐儀 曄衝縑 蜃啪 殮溘ж撮蹂."); return ReservationSession();
			 * }
			 */			
			System.out.println();
		  	System.out.println("式式式式式式獄 鼻ヶ擊 蕨衙 ж衛啊蝗棲梱?式式式式式");
		  	System.out.println("1.⑷營 匐儀и 鼻ヶ擊 蕨衙и棻.");
		  	System.out.println("2.ぬ酈雖 匐儀欽戲煎 給嬴除棻.");
		  	System.out.println("3.�蛾矕傱虞� 給嬴除棻.");
		  	System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
		  	
		  	String select3 = sc.nextLine(); // select3 輿跡
			
		  	if(select3.equals("1")) {
		  		
		  	return packlist = dao.touristResrvationInsert(touristSerching, id, pwd);
		  	
		  	} else if(select3.equals("2")) {

		  	return ReservationSession();
		  		
		  	} else if(select3.equals("3")) {
		  		
		  	return session();
		  	
		  	}
		  	
		  	else {
		  	System.out.println("澀跤 殮溘и 高殮棲棻.");
		  	
		  	return ReservationSession();
		  	}
		  	
		} else if(select1.equals("3")) { // 輿曖 selcet1檜嘎煎  裔籀擠縑 殮溘嫡擎 滲熱檜晦縑 輿曖 ж褊衛棻.
			return session();
			
		} else {
			System.out.println("澀跤 殮溘и 高殮棲棻.");
	  		return ReservationSession();
		}
		
		} catch (Exception e) {
			System.out.println("蕨晦纂 跤и 螃盟");
			return session();
		}
		return packlist;
	} // ぬ酈雖 褻�號� 蕨衙 撮暮	
		
		
	public List MemberReserLookup() throws SQLException { // �蛾衋� 蕨衙и ぬ酈雖 褻�蛾� 鏃模
		Scanner sc = new Scanner(System.in);
		UserDao__ dao = new UserDao__();
		ReservationVO vo = new ReservationVO();
		List<ReservationVO> list = new ArrayList<>();
		List<User_AccountVo> list2 = new ArrayList<>();
	
		System.out.println("式式式式式�蛾矕埬� 蕨衙и 鼻ヶ擊 褻�裔炴� 欽式式式式式");
		System.out.println("1.蕨衙 褻�� 塽 鏃模");
		System.out.println("2.�蛾矕傱虞� 給嬴陛晦");
		String select1 = sc.nextLine();
	
	if(select1.equals("1")) {
	
		System.out.println("綠塵廓�ㄧ� 殮溘п輿撮蹂:");
		String pwd = sc.nextLine();
		System.out.println();
		
		list=dao.ReserLookup(pwd); // 綠塵廓�ㄧ� 殮溘ц擊陽 蕨衙и ぬ酈雖蒂 陛螳螃朝 詭憮萄殮棲棻.
		for(int i=0; i<list.size(); i++) {
		vo=list.get(i);
		System.out.println("式式式式式蕨衙⑷�畢’’’’’’’’�");
		System.out.println("�蛾矕埬� 蕨擒廓��:"+vo.getReserno());
		System.out.println("ぬ酈雖 廓��:"+vo.getPackno());
		System.out.println("蕨擒ж褐 �蛾矕埬� ID:"+vo.getId());
		System.out.println("蕨擒 ぬ酈雖 檜葷:"+vo.getPackname());
		} // for僥
	
	   System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式");
	   System.out.println("1.�蛾虃撮Ш虞� 給嬴陛晦");
	   System.out.println("2.蕨衙鏃模ж晦");
	   System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式");
	   String select2 = sc.nextLine();
	
	if(select2.equals("1")) {
	return session(); // �蛾矕傱虞� 給嬴骨棲棻.
		
	} else if(select2.equals("2")) {
		System.out.println("餉薯ж褒 蕨衙 ぬ酈雖曖 蕨衙廓�ㄧ� 殮溘ж撮蹂.");
		int reserno = sc.nextInt();
		dao.DeleteResr(pwd, reserno);
	} else {
		System.out.println("綠塵廓�ㄟ� 橾纂ж雖 彊蝗棲棻.");
		return session();
	}
		
	} else if(select1.equals("2")) {
		return session();
		
	} else { // select1 彰嬪蒂 慇橫陪 高縑 渠и else
		System.out.println("澀跤脹 高 殮棲棻.");
		return session();
	}
	
	return list;
	
	} // 蕨衙褻�� 欽 詭憮萄曖 部
} // 憮綠蝶 贗楚蝶欽