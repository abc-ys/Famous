package com.ubn.befamous.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.Creator;
import com.ubn.befamous.entity.InelegantKeyword;
import com.ubn.befamous.entity.Keyword;
import com.ubn.befamous.entity.MusicCategory;
import com.ubn.befamous.entity.News;
import com.ubn.befamous.entity.Song;
import com.ubn.befamous.entity.SongPrice;
import com.ubn.befamous.service.MusicService;

@Controller
@SessionAttributes
public class KeyWordController {
	
	@Autowired
	public MusicService musicService;
	
	//查詢關鍵字
	@RequestMapping(value = "/queryKeyWord")
	public ModelAndView queryKeyWord(String keyWord) {
		
		ModelAndView mav = new ModelAndView("queryKeyWord");
		//String aaa = "kevin[lucy";
		//String[]  test =  aaa.split("\\[");
		//System.out.println("AAAA==>"+test[0]);
		
		ArrayList a =musicService.queryKeyword(keyWord);
		mav.addObject("keyWordList",a);
		mav.addObject("keyWord",keyWord);
		mav.addObject("arAlbum",a.get(0));  //關鍵字首頁的專輯清單
		mav.addObject("arSong",a.get(1));  //關鍵字首頁的歌曲清單
		mav.addObject("arCreator",a.get(2));  //關鍵字首頁的創作人清單
		return mav;
	}
	
	
	//查詢關鍵字(歌曲)
	@RequestMapping(value = "/queryKeyWordForSong")
	public ModelAndView queryKeyWordForSong(String keyWord) {
		
		Song[] arSong =musicService.queryKeywordForSongs(keyWord);
		
		return new ModelAndView("queryKeyWordForSong","arSong",arSong);
		
		
	}
	
	//查詢關鍵字(專輯)
	@RequestMapping(value = "/queryKeyWordForAlbum")
	public ModelAndView queryKeyWordForAlbum(String keyWord) {
		ModelAndView mav = new ModelAndView("queryKeyWordForAlbum");
		
		Album[] arAlbum = musicService.queryKeywordForAlbums(keyWord); 
		
		mav.addObject("arAlbum", arAlbum);
		return mav;
		
		
	}
	
	//查詢關鍵字(創作人)
	@RequestMapping(value = "/queryKeyWordForCreator")
	public ModelAndView queryKeyWordForCreator(String keyWord) {
		ModelAndView mav = new ModelAndView("queryKeyWordForCreator");
		
		Creator[] arCreator = musicService.queryKeywordForCreators(keyWord);
	
		mav.addObject("arCreator", arCreator);
		return mav;
		
		
	}
	
	
	//查詢關鍵字(消息)
	@RequestMapping(value = "/queryKeyWordForNews")
	public ModelAndView queryKeyWordForNews(String keyWord) {
		ModelAndView mav = new ModelAndView("queryKeyWordForNews");
	     
	    News[] arNews = musicService.queryKeywordForNews(keyWord);
	    mav.addObject("arNews", arNews);
	   
	    return mav;
	}
	
	
	//怡秀 write-1107
		//管理關鍵字-排除字詞組設定
		@RequestMapping("/manageKeyWord")
		public ModelAndView managekeyword(long adminID) {
			ModelAndView mav = new ModelAndView("manageKeyWord");
			    
			InelegantKeyword[] kw = musicService.queryInelegantKeywords();
			
			mav.addObject("keyword", kw);
			mav.addObject("adminID", adminID);
			return mav;
		}
		
		//管理關鍵字-排除字詞組設定-新增關鍵字
		@RequestMapping("/addKeyWord")
		public String addkeyword(String keyWordName,long adminID) {
			
			System.out.println("keyWordName==>"+keyWordName);
			musicService.addInelegantKeywords(adminID, keyWordName);
			return "redirect:manageKeyWord.do?adminID="+adminID;
		}
		
		//管理關鍵字-排除字詞組設定-刪除關鍵字
		@RequestMapping("/deleteKeyWord")
		public String deletekeyword(long ID,long adminID) {
			
			System.out.println("刪除關鍵字");
			musicService.deleteInelegantKeywords(ID);
			return "redirect:manageKeyWord.do?adminID="+adminID;
		}
}
