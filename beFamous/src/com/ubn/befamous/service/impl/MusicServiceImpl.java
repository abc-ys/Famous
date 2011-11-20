package com.ubn.befamous.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.Creator;
import com.ubn.befamous.entity.Keyword;
import com.ubn.befamous.entity.Member;
import com.ubn.befamous.entity.MemberStatus;
import com.ubn.befamous.entity.MusicCategory;
import com.ubn.befamous.entity.News;
import com.ubn.befamous.entity.RecommendActivity;
import com.ubn.befamous.entity.Song;
import com.ubn.befamous.entity.SongPrice;
import com.ubn.befamous.service.MusicService;

@Service("musicService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MusicServiceImpl implements MusicService{
	
	@Autowired
	private SessionFactory sessionfactory;
	
	@Autowired
	@Qualifier("albumDAO")
	private IBaseDao<Album, Long> albumDAO;
	
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
	
	
	//瀏覽專輯與排行榜 - 怡秀write-1115
	
	//瀏覽排行榜
	
	/**
     * 查詢專輯周榜       (還未完成  預計1114)
     * @param datetime 現在日期時間
     */
    public Album[] queryAlbumsWeekRanking(String datetime){
    	Album[] albumList = {};
    	
    	return albumList;
    } 
	
	/**
     * 查詢專輯月榜         (還未完成  預計1114)
     * @param datetime 現在日期時間
     */
    public Album[] queryAlbumsMonthRanking(String datetime){
    	Album[] albumList = {};
    	
    	return albumList;
    }
    
    /**
     * 查詢歌曲周榜         (還未完成  預計1114)
     * @param datetime 現在日期時間
     */
    public Song[] querySongsWeekRanking(String datetime){
    	Song[] songList = {};
    	
    	return songList;
    }
    
    /**
     * 查詢歌曲月榜          (還未完成  預計1114)
     * @param datetime 現在日期時間
     */
    public Song[] querySongsMonthRanking(String datetime){
    	Song[] songList = {};
    	
    	return songList;
    }
    
    /**
     * 查詢創作人週榜      (還未完成  預計1114)
     * @param datetime 現在日期時間
     */
    public Creator[] queryCreatorWeekRanking(String datetime){
    	Creator[] creatorList = {};
    	
    	return creatorList;
    }
    
    /**
     * 查詢創作人月榜      (還未完成  預計1114)
     * @param datetime 現在日期時間
     */
    public Creator[] queryCreatorMonthRanking(String datetime){
    	Creator[] creatorList = {};
    	
    	return creatorList;
    }
    
    /**
     * 查詢專輯資料     (再確認一下，為什麼要有RecommendAlbum[]和song[])
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
    	String datetime = DateFormatUtils.format(new Date(), "yyyy-MM-dd");   //今天日期時間
    	String newest = DateFormatUtils.format(DateUtils.addMonths(new Date(), -3), "yyyy-MM-dd");    //最近三個月發表的專輯就算最新專輯
    	
    	Query query = this.sessionfactory.getCurrentSession().createQuery("from Album a where (a.createDate between :newest and :datetime) and (a.musicCategory.id = :musicCatrgoryID) and (a.hidden is empty) and (a.dropDate is null) and (a.creator.memberStatus.statusName = :memberStatus) ORDER BY a.createDate DESC");
		query.setString("datetime", datetime);
		query.setString("newest", newest);
		query.setLong("musicCatrgoryID", musicCatrgoryID);
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
    	String newest = DateFormatUtils.format(DateUtils.addMonths(new Date(), -3), "yyyy-MM-dd");    //最近三個月發表的專輯就算最新專輯
    	
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
    	String today=DateFormatUtils.format(new Date(), "yyyyMMdd");
    	Query query = this.sessionfactory.getCurrentSession().createQuery("from RecommendActivity a where (:today  between a.startDate and a.endDate) and (a.albumSet.hidden is empty) and (a.albumSet.dropDate is null) and (a.albumSet.creator.memberStatus.statusName = :memberStatus)");
		//Query query = this.sessionfactory.getCurrentSession().createQuery("from RecommendActivity a join a.albumSet b where (:today  between a.startDate and a.endDate) and (a.status = :status) and (b.hidden is empty) and (b.dropDate is null) and (b.creator.memberStatus.statusName = :memberStatus)");
		query.setString("today", today);
		query.setString("memberStatus", "1");       //1: 會員狀態為正常 
		query.setString("status", "1");       //1: 專輯條件為公開
		
		List<RecommendActivity> resultList=query.list();
		if(resultList==null||resultList.isEmpty()){
			return new Album[0];
		}
		RecommendActivity s=resultList.get(0);
//		for (RecommendActivity s:resultList) {
			System.out.println("size"+s.getAlbumSet().size());
			Album[] albumset = new Album[s.getAlbumSet().size()];
			int i=0;
			for (Album as:s.getAlbumSet()) {
				albumset[i]=as;
				//System.out.println("ssss==>"+albumset[i].getCreator().getUserName());
				i++;
			}
    	return albumset;
    }
	
    //上傳音樂-上傳歌曲
    
    /**
     * 儲存專輯
     */
    public long saveAlbum (long creatorId,String albumType, String albumName, String albumBrand, long musicCategory, String albumTag, String albumIntroduction, String albumStatus, String albumCover){
    	//creatorId=1;
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddhhmmss");  //建立當天日期時間
    	
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
    	album.setCover(albumCover);
    	album.setCreateDate(datetime);
    	album.setCreator((Creator) member);
    	
    	this.albumDAO.save(album);
    	
    	//儲存專輯後再重新查詢專輯的ID
    	//存好音樂資料後，再拿創作人ID以及現在時間去查剛剛存的音樂ID
    	Query query3 = this.sessionfactory.getCurrentSession().createQuery("from Album where creator.id = :creatorId and createDate = :datetime");
    	query3.setLong("creatorId", creatorId);
    	query3.setString("datetime", datetime);
    	Album aLbum = (Album)query3.uniqueResult();
    	long albumID = aLbum.getId();
       
    	
    	return albumID;
    }
    
    /**
     * 儲存歌曲
     * @param albumID 專輯編號
     * @param album 專輯的bean
     */
    public long saveSong (String albumID, File songFile){
    	long songID = 111;
    	return songID;
    }
    
    /**
     * 儲存歌曲資訊
     * @param song 歌曲的bean
     */
    public void saveSongDetail (Song song){
    }
    
    /**
     * 查詢音樂  (音樂管理-編輯專輯的第二個頁面)
     * @param albumID 歌曲編號
     */
    public Album queryMusic (long albumID){
    	
    	Album album = this.albumDAO.find(albumID);
    	/*
    	System.out.println("albumName=="+album.getName());
    	Song[] c2 = new Song[album.getSongSet().size()];
		int w = 0;
		//int j = 0;
		for (Song c3:album.getSongSet()) {
			c2[w]=c3;
			System.out.println("aaaaa");
			System.out.println("ID==>"+c2[w].getId());
			System.out.println("s==>"+c2[w].getName());
			w++;
		}*/
    	
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
     * 更新專輯    (更新失敗)
     * @param album 專輯的bean
     */
    public void updateAlbum (long albumID, String albumType,String name,String date,String brand,String musicCategory,String tag,String cover,String cover2,String introduction,String status){
        	
    	Album album = this.albumDAO.find(albumID);
    	album.setType(albumType);
    	album.setName(name);
    	album.setModifyDate(date);
    	album.setBrand(brand);    	
    	MusicCategory mc = this.musicCategoryDAO.find(album.getMusicCategory().getId());
    	mc.setName(musicCategory);
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
     * 更新歌曲       (更新失敗)
     * @param song 歌曲的bean
     */
    public void updateSong(long songID, String songName, String musicType, String MOPEND, String status, String price, String price2, String discount, String tag, String lyrics, String lyricist, String composer, String producer){
    	
    	Song song = this.songDAO.find(songID);
    	song.setName(songName);
    	MusicCategory mc = this.musicCategoryDAO.find(song.getMusicCategory().getId());
    	mc.setName(musicType);
    	song.setMusicCategory(mc);
    	song.setModifyDate(MOPEND);
    	song.setSeconds(status);
    	SongPrice sp = this.songPriceDAO.find(song.getSongPrice().getId());
    	if(price2.equals("")){
    		sp.setDiscountPrice(discount);
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
     * 刪除歌曲       (刪除失敗)
     * @param songID 歌曲編號
     */
    public void deleteSong(long songID){
    	
    	this.songDAO.delete(songID);
    	
    }
    
    //瀏覽所有歌曲
    /**
     * 查詢所有專輯 (音樂管理-編輯專輯的第一個頁面)
     * @param creatorID 創作人編號
     */
    public Album[] queryOwnAlbums (long creatorID){
    	
    	Query query = this.sessionfactory.getCurrentSession().createQuery("from Album a where (a.creator.id = :creatorId) and (a.dropDate is null) and (a.status = :status) and (a.hidden is empty) and (a.status = :status) ORDER BY createDate DESC");
    	query.setLong("creatorId", creatorID);
		query.setString("status", "1");       //1: 專輯條件為公開
    	
    	List<Album> albumList = (List<Album>)query.list();
    	Album[] albumList2 = new Album[albumList.size()];
		int i = 0;
		System.out.println("ssss==>");
		for (Album albumList3:albumList) {
			albumList2[i]=albumList3;
			System.out.println("a==>"+albumList2[i].getId());
			i++;
		}
    	
    	return albumList2;
    }
    
    //查詢關鍵字
    
    /**
     * 查詢關鍵字
     * @param keyword 關鍵字
     */
    public ArrayList queryKeyword (String keyword){
    	
    	ArrayList list = new ArrayList();
    	//list.add(k);
//    	list.add(a2);
//    	list.add(s2);
//    	list.add(c2);
//    	list.add(k2);
    	return list;
    }
    
    /**
     * 查詢專輯的關鍵字
     * @param keyword 關鍵字
     */
    public Album[] queryKeywordForAlbums (String keyword){
    	Album[] a = {};
    	return a;
    }
    
    /**
     * 查詢歌曲的關鍵字
     * @param keyword 關鍵字
     */
    public Song[] queryKeywordForSongs (String keyword){
    	Song[] s = {};
    	return s;
    }
    
    /**
     * 查詢創作人的關鍵字
     * @param keyword 關鍵字
     */
    public Creator[] queryKeywordForCreators (String keyword){
    	Creator[] c = {};
    	return c;
    }
    
    /**
     * 查詢消息的關鍵字
     * @param keyword 關鍵字
     */
    public News[] queryKeywordForNews (String keyword){
    	News[] n = {};
    	return n;
    }
    
    //管理關鍵字
    
    /**
     * 查詢不雅字關鍵字    (??????)
     */
    public Keyword[] queryInelegantKeywords(){

    	Keyword[] keyword = this.keywordDAO.findAll();
    	Keyword[] k = new Keyword[keyword.length];
		int i = 0;
		for (Keyword k2:keyword) {
			k[i]=k2;
			System.out.println("a==>"+k[i].getName());
			i++;
		}
    	
    	return k;
    }
    
    /**
     * 新增不雅字關鍵字    (??????)
     * @param managerID 管理者編號
     * @param keyword 關鍵字
     */
    public void addInelegantKeywords(long managerID, String keyword){
    	
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddhhmmss");  //當天日期時間

    	Keyword kw = new Keyword();
    	kw.setCreateDate(datetime);
    	kw.setCreateUser(String.valueOf(managerID));
    	kw.setName(keyword);
    	
    	this.keywordDAO.save(kw);
    }
    
    /**
     * 刪除不雅字關鍵字    (??????)   (刪除失敗)
     * @param inelegantKeywordID 不雅關鍵字編號
     */
    public void deleteInelegantKeywords(long inelegantKeywordID){
    	
    	this.keywordDAO.delete(inelegantKeywordID);
    }
    
    //管理專輯類別與排行榜
    //管理音樂類別
    
    /**
     * 查詢音樂類別
     */
    public MusicCategory[] queryMusicCategory (){

    	MusicCategory[] musicCategory = this.musicCategoryDAO.findAll();
    	
    	MusicCategory[] mc = new MusicCategory[musicCategory.length];
		int i = 0;
		System.out.println("ssss==>");
		for (MusicCategory mc2:musicCategory) {
			mc[i]=mc2;
			System.out.println("a==>"+mc[i].getId());
			i++;
		}
    	
    	return musicCategory;
    }
    
    /**
     * 刪除音樂類別        (刪除再度失敗)
     * @param musicCategoryID 音樂分類編號
     * @param managerID 管理者編號
     */
    public void deleteMusicCategory (long musicCategoryID){
    	
    	this.musicCategoryDAO.delete(musicCategoryID);
    	
    }
    
    /**
     * 新增音樂類別
     * @param managerID 管理者編號
     */
    public void addMusicCategory(long managerID){
    	
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddhhmmss");  //當天日期時間
    	
    	MusicCategory mc = new MusicCategory();
    	mc.setName("抒情樂");
    	mc.setCreateDate(datetime);
    	mc.setCreateUser(String.valueOf(managerID));
    	
    	this.musicCategoryDAO.save(mc);
    }
    
    /**
     * 更新音樂類別           (更新再度失敗)
     * @param musicCategoryID 音樂分類編號
     * @param name 音樂分類名稱
     * @param managerID 管理者編號
     */
    public void updateMusicCategory(long musicCategoryID, String name, long managerID){
    	
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddhhmmss");  //當天日期時間
    	
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
				System.out.println("ssss==>"+mcSet[i].getName());
				i++;
			}
    	
    	return mcSet;
    }
    
    //管理排行榜
    /**
     * 管理者查詢專輯週榜
     * @param albumName 專輯名稱
     * @param creatorName 創作人姓名
     * @param musicCategoryID 音樂分類編號
     * @param startDate 專輯上架起始日期
     * @param endDate 專輯上架結束日期
     */
    public Album[] queryAlbumWeekRankingForAdmin (String albumName, String creatorName, long musicCategoryID, String startDate, String endDate){
    	Album[] a = {};
    	return a;
    }
    
    /**
     * 管理者查詢專輯月榜
     * @param albumName 專輯名稱
     * @param creatorName 創作人姓名
     * @param musicCategoryID 音樂分類編號
     * @param startDate 專輯上架起始日期
     * @param endDate 專輯上架結束日期
     */
    public Album[] queryAlbumMonthRankingForAdmin (String albumName, String creatorName, long musicCategoryID, String startDate, String endDate){
    	Album[] a = {};
    	return a;
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
    public Song[] querySongWeekRankingForAdmin (String songName, String albumName, String creatorName, long musicCategoryID, String startDate, String endDate){
    	Song[] s = {};
    	return s;
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
    public Song[] querySongMonthRankingForAdmin (String songName, String albumName, String creatorName, long musicCategoryID, String startDate, String endDate){
    	Song[] s = {};
    	return s;
    }
    
    /**
     * 管理者查詢創作人週榜
     * @param creatorName 創作人姓名
     */
    public Creator[] queryCreatorWeekRankingForAdmin (String creatorName){
    	Creator[] c = {};
    	return c;
    }
    
    /**
     * 管理者查詢創作人月榜
     * @param creatorName 創作人姓名
     */
    public Creator[] queryCreatorMonthRankingForAdmin (String creatorName){
    	Creator[] c = {};
    	return c;
    }
    
    /**
     * 更新專輯週榜的CP值
     * @param CP CP值
     * @param modifyDate 音樂分類編號
     * @param startDate 專輯周榜起始日期
     * @param endDate 專輯周架結束日期
     */
    public Album[] updateAlbumWeekCP (String startDate, String endDate, String modifyDate, int CP){
    	Album[] a = {};
    	return a;
    }
    
    /**
     * 更新專輯月榜的CP值
     * @param CP CP值
     * @param modifyDate 音樂分類編號
     * @param startDate 專輯周榜起始日期
     * @param endDate 專輯周架結束日期
     */
    public Album[] updateAlbumMonthCP (String startDate, String endDate, String modifyDate, int CP){
    	Album[] a = {};
    	return a;
    }
    
    /**
     * 更新歌曲週榜的CP值
     * @param CP CP值
     * @param modifyDate 音樂分類編號
     * @param startDate 專輯周榜起始日期
     * @param endDate 專輯周架結束日期
     */
    public Song[] updateSongWeekCP (String startDate, String endDate, String modifyDate, int CP){
    	Song[] s = {};
    	return s;
    }
    
    /**
     * 更新歌曲月榜的CP值
     * @param CP CP值
     * @param modifyDate 音樂分類編號
     * @param startDate 專輯月榜起始日期
     * @param endDate 專輯月榜結束日期
     */
    public Song[] updateSongMonthCP (String startDate, String endDate, String modifyDate, int CP){
    	Song[] s = {};
    	return s;
    }
    
    /**
     * 更新創作人週榜的CP值
     * @param CP CP值
     * @param modifyDate 音樂分類編號
     * @param startDate 專輯周榜起始日期
     * @param endDate 專輯周架結束日期
     */
    public Creator[] updateCreatorWeekCP (String startDate, String endDate, String modifyDate, int CP){
    	Creator[] c = {};
    	return c;
    }
    
    /**
     * 更新創作人月榜的CP值
     * @param CP CP值
     * @param modifyDate 音樂分類編號
     * @param startDate 專輯月榜起始日期
     * @param endDate 專輯月榜結束日期
     */
    public Creator[] updateCreatorMonthCP (String startDate, String endDate, String modifyDate, int CP){
    	Creator[] c = {};
    	return c;
    }
    
    /**
     * 試聽      (怪怪的，好像少了檔案路徑)
     * @param songID 歌曲編號
     */
    public Song listenSong (long songID){
    	Song s = new Song();
    	return s;
    }
    
    
}
