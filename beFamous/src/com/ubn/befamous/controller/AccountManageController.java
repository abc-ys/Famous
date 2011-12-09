package com.ubn.befamous.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.Creator;
import com.ubn.befamous.entity.GsiMoney;
import com.ubn.befamous.entity.Member;
import com.ubn.befamous.entity.Order;
import com.ubn.befamous.entity.OrderDetail;
import com.ubn.befamous.service.PersonService;
import com.ubn.befamous.service.TransactionRecordService;

@Controller
public class AccountManageController {

	@Autowired
	private TransactionRecordService transactionRecordService;
	
	@Autowired
	private PersonService personService;

	//帳務管理-訂單發票登錄(起始查詢)
		@RequestMapping("/billRegister")
		public ModelAndView billregister(long adminId) {
			ModelAndView mav = new ModelAndView("billRegister");
			String nowDate = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
			String year = nowDate.substring(0,4);
			String month = nowDate.substring(4,6);
			ArrayList allList = new ArrayList();
			Order[] orders = this.transactionRecordService.queryBillRcd(year, month);
			for(Order o:orders){
				ArrayList list = new ArrayList();
				list.add(o);
				List<Object[]> objectList = (List<Object[]>) this.transactionRecordService.queryBuyLogDetail(o.getId());
				for(Object[] object:objectList){
					OrderDetail orderDetail = (OrderDetail) object[2];
					list.add(orderDetail);
				}
				allList.add(list);
			}
			mav.addObject("year",year);
			mav.addObject("month",month);
			mav.addObject("orders",allList);
			mav.addObject("adminId",adminId);
			return mav;
		}
		
		//帳務管理-訂單發票登錄(根據查詢條件)
		@RequestMapping("/billRegisters")
		public ModelAndView billRegisters(long adminId, String year, String month) {
			ModelAndView mav = new ModelAndView("billRegisters");
			ArrayList allList = new ArrayList();
			Order[] orders = this.transactionRecordService.queryBillRcd(year, month);
			for(Order o:orders){
				ArrayList list = new ArrayList();
				list.add(o);
				List<Object[]> objectList = (List<Object[]>) this.transactionRecordService.queryBuyLogDetail(o.getId());
				for(Object[] object:objectList){
					OrderDetail orderDetail = (OrderDetail) object[2];
					list.add(orderDetail);
				}
				allList.add(list);
			}
			mav.addObject("year",year);
			mav.addObject("month",month);
			mav.addObject("orders",allList);
			mav.addObject("adminId",adminId);
			return mav;
		}
		
		//帳務管理-訂單發票登錄-完成發票登錄
		@RequestMapping("/updateBill")
		public String updatebill(long adminId, long orderId, String billNo, String memo2, String payStatus) {
			this.transactionRecordService.updateBill(adminId, orderId, billNo, memo2, payStatus);		
			return "redirect:billRegister.do?adminId="+adminId;
		}
		
		//帳務管理-兌換金額管理(起始查詢)
		@RequestMapping("/exchangeCash")
		public ModelAndView exchangecash(long adminId) {
			ModelAndView mav = new ModelAndView("exchangeCash");
			GsiMoney[] gsiMoney = this.transactionRecordService.queryFirstExchangeList();
			ArrayList all = new ArrayList();
			for (GsiMoney g:gsiMoney) {
				ArrayList one = new ArrayList();;
				one.add(g);
				Member member = (Member) this.personService.queryMember(g.getMember().getId()).get(0);
				one.add(member);
				all.add(one);			
			}
			mav.addObject("gsiMoneys",all);
			mav.addObject("adminId",adminId);
			return mav;
		}
		
		//帳務管理-兌換金額管理(根據查詢條件)
		@RequestMapping("/exchangeCashs")
		public ModelAndView exchangeCashs(long adminId, String year, String month) {
			ModelAndView mav = new ModelAndView("exchangeCashs");
			GsiMoney[] gsiMoney = this.transactionRecordService.queryExchangeList(year, month);
			ArrayList all = new ArrayList();
			for (GsiMoney g:gsiMoney) {
				ArrayList one = new ArrayList();
				one.add(g);
				Member member = (Member) this.personService.queryMember(g.getMember().getId()).get(0);
				one.add(member);
				all.add(one);			
			}
			mav.addObject("year",year);
			mav.addObject("month",month);
			mav.addObject("gsiMoneys",all);
			mav.addObject("adminId",adminId);		
			return mav;
		}
		
		//帳務管理-兌換金額管理-完成兌換登錄
		@RequestMapping("/updateExchangeCash")
		public String updateexchangecash(long adminId,long gsiMoneyId, String exchangeTax, String paid, String exchangeStatus, String paidDate, String memo, String year, String month) {
			String tempMemo="";
			if(memo.equals("0")){
				tempMemo="完成";
			}else{
				tempMemo=memo;
			}
			this.transactionRecordService.updateExchange(adminId, gsiMoneyId, exchangeTax, paid, exchangeStatus, paidDate, tempMemo);	
			return "redirect:exchangeCashs.do?adminId="+adminId+"&year="+year+"&month="+month;
		}
}
