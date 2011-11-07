package com.ubn.befamous.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.GsiMoney;
import com.ubn.befamous.entity.Order;
import com.ubn.befamous.entity.OrderDetail;
import com.ubn.befamous.entity.ProductionCategory;
import com.ubn.befamous.entity.SDCard;
import com.ubn.befamous.entity.SDCardPrice;

@Controller
@SessionAttributes
public class GsiMoneyBonusController {
	
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

	//查詢詳細現金消費記錄(怡秀部分)
	@RequestMapping("/cashPayDetail")
	public ModelAndView cashpaydetail() {
		System.out.println("cashPayDetail------");
		ModelAndView mav = new ModelAndView("cashpaydetail");
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
}
