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

import com.ubn.befamous.constant.ConfingConstants;
import com.ubn.befamous.constant.SessionAttribute;
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
import com.ubn.befamous.util.CheckPriceUtil;

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
		//long userID=1;
		long userID = (Long)request.getSession().getAttribute(SessionAttribute.USER_ID);  //從session取得userID
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
		//long userID=1;
		long userID = (Long)request.getSession().getAttribute(SessionAttribute.USER_ID);  //從session取得userID
		PrePaid paid = (PrePaid)request.getSession().getAttribute("PRE_PAID");
		Order order = (Order)request.getSession().getAttribute("ORDER");
		order = transactionRecordService.purchasePrepaid(userID,paid,order);
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
	public ModelAndView sdCardTwo(HttpServletRequest request, String price, String amount, Model model) {
		System.out.println("sdCardTwo====>");
		String []splits = price.split("_");
		long productId =Long.valueOf(splits[0]);
		String priceType = splits[1];
		System.out.println("	productId="+productId+", priceType"+priceType);
		//加入購物車
		//ArrayList list = this.transactionRecordService.addShoppingCart(userId, productId, amount);
		ArrayList list = transactionRecordService.queryProductDetail(productId);
		SDCard sdCard = (SDCard)list.get(0);
		request.getSession().setAttribute(SessionAttribute.SDCARD, sdCard);
		
		long userID = (Long)request.getSession().getAttribute(SessionAttribute.USER_ID);  //從session取得userID
		System.out.println("userID====>"+userID);
		Member member = transactionRecordService.queryMember(userID);
		
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
		public ModelAndView sdCardThree(String priceType,String amount,String pay, String price, Member member,
				String discountBonus,String sdName, HttpServletRequest request,Order order) {
			ModelAndView mav = new ModelAndView("sdCardThree");
			System.out.println("pay==>"+pay);
			System.out.println("order.getBillNo()==>"+order.getBillNo());
			
			//long userID=1;
			long userID = (Long)request.getSession().getAttribute(SessionAttribute.USER_ID);  //從session取得userID
			Member memberUser = transactionRecordService.addMemberData(userID,member);
			
			SDCard sdCard = (SDCard)request.getSession().getAttribute(SessionAttribute.SDCARD);
			System.out.println("sdCard222222==>"+sdCard);
			request.getSession().setAttribute(SessionAttribute.ORDER, order);
			//sdCard.getSdCardPrice().g
			
			int totalPrice = 0;
			int totalBonus = 0;
			int shipPrice = 0;
			String sdPrice = "";
			String sdBonus = "";
			if("1".equals(priceType)){ //一般售價
				sdPrice = sdCard.getSdCardPrice().getPrice();
				totalPrice = Integer.valueOf(sdPrice) * Integer.valueOf(amount);
			}else{ //優惠價+Bonus
				sdPrice = sdCard.getSdCardPrice().getDiscountPrice();
				sdBonus = sdCard.getSdCardPrice().getDiscountBonus();
				totalPrice = Integer.valueOf(sdPrice) * Integer.valueOf(amount);
				totalBonus = Integer.valueOf(sdBonus) * Integer.valueOf(amount);
			}
			
			//購買價格低於600,需加運費50元
			if(totalPrice < ConfingConstants.LEAST_PRICE){
				shipPrice = ConfingConstants.SHIP_PRICE;
			}
			
			mav.addObject("sdName",sdName);
			mav.addObject("SDCard",sdCard);
			mav.addObject("pay",pay);
			mav.addObject("sdPrice",sdPrice);
			mav.addObject("sdBonus",sdBonus);
			mav.addObject("totalPrice",totalPrice);
			mav.addObject("totalBonus",totalBonus);
			mav.addObject("shipPrice",shipPrice);
			mav.addObject("amount",amount);
			mav.addObject("memberUser",memberUser);
			return mav;
		}
	
		//SD卡-購物車-完成結帳頁
		@RequestMapping("/sdCardFinal")
		public ModelAndView sdCardFinal(@RequestParam String discountBonus,HttpServletRequest request,String amount) {
			ModelAndView mav = new ModelAndView("prepayFinal");

			//long userID=1;
			long userID = (Long)request.getSession().getAttribute(SessionAttribute.USER_ID);  //從session取得userID
			
			SDCard sdCard = (SDCard)request.getSession().getAttribute(SessionAttribute.SDCARD);
			Order order = (Order)request.getSession().getAttribute(SessionAttribute.ORDER);
			order = transactionRecordService.purchaseSDCard(sdCard,order,amount);
			
			mav.addObject("discountBonus",discountBonus);
			mav.addObject("order",order);
			return mav;
		}
		
		
		
	//音樂-購物車-步驟一
	@RequestMapping("/shoppingCartOne")
	public ModelAndView shoppingcartone(HttpServletRequest request) {
		
		//long userID = 4;
		long userID = (Long)request.getSession().getAttribute(SessionAttribute.USER_ID);  //從session取得userID
		ArrayList list = transactionRecordService.queryMusicShoppingCart(userID);
		
		System.out.println("list=====>"+list);
		ShoppingCartDetail[] songDetail = (ShoppingCartDetail[])list.get(0);
		ShoppingCartDetail[] albumDetail = (ShoppingCartDetail[])list.get(1);
		ArrayList<Integer> listPrice = CheckPriceUtil.checkPrice(songDetail, albumDetail);
		/*int tPrice = 0;
		int tBonus = 0;
		for(ShoppingCartDetail d : songDetail){
			if(d.getProductionCategory() instanceof Song){
				Song s = (Song)d.getProductionCategory();
				String price = "0";
				String bonus = "0";
				if("N".equals(d.getUseBonus())){
				      price = s.getSongPrice().getPrice();
				}else{
					  price = s.getSongPrice().getDiscountPrice();
					  bonus = s.getSongPrice().getDiscountBonus();
				}
				tPrice += Integer.parseInt(price);
				tBonus += Integer.parseInt(bonus);
			}else{
				
			}
			
		}*/
		ModelAndView mav = new ModelAndView("shoppingCartOne");
		mav.addObject("tPrice",listPrice.get(0));
		mav.addObject("tBonus",listPrice.get(1));
		mav.addObject("tAlbumPrice",listPrice.get(2));
		mav.addObject("tAlbumBonus",listPrice.get(3));
		mav.addObject("SongDetail",songDetail);
		mav.addObject("AlbumDetail",albumDetail);
		return mav;
	}
	
	//音樂-購物車-選擇定價或用Bonus折抵
	@RequestMapping("/shoppingCartChangePrice")
	public ModelAndView shoppingCartChangePrice(HttpServletRequest request,String useBonus,long id){
		
		ModelAndView mav = new ModelAndView("shoppingCartChangePrice");
		//long userID = 4;
		long userID = (Long)request.getSession().getAttribute(SessionAttribute.USER_ID);  //從session取得userID
		ArrayList list = transactionRecordService.updateUseBonus(userID,id, useBonus);
		ShoppingCartDetail[] songDetail = (ShoppingCartDetail[])list.get(0);
		ShoppingCartDetail[] albumDetail = (ShoppingCartDetail[])list.get(1);
		ArrayList<Integer> listPrice = CheckPriceUtil.checkPrice(songDetail, albumDetail);
		/*
		int tPrice = 0;
		int tBonus = 0;
		
		for(ShoppingCartDetail d : songDetail){
			
			if(d.getProductionCategory() instanceof Song){
				Song s = (Song)d.getProductionCategory();
				String price = "0";
				String bonus = "0";
				if("N".equals(d.getUseBonus())){
				      price = s.getSongPrice().getPrice();
				}else{
					  price = s.getSongPrice().getDiscountPrice();
					  bonus = s.getSongPrice().getDiscountBonus();
				}
				tPrice += Integer.parseInt(price);
				tBonus += Integer.parseInt(bonus);
			}
			
		}*/
		mav.addObject("tPrice",listPrice.get(0));
		mav.addObject("tBonus",listPrice.get(1));
		mav.addObject("tAlbumPrice",listPrice.get(2));
		mav.addObject("tAlbumBonus",listPrice.get(3));
		mav.addObject("SongDetail",songDetail);
		mav.addObject("AlbumDetail",albumDetail);
		return mav;

	}
	
	//刪除購物車品項
	@RequestMapping("/deleteShoppingCart")
	public ModelAndView deleteShoppingCart(HttpServletRequest request,long id){
		
		ModelAndView mav = new ModelAndView("shoppingCartChangePrice");
		
		long userID = (Long)request.getSession().getAttribute(SessionAttribute.USER_ID);  //從session取得userID
		System.out.println("deleteuserID====>"+userID);
		System.out.println("deleteid====>"+id);
		ArrayList list = transactionRecordService.deleteShoppingCart(userID, id);
		ShoppingCartDetail[] songDetail = (ShoppingCartDetail[])list.get(0);
		ShoppingCartDetail[] albumDetail = (ShoppingCartDetail[])list.get(1);
		ArrayList<Integer> listPrice = CheckPriceUtil.checkPrice(songDetail, albumDetail);
		/*
		int tPrice = 0;
		int tBonus = 0;
		
		for(ShoppingCartDetail d : songDetail){
			
			if(d.getProductionCategory() instanceof Song){
				Song s = (Song)d.getProductionCategory();
				String price = "0";
				String bonus = "0";
				if("N".equals(d.getUseBonus())){
				      price = s.getSongPrice().getPrice();
				}else{
					  price = s.getSongPrice().getDiscountPrice();
					  bonus = s.getSongPrice().getDiscountBonus();
				}
				tPrice += Integer.parseInt(price);
				tBonus += Integer.parseInt(bonus);
			}
			
		}*/
		mav.addObject("tPrice",listPrice.get(0));
		mav.addObject("tBonus",listPrice.get(1));
		mav.addObject("tAlbumPrice",listPrice.get(2));
		mav.addObject("tAlbumBonus",listPrice.get(3));
		mav.addObject("SongDetail",songDetail);
		mav.addObject("AlbumDetail",albumDetail);
		return mav;
	}
	
	//音樂-購物車-步驟二
	@RequestMapping("/shoppingCartTwo")
	public ModelAndView shoppingcarttwo(HttpServletRequest request,String sName, String aName2, String sUserName, String sprice) {
		ModelAndView mav = new ModelAndView("shoppingCartTwo");
	
		//long userID = 4;
		long userID = (Long)request.getSession().getAttribute(SessionAttribute.USER_ID);  //從session取得userID
		ArrayList list = transactionRecordService.queryMusicShoppingCart(userID);
		ShoppingCartDetail[] songDetail = (ShoppingCartDetail[])list.get(0);
		ShoppingCartDetail[] albumDetail = (ShoppingCartDetail[])list.get(1);
		ArrayList<Integer> listPrice = CheckPriceUtil.checkPrice(songDetail, albumDetail);
		if(songDetail.length>0){	
			mav.addObject("shoppingCartId",songDetail[0].getShoppingCart().getId());
		}
		
		/*
		int tPrice = 0;
		int tBonus = 0;
		
		for(ShoppingCartDetail d : songDetail){
			
			if(d.getProductionCategory() instanceof Song){
				Song s = (Song)d.getProductionCategory();
				String price = "0";
				String bonus = "0";
				if("N".equals(d.getUseBonus())){
				      price = s.getSongPrice().getPrice();
				}else{
					  price = s.getSongPrice().getDiscountPrice();
					  bonus = s.getSongPrice().getDiscountBonus();
				}
				tPrice += Integer.parseInt(price);
				tBonus += Integer.parseInt(bonus);
			}
			
		}*/
		mav.addObject("tPrice",listPrice.get(0));
		mav.addObject("tBonus",listPrice.get(1));
		mav.addObject("tAlbumPrice",listPrice.get(2));
		mav.addObject("tAlbumBonus",listPrice.get(3));
		mav.addObject("SongDetail",songDetail);
		mav.addObject("AlbumDetail",albumDetail);
		return mav;
	}
	
	//音樂-購物車-步驟三
	@RequestMapping("/shoppingCartThree")
	public ModelAndView shoppingcartthree(@RequestParam  String totalBonus,long shoppingCartId,String totalPrice) {
		ModelAndView mav = new ModelAndView("shoppingCartThree");
		int tReward = transactionRecordService.purchaseConfirm(shoppingCartId,totalPrice,totalBonus);
		mav.addObject("tReward",tReward);
		System.out.println("2"+tReward);
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
	
	//加入購物車(音樂)
	@RequestMapping("/addMusicShoppingCart")
	public String addMusicShoppingCart(HttpServletRequest request, long productId,String useBonus) {
		//long userId=4; 
		long userID = (Long)request.getSession().getAttribute(SessionAttribute.USER_ID);  //從session取得userID
		transactionRecordService.addMusicShoppingCart(userID,productId,useBonus);
		return "redirect:shoppingCartOne.do";
	}
	
	
}