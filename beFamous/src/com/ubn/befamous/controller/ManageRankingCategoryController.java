package com.ubn.befamous.controller;

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
	public ModelAndView managemusiccategory() {
		ModelAndView mav = new ModelAndView("manageMusicCategory");
		
		MusicCategory[] mc = musicService.queryMusicCategory();
		
		mav.addObject("musicCategory",mc);
		return mav;
	}
	
	//音樂類別管理-新增音樂類別
	@RequestMapping("/addMusicCategory")
	public String addmusiccategory(String categoryName) {

		long managerID = 1;
		
		System.out.println("categoryName==>"+categoryName);
		
		musicService.addMusicCategory(managerID,categoryName);
		
		return "redirect:manageMusicCategory.do";
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
	
	//音樂類別管理-刪除音樂類別或子類別
	@RequestMapping("/deleteMusicCategory")
	public String deletemusiccategory(long ID) {
		
		musicService.deleteMusicCategory(ID);
		
		System.out.println("刪除類別");
		
		return "redirect:manageMusicCategory.do";
	}
	
	//音樂子類別管理
	@RequestMapping("/subMusicCategory")
	public ModelAndView submusiccategory(long ID,long adminID) {
		ModelAndView mav = new ModelAndView("subMusicCategory");
		
		MusicCategory[] mc = musicService.querySubMusicCatrgory(ID);
		
		mav.addObject("musicCategory",mc);
		mav.addObject("adminID",adminID);
		mav.addObject("fatherID",ID);
		return mav;
	}
	
	//Kevin的
	//查詢專輯周榜
	@RequestMapping(value = "/queryAlbumWeekRankingForAdmin")
	public ModelAndView queryAlbumWeekRankingForAdmin(String albumName,String creatorName,
			String musicCategory,String startDate,String endDate) {
		System.out.println("musicCategory===>"+musicCategory);
		
		ModelAndView mav = new ModelAndView("queryAlbumWeekRankingForAdmin");
		
		Creator creator = new Creator();
		MusicCategory musicCategory1 = new MusicCategory();
		musicCategory1.setName("rock");
		creator.setAccountName("kevin");
		creator.setAccountNO("12345678");
		creator.setId(123456);
		
		Album album = new Album();
		album.setName("bird");
		album.setId(1234567);
		album.setCreator(creator);
		album.setBrand("UBN");
		album.setCover("image/image001.png");
		album.setType("MP3");
		album.setCreateDate("2011/10/25");
		album.setMusicCategory(musicCategory1);
		album.setIntroduction("This is very good album!");
		
		WeekRanking weekRanking = new WeekRanking();
		weekRanking.setId(123456);
		weekRanking.setModifyValue("20");
		WeekRanking weekRanking2 = new WeekRanking();
		weekRanking2.setId(1234567);
		weekRanking2.setModifyValue("30");
		
		Album album2 = new Album();
		album2.setName("兩隻老虎");
		album2.setId(1234567);
		album2.setCreator(creator);
		album2.setBrand("UBN");
		album2.setCover("image/image001.png");
		album2.setType("MP3");
		album2.setCreateDate("2011/10/25");
		album2.setMusicCategory(musicCategory1);
		album2.setIntroduction("This is very good album!");
		
		Set<WeekRanking> wr = new HashSet();
		wr.add(weekRanking);
		
		Set<WeekRanking> wr2 = new HashSet();
		wr2.add(weekRanking2);
		
		album.setWeekRanking(wr);		
		album2.setWeekRanking(wr2);
		
        Album[] arAlbum = {album,album2}; 
		
		mav.addObject("arAlbum", arAlbum);
	
		return mav;
	}
	
	//修改專輯週CP值
	@RequestMapping(value = "/updateAlbumWeekCP", method = RequestMethod.POST)
	public String updateAlbumWeekCP(String cpPoint,String cpId) {
		
		//update CP值
		System.out.println("cpPoint===>"+cpPoint);
		System.out.println("cpId===>"+cpId);
		return "queryAlbumWeekRankingForAdmin";
	}
	
	
	//查詢專輯月榜
	@RequestMapping(value = "/queryAlbumMonthRankingForAdmin")
	public ModelAndView queryAlbumMonthRankingForAdmin(String albumName,String creatorName,
			String musicCategory,String startDate,String endDate) {
		System.out.println("musicCategory===>"+musicCategory);
		
		ModelAndView mav = new ModelAndView("queryAlbumMonthRankingForAdmin");
		
		Creator creator = new Creator();
		MusicCategory musicCategory1 = new MusicCategory();
		musicCategory1.setName("rock");
		creator.setAccountName("kevin");
		creator.setAccountNO("12345678");
		creator.setId(123456);
		
		Album album = new Album();
		album.setName("bird");
		album.setId(1234567);
		album.setCreator(creator);
		album.setBrand("UBN");
		album.setCover("image/image001.png");
		album.setType("MP3");
		album.setCreateDate("2011/10/25");
		album.setMusicCategory(musicCategory1);
		album.setIntroduction("This is very good album!");
		
		MonthRanking monthRanking = new MonthRanking();
		monthRanking.setId(123456);
		monthRanking.setModifyValue("20");
		MonthRanking monthRanking2 = new MonthRanking();
		monthRanking2.setId(1234567);
		monthRanking2.setModifyValue("30");
		
		Album album2 = new Album();
		album2.setName("兩隻老虎");
		album2.setId(1234567);
		album2.setCreator(creator);
		album2.setBrand("UBN");
		album2.setCover("image/image001.png");
		album2.setType("MP3");
		album2.setCreateDate("2011/10/25");
		album2.setMusicCategory(musicCategory1);
		album2.setIntroduction("This is very good album!");
		
		Set<MonthRanking> mr = new HashSet();
		mr.add(monthRanking);
		
		Set<MonthRanking> mr2 = new HashSet();
		mr2.add(monthRanking2);
		
		album.setMonthRanking(mr);
		album2.setMonthRanking(mr2);
		
        Album[] arAlbum = {album,album2}; 
		
		mav.addObject("arAlbum", arAlbum);
	
		return mav;
	}
	
	
	//修改專輯月CP值
	@RequestMapping(value = "/updateAlbumMonthCP", method = RequestMethod.POST)
	public String updateAlbumMonthCP(String cpPoint,String cpId) {
		
		//update CP值
		System.out.println("cpPoint===>"+cpPoint);
		return "queryAlbumMonthRankingForAdmin";
	}
	
	
	//查詢歌曲周榜
	@RequestMapping(value = "/querySongWeekRankingForAdmin")
	public ModelAndView querySongWeekRankingForAdmin(String songName,String albumName,String creatorName,
			String musicCategory,String startDate,String endDate) {
		System.out.println("musicCategory===>"+musicCategory);
		
		ModelAndView mav = new ModelAndView("querySongWeekRankingForAdmin");	
		Creator creator = new Creator();
		creator.setAccountName("kevin");
		creator.setAccountNO("12345678");
		creator.setUserName("kevin");
		Album album = new Album();
		album.setName("鳥");
		album.setId(1234567);
		album.setCreator(creator);
		album.setBrand("UBN");
		album.setCover("image/image001.png");
		album.setType("MP3");
		album.setCreateDate("2011/10/25");
		album.setIntroduction("This is very good album!");
		
		WeekRanking weekRanking = new WeekRanking();
		weekRanking.setId(123456);
		weekRanking.setModifyValue("20");
		WeekRanking weekRanking2 = new WeekRanking();
		weekRanking2.setId(1234567);
		weekRanking2.setModifyValue("30");
		
		MusicCategory musicCategory1 = new MusicCategory();
		musicCategory1.setName("rock");
		Song song = new Song();
		SongPrice songPrice = new SongPrice();
		songPrice.setPrice("30");
		songPrice.setDiscountBonus("15");
		songPrice.setDiscountPrice("15");
		song.setId(1234);
		song.setName("我愛一條柴");
		song.setSongPrice(songPrice);
	    song.setAlbum(album);
	    song.setCreateDate("2011/11/01");
	    song.setMusicCategory(musicCategory1);
		Song song2 = new Song();
		song2.setId(5678);
		song2.setName("忘情水");
		song2.setSongPrice(songPrice);
		song2.setAlbum(album);
		song2.setCreateDate("2011/11/01");
		song2.setMusicCategory(musicCategory1);
		
		
		Set<WeekRanking> wr = new HashSet();
		wr.add(weekRanking);
		
		Set<WeekRanking> wr2 = new HashSet();
		wr2.add(weekRanking2);
		
		song.setWeekRanking(wr);
		song2.setWeekRanking(wr2);
		
		Song[] arSong = {song,song2}; 
		mav.addObject("arSong", arSong);
		
		return mav;
		
	}
	
	//修改歌曲週CP值
	@RequestMapping(value = "/updateSongWeekCP", method = RequestMethod.POST)
	public String updateSongWeekCP(String cpPoint,String cpId) {
		
		//update CP值
		System.out.println("songWeekcpPoint===>"+cpPoint);
		return "querySongWeekRankingForAdmin";
	}
	
	
	//查詢歌曲月榜
	@RequestMapping(value = "/querySongMonthRankingForAdmin")
	public ModelAndView querySongMonthRankingForAdmin(String songName,String albumName,String creatorName,
			String musicCategory,String startDate,String endDate) {
		System.out.println("musicCategory===>"+musicCategory);
		
		ModelAndView mav = new ModelAndView("querySongMonthRankingForAdmin");	
		Creator creator = new Creator();
		creator.setAccountName("kevin");
		creator.setAccountNO("12345678");
		creator.setUserName("kevin");
		Album album = new Album();
		album.setName("鳥");
		album.setId(1234567);
		album.setCreator(creator);
		album.setBrand("UBN");
		album.setCover("image/image001.png");
		album.setType("MP3");
		album.setCreateDate("2011/10/25");
		album.setIntroduction("This is very good album!");
		
		
		MonthRanking monthRanking = new MonthRanking();
		monthRanking.setId(123456);
		monthRanking.setModifyValue("20");
		MonthRanking monthRanking2 = new MonthRanking();
		monthRanking2.setId(1234567);
		monthRanking2.setModifyValue("30");
		
		MusicCategory musicCategory1 = new MusicCategory();
		musicCategory1.setName("rock");
		Song song = new Song();
		SongPrice songPrice = new SongPrice();
		songPrice.setPrice("30");
		songPrice.setDiscountBonus("15");
		songPrice.setDiscountPrice("15");
		song.setId(1234);
		song.setName("我愛一條柴");
		song.setSongPrice(songPrice);
	    song.setAlbum(album);
	    song.setCreateDate("2011/11/01");
	    song.setMusicCategory(musicCategory1);
		Song song2 = new Song();
		song2.setId(5678);
		song2.setName("忘情水");
		song2.setSongPrice(songPrice);
		song2.setAlbum(album);
		song2.setCreateDate("2011/11/01");
		song2.setMusicCategory(musicCategory1);
		

		Set<MonthRanking> mr = new HashSet();
		mr.add(monthRanking);
		
		Set<MonthRanking> mr2 = new HashSet();
		mr2.add(monthRanking2);
		
		song.setMonthRanking(mr);
		song2.setMonthRanking(mr2);
		
		Song[] arSong = {song,song2}; 
		mav.addObject("arSong", arSong);
		
		return mav;
		
	}
	
	//修改歌曲月CP值
	@RequestMapping(value = "/updateSongMonthCP", method = RequestMethod.POST)
	public String updateSongMonthCP(String cpPoint,String cpId) {
		
		//update CP值
		System.out.println("songWeekcpPoint===>"+cpPoint);
		return "querySongMonthRankingForAdmin";
	}
	
	
	//查詢創作人周榜
	@RequestMapping(value = "/queryCreatorWeekRankingForAdmin")
	public ModelAndView queryCreatorWeekRankingForAdmin(String albumName,String creatorName,
			String musicCategory,String startDate,String endDate) {
		System.out.println("musicCategory===>"+musicCategory);
		
		ModelAndView mav = new ModelAndView("queryCreatorWeekRankingForAdmin");
		
		
		Creator creator = new Creator();
		creator.setAccountName("kevin");
		creator.setAccountNO("12345678");
		creator.setUserName("怡秀姐");
		creator.setId(123456);
		Creator creator2 = new Creator();
		creator2.setAccountName("kevin");
		creator2.setAccountNO("12345678");
		creator2.setUserName("劉小駿");
		creator2.setId(555555);
		
		WeekRanking weekRanking = new WeekRanking();
		weekRanking.setId(123456);
		weekRanking.setModifyValue("20");
		WeekRanking weekRanking2 = new WeekRanking();
		weekRanking2.setId(1234567);
		weekRanking2.setModifyValue("30");
		
		
		Set<WeekRanking> wr = new HashSet();
		wr.add(weekRanking);
		
		Set<WeekRanking> wr2 = new HashSet();
		wr2.add(weekRanking2);
		
		creator.setWeekRanking(wr);
		creator2.setWeekRanking(wr2);
		
		Creator[] arCreator = {creator,creator2}; 
		mav.addObject("arCreator", arCreator);
		return mav;
	}
	
	//修改創作人週CP值
	@RequestMapping(value = "/updateCreatorWeekCP", method = RequestMethod.POST)
	public String updateCreatorWeekCP(String cpPoint,String cpId) {
		
		//update CP值
		System.out.println("creatorWeekcpPoint===>"+cpPoint);
		return "queryCreatorWeekRankingForAdmin";
	}
	
	
	//查詢創作人月榜
	@RequestMapping(value = "/queryCreatorMonthRankingForAdmin")
	public ModelAndView queryCreatorMonthRankingForAdmin(String albumName,String creatorName,
			String musicCategory,String startDate,String endDate) {
		System.out.println("musicCategory===>"+musicCategory);
		
		ModelAndView mav = new ModelAndView("queryCreatorMonthRankingForAdmin");
		
		
		Creator creator = new Creator();
		creator.setAccountName("kevin");
		creator.setAccountNO("12345678");
		creator.setUserName("怡秀姐");
		creator.setId(123456);
		Creator creator2 = new Creator();
		creator2.setAccountName("kevin");
		creator2.setAccountNO("12345678");
		creator2.setUserName("劉小駿");
		creator2.setId(555555);
		
		MonthRanking monthRanking = new MonthRanking();
		monthRanking.setId(123456);
		monthRanking.setModifyValue("20");
		MonthRanking monthRanking2 = new MonthRanking();
		monthRanking2.setId(1234567);
		monthRanking2.setModifyValue("30");	

		Set<MonthRanking> mr = new HashSet();
		mr.add(monthRanking);
		
		Set<MonthRanking> mr2 = new HashSet();
		mr2.add(monthRanking2);
		
		creator.setMonthRanking(mr);
		creator2.setMonthRanking(mr2);
	
		Creator[] arCreator = {creator,creator2}; 
		mav.addObject("arCreator", arCreator);
		return mav;
	}
	
	//修改創作人月CP值
	@RequestMapping(value = "/updateCreatorMonthCP", method = RequestMethod.POST)
	public String updateCreatorMonthCP(String cpPoint,String cpId) {
		
		//update CP值
		System.out.println("CreatorMonthcpPoint===>"+cpPoint);
		return "queryCreatorMonthRankingForAdmin";
	}
}