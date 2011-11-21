package com.ubn.befamous.service;

import java.util.ArrayList;

import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.LikeCreator;
import com.ubn.befamous.entity.Member;
import com.ubn.befamous.entity.News;

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
	
}
