package com.ubn.befamous.service.impl;

import java.io.File;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.time.DateFormatUtils;
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
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public SDCard[] querySDCard() {	
		SDCard[] sdList = this.sdCardDAO.findAll();		
		return sdList;		
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public PrePaid[] queryPrePaid() {		
		PrePaid[] pdList = this.prePaidDAO.findAll();
		return pdList;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ArrayList addShoppingCart(long userId, long productId, String amount) {
		
		Member member = this.memberDAO.find(userId);	
		ProductionCategory productionCategory = this.productionCategoryDAO.find(productId);
		Set<ShoppingCartDetail> sclist = new HashSet();
		ShoppingCartDetail cartDetail = new ShoppingCartDetail();
		sclist.add(cartDetail);
		
		ShoppingCart cart = new ShoppingCart();
		cart.setMember(member);
		cart.setCreateDate(DateFormatUtils.format(new Date(), "yyyyMMddhhmmss"));
		cart.setShoppingCartDetail(sclist);
		
		cartDetail.setShoppingCart(cart);
		cartDetail.setAmount(amount);
		cartDetail.setProductionCategory(productionCategory);
		this.shoppingCartDetailDAO.save(cartDetail);		
		
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
	public ShoppingCartDetail[] deleteShoppingCart(long userId, long transactionId) {
		this.shoppingCartDetailDAO.delete(transactionId);
		ShoppingCartDetail[] shoppingCartDetail = this.queryShoppingCart(userId); 		 
		return shoppingCartDetail;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ShoppingCartDetail[] forwardPurchase(long userId) {
		ShoppingCartDetail[] shoppingCartDetail = this.queryShoppingCart(userId); 		 
		return shoppingCartDetail;
	}

	//確認購買，轉成訂單!
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void purchaseConfirm(long shoppingCartId, String gsiMoney, String gsiBonus) {
		
		String date = DateFormatUtils.format(new Date(), "yyyyMMddhhmmss");
		
		ShoppingCart s = this.shoppingCartDAO.find(shoppingCartId);
		Set<ShoppingCartDetail> detailSet = s.getShoppingCartDetail();
		Member member = s.getMember();
		
		Order order = new Order();
		order.setMember(member);
		order.setCreateDate(date);
		order.setPayDate(date);
		order.setPurchaseDate(date);
		order.setBillDate(date);
		order.setHandleStatus("1");
		order.setPayStatus("1");
		order.setPayMethod("1");
		this.orderDAO.save(order);
		
		for (ShoppingCartDetail od:detailSet) {
			
			OrderDetail orderDetail = new OrderDetail();			
			orderDetail.setOrder(order);
			orderDetail.setAmount("1");
			orderDetail.setProductionCategory(od.getProductionCategory());
			orderDetail.setGsiMoney(gsiMoney);
			orderDetail.setGsiBonus(gsiBonus);
		
		this.orderDetailDAO.save(orderDetail);
		}
		
		GsiMoney gm = new GsiMoney();
		gm.setCreateDate(date);
		gm.setMember(member);
		gm.setPurchase(gsiMoney);
		this.gsiMoneyDAO.save(gm);
		
		GsiBonus gb = new GsiBonus();
		gb.setCreateDate(date);
		gb.setMember(member);
		gb.setPurchase(gsiBonus);
		this.gsiBonusDAO.save(gb);
		
	}


	//訂單管理-實體幣訂單
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Order[] queryCashTransRcd(String userEmail, long transactionId,
			String startDate, String endDate, String orderStatus) {
	
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ArrayList queryCashTransDetail(long transactionId) {
		
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean saveCashTransRcd(long transactionId, String billStatus,
			String payStatus, String processStatus, String memo1, String memo2) {
	
		return false;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Order queryDeliveryRcd(long transactionId) {
		
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ArrayList updateDeliveryRcd(long transactionId, String deliveryDate,
			String deliveryNo, String billNo) {
		
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ArrayList returnGoods(long transactionId) {
		
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ArrayList cancelOrder(long transactionId) {
		
		return null;
	}

	//訂單管理-虛擬幣訂單
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Order[] queryInventTransRcd(String userEmail, long transactionId,
			String startDate, String endDate, String orderStatus) {
	
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public OrderDetail queryInventTransDetail(long transactionId) {
		
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Order queryInventMemo(long transactionId) {
		
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean updateInventMemo(long transactionId, String memo1,
			String memo2) {
		
		return false;
	}

	//發票登錄-兌換金額管理
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public GsiMoney[] queryExchangeList(String year, String month) {
		
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public GsiMoney queryExchangeDetail(long gsiMoneyId) {
		
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public GsiMoney[] updateExchange(GsiMoney gsiMoney, String year,
			String month) {
		
		return null;
	}
	
	//發票登錄-訂單發票登錄
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Order[] queryBillRcd(String year, String month) {
		
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public OrderDetail queryBillDetail(long transactionId) {
		
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Order[] updateBill(Order order, String year, String month) {
		
		return null;
	}

	
	
	//GSiMoney&GSiBonus
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public GsiMoney queryGsiMoney(long userId) {
		
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ArrayList queryTransRcd(long userId, String year, String month) {
		
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public OrderDetail[] queryTransDetail(long transactionId) {
	
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ArrayList applyChange(long userId) {
		
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public boolean Savechange(Member member, String money) {
		
		return false;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public GsiMoney[] queryExchangeLog(long userId, String year, String month) {
	
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Order[] queryPrePaidRcd(long userId, String year, String month) {
		
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Order[] queryBuyLogRcd(long userId, String year, String month) {
		
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public OrderDetail[] queryBuyLogDetail(long transactionId) {
		
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ArrayList queryBonusDetail(long userId) {
	
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Order[] queryCashRcd(long userId, String status, long transactionId) {
	
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public OrderDetail[] queryCashRcdDetail(long transactionId) {
	
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void downloadReturnList() {
		
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
			offense.setOffenseStatus("1");
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
			
			Query query = this.sessionFactory.getCurrentSession().createQuery("select a, COUNT(a) from Offense o join o.album a left join a.hidden h where h is null group by a order by o.createDate desc");
			List<Object[]> albums = (List<Object[]>)query.list();
			
			Query query2 = this.sessionFactory.getCurrentSession().createQuery("select s, COUNT(s) from Offense o join o.song s left join s.hidden h where h is null group by s order by o.createDate desc");
			List<Song> songs = (List<Song>)query2.list();
			
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
			
			Query query = this.sessionFactory.getCurrentSession().createQuery("select a, COUNT(o) from Album a join a.offense o left join a.hidden h where (o.modifier is null) and (h.endDate is null) and (h.createUser.id='1')");
			List<Object[]> albums = (List<Object[]>)query.list();
			
			//Query query2 = this.sessionFactory.getCurrentSession().createQuery("select s, COUNT(o) from Song s join s.offense o left join s.hidden h where (o.modifier is null) and (h.endDate is null) and (h.createUser.id='1')");
			//List<Song> songs = (List<Song>)query2.list();
			
			ArrayList list = new ArrayList();
			list.add(albums);
			//list.add(songs);
			return list;
		}

		//檢舉管理-查詢被系統自動隱藏的專輯/歌曲(根據查詢條件)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public ArrayList queryAutoHideByDate(String type, String year, String month,
				String creator) {		
			List albums = new ArrayList();
			List songs = new ArrayList();
			StringBuffer tempQuery = new StringBuffer();
			ArrayList list = new ArrayList();
			
			if (type.equals("1")){ //type=1為專輯		
				
				tempQuery.append("select a, COUNT(o) from Album a join a.offense o left join a.hidden h where (o.modifier is null) and (h.endDate is null) and (h.createUser.id='1')");
				
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
				 albums = (List<Album>)query.list();
				 list.add(albums);
				
			}else{
				
				tempQuery.append("select s, COUNT(o) from Song s join s.offense o left join s.hidden h where (o.modifier is null) and (h.endDate is null) and (h.createUser.id='1')");
				
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
				songs = (List<Song>)query.list();
				list.add(songs);
			}
			return list;
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
			Query query = this.sessionFactory.getCurrentSession().createQuery("select a, COUNT(o) from Album a " +
					"join a.hidden h left join a.offense o where (h.endDate is null) and (h.createUser!= 1) and" +
					" (o.modifier is null) and (year(h.createDate) in (:year)) and (month(h.createDate) in" +
					" (:month)) order by a");
			query.setParameter("year", year);
			query.setParameter("month", month);
			
			List<Object[]> albums = (List<Object[]>)query.list();
			
			/*Query query2 = this.sessionFactory.getCurrentSession().createQuery("select s, COUNT(o) from Hidden h join h.song s left join s.offense o where h.endDate is null and h.createUser!='1' and ((year(h.createDate) in (:year)) and (month(h.createDate) in (:month))) ");
			query2.setParameter("year", year);
			query2.setParameter("month", month);	
			List<Song> songs = (List<Song>)query2.list();*/		
			ArrayList list = new ArrayList();
			list.add(albums);
			//list.add(songs);
			return list;
		}

		//檢舉管理-查詢已被隱藏的專輯/歌曲(根據查詢條件)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public ArrayList queryAlreadyHideByDate(String type, String year, String month,
				String creator) {		
			List albums = new ArrayList();
			List songs = new ArrayList();
			StringBuffer tempQuery = new StringBuffer();
			ArrayList list = new ArrayList();
			
			if (type.equals("1")){ //type=1為專輯		
				
				tempQuery.append("select a, COUNT(o) from Album a " +
					"join a.hidden h left join a.offense o where (h.endDate is null) and (h.createUser!= 1) and" +
					" (o.modifier is null) ");
				
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
				 albums = (List<Album>)query.list();
				 list.add(albums);
				
			}else{
				
				tempQuery.append("select s, COUNT(o) from Song s " +
					"join s.hidden h left join s.offense o where (h.endDate is null) and (h.createUser!= 1) and" +
					" (o.modifier is null) ");
				
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
				songs = (List<Song>)query.list();
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

		
		//商品管理-新增商品 //尚未parseEXCEL部分
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public void addProduct(File file) {
			
		}

		//商品管理-查詢商品類別 
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public ProductionClassification[] queryProductionClassification() {
			ProductionClassification[] pClassificationList= this.productionClassificationDAO.findAll();	
			return pClassificationList;
		}

		//商品管理-新增商品類別
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public void addProductionClassification(String productionClassificationName) {
			String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
			
			ProductionClassification productionClassification = new ProductionClassification(); //新增一個商品分類
			productionClassification.setCreateDate(date);
			productionClassification.setName(productionClassificationName);
			this.productionClassificationDAO.save(productionClassification); //儲存
		}

		//商品管理-編輯商品類別
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public void editProductionClassification(long productionClassificationId,
				String productionClassificationName) {
			
			String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
			
			ProductionClassification productionClassification = this.productionClassificationDAO.find(productionClassificationId);
			productionClassification.setName(productionClassificationName);
			productionClassification.setModifyDate(date);
			this.productionClassificationDAO.update(productionClassification);
			
		}

		//商品管理-刪除商品類別
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public ProductionClassification[] deleteProductionClassification(long productionClassificationId) {
			
			String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
			
			ProductionClassification productionClassification = this.productionClassificationDAO.find(productionClassificationId);
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
		public void updateProductBatch(long productionClassificationId, String condition, double rate) { //condition為選擇的條件(1:定價 2:贈送點數)
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
						sdPrice = Integer.valueOf(s.getSdCardPrice().getPrice());
						System.out.println(sdPrice);
						BigDecimal temp = new BigDecimal(sdPrice*rate);
						finalPrice = String.valueOf(temp.setScale(0, RoundingMode.HALF_UP));
						s.getSdCardPrice().setPrice(finalPrice);
						this.sdCardDAO.update(s);
					}
				}else if(condition.equals("2")){
					for (SDCard s:sdCardList) {
						sdReward = Integer.valueOf(s.getReward());
						BigDecimal temp = new BigDecimal(sdReward*rate);
						finalReward = String.valueOf(temp.setScale(0, RoundingMode.HALF_UP));
						s.setReward(finalReward);
						this.sdCardDAO.update(s);
					}
				}
			}else if(list.get(2).equals("PrePaid")){	//代表list為List<PrePaid>
				List<PrePaid> prePaidList= (List<PrePaid>) list.get(1);
				if(condition.equals("1")){
					for (PrePaid s:prePaidList) {
						prePaidPrice = Integer.valueOf(s.getPrePaidPrice().getPrice());
						
						finalPrice = String.valueOf(prePaidPrice*rate);
						s.getPrePaidPrice().setPrice(finalPrice);
						this.prePaidDAO.update(s);
					}
				}else if(condition.equals("2")){
					for (PrePaid s:prePaidList) {
						prePaidReward = Integer.valueOf(s.getReward());					
						finalReward = String.valueOf(prePaidReward*rate);
						s.setReward(finalReward);
						this.prePaidDAO.update(s);
					}
				}
			}
		}

		//商品管理-查詢商品細節 
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public ArrayList queryProductDetail(long productId) {
			ArrayList list = new ArrayList();		
			
			Query query = this.sessionFactory.getCurrentSession().createQuery("FROM ProductionCategory p where p.pid = :productId");
			query.setParameter("productId", productId);
			Object product = query.uniqueResult();
			if (product instanceof SDCard) {
				SDCard sdCard = (SDCard)product;
				list.add(sdCard);
			}else{
				PrePaid prePaid = (PrePaid)product;
				list.add(prePaid);
			}
			
			ProductionClassification[] proClassList = this.productionClassificationDAO.findAll();
			list.add(proClassList);
			
			return list;
		}


		/*//商品管理-更新商品資訊(查詢商品資訊頁) (修改者Modifier未加入)
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public long saveModify(long productId, String realPrice,
				String specialPrice, String status, long productionClassificationId) {
			String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
			
			Query query = this.sessionFactory.getCurrentSession().createQuery("FROM ProductionCategory p where p.pid = :productId");
			query.setParameter("productId", productId);
			Object product = query.uniqueResult();
			
			if (product instanceof SDCard) {			
				SDCard sdCard = (SDCard)product;
				sdCard.setModifyDate(date);
				sdCard.getSdCardPrice().setPrice(realPrice);			
				sdCard.setStatus(status);
				this.sdCardDAO.update(sdCard);	
			}
			return productionClassificationId;
		}*/

		
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

}
