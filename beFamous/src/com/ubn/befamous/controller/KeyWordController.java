package com.ubn.befamous.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.Creator;
import com.ubn.befamous.entity.Keyword;
import com.ubn.befamous.entity.MusicCategory;
import com.ubn.befamous.entity.News;
import com.ubn.befamous.entity.Song;
import com.ubn.befamous.entity.SongPrice;

@Controller
@SessionAttributes
public class KeyWordController {
	
	//查詢關鍵字
	@RequestMapping(value = "/queryKeyWord", method = RequestMethod.GET)
	public ModelAndView queryKeyWord(@RequestParam String keyWord) {
		
		ModelAndView mav = new ModelAndView("queryKeyWord");
		//String aaa = "kevin[lucy";
		//String[]  test =  aaa.split("\\[");
		//System.out.println("AAAA==>"+test[0]);
		
		
		Keyword keyword = new Keyword();
		keyword.setName("笨蛋");
		Keyword keyword2 = new Keyword();
		keyword2.setName("笨蛋2");
		Keyword[] arKeyword={keyword,keyword2};
		
		
		Creator creator = new Creator();
		creator.setAccountName("kevin");
		creator.setAccountNO("12345678");
		creator.setUserName("kevin");
		creator.setPicture("images/title_01.gif");
		
		Creator creator2 = new Creator();
		creator2.setAccountName("kevin");
		creator2.setAccountNO("12345678");
		creator2.setUserName("kevin");
		creator2.setPicture("images/title_01.gif");
		Creator[] arCreator={creator,creator2};
		
		MusicCategory musicCategory = new MusicCategory();
		musicCategory.setName("rock");
		
		
		Album album = new Album();
		album.setName("bird");
		album.setAlbumID(1234567);
		album.setCreator(creator);
		album.setBrand("UBN");
		album.setCover("image/image001.png");
		album.setType("MP3");
		album.setDate("2011/10/25");
		album.setMusicCategory(musicCategory);
		album.setIntroduction("This is very good album!");
		Album[] arAlbum = {album};
		
		Song song = new Song();
		SongPrice songPrice = new SongPrice();
		songPrice.setPrice("30");
		songPrice.setDiscountBonus("15");
		songPrice.setDiscountPrice("15");
		song.setName("AAA");
		song.setSongPrice(songPrice);
		song.setAlbum(album);
		Song song2 = new Song();
		song2.setName("AAA");
		song2.setSongPrice(songPrice);
		song2.setAlbum(album);
		Song[] arSong = {song,song2};
		
		Set<Album> set = new HashSet();
		set.add(album);
		creator.setAlbum(set);
		
		
		News news = new News();
	    news.setContent("蠟筆小新好看");
	    news.setNewsName("娛樂頭版");
	    News news2 = new News();
	    news2.setContent("周董又把妹了");
	    news2.setNewsName("娛樂頭版2");
	    News[] arNews = {news,news2};
	    mav.addObject("arNews", arNews);
		
		mav.addObject("arKeyword",arKeyword);
		mav.addObject("arAlbum",arAlbum);
		mav.addObject("arCreator",arCreator);
		mav.addObject("arSong",arSong);
		mav.addObject("keyWord",keyWord);
		
		return mav;
	}
	
	
	//查詢關鍵字(歌曲)
	@RequestMapping(value = "/queryKeyWordForSong", method = RequestMethod.GET)
	public ModelAndView queryKeyWordForSong(@RequestParam String keyWord) {
		
        //歌曲
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
		
		
		Song song = new Song();
		SongPrice songPrice = new SongPrice();
		songPrice.setPrice("30");
		songPrice.setDiscountBonus("15");
		songPrice.setDiscountPrice("15");
		song.setName("AAA");
		song.setSongPrice(songPrice);
        song.setAlbum(album);
		Song song2 = new Song();
		song2.setName("AAA");
		song2.setSongPrice(songPrice);
		song2.setAlbum(album);
		Song[] arSong = {song,song2};
		
		return new ModelAndView("queryKeyWordForSong","arSong",arSong);
		
		
	}
	
	//查詢關鍵字(專輯)
	@RequestMapping(value = "/queryKeyWordForAlbum")
	public ModelAndView queryKeyWordForAlbum(@RequestParam String keyWord) {
		ModelAndView mav = new ModelAndView("queryKeyWordForAlbum");
		Creator creator = new Creator();
		creator.setAccountName("kevin");
		creator.setAccountNO("12345678");
		creator.setUserName("kevin");
		MusicCategory musicCategory = new MusicCategory();
		musicCategory.setName("rock");
		
		//專輯
		Album album3 = new Album();
		album3.setName("bird");
		album3.setAlbumID(1234567);
		album3.setCreator(creator);
		album3.setBrand("UBN");
		album3.setCover("image/image001.png");
		album3.setType("MP3");
		album3.setDate("2011/10/25");
		album3.setMusicCategory(musicCategory);
		album3.setIntroduction("This is very good album!");
		Album[] arAlbum = {album3}; 
		
		mav.addObject("arAlbum", arAlbum);
		
		return mav;
		
		
	}
	
	//查詢關鍵字(創作人)
	@RequestMapping(value = "/queryKeyWordForCreator")
	public ModelAndView queryKeyWordForCreator(@RequestParam String keyWord) {
		ModelAndView mav = new ModelAndView("queryKeyWordForCreator");
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
		album.setDate("2011.10.25");
		album.setAlbumID(1234567);
		Album album2 = new Album();
		album.setCover("image/image001.png");	
		album.setName("ccc");
		album.setCreator(creator);
		album.setDate("2011.10.25");
		album.setAlbumID(1234567);
		Set<Album> set = new HashSet();
		set.add(album);
		set.add(album2);
		creator.setAlbum(set);
		creator2.setAlbum(set);
		
		//model.addAttribute(arg0, "2");
		Creator[] arCreator = {creator,creator2};
	
		
		mav.addObject("arCreator", arCreator);
		
		return mav;
		
		
	}
	
	
	//查詢關鍵字(消息)
	@RequestMapping(value = "/queryKeyWordForNews")
	public ModelAndView queryKeyWordForNews(@RequestParam String keyWord) {
		ModelAndView mav = new ModelAndView("queryKeyWordForNews");
	     
		News news = new News();
	    news.setContent("蠟筆小新好看");
	    news.setNewsName("娛樂頭版");
	    news.setCreateDate("2011/10/27");
	    News news2 = new News();
	    news2.setContent("周董又把妹了");
	    news2.setNewsName("娛樂頭版2");
	    news2.setCreateDate("2011/10/27");
	    News[] arNews = {news,news2};
	    mav.addObject("arNews", arNews);
	   
	    return mav;
	}
	
	
	//怡秀 write-1107
	//管理關鍵字
	@RequestMapping(value = "/manageKeyWord")
	public ModelAndView managekeyword(@RequestParam String keyWord) {
		ModelAndView mav = new ModelAndView("manageKeyWord");
		    
		   
		return mav;
	}
}
