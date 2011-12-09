package com.ubn.befamous.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.Creator;
import com.ubn.befamous.entity.MonthRanking;
import com.ubn.befamous.entity.MusicCategory;
import com.ubn.befamous.entity.Offense;
import com.ubn.befamous.entity.Song;
import com.ubn.befamous.entity.SongPrice;
import com.ubn.befamous.entity.WeekRanking;
import com.ubn.befamous.service.MusicService;


@Controller
public class ManageRankingCategoryController {
	
	@Autowired
	 MusicService musicService;
	
	//音樂類別管理
	@RequestMapping("/manageMusicCategory")
	public ModelAndView managemusiccategory(String adminId) {
		ModelAndView mav = new ModelAndView("manageMusicCategory");
		
		MusicCategory[] mc = musicService.queryMusicCategory();
		
		mav.addObject("musicCategory",mc);
		mav.addObject("adminID",adminId);
		return mav;
	}
	
	//音樂類別管理-新增音樂類別
	@RequestMapping("/addMusicCategory")
	public String addmusiccategory(String categoryName,String adminID) {
		
		System.out.println("categoryName==>"+categoryName);
		
		musicService.addMusicCategory(Long.parseLong(adminID),categoryName);
		
		return "redirect:manageMusicCategory.do?adminId="+adminID;
	}
	
	//音樂類別管理-新增音樂子類別
	@RequestMapping("/addSubMusicCategory")
	public String addsubmusiccategory(String categoryName,long adminID,long fatherID) {
		
		System.out.println("adminID==>"+adminID);
		System.out.println("fatherID==>"+fatherID);
		System.out.println("subcategoryName==>"+categoryName);
			
		musicService.addSubMusicCategory(adminID,categoryName,fatherID);
		return "redirect:subMusicCategory.do?ID="+fatherID+"&adminID="+adminID;
	}
	
	//音樂類別管理-編輯音樂類別或子類別
	@RequestMapping("/editMusicCategory")
	public ModelAndView editmusiccategory(long ID,long adminID) {
		ModelAndView mav = new ModelAndView("editMusicCategory");
		MusicCategory mc = musicService.musicCategory(String.valueOf(ID));
		mav.addObject("mc",mc);
		mav.addObject("adminID",adminID);
		mav.addObject("fatherID",ID);
		return mav;
	}
	
	//音樂類別管理-關閉編輯音樂類別或子類別的視窗   (怪怪的)
		@RequestMapping("/editMusicCategoryClose")
		public ModelAndView editmusiccategoryclose(long ID,long adminID, String modifyName) {
			ModelAndView mav = new ModelAndView("editMusicCategoryClose");

			System.out.println("modifyName==>"+modifyName);
			musicService.updateMusicCategory(ID, modifyName, adminID);
			return mav;
		}
	
	//音樂類別管理-刪除音樂類別
	@RequestMapping("/deleteMusicCategory")
	public String deletemusiccategory(long ID) {
		
		musicService.deleteMusicCategory(ID);
		
		System.out.println("刪除類別");
		
		return "redirect:manageMusicCategory.do";
	}
	
	//音樂類別管理-刪除音樂子類別
	@RequestMapping("/deleteSubMusicCategory")
	public String deletesubmusiccategory(long ID) {
			
		musicService.deleteMusicCategory(ID);
			
		System.out.println("刪除類別");
			
		return "redirect:subMusicCategory.do";
	}
	
	//音樂子類別管理
	@RequestMapping("/subMusicCategory")
	public ModelAndView submusiccategory(long ID,long adminID) {
		ModelAndView mav = new ModelAndView("subMusicCategory");
		System.out.println("ID==>"+ID+", adminID==>"+adminID);
		MusicCategory[] mc = musicService.querySubMusicCatrgory(ID);
		
		
		mav.addObject("musicCategory",mc);
		mav.addObject("adminID",adminID);
		mav.addObject("fatherID",ID);
		return mav;
	}
	
	//Kevin的
	//查詢專輯周榜
	@RequestMapping(value = "/queryAlbumWeekRankingForAdmin")
	public ModelAndView queryAlbumWeekRankingForAdmin(String adminId,String albumName,String creatorName,
			String musicCategory,String startDate,String endDate) {
		System.out.println("musicCategory===>"+musicCategory);
		
		ModelAndView mav = new ModelAndView("queryAlbumWeekRankingForAdmin");
		
		ArrayList list = musicService.queryAlbumWeekRankingForAdmin(albumName, creatorName, musicCategory, startDate, endDate);
		MusicCategory[] mc = musicService.musicCategoryList();
		mav.addObject("arAlbum", list);
		mav.addObject("mc", mc);
		mav.addObject("adminID", adminId);
		return mav;
	}
	
	//修改專輯週CP值
	@RequestMapping(value = "/updateAlbumWeekCP")
	public String updateAlbumWeekCP(String cpPoint,String adminID,String albumID) {
		
		//update CP值
		System.out.println("cpPoint===>"+cpPoint);
		System.out.println("albumID===>"+albumID);
		musicService.updateAlbumWeekCP(adminID, albumID, Integer.parseInt(cpPoint));
		return "queryAlbumWeekRankingForAdmin";
	}
	
	
	//查詢專輯月榜
	@RequestMapping(value = "/queryAlbumMonthRankingForAdmin")
	public ModelAndView queryAlbumMonthRankingForAdmin(String adminId,String albumName,String creatorName,
			String musicCategory,String startDate,String endDate) {
		System.out.println("musicCategory===>"+musicCategory);
		
		ModelAndView mav = new ModelAndView("queryAlbumMonthRankingForAdmin");
		
		ArrayList list = musicService.queryAlbumMonthRankingForAdmin(albumName, creatorName, musicCategory, startDate, endDate);
		MusicCategory[] mc = musicService.musicCategoryList();
		mav.addObject("mc", mc);
		mav.addObject("arAlbum", list);
		mav.addObject("adminID", adminId);
		return mav;
	}
	
	
	//修改專輯月CP值
	@RequestMapping(value = "/updateAlbumMonthCP")
	public String updateAlbumMonthCP(String cpPoint,String adminID,String albumID) {
		
		//update CP值
		System.out.println("cpPoint===>"+cpPoint);
		System.out.println("albumID===>"+albumID);
		musicService.updateAlbumMonthCP(adminID, albumID, Integer.parseInt(cpPoint));
		return "queryAlbumMonthRankingForAdmin";
	}
	
	
	//查詢歌曲周榜
	@RequestMapping(value = "/querySongWeekRankingForAdmin")
	public ModelAndView querySongWeekRankingForAdmin(String adminId,String songName,String albumName,String creatorName,
			String musicCategory,String startDate,String endDate) {
		System.out.println("musicCategory===>"+musicCategory);
		
		ModelAndView mav = new ModelAndView("querySongWeekRankingForAdmin");	
		
		ArrayList list = musicService.querySongWeekRankingForAdmin(songName, albumName, creatorName, musicCategory, startDate, endDate);
		MusicCategory[] mc = musicService.musicCategoryList();
		mav.addObject("mc", mc);
		mav.addObject("arSong", list);
		mav.addObject("adminID", adminId);
		return mav;
		
	}
	
	//修改歌曲週CP值
	@RequestMapping(value = "/updateSongWeekCP")
	public String updateSongWeekCP(String cpPoint,String adminID,String songID) {
		
		//update CP值
		System.out.println("songWeekcpPoint===>"+cpPoint);
		System.out.println("songID===>"+songID);
		musicService.updateSongWeekCP(adminID, songID, Integer.parseInt(cpPoint));
		return "querySongWeekRankingForAdmin";
	}
	
	
	//查詢歌曲月榜
	@RequestMapping(value = "/querySongMonthRankingForAdmin")
	public ModelAndView querySongMonthRankingForAdmin(String adminId,String songName,String albumName,String creatorName,
			String musicCategory,String startDate,String endDate) {
		System.out.println("musicCategory===>"+musicCategory);
		
		ModelAndView mav = new ModelAndView("querySongMonthRankingForAdmin");	
		
		ArrayList list = musicService.querySongMonthRankingForAdmin(songName, albumName, creatorName, musicCategory, startDate, endDate);
		MusicCategory[] mc = musicService.musicCategoryList();
		mav.addObject("mc", mc);
		mav.addObject("arSong", list);
		mav.addObject("adminID", adminId);
		return mav;
		
	}
	
	//修改歌曲月CP值
	@RequestMapping(value = "/updateSongMonthCP")
	public String updateSongMonthCP(String cpPoint,String adminID,String songID) {
		
		//update CP值
		System.out.println("songWeekcpPoint===>"+cpPoint);
		System.out.println("songID===>"+songID);
		musicService.updateSongMonthCP(adminID, songID, Integer.parseInt(cpPoint));
		return "querySongMonthRankingForAdmin";
	}
	
	
	//查詢創作人周榜
	@RequestMapping(value = "/queryCreatorWeekRankingForAdmin")
	public ModelAndView queryCreatorWeekRankingForAdmin(String adminId,String albumName,String creatorName,
			String musicCategory,String startDate,String endDate) {
		System.out.println("musicCategory===>"+musicCategory);
		
		ModelAndView mav = new ModelAndView("queryCreatorWeekRankingForAdmin");
		
		ArrayList list = musicService.queryCreatorWeekRankingForAdmin(creatorName);
		MusicCategory[] mc = musicService.musicCategoryList();
		mav.addObject("mc", mc);
		mav.addObject("arCreator", list);
		mav.addObject("adminID", adminId);
		return mav;
	}
	
	//修改創作人週CP值
	@RequestMapping(value = "/updateCreatorWeekCP")
	public String updateCreatorWeekCP(String cpPoint,String adminID,String creatorID) {
		
		//update CP值
		System.out.println("creatorWeekcpPoint===>"+cpPoint);
		System.out.println("creatorID===>"+creatorID);
		musicService.updateCreatorWeekCP(adminID, creatorID, Integer.parseInt(cpPoint));
		return "queryCreatorWeekRankingForAdmin";
	}
	
	
	//查詢創作人月榜
	@RequestMapping(value = "/queryCreatorMonthRankingForAdmin")
	public ModelAndView queryCreatorMonthRankingForAdmin(String adminId,String albumName,String creatorName,
			String musicCategory,String startDate,String endDate) {
		System.out.println("musicCategory===>"+musicCategory);
		
		ModelAndView mav = new ModelAndView("queryCreatorMonthRankingForAdmin");
	
		ArrayList list = musicService.queryCreatorMonthRankingForAdmin(creatorName);
		MusicCategory[] mc = musicService.musicCategoryList();
		mav.addObject("mc", mc);
		mav.addObject("arCreator", list);
		mav.addObject("adminID", adminId);
		return mav;
	}
	
	//修改創作人月CP值
	@RequestMapping(value = "/updateCreatorMonthCP")
	public String updateCreatorMonthCP(String cpPoint,String adminID,String creatorID) {
		
		//update CP值
		System.out.println("CreatorMonthcpPoint===>"+cpPoint);
		System.out.println("creatorID===>"+creatorID);
		musicService.updateCreatorMonthCP(adminID, creatorID, Integer.parseInt(cpPoint));
		return "queryCreatorMonthRankingForAdmin";
	}
}