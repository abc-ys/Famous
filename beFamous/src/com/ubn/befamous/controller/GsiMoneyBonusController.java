package com.ubn.befamous.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

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

@Controller
public class GsiMoneyBonusController {
	
	//查詢GSiMoney收支表
	@RequestMapping("/incomeOutgo")
	public ModelAndView incomeoutgo() {
		ModelAndView mav = new ModelAndView("incomeOutgo");
		Order order = new Order();
		long id = 89123456;
		order.setOrderRid(id);
		order.setPurchaseDate("2011/03/31");
		GsiMoney gsiMoney = new GsiMoney();
		gsiMoney.setBalance("600");
		gsiMoney.setIncome("300");
		gsiMoney.setMemo("作品販賣");
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrder(order);
		orderDetail.setGsiMoney(gsiMoney);
		
		Order order2 = new Order();
		long id2 = 63253456;
		order2.setOrderRid(id2);
		order2.setPurchaseDate("2011/06/28");
		GsiMoney gsiMoney2 = new GsiMoney();
		gsiMoney2.setBalance("300");
		gsiMoney2.setExchange("300");
		gsiMoney2.setMemo("申請兌換");
		OrderDetail orderDetail2 = new OrderDetail();
		orderDetail2.setOrder(order2);
		orderDetail2.setGsiMoney(gsiMoney2);
		mav.addObject("orderDetail",orderDetail);
		mav.addObject("orderDetail2",orderDetail2);
		return mav;
	}
	
	//GSiMoney收支表的兌換申請
	@RequestMapping("/applyExchange")
	public ModelAndView applyexchage() {
		ModelAndView mav = new ModelAndView("applyExchange");
		Creator creator = new Creator();
		creator.setUserName("路人甲");
		creator.setAccountName("王路人");
		creator.setBankName("華南銀行");
		creator.setBankBranch("中正分行");
		creator.setAccountNO("12345678912");
		creator.setIdentityNO("F123456789");
		creator.setAddress("台北市松山區五分埔");
		creator.setCellPhone("0911254354");
		creator.setTel("0225685697");
		mav.addObject("creator",creator);
		return mav;
	}
	
	//確定申請兌換GSiMoney
	@RequestMapping("/updateExchange")
	public String updateexchange() {
		return "redirect:incomeOutgo.do";
	}
	
	//查詢儲值紀錄
	@RequestMapping("/prePayRecord")
	public ModelAndView prepay() {
		ModelAndView mav = new ModelAndView("prePayRecord");
		Order order = new Order();
		long id = 89123456;
		order.setOrderRid(id);
		order.setPurchaseDate("2011/03/31");
		order.setPayMethod("信用卡付款");
		order.setPayDate("2011/04/01 12:33:46");
		order.setBillStatus("已寄送");
		GsiMoney gsiMoney = new GsiMoney();
		gsiMoney.setPrepaid("300");
		gsiMoney.setMemo("test");
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrder(order);
		orderDetail.setGsiMoney(gsiMoney);
		
		Order order2 = new Order();
		long id2 = 63253456;
		order2.setOrderRid(id2);
		order2.setPurchaseDate("2011/06/28");
		order2.setPayMethod("轉帳付款");
		order2.setPayDate("2011/06/29 12:33:46");
		order2.setBillStatus("未寄送");
		GsiMoney gsiMoney2 = new GsiMoney();
		gsiMoney2.setPrepaid("200");
		gsiMoney2.setMemo("test");
		OrderDetail orderDetail2 = new OrderDetail();
		orderDetail2.setOrder(order2);
		orderDetail2.setGsiMoney(gsiMoney2);
		mav.addObject("orderDetail",orderDetail);
		mav.addObject("orderDetail2",orderDetail2);
		return mav;
	}
	
	//查詢消費紀錄
	@RequestMapping("/payRecord")
	public ModelAndView pay() {
		ModelAndView mav = new ModelAndView("payRecord");
		String tPrice="350";
		String tPrice2="250";
		Order order = new Order();
		long id = 89123456;
		order.setOrderRid(id);
		order.setPurchaseDate("2011/03/31");
		Song song = new Song();
		song.setName("早安晨之美");
		Song song2 = new Song();
		song2.setName("你");
		Album album = new Album();
		album.setName("盧廣仲");
		Album album2 = new Album();
		album2.setName("蕭敬騰");
		ProductionCategory productionCategory = new ProductionCategory();
		productionCategory.setSong(song);
		productionCategory.setAlbum(album);
		ProductionCategory productionCategory2 = new ProductionCategory();
		productionCategory2.setSong(song2);
		productionCategory2.setAlbum(album2);
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setProductionCategory(productionCategory);
		orderDetail.setOrder(order);
		OrderDetail orderDetail2 = new OrderDetail();
		orderDetail2.setProductionCategory(productionCategory2);
		
		
		Order order2 = new Order();
		long id2 = 63253456;
		order2.setOrderRid(id2);
		order2.setPurchaseDate("2011/06/28");
		Song song3 = new Song();
		song3.setName("敷衍");
		Song song4 = new Song();
		song4.setName("仰望");
		Album album3 = new Album();
		album3.setName("蕭敬騰");
		Album album4 = new Album();
		album4.setName("楊丞琳");
		ProductionCategory productionCategory3 = new ProductionCategory();
		productionCategory3.setSong(song3);
		productionCategory3.setAlbum(album3);
		ProductionCategory productionCategory4 = new ProductionCategory();
		productionCategory4.setSong(song4);
		productionCategory4.setAlbum(album4);
		OrderDetail orderDetail3 = new OrderDetail();
		orderDetail3.setProductionCategory(productionCategory3);
		orderDetail3.setOrder(order2);
		OrderDetail orderDetail4 = new OrderDetail();
		orderDetail4.setProductionCategory(productionCategory4);
		OrderDetail[] od = {orderDetail,orderDetail2,orderDetail3,orderDetail4};
		mav.addObject("orderDetail",od);
		mav.addObject("tPrice",tPrice);
		mav.addObject("tPrice2",tPrice2);
		return mav;
	}
	
	//查詢訂單詳細紀錄(訂單編號的連結)
	@RequestMapping("/payDetailRecord")  
	public ModelAndView payDetail() {
		ModelAndView mav = new ModelAndView("payDetailRecord");
		String tPrice="350";
		Order order = new Order();
		long id = 89123456;
		order.setOrderRid(id);
		order.setPurchaseDate("2011/03/31");
		SongPrice songPrice = new SongPrice();
		songPrice.setPrice("20");
		Song song = new Song();
		song.setName("早安晨之美");
		song.setSongPrice(songPrice);
		SongPrice songPrice2 = new SongPrice();
		songPrice2.setPrice("25");
		Song song2 = new Song();
		song2.setName("你");
		song2.setSongPrice(songPrice2);
		Album album = new Album();
		album.setName("盧廣仲");
		Album album2 = new Album();
		album2.setName("蕭敬騰");
		ProductionCategory productionCategory = new ProductionCategory();
		productionCategory.setSong(song);
		productionCategory.setAlbum(album);
		ProductionCategory productionCategory2 = new ProductionCategory();
		productionCategory2.setSong(song2);
		productionCategory2.setAlbum(album2);
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setProductionCategory(productionCategory);
		orderDetail.setOrder(order);
		OrderDetail orderDetail2 = new OrderDetail();
		orderDetail2.setProductionCategory(productionCategory2);
		OrderDetail[] od = {orderDetail,orderDetail2};
		mav.addObject("orderDetail",od);
		mav.addObject("tPrice",tPrice);
		return mav;
	}
	
	//查詢銷售紀錄
	@RequestMapping("/saleRecord")
	public ModelAndView salerecord() {
		ModelAndView mav = new ModelAndView("saleRecord");
		Order order = new Order();
		order.setPurchaseDate("2011/03/31");
		SongPrice songPrice = new SongPrice();
		songPrice.setPrice("20");
		Song song = new Song();
		song.setName("早安晨之美");
		song.setSongPrice(songPrice);
		Album album = new Album();
		album.setName("美妙生活");
		ProductionCategory productionCategory = new ProductionCategory();
		productionCategory.setSong(song);
		productionCategory.setAlbum(album);
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setAmount("2");
		orderDetail.setProductionCategory(productionCategory);
		orderDetail.setOrder(order);
		int tPrice = 40;
		int income = (int) (tPrice-tPrice*0.3);
		
		Order order2 = new Order();
		order2.setPurchaseDate("2011/06/28");
		SongPrice songPrice2 = new SongPrice();
		songPrice2.setPrice("280");
		Song song2 = new Song();
		song2.setSongPrice(songPrice2);
		Album album2 = new Album();
		album2.setName("原來如此");
		ProductionCategory productionCategory2 = new ProductionCategory();
		productionCategory2.setSong(song2);
		productionCategory2.setAlbum(album2);
		OrderDetail orderDetail2 = new OrderDetail();
		orderDetail2.setAmount("1");
		orderDetail2.setProductionCategory(productionCategory2);
		orderDetail2.setOrder(order2);
		int tPrice2 = 280;
		int income2 = (int) (tPrice2-tPrice2*0.3);
		
		mav.addObject("orderDetail",orderDetail);
		mav.addObject("orderDetail2",orderDetail2);
		mav.addObject("tPrice",tPrice);
		mav.addObject("tPrice2",tPrice2);
		mav.addObject("income",income);
		mav.addObject("income2",income2);
		return mav;
	}
	
	//查詢贈送點數記錄
	@RequestMapping("/rewardRecord")
	public ModelAndView rewardrecord() {
		ModelAndView mav = new ModelAndView("rewardRecord");
		String waitOnBonus = "0";
		String OnBonus = "300";
		String offBonus = "0";
		Order order = new Order();
		long id = 89123456;
		order.setOrderRid(id);
		GsiBonus gsiBonus = new GsiBonus();
		gsiBonus.setOnDate("2011/03/31");
		gsiBonus.setOffDate("2011/10/31");
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrder(order);
		orderDetail.setGsiBonus(gsiBonus);
		mav.addObject("orderDetail",orderDetail);
		mav.addObject("waitOnBonus",waitOnBonus);
		mav.addObject("OnBonus",OnBonus);
		mav.addObject("offBonus",offBonus);
		return mav;
	}
	
	//Lucy Start
	//我的帳本中的兌換記錄
	@RequestMapping("/exchangeRecord")
	public ModelAndView queryExchangeRcd(String memberId, String year, String month, Model model){
		
		System.out.println("exchangeRecord==>");
		System.out.println("   MemberID="+memberId+", year="+year+", month="+month);
		GsiMoney money = new GsiMoney ();
		money.setExchangeDate("2011/10/31");
		money.setExchange("500");
		money.setExchangeTax("10");
		money.setPaid("490");
		money.setPaidDate("轉帳失敗");
		money.setExchangeStartDate("2011/09/30");
		money.setExchangeEndDate("2011/10/31");
		money.setBalance("200");
		money.setMemo("帳號資料不齊");
		
		GsiMoney money2 = new GsiMoney ();
		money2.setExchangeDate("2011/10/31");
		money2.setExchange("500");
		money2.setExchangeTax("10");
		money2.setPaid("490");
		money2.setPaidDate("2011/11/12");
		money2.setExchangeStartDate("2011/09/30");
		money2.setExchangeEndDate("2011/10/31");
		money2.setBalance("200");
		money2.setMemo("");
		
		GsiMoney[] exchangeMoneys= {money, money2};
		
		model.addAttribute("exchangeMoneys", exchangeMoneys);
		return new ModelAndView("queryExchangeRcd");
	}
	
	//我的帳本中的現金消費記錄
	@RequestMapping("/cashPay")
	public ModelAndView queryCashRcd(String memberId,String choice, String orderNo, Model model) 
	{
		System.out.println("cashPay==>");
		System.out.println("   MemberID="+memberId+", choice="+choice+", orderNo="+orderNo);

		Order order = new Order();
		order.setOrderRid(1111);
		order.setPurchaseDate("2011/10/10");
		order.setPayMethod("ATM轉帳");
		order.setPayStatus("08/08已付款");
		
		SDCardPrice sdCardPrice = new SDCardPrice();
		sdCardPrice.setpPrice("100");
		SDCard sdCard = new SDCard();
		sdCard.setSdCardPrice(sdCardPrice);
		sdCard.setName("4G GSISD卡");
		ProductionCategory productionCategory = new ProductionCategory();
		productionCategory.setSdCard(sdCard);
		OrderDetail oDetail1 = new OrderDetail();
		oDetail1.setProductionCategory(productionCategory);
		oDetail1.setAmount("1");
		Set<OrderDetail> orderDetail = new HashSet();
		orderDetail.add(oDetail1);		
		order.setOrderDetail(orderDetail);
		
		Order[] orders = {order};
		
		model.addAttribute("orders",orders);
		return new ModelAndView("queryCashRcd");
	}
	//Lucy End
	
	//查詢詳細現金消費記錄
	@RequestMapping("/cashPayDetail")
	public ModelAndView cashpaydetail() {
		ModelAndView mav = new ModelAndView("cashPayDetail");
		String tPrice="499";
		Order order = new Order();
		long id = 89123456;
		order.setOrderRid(id);
		order.setPurchaseDate("2011/03/31");
		order.setPayMethod("信用卡刷卡");
		order.setHandleStatus("已寄送");
		order.setBillStatus("已寄送");
		order.setMemo1("test");
		SDCardPrice sdCardPrice = new SDCardPrice();
		sdCardPrice.setpPrice("499");
		SDCard sdCard = new SDCard();
		sdCard.setName("4G GSiSD卡");
		sdCard.setSdCardPrice(sdCardPrice);
		ProductionCategory productionCategory = new ProductionCategory();
		productionCategory.setSdCard(sdCard);
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setProductionCategory(productionCategory);
		orderDetail.setOrder(order);
		orderDetail.setAmount("2");
		mav.addObject("orderDetail",orderDetail);
		mav.addObject("tPrice",tPrice);
		return mav;
	}
	
	//詳細現金消費記錄裡的-- 取消現金消費訂單
	@RequestMapping("/cancelGoods")
	public String cancelgoods() {
		System.out.println("取消訂單");
		return "redirect:cashPayDetail.do";
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