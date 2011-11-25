package com.ubn.befamous.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.ubn.befamous.entity.MusicCategory;
import com.ubn.befamous.entity.News;
import com.ubn.befamous.entity.Question;
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
     * 查詢專輯資料(就是專輯profile頁)     (再確認一下，為什麼要有RecommendAlbum[]和song[])
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
    	
    	
    	//把音樂類別的資料撈出來
    	Query query = this.sessionfactory.getCurrentSession().createQuery("from MusicCategory where id = :musicCategoryId");
    	query.setLong("musicCategoryId", Long.parseLong(musicCategory));
    	MusicCategory mc = (MusicCategory)query.uniqueResult();
    	
    	Song song = this.songDAO.find(songId);
    	song.setName(name);
    	song.setCreateDate(date);
    	song.setMusicCategory(mc);
    	song.setSeconds(status);
    	SongPrice sp = this.songPriceDAO.find(song.getSongPrice().getId());
    	sp.setDiscountPrice(discount);
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
     * 更新歌曲   
     * @param song 歌曲的bean
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void updateSong(long creatorId,long songID, String songName, String musicType, String MOPEND, String status, String price, String price2, String discount, String tag, String lyrics, String lyricist, String composer, String producer){
    	
    	String datetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");  //建立當天日期時間
    	
    	Song song = this.songDAO.find(songID);
    	song.setName(songName);
    	MusicCategory mc = this.musicCategoryDAO.find(song.getMusicCategory().getId());
    	mc.setName(musicType);
    	song.setMusicCategory(mc);
    	song.setModifyDate(datetime);
    	song.setModifier(String.valueOf(creatorId));
    	song.setSeconds(status);
    	SongPrice sp = this.songPriceDAO.find(song.getSongPrice().getId());
    	sp.setDiscountPrice(discount);
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
		/*
			int i=0;
			for (MusicCategory mc2:resultList) {
				mcSet[i]=mc2;
				System.out.println("ssss==>"+mcSet[i].getName());
				i++;
			}
    	*/
    	return mcSet;
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
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
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
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
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
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
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
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
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
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
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
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
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
		queryString.append("from Song a where (1=1)");   
		
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
}
