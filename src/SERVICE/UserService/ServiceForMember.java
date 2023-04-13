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


public class ServiceForMember { // ½ÇÁúÀûÀÎ È¨ÆäÀÌÁö ¼­ºñ½º ºä ¿ªÇÒÀ» ÇÏ´Â Å¬·¡½º. dao¿¡¼­ ¸¸µç ±â´ÉÀ» Ui¿Í °áÇÕ.
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
		
		System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡·Î±×ÀÎÃ¢¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.println("ID ÀÔ·Â:");
		id = sc.nextLine();
		System.out.println("ºñ¹Ð¹øÈ£ ÀÔ·Â:");
		pwd = sc.nextLine();
		System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		
		return dao.login(id, pwd);

	} // ·Î±×ÀÎ ¼¼¼Çview ³¡

	
	public List session() throws SQLException {
		Scanner sc = new Scanner(System.in);
		UserDao__ dao = new UserDao__();
		List<User_AccountVo> list = new ArrayList<>();
		User_AccountVo vo = new User_AccountVo();
		String select;
		
		try {
		System.out.println();
		System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡È¸¿ø´Ü¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.println("1.ÆÐÅ°Áö °Ë»ö°ú ¿¹¸Å");
		System.out.println("2.È¸¿ø Á¤º¸ Á¶È¸ ¹× ¼öÁ¤");
		System.out.println("3.¿¹¸Å ÇöÈ² ¹× Ãë¼Ò");
		System.out.println("4.·Î±×¾Æ¿ô");
		System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		select = sc.nextLine();

		
		if (select.equals("1")) {
			return ReservationSession();
		} 
		
		else if (select.equals("2")) {
			
			System.out.println("¦¡¦¡¦¡¦¡È¸¿ø Á¤º¸ Á¶È¸ ¹× ¼öÁ¤´Ü¦¡¦¡¦¡");
			System.out.println("ºñ¹Ð¹øÈ£¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.:");
			String pwd = sc.nextLine();
			
			list = dao.serchingMember(pwd); // È¸¿ø Á¤º¸ Á¶È¸¹× ¼öÁ¤ ¸Þ¼­µå·Î ÀÌµ¿
			System.out.println();
			
			
		} else if (select.equals("3")) {
			return MemberReserLookup();
			
		} else if (select.equals("4")) {
			return null;
			
		} else {
			System.out.println("Àß¸øµÈ ÀÔ·Â °ª ÀÔ´Ï´Ù.");
			return session();
		}
		
		} catch(Exception e) {
			System.out.println("Àß Á» ÀÔ·ÂÇÏ¼¼¿ä.");
			return null;
		}
		
		return null; 
	} // sessiong ½ºÄÚÇÁ

	
	public List ReservationSession() throws SQLException { // ÆÐÅ°Áö Á¶È¸¹× ¿¹¸Å »çÀÌÆ® ´ã´ç ¼¼¼Ç
		Scanner sc = new Scanner(System.in);
		UserDao__ dao = new UserDao__();
		PackageVo packvo = new PackageVo();
		List<PackageVo> packlist = new ArrayList<>();
		packlist = dao.AllpacakgeSerching();
		
		try {
		System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡ÇöÀç °Ô½ÃÁßÀÎ ÆÐÅ°Áö ¸ñ·Ï¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		for(int i=0; i<packlist.size(); i++) {
		packvo=packlist.get(i);
		System.out.print("»óÇ°¹øÈ£:"+packvo.getPackno());
		System.out.print("  "+" ÆÐÅ°Áö ÀÌ¸§:"+packvo.getPackname());
		System.out.print("  "+" ÆÐÅ°Áö °¡°Ý:"+packvo.getPackprice());
		System.out.print("  "+" ÀÏÁ¤:"+packvo.getPackplan());
		System.out.print("  "+" ¿©ÇàÁö:"+packvo.getTourist_spot()+"\n");
		System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.println();
		  } // for¹®
		System.out.println("¦¡¦¡>¿øÇÏ½Ã´Â ÆÐÅ°Áö¸¦ ¿¹¸ÅÇÏ¼¼¿ä!¦¡¦¡¦¡¦¡¦¡");
		System.out.println("1.ÆÐÅ°Áö ÀÌ¸§À¸·Î °Ë»ö");
		System.out.println("2.¿©ÇàÁö ÀÌ¸§À¸·Î °Ë»ö");
		System.out.println("3.È¸¿ø´ÜÀ¸·Î µ¹¾Æ°¡±â");
		System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		String select1 = sc.nextLine();
		
		// Á¶°Ç¹® ½ÃÀÛ
		if(select1.equals("1")) {
		System.out.println("ÆÐÅ°Áö ÀÌ¸§À» ÀÔ·ÂÇÏ¼¼¿ä:");
		String packname = sc.nextLine();
		System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		
		packlist = dao.serchingPack(packname); // Äõ¸®¸¦ ³¯·Á °¡Á®¿Â ÆÐÅ°Áö Á¤º¸¸¦ ¸®½ºÆ®¿¡ ´ã´Â´Ù.
		
		for (int i = 0; i < packlist.size(); i++){
			packvo = packlist.get(i);
			System.out.println("ÆÐÅ°Áö ÀÌ¸§:¦¡¦¡"+packvo.getPackname()+"¦¡¦¡");
			System.out.println("°¡°Ý:¦¡¦¡"+packvo.getPackprice()+"¦¡¦¡");
			System.out.println("ÀÏÁ¤:¦¡¦¡"+packvo.getPackplan()+"¦¡¦¡");
			System.out.println("Ãâ¹ß ÁöÁ¡:¦¡¦¡"+packvo.getStarting_point()+"¦¡¦¡");
			System.out.println("µµÂø ÁöÁ¡:¦¡¦¡"+packvo.getDestination()+"¦¡¦¡");
			System.out.println("Ãâ¹ß ³¯Â¥:¦¡¦¡"+packvo.getDeparture_time()+"¦¡¦¡");
			System.out.println("µµÂø ³¯Â¥:¦¡¦¡"+packvo.getArrival_time()+"¦¡¦¡");
			} // for¹®
		
		  	System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		  	System.out.println("¦¡¦¡¦¡º» »óÇ°À» ¿¹¸Å ÇÏ½Ã°Ú½À´Ï±î?¦¡¦¡¦¡");
		  	System.out.println("1.ÇöÀç °Ë»öÇÑ »óÇ°À» ¿¹¸ÅÇÑ´Ù.");
		  	System.out.println("2.ÆÐÅ°Áö °Ë»ö´ÜÀ¸·Î µ¹¾Æ°£´Ù.");
		  	System.out.println("3.È¸¿ø´ÜÀ¸·Î µ¹¾Æ°£´Ù.");
		  	System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		  	
		  	String select2 = sc.nextLine(); // ¿©ÇàÁö ÀÌ¸§À¸·Î °Ë»öÀÎ 2¹øÀ» ÀÔ·ÂÇßÀ»¶§ ÀÔ·Â ¹ÞÀ» º¯¼ö select2
		  	
		if(select2.equals("1")) {
		   dao.insertReser(packname, id, pwd);
			
		}  else if(select2.equals("2")) {
			return ReservationSession();
			
		
		}  else if(select2.equals("3")) {
			return session();
		
		}  else { // ÁöÁ¤µÈ °ª 1,2,3À» ¹þ¾î³ª¸é ´Ù½Ã À§·Î µ¹¾Æ°£´Ù.
			System.out.println();
			System.out.println("Àß¸ø ÀÔ·ÂµÈ °ª ÀÔ´Ï´Ù.");
			return packlist = dao.serchingPack(packname);
		}
		
		
		} else if(select1.equals("2")) { // 2¹ø ¼±ÅÃ ¿©ÇàÁö ÀÌ¸§À¸·Î °Ë»ö º¯¼ö¸í ÁÖ½Ã select1ÀÌ´Ù.
			PackageVo vo = new PackageVo();
			packlist=dao.touristsopt(); // ÇöÀç È°¼ºÈ­µÇ¾î ÀÖ´Â ¿©ÇàÁö¸¦ º¸¿©Áà¼­ »ç¿ëÀÚ°¡ ¿©ÇàÁö¸¦º¸°í °Ë»öÇÒ ¼ö ÀÖ°Ô ÇØÁÝ´Ï´Ù.
			
			System.out.println("ÇöÀç È°¼ºÈ­ µÈ ¿©ÇàÁö ÀÔ´Ï´Ù."); // ÇöÀç È°¼ºÈ­µÈ ¿©ÇàÁö¸¦ º¸¿©ÁÝ´Ï´Ù.
			for(int i=0; i<packlist.size(); i++) {
				vo = packlist.get(i);
				System.out.println(vo.getTourist_spot());
			} // for¹® 
			
			System.out.println("À§ÀÇ È°¼ºÈ­µÈ ¿©ÇàÁö ÀÌ¸§À» ¼±ÅÃÇØ ÀÔ·ÂÇØÁÖ¼¼¿ä:");
			/* System.out.println("'ÀÌ¸§-ÀÌ¸§'½ÄÀ¸·Î Áß°£¿¡ ÇÏÀÌÇÂÀ» Ãß°¡ÇØ¼­ ³Ö¾î¼­ ÀÔ·ÂÇØ¾ßÇÕ´Ï´Ù."); */
			String touristSerching = sc.nextLine();
			/* String touristSerching_pattern = "[°¡-ÆR]{5}"; */
			packlist=dao.touristsoptSerching(touristSerching);
			System.out.println("¿©ÇàÁö·Î °Ë»ö ¼º°ø");
			
			for(int i=0; i<packlist.size(); i++) {
				vo = packlist.get(i);
				System.out.println("ÆÐÅ°Áö ÀÌ¸§:¦¡¦¡"+packvo.getPackname()+"¦¡¦¡");
				System.out.println("°¡°Ý:¦¡¦¡"+packvo.getPackprice()+"¦¡¦¡");
				System.out.println("ÀÏÁ¤:¦¡¦¡"+packvo.getPackplan()+"¦¡¦¡");
				System.out.println("Ãâ¹ß ÁöÁ¡:¦¡¦¡"+packvo.getStarting_point()+"¦¡¦¡");
				System.out.println("µµÂø ÁöÁ¡:¦¡¦¡"+packvo.getDestination()+"¦¡¦¡");
				System.out.println("Ãâ¹ß ³¯Â¥:¦¡¦¡"+packvo.getDeparture_time()+"¦¡¦¡");
				System.out.println("µµÂø ³¯Â¥:¦¡¦¡"+packvo.getArrival_time()+"¦¡¦¡");
				System.out.println("¿©ÇàÁö:¦¡¦¡"+packvo.getTourist_spot()+"¦¡¦¡");
			}// for¹® ¿©ÇàÁö·Î °Ë»ö Á¶È¸ 
			
			/*if(Pattern.matches(touristSerching_pattern, touristSerching))  {*/
				 // ¿©ÇàÁö·Î °Ë»öÇØ Ã£Àº ÆÐÅ°Áö Á¤º¸¸¦ list¿¡ ´ã´Â´Ù.
				
			/*
			 * } else { System.out.println("°Ë»ö ¾ç½Ä¿¡ ¸Â°Ô ÀÔ·ÂÇÏ¼¼¿ä."); return ReservationSession();
			 * }
			 */			
			System.out.println();
		  	System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡º» »óÇ°À» ¿¹¸Å ÇÏ½Ã°Ú½À´Ï±î?¦¡¦¡¦¡¦¡¦¡");
		  	System.out.println("1.ÇöÀç °Ë»öÇÑ »óÇ°À» ¿¹¸ÅÇÑ´Ù.");
		  	System.out.println("2.ÆÐÅ°Áö °Ë»ö´ÜÀ¸·Î µ¹¾Æ°£´Ù.");
		  	System.out.println("3.È¸¿ø´ÜÀ¸·Î µ¹¾Æ°£´Ù.");
		  	System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		  	
		  	String select3 = sc.nextLine(); // select3 ÁÖ¸ñ
			
		  	if(select3.equals("1")) {
		  		
		  	return packlist = dao.touristResrvationInsert(touristSerching, id, pwd);
		  	
		  	} else if(select3.equals("2")) {

		  	return ReservationSession();
		  		
		  	} else if(select3.equals("3")) {
		  		
		  	return session();
		  	
		  	}
		  	
		  	else {
		  	System.out.println("Àß¸ø ÀÔ·ÂÇÑ °ªÀÔ´Ï´Ù.");
		  	
		  	return ReservationSession();
		  	}
		  	
		} else if(select1.equals("3")) { // ÁÖÀÇ selcet1ÀÌ¹Ç·Î  ¸ÇÃ³À½¿¡ ÀÔ·Â¹ÞÀº º¯¼öÀÌ±â¿¡ ÁÖÀÇ ÇÏ½Ê½Ã´Ù.
			return session();
			
		} else {
			System.out.println("Àß¸ø ÀÔ·ÂÇÑ °ªÀÔ´Ï´Ù.");
	  		return ReservationSession();
		}
		
		} catch (Exception e) {
			System.out.println("¿¹±âÄ¡ ¸øÇÑ ¿À·ù");
			return session();
		}
		return packlist;
	} // ÆÐÅ°Áö Á¶È¸¹× ¿¹¸Å ¼¼¼Ç	
		
		
	public List MemberReserLookup() throws SQLException { // È¸¿øÀÌ ¿¹¸ÅÇÑ ÆÐÅ°Áö Á¶È¸¿Í Ãë¼Ò
		Scanner sc = new Scanner(System.in);
		UserDao__ dao = new UserDao__();
		ReservationVO vo = new ReservationVO();
		List<ReservationVO> list = new ArrayList<>();
		List<User_AccountVo> list2 = new ArrayList<>();
	
		System.out.println("¦¡¦¡¦¡¦¡¦¡È¸¿ø´ÔÀÇ ¿¹¸ÅÇÑ »óÇ°À» Á¶È¸ÇÏ´Â ´Ü¦¡¦¡¦¡¦¡¦¡");
		System.out.println("1.¿¹¸Å Á¶È¸ ¹× Ãë¼Ò");
		System.out.println("2.È¸¿ø´ÜÀ¸·Î µ¹¾Æ°¡±â");
		String select1 = sc.nextLine();
	
	if(select1.equals("1")) {
	
		System.out.println("ºñ¹Ð¹øÈ£¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä:");
		String pwd = sc.nextLine();
		System.out.println();
		
		list=dao.ReserLookup(pwd); // ºñ¹Ð¹øÈ£¸¦ ÀÔ·ÂÇßÀ»¶§ ¿¹¸ÅÇÑ ÆÐÅ°Áö¸¦ °¡Á®¿À´Â ¸Þ¼­µåÀÔ´Ï´Ù.
		for(int i=0; i<list.size(); i++) {
		vo=list.get(i);
		System.out.println("¦¡¦¡¦¡¦¡¦¡¿¹¸ÅÇöÈ²¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.println("È¸¿ø´ÔÀÇ ¿¹¾à¹øÈ£:"+vo.getReserno());
		System.out.println("ÆÐÅ°Áö ¹øÈ£:"+vo.getPackno());
		System.out.println("¿¹¾àÇÏ½Å È¸¿ø´ÔÀÇ ID:"+vo.getId());
		System.out.println("¿¹¾à ÆÐÅ°Áö ÀÌ¸§:"+vo.getPackname());
		} // for¹®
	
	   System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
	   System.out.println("1.È¸¿ø¼¼¼ÇÀ¸·Î µ¹¾Æ°¡±â");
	   System.out.println("2.¿¹¸ÅÃë¼ÒÇÏ±â");
	   System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
	   String select2 = sc.nextLine();
	
	if(select2.equals("1")) {
	return session(); // È¸¿ø´ÜÀ¸·Î µ¹¾Æ°©´Ï´Ù.
		
	} else if(select2.equals("2")) {
		System.out.println("»èÁ¦ÇÏ½Ç ¿¹¸Å ÆÐÅ°ÁöÀÇ ¿¹¸Å¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
		int reserno = sc.nextInt();
		dao.DeleteResr(pwd, reserno);
	} else {
		System.out.println("ºñ¹Ð¹øÈ£°¡ ÀÏÄ¡ÇÏÁö ¾Ê½À´Ï´Ù.");
		return session();
	}
		
	} else if(select1.equals("2")) {
		return session();
		
	} else { // select1 ¹üÀ§¸¦ ¹þ¾î³­ °ª¿¡ ´ëÇÑ else
		System.out.println("Àß¸øµÈ °ª ÀÔ´Ï´Ù.");
		return session();
	}
	
	return list;
	
	} // ¿¹¸ÅÁ¶È¸ ´Ü ¸Þ¼­µåÀÇ ³¡
} // ¼­ºñ½º Å¬·¡½º´Ü