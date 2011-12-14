package com.ubn.befamous.service.impl;

import java.io.File;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ubn.befamous.dao.IBaseDao;
import com.ubn.befamous.entity.Admin;
import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.Creator;
import com.ubn.befamous.entity.DownloadList;
import com.ubn.befamous.entity.GsiBonus;
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
import com.ubn.befamous.entity.SDCardPrice;
import com.ubn.befamous.entity.ShoppingCart;
import com.ubn.befamous.entity.ShoppingCartDetail;
import com.ubn.befamous.entity.Song;
import com.ubn.befamous.entity.TransactionRcd;
import com.ubn.befamous.service.TransactionRecordService;

@Service("transactionRecordService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TransactionRecordServiceImpl implements TransactionRecordService{
	@Autowired
	@Qualifier("sdCardDAO")
	public IBaseDao<SDCard, Long> sdCardDAO;
	
	@Autowired
	@Qualifier("prePaidDAO")
	public IBaseDao<PrePaid, Long> prePaidDAO;
	
	@Autowired
	@Qualifier("creatorDAO")
	private IBaseDao<Creator, Long> creatorDAO;
	
	@Autowired
	@Qualifier("memberDAO")
	public IBaseDao<Member, Long> memberDAO;

	@Autowired
	@Qualifier("productionCategoryDAO")
	public IBaseDao<ProductionCategory, Long> productionCategoryDAO;
	
	@Autowired
	@Qualifier("shoppingCartDAO")
	public IBaseDao<ShoppingCart, Long> shoppingCartDAO;
	
	@Autowired
	@Qualifier("shoppingCartDetailDAO")
	public IBaseDao<ShoppingCartDetail, Long> shoppingCartDetailDAO;
	
	@Autowired
	@Qualifier("orderDAO")
	public IBaseDao<Order, Long> orderDAO;
	
	@Autowired
	@Qualifier("orderDetailDAO")
	public IBaseDao<OrderDetail, Long> orderDetailDAO;
	
	@Autowired
	@Qualifier("gsiMoneyDAO")
	public IBaseDao<GsiMoney, Long> gsiMoneyDAO;
	
	@Autowired
	@Qualifier("gsiBonusDAO")
	public IBaseDao<GsiBonus, Long> gsiBonusDAO;
	
	@Autowired
	@Qualifier("offenseDAO")
	public IBaseDao<Offense, Long> offenseDAO;
	
	@Autowired
	@Qualifier("offenseTypeDAO")
	public IBaseDao<OffenseType, Long> offenseTypeDAO;
	
	@Autowired
	@Qualifier("albumDAO")
	public IBaseDao<Album, Long> albumDAO;
	
	@Autowired
	@Qualifier("songDAO")
	public IBaseDao<Song, Long> songDAO;
		
	@Autowired
	@Qualifier("adminDAO")
	public IBaseDao<Admin, Long> adminDAO;
	
	@Autowired
	@Qualifier("hiddenDAO")
	public IBaseDao<Hidden, Long> hiddenDAO;
	
	@Autowired
	@Qualifier("productionClassificationDAO")
	public IBaseDao<ProductionClassification, Long> productionClassificationDAO;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//購買SD卡與儲值
	
	/**
	 *購買SD卡第一步:查詢SDCard清單 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public SDCard[] querySDCard() {	
		SDCard[] sdList = this.sdCardDAO.findAll();		
		return sdList;		
	}

	/**
	 *儲值第一步:查詢PrePaid清單 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public PrePaid[] queryPrePaid() {		
		PrePaid[] pdList = this.prePaidDAO.findAll();
		return pdList;
	}

	/**
	 * 第二步:加入購物車*/
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ArrayList addShoppingCart(long userId, long productId, String amount) {
		
		Member member = this.memberDAO.find(userId);	
		ProductionCategory productionCategory = this.productionCategoryDAO.find(productId);
		Set<ShoppingCartDetail> shoppingCartList = new HashSet();
		ShoppingCartDetail cartDetail = new ShoppingCartDetail();		
		
		ShoppingCart cart = new ShoppingCart();
		cart.setMember(member);
		cart.setCreateDate(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		cart.setShoppingCartDetail(shoppingCartList);
		
		cartDetail.setShoppingCart(cart);
		cartDetail.setAmount(amount);
		cartDetail.setProductionCategory(productionCategory);
		
		shoppingCartList.add(cartDetail);
		this.shoppingCartDetailDAO.save(cartDetail);
		this.shoppingCartDAO.save(cart);
		
		ArrayList list = new ArrayList();
		list.add(member);
		list.add(cart);	
		
		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ArrayList purchaseConfirmShoppingCart(long userId, ShoppingCart shoppingCart) {
		Member member = this.memberDAO.find(userId);		
		ArrayList list = new ArrayList();
		list.add(member);
		list.add(shoppingCart);
		
		return list;
	}
	
	//實體幣-購物車變為訂單。
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Order addOrder(long shoppingCartId) {
		
		
		return null;
	}

	//購買專輯或歌曲
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ShoppingCartDetail[] queryShoppingCart(long userId) {
		
		Query query = this.sessionFactory.getCurrentSession().createQuery("from ShoppingCart s where s.member.id =:v1 order by s.id desc");
		query.setParameter("v1", userId);
		
		List<ShoppingCart> shoppingCart = (List<ShoppingCart>)query.list();		
		ShoppingCart newShoppingCart = shoppingCart.get(0);
		
		Set<ShoppingCartDetail> detailSet = newShoppingCart.getShoppingCartDetail();
		
		ShoppingCartDetail[] shoppingCartDetail = detailSet.toArray(new ShoppingCartDetail[detailSet.size()]);
		
		return shoppingCartDetail;
	}


	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ShoppingCartDetail[] forwardPurchase(long userId) {
		ShoppingCartDetail[] shoppingCartDetail = this.queryShoppingCart(userId); 		 
		return shoppingCartDetail;
	}


		//訂單管理-實體幣訂單-搜尋實體幣訂單
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public Order[] queryCashTransRcd(String userEmail, String orderId,
				String startDate, String endDate, String handleStatus) {
			System.out.println("queryCashTransRcd====>userEmail="+userEmail+", orderId="+orderId+", startDate="+startDate+", endDate="+endDate+", handleStatus="+handleStatus);
			StringBuffer tempQuery = new StringBuffer();
			tempQuery.append("select o from Order o where o.payMethod is not null ");		
			if(!userEmail.isEmpty()){
				tempQuery.append("and o.member.email = :email ");
			}
			if(!orderId.isEmpty()){
				tempQuery.append("and o.id = :orderId ");
			}
			if(!startDate.isEmpty()&&!endDate.isEmpty()){
				tempQuery.append("and (o.billDate between :startDate and :endDate) ");
			}
			if(!handleStatus.isEmpty()&& !handleStatus.equals("0")){
				tempQuery.append("and o.handleStatus = :handleStatus ");
			}
			
			Query query = this.sessionFactory.getCurrentSession().createQuery(tempQuery.toString());
			if(!userEmail.isEmpty()){
				query.setParameter("email", userEmail);
			}
			if(!orderId.isEmpty()){
				query.setLong("orderId", Long.parseLong(orderId));
			}
			if(!startDate.isEmpty()&&!endDate.isEmpty()){
				String sDate = startDate.replaceAll("-", "");
				sDate = sDate+"000000";
				String eDate = endDate.replaceAll("-", "");
				eDate = eDate+"235959";
				query.setParameter("startDate",sDate);
				query.setParameter("endDate",eDate);
			}
			if(!handleStatus.isEmpty()&& !handleStatus.equals("0")){
				query.setParameter("handleStatus",handleStatus);
			}
			List<Order> orderList = (List<Order>)query.list();
			Order[] orders = orderList.toArray(new Order[orderList.size()]);
			return orders;
		}

		//訂單管理-現金訂單管理-現金訂單明細-儲存出貨登錄
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public void updateShipRegister(long adminId, long orderId, String shipDate, String shipNo, String billNo) {
			String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
			Order order = this.orderDAO.find(orderId);
			order.setShipNo(shipNo);
			order.setBillNo(billNo);
			order.setShipDate(shipDate);
			order.setModifier(String.valueOf(adminId));
			order.setModifyDate(date);
			this.orderDAO.update(order);
		}

		//訂單管理-實體幣訂單-儲存處理實體幣訂單結果
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public void saveCashTransRcd(long adminId, long orderId, String billStatus,
				String payStatus, String handleStatus, String memo1, String memo2) {
			String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
			Order order = this.orderDAO.find(orderId);
			order.setModifier(String.valueOf(adminId));
			order.setBillStatus(billStatus);
			order.setPayStatus(payStatus);
			order.setHandleStatus(handleStatus);
			order.setMemo1(memo1);
			order.setMemo2(memo2);
			order.setModifyDate(date);
			this.orderDAO.update(order);
		
		}

		//訂單管理-虛擬幣訂單-搜尋虛擬幣訂單(起始搜尋)
		public Order[] queryFirstInventTransRcd(String year, String month){
			Integer y = Integer.valueOf(year);
			Integer m = Integer.valueOf(month);
			Query query = this.sessionFactory.getCurrentSession().createQuery("select o from Order o where (year(o.billDate) in (:y)) and (month(o.billDate) in (:mo)) and (o.gsiMoney is not null) and (o.payMethod is null)");
			query.setParameter("y", y);
			query.setParameter("mo", m);
			List<Order> gsiMoneyList = (List<Order>)query.list();
			Order[] orders = gsiMoneyList.toArray(new Order[gsiMoneyList.size()]);	
			return orders;
			
		}

		//訂單管理-虛擬幣訂單(根據搜尋條件)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public Order[] queryInventTransRcd(String userEmail, String orderId, String startDate, String endDate) {
			StringBuffer tempQuery = new StringBuffer();
			tempQuery.append("select o from Order o join o.member m where (o.gsiMoney is not null) and (o.payMethod is null)");		
			if(!userEmail.isEmpty()){
				tempQuery.append("and m.email = :email ");
			}
			if(!orderId.isEmpty()){
				tempQuery.append("and o.id = :orderId ");
			}
			if(!startDate.isEmpty()&&!endDate.isEmpty()){
				tempQuery.append("and (o.billDate between :startDate and :endDate) ");
			}
			
			Query query = this.sessionFactory.getCurrentSession().createQuery(tempQuery.toString());
			if(!userEmail.isEmpty()){
				query.setParameter("email", userEmail);
			}
			if(!orderId.isEmpty()){
				query.setLong("orderId", Long.parseLong(orderId));
			}
			if(!startDate.isEmpty()&&!endDate.isEmpty()){
				String sDate = startDate.replaceAll("-", "");
				sDate = sDate+"000000";
				String eDate = endDate.replaceAll("-", "");
				eDate = eDate+"235959";
				query.setParameter("startDate",sDate);
				query.setParameter("endDate",eDate);
			}
			List<Order> orderList = (List<Order>)query.list();
			Order[] orders = orderList.toArray(new Order[orderList.size()]);
			return orders;
		}

		//訂單管理-虛擬幣訂單-搜尋訂單MEMO
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public ArrayList queryInventMemo(long orderId) {
			ArrayList note = new ArrayList();
			Order order = this.orderDAO.find(orderId);
			note.add(order.getMemo1());
			note.add(order.getMemo2());
			return note;
		}

		//訂單管理-虛擬幣訂單-儲存訂單MEMO
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public void updateInventMemo(long adminId,long orderId, String memo1, String memo2) {
			String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
			Admin admin = this.adminDAO.find(adminId);
			Order order = this.orderDAO.find(orderId);
			order.setModifier(String.valueOf(admin.getId()));
			order.setModifyDate(date);
			order.setMemo1(memo1);
			order.setMemo2(memo2);
			this.orderDAO.update(order);		
		}

		//發票登錄-兌換金額管理(起始查詢)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public GsiMoney[] queryFirstExchangeList(){
			Query query = this.sessionFactory.getCurrentSession().createQuery("from GsiMoney g where g.exchangeDate is not null and g.transactionType='4' and g.exchangeStatus='1'");
			List<GsiMoney> gsiMoneyList = (List<GsiMoney>)query.list();
			GsiMoney[] gsiMoney = gsiMoneyList.toArray(new GsiMoney[gsiMoneyList.size()]);	
			return gsiMoney;
			
		}
		
		//發票登錄-兌換金額管理(根據查詢條件)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public GsiMoney[] queryExchangeList(String year, String month) {		
			Integer y = Integer.valueOf(year);
			Integer m = Integer.valueOf(month);
			Query query = this.sessionFactory.getCurrentSession().createQuery("from GsiMoney g where (year(g.exchangeDate) in (:y)) and (month(g.exchangeDate) in (:mo)) and (g.transactionType='4')and (g.exchangeStatus='1')");
			query.setParameter("y", y);
			query.setParameter("mo", m);
			List<GsiMoney> gsiMoneyList = (List<GsiMoney>)query.list();
			GsiMoney[] gsiMoney = gsiMoneyList.toArray(new GsiMoney[gsiMoneyList.size()]);	
			return gsiMoney;
		}

		//發票登錄-儲存兌換金額訂單明細
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public void updateExchange(long adminId, long gsiMoneyId,String exchangeTax, String paid, String exchangeStatus, String paidDate, String memo) {
			String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
			GsiMoney g = this.gsiMoneyDAO.find(gsiMoneyId);
			g.setModifier(String.valueOf(adminId));
			g.setModifyDate(date);
			g.setExchangeTax(exchangeTax);
			g.setPaid(paid);
			g.setExchangeStatus(exchangeStatus);
			g.setPaidDate(date);
			g.setMemo(memo);
			this.gsiMoneyDAO.update(g);
		}
		
		//發票登錄-訂單發票登錄
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public Order[] queryBillRcd(String year, String month) {

			System.out.println("queryBillRcd====>");
			Integer y = Integer.valueOf(year);
			Integer m = Integer.valueOf(month);
			Query query = this.sessionFactory.getCurrentSession().createQuery("select o from Order o where (year(o.purchaseDate) in (:y)) and (month(o.purchaseDate) in (:mo)) and o.billNo is null and (o.gsiMoney is null)");
			query.setParameter("y", y);
			query.setParameter("mo", m);
			List<Order> gsiMoneyList = (List<Order>)query.list();	
			Order[] orders = gsiMoneyList.toArray(new Order[gsiMoneyList.size()]);	
			return orders;
		}


		//發票登錄-儲存訂單明細
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public void updateBill(long adminId, long orderId, String billNo, String memo2, String payStatus) {
			String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
			Admin admin = this.adminDAO.find(adminId);
			Order order = this.orderDAO.find(orderId);
			order.setModifier(String.valueOf(admin.getId()));
			order.setModifyDate(date);
			order.setBillNo(billNo);
			order.setPayStatus(payStatus);
			order.setMemo2(memo2);
			this.orderDAO.update(order);
		}
		
		//我的帳本-查看收支表(明細)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public Order[] queryGsiMoney(long userId, String year, String month) {
			
			Integer y = Integer.valueOf(year);
			Integer m = Integer.valueOf(month);
			Query query = this.sessionFactory.getCurrentSession().createQuery("select o from Member m join m.order o where m.id = :userId and (year(o.billDate) in (:y)) and (month(o.billDate) in (:mo) and o.gsiMoney is not null)");
			query.setParameter("userId", userId);
			query.setParameter("y", y);
			query.setParameter("mo", m);
			
			List<Order> gsiMoneyList = (List<Order>)query.list();
			Order[] orders = gsiMoneyList.toArray(new Order[gsiMoneyList.size()]);
			return orders;
		}
		
		
		//我的帳本-查看收支表(最後一筆)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public List<GsiMoney> queryOneGsiMoney(long userId, String year, String month) {			
			Integer y = Integer.valueOf(year);
			Integer m = Integer.valueOf(month);
			Query query = this.sessionFactory.getCurrentSession().createQuery("select g from Member m join m.gsiMoney g where m.id = :userId and year(g.createDate) in (:y) and month(g.createDate) in (:mo) order by g.createDate desc");
			query.setParameter("userId", userId);
			query.setParameter("y", y);
			query.setParameter("mo", m);
			List<GsiMoney> gMoneyList = (List<GsiMoney>) query.list();
			return gMoneyList;
		}

		//我的帳本-查看收支表(這個月的兌換記錄)
		public List<GsiMoney> queryOneExchangeRcd(long userId, String year, String month){
			Integer y = Integer.valueOf(year);
			Integer m = Integer.valueOf(month);
			Query query = this.sessionFactory.getCurrentSession().createQuery("select g from Member m join m.gsiMoney g where m.id = :userId and year(g.exchangeDate) in (:y) and month(g.exchangeDate) in (:m)");
			query.setParameter("userId", userId);
			query.setParameter("y", y);
			query.setParameter("m", m);
			List<GsiMoney> gMoneyList = (List<GsiMoney>) query.list();
			return gMoneyList;
		}
		
		//根據訂單編號找尋訂單
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public Order queryTransRcd(long orderId){
			Order order = this.orderDAO.find(orderId);		
			return order;
		}

		//我的帳本-儲存兌換申請以及創作者帳戶資料
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public void saveChange(Creator creator, int money, String synUpdate) {
			String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");		
			Creator c = this.creatorDAO.find(creator.getId());//更新creator部分資料
			if(synUpdate.equals("y"))
			{
				c.setRealName(creator.getRealName());
				c.setIdentityNO(creator.getIdentityNO());
				c.setAddress(creator.getAddress());
				c.setCellPhone(creator.getCellPhone());
				c.setTel(creator.getTel());
				c.setAccountName(creator.getAccountName());
				c.setAccountNO(creator.getAccountNO());
				c.setBankBranch(creator.getBankBranch());
				c.setBankName(creator.getBankName());
				this.creatorDAO.update(c);
			}
			
			Query query = this.sessionFactory.getCurrentSession().createQuery("from Member m where m.id = :userId");
			query.setParameter("userId", creator.getId());
			Member member = (Member) query.uniqueResult();
			
			Query query2 = this.sessionFactory.getCurrentSession().createQuery("from GsiMoney g order by g.createDate desc");
			GsiMoney lastGsiMoney = (GsiMoney) query2.list().get(0);
			int lastMoney = Integer.parseInt(lastGsiMoney.getBalance());
			String newBalance = String.valueOf(lastMoney - money);
			
			GsiMoney gsiMoney = new GsiMoney();
			gsiMoney.setCreateDate(date);
			gsiMoney.setMember(member);
			gsiMoney.setOutgo(String.valueOf(money));
			gsiMoney.setTransactionType("4");
			gsiMoney.setBalance(newBalance);
			gsiMoney.setExchangeDate(date);
			gsiMoney.setExchangeStatus("1");//1為待處理
			gsiMoney.setCreateUser(String.valueOf(creator.getId()));
			this.gsiMoneyDAO.save(gsiMoney);
		}

		//我的帳本-兌換記錄(起始查詢六個月內)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public GsiMoney[] queryFirstExchangeLog(long userId, String lastDate, String nowDate) {
			Query query = this.sessionFactory.getCurrentSession().createQuery("select g from Member m join m.gsiMoney g where m.id = :userId and g.transactionType='4' and (g.exchangeDate between :lastDate and :nowDate) and g.exchangeStatus='2'");
			query.setParameter("userId", userId);
			query.setParameter("lastDate", lastDate);
			query.setParameter("nowDate", nowDate);
			
			List<GsiMoney> gsiMoneyList = (List<GsiMoney>)query.list();
			GsiMoney[] gsiMoney = gsiMoneyList.toArray(new GsiMoney[gsiMoneyList.size()]);
			return gsiMoney;
		}
		
		//我的帳本-兌換記錄(查詢條件)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public GsiMoney[] queryExchangeLog(long userId, String year, String month) {
			Integer y = Integer.valueOf(year);
			Integer m = Integer.valueOf(month);
			Query query = this.sessionFactory.getCurrentSession().createQuery("select g from Member m join m.gsiMoney g where m.id = :userId and (year(g.exchangeDate) in (:y)) and (month(g.exchangeDate) in (:mo)) and g.exchangeStatus='2' and g.transactionType='4'");
			query.setParameter("userId", userId);
			query.setParameter("y", y);
			query.setParameter("mo", m);
				
			List<GsiMoney> gsiMoneyList = (List<GsiMoney>)query.list();
			GsiMoney[] gsiMoney = gsiMoneyList.toArray(new GsiMoney[gsiMoneyList.size()]);
			return gsiMoney;
		}
		
		//我的帳本-儲值記錄
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public Order[] queryPrePaidRcd(long userId, String year, String month) {
			Integer y = Integer.valueOf(year);
			Integer m = Integer.valueOf(month);
			Query query = this.sessionFactory.getCurrentSession().createQuery("select o from Member m join m.order o join o.gsiMoney g where m.id = :userId and (year(o.billDate) in (:y)) and (month(o.billDate) in (:mo)) and g.transactionType='1'");
			query.setParameter("userId", userId);
			query.setParameter("y", y);
			query.setParameter("mo", m);
			
			List<Order> gsiMoneyList = (List<Order>)query.list();
			Order[] orders = gsiMoneyList.toArray(new Order[gsiMoneyList.size()]);
			return orders;
		}

		//我的帳本-GSiMoney消費記錄
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public List<Order> queryBuyLogRcd(long userId, String year, String month) {		
			System.out.println("aaaa");
			Integer y = Integer.valueOf(year);
			Integer m = Integer.valueOf(month);
			Query query = this.sessionFactory.getCurrentSession().createQuery("select o from Member m join m.order o join o.gsiMoney g where m.id = :userId and (year(o.billDate) in (:y)) and (month(o.billDate) in (:mo)) and g.transactionType='3'");
			query.setParameter("userId", userId);
			query.setParameter("y", y);
			query.setParameter("mo", m);
			List<Order> gsiMoneyList = (List<Order>)query.list();
			
		return gsiMoneyList;
			
		}
		
		//我的帳本-購買記錄明細(訂單編號的連結)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public List<Object[]> queryBuyLogDetail(long orderId) {
			
			//根據訂單編號找尋其訂單明細
			Query query2 = this.sessionFactory.getCurrentSession().createQuery("select ots from OrderDetail ots join ots.order o where ots.order.id = :oid");
			query2.setParameter("oid",orderId);
			List<OrderDetail> orderDetails =(List<OrderDetail>)query2.list();		
			
			//根據訂單明細去判斷產品(專輯/歌曲)
			List<Object[]> list = new ArrayList();
			for(OrderDetail ot:orderDetails){
				Object[] object = new Object[3];
				Query query = this.sessionFactory.getCurrentSession().createQuery("FROM ProductionCategory p where p.pid = :productId");
				query.setLong("productId", ot.getProductionCategory().getPid());
				Object product = query.uniqueResult();
				if (product instanceof Album) {
					Album album = (Album)product;			
					object[0]="1";
					object[1]= album;
					object[2]= ot;
					list.add(object);
				}else if (product instanceof Song){
					Song song = (Song)product;
					object[0]="2";
					object[1]=song;
					object[2]= ot;
					list.add(object);
				}else if (product instanceof SDCard){
					SDCard sdCard = (SDCard)product;
					object[0]="3";
					object[1]=sdCard;
					object[2]= ot;
					list.add(object);
				}else if (product instanceof PrePaid){
					PrePaid prePaid = (PrePaid)product;
					object[0]="4";
					object[1]=prePaid;
					object[2]= ot;
					list.add(object);
				}
			}
			return list;
		}
		
		//我的帳本-銷售紀錄
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public List<Object[]> querySaleRcd(long userId, String year, String month) {
			Integer y = Integer.valueOf(year);
			Integer m = Integer.valueOf(month);
			Query query = this.sessionFactory.getCurrentSession().createQuery("select o from Order o join o.gsiMoney g where (year(o.billDate) in (:y)) and (month(o.billDate) in (:mo)) and g.transactionType='2'");
			query.setParameter("y", y);
			query.setParameter("mo", m);
			List<Order> gsiMoneyList = (List<Order>)query.list();
			List<Object[]> list = new ArrayList();
			for(Order o:gsiMoneyList){
				//根據訂單編號找尋其訂單明細
				Query query2 = this.sessionFactory.getCurrentSession().createQuery("select ots from OrderDetail ots join ots.order o where ots.order.id = :oid");
				query2.setParameter("oid",o.getId());
				List<OrderDetail> orderDetails =(List<OrderDetail>)query2.list();		
				
				//根據訂單明細去判斷產品(專輯/歌曲)
				for(OrderDetail ot:orderDetails){
					Object[] object = new Object[3];
					Query query3 = this.sessionFactory.getCurrentSession().createQuery("FROM ProductionCategory p where p.pid = :productId");
					query3.setLong("productId", ot.getProductionCategory().getPid());
					Object product = query3.uniqueResult();
					if (product instanceof Album) {
						Album album = (Album)product;					
						if(album.getCreator().getId()==userId){
							object[0]="1";
							object[1]= album;
							object[2]= ot;
							list.add(object);						
						}					
					}else if (product instanceof Song){
						Song song = (Song)product;
						if(song.getAlbum().getCreator().getId()==userId){
							object[0]="2";
							object[1]=song;
							object[2]= ot;
							list.add(object);
						}
					}
				}
			}
			return list;
		}

		
		//我的帳本-贈送Bonus明細
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public ArrayList queryBonusDetail(long userId) {
			String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");		
			ArrayList list = new ArrayList(); 
			Query query = this.sessionFactory.getCurrentSession().createQuery("select g from Member m join m.gsiBonus g where m.id = :userId order by g.createDate desc");
			query.setParameter("userId", userId);
			List<GsiBonus> gb = (List<GsiBonus>)query.list();
			list.add(gb);
			Query query2 = this.sessionFactory.getCurrentSession().createQuery("select o from Member m join m.order o join o.gsiBonus g where m.id = :userId ");
			query2.setParameter("userId", userId);
			List<Order> orderList = (List<Order>)query2.list();
			list.add(orderList);
			Query query3 = this.sessionFactory.getCurrentSession().createQuery("select sum(g.reward) from Member m join m.gsiBonus g where m.id = :userId and g.onDate > :date");
			query3.setParameter("userId", userId);
			query3.setParameter("date", date);
			String unBonus = (String) query3.uniqueResult();
			list.add(unBonus);
			return list;
		}
		
		//我的帳本-現金消費記錄
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public Order[] queryCashRcd(long userId, String choice, String orderId) {

			System.out.println("userId======>"+userId);
			System.out.println("choice======>"+choice);
			System.out.println("orderId======>"+orderId);
			StringBuffer tempQuery = new StringBuffer();
			tempQuery.append("select o from Member m join m.order o where m.id = :userId and (o.gsiMoney is null) ");
			
			if(!choice.isEmpty()){
				if(choice.equals("1")){
					tempQuery.append("and (o.billDate between :lastDate and :nowDate) and o.shipDate is null ");
				}
				if(choice.equals("2")){
					tempQuery.append("and (o.billDate between :lastDate and :nowDate) and o.shipDate is null ");
				}
				if(choice.equals("3")){
					tempQuery.append("and (o.billDate between :lastDate and :nowDate) and o.applyReturnGoodDate is not null ");
				}
				if(choice.equals("4")){
					tempQuery.append("and (o.billDate between :lastDate and :nowDate) ");
				}
			}
			if(!orderId.isEmpty()){
				tempQuery.append("and o.id = :orderId ");
			}
			Query query = this.sessionFactory.getCurrentSession().createQuery(tempQuery.toString());
			query.setParameter("userId", userId);
			
			if(!choice.isEmpty()){
				if(choice.equals("1")){
					String nowDate = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
					Date tempDate = DateUtils.addDays(new Date(), -30); 
					String lastDate = DateFormatUtils.format(tempDate, "yyyyMMddHHmmss");			
					query.setParameter("nowDate", nowDate);
					query.setParameter("lastDate", lastDate);
				}
				if(choice.equals("2")){
					String nowDate = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
					Date tempDate = DateUtils.addDays(new Date(), -180); 
					String lastDate = DateFormatUtils.format(tempDate, "yyyyMMddHHmmss");
					query.setParameter("nowDate", nowDate);
					query.setParameter("lastDate", lastDate);
				}
				if(choice.equals("3")){
					String nowDate = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
					Date tempDate = DateUtils.addDays(new Date(), -180);
					String lastDate = DateFormatUtils.format(tempDate, "yyyyMMddHHmmss");
					query.setParameter("nowDate", nowDate);
					query.setParameter("lastDate", lastDate);
				}
				if(choice.equals("4")){
					String nowDate = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
					Date tempDate = DateUtils.addDays(new Date(), -180);
					String lastDate = DateFormatUtils.format(tempDate, "yyyyMMddHHmmss");
					query.setParameter("nowDate", nowDate);
					query.setParameter("lastDate", lastDate);
				}
			}
			
			if(!orderId.isEmpty()){
				query.setLong("orderId", Long.parseLong(orderId));
			}
			List<Order> orderList = (List<Order>)query.list();
			Order[] orders = orderList.toArray(new Order[orderList.size()]);
			return orders;
		}

		//我的帳本-取消現金訂單(XXXXXXXXXXXXXXXXXXXXXXX)
		public void cancelGoods(long orderId){
			System.out.println("cancelGoods====>");
			String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
			Query query = this.sessionFactory.getCurrentSession().createQuery("select o from Order o where o.id = :orderId");
			query.setParameter("orderId", orderId);
			
			System.out.println("orderId===="+orderId);
			Order order = (Order) query.uniqueResult();
			System.out.println("date1===="+order.getApplyReturnGoodDate());
			order.setApplyReturnGoodDate(date);		
			this.orderDAO.update(order);
			Order o = this.orderDAO.find(order.getId());
			System.out.println("date2===="+o.getApplyReturnGoodDate());
		}	
		
		//檢舉管理-新增檢舉介面
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public ArrayList queryOffenseCategory(long productionCategoryId) {
			OffenseType[] offenseType = this.offenseTypeDAO.findAll();		
			ArrayList list = new ArrayList();		
			list.add(offenseType);
			list.add(productionCategoryId);
			return list;
		}

		//檢舉管理-儲存檢舉內容
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public void addOffense(long userId, long productionCategoryId, long offenseTypeId,
				String reason) {	
			String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
			Member member = this.memberDAO.find(userId);
			OffenseType offenseType =this.offenseTypeDAO.find(offenseTypeId);
			
			Offense offense = new Offense(); //建立檢舉
			offense.setCreateDate(date);
			offense.setMember(member);		
			offense.setReason(reason);
			offense.setOffenseType(offenseType);
			offense.setOffenseStatus("1");//新增時，檢舉狀態為正常檢舉
			offense.setCreateUser(String.valueOf(userId));
			//根據產品類別編號找尋產品(專輯/歌曲)
			Query query = this.sessionFactory.getCurrentSession().createQuery("FROM ProductionCategory p where p.pid = :id");
			query.setParameter("id", productionCategoryId);
			Object product = query.uniqueResult();
			if (product instanceof Song) {
				Song song = (Song)product;
				offense.setSong(song);
			}else{
				Album album = (Album)product;
				offense.setAlbum(album);
			}	
						
			this.offenseDAO.save(offense);
		}

		//檢舉管理-檢視被檢舉的專輯和歌曲
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public ArrayList queryUnHandle() {
			
			Query query = this.sessionFactory.getCurrentSession().createQuery("select a, COUNT(a) from Offense o join o.album a where o.offenseStatus ='1' and a.hidden is null group by a order by o.createDate desc");
			List<Object[]> albums = (List<Object[]>)query.list();
			
			Query query2 = this.sessionFactory.getCurrentSession().createQuery("select s, COUNT(s) from Offense o join o.song s where o.offenseStatus ='1' and s.hidden is null group by s order by o.createDate desc");
			List<Object[]> songs = (List<Object[]>)query2.list();
			
			ArrayList list = new ArrayList();
			list.add(albums);
			list.add(songs);
			return list;
		}

		//檢舉管理-隱藏專輯
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public void updateAlbumHide(long adminId, long albumId) {
			Album album = this.albumDAO.find(albumId);
			Admin createUser = this.adminDAO.find(adminId);
			String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
			
			Hidden hidden = new Hidden();
			hidden.setAlbum(album);
			hidden.setCreateDate(date);
			hidden.setStartDate(date);
			hidden.setCreateUser(createUser);		
			this.hiddenDAO.save(hidden);
			
			album.setHidden(hidden);
			this.albumDAO.update(album);
		}

		//檢舉管理-隱藏歌曲
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public void updateSongHide(long adminId, long songId) {
		
			Song song = this.songDAO.find(songId);
			Admin createUser = this.adminDAO.find(adminId);
			String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
			
			Hidden hidden = new Hidden();
			hidden.setSong(song);
			hidden.setCreateDate(date);
			hidden.setStartDate(date);
			hidden.setCreateUser(createUser);		
			this.hiddenDAO.save(hidden);
			
			song.setHidden(hidden);
			this.songDAO.update(song);
		}

		//檢舉管理-查詢檢舉理由 
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public Offense[] queryOffenseReason(long productionCategoryId) {
			Offense[] offenseList;
			Set<Offense> offenseSet = new HashSet();
			Object product = this.productionCategoryDAO.find(productionCategoryId);		
			if (product instanceof Song) {
				Song song = (Song)product;
				offenseSet = song.getOffense();		
			}else{
				Album album = (Album)product;
				offenseSet = album.getOffense();			
			}	
			offenseList = offenseSet.toArray(new Offense[offenseSet.size()]);
			return offenseList;
		}
			
		//檢舉管理-更新檢舉狀態(不正當檢舉)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public void updateIncorrectOffense(long adminId, long offenseId) {
			String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
			Offense offense = this.offenseDAO.find(offenseId);
			offense.setModifier(String.valueOf(adminId));
			offense.setModifyDate(date);
			offense.setOffenseStatus("2");
			this.offenseDAO.update(offense);
		}
			
		//檢舉管理-查詢被系統自動隱藏的專輯/歌曲(起始查詢)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public ArrayList queryAutoHide() {
			//Query query = this.sessionFactory.getCurrentSession().createQuery("select a, COUNT(o) from Album a join a.offense o where (o.offenseStatus ='1') and (a.hidden.endDate is null) and (a.hidden.createUser.id='1')");
			ArrayList allList = new ArrayList();
			Query query = this.sessionFactory.getCurrentSession().createQuery("select a from Album a where (a.hidden is not null) and (a.hidden.endDate is null) and (a.hidden.createUser.id='1')");
			List<Album> albums = (List<Album>)query.list();
			for(Album s:albums){
				ArrayList list = new ArrayList();
				list.add(s);
				Query query2 = this.sessionFactory.getCurrentSession().createQuery("select COUNT(a) from Offense o join o.album a where (a.pid=:albumId) and (o.offenseStatus ='1')");
				query2.setParameter("albumId", s.getPid());
				long times = (Long) query2.uniqueResult();
				list.add(times);
				allList.add(list);
			}
			
			return allList;
		}

		//檢舉管理-查詢被系統自動隱藏的專輯/歌曲(根據查詢條件)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public ArrayList queryAutoHideByDate(String type, String year, String month,
				String creator) {		
			List<Album> albums = new ArrayList();
			List<Song> songs = new ArrayList();
			StringBuffer tempQuery = new StringBuffer();
			ArrayList allList = new ArrayList();
			
			if (type.equals("1")){ //type=1為專輯		
				System.out.println("atype=============="+type);
				System.out.println("ayear=============="+year);
				System.out.println("amonth=============="+month);
				System.out.println("acreator=============="+creator);
				
				tempQuery.append("select a from Album a where (a.hidden is not null) and (a.hidden.endDate is null) and (a.hidden.createUser.id='1') ");
				
				if (!year.equals("0") && !month.equals("0")){
					tempQuery.append("and ((year(a.hidden.createDate) in (:year)) and (month(a.hidden.createDate) in (:month))) ");
				}
				if (!creator.isEmpty()){
					tempQuery.append("and a.creator.userName in (:creator) ");
				}	
				
				Query query = this.sessionFactory.getCurrentSession().createQuery(tempQuery.toString());
			
				if (!year.equals("0") && !month.equals("0")){
					Integer y = Integer.valueOf(year); //year轉型為Integer，以便查詢。
					Integer m = Integer.valueOf(month); //month轉型為Integer，以便查詢。
					System.out.println("a=============="+y);
					System.out.println("a=============="+m);
					query.setParameter("year", y);
					query.setParameter("month", m);
				}
				if (!creator.isEmpty()){
					query.setParameter("creator", creator);
				}
				
				albums = (List<Album>)query.list();
				for(Album s:albums){
					ArrayList list = new ArrayList();
					list.add(s);
					Query query2 = this.sessionFactory.getCurrentSession().createQuery("select COUNT(a) from Offense o join o.album a where (a.pid=:albumId) and (o.offenseStatus ='1')");
					query2.setParameter("albumId", s.getPid());
					long times = (Long) query2.uniqueResult();
					list.add(times);
					allList.add(list);
				}
			
			}else{  //type=2為歌曲	
				System.out.println("stype=============="+type);
				System.out.println("syear=============="+year);
				System.out.println("smonth=============="+month);
				System.out.println("screator=============="+creator);
				tempQuery.append("select s from Song s where (s.hidden is not null) and (s.hidden.endDate is null) and (s.hidden.createUser.id='1') ");
				
				if (!year.equals("0") && !month.equals("0")){
					tempQuery.append("and ((year(s.hidden.createDate) in (:year)) and (month(s.hidden.createDate) in (:month))) ");
				}
				if (!creator.isEmpty()){
					tempQuery.append("and s.album.creator.userName in (:creator) ");
				}	
				
				Query query = this.sessionFactory.getCurrentSession().createQuery(tempQuery.toString());
			
				if (!year.equals("0") && !month.equals("0")){
					Integer y = Integer.valueOf(year); //year轉型為Integer，以便查詢。
					Integer m = Integer.valueOf(month); //month轉型為Integer，以便查詢。
					System.out.println("=============="+y);
					System.out.println("=============="+m);
					query.setParameter("year", y);
					query.setParameter("month", m);
				}
				if (!creator.isEmpty()){
					query.setParameter("creator", creator);
				}
				songs = (List<Song>)query.list();
				for(Song s:songs){
					ArrayList list = new ArrayList();
					list.add(s);
					Query query2 = this.sessionFactory.getCurrentSession().createQuery("select COUNT(s) from Offense o join o.song s where (s.pid=:songId) and (o.offenseStatus ='1')");
					query2.setParameter("songId", s.getPid());
					long times = (Long) query2.uniqueResult();
					list.add(times);
					allList.add(list);
				}
			}
			return allList;
		}
		
		//檢舉管理-取消隱藏狀態 
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public void cancelHide(long adminId, long hiddenId) {
		
			Hidden hidden = this.hiddenDAO.find(hiddenId);
			Admin modifier = this.adminDAO.find(adminId);
			String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
			hidden.setEndDate(date);
			hidden.setDropDate(date);
			hidden.setModifier(modifier);
			this.hiddenDAO.update(hidden);		
		}
		
		//檢舉管理-確認隱藏狀態
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public void comfirmHide(long adminId, long hiddenId) {
			Hidden hidden = this.hiddenDAO.find(hiddenId);
			Admin modifier = this.adminDAO.find(adminId);
			String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
			hidden.setModifier(modifier);
			hidden.setModifyDate(date);
			hidden.setCreateUser(modifier);
			this.hiddenDAO.update(hidden);		
		}
		
		//檢舉管理-查詢已被隱藏的專輯(起始查詢)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public ArrayList queryAlreadyHide() {
			System.out.println("查詢已被隱藏的專輯");		
			String nowDate = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
			Integer year = Integer.valueOf(nowDate.substring(0,4)); //year轉型為Integer，以便查詢。
			Integer month = Integer.valueOf(nowDate.substring(4,6));//month轉型為Integer，以便查詢。
			System.out.println("year="+year+", month="+month);
			Query query = this.sessionFactory.getCurrentSession().createQuery("select a from Hidden h join h.album a where (h.endDate is null) and (h.createUser!= 1) and (year(h.createDate) in (:year)) and (month(h.createDate) in (:month))");
			query.setParameter("year", year);
			query.setParameter("month", month);
			
			List<Album> albums = (List<Album>)query.list();

			ArrayList list = new ArrayList();
			list.add(albums);
			//list.add(songs);
			return list;
		}

		//檢舉管理-查詢已被隱藏的專輯/歌曲(根據查詢條件)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public ArrayList queryAlreadyHideByDate(String type, String year, String month,
				String creator) {
			System.out.println("type===="+type);
			StringBuffer tempQuery = new StringBuffer();
			ArrayList list = new ArrayList();
			
			if (type.equals("1")){ //type=1為專輯			
				tempQuery.append("select a from Hidden h join h.album a where (h.endDate is null) and (h.createUser.id != '1')");
				
				if (!year.isEmpty()&&!month.isEmpty()){
					tempQuery.append("and ((year(h.createDate) in (:year)) and (month(h.createDate) in (:month))) ");
				}
				if (!creator.isEmpty()){
					tempQuery.append("and a.creator.userName in (:creator) ");
				}	
				
				Query query = this.sessionFactory.getCurrentSession().createQuery(tempQuery.toString());
			
				if (!year.isEmpty()&&!month.isEmpty()){
					Integer y = Integer.valueOf(year); //year轉型為Integer，以便查詢。
					Integer m = Integer.valueOf(month); //month轉型為Integer，以便查詢。
					System.out.println("=============="+y);
					System.out.println("=============="+m);
					query.setParameter("year", y);
					query.setParameter("month", m);
				}
				if (!creator.isEmpty()){
					query.setParameter("creator", creator);
				}
				List<Album> albums = (List<Album>)query.list();
				 list.add(albums);
				
			}else{
				
				tempQuery.append("select s from Hidden h join h.song s where (h.endDate is null) and (h.createUser.id != '1') ");
				
				if (!year.isEmpty()&&!month.isEmpty()){
					tempQuery.append("and ((year(h.createDate) in (:year)) and (month(h.createDate) in (:month))) ");
				}
				if (!creator.isEmpty()){
					tempQuery.append("and s.album.creator.userName in (:creator) ");
				}	
				
				Query query = this.sessionFactory.getCurrentSession().createQuery(tempQuery.toString());
			
				if (!year.isEmpty()&&!month.isEmpty()){
					Integer y = Integer.valueOf(year); //year轉型為Integer，以便查詢。
					Integer m = Integer.valueOf(month); //month轉型為Integer，以便查詢。
					System.out.println("=============="+y);
					System.out.println("=============="+m);
					query.setParameter("year", y);
					query.setParameter("month", m);
				}
				if (!creator.isEmpty()){
					query.setParameter("creator", creator);
				}
				List<Song> songs = (List<Song>)query.list();
				list.add(songs);
			}
			return list;
		}

		//檢舉管理-查詢會員檢舉清單 
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public Offense[] queryOffenseByUser(long userId) {
			Query query = this.sessionFactory.getCurrentSession().createQuery("from Offense o where o.member.id = :userId");
			query.setParameter("userId", userId);
			List<Offense> offenseSet = (List<Offense>)query.list();
			Offense[] offenseList = offenseSet.toArray(new Offense[offenseSet.size()]);
			return offenseList;
		}
		
		//會員詳細資料頁-該會員被檢舉的專輯清單
		public ArrayList queryOffenseAlbumForUser(long userId){
			ArrayList list = new ArrayList();
			
			Query query = this.sessionFactory.getCurrentSession().createQuery("select distinct b from Creator a join a.album b, Offense d where a.id = :userId and d.album.pid=b");
			query.setParameter("userId", userId);
			List<Album> resultList = (List<Album>)query.list();
			Album[] raList = new Album[resultList.size()];
	    	Long[] raList2 = new Long[resultList.size()];
			System.out.println("CreatorAlbum==>"+resultList.size());
				int j=0;
				for (Album mc2:resultList) {
					ArrayList list2 = new ArrayList();
					raList[j]=mc2;
					list2.add(mc2);             
					System.out.println("Album==>"+raList[j].getName());
					
					Query query2 = this.sessionFactory.getCurrentSession().createQuery("select count(a.album.pid) from Offense a where a.album.pid=:id");
					query2.setLong("id", raList[j].getPid());
					raList2[j]=(Long)query2.uniqueResult();
					System.out.println("AlbumAmount==>"+raList2[j]);
					list2.add(raList2[j]);                                            //被檢舉數
					list.add(list2);
					
					j++;
				}
			
			return list;
		}
				
		//會員詳細資料頁-該會員被檢舉的歌曲清單
		public ArrayList queryOffenseSongForUser(long userId){
			ArrayList list = new ArrayList();
			
			Query query = this.sessionFactory.getCurrentSession().createQuery("select distinct c from Creator a join a.album b join b.songSet c, Offense d where a.id = :userId and d.song.pid=c");
			query.setParameter("userId", userId);
			List<Song> resultList = (List<Song>)query.list();
			Song[] raList = new Song[resultList.size()];
	    	Long[] raList2 = new Long[resultList.size()];
			System.out.println("CreatorAlbum==>"+resultList.size());
				int j=0;
				for (Song mc2:resultList) {
					ArrayList list2 = new ArrayList();
					raList[j]=mc2;
					list2.add(mc2);             
					System.out.println("song==>"+raList[j].getName());
					
					Query query2 = this.sessionFactory.getCurrentSession().createQuery("select count(a.song.pid) from Offense a where a.song.pid=:id");
					query2.setLong("id", raList[j].getPid());
					raList2[j]=(Long)query2.uniqueResult();
					System.out.println("AlbumAmount==>"+raList2[j]);
					list2.add(raList2[j]);                                            //被檢舉數
					list.add(list2);
					
					j++;
				}
			
			return list;
		}


		//商品管理-查詢所有商品類別 
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public ProductionClassification[] queryProductionClassification() {
			ProductionClassification[] pClassificationList= this.productionClassificationDAO.findAll();	
			return pClassificationList;
		}
		
		//商品管理-查詢單一商品類別 
		public ProductionClassification queryOneProductionClassification(long productionClassificationId){
			ProductionClassification productionClassification = this.productionClassificationDAO.find(productionClassificationId);
			return productionClassification;
		}

		//商品管理-新增商品類別
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public void addProductionClassification(long adminId, String productionClassificationName) {
			String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");		
			ProductionClassification productionClassification = new ProductionClassification(); //新增一個商品分類
			productionClassification.setCreateUser(String.valueOf(adminId));
			productionClassification.setCreateDate(date);
			productionClassification.setName(productionClassificationName);
			this.productionClassificationDAO.save(productionClassification); //儲存
		}

		//商品管理-編輯商品類別
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public void editProductionClassification(long adminId, long productionClassificationId,
				String productionClassificationName) {
			
			String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");		
			ProductionClassification productionClassification = this.productionClassificationDAO.find(productionClassificationId);
			productionClassification.setName(productionClassificationName);
			productionClassification.setModifier(String.valueOf(adminId));
			productionClassification.setModifyDate(date);
			this.productionClassificationDAO.update(productionClassification);
			
		}

		//商品管理-刪除商品類別
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public ProductionClassification[] deleteProductionClassification(long adminId, long productionClassificationId) {
			
			String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
			
			ProductionClassification productionClassification = this.productionClassificationDAO.find(productionClassificationId);
			productionClassification.setModifier(String.valueOf(adminId));
			productionClassification.setDropDate(date);
			this.productionClassificationDAO.update(productionClassification);
			
			ProductionClassification[] pClassificationList= this.queryProductionClassification();
			return pClassificationList;
		}
		
		//商品管理-查詢商品
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public ArrayList queryProduct() {
			
			ProductionClassification[] proClassList = this.productionClassificationDAO.findAll();
			ArrayList list = new ArrayList();
			List emptyList = new ArrayList();		
			list.add(proClassList);
			list.add(emptyList);
			return  list;
		}


		//商品管理-查詢商品資訊
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public ArrayList queryProductInfo(long productionClassificationId) {
			
			ArrayList list = new ArrayList();
			String type="";
			
			ProductionClassification[] proClassList = this.productionClassificationDAO.findAll();
			list.add(proClassList);
			
			Query query = this.sessionFactory.getCurrentSession().createQuery("from SDCard sd where sd.productionClassification.id = :productionClassificationId");
			query.setParameter("productionClassificationId", productionClassificationId);
			
			if(query.list().isEmpty()){
				type = "PrePaid";
				Query query2 = this.sessionFactory.getCurrentSession().createQuery("from PrePaid p where p.productionClassification.id = :productionClassificationId");
				query2.setParameter("productionClassificationId", productionClassificationId);
				List<PrePaid> prePaidList = (List<PrePaid>)query2.list();
				list.add(prePaidList);
				list.add(type);
			}else{
				type = "SDCard";
				List<SDCard> sdCardList= (List<SDCard>)query.list();
				list.add(sdCardList);
				list.add(type);
				
			}
			return list;
			
			
		}

		//商品管理-批次更新商品
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public void updateProductBatch(long adminId, long productionClassificationId, String condition, double rate) { //condition為選擇的條件(1:定價 2:贈送點數)
			String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
			int sdPrice = 0;
			int sdReward = 0;
			int prePaidPrice = 0;
			int prePaidReward = 0 ;
			String finalPrice="";
			String finalReward="";
			ArrayList list = this.queryProductInfo(productionClassificationId);
			
			if(list.get(2).equals("SDCard")){ 
				List<SDCard> sdCardList= (List<SDCard>) list.get(1);
				if(condition.equals("1")){
					for (SDCard s:sdCardList) {
						if(s.getSdCardPrice().getPrice().isEmpty()){
							sdPrice=0;
						}else{
							sdPrice = Integer.valueOf(s.getSdCardPrice().getPrice());
						}
						System.out.println(sdPrice);
						BigDecimal temp = new BigDecimal(sdPrice*rate);
						finalPrice = String.valueOf(temp.setScale(0, RoundingMode.HALF_UP));
						s.getSdCardPrice().setPrice(finalPrice);
						s.setModifier(String.valueOf(adminId));
						s.setModifyDate(date);
						this.sdCardDAO.update(s);
					}
				}else if(condition.equals("2")){
					for (SDCard s:sdCardList) {
						BigDecimal temp = new BigDecimal(rate);
						finalReward = String.valueOf(temp.setScale(0, RoundingMode.HALF_UP));
						s.setReward(finalReward);
						s.setModifier(String.valueOf(adminId));
						s.setModifyDate(date);
						this.sdCardDAO.update(s);
					}
				}
			}else if(list.get(2).equals("PrePaid")){	//代表list為List<PrePaid>
				List<PrePaid> prePaidList= (List<PrePaid>) list.get(1);
				if(condition.equals("1")){
					for (PrePaid s:prePaidList) {
						if(s.getPrePaidPrice().getPrice().isEmpty()){
							prePaidPrice=0;
						}else{
							prePaidPrice = Integer.valueOf(s.getPrePaidPrice().getPrice());
						}
						BigDecimal temp = new BigDecimal(prePaidPrice*rate);
						finalPrice = String.valueOf(temp.setScale(0, RoundingMode.HALF_UP));
						s.getPrePaidPrice().setPrice(finalPrice);
						s.setModifier(String.valueOf(adminId));
						s.setModifyDate(date);
						this.prePaidDAO.update(s);
					}
				}else if(condition.equals("2")){
					for (PrePaid s:prePaidList) {			
						BigDecimal temp = new BigDecimal(rate);
						finalReward = String.valueOf(temp.setScale(0, RoundingMode.HALF_UP));
						s.setReward(finalReward);
						s.setModifier(String.valueOf(adminId));
						s.setModifyDate(date);
						this.prePaidDAO.update(s);
					}
				}
			}
		}

		//商品管理-查詢商品細節 
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public ArrayList queryProductDetail(long productId) {
			ArrayList list = new ArrayList();		
			String type="";
			Query query = this.sessionFactory.getCurrentSession().createQuery("FROM ProductionCategory p where p.pid = :productId");
			query.setParameter("productId", productId);
			Object product = query.uniqueResult();
			if (product instanceof SDCard) {
				type = "SDCard";
				SDCard sdCard = (SDCard)product;
				list.add(sdCard);
				list.add(type);
				
			}else{
				type = "PrePaid";
				PrePaid prePaid = (PrePaid)product;
				list.add(prePaid);
				list.add(type);			
			}
			
			ProductionClassification[] proClassList = this.productionClassificationDAO.findAll();
			list.add(proClassList);
			
			return list;
		}
		
		//商品管理-更新商品資訊(查詢商品細節頁) //只有SD卡和儲值的部分
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public long updateProduct(long adminId, long productId, String productName,
				long newProductionClassificationId, String transactionType,
				String realPrice, String specialPrice, String discountPrice, String discountBonus, String giveBonus,
				String stock, String status, String keyword, String memo,
				long productionClassificationId) {
			
			int rPrice = Integer.valueOf(realPrice);
			String sPrice = ""; //特惠價計算
			if(!specialPrice.isEmpty()){ //若特惠價欄位不為空才計算。
				double sRate = Double.valueOf(specialPrice);		
				BigDecimal temp = new BigDecimal(rPrice*sRate);
				sPrice = String.valueOf(temp.setScale(0, RoundingMode.HALF_UP));
			}
			String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
			ProductionClassification newProductionClassification = this.productionClassificationDAO.find(newProductionClassificationId);
			Query query = this.sessionFactory.getCurrentSession().createQuery("FROM ProductionCategory p where p.pid = :productId");
			query.setParameter("productId", productId);
			Object product = query.uniqueResult();
			
			if (product instanceof SDCard) {			
				SDCard sdCard = (SDCard)product;
				sdCard.setModifier(String.valueOf(adminId));
				sdCard.setModifyDate(date);
				sdCard.setName(productName);
				sdCard.setProductionClassification(newProductionClassification);
				sdCard.setTransactionType(transactionType);
				sdCard.getSdCardPrice().setPrice(realPrice);			
				sdCard.getSdCardPrice().setSpecialPrice(sPrice);
				sdCard.getSdCardPrice().setDiscountPrice(discountPrice);
				sdCard.getSdCardPrice().setDiscountBonus(discountBonus);
				sdCard.setReward(giveBonus);
				sdCard.setAmount(stock);
				sdCard.setStatus(status);
				sdCard.setKeyWord(keyword);
				sdCard.setIntroduction(memo);
				this.sdCardDAO.update(sdCard);			
			}else if (product instanceof PrePaid) {
				PrePaid prePaid = (PrePaid)product;
				prePaid.setModifier(String.valueOf(adminId));
				prePaid.setModifyDate(date);
				prePaid.setName(productName);
				prePaid.setProductionClassification(newProductionClassification);
				prePaid.setTransactionType(transactionType);
				prePaid.getPrePaidPrice().setPrice(realPrice);			
				prePaid.getPrePaidPrice().setSpecialPrice(sPrice);
				prePaid.getPrePaidPrice().setDiscountPrice(discountPrice);
				prePaid.getPrePaidPrice().setDiscountBonus(discountBonus);
				prePaid.setReward(giveBonus);
				prePaid.setAmount(stock);
				prePaid.setStatus(status);
				prePaid.setKeyWord(keyword);
				prePaid.setIntroduction(memo);
				this.prePaidDAO.update(prePaid);			
			}		
			return productionClassificationId;
		}

		/**=====================kevin add===========================**/
		//會員
			@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
			public Member queryMember(long userID){
				
				//1. Member Bean
				Member user = memberDAO.find(userID);	
				return user;
			}
		
		//修改收件人資訊
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public Member addMemberData(long userID,Member member){
			  Member user = memberDAO.find(userID);	
			  user.setReceiver(member.getReceiver());
			  user.setCity(member.getCity());
			  user.setTel(member.getTel());
			  user.setAddress(member.getAddress());
			  return user;
		}
		
		//儲值轉訂單
		public Order purchasePrepaid(long userID,PrePaid prePaid,Order order){
			String createDate = DateFormatUtils.format(new Date(), "yyyyMMddhhmmss");
			Query query = this.sessionFactory.getCurrentSession().createQuery("from GsiMoney g where g.member.id =:v1 order by g.id desc");
			query.setParameter("v1", userID);
			List<GsiMoney> gsiMoneyList = (List<GsiMoney>)query.list();	
			GsiMoney gsiMoney = new GsiMoney();
			if(gsiMoneyList.size()>0){
				gsiMoney = gsiMoneyList.get(0);
			}
			gsiMoney.setCreateDate(createDate);
			gsiMoney.setOutgo(prePaid.getPrePaidPrice().getPrice());
			int totalPrice = Integer.valueOf(prePaid.getPrePaidPrice().getPrice());
			String balance = String.valueOf(Integer.valueOf(gsiMoney.getBalance()) - totalPrice) ;
			gsiMoney.setBalance(balance);
			gsiMoneyDAO.save(gsiMoney);
			
			
			OrderDetail detail = new OrderDetail();
			detail.setProductionCategory(prePaid);
			detail.setOrder(order);
			Set<OrderDetail> detailSet = new HashSet<OrderDetail>();
			detailSet.add(detail);
			
			order.setOrderDetail(detailSet);
			
			orderDAO.save(order);
			return order;
		}

		//SD卡轉訂單
		public Order purchaseSDCard(SDCard adCard,Order order,String amount){
			String createDate = DateFormatUtils.format(new Date(), "yyyyMMddhhmmss");
			//GsiMoney[] arGsiMoney = gsiMoneyList.toArray(new GsiMoney[gsiMoneyList.size()]);
			
			OrderDetail detail = new OrderDetail();
			detail.setAmount(amount);
			detail.setProductionCategory(adCard);
			detail.setOrder(order);
			Set<OrderDetail> detailSet = new HashSet<OrderDetail>();
			detailSet.add(detail);
				
			order.setOrderDetail(detailSet);
		    order.setCreateDate(createDate);
			orderDAO.save(order);
			return order;
		}
		
		
		/**
		 * 第二步:加入購物車*/
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public ArrayList addMusicShoppingCart(long userId, long productId,String useBonus) {
			
			Query query = this.sessionFactory.getCurrentSession().createQuery("from ShoppingCart s where s.member.id = :userID and s.dropDate is null");
			query.setLong("userID", userId);
			ShoppingCart cart = (ShoppingCart)query.uniqueResult();
			
			Member member = this.memberDAO.find(userId);	
			ProductionCategory productionCategory = this.productionCategoryDAO.find(productId);
			Set<ShoppingCartDetail> shoppingCartList = new HashSet<ShoppingCartDetail>();
			ShoppingCartDetail cartDetail = new ShoppingCartDetail();		
			
			if(cart == null){
				cart = new ShoppingCart();
				cart.setMember(member);
				cart.setCreateDate(DateFormatUtils.format(new Date(), "yyyyMMddhhmmss"));
				cart.setShoppingCartDetail(shoppingCartList);
				this.shoppingCartDAO.save(cart);
			}else{
				cart.setShoppingCartDetail(shoppingCartList);		
			}
		
			cartDetail.setShoppingCart(cart);
			cartDetail.setProductionCategory(productionCategory);
			cartDetail.setUseBonus(useBonus);
			
			shoppingCartList.add(cartDetail);
			this.shoppingCartDetailDAO.save(cartDetail);
			
			
			ArrayList list = new ArrayList();
			list.add(member);
			list.add(cart);	
			
			return list;
			
		}
		
		
		
		//購買專輯或歌曲
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public ArrayList queryMusicShoppingCart(long userId) {
			
			
			Query query = this.sessionFactory.getCurrentSession().createQuery("select d from ShoppingCart s join s.shoppingCartDetail d where s.member.id =:v1 and s.dropDate is null and d.dropDate is null order by s.id desc");
			query.setParameter("v1", userId);
				
			List<ShoppingCartDetail> detailList = (List<ShoppingCartDetail>)query.list();		
			//ShoppingCartDetail[] songDetail = new ShoppingCartDetail[detailList.size()] ;
			//ShoppingCartDetail[] anbumDetail = new ShoppingCartDetail[detailList.size()] ;
			ArrayList<ShoppingCartDetail> songlist = new ArrayList<ShoppingCartDetail>();
			ArrayList<ShoppingCartDetail> albumlist = new ArrayList<ShoppingCartDetail>();
			
			int i=0;
			int j=0;
			for(ShoppingCartDetail d : detailList){
				System.out.println("id===>"+d.getId());
				Object productionCategory = d.getProductionCategory();
				if(productionCategory instanceof Song){
					System.out.println("Song------");
					Song song = (Song)productionCategory;
					songlist.add(d);
					//songDetail[i] = d;
					//i++;
				}else if(productionCategory instanceof Album){
					System.out.println("Album------");
					Album album = (Album)productionCategory;
					albumlist.add(d);
					//anbumDetail[j] = d;
					//j++;
				}
				
			}
				
			//ShoppingCartDetail[] songDetail = new ShoppingCartDetail[detailList.size()] ;
		    //ShoppingCartDetail[] albumDetail = new ShoppingCartDetail[detailList.size()] ;
			ShoppingCartDetail[] songDetail = songlist.toArray(new ShoppingCartDetail[songlist.size()]);
			ShoppingCartDetail[] albumDetail = albumlist.toArray(new ShoppingCartDetail[albumlist.size()]);
			
			ArrayList<ShoppingCartDetail[]> list = new ArrayList<ShoppingCartDetail[]>();
			list.add(songDetail);
			list.add(albumDetail);
				//ShoppingCart newShoppingCart = shoppingCart.get(0);
				
				//Set<ShoppingCartDetail> detailSet = newShoppingCart.getShoppingCartDetail();
				
				//ShoppingCartDetail[] shoppingCartDetail = detailList.toArray(new ShoppingCartDetail[detailList.size()]);
				
				return list;
		}
			
			
		//確認購買，轉成訂單!
			@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
			public int purchaseConfirm(long shoppingCartId, String gsiMoney, String gsiBonus) {
				
				String date = DateFormatUtils.format(new Date(), "yyyyMMddhhmmss");
				System.out.println("shoppingCartId==>"+shoppingCartId);
				ShoppingCart s = this.shoppingCartDAO.find(shoppingCartId);
				//Set<ShoppingCartDetail> detailSet = s.getShoppingCartDetail();
				Query query = this.sessionFactory.getCurrentSession().createQuery(" from ShoppingCartDetail d where d.shoppingCart.id = :shoppingCartId and d.dropDate is null");
				query.setParameter("shoppingCartId", shoppingCartId);
				List<ShoppingCartDetail> detailSet = (List<ShoppingCartDetail>)query.list();	
				s.setDropDate(date);
				
				Member member = s.getMember();
				System.out.println("購買者人id==>"+member.getId());
				System.out.println("總金額==>"+gsiMoney);
				System.out.println("總Bonus==>"+gsiBonus);
				
				/**=================新增購買者的GsiMoney=================**/
				 query = this.sessionFactory.getCurrentSession().createQuery("from GsiMoney g where g.member.id =:v1 order by g.id desc");
				query.setParameter("v1", member.getId());
				String createDate = DateFormatUtils.format(new Date(), "yyyyMMddhhmmss");
				List<GsiMoney> userGsiMoneyList = (List<GsiMoney>)query.list();	
				GsiMoney userMoney = new GsiMoney();
				if(userGsiMoneyList.size()>0){
					userMoney = userGsiMoneyList.get(0);
				}
				
				GsiMoney gm = new GsiMoney();
				gm.setCreateDate(date);
				gm.setMember(member);
				gm.setOutgo(gsiMoney);
				int totalCost = Integer.valueOf(gsiMoney);
				String balance = String.valueOf(Integer.valueOf(userMoney.getBalance()) - totalCost) ;
				gm.setBalance(balance);
				//gm.setPurchase(gsiMoney);
				this.gsiMoneyDAO.save(gm);
				
				
				/**=================新增購買者的GsiBonus=================**/
				query = this.sessionFactory.getCurrentSession().createQuery("from GsiBonus g where g.member.id =:v1 order by g.id desc");
				query.setParameter("v1", member.getId());
				List<GsiBonus> userGsiBonusList = (List<GsiBonus>)query.list();	
				GsiBonus userBonus = new GsiBonus();
				if(userGsiBonusList.size()>0){
					userBonus = userGsiBonusList.get(0);
				}
				int totalUseBonus = Integer.valueOf(gsiBonus);
				String balanceBonus = String.valueOf(Integer.valueOf(userBonus.getBalance()) - totalUseBonus) ;
				GsiBonus gb = new GsiBonus();
				gb.setCreateDate(date);
				gb.setMember(member);
				gb.setPurchase(gsiBonus);
				gb.setBalance(balanceBonus);
				this.gsiBonusDAO.save(gb);
				
				
				//新增訂單
				Order order = new Order();
				order.setMember(member);
				order.setCreateDate(date);
				order.setPayDate(date);
				order.setPurchaseDate(date);
				order.setBillDate(date);
				order.setHandleStatus("1");
				order.setPayStatus("1");
				order.setPayMethod("1");
				order.setGsiMoney(gm);
				//order.setGsiBonus(gb);
				this.orderDAO.save(order);
				
				
				//找出不重複的creator
				Set<Long> setCreator = new HashSet<Long>();
				for (ShoppingCartDetail od:detailSet) {
					if(od.getProductionCategory() instanceof Song){
						Song song = (Song)od.getProductionCategory();
						long creatorId = song.getAlbum().getCreator().getId();
					    setCreator.add(creatorId);
					}else{
						Album album = (Album)od.getProductionCategory();
						setCreator.add(album.getCreator().getId());
					}
				}
				
				/**============ 找出創作人GsiMoney ============**/
				List<GsiMoney> tempList = new ArrayList<GsiMoney>(); 
				for(long creatorId : setCreator){
					query = this.sessionFactory.getCurrentSession().createQuery("from GsiMoney g where g.member.id =:v1 order by g.id desc");
					query.setParameter("v1", creatorId);
		
					List<GsiMoney> gsiMoneyList = (List<GsiMoney>)query.list();	
					GsiMoney creatorMoney = new GsiMoney();
					if(gsiMoneyList.size()>0){
						GsiMoney g = gsiMoneyList.get(0);
						creatorMoney.setBalance(g.getBalance());
						creatorMoney.setCreateUser(g.getMember().getIdentityName());
						creatorMoney.setExchangeDate(g.getExchangeDate());
						creatorMoney.setExchangeStatus(g.getExchangeStatus());
						creatorMoney.setExchangeTax(g.getExchangeTax());
						creatorMoney.setMember(g.getMember());
						creatorMoney.setMemo(g.getMemo());
						creatorMoney.setPaid(g.getPaid());
						creatorMoney.setPaid(g.getPaidDate());
						creatorMoney.setTransactionType(g.getTransactionType());
					}
					tempList.add(creatorMoney);
				}
				/**============ 找出創作人GsiMoney End============**/
				System.out.println("創作人GsiMoney===>"+tempList);
				
				
				
				/**============ 找出創作人GsiBonus ============**/
				List<GsiBonus> tempBonusList = new ArrayList<GsiBonus>(); 
				for(long creatorId : setCreator){
					query = this.sessionFactory.getCurrentSession().createQuery("from GsiBonus g where g.member.id =:v1 order by g.id desc");
					query.setParameter("v1", creatorId);
		
					List<GsiBonus> gsiBonusList = (List<GsiBonus>)query.list();	
					GsiBonus creatorBonus = new GsiBonus();
					if(gsiBonusList.size()>0){
						GsiBonus g = gsiBonusList.get(0);
						creatorBonus.setBalance(g.getBalance());
						creatorBonus.setMember(g.getMember());

					}
					tempBonusList.add(creatorBonus);
				}
				/**============ 找出創作人GsiBonus End============**/
				System.out.println("創作人GsiBonus===>"+tempBonusList);
				
				
				
				/**================新增訂單明細==================**/
				int tReward = 0; //回饋給購買者的Bonus
				for (ShoppingCartDetail od:detailSet) {
					
					OrderDetail orderDetail = new OrderDetail();			
					orderDetail.setOrder(order);
					orderDetail.setAmount("1");
					orderDetail.setProductionCategory(od.getProductionCategory());
					if(od.getProductionCategory() instanceof Song){
						Song song = (Song)od.getProductionCategory();
						long creatorId = song.getAlbum().getCreator().getId();
						//int price = Integer.valueOf(song.getSongPrice().getPrice());			
						
						//設定創作人的GsiMoney
						for(GsiMoney creatorMoney : tempList){
							long tempCreatorId = creatorMoney.getMember().getId();
							if(creatorId == tempCreatorId){
								int productIncome = Integer.valueOf(song.getSongPrice().getPrice());
								creatorMoney.setCreateDate(createDate);
								creatorMoney.setIncome(song.getSongPrice().getPrice());
								String b2 = String.valueOf(Integer.valueOf(creatorMoney.getBalance()) + productIncome) ;
								creatorMoney.setBalance(b2);
								order.setGsiMoney(creatorMoney);
							}
						}	
						
						//設定創作人的GsiBonus
						for(GsiBonus creatorBonus : tempBonusList){
							long tempCreatorId = creatorBonus.getMember().getId();
							if(creatorId == tempCreatorId){
								int reward = Integer.valueOf(song.getSongPrice().getCreatorReward());
								creatorBonus.setCreateDate(createDate);
								creatorBonus.setReward(song.getSongPrice().getCreatorReward());
								String b2 = String.valueOf(Integer.valueOf(creatorBonus.getBalance()) + reward) ;
								creatorBonus.setBalance(b2);
								order.setGsiBonus(creatorBonus);
							}
						}
						
						//是否使用Bonus購買
						if("N".equals(od.getUseBonus())){
							orderDetail.setGsiMoney(song.getSongPrice().getPrice());
						}else{
							orderDetail.setGsiMoney(song.getSongPrice().getDiscountPrice());
							orderDetail.setGsiBonus(song.getSongPrice().getDiscountBonus());
						}			
						tReward += Integer.valueOf(song.getSongPrice().getReward());
						System.out.println("tReward111===>"+tReward);
					}else if(od.getProductionCategory() instanceof Album){
						//Album album = (Album)od.getProductionCategory();
						//orderDetail.setGsiMoney(album.g)
						int tAlbumPrice = 0;
						int tAlbumBonus = 0;
						int tAlbumReward = 0;
						Album album = (Album)od.getProductionCategory();
						
						Set<Song> songSet = album.getSongSet();
						if("N".equals(od.getUseBonus())){
						     for(Song song : songSet){
						    	 tAlbumPrice += Integer.parseInt(song.getSongPrice().getPrice());
						    	 tAlbumReward += Integer.valueOf(song.getSongPrice().getReward()); 
						     }	
						     orderDetail.setGsiMoney(String.valueOf(tAlbumPrice));
						}else{
							 for(Song song : songSet){
								 tAlbumPrice += Integer.parseInt(song.getSongPrice().getDiscountPrice());
								 tAlbumBonus += Integer.parseInt(song.getSongPrice().getDiscountBonus());
								 tAlbumReward += Integer.valueOf(song.getSongPrice().getReward());
						     }	
							 orderDetail.setGsiMoney(String.valueOf(tAlbumPrice));
							 orderDetail.setGsiBonus(String.valueOf(tAlbumBonus));
						}
						
						long creatorId = album.getCreator().getId();
						//設定創作人的GsiMoney
						for(GsiMoney creatorMoney : tempList){
							long tempCreatorId = creatorMoney.getMember().getId();
							if(creatorId == tempCreatorId){
								creatorMoney.setCreateDate(createDate);
								creatorMoney.setIncome(String.valueOf(tAlbumPrice));
								String b2 = String.valueOf(Integer.valueOf(creatorMoney.getBalance()) + tAlbumPrice) ;
								System.out.println("最後創作人的GsiMoney==>"+b2);
								creatorMoney.setBalance(b2);
								order.setGsiMoney(creatorMoney);
							}
						}	
						
						//設定創作人的GsiBonus
						for(GsiBonus creatorBonus : tempBonusList){
							long tempCreatorId = creatorBonus.getMember().getId();
							if(creatorId == tempCreatorId){
								//int reward = Integer.valueOf(song.getSongPrice().getCreatorReward());
								creatorBonus.setCreateDate(createDate);
								creatorBonus.setReward(String.valueOf(tAlbumReward));
								String b2 = String.valueOf(Integer.valueOf(creatorBonus.getBalance()) + tAlbumReward) ;
								System.out.println("最後創作人的GsiBonus==>"+b2);
								creatorBonus.setBalance(b2);
								order.setGsiBonus(creatorBonus);
							}
						}		
						tReward += tAlbumReward;
						System.out.println("tReward222===>"+tReward);
					}
				
				    this.orderDetailDAO.save(orderDetail);
				}
				System.out.println("回饋給購買者的Bonus==>"+tReward);
				/**================新增訂單明細 End==================**/
				gb.setReward(""+tReward);
				
				//新增創作人GsiMoney
				Iterator<GsiMoney> it = tempList.iterator();
				while(it.hasNext()){
					gsiMoneyDAO.save(it.next());
				}
				Iterator<GsiBonus> it2 = tempBonusList.iterator();
				while(it2.hasNext()){
					gsiBonusDAO.save(it2.next());
				}
				return tReward;

			}
		
	    //變更價格
	    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public ArrayList updateUseBonus(long userID,long id,String useBonus){
			ShoppingCartDetail detail =this.shoppingCartDetailDAO.find(id);
			detail.setUseBonus(useBonus);
			
			return queryMusicShoppingCart(userID);
			
		}
	    
	    //刪除購物車
	    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public ArrayList deleteShoppingCart(long userId, long transactionId) {
			this.shoppingCartDetailDAO.delete(transactionId);
			//ShoppingCartDetail[] shoppingCartDetail = this.queryMusicShoppingCart(userId); 
			ArrayList list =  this.queryMusicShoppingCart(userId);
			return list;
		}
}
