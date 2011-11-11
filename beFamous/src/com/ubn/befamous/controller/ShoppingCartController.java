package com.ubn.befamous.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.Creator;
import com.ubn.befamous.entity.Order;
import com.ubn.befamous.entity.PrePaidPrice;
import com.ubn.befamous.entity.SDCard;
import com.ubn.befamous.entity.SDCardPrice;
import com.ubn.befamous.entity.Song;
import com.ubn.befamous.entity.SongPrice;
import com.ubn.befamous.entity.PrePaid;

@Controller
public class ShoppingCartController {
	
	//儲值-購物車-步驟一
	@RequestMapping("/prepay")
	public ModelAndView prepay() {
		PrePaidPrice price = new PrePaidPrice();
		PrePaid prePaid = new PrePaid();
		prePaid.setName("儲值100");
		price.setpPrice("100");
		PrePaidPrice price2 = new PrePaidPrice();
		PrePaid prePaid2 = new PrePaid();
		prePaid2.setName("儲值300");
		price2.setpPrice("300");
		PrePaidPrice price3 = new PrePaidPrice();
		PrePaid prePaid3 = new PrePaid();
		prePaid3.setName("儲值500");
		price3.setpPrice("500");
		PrePaidPrice price4 = new PrePaidPrice();
		PrePaid prePaid4 = new PrePaid();
		prePaid4.setName("儲值800");
		price4.setpPrice("800");
		PrePaidPrice price5 = new PrePaidPrice();
		PrePaid prePaid5 = new PrePaid();
		prePaid5.setName("儲值1000");
		price5.setpPrice("1000");
		PrePaid[] pre = {prePaid,prePaid2,prePaid3,prePaid4,prePaid5};
		PrePaidPrice[] prIce = {price,price2,price3,price4,price5};
		ArrayList list = new ArrayList();
		list.add(pre);
		list.add(prIce);
		return new ModelAndView("prepay","prePay",list);
	}
	
	//儲值-購物車-步驟二
	@RequestMapping("/prepayTwo")
	public ModelAndView prepaytwo(String prePay, String agree1) {
		ModelAndView mav = new ModelAndView("prepayTwo");
		
		System.out.println("prePay==>"+prePay);
		System.out.println("agree==>"+agree1);
		
		mav.addObject("price",prePay);
		return mav;
	}
	
	//儲值-購物車-步驟三
	@RequestMapping("/prepayThree")
	public ModelAndView prepaythree(String pay, String price, String tel, String msg, String email, String city, String region, String number, String address, String time, String billData, String discountBonus, String sdName) {
		ModelAndView mav = new ModelAndView("prepayThree");
		mav.addObject("pay",pay);
		mav.addObject("price",price);
		mav.addObject("tel",tel);
		mav.addObject("msg",msg);
		mav.addObject("email",email);
		mav.addObject("city",city);
		mav.addObject("region",region);
		mav.addObject("number",number);
		mav.addObject("address",address);
		mav.addObject("time",time);
		mav.addObject("billData",billData);
		mav.addObject("discountBonus",discountBonus);
		mav.addObject("sdName",sdName);
		return mav;
	}
	
	//儲值和SD卡-購物車-信用卡付款頁
	@RequestMapping("/cardPay")
	public ModelAndView cardpay(@RequestParam String discountBonus) {
		ModelAndView mav = new ModelAndView("cardPay");
		mav.addObject("discountBonus",discountBonus);
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
	public ModelAndView prepayfinal(@RequestParam String discountBonus) {
		ModelAndView mav = new ModelAndView("prepayFinal");
		Order order = new Order();
		long orderNo = 1109211303;
		order.setOrderRid(orderNo);
		mav.addObject("orDer",order);
		mav.addObject("discountBonus",discountBonus);
		return mav;
	}
	
	//SD卡-購物車-步驟一
	@RequestMapping("/sdCard") 
	public ModelAndView sdcard() {
		SDCard sdCard = new SDCard();
		SDCardPrice cardPrice = new SDCardPrice();
		sdCard.setName("1G GSiSD卡");
		cardPrice.setpPrice("299");
		cardPrice.setDiscountPrice("249");
		cardPrice.setDiscountBonus("50");
		SDCard sdCard2 = new SDCard();
		SDCardPrice cardPrice2 = new SDCardPrice();
		sdCard2.setName("2G GSiSD卡");
		cardPrice2.setpPrice("399");
		cardPrice2.setDiscountPrice("349");
		cardPrice2.setDiscountBonus("50");
		SDCard sdCard3 = new SDCard();
		SDCardPrice cardPrice3 = new SDCardPrice();
		sdCard3.setName("4G GSiSD卡");
		cardPrice3.setpPrice("599");
		cardPrice3.setDiscountPrice("549");
		cardPrice3.setDiscountBonus("50");
		SDCard[] sd = {sdCard,sdCard2,sdCard3};
		SDCardPrice[] cp = {cardPrice,cardPrice2,cardPrice3};
		ArrayList list = new ArrayList();
		list.add(sd);
		list.add(cp);
		return new ModelAndView("sdCard","sdCard",list);
	}
	
	//SD卡-購物車-步驟二
	@RequestMapping("/sdcardTwo")
	public ModelAndView sdcardtwo(@RequestParam String sdprice, String sdmsg, String discountBonus, String sdName) {
		ModelAndView mav = new ModelAndView("prepayTwo");
		mav.addObject("price",sdprice);
		mav.addObject("msg",sdmsg);
		mav.addObject("discountBonus",discountBonus);
		mav.addObject("sdName",sdName);
		return mav;
	}
	
	//音樂-購物車-步驟一
	@RequestMapping("/shoppingCartOne")
	public ModelAndView shoppingcartone() {
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
		return new ModelAndView("shoppingCartOne","albumSong",list);
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