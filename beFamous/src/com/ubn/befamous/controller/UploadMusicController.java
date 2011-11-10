package com.ubn.befamous.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.Hidden;
import com.ubn.befamous.entity.MusicCategory;
import com.ubn.befamous.entity.OrderDetail;
import com.ubn.befamous.entity.Song;
import com.ubn.befamous.entity.SongPrice;


@Controller
public class UploadMusicController {

	//音樂管理-編輯專輯
	@RequestMapping("/editAlbum")
	public ModelAndView editalbum(){
		ModelAndView mav = new ModelAndView("editAlbum");
		
		Hidden hidden = new Hidden();
		hidden.setHiddenReason("被檢舉次數過多");
		Album album = new Album();
		album.setName("小星星");
		album.setDate("2011/06/10 12:00:30");
		album.setCover("images/album.jpg");
		album.setHidden(hidden);
		long p = 22222;
		album.setAlbumID(p);
		
		Hidden hidden2 = new Hidden();
		hidden2.setHiddenReason("");
		Album album2 = new Album();
		album2.setName("原來如此");
		album2.setDate("2011/09/25 14:45:30");
		album2.setCover("images/album.jpg");
		album2.setHidden(hidden2);
		long p2 = 33333;
		album2.setAlbumID(p2);
		
		Album[] a = {album,album2};
		mav.addObject("album", a);
		return mav;
	}
	
	//音樂管理-編輯專輯
	@RequestMapping("/editAlbumContent")
	public ModelAndView editalbumcontent(String albumID){
		
		System.out.println("albumID==>"+albumID);
		
		ModelAndView mav = new ModelAndView("editAlbumContent");
		
		Song song = new Song();
		long s = 444;
		song.setSongID(s);
		song.setName("你");
		Song song2 = new Song();
		long s2 = 888;
		song2.setSongID(s2);
		song2.setName("敷衍");
		Set<Song> songSet = new HashSet();
		songSet.add(song);
		songSet.add(song2);
		MusicCategory musicCategory = new MusicCategory();
		musicCategory.setName("兒歌");
		Album album = new Album();
		long a = 1666;
		album.setAlbumID(a);
		album.setName("小星星");
		album.setMusicCategory(musicCategory);
		album.setDate("2011/06/10 12:00:30");
		album.setCover("images/album.jpg");
		album.setStatus("隱藏");
		album.setSongSet(songSet);
		
		mav.addObject("album", album);
		return mav;
	}
	
	//音樂管理-編輯專輯
	@RequestMapping("/changeState")
	public String changestate(String state)throws UnsupportedEncodingException{
			
		String selStr=java.net.URLDecoder.decode(state,"UTF-8"); //這行是將中文字做解碼
		
		System.out.println("state==>"+selStr);
			
		return "redirect:editAlbumContent.do";
	}
	
	//音樂管理-編輯專輯-儲存
	@RequestMapping("/saveAlbumData")
	public String savealbumdata(String albumID){
				
		System.out.println("albumID==>"+albumID);
				
		return "redirect:editAlbumContent.do";
	}
	
	//音樂管理-編輯專輯-刪除歌曲
	@RequestMapping("/deleteSongData")
	public String deletesongdata(){
					
		System.out.println("刪除歌曲");
					
		return "redirect:editAlbumContent.do";
	}
	
	//音樂管理-編輯專輯-編輯歌曲資訊
	@RequestMapping("/editSongData")
	public ModelAndView editsongdata(String songID, String songName, String musicType, String MOPEND, String status, String price, String price2, String discount, String tag, String lyrics, String lyricist, String composer, String producer){
		
		System.out.println("songID==>"+songID);
		System.out.println("songName==>"+songName);
		System.out.println("musicType==>"+musicType);
		System.out.println("MOPEND==>"+MOPEND);
		System.out.println("status==>"+status);
		System.out.println("price==>"+price);
		System.out.println("price2==>"+price2);
		System.out.println("discount==>"+discount);
		System.out.println("tag==>"+tag);
		System.out.println("lyrics==>"+lyrics);
		System.out.println("lyricist==>"+lyricist);
		System.out.println("composer==>"+composer);
		System.out.println("producer==>"+producer);
		
		ModelAndView mav = new ModelAndView("editSongData");
						
		MusicCategory mc = new MusicCategory();
		mc.setName("古典樂");
		MusicCategory mc2 = new MusicCategory();
		mc2.setName("搖滾樂");
		MusicCategory mc3 = new MusicCategory();
		mc3.setName("兒歌");
		
		MusicCategory[] mType = {mc,mc2,mc3};
		
		SongPrice songPrice = new SongPrice();
		songPrice.setPrice("");
		songPrice.setDiscountPrice("不提供紅包打賞");
		MusicCategory musicCategory = new MusicCategory();
		musicCategory.setName("古典樂");
		Song song = new Song();
		song.setName("小星星");
		song.setMusicCategory(musicCategory);
		song.setDate("2011-06-30");
		song.setSeconds("90秒");
		song.setSongPrice(songPrice);
		song.setTag("小星星,兒歌");
		song.setLyrics("一閃一閃亮晶晶 滿天都是小星星");
		song.setComposer("小明");
		song.setLyricist("中明");
		song.setProducer("大明");
		
		mav.addObject("mType",mType);
		mav.addObject("song",song);
		mav.addObject("songID", songID);
		return mav;
	}
	
	//音樂管理-編輯專輯-編輯專輯資訊
	@RequestMapping("/editAlbumData")
	public ModelAndView editalbumdata(String albumID){
			
		System.out.println("albumID==>"+albumID);
			
		ModelAndView mav = new ModelAndView("editAlbumData");
							
		MusicCategory mc = new MusicCategory();
		mc.setName("古典樂");
		MusicCategory mc2 = new MusicCategory();
		mc2.setName("搖滾樂");
		MusicCategory mc3 = new MusicCategory();
		mc3.setName("兒歌");
		
		MusicCategory[] mType = {mc,mc2,mc3};
		
		Album ac = new Album();
		ac.setCover("images/album.jpg");
		Album ac2 = new Album();
		ac2.setCover("images/title_01.gif");
		Album ac3 = new Album();
		ac3.setCover("images/play.png");
		Album[] a = {ac,ac2,ac3};
		
		Album album = new Album();
		album.setType("EP");
		album.setName("小星星");
		album.setDate("2011-09-01");
		album.setBrand("統一");
		MusicCategory musicCategory = new MusicCategory();
		musicCategory.setName("兒歌");
		album.setMusicCategory(musicCategory);
		album.setTag("小星星,兒歌");
		album.setIntroduction("小時候唱的歌");
		album.setStatus("公開");
		album.setCover("images/repair.png");
			
		mav.addObject("albumID",albumID);
		mav.addObject("mType",mType);
		mav.addObject("cover",a);
		mav.addObject("album",album);
		return mav;
	}
	
	//音樂管理-編輯專輯-編輯專輯資訊
	@RequestMapping("/saveAlbum")
	public String savealbum(String albumID, String albumType, String name, String date, String brand, String musicCategory, String tag, String cover, String cover2, String introduction, String status){
				
		System.out.println("albumID==>"+albumID);
		System.out.println("albumType==>"+albumType);
		System.out.println("name==>"+name);
		System.out.println("date==>"+date);
		System.out.println("brand==>"+brand);
		System.out.println("musicCategory==>"+musicCategory);
		System.out.println("tag==>"+tag);
		System.out.println("cover==>"+cover);
		System.out.println("cover2==>"+cover2);
		System.out.println("introduction==>"+introduction);
		System.out.println("status==>"+status);							
			
				
		return "redirect:editAlbumData.do";
	}
}
