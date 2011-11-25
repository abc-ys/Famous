package com.ubn.befamous.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.PrimaryKeyJoinColumn;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
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
import com.ubn.befamous.entity.Admin;
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
import com.ubn.befamous.entity.MemberStatus;
import com.ubn.befamous.entity.ModifyData;
import com.ubn.befamous.entity.News;
import com.ubn.befamous.entity.Offense;
import com.ubn.befamous.entity.ProductionCategory;
import com.ubn.befamous.entity.Question;
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
	@Qualifier("newsDAO")
	private IBaseDao<News, Long> newsDAO;
	
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
	
	@Autowired
	@Qualifier("questionDAO")
	private IBaseDao<Question, Long> questionDAO;
	
	@Autowired
	@Qualifier("adminDAO")
	private IBaseDao<Admin, Long> adminDAO;
	
	@Autowired
	@Qualifier("modifyDataDAO")
	private IBaseDao<ModifyData, Long> modifyDataDAO;
	
	@Autowired
	@Qualifier("memberStatusDAO")
	private IBaseDao<MemberStatus, Long> memberStatusDAO;
	
	
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
		String pattern = "yyyyMMddHHmmss";
		String inviteDate = DateFormatUtils.format(new Date(), pattern);

		Friend friend = new Friend();
		Set<Friend> setFriend = new HashSet<Friend>();
		//friend.setInviter(String.valueOf(userID));
		//friend.setFriend(String.valueOf(addMemberID));
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
		String pattern = "yyyyMMddHHmmss";
		String currentDate = DateFormatUtils.format(new Date(), pattern);
		
		Creator creator = new Creator();
		creator.setAccountName("111");
		creator.setCellPhone("222");
		creatorDAO.save(creator);
		
		
		Set<Fan> FanSet = new HashSet<Fan>();
		Fan fan = new Fan();
		//fan.setFan(addMemberID);
		fan.setAddDate(currentDate);
		FanSet.add(fan);
		
		//Creator[] creator = creatorDAO.findAll();
		creator.setFan(FanSet);
		
		//System.out.println("DDD"+creator[0].getAccountName());
		fanDAO.save(fan);
		
		Set<LikeCreator> LikeCreatorSet = new HashSet<LikeCreator>();
		LikeCreator likeCreator = new LikeCreator();
		//likeCreator.setCreatorLiked(String.valueOf(addMemberID));
		likeCreator.setCreateDate(currentDate);
		//likeCreator.setCreateUser(String.valueOf(userID));
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
	
	//Lucy寫的
	
	//查詢會員資料
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

	//更新會員資料
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
		
	//更新會員密碼
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updatePassword(long userID, String password) {
		Member member = this.memberDAO.find(userID);
		member.setPassword(password);
		this.memberDAO.update(member);
	}
		
	//更新會員信箱
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateEmail(long userID, String email) {
		Member member = this.memberDAO.find(userID);
		member.setEmail(email);
		this.memberDAO.update(member);		
	}
		
	//刪除會員圖片
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void deleteMemberPicture(long userID) {
		Member member = this.memberDAO.find(userID);
		member.setPicture("");
		this.memberDAO.update(member);	
	}
		
	//更新會員圖片
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void handleUploadPicture(long userID, String picture){
		Member member = this.memberDAO.find(userID);
		member.setPicture(picture);
		this.memberDAO.update(member);	
	}
		
	//更新會員帳戶資料
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
	
	
	//怡秀寫start 2011-11-22
	
	//客服-提問頁-儲存問題
	public void saveQuestion(String productType,String userIdentity,String name,String email,String tel,String questionType,String questionContent){
		String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");   //今天日期時間
		
		Question question = new Question();
		question.setProductionType(productType);
		question.setUserIdentity(userIdentity);
		question.setUserName(name);
		question.setEmail(email);
		question.setTel(tel);
		question.setQuestionType(questionType);
		question.setQuestionContent(questionContent);
		question.setCreateDate(datetime);
		if(userIdentity.equals("1")){
			question.setCreateUser("會員-"+name);
		}else{
			question.setCreateUser("非會員-"+name);
		}
		question.setQuestionDate(datetime);
		question.setHandleStatus("1");
		
		this.questionDAO.save(question);
	}
		
	//客服-管理者的問題管理第一個頁面    
	public Question[] queryQuestion(String startDate,String endDate,String productType,String email,String questionType){		
		if (StringUtils.isNotBlank(startDate)&& StringUtils.isNotBlank(endDate)) {
			startDate= StringUtils.replaceChars(startDate, "-", "")+"000000";
			endDate= StringUtils.replaceChars(endDate, "-", "")+"235959";
		}
		
		StringBuilder queryString = new StringBuilder();
		queryString.append("from Question a where (a.handleStatus = :handleStatus)");
		if (StringUtils.isNotBlank(startDate)&& StringUtils.isNotBlank(endDate)) {
			queryString.append("and (a.questionDate  between :startDate and :endDate)");
		}
		if (StringUtils.isNotBlank(questionType)) {
			queryString.append("and (a.questionType=:questionType)");
		}
		if (StringUtils.isNotBlank(productType)) {
			queryString.append("and (a.productionType = :productType)");
		}
		if (StringUtils.isNotBlank(email)) {
			queryString.append("and (a.email = :email)");
		}

		Query query = this.sessionFactory.getCurrentSession().createQuery(queryString.toString());
		
		if(StringUtils.isNotBlank(startDate)&&StringUtils.isNotBlank(endDate)){
			query.setString("startDate", startDate);			
			query.setString("endDate", endDate);
		}
		if(StringUtils.isNotBlank(productType)){
		query.setString("productType", productType);
		}
		if(StringUtils.isNotBlank(email)){
		query.setString("email", email);
		}
		if(StringUtils.isNotBlank(questionType)){
		query.setString("questionType", questionType);
		}
		query.setString("handleStatus", "1");
		
		List<Question> resultList=(List<Question>)query.list();
		Question[] questionset = new Question[resultList.size()];
		
			int i=0;
			for (Question as:resultList) {
				questionset[i]=as;
				System.out.println("ssss==>"+questionset[i].getId());
				i++;
			}
		return questionset;
	}
		
	//客服-查詢管理者的名稱
	public Admin queryAdminName(long adminId){
		Admin q=this.adminDAO.find(adminId);
		return q;
	}
	
	//客服-查詢問題的細節
	public Question queryQuestionDetail(long questionID){
		Question q=this.questionDAO.find(questionID);
		return q;
	}
		
	//客服-儲存回覆
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public Question saveAnswer(long questionID,long adminId,String answerContent,String adminName){
		String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");   //今天日期時間  yyyyMMddhhmmss
		
		Question q=this.questionDAO.find(questionID);
		q.setAnswerContent(answerContent);
		q.setAnswerDate(datetime);
		q.setHandleStatus("2");
		q.setModifier(String.valueOf(adminId));
		q.setAnswerPerson(adminName);
		q.setModifyDate(datetime);
		
		this.questionDAO.update(q);
		
		Question q2=this.questionDAO.find(questionID);
		return q2;
	}
	
	//客服-儲存備註
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public Question saveNote(long questionID,long adminId,String noteContent,String adminName){
		String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");   //今天日期時間  yyyyMMddhhmmss
		
		Question q=this.questionDAO.find(questionID);
		q.setNoteContent(noteContent);
		q.setNoteDate(datetime);
		q.setHandleStatus("2");
		q.setModifier(String.valueOf(adminId));
		q.setNotePerson(adminName);
		q.setModifyDate(datetime);
		
		this.questionDAO.update(q);
		
		Question q2=this.questionDAO.find(questionID);
		return q2;
	}
	
	
	//廣告管理
	
	//新增管理者廣告
	public void saveManagerAd(long adminId,String bannerType,String actionName,String picture,String startDate,String endDate,String url,String onDate,String offDate,String createDate){
		String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");   //今天日期時間  yyyyMMddHHmmss
		
		startDate= StringUtils.replaceChars(startDate, "-", "")+"000000";
		endDate= StringUtils.replaceChars(endDate, "-", "")+"235959";
		onDate= StringUtils.replaceChars(onDate, "-", "")+"000000";
		offDate= StringUtils.replaceChars(offDate, "-", "")+"235959";
		
		AdType adType = this.adTypeDAO.find(Long.parseLong(bannerType));
		
		Ad ad = new Ad();
		ad.setAdminCreator(String.valueOf(adminId));
		ad.setCreateDate(datetime);
		ad.setAdType(adType);
		ad.setPicture(picture);
		ad.setActivityStartDate(startDate);
		ad.setActivityEndDate(endDate);
		ad.setWebsite(url);
		ad.setOnDate(onDate);
		ad.setOffDate(offDate);
		ad.setOnStatus("1");
		ad.setCheckStatus("2");
		ad.setActivityName(actionName);
		
		this.adDAO.save(ad);
	}
		
	//查詢廣告詳細資料
	public Ad queryAdDetail(long adID){
		Ad a = this.adDAO.find(adID);
		return a;
	}
		
	//修改廣告資料
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public Ad modifyAd(long adID,long adminID,String bannerType,String actionName,String picture,String fileName,String startDate,String endDate,String url,String onDate,String offDate){
		String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");   //今天日期時間  yyyyMMddHHmmss
		
		startDate= StringUtils.replaceChars(startDate, "-", "")+"000000";
		endDate= StringUtils.replaceChars(endDate, "-", "")+"235959";
		onDate= StringUtils.replaceChars(onDate, "-", "")+"000000";
		offDate= StringUtils.replaceChars(offDate, "-", "")+"235959";
		
		Ad a = this.adDAO.find(adID);
		
		AdType adType = this.adTypeDAO.find(Long.parseLong(bannerType));
		
		a.setModifier(String.valueOf(adminID));
		a.setModifyDate(datetime);
		a.setAdType(adType);
		a.setActivityName(actionName);
		if("".equals(fileName)){
			a.setPicture(picture);
		}else{
			a.setPicture(fileName);
		}
		a.setActivityStartDate(startDate);
		a.setActivityEndDate(endDate);
		a.setWebsite(url);
		a.setOnDate(onDate);
		a.setOffDate(offDate);
		
		this.adDAO.update(a);
		return a;
	}
		
	//查詢廣告清單
	public Ad[] queryAd(String bannerType,String actionName,String upStartDate,String upEndDate,String downStartDate,String downEndDate){
		if (StringUtils.isNotBlank(upStartDate)&& StringUtils.isNotBlank(upEndDate)) {
		upStartDate= StringUtils.replaceChars(upStartDate, "-", "")+"000000";
		upEndDate= StringUtils.replaceChars(upEndDate, "-", "")+"235959";}
		if (StringUtils.isNotBlank(downStartDate)&& StringUtils.isNotBlank(downEndDate)) {
		downStartDate= StringUtils.replaceChars(downStartDate, "-", "")+"000000";
		downEndDate= StringUtils.replaceChars(downEndDate, "-", "")+"235959";}
		
		System.out.println("upStartDate==>"+upStartDate+", upEndDate==>"+upEndDate+", downStartDate==>"+downStartDate+", downEndDate==>"+downEndDate);
		
		StringBuilder queryString = new StringBuilder();
		queryString.append("from Ad a where (a.onStatus = :onStatus)");
		if (StringUtils.isNotBlank(upStartDate)
				&& StringUtils.isNotBlank(upEndDate)) {
			queryString.append("and (a.onDate  between :startDate and :endDate)");
		}
		if (StringUtils.isNotEmpty(downStartDate)
				&& StringUtils.isNotBlank(downEndDate)) {
			queryString.append("and (a.offDate  between :startDate and :endDate)");
		}
		if (StringUtils.isNotBlank(bannerType)) {
			queryString.append("and (a.adType.id=:bannerType)");
		}
		if (StringUtils.isNotBlank(actionName)) {
			queryString.append("and (a.activityName = :activityName)");
		}

		Query query = this.sessionFactory.getCurrentSession().createQuery(queryString.toString());
		
		if(StringUtils.isNotBlank(upStartDate)&&StringUtils.isNotBlank(upEndDate)){
			query.setString("startDate", upStartDate);
			query.setString("endDate", upEndDate);
		}
		if (StringUtils.isNotBlank(downStartDate)
				&& StringUtils.isNotBlank(downEndDate)) {
			query.setString("startDate", downStartDate);
			query.setString("endDate", downEndDate);
		}
		if (StringUtils.isNotBlank(bannerType)) {
			query.setString("bannerType", bannerType);
		}
		if (StringUtils.isNotBlank(actionName)) {
			query.setString("activityName", actionName);
		}
		query.setString("onStatus", "1");
		
		List<Ad> resultList=(List<Ad>)query.list();
		Ad[] ADset = new Ad[resultList.size()];
		
			int i=0;
			for (Ad as:resultList) {
				ADset[i]=as;
				System.out.println("ssss==>"+ADset[i].getId());
				i++;
			}
		
		return ADset;
	}
		
	//查詢創作者的廣告清單      (專輯數的條件還沒加)
	public Ad[] queryCreatorAd(String startDate,String endDate,String checkStatus,String albumAmount){
		if(StringUtils.isNotBlank(startDate)&& StringUtils.isNotBlank(endDate)){
		startDate= StringUtils.replaceChars(startDate, "-", "")+"000000";
		endDate= StringUtils.replaceChars(endDate, "-", "")+"235959";}
		
		if(StringUtils.isBlank(checkStatus)){
		checkStatus="1";}
		
		StringBuilder queryString = new StringBuilder();
		queryString.append("from Ad a where (a.checkStatus = :checkStatus) and (a.memberCreator is not null)");
		if (StringUtils.isNotBlank(startDate)&& StringUtils.isNotBlank(endDate)) {
			queryString.append("and (:startDate  between a.activityStartDate and a.activityEndDate) and (:endDate between a.activityStartDate and a.activityEndDate)");
		}
		/*if (StringUtils.isNotEmpty(albumAmount)) {
			queryString.append("and (a.activityName = :albumAmount)");
		}*/

		Query query = this.sessionFactory.getCurrentSession().createQuery(queryString.toString());
		
		if (StringUtils.isNotBlank(startDate)&& StringUtils.isNotBlank(endDate)) {
			query.setString("startDate", startDate);
			query.setString("endDate", endDate);
		}
		/*if (StringUtils.isNotEmpty(albumAmount)) {
			query.setString("albumAmount", albumAmount);
		}*/
		query.setString("checkStatus", checkStatus);
		
		List<Ad> resultList=(List<Ad>)query.list();
		Ad[] ADset = new Ad[resultList.size()];
		
			int i=0;
			for (Ad as:resultList) {
				ADset[i]=as;
				System.out.println("ssss==>"+ADset[i].getId());
				i++;
			}
		
		return ADset;
	}
		
	//儲存備註
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void saveNote(long creatorAdID,String checkStatus,String reason,long adminID){
		String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");   //今天日期時間  yyyyMMddHHmmss
		
		Ad a = this.adDAO.find(creatorAdID);
		a.setModifier(String.valueOf(adminID));
		a.setModifyDate(datetime);
		a.setCheckStatus(checkStatus);
		a.setNote(reason);
		this.adDAO.update(a);
	}
	
	
	//Lucy@20111123
	//儲存創作人刊登的訊息
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)	
	public void saveNews(long userID, String newsName, String newsSouce, String content, String onStatus) {
			
		String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
		Creator creator = (Creator) this.memberDAO.find(userID);
		News news = new News();
		news.setCreateDate(date);
		news.setNewsName(newsName);
		news.setContent(content);
		news.setNewsSouce(newsSouce);
		news.setOnStatus(onStatus);
		creator.getNews().add(news);
		if(onStatus.equals("1")){
			news.setOnDate(date);			
		}
		this.newsDAO.save(news);
			
	}
	//創作人查詢刊登訊息
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public News[] queryNews(long userID, String onStatus) {		
		
		Query query = this.sessionFactory.getCurrentSession().createQuery("select n from Creator c join c.news n where c.id =:v1 and n.onStatus = :v2 and n.dropDate is null");
		query.setLong("v1", userID);
		query.setParameter("v2", onStatus);
		List<News> newsSet = (List<News>)query.list();		
		News[] newsList = newsSet.toArray(new News[newsSet.size()]);
					
		return  newsList;
	}

	//刪除刊登訊息
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void deleteNews(long newsID) {
		this.newsDAO.delete(newsID);
	}

	//查詢訊息明細
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public News queryNewsDetail(long newsID) {
		News news = this.newsDAO.find(newsID);
		return news;
	}

	//儲存創作人更新的訊息
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateNews(long newsID, String newsName, String newsSouce, String content, String onStatus) {
		String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
		News news = this.newsDAO.find(newsID);
		news.setNewsName(newsName);
		news.setContent(content);
		news.setNewsSouce(newsSouce);
		news.setOnStatus(onStatus);
		if(onStatus.equals("1")){
			news.setOnDate(date);			
		}
		this.newsDAO.update(news);
	}

	//儲存管理者新增的訊息
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void saveManagerNews(long adminID, String newsCategory, String newsName, String picture, String newsSouce, String onDate, String content, String onStatus) {
		String date = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
		News news = new News();
		news.setNewsCategory(newsCategory);
		news.setCreateDate(date);
		news.setNewsName(newsName);
		news.setContent(content);
		news.setNewsSouce(newsSouce);
		news.setPicture(picture);
		news.setCreateUser(String.valueOf(adminID));
		news.setOnDate(onDate);
		news.setOnStatus(onStatus);
		this.newsDAO.save(news);		
	}
		
	//管理者查詢刊登訊息(起始頁面)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public News[] queryFirstNewsList(){
		String nowDate = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
		Date tempDate = DateUtils.addDays(new Date(), -14);
		String date = DateFormatUtils.format(tempDate, "yyyyMMddHHmmss");
		Query query = this.sessionFactory.getCurrentSession().createQuery("from News where(createUser is not null)and(createDate between :date and :nowDate)and(dropDate is null)");
		query.setParameter("nowDate", nowDate);
		query.setParameter("date", date);		
		List<News> newsSet = (List<News>)query.list();		
		News[] newsList = newsSet.toArray(new News[newsSet.size()]);
		return newsList;
	}
		
	//管理者查詢刊登訊息(查詢條件)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public News[] queryNewsList(String newsCategory, String newsName,
		String MOPEND, String MCLOSED, String onStatus, String newsSource) 
	{		
		StringBuffer tempQuery = new StringBuffer();
		tempQuery.append("from News where createUser is not null and dropDate is null ");
		
		if(!newsCategory.isEmpty()){
			tempQuery.append("and newsCategory = :newsCategory ");
		}
		if (!newsName.isEmpty()){
			tempQuery.append("and newsName = :newsName ");
		}
		if (!MOPEND.isEmpty()&&!MCLOSED.isEmpty()){
			tempQuery.append("and createDate between :MOPEND and :MCLOSED ");
		}
		if (!onStatus.isEmpty()){
			tempQuery.append("and onStatus = :onStatus ");
		}
		if (!newsSource.isEmpty()){
			tempQuery.append("and newsSource = :newsSource ");
		}		
			
		Query query = this.sessionFactory.getCurrentSession().createQuery(tempQuery.toString());
		if(!newsCategory.isEmpty()){
			query.setParameter("newsCategory", newsCategory);
		}
		if (!newsName.isEmpty()){
			query.setParameter("newsName", newsName);
		}
		if (!MOPEND.isEmpty()&&!MCLOSED.isEmpty()){
			String sDate = MOPEND.replaceAll("-", "");
			sDate = sDate+"000000";
			String eDate = MCLOSED.replaceAll("-", "");
			eDate = eDate+"235959";		
			query.setParameter("MOPEND", sDate);
			query.setParameter("MCLOSED", eDate);
		}
		if (!onStatus.isEmpty()){
			query.setParameter("onStatus", onStatus);
		}
		if (!newsSource.isEmpty()){
			query.setParameter("newsSource", newsSource);
		}		
		
		List<News> newsSet = (List<News>)query.list();		
		News[] newsList = newsSet.toArray(new News[newsSet.size()]);
		return newsList;
	}

	//儲存管理者更新的訊息
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateManagerNews(long newsID, String newsCategory, String newsName, String picture, String newsSouce, String onDate, String content){
		News news = this.newsDAO.find(newsID);
		news.setNewsCategory(newsCategory);
		news.setNewsName(newsName);
		news.setPicture(picture);
		news.setContent(content);
		news.setNewsSouce(newsSouce);
		news.setOnDate(onDate);		
		this.newsDAO.update(news);
	}
	
	
	//怡秀寫的  2011-11-24
	//管理會員資料
		
	//查詢會員清單
	public Member[] queryMemberList(String email,String identity,String startDate,String endDate,String location,String fanAmountOne,String fanAmountTwo,String friendAmountOne,String friendAmountTwo,String status){
//		Member memberX=(Member)this.sessionFactory.getCurrentSession().get(Member.class,new Long( 2));
//		System.out.println("memberX="+memberX.getFriend().size());
//		
		if (StringUtils.isNotBlank(startDate)
				&& StringUtils.isNotBlank(endDate)) {
			startDate= StringUtils.replaceChars(startDate, "-", "")+"000000";
			endDate= StringUtils.replaceChars(endDate, "-", "")+"235959";
		}
		
		StringBuilder queryString = new StringBuilder();
		if(StringUtils.isNotBlank(fanAmountOne)&& StringUtils.isNotBlank(fanAmountTwo)) {
			queryString.append("from Creator a where (1=1)");
		}else{
		   queryString.append("from Member a where (1=1) ");   
		}
		if(StringUtils.isNotBlank(startDate)&& StringUtils.isNotBlank(endDate)) {
			startDate= StringUtils.replaceChars(startDate, "-", "")+"000000";
			endDate= StringUtils.replaceChars(endDate, "-", "")+"235959";
			
			queryString.append(" and (a.createDate  between :startDate and :endDate)");
			
		}
		if (StringUtils.isNotBlank(email)) {
			queryString.append(" and (a.email=:email)");
		}
		if (StringUtils.isNotBlank(identity)) {
			queryString.append(" and (a.identityName = :identity)");
		}
		if (StringUtils.isNotBlank(location)) {
			queryString.append(" and (a.location = :location)");
		}
		if (StringUtils.isNotBlank(status)) {
			queryString.append(" and (a.memberStatus.id=:status)");
		}
		if(StringUtils.isNotBlank(friendAmountOne)&& StringUtils.isNotBlank(friendAmountTwo)) {
			queryString.append(" and size(a.friend) between :friendAmountOne and :friendAmountTwo");
		}
		if(StringUtils.isNotBlank(fanAmountOne)&& StringUtils.isNotBlank(fanAmountTwo)) {
			queryString.append(" and size(a.fan) between :fanAmountOne and :fanAmountTwo");
		}
		
		
		Query query = this.sessionFactory.getCurrentSession().createQuery(queryString.toString());

		if(StringUtils.isNotBlank(startDate)&& StringUtils.isNotBlank(endDate)) {
			query.setString("startDate", startDate);
			query.setString("endDate", endDate);
		}
		if (StringUtils.isNotBlank(email)) {
		query.setString("email", email);
		}
		if (StringUtils.isNotBlank(identity)) {
		query.setString("identity", identity);
		}
		if (StringUtils.isNotBlank(location)) {
		query.setString("location", location);
		}
		if (StringUtils.isNotBlank(status)) {
		query.setString("status", status);
		}
		if(StringUtils.isNotBlank(friendAmountOne)&& StringUtils.isNotBlank(friendAmountTwo)) {
			query.setString("friendAmountOne", friendAmountOne);
			query.setString("friendAmountTwo", friendAmountTwo);
		}
		if(StringUtils.isNotBlank(fanAmountOne)&& StringUtils.isNotBlank(fanAmountTwo)) {
			query.setString("fanAmountOne", fanAmountOne);
			query.setString("fanAmountTwo", fanAmountTwo);
		}
		
		List<Member> resultList=(List<Member>)query.list();
		Member[] memberSet = new Member[resultList.size()];
		
			int i=0;
			for (Member as:resultList) {
				memberSet[i]=as;
				System.out.println("memberID==>"+memberSet[i].getId());
				i++;
		}
			return memberSet;
		
		
	}
		
	//查詢修改紀錄
	public ModifyData[] queryModifyRecord(String userID){
		Creator c = this.creatorDAO.find(Long.parseLong(userID));
		ModifyData[] md = new ModifyData[c.getModifyData().size()];
		
		int i=0;
		for (ModifyData as:c.getModifyData()) {
			md[i]=as;
			System.out.println("modifyData==>"+md[i].getContent());
			i++;
		}
				
		return md;
	}
		
	//查詢會員詳細資料     
	public ArrayList queryMemberDetailData(long userID){
		ArrayList list = new ArrayList();	
		
		int fan =0;
		int friend =0;
		int album=0;
		long song=0;
		long offenseAlbum=0;
		long offenseSong=0;
		int offense=0;
		
		//會員資料
		Query query = this.sessionFactory.getCurrentSession().createQuery("FROM Member where id = :userID");
		query.setParameter("userID", userID);
			
		Object person = query.uniqueResult();
		if (person instanceof Creator) {
			Creator creator = (Creator)person;
			list.add(creator);
			System.out.println("creatorName=="+creator.getUserName());
			fan =creator.getFan().size();                //粉絲數
			friend = creator.getFriend().size();         //好友數
			offense=creator.getOffense().size();      //檢舉次數
			album=creator.getAlbum().size();           //專輯數
			
			query = this.sessionFactory.getCurrentSession().createQuery("select count(a) FROM Song a where a.createUser = :userID");
			query.setString("userID", String.valueOf(userID));
			song= (Long) query.uniqueResult();   //歌曲數
			
		}else{
			GeneralMember generalMember = (GeneralMember)person;
			list.add(generalMember);
			friend = generalMember.getFriend().size();
			offense=generalMember.getOffense().size();
		}
		list.add(fan);
		list.add(friend);
		list.add(offense);
		list.add(album);
		list.add(song);
		
		//GSiMoney
	    query = this.sessionFactory.getCurrentSession().createQuery(" from GsiMoney g where g.member.id = :userID  order by createDate desc"); 
		query.setLong("userID", userID);
		List<GsiMoney> GsiMoneylist = (List<GsiMoney>)query.list();
		if(GsiMoneylist.size()>0){
			list.add(GsiMoneylist.get(0).getBalance());
		}
		
		//GSiBonus
	    query = this.sessionFactory.getCurrentSession().createQuery(" from GsiBonus g where g.member.id = :userID  order by createDate desc"); 
		query.setLong("userID", userID);
		List<GsiBonus> GsiBonuslist = (List<GsiBonus>)query.list();
		if(GsiBonuslist.size()>0){
			list.add(GsiBonuslist.get(0).getBalance());
		}
		
		//被檢舉歌曲數
		query = this.sessionFactory.getCurrentSession().createQuery("select count(a) from Offense a where a.album.creator.id = :userID"); 
		query.setLong("userID", userID);
		offenseSong=(Long) query.uniqueResult();   
		System.out.println("被檢舉歌曲數"+query.uniqueResult());
		list.add(offenseSong);
		
		//被檢舉專輯數
		query = this.sessionFactory.getCurrentSession().createQuery("select count(a) from Offense a where a.song.album.creator.id = :userID"); 
		query.setLong("userID", userID);
		offenseAlbum=(Long) query.uniqueResult(); 
		System.out.println("被檢舉專輯數"+query.uniqueResult());
		list.add(offenseAlbum);
		
		System.out.println("fan=="+fan);
		System.out.println("friend=="+friend);
		System.out.println("offense=="+offense);
		System.out.println("album=="+album);
		System.out.println("song=="+song);
		System.out.println("GsiBonuslist=="+GsiBonuslist.get(0).getBalance());
		System.out.println("GsiMoneylist=="+GsiMoneylist.get(0).getBalance());
		System.out.println("offenseSong=="+offenseSong);
		
		return list;
	}
		
	//更新會員狀態
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateStatus(String userID,String adminID,String statusReason,String statusName){
		String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");   //今天日期時間  yyyyMMddHHmmss
		
		MemberStatus ms = this.memberStatusDAO.find(Long.parseLong(userID));
		ms.setModifier(adminID);
		ms.setModifyDate(datetime);
		ms.setStatusName(statusName);
		ms.setStatusReason(statusReason);
		
		Member m = this.memberDAO.find(Long.parseLong(userID));
		m.setMemberStatus(ms);
		m.setModifier(adminID);
		m.setModifyDate(datetime);
		
		this.memberDAO.update(m);
		this.memberStatusDAO.update(ms);
	}
		
	//更新付款資料
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateAccount(String memberId,String adminID,String userName,String identityNO,String address,String cellPhone,String tel,String accountName,String bankName,String bankBranch,String accountNO,String statusName){
		String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");   //今天日期時間  yyyyMMddHHmmss
		
		Creator c = this.creatorDAO.find(Long.parseLong(memberId));
		c.setAccountName(accountName);
		c.setAccountNO(accountNO);
		c.setAddress(address);
		c.setBankBranch(bankBranch);
		c.setBankName(bankName);
		c.setCellPhone(cellPhone);
		c.setTel(tel);
		c.setIdentityNO(identityNO);
		c.setModifier(adminID);
		c.setModifyDate(datetime);
		c.setRealName(userName);
		if(statusName.equals("正常")){
		MemberStatus ms = this.memberStatusDAO.find(Long.parseLong(memberId));
		ms.setModifier(adminID);
		ms.setModifyDate(datetime);
		ms.setStatusName(statusName);
		ms.setStatusReason("");
		c.setMemberStatus(ms);}
		this.creatorDAO.update(c);
	}
	
	
	//加入會員與會員登入
	
	//加入會員-儲存會員
	public Member saveMember(String email,String userName,String password,String sex,String birthday,String location){
		String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");   //今天日期時間  yyyyMMddHHmmss
		
		GeneralMember m = new GeneralMember();
		m.setEmail(email);
		m.setCreateDate(datetime);
		m.setCreateUser(userName);
		m.setUserName(userName);
		m.setPassword(password);
		m.setSex(sex);
		m.setBirthday(birthday);
		m.setLocation(location);
		m.setIdentityName("1");
		MemberStatus memberStatus = new MemberStatus();
		memberStatus.setCreateDate(datetime);
		memberStatus.setCreateUser(userName);
		memberStatus.setStatusName("正常");
		m.setMemberStatus(memberStatus);
		this.generalMemberDAO.save(m);
		this.memberStatusDAO.save(memberStatus);
		
		Query query = this.sessionFactory.getCurrentSession().createQuery("from Member a where (a.userName = :userName) and (a.email = :email) and (a.password = :password)");
		query.setString("userName", userName);
		query.setString("email", email);
		query.setString("password", password);
		
		Member member = (Member)query.uniqueResult();
		return member;
	}
		
	//加入會員-上傳圖片
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateMemberPicture(String userID,String fileName){
		String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");   //今天日期時間  yyyyMMddHHmmss
		
		Member m = this.memberDAO.find(Long.parseLong(userID));
		m.setPicture(fileName);
		m.setModifier(userID);
		m.setModifyDate(datetime);
		this.memberDAO.update(m);
	}
	
	//加入會員-查詢會員
	public Member queryMemberInfo(String userID){
		Member m = this.memberDAO.find(Long.parseLong(userID));
		return m;
	}
}
