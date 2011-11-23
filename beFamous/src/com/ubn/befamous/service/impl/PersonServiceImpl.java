package com.ubn.befamous.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.PrimaryKeyJoinColumn;

import org.apache.commons.lang3.StringUtils;
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
		
	//客服-管理者的問題管理第一個頁面     (時間yyyy-mm-dd還沒轉型成yyyymmdd)
	public Question[] queryQuestion(String startDate,String endDate,String productType,String email,String questionType){
		/*
		startDate= DateFormatUtils.format(Long.parseLong(startDate), "yyyyMMddHHmmss");
		endDate= DateFormatUtils.format(Long.parseLong(endDate), "yyyyMMddHHmmss");*/
		
		StringBuilder queryString = new StringBuilder();
		queryString.append("from Question a where (a.handleStatus = :handleStatus)");
		if (StringUtils.isNotEmpty(startDate)
				&& StringUtils.isNotEmpty(endDate)) {
			queryString.append("and (a.questionDate  between :startDate and :endDate)");
		}
		if (StringUtils.isNotEmpty(questionType)) {
			queryString.append("and (a.questionType=:questionType)");
		}
		if (StringUtils.isNotEmpty(productType)) {
			queryString.append("and (a.productionType = :productType)");
		}
		if (StringUtils.isNotEmpty(email)) {
			queryString.append("and (a.email = :email)");
		}

		System.out.println("Q="+queryString);
		Query query = this.sessionFactory.getCurrentSession().createQuery(queryString.toString());
		System.out.println("A="+startDate+","+endDate);
		if(StringUtils.isNotEmpty(startDate)&&StringUtils.isNotEmpty(endDate)){
			System.out.println("2="+queryString);
			query.setString("startDate", startDate);
			System.out.println("3="+queryString);
			
			query.setString("endDate", endDate);
		}
		if(StringUtils.isNotEmpty(productType)){
		query.setString("productType", productType);
		}
		if(StringUtils.isNotEmpty(email)){
		query.setString("email", email);
		}
		if(StringUtils.isNotEmpty(questionType)){
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
	
	//Lucy@20111123
	//儲存創作人刊登的訊息
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)	
	public void saveNews(long userID, String newsName, String newsSouce, String content, String onStatus) {
		
		String date = DateFormatUtils.format(new Date(), "yyyyMMddhhmmss");
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
		String date = DateFormatUtils.format(new Date(), "yyyyMMddhhmmss");
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
		String date = DateFormatUtils.format(new Date(), "yyyyMMddhhmmss");
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
		String nowDate = DateFormatUtils.format(new Date(), "yyyyMMddhhmmss");
		Date tempDate = DateUtils.addDays(new Date(), -14);
		String date = DateFormatUtils.format(tempDate, "yyyyMMddhhmmss");
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
}
