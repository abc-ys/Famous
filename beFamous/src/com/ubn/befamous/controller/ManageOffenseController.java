package com.ubn.befamous.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.ubn.befamous.service.TransactionRecordService;


@Controller
public class ManageOffenseController {
	
	@Autowired
	private TransactionRecordService transactionRecordService;

	//查詢檢舉內容
		@RequestMapping("/offenseArea")
		public ModelAndView offensearea(long adminId) {
			ModelAndView mav = new ModelAndView("offenseArea");
			mav.addObject("adminId", adminId);
			return mav;
		}

		//查詢檢舉內容-專輯Tab
		@RequestMapping("/offenseAlbum")
		public ModelAndView offensealbum(long adminId) {
			ModelAndView mav = new ModelAndView("offenseAlbum");	
			List<Object[]> albums =  (List<Object[]>) this.transactionRecordService.queryUnHandle().get(0);		
			ArrayList allList = new ArrayList();
			for (Object[] s:albums) {
				ArrayList list = new ArrayList();
				list.add((Album)s[0]);
				list.add((Long)s[1]);
				allList.add(list);
			}
			mav.addObject("adminId", adminId);
			mav.addObject("offenses", allList);
			return mav;
		}
		
		//查詢檢舉內容-歌曲Tab
		@RequestMapping("/offenseSong")
		public ModelAndView offensesong(long adminId) {
			ModelAndView mav = new ModelAndView("offenseSong");			
			List<Object[]> songs =  (List<Object[]>) this.transactionRecordService.queryUnHandle().get(1);		
			ArrayList allList = new ArrayList();
			for (Object[] s:songs) {
				ArrayList list = new ArrayList();
				list.add((Song)s[0]);
				list.add((Long)s[1]);
				allList.add(list);
			}		
			mav.addObject("adminId", adminId);
			mav.addObject("offenses", allList);
			return mav;
		}
		
		//查詢檢舉內容-檢舉原因
		@RequestMapping("/offenseReason")
		public ModelAndView offensereason(long productionCategoryId) {
			ModelAndView mav = new ModelAndView("offenseReason");	
			Offense[] offenseReason = this.transactionRecordService.queryOffenseReason(productionCategoryId);
			mav.addObject("offenseReason", offenseReason);
			mav.addObject("productionCategoryId", productionCategoryId);
			return mav;
		}
		
		//查詢檢舉內容-不當檢舉
		@RequestMapping("/offenseWrong")
		public String offensewrong(long adminId, long offenseId, long productionCategoryId) {
			this.transactionRecordService.updateIncorrectOffense(adminId, offenseId);
			return "redirect:offenseReason.do?productionCategoryId="+productionCategoryId;
		}
		
		//查詢檢舉內容-隱藏專輯
		@RequestMapping("/hiddenAlbum")
		public String hiddenalbum(long albumId, long adminId){				
			this.transactionRecordService.updateAlbumHide(adminId, albumId);		
			return "redirect:offenseAlbum.do?adminId="+adminId;
		}
		
		//查詢檢舉內容-隱藏歌曲
		@RequestMapping("/hiddenSong")
		public String hiddensong(long adminId, long songId) {
			System.out.println(" admin="+adminId+", songId="+songId);
			this.transactionRecordService.updateSongHide(adminId, songId);	
			return "redirect:offenseSong.do?adminId="+adminId;
		}
		
		//查詢系統自動隱藏的資料(起始查詢)
		@RequestMapping("/systemAutoHidden")
		public ModelAndView systemautohidden(long adminId) {
			ModelAndView mav = new ModelAndView("systemAutoHidden");
			ArrayList albums =  this.transactionRecordService.queryAutoHide();
			mav.addObject("queryType","1");
			mav.addObject("offenses", albums);
			mav.addObject("adminId", adminId);
			return mav;
		}
			
		//查詢系統自動隱藏的資料-(根據查詢條件)
		@RequestMapping("/queryHiddenList")
		public ModelAndView queryHiddenList(String type, String year, String month, String queryCreator, long adminId) {
			ModelAndView mav = new ModelAndView("queryHiddenList");	
			ArrayList allList = new ArrayList();
			if(type.equals("1")){
				ArrayList albums =  this.transactionRecordService.queryAutoHideByDate(type,year,month,queryCreator);
				mav.addObject("offenses", albums);
			}
			else{
				ArrayList songs = this.transactionRecordService.queryAutoHideByDate(type,year,month,queryCreator);
				mav.addObject("offenses", songs);
			}
			mav.addObject("year", year);
			mav.addObject("month", month);
			mav.addObject("queryCreator", queryCreator);
			mav.addObject("adminId", adminId);
			mav.addObject("queryType",type);	
			return mav;
		}
			
		//查詢系統自動隱藏的資料-取消隱藏
		@RequestMapping("/noHidden")
		public String nohidden(long adminId,long hiddenId, String type, String year, String month, String queryCreator) {
			this.transactionRecordService.cancelHide(adminId, hiddenId);
			return "redirect:queryHiddenList.do?type="+type+"&year="+year+"&month="+month+"&queryCreator="+queryCreator+"&adminId="+adminId;
		}
				
		//查詢系統自動隱藏的資料-確定隱藏
		@RequestMapping("/yesHidden")
		public String yeshidden(long adminId,long hiddenId, String type, String year, String month, String queryCreator) {
			this.transactionRecordService.comfirmHide(adminId, hiddenId);
			return "redirect:queryHiddenList.do?type="+type+"&year="+year+"&month="+month+"&queryCreator="+queryCreator+"&adminId="+adminId;	
		}
		
		//檢舉介面
		@RequestMapping("/offense")
		public ModelAndView offense(long productionCategoryId, long userId) {
			ModelAndView mav = new ModelAndView("offense");	
			System.out.println("offense======>");
			System.out.println("productionCategoryId="+productionCategoryId+", userId="+userId);
			mav.addObject("productionCategoryId", productionCategoryId);
			mav.addObject("userId", userId);
			mav.addObject("offense", this.transactionRecordService.queryOffenseCategory(productionCategoryId));
			return mav;
		}
		
		//檢舉介面-確定按鈕
		@RequestMapping("/addOffense")
		public ModelAndView addoffense(long userId, long productionCategoryId, long offenseTypeId,
				String reason) {
			System.out.println("addOffense==>");
			ModelAndView mav = new ModelAndView("editMusicCategoryClose");
			System.out.println("userId==>"+userId);
			System.out.println("productionCategoryId==>"+productionCategoryId);
			System.out.println("offenseTypeId==>"+offenseTypeId);
			System.out.println("reason==>"+reason);
			this.transactionRecordService.addOffense(userId, productionCategoryId, offenseTypeId,reason);	
			return mav;
		}
			
		//會員檢舉清單
		@RequestMapping("/memberOffenseList")
		public ModelAndView memberoffenselist(long adminId, long userId) {
			ModelAndView mav = new ModelAndView("memberOffenseList");			
			Offense[] offenseList = this.transactionRecordService.queryOffenseByUser(userId);			
			mav.addObject("member", offenseList);
			mav.addObject("adminId", adminId);
			return mav;
		}
		
		//會員檢舉清單-不當檢舉
		@RequestMapping("/memberOffenseWrong")
		public String memberoffensewrong(long adminId, long offenseId, long userId) {
			System.out.println("會員檢舉清單-不當檢舉");
			this.transactionRecordService.updateIncorrectOffense(adminId,offenseId);				
			return "redirect:memberOffenseList.do?userId="+userId+"&adminId="+adminId;
		}
		
		//查詢已被隱藏的資料(起始查詢)
		@RequestMapping("/hiddened")
		public ModelAndView hiddened(long adminId) {
			System.out.println("hiddened");
			ModelAndView mav = new ModelAndView("hiddened");	
			List<Album> albums =  (List<Album>) this.transactionRecordService.queryAlreadyHide().get(0);
			ArrayList allList = new ArrayList();
			allList.add(albums);
			mav.addObject("queryType","1");
			mav.addObject("adminId",adminId);
			mav.addObject("offenses", allList);
			return mav;
		}
			
		//查詢已被隱藏的資料-(根據查詢條件)
		@RequestMapping("/queryHiddenedList")
		public ModelAndView queryHiddenedList(long adminId, String type, String year, String month, String queryCreator) {
			ModelAndView mav = new ModelAndView("queryHiddenedList");	
			ArrayList allList = new ArrayList();
			if(type.equals("1")){
				List<Album> albums =  (List<Album>) this.transactionRecordService.queryAlreadyHideByDate(type, year, month, queryCreator).get(0);
				allList.add(albums);
			}
			else{
				List<Song> songs =  (List<Song>) this.transactionRecordService.queryAlreadyHideByDate(type,year,month,queryCreator).get(0);
				allList.add(songs);
			}
			mav.addObject("queryType",type);
			mav.addObject("year",year);
			mav.addObject("month",month);
			mav.addObject("queryCreator",queryCreator);
			mav.addObject("adminId",adminId);
			mav.addObject("offenses", allList);
			return mav;
		}
		
		//查詢已被隱藏的資料-取消隱藏
		@RequestMapping("/noHiddened")
		public String nohiddened(long adminId,long hiddenId, String type, String year, String month, String queryCreator) {
			System.out.println("hiddenId>no==>"+hiddenId);
			this.transactionRecordService.cancelHide(adminId, hiddenId);
			return "redirect:queryHiddenedList.do?adminId="+adminId+"&type="+type+"&year="+year+"&month="+month+"&queryCreator="+queryCreator;
		
		}
}
