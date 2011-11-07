package com.ubn.befamous.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.Creator;
import com.ubn.befamous.entity.Movies;
import com.ubn.befamous.entity.MusicCategory;
import com.ubn.befamous.entity.Song;
import com.ubn.befamous.entity.SongPrice;


@Controller
@SessionAttributes
public class AlbumProfileController {
	
	//瀏覽專輯profile
	@RequestMapping(value = "/queryAlbumData")
	public ModelAndView queryAlbumData(@RequestParam String albumid) {
		System.out.println("albumid"+albumid);
		ArrayList list = new ArrayList();
		
		Creator creator = new Creator();
		MusicCategory musicCategory = new MusicCategory();
		
		//歌曲
		Song song = new Song();
		SongPrice songPrice = new SongPrice();
		songPrice.setPrice(30);
		songPrice.setDiscountBonus("15");
		songPrice.setDiscountPrice("15");
		song.setName("AAA");
		song.setSongPrice(songPrice);
		
		
		musicCategory.setName("rock");
		creator.setAccountName("kevin");
		creator.setAccountNO("12345678");
		
		Album album = new Album();
		album.setName("bird");
		album.setAlbumID(1234567);
		album.setCreator(creator);
		album.setBrand("UBN");
		album.setCover("images/ad01.jpg");
		album.setType("MP3");
		album.setDate("2011/10/25");
		album.setMusicCategory(musicCategory);
		album.setIntroduction("This is very good album!");
		Album album2 = new Album();
		
		Album[] aaa = {album,album2};
		
		list.add(album);
		list.add(song);
		return new ModelAndView("queryAlbumData","profile",list);

	} 
	
	//查詢歌曲清單
	@RequestMapping(value = "/querySongList")
	public ModelAndView querySongList() {
		//歌曲
		Song song = new Song();
		SongPrice songPrice = new SongPrice();
		songPrice.setPrice(30);
		songPrice.setDiscountBonus("15");
		songPrice.setDiscountPrice("15");
		song.setName("AAA");
		song.setSongPrice(songPrice);
		return new ModelAndView("querySongList","song",song);
		
	}
	
	//其他專輯
	@RequestMapping(value = "/queryOtherAlbum")
	public ModelAndView queryOtherAlbum() {
		return new ModelAndView("queryOtherAlbum");
	}
	
	
	//專輯大力推
	@RequestMapping(value = "/queryPromotionAlbum")
	public ModelAndView queryPromotionAlbum() {
		
		Creator creator = new Creator();
		creator.setAccountName("kevin");
		creator.setAccountNO("12345678");
		creator.setUserName("kevin");
		MusicCategory musicCategory = new MusicCategory();
		musicCategory.setName("rock");
		
		Album album = new Album();
		album.setName("bird");
		album.setAlbumID(1234567);
		album.setCreator(creator);
		album.setBrand("UBN");
		album.setCover("images/album.png");
		album.setType("MP3");
		album.setDate("2011/10/25");
		album.setMusicCategory(musicCategory);
		album.setIntroduction("This is very good album!");
		Album album2 = new Album();
		album2.setName("bird2");
		album2.setAlbumID(1234567);
		album2.setCreator(creator);
		album2.setBrand("UBN2");
		album2.setCover("images/album.png");
		album2.setType("MP3");
		album2.setDate("2011/10/25");
		album2.setMusicCategory(musicCategory);
		album2.setIntroduction("This is very good album!");
		Album[] arAlbum = {album,album2};
		return new ModelAndView("queryPromotionAlbum","arAlbum",arAlbum);
	}
	
	//最新專輯
	@RequestMapping(value = "/queryNewAlbum")
	public ModelAndView queryNewAlbum() {
		
		Creator creator = new Creator();
		creator.setAccountName("kevin");
		creator.setAccountNO("12345678");
		creator.setUserName("kevin");
		MusicCategory musicCategory = new MusicCategory();
		musicCategory.setName("rock");
		
		Album album = new Album();
		album.setName("bird");
		album.setAlbumID(1234567);
		album.setCreator(creator);
		album.setBrand("UBN");
		album.setCover("images/album.png");
		album.setType("MP3");
		album.setDate("2011/10/25");
		album.setMusicCategory(musicCategory);
		album.setIntroduction("This is very good album!");
		Album album2 = new Album();
		album2.setName("bird2");
		album2.setAlbumID(1234567);
		album2.setCreator(creator);
		album2.setBrand("UBN2");
		album2.setCover("images/album.png");
		album2.setType("MP3");
		album2.setDate("2011/10/25");
		album2.setMusicCategory(musicCategory);
		album2.setIntroduction("This is very good album!");
		Album[] arAlbum = {album,album2};
		return new ModelAndView("queryNewAlbum","arAlbum",arAlbum);
	}
	

}
