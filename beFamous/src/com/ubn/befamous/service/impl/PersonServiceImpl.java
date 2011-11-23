package com.ubn.befamous.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.PrimaryKeyJoinColumn;

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
import com.ubn.befamous.entity.ProductionCategory;
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
	@Qualifier("songDAO")
	private IBaseDao<Song, Long> songDAO;
	
	@Autowired
	@Qualifier("friendDAO")
	private IBaseDao<Friend, Long> friendDAO;
	
	
	public ArrayList queryMemberData(long userID){
		/*Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(" from Member ");
		List<Member> memberList	= (List<Member>)query.list();
		Member[] arMember = new Member[memberList.size()];
		int i = 0;
		for(Member m : memberList){
			arMember[i] = m;
			System.out.println("member===>"+arMember[i].getCellPhone());
			i++;
		}*/
		
		//1. Member
		Member member = (Member) memberDAO.find(userID);
		//Session session = sessionFactory.getCurrentSession();
		//Query query = session.createQuery(" from Member m where m.id");
		//List<Member> memberList	= (List<Member>)query.list();
		
		//Member[] arMember = memberList.toArray(new Member[memberList.size()]);
		System.out.println("member===>"+member.getCellPhone());
		
		//2.最近喊讚的專輯清單
		Album[] arAlbum = albumDAO.findAll();
           		
	    //3.最近喊讚的歌曲清單
		Song[] arSong = songDAO.findAll();
		
		//4.最近試聽的專輯清單
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(" from Album a where a.album.creator='' ");
		
		return null;
	}
	
	public Member queryBriefIntroduction(long userID){
		Member member = (Member) memberDAO.find(userID);
		return member;
	}
	
	public void addFriend(long addMemberID ,long userID,String message){
		Session session = sessionFactory.getCurrentSession();
		String pattern = "yyyy-MM-dd";
		String inviteDate = DateFormatUtils.format(new Date(), pattern);

		Friend friend = new Friend();
		Set<Friend> setFriend = new HashSet<Friend>();
		friend.setInviter(String.valueOf(userID));
		friend.setFriend(String.valueOf(addMemberID));
		friend.setInviteDate(inviteDate);
		setFriend.add(friend);
		
		Member member = (Member) memberDAO.find(userID);
		System.out.println("member birthday1=>"+member.getBirthday());
		member.setFriend(setFriend);
		member.setBirthday("19800202");
		System.out.println("member birthday2=>"+member.getBirthday());
		memberDAO.update(member);
		friendDAO.save(friend);
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
		list.add(arLikeCreator);
		list.add(arFriend);
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
		
		//4.所有專輯清單
		Set<Album> albumSet = creator.getAlbum();
		Album[] arAlbum = albumSet.toArray(new Album[albumSet.size()]);
		list.add(arAlbum);
		
		//5.最受歡迎歌曲(創作人被試聽最多次 取top10)??
		query = session.createQuery(" from Song s join s.audition a where s.album.creator.id = :userID order by a.id desc");
		query.setLong("userID", userID);
		List<Song> newSongList = (List<Song>)query.list();
		list.add(newSongList);
		
		return list;
	}
	
	//查詢所有專輯
	public ArrayList queryAllCreatorAlbum(long userID){
		
		ArrayList list = new ArrayList();
		Session session = sessionFactory.getCurrentSession();
		Creator creator = (Creator) creatorDAO.find(userID);
		//所有專輯清單
		Set<Album> albumSet = creator.getAlbum();
		Album[] arAlbum = albumSet.toArray(new Album[albumSet.size()]);
		list.add(arAlbum);
		
		//最受歡迎歌曲(創作人被試聽最多次 取top10)??
		Query query = session.createQuery(" from Song s join s.audition a where s.album.creator.id = :userID order by a.id desc");
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
		Set<Fan>    fanSet = creator.getFan();
		Friend[] arfriend = friendSet.toArray(new Friend[friendSet.size()]);
		Fan[]    arFan    = fanSet.toArray(new Fan[fanSet.size()]);
		list.add(arfriend);
		list.add(arFan);
		return list;
		
	}
	
	
	public void addFan(long addMemberID,long userID){
		String pattern = "yyyy-MM-dd";
		String currentDate = DateFormatUtils.format(new Date(), pattern);
		
		Creator creator = new Creator();
		creator.setAccountName("111");
		creator.setCellPhone("222");
		creatorDAO.save(creator);
		
		
		Set<Fan> FanSet = new HashSet<Fan>();
		Fan fan = new Fan();
		fan.setFan(addMemberID);
		fan.setAddDate(currentDate);
		FanSet.add(fan);
		
		//Creator[] creator = creatorDAO.findAll();
		creator.setFan(FanSet);
		
		//System.out.println("DDD"+creator[0].getAccountName());
		fanDAO.save(fan);
		
		Set<LikeCreator> LikeCreatorSet = new HashSet<LikeCreator>();
		LikeCreator likeCreator = new LikeCreator();
		likeCreator.setCreatorLiked(String.valueOf(addMemberID));
		likeCreator.setCreateDate(currentDate);
		likeCreator.setCreateUser(String.valueOf(userID));
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
		List<Creator> list = (List<Creator>)query.list();
		System.out.println("list===>"+list);
		return null;
	}
	
	public ArrayList queryMemberTotalData(long userID){
		
		ArrayList list = new ArrayList();
		Session session = sessionFactory.getCurrentSession();
		
		//1.尚未下載的歌曲數量
		Query query = session.createQuery(" from downloadlist where createUser = :userID  "); 
		query.setLong("userID", userID);
		DownloadList dlist = (DownloadList)query.uniqueResult();
		//Set songSet = dlist.getSong();
		//list.add(songSet);
		
		//2.還在購物車的商品數量 ?
		
		//3.好友邀請數量 ?
		
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
	
	public ArrayList queryFriend(String userID){
		long productionCategoryId = 2;
		Session session = sessionFactory.getCurrentSession();
		Query query =  session.createQuery("FROM ProductionCategory p where p.pid = :id");
		query.setParameter("id", productionCategoryId);		
		
		ProductionCategory s = (ProductionCategory) query.uniqueResult();
	     Album  f = (Album)s;
	     System.out.println(f.getName());
		System.out.println(s==null?"null":s.getPid());
		return null;
	}
	
	public void saveFriend(String userID,String memberID){
		
	}
	
	public void deleteFriend(String userID,String memberID){
		
	}
	
	public LikeCreator[] queryLikeCreator(String userID){
		return null;
	}
	
	public void deleteCreator(String userID,String creatorID){
		
	}
	
	public void saveAd(String userID,String email,String bannerType,
	           String actionName,String startDate,String endDate,
	           String url,String createDate,String actionContent){
		
	}
	
	
	public ArrayList queryMember(long userID) {
		
		ArrayList list = new ArrayList();	
		Query query = this.sessionFactory.getCurrentSession().createQuery("FROM Member where id = :userID");
		query.setParameter("userID", userID);
		
		Object person = query.uniqueResult();
		if (person instanceof Creator) {
			Creator creator = (Creator)person;
			list.add(creator);
		}else{
		GeneralMember generalMember = (GeneralMember)person;
			list.add(generalMember);
		}
		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateMember(long userID,String identityName,String userName,String location,String city,String birthday, String sex,String webSite,String subscribeStatus,String introduction,String likeMusicTypes,String likeSingers){
		if(identityName.equals("1")){
			GeneralMember generalMember = this.generalMemberDAO.find(userID);
			generalMember.setIdentityName(identityName);
			generalMember.setUserName(userName);
			generalMember.setLocation(location);
			generalMember.setCity(city);
			generalMember.setBirthday(birthday);
			generalMember.setSex(sex);
			generalMember.setWebSite(webSite);
			generalMember.setSubscribeStatus(subscribeStatus);
			generalMember.setIntroduction(introduction);
			this.generalMemberDAO.update(generalMember);			
		}else{
			Creator creator =  this.creatorDAO.find(userID);
			creator.setIdentityName(identityName);
			creator.setUserName(userName);
			creator.setLocation(location);
			creator.setCity(city);
			creator.setBirthday(birthday);
			creator.setSex(sex);
			creator.setWebSite(webSite);
			creator.setSubscribeStatus(subscribeStatus);
			creator.setIntroduction(introduction);
			creator.setLikeMusicType(likeMusicTypes);
			creator.setLikeSinger(likeSingers);
			this.creatorDAO.update(creator);			
		}
		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updatePassword(long userID, String password) {
		Member member = this.memberDAO.find(userID);
		member.setPassword(password);
		this.memberDAO.update(member);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateEmail(long userID, String email) {
		Member member = this.memberDAO.find(userID);
		member.setEmail(email);
		this.memberDAO.update(member);		
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void deleteMemberPicture(long userID) {
		Member member = this.memberDAO.find(userID);
		member.setPicture("");
		this.memberDAO.update(member);	
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void handleUploadPicture(long userID, String picture){
		Member member = this.memberDAO.find(userID);
		member.setPicture(picture);
		this.memberDAO.update(member);	
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateAccountData(long userID, String accountName, String accountNO, String bankName, String bankBranch, String identityNO, String address, String tel, String cellPhone) {
		Creator creator =  this.creatorDAO.find(userID);
		creator.setAccountName(accountName);
		creator.setAccountNO(accountNO);
		creator.setBankName(bankName);
		creator.setBankBranch(bankBranch);
		creator.setIdentityNO(identityNO);
		creator.setCellPhone(cellPhone);
		creator.setAddress(address);
		creator.setTel(tel);
		this.creatorDAO.update(creator);		
	}
}
