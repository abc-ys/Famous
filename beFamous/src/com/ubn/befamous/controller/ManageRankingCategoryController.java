package com.ubn.befamous.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.Creator;
import com.ubn.befamous.entity.MonthRanking;
import com.ubn.befamous.entity.MusicCategory;
import com.ubn.befamous.entity.Song;
import com.ubn.befamous.entity.SongPrice;
import com.ubn.befamous.entity.WeekRanking;


@Controller
public class ManageRankingCategoryController {
	
	//音樂類別管理
	@RequestMapping("/manageMusicCategory")
	public ModelAndView managemusiccategory() {
		ModelAndView mav = new ModelAndView("manageMusicCategory");
		
		MusicCategory musicCategory = new MusicCategory();
		musicCategory.setName("兒歌 Children's Music");
		MusicCategory musicCategory2 = new MusicCategory();
		musicCategory2.setName("民歌 Folk");
		MusicCategory[] mc = {musicCategory,musicCategory2};
		
		mav.addObject("musicCategory",mc);
		return mav;
	}
	
	//音樂類別管理-新增音樂類別或子類別
	@RequestMapping("/addMusicCategory/{type}")
	public String addmusiccategory(@PathVariable("type") String type, String categoryName) {

		System.out.println("categoryName==>"+categoryName);
		
		ModelAndView mav = new ModelAndView();		
		if(type.equals("father")){
			return "redirect:manageMusicCategory.do";
		}else{
			return "redirect:subMusicCategory.do";
		}
	}
	
	//音樂類別管理-編輯音樂類別或子類別
	@RequestMapping("/editMusicCategory")
	public ModelAndView editmusiccategory() {
		ModelAndView mav = new ModelAndView("editMusicCategory");
		return mav;
	}
	
	//音樂類別管理-關閉編輯音樂類別或子類別的視窗
		@RequestMapping("/editMusicCategoryClose")
		public ModelAndView editmusiccategoryclose(String modifyName) {
			ModelAndView mav = new ModelAndView("editMusicCategoryClose");

			System.out.println("modifyName==>"+modifyName);
			
			return mav;
		}
	
	//音樂類別管理-刪除音樂類別或子類別
	@RequestMapping("/deleteMusicCategory")
	public String deletemusiccategory() {
		
		System.out.println("刪除類別");
		
		return "redirect:manageMusicCategory.do";
	}
	
	//音樂子類別管理
	@RequestMapping("/subMusicCategory")
	public ModelAndView submusiccategory() {
		ModelAndView mav = new ModelAndView("subMusicCategory");
		MusicCategory musicCategory = new MusicCategory();
		musicCategory.setName("兒歌 Children's Music");
		MusicCategory musicCategory2 = new MusicCategory();
		musicCategory2.setName("民歌 Folk");
		MusicCategory[] mc = {musicCategory,musicCategory2};
		mav.addObject("musicCategory",mc);
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
		creator.setMemberId(123456);
		
		Album album = new Album();
		album.setName("bird");
		album.setAlbumID(1234567);
		album.setCreator(creator);
		album.setBrand("UBN");
		album.setCover("image/image001.png");
		album.setType("MP3");
		album.setDate("2011/10/25");
		album.setMusicCategory(musicCategory1);
		album.setIntroduction("This is very good album!");
		
		WeekRanking weekRanking = new WeekRanking();
		weekRanking.setCpID(123456);
		weekRanking.setModifyValue("20");
		WeekRanking weekRanking2 = new WeekRanking();
		weekRanking2.setCpID(1234567);
		weekRanking2.setModifyValue("30");
		
		Album album2 = new Album();
		album2.setName("兩隻老虎");
		album2.setAlbumID(1234567);
		album2.setCreator(creator);
		album2.setBrand("UBN");
		album2.setCover("image/image001.png");
		album2.setType("MP3");
		album2.setDate("2011/10/25");
		album2.setMusicCategory(musicCategory1);
		album2.setIntroduction("This is very good album!");
		
		
		album.setCp(weekRanking);
		album2.setCp(weekRanking2);
		
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
		creator.setMemberId(123456);
		
		Album album = new Album();
		album.setName("bird");
		album.setAlbumID(1234567);
		album.setCreator(creator);
		album.setBrand("UBN");
		album.setCover("image/image001.png");
		album.setType("MP3");
		album.setDate("2011/10/25");
		album.setMusicCategory(musicCategory1);
		album.setIntroduction("This is very good album!");
		
		MonthRanking monthRanking = new MonthRanking();
		monthRanking.setCpID(123456);
		monthRanking.setModifyValue("20");
		MonthRanking monthRanking2 = new MonthRanking();
		monthRanking2.setCpID(1234567);
		monthRanking2.setModifyValue("30");
		
		Album album2 = new Album();
		album2.setName("兩隻老虎");
		album2.setAlbumID(1234567);
		album2.setCreator(creator);
		album2.setBrand("UBN");
		album2.setCover("image/image001.png");
		album2.setType("MP3");
		album2.setDate("2011/10/25");
		album2.setMusicCategory(musicCategory1);
		album2.setIntroduction("This is very good album!");
		
		album.setCp(monthRanking);
		album2.setCp(monthRanking2);
		
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
		album.setAlbumID(1234567);
		album.setCreator(creator);
		album.setBrand("UBN");
		album.setCover("image/image001.png");
		album.setType("MP3");
		album.setDate("2011/10/25");
		album.setIntroduction("This is very good album!");
		
		WeekRanking weekRanking = new WeekRanking();
		weekRanking.setCpID(123456);
		weekRanking.setModifyValue("20");
		WeekRanking weekRanking2 = new WeekRanking();
		weekRanking2.setCpID(1234567);
		weekRanking2.setModifyValue("30");
		
		MusicCategory musicCategory1 = new MusicCategory();
		musicCategory1.setName("rock");
		Song song = new Song();
		SongPrice songPrice = new SongPrice();
		songPrice.setPrice("30");
		songPrice.setDiscountBonus("15");
		songPrice.setDiscountPrice("15");
		song.setSongID(1234);
		song.setName("我愛一條柴");
		song.setSongPrice(songPrice);
	    song.setAlbum(album);
	    song.setDate("2011/11/01");
	    song.setMusicCategory(musicCategory1);
		Song song2 = new Song();
		song2.setSongID(5678);
		song2.setName("忘情水");
		song2.setSongPrice(songPrice);
		song2.setAlbum(album);
		song2.setDate("2011/11/01");
		song2.setMusicCategory(musicCategory1);
		
		song.setCp(weekRanking);
		song2.setCp(weekRanking2);
		
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
		album.setAlbumID(1234567);
		album.setCreator(creator);
		album.setBrand("UBN");
		album.setCover("image/image001.png");
		album.setType("MP3");
		album.setDate("2011/10/25");
		album.setIntroduction("This is very good album!");
		
		
		MonthRanking monthRanking = new MonthRanking();
		monthRanking.setCpID(123456);
		monthRanking.setModifyValue("20");
		MonthRanking monthRanking2 = new MonthRanking();
		monthRanking2.setCpID(1234567);
		monthRanking2.setModifyValue("30");
		
		MusicCategory musicCategory1 = new MusicCategory();
		musicCategory1.setName("rock");
		Song song = new Song();
		SongPrice songPrice = new SongPrice();
		songPrice.setPrice("30");
		songPrice.setDiscountBonus("15");
		songPrice.setDiscountPrice("15");
		song.setSongID(1234);
		song.setName("我愛一條柴");
		song.setSongPrice(songPrice);
	    song.setAlbum(album);
	    song.setDate("2011/11/01");
	    song.setMusicCategory(musicCategory1);
		Song song2 = new Song();
		song2.setSongID(5678);
		song2.setName("忘情水");
		song2.setSongPrice(songPrice);
		song2.setAlbum(album);
		song2.setDate("2011/11/01");
		song2.setMusicCategory(musicCategory1);
		
		song.setCp(monthRanking);
		song2.setCp(monthRanking2);
		
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
		creator.setMemberId(123456);
		Creator creator2 = new Creator();
		creator2.setAccountName("kevin");
		creator2.setAccountNO("12345678");
		creator2.setUserName("劉小駿");
		creator2.setMemberId(555555);
		
		WeekRanking weekRanking = new WeekRanking();
		weekRanking.setCpID(123456);
		weekRanking.setModifyValue("20");
		WeekRanking weekRanking2 = new WeekRanking();
		weekRanking2.setCpID(1234567);
		weekRanking2.setModifyValue("30");
		
		creator.setCp(weekRanking);
		creator2.setCp(weekRanking2);
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
		creator.setMemberId(123456);
		Creator creator2 = new Creator();
		creator2.setAccountName("kevin");
		creator2.setAccountNO("12345678");
		creator2.setUserName("劉小駿");
		creator2.setMemberId(555555);
		
		MonthRanking monthRanking = new MonthRanking();
		monthRanking.setCpID(123456);
		monthRanking.setModifyValue("20");
		MonthRanking monthRanking2 = new MonthRanking();
		monthRanking2.setCpID(1234567);
		monthRanking2.setModifyValue("30");
		
		creator.setCp(monthRanking);
		creator2.setCp(monthRanking2);
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