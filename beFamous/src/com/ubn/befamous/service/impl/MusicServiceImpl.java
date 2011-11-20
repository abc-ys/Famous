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
import com.ubn.befamous.entity.Member;
import com.ubn.befamous.entity.MusicCategory;
import com.ubn.befamous.entity.News;
import com.ubn.befamous.entity.RecommendActivity;
import com.ubn.befamous.entity.Song;
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
     * 查詢專輯資料
     * @param albumID 專輯編號
     */
    public ArrayList queryAlbumData(long albumID){
    	ArrayList albumData = new ArrayList();
    	
    	return albumData;
    }
    
    /**
     * 新增專輯試聽
     * @param albumID 專輯編號
     */
    public Song[] addAlbumAudition(long albumID){
    	Song[] albumAudition = {};
    	
    	return albumAudition;
    }
    
    //瀏覽分類頁面
    
    /**
     * 音樂分類-查詢最新專輯       (要再加會員狀態不能是關閉的條件和被隱藏的條件)
     * @param musicCatrgoryID 音樂分類編號
     */
    public Album[] queryNewAlbumsForMusicCatrgory (long musicCatrgoryID){
    	String datetime = DateFormatUtils.format(new Date(), "yyyy-MM-dd");   //今天日期時間
    	String newest = DateFormatUtils.format(DateUtils.addMonths(new Date(), -3), "yyyy-MM-dd");    //最近三個月發表的專輯就算最新專輯
    	
    	Query query = this.sessionfactory.getCurrentSession().createQuery("from Album a where (a.createDate between :newest and :datetime) and (a.musicCategory.id = :musicCatrgoryID)  ORDER BY a.createDate DESC");
		query.setString("datetime", datetime);
		query.setString("newest", newest);
		query.setLong("musicCatrgoryID", musicCatrgoryID);
    	
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
     * 查詢最新專輯          (要再加會員狀態不能是關閉的條件)
     * @param datetime 現在日期時間
     */
    public Album[] queryNewAlbums (String datetime){
    	//datetime = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
    	String newest = DateFormatUtils.format(DateUtils.addMonths(new Date(), -3), "yyyy-MM-dd");    //最近三個月發表的專輯就算最新專輯
    	
    	Query query = this.sessionfactory.getCurrentSession().createQuery("from Album a where (a.createDate between :newest and :datetime) and (a.status = :status) and (a.hidden is empty) ORDER BY a.createDate DESC");
		query.setString("datetime", datetime);
		query.setString("newest", newest);
		query.setString("status", "1");       //1: 專輯條件為公開
    	
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
     * 查詢推薦專輯    (要再加會員狀態不能是關閉的條件和刪除時間為空)
     */
    public Album[] queryPromotionAlbums(){
    	String today=DateFormatUtils.format(new Date(), "yyyyMMdd");
		Query query = this.sessionfactory.getCurrentSession().createQuery("from RecommendActivity a, Album b where (:today  between a.startDate and a.endDate) and ");
		query.setString("today", today);
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
     * 查詢音樂
     * @param songID 歌曲編號
     */
    public ArrayList queryMusic (long songID){
    	Album album = new Album();
    	Song[] s = {};
    	
    	ArrayList list = new ArrayList();
    	list.add(album);
    	list.add(s);
    	return list;
    }
    
    /**
     * 查詢專輯
     * @param albumID 專輯編號
     */
    public Album queryAlbum (long albumID){
    	Album a = new Album();
    	return a;
    }
    
    /**
     * 更新專輯
     * @param album 專輯的bean
     */
    public void updateAlbum (Album album){
    	
    }
    
    /**
     * 查詢歌曲
     * @param songID 歌曲編號
     */
    public Song querySong (long songID){
    	Song s = new Song();
    	return s;
    }
    
    /**
     * 更新歌曲
     * @param song 歌曲的bean
     */
    public void updateSong (Song song){
    	
    }
    
    /**
     * 刪除歌曲
     * @param songID 歌曲編號
     */
    public void deleteSong (long songID){
    	
    }
    
    //瀏覽所有歌曲
    /**
     * 查詢所有專輯
     * @param creatorID 創作人編號
     */
    public Album[] queryOwnAlbums (long creatorID){
    	
    	Query query = this.sessionfactory.getCurrentSession().createQuery("from Album where creator.id = :creatorId and dropDate is null ORDER BY createDate DESC");
    	query.setLong("creatorId", creatorID);
    	
    	List<Album> albumList = (List<Album>)query.list();
    	Album[] albumList2 = new Album[albumList.size()];
		int i = 0;
		System.out.println("ssss==>");
		for (Album albumList3:albumList) {
			albumList2[i]=albumList3;
			System.out.println("a==>"+albumList2[i].getId());
			i++;
		}
    	
    	Album[] a = {};
    	return a;
    }
    
    //查詢關鍵字
    
    /**
     * 查詢關鍵字
     * @param keyword 關鍵字
     */
    public ArrayList queryKeyword (String keyword){
    	Session session = sessionfactory.getCurrentSession();
    	
    	//Keyword[] k = this.keywordDAO.findAll();
    	
    	/*
		Query query = session.createQuery("from Keyword ");
		
		List<Keyword> k = (List<Keyword>)query.list();
		Keyword[] k2 = new Keyword[k.size()];
		int i = 0;
		System.out.println("ssss==>");
		for (Keyword k3:k) {
			k2[i]=k3;
			System.out.println("ssss==>"+k2[i].getName());
			i++;
		}
		
		
		
		Query query2 = session.createQuery("from Album");
		
		List<Album> a = (List<Album>)query2.list();
		Album[] a2 = new Album[a.size()];
		int j = 0;
		System.out.println("ssss==>");
		for (Album a3:a) {
			a2[j]=a3;
			System.out.println("ssss==>"+a2[j].getName());
			j++;
		}
		
		
		Query query3 = session.createQuery("from Song");
		
		List<Song> s = (List<Song>)query3.list();
		Song[] s2 = new Song[s.size()];
		int z = 0;
		System.out.println("ssss==>");
		for (Song s3:s) {
			s2[z]=s3;
			System.out.println("ssss==>"+s2[z].getName());
			z++;
		}
		
		
		Query query4 = session.createQuery("from Creator");
		
		List<Creator> c = (List<Creator>)query4.list();
		Creator[] c2 = new Creator[c.size()];
		int w = 0;
		System.out.println("ssss==>");
		for (Creator c3:c) {
			c2[w]=c3;
			System.out.println("ssss==>"+c2[w].getUserName());
			w++;
		}
    	*/
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
    
    //管理專輯類別與排行榜
    //管理音樂類別
    
    /**
     * 查詢音樂類別
     */
    public MusicCategory[] queryMusicCategory (){
    	MusicCategory[] mc = {};
    	return mc;
    }
    
    /**
     * 刪除音樂類別
     * @param musicCategoryID 音樂分類編號
     * @param managerID 管理者編號
     */
    public void deleteMusicCategory (long musicCategoryID, long managerID){
    	
    }
    
    /**
     * 新增音樂類別
     * @param musicCategoryID 音樂分類編號
     * @param managerID 管理者編號
     */
    public void addMusicCategory  (long musicCategoryID, long managerID){
    	
    }
    
    /**
     * 更新音樂類別
     * @param musicCategoryID 音樂分類編號
     * @param managerID 管理者編號
     */
    public void updateMusicCategory  (long musicCategoryID, long managerID){
    	
    }
    
    /**
     * 查詢子音樂類別
     * @param musicCategoryID 音樂分類編號
     */
    public MusicCategory[] querySubMusicCatrgory (long musicCategoryID){
    	MusicCategory[] mc = {};
    	return mc;
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
     * 試聽
     * @param songID 歌曲編號
     */
    public Song listenSong (long songID){
    	Song s = new Song();
    	return s;
    }
}
