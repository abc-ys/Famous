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
	public ArrayList addShoppingCart(Long userId, Long productId, String amount);
	public ArrayList purchaseConfirmShoppingCart(Long userId, ShoppingCart shoppingCart);
	public Order addOrder(Long shoppingCartId);
	
	//購物車service
	public ShoppingCartDetail[] queryShoppingCart(Long userId);
	public ShoppingCartDetail[] deleteShoppingCart(Long userId,Long transactionId);
	public ShoppingCartDetail[] forwardPurchase(Long userId);
	public void purchaseConfirm(Long shoppingCartId, String gsiMoney, String gsiBonus);
	
	//訂單管理service-實體幣訂單
	public Order[] queryCashTransRcd(String userEmail, Long transactionId, String startDate, String endDate, String orderStatus);
	public ArrayList queryCashTransDetail(Long transactionId);
	public boolean saveCashTransRcd(Long transactionId,String billStatus, String payStatus, String processStatus, String memo1, String memo2);
	public Order queryDeliveryRcd(Long transactionId);
	public ArrayList updateDeliveryRcd(Long transactionId, String deliveryDate, String deliveryNo, String billNo);
	public ArrayList returnGoods(Long transactionId);
	public ArrayList cancelOrder(Long transactionId);
	
	//訂單管理service-虛擬幣訂單
	public Order[] queryInventTransRcd(String userEmail, Long transactionId, String startDate, String endDate, String orderStatus);
	public OrderDetail queryInventTransDetail(Long transactionId);
	public Order queryInventMemo(Long transactionId);
	public boolean updateInventMemo(Long transactionId, String memo1, String memo2);

	//發票登錄-兌換金額管理
	public GsiMoney[] queryExchangeList(String year, String month);
	public GsiMoney queryExchangeDetail(Long gsiMoneyId);
	public GsiMoney[] updateExchange(GsiMoney gsiMoney, String year, String month);
	
	//發票登錄-訂單發票登錄
	public Order[] queryBillRcd(String year, String month);
	public OrderDetail queryBillDetail(Long transactionId);
	public Order[] updateBill(Order order, String year, String month);

	//GSiMoney&GSiBonus
	public GsiMoney queryGsiMoney(Long userId);
	public ArrayList queryTransRcd(Long userId, String year, String month);
	public OrderDetail[] queryTransDetail(Long transactionId);
	public ArrayList applyChange(Long userId);
	public boolean Savechange(Member member, String money);
	public GsiMoney[] queryExchangeLog(Long userId, String year, String month);
	public Order[] queryPrePaidRcd(Long userId, String year, String month);
	public Order[] queryBuyLogRcd(Long userId, String year, String month);
	public OrderDetail[] queryBuyLogDetail(Long transactionId);
	public ArrayList queryBonusDetail(Long userId);
	public Order[] queryCashRcd(Long userId,String status, Long transactionId);
	public OrderDetail[] queryCashRcdDetail(Long transactionId);
	public void downloadReturnList();
	
	//檢舉管理
	public ArrayList queryOffenseCategory(Long productionCategoryId);
	public void addOffense(Long userId, Long productionCategoryId, long offenseTypeId, String reason);
	public ArrayList queryUnHandle();
	public ArrayList updateAlbumHide(Long adminId, Long albumId);
	public ArrayList updateSongHide(Long adminId, Long songId);
	public Offense[] queryOffenseReason(Long productionCategoryId);
	public Album[] queryAutoHide();
	public ArrayList queryAutoHideByDate(String type,String year, String month, String creator);
	public void canceAlbumHide(Long adminId, Long albumId);
	public void canceSonglHide(Long adminId, Long songId);
	public Album[] queryAlreadyHide(String type);
	public ArrayList queryAlreadyHideByDate(String year, String month, String creator);
	public Offense[] queryOffenseByUser(Long userId);
	public void updateIncorrectOffense(Long adminId, Long offenseId);
	
	
	//商品管理
	public void addProduct(File file);
	public ProductionClassification[] queryProductionClassification();
	public void addProductionClassification(String productionClassificationName);
	public void editProductionClassification(Long productionClassificationId, String productionClassificationName);
	public ProductionClassification[] deleteProductionClassification(Long roductionClassificationId);
	public ArrayList queryProductInfo(Long roductionClassificationId);
	public ArrayList updateProductBatch(Long productionClassificationId, String condition, double rate);
	public ProductionClassification queryProductDetail(Long productId);
	public ProductionClassification[] updateProduct(Long productId, String productName,String roductionClassification, String realPrice,String specialPrice, String giveBonus, String stock, String status, String keyword, String memo);

}
