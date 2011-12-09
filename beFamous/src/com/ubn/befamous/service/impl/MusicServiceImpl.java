package com.ubn.befamous.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ubn.befamous.dao.IBaseDao;
import com.ubn.befamous.entity.Admin;
import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.Creator;
import com.ubn.befamous.entity.Hidden;
import com.ubn.befamous.entity.InelegantKeyword;
import com.ubn.befamous.entity.Keyword;
import com.ubn.befamous.entity.Member;
import com.ubn.befamous.entity.MemberStatus;
import com.ubn.befamous.entity.MonthRanking;
import com.ubn.befamous.entity.MusicCategory;
import com.ubn.befamous.entity.News;
import com.ubn.befamous.entity.Order;
import com.ubn.befamous.entity.OrderDetail;
import com.ubn.befamous.entity.PrePaid;
import com.ubn.befamous.entity.PromotionActivity;
import com.ubn.befamous.entity.Question;
import com.ubn.befamous.entity.RecommendActivity;
import com.ubn.befamous.entity.Song;
import com.ubn.befamous.entity.SongPrice;
import com.ubn.befamous.entity.TransactionRcd;
import com.ubn.befamous.entity.WeekRanking;
import com.ubn.befamous.service.MusicService;

@Service("musicService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MusicServiceImpl implements MusicService{
	
	@Autowired
	private SessionFactory sessionfactory;
	
	@Autowired
	@Qualifier("creatorDAO")
	private IBaseDao<Creator, Long> creatorDAO;
	
	@Autowired
	@Qualifier("weekRankingDAO")
	private IBaseDao<WeekRanking, Long> weekRankingDAO;
	
	@Autowired
	@Qualifier("albumDAO")
	private IBaseDao<Album, Long> albumDAO;
	
	@Autowired
	@Qualifier("inelegantKeywordDAO")
	private IBaseDao<InelegantKeyword, Long> inelegantKeywordDAO;
	
	@Autowired
	@Qualifier("songPriceDAO")
	private IBaseDao<SongPrice, Long> songPriceDAO;
	
	@Autowired
	@Qualifier("musicCategoryDAO")
	private IBaseDao<MusicCategory, Long> musicCategoryDAO;
	
	@Autowired
	@Qualifier("songDAO")
	private IBaseDao<Song, Long> songDAO;
	
	@Autowired
	@Qualifier("keywordDAO")
	private IBaseDao<Keyword, Long> keywordDAO;
	
	@Autowired
	@Qualifier("recommendActivityDAO")
	private IBaseDao<RecommendActivity, Long> recommendActivityDAO;
	
	@Autowired
	@Qualifier("memberDAO")
	private IBaseDao<Member, Long> memberDAO;
	
	@Autowired
	@Qualifier("adminDAO")
	private IBaseDao<Admin, Long> adminDAO;
	
	@Autowired
	@Qualifier("hiddenDAO")
	private IBaseDao<Hidden, Long> hiddenDAO;
	
	@Autowired
	@Qualifier("promotionActivityDAO")
	private IBaseDao<PromotionActivity, Long> promotionActivityDAO;
	
	
	//瀏覽專輯與排行榜 - 怡秀write-1115
	
	//瀏覽排行榜
	
	/**
     * 查詢專輯周榜       
     * @param datetime 現在日期時間
     */
    public Album[] queryAlbumsWeekRanking(){
    	
    	Calendar c = Calendar.getInstance();
    	Date d =DateUtils.addDays(new Date(), -c.get(Calendar.DAY_OF_WEEK)+1);
		Date d2 =DateUtils.addDays(new Date(), -c.get(Calendar.DAY_OF_WEEK)-5);
		String sunday = DateFormatUtils.format(d, "yyyyMMdd")+"235959";    //上禮拜的週日
		String monday = DateFormatUtils.format(d2, "yyyyMMdd")+"000000";   //上星期一
		/*
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d, "yyyyMMdd"));
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d2, "yyyyMMdd"));*/
		
		Query query = this.sessionfactory.getCurrentSession().createQuery("Select distinct a from Album a where (a.createDate between :monday and :sunday)");
		query.setString("sunday", sunday);
		query.setString("monday", monday);
		
		List<Album> resultList=(List<Album>)query.list();
		Album[] albumSet = new Album[resultList.size()];
		System.out.println("resultList.size()=="+query.list());
			int i=0;
			for (Album as:resultList) {
				albumSet[i]=as;
				System.out.println("album==>"+albumSet[i].getPid());
				i++;
		}
    	
    	return albumSet;
    } 
	
	/**
     * 查詢專輯月榜         
     * @param datetime 現在日期時間
     */
    public Album[] queryAlbumsMonthRanking(){
    	Calendar c = Calendar.getInstance();
    	Date d =DateUtils.addDays(new Date(), -c.get(Calendar.DAY_OF_MONTH));
		Date d2 =DateUtils.addMonths(new Date(), -1);
		String sunday = DateFormatUtils.format(d, "yyyyMMdd")+"235959";    //上個月的最後一天
		String monday = DateFormatUtils.format(d2, "yyyyMMdd")+"000000";   //上個月的第一天
		/*
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d, "yyyyMMdd"));
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d2, "yyyyMMdd"));*/
		
		Query query = this.sessionfactory.getCurrentSession().createQuery("Select distinct a from Album a where (a.createDate between :monday and :sunday)");
		query.setString("sunday", sunday);
		query.setString("monday", monday);
		
		List<Album> resultList=(List<Album>)query.list();
		Album[] albumSet = new Album[resultList.size()];
		System.out.println("resultList.size()=="+query.list());
			int i=0;
			for (Album as:resultList) {
				albumSet[i]=as;
				System.out.println("album==>"+albumSet[i].getPid());
				i++;
		}
    	
    	return albumSet;
    }
    
    /**
     * 查詢歌曲周榜        
     * @param datetime 現在日期時間
     */
    public Song[] querySongsWeekRanking(){
    	Calendar c = Calendar.getInstance();
    	Date d =DateUtils.addDays(new Date(), -c.get(Calendar.DAY_OF_WEEK)+1);
		Date d2 =DateUtils.addDays(new Date(), -c.get(Calendar.DAY_OF_WEEK)-5);
		String sunday = DateFormatUtils.format(d, "yyyyMMdd")+"235959";    //上禮拜的週日
		String monday = DateFormatUtils.format(d2, "yyyyMMdd")+"000000";   //上星期一
		/*
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d, "yyyyMMdd"));
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d2, "yyyyMMdd"));*/
		
		Query query = this.sessionfactory.getCurrentSession().createQuery("Select distinct a from Song a where (a.createDate between :monday and :sunday)");
		query.setString("sunday", sunday);
		query.setString("monday", monday);
		
		List<Song> resultList=(List<Song>)query.list();
		Song[] songSet = new Song[resultList.size()];
		System.out.println("resultList.size()=="+query.list());
			int i=0;
			for (Song as:resultList) {
				songSet[i]=as;
				System.out.println("album==>"+songSet[i].getPid());
				i++;
		}
    	
    	return songSet;
    }
    
    /**
     * 查詢歌曲月榜  
     * @param datetime 現在日期時間
     */
    public Song[] querySongsMonthRanking(){
    	Calendar c = Calendar.getInstance();
    	Date d =DateUtils.addDays(new Date(), -c.get(Calendar.DAY_OF_MONTH));
		Date d2 =DateUtils.addMonths(new Date(), -1);
		String sunday = DateFormatUtils.format(d, "yyyyMMdd")+"235959";    //上個月的最後一天
		String monday = DateFormatUtils.format(d2, "yyyyMMdd")+"000000";   //上個月的第一天
		/*
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d, "yyyyMMdd"));
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d2, "yyyyMMdd"));*/
		
		Query query = this.sessionfactory.getCurrentSession().createQuery("Select distinct a from Song a where (a.createDate between :monday and :sunday)");
		query.setString("sunday", sunday);
		query.setString("monday", monday);
		
		List<Song> resultList=(List<Song>)query.list();
		Song[] songSet = new Song[resultList.size()];
		System.out.println("resultList.size()=="+query.list());
			int i=0;
			for (Song as:resultList) {
				songSet[i]=as;
				System.out.println("album==>"+songSet[i].getPid());
				i++;
		}
    	
    	return songSet;
    }
    
    /**
     * 查詢創作人週榜  
     * @param datetime 現在日期時間
     */
    public ArrayList queryCreatorWeekRanking(){
    	ArrayList list = new ArrayList();
    	
    	Calendar c = Calendar.getInstance();
    	Date d =DateUtils.addDays(new Date(), -c.get(Calendar.DAY_OF_WEEK)+1);
		Date d2 =DateUtils.addDays(new Date(), -c.get(Calendar.DAY_OF_WEEK)-5);
		String sunday = DateFormatUtils.format(d, "yyyyMMdd")+"235959";    //上禮拜的週日
		String monday = DateFormatUtils.format(d2, "yyyyMMdd")+"000000";   //上星期一
		/*
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d, "yyyyMMdd"));
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d2, "yyyyMMdd"));*/
		
		Query query = this.sessionfactory.getCurrentSession().createQuery("Select distinct a from Creator a where (a.createDate between :monday and :sunday)");
		query.setString("sunday", sunday);
		query.setString("monday", monday);
		
		List<Creator> resultList=(List<Creator>)query.list();
		Creator[] creatorSet = new Creator[resultList.size()];
		Long[] creatorSet2 = new Long[resultList.size()];
		System.out.println("resultList.size()=="+query.list());
			int i=0;
			for (Creator as:resultList) {
				ArrayList list2 = new ArrayList();
				creatorSet[i]=as;
				list2.add(creatorSet[i]);                                            //創作人
				System.out.println("album==>"+creatorSet[i].getId());
				
				Query query2 = this.sessionfactory.getCurrentSession().createQuery("Select count(a) from Album a where a.creator.id=:id");
				query2.setLong("id", creatorSet[i].getId());
				creatorSet2[i]=(Long)query2.uniqueResult();
				list2.add(creatorSet2[i]);                                          //專輯數
				
				list.add(list2);
				i++;
		}
    	
    	return list;
    }
    
    /**
     * 查詢創作人月榜   
     * @param datetime 現在日期時間
     */
    public ArrayList queryCreatorMonthRanking(){
    	ArrayList list = new ArrayList();
    	
    	Calendar c = Calendar.getInstance();
    	Date d =DateUtils.addDays(new Date(), -c.get(Calendar.DAY_OF_MONTH));
		Date d2 =DateUtils.addMonths(new Date(), -1);
		String sunday = DateFormatUtils.format(d, "yyyyMMdd")+"235959";    //上個月的最後一天
		String monday = DateFormatUtils.format(d2, "yyyyMMdd")+"000000";   //上個月的第一天
		/*
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d, "yyyyMMdd"));
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d2, "yyyyMMdd"));*/
		
		Query query = this.sessionfactory.getCurrentSession().createQuery("Select distinct a from Creator a where (a.createDate between :monday and :sunday)");
		query.setString("sunday", sunday);
		query.setString("monday", monday);
		
		List<Creator> resultList=(List<Creator>)query.list();
		Creator[] creatorSet = new Creator[resultList.size()];
		Long[] creatorSet2 = new Long[resultList.size()];
		System.out.println("resultList.size()=="+query.list());
			int i=0;
			for (Creator as:resultList) {
				ArrayList list2 = new ArrayList();
				creatorSet[i]=as;
				list2.add(creatorSet[i]);                                            //創作人
				System.out.println("album==>"+creatorSet[i].getId());
				
				Query query2 = this.sessionfactory.getCurrentSession().createQuery("Select count(a) from Album a where a.creator.id=:id");
				query2.setLong("id", creatorSet[i].getId());
				creatorSet2[i]=(Long)query2.uniqueResult();
				list2.add(creatorSet2[i]);                                          //專輯數
				
				list.add(list2);
				i++;
		}
    	
    	return list;
    }
    
    /**
     * 查詢專輯資料(就是專輯profile頁) 
     * @param albumID 專輯編號
     */
    public ArrayList queryAlbumData(long albumID){
    	ArrayList albumData = new ArrayList();
    	
    	return albumData;
    }
    
    /**
     * 新增專輯試聽     (專輯試聽....?!)
     * @param albumID 專輯編號
     */
    public Song[] addAlbumAudition(long albumID){
    	Song[] albumAudition = {};
    	
    	return albumAudition;
    }
    
    //瀏覽分類頁面
    
    /**
     * 音樂分類-查詢最新專輯       
     * @param musicCatrgoryID 音樂分類編號
     */
    public Album[] queryNewAlbumsForMusicCatrgory (long musicCatrgoryID){
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");   //今天日期時間  yyyyMMddhhmmss
    	String newest = DateFormatUtils.format(DateUtils.addMonths(new Date(), -3), "yyyyMMddHHmmss");    //最近三個月發表的專輯就算最新專輯
    	
    	Query query = this.sessionfactory.getCurrentSession().createQuery("from Album a where (a.createDate between :newest and :datetime) and (a.musicCategory.id = :musicCatrgoryID) and (a.dropDate is null) ORDER BY a.createDate DESC");
    	//Query query = this.sessionfactory.getCurrentSession().createQuery("from Album a where (a.createDate between :datetime and :newest) and (a.musicCategory.id = :musicCatrgoryID) and (a.hidden is empty) and (a.dropDate is null) and (a.creator.memberStatus.statusName = :memberStatus) ORDER BY a.createDate DESC");
		query.setString("datetime", datetime);
		query.setString("newest", newest);
		query.setLong("musicCatrgoryID", musicCatrgoryID);
		//query.setString("memberStatus", "1");       //1: 會員狀態為正常 
    	
		List<Album> resultList=(List<Album>)query.list();
		Album[] albumset = new Album[resultList.size()];
		//RecommendActivity s=resultList.get(0);
		
			int i=0;
			for (Album as:resultList) {
				albumset[i]=as;
				//System.out.println("ssss==>"+albumset[i].getCover());
				i++;
			}
    	
    	return albumset;
    }
    
    /**
     * 音樂分類-查詢熱門專輯      (還未完成  預計1114)
     * @param musicCatrgoryID 音樂分類編號
     */
    public Album[] queryHotAlbumsForMusicCatrgory (long musicCatrgoryID){
    	Album[] albumList = {};
    	
    	return albumList;
    }
    
    //瀏覽最新專輯
    
    /**
     * 查詢最新專輯          
     * @param datetime 現在日期時間
     */
    public Album[] queryNewAlbums (String datetime){
    	//datetime = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
    	String newest = DateFormatUtils.format(DateUtils.addMonths(new Date(), -3), "yyyyMMddHHmmss");    //最近三個月發表的專輯就算最新專輯
    	
    	Query query = this.sessionfactory.getCurrentSession().createQuery("from Album a where (a.createDate between :newest and :datetime) and (a.status = :status) and (a.hidden is empty) and (a.dropDate is null) and (a.creator.memberStatus.statusName = :memberStatus) ORDER BY a.createDate DESC");
		query.setString("datetime", datetime);
		query.setString("newest", newest);
		query.setString("status", "1");       //1: 專輯條件為公開
		query.setString("memberStatus", "1");       //1: 會員狀態為正常 
		
		List<Album> resultList=(List<Album>)query.list();
		Album[] albumset = new Album[resultList.size()];
		//RecommendActivity s=resultList.get(0);
		
			int i=0;
			for (Album as:resultList) {
				albumset[i]=as;
				
				//System.out.println("ssss==>"+albumset[i].getCover());
				i++;
			}
    	
    	return albumset;
    }
    
    //瀏覽大力推清單
    
    /**
     * 查詢推薦專輯    (有問題!!)
     */
    public Album[] queryPromotionAlbums(){
    	String today=DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
    	
    	System.out.println("today"+today);
    	Query query = this.sessionfactory.getCurrentSession().createQuery("from RecommendActivity a where (:today  between a.startDate and a.endDate)");
    	query.setString("today", today);
//		query.setString("memberStatus", "1");       //1: 會員狀態為正常 
//		query.setString("status", "1");       //1: 專輯條件為公開
		
		List<RecommendActivity> resultList=query.list();
		/*if(resultList==null||resultList.isEmpty()){
			System.out.println("resultList is empty");
			return new Album[0];
		}
		System.out.println("resultList is not  empty");*/
		RecommendActivity s=resultList.get(0);
		
			System.out.println("size"+s.getAlbumSet().size());
			Album[] albumset = new Album[s.getAlbumSet().size()];
			int i=0;
			for (Album as:s.getAlbumSet()) {
				albumset[i]=as;
				System.out.println("ssss==>"+albumset[i].getCreator().getUserName());
				i++;
			}
		
    	return albumset;
    }
	
    //上傳音樂-上傳歌曲
    
    /**
     * 儲存專輯
     */
    public long saveAlbum (long creatorId,String albumType, String albumName, String albumBrand, long musicCategory, String albumTag, String albumIntroduction, String albumStatus, String albumCover,String defaultCover){
    	//creatorId=1;
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");  //建立專輯當天日期時間
    	
    	//把會員資料撈出來
    	Query query = this.sessionfactory.getCurrentSession().createQuery("from Member where id = :creatorId");
    	query.setLong("creatorId", creatorId);
    	Member member = (Member)query.uniqueResult();		
    	
    	//查該會員的身分是否為一般會員，如果是一般會員，則將狀態改為2(個人創作者)
    	if(member.getIdentityName().equals("1")){
    		member.setIdentityName("2");
    		this.memberDAO.update(member);
    		
    		Query query2 = this.sessionfactory.getCurrentSession().createQuery("from Member where id = :creatorId");
    		query2.setLong("creatorId", creatorId);
    		member = (Member)query2.uniqueResult();	
    	}
    	//把音樂類別的資料撈出來
    	Query query2 = this.sessionfactory.getCurrentSession().createQuery("from MusicCategory where id = :musicCategoryId");
    	query2.setLong("musicCategoryId", musicCategory);
    	MusicCategory mc = (MusicCategory)query2.uniqueResult();
    	
    	Album album = new Album();
    	album.setType(albumType);
    	album.setName(albumName);
    	album.setBrand(albumBrand);
    	album.setMusicCategory(mc);
    	album.setTag(albumTag);
    	album.setIntroduction(albumIntroduction);
    	album.setStatus(albumStatus);
    	if(!albumCover.equals(null)){
    	album.setCover(albumCover);
    	}else{
    		album.setCover(defaultCover);
    	}
    	album.setCreateDate(datetime);
    	album.setCreator((Creator) member);
    	
    	this.albumDAO.save(album);
    	
    	//儲存專輯後再重新查詢專輯的ID
    	//存好音樂資料後，再拿創作人ID以及現在時間去查剛剛存的音樂ID
    	Query query3 = this.sessionfactory.getCurrentSession().createQuery("from Album where creator.id = :creatorId and createDate = :datetime");
    	query3.setLong("creatorId", creatorId);
    	query3.setString("datetime", datetime);
    	Album aLbum = (Album)query3.uniqueResult();
    	long albumID = aLbum.getPid();
       
    	
    	return albumID;
    }
    
    /**
     * 儲存歌曲
     * @param albumID 專輯編號
     * @param creatorId 創作人編號
     * @param fileName 歌曲的檔案名稱
     */
    public long saveSong(long albumID, long creatorId,String fileName){
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");  //建立當天日期時間
    	
    	//把專輯資料撈出來
    	Query query = this.sessionfactory.getCurrentSession().createQuery("from Album where pid = :albumID");
    	query.setLong("albumID", albumID);
    	Album album = (Album)query.uniqueResult();		
    	
    	Song song = new Song();
    	song.setAlbum(album);
    	song.setCreateDate(datetime);
    	song.setCreateUser(String.valueOf(creatorId));
    	song.setSongFile(fileName);
    	SongPrice sp = new SongPrice();
    	sp.setCreateDate(datetime);
    	sp.setCreateUser(String.valueOf(creatorId));
    	song.setSongPrice(sp);
    	
    	this.songDAO.save(song);
    	
    	//儲存歌曲後再重新查詢歌曲的ID
    	//存好音樂資料後，再拿創作人ID以及現在時間去查剛剛存的音樂ID
    	Query query2 = this.sessionfactory.getCurrentSession().createQuery("from Song where album.pid = :albumID and createDate = :datetime");
    	query2.setLong("albumID", albumID);
    	query2.setString("datetime", datetime);
    	Song s = (Song)query2.uniqueResult();
    	long songID = s.getPid();
    	
    	return songID;
    }
    
    /**
     * 儲存歌曲資訊
     * @param song 歌曲的bean
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void saveSongDetail (long songId,long creatorId, long albumId, String name, String date, String musicCategory, String status, String price, String price2, String discount, String tag){
    	
    	date= StringUtils.replaceChars(date, "-", "")+"000000";
    	
    	//把音樂類別的資料撈出來
    	Query query = this.sessionfactory.getCurrentSession().createQuery("from MusicCategory where id = :musicCategoryId");
    	query.setLong("musicCategoryId", Long.parseLong(musicCategory));
    	MusicCategory mc = (MusicCategory)query.uniqueResult();
    	
    	Song song = this.songDAO.find(songId);
    	song.setName(name);
    	song.setCreateDate(date);
    	song.setMusicCategory(mc);
    	song.setSeconds(status);
    	SongPrice sp = new SongPrice();
    	//SongPrice sp = this.songPriceDAO.find(song.getSongPrice().getId());
    	if(discount.equals("1")){                 
    		sp.setDiscountPrice("5");            //小紅包5元
    		sp.setCreatorReward("8");          //回饋給創作人8點GSiBonus
    	}else if(discount.equals("2")){         
    		sp.setDiscountPrice("15");           //大紅包15元
    		sp.setCreatorReward("3");          //回饋給創作人3點GSiBonus
    	}else{
    		sp.setDiscountPrice("");              //不提供打賞
    		sp.setCreatorReward("");            //不回饋給創作人GSiBonus
    	}
    	
    	sp.setCreateUser(String.valueOf(creatorId));
    	if(price2.equals(null)&&price.equals("2")){
    		sp.setPrice("");
    	}else if(!price2.equals(null)&&price.equals("2")){
    		sp.setPrice("");
    	}else{
    		sp.setPrice(price2);
    	}
    	song.setSongPrice(sp);
    	song.setTag(tag);
    	this.songDAO.update(song);
    	
    }
    
    /**
     * 查詢音樂  (音樂管理-編輯專輯的第二個頁面)
     * @param albumID 歌曲編號
     */
    public Album queryMusic(long albumID){
    	
    	Album album = this.albumDAO.find(albumID);
    	
    	System.out.println("albumName=="+album.getName());
    	Song[] c2 = new Song[album.getSongSet().size()];
		int w = 0;
		//int j = 0;
		for (Song c3:album.getSongSet()) {
			c2[w]=c3;
			System.out.println("aaaaa");
			System.out.println("ID==>"+c2[w].getPid());
			System.out.println("s==>"+c2[w].getName());
			w++;
		}
    	
    	return album;
    }
    
    /**
     * 查詢專輯 (音樂管理-編輯專輯的編輯專輯資訊)
     * @param albumID 專輯編號
     */
    public Album queryAlbum (long albumID){
    	
    	Album album = this.albumDAO.find(albumID);
    	
    	return album;
    }
    
    /**
     * 更新專輯的狀態
     * @param state 專輯狀態
     * @param albumID 專輯ID
     * @param creatorId 創作人ID
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void changeAlbumStatus(String state,long albumID,long creatorId){
        	
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");  //建立當天日期時間
    	
    	Album album = this.albumDAO.find(albumID);
    	album.setStatus(state);
    	album.setModifyDate(datetime);
    	album.setModifier(String.valueOf(creatorId));
    	
    	this.albumDAO.update(album);
    }
    
    /**
     * 更新專輯   
     * @param album 專輯的bean
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void updateAlbum (long creatorId,long albumID, String albumType,String name,String date,String brand,String musicCategory,String tag,String cover,String cover2,String introduction,String status){
        	
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");  //建立當天日期時間
    	
    	Album album = this.albumDAO.find(albumID);
    	album.setType(albumType);
    	album.setName(name);
    	album.setModifyDate(datetime);
    	album.setModifier(String.valueOf(creatorId));
    	album.setBrand(brand);    	
    	MusicCategory mc = this.musicCategoryDAO.find(Long.parseLong(musicCategory));
    	album.setMusicCategory(mc);
    	album.setTag(tag);
    	if(cover.equals("")){
    	album.setCover(cover2);
    	}else{
    		album.setCover(cover);
    	}
    	album.setIntroduction(introduction);
    	album.setStatus(status);
    	
    	this.albumDAO.update(album);
    }
    
    /**
     * 查詢歌曲
     * @param songID 歌曲編號
     */
    public Song querySong(long songID){
    	
    	Song song = this.songDAO.find(songID);
    	
    	System.out.println("songName=="+song.getName());
    	
    	return song;
    }
    
    /**
     * 更新歌曲   
     * @param song 歌曲的bean
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void updateSong(long creatorId,long songID, String songName, String musicType, String MOPEND, String status, String price, String price2, String discount, String tag, String lyrics, String lyricist, String composer, String producer){
    	
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");  //建立當天日期時間
    	
    	Song song = this.songDAO.find(songID);
    	song.setName(songName);
    	MusicCategory mc = this.musicCategoryDAO.find(Long.parseLong(musicType));
    	song.setMusicCategory(mc);
    	song.setModifyDate(datetime);
    	song.setModifier(String.valueOf(creatorId));
    	song.setSeconds(status);
    	SongPrice sp = this.songPriceDAO.find(song.getSongPrice().getId());
    	
    	if(discount.equals("1")){                 
    		sp.setDiscountPrice("5");            //小紅包5元
    		sp.setCreatorReward("8");          //回饋給創作人8點GSiBonus
    	}else if(discount.equals("2")){         
    		sp.setDiscountPrice("15");           //大紅包15元
    		sp.setCreatorReward("3");          //回饋給創作人3點GSiBonus
    	}else{
    		sp.setDiscountPrice("");              //不提供打賞
    		sp.setCreatorReward("");            //不回饋給創作人GSiBonus
    	}
    	
    	sp.setModifier(String.valueOf(creatorId));
    	sp.setModifyDate(datetime);
    	if(price2.equals("")&&price.equals("2")){
    		sp.setPrice("");
    	}else if(!price2.equals("")&&price.equals("2")){
    		sp.setPrice("");
    	}else{
    		sp.setPrice(price2);
    	}
    	song.setSongPrice(sp);
    	song.setTag(tag);
    	song.setLyrics(lyrics);
    	song.setLyricist(lyricist);
    	song.setComposer(composer);
    	song.setProducer(producer);
    	
    	this.songDAO.update(song);
    	
    }
    
    /**
     * 刪除歌曲    
     * @param songID 歌曲編號
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void deleteSong(long songID){
    	
    	this.songDAO.delete(songID);
    	
    }
    
    //瀏覽所有歌曲
    /**
     * 查詢所有專輯 (音樂管理-編輯專輯的第一個頁面)
     * @param creatorID 創作人編號
     */
    public Album[] queryOwnAlbums (long creatorID){
    	
    	Query query = this.sessionfactory.getCurrentSession().createQuery("from Album a where (a.creator.id = :creatorId) and (a.dropDate is null) ORDER BY createDate DESC");
    	query.setLong("creatorId", creatorID);
    	
    	List<Album> albumList = (List<Album>)query.list();
    	Album[] albumList2 = new Album[albumList.size()];
		int i = 0;
		for (Album albumList3:albumList) {
			albumList2[i]=albumList3;
			System.out.println("a==>"+albumList2[i].getPid());
			i++;
		}
    	
    	return albumList2;
    }
    
    //查詢關鍵字
    
    /**
     * 查詢關鍵字      (關鍵字的部分還沒加依照關鍵字筆數作排序)
     * @param keyword 關鍵字
     */
    public ArrayList queryKeyword (String keyword){
    	ArrayList list = new ArrayList();
    	
    	//專輯
    	Query query = this.sessionfactory.getCurrentSession().createQuery("from Album a where a.name like :keyword order by a.createDate desc");
    	query.setString("keyword", "%"+keyword+"%");
    	List<Album> albumList = (List<Album>)query.list();
    	Album[] albumList2 = new Album[albumList.size()];
		int i = 0;
		for (Album albumList3:albumList) {
			albumList2[i]=albumList3;
			System.out.println("Album==>"+albumList2[i].getPid());
			i++;
		}
		list.add(albumList2);
    	
		//歌曲
		query = this.sessionfactory.getCurrentSession().createQuery("from Song a where (a.name like :keyword) or (a.album.name like :keyword) or (a.album.creator.userName like :keyword) order by a.createDate desc");
    	query.setString("keyword", "%"+keyword+"%");
    	List<Song> songList = (List<Song>)query.list();
    	Song[] songList2 = new Song[songList.size()];
		int j = 0;
		for (Song songList3:songList) {
			songList2[j]=songList3;
			System.out.println("Song==>"+songList2[j].getPid());
			j++;
		}
		list.add(songList2);
		
		//創作人
		query = this.sessionfactory.getCurrentSession().createQuery("from Creator a where (a.userName like :keyword) order by a.createDate desc");
    	query.setString("keyword", "%"+keyword+"%");
    	List<Creator> creatorList = (List<Creator>)query.list();
    	Creator[] creatorList2 = new Creator[creatorList.size()];
		int k = 0;
		for (Creator creatorList3:creatorList) {
			creatorList2[k]=creatorList3;
			System.out.println("Creator==>"+creatorList2[k].getId());
			k++;
		}
		list.add(creatorList2);
		
		//消息
		query = this.sessionfactory.getCurrentSession().createQuery("from News a where (a.newsName like :keyword) order by a.createDate desc");
    	query.setString("keyword", "%"+keyword+"%");
    	List<News> newsList = (List<News>)query.list();
    	News[] newsList2 = new News[newsList.size()];
		int n = 0;
		for (News newsList3:newsList) {
			newsList2[n]=newsList3;
			System.out.println("Creator==>"+newsList2[n].getId());
			n++;
		}
		list.add(newsList2);
		
		//熱門關鍵字
		query = this.sessionfactory.getCurrentSession().createQuery("select distinct a.name from Keyword a ");
    	List<String> keywordList = (List<String>)query.list();
    	String[] keywordList2 = new String[keywordList.size()];
    	System.out.println("keywordList.size()==>"+keywordList.size());
		int m = 0;
		for (String keywordList3:keywordList) {
			keywordList2[m]=keywordList3;
			System.out.println("Keyword==>"+keywordList2[m]);
			m++;
		}
		list.add(keywordList2);
    	return list;
    }
    
    /**
     * 查詢專輯的關鍵字
     * @param keyword 關鍵字
     */
    public Album[] queryKeywordForAlbums (String keyword){
    	Query query = this.sessionfactory.getCurrentSession().createQuery("from Album a where a.name like :keyword order by a.createDate desc");
    	query.setString("keyword", "%"+keyword+"%");
    	List<Album> albumList = (List<Album>)query.list();
    	Album[] albumList2 = new Album[albumList.size()];
		int i = 0;
		for (Album albumList3:albumList) {
			albumList2[i]=albumList3;
			System.out.println("Album==>"+albumList2[i].getName());
			i++;
		}
    	return albumList2;
    }
    
    /**
     * 查詢歌曲的關鍵字
     * @param keyword 關鍵字
     */
    public Song[] queryKeywordForSongs (String keyword){
    	Query query = this.sessionfactory.getCurrentSession().createQuery("from Song a where (a.name like :keyword) or (a.album.name like :keyword) or (a.album.creator.userName like :keyword) order by a.createDate desc");
    	query.setString("keyword", "%"+keyword+"%");
    	List<Song> songList = (List<Song>)query.list();
    	Song[] songList2 = new Song[songList.size()];
		int j = 0;
		for (Song songList3:songList) {
			songList2[j]=songList3;
			System.out.println("Song==>"+songList2[j].getName());
			j++;
		}
    	
    	return songList2;
    }
    
    /**
     * 查詢創作人的關鍵字
     * @param keyword 關鍵字
     */
    public Creator[] queryKeywordForCreators (String keyword){
    	Query query = this.sessionfactory.getCurrentSession().createQuery("from Creator a where (a.userName like :keyword) order by a.createDate desc");
    	query.setString("keyword", "%"+keyword+"%");
    	List<Creator> creatorList = (List<Creator>)query.list();
    	Creator[] creatorList2 = new Creator[creatorList.size()];
		int k = 0;
		for (Creator creatorList3:creatorList) {
			creatorList2[k]=creatorList3;
			System.out.println("Creator==>"+creatorList2[k].getUserName());
			k++;
		}
    	return creatorList2;
    }
    
    /**
     * 查詢消息的關鍵字
     * @param keyword 關鍵字
     */
    public News[] queryKeywordForNews (String keyword){
    	//消息
    	Query query = this.sessionfactory.getCurrentSession().createQuery("from News a where (a.newsName like :keyword) order by a.createDate desc");
    	query.setString("keyword", "%"+keyword+"%");
    	List<News> newsList = (List<News>)query.list();
    	News[] newsList2 = new News[newsList.size()];
    	int n = 0;
    	for (News newsList3:newsList) {
    		newsList2[n]=newsList3;
    		System.out.println("News==>"+newsList2[n].getNewsName());
    		n++;
    	}
    	
    	return newsList2;
    }
    
    //管理關鍵字
    
    /**
     * 查詢不雅字關鍵字  
     */
    public InelegantKeyword[] queryInelegantKeywords(){

    	InelegantKeyword[] keyword = this.inelegantKeywordDAO.findAll();
    	InelegantKeyword[] k = new InelegantKeyword[keyword.length];
		int i = 0;
		for (InelegantKeyword k2:keyword) {
			k[i]=k2;
			System.out.println("a==>"+k[i].getName());
			i++;
		}
    	
    	return k;
    }
    
    /**
     * 新增不雅字關鍵字 
     * @param managerID 管理者編號
     * @param keyword 關鍵字
     */
    public void addInelegantKeywords(long managerID, String keyword){
    	
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");  //當天日期時間

    	InelegantKeyword kw = new InelegantKeyword();
    	kw.setCreateDate(datetime);
    	kw.setCreateUser(String.valueOf(managerID));
    	kw.setName(keyword);
    	
    	this.inelegantKeywordDAO.save(kw);
    }
    
    /**
     * 刪除不雅字關鍵字    
     * @param inelegantKeywordID 不雅關鍵字編號
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void deleteInelegantKeywords(long inelegantKeywordID){
    	
    	this.inelegantKeywordDAO.delete(inelegantKeywordID);
    }
    
    //管理專輯類別與排行榜
    //管理音樂類別
    
    /**
     * 查詢音樂類別
     */
    public MusicCategory[] queryMusicCategory (){

    	MusicCategory[] musicCategory = this.musicCategoryDAO.findAll();
    	
    	Query query = this.sessionfactory.getCurrentSession().createQuery("from MusicCategory a where a.parent is null and a.dropDate is null");
		
		List<MusicCategory> resultList=(List<MusicCategory>)query.list();
		MusicCategory[] mcSet = new MusicCategory[resultList.size()];
		
			int i=0;
			for (MusicCategory mc2:resultList) {
				mcSet[i]=mc2;
				System.out.println("ssss==>"+mcSet[i].getName());
				i++;
			}
    	
    	return mcSet;
    }
    
    /**
     * 查詢音樂類別
     */
    public MusicCategory musicCategory (String mcID){
    	
    	MusicCategory musicCategory = this.musicCategoryDAO.find(Long.parseLong(mcID));
    	
    	return musicCategory;
    }
    
    /**
     * 刪除音樂類別    
     * @param musicCategoryID 音樂分類編號
     * @param managerID 管理者編號
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void deleteMusicCategory (long musicCategoryID){
    	
    	this.musicCategoryDAO.delete(musicCategoryID);
    	
    }
    
    /**
     * 新增音樂類別
     * @param managerID 管理者編號
     */
    public void addMusicCategory(long managerID,String categoryName){
    	
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");  //當天日期時間
    	
    	MusicCategory mc = new MusicCategory();
    	mc.setName(categoryName);
    	mc.setCreateDate(datetime);
    	mc.setCreateUser(String.valueOf(managerID));
    	
    	this.musicCategoryDAO.save(mc);
    }
    
    /**
     * 新增音樂子類別
     * @param managerID 管理者編號
     */
    public void addSubMusicCategory(long managerID, String categoryName, long fatherID){
    	
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");  //當天日期時間
    	
    	MusicCategory mc = new MusicCategory();
    	mc.setName(categoryName);
    	mc.setCreateDate(datetime);
    	mc.setCreateUser(String.valueOf(managerID));
    	mc.setParent(String.valueOf(fatherID));
    	
    	this.musicCategoryDAO.save(mc);
    }
    
    /**
     * 更新音樂類別      
     * @param musicCategoryID 音樂分類編號
     * @param name 音樂分類名稱
     * @param managerID 管理者編號
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void updateMusicCategory(long musicCategoryID, String name, long managerID){
    	
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");  //當天日期時間
    	
    	MusicCategory mc = this.musicCategoryDAO.find(musicCategoryID);
    	mc.setName(name);
    	mc.setModifier(String.valueOf(managerID));
    	mc.setModifyDate(datetime);
    	
    	this.musicCategoryDAO.update(mc);
    }
    
    /**
     * 查詢子音樂類別
     * @param musicCategoryID 音樂分類編號
     */
    public MusicCategory[] querySubMusicCatrgory(long musicCategoryID){
    	
    	Query query = this.sessionfactory.getCurrentSession().createQuery("from MusicCategory a where a.parent = :musicCategoryID");
		query.setLong("musicCategoryID", musicCategoryID);
		
		List<MusicCategory> resultList=(List<MusicCategory>)query.list();
		MusicCategory[] mcSet = new MusicCategory[resultList.size()];
		
			int i=0;
			for (MusicCategory mc2:resultList) {
				mcSet[i]=mc2;
				System.out.println("category==>"+mcSet[i].getName());
				i++;
			}
    	
    	return mcSet;
    }
    
    //管理排行榜
    /**
     * 查詢音樂類別清單
     */
    public MusicCategory[] musicCategoryList(){

    	MusicCategory[] musicCategory = this.musicCategoryDAO.findAll();
    	
    	return musicCategory;
    }
    
    /**
     * 管理者查詢專輯週榜
     * @param albumName 專輯名稱
     * @param creatorName 創作人姓名
     * @param musicCategoryID 音樂分類編號
     * @param startDate 專輯上架起始日期
     * @param endDate 專輯上架結束日期
     */
    public ArrayList queryAlbumWeekRankingForAdmin(String albumName, String creatorName, String musicCategoryID, String startDate, String endDate){

    	ArrayList list = new ArrayList();
    	
    	Calendar c = Calendar.getInstance();
    	Date d =DateUtils.addDays(new Date(), -c.get(Calendar.DAY_OF_WEEK)+1);
		Date d2 =DateUtils.addDays(new Date(), -c.get(Calendar.DAY_OF_WEEK)-5);
		String sunday = DateFormatUtils.format(d, "yyyyMMdd")+"235959";    //上禮拜的週日
		String monday = DateFormatUtils.format(d2, "yyyyMMdd")+"000000";   //上星期一
		/*
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d, "yyyyMMdd"));
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d2, "yyyyMMdd"));*/
		
		StringBuilder queryString = new StringBuilder();
			queryString.append("from Album a where (1=1) ");
			
		if(StringUtils.isNotBlank(startDate)&& StringUtils.isNotBlank(endDate)) {
			startDate= StringUtils.replaceChars(startDate, "-", "")+"000000";
			endDate= StringUtils.replaceChars(endDate, "-", "")+"235959";
			
			queryString.append(" and (a.createDate  between :startDate and :endDate)");
		}
		if (StringUtils.isNotBlank(albumName)) {
			queryString.append(" and (a.name=:albumName)");
		}
		if (StringUtils.isNotBlank(creatorName)) {
			queryString.append(" and (a.creator.userName = :creatorName)");
		}
		if (StringUtils.isNotBlank(musicCategoryID)) {
			queryString.append(" and (a.musicCategory.id = :musicCategoryID)");
		}		
		
		
		System.out.println("queryString=="+queryString);
		Query query = this.sessionfactory.getCurrentSession().createQuery(queryString.toString());

		if(StringUtils.isNotBlank(startDate)&& StringUtils.isNotBlank(endDate)) {
			query.setString("startDate", startDate);
			query.setString("endDate", endDate);
		}
		if (StringUtils.isNotBlank(albumName)) {
		query.setString("albumName", albumName);
		}
		if (StringUtils.isNotBlank(creatorName)) {
		query.setString("creatorName", creatorName);
		}
		if (StringUtils.isNotBlank(musicCategoryID)) {
		query.setString("musicCategoryID", musicCategoryID);
		}
		
		
		List<Album> resultList=(List<Album>)query.list();
		Album[] albumSet = new Album[resultList.size()];
		Integer[] albumSet2 = new Integer[resultList.size()];
		Long[] albumSet3 = new Long[resultList.size()];
			int i=0;
			for (Album as:resultList) {
				ArrayList list2 = new ArrayList();
				albumSet[i]=as;
				list2.add(as);                                                        //專輯
				
				Query query3 = this.sessionfactory.getCurrentSession().createQuery("select size(b) from Album a join a.audition b where (b.createDate between :monday and :sunday) and a.pid=:id");
				query3.setString("sunday", sunday);
				query3.setString("monday", monday);
				query3.setLong("id", albumSet[i].getPid());
				albumSet2[i]=(Integer)query3.uniqueResult();
				list2.add(albumSet2[i]);                                           //試聽數
				
				Query query2 = this.sessionfactory.getCurrentSession().createQuery("select count(b.productionCategory.pid) from Order a join a.orderDetail b where b.productionCategory.pid = :id and a.createDate between :monday and :sunday");
				query2.setLong("id", albumSet[i].getPid());
				query2.setString("sunday", sunday);
				query2.setString("monday", monday);
				albumSet3[i]=(Long)query2.uniqueResult();
				list2.add(albumSet3[i]);                                            //被購買數
				list.add(list2);
				System.out.println("buy==>"+query2.uniqueResult());
				System.out.println("song==>"+albumSet[i].getPid());
				System.out.println("count==>"+albumSet2[i]);
				i++;
		}
    	return list;
    }
    
    /**
     * 管理者查詢專輯月榜
     * @param albumName 專輯名稱
     * @param creatorName 創作人姓名
     * @param musicCategoryID 音樂分類編號
     * @param startDate 專輯上架起始日期
     * @param endDate 專輯上架結束日期
     */
    public ArrayList queryAlbumMonthRankingForAdmin (String albumName, String creatorName, String musicCategoryID, String startDate, String endDate){
    	
    	ArrayList list = new ArrayList();
    	
    	Calendar c = Calendar.getInstance();
    	Date d =DateUtils.addDays(new Date(), -c.get(Calendar.DAY_OF_MONTH));
		Date d2 =DateUtils.addMonths(new Date(), -1);
		String sunday = DateFormatUtils.format(d, "yyyyMMdd")+"235959";    //上個月的最後一天
		String monday = DateFormatUtils.format(d2, "yyyyMMdd")+"000000";   //上個月的第一天
		/*
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d, "yyyyMMdd"));
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d2, "yyyyMMdd"));*/
		
		StringBuilder queryString = new StringBuilder();
		queryString.append("from Album a where (1=1) ");
		
		System.out.println("queryString=="+queryString);
		if(StringUtils.isNotBlank(startDate)&& StringUtils.isNotBlank(endDate)) {
			startDate= StringUtils.replaceChars(startDate, "-", "")+"000000";
			endDate= StringUtils.replaceChars(endDate, "-", "")+"235959";
			
			queryString.append(" and (a.createDate  between :startDate and :endDate)");
		}
		if (StringUtils.isNotBlank(albumName)) {
			queryString.append(" and (a.name=:albumName)");
		}
		if (StringUtils.isNotBlank(creatorName)) {
			queryString.append(" and (a.creator.userName = :creatorName)");
		}
		if (StringUtils.isNotBlank(musicCategoryID)) {
			queryString.append(" and (a.musicCategory.id = :musicCategoryID)");
		}				
		
		System.out.println("queryString=="+queryString);
		Query query = this.sessionfactory.getCurrentSession().createQuery(queryString.toString());

		if(StringUtils.isNotBlank(startDate)&& StringUtils.isNotBlank(endDate)) {
			query.setString("startDate", startDate);
			query.setString("endDate", endDate);
		}
		if (StringUtils.isNotBlank(albumName)) {
		query.setString("albumName", albumName);
		}
		if (StringUtils.isNotBlank(creatorName)) {
		query.setString("creatorName", creatorName);
		}
		if (StringUtils.isNotBlank(musicCategoryID)) {
		query.setString("musicCategoryID", musicCategoryID);
		}		
		
		List<Album> resultList=(List<Album>)query.list();
		Album[] albumSet = new Album[resultList.size()];
		Integer[] albumSet2 = new Integer[resultList.size()];
		Long[] albumSet3 = new Long[resultList.size()];
		
			int i=0;
			for (Album as:resultList) {
				ArrayList list2 = new ArrayList();
				albumSet[i]=as;
				list2.add(as);                                                          //專輯
				
				Query query3 = this.sessionfactory.getCurrentSession().createQuery("select size(b) from Album a join a.audition b where (b.createDate between :monday and :sunday) and a.pid=:id");
				query3.setString("sunday", sunday);
				query3.setString("monday", monday);
				query3.setLong("id", albumSet[i].getPid());
				albumSet2[i]=(Integer)query3.uniqueResult();
				list2.add(albumSet2[i]);                                            //試聽數
				
				Query query2 = this.sessionfactory.getCurrentSession().createQuery("select count(b.productionCategory.pid) from Order a join a.orderDetail b where b.productionCategory.pid = :id and a.createDate between :monday and :sunday");
				query2.setLong("id", albumSet[i].getPid());
				query2.setString("sunday", sunday);
				query2.setString("monday", monday);
				albumSet3[i]=(Long)query2.uniqueResult();
				list2.add(albumSet3[i]);                                            //被購買數
				list.add(list2);
				System.out.println("buy==>"+query2.uniqueResult());
				System.out.println("song==>"+albumSet[i].getPid());
				System.out.println("count==>"+albumSet2[i]);
				i++;
		}
    	
    	return list;
    }
    
    /**
     * 管理者查詢歌曲週榜
     * @param songName 歌曲名稱
     * @param albumName 專輯名稱
     * @param creatorName 創作人姓名
     * @param musicCategoryID 音樂分類編號
     * @param startDate 專輯上架起始日期
     * @param endDate 專輯上架結束日期
     */
    public ArrayList querySongWeekRankingForAdmin(String songName, String albumName, String creatorName, String musicCategoryID, String startDate, String endDate){
    	
    	ArrayList list = new ArrayList();
    	
    	Calendar c = Calendar.getInstance();
    	Date d =DateUtils.addDays(new Date(), -c.get(Calendar.DAY_OF_WEEK)+1);
		Date d2 =DateUtils.addDays(new Date(), -c.get(Calendar.DAY_OF_WEEK)-5);
		String sunday = DateFormatUtils.format(d, "yyyyMMdd")+"235959";    //上禮拜的週日
		String monday = DateFormatUtils.format(d2, "yyyyMMdd")+"000000";   //上星期一
		/*
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d, "yyyyMMdd"));
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d2, "yyyyMMdd"));*/
		
		StringBuilder queryString = new StringBuilder();
		queryString.append("from Song a where (1=1)");
		
		if(StringUtils.isNotBlank(startDate)&& StringUtils.isNotBlank(endDate)) {
			startDate= StringUtils.replaceChars(startDate, "-", "")+"000000";
			endDate= StringUtils.replaceChars(endDate, "-", "")+"235959";
			
			queryString.append(" and (a.createDate between :startDate and :endDate)");
		}
		if (StringUtils.isNotBlank(songName)) {
			queryString.append(" and (a.name=:songName)");
		}
		if (StringUtils.isNotBlank(albumName)) {
			queryString.append(" and (a.album.name=:albumName)");
		}
		if (StringUtils.isNotBlank(creatorName)) {
			queryString.append(" and (a.album.creator.userName like :creatorName)");
		}
		if (StringUtils.isNotBlank(musicCategoryID)) {
			queryString.append(" and (a.musicCategory.id = :musicCategoryID)");
		}		
				
		System.out.println("queryString=="+queryString);
		Query query = this.sessionfactory.getCurrentSession().createQuery(queryString.toString());

		if(StringUtils.isNotBlank(startDate)&& StringUtils.isNotBlank(endDate)) {
			query.setString("startDate", startDate);
			query.setString("endDate", endDate);
		}
		if (StringUtils.isNotBlank(songName)) {
			query.setString("songName", songName);
		}
		if (StringUtils.isNotBlank(albumName)) {
		query.setString("albumName", albumName);
		}
		if (StringUtils.isNotBlank(creatorName)) {
		query.setString("creatorName", creatorName);
		}
		if (StringUtils.isNotBlank(musicCategoryID)) {
		query.setString("musicCategoryID", musicCategoryID);
		}
		
				
		List<Song> resultList=(List<Song>)query.list();
		Song[] songSet = new Song[resultList.size()];
		Integer[] songSet2 = new Integer[resultList.size()];
		Long[] songSet3 = new Long[resultList.size()];
			int i=0;
			for (Song as:resultList) {
				ArrayList list2 = new ArrayList();
				songSet[i]=as;
				list2.add(as);                                                      //歌曲
				
				Query query3 = this.sessionfactory.getCurrentSession().createQuery("select size(b) from Song a join a.audition b where (b.createDate between :monday and :sunday) and a.pid=:id");
				query3.setString("sunday", sunday);
				query3.setString("monday", monday);
				query3.setLong("id", songSet[i].getPid());
				songSet2[i]=(Integer)query3.uniqueResult();
				list2.add(songSet2[i]);                                          //試聽數
				
				Query query2 = this.sessionfactory.getCurrentSession().createQuery("select count(b.productionCategory.pid) from Order a join a.orderDetail b where b.productionCategory.pid = :id and a.createDate between :monday and :sunday");
				query2.setLong("id", songSet[i].getPid());
				query2.setString("sunday", sunday);
				query2.setString("monday", monday);
				songSet3[i]=(Long)query2.uniqueResult();
				list2.add(songSet3[i]);                                          //被購買數
				list.add(list2);
				System.out.println("buy==>"+query2.uniqueResult());
				System.out.println("song==>"+songSet[i].getPid());
				System.out.println("count==>"+songSet2[i]);
				i++;
				}
				
		return list;
    }
    
    /**
     * 管理者查詢歌曲月榜
     * @param songName 歌曲名稱
     * @param albumName 專輯名稱
     * @param creatorName 創作人姓名
     * @param musicCategoryID 音樂分類編號
     * @param startDate 專輯上架起始日期
     * @param endDate 專輯上架結束日期
     */
    public ArrayList querySongMonthRankingForAdmin (String songName, String albumName, String creatorName, String musicCategoryID, String startDate, String endDate){
    	
    	ArrayList list = new ArrayList();
    	
    	Calendar c = Calendar.getInstance();
    	Date d =DateUtils.addDays(new Date(), -c.get(Calendar.DAY_OF_MONTH));
		Date d2 =DateUtils.addMonths(new Date(), -1);
		String sunday = DateFormatUtils.format(d, "yyyyMMdd")+"235959";    //上個月的最後一天
		String monday = DateFormatUtils.format(d2, "yyyyMMdd")+"000000";   //上個月的第一天
		
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d, "yyyyMMdd"));
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d2, "yyyyMMdd"));
		
		StringBuilder queryString = new StringBuilder();
		queryString.append("from Song a where (1=1)");
		
		System.out.println("queryString=="+queryString);
		if(StringUtils.isNotBlank(startDate)&& StringUtils.isNotBlank(endDate)) {
			startDate= StringUtils.replaceChars(startDate, "-", "")+"000000";
			endDate= StringUtils.replaceChars(endDate, "-", "")+"235959";
			
			queryString.append(" and (a.createDate  between :startDate and :endDate)");
		}
		if (StringUtils.isNotBlank(songName)) {
			queryString.append(" and (a.name=:songName)");
		}
		if (StringUtils.isNotBlank(albumName)) {
			queryString.append(" and (a.album.name=:albumName)");
		}
		if (StringUtils.isNotBlank(creatorName)) {
			queryString.append(" and (a.album.creator.userName = :creatorName)");
		}
		if (StringUtils.isNotBlank(musicCategoryID)) {
			queryString.append(" and (a.musicCategory.id = :musicCategoryID)");
		}		
				
		System.out.println("queryString=="+queryString);
		Query query = this.sessionfactory.getCurrentSession().createQuery(queryString.toString());

		if(StringUtils.isNotBlank(startDate)&& StringUtils.isNotBlank(endDate)) {
			query.setString("startDate", startDate);
			query.setString("endDate", endDate);
		}
		if (StringUtils.isNotBlank(songName)) {
			query.setString("songName", songName);
		}
		if (StringUtils.isNotBlank(albumName)) {
		query.setString("albumName", albumName);
		}
		if (StringUtils.isNotBlank(creatorName)) {
		query.setString("creatorName", creatorName);
		}
		if (StringUtils.isNotBlank(musicCategoryID)) {
		query.setString("musicCategoryID", musicCategoryID);
		}
				
		List<Song> resultList=(List<Song>)query.list();
		Song[] songSet = new Song[resultList.size()];
		Integer[] songSet2 = new Integer[resultList.size()];
		Long[] songSet3 = new Long[resultList.size()];
		System.out.println("resultList.size()=="+query.list());
			int i=0;
			for (Song as:resultList) {
				ArrayList list2 = new ArrayList();
				songSet[i]=as;
				list2.add(as);                                                 //歌曲
				
				Query query3 = this.sessionfactory.getCurrentSession().createQuery("select size(b) from Song a join a.audition b where (b.createDate between :monday and :sunday) and a.pid=:id");
				query3.setString("sunday", sunday);
				query3.setString("monday", monday);
				query3.setLong("id", songSet[i].getPid());
				songSet2[i]=(Integer)query3.uniqueResult();
				list2.add(songSet2[i]);                                     //試聽數
				                       
				Query query2 = this.sessionfactory.getCurrentSession().createQuery("select count(b.productionCategory.pid) from Order a join a.orderDetail b where b.productionCategory.pid = :id and a.createDate between :monday and :sunday");
				query2.setLong("id", songSet[i].getPid());
				query2.setString("sunday", sunday);
				query2.setString("monday", monday);
				songSet3[i]=(Long)query2.uniqueResult();
				list2.add(songSet3[i]);                                     //被購買數
				list.add(list2);
				System.out.println("buy==>"+query2.uniqueResult());
				System.out.println("song==>"+songSet[i].getPid());
				System.out.println("count==>"+songSet2[i]);
				i++;
		}
    	return list;
    }
    
    /**
     * 管理者查詢創作人週榜
     * @param creatorName 創作人姓名
     */
    public ArrayList queryCreatorWeekRankingForAdmin (String creatorName){
    	
    	ArrayList list = new ArrayList();
    	
    	Calendar c = Calendar.getInstance();
    	Date d =DateUtils.addDays(new Date(), -c.get(Calendar.DAY_OF_WEEK)+1);
		Date d2 =DateUtils.addDays(new Date(), -c.get(Calendar.DAY_OF_WEEK)-5);
		String sunday = DateFormatUtils.format(d, "yyyyMMdd")+"235959";    //上禮拜的週日
		String monday = DateFormatUtils.format(d2, "yyyyMMdd")+"000000";   //上星期一
		
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d, "yyyyMMdd"));
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d2, "yyyyMMdd"));
		
		StringBuilder queryString = new StringBuilder();
		queryString.append("from Creator a where (1=1) ");
		
		if (StringUtils.isNotBlank(creatorName)) {
			queryString.append(" and (a.userName = :creatorName)");
		}
				
		System.out.println("queryString=="+queryString);
		Query query = this.sessionfactory.getCurrentSession().createQuery(queryString.toString());

		if (StringUtils.isNotBlank(creatorName)) {
		query.setString("creatorName", creatorName);
		}
		
		List<Creator> resultList=(List<Creator>)query.list();
		Creator[] creatorSet = new Creator[resultList.size()];
		Integer[] creatorSet2 = new Integer[resultList.size()];
		Integer[] creatorSet3 = new Integer[resultList.size()];
		System.out.println("resultList.size()=="+query.list());
			int i=0;
			for (Creator as:resultList) {
				ArrayList list2 = new ArrayList();
				creatorSet[i]=as;                               
				list2.add(as);                                                 //創作人
				creatorSet2[i]=creatorSet[i].getFan().size();     
				list2.add(creatorSet2[i]);                                 //粉絲數
				
				Query query3 = this.sessionfactory.getCurrentSession().createQuery("select size(b) from Member a join a.hit b where (b.createDate between :monday and :sunday) and a.id=:id");
				query3.setString("sunday", sunday);
				query3.setString("monday", monday);
				query3.setLong("id", creatorSet[i].getId());
				creatorSet3[i]=(Integer)query3.uniqueResult();
				list2.add(creatorSet3[i]);                                 //點閱數
				list.add(list2);
				System.out.println("song==>"+creatorSet[i].getId());
				System.out.println("count1==>"+creatorSet[i].getFan().size());
				System.out.println("count2==>"+creatorSet2[i]);
				i++;
		}
    	return list;
    }
    
    /**
     * 管理者查詢創作人月榜
     * @param creatorName 創作人姓名
     */
    public ArrayList queryCreatorMonthRankingForAdmin (String creatorName){
    	
    	ArrayList list = new ArrayList();
    	
    	Calendar c = Calendar.getInstance();
    	Date d =DateUtils.addDays(new Date(), -c.get(Calendar.DAY_OF_MONTH));
		Date d2 =DateUtils.addMonths(new Date(), -1);
		String sunday = DateFormatUtils.format(d, "yyyyMMdd")+"235959";    //上個月的最後一天
		String monday = DateFormatUtils.format(d2, "yyyyMMdd")+"000000";   //上個月的第一天
		
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d, "yyyyMMdd"));
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d2, "yyyyMMdd"));
		
		StringBuilder queryString = new StringBuilder();
		queryString.append("from Creator a where (1=1) ");
		
		if (StringUtils.isNotBlank(creatorName)) {
			queryString.append(" and (a.userName = :creatorName)");
		}
				
		System.out.println("queryString=="+queryString);
		Query query = this.sessionfactory.getCurrentSession().createQuery(queryString.toString());

		if (StringUtils.isNotBlank(creatorName)) {
		query.setString("creatorName", creatorName);
		}
		
		List<Creator> resultList=(List<Creator>)query.list();
		Creator[] creatorSet = new Creator[resultList.size()];
		Integer[] creatorSet2 = new Integer[resultList.size()];
		Integer[] creatorSet3 = new Integer[resultList.size()];
		System.out.println("resultList.size()=="+query.list());
			int i=0;
			for (Creator as:resultList) {
				ArrayList list2 = new ArrayList();
				creatorSet[i]=as;
				list2.add(as);
				creatorSet2[i]=creatorSet[i].getFan().size();        //粉絲數
				list2.add(creatorSet2[i]);
				
				Query query3 = this.sessionfactory.getCurrentSession().createQuery("select size(b) from Member a join a.hit b where (b.createDate between :monday and :sunday) and a.id=:id");
				query3.setString("sunday", sunday);
				query3.setString("monday", monday);
				query3.setLong("id", creatorSet[i].getId());
				creatorSet3[i]=(Integer)query3.uniqueResult();
				list2.add(creatorSet3[i]);
				list.add(list2);                                                 //點閱數
				System.out.println("song==>"+creatorSet[i].getId());
				System.out.println("count1==>"+creatorSet[i].getFan().size());
				System.out.println("count2==>"+creatorSet2[i]);
				i++;
		}
    	return list;
    }
    
    /**
     * 更新專輯週榜的CP值
     * @param CP CP值
     * @param modifyDate 音樂分類編號
     * @param startDate 專輯周榜起始日期
     * @param endDate 專輯周架結束日期
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void updateAlbumWeekCP (String adminID,String albumID, int CP){
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");  //建立當天日期時間
    	Calendar c = Calendar.getInstance();
		Date d =DateUtils.addDays(new Date(), -c.get(Calendar.DAY_OF_WEEK)+2);       //這裡拜的星期一
		Date d2 =DateUtils.addDays(new Date(), -c.get(Calendar.DAY_OF_WEEK)+8);//這裡拜的星期日
		String monday = DateFormatUtils.format(d, "yyyyMMdd")+"000000";    
		String sunday = DateFormatUtils.format(d2, "yyyyMMdd")+"235959";   
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d, "yyyyMMdd"));
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d2, "yyyyMMdd"));
    	
		Album s = this.albumDAO.find(Long.parseLong(albumID));
    	WeekRanking wk = new WeekRanking();
    	wk.setModifyValue(String.valueOf(CP));
    	wk.setCreateDate(datetime);
    	wk.setEndDate(sunday);
    	wk.setModifyDate(datetime);
    	wk.setStartDate(monday);
    	wk.setCreateUser(adminID);
    	wk.setModifier(adminID);
    	s.getWeekRanking().add(wk);
        this.albumDAO.update(s);
    	
    }
    
    /**
     * 更新專輯月榜的CP值
     * @param CP CP值
     * @param modifyDate 音樂分類編號
     * @param startDate 專輯周榜起始日期
     * @param endDate 專輯周架結束日期
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void updateAlbumMonthCP (String adminID,String albumID, int CP){
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");  //建立當天日期時間
    	Calendar c = Calendar.getInstance();
    	Date d =DateUtils.addDays(new Date(), -c.get(Calendar.DAY_OF_MONTH)+1);       //這個月的第一天
		Date d2 =DateUtils.addMonths(new Date(), -1);//這個月的最後一天
		String monday = DateFormatUtils.format(d, "yyyyMMdd")+"000000";    
		String sunday = DateFormatUtils.format(d2, "yyyyMMdd")+"235959";   
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d, "yyyyMMdd"));
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d2, "yyyyMMdd"));
    	
		Album s = this.albumDAO.find(Long.parseLong(albumID));
    	MonthRanking wk = new MonthRanking();
    	wk.setModifyValue(String.valueOf(CP));
    	wk.setCreateDate(datetime);
    	wk.setEndDate(sunday);
    	wk.setModifyDate(datetime);
    	wk.setStartDate(monday);
    	wk.setCreateUser(adminID);
    	wk.setModifier(adminID);
    	s.getMonthRanking().add(wk);
        this.albumDAO.update(s);
    }
    
    /**
     * 更新歌曲週榜的CP值
     * @param CP CP值
     * @param modifyDate 音樂分類編號
     * @param startDate 專輯周榜起始日期
     * @param endDate 專輯周架結束日期
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void updateSongWeekCP (String adminID,String songID, int CP){
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");  //建立當天日期時間
    	Calendar c = Calendar.getInstance();
		Date d =DateUtils.addDays(new Date(), -c.get(Calendar.DAY_OF_WEEK)+2);       //這裡拜的星期一
		Date d2 =DateUtils.addDays(new Date(), -c.get(Calendar.DAY_OF_WEEK)+8);//這裡拜的星期日
		String monday = DateFormatUtils.format(d, "yyyyMMdd")+"000000";    
		String sunday = DateFormatUtils.format(d2, "yyyyMMdd")+"235959";   
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d, "yyyyMMdd"));
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d2, "yyyyMMdd"));
    	
		Song s = this.songDAO.find(Long.parseLong(songID));
    	WeekRanking wk = new WeekRanking();
    	wk.setModifyValue(String.valueOf(CP));
    	wk.setCreateDate(datetime);
    	wk.setEndDate(sunday);
    	wk.setModifyDate(datetime);
    	wk.setStartDate(monday);
    	wk.setCreateUser(adminID);
    	wk.setModifier(adminID);
    	s.getWeekRanking().add(wk);
        this.songDAO.update(s);
    }
    
    /**
     * 更新歌曲月榜的CP值
     * @param CP CP值
     * @param modifyDate 音樂分類編號
     * @param startDate 專輯月榜起始日期
     * @param endDate 專輯月榜結束日期
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void updateSongMonthCP (String adminID,String songID, int CP){
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");  //建立當天日期時間
    	Calendar c = Calendar.getInstance();
    	Date d =DateUtils.addDays(new Date(), -c.get(Calendar.DAY_OF_MONTH)+1);       //這個月的第一天
		Date d2 =DateUtils.addMonths(new Date(), -1);//這個月的最後一天
		String monday = DateFormatUtils.format(d, "yyyyMMdd")+"000000";    
		String sunday = DateFormatUtils.format(d2, "yyyyMMdd")+"235959";   
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d, "yyyyMMdd"));
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d2, "yyyyMMdd"));
    	
		Song s = this.songDAO.find(Long.parseLong(songID));
    	MonthRanking wk = new MonthRanking();
    	wk.setModifyValue(String.valueOf(CP));
    	wk.setCreateDate(datetime);
    	wk.setEndDate(sunday);
    	wk.setModifyDate(datetime);
    	wk.setStartDate(monday);
    	wk.setCreateUser(adminID);
    	wk.setModifier(adminID);
    	s.getMonthRanking().add(wk);
        this.songDAO.update(s);
    }
    
    /**
     * 更新創作人週榜的CP值
     * @param CP CP值
     * @param modifyDate 音樂分類編號
     * @param startDate 專輯周榜起始日期
     * @param endDate 專輯周架結束日期
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void updateCreatorWeekCP (String adminID,String creatorID, int CP){
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");  //建立當天日期時間
    	Calendar c = Calendar.getInstance();
		Date d =DateUtils.addDays(new Date(), -c.get(Calendar.DAY_OF_WEEK)+2);       //這裡拜的星期一
		Date d2 =DateUtils.addDays(new Date(), -c.get(Calendar.DAY_OF_WEEK)+8);//這裡拜的星期日
		String monday = DateFormatUtils.format(d, "yyyyMMdd")+"000000";    
		String sunday = DateFormatUtils.format(d2, "yyyyMMdd")+"235959";   
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d, "yyyyMMdd"));
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d2, "yyyyMMdd"));
    	
		Creator s = this.creatorDAO.find(Long.parseLong(creatorID));
    	WeekRanking wk = new WeekRanking();
    	wk.setModifyValue(String.valueOf(CP));
    	wk.setCreateDate(datetime);
    	wk.setEndDate(sunday);
    	wk.setModifyDate(datetime);
    	wk.setStartDate(monday);
    	wk.setCreateUser(adminID);
    	wk.setModifier(adminID);
    	s.getWeekRanking().add(wk);
        this.creatorDAO.update(s);
    }
    
    /**
     * 更新創作人月榜的CP值
     * @param CP CP值
     * @param modifyDate 音樂分類編號
     * @param startDate 專輯月榜起始日期
     * @param endDate 專輯月榜結束日期
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void updateCreatorMonthCP (String adminID,String creatorID, int CP){
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");  //建立當天日期時間
    	Calendar c = Calendar.getInstance();
    	Date d =DateUtils.addDays(new Date(), -c.get(Calendar.DAY_OF_MONTH)+1);       //這個月的第一天
		Date d2 =DateUtils.addMonths(new Date(), -1);//這個月的最後一天
		String monday = DateFormatUtils.format(d, "yyyyMMdd")+"000000";    
		String sunday = DateFormatUtils.format(d2, "yyyyMMdd")+"235959";   
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d, "yyyyMMdd"));
		System.out.println("Calendar.MONDAY=="+DateFormatUtils.format(d2, "yyyyMMdd"));
    	
		Creator s = this.creatorDAO.find(Long.parseLong(creatorID));
    	MonthRanking wk = new MonthRanking();
    	wk.setModifyValue(String.valueOf(CP));
    	wk.setCreateDate(datetime);
    	wk.setEndDate(sunday);
    	wk.setModifyDate(datetime);
    	wk.setStartDate(monday);
    	wk.setCreateUser(adminID);
    	wk.setModifier(adminID);
    	s.getMonthRanking().add(wk);
        this.creatorDAO.update(s);
    }
    
    /**
     * 試聽      (怪怪的，好像少了檔案路徑)
     * @param songID 歌曲編號
     */
    public Song listenSong (long songID){
    	Song s = new Song();
    	return s;
    }
    
    
    //瀏覽專輯Profile頁
    
    /**
     * 查詢創作人專輯
     * @param albumID 專輯編號
     * @param creatorID 創作人編號
     */
    public ArrayList queryCreatorAlbums(long albumID){
    	//找出該張專輯
    	Album a = this.albumDAO.find(albumID);
    	System.out.println("Album==>"+a.getName());
    	
    	//找出與該張專輯的音樂分類相同的其他專輯
    	Query query2 = this.sessionfactory.getCurrentSession().createQuery("from Album a where a.musicCategory.id = :musicCategory and a.creator.id is not :creatorID and a.dropDate is null and a.hidden is empty and a.creator.memberStatus.statusName = :memberStatus order by a.createDate desc");
    	query2.setLong("musicCategory", a.getMusicCategory().getId());
    	query2.setLong("creatorID", a.getCreator().getId());
    	query2.setString("memberStatus", "正常");
    	
		List<Album> resultList=(List<Album>)query2.list();
		Album[] RecommendAlbum = new Album[resultList.size()];
		System.out.println("RecommendAlbum==>"+resultList.size());
			int i=0;
			for (Album mc2:resultList) {
				RecommendAlbum[i]=mc2;
				System.out.println("RecommendAlbum==>"+RecommendAlbum[i].getName());
				i++;
			}
			
		//找出該創作人的其他專輯
		Query query3 = this.sessionfactory.getCurrentSession().createQuery("from Album a where a.pid is not :albumID and a.creator.id = :creatorID and a.dropDate is null and a.hidden is empty order by a.createDate desc");
		query3.setLong("albumID", albumID);
	    query3.setLong("creatorID", a.getCreator().getId());
	    
	    List<Album> resultList2=(List<Album>)query3.list();
		Album[] CreatorAlbum = new Album[resultList2.size()];
		System.out.println("CreatorAlbum==>"+resultList2.size());
			int j=0;
			for (Album mc2:resultList2) {
				CreatorAlbum[j]=mc2;
				//System.out.println("CreatorAlbum==>"+CreatorAlbum[j].getName());
				j++;
			}
    	
    	ArrayList arrayList = new ArrayList();
    	arrayList.add(a);
    	arrayList.add(RecommendAlbum);
    	arrayList.add(CreatorAlbum);
    	
    	return arrayList;
    }
    
    /**
     * 查詢專輯的歌曲      
     * @param albumID 專輯編號
     */
    public Song[] querySongSet(long albumID){
    	
    	Album a = this.albumDAO.find(albumID);
 		Song[] SongSet = new Song[a.getSongSet().size()];
 		System.out.println("CreatorAlbum==>"+a.getSongSet().size());
 			int j=0;
 			for (Song mc2:a.getSongSet()) {
 				SongSet[j]=mc2;
 				//System.out.println("CreatorAlbum==>"+CreatorAlbum[j].getName());
 				j++;
 			}
    	return SongSet;
    }
    
    /**
     * 查詢創作人下的其他專輯      
     * @param albumID 專輯編號
     */
    public Album[] queryOtherAlbum(long albumID,long creatorID){
    	
    	//找出該創作人的其他專輯
		Query query3 = this.sessionfactory.getCurrentSession().createQuery("from Album a where a.pid is not :albumID and a.creator.id = :creatorID and a.dropDate is null and a.hidden is empty order by a.createDate desc");
		query3.setLong("albumID", albumID);
	    query3.setLong("creatorID", creatorID);
	    
	    List<Album> resultList2=(List<Album>)query3.list();
		Album[] CreatorAlbum = new Album[resultList2.size()];
		System.out.println("CreatorAlbum==>"+resultList2.size());
			int j=0;
			for (Album mc2:resultList2) {
				CreatorAlbum[j]=mc2;
				System.out.println("CreatorAlbum==>"+CreatorAlbum[j].getName());
				j++;
			}
    	return CreatorAlbum;
    }
    
    /**
     * 查詢專輯簡介      (目前頁面上還沒有)
     * @param albumID 專輯編號
     */
    public Album queryAlbumIntroduction(long albumID){
    	
    	Album a = this.albumDAO.find(albumID);
    	return a;
    }
    
    /**
     * 新增專輯評論(迴響)    <=先擱著，因為有用到facebook
     * @param albumID 專輯編號
     */
    public void addAlbumComment(long albumID,String albumComment){
    	
    }
    
    /**
     * 加入歌曲試聽
     * @param songID 歌曲編號
     */
    public Song addSongAudition(long songID){
    	Song s = new Song();
    	return s;
    }
    
    //管理音樂上傳
    
    /**
     * 查詢音樂類別
     */
    public MusicCategory[] queryAllMusicCategory(){
    	MusicCategory[] mc = this.musicCategoryDAO.findAll();
    	return mc;
    }
    
    /**
     * 查詢專輯
     * @param albumType 專輯類型
     * @param startDate 專輯公開開始日期
     * @param endDate 專輯公開結束日期
     * @param creatorName 創作人姓名
     * @param musicCatrgory 音樂分類
     */
    public Album[] queryForAlbums(String albumType,String startDate,String endDate,String creatorName,String musicCatrgory){
    	if (StringUtils.isNotEmpty(startDate)
				&& StringUtils.isNotEmpty(endDate)) {
			startDate= StringUtils.replaceChars(startDate, "-", "")+"000000";
			endDate= StringUtils.replaceChars(endDate, "-", "")+"235959";
		}
		
		StringBuilder queryString = new StringBuilder();
		queryString.append("from Album a where (1=1)");   
		if(StringUtils.isNotBlank(startDate)&& StringUtils.isNotBlank(endDate)) {
			startDate= StringUtils.replaceChars(startDate, "-", "")+"000000";
			endDate= StringUtils.replaceChars(endDate, "-", "")+"235959";
			
			queryString.append("and (a.createDate  between :startDate and :endDate)");
			
		}
		if (StringUtils.isNotBlank(albumType)) {
			queryString.append("and (a.type=:albumType)");
		}
		if (StringUtils.isNotBlank(creatorName)) {
			queryString.append("and (a.creator.userName = :creatorName)");
		}
		if (StringUtils.isNotBlank(musicCatrgory)) {
			queryString.append("and (a.musicCategory.id = :musicCatrgory)");
		}

		
		Query query = this.sessionfactory.getCurrentSession().createQuery(queryString.toString());

		if(StringUtils.isNotBlank(startDate)&& StringUtils.isNotBlank(endDate)) {
			query.setString("startDate", startDate);
			query.setString("endDate", endDate);
		}
		if (StringUtils.isNotBlank(albumType)) {
		query.setString("albumType", albumType);
		}
		if (StringUtils.isNotBlank(creatorName)) {
		query.setString("creatorName", creatorName);
		}
		if (StringUtils.isNotBlank(musicCatrgory)) {
		query.setString("musicCatrgory", musicCatrgory);
		}
		
		List<Album> resultList=(List<Album>)query.list();
		Album[] albumSet = new Album[resultList.size()];
		
			int i=0;
			for (Album as:resultList) {
				albumSet[i]=as;
				System.out.println("ssss==>"+albumSet[i].getPid());
				i++;
			}
    	return albumSet;
    }

    /**
     * 隱藏專輯
     * @param albumID 專輯編號
     * @param adminID 管理者編號
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public Hidden hideAlbum(long albumID,long adminID){
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");  //建立當天日期時間
    	
    	Admin admin = this.adminDAO.find(adminID);
    	
    	Hidden hidden = new Hidden();
    	Album a = this.albumDAO.find(albumID);
    	a.setHidden(hidden);
    	hidden.setAlbum(a);
    	hidden.setCreateDate(datetime);
    	hidden.setCreateUser(admin);
    	hidden.setStartDate(datetime);
    	this.hiddenDAO.save(hidden);
    	this.albumDAO.update(a);
    	
    	Query query = this.sessionfactory.getCurrentSession().createQuery("from Hidden a where a.album.pid = :albumID");
		query.setLong("albumID", albumID);
		Hidden h = (Hidden)query.uniqueResult();
    	
    	return h;
    }
    
    /**
     * 取消隱藏專輯,更新專輯隱藏狀態
     * @param albumID 專輯編號
     * @param adminID 管理者編號
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void displayAlbum(long albumID,long adminID){
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");  //建立當天日期時間
    	
    	Query query = this.sessionfactory.getCurrentSession().createQuery("from Hidden a where a.album.pid = :albumID");
		query.setLong("albumID", albumID);
		Hidden h = (Hidden)query.uniqueResult();
		Admin admin = this.adminDAO.find(adminID);
		h.setModifier(admin);
		h.setModifyDate(datetime);
		h.setEndDate(datetime);
		this.hiddenDAO.update(h);
    }
    
    /**
     * 儲存專輯的隱藏原因
     * @param albumHiddenID 專輯隱藏編號
     * @param albumHiddenReason 專輯隱藏原因
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void saveAlbumHiddenReason(long albumHiddenID,String albumHiddenReason,long adminID){
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");  //建立當天日期時間
    	
    	Hidden h = this.hiddenDAO.find(albumHiddenID);
    	Admin admin = this.adminDAO.find(adminID);
    	h.setModifier(admin);
    	h.setModifyDate(datetime);
    	h.setHiddenReason(albumHiddenReason);
    	this.hiddenDAO.update(h);
    }
    
    /**
     * 查詢歌曲
     * @param albumName 專輯名稱
     * @param creatorName 創作人名稱
     * @param musicCatrgory 音樂類別
     */
    public Song[] queryForSongs(String albumName,String creatorName,String musicCatrgory){
		
		StringBuilder queryString = new StringBuilder();
		queryString.append("from Song a where (a.dropDate is null)");   
		
		if (StringUtils.isNotBlank(albumName)) {
			queryString.append("and (a.album.name=:albumName)");
		}
		if (StringUtils.isNotBlank(creatorName)) {
			queryString.append("and (a.album.creator.userName = :creatorName)");
		}
		if (StringUtils.isNotBlank(musicCatrgory)) {
			queryString.append("and (a.musicCategory.id = :musicCatrgory)");
		}

		
		Query query = this.sessionfactory.getCurrentSession().createQuery(queryString.toString());

		if (StringUtils.isNotBlank(albumName)) {
		query.setString("albumName", albumName);
		}
		if (StringUtils.isNotBlank(creatorName)) {
		query.setString("creatorName", creatorName);
		}
		if (StringUtils.isNotBlank(musicCatrgory)) {
		query.setString("musicCatrgory", musicCatrgory);
		}
		
		List<Song> resultList=(List<Song>)query.list();
		Song[] songSet = new Song[resultList.size()];
		
			int i=0;
			for (Song as:resultList) {
				songSet[i]=as;
				System.out.println("ssss==>"+songSet[i].getPid());
				i++;
			}
    	
    	return songSet;
    }
    
    /**
     * 隱藏歌曲
     * @param songID 歌曲編號
     * @param adminID 管理者編號
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public Hidden hideSong(long songID,long adminID){
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");  //建立當天日期時間
    	
    	Admin admin = this.adminDAO.find(adminID);
    	
    	Hidden hidden = new Hidden();
    	Song a = this.songDAO.find(songID);
    	a.setHidden(hidden);
    	hidden.setSong(a);
    	hidden.setCreateDate(datetime);
    	hidden.setCreateUser(admin);
    	hidden.setStartDate(datetime);
    	this.hiddenDAO.save(hidden);
    	this.songDAO.update(a);
    	
    	Query query = this.sessionfactory.getCurrentSession().createQuery("from Hidden a where a.song.pid = :songID");
		query.setLong("songID", songID);
		Hidden h = (Hidden)query.uniqueResult();
    	
    	return h;
    }
    
    /**
     * 取消隱藏歌曲,更新歌曲隱藏狀態
     * @param songID 歌曲編號
     * @param adminID 管理者編號
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void displaySong(long songID,long adminID){
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");  //建立當天日期時間
    	
    	Query query = this.sessionfactory.getCurrentSession().createQuery("from Hidden a where a.song.pid = :songID");
		query.setLong("songID", songID);
		Hidden h = (Hidden)query.uniqueResult();
		Admin admin = this.adminDAO.find(adminID);
		h.setModifier(admin);
		h.setModifyDate(datetime);
		h.setEndDate(datetime);
		this.hiddenDAO.update(h);
    }
    
    /**
     * 儲存歌曲的隱藏原因
     * @param songHiddenID 歌曲隱藏編號
     * @param songHiddenReason 歌曲隱藏原因
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void saveSongHiddenReason(long songHiddenID,String songHiddenReason,long adminID){
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");  //建立當天日期時間
    	
    	Hidden h = this.hiddenDAO.find(songHiddenID);
    	Admin admin = this.adminDAO.find(adminID);
    	h.setModifier(admin);
    	h.setModifyDate(datetime);
    	h.setHiddenReason(songHiddenReason);
    	this.hiddenDAO.update(h);
    }
    
    /**
     * 查詢歌詞
     * @param songID 歌曲編號
     */
    public Song querySongLyrics(long songID){
    	Song s =this.songDAO.find(songID);
    	return s;
    }
    
    
    //大力推活動-新增活動
    /**
     * 儲存推薦活動
     * @param songID 歌曲編號
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void saveRecommendActivityForAlbum(String adminID,String recommendActName,String startDate,String endDate,String[] recommendActContent,String recommendActState){
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");  //建立當天日期時間
    	startDate= StringUtils.replaceChars(startDate, "-", "")+"000000";
		endDate= StringUtils.replaceChars(endDate, "-", "")+"235959";
    	
		RecommendActivity ra =new RecommendActivity();
    	
    	for(int i=0;i<recommendActContent.length;i++){
    	Album a = this.albumDAO.find(Long.parseLong(recommendActContent[i]));
    	ra.getAlbumSet().add(a);
    	}
    	
    	ra.setStartDate(startDate);
    	ra.setEndDate(endDate);
    	ra.setCreateDate(datetime);
    	ra.setCreateUser(adminID);
    	ra.setTitle(recommendActName);
    	ra.setStatus(recommendActState);
    	this.recommendActivityDAO.save(ra);
    }
    
    /**
     * 查詢推薦活動清單
     * @param songID 歌曲編號
     */
    public RecommendActivity[] queryRecommendActivities(String year,String month){
    	
    	Integer y = Integer.valueOf(year); //year轉型為Integer，以便查詢。
		Integer m = Integer.valueOf(month); //month轉型為Integer，以便查詢。
    	
    	Query query = this.sessionfactory.getCurrentSession().createQuery("from RecommendActivity a where ((year(a.createDate) in (:year)) and (month(a.createDate) in (:month)))");
    	query.setInteger("year", y);
    	query.setInteger("month", m);
    	
    	List<RecommendActivity> resultList2=(List<RecommendActivity>)query.list();
    	RecommendActivity[] raList = new RecommendActivity[resultList2.size()];
		System.out.println("amount==>"+resultList2.size());
			int j=0;
			for (RecommendActivity mc2:resultList2) {
				raList[j]=mc2;
				System.out.println("CreatorAlbum==>"+raList[j].getTitle());
				j++;
			}
    	
    	return raList;
    }
    
    /**
     * 查詢推薦活動
     * @param songID 歌曲編號
     */
    public RecommendActivity queryRecommendActivity(String recommendActID){
    	RecommendActivity ra= this.recommendActivityDAO.find(Long.parseLong(recommendActID));
    	return ra;
    }
    
    /**
     * 更新推薦活動
     * @param songID 歌曲編號
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void updateRecommendActivity(String adminID,String recommendActID,String recommendActName,String startDate,String endDate,String[] recommendActContent,String recommendActState){
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");  //建立當天日期時間
    	startDate= StringUtils.replaceChars(startDate, "-", "")+"000000";
		endDate= StringUtils.replaceChars(endDate, "-", "")+"235959";
    	
		RecommendActivity ra =this.recommendActivityDAO.find(Long.parseLong(recommendActID));
    	
    	for(int i=0;i<recommendActContent.length;i++){
    	Album a = this.albumDAO.find(Long.parseLong(recommendActContent[i]));
    	ra.getAlbumSet().add(a);
    	}

    	ra.setStartDate(startDate);
    	ra.setEndDate(endDate);
    	ra.setModifyDate(datetime);
    	ra.setModifier(adminID);
    	ra.setTitle(recommendActName);
    	ra.setStatus(recommendActState);
    	this.recommendActivityDAO.update(ra);
    }

    /**
     * 查詢專輯，然後將專輯加至推薦活動中
     * @param songID 歌曲編號
     */
    public Map queryJoinAlbumsForRec(String recommendActID){
    	Album a = this.albumDAO.find(Long.parseLong(recommendActID));
    	System.out.println("a"+a.getName());
        Map<String,String> map = new HashMap();
        map.put("name", a.getName());
        map.put("albumID", String.valueOf(a.getPid()));
        map.put("creatorID", String.valueOf(a.getCreator().getId()));
        map.put("creatorName", a.getCreator().getUserName());
    	return map;
    }
    
    /**
     * 查詢歌曲，然後將歌曲加至推薦活動中
     * @param songID 歌曲編號
     */
    public Map queryJoinSongsForRec(String recommendActID){
    	Song a = this.songDAO.find(Long.parseLong(recommendActID));
    	 Map<String,String> map = new HashMap();
         map.put("name", a.getName());
         map.put("songID", String.valueOf(a.getPid()));
         map.put("albumName", a.getAlbum().getName());
         map.put("albumID", String.valueOf(a.getAlbum().getPid()));
         map.put("creatorID", String.valueOf(a.getAlbum().getCreator().getId()));
         map.put("creatorName", a.getAlbum().getCreator().getUserName());
     	return map;
    }
    
    /**
     * 查詢推薦活動裡專輯被購買數
     * @param songID 歌曲編號
     */
    public ArrayList  queryJoinAlbumForRec(String recommendActID){
    	ArrayList list = new ArrayList();
    	
    	Query query = this.sessionfactory.getCurrentSession().createQuery("select b from RecommendActivity a join a.albumSet b,TransactionRcd d where b=d.productionCategory.pid and a.id=:activity");
    	query.setString("activity", recommendActID);
    	
    	List<Album> resultList2=(List<Album>)query.list();
    	Album[] raList = new Album[resultList2.size()];
    	Long[] raList2 = new Long[resultList2.size()];
		System.out.println("CreatorAlbum==>"+resultList2.size());
			int j=0;
			for (Album mc2:resultList2) {
				ArrayList list2 = new ArrayList();
				raList[j]=mc2;
				list2.add(mc2);                                                  //參加的專輯
				
				Query query2 = this.sessionfactory.getCurrentSession().createQuery("select count(d.productionCategory.pid) from RecommendActivity a join a.albumSet b,TransactionRcd d where b=d.productionCategory.pid and a.id=:activity");
				query2.setString("activity", recommendActID);
				raList2[j]=(Long)query2.uniqueResult();
				System.out.println("AlbumAmount==>"+raList2[j]);
				list2.add(raList2[j]);                                            //被購買數
				list.add(list2);
				
				System.out.println("CreatorAlbum==>"+raList[j].getName());
				j++;
			}
    	
    	return list;
    }
    
    /**
     * 查詢有購買推薦活動裡專輯的會員
     * @param songID 歌曲編號
     */
    public Member[] queryJoinMembersForRec(String recommendActID){
    	
    	Query query = this.sessionfactory.getCurrentSession().createQuery("select distinct c.member from RecommendActivity a join a.albumSet b,TransactionRcd d,Order c join c.orderDetail e where b=d.productionCategory.pid and a.id=:activity and d.id=e.id");
    	query.setString("activity", recommendActID);
    	
    	List<Member> resultList2=(List<Member>)query.list();
    	Member[] raList = new Member[resultList2.size()];
		System.out.println("amount==>"+resultList2.size());
			int j=0;
			for (Member mc2:resultList2) {
				raList[j]=mc2;                                                      //有購買此活動裡專輯的會員
				
				System.out.println("CreatorAlbum==>"+raList[j].getUserName());
				j++;
			}
    	
    	return raList;
    }
    
    //行銷活動
    /**
     * 儲存行銷活動
     * @param songID 歌曲編號
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void savePromotionActivity(String adminID,String promotionActName,String startDate,String endDate,String prepaidMoney,String prepaidCount,String promotionActContentType,String[] promotionActContent,String promotionActCondition,String presentType,int presentBonus,String presentDeadline,String promotionActState){
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");  //建立當天日期時間
    	startDate= StringUtils.replaceChars(startDate, "-", "")+"000000";
		endDate= StringUtils.replaceChars(endDate, "-", "")+"235959";
    	
		PromotionActivity pa =new PromotionActivity();
    	
		if(promotionActContentType.equals("1")){
			pa.setContent(prepaidMoney);
	    }else if(promotionActContentType.equals("2")){
	    	pa.setContent(prepaidCount);
	    }else if(promotionActContentType.equals("3")){
    	for(int i=0;i<promotionActContent.length;i++){
    	Album a = this.albumDAO.find(Long.parseLong(promotionActContent[i]));
    	pa.getAlbumSet().add(a);
    	}}else if(promotionActContentType.equals("4")){
    	for(int i=0;i<promotionActContent.length;i++){
        	Song a = this.songDAO.find(Long.parseLong(promotionActContent[i]));
        	pa.getSongSet().add(a);
        }}
		
    	pa.setStartDate(startDate);
    	pa.setEndDate(endDate);
    	pa.setCreateDate(datetime);
    	pa.setCreateUser(adminID);
    	pa.setTitle(promotionActName);
    	pa.setStatus(promotionActState);
    	pa.setContentCondition(promotionActContentType);
    	pa.setRewardCondition(presentType);
    	pa.setReward(String.valueOf(presentBonus));
    	pa.setRewardDeadline(presentDeadline);
    	pa.setCondition(promotionActCondition);
    	this.promotionActivityDAO.save(pa);
    }
    
    /**
     * 查詢推薦的專輯
     * @param songID 歌曲編號
     */
    public PromotionActivity[] queryPromotionActivities(String year,String month){
    	Integer y = Integer.valueOf(year); //year轉型為Integer，以便查詢。
		Integer m = Integer.valueOf(month); //month轉型為Integer，以便查詢。
    	
    	Query query = this.sessionfactory.getCurrentSession().createQuery("from PromotionActivity a where ((year(a.createDate) in (:year)) and (month(a.createDate) in (:month)))");
    	query.setInteger("year", y);
    	query.setInteger("month", m);
    	
    	List<PromotionActivity> resultList2=(List<PromotionActivity>)query.list();
    	PromotionActivity[] paList = new PromotionActivity[resultList2.size()];
		System.out.println("CreatorAlbum==>"+resultList2.size());
			int j=0;
			for (PromotionActivity mc2:resultList2) {
				paList[j]=mc2;
				System.out.println("CreatorAlbum==>"+paList[j].getTitle());
				j++;
			}
    	return paList;
    }
     
    /**
     * 查詢推薦的專輯
     * @param songID 歌曲編號
     */
    public PromotionActivity queryPromotionActivity(String promotionActID){
    	PromotionActivity pa =this.promotionActivityDAO.find(Long.parseLong(promotionActID));
    	return pa;
    }
    
    /**
     * 查詢推薦的專輯
     * @param songID 歌曲編號
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void updatePromotionActivity(String adminID,String promotionActID,String promotionActName,String startDate,String endDate,String prepaidMoney,String prepaidCount,String promotionActContentType,String[] promotionActContent,String promotionActCondition,String presentType,int presentBonus,String presentDeadline,String promotionActState){
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");  //建立當天日期時間
    	startDate= StringUtils.replaceChars(startDate, "-", "")+"000000";
		endDate= StringUtils.replaceChars(endDate, "-", "")+"235959";
    	
		PromotionActivity pa =this.promotionActivityDAO.find(Long.parseLong(promotionActID));
    	
    	if(promotionActContentType.equals("1")){
			pa.setContent(prepaidMoney);
	    }else if(promotionActContentType.equals("2")){
	    	pa.setContent(prepaidCount);
	    }else if(promotionActContentType.equals("3")){
    	for(int i=0;i<promotionActContent.length;i++){
    	Album a = this.albumDAO.find(Long.parseLong(promotionActContent[i]));
    	pa.getAlbumSet().add(a);
    	}}else if(promotionActContentType.equals("4")){
    	for(int i=0;i<promotionActContent.length;i++){
        	Song a = this.songDAO.find(Long.parseLong(promotionActContent[i]));
        	pa.getSongSet().add(a);
        }}
		
    	pa.setStartDate(startDate);
    	pa.setEndDate(endDate);
    	pa.setCreateDate(datetime);
    	pa.setCreateUser(adminID);
    	pa.setTitle(promotionActName);
    	pa.setStatus(promotionActState);
    	pa.setContentCondition(promotionActContentType);
    	pa.setRewardCondition(presentType);
    	pa.setReward(String.valueOf(presentBonus));
    	pa.setRewardDeadline(presentDeadline);
    	pa.setCondition(promotionActCondition);
    	
    	this.promotionActivityDAO.update(pa);
    }
    
    /**
     * 查詢行銷活動的會員
     * @param songID 歌曲編號
     */
    public Member[] queryJoinMembersForPro(String promotionActID){
    	Query query = this.sessionfactory.getCurrentSession().createQuery("select distinct c.member from PromotionActivity a join a.albumSet b,PromotionActivity h join h.songSet f,TransactionRcd d,Order c join c.orderDetail e, PrePaid g where b=d.productionCategory.pid or f=d.productionCategory.pid or g=d.productionCategory.pid and a.id=:activity and d.id=e.id");
    	query.setString("activity", promotionActID);
    	
    	List<Member> resultList2=(List<Member>)query.list();
    	Member[] raList = new Member[resultList2.size()];
		System.out.println("amount==>"+resultList2.size());
			int j=0;
			for (Member mc2:resultList2) {
				raList[j]=mc2;                                                      //有購買此活動裡專輯的會員
				
				System.out.println("CreatorAlbum==>"+raList[j].getUserName());
				j++;
			}
			return raList;
    }
    
    /**
     * 查詢行銷活動的GSiMoney
     * @param songID 歌曲編號
     */
    public Order[] queryJoinGSiMoneyForPro(String promotionActID){
    	ArrayList list = new ArrayList();
    	
    	Query query = this.sessionfactory.getCurrentSession().createQuery("select c from PromotionActivity a, PrePaid b, Order c join c.orderDetail e, TransactionRcd d where a.contentCondition = 1 and b.pid=d.productionCategory.pid and a.id=:activity and d=e");
    	query.setString("activity", promotionActID);
    	
    	List<Order> resultList2=(List<Order>)query.list();
    	Order[] raList = new Order[resultList2.size()];
		System.out.println("CreatorAlbum==>"+resultList2.size());
			int j=0;
			for (Order mc2:resultList2) {
				raList[j]=mc2;
				
				//System.out.println("CreatorAlbum==>"+raList[j].getGsiMoney().getIncome());
				j++;
			}
    	
    	return raList;
    }
    
    /**
     * 查詢行銷活動的儲值次數
     * @param songID 歌曲編號
     */
    public ArrayList queryJoinTimesForPro(String promotionActID){
    	ArrayList list = new ArrayList();
    	
    	Query query = this.sessionfactory.getCurrentSession().createQuery("select c.member from PromotionActivity a, PrePaid b, Order c join c.orderDetail e, TransactionRcd d where a.contentCondition = 1 and b.pid=d.productionCategory.pid and a.id=:activity and d=e");
    	query.setString("activity", promotionActID);
    	
    	List<Member> resultList2=(List<Member>)query.list();
    	Member[] raList = new Member[resultList2.size()];
    	Long[] raList2 = new Long[resultList2.size()];
    	String[] raList3 = new String[resultList2.size()];
		System.out.println("resultList2.size()==>"+resultList2.size());
			int j=0;
			for (Member mc2:resultList2) {
				ArrayList list2 = new ArrayList();
				raList[j]=mc2;
				list2.add(mc2);                                                  //參加的會員
				
				Query query2 = this.sessionfactory.getCurrentSession().createQuery("select count(d.productionCategory.pid) from PromotionActivity a, PrePaid b, Order c join c.orderDetail e, TransactionRcd d where a.contentCondition = 1 and b.pid=d.productionCategory.pid and a.id=:activity and d=e and c.member.id=:member");
				query2.setString("activity", promotionActID);
				query2.setLong("member", raList[j].getId());
				raList2[j]=(Long)query2.uniqueResult();
				System.out.println("會員==>"+raList[j].getId());
				System.out.println("儲值次數==>"+raList2[j]);
				list2.add(raList2[j]);                                            //儲值次數
				
				Query query3 = this.sessionfactory.getCurrentSession().createQuery("select sum(e.gsiMoney) from PromotionActivity a, PrePaid b, Order c join c.orderDetail e, TransactionRcd d where a.contentCondition = 1 and b.pid=d.productionCategory.pid and a.id=:activity and d=e and c.member.id=:member");
				query3.setString("activity", promotionActID);
				query3.setLong("member", raList[j].getId());
				raList3[j]=(String)query3.uniqueResult();
				System.out.println("儲值總金額==>"+raList3[j]);
				list2.add(raList3[j]);                                            //儲值總金額
				
				list.add(list2);
				
				j++;
			}
    	
    	return list;
    }
    
    /**
     * 查詢行銷活動的專輯
     * @param songID 歌曲編號
     */
    public ArrayList queryJoinAlbumsForPro(String promotionActID){
    ArrayList list = new ArrayList();
    	
    	Query query = this.sessionfactory.getCurrentSession().createQuery("select b from PromotionActivity a join a.albumSet b,TransactionRcd d where b=d.productionCategory.pid and a.id=:activity");
    	query.setString("activity", promotionActID);
    	
    	List<Album> resultList2=(List<Album>)query.list();
    	Album[] raList = new Album[resultList2.size()];
    	Long[] raList2 = new Long[resultList2.size()];
		System.out.println("CreatorAlbum==>"+resultList2.size());
			int j=0;
			for (Album mc2:resultList2) {
				ArrayList list2 = new ArrayList();
				raList[j]=mc2;
				list2.add(mc2);                                                  //參加的專輯
				
				Query query2 = this.sessionfactory.getCurrentSession().createQuery("select count(d.productionCategory.pid) from PromotionActivity a join a.albumSet b,TransactionRcd d where b=d.productionCategory.pid and a.id=:activity");
				query2.setString("activity", promotionActID);
				raList2[j]=(Long)query2.uniqueResult();
				System.out.println("AlbumAmount==>"+raList2[j]);
				list2.add(raList2[j]);                                            //被購買數
				list.add(list2);
				
				System.out.println("CreatorAlbum==>"+raList[j].getName());
				j++;
			}
    	
    	return list;
    }
    
    /**
     * 查詢行銷活動的歌曲
     * @param songID 歌曲編號
     */
    public ArrayList queryJoinSongsForPro(String promotionActID){
ArrayList list = new ArrayList();
    	
    	Query query = this.sessionfactory.getCurrentSession().createQuery("select b from PromotionActivity a join a.songSet b,TransactionRcd d where b=d.productionCategory.pid and a.id=:activity");
    	query.setString("activity", promotionActID);
    	
    	List<Song> resultList2=(List<Song>)query.list();
    	Song[] raList = new Song[resultList2.size()];
    	Long[] raList2 = new Long[resultList2.size()];
		System.out.println("CreatorAlbum==>"+resultList2.size());
			int j=0;
			for (Song mc2:resultList2) {
				ArrayList list2 = new ArrayList();
				raList[j]=mc2;
				list2.add(mc2);                                                  //參加的專輯
				
				Query query2 = this.sessionfactory.getCurrentSession().createQuery("select count(d.productionCategory.pid) from PromotionActivity a join a.songSet b,TransactionRcd d where b=d.productionCategory.pid and a.id=:activity");
				query2.setString("activity", promotionActID);
				raList2[j]=(Long)query2.uniqueResult();
				System.out.println("AlbumAmount==>"+raList2[j]);
				list2.add(raList2[j]);                                            //被購買數
				list.add(list2);
				
				System.out.println("CreatorAlbum==>"+raList[j].getName());
				j++;
			}
    	
    	return list;
    }
}
