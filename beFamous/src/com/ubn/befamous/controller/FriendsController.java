package com.ubn.befamous.controller;

import java.util.ArrayList;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.Friend;
import com.ubn.befamous.entity.GeneralMember;
import com.ubn.befamous.entity.LikeCreator;
import com.ubn.befamous.entity.Member;
import com.ubn.befamous.service.PersonService;

@Controller
@SessionAttributes
public class FriendsController {
	
	@Autowired
	private PersonService personService;
	
	@RequestMapping("/addFriend")
	public @ResponseBody int addFriend(long memberID,HttpServletRequest request,Model model) {
		long userID=3;
		//long userID = (Long)request.getSession().getAttribute("userID");  從session取得userID
		
		personService.addFriend(memberID, userID, "");
		GeneralMember member = personService.queryMemberData(memberID);
		Set<Friend> friend = member.getFriend();
		return friend.size();
	}
	
	@RequestMapping("/addCreatorFriend")
	public @ResponseBody void addCreatorFriend(long memberID,Model model) {
		long userID=3;
		//long userID = (Long)request.getSession().getAttribute("userID"); 從session取得userID
		personService.addFriend(memberID, userID, "");
	}
	
	@RequestMapping("/addFan")
	public @ResponseBody void addFan(long memberID,Model model) {
		long userID=2;
		//long userID = (Long)request.getSession().getAttribute("userID"); 從session取得userID
		
		personService.addFan(memberID, userID);
	}
	
	//交友圈管理的好友列表(待確認好友清單)
		@RequestMapping("/myFriendList/{userID}")
		public ModelAndView queryFriend(@PathVariable("userID") long userID,Model model) 
		{	
			System.out.println("myFriendList==>");
			/*Member unfriend = new Member();
			unfriend.setUserName("aaa");
			unfriend.setId(001);
			unfriend.setPicture("images/title_01.gif");
			unfriend.setIdentityName("一般會員");
			
			Member[] unfriends = {unfriend};		
			*/
			Friend[] arFriend = personService.queryUnCheckFriend(userID);
			model.addAttribute("userID", userID);	
			
			return new ModelAndView("queryFriend");
		}
		
		//儲存確認好友邀請結果
		@RequestMapping("/acceptFriend")
		public String acceptFriend(long userID,long friendID)
		{
			System.out.println("acceptFriend==>");
			
			//設定接受時間AcceptInviteDate("2011/11/11");
			personService.saveFriend(userID,friendID,"1");
			return "redirect:myFriendList/"+userID+".do";
		}
		
		//儲存拒絕好友邀請結果
		@RequestMapping("/refuseFriend")
		public String refuseFriend(long userID,long friendID)
		{
			System.out.println("refuseFriend==>");
			
			//設定拒絕時間RejectInviteDate("2011/11/11");
			personService.saveFriend(userID,friendID,"2");
			return "redirect:myFriendList/"+userID+".do";
		}
		
		//交友圈管理的好友列表(已確認好友清單)(iframe)
		@RequestMapping("/myFriendIframe")
		public ModelAndView myFriendIframe(long userID,Model model) 
		{
			System.out.println("myFriendIframe==>");
			/*Member friend1 = new Member();
			friend1.setUserName("bbb");
			friend1.setId(002);
			friend1.setPicture("images/title_01.gif");
			friend1.setIdentityName("一般會員");	
			
			Member friend2 = new Member();
			friend2.setUserName("ccc");
			friend2.setId(003);
			friend2.setPicture("images/title_01.gif");
			friend1.setIdentityName("創作者");
			
			Member friend3 = new Member();
			friend3.setUserName("ddd");
			friend3.setId(004);
			friend3.setPicture("images/title_01.gif");
			friend1.setIdentityName("創作者");
			
			Member[] friends = {friend1, friend2, friend3};
			*/
			Friend[] arFriend = personService.queryFriend(userID);
			model.addAttribute("userID", userID);	
			model.addAttribute("friends", arFriend);	
			return new ModelAndView("myFriendIframe");
		}
		
		//是否要刪除已確認的好友(window open)	
		@RequestMapping("/confirmDelFriend")
		public ModelAndView confirmDelFriend(long userID,long friendID,Model model)
		{
			System.out.println("confirmDelFriend==>");
			//String fname="bbb";
			//String fid="002";
			Friend friend = personService.queryFriend(userID,friendID);
			
			model.addAttribute("userID",userID);
			model.addAttribute("fname",friend.getFriend().getUserName());
			model.addAttribute("fid",friendID);
			return new ModelAndView("confirmDelFriend");
		}
		
		//儲存刪除好友結果 (window close)	
		@RequestMapping("/saveDelFriend")
		public ModelAndView deleteFriend(long userID,long friendID)
		{
			System.out.println("saveDelFriend==>");
			personService.deleteFriend(userID,friendID);
			return new ModelAndView("saveDelFriend");
		}
		
		//交友圈管理的喜愛創作人列表
		@RequestMapping("/myLikeCreatorList/{userID}")
		public ModelAndView queryLikeCreator(@PathVariable("userID") long userID,Model model) 
		{
			System.out.println("myLikeCreatorList==>");
			/*
			Member creator = new Member();
			creator.setUserName("aaaa");
			creator.setId(105);
			creator.setPicture("images/title_01.gif");
			
			Member creator1 = new Member();
			creator1.setUserName("bbbb");
			creator1.setId(106);
			creator1.setPicture("images/title_01.gif");
			
			Member creator2 = new Member();
			creator2.setUserName("cccc");
			creator2.setId(107);
			creator2.setPicture("images/title_01.gif");
			
			Member creator3 = new Member();
			creator3.setUserName("dddd");
			creator3.setId(108);
			creator3.setPicture("images/title_01.gif");
			
			Member[] creators = {creator, creator1, creator2, creator3};
			*/
			LikeCreator[] arLikeCreator = personService.queryLikeCreator(userID);
			model.addAttribute("userID", userID);	
			model.addAttribute("creators",arLikeCreator);
			return new ModelAndView("queryLikeCreator");
		}
		
		//刪除喜愛創作人(window open)	
		@RequestMapping("/confirmDelCreator/{userID}/{creatorID}/{creatorName}")
		public ModelAndView confirmDelCreator(@PathVariable("userID")long userID,@PathVariable("creatorID")long creatorID, @PathVariable("creatorName")String creatorName, Model model)
		{
			System.out.println("confirmDelCreator==>");
			System.out.println("    userID="+userID+", creatorID="+creatorID+", creatorName="+creatorName);
			model.addAttribute("userID",userID);
			model.addAttribute("cname",creatorName);
			model.addAttribute("cid",creatorID);
			return new ModelAndView("confirmDelCreator");
		}
		
		//儲存刪除喜愛創作人結果(window close)	
		@RequestMapping("/saveDelCreator/{userID}/{creatorID}")
		public ModelAndView saveDelCreator(@PathVariable("userID") long userID,@PathVariable("creatorID") long creatorID)
		{
			System.out.println("saveDelCreator==>");	
			System.out.println("    userID="+userID+", creatorID="+creatorID);
			personService.deleteCreator(userID,creatorID);
			return new ModelAndView("saveDelCreator");
		}

}
