package com.ubn.befamous.controller;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.Creator;
import com.ubn.befamous.entity.DownloadList;
import com.ubn.befamous.entity.Friend;
import com.ubn.befamous.entity.Member;
import com.ubn.befamous.entity.ShoppingCart;
import com.ubn.befamous.entity.ShoppingCartDetail;

@Controller
@SessionAttributes
public class AccountController {
	
	@RequestMapping("/myAccount")
	public ModelAndView queryMemberTotalData(String memberID) 
	{
		ArrayList list = new ArrayList();	
		
		int undownloads = 1; //未下載歌曲數量
		int shoppingcarts = 1; //尚未購買數量
		int friends = 1; //朋友邀請數量
		int gsimoney = 800; //尚餘gsimoney
		int gsibonus = 20; //尚餘gsibonus
		int sales = 20; //本月銷售量
		
		Creator recommendCreator = new Creator();
		recommendCreator.setMemberId(001);
		recommendCreator.setUserName("lucy");
		Creator recommendCreator2 = new Creator();
		recommendCreator2.setMemberId(002);
		recommendCreator2.setUserName("kevin");
		Creator[] recommendCreators = {recommendCreator,recommendCreator2};
		
		list.add(memberID);	
		list.add(undownloads);		
		list.add(shoppingcarts);	
		list.add(friends);
		list.add(gsimoney);	
		list.add(gsibonus);	
		list.add(sales);
		list.add(recommendCreators);
		return new ModelAndView("queryMemberTotalData","account",list);
	}
	
	@RequestMapping("/myDownload")
	public ModelAndView queryDownload(String memberID) 
	{
		ArrayList list = new ArrayList();	
		DownloadList download = new DownloadList();
		download.setDownloadList_rid(01);
		list.add(download);
		return new ModelAndView("queryDownload","downloadlist",list);
	}
	
	@RequestMapping("/myShoppingCart")
	public ModelAndView queryShoppingCart(String memberID) 
	{
		ArrayList list = new ArrayList();	
		return new ModelAndView("queryShoppingCart","shoppingCartList",list);
	}
	
	
	@RequestMapping("/myGSiMoney")
	public ModelAndView queryGSiMoney(String memberID) 
	{
		ArrayList list = new ArrayList();	

		return new ModelAndView("queryGSiMoney","account",list);
	}
	@RequestMapping("/myGSiBonus")
	public ModelAndView queryGSiBonus(String memberID) 
	{
		ArrayList list = new ArrayList();	

		return new ModelAndView("queryGSiBonus","account",list);
	}
	//帳戶資料編輯頁
	@RequestMapping("/editMemberAccount")
	public ModelAndView queryAccountData(String memberID, Model model) 
	{
		System.out.println("queryAccountData==>");
		System.out.println("	memberID="+memberID);
		Creator creator = new Creator();
		creator.setAccountName("劉為駿");
		creator.setAccountNO("1234567");
		creator.setAddress("台北市");
		creator.setCellPhone("0999999999");
		creator.setBankBranch("台中分行");
		creator.setBankName("台灣銀行");
		creator.setTel("0422222222");
		creator.setIdentityNO("N111111111");
		model.addAttribute("member",memberID);
		model.addAttribute("account",creator);
		
		return new ModelAndView("editMemberAccount");
		
	}
	//修改帳戶資料編輯頁的修改並呈現修改後結果
	@RequestMapping("/saveAccountData")
	public ModelAndView saveAccountData(String memberID, String accountName, String accountNo, String bankName, String bankBranch, String identityNO, String address, String tel, String cellPhone, Model model ) 
	{
		System.out.println("saveAccountData==>");
		System.out.println("	memberID="+memberID+", accountName="+accountName+", accountNo="+accountNo+", bankName="+bankName+", bankBranch="+bankBranch+", identityNO="+identityNO+", address="+address+", tel="+tel+", cellPhone="+cellPhone);
		Creator creator = new Creator();
		creator.setAccountName(accountName);
		creator.setAccountNO(accountNo);
		creator.setAddress(address);
		creator.setCellPhone(cellPhone);
		creator.setBankBranch(bankBranch);
		creator.setBankName(bankName);
		creator.setTel(tel);
		creator.setIdentityNO(identityNO);
		
		model.addAttribute("account",creator);
		
		return new ModelAndView("saveAccountData");
		
	}
}
