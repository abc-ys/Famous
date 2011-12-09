package com.ubn.befamous.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.Creator;
import com.ubn.befamous.entity.Member;
import com.ubn.befamous.entity.Order;
import com.ubn.befamous.entity.PrePaidPrice;
import com.ubn.befamous.entity.SDCard;
import com.ubn.befamous.entity.SDCardPrice;
import com.ubn.befamous.entity.ShoppingCart;
import com.ubn.befamous.entity.ShoppingCartDetail;
import com.ubn.befamous.entity.Song;
import com.ubn.befamous.entity.SongPrice;
import com.ubn.befamous.entity.PrePaid;
import com.ubn.befamous.service.TransactionRecordService;

@Controller
@SessionAttributes
public class ShoppingCartController {
	
	@Autowired
	private TransactionRecordService transactionRecordService;
	
	//儲值-購物車-步驟一
	@RequestMapping("/prepay")
	public ModelAndView prepay() {
		return new ModelAndView("prepay", "prePay",this.transactionRecordService.queryPrePaid());
	}
	
	//儲值-購物車-步驟二
	@RequestMapping("/prepayTwo")
	public ModelAndView prepaytwo(String prePay, String agree1,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("prepayTwo");
		String[] prePaid = prePay.split(";");
		System.out.println("prePaid[0]==>"+prePaid[0]);
		ArrayList list = transactionRecordService.queryProductDetail(Long.parseLong(prePaid[0]));
		PrePaid paid = (PrePaid)list.get(0);
		request.getSession().setAttribute("PRE_PAID", paid);
		System.out.println("paid1111111==>"+paid);
		//this.transactionRecordService.purchaseConfirmShoppingCart(userId, shoppingCart);
		
		mav.addObject("price",prePay);
		return mav;
	}
	
	//儲值-購物車-步驟三
	@RequestMapping("/prepayThree")
	public ModelAndView prepaythree(String pay, String price, Member member,
			String time, String billData, String discountBonus,
			String sdName, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("prepayThree");
		System.out.println("pay==>"+pay);
		long userID=1;
		//long userID = (Long)request.getSession().getAttribute("userID");  從session取得userID
		Member memberUser = transactionRecordService.addMemberData(userID,member);
		
		PrePaid paid = (PrePaid)request.getSession().getAttribute("PRE_PAID");
		System.out.println("paid222222==>"+paid);
		
		mav.addObject("sdName",sdName);
		mav.addObject("PrePaid",paid);
		mav.addObject("pay",pay);
		mav.addObject("memberUser",memberUser);
		return mav;
	}
	
	//儲值和SD卡-購物車-信用卡付款頁
	@RequestMapping("/cardPay")
	public ModelAndView cardpay(@RequestParam String discountBonus,HttpServletRequest request,
			Order order,String amount,String productType) {
		ModelAndView mav = new ModelAndView("cardPay");
		
		request.getSession().setAttribute("ORDER", order);
		mav.addObject("discountBonus",discountBonus);
		mav.addObject("amount",amount);
		mav.addObject("productType",productType);
		return mav;
	}
	
	//儲值和SD卡-購物車-ATM轉帳頁
	@RequestMapping("/ATM")
	public ModelAndView atm() {
		ModelAndView mav = new ModelAndView("ATM");
		return mav;
	}
	
	//儲值和SD卡-購物車-完成結帳頁
	@RequestMapping("/prepayFinal")
	public ModelAndView prepayfinal(@RequestParam String discountBonus,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("prepayFinal");
		//Order order = new Order();
		//long orderNo = 1109211303;
		//order.setId(orderNo);
		//mav.addObject("orDer",order);
		PrePaid paid = (PrePaid)request.getSession().getAttribute("PRE_PAID");
		Order order = (Order)request.getSession().getAttribute("ORDER");
		order = transactionRecordService.purchasePrepaid(paid,order);
		mav.addObject("discountBonus",discountBonus);
		mav.addObject("order",order);
		return mav;
	}
	
	//SD卡-購物車-步驟一
	@RequestMapping("/sdCard") 
	public ModelAndView sdcard() {
		return new ModelAndView("sdCard", "sdCard",this.transactionRecordService.querySDCard());	
	}
	
	//SD卡-購物車-步驟二
	@RequestMapping("/sdCardTwo")
	public ModelAndView sdCardTwo(HttpServletRequest request,@RequestParam long userId, String price, String amount, Model model) {
		System.out.println("sdCardTwo====>");
		String []splits = price.split("_");
		long productId =Long.valueOf(splits[0]);
		String priceType = splits[1];
		System.out.println("	productId="+productId+", priceType"+priceType);
		//加入購物車
		//ArrayList list = this.transactionRecordService.addShoppingCart(userId, productId, amount);
		ArrayList list = transactionRecordService.queryProductDetail(productId);
		SDCard sdCard = (SDCard)list.get(0);
		request.getSession().setAttribute("SDCARD", sdCard);
		
		//Member member = (Member) list.get(0);
		ArrayList list2 = new ArrayList();
		Member member = transactionRecordService.queryMember(userId);
		
		//ShoppingCart shoppingCart = (ShoppingCart) list.get(1);
		model.addAttribute("priceType", priceType);
		model.addAttribute("member", member);
		model.addAttribute("amount", amount);
		//model.addAttribute("SDCard", sdCard);
		//model.addAttribute("shoppingCart", shoppingCart);
		return new ModelAndView("sdCardTwo");
	}
	
	//SD卡-購物車-步驟三
		@RequestMapping("/sdCardThree")
		public ModelAndView sdCardThree(String amount,String pay, String price, Member member,
				String time, String billData, String discountBonus,
				String sdName, HttpServletRequest request) {
			ModelAndView mav = new ModelAndView("sdCardThree");
			System.out.println("pay==>"+pay);
			long userID=1;
			//long userID = (Long)request.getSession().getAttribute("userID");  從session取得userID
			Member memberUser = transactionRecordService.addMemberData(userID,member);
			
			SDCard sdCard = (SDCard)request.getSession().getAttribute("SDCARD");
			System.out.println("sdCard222222==>"+sdCard);
			
			mav.addObject("sdName",sdName);
			mav.addObject("SDCard",sdCard);
			mav.addObject("pay",pay);
			mav.addObject("amount",amount);
			mav.addObject("memberUser",memberUser);
			return mav;
		}
	
		//SD卡-購物車-完成結帳頁
		@RequestMapping("/sdCardFinal")
		public ModelAndView sdCardFinal(@RequestParam String discountBonus,HttpServletRequest request,String amount) {
			ModelAndView mav = new ModelAndView("prepayFinal");
			//Order order = new Order();
			//long orderNo = 1109211303;
			//order.setId(orderNo);
			//mav.addObject("orDer",order);
			SDCard sdCard = (SDCard)request.getSession().getAttribute("SDCARD");
			Order order = (Order)request.getSession().getAttribute("ORDER");
			order = transactionRecordService.purchaseSDCard(sdCard,order,amount);
			
			mav.addObject("discountBonus",discountBonus);
			mav.addObject("order",order);
			return mav;
		}
		
		
		
	//音樂-購物車-步驟一
	@RequestMapping("/shoppingCartOne")
	public ModelAndView shoppingcartone() {
		/*
		String albumPrice = "300";
		String albumDiscountPrice = "250";
		String albumDiscountBonus = "50";
		Creator creator = new Creator();
		creator.setUserName("盧廣仲");
		Album album = new Album();
		album.setCover("images/album.jpg");
		album.setName("慢靈魂");
		album.setCreator(creator);
		Creator creator2 = new Creator();
		creator2.setUserName("蕭敬騰");
		Album album2 = new Album();
		album2.setCreator(creator2);
		album2.setName("狂想曲");
		Song song = new Song();
		song.setName("敷衍");
		SongPrice sp = new SongPrice();
		sp.setPrice("100");
		sp.setDiscountPrice("80");
		sp.setDiscountBonus("20");
		song.setSongPrice(sp);
		song.setAlbum(album2);
		Album[] alBum = {album};
		Song[] soNg = {song};
		ArrayList list = new ArrayList();
		list.add(alBum);
		list.add(soNg);
		list.add(albumPrice);
		list.add(albumDiscountPrice);
		list.add(albumDiscountBonus);
		*/
		long userID = 2;
		ShoppingCartDetail[] arDetail = transactionRecordService.queryMusicShoppingCart(userID);
		
		return new ModelAndView("shoppingCartOne","albumSong",arDetail);
	}
	
	//音樂-購物車-步驟二
	@RequestMapping("/shoppingCartTwo")
	public ModelAndView shoppingcarttwo(@RequestParam String cover, String aName, String aUserName, String aprice, String sName, String aName2, String sUserName, String sprice, String tPrice, String tBonus) {
		ModelAndView mav = new ModelAndView("shoppingCartTwo");
		mav.addObject("cover",cover);
		mav.addObject("aName",aName);
		mav.addObject("aUserName",aUserName);
		mav.addObject("aprice",aprice);
		mav.addObject("sName",sName);
		mav.addObject("aName2",aName2);
		mav.addObject("sUserName",sUserName);
		mav.addObject("sprice",sprice);
		mav.addObject("tPrice",tPrice);
		mav.addObject("tBonus",tBonus);
		System.out.println(tPrice);
		System.out.println(tBonus);
		return mav;
	}
	
	//音樂-購物車-步驟三
	@RequestMapping("/shoppingCartThree")
	public ModelAndView shoppingcartthree(@RequestParam  String tBonus) {
		ModelAndView mav = new ModelAndView("shoppingCartThree");
		mav.addObject("tBonus",tBonus);
		System.out.println("2"+tBonus);
		return mav;
	}
	
	//臨時購物車
	@RequestMapping("/tempShoppingCart")
	public ModelAndView tempshoppingcart() {
		String albumPrice = "300";
		Creator creator = new Creator();
		creator.setUserName("盧廣仲");
		Album album = new Album();
		album.setName("慢靈魂");
		album.setCreator(creator);
		Creator creator2 = new Creator();
		creator2.setUserName("蕭敬騰");
		Album album2 = new Album();
		album2.setCreator(creator2);
		Song song = new Song();
		SongPrice sp = new SongPrice();
		sp.setDiscountPrice("80");
		sp.setDiscountBonus("20");
		song.setSongPrice(sp);
		song.setName("狂想曲");
		String tPrice="380";
		String tBonus="20";
		ArrayList list = new ArrayList();
		list.add(album);
		list.add(song);
		list.add(albumPrice);
		list.add(tPrice);
		list.add(tBonus);
		return new ModelAndView("tempShoppingCart","albumSong",list);
	}
}