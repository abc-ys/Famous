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
import com.ubn.befamous.entity.AdType;
import com.ubn.befamous.entity.Admin;
import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.Fan;
import com.ubn.befamous.entity.Friend;
import com.ubn.befamous.entity.GeneralMember;
import com.ubn.befamous.entity.LikeCreator;
import com.ubn.befamous.entity.Member;
import com.ubn.befamous.entity.ModifyData;
import com.ubn.befamous.entity.News;
import com.ubn.befamous.entity.PrePaid;
import com.ubn.befamous.entity.PrePaidPrice;
import com.ubn.befamous.entity.Question;
import com.ubn.befamous.entity.SDCard;
import com.ubn.befamous.entity.SDCardPrice;

public interface PersonService {

	//會員profile頁
		public GeneralMember queryMemberData(long userID);
		
		/**
		 * 查詢一般會員最近動態
		 * @param userID
		 * @return |- 1. RecentLikeAlbum(最近喊讚的專輯清單) - Array
	               |- 2. RecentLikeSong(最近喊讚的歌曲清單)  - Array
	               |- 3. RecentListenSong(最近試聽的專輯清單) – Array
		 */
		public ArrayList queryMemberRecentAction(long userID);
		
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
		
		public Fan[] queryFans(long userID, long creatorId);
		
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
		public Friend[] queryFriend(long userID);
		
		public Friend[] queryUnCheckFriend(long userID);
		
		public Friend queryFriend(long userID,long memberID);
		
		public void saveFriend(long userID,long memberID,String type);
		
		public void deleteFriend(long userID,long memberID);
		
		public LikeCreator[] queryLikeCreator(long userID);
		
		public void deleteCreator(long userID,long creatorID);
		
		public void saveAd(long userID,long bannerTypeId,Ad ad);
		
		public AdType[] getAdType();
		
		public void saveProduction(String userID,String classification , SDCard sdcard, SDCardPrice sdcardPrice,
				PrePaid prepaid,PrePaidPrice prePaidPrice);
	
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
	public Question[] queryQuestion(String startDate,String endDate,String productType,String email,String questionType,String handleStatus);
			
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
	
		//最新訊息-儲存創作人刊登的訊息/
		public void saveNews(long userID, String newsName, String newsSouce, String content, String onStatus);
		
		//最新訊息-創作人查詢刊登訊息
		public News[] queryNews(long userID, String onStatus);	
		
		//最新訊息-創作人刪除刊登訊息
		public void deleteNews(long newsID);	
		
		//最新訊息-查詢訊息明細
		public News queryNewsDetail(long newsID);
		
		//最新訊息-儲存創作人更新的訊息
		public void updateNews(long newsID, String newsName, String newsSouce, String content, String onStatus);
		
		//訊息管理-儲存管理者新增的訊息
		public void saveManagerNews(long adminID, String newsCategory, String newsName, String picture, String newsSouce, String onDate, String content, String onStatus);
		
		//訊息管理-管理者查詢刊登訊息(起始頁面)
		public News[] queryFirstNewsList();
		
		//訊息管理-管理者查詢刊登訊息(查詢條件)/
		public News[] queryNewsList(String newsCategory, String newsName, String MOPEND, String MCLOSED, String onStatus, String newsSource);
		
		//訊息管理-管理者刪除刊登訊息
		public void deleteManageNews(long adminId, long newsID);
		
		//訊息管理-儲存管理者更新的訊息
		public void updateManagerNews(long adminID,long newsID, String newsCategory, String newsName, String picture, String newsSouce, String onDate, String content);


	//怡秀寫的  2011-11-24
	//管理會員資料
	
	//查詢會員清單
	public Member[] queryMemberList(String email,String identity,String startDate,String endDate,String location,String fanAmountOne,String fanAmountTwo,String friendAmountOne,String friendAmountTwo,String status);
	
	//查詢修改紀錄
	public ModifyData[] queryModifyRecord(String userID);
	
	//查詢會員詳細資料
	public ArrayList queryMemberDetailData(long userID);
	
	//更新會員狀態
	public void updateStatus(String userID,String adminID,String statusReason,String statusName);
	
	//更新付款資料
	public void updateAccount(String memberId,String adminID,String userName,String identityNO,String address,String cellPhone,String tel,String accountName,String bankName,String bankBranch,String accountNO,String statusName);
	
	
	//加入會員與會員登入
	
	//加入會員-儲存會員
	public Member saveMember(String email,String userName,String password,String sex,String birthday,String location);
	
	//加入會員-上傳圖片
	public void updateMemberPicture(String userID,String fileName);
	
	//加入會員-查詢會員
	public Member queryMemberInfo(String userID);
	
}
