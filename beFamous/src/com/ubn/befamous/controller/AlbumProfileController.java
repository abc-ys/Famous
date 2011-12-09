package com.ubn.befamous.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.Creator;
import com.ubn.befamous.entity.MusicCategory;
import com.ubn.befamous.entity.Song;
import com.ubn.befamous.entity.SongPrice;
import com.ubn.befamous.service.MusicService;


@Controller
@SessionAttributes
public class AlbumProfileController {
	
	@Autowired
	 MusicService musicService;
	
	
	//瀏覽專輯profile
	@RequestMapping(value = "/queryAlbumData")
	public ModelAndView queryAlbumData(long albumid,long userId) {
		System.out.println("albumid="+albumid);
		System.out.println("userId="+userId);
		
		ModelAndView mav = new ModelAndView("queryAlbumData");
		
		ArrayList list = musicService.queryCreatorAlbums(albumid);
		
		mav.addObject("price", "30");
		mav.addObject("discountPrice", "20");
		mav.addObject("discountBonus", "10");
		mav.addObject("album", list);
		mav.addObject("userId", userId);
		return mav;

	} 
	
	//查詢歌曲清單
	@RequestMapping(value = "/querySongList")
	public ModelAndView querySongList(long albumID) {
		
		Song[] arSong = musicService.querySongSet(albumID);
		
		return new ModelAndView("querySongList","arSong",arSong);
		
	}
	
	//其他專輯
	@RequestMapping(value = "/queryOtherAlbum")
	public ModelAndView queryOtherAlbum(long albumID,long creatorID) {
		ModelAndView mav = new ModelAndView("queryOtherAlbum");
		
		Album[] arOtherAlbum = musicService.queryOtherAlbum(albumID, creatorID);
		
		mav.addObject("arOtherAlbum", arOtherAlbum);
		
		return mav;
	}
	
	
	//專輯大力推
	@RequestMapping(value = "/queryPromotionAlbum")
	public ModelAndView queryPromotionAlbum() {
		
		Album[] arAlbum = musicService.queryPromotionAlbums();
		for(Album a:arAlbum){
			System.out.println(a.getCreator().getUserName());
		}
		
		return new ModelAndView("queryPromotionAlbum","arAlbum",arAlbum);
	}
	
	//最新專輯
	@RequestMapping(value = "/queryNewAlbum")
	public ModelAndView queryNewAlbum(Model model) {
		String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");   //現在日期時間
		Album[] arAlbum = musicService.queryNewAlbums(datetime);
		
		for(Album a:arAlbum){
			System.out.println(a.getCover());
		}
		
		model.addAttribute("arAlbum", arAlbum);
		return new ModelAndView("queryNewAlbum");
	}
	

	//分類名稱-最新發佈
	@RequestMapping(value = "/queryNewAlbumsForMusicCategory/{categoryID}")
	public ModelAndView queryNewAlbumsForMusicCategory(@PathVariable("categoryID") String categoryID,Model model) {
		
		long musicCatrgoryID = 1;
		//Album[] arAlbum = musicService.queryAlbumsWeekRanking();
		Album[] arAlbum = musicService.queryNewAlbumsForMusicCatrgory(Long.parseLong(categoryID));
		
		for(Album a:arAlbum){
			System.out.println(a.getCover());
		}
		model.addAttribute("categoryID", categoryID);
		model.addAttribute("arAlbum", arAlbum);
		return new ModelAndView("queryNewAlbumsForMusicCategory");
	}
	
	
	//分類名稱-最受歡迎
	@RequestMapping(value = "/queryHotAlbumsForMusicCategory/{categoryID}")
	public ModelAndView queryHotAlbumsForMusicCategory(@PathVariable("categoryID") String categoryID,Model model) {
		
		Creator creator = new Creator();
		creator.setAccountName("kevin");
		creator.setAccountNO("12345678");
		creator.setUserName("kevin");
		MusicCategory musicCategory = new MusicCategory();
		musicCategory.setName("rock");
		
		Album album = new Album();
		album.setName("日不落");
		//album.setId(1234567);
		album.setCreator(creator);
		album.setBrand("UBN");
		album.setCover("image/image001.png");
		album.setType("MP3");
		album.setCreateDate("2011/10/25");
		album.setMusicCategory(musicCategory);
		album.setIntroduction("This is very good album!");
		Album album2 = new Album();
		album2.setName("bird2");
		//album2.setId(1234567);
		album2.setCreator(creator);
		album2.setBrand("UBN2");
		album2.setCover("image/image002.png");
		album2.setType("MP3");
		album2.setCreateDate("2011/10/25");
		album2.setMusicCategory(musicCategory);
		album2.setIntroduction("This is very good album!");
		Album[] arAlbum = {album,album2};
		model.addAttribute("categoryID", categoryID);
		model.addAttribute("arAlbum", arAlbum);
		return new ModelAndView("queryHotAlbumsForMusicCategory");
	}
	
	
	
	//排行榜-專輯排行榜(週)
	@RequestMapping(value = "/queryAlbumsWeekRanking")
	public ModelAndView queryAlbumsWeekRanking() {
		
		Album[] arAlbum = musicService.queryAlbumsWeekRanking();
		return new ModelAndView("queryAlbumsWeekRanking","arAlbum",arAlbum);
	}
	
	
	//查詢歌曲排行榜(週)
	@RequestMapping(value = "/querySongsWeekRanking")
	public ModelAndView querySongsWeekRanking(Model model) {
		
		Song[] arSong = musicService.querySongsWeekRanking();
		model.addAttribute("arSong", arSong);
		return new ModelAndView("querySongsWeekRanking");
		
	}
	
	
	//我最大聲排行榜(週)
	@RequestMapping(value = "/queryCreatorWeekRanking")
	public ModelAndView queryCreatorWeekRanking(Model model) {
		
		
		ArrayList arCreator = musicService.queryCreatorWeekRanking();
		model.addAttribute("arCreator", arCreator);
		return new ModelAndView("queryCreatorWeekRanking");
		
	}
	
	
	
	//排行榜-專輯排行榜(月)
	@RequestMapping(value = "/queryAlbumsMonthRanking")
	public ModelAndView queryAlbumsMonthRanking() {
		
		Album[] arAlbum = musicService.queryAlbumsMonthRanking();
		return new ModelAndView("queryAlbumsMonthRanking","arAlbum",arAlbum);
	}
	
	
	//查詢歌曲排行榜(月)
	@RequestMapping(value = "/querySongsMonthRanking")
	public ModelAndView querySongsMonthRanking(Model model) {
		
		Song[] arSong = musicService.querySongsMonthRanking();
		model.addAttribute("arSong", arSong);
		return new ModelAndView("querySongsMonthRanking");
		
	}
	
	
	//我最大聲排行榜(月)
	@RequestMapping(value = "/queryCreatorMonthRanking")
	public ModelAndView queryCreatorMonthRanking(Model model) {
		
		ArrayList arCreator = musicService.queryCreatorMonthRanking();
		model.addAttribute("arCreator", arCreator);
		return new ModelAndView("queryCreatorMonthRanking");
		
	}
	
}
