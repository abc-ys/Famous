package com.ubn.befamous.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.GsiMoney;
import com.ubn.befamous.entity.Member;
import com.ubn.befamous.entity.Order;

@Controller
public class AccountManageController {

	//帳務管理-訂單發票登錄
	@RequestMapping("/billRegister")
	public ModelAndView billregister(String year, String month) {
		ModelAndView mav = new ModelAndView("billRegister");
		
		//System.out.println("year==>"+year);
		//System.out.println("month==>"+month);
		
		String price = "499";
		long p = 12345678;
		Order order = new Order();
		order.setOrderRid(p);
		order.setPurchaseDate("2011/11/1 10:30:06");
		order.setPayMethod("信用卡刷卡");
		order.setPayStatus("");
		order.setBillNo("");
		order.setMemo2("");
		
		String price2 = "300";
		long p2 = 56781234;
		Order order2 = new Order();
		order2.setOrderRid(p2);
		order2.setPurchaseDate("2011/10/26 13:20:16");
		order2.setPayMethod("ATM轉帳");
		order2.setPayStatus("");
		order2.setBillNo("");
		order2.setMemo2("");
		
		ArrayList list = new ArrayList();     //明細第一筆
		list.add(order);
		list.add(price);
		
		ArrayList list1 = new ArrayList();    //明細第二筆
		list1.add(order2);
		list1.add(price2);
		
		ArrayList[]  list3 = {list, list1};
		
		mav.addObject("orders",list3);
		return mav;
	}
	
	//帳務管理-訂單發票登錄-完成發票登錄
	@RequestMapping("/updateBill")
	public String updatebill(String billNo, String note, String pay) {
			
		//System.out.println("pay==>"+pay);
		//System.out.println("billNo==>"+billNo);
		//System.out.println("note==>"+note);
		
		return "redirect:billRegister.do";
	}
	
	//帳務管理-兌換金額管理
	@RequestMapping("/exchangeCash")
	public ModelAndView exchangecash(String year, String month) {
		ModelAndView mav = new ModelAndView("exchangeCash");
		
		//System.out.println("year==>"+year);
		//System.out.println("month==>"+month);
			
		GsiMoney gsiMoney = new GsiMoney();
		gsiMoney.setExchangeDate("2011/10/13 13:20:30");
		gsiMoney.setExchangeStartDate("2011/09/19");
		gsiMoney.setExchangeEndDate("2011/10/13");
		gsiMoney.setExchange("1500");
		Member member = new Member();
		member.setUserName("路人甲");
		long p = 12345678;
		member.setMemberId(p);
		member.setIdentityName("一般會員");
		member.setGsiMoney(gsiMoney);
		
		GsiMoney gsiMoney2 = new GsiMoney();
		gsiMoney2.setExchangeDate("2011/10/10 10:20:30");
		gsiMoney2.setExchangeStartDate("2011/09/15");
		gsiMoney2.setExchangeEndDate("2011/10/10");
		gsiMoney2.setExchange("500");
		Member member2 = new Member();
		member2.setUserName("路人乙");
		long p2 = 56781234;
		member2.setMemberId(p2);
		member2.setIdentityName("樂團");
		member2.setGsiMoney(gsiMoney2);
		
		Member[] members = {member,member2};
		mav.addObject("members",members);
		return mav;
	}
	
	//帳務管理-兌換金額管理-完成兌換登錄
	@RequestMapping("/updateExchangeCash")
	public String updateexchangecash(String exchangeTax, String paid, String handleStatus, String MOPEND, String note) {
				
		//System.out.println("exchangeTax==>"+exchangeTax);
		//System.out.println("paid==>"+paid);
		//System.out.println("handleStatus==>"+handleStatus);
		//System.out.println("MOPEND==>"+MOPEND);
		//System.out.println("note==>"+note);
			
		return "redirect:exchangeCash.do";
	}
}
