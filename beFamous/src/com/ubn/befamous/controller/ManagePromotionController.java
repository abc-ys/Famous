package com.ubn.befamous.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.Creator;
import com.ubn.befamous.entity.Member;
import com.ubn.befamous.entity.MemberStatus;
import com.ubn.befamous.entity.PromotionActivity;
import com.ubn.befamous.entity.RecommendActivity;
import com.ubn.befamous.entity.Song;
import com.ubn.befamous.entity.SongPrice;

/**
 * 管理大力推活動
 * @author kevin
 *
 */

@Controller
@SessionAttributes
public class ManagePromotionController {
	
	@RequestMapping(value = "/forwardRecommendActivity")
	public ModelAndView forwardRecommendActivity() {
		
		ModelAndView mav = new ModelAndView("forwardRecommendActivity");
	    return mav;
	
	}
	
	@RequestMapping(value = "/saveAlbumRecommendActivity")
	public String saveAlbumRecommendActivity(String[] albumID,RecommendActivity activity) {
		//System.out.println("111==>"+arAlbum.getAlbumID());
		System.out.println("111==>"+albumID[0]);
		System.out.println("111==>"+activity.getTitle());
		//傳入專輯ID
		
	    return "redirect:forwardRecommendActivity.do";
	
	}
	
	@RequestMapping(value = "/saveSongRecommendActivity")
	public String saveSongRecommendActivity(String[] songID,RecommendActivity activity) {
		System.out.println("222==>"+songID[0]);
		System.out.println("222==>"+activity.getTitle());
		//傳入歌曲ID
		
	    return "redirect:forwardRecommendActivity.do";
	
	}
	
	
	@RequestMapping(value = "/queryAlbumById")
	public @ResponseBody 
	Album[] queryAlbumById(String albumId) {
		
		Creator creator = new Creator();
		creator.setAccountName("kevin");
		creator.setMemberId(123456);
		creator.setAccountNO("12345678");
		creator.setUserName("kevin");
		Album album = new Album();
		album.setName("bird");
		album.setAlbumID(Long.parseLong(albumId));
		album.setCreator(creator);
		//album.setBrand("UBN");
		//album.setCover("image/image001.png");
		//album.setType("MP3");
		//album.setDate("2011/10/25");
		//album.setMusicCategory(musicCategory1);
		album.setIntroduction("This is very good album!");

		Album[] arAlbum = {album};
		
	    return arAlbum;
	
	}
	
	@RequestMapping(value = "/querySongById")
	public @ResponseBody 
	Song[] querySongById(String songId) {
		
		
		Creator creator = new Creator();
		creator.setAccountName("kevin");
		creator.setAccountNO("12345678");
		creator.setUserName("kevin");
		creator.setMemberId(123456);
		
		Album album = new Album();
		album.setName("bird");
		album.setAlbumID(1234567);
		album.setCreator(creator);
		album.setBrand("UBN");
		album.setCover("images/album.png");
		album.setType("MP3");
		album.setDate("2011/10/25");
		album.setIntroduction("This is very good album!");
		
		Song song = new Song();
		song.setSongID(Long.parseLong(songId));
		SongPrice songPrice = new SongPrice();
		songPrice.setPrice("30");
		songPrice.setDiscountBonus("15");
		songPrice.setDiscountPrice("15");
		song.setName("AAA");
		song.setSongPrice(songPrice);
		song.setAlbum(album);
		
		Song[] arSong = {song};
		
		return arSong;
		
	}
	
	//查詢推薦專輯列表
	@RequestMapping(value = "/queryRecommendActivity")
	public ModelAndView queryRecommendActivity(String year,String Month) {
		
		ModelAndView mav = new ModelAndView("queryRecommendActivity");
		
		Creator creator = new Creator();
		creator.setAccountName("kevin");
		creator.setAccountNO("12345678");
		creator.setUserName("kevin");
		creator.setMemberId(123456);
		
		RecommendActivity activity = new RecommendActivity();
		Album album = new Album();
		album.setName("bird");
		album.setAlbumID(1234567);
		album.setCreator(creator);
		album.setBrand("UBN");
		album.setCover("images/album.png");
		album.setType("MP3");
		album.setDate("2011/10/25");
		album.setIntroduction("This is very good album!");
		
		Album album2 = new Album();
		album2.setName("bird");
		album2.setAlbumID(1234567);
		album2.setCreator(creator);
		album2.setBrand("UBN");
		album2.setCover("images/album.png");
		album2.setType("MP3");
		album2.setDate("2011/10/25");
		album2.setIntroduction("This is very good album!");
		Set<Album> albumSet = new HashSet();
		albumSet.add(album);
		albumSet.add(album2);
		
		activity.setTitle("萬聖節搞什麼鬼");
		activity.setStartDate("2011/11/08");
		activity.setEndDate("2011/11/10");
		activity.setAlbumSet(albumSet);
		activity.setStatus("1");
		activity.setActivityID(123456);
		RecommendActivity[] arActivity = {activity};
		
		mav.addObject("arActivity", arActivity);
		
	    return mav;
	
	}
	
	//查詢參加會員
	@RequestMapping(value = "/queryJoinMemberForRec")
	public ModelAndView queryJoinMemberForRec(String activityID) {
		
		ModelAndView mav = new ModelAndView("queryJoinMemberForRec");
		
		System.out.println("activityID==>"+activityID);
		
		Member member = new Member();
		member.setMemberId(1234567);
		member.setEmail("123@ubn.net");
		member.setIdentityName("一般會員");
		member.setCreateDate("2010/06/10");
		
		Member[] arMember = {member};
		
		mav.addObject("arMember", arMember);
		
		return mav;
		
	}
	
	
	//查詢購買專輯總數
	@RequestMapping(value = "/queryJoinAlbumForRec")
	public ModelAndView queryJoinAlbumForRec(String activityID) {
		
		ModelAndView mav = new ModelAndView("queryJoinAlbumForRec");
		
		
		Creator creator = new Creator();
		creator.setAccountName("kevin");
		creator.setAccountNO("12345678");
		creator.setUserName("kevin");
		creator.setMemberId(123456);
		
		RecommendActivity activity = new RecommendActivity();
		Album album = new Album();
		album.setName("bird");
		album.setAlbumID(1234567);
		album.setCreator(creator);
		album.setBrand("UBN");
		album.setCover("images/album.png");
		album.setType("MP3");
		album.setDate("2011/10/25");
		album.setIntroduction("This is very good album!");
		
		Album[] arAlbum = {album};
		mav.addObject("arAlbum", arAlbum);
		mav.addObject("purchaseCount", 33);
		mav.addObject("totalCount", 66);
		
		return mav;
		 
	}
	
	
	
	@RequestMapping(value = "/forwardPromotionActivity")
	public ModelAndView forwardPromotionActivity() {
		
		ModelAndView mav = new ModelAndView("forwardPromotionActivity");
	    return mav;
	
	}
	
	
	//儲存行銷活動
	@RequestMapping(value = "/savePromotionActivity")
	public String savePromotionActivity(String[] albumID,String[] songID,String prepaidMoney,String prepaidCount,PromotionActivity activity) {
		if(albumID !=null)
		System.out.println("111albumID==>"+albumID[0]);
		if(songID !=null)
		System.out.println("222songID==>"+songID[0]);
		
		System.out.println("111title==>"+activity.getTitle());
		System.out.println("111Condition==>"+activity.getCondition());	
		System.out.println("111ContentCondition==>"+activity.getContentCondition());
		System.out.println("111prepaidMoney==>"+prepaidMoney);
		System.out.println("111prepaidCount==>"+prepaidCount);
		//傳入專輯ID
		
	    return "redirect:forwardPromotionActivity.do";
	
	}
	
	
	
	
	
	    //查詢活動列表
		@RequestMapping(value = "/queryPromotionActivity")
		public ModelAndView queryPromotionActivity(String year,String Month) {
			
			ModelAndView mav = new ModelAndView("queryPromotionActivity");
			
			Creator creator = new Creator();
			creator.setAccountName("kevin");
			creator.setAccountNO("12345678");
			creator.setUserName("kevin");
			creator.setMemberId(123456);
			
			PromotionActivity activity = new PromotionActivity();
			Album album = new Album();
			album.setName("bird");
			album.setAlbumID(1234567);
			album.setCreator(creator);
			album.setBrand("UBN");
			album.setCover("images/album.png");
			album.setType("MP3");
			album.setDate("2011/10/25");
			album.setIntroduction("This is very good album!");
			
			Album album2 = new Album();
			album2.setName("bird");
			album2.setAlbumID(1234567);
			album2.setCreator(creator);
			album2.setBrand("UBN");
			album2.setCover("images/album.png");
			album2.setType("MP3");
			album2.setDate("2011/10/25");
			album2.setIntroduction("This is very good album!");
			Set<Album> albumSet = new HashSet();
			albumSet.add(album);
			albumSet.add(album2);
			
			activity.setTitle("萬聖節搞什麼鬼");
			activity.setStartDate("2011/11/08");
			activity.setEndDate("2011/11/10");
			activity.setAlbumSet(albumSet);
			activity.setStatus("1");
			activity.setActivityID(123456);
			activity.setContentCondition("1");
			activity.setRewardCondition("1");
			activity.setReward("20");
			PromotionActivity[] arActivity = {activity};
			
			mav.addObject("arActivity", arActivity);
			
		    return mav;
		
		}
	
	
		//查詢參加會員(行銷活動)
		@RequestMapping(value = "/queryJoinMemberForPro")
		public ModelAndView queryJoinMemberForPro(String activityID) {
			
			ModelAndView mav = new ModelAndView("queryJoinMemberForPro");
			
			System.out.println("activityID==>"+activityID);
			
			Member member = new Member();
			member.setMemberId(1234567);
			member.setEmail("123@ubn.net");
			member.setIdentityName("一般會員");
			member.setCreateDate("2010/06/10");
			
			Member[] arMember = {member};
			
			mav.addObject("arMember", arMember);
			
			return mav;
			
		}
	
	
}
