package com.ubn.befamous.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.Creator;
import com.ubn.befamous.entity.Hidden;
import com.ubn.befamous.entity.MusicCategory;
import com.ubn.befamous.entity.Offense;
import com.ubn.befamous.entity.Song;
import com.ubn.befamous.entity.SongPrice;
import com.ubn.befamous.service.MusicService;


@Controller
public class ManageMusicController {

	@Autowired
	 MusicService musicService;
	
	//音樂管理-查詢專輯
	@RequestMapping("/queryAlbum")
	public ModelAndView queryalbum(String type, String MOPEND, String MCLOSED, String name, String musicType,String adminID) {
		
		ModelAndView mav = new ModelAndView("queryAlbum");
		
		Album[] albumSet;
		if (StringUtils.isBlank(type)&& StringUtils.isBlank(MOPEND)&&StringUtils.isBlank(MCLOSED)&& StringUtils.isBlank(name)&& StringUtils.isBlank(musicType)) {
			albumSet=new Album[0];
			System.out.println("條件為空");
		}else{
			albumSet = musicService.queryForAlbums(type, MOPEND, MCLOSED, name, musicType);
			System.out.println("條件不為空");
			System.out.println("type==>"+type);
			System.out.println("MOPEND==>"+MOPEND);
			System.out.println("MCLOSED==>"+MCLOSED);
			System.out.println("name==>"+name);
			System.out.println("musicType==>"+musicType);
		}
		MusicCategory[] musicCategory = musicService.queryAllMusicCategory();
		
		mav.addObject("Album", albumSet);
		mav.addObject("adminID", "1");
		mav.addObject("musicCategory", musicCategory);
		return mav;
	}
	
	//音樂管理-查詢專輯-隱藏專輯
	@RequestMapping("/hiddenForAlbum")
	public ModelAndView hiddenalbum(String albumID,String adminID,String mType) {
		ModelAndView mav = new ModelAndView("hiddenForAlbum");
		
		System.out.println("隱藏albumID==>"+albumID);
		System.out.println("mType==>"+mType);
		
		Hidden h = musicService.hideAlbum(Long.parseLong(albumID),Long.parseLong(adminID));
		mav.addObject("Hidden", h);
		mav.addObject("mType", mType);
		mav.addObject("albumID", albumID);
		mav.addObject("adminID", adminID);
		return mav;
	}
	
	//音樂管理-查詢專輯-取消隱藏專輯
	@RequestMapping("/cancleHiddenAlbum")
	public String canclehiddenalbum(String albumID,String adminID) {
		
		System.out.println("取消隱藏albumID==>"+albumID);
		musicService.displayAlbum(Long.parseLong(albumID), Long.parseLong(adminID));
		return "redirect:queryAlbum.do?adminID="+adminID;
	}
	
	//音樂管理-查詢專輯-儲存隱藏理由並關閉隱藏理由的視窗
	@RequestMapping("/addReason")
	public ModelAndView addreason(String reason, String mType,String adminID,String HiddenID) {
		
		System.out.println("reason==>"+reason);
		System.out.println("mType==>"+mType);
		
		ModelAndView mav = new ModelAndView("editMusicCategoryClose");
		if(mType.equals("song")){
			System.out.println("歌曲");
			musicService.saveSongHiddenReason(Long.parseLong(HiddenID), reason, Long.parseLong(adminID));
		}else{
			System.out.println("專輯");
			musicService.saveAlbumHiddenReason(Long.parseLong(HiddenID), reason, Long.parseLong(adminID));
		}
		mav.addObject("adminID", adminID);
		return mav;
	}
	
	//音樂管理-查詢歌曲
	@RequestMapping("/querySong")
	public ModelAndView querysong(String albumName, String name, String musicType,String adminID) {
			
		System.out.println("albumName==>"+albumName);
		System.out.println("name==>"+name);
		System.out.println("musicType==>"+musicType);
			
		ModelAndView mav = new ModelAndView("querySong");
			
		Song[] songSet;
		if (StringUtils.isBlank(albumName)&& StringUtils.isBlank(musicType)&& StringUtils.isBlank(name)) {
			songSet=new Song[0];
			System.out.println("條件為空");
		}else{
			songSet = musicService.queryForSongs(albumName, name, musicType);
			System.out.println("條件不為空");
			System.out.println("albumName==>"+albumName);
			System.out.println("name==>"+name);
			System.out.println("musicType==>"+musicType);
		}
		MusicCategory[] musicCategory = musicService.queryAllMusicCategory();
		
		mav.addObject("Song", songSet);
		mav.addObject("adminID", "1");
		mav.addObject("musicCategory", musicCategory);
		return mav;
	}
		
	//音樂管理-查詢歌曲-隱藏歌曲
	@RequestMapping("/hiddenForSong")
	public ModelAndView hiddensong(String songID, String mType,String adminID) {
		ModelAndView mav = new ModelAndView("hiddenForAlbum");
			
		System.out.println("隱藏songID==>"+songID);
		System.out.println("mType==>"+mType);
			
		Hidden h = musicService.hideSong(Long.parseLong(songID),Long.parseLong(adminID));
		mav.addObject("Hidden", h);
		mav.addObject("mType", mType);
		mav.addObject("songID", songID);
		mav.addObject("adminID", adminID);
		return mav;
	}
		
	//音樂管理-查詢歌曲-取消隱藏歌曲
	@RequestMapping("/cancleHiddenSong")
	public String canclehiddensong(String songID,String adminID) {
			
		System.out.println("取消隱藏songID==>"+songID);
		musicService.displaySong(Long.parseLong(songID), Long.parseLong(adminID));
		return "redirect:querySong.do?adminID="+adminID;
	}
	
	//音樂管理-查詢歌曲-歌詞
	@RequestMapping("/showLyrics")
	public ModelAndView showlyrics(String songID) {
		
		System.out.println("songID==>"+songID);
		
		ModelAndView mav = new ModelAndView("showLyrics");
		
		Song s = musicService.querySongLyrics(Long.parseLong(songID));
		
		mav.addObject("song", s);
		return mav;
	}
}
