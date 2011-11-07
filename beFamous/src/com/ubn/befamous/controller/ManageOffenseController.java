package com.ubn.befamous.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.Creator;
import com.ubn.befamous.entity.Hidden;
import com.ubn.befamous.entity.Member;
import com.ubn.befamous.entity.Offense;
import com.ubn.befamous.entity.OffenseType;
import com.ubn.befamous.entity.OrderDetail;
import com.ubn.befamous.entity.ProductionCategory;
import com.ubn.befamous.entity.Song;


@Controller
public class ManageOffenseController {

	//查詢檢舉內容
	@RequestMapping("/offenseArea")
	public ModelAndView offensearea() {
		ModelAndView mav = new ModelAndView("offenseArea");	
		
		return mav;
	}

	//查詢檢舉內容-專輯Tab
	@RequestMapping("/offenseAlbum")
	public ModelAndView offensealbum() {
		ModelAndView mav = new ModelAndView("offenseAlbum");	
				
		String amount = "12";
		String amount2 = "20";
		
		Creator creator = new Creator();
		creator.setUserName("鄧福如");
		long p = 89451237;
		creator.setMemberId(p);
		Album album = new Album();
		album.setName("原來如此");
		album.setCreator(creator);
		long a = 001;
		album.setAlbumID(a);
		Offense offense = new Offense();
		offense.setAlbum(album);
		
		Creator creator2 = new Creator();
		creator2.setUserName("蕭敬騰");
		long p2 = 12378945;
		creator2.setMemberId(p2);
		Album album2 = new Album();
		album2.setName("狂想曲");
		album2.setCreator(creator2);
		long a2 = 002;
		album2.setAlbumID(a2);
		Offense offense2 = new Offense();
		offense2.setAlbum(album2);
		
		ArrayList list = new ArrayList();     //明細第一筆
		list.add(offense);
		list.add(amount);
		
		ArrayList list1 = new ArrayList();    //明細第二筆
		list1.add(offense2);
		list1.add(amount2);
		
		ArrayList[]  list3 = {list, list1};
		
		mav.addObject("offenses", list3);
		return mav;
	}
	
	//查詢檢舉內容-歌曲Tab
	@RequestMapping("/offenseSong")
	public ModelAndView offensesong() {
		ModelAndView mav = new ModelAndView("offenseSong");	
			
		String amount = "14";
		String amount2 = "30";
		
		Creator creator = new Creator();
		creator.setUserName("鄧福如");
		long p = 89451237;
		creator.setMemberId(p);
		Album album = new Album();
		album.setName("原來如此");
		album.setCreator(creator);
		long a = 111;
		album.setAlbumID(a);
		Song song = new Song();
		song.setName("未填詞");
		song.setAlbum(album);
		long s = 456;
		song.setSongID(s);
		Offense offense = new Offense();
		offense.setSong(song);
		
		Creator creator2 = new Creator();
		creator2.setUserName("蕭敬騰");
		long p2 = 12378945;
		creator2.setMemberId(p2);
		Album album2 = new Album();
		album2.setName("狂想曲");
		album2.setCreator(creator2);
		long a2 = 222;
		album2.setAlbumID(a2);
		Song song2 = new Song();
		song2.setName("你");
		song2.setAlbum(album2);
		long s2 = 789;
		song2.setSongID(s2);
		Offense offense2 = new Offense();
		offense2.setSong(song2);
		
		ArrayList list = new ArrayList();     //明細第一筆
		list.add(offense);
		list.add(amount);
		
		ArrayList list1 = new ArrayList();    //明細第二筆
		list1.add(offense2);
		list1.add(amount2);
		
		ArrayList[]  list3 = {list, list1};
		
		mav.addObject("offenses", list3);
		
		return mav;
	}
	
	//查詢檢舉內容-檢舉原因
	@RequestMapping("/offenseReason")
	public ModelAndView offensereason(String id) {
		ModelAndView mav = new ModelAndView("offenseReason");	
			
		System.out.println("ID=>"+id);
		
		Member member = new Member();
		member.setEmail("aaa@mail.com");
		member.setIdentityName("一般會員");
		long p = 12345678;
		member.setMemberId(p);
		Offense o = new Offense();
		o.setMember(member);
		o.setReason("有抄襲嫌疑");
		o.setCreateDate("2011/10/26 10:30:06");
		long of = 951;
		o.setOffenseRid(of);
		
		Member member2 = new Member();
		member2.setEmail("bbb@mail.com");
		member2.setIdentityName("個人創作者");
		long p2 = 56781234;
		member2.setMemberId(p2);
		Offense o2 = new Offense();
		o2.setMember(member2);
		o2.setReason("非該創作人的創作");
		o2.setCreateDate("2011/10/28 16:10:16");
		long of2 = 753;
		o2.setOffenseRid(of2);
		
		Set<Offense> offense = new HashSet();
		offense.add(o);
		offense.add(o2);
		Song song = new Song();
		song.setOffense(offense);
		
		mav.addObject("offenses", song);
		return mav;
	}
	
	//查詢檢舉內容-不當檢舉
	@RequestMapping("/offenseWrong")
	public String offensewrong(String memberId, String offenseId) {
				
		//System.out.println("memberId==>"+memberId);
		//System.out.println("offenseId==>"+offenseId);
		//System.out.println("不當檢舉!!!");
			
		return "redirect:offenseReason.do";
	}
	
	//查詢檢舉內容-隱藏專輯
	@RequestMapping("/hiddenAlbum")
	public String hiddenalbum() {
				
		//System.out.println("隱藏專輯!!!");
		
		return "redirect:offenseAlbum.do";
	}
	
	//查詢檢舉內容-隱藏歌曲
	@RequestMapping("/hiddenSong")
	public String hiddensong() {
					
		//System.out.println("隱藏歌曲!!!");
			
		return "redirect:offenseSong.do";
	}
	
	//查詢系統自動隱藏的資料
	@RequestMapping("/systemAutoHidden")
	public ModelAndView systemautohidden() {
		ModelAndView mav = new ModelAndView("systemAutoHidden");	
				
		return mav;
	}
	
	//查詢系統自動隱藏的資料-專輯Tab
	@RequestMapping("/queryHiddenAlbum")
	public ModelAndView queryhiddenalbum(String year, String month, String queryCreator) {
		ModelAndView mav = new ModelAndView("queryHiddenAlbum");	

		System.out.println("year==>"+year);
		System.out.println("month==>"+month);
		System.out.println("queryCreator==>"+queryCreator);
		
		String amount = "14";
		String amount2 = "30";
		
		Creator creator = new Creator();
		creator.setUserName("鄧福如");
		long p = 89451237;
		creator.setMemberId(p);
		Album album = new Album();
		album.setName("原來如此");
		album.setCreator(creator);
		long a = 111;
		album.setAlbumID(a);
		Offense offense = new Offense();
		offense.setAlbum(album);
		Hidden hidden = new Hidden();
		hidden.setOffense(offense);
		hidden.setStartDate("2011/09/10 12:30:10");
		

		Creator creator2 = new Creator();
		creator2.setUserName("蕭敬騰");
		long p2 = 12378945;
		creator2.setMemberId(p2);
		Album album2 = new Album();
		album2.setName("狂想曲");
		album2.setCreator(creator2);
		long a2 = 222;
		album2.setAlbumID(a2);
		Offense offense2 = new Offense();
		offense2.setAlbum(album2);
		Hidden hidden2 = new Hidden();
		hidden2.setOffense(offense2);
		hidden2.setStartDate("2011/09/30 14:50:00");
		
		ArrayList list = new ArrayList();     //明細第一筆
		list.add(hidden);
		list.add(amount);
		
		ArrayList list1 = new ArrayList();    //明細第二筆
		list1.add(hidden2);
		list1.add(amount2);
		
		ArrayList[]  list3 = {list, list1};
		
		mav.addObject("offenses", list3);
		return mav;
	}
	
	//查詢系統自動隱藏的資料-專輯Tab-取消隱藏
	@RequestMapping("/noHidden")
	public String nohidden(String ID) {

		System.out.println("ID>no==>"+ID);
				
		return "redirect:queryHiddenAlbum.do";
	}
			
	//查詢系統自動隱藏的資料-專輯Tab-確定隱藏
	@RequestMapping("/yesHidden")
	public String yeshidden(String ID) {

		System.out.println("ID>yes==>"+ID);
						
		return "redirect:queryHiddenAlbum.do";
	}
	
	//查詢系統自動隱藏的資料-歌曲Tab
	@RequestMapping("/queryHiddenSong")
	public ModelAndView queryhiddensong(String year, String month, String queryCreator) {
		ModelAndView mav = new ModelAndView("queryHiddenSong");	

		//System.out.println("year==>"+year);
		//System.out.println("month==>"+month);
		//System.out.println("queryCreator==>"+queryCreator);
		
		
		String amount = "14";
		String amount2 = "30";
		
		Creator creator = new Creator();
		creator.setUserName("鄧福如");
		long p = 89451237;
		creator.setMemberId(p);
		Album album = new Album();
		album.setName("原來如此");
		album.setCreator(creator);
		long a = 111;
		album.setAlbumID(a);
		Song song = new Song();
		song.setName("未填詞");
		song.setAlbum(album);
		long s = 456;
		song.setSongID(s);
		Offense offense = new Offense();
		offense.setSong(song);
		Hidden hidden = new Hidden();
		hidden.setOffense(offense);
		hidden.setStartDate("2011/09/10 12:30:10");
		
		Creator creator2 = new Creator();
		creator2.setUserName("蕭敬騰");
		long p2 = 12378945;
		creator2.setMemberId(p2);
		Album album2 = new Album();
		album2.setName("狂想曲");
		album2.setCreator(creator2);
		long a2 = 222;
		album2.setAlbumID(a2);
		Song song2 = new Song();
		song2.setName("你");
		song2.setAlbum(album2);
		long s2 = 789;
		song2.setSongID(s2);
		Offense offense2 = new Offense();
		offense2.setSong(song2);
		Hidden hidden2 = new Hidden();
		hidden2.setOffense(offense2);
		hidden2.setStartDate("2011/09/30 14:50:00");
		
		ArrayList list = new ArrayList();     //明細第一筆
		list.add(hidden);
		list.add(amount);
		
		ArrayList list1 = new ArrayList();    //明細第二筆
		list1.add(hidden2);
		list1.add(amount2);
		
		ArrayList[]  list3 = {list, list1};
		
		mav.addObject("offenses", list3);
		
		return mav;
	}
	
	//查詢系統自動隱藏的資料-歌曲Tab-取消隱藏
	@RequestMapping("/noHiddenSong")
	public String nohiddensong(String ID) {

		//System.out.println("ID>no==>"+ID);
					
		return "redirect:queryHiddenSong.do";
	}
				
	//查詢系統自動隱藏的資料-歌曲Tab-確定隱藏
	@RequestMapping("/yesHiddenSong")
	public String yeshiddensong(String ID) {

		//System.out.println("ID>yes==>"+ID);
							
		return "redirect:queryHiddenSong.do";
	}
	
	//檢舉介面
	@RequestMapping("/offense")
	public ModelAndView offense() {
		ModelAndView mav = new ModelAndView("offense");	
		
		OffenseType offenseType = new OffenseType();
		offenseType.setOffenseTypeName("歌曲/專輯重複");
		Offense offense = new Offense();
		offense.setOffenseType(offenseType);
		
		OffenseType offenseType2 = new OffenseType();
		offenseType2.setOffenseTypeName("歌詞有不雅文字");
		Offense offense2 = new Offense();
		offense2.setOffenseType(offenseType2);
		
		OffenseType offenseType3 = new OffenseType();
		offenseType3.setOffenseTypeName("音質不佳");
		Offense offense3 = new Offense();
		offense3.setOffenseType(offenseType3);

		OffenseType offenseType4 = new OffenseType();
		offenseType4.setOffenseTypeName("涉嫌抄襲");
		Offense offense4 = new Offense();
		offense4.setOffenseType(offenseType4);
		
		Offense[] of = {offense,offense2,offense3,offense4};
		mav.addObject("offense", of);
		return mav;
	}
	
	//檢舉介面-確定按鈕
	@RequestMapping("/addOffense")
	public ModelAndView addoffense(String offenseType, String content) {
		ModelAndView mav = new ModelAndView("editMusicCategoryClose");	
		
		//System.out.println("offenseType==>"+offenseType);
		//System.out.println("content==>"+content);
		
		return mav;
	}
	
	//檢舉介面-取消按鈕
	@RequestMapping("/cancleOffense")
	public ModelAndView cancleoffense() {
		ModelAndView mav = new ModelAndView("editMusicCategoryClose");	
		
		//System.out.println("取消檢舉!!!!!!");
		
		return mav;
	}
	
	//會員檢舉清單
	@RequestMapping("/memberOffenseList")
	public ModelAndView memberoffenselist() {
		ModelAndView mav = new ModelAndView("memberOffenseList");	
			
		Album album=new Album();
		album.setName("狂想曲");
		Song song=new Song();
		song.setName("你");
		song.setAlbum(album);
		Offense offense = new Offense();
		offense.setReason("音質太差");
		offense.setSong(song);
		long p = 123;
		offense.setOffenseRid(p);
		offense.setCreateDate("2011/10/26 10:30:06");
		
		Album album2=new Album();
		album2.setName("原來如此");
		Song song2=new Song();
		song2.setName("未填詞");
		song2.setAlbum(album2);
		Offense offense2 = new Offense();
		offense2.setReason("涉嫌抄襲");
		offense2.setSong(song2);
		long p2 = 456;
		offense2.setOffenseRid(p2);
		offense2.setCreateDate("2011/10/28 16:10:16");
		
		Set<Offense> of = new HashSet();
		of.add(offense);
		of.add(offense2);
		Member member = new Member();
		member.setOffense(of);
		long m = 75346;
		member.setMemberId(m);
			
		mav.addObject("member", member);
		return mav;
	}
	
	//會員檢舉清單-不當檢舉
	@RequestMapping("/memberOffenseWrong")
	public String memberoffensewrong(String memberId, String offenseId) {
					
		//System.out.println("memberId==>"+memberId);
		//System.out.println("offenseId==>"+offenseId);
		//System.out.println("不當檢舉!!!");
				
		return "redirect:memberOffenseList.do";
	}
	
	//查詢已被隱藏的資料
	@RequestMapping("/hiddened")
	public ModelAndView hiddened() {
		ModelAndView mav = new ModelAndView("hiddened");	
				
		return mav;
	}
		
	//查詢已被隱藏的資料-專輯Tab
	@RequestMapping("/hiddenedAlbum")
	public ModelAndView hiddenedalbum(String year, String month, String queryCreator) {
		ModelAndView mav = new ModelAndView("hiddenedAlbum");	

		System.out.println("year==>"+year);
		System.out.println("month==>"+month);
		System.out.println("queryCreator==>"+queryCreator);
			
		String amount = "14";
		String amount2 = "30";
			
		Creator creator = new Creator();
		creator.setUserName("鄧福如");
		long p = 89451237;
		creator.setMemberId(p);
		Album album = new Album();
		album.setName("原來如此");
		album.setCreator(creator);
		long a = 111;
		album.setAlbumID(a);
		Offense offense = new Offense();
		offense.setAlbum(album);
		Hidden hidden = new Hidden();
		hidden.setOffense(offense);
		hidden.setModifyDate("2011/09/10 12:30:10");
		hidden.setModifyUser("小明");
			

		Creator creator2 = new Creator();
		creator2.setUserName("蕭敬騰");
		long p2 = 12378945;
		creator2.setMemberId(p2);
		Album album2 = new Album();
		album2.setName("狂想曲");
		album2.setCreator(creator2);
		long a2 = 222;
		album2.setAlbumID(a2);
		Offense offense2 = new Offense();
		offense2.setAlbum(album2);
		Hidden hidden2 = new Hidden();
		hidden2.setOffense(offense2);
		hidden2.setModifyDate("2011/09/30 14:50:00");
		hidden2.setModifyUser("大胖");
		
			
		ArrayList list = new ArrayList();     //明細第一筆
		list.add(hidden);
		list.add(amount);
			
		ArrayList list1 = new ArrayList();    //明細第二筆
		list1.add(hidden2);
		list1.add(amount2);
			
		ArrayList[]  list3 = {list, list1};
			
		mav.addObject("offenses", list3);
		return mav;
	}
		
	//查詢已被隱藏的資料-專輯Tab-取消隱藏
	@RequestMapping("/noHiddened")
	public String nohiddened(String ID) {

		System.out.println("ID>no==>"+ID);
					
		return "redirect:hiddenedAlbum.do";
	}
				
	//查詢已被隱藏的資料-專輯Tab-確定隱藏
	@RequestMapping("/yesHiddened")
	public String yeshiddened(String ID) {

		System.out.println("ID>yes==>"+ID);
							
		return "redirect:hiddenedAlbum.do";
	}
		
	//查詢已被隱藏的資料-歌曲Tab
	@RequestMapping("/hiddenedSong")
	public ModelAndView hiddenedsong(String year, String month, String queryCreator) {
		ModelAndView mav = new ModelAndView("hiddenedSong");	

		//System.out.println("year==>"+year);
		//System.out.println("month==>"+month);
		//System.out.println("queryCreator==>"+queryCreator);
			
			
		String amount = "14";
		String amount2 = "30";
			
		Creator creator = new Creator();
		creator.setUserName("鄧福如");
		long p = 89451237;
		creator.setMemberId(p);
		Album album = new Album();
		album.setName("原來如此");
		album.setCreator(creator);
		long a = 111;
		album.setAlbumID(a);
		Song song = new Song();
		song.setName("未填詞");
		song.setAlbum(album);
		long s = 456;
		song.setSongID(s);
		Offense offense = new Offense();
		offense.setSong(song);
		Hidden hidden = new Hidden();
		hidden.setOffense(offense);
		hidden.setModifyDate("2011/09/10 12:30:10");
		hidden.setModifyUser("小胖");
		
			
		Creator creator2 = new Creator();
		creator2.setUserName("蕭敬騰");
		long p2 = 12378945;
		creator2.setMemberId(p2);
		Album album2 = new Album();
		album2.setName("狂想曲");
		album2.setCreator(creator2);
		long a2 = 222;
		album2.setAlbumID(a2);
		Song song2 = new Song();
		song2.setName("你");
		song2.setAlbum(album2);
		long s2 = 789;
		song2.setSongID(s2);
		Offense offense2 = new Offense();
		offense2.setSong(song2);
		Hidden hidden2 = new Hidden();
		hidden2.setOffense(offense2);
		hidden2.setModifyDate("2011/09/30 14:50:00");
		hidden2.setModifyUser("大雄");
		
			
		ArrayList list = new ArrayList();     //明細第一筆
		list.add(hidden);
		list.add(amount);
			
		ArrayList list1 = new ArrayList();    //明細第二筆
		list1.add(hidden2);
		list1.add(amount2);
			
		ArrayList[]  list3 = {list, list1};
			
		mav.addObject("offenses", list3);
			
		return mav;
	}
		
	//查詢已被隱藏的資料-歌曲Tab-取消隱藏
	@RequestMapping("/noHiddenedSong")
	public String nohiddenedsong(String ID) {

		//System.out.println("ID>no==>"+ID);
						
		return "redirect:hiddenedSong.do";
	}
					
	//查詢已被隱藏的資料-歌曲Tab-確定隱藏
	@RequestMapping("/yesHiddenedSong")
	public String yeshiddenedsong(String ID) {

		//System.out.println("ID>yes==>"+ID);
								
		return "redirect:hiddenedSong.do";
	}
}
