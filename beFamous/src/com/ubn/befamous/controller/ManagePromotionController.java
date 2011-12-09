package com.ubn.befamous.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.Creator;
import com.ubn.befamous.entity.Member;
import com.ubn.befamous.entity.MemberStatus;
import com.ubn.befamous.entity.Order;
import com.ubn.befamous.entity.OrderDetail;
import com.ubn.befamous.entity.PrePaid;
import com.ubn.befamous.entity.PrePaidPrice;
import com.ubn.befamous.entity.ProductionCategory;
import com.ubn.befamous.entity.PromotionActivity;
import com.ubn.befamous.entity.RecommendActivity;
import com.ubn.befamous.entity.Song;
import com.ubn.befamous.entity.SongPrice;
import com.ubn.befamous.service.MusicService;

/**
 * 管理大力推活動
 * @author kevin
 *
 */

@Controller
@SessionAttributes
public class ManagePromotionController {
	
	@Autowired
	 MusicService musicService;
	
	@RequestMapping(value = "/forwardRecommendActivity")
	public ModelAndView forwardRecommendActivity(String adminId) {
		ModelAndView mav = new ModelAndView("forwardRecommendActivity");
		mav.addObject("adminID", adminId);
	    return mav;
	
	}
	
	@RequestMapping(value = "/saveAlbumRecommendActivity")
	public String saveAlbumRecommendActivity(String adminID,String[] albumID,RecommendActivity activity) {
		//System.out.println("111==>"+arAlbum.getAlbumID());
		for(int i=0;i<albumID.length;i++){
		System.out.println("222==>"+albumID[i]);}
		System.out.println("111==>"+activity.getTitle());
		//傳入專輯ID
		musicService.saveRecommendActivityForAlbum(adminID, activity.getTitle(), activity.getStartDate(), activity.getEndDate(), albumID, activity.getStatus());
		
	    return "redirect:forwardRecommendActivity.do?adminId="+adminID;
	
	}	
	
	@RequestMapping(value = "/queryAlbumById")
	public @ResponseBody 
	List<Map<String,String>> queryAlbumById(String albumId) {

		Map<String,String>map = musicService.queryJoinAlbumsForRec(albumId);
		List<Map<String,String>> resultList=new ArrayList<Map<String,String>>();
		resultList.add(map);
	    return resultList;
	
	}
	
	@RequestMapping(value = "/querySongById")
	public @ResponseBody 
	List<Map<String,String>> querySongById(String songId) {
		
		Map<String,String>map = musicService.queryJoinSongsForRec(songId);
		List<Map<String,String>> resultList=new ArrayList<Map<String,String>>();
		resultList.add(map);
	    return resultList;
		
	}
	
	//查詢推薦專輯列表
	@RequestMapping(value = "/queryRecommendActivitys")
	public ModelAndView queryRecommendActivitys(String adminId,String year,String Month) {
		
		ModelAndView mav = new ModelAndView("queryRecommendActivitys");
		
		if(StringUtils.isBlank(year)&&StringUtils.isBlank(Month)){
		String nowDate = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
		year = nowDate.substring(0,4);
		Month = nowDate.substring(4,6);
		}
		System.out.println("year="+year+","+"Month="+Month);
		RecommendActivity[] arActivity = musicService.queryRecommendActivities(year, Month);
		mav.addObject("arActivity", arActivity);
		mav.addObject("adminID", adminId);
		mav.addObject("year",year);
		mav.addObject("month",Month);
	    return mav;
	
	}
	
	
	//更新推薦專輯列表
	@RequestMapping(value = "/updateAlbumRecommendActivity")
	public String updateAlbumRecommendActivity(String[] albumID,RecommendActivity activity,String adminID,String activityID) {
		System.out.println("###albumID===>"+albumID[0]);
		//ModelAndView mav = new ModelAndView("updateAlbumRecommendActivity");	
		//long activityID = activity.getId();
		//return "redirect:forwardUpdatePromotionActivity.do";
		musicService.updateRecommendActivity(adminID, activityID, activity.getTitle(), activity.getStartDate(), activity.getEndDate(), albumID, activity.getStatus());
		return "redirect:/forwardUpdateRecommendActivity/"+activityID+".do?adminID="+adminID;
	}
			
	
	//查看更新的推薦專輯
    @RequestMapping(value = "/forwardUpdateRecommendActivity/{activityID}")
	public ModelAndView forwardUpdateRecommendActivity(@PathVariable("activityID") String activityID,String adminID) {
			System.out.println("###activityID===>"+activityID);
			ModelAndView mav = new ModelAndView("forwardUpdateRecommendActivity");
			RecommendActivity activity = musicService.queryRecommendActivity(activityID);			
			mav.addObject("activity", activity);
			mav.addObject("adminID", adminID);
		    return mav;
		
		}
	
	
	
	
	//查看推薦專輯
    @RequestMapping(value = "/queryRecommendActivity/{activityID}")
	public ModelAndView queryRecommendActivity(@PathVariable("activityID") String activityID) {
			System.out.println("###activityID===>"+activityID);
			ModelAndView mav = new ModelAndView("queryRecommendActivity");
			
			RecommendActivity activity = musicService.queryRecommendActivity(activityID);
			mav.addObject("activity", activity);
		    return mav;
		
		}
	
	
	
	
	//查詢參加會員
	@RequestMapping(value = "/queryJoinMemberForRec")
	public ModelAndView queryJoinMemberForRec(String activityID) {
		
		ModelAndView mav = new ModelAndView("queryJoinMemberForRec");
		
		System.out.println("activityID==>"+activityID);
		
		Member[] arMember = musicService.queryJoinMembersForRec(activityID);
		
		mav.addObject("arMember", arMember);
		return mav;
	}
	
	
	//查詢購買專輯總數
	@RequestMapping(value = "/queryJoinAlbumForRec")
	public ModelAndView queryJoinAlbumForRec(String activityID) {
		
		ModelAndView mav = new ModelAndView("queryJoinAlbumForRec");
		
		ArrayList list = musicService.queryJoinAlbumForRec(activityID);
		
		mav.addObject("arAlbum", list);
		return mav;
		 
	}
	
	
	
	@RequestMapping(value = "/forwardPromotionActivity")
	public ModelAndView forwardPromotionActivity(String adminId) {
		
		ModelAndView mav = new ModelAndView("forwardPromotionActivity");
		mav.addObject("adminID", adminId);
	    return mav;
	
	}
	
	
	//儲存行銷活動
	@RequestMapping(value = "/savePromotionActivity")
	public String savePromotionActivity(String adminID,String[] albumID,String[] songID,String prepaidMoney,String prepaidCount,String rewardMoney1,String rewardMoney2,PromotionActivity activity) {
		String[] emptySet = {};
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
		if(albumID !=null){
			if(StringUtils.isNotBlank(rewardMoney1)){
				musicService.savePromotionActivity(adminID, activity.getTitle(), activity.getStartDate(), activity.getEndDate(), prepaidMoney, prepaidCount, activity.getContentCondition(), albumID, activity.getCondition(), activity.getRewardCondition(), Integer.parseInt(rewardMoney1), activity.getRewardDeadline(), activity.getStatus());
			}else if(StringUtils.isNotBlank(rewardMoney2)){
				musicService.savePromotionActivity(adminID, activity.getTitle(), activity.getStartDate(), activity.getEndDate(), prepaidMoney, prepaidCount, activity.getContentCondition(), albumID, activity.getCondition(), activity.getRewardCondition(), Integer.parseInt(rewardMoney2), activity.getRewardDeadline(), activity.getStatus());
			}}else if(songID !=null){
				if(StringUtils.isNotBlank(rewardMoney1)){
					musicService.savePromotionActivity(adminID, activity.getTitle(), activity.getStartDate(), activity.getEndDate(), prepaidMoney, prepaidCount, activity.getContentCondition(), songID, activity.getCondition(), activity.getRewardCondition(), Integer.parseInt(rewardMoney1), activity.getRewardDeadline(), activity.getStatus());
				}else if(StringUtils.isNotBlank(rewardMoney2)){
					musicService.savePromotionActivity(adminID, activity.getTitle(), activity.getStartDate(), activity.getEndDate(), prepaidMoney, prepaidCount, activity.getContentCondition(), songID, activity.getCondition(), activity.getRewardCondition(), Integer.parseInt(rewardMoney2), activity.getRewardDeadline(), activity.getStatus());
				}}else{
					if(StringUtils.isNotBlank(rewardMoney1)){
						musicService.savePromotionActivity(adminID, activity.getTitle(), activity.getStartDate(), activity.getEndDate(), prepaidMoney, prepaidCount, activity.getContentCondition(), emptySet, activity.getCondition(), activity.getRewardCondition(), Integer.parseInt(rewardMoney1), activity.getRewardDeadline(), activity.getStatus());
					}else if(StringUtils.isNotBlank(rewardMoney2)){
						musicService.savePromotionActivity(adminID, activity.getTitle(), activity.getStartDate(), activity.getEndDate(), prepaidMoney, prepaidCount, activity.getContentCondition(), emptySet, activity.getCondition(), activity.getRewardCondition(), Integer.parseInt(rewardMoney2), activity.getRewardDeadline(), activity.getStatus());
					}
				}
				return "redirect:forwardPromotionActivity.do?adminId="+adminID;
	
	}
		
	    //查詢活動列表
		@RequestMapping(value = "/queryPromotionActivity")
		public ModelAndView queryPromotionActivity(String year,String Month,String adminId) {
			
			ModelAndView mav = new ModelAndView("queryPromotionActivity");
			
			if(StringUtils.isBlank(year)&&StringUtils.isBlank(Month)){
			String nowDate = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
			year = nowDate.substring(0,4);
			Month = nowDate.substring(4,6);
			}
			System.out.println("year="+year+","+"month="+Month);
			PromotionActivity[] arActivity = musicService.queryPromotionActivities(year, Month);
			
			mav.addObject("arActivity", arActivity);
			mav.addObject("adminID", adminId);
			mav.addObject("year",year);
			mav.addObject("month",Month);
		    return mav;
		
		}
	
		
		
		//更新活動列表
		@RequestMapping(value = "/updatePromotionActivity")
		public String updatePromotionActivity(String adminID,String[] albumID,String[] songID,String prepaidMoney,String prepaidCount,String rewardMoney1,String rewardMoney2,PromotionActivity activity,String activityID) {
			String[] emptySet = {};
			ModelAndView mav = new ModelAndView("updatePromotionActivity");	
			//long activityID = activity.getId();
			//return "redirect:forwardUpdatePromotionActivity.do";
			
			if(activity.getContentCondition().equals("3")){
				if(StringUtils.isNotBlank(rewardMoney1)){
					musicService.updatePromotionActivity(adminID, activityID, activity.getTitle(), activity.getStartDate(), activity.getEndDate(), prepaidMoney, prepaidCount, activity.getContentCondition(), albumID, activity.getCondition(), activity.getRewardCondition(), Integer.parseInt(rewardMoney1), activity.getRewardDeadline(), activity.getStatus());
				}else if(StringUtils.isNotBlank(rewardMoney2)){
					musicService.updatePromotionActivity(adminID, activityID, activity.getTitle(), activity.getStartDate(), activity.getEndDate(), prepaidMoney, prepaidCount, activity.getContentCondition(), albumID, activity.getCondition(), activity.getRewardCondition(), Integer.parseInt(rewardMoney2), activity.getRewardDeadline(), activity.getStatus());
				}}else if(activity.getContentCondition().equals("4")){
					if(StringUtils.isNotBlank(rewardMoney1)){
						musicService.updatePromotionActivity(adminID, activityID, activity.getTitle(), activity.getStartDate(), activity.getEndDate(), prepaidMoney, prepaidCount, activity.getContentCondition(), songID, activity.getCondition(), activity.getRewardCondition(), Integer.parseInt(rewardMoney1), activity.getRewardDeadline(), activity.getStatus());
					}else if(StringUtils.isNotBlank(rewardMoney2)){
						musicService.updatePromotionActivity(adminID, activityID, activity.getTitle(), activity.getStartDate(), activity.getEndDate(), prepaidMoney, prepaidCount, activity.getContentCondition(), songID, activity.getCondition(), activity.getRewardCondition(), Integer.parseInt(rewardMoney2), activity.getRewardDeadline(), activity.getStatus());
					}}else{
						if(StringUtils.isNotBlank(rewardMoney1)){
							musicService.updatePromotionActivity(adminID, activityID, activity.getTitle(), activity.getStartDate(), activity.getEndDate(), prepaidMoney, prepaidCount, activity.getContentCondition(), emptySet, activity.getCondition(), activity.getRewardCondition(), Integer.parseInt(rewardMoney1), activity.getRewardDeadline(), activity.getStatus());
						}else if(StringUtils.isNotBlank(rewardMoney2)){
							musicService.updatePromotionActivity(adminID, activityID, activity.getTitle(), activity.getStartDate(), activity.getEndDate(), prepaidMoney, prepaidCount, activity.getContentCondition(), emptySet, activity.getCondition(), activity.getRewardCondition(), Integer.parseInt(rewardMoney2), activity.getRewardDeadline(), activity.getStatus());
						}
					}
					return "redirect:/forwardUpdatePromotionActivity/"+activityID+".do?adminID="+adminID;
		}
		
		
		
		//查看更新活動列表
		@RequestMapping(value = "/forwardUpdatePromotionActivity/{activityID}")
		public ModelAndView forwardUpdatePromotionActivity(@PathVariable("activityID") String activityID,String adminID) {
			System.out.println("@@@activityID"+activityID);		
			ModelAndView mav = new ModelAndView("forwardUpdatePromotionActivity");	
			
			PromotionActivity activity = musicService.queryPromotionActivity(activityID);
			mav.addObject("activity", activity);
			mav.addObject("adminID", adminID);
			System.out.println("111title==>"+activity.getTitle());
			System.out.println("111Condition==>"+activity.getCondition());	
			System.out.println("111ContentCondition==>"+activity.getContentCondition());
			return mav;
			
		}
	
		//查詢參加會員(行銷活動)
		@RequestMapping(value = "/queryJoinMemberForPro")
		public ModelAndView queryJoinMemberForPro(String activityID) {
			ModelAndView mav = new ModelAndView("queryJoinMemberForPro");
			
			System.out.println("activityID==>"+activityID);
			
			Member[] arMember = musicService.queryJoinMembersForPro(activityID);
			mav.addObject("arMember", arMember);
			return mav;
			
		}
	
	
		//查詢儲值金額(行銷活動)
		@RequestMapping(value = "/queryJoinGSiMoneyForPro")
		public ModelAndView queryJoinGSiMoneyForPro(String activityID) {
					
			ModelAndView mav = new ModelAndView("queryJoinGSiMoneyForPro");
			
			Order[] arOrder = musicService.queryJoinGSiMoneyForPro(activityID);
			mav.addObject("arOrder", arOrder);			
			return mav;					
		}
		
		//查詢儲值次數(行銷活動)
		@RequestMapping(value = "/queryJoinTimesForPro")
		public ModelAndView queryJoinTimesForPro(String activityID) {
							
			ModelAndView mav = new ModelAndView("queryJoinTimesForPro");
			
			ArrayList arMember = musicService.queryJoinTimesForPro(activityID);
			mav.addObject("arMember", arMember);			
			return mav;	
		}
		
		
		
		
		//查詢購買專輯總數(行銷活動)
		@RequestMapping(value = "/queryJoinAlbumForPro")
		public ModelAndView queryJoinAlbumForPro(String activityID) {
			
			ModelAndView mav = new ModelAndView("queryJoinAlbumForPro");
			
			ArrayList arAlbum = musicService.queryJoinAlbumsForPro(activityID);
			mav.addObject("arAlbum", arAlbum);
			
			return mav;
			 
		}
		
		
		//查詢購買單曲總數(行銷活動)
		@RequestMapping(value = "/queryJoinSongsForPro")
		public ModelAndView queryJoinSongsForPro(String activityID) {
			ModelAndView mav = new ModelAndView("queryJoinSongsForPro");
			
			ArrayList arSong = musicService.queryJoinSongsForPro(activityID);
			mav.addObject("arSong", arSong);
			return mav;
		}
		
		
		
}
