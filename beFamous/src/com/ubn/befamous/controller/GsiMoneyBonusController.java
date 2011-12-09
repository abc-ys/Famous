package com.ubn.befamous.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.Creator;
import com.ubn.befamous.entity.GsiBonus;
import com.ubn.befamous.entity.GsiMoney;
import com.ubn.befamous.entity.Order;
import com.ubn.befamous.entity.OrderDetail;
import com.ubn.befamous.entity.ProductionCategory;
import com.ubn.befamous.entity.SDCard;
import com.ubn.befamous.entity.SDCardPrice;
import com.ubn.befamous.entity.Song;
import com.ubn.befamous.entity.SongPrice;
import com.ubn.befamous.service.PersonService;
import com.ubn.befamous.service.TransactionRecordService;

@Controller
public class GsiMoneyBonusController {
	
	@Autowired
	private TransactionRecordService transactionRecordService;
	
	@Autowired
	private PersonService personService;
	
	//查詢GSiMoney收支表(起始查詢)
	@RequestMapping("/incomeOutgo")
	public ModelAndView incomeoutgo(long userId) {
		ModelAndView mav = new ModelAndView("incomeOutgo");
		
		String nowDate = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
		String year = nowDate.substring(0,4);
		String month = nowDate.substring(4,6);

		Order[] orders = this.transactionRecordService.queryGsiMoney(userId, year, month);
		
		//搜尋最新一筆gsiMoney資料
		GsiMoney newOrder = (GsiMoney) this.transactionRecordService.queryOneGsiMoney(userId, year, month).get(0);
		
		//搜尋這個月是否有兌換紀錄
		List<GsiMoney> exchangeRcd = this.transactionRecordService.queryOneExchangeRcd(userId, year, month);
		String noExchange="";
		if(exchangeRcd.isEmpty()){ //代表這個月尚未有兌換記錄
			noExchange = "T";
		}else{
			noExchange = "F";	 //代表這個月已經有兌換記錄
		}
		
		//搜尋上個月最後一筆gsiMoney資料
		Date tempDate = DateUtils.addDays(new Date(), -30);
		String lastDate = DateFormatUtils.format(tempDate, "yyyyMMddHHmmss");		
		String year2 = lastDate.substring(0,4);
		String month2 = lastDate.substring(4,6);
		
		List<GsiMoney> lastOrderList = this.transactionRecordService.queryOneGsiMoney(userId, year2, month2);
		GsiMoney lastOrder = new GsiMoney();
		String noRecord="";
		if(lastOrderList.isEmpty()){ //代表上個月沒有資料
			noRecord = "T";
		}else{
			noRecord = "F"; //代表上個月有資料
			lastOrder = lastOrderList.get(0);			
		}
		
		mav.addObject("userId",userId);
		mav.addObject("noExchange",noExchange);
		mav.addObject("noRecord",noRecord);
		mav.addObject("lastDate",lastDate);
		mav.addObject("newOrder",newOrder);
		mav.addObject("lastOrder",lastOrder);
		mav.addObject("orders",orders);
		mav.addObject("year",year);
		mav.addObject("month",month);
		return mav;
	}
	
	//查詢GSiMoney收支表(根據查詢條件)
	@RequestMapping("/queryIncomeOutgo")
	public ModelAndView queryIncomeOutgo(long userId, String year, String month) {
		ModelAndView mav = new ModelAndView("queryIncomeOutgo");
		
		Order[] orders = this.transactionRecordService.queryGsiMoney(userId, year, month);
		
		//搜尋最新一筆gsiMoney資料
		String nowDate = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
		String nowYear = nowDate.substring(0,4);
		String nowMonth = nowDate.substring(4,6);
		GsiMoney newOrder = (GsiMoney) this.transactionRecordService.queryOneGsiMoney(userId, nowYear, nowMonth).get(0);

		//搜尋這個月是否有兌換紀錄
		List<GsiMoney> exchangeRcd = this.transactionRecordService.queryOneExchangeRcd(userId, nowYear, nowMonth);
		String noExchange="";
		if(exchangeRcd.isEmpty()){ //代表這個月尚未有兌換記錄
			noExchange = "T";
		}else{
			noExchange = "F";	 //代表這個月已經有兌換記錄
		}
		
		//搜尋上個月最後一筆gsiMoney資料
		Date tempDate = DateUtils.addDays(new Date(), -30);
		String lastDate = DateFormatUtils.format(tempDate, "yyyyMMddHHmmss");		
		String year2 = lastDate.substring(0,4);
		String month2 = lastDate.substring(4,6);	
		List<GsiMoney> lastOrderList = this.transactionRecordService.queryOneGsiMoney(userId, year2, month2);
		GsiMoney lastOrder = new GsiMoney();
		String noRecord="";
		if(lastOrderList.isEmpty()){  //代表上個月沒有資料
			noRecord = "T";
		}else{
			noRecord = "F";  //代表上個月有資料
			lastOrder = lastOrderList.get(0);
			
		}		
		mav.addObject("userId",userId);
		mav.addObject("noRecord",noRecord);
		mav.addObject("noExchange",noExchange);
		mav.addObject("lastDate",lastDate);
		mav.addObject("newOrder",newOrder);
		mav.addObject("lastOrder",lastOrder);
		mav.addObject("orders",orders);
		mav.addObject("year",year);
		mav.addObject("month",month);
		return mav;
	}
	
	//GSiMoney收支表的兌換申請
	@RequestMapping("/applyExchange")
	public ModelAndView applyexchage(long userId, String year, String month) {
		ModelAndView mav = new ModelAndView("applyExchange");
		
		//搜尋上個月最後一筆gsiMoney資料
		Date tempDate = DateUtils.addDays(new Date(), -30);
		String lastDate = DateFormatUtils.format(tempDate, "yyyyMMddHHmmss");		
		String year2 = lastDate.substring(0,4);
		String month2 = lastDate.substring(4,6);
		GsiMoney lastOrder = this.transactionRecordService.queryOneGsiMoney(userId, year2, month2).get(0);		
		
		//找尋Creator been
		Creator creator = (Creator) this.personService.queryMember(userId).get(0);
		mav.addObject("lastDate",lastDate);
		mav.addObject("lastOrder",lastOrder);
		mav.addObject("creator",creator);
		System.out.println("year======"+year);
		System.out.println("month======"+month);
		mav.addObject("year",year);
		mav.addObject("month",month);
		return mav;
	}
	
	//儲存兌換GSiMoney申請
	@RequestMapping("/updateExchange")
	public String updateexchange(Creator creator,int money,String synUpdate, String year, String month) {
		System.out.println("synUpdate=====>"+synUpdate);
		this.transactionRecordService.saveChange(creator, money, synUpdate);
		long userId = creator.getId();
		return "redirect:queryIncomeOutgo.do?userId="+userId+"&year="+year+"&month="+month;
	}
	
	//查詢儲值紀錄(起始查詢)
	@RequestMapping("/prePayRecord")
	public ModelAndView prepay(long userId) {
		ModelAndView mav = new ModelAndView("prePayRecord");
		String nowDate = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
		String year = nowDate.substring(0,4);
		String month = nowDate.substring(4,6);
				
		Order[] orders = this.transactionRecordService.queryPrePaidRcd(userId, year, month);
		mav.addObject("year",year);
		mav.addObject("month",month);
		mav.addObject("userId",userId);
		mav.addObject("orders",orders);
		return mav;
	}
	
	//查詢儲值紀錄(根據查詢條件)
	@RequestMapping("/prePayRecords")
	public ModelAndView prePayRecords(long userId, String year, String month) {
		ModelAndView mav = new ModelAndView("prePayRecords");
		Order[] orders = this.transactionRecordService.queryPrePaidRcd(userId, year, month);
		mav.addObject("userId",userId);
		mav.addObject("orders",orders);
		mav.addObject("year",year);
		mav.addObject("month",month);
		return mav;
	}

	//查詢消費紀錄(起始查詢)
	@RequestMapping("/payRecord")
	public ModelAndView pay(long userId) {
		ModelAndView mav = new ModelAndView("payRecord");
		String nowDate = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
		String year = nowDate.substring(0,4);
		String month = nowDate.substring(4,6);
		List<Order> gsiMoneyList  = this.transactionRecordService.queryBuyLogRcd(userId, year, month);		
		ArrayList all = new ArrayList();
		for (Order s:gsiMoneyList) {
			ArrayList one = new ArrayList();
			Order o = (Order) s;
			one.add(s);
			System.out.println("orderId======"+s==null?"null":o.getId());
			List<Object[]> orderDetails  = this.transactionRecordService.queryBuyLogDetail(s.getId());
			one.add(orderDetails);
			all.add(one);
			
		}
		mav.addObject("userId",userId);
		mav.addObject("orders",all);
		mav.addObject("year",year);
		mav.addObject("month",month);
		return mav;
	}
	
	//查詢消費紀錄(根據查詢條件)
	@RequestMapping("/payRecords")
	public ModelAndView payRecords(long userId, String year, String month) {
		ModelAndView mav = new ModelAndView("payRecords");
		List<Order> gsiMoneyList  = this.transactionRecordService.queryBuyLogRcd(userId, year, month);		
		ArrayList all = new ArrayList();
		for (Order s:gsiMoneyList) {
			ArrayList one = new ArrayList();
			Order o = (Order) s;
			one.add(s);
			System.out.println("orderId======"+s==null?"null":o.getId());
			List<Object[]> orderDetails  = this.transactionRecordService.queryBuyLogDetail(s.getId());
			one.add(orderDetails);
			all.add(one);
			
		}
		mav.addObject("userId",userId);
		mav.addObject("orders",all);
		mav.addObject("year",year);
		mav.addObject("month",month);
		return mav;
	}
	
	//查詢訂單詳細紀錄(訂單編號的連結)
	@RequestMapping("/payDetailRecord")  
	public ModelAndView payDetail(long orderId) {
		ModelAndView mav = new ModelAndView("payDetailRecord");
		Order order = this.transactionRecordService.queryTransRcd(orderId);//根據訂單編號找尋訂單been
		List<Object[]> orderDetails  = this.transactionRecordService.queryBuyLogDetail(orderId);//根據訂單編號找尋訂單明細
		mav.addObject("order",order);
		mav.addObject("orderDetails",orderDetails);
		return mav;
	}
	
	//查詢銷售紀錄(起始查詢)
	@RequestMapping("/saleRecord")
	public ModelAndView salerecord(long userId) {
		ModelAndView mav = new ModelAndView("saleRecord");
		String nowDate = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
		String year = nowDate.substring(0,4);
		String month = nowDate.substring(4,6);
		List<Object[]> orderDetails  = this.transactionRecordService.querySaleRcd(userId, year, month);
		mav.addObject("orderDetails",orderDetails);
		mav.addObject("userId",userId);
		mav.addObject("year",year);
		mav.addObject("month",month);
		return mav;
	}
	
	//查詢銷售紀錄(根據查詢條件)
		@RequestMapping("/saleRecords")
		public ModelAndView saleRecords(long userId, String year, String month) {
			ModelAndView mav = new ModelAndView("saleRecords");
			List<Object[]> orderDetails  = this.transactionRecordService.querySaleRcd(userId, year, month);
			mav.addObject("orderDetails",orderDetails);
			mav.addObject("userId",userId);
			mav.addObject("year",year);
			mav.addObject("month",month);
			return mav;
		}
	
	//查詢贈送點數記錄
	@RequestMapping("/rewardRecord")
	public ModelAndView rewardrecord(long userId) {
		ModelAndView mav = new ModelAndView("rewardRecord");
		String nowDate = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
		System.out.println("nowDate=="+nowDate);
		ArrayList list = this.transactionRecordService.queryBonusDetail(userId);
		List<GsiBonus> lastBonusList = (List<GsiBonus>) list.get(0);
		GsiBonus lastBonus = new GsiBonus();
		String noRecord="";
		if(lastBonusList.isEmpty()){
			noRecord = "T";
		}else{
			noRecord = "F";
			for(GsiBonus gs:lastBonusList){
				System.out.println("GsiBonus======"+gs.getId());
				if(gs.getOnDate()==null)
				{
					lastBonus=gs;
					System.out.println("lastBonus======"+gs.getBalance());
					break;
					
				}else{
					if(gs.getOnDate().compareTo(nowDate)<0){
						lastBonus=gs;
						System.out.println("lastBonus======"+gs.getBalance());
						break;
					}
				}
			}
		}
		mav.addObject("userId",userId);
		mav.addObject("nowDate",nowDate);
		mav.addObject("noRecord",noRecord);
		mav.addObject("lastBonus",lastBonus);
		mav.addObject("orders",list.get(1));
		mav.addObject("unBonus",list.get(2));
		return mav;
	}
	
	//Lucy Start
	//我的帳本中的兌換記錄(起始搜尋-找尋六個月的兌換紀錄)
	@RequestMapping("/exchangeRecord")
	public ModelAndView queryExchangeRcd(long userId){
		ModelAndView mav = new ModelAndView("queryExchangeRcd");		
		String nowDate = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
		Date tempDate = DateUtils.addDays(new Date(), -180); 
		String lastDate = DateFormatUtils.format(tempDate, "yyyyMMddHHmmss");		
		GsiMoney[] exchangeMoneys  = this.transactionRecordService.queryFirstExchangeLog(userId, lastDate, nowDate);		
		mav.addObject("userId",userId);
		mav.addObject("exchangeMoneys",exchangeMoneys);
		return mav;
	}
	
	//我的帳本中的兌換記錄(根據查詢條件)
	@RequestMapping("/exchangeRecords")
	public ModelAndView queryExchangeRcds(long userId, String year, String month){
		ModelAndView mav = new ModelAndView("queryExchangeRcds");		
		GsiMoney[] exchangeMoneys = this.transactionRecordService.queryExchangeLog(userId, year, month);
		mav.addObject("userId",userId);
		mav.addObject("exchangeMoneys",exchangeMoneys);
		mav.addObject("year",year);
		mav.addObject("month",month);
		return mav;
	}
	
	//我的帳本中的現金消費記錄(起始查詢)
	@RequestMapping("/cashPay")
	public ModelAndView queryCashRcd(long userId) 
	{
		ModelAndView mav = new ModelAndView("queryCashRcd");		
		Order[] orders = this.transactionRecordService.queryCashRcd(userId, "1","");
		ArrayList all = new ArrayList();
		for (Order s:orders) {
			ArrayList one = new ArrayList();
			Order o = (Order) s;
			one.add(s);
			System.out.println("orderId======"+s==null?"null":o.getId());
			List<Object[]> orderDetails  = this.transactionRecordService.queryBuyLogDetail(s.getId());
			one.add(orderDetails);
			all.add(one);
			
		}
		mav.addObject("userId",userId);
		mav.addObject("orders",all);
		mav.addObject("choice","1");
		return mav;
	}
	
	//我的帳本中的現金消費記錄(根據查詢條件)
	@RequestMapping("/cashPays")
	public ModelAndView queryCashRcds(long userId,String choice, String orderId) 
	{
		ModelAndView mav = new ModelAndView("queryCashRcds");
		System.out.println("orderId======>"+orderId);
		Order[] orders = this.transactionRecordService.queryCashRcd(userId, choice, orderId);
		ArrayList all = new ArrayList();
		for (Order s:orders) {
			ArrayList one = new ArrayList();
			Order o = (Order) s;
			one.add(s);
			System.out.println("orderId======"+s==null?"null":o.getId());
			List<Object[]> orderDetails  = this.transactionRecordService.queryBuyLogDetail(s.getId());
			one.add(orderDetails);
			all.add(one);
			
		}
		mav.addObject("userId",userId);
		mav.addObject("orders",all);
		mav.addObject("orderId",orderId);
		mav.addObject("choice",choice);
		return mav;
	}
	//Lucy End
	
	//查詢詳細現金消費記錄
	@RequestMapping("/cashPayDetail")
	public ModelAndView cashpaydetail(long orderId) {
		ModelAndView mav = new ModelAndView("cashPayDetail");
		Order order = this.transactionRecordService.queryTransRcd(orderId);//根據訂單編號找尋訂單been
		List<Object[]> orderDetails  = this.transactionRecordService.queryBuyLogDetail(orderId);//根據訂單編號找尋訂單明細
		String back="";
		
		if(order.getGsiMoney()==null){
			String mDate= order.getModifyDate(); //已送達後狀態修改的日期
			//判斷消費者是否可以退貨
			Date tempDate = DateUtils.addDays(new Date(), -7);//現在日期往前數七天
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			try {
				Date modifyDate = sdf.parse(mDate);
				if(tempDate.after(modifyDate)){//若現在日期往前數七天還大於修改的日期，表示消費者不能退貨。
					back="n";
				}else{
					back="y";
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		mav.addObject("back",back);
		mav.addObject("order",order);
		mav.addObject("orderDetails",orderDetails);
		return mav;
	}
	
	//詳細現金消費記錄裡的-- 取消現金消費訂單(XXXXXXXXXXXXXXXXXXXXXXX)
	@RequestMapping("/cancelGoods")
	public String cancelgoods(long orderId) {
		System.out.println("取消訂單");
		this.transactionRecordService.cancelGoods(orderId);
		return "redirect:cashPayDetail.do?orderId="+orderId;
	}
	
	//詳細現金消費記錄裡的-- 退貨按鈕
	@RequestMapping("/returnGoods")
	public ModelAndView returngoods(@RequestParam String orderNumber, String productName, String amount) throws UnsupportedEncodingException{
		
		String selStr=java.net.URLDecoder.decode(productName,"UTF-8"); //這行是將中文字做解碼
		
		ModelAndView mav = new ModelAndView("returnGoods");
		mav.addObject("orderNumber",orderNumber);
		mav.addObject("productName",selStr);
		mav.addObject("amount",amount);
		return mav;
	}
}