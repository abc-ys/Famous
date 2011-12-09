package com.ubn.befamous.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.GsiBonus;
import com.ubn.befamous.entity.Member;
import com.ubn.befamous.entity.MusicCategory;
import com.ubn.befamous.entity.Order;
import com.ubn.befamous.entity.OrderDetail;
import com.ubn.befamous.entity.PrePaid;
import com.ubn.befamous.entity.ProductionCategory;
import com.ubn.befamous.entity.SDCard;
import com.ubn.befamous.entity.SDCardPrice;
import com.ubn.befamous.entity.Song;
import com.ubn.befamous.entity.SongPrice;
import com.ubn.befamous.service.PersonService;
import com.ubn.befamous.service.TransactionRecordService;


@Controller
public class ManageOrderController {	

	@Autowired
	private TransactionRecordService transactionRecordService;
	
	@Autowired
	private PersonService personService;
	
	//訂單管理-現金訂單管理(起始搜尋)
		@RequestMapping("/manageCashOrder")
		public ModelAndView cashorder(long adminId) {
			ModelAndView mav = new ModelAndView("manageCashOrder");
			Order[] orders = this.transactionRecordService.queryCashTransRcd("", "","","","1");
			ArrayList all = new ArrayList();
			for (Order s:orders) {
				ArrayList one = new ArrayList();
				Order o = (Order) s;
				one.add(s);
				System.out.println("orderId======"+s==null?"null":o.getId());
				Member member = (Member) this.personService.queryMember(o.getMember().getId()).get(0);
				System.out.println("memberId======"+s==null?"null":member.getIdentityName());
				one.add(member);
				List<Object[]> orderDetails  = this.transactionRecordService.queryBuyLogDetail(s.getId());
				one.add(orderDetails);
				all.add(one);			
			}
			mav.addObject("adminId",adminId);
			mav.addObject("orders",all);
			mav.addObject("hStatus","1");
			return mav;
		}
		
		//訂單管理-現金訂單管理(根據查詢條件)
			@RequestMapping("/manageCashOrders")
			public ModelAndView manageCashOrders(long adminId, String userEmail, String orderId, String handleStatus, String startDate, String endDate) {
				ModelAndView mav = new ModelAndView("manageCashOrders");
				Order[] orders = this.transactionRecordService.queryCashTransRcd(userEmail, orderId, startDate, endDate, handleStatus);
				System.out.println("userEmail="+userEmail+",	orderId="+orderId+",	handleStatus="+handleStatus+",	startDate="+startDate+",	endDate="+endDate);
				ArrayList all = new ArrayList();
				for (Order s:orders) {
					ArrayList one = new ArrayList();
					Order o = (Order) s;
					one.add(s);
					System.out.println("orderId======"+s==null?"null":o.getId());
					Member member = (Member) this.personService.queryMember(o.getMember().getId()).get(0);
					one.add(member);
					List<Object[]> orderDetails  = this.transactionRecordService.queryBuyLogDetail(s.getId());
					one.add(orderDetails);
					all.add(one);		
				}
				mav.addObject("adminId",adminId);
				mav.addObject("orders",all);
				mav.addObject("userEmail",userEmail);
				mav.addObject("orderId",orderId);
				mav.addObject("hStatus",handleStatus);
				mav.addObject("startDate",startDate);
				mav.addObject("endDate",endDate);
				return mav;
			}
		
		//訂單管理-現金訂單管理-現金訂單明細
		@RequestMapping("/cashOrderDetail")
		public ModelAndView cashorderdetail(long orderId, long adminId) {
			ModelAndView mav = new ModelAndView("cashOrderDetail");
			String nowDate = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
			Order order = this.transactionRecordService.queryTransRcd(orderId);//根據訂單編號找尋訂單been
			Member member = (Member) this.personService.queryMember(order.getMember().getId()).get(0);
			List<Object[]> orderDetails  = this.transactionRecordService.queryBuyLogDetail(orderId);//根據訂單編號找尋訂單明細
			mav.addObject("nowDate",nowDate);
			mav.addObject("member",member);
			mav.addObject("order",order);
			mav.addObject("adminId",adminId);
			mav.addObject("orderDetails",orderDetails);
			return mav;
		}
			
		//訂單管理-現金訂單管理-儲存現金訂單明細
		@RequestMapping("/saveCashOrderDetail")
		public ModelAndView saveCashOrderDetail(long adminId, long orderId, String payStatus, String handleStatus, String billStatus, String memo1, String memo2) {
			this.transactionRecordService.saveCashTransRcd(adminId, orderId, billStatus, payStatus, handleStatus, memo1, memo2);	
			return this.cashorderdetail(orderId, adminId);
		}
		
		//訂單管理-現金訂單管理-現金訂單明細-出貨登錄
		@RequestMapping("/shipRegister")
		public ModelAndView shipregister(long adminId, String shipName, long orderId) {
			ModelAndView mav = new ModelAndView("shipRegister");
			mav.addObject("orderId",orderId);
			mav.addObject("adminId",adminId);
			mav.addObject("shipName",shipName);
			return mav;
		}
			
		//訂單管理-現金訂單管理-現金訂單明細-儲存出貨登錄
		@RequestMapping("/updateShipRegister")
		public String updateshipregister(long adminId, long orderId, String MOPEND, String shipNo, String billNo) {
								
			System.out.println("MOPEND=="+MOPEND);
			System.out.println("shipNo=="+shipNo);
			System.out.println("billNo=="+billNo);
			String shipDate = MOPEND.replaceAll("-", "");
			shipDate = shipDate+"000000";
			this.transactionRecordService.updateShipRegister(adminId, orderId, shipDate, shipNo, billNo);
			return "redirect:cashOrderDetail.do?orderId="+orderId+"&adminId="+adminId;
		}
		
		//訂單管理-GSiMoney訂單管理(起始搜尋)
		@RequestMapping("/manageGSiMoneyOrder")
		public ModelAndView gsimoneyorder(long adminId) {
			ModelAndView mav = new ModelAndView("manageGSiMoneyOrder");
			String nowDate = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
			String year = nowDate.substring(0,4);
			String month = nowDate.substring(4,6);
			Order[] orders = this.transactionRecordService.queryFirstInventTransRcd(year, month);
			ArrayList all = new ArrayList();
			for (Order s:orders) {
				ArrayList one = new ArrayList();
				Order o = (Order) s;
				one.add(s);
				System.out.println("orderId======"+s==null?"null":o.getId());
				Member member = (Member) this.personService.queryMember(o.getMember().getId()).get(0);
				one.add(member);
				List<Object[]> orderDetails  = this.transactionRecordService.queryBuyLogDetail(s.getId());
				one.add(orderDetails);
				all.add(one);			
			}
			mav.addObject("adminId",adminId);
			mav.addObject("orders",all);
			return mav;
		}
		
		//訂單管理-GSiMoney訂單管理(根據查詢條件)
		@RequestMapping("/manageGSiMoneyOrders")
		public ModelAndView manageGSiMoneyOrders(long adminId, String userEmail, String orderId, String startDate, String endDate) {
			ModelAndView mav = new ModelAndView("manageGSiMoneyOrders");
			Order[] orders = this.transactionRecordService.queryInventTransRcd(userEmail, orderId, startDate, endDate);
			ArrayList all = new ArrayList();
			for (Order s:orders) {
				ArrayList one = new ArrayList();
				Order o = (Order) s;
				one.add(s);
				System.out.println("orderId======"+s==null?"null":o.getId());
				Member member = (Member) this.personService.queryMember(o.getMember().getId()).get(0);
				one.add(member);
				List<Object[]> orderDetails  = this.transactionRecordService.queryBuyLogDetail(s.getId());
				one.add(orderDetails);
				all.add(one);
				
			}
			mav.addObject("adminId",adminId);
			mav.addObject("orders",all);
			mav.addObject("userEmail",userEmail);
			mav.addObject("orderId",orderId);
			mav.addObject("startDate",startDate);
			mav.addObject("endDate",endDate);
			return mav;
		}
		
		//訂單管理-GSiMoney訂單管理-GSiMoney訂單明細
		@RequestMapping("/gsimoneyOrderDetail")
		public ModelAndView gsimoneyorderdetail(long orderId) {
			ModelAndView mav = new ModelAndView("gsimoneyOrderDetail");			
			Order order = this.transactionRecordService.queryTransRcd(orderId);//根據訂單編號找尋訂單been
			List<Object[]> orderDetails  = this.transactionRecordService.queryBuyLogDetail(orderId);//根據訂單編號找尋訂單明細
			mav.addObject("order",order);
			mav.addObject("orderDetails",orderDetails);
			return mav;
		}
		
		//訂單管理-GSiMoney訂單管理-GSiMoney訂單備註
		@RequestMapping("/gsimoneyOrderNote")
		public ModelAndView gsimoneyordernote(long adminId, long orderId) {
			ModelAndView mav = new ModelAndView("gsimoneyOrderNote");
			ArrayList noteList = this.transactionRecordService.queryInventMemo(orderId);
			mav.addObject("noteList",noteList);
			mav.addObject("adminId",adminId);
			mav.addObject("orderId",orderId);
			return mav;
		}
		
		//訂單管理-GSiMoney訂單管理-儲存備註內容並關閉GSiMoney訂單備註的視窗
		@RequestMapping("/editClose")
		public ModelAndView editclose(long adminId, long orderId, String memo1, String memo2) {
			ModelAndView mav = new ModelAndView("editMusicCategoryClose");
			this.transactionRecordService.updateInventMemo(adminId, orderId, memo1, memo2);
			System.out.println("memo1==>"+memo1);
			System.out.println("memo2==>"+memo2);
			return mav;
		}
}