package com.ubn.befamous.service.impl;

import java.util.*;

import javax.persistence.PrimaryKeyJoinColumn;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;


import com.ubn.befamous.dao.IBaseDao;
import com.ubn.befamous.entity.Ad;
import com.ubn.befamous.entity.AdType;
import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.Audition;
import com.ubn.befamous.entity.Creator;
import com.ubn.befamous.entity.DownloadList;
import com.ubn.befamous.entity.Fan;
import com.ubn.befamous.entity.Friend;
import com.ubn.befamous.entity.GeneralMember;
import com.ubn.befamous.entity.GsiBonus;
import com.ubn.befamous.entity.GsiMoney;
import com.ubn.befamous.entity.LikeCreator;
import com.ubn.befamous.entity.Member;
import com.ubn.befamous.entity.News;
import com.ubn.befamous.entity.Offense;
import com.ubn.befamous.entity.PrePaid;
import com.ubn.befamous.entity.PrePaidPrice;
import com.ubn.befamous.entity.ProductionCategory;
import com.ubn.befamous.entity.ProductionClassification;
import com.ubn.befamous.entity.SDCard;
import com.ubn.befamous.entity.SDCardPrice;
import com.ubn.befamous.entity.Song;
import com.ubn.befamous.service.PersonService;

@Service("personService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PersonServiceImpl implements PersonService{
	
	public PersonServiceImpl() {
	}
	
	@Autowired
	private  SessionFactory sessionFactory;
	
	@Autowired
	@Qualifier("adDAO")
	private IBaseDao<Ad, Long> adDAO;
	
	@Autowired
	@Qualifier("adTypeDAO")
	private IBaseDao<AdType, Long> adTypeDAO;
	
	@Autowired
	@Qualifier("auditionDAO")
	private IBaseDao<Audition, Long> auditionDAO;
	
	@Autowired
	@Qualifier("memberDAO")
	private IBaseDao<Member, Long> memberDAO;
	
	@Autowired
	@Qualifier("generalMemberDAO")
	private IBaseDao<GeneralMember, Long> generalMemberDAO;
	
	@Autowired
	@Qualifier("creatorDAO")
	private IBaseDao<Creator, Long> creatorDAO;
	
	
	
	@Autowired
	@Qualifier("downloadListDAO")
	private IBaseDao<DownloadList, Long> downloadListDAO;
	
	@Autowired
	@Qualifier("fanDAO")
	private IBaseDao<Fan, Long> fanDAO;
	
	@Autowired
	@Qualifier("likeCreatorDAO")
	private IBaseDao<LikeCreator, Long> likeCreatorDAO;
	
	@Autowired
	@Qualifier("albumDAO")
	private IBaseDao<Album, Long> albumDAO;
	
	@Autowired
	@Qualifier("offenseDAO")
	private IBaseDao<Offense, Long> offenseDAO;
	
	
	@Autowired
	@Qualifier("productionClassificationDAO")
	private IBaseDao<ProductionClassification, Long> productionClassificationDAO;
	
	@Autowired
	@Qualifier("prePaidDAO")
	private IBaseDao<PrePaid, Long> prePaidDAO;
	
	@Autowired
	@Qualifier("prePaidPriceDAO")
	private IBaseDao<PrePaidPrice, Long> prePaidPriceDAO;
	
	@Autowired
	@Qualifier("songDAO")
	private IBaseDao<Song, Long> songDAO;

	@Autowired
	@Qualifier("sdCardDAO")
	private IBaseDao<SDCard, Long> sdCardDAO;
	
	@Autowired
	@Qualifier("sdCardPriceDAO")
	private IBaseDao<SDCardPrice, Long> sdCardPriceDAO;
	
	@Autowired
	@Qualifier("friendDAO")
	private IBaseDao<Friend, Long> friendDAO;
	
	//會員profile頁
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public GeneralMember queryMemberData(long userID){
		
		//1. Member Bean
		GeneralMember generalMember = (GeneralMember) generalMemberDAO.find(userID);
		Set<Friend> friend = generalMember.getFriend();
		System.out.println("friend==>"+friend);
		return generalMember;
	}
	
	//查詢一般會員最近動態
	public ArrayList queryMemberRecentAction(long userID){
		ArrayList list = new ArrayList();
		//1.最近喊讚的專輯清單
		Album[] arAlbum = albumDAO.findAll();
		list.add(arAlbum);
				
		//2.最近喊讚的歌曲清單
		Song[] arSong = songDAO.findAll();
		list.add(arSong);
				
		//3.最近試聽的專輯清單
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(" from Song s join s.audition an where s.album.creator.id = :userID and an.createDate is not null ");
		query.setLong("userID", userID);
		List<Song> auditionSongList = (List<Song>)query.list();
				
		Song[] arAuditionSong = auditionSongList.toArray(new Song[auditionSongList.size()]);
		System.out.println("arAuditionSong==>"+arAuditionSong);
		list.add(arAuditionSong);
				
		return list;
	}
	
	
	public Member queryBriefIntroduction(long userID){
		Member member = (Member) memberDAO.find(userID);
		return member;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void addFriend(long addMemberID ,long userID,String message){
		String pattern = "yyyy-MM-dd";
		String inviteDate = DateFormatUtils.format(new Date(), pattern);
		Member memberUser = (Member) memberDAO.find(userID);
		Member memberAddMember = (Member) memberDAO.find(addMemberID);
		
		Friend friend = new Friend();
		Set<Friend> setFriend = new HashSet<Friend>();
		friend.setInviter(memberUser);
		friend.setFriend(memberAddMember);
		friend.setInviteDate(inviteDate);
		setFriend.add(friend);
		memberUser.setFriend(setFriend);
		memberAddMember.setFriend(setFriend);
		friendDAO.save(friend);
		
		//System.out.println("member birthday1=>"+memberUser.getBirthday());
		//memberUser.setFriend(setFriend);
		//memberUser.setBirthday("19800202");
		//System.out.println("member birthday2=>"+memberUser.getBirthday());
		//memberDAO.update(memberUser);
		
		//session.update(member);
		//session.save(friend);
		
	}
	
	public ArrayList queryFriendsCreators(long userID){
		
		//1. 好友清單
		GeneralMember member = (GeneralMember) generalMemberDAO.find(userID);
		Set<Friend> FriendSet = member.getFriend();
		Friend[] arFriend = FriendSet.toArray(new Friend[FriendSet.size()]);
		
		//2.追蹤中的創作人清單
		Set<LikeCreator> LikeCreatorSet = member.getLikeCreator();
		LikeCreator[] arLikeCreator = LikeCreatorSet.toArray(new LikeCreator[LikeCreatorSet.size()]);

		ArrayList list = new ArrayList();
		list.add(arFriend);
		list.add(arLikeCreator);
		return list;
	}
	
	public ArrayList queryCreatorData(long userID){
		
		ArrayList list = new ArrayList();
		
		//1.創作人
		Creator creator = (Creator) creatorDAO.find(userID);
		list.add(creator);
		
		//2.推薦創作人清單 ??
		list.add(creator);
		
		//3.最新專輯
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(" from Album a where a.creator.id = :userID order by a.createDate desc");
		query.setLong("userID", userID);
		List<Album> newAlbumList = (List<Album>)query.list();
		if(newAlbumList.size()>0){
			Album[] arAlbum = newAlbumList.toArray(new Album[newAlbumList.size()]);
			list.add(arAlbum);
		}
		
		return list;
	}
	
	//查詢所有專輯
	public ArrayList queryAllCreatorAlbum(long userID){
		
		/*Set<Audition> AudSet = new HashSet<Audition>();
		Audition aa = new Audition();
		aa.setCreateUser("1");
		AudSet.add(aa);
		
        Song ss = (Song)songDAO.find(1l);
		ss.setAudition(AudSet);
		auditionDAO.save(aa);
		*/
		ArrayList list = new ArrayList();
		Session session = sessionFactory.getCurrentSession();
		Creator creator = (Creator) creatorDAO.find(userID);
		//所有專輯清單
		Set<Album> albumSet = creator.getAlbum();
		Album[] arAlbum = albumSet.toArray(new Album[albumSet.size()]);
		list.add(arAlbum);
		
		//最受歡迎歌曲(創作人被試聽最多次 取top10)??
		Query query = session.createQuery("select s from Song s join s.audition a where s.album.creator.id = :userID order by a.id desc");
		query.setLong("userID", userID);
		List<Song> newSongList = (List<Song>)query.list();
		Song[] arSong = newSongList.toArray(new Song[newSongList.size()]);
		list.add(arSong);
		
		return list;
	}
	
	//查詢最近動態
	public ArrayList queryRecentActivity (long userID){
		
		ArrayList list = new ArrayList();
		
		Member member = (Member) memberDAO.find(userID);
		
		//1.最近喊讚的專輯清單?
		Album[] arAlbum = albumDAO.findAll();
		list.add(arAlbum);
		
	    //2.最近喊讚的歌曲清單?
		Song[] arSong = songDAO.findAll();
		list.add(arSong);
		
		//3.最近試聽的歌曲清單
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(" from Song s where s.album.creator.id = :userID");
		query.setLong("userID", userID);
		List<Song> newSongList = (List<Song>)query.list();
		Song[] arListenSong = newSongList.toArray(new Song[newSongList.size()]);
		list.add(arListenSong);
		
		return list;
	}
	
	//好友及粉絲團
	public ArrayList queryFriendFans(long userID){
		ArrayList list = new ArrayList();
		Creator creator = (Creator) creatorDAO.find(userID);
		Set<Friend> friendSet = creator.getFriend();
		Friend[] arfriend = friendSet.toArray(new Friend[friendSet.size()]);
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(" from Fan f where f.createUser= :creatorId ");
		query.setLong("creatorId", userID);
		List<Fan> FanList = (List<Fan>)query.list();
		Fan[] arFan = FanList.toArray(new Fan[FanList.size()]);
		//Set<Fan>    fanSet = creator.getFan();
		//Fan[]    arFan    = fanSet.toArray(new Fan[fanSet.size()]);
		list.add(arfriend);
		list.add(arFan);
		return list;
		
	}
	
	//查是否為粉絲團成員
	public Fan[] queryFans(long userID, long creatorId){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(" from Fan f where f.member.id= :userID and f.createUser= :creatorId ");
		query.setLong("userID", userID);
		query.setLong("creatorId", creatorId);
		List<Fan> FanList = (List<Fan>)query.list();
		Fan[] arFan = FanList.toArray(new Fan[FanList.size()]);
		return arFan;
	}
	
	public void addFan(long addMemberID,long userID){
		String pattern = "yyyy-MM-dd";
		String currentDate = DateFormatUtils.format(new Date(), pattern);
		
		Creator creatorAddMember = (Creator) creatorDAO.find(addMemberID);
		Member memberUser = (Member) memberDAO.find(userID);
		
		Set<Fan> FanSet = new HashSet<Fan>();
		Fan fan = new Fan();
		fan.setMember(memberUser);
		fan.setAddDate(currentDate);
		fan.setCreator(creatorAddMember);
		fan.setCreateUser(String.valueOf(creatorAddMember.getId()));
		FanSet.add(fan);
		
		memberUser.setFan(FanSet);
		creatorAddMember.setFan(FanSet);
		fanDAO.save(fan);
		
		Set<LikeCreator> LikeCreatorSet = new HashSet<LikeCreator>();
		LikeCreator likeCreator = new LikeCreator();
		likeCreator.setCreatorLiked(creatorAddMember);
		likeCreator.setCreateDate(currentDate);
		likeCreator.setCreateUser(memberUser);
		LikeCreatorSet.add(likeCreator);
		
		GeneralMember generalMember = new GeneralMember();
		generalMember.setLikeCreator(LikeCreatorSet);
		likeCreatorDAO.save(likeCreator);
		
	}
	
	public News[] queryRecentNews(long userID){
		
		//News n = new News();
		//n.setCreateUser("6");
	
		//Session session = sessionFactory.getCurrentSession();
		//Query query = session.createQuery(" select distinct a.name from Offense o join o.album a order by o.createDate desc");
		//List<Album> list = (List<Album>)query.list();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(" from News n where n.createUser = :userID order by n.onDate desc");
		query.setLong("userID", userID);
		List<News> newsList = (List<News>)query.list();
		System.out.println("list===>"+newsList);
		News[] arNews = newsList.toArray(new News[newsList.size()]);
		return arNews;
	}
	
	public ArrayList queryMemberTotalData(long userID){
		
		ArrayList list = new ArrayList();
		Session session = sessionFactory.getCurrentSession();
		
		//1.尚未下載的歌曲數量
		Query query = session.createQuery(" from DownloadList d where d.createUser = :userID  "); 
		query.setLong("userID", userID);
		DownloadList dlist = (DownloadList)query.uniqueResult();
		
		Set songSet = dlist==null?new HashSet<Song>():dlist.getSong();
		list.add(songSet);
		
		//2.還在購物車的商品數量 ?
		list.add(songSet);
		
		//3.好友邀請數量 ?
		query = session.createQuery(" from Friend f where f.inviter.id = :userID and f.acceptInviteDate is null and f.rejectInviteDate is null");
		query.setLong("userID", userID);
		List<Friend> friendlist = (List<Friend>)query.list();
		list.add(friendlist);
		
		//4.GSiMoney
	    query = session.createQuery(" from GsiMoney g where g.member.id = :userID  order by createDate desc"); 
		query.setLong("userID", userID);
		List<GsiMoney> GsiMoneylist = (List<GsiMoney>)query.list();
		if(GsiMoneylist.size()>0){
			list.add(GsiMoneylist.get(0));
		}
		
		//5.GSiBonus
	    query = session.createQuery(" from GsiBonus g where g.member.id = :userID  order by createDate desc"); 
		query.setLong("userID", userID);
		List<GsiBonus> GsiBonuslist = (List<GsiBonus>)query.list();
		if(GsiBonuslist.size()>0){
			list.add(GsiBonuslist.get(0));
		}
		
		//6.歌曲銷售數量
		
		
		return null;
	}
	
	
	public Friend[] queryUnCheckFriend(long userID){
		Session session = sessionFactory.getCurrentSession();
		List<Friend> friendUnChecklist = new ArrayList<Friend>();
		Query query = session.createQuery(" from Friend f where f.inviter.id = :userID and f.dropDate is null");
		query.setLong("userID", userID);
		List<Friend> friendlist = (List<Friend>)query.list();
		for(Friend f: friendlist){
			String acceptDate = f.getAcceptInviteDate();
			String rejectDate = f.getRejectInviteDate();
			if( StringUtils.isEmpty(acceptDate) && StringUtils.isEmpty(rejectDate) ){
				friendUnChecklist.add(f);
			}
		}
		//1.好友待確認清單
		Friend[] arUncheckFriend = friendUnChecklist.toArray(new Friend[friendUnChecklist.size()]);
		return arUncheckFriend;
	}
	
	public Friend[] queryFriend(long userID){
		Session session = sessionFactory.getCurrentSession();
		ArrayList list = new ArrayList();
		
		Query query = session.createQuery(" from Friend f where f.inviter.id = :userID and f.dropDate is null and f.acceptInviteDate is not null and f.rejectInviteDate is null");
		query.setLong("userID", userID);
		List<Friend> friendlist = (List<Friend>)query.list();
		System.out.println("friendlist==>"+friendlist);
		//2.好友清單
		Friend[] arFriend = friendlist.toArray(new Friend[friendlist.size()]);

		return arFriend;
	}
	
	public Friend queryFriend(long userID,long memberID){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(" from Friend f where f.inviter.id = :userID and f.friend.id = :memberID and f.dropDate is null");
		query.setLong("userID", userID);
		query.setLong("memberID", memberID);
		Friend friend = (Friend)query.uniqueResult();
		return friend;
	}
	
	//type=1:確認好友邀請  type=2:拒絕好友邀請
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void saveFriend(long userID,long memberID,String type){
	
		Friend friend = this.queryFriend(userID, memberID);
		String date = DateFormatUtils.format(new Date(), "yyyyMMddhhmmss");
		if("1".equals(type)){
		  friend.setAcceptInviteDate(date);
		}else{
		  friend.setRejectInviteDate(date);
		}
		
		//friendDAO.update(friend);
		//List<Friend> friendlist = (List<Friend>)query.list();
		//System.out.println("friendlist="+friendlist);
		//String d = friendlist.get(0).getAcceptInviteDate();
		//System.out.println("dd="+d);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void deleteFriend(long userID,long memberID){
		String dropDate = DateFormatUtils.format(new Date(), "yyyyMMddhhmmss");
		Friend friend = this.queryFriend(userID, memberID);
		friend.setDropDate(dropDate);
	}
	
	public LikeCreator[] queryLikeCreator(long userID){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(" from LikeCreator l where l.createUser.id = :userID and l.dropDate is null");
		query.setLong("userID", userID);
		List<LikeCreator> likeCreatorlist = (List<LikeCreator>)query.list();
		LikeCreator[] arLikeCreator = likeCreatorlist.toArray(new LikeCreator[likeCreatorlist.size()]);
		return arLikeCreator;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void deleteCreator(long userID,long creatorID){
		Session session = sessionFactory.getCurrentSession();
		String dropDate = DateFormatUtils.format(new Date(), "yyyyMMddhhmmss");
		
		Query query = session.createQuery(" from LikeCreator l where l.createUser.id = :userID and l.creatorLiked.id = :creatorID  ");
		query.setLong("userID", userID);
		query.setLong("creatorID", creatorID);	
		LikeCreator likeCreator = (LikeCreator)query.uniqueResult();
		likeCreator.setDropDate(dropDate);
	}
	
	public void saveAd(long userID,long bannerTypeId,Ad ad){
		
		/*AdType adType = new AdType();
		adType.setAdTypeName("Sport");
		adType.setCreateUser("kevin");
		adTypeDAO.save(adType);
		*/
		
		Member member = (Member) memberDAO.find(userID);
		String createDate = DateFormatUtils.format(new Date(), "yyyyMMddhhmmss");
		
		
		Set<Ad> AdSet = new HashSet<Ad>();
		AdType adType = new AdType();
		adType = adTypeDAO.find(bannerTypeId);
		
		ad.setAdType(adType);
		
		ad.setCreateDate(createDate);
		ad.setOnStatus("1");    //1: 上架中 ???
		AdSet.add(ad);
		adType.setAd(AdSet);
		ad.setMemberCreator(member);
		adDAO.save(ad);
		
		
	}
	
	public AdType[] getAdType(){
		return adTypeDAO.findAll();
	}
	
	
	/**
	 * 新增商品資料
	 * @param classification  1: SD卡    2:儲值
	 * @param sdcard
	 * @param sdcardPrice
	 * @param prepaid
	 * @param prePaidPrice
	 */
	public void saveProduction(String classification , SDCard sdcard, SDCardPrice sdcardPrice,
			PrePaid prepaid,PrePaidPrice prePaidPrice){
		
		//ProductionClassification pp = new ProductionClassification();
		//pp.setName("PrePaid");
		//productionClassificationDAO.save(pp);
		ProductionClassification pp = productionClassificationDAO.find(Long.parseLong(classification));
		if("1".equals(classification)){
			
			Set<SDCard> SDCardSet = new HashSet<SDCard>();
			SDCardSet.add(sdcard);
			
			pp.setSdCard(SDCardSet);
			sdcard.setProductionClassification(pp);
			
			sdcard.setSdCardPrice(sdcardPrice);
			sdCardPriceDAO.save(sdcardPrice);
			sdCardDAO.save(sdcard);		
		}else if("2".equals(classification)){
			
			Set<PrePaid> PrePaidSet = new HashSet<PrePaid>();
			PrePaidSet.add(prepaid);
			
			pp.setPrePaid(PrePaidSet);
			prepaid.setProductionClassification(pp);
			prepaid.setPrePaidPrice(prePaidPrice);
			prePaidPriceDAO.save(prePaidPrice);
			prePaidDAO.save(prepaid);
		}
		
		
	}
	
	
	
}
