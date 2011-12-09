package com.ubn.befamous.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.Creator;
import com.ubn.befamous.entity.DownloadList;
import com.ubn.befamous.entity.GsiMoney;
import com.ubn.befamous.entity.Hidden;
import com.ubn.befamous.entity.Member;
import com.ubn.befamous.entity.Offense;
import com.ubn.befamous.entity.OffenseType;
import com.ubn.befamous.entity.Order;
import com.ubn.befamous.entity.OrderDetail;
import com.ubn.befamous.entity.PrePaid;
import com.ubn.befamous.entity.ProductionCategory;
import com.ubn.befamous.entity.ProductionClassification;
import com.ubn.befamous.entity.SDCard;
import com.ubn.befamous.entity.ShoppingCart;
import com.ubn.befamous.entity.ShoppingCartDetail;

public interface TransactionRecordService{
	
	//SD卡與儲值service
		public SDCard[] querySDCard();
		public PrePaid[] queryPrePaid();
		public ArrayList addShoppingCart(long userId, long productId, String amount);
		public ArrayList purchaseConfirmShoppingCart(long userId, ShoppingCart shoppingCart);
		public Order addOrder(long shoppingCartId);
		
		//購物車service
		public ShoppingCartDetail[] queryShoppingCart(long userId);
		public ShoppingCartDetail[] forwardPurchase(long userId);
		
		
		
		//訂單管理-實體幣訂單-搜尋實體幣訂單
		public Order[] queryCashTransRcd(String userEmail, String orderId, String startDate, String endDate, String handleStatus);
		
		//訂單管理-現金訂單管理-現金訂單明細-儲存出貨登錄
		public void updateShipRegister(long adminId, long orderId, String shipDate, String shipNo, String billNo);
		
		//訂單管理-實體幣訂單-儲存處理實體幣訂單結果
		public void saveCashTransRcd(long adminId, long orderId, String billStatus, String payStatus, String handleStatus, String memo1, String memo2);
		
		//訂單管理-虛擬幣訂單-搜尋虛擬幣訂單(起始搜尋)
		public Order[] queryFirstInventTransRcd(String year, String month);		
		
		//訂單管理-虛擬幣訂單-搜尋虛擬幣訂單(根據搜尋條件)
		public Order[] queryInventTransRcd(String userEmail, String orderId, String startDate, String endDate);
		
		//訂單管理-虛擬幣訂單-搜尋訂單MEMO
		public ArrayList queryInventMemo(long orderId);
		
		//訂單管理-虛擬幣訂單-儲存訂單MEMO
		public void updateInventMemo(long adminId,long orderId, String memo1, String memo2);

		//發票登錄-兌換金額管理(起始查詢)
		public GsiMoney[] queryFirstExchangeList();
		
		//發票登錄-兌換金額管理(根據查詢條件)
		public GsiMoney[] queryExchangeList(String year, String month);
		
		//發票登錄-儲存兌換金額訂單明細
		public void updateExchange(long adminId, long gsiMoneyId,String exchangeTax, String paid, String exchangeStatus, String paidDate, String memo);
		
		//發票登錄-訂單發票登錄
		public Order[] queryBillRcd(String year, String month);
		
		//發票登錄-儲存訂單明細
		public void updateBill(long adminId, long orderId, String billNo, String memo2, String payStatus);

		//Lucy@20111125
		//我的帳本-查看收支表(明細)
		public Order[] queryGsiMoney(long userId, String year, String month);
		
		//我的帳本-查看收支表(單筆)
		public List<GsiMoney> queryOneGsiMoney(long userId, String year, String month);
		
		//我的帳本-查看收支表(這個月的兌換記錄)
		public List<GsiMoney> queryOneExchangeRcd(long userId, String year, String month);
		
		//根據訂單編號找尋訂單
		public Order queryTransRcd(long orderId);
		
		//我的帳本-儲存兌換申請以及創作者帳戶資料
		public void saveChange(Creator creator, int money, String synUpdate);
		
		//我的帳本-兌換記錄(起始查詢六個月內)
		public GsiMoney[] queryFirstExchangeLog(long userId, String lastDate, String nowDate);
		
		//我的帳本-兌換記錄(查詢條件)
		public GsiMoney[] queryExchangeLog(long userId, String year, String month);
		
		//我的帳本-儲值記錄
		public Order[] queryPrePaidRcd(long userId, String year, String month);
		
		//我的帳本-GSiMoney消費記錄
		public List<Order> queryBuyLogRcd(long userId, String year, String month);
		
		//我的帳本-購買記錄明細
		public List<Object[]> queryBuyLogDetail(long transactionId);
		
		//我的帳本-銷售紀錄
		public List<Object[]> querySaleRcd(long userId, String year, String month);
		
		//我的帳本-贈送Bonus明細
		public ArrayList queryBonusDetail(long userId);
		
		//我的帳本-現金消費記錄
		public Order[] queryCashRcd(long userId,String choice, String orderId);
		
		//我的帳本-取消現金訂單
		public void cancelGoods(long orderId);
		
		//Lucy@20111124	
		//檢舉管理-新增檢舉介面
		public ArrayList queryOffenseCategory(long productionCategoryId);
		
		//檢舉管理-儲存檢舉內容
		public void addOffense(long userId, long productionCategoryId, long offenseTypeId, String reason);
		
		//檢舉管理-檢視被檢舉的專輯和歌曲
		public ArrayList queryUnHandle();
		
		//檢舉管理-隱藏專輯
		public void updateAlbumHide(long adminId, long albumId);
		
		//檢舉管理-隱藏歌曲
		public void updateSongHide(long adminId, long songId);
		
		//檢舉管理-查詢檢舉理由 
		public Offense[] queryOffenseReason(long productionCategoryId);
		
		//檢舉管理-更新檢舉狀態(不正當檢舉)
		public void updateIncorrectOffense(long adminId, long offenseId);
		
		//檢舉管理-查詢被系統自動隱藏的專輯/歌曲(起始查詢)
		public ArrayList queryAutoHide();
		
		//檢舉管理-查詢被系統自動隱藏的專輯/歌曲(根據查詢條件)
		public ArrayList queryAutoHideByDate(String type,String year, String month, String creator);
		
		//檢舉管理-取消隱藏狀態 
		public void cancelHide(long adminId, long hiddenId);
		
		//檢舉管理-確認隱藏狀態
		public void comfirmHide(long adminId, long hiddenId);
		
		//檢舉管理-查詢已被隱藏的專輯(起始查詢)
		public ArrayList queryAlreadyHide();
		
		//檢舉管理-查詢已被隱藏的專輯/歌曲(根據查詢條件)
		public ArrayList queryAlreadyHideByDate(String type,String year, String month, String creator);
		
		//檢舉管理-查詢會員檢舉清單 
		public Offense[] queryOffenseByUser(long userId);
		
		//會員詳細資料頁-該會員被檢舉的專輯清單
		public ArrayList queryOffenseAlbumForUser(long userId);
		
		//會員詳細資料頁-該會員被檢舉的歌曲清單
		public ArrayList queryOffenseSongForUser(long userId);
		
		
		//商品管理-查詢所有商品類別 
		public ProductionClassification[] queryProductionClassification();
		
		//商品管理-查詢單一商品類別 
		public ProductionClassification queryOneProductionClassification(long productionClassificationId);
		
		//商品管理-新增商品類別
		public void addProductionClassification(long adminId, String productionClassificationName);
		
		//商品管理-編輯商品類別
		public void editProductionClassification(long adminId, long productionClassificationId, String productionClassificationName);
		
		//商品管理-刪除商品類別
		public ProductionClassification[] deleteProductionClassification(long adminId, long roductionClassificationId);
		
		//商品管理-查詢商品
		public ArrayList queryProduct();
		
		//商品管理-查詢商品資訊
		public ArrayList queryProductInfo(long roductionClassificationId);
		
		//商品管理-批次更新商品
		public void updateProductBatch(long adminId, long productionClassificationId, String condition, double rate);
		
		//商品管理-查詢商品細節
		public ArrayList queryProductDetail(long productId);
				
		//商品管理-更新商品資訊(查詢商品細節頁)
		public long updateProduct(long adminId, long productId, String productName,long newProductionClassificationId, String transactionType,String realPrice,String specialPrice, String discountPrice, String discountBonus, String giveBonus, String stock, String status, String keyword, String memo, long productionClassificationId);
		
		
		//kevin
		public Member addMemberData(long userID,Member member);
		//儲值轉訂單
		public Order purchasePrepaid(long userID,PrePaid prePaid,Order order);
		//SD卡轉訂單
		public Order purchaseSDCard(SDCard adCard,Order order,String amount);
		public Member queryMember(long userID);
		public ArrayList queryMusicShoppingCart(long userId);
		public ArrayList addMusicShoppingCart(long userId, long productId,String useBonus);
		public int purchaseConfirm(long shoppingCartId, String gsiMoney, String gsiBonus);
		public ArrayList updateUseBonus(long userID,long id,String useBonus);
		public ArrayList deleteShoppingCart(long userId,long transactionId);
}