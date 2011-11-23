package com.ubn.befamous.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	@RequestMapping(value = "/queryAlbumData", method = RequestMethod.GET)
	public ModelAndView queryAlbumData(long albumid) {
		System.out.println("albumid"+albumid);
		
		ModelAndView mav = new ModelAndView("queryAlbumData");
		
		ArrayList list = musicService.queryCreatorAlbums(albumid);
		
		mav.addObject("price", "30");
		mav.addObject("discountPrice", "20");
		mav.addObject("discountBonus", "10");
		mav.addObject("album", list);
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
		String datetime = DateFormatUtils.format(new Date(), "yyyy-MM-dd");   //現在日期時間
		Album[] arAlbum = musicService.queryNewAlbums(datetime);
		
		for(Album a:arAlbum){
			System.out.println(a.getCover());
		}
		
		model.addAttribute("arAlbum", arAlbum);
		return new ModelAndView("queryNewAlbum");
	}
	

	//分類名稱-最新發佈
	@RequestMapping(value = "/queryNewAlbumsForMusicCategory")
	public ModelAndView queryNewAlbumsForMusicCategory() {
		
		long musicCatrgoryID = 1;
		Album[] arAlbum = musicService.queryNewAlbumsForMusicCatrgory(musicCatrgoryID);
		
		for(Album a:arAlbum){
			System.out.println(a.getCover());
		}
		
		return new ModelAndView("queryNewAlbumsForMusicCategory","arAlbum",arAlbum);
	}
	
	
	//分類名稱-最受歡迎
	@RequestMapping(value = "/queryHotAlbumsForMusicCategory")
	public ModelAndView queryHotAlbumsForMusicCategory() {
		
		Creator creator = new Creator();
		creator.setAccountName("kevin");
		creator.setAccountNO("12345678");
		creator.setUserName("kevin");
		MusicCategory musicCategory = new MusicCategory();
		musicCategory.setName("rock");
		
		Album album = new Album();
		album.setName("日不落");
		album.setId(1234567);
		album.setCreator(creator);
		album.setBrand("UBN");
		album.setCover("image/image001.png");
		album.setType("MP3");
		album.setCreateDate("2011/10/25");
		album.setMusicCategory(musicCategory);
		album.setIntroduction("This is very good album!");
		Album album2 = new Album();
		album2.setName("bird2");
		album2.setId(1234567);
		album2.setCreator(creator);
		album2.setBrand("UBN2");
		album2.setCover("image/image002.png");
		album2.setType("MP3");
		album2.setCreateDate("2011/10/25");
		album2.setMusicCategory(musicCategory);
		album2.setIntroduction("This is very good album!");
		Album[] arAlbum = {album,album2};
		return new ModelAndView("queryHotAlbumsForMusicCategory","arAlbum",arAlbum);
	}
	
	
	
	//排行榜-專輯排行榜(週)
	@RequestMapping(value = "/queryAlbumsWeekRanking")
	public ModelAndView queryAlbumsWeekRanking() {
		
		Creator creator = new Creator();
		creator.setAccountName("kevin");
		creator.setAccountNO("12345678");
		creator.setUserName("kevin");
		MusicCategory musicCategory = new MusicCategory();
		musicCategory.setName("rock");
		
		Album album = new Album();
		album.setName("日不落");
		album.setId(1234567);
		album.setCreator(creator);
		album.setBrand("UBN");
		album.setCover("image/image001.png");
		album.setType("MP3");
		album.setCreateDate("2011/10/25");
		album.setMusicCategory(musicCategory);
		album.setIntroduction("This is very good album!");
		Album album2 = new Album();
		album2.setName("bird2");
		album2.setId(1234567);
		album2.setCreator(creator);
		album2.setBrand("UBN2");
		album2.setCover("image/image002.png");
		album2.setType("MP3");
		album2.setCreateDate("2011/10/25");
		album2.setMusicCategory(musicCategory);
		album2.setIntroduction("This is very good album!");
		Album[] arAlbum = {album,album2};
		return new ModelAndView("queryAlbumsWeekRanking","arAlbum",arAlbum);
	}
	
	
	//查詢歌曲排行榜(週)
	@RequestMapping(value = "/querySongsWeekRanking")
	public ModelAndView querySongsWeekRanking(Model model) {
		//歌曲
		Song song = new Song();
		SongPrice songPrice = new SongPrice();
		songPrice.setPrice("30");
		songPrice.setDiscountBonus("15");
		songPrice.setDiscountPrice("15");
		Creator creator = new Creator();
		creator.setAccountName("kevin");
		creator.setAccountNO("12345678");
		creator.setUserName("kevin");
		Album album = new Album();
		album.setCover("image/image001.png");	
		album.setName("ccc");
		album.setCreator(creator);
		album.setCreateDate("2011.10.25");
		album.setId(1234567);
		
		song.setName("AAA");
		song.setSongPrice(songPrice);
		song.setAlbum(album);
		
		
		Song song2 = new Song();
		song2.setName("AAA");
		song2.setSongPrice(songPrice);
		song2.setAlbum(album);
		Song[] arSong = {song,song2};
		model.addAttribute("arSong", arSong);
		return new ModelAndView("querySongsWeekRanking");
		
	}
	
	
	//我最大聲排行榜(週)
	@RequestMapping(value = "/queryCreatorWeekRanking")
	public ModelAndView queryCreatorWeekRanking(Model model) {
		
		Creator creator = new Creator();
		creator.setAccountName("kevin");
		creator.setAccountNO("12345678");
		creator.setUserName("kevin");
		creator.setPicture("images/title_01.gif");
		
		Creator creator2 = new Creator();
		creator2.setAccountName("kevin");
		creator2.setAccountNO("12345678");
		creator2.setUserName("rose");
		creator2.setPicture("images/title_01.gif");
		
		Album album = new Album();
		album.setCover("image/image001.png");	
		album.setName("ccc");
		album.setCreator(creator);
		album.setCreateDate("2011.10.25");
		album.setId(1234567);
		Album album2 = new Album();
		album.setCover("image/image001.png");	
		album.setName("ccc");
		album.setCreator(creator);
		album.setCreateDate("2011.10.25");
		album.setId(1234567);
		Set<Album> set = new HashSet();
		set.add(album);
		set.add(album2);
		creator.setAlbum(set);
		creator2.setAlbum(set);
		
		//model.addAttribute(arg0, "2");
		Creator[] arCreator = {creator,creator2};
		model.addAttribute("arCreator", arCreator);
		return new ModelAndView("queryCreatorWeekRanking");
		
	}
	
	
	
	//排行榜-專輯排行榜(月)
	@RequestMapping(value = "/queryAlbumsMonthRanking")
	public ModelAndView queryAlbumsMonthRanking() {
		
		Creator creator = new Creator();
		creator.setAccountName("kevin");
		creator.setAccountNO("12345678");
		creator.setUserName("kevin");
		MusicCategory musicCategory = new MusicCategory();
		musicCategory.setName("rock");
		
		Album album = new Album();
		album.setName("日不落國");
		album.setId(1234567);
		album.setCreator(creator);
		album.setBrand("UBN");
		album.setCover("image/image001.png");
		album.setType("MP3");
		album.setCreateDate("2011/10/25");
		album.setMusicCategory(musicCategory);
		album.setIntroduction("This is very good album!");
		Album album2 = new Album();
		album2.setName("東風破");
		album2.setId(1234567);
		album2.setCreator(creator);
		album2.setBrand("UBN2");
		album2.setCover("image/image002.png");
		album2.setType("MP3");
		album2.setCreateDate("2011/10/25");
		album2.setMusicCategory(musicCategory);
		album2.setIntroduction("This is very good album!");
		Album[] arAlbum = {album,album2};
		return new ModelAndView("queryAlbumsMonthRanking","arAlbum",arAlbum);
	}
	
	
	//查詢歌曲排行榜(月)
	@RequestMapping(value = "/querySongsMonthRanking")
	public ModelAndView querySongsMonthRanking(Model model) {
		//歌曲
		Song song = new Song();
		SongPrice songPrice = new SongPrice();
		songPrice.setPrice("30");
		songPrice.setDiscountBonus("15");
		songPrice.setDiscountPrice("15");
		Creator creator = new Creator();
		creator.setAccountName("kevin");
		creator.setAccountNO("12345678");
		creator.setUserName("kevin");
		Album album = new Album();
		album.setCover("image/image001.png");	
		album.setName("ccc");
		album.setCreator(creator);
		album.setCreateDate("2011.10.25");
		album.setId(1234567);
		
		song.setName("淚海");
		song.setSongPrice(songPrice);
		song.setAlbum(album);
		
		
		Song song2 = new Song();
		song2.setName("膽小鬼");
		song2.setSongPrice(songPrice);
		song2.setAlbum(album);
		Song[] arSong = {song,song2};
		model.addAttribute("arSong", arSong);
		return new ModelAndView("querySongsMonthRanking");
		
	}
	
	
	//我最大聲排行榜(月)
	@RequestMapping(value = "/queryCreatorMonthRanking")
	public ModelAndView queryCreatorMonthRanking(Model model) {
		
		Creator creator = new Creator();
		creator.setAccountName("kevin");
		creator.setAccountNO("12345678");
		creator.setUserName("kevin1");
		creator.setPicture("images/title_01.gif");
		
		Creator creator2 = new Creator();
		creator2.setAccountName("kevin");
		creator2.setAccountNO("12345678");
		creator2.setUserName("rose1");
		creator2.setPicture("images/title_01.gif");
		
		Album album = new Album();
		album.setCover("image/image001.png");	
		album.setName("ccc");
		album.setCreator(creator);
		album.setCreateDate("2011.10.25");
		album.setId(1234567);
		Album album2 = new Album();
		album.setCover("image/image001.png");	
		album.setName("ccc");
		album.setCreator(creator);
		album.setCreateDate("2011.10.25");
		album.setId(1234567);
		Set<Album> set = new HashSet();
		set.add(album);
		set.add(album2);
		creator.setAlbum(set);
		creator2.setAlbum(set);
		
		//model.addAttribute(arg0, "2");
		Creator[] arCreator = {creator,creator2};
		model.addAttribute("arCreator", arCreator);
		return new ModelAndView("queryCreatorMonthRanking");
		
	}
	
	
	
	
	
	
	
	
}
