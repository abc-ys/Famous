package com.ubn.befamous.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.Creator;
import com.ubn.befamous.entity.Hidden;
import com.ubn.befamous.entity.MusicCategory;
import com.ubn.befamous.entity.Song;
import com.ubn.befamous.entity.SongPrice;


@Controller
public class ManageMusicController {

	//音樂管理-查詢專輯
	@RequestMapping("/queryAlbum")
	public ModelAndView queryalbum(String type, String MOPEND, String MCLOSED, String name, String musicType) {
		
		System.out.println("type==>"+type);
		System.out.println("MOPEND==>"+MOPEND);
		System.out.println("MCLOSED==>"+MCLOSED);
		System.out.println("name==>"+name);
		System.out.println("musicType==>"+musicType);
		
		ModelAndView mav = new ModelAndView("queryAlbum");
		
		MusicCategory mc = new MusicCategory();
		mc.setName("搖滾樂");
		MusicCategory mc2 = new MusicCategory();
		mc2.setName("古典樂");
		MusicCategory[] Mc = {mc,mc2};
		
		String amount = "1";
		String amount2 = "5";
		String price = "60";
		String price2 = "180";
		
		MusicCategory musicCategory = new MusicCategory();
		musicCategory.setName("抒情樂");
		Creator creator = new Creator();
		creator.setUserName("鄧福如");
		long u = 77532;
		creator.setMemberId(u);
		Hidden hidden = new Hidden();
		hidden.setHiddenReason("");
		Album album = new Album();
		album.setType("單曲");
		long p = 1159;
		album.setAlbumID(p);
		album.setName("原來如此");
		album.setCreator(creator);
		album.setMusicCategory(musicCategory);
		album.setDate("2011/06/30 14:30:20");
		album.setTag("鄧福如,原來如此,抒情歌");
		album.setHidden(hidden);
		
		MusicCategory musicCategory2 = new MusicCategory();
		musicCategory2.setName("搖滾樂");
		Creator creator2 = new Creator();
		creator2.setUserName("蕭敬騰");
		long u2 = 74102;
		creator2.setMemberId(u2);
		Hidden hidden2 = new Hidden();
		hidden2.setHiddenReason("音質太差");
		hidden2.setModifyDate("2011/08/29 12:30:06");
		hidden2.setModifyUser("小明");
		Album album2 = new Album();
		album2.setType("EP");
		long p2 =13456;
		album2.setAlbumID(p2);
		album2.setName("狂想曲");
		album2.setCreator(creator2);
		album2.setMusicCategory(musicCategory2);
		album2.setDate("2011/07/31 10:30:20");
		album2.setTag("蕭敬騰,狂想曲,搖滾樂");
		album2.setHidden(hidden2);
		
		
		ArrayList list = new ArrayList();     //明細第一筆
		list.add(album);
		list.add(amount);
		list.add(price);
		
		ArrayList list1 = new ArrayList();    //明細第二筆
		list1.add(album2);
		list1.add(amount2);
		list1.add(price2);
		
		ArrayList[]  list3 = {list, list1};
		
		mav.addObject("Album", list3);
		mav.addObject("MusicCategory", Mc);
		return mav;
	}
	
	//音樂管理-查詢專輯-隱藏專輯
	@RequestMapping("/hiddenForAlbum")
	public ModelAndView hiddenalbum(String albumID, String mType) {
		ModelAndView mav = new ModelAndView("hiddenForAlbum");
		
		System.out.println("隱藏albumID==>"+albumID);
		System.out.println("mType==>"+mType);
		
		mav.addObject("mType", mType);
		return mav;
	}
	
	//音樂管理-查詢專輯-取消隱藏專輯
	@RequestMapping("/cancleHiddenAlbum")
	public String canclehiddenalbum(String albumID) {
		
		System.out.println("取消隱藏albumID==>"+albumID);
		
		return "redirect:queryAlbum.do";
	}
	
	//音樂管理-查詢專輯-儲存隱藏理由並關閉隱藏理由的視窗
	@RequestMapping("/addReason")
	public ModelAndView addreason(String reason, String mType) {
		
		System.out.println("reason==>"+reason);
		System.out.println("mType==>"+mType);
		
		ModelAndView mav = new ModelAndView("editMusicCategoryClose");
		if(mType.equals("song")){
			System.out.println("歌曲");
		}else{
			System.out.println("專輯");
		}
		return mav;
	}
	
	//音樂管理-查詢歌曲
	@RequestMapping("/querySong")
	public ModelAndView querysong(String albumName, String name, String musicType) {
			
		System.out.println("albumName==>"+albumName);
		System.out.println("name==>"+name);
		System.out.println("musicType==>"+musicType);
			
		ModelAndView mav = new ModelAndView("querySong");
			
		MusicCategory mc = new MusicCategory();
		mc.setName("搖滾樂");
		MusicCategory mc2 = new MusicCategory();
		mc2.setName("古典樂");
		MusicCategory[] Mc = {mc,mc2};
		
		Creator creator = new Creator();
		creator.setUserName("盧廣仲");
		Album album = new Album();
		album.setName("原來如此");
		album.setCreator(creator);
		long a = 12378;
		album.setAlbumID(a);
		MusicCategory musicCategory = new MusicCategory();
		musicCategory.setName("搖滾樂");
		Hidden hidden = new Hidden();
		hidden.setModifyDate("2011/06/18 08:06:10");
		hidden.setModifyUser("大胖");
		hidden.setHiddenReason("涉嫌抄襲");
		SongPrice songPrice = new SongPrice();
		songPrice.setPrice("20");
		songPrice.setDiscountPrice("15元");
		Song song = new Song();
		long p = 45698;
		song.setSongID(p);
		song.setName("一百種生活");
		song.setAlbum(album);
		song.setMusicCategory(musicCategory);
		song.setDate("2011/03/19 16:00:03");
		song.setSongPrice(songPrice);
		song.setTag("盧廣仲,搖滾樂,一百種生活");
		song.setHidden(hidden);
		
		Creator creator2 = new Creator();
		creator2.setUserName("蕭敬騰");
		Album album2 = new Album();
		album2.setName("狂想曲");
		album2.setCreator(creator2);
		long a2 = 45612;
		album2.setAlbumID(a2);
		MusicCategory musicCategory2 = new MusicCategory();
		musicCategory2.setName("古典樂");
		Hidden hidden2 = new Hidden();
		hidden2.setModifyDate("");
		hidden2.setModifyUser("");
		hidden2.setHiddenReason("");
		SongPrice songPrice2 = new SongPrice();
		songPrice2.setPrice("15");
		songPrice2.setDiscountPrice("不提供");
		Song song2 = new Song();
		long p2 = 12345;
		song2.setSongID(p2);
		song2.setName("你");
		song2.setAlbum(album2);
		song2.setMusicCategory(musicCategory2);
		song2.setDate("2011/05/03 10:03:23");
		song2.setSongPrice(songPrice2);
		song2.setTag("蕭敬騰,狂想曲,你");
		song2.setHidden(hidden2);
			
		Song[] s = {song,song2};
		
		mav.addObject("song", s);
		mav.addObject("MusicCategory", Mc);
		return mav;
	}
		
	//音樂管理-查詢歌曲-隱藏歌曲
	@RequestMapping("/hiddenForSong")
	public ModelAndView hiddensong(String songID, String mType) {
		ModelAndView mav = new ModelAndView("hiddenForAlbum");
			
		System.out.println("隱藏songID==>"+songID);
		System.out.println("mType==>"+mType);
			
		mav.addObject("mType", mType);
		return mav;
	}
		
	//音樂管理-查詢歌曲-取消隱藏歌曲
	@RequestMapping("/cancleHiddenSong")
	public String canclehiddensong(String songID) {
			
		System.out.println("取消隱藏songID==>"+songID);
			
		return "redirect:querySong.do";
	}
	
	//音樂管理-查詢歌曲-歌詞
	@RequestMapping("/showLyrics")
	public ModelAndView showlyrics(String songID) {
		
		System.out.println("songID==>"+songID);
		
		ModelAndView mav = new ModelAndView("showLyrics");
		
		Song s = new Song();
		s.setName("小星星");
		s.setComposer("小明");
		s.setLyricist("中明");
		s.setProducer("大明");
		s.setLyrics("一閃一閃亮晶晶 滿天都是小星星");
		
		mav.addObject("song", s);
		return mav;
	}
}
