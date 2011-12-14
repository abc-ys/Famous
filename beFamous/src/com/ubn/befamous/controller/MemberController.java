package com.ubn.befamous.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import com.ubn.befamous.constant.SessionAttribute;
import com.ubn.befamous.entity.Admin;
import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.Creator;
import com.ubn.befamous.entity.Fan;
import com.ubn.befamous.entity.Friend;
import com.ubn.befamous.entity.GeneralMember;
import com.ubn.befamous.entity.GsiBonus;
import com.ubn.befamous.entity.GsiMoney;
import com.ubn.befamous.entity.Member;
import com.ubn.befamous.entity.MemberStatus;
import com.ubn.befamous.entity.ModifyData;
import com.ubn.befamous.entity.Order;
import com.ubn.befamous.entity.OrderDetail;
import com.ubn.befamous.entity.ProductionCategory;
import com.ubn.befamous.entity.Song;
import com.ubn.befamous.entity.SongPrice;
import com.ubn.befamous.service.PersonService;
import com.ubn.befamous.service.TransactionRecordService;

@Controller
@SessionAttributes
public class MemberController {
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private TransactionRecordService transactionRecordService;
	
	//創作人頁面
	@RequestMapping("/creatorProfile")
	public ModelAndView creatorProfile(long creatorID,Model model,HttpServletRequest request) {
		System.out.println("creatorProfile---"+creatorID);
		//long userID =2;
		long userID = (Long)request.getSession().getAttribute(SessionAttribute.USER_ID);  //從session取得userID
		
		ArrayList list = personService.queryCreatorData(creatorID);
		Creator creator = (Creator)list.get(0);
		
		//GeneralMember user = personService.queryMemberData(userID);  //user
		Friend[] arFriend = personService.queryFriend(userID);//user
		Fan[] arFan = personService.queryFans(userID,creatorID);//user
		//Fan[] arFan =  (Fan[])FansList.get(1);
		boolean isFan = false;
		//判斷是否已加入粉絲
		for(Fan f : arFan){
			if(f.getMember().getId() == userID){
				isFan = true;
				break;
			}
		}
	
		boolean isFriend = false;
		//判斷是否已加入好友
		for(Friend f : arFriend){
			System.out.println("1111111111111===>"+f.getInviter().getId());
			System.out.println("2222222222222===>"+creator.getId());
			if(f.getFriend().getId() == creator.getId()){
				isFriend = true;
				break;
			}
		}
		model.addAttribute("isFan", isFan);
		model.addAttribute("isFriend", isFriend);	
		model.addAttribute("creator", creator);
		model.addAttribute("newAlbum", list.get(2));
		
		return new ModelAndView("creatorProfile");		
	}		
	
	//創作人頁面的所有專輯tab(iframe)
	@RequestMapping("/creatorAllAlbums")
	public ModelAndView queryAllAlbums(long creatorID,Model model) {		
			
		System.out.println("AllAlbums---"+creatorID);
		ArrayList list = personService.queryAllCreatorAlbum(creatorID);
		model.addAttribute("albumList", list.get(0));
		model.addAttribute("songList", list.get(1));
		return new ModelAndView("queryAllAlbums");
	}
	
	//創作人頁面的最新動態tab(iframe)
	@RequestMapping("/creatorNewActivity")
	public ModelAndView queryCreatorRecentAction(long creatorID, Model model) {		
		System.out.println("CreatorRecentAction---"+creatorID);
		
		ArrayList list = personService.queryRecentActivity(creatorID);
		
		model.addAttribute("albumList", list.get(0));
		model.addAttribute("likeSongList", list.get(1));
		model.addAttribute("listenSongList", list.get(2));
		return new ModelAndView("queryCreatorRecentAction");
	}
	
	//創作人頁面的好友與粉絲tab(iframe)
	@RequestMapping("/creatorAllFriendsFans")
	public ModelAndView queryFriendsFans(long creatorID,Model model) {		
		
		System.out.println("FriendsFans---"+creatorID);
		
		ArrayList list = personService.queryFriendFans(creatorID);
		model.addAttribute("friendList", list.get(0));
		model.addAttribute("fanList", list.get(1));
			
		return new ModelAndView("queryFriendsFans");
	}

	//一般會員頁面
	@RequestMapping("/memberProfile")
	public ModelAndView memberProfile(long memberID,Model model,HttpServletRequest request) {
		System.out.println("memberProfile==>"+memberID);
		//long userID =4;
		long userID = (Long)request.getSession().getAttribute(SessionAttribute.USER_ID);  //從session取得userID
		
		GeneralMember member = personService.queryMemberData(memberID); //會員
		//GeneralMember member2 = personService.queryMemberData(userID);  //user
		Set<Friend> FriendSet = member.getFriend();
		Friend[] arFriend = personService.queryFriend(userID);
		boolean isFriend = false;
		//判斷是否已加入好友
		for(Friend f : arFriend){
			if(f.getFriend().getId() == member.getId()){
				isFriend = true;
				break;
			}
		}
		model.addAttribute("isFriend", isFriend);	
		model.addAttribute("GeneralMember", member);	
		model.addAttribute("FriendSet", FriendSet);	
		return new ModelAndView("memberProfile");			
	}
	
	//一般會員頁面的最新動態tab(iframe)
	@RequestMapping("/memberNewActivity")
	public ModelAndView queryMemberRecentAction(long memberID,Model model) {		
		System.out.println("MemberRecentAction---"+memberID);

		ArrayList list = personService.queryMemberRecentAction(memberID);
		model.addAttribute("albumList", list.get(0));
		model.addAttribute("likeSongList", list.get(1));
		model.addAttribute("listenSongList", list.get(2));
		return new ModelAndView("queryMemberRecentAction");
	}

	//一般會員頁面的好友與喜愛創作人tab(iframe)
	@RequestMapping("/memberAllFriendsCreators")
	public ModelAndView queryFriendsCreators(long memberID,Model model) {		
		System.out.println("AllFriendsCreators---"+memberID);
		
		ArrayList list= personService.queryFriendsCreators(memberID);  //member
		
		model.addAttribute("friendList", list.get(0));
		model.addAttribute("creatorList", list.get(1));
		return new ModelAndView("queryFriendsCreators");
	}

			//個人資料編輯-個人資料編輯頁
			@RequestMapping("/editMemberData")
			public ModelAndView queryMember(String userId)
			{
				ModelAndView mav = new ModelAndView("queryMember");		
				System.out.println("editMemberData==>");
				ArrayList list = this.personService.queryMember(Long.valueOf(userId));
				Member member = (Member) list.get(0);
				String likeMusicType = member.getLikeMusicType();
				String likeSinger = member.getLikeSinger();
				if(likeSinger ==null){
					mav.addObject("likeSingers",null);
				}else{
					String[] likeSingers  = likeSinger.split(",");
					mav.addObject("likeSingers",likeSingers);
				}
				mav.addObject("likeMusicType",likeMusicType);		
				mav.addObject("member",member);		
				return mav; 
			}
				
			//個人資料編輯-儲存個人資料編輯頁的修改
			@RequestMapping("/saveMemberData")
			public String saveMemberData(long userId,String identityName,String userName,String location,String city,String birthday, String sex,String webSite,String subscribeStatus,String introduction,String likeMusicTypes,String likeSingers)
			{
				System.out.println("saveMemberData==>");
				String birth = birthday.replaceAll("-", "");
				birth = birth+"000000";			
				this.personService.updateMember(userId, identityName, userName, location, city, birth, sex, webSite, subscribeStatus, introduction, likeMusicTypes, likeSingers);		
				return "redirect:editMemberData.do?userId="+userId;	
			}
				
			//個人資料編輯-修改email(window open)
			@RequestMapping("/modifyEmail")
			public ModelAndView modifyEmail(long userId, Model model) {
				System.out.println("modifyEmail==>");
				Member member = (Member) this.personService.queryMember(userId).get(0);
				model.addAttribute("member",member);
				return new ModelAndView("modifyEmail");
			}
				
			//個人資料編輯-儲存修改email結果
			@RequestMapping("/saveEmail")
			public ModelAndView saveEmail(long userId, String newEmail) {
				System.out.println("saveEmail==>");
				System.out.println(		"userID="+userId+", newEmail="+newEmail);
				this.personService.updateEmail(userId, newEmail);
				return new ModelAndView("saveEmail");
			}
				
			//個人資料編輯-修改密碼(window open)
			@RequestMapping("/modifyPassword")
			public ModelAndView modifyPassword(long userId, Model model) {
				System.out.println("modifyPassword==>");
				Member member = (Member) this.personService.queryMember(userId).get(0);
				model.addAttribute("member",member);
				return new ModelAndView("modifyPassword");
			}
			
			//個人資料編輯-儲存修改密碼結果
			@RequestMapping("/savePassword")
			public ModelAndView savePassword(long userId, String newPassword) {
				System.out.println("savePassword==>");
				this.personService.updatePassword(userId, newPassword);
				return new ModelAndView("savePassword");
			}

			//個人資料編輯-刪除個人圖片
			@RequestMapping("/deleteMemberPicture")
			public String deleteMemberPicture(long userId) {
				this.personService.deleteMemberPicture(userId);
				return "redirect:editMemberData.do?userId="+userId;	
			}
				
			//個人資料編輯-上傳個人圖片(window open)
			@RequestMapping("/uploadMemberPicture")
			public ModelAndView uploadMemberPicture(long userId, Model model) {
				System.out.println("uploadMemberPicture==>");
				System.out.println(userId);
				Member member = (Member) this.personService.queryMember(userId).get(0);
				model.addAttribute("member",member);
				return new ModelAndView("uploadMemberPicture");
			}
				
			//個人資料編輯-儲存會員上傳的圖片
			@RequestMapping("/handleUploadPicture")
			public ModelAndView handleUploadPicture(HttpServletRequest request) throws Exception {
				int yourMaxMemorySize = 500 * 500* 1024;
				File yourTempDirectory = new File("/tmp");
				int yourMaxRequestSize = 500 * 500* 1024;
				boolean writeToFile = true;
				String allowedFileTypes = ".gif .jpg .png";
				String fileName="";
				String fName="";
				String userId="";
				String saveDirectory = "D:/gitTest/ImageWeb/WebContent/image";
				// Check that we have a file upload request
				boolean isMultipart = ServletFileUpload.isMultipartContent(request);
				System.out.println("isMultipart=" + isMultipart + "<br>");

				// Create a factory for disk-based file items
				DiskFileItemFactory factory = new DiskFileItemFactory(yourMaxMemorySize, yourTempDirectory);
				
				// Create a new file upload handler
				ServletFileUpload upload = new ServletFileUpload(factory);

				// Set overall request size constraint
				upload.setSizeMax(yourMaxRequestSize);

				try {
					// Parse the request
					List items = upload.parseRequest(request);
						// Process the uploaded items
					Iterator iter = items.iterator();
					while (iter.hasNext()) {
						FileItem item = (FileItem) iter.next();

						if (item.isFormField()) {
							// Process a regular form field	
							//processFormField(item);		
							String name = item.getFieldName();
							String value = item.getString("UTF-8");
							if(name.equals("userId")){
								userId = value;
								fName = value;
							}
							System.out.println(name + "=" + value + "<br />");
						} else {
							// Process a file upload
							//processUploadedFile(item);	
							String fieldName = item.getFieldName();
							fileName = item.getName();
							String contentType = item.getContentType();
							boolean isInMemory = item.isInMemory();
							long sizeInBytes = item.getSize();
							//System.out.println("userID=" + userID + "<br />");
							System.out.println("fieldName=" + fieldName + "<br />");
							System.out.println("fileName=" + fileName + "<br />");
							System.out.println("contentType=" + contentType + "<br />");
							System.out.println("isInMemory=" + isInMemory + "<br />");
							System.out.println("sizeInBytes=" + sizeInBytes + "<br />");
						      
							if (fileName != null && !"".equals(fileName)) {
								if (writeToFile) {
									// 副檔名
									 String formatName = fileName.substring(fileName.length() - 4,fileName.length());
									 fileName = (fName + formatName).toLowerCase();
								      
									System.out.println("fileName to be saved=" + fileName + "<br />");
									String extension = FilenameUtils.getExtension(fileName);
									if (allowedFileTypes.indexOf(extension.toLowerCase()) != -1) {
									    File uploadedFile = new File(saveDirectory,	fileName);						
									    item.write(uploadedFile);
									} else {
										System.out.println("上傳的檔案不能是" + extension + "<br />");
									}
								} else {						
									//InputStream uploadedStream = item.getInputStream();
									//...
									//uploadedStream.close();
									// Process a file upload in memory
									byte[] data = item.get();
									System.out.println("data size=" + data.length + "<br />");
								}
							}
						}
					}
				} catch (FileUploadBase.SizeLimitExceededException ex1) {
					System.out.println("上傳檔案超過最大檔案允許大小" + yourMaxRequestSize / (1024 * 1024) + "MB !");
				}
				System.out.println(userId);
				String picture = "image/"+fileName;
				this.personService.handleUploadPicture(Long.valueOf(userId), picture);
				return new ModelAndView("handleUploadPicture");
			}
	
		
	//管理者查詢會員資料
	@RequestMapping("/manageMember")
	public ModelAndView queryMemberData(String adminID,String orderNo, String identity, String MOPEND, String MCLOSED, String location, String minFns, String maxFns, String minFds, String maxFds, String status, Model model) {
		
		System.out.println("manageMember==>");		
		System.out.println("    orderNo="+orderNo+", userIdentity="+identity+", MOPEND="+MOPEND+", MCLOSED="+MCLOSED+", location="+location+", minFns="+minFns+", maxFns="+maxFns+", minFds="+minFds+", maxFds="+maxFds+", statusName="+status);
		
		Member[] member;
		if (StringUtils.isBlank(orderNo)&& StringUtils.isBlank(identity)&& StringUtils.isBlank(MOPEND)
				&&	StringUtils.isBlank(MCLOSED)&& StringUtils.isBlank(location)&& StringUtils.isBlank(minFns)
				&&StringUtils.isBlank(maxFns)&& StringUtils.isBlank(minFds)&& StringUtils.isBlank(maxFds)
				&&StringUtils.isBlank(status)) {
			member=new Member[0];
			System.out.println("條件為空");
		}else{
		
		member = personService.queryMemberList(orderNo, identity, MOPEND, MCLOSED, location, minFns, maxFns, minFds, maxFds, status);
		}
		int fans = 22;
		int friends = 66;
		
		ArrayList memberList = new ArrayList();
		memberList.add(member);
		memberList.add(fans);
		memberList.add(friends);
		
		model.addAttribute("member",member);
		model.addAttribute("adminID","1");
		model.addAttribute("fans",fans);
		model.addAttribute("friends",friends);
		
		return new ModelAndView("queryMemberData");
		
	}
	
	//管理者查詢會員資料的修改記錄(window open)
	@RequestMapping("/manageMemberNote")
	public ModelAndView manageMemberNote(String memberId, Model model) {
		System.out.println("manageMemberNote==>");		
		System.out.println("    memberId="+memberId);
		
		ModifyData[] md = personService.queryModifyRecord(memberId);
		
		model.addAttribute("modifyData",md);
		return new ModelAndView("manageMemberNote");
	}

	//管理者查詢會員資料詳細頁
	@RequestMapping("/manageGMemberDetail/{type}/{adminId}/{memberId}")
	public ModelAndView queryGMemberDetail(@PathVariable("type") String type,@PathVariable("adminId") String adminId,@PathVariable("memberId") String memberId, Model model) {
	
		System.out.println("manageGMemberDetail==>");		
		System.out.println("    type="+type+", adminId="+adminId+", memberId="+memberId);
		
		ArrayList memberDetail = personService.queryMemberDetailData(Long.parseLong(memberId));
		
		model.addAttribute("memberDetail",memberDetail);
		model.addAttribute("admin",adminId);
		
		ModelAndView mav = new ModelAndView();		
		if(type.equals("get")){
			mav.setViewName("queryGMemberDetail");
		}else{
			mav.setViewName("modifyGMemberDetail");
		}
		return mav;
					
	}
	
	//管理者填寫停權理由 (window open)
	@RequestMapping("/addOffenseReason/{adminId}/{memberId}")
	public ModelAndView addOffenseReason(@PathVariable("adminId") String adminId,@PathVariable("memberId") String memberId, Model model) {
		
		System.out.println("addOffenseReason==>");		
		System.out.println("    memberId="+memberId+", adminId="+adminId);
		
		model.addAttribute("memberId",memberId);
		model.addAttribute("admin",adminId);
		return new ModelAndView("addOffenseReason");
	}
	
	//儲存管理者修改的一般會員資訊
	@RequestMapping("/saveGMember")
	public String saveGMember(String adminId,String memberId,String statusName, Model model) {				
		System.out.println("saveGMember==>");		
		System.out.println("    memberId="+memberId+", adminId="+adminId);
		String reason="";
		if(statusName.equals("停權")){
			Member m = personService.queryMemberInfo(memberId);
			reason=m.getMemberStatus().getStatusReason();
		}
		personService.updateStatus(memberId, adminId, reason, statusName);
		return "redirect:/manageGMemberDetail/get/"+adminId+"/"+memberId+".do";
	}
	
	//儲存停權理由
	@RequestMapping("/saveOffenseReason")
	public ModelAndView saveOffenseReason(String adminId,String memberId,String reason,String statusName, Model model) {						
		System.out.println("saveOffenseReason==>");		
		System.out.println("    memberId="+memberId+", adminId="+adminId+", reason="+reason);
		personService.updateStatus(memberId, adminId, reason, statusName);
		return new ModelAndView("saveOffenseReason");
	}	
	
	//管理者查詢創作人資料詳細頁
	@RequestMapping("/manageCreatorDetail/{type}/{adminId}/{memberId}")
	public ModelAndView queryCreatorDetail(@PathVariable("type") String type,@PathVariable("adminId") String adminId,@PathVariable("memberId") String memberId, Model model) {
		
		
		ArrayList creatorDetail = personService.queryMemberDetailData(Long.parseLong(memberId));
		
			
		model.addAttribute("creatorDetail",creatorDetail);
		model.addAttribute("admin",adminId);
			
		ModelAndView mav = new ModelAndView();		
		if(type.equals("get")){
			mav.setViewName("queryCreatorDetail");
		}else{
			mav.setViewName("modifyCreatorDetail");
		}
		return mav;
	}
		
	//儲存管理者修改的創作人資訊
	@RequestMapping("/saveCreator")
	public String saveCreator(String adminId,String memberId,String statusName,String accountName,String bankName,String bankBranch,String accountNO,String userName,String identityNO,String address,String cellPhone,String tel, Model model) {		
		System.out.println("saveCreator==>");		
		System.out.println("    memberId="+memberId+", adminId="+adminId+", statusName="+statusName+", accountName="+accountName+", bankName="+bankName+", bankBranch="+bankBranch+", accountNO="+accountNO+", userName="+userName+", identityNO="+identityNO+", address="+address+", cellPhone="+cellPhone+", tel="+tel);
		personService.updateAccount(memberId, adminId, userName, identityNO, address, cellPhone, tel, accountName, bankName, bankBranch, accountNO, statusName);
		return "redirect:/manageCreatorDetail/get/"+adminId+"/"+memberId+".do";
	}
	
	//創作人被檢舉清單(專輯)
			@RequestMapping("/queryOffenseAlbum")
			public ModelAndView queryOffenseAlbum(String adminId,String memberId, Model model) {						
				System.out.println("queryOffenseAlbum==>");		
				System.out.println("    memberId="+memberId+", adminId="+adminId);

				ArrayList offenseAlbums = transactionRecordService.queryOffenseAlbumForUser(Long.parseLong(memberId));
	
				ArrayList offenseSongs = transactionRecordService.queryOffenseSongForUser(Long.parseLong(memberId));

				model.addAttribute("offenseAlbums", offenseAlbums);
				model.addAttribute("offenseSongs", offenseSongs);
				return new ModelAndView("queryOffenseAlbum");
			}	

			//創作人被檢舉清單(歌曲)
			@RequestMapping("/queryOffenseSong")
			public ModelAndView queryOffenseSong(String adminId,String memberId, Model model) {						
				System.out.println("queryOffenseSong==>");		
				System.out.println("    memberId="+memberId+", adminId="+adminId);

				ArrayList offenseAlbums = transactionRecordService.queryOffenseAlbumForUser(Long.parseLong(memberId));
				
				ArrayList offenseSongs = transactionRecordService.queryOffenseSongForUser(Long.parseLong(memberId));

				model.addAttribute("offenseAlbums", offenseAlbums);
				model.addAttribute("offenseSongs", offenseSongs);		
				return new ModelAndView("queryOffenseSong");
			}

}

