package com.ubn.befamous.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.hibernate.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ubn.befamous.entity.Ad;
import com.ubn.befamous.entity.Admin;
import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.LikeCreator;
import com.ubn.befamous.entity.Member;
import com.ubn.befamous.entity.News;
import com.ubn.befamous.entity.Question;

public interface PersonService {

	/**
	 * 
	 * @param userID
	 * @return |- 1. Member(會員Bean)
               |- 2. RecentLikeAlbum(最近喊讚的專輯清單) - Array
               |- 3. RecentLikeSong(最近喊讚的歌曲清單)  - Array
               |- 4. RecentListenSong(最近試聽的專輯清單) – Array
	 */
	public ArrayList queryMemberData(long userID);
	
	public Member queryBriefIntroduction(long userID);
	
	public void addFriend(long addMemberID, long userID,String message);
	
	/**
	 * 
	 * @param userID
	 * @return  ArrayList
                |- 1. Friends(好友清單) - Array
                |- 2. LikeCreators(追蹤中的創作人清單) – Array
	 */
	public ArrayList queryFriendsCreators(long userID);
	
	/**
	 * 
	 * @param userID
	 * @return |- 1. Member(會員Bean)
               |- 2. RecomCreator(推薦創作人清單) - Array
               |- 3. Album(專輯Bean)
               |- 4. Album(所有專輯清單) - Array
               |- 5. PopularSong(最受歡迎歌曲清單) – Array
	 */
	public ArrayList queryCreatorData(long userID);
	
	//查詢所有專輯
	public ArrayList queryAllCreatorAlbum(long userID);
	
	//查詢最近動態
	public ArrayList queryRecentActivity (long userID);
	
	//好友及粉絲團
	public ArrayList queryFriendFans(long userID);
	
	public void addFan(long addMemberID,long userID);
	
	public News[] queryRecentNews(long userID);
	
	/**
	 * 
	 * @param userID
	 * @return ArrayList
              |- 1. noDownloadAmount(尚未下載的歌曲數量) – String
              |- 2. productAmount(還在購物車的商品數量) – String
              |- 3. friendInviteAmount (好友邀請數量) – String
              |- 4. GSiMoney(GSiMoney Bean)
              |- 5. GSiBonus(GSiBonus Bean)
              |- 6. saleAmountForSong(歌曲銷售數量) – String
	 */
	public ArrayList queryMemberTotalData(long userID);
	
	/**
	 * 
	 * @param userID
	 * @return ArrayList
               |- 1. FriendInviteList(好友邀請清單) - Array
               |- 2. FriendList(好友清單) - Array
	 */
	public ArrayList queryFriend(String userID);
	
	
	public void saveFriend(String userID,String memberID);
	
	public void deleteFriend(String userID,String memberID);
	
	public LikeCreator[] queryLikeCreator(String userID);
	
	public void deleteCreator(String userID,String creatorID);
	
	public void saveAd(String userID,String email,String bannerType,
			           String actionName,String startDate,String endDate,
			           String url,String createDate,String actionContent);
	
	//Lucy寫的
	
		//查詢會員資料
		public ArrayList queryMember(long userID);
		
		//更新會員資料
		public void updateMember(long userID,String identityName,String userName,String location,String city,String birthday, String sex,String webSite,String subscribeStatus,String introduction,String likeMusicTypes,String likeSingers);
		
		//更新會員密碼
		public void updatePassword(long userID, String password);
		
		//更新會員信箱
		public void updateEmail(long userID, String email);
		
		//刪除會員圖片
		public void deleteMemberPicture(long userID);	
		
		//更新會員圖片/
		public void handleUploadPicture(long userID, String picture);
			
		//更新會員帳戶資料
		public void updateAccountData(long userID, String accountName, String accountNO, String bankName, String bankBranch, String identityNO, String address, String tel, String cellPhone);
		
	//怡秀寫的     2011-11-22
	
	//線上客服
	
	//客服-提問頁-儲存問題
	public void saveQuestion(String productType,String userIdentity,String name,String email,String tel,String questionType,String questionContent);
			
	//客服-管理者的問題管理第一個頁面    
	public Question[] queryQuestion(String startDate,String endDate,String productType,String email,String questionType);
			
	//客服-查詢管理者的名稱
	public Admin queryAdminName(long adminId);
		
	//客服-查詢問題的細節
	public Question queryQuestionDetail(long questionID);
			
	//客服-儲存回覆
	public Question saveAnswer(long questionID,long adminId,String answerContent,String adminName);
		
	//客服-儲存備註
	public Question saveNote(long questionID,long adminId,String noteContent,String adminName);
	
	
	//廣告管理
	
	//新增管理者廣告
	public void saveManagerAd(long adminId,String bannerType,String actionName,String picture,String startDate,String endDate,String url,String onDate,String offDate,String createDate);
	
	//查詢廣告詳細資料
	public Ad queryAdDetail(long adID);
	
	//修改廣告資料
	public Ad modifyAd(long adID,long adminID,String bannerType,String actionName,String picture,String fileName,String startDate,String endDate,String url,String onDate,String offDate);
	
	//查詢廣告清單
	public Ad[] queryAd(String bannerType,String actionName,String upStartDate,String upEndDate,String downStartDate,String downEndDate);
	
	//查詢創作者的廣告清單
	public Ad[] queryCreatorAd(String startDate,String endDate,String checkStatus,String albumAmount);
	
	//儲存備註
	public void saveNote(long creatorAdID,String checkStatus,String reason,long adminID);
	
	//Lucy@20111123
	
		//儲存創作人刊登的訊息/
		public void saveNews(long userID, String newsName, String newsSouce, String content, String onStatus);
		
		//創作人查詢刊登訊息
		public News[] queryNews(long userID, String onStatus);	
		
		//刪除刊登訊息
		public void deleteNews(long newsID);	
		
		//查詢訊息明細
		public News queryNewsDetail(long newsID);
		
		//儲存創作人更新的訊息
		public void updateNews(long newsID, String newsName, String newsSouce, String content, String onStatus);
		
		//儲存管理者新增的訊息
		public void saveManagerNews(long adminID, String newsCategory, String newsName, String picture, String newsSouce, String onDate, String content, String onStatus);
		
		//管理者查詢刊登訊息(起始頁面)
		public News[] queryFirstNewsList();
		
		//管理者查詢刊登訊息(查詢條件)/
		public News[] queryNewsList(String newsCategory, String newsName, String MOPEND, String MCLOSED, String onStatus, String newsSource);
		
		//儲存管理者更新的訊息
		public void updateManagerNews(long adminID,long newsID, String newsCategory, String newsName, String picture, String newsSouce, String onDate, String content);
		
	
}
