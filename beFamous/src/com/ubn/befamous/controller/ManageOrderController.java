package com.ubn.befamous.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
import com.ubn.befamous.entity.ProductionCategory;
import com.ubn.befamous.entity.SDCard;
import com.ubn.befamous.entity.SDCardPrice;
import com.ubn.befamous.entity.Song;
import com.ubn.befamous.entity.SongPrice;
import com.ubn.befamous.entity.prePaid;


@Controller
public class ManageOrderController {
	
	//訂單管理-現金訂單管理
	@RequestMapping("/manageCashOrder")
	public ModelAndView cashorder(String userMail, String orderNumber, String handleStatus, String MOPEND, String MCLOSED) {
		ModelAndView mav = new ModelAndView("manageCashOrder");
		System.out.println("userMail=="+userMail);
		System.out.println("orderNumber=="+orderNumber);
		System.out.println("handleStatus=="+handleStatus);
		System.out.println("MOPEND=="+MOPEND);
		System.out.println("MCLOSED=="+MCLOSED);
		
		String tPrice = "499";
		long p = 12345678;
		Member member = new Member();
		member.setEmail("mail@mail.com");
		member.setIdentityName("一般會員");
		long u =123;
		member.setMemberId(u);
		SDCard sdCard = new SDCard();
		sdCard.setName("4G GSiSD卡");
		ProductionCategory productionCategory = new ProductionCategory();
		productionCategory.setSdCard(sdCard);
		OrderDetail oDetail1 = new OrderDetail();
		oDetail1.setProductionCategory(productionCategory);
		Set<OrderDetail> orderDetail = new HashSet();
		orderDetail.add(oDetail1);
		Order order = new Order();
		order.setOrderRid(p);
		order.setMember(member);
		order.setPayMethod("信用卡刷卡");
		order.setPurchaseDate("2011/10/23 13:20:19");
		order.setOrderDetail(orderDetail);
		order.setPayStatus("已付款");
		order.setHandleStatus("處理中");
		order.setMemo2("調貨");
		
		String tPrice2 = "300";
		long p2 = 45612378;
		Member member2 = new Member();
		member2.setEmail("mail2@mail.com");
		member2.setIdentityName("樂團");
		long u2 =123;
		member2.setMemberId(u2);
		prePaid prePaid = new prePaid();
		prePaid.setName("儲值GSiMoney 300元");
		ProductionCategory productionCategory2 = new ProductionCategory();
		productionCategory2.setPrePaid(prePaid);
		OrderDetail oDetail2 = new OrderDetail();
		oDetail2.setProductionCategory(productionCategory2);
		Set<OrderDetail> orderDetail2 = new HashSet();
		orderDetail2.add(oDetail2);
		Order order2 = new Order();
		order2.setOrderRid(p2);
		order2.setMember(member2);
		order2.setPayMethod("ATM匯款");
		order2.setPurchaseDate("2011/10/24 15:20:19");
		order2.setOrderDetail(orderDetail2);
		order2.setPayStatus("已付款");
		order2.setHandleStatus("已儲值");
		order2.setMemo2("test");
		
		Order[] orders = {order,order2};
		mav.addObject("orders",orders);
		mav.addObject("tPrice",tPrice);
		mav.addObject("tPrice2",tPrice2);
		return mav;
	}
	
	//訂單管理-現金訂單管理-現金訂單明細
	@RequestMapping("/cashOrderDetail")
	public ModelAndView cashorderdetail(String payStatus, String handleStatus, String billStatus, String memo1, String memo2) {
		ModelAndView mav = new ModelAndView("cashOrderDetail");
			
		String tPrice = "499";
		String giftPrice = "30";
		long p = 12345678;
		Member member = new Member();
		member.setEmail("mail@mail.com");
		SDCardPrice sdCardPrice = new SDCardPrice();
		sdCardPrice.setDiscountPrice("449");
		sdCardPrice.setDiscountBonus("50");
		SDCard sdCard = new SDCard();
		sdCard.setName("4G GSiSD卡");
		sdCard.setSdCardPrice(sdCardPrice);
		ProductionCategory productionCategory = new ProductionCategory();
		productionCategory.setSdCard(sdCard);
		GsiBonus gsiBonus = new GsiBonus();
		gsiBonus.setOnDate("2011/10/31");
		OrderDetail oDetail1 = new OrderDetail();
		oDetail1.setProductionCategory(productionCategory);
		Set<OrderDetail> orderDetail = new HashSet();
		orderDetail.add(oDetail1);
		Order order = new Order();
		order.setOrderRid(p);
		order.setMember(member);
		order.setReceiver("路人乙");
		order.setReceiverTel("0934123456");
		order.setThreeBillNo("012345678");
		order.setPayMethod("信用卡刷卡");
		order.setShipAddress("堤頂大道32號");
		order.setShipCity("台北市");
		order.setShipRegion("大同區");
		order.setShipRegionNo("116");
		order.setPurchaseDate("2011/10/23 13:20:19");
		order.setOrderDetail(orderDetail);
		order.setPayStatus("已付款");
		order.setHandleStatus("處理中");
		order.setBillType("三聯式發票");
		order.setShipDate("2011/10/25");
		order.setBillShipDate("2011/10/25");
		order.setShipName("黑貓宅急便");
		order.setShipNo("000365899");
			
		mav.addObject("order",order);
		mav.addObject("tPrice",tPrice);
		mav.addObject("giftPrice",giftPrice);
		mav.addObject("gsiBonus",gsiBonus);
			
		//System.out.println("payStatus=="+payStatus);
		//System.out.println("handleStatus=="+handleStatus);
		//System.out.println("billStatus=="+billStatus);
		//System.out.println("memo1=="+memo1);
		//System.out.println("memo2=="+memo2);
		return mav;
	}
		
	//訂單管理-現金訂單管理-現金訂單明細-出貨登錄
	@RequestMapping("/shipRegister")
	public ModelAndView shipregister(String shipName, String orderRid) {
		ModelAndView mav = new ModelAndView("shipRegister");
					
		mav.addObject("orderRid",orderRid);
		mav.addObject("shipName",shipName);
		return mav;
	}
		
	//訂單管理-現金訂單管理-現金訂單明細-儲存出貨登錄
	@RequestMapping("/updateShipRegister")
	public String updateshipregister(String MOPEND, String shipNo, String billNo) {
							
		System.out.println("MOPEND=="+MOPEND);
		System.out.println("shipNo=="+shipNo);
		System.out.println("billNo=="+billNo);
		return "redirect:cashOrderDetail.do";
	}

	//訂單管理-GSiMoney訂單管理
	@RequestMapping("/manageGSiMoneyOrder")
	public ModelAndView gsimoneyorder(String userMail, String orderNumber, String handleStatus, String MOPEND, String MCLOSED) {
		ModelAndView mav = new ModelAndView("manageGSiMoneyOrder");
		System.out.println("userMail==>"+userMail);
		System.out.println("orderNumber==>"+orderNumber);
		System.out.println("handleStatus==>"+handleStatus);
		System.out.println("MOPEND==>"+MOPEND);
		System.out.println("MCLOSED==>"+MCLOSED);
		
		String tPrice = "160";
		long p = 12345678;
		Member member = new Member();
		member.setEmail("mail@mail.com");
		member.setIdentityName("樂團");
		long u =123;
		member.setMemberId(u);
		Album album = new Album();
		album.setName("狂想曲");
		Song song = new Song();
		song.setName("你");
		song.setAlbum(album);
		Album album2 = new Album();
		album2.setName("原來如此");
		Song song2 = new Song();
		song2.setName("");
		song2.setAlbum(album2);
		
		ProductionCategory productionCategory = new ProductionCategory();
		productionCategory.setSong(song);
		ProductionCategory productionCategory2 = new ProductionCategory();
		productionCategory2.setSong(song2);
		OrderDetail oDetail1 = new OrderDetail();
		oDetail1.setProductionCategory(productionCategory);
		OrderDetail oDetail2 = new OrderDetail();
		oDetail2.setProductionCategory(productionCategory2);
		Set<OrderDetail> orderDetail = new HashSet();
		orderDetail.add(oDetail1);
		orderDetail.add(oDetail2);
		Order order = new Order();
		order.setOrderRid(p);
		order.setMember(member);
		order.setPayMethod("信用卡刷卡");
		order.setPurchaseDate("2011/10/23 13:20:19");
		order.setOrderDetail(orderDetail);
		
		String tPrice2 = "220";
		long p2 = 45612378;
		Member member2 = new Member();
		member2.setEmail("mail2@mail.com");
		member2.setIdentityName("一般會員");
		long u2 =456;
		member2.setMemberId(u2);
		Album album3 = new Album();
		album3.setName("你好");
		ProductionCategory productionCategory3 = new ProductionCategory();
		productionCategory3.setAlbum(album3);
		OrderDetail oDetail3 = new OrderDetail();
		oDetail3.setProductionCategory(productionCategory3);
		Set<OrderDetail> orderDetail3 = new HashSet();
		orderDetail3.add(oDetail3);
		Order order2 = new Order();
		order2.setOrderRid(p2);
		order2.setMember(member2);
		order2.setPayMethod("ATM匯款");
		order2.setPurchaseDate("2011/10/24 15:20:19");
		order2.setOrderDetail(orderDetail3);
		order2.setPayStatus("已付款");
		order2.setHandleStatus("已儲值");
		order2.setMemo2("test");
		
		Order[] orders = {order,order2};
		mav.addObject("orders",orders);
		mav.addObject("tPrice",tPrice);
		mav.addObject("tPrice2",tPrice2);
		return mav;
	}
	
	//訂單管理-GSiMoney訂單管理-GSiMoney訂單明細
	@RequestMapping("/gsimoneyOrderDetail")
	public ModelAndView gsimoneyorderdetail() {
		ModelAndView mav = new ModelAndView("gsimoneyOrderDetail");
			
		String tPrice = "160";
		String price = "110";
		String giftPrice = "30";
		String giftPrice2 = "0";
		String hs1 = "未下載";
		String hs2 = "已下載";
		GsiBonus gsiBonus = new GsiBonus();
		gsiBonus.setOnDate("2011/10/31");
		GsiBonus gsiBonus2 = new GsiBonus();
		gsiBonus2.setOnDate("2011/11/21");
		
		long p = 12345678;
		
		SongPrice songPrice = new SongPrice();
		songPrice.setPrice("20");
		Album album = new Album();
		album.setName("狂想曲");
		Song song = new Song();
		song.setName("你");
		song.setAlbum(album);
		song.setSongPrice(songPrice);
		
		SongPrice songPrice2 = new SongPrice();
		songPrice2.setPrice("30");
		Album album2 = new Album();
		album2.setName("原來如此");
		Song song2 = new Song();
		song2.setName("HI");
		song2.setAlbum(album2);
		song2.setSongPrice(songPrice);
		ProductionCategory productionCategory = new ProductionCategory();
		productionCategory.setSong(song);
		ProductionCategory productionCategory2 = new ProductionCategory();
		productionCategory2.setSong(song2);
		OrderDetail oDetail1 = new OrderDetail();
		oDetail1.setProductionCategory(productionCategory);
		OrderDetail oDetail2 = new OrderDetail();
		oDetail2.setProductionCategory(productionCategory2);
		Set<OrderDetail> orderDetail = new HashSet();
		orderDetail.add(oDetail1);
		orderDetail.add(oDetail2);
		Order order = new Order();
		order.setOrderRid(p);
		order.setPurchaseDate("2011/10/23 13:20:19");
		
		ArrayList list = new ArrayList();     //明細第一筆
		list.add(oDetail1);
		list.add(giftPrice);
		list.add(hs1);
		list.add(gsiBonus);
		
		ArrayList list1 = new ArrayList();    //明細第二筆
		list1.add(oDetail2);
		list1.add(giftPrice2);
		list1.add(hs2);
		list1.add(gsiBonus2);
		
		ArrayList[]  list3 = {list, list1};
			
		mav.addObject("orders",list3);
		mav.addObject("order",order);
		mav.addObject("tPrice",tPrice);
		mav.addObject("price",price);
		mav.addObject("giftPrice",giftPrice);
		return mav;
	}
	
	//訂單管理-GSiMoney訂單管理-GSiMoney訂單備註
	@RequestMapping("/gsimoneyOrderNote")
	public ModelAndView gsimoneyordernote() {
		ModelAndView mav = new ModelAndView("gsimoneyOrderNote");
		return mav;
	}
	
	//訂單管理-GSiMoney訂單管理-儲存備註內容並關閉GSiMoney訂單備註的視窗
	@RequestMapping("/editClose")
	public ModelAndView editclose(String memo1, String memo2) {
		ModelAndView mav = new ModelAndView("editMusicCategoryClose");
		System.out.println("memo1==>"+memo1);
		System.out.println("memo2==>"+memo2);
		return mav;
	}
}