package com.ubn.befamous.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.Creator;
import com.ubn.befamous.entity.Hidden;
import com.ubn.befamous.entity.InelegantKeyword;
import com.ubn.befamous.entity.Keyword;
import com.ubn.befamous.entity.Member;
import com.ubn.befamous.entity.MusicCategory;
import com.ubn.befamous.entity.News;
import com.ubn.befamous.entity.Order;
import com.ubn.befamous.entity.OrderDetail;
import com.ubn.befamous.entity.PromotionActivity;
import com.ubn.befamous.entity.RecommendActivity;
import com.ubn.befamous.entity.Song;

public interface MusicService {

	//瀏覽專輯與排行榜 - 怡秀write-1115
	
	//瀏覽排行榜
	
	/**
     * 查詢專輯周榜
     * @param datetime 現在日期時間
     */
    public Album[] queryAlbumsWeekRanking();
    
    /**
     * 查詢專輯月榜
     * @param datetime 現在日期時間
     */
    public Album[] queryAlbumsMonthRanking();
    
    /**
     * 查詢歌曲周榜
     * @param datetime 現在日期時間
     */
    public Song[] querySongsWeekRanking();
    
    /**
     * 查詢歌曲月榜
     * @param datetime 現在日期時間
     */
    public Song[] querySongsMonthRanking();
    
    /**
     * 查詢創作人週榜
     * @param datetime 現在日期時間
     */
    public ArrayList queryCreatorWeekRanking();
    
    /**
     * 查詢創作人月榜
     * @param datetime 現在日期時間
     */
    public ArrayList queryCreatorMonthRanking();
    
    /**
     * 查詢專輯資料
     * @param albumID 專輯編號
     */
    public ArrayList queryAlbumData(long albumID);
    
    /**
     * 新增專輯試聽
     * @param albumID 專輯編號
     */
    public Song[] addAlbumAudition(long albumID);
    
    //瀏覽分類頁面
    
    /**
     * 音樂分類-查詢新專輯
     * @param musicCatrgoryID 音樂分類編號
     */
    public Album[] queryNewAlbumsForMusicCatrgory (long musicCatrgoryID);
    
    /**
     * 音樂分類-查詢熱門專輯
     * @param musicCatrgoryID 音樂分類編號
     */
    public Album[] queryHotAlbumsForMusicCatrgory (long musicCatrgoryID);
	
    //瀏覽最新專輯
    
    /**
     * 查詢最新專輯
     * @param datetime 現在日期時間
     */
    public Album[] queryNewAlbums (String datetime);
    
    //瀏覽大力推清單
    
    /**
     * 查詢推薦專輯
     */
    public Album[] queryPromotionAlbums ();
    
    //上傳音樂-上傳歌曲
    
    /**
     * 儲存專輯
     * @param album 專輯的bean
     */
    public long saveAlbum (long creatorId,String albumType, String albumName, String albumBrand, long musicCategory, String albumTag, String albumIntroduction, String albumStatus, String albumCover, String defaultCover);
    
    /**
     * 儲存歌曲
     * @param albumID 專輯編號
     * @param creatorId 創作人編號
     * @param fileName 歌曲的檔案名稱
     */
    public long saveSong (long albumID, long creatorId,String fileName);
    
    /**
     * 儲存歌曲資訊
     * @param song 歌曲的bean
     */
    public void saveSongDetail (long songId,long creatorId, long albumId, String name, String date, String musicCategory, String status, String price, String price2, String discount, String tag);

    /**
     * 查詢音樂
     * @param albumID 歌曲編號
     */
    public Album queryMusic (long albumID);
    
    /**
     * 查詢專輯
     * @param albumID 專輯編號
     */
    public Album queryAlbum (long albumID);
    
    /**
     * 更新專輯的狀態
     * @param state 專輯狀態
     * @param albumID 專輯ID
     * @param creatorId 創作人ID
     */
    public void changeAlbumStatus(String state,long albumID,long creatorId);
    
    /**
     * 更新專輯
     * @param albumID 專輯編號
     */
    public void updateAlbum (long creatorId,long albumID, String albumType,String name,String date,String brand,String musicCategory,String tag,String cover,String cover2,String introduction,String status);
    
    /**
     * 查詢歌曲
     * @param songID 歌曲編號
     */
    public Song querySong (long songID);
    
    /**
     * 更新歌曲
     * @param song 歌曲的bean
     */
    public void updateSong (long creatorId,long songID, String songName, String musicType, String MOPEND, String status, String price, String price2, String discount, String tag, String lyrics, String lyricist, String composer, String producer);
    
    /**
     * 刪除歌曲
     * @param songID 歌曲編號
     */
    public void deleteSong (long songID);
    
    //瀏覽所有歌曲
    /**
     * 查詢所有專輯
     * @param creatorID 創作人編號
     */
    public Album[] queryOwnAlbums (long creatorID);
    
    //查詢關鍵字
    
    /**
     * 查詢關鍵字
     * @param keyword 關鍵字
     */
    public ArrayList queryKeyword (String keyword);
    
    /**
     * 查詢專輯的關鍵字
     * @param keyword 關鍵字
     */
    public Album[] queryKeywordForAlbums (String keyword);
    
    /**
     * 查詢歌曲的關鍵字
     * @param keyword 關鍵字
     */
    public Song[] queryKeywordForSongs (String keyword);
    
    /**
     * 查詢創作人的關鍵字
     * @param keyword 關鍵字
     */
    public Creator[] queryKeywordForCreators (String keyword);
    
    /**
     * 查詢消息的關鍵字
     * @param keyword 關鍵字
     */
    public News[] queryKeywordForNews (String keyword);
    
    //管理關鍵字
    
    /**
     * 查詢不雅字關鍵字    
     */
    public InelegantKeyword[] queryInelegantKeywords();
    
    /**
     * 新增不雅字關鍵字  
     */
    public void addInelegantKeywords(long managerID, String keyword);
    
    /**
     * 刪除不雅字關鍵字  
     * @param managerID 管理者編號
     * @param inelegantKeywordID 不雅關鍵字編號
     */
    public void deleteInelegantKeywords(long inelegantKeywordID);
    
    //管理專輯類別與排行榜
    //管理音樂類別
    
    /**
     * 查詢音樂類別
     */
    public MusicCategory[] queryMusicCategory ();
    
    /**
     * 查詢音樂類別
     */
    public MusicCategory musicCategory (String mcID);
    
    /**
     * 刪除音樂類別
     * @param musicCategoryID 音樂分類編號
     * @param managerID 管理者編號
     */
    public void deleteMusicCategory (long musicCategoryID);
    
    /**
     * 新增音樂類別
     * @param managerID 管理者編號
     */
    public void addMusicCategory (long managerID,String categoryName);
    
    /**
     * 新增音樂子類別
     * @param managerID 管理者編號
     */
    public void addSubMusicCategory(long managerID, String categoryName, long fatherID);
    
    /**
     * 更新音樂類別
     * @param musicCategoryID 音樂分類編號
     * @param name 音樂分類名稱
     * @param managerID 管理者編號
     */
    public void updateMusicCategory (long musicCategoryID, String name, long managerID);
    
    /**
     * 查詢子音樂類別
     * @param musicCategoryID 音樂分類編號
     */
    public MusicCategory[] querySubMusicCatrgory (long musicCategoryID);
    
    //管理排行榜
    /**
     * 查詢音樂類別清單
     */
    public MusicCategory[] musicCategoryList();
    
    /**
     * 管理者查詢專輯週榜
     * @param albumName 專輯名稱
     * @param creatorName 創作人姓名
     * @param musicCategoryID 音樂分類編號
     * @param startDate 專輯上架起始日期
     * @param endDate 專輯上架結束日期
     */
    public ArrayList queryAlbumWeekRankingForAdmin (String albumName, String creatorName, String musicCategoryID, String startDate, String endDate);
    
    /**
     * 管理者查詢專輯月榜
     * @param albumName 專輯名稱
     * @param creatorName 創作人姓名
     * @param musicCategoryID 音樂分類編號
     * @param startDate 專輯上架起始日期
     * @param endDate 專輯上架結束日期
     */
    public ArrayList queryAlbumMonthRankingForAdmin (String albumName, String creatorName, String musicCategoryID, String startDate, String endDate);
    
    /**
     * 管理者查詢歌曲週榜
     * @param songName 歌曲名稱
     * @param albumName 專輯名稱
     * @param creatorName 創作人姓名
     * @param musicCategoryID 音樂分類編號
     * @param startDate 專輯上架起始日期
     * @param endDate 專輯上架結束日期
     */
    public ArrayList querySongWeekRankingForAdmin (String songName, String albumName, String creatorName, String musicCategoryID, String startDate, String endDate);
    
    /**
     * 管理者查詢歌曲月榜
     * @param songName 歌曲名稱
     * @param albumName 專輯名稱
     * @param creatorName 創作人姓名
     * @param musicCategoryID 音樂分類編號
     * @param startDate 專輯上架起始日期
     * @param endDate 專輯上架結束日期
     */
    public ArrayList querySongMonthRankingForAdmin (String songName, String albumName, String creatorName, String musicCategoryID, String startDate, String endDate);
    
    /**
     * 管理者查詢創作人週榜
     * @param creatorName 創作人姓名
     */
    public ArrayList queryCreatorWeekRankingForAdmin (String creatorName);
    
    /**
     * 管理者查詢創作人月榜
     * @param creatorName 創作人姓名
     */
    public ArrayList queryCreatorMonthRankingForAdmin (String creatorName);
    
    /**
     * 更新專輯週榜的CP值
     * @param CP CP值
     * @param modifyDate 音樂分類編號
     * @param startDate 專輯周榜起始日期
     * @param endDate 專輯周架結束日期
     */
    public void updateAlbumWeekCP (String adminID,String albumID, int CP);
    
    /**
     * 更新專輯月榜的CP值
     * @param CP CP值
     * @param modifyDate 音樂分類編號
     * @param startDate 專輯月榜起始日期
     * @param endDate 專輯月榜結束日期
     */
    public void updateAlbumMonthCP (String adminID,String albumID, int CP);
    
    /**
     * 更新歌曲週榜的CP值
     * @param CP CP值
     * @param modifyDate 音樂分類編號
     * @param startDate 專輯周榜起始日期
     * @param endDate 專輯周架結束日期
     */
    public void updateSongWeekCP (String adminID,String songID, int CP);
    
    /**
     * 更新歌曲月榜的CP值
     * @param CP CP值
     * @param modifyDate 音樂分類編號
     * @param startDate 專輯月榜起始日期
     * @param endDate 專輯月榜結束日期
     */
    public void updateSongMonthCP (String adminID,String songID, int CP);
    
    /**
     * 更新創作人週榜的CP值
     * @param CP CP值
     * @param modifyDate 音樂分類編號
     * @param startDate 專輯周榜起始日期
     * @param endDate 專輯周架結束日期
     */
    public void updateCreatorWeekCP (String adminID,String creatorID, int CP);
    
    /**
     * 更新創作人月榜的CP值
     * @param CP CP值
     * @param modifyDate 音樂分類編號
     * @param startDate 專輯月榜起始日期
     * @param endDate 專輯月榜結束日期
     */
    public void updateCreatorMonthCP (String adminID,String creatorID, int CP);
    
    /**
     * 試聽
     * @param songID 歌曲編號
     */
    public Song listenSong (long songID);
    
    
    //瀏覽專輯Profile頁
    
    /**
     * 查詢創作人專輯
     * @param albumID 專輯編號
     * @param creatorID 創作人編號
     */
    public ArrayList queryCreatorAlbums(long albumID);
    
    /**
     * 查詢專輯的歌曲      
     * @param albumID 專輯編號
     */
    public Song[] querySongSet(long albumID);
    
    /**
     * 查詢專輯簡介
     * @param albumID 專輯編號
     */
    public Album queryAlbumIntroduction(long albumID);
    
    /**
     * 新增專輯評論(迴響)    <=先擱著，因為有用到facebook
     * @param albumID 專輯編號
     */
    public void addAlbumComment(long albumID,String albumComment);
    
    /**
     * 查詢創作人下的其他專輯      
     * @param albumID 專輯編號
     * @param albumID 專輯編號
     */
    public Album[] queryOtherAlbum(long albumID,long creatorID);
    
    /**
     * 加入歌曲試聽
     * @param songID 歌曲編號
     */
    public Song addSongAudition(long songID);
    
    
    //管理音樂上傳
    
    /**
     * 查詢音樂類別
     */
    public MusicCategory[] queryAllMusicCategory();
    
    /**
     * 查詢專輯
     * @param albumType 專輯類型
     * @param startDate 專輯公開開始日期
     * @param endDate 專輯公開結束日期
     * @param creatorName 創作人姓名
     * @param musicCatrgory 音樂分類
     */
    public Album[] queryForAlbums(String albumType,String startDate,String endDate,String creatorName,String musicCatrgory);
    
    /**
     * 隱藏專輯
     * @param albumID 專輯編號
     * @param adminID 管理者編號
     */
    public Hidden hideAlbum(long albumID,long adminID);
    
    /**
     * 取消隱藏專輯,更新專輯隱藏狀態
     * @param albumID 專輯編號
     * @param adminID 管理者編號
     */
    public void displayAlbum(long albumID,long adminID);
    
    /**
     * 儲存專輯的隱藏原因
     * @param albumHiddenID 專輯隱藏編號
     * @param albumHiddenReason 專輯隱藏原因
     */
    public void saveAlbumHiddenReason(long albumHiddenID,String albumHiddenReason,long adminID);
    
    /**
     * 查詢歌曲
     * @param albumName 專輯名稱
     * @param creatorName 創作人名稱
     * @param musicCatrgory 音樂類別
     */
    public Song[] queryForSongs(String albumName,String creatorName,String musicCatrgory);
    
    /**
     * 隱藏歌曲
     * @param songID 歌曲編號
     * @param adminID 管理者編號
     */
    public Hidden hideSong(long songID,long adminID);
    
    /**
     * 取消隱藏歌曲,更新歌曲隱藏狀態
     * @param songID 歌曲編號
     * @param adminID 管理者編號
     */
    public void displaySong(long songID,long adminID);
    
    /**
     * 儲存歌曲的隱藏原因
     * @param songHiddenID 歌曲隱藏編號
     * @param songHiddenReason 歌曲隱藏原因
     */
    public void saveSongHiddenReason(long songHiddenID,String songHiddenReason,long adminID);
    
    /**
     * 查詢歌詞
     * @param songID 歌曲編號
     */
    public Song querySongLyrics(long songID);
    
    
    //大力推活動
    /**
     * 儲存推薦活動
     * @param songID 歌曲編號
     */
    public void saveRecommendActivityForAlbum(String adminID,String recommendActName,String startDate,String endDate,String[] recommendActContent,String recommendActState);
   
    /**
     * 查詢推薦活動清單
     * @param songID 歌曲編號
     */
    public RecommendActivity[] queryRecommendActivities(String year,String month);
    
    /**
     * 查詢推薦活動
     * @param songID 歌曲編號
     */
    public RecommendActivity queryRecommendActivity(String recommendActID);
    
    /**
     * 更新推薦活動
     * @param songID 歌曲編號
     */
    public void updateRecommendActivity(String adminID,String recommendActID,String recommendActName,String startDate,String endDate,String[] recommendActContent,String recommendActState);

    /**
     * 查詢專輯，然後將專輯加至推薦活動中
     * @param songID 歌曲編號
     */
    public Map queryJoinAlbumsForRec(String recommendActID);
    
    /**
     * 查詢歌曲，然後將歌曲加至推薦活動中
     * @param songID 歌曲編號
     */
    public Map queryJoinSongsForRec(String recommendActID);
    
    /**
     * 查詢推薦活動裡專輯被購買數
     * @param songID 歌曲編號
     */
    public ArrayList  queryJoinAlbumForRec(String recommendActID);
    
    /**
     * 查詢有購買推薦活動裡專輯的會員
     * @param songID 歌曲編號
     */
    public Member[] queryJoinMembersForRec(String recommendActID);

    
    //行銷活動
    /**
     * 查詢推薦的專輯
     * @param songID 歌曲編號
     */
    public void savePromotionActivity(String adminID,String promotionActName,String startDate,String endDate,String prepaidMoney,String prepaidCount,String promotionActContentType,String[] promotionActContent,String promotionActCondition,String presentType,int presentBonus,String presentDeadline,String promotionActState);
    
    /**
     * 查詢推薦的專輯
     * @param songID 歌曲編號
     */
    public PromotionActivity[] queryPromotionActivities(String year,String month);
     
    /**
     * 查詢推薦的專輯
     * @param songID 歌曲編號
     */
    public PromotionActivity queryPromotionActivity(String promotionActID);
    
    /**
     * 查詢推薦的專輯
     * @param songID 歌曲編號
     */
    public void updatePromotionActivity(String adminID,String promotionActID,String promotionActName,String prepaidMoney,String prepaidCount,String startDate,String endDate,String promotionActContentType,String[] promotionActContent,String promotionActCondition,String presentType,int presentBonus,String presentDeadline,String promotionActState);
    
    /**
     * 查詢行銷活動的專輯
     * @param songID 歌曲編號
     */
    public Member[ ] queryJoinMembersForPro(String promotionActID);
    
    /**
     * 查詢推薦的專輯
     * @param songID 歌曲編號
     */
    public Order[] queryJoinGSiMoneyForPro(String promotionActID);
    
    /**
     * 查詢行銷活動的專輯
     * @param songID 歌曲編號
     */
    public ArrayList queryJoinTimesForPro(String promotionActID);
    
    /**
     * 查詢推薦的專輯
     * @param songID 歌曲編號
     */
    public ArrayList queryJoinAlbumsForPro(String promotionActID);
    
    /**
     * 查詢推薦的專輯
     * @param songID 歌曲編號
     */
    public ArrayList queryJoinSongsForPro(String promotionActID);
}
