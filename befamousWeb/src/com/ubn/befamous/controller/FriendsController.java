package com.ubn.befamous.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.Friend;
import com.ubn.befamous.entity.Member;

@Controller
@SessionAttributes
public class FriendsController {
	
	//交友圈管理的好友列表(待確認好友清單)
		@RequestMapping("/myFriendList")
		public ModelAndView queryFriend(String memberID,Model model) 
		{	
			System.out.println("myFriendList==>");
			Member unfriend = new Member();
			unfriend.setUserName("aaa");
			unfriend.setMemberId(001);
			unfriend.setPicture("images/title_01.gif");
			unfriend.setIdentityName("一般會員");
			
			Member[] unfriends = {unfriend};		
			
			model.addAttribute("memberID", memberID);	
			model.addAttribute("unfriends", unfriends);	
			
			return new ModelAndView("queryFriend");
		}
		
		//儲存確認好友邀請結果
		@RequestMapping("/acceptFriend")
		public String acceptFriend(String memberID,String friendID)
		{
			System.out.println("acceptFriend==>");
			Friend f = new Friend();
			f.setAcceptInviteDate("2011/11/11");
			return "redirect:myFriendList.do";
		}
		
		//儲存拒絕好友邀請結果
		@RequestMapping("/refuseFriend")
		public String refuseFriend(String memberID,String friendID)
		{
			System.out.println("refuseFriend==>");
			Friend f = new Friend();
			f.setRejectInviteDate("2011/11/11");
			return "redirect:myFriendList.do";
		}
		
		//交友圈管理的好友列表(已確認好友清單)(iframe)
		@RequestMapping("/myFriendIframe")
		public ModelAndView myFriendIframe(String memberID,Model model) 
		{
			System.out.println("myFriendIframe==>");
			Member friend1 = new Member();
			friend1.setUserName("bbb");
			friend1.setMemberId(002);
			friend1.setPicture("images/title_01.gif");
			friend1.setIdentityName("一般會員");	
			
			Member friend2 = new Member();
			friend2.setUserName("ccc");
			friend2.setMemberId(003);
			friend2.setPicture("images/title_01.gif");
			friend1.setIdentityName("創作者");
			
			Member friend3 = new Member();
			friend3.setUserName("ddd");
			friend3.setMemberId(004);
			friend3.setPicture("images/title_01.gif");
			friend1.setIdentityName("創作者");
			
			Member[] friends = {friend1, friend2, friend3};
			model.addAttribute("memberID", memberID);	
			model.addAttribute("friends", friends);	
			return new ModelAndView("myFriendIframe");
		}
		
		//刪除已確認的好友(window open)	
		@RequestMapping("/confirmDelFriend")
		public ModelAndView confirmDelFriend(String memberID,String friendID,Model model)
		{
			System.out.println("confirmDelFriend==>");
			String fname="bbb";
			String fid="002";
			
			model.addAttribute("memerID",memberID);
			model.addAttribute("fname",fname);
			model.addAttribute("fid",fid);
			return new ModelAndView("confirmDelFriend");
		}
		
		//儲存刪除好友結果 (window close)	
		@RequestMapping("/saveDelFriend")
		public ModelAndView deleteFriend(String memberID,String friendID)
		{
			System.out.println("saveDelFriend==>");
			return new ModelAndView("saveDelFriend");
		}
		
		//交友圈管理的喜愛創作人列表
		@RequestMapping("/myLikeCreatorList")
		public ModelAndView queryLikeCreator(String memberID,Model model) 
		{
			System.out.println("myLikeCreatorList==>");
			Member creator = new Member();
			creator.setUserName("aaaa");
			creator.setMemberId(105);
			creator.setPicture("images/title_01.gif");
			
			Member creator1 = new Member();
			creator1.setUserName("bbbb");
			creator1.setMemberId(106);
			creator1.setPicture("images/title_01.gif");
			
			Member creator2 = new Member();
			creator2.setUserName("cccc");
			creator2.setMemberId(107);
			creator2.setPicture("images/title_01.gif");
			
			Member creator3 = new Member();
			creator3.setUserName("dddd");
			creator3.setMemberId(108);
			creator3.setPicture("images/title_01.gif");
			
			Member[] creators = {creator, creator1, creator2, creator3};
			
			model.addAttribute("memberID", memberID);	
			model.addAttribute("creators",creators);
			return new ModelAndView("queryLikeCreator");
		}
		
		//刪除喜愛創作人(window open)	
		@RequestMapping("/confirmDelCreator")
		public ModelAndView confirmDelCreator(String memberID,String creatorID, String creatorName, Model model)
		{
			System.out.println("confirmDelCreator==>");
			System.out.println("    memberID="+memberID+", creatorID="+creatorID+", creatorName="+creatorName);
			model.addAttribute("memerID",memberID);
			model.addAttribute("cname",creatorName);
			model.addAttribute("cid",creatorID);
			return new ModelAndView("confirmDelCreator");
		}
		
		//儲存刪除喜愛創作人結果(window close)	
		@RequestMapping("/saveDelCreator")
		public ModelAndView saveDelCreator(String memberID,String creatorID)
		{
			System.out.println("saveDelCreator==>");	
			System.out.println("    memberID="+memberID+", creatorID="+creatorID);
			return new ModelAndView("saveDelCreator");
		}

}
