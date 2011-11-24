package com.ubn.befamous.service;

import java.io.File;
import java.util.ArrayList;

import com.ubn.befamous.entity.Album;
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
	public ShoppingCartDetail[] deleteShoppingCart(long userId,long transactionId);
	public ShoppingCartDetail[] forwardPurchase(long userId);
	public void purchaseConfirm(long shoppingCartId, String gsiMoney, String gsiBonus);
	
	//訂單管理service-實體幣訂單
	public Order[] queryCashTransRcd(String userEmail, long transactionId, String startDate, String endDate, String orderStatus);
	public ArrayList queryCashTransDetail(long transactionId);
	public boolean saveCashTransRcd(long transactionId,String billStatus, String payStatus, String processStatus, String memo1, String memo2);
	public Order queryDeliveryRcd(long transactionId);
	public ArrayList updateDeliveryRcd(long transactionId, String deliveryDate, String deliveryNo, String billNo);
	public ArrayList returnGoods(long transactionId);
	public ArrayList cancelOrder(long transactionId);
	
	//訂單管理service-虛擬幣訂單
	public Order[] queryInventTransRcd(String userEmail, long transactionId, String startDate, String endDate, String orderStatus);
	public OrderDetail queryInventTransDetail(long transactionId);
	public Order queryInventMemo(long transactionId);
	public boolean updateInventMemo(long transactionId, String memo1, String memo2);

	//發票登錄-兌換金額管理
	public GsiMoney[] queryExchangeList(String year, String month);
	public GsiMoney queryExchangeDetail(long gsiMoneyId);
	public GsiMoney[] updateExchange(GsiMoney gsiMoney, String year, String month);
	
	//發票登錄-訂單發票登錄
	public Order[] queryBillRcd(String year, String month);
	public OrderDetail queryBillDetail(long transactionId);
	public Order[] updateBill(Order order, String year, String month);

	//GSiMoney&GSiBonus
	public GsiMoney queryGsiMoney(long userId);
	public ArrayList queryTransRcd(long userId, String year, String month);
	public OrderDetail[] queryTransDetail(long transactionId);
	public ArrayList applyChange(long userId);
	public boolean Savechange(Member member, String money);
	public GsiMoney[] queryExchangeLog(long userId, String year, String month);
	public Order[] queryPrePaidRcd(long userId, String year, String month);
	public Order[] queryBuyLogRcd(long userId, String year, String month);
	public OrderDetail[] queryBuyLogDetail(long transactionId);
	public ArrayList queryBonusDetail(long userId);
	public Order[] queryCashRcd(long userId,String status, long transactionId);
	public OrderDetail[] queryCashRcdDetail(long transactionId);
	public void downloadReturnList();
	
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
		
		
		//商品管理-新增商品 
		public void addProduct(File file);
		
		//商品管理-查詢商品類別 
		public ProductionClassification[] queryProductionClassification();
		
		//商品管理-新增商品類別
		public void addProductionClassification(String productionClassificationName);
		
		//商品管理-編輯商品類別
		public void editProductionClassification(long productionClassificationId, String productionClassificationName);
		
		//商品管理-刪除商品類別
		public ProductionClassification[] deleteProductionClassification(long roductionClassificationId);
		
		//商品管理-查詢商品
		public ArrayList queryProduct();
		
		//商品管理-查詢商品資訊
		public ArrayList queryProductInfo(long roductionClassificationId);
		
		//商品管理-批次更新商品
		public void updateProductBatch(long productionClassificationId, String condition, double rate);
		
		//商品管理-查詢商品細節
		public ArrayList queryProductDetail(long productId);
		
		//商品管理-更新商品資訊(查詢商品資訊頁)
		//public long saveModify(long productId, String realPrice,String specialPrice, String status, long productionClassificationId);
		
		//商品管理-更新商品資訊(查詢商品細節頁)
		public long updateProduct(long adminId, long productId, String productName,long newProductionClassificationId, String transactionType,String realPrice,String specialPrice, String discountPrice, String discountBonus, String giveBonus, String stock, String status, String keyword, String memo, long productionClassificationId);
		
}