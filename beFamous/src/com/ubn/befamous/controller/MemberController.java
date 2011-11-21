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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import com.ubn.befamous.entity.Admin;
import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.Creator;
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

@Controller
@SessionAttributes
public class MemberController {
	
	@Autowired
	private PersonService personService;
	
	//創作人頁面
	@RequestMapping("/creatorProfile")
	public ModelAndView creatorProfile(String creatorID,Model model) {
		System.out.println("creatorProfile---"+creatorID);
		ArrayList list = personService.queryCreatorData(Long.parseLong(creatorID));
		/*Creator creator = new Creator();
		creator.setId(111);
		creator.setPicture("images/lucy.jpg");
		creator.setUserName("劉為駿");
		creator.setLocation("台灣");
		creator.setCity("台中");
		creator.setIdentityName("樂團");
		creator.setLikeMusicType("搖滾樂,電子音樂");
		creator.setLikeSinger("安心亞");
		creator.setIntroduction("我是kevin，我長得像小夫!");
		
		Album newAlbum = new Album();
		newAlbum.setId(111);
		newAlbum.setCover("image/image001.png");	
		newAlbum.setName("aaa");
		newAlbum.setCreator(creator);
		newAlbum.setCreateDate("2011.10.25");
		
		Album[] albums={newAlbum};		
		model.addAttribute("creator", creator);
		model.addAttribute("newAlbum", albums);
		*/
		model.addAttribute("creator", list.get(0));
		model.addAttribute("newAlbum", list.get(2));
		
		return new ModelAndView("creatorProfile");		
	}		
	
	//創作人頁面的所有專輯tab(iframe)
	@RequestMapping("/creatorAllAlbums")
	public ModelAndView queryAllAlbums(long creatorID,Model model) {		
			
		System.out.println("AllAlbums---"+creatorID);
		ArrayList list = personService.queryAllCreatorAlbum(creatorID);
		/*Creator creator = new Creator();		
		creator.setId(creatorID);
		creator.setUserName("lendy");	
		
		Album album = new Album();
		album.setId(111);
		album.setCover("image/image001.png");	
		album.setName("aaa");
		album.setCreator(creator);
		album.setCreateDate("2011.10.25");
	
		Album album1 = new Album();
		album1.setId(112);
		album1.setCover("image/image002.png");	
		album1.setName("bbb");
		album1.setCreator(creator);
		album1.setCreateDate("2011.10.24");
		
		Album album2 = new Album();
		album2.setId(113);
		album2.setCover("image/image003.png");	
		album2.setName("bbb");
		album2.setCreator(creator);
		album2.setCreateDate("2011.10.24");
		
		Album[] albums = {album, album1, album2};	
		
		Song hotSong = new Song();
		hotSong.setName("兩隻老虎");
		hotSong.setAlbum(album);
		
		Song hotSong1 = new Song();
		hotSong1.setName("一隻沒有眼睛");
		hotSong1.setAlbum(album1);
		
		Song hotSong2 = new Song();
		hotSong2.setName("一隻沒有尾巴");
		hotSong2.setAlbum(album1);
		
		Song[] hotSongs = { hotSong, hotSong1, hotSong2};
		*/
		model.addAttribute("albumList", list.get(0));
		model.addAttribute("songList", list.get(1));
		return new ModelAndView("queryAllAlbums");
	}
	
	//創作人頁面的最新動態tab(iframe)
	@RequestMapping("/creatorNewActivity")
	public ModelAndView queryCreatorRecentAction(long creatorID, Model model) {		
		System.out.println("CreatorRecentAction---"+creatorID);
		/*
		Creator creator = new Creator();		
		creator.setId(9999);
		creator.setUserName("lendy");
		Creator creator2 = new Creator();
		creator2.setId(8888);
		creator2.setUserName("KevinLiu");
		Creator creator3 = new Creator();		
		creator3.setId(7777);
		creator3.setUserName("lendyLin");
		
		Album album = new Album();
		album.setCover("image/image001.png");	
		album.setName("ccc");
		album.setCreator(creator);
		//album.setDate("2011.10.25");
		
		Album album1 = new Album();
		album1.setCover("image/image002.png");	
		album1.setName("dddd");
		album1.setCreator(creator2);
		//album1.setDate("2011.10.25");
		
		Album album2 = new Album();
		album2.setCover("image/image003.png");	
		album2.setName("eeeee");
		album2.setCreator(creator3);
		//album2.setDate("2011.10.25");
		
		Album[] albums = { album, album1, album2};
		
		Song likeSong = new Song();
		likeSong.setName("兩隻老虎");
		likeSong.setAlbum(album);
		
		Song likeSong1 = new Song();
		likeSong1.setName("一隻沒有眼睛");
		likeSong1.setAlbum(album1);
		
		Song likeSong2 = new Song();
		likeSong2.setName("一隻沒有尾巴");
		likeSong2.setAlbum(album2);
		
		Song[] likeSongs = { likeSong, likeSong1, likeSong2};
		
		Song listenSong = new Song();
		listenSong.setName("小星星");
		listenSong.setAlbum(album);
		
		Song listenSong1 = new Song();
		listenSong1.setName("一閃一閃");
		listenSong1.setAlbum(album1);
		
		Song listenSong2 = new Song();
		listenSong2.setName("亮晶晶");
		listenSong2.setAlbum(album2);
		
		Song[] listenSongs = { likeSong, likeSong1, likeSong2};
		*/
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
		/*
		Member friend = new Member();
		friend.setUserName("aaa");
		friend.setIdentityName("一般會員");
		friend.setId(001);
		friend.setPicture("images/title_01.gif");
		
		Member friend1 = new Member();
		friend1.setUserName("bbb");
		friend1.setIdentityName("創作人");
		friend1.setId(002);
		friend1.setPicture("images/title_01.gif");
		
		Member friend2 = new Member();
		friend2.setUserName("ccc");
		friend2.setIdentityName("一般會員");
		friend2.setId(003);
		friend2.setPicture("images/title_01.gif");
		
		Member friend3 = new Member();
		friend3.setUserName("ddd");
		friend3.setIdentityName("創作人");
		friend3.setId(004);
		friend3.setPicture("images/title_01.gif");
		
		Member[] friends = {friend, friend1, friend2, friend3};
		
		Member fan = new Member();
		fan.setUserName("aaaa");
		fan.setId(104);
		fan.setPicture("images/title_01.gif");
		
		Member fan1 = new Member();
		fan1.setUserName("bbbb");
		fan1.setId(105);
		fan1.setPicture("images/title_01.gif");
		
		Member fan2 = new Member();
		fan2.setUserName("cccc");
		fan2.setId(106);
		fan2.setPicture("images/title_01.gif");
		
		Member fan3 = new Member();
		fan3.setUserName("dddd");
		fan3.setId(107);
		fan3.setPicture("images/title_01.gif");
		
		Member[] fans = {fan, fan1, fan2, fan3};
		*/
		ArrayList list = personService.queryFriendFans(creatorID);
		model.addAttribute("friendList", list.get(0));
		model.addAttribute("fanList", list.get(1));
			
		return new ModelAndView("queryFriendsFans");
	}

	//一般會員頁面
	@RequestMapping("/memberProfile")
	public ModelAndView memberProfile(String memberID,Model model) {
		System.out.println("memberProfile==>"+memberID);
		Member member = new Member();
		member.setId(111);
		member.setPicture("images/lucy.jpg");
		member.setUserName("劉為駿");
		member.setLocation("台灣");
		member.setCity("台中");
		member.setIntroduction("我是kevin，我長得像小夫!");	
		model.addAttribute("member", member);			
		return new ModelAndView("memberProfile");			
	}
	
	//一般會員頁面的最新動態tab(iframe)
	@RequestMapping("/memberNewActivity")
	public ModelAndView queryMemberRecentAction(String memberID,Model model) {		
		System.out.println("MemberRecentAction---"+memberID);
		
		Creator creator = new Creator();		
		creator.setId(1111);
		creator.setUserName("lendy");
		Creator creator2 = new Creator();
		creator2.setId(1111);
		creator2.setUserName("KevinLiu");
		Creator creator3 = new Creator();		
		creator3.setId(1111);
		creator3.setUserName("lendyLin");
		
		Album album = new Album();
		album.setCover("image/image001.png");	
		album.setName("ccc");
		album.setCreator(creator);
		//album.setDate("2011.10.25");
		
		Album album1 = new Album();
		album1.setCover("image/image002.png");	
		album1.setName("dddd");
		album1.setCreator(creator2);
		//album1.setDate("2011.10.25");
		
		Album album2 = new Album();
		album2.setCover("image/image003.png");	
		album2.setName("eeeee");
		album2.setCreator(creator3);
		//album2.setDate("2011.10.25");
		
		Album[] albums = { album, album1, album2};
		
		Song likeSong = new Song();
		likeSong.setName("兩隻老虎");
		likeSong.setAlbum(album);
		
		Song likeSong1 = new Song();
		likeSong1.setName("一隻沒有眼睛");
		likeSong1.setAlbum(album1);
		
		Song likeSong2 = new Song();
		likeSong2.setName("一隻沒有尾巴");
		likeSong2.setAlbum(album2);
		
		Song[] likeSongs = { likeSong, likeSong1, likeSong2};
		
		Song listenSong = new Song();
		listenSong.setName("小星星");
		listenSong.setAlbum(album);
		
		Song listenSong1 = new Song();
		listenSong1.setName("一閃一閃");
		listenSong1.setAlbum(album1);
		
		Song listenSong2 = new Song();
		listenSong2.setName("亮晶晶");
		listenSong2.setAlbum(album2);
		
		Song[] listenSongs = { likeSong, likeSong1, likeSong2};
				
		model.addAttribute("albumList", albums);
		model.addAttribute("likeSongList", likeSongs);
		model.addAttribute("listenSongList", listenSongs);
		return new ModelAndView("queryMemberRecentAction");
	}

	//一般會員頁面的好友與喜愛創作人tab(iframe)
	@RequestMapping("/memberAllFriendsCreators")
	public ModelAndView queryFriendsCreators(String memberID,Model model) {		
		System.out.println("AllFriendsCreators---"+memberID);
		
		Member friend = new Member();
		friend.setUserName("aaa");
		friend.setId(001);
		friend.setPicture("images/title_01.gif");
		
		Member friend1 = new Member();
		friend1.setUserName("bbb");
		friend1.setId(002);
		friend1.setPicture("images/title_01.gif");
		
		Member friend2 = new Member();
		friend2.setUserName("ccc");
		friend2.setId(003);
		friend2.setPicture("images/title_01.gif");
		Member friend3 = new Member();
		friend3.setUserName("ddd");
		friend3.setId(004);
		friend3.setPicture("images/title_01.gif");
		
		Member[] friends = {friend, friend1, friend2, friend3};
		
		Member creator = new Member();
		creator.setUserName("aaaa");
		creator.setId(105);
		creator.setPicture("images/title_01.gif");
		
		Member creator1 = new Member();
		creator1.setUserName("bbbb");
		creator1.setId(106);
		creator1.setPicture("images/title_01.gif");
		
		Member creator2 = new Member();
		creator2.setUserName("cccc");
		creator2.setId(107);
		creator2.setPicture("images/title_01.gif");
		
		Member creator3 = new Member();
		creator3.setUserName("dddd");
		creator3.setId(108);
		creator3.setPicture("images/title_01.gif");
		
		Member[] creators = {creator, creator1, creator2, creator3};
			
		model.addAttribute("friendList", friends);
		model.addAttribute("creatorList", creators);
		return new ModelAndView("queryFriendsCreators");
	}

	//個人資料編輯頁
	@RequestMapping("/editMemberData")
	public ModelAndView queryMember(String memberID, Model model)
	{
		System.out.println("editMemberData==>");
		System.out.println("	memberID="+memberID);
		Creator creator = new Creator();
		creator.setLikeMusicType("兒歌");
		creator.setLikeSinger("安心亞,瑤瑤");
		
		Member member = new Member();
		member.setId(111);
		member.setIdentityName("創作人");
		member.setPicture("images/lucy.jpg");
		member.setUserName("劉為駿");
		member.setLocation("台灣");
		member.setCity("台中");
		member.setBirthday("2011/11/11");
		member.setEmail("aaa.@ubn.com");
		member.setPassword("xxxxxxx");
		member.setSex("男");
		member.setWebSite("www.google.com");		
		member.setSubscribeStatus("Y");
		member.setIntroduction("我是kevin，我長得像小夫!");
		//member.setCreator(creator);
				
		model.addAttribute("member", member);			
		
		return new ModelAndView("queryMember");
		
	}
	
	//儲存個人資料編輯頁的修改並呈現修改後結果
	@RequestMapping("/saveMemberData")
	public String saveMemberData(String memberID,String picture,String identityName,String userName,String location,String city,String birthday,String password, String email,String sex,String webSite,String subscribeStatus,String introduction,String likeMusicType,String likeSinger,Model model)
	{
		System.out.println("saveMemberData==>");
		System.out.println("	memberID="+memberID+", identityName="+identityName+", userName="+userName+", location="+location+", city="+city+", birthday="+birthday+", sex="+sex+", webSite="+webSite+", subscribeStatus="+subscribeStatus+", introduction="+introduction+", likeMusicType="+likeMusicType+", likeSinger="+likeSinger);
		
		Creator creator = new Creator();
		creator.setLikeMusicType(likeMusicType);
		creator.setLikeSinger(likeSinger);
		Member member = new Member();		
		member.setPicture(picture);
		member.setIdentityName(identityName);
		member.setUserName(userName);
		member.setLocation(location);
		member.setCity(city);
		member.setBirthday(birthday);
		member.setEmail(email);
		member.setPassword(password);
		member.setSex(sex);
		member.setWebSite(webSite);		
		member.setSubscribeStatus(subscribeStatus);
		member.setIntroduction(introduction);
		//member.setCreator(creator);
		
		model.addAttribute("member", member);	
		return "redirect:editMemberData.do";	
	}
	
	//個人資料編輯頁的修改email(window open)
	@RequestMapping("/modifyEmail")
	public ModelAndView modifyEmail(String memberID, String password,Model model) {
		System.out.println("modifyEmail==>");
		System.out.println(		"memberID="+memberID+"password="+password);
		model.addAttribute("password",password);
		return new ModelAndView("modifyEmail");
	}
	
	//儲存修改email結果
	@RequestMapping("/saveEmail")
	public ModelAndView saveEmail(String memberID, String newEmail) {
		System.out.println("saveEmail==>");
		System.out.println(		"memberID="+memberID+"newEmail="+newEmail);
		Member member = new Member();
		member.setEmail(newEmail);
		System.out.println("newEmail------"+newEmail);
		return new ModelAndView("saveEmail");
	}
	
	//個人資料編輯頁的修改密碼(window open)
	@RequestMapping("/modifyPassword")
	public ModelAndView modifyPassword(String memberID, String password,Model model) {
		System.out.println("modifyPassword==>");
		System.out.println(		"memberID="+memberID+"password="+password);
		model.addAttribute("password",password);
		return new ModelAndView("modifyPassword");
	}
	
	//儲存修改密碼結果
	@RequestMapping("/savePassword")
	public ModelAndView savePassword(String memberID, String newPassword) {
		System.out.println("savePassword==>");
		System.out.println(		"memberID="+memberID+"newPassword="+newPassword);
		Member member = new Member();
		member.setPassword(newPassword);
		System.out.println("newPassword------"+newPassword);
		return new ModelAndView("savePassword");
	}

	//個人資料編輯頁的刪除個人圖片
	@RequestMapping("/deleteMemberPicture")
	public String deleteMemberPicture(String memberID) {
		Member member = new Member();
		member.setPicture("");
		return "redirect:editMemberData.do";
	}
	
	//個人資料編輯頁的上傳個人圖片(window open)
	@RequestMapping("/uploadMemberPicture")
	public ModelAndView uploadMemberPicture(String memberId,Model model) {
		System.out.println("uploadMemberPicture==>");
		System.out.println(		"memberId="+memberId);
		model.addAttribute("memberId",memberId);
		return new ModelAndView("uploadMemberPicture");
	}
	
	//儲存會員上傳的圖片
	@RequestMapping("/handleUploadPicture")
	public ModelAndView handleUploadPicture(HttpServletRequest request, String memberId) throws Exception {
		
		int yourMaxMemorySize = 500 * 500* 1024;
		File yourTempDirectory = new File("/tmp");
		int yourMaxRequestSize = 500 * 500* 1024;
		boolean writeToFile = true;
		String allowedFileTypes = ".gif .jpg .png";

		String saveDirectory = "D:/gitTest/ImageWeb/WebContent/image/memberPicture";

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
					System.out.println(name + "=" + value + "<br />");
				} else {
					// Process a file upload
					//processUploadedFile(item);	
					String fieldName = item.getFieldName();
					String fileName = item.getName();
					String contentType = item.getContentType();
					boolean isInMemory = item.isInMemory();
					long sizeInBytes = item.getSize();
					System.out.println("memberId=" + memberId + "<br />");
					System.out.println("fieldName=" + fieldName + "<br />");
					System.out.println("fileName=" + fileName + "<br />");
					System.out.println("contentType=" + contentType + "<br />");
					System.out.println("isInMemory=" + isInMemory + "<br />");
					System.out.println("sizeInBytes=" + sizeInBytes + "<br />");
					
				      
					if (fileName != null && !"".equals(fileName)) {
						if (writeToFile) {
							// 副檔名
							 String formatName = fileName.substring(fileName.length() - 4,fileName.length());
						    fileName = (memberId + formatName).toLowerCase();
						      
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
		return new ModelAndView("handleUploadPicture");
	}
	
	//管理者查詢會員資料
	@RequestMapping("/manageMember")
	public ModelAndView queryMemberData(String orderNo, String userIdentity, String MOPEND, String MCLOSED, String location, String minFns, String maxFns, String minFds, String maxFds, String statusName, Model model) {
		
		System.out.println("manageMember==>");		
		System.out.println("    orderNo="+orderNo+", userIdentity="+userIdentity+", MOPEND="+MOPEND+", MCLOSED="+MCLOSED+", location="+location+", minFns="+minFns+", maxFns="+maxFns+", minFds="+minFds+", maxFds="+maxFds+", statusName="+statusName);
		
		MemberStatus ms = new MemberStatus();
		ms.setStatusName("正常");
		
		Member member = new Member();
		member.setId(1111111);
		member.setEmail("xxx@ubn.net");
		member.setUserName("王大明");
		member.setIdentityName("創作人");
		member.setLocation("台灣");
		member.setCreateDate("2011/11/11");
		member.setMemberStatus(ms);		
		int fans = 22;
		int friends = 66;
		
		ArrayList memberList = new ArrayList();
		memberList.add(member);
		memberList.add(fans);
		memberList.add(friends);
		
		ArrayList[] memberLists = {memberList};
		model.addAttribute("memberLists",memberLists);
		
		Admin admin = new Admin();
		admin.setId(1111);
		model.addAttribute("admin",admin);
		return new ModelAndView("queryMemberData");
		
	}
	
	//管理者查詢會員資料的修改記錄(window open)
	@RequestMapping("/manageMemberNote")
	public ModelAndView manageMemberNote(String memberId, Model model) {
		System.out.println("manageMemberNote==>");		
		System.out.println("    memberId="+memberId);
		
		ModifyData md = new ModifyData();
		md.setContent("電話修正為0922222222");
		md.setCreateDate("2011-10-10 19:20:20");
		//md.setCreator("xxx");
		md.setId(001);
		
		ModifyData md1 = new ModifyData();
		md1.setContent("電話修正為0933333333");
		md1.setCreateDate("2011-10-13 13:20:20");
		//md1.setCreator("xxx");
		md1.setId(002);
		
		ModifyData[] mds = {md, md1};
		
		model.addAttribute("mds",mds);
		return new ModelAndView("manageMemberNote");
	}

	//管理者查詢會員資料詳細頁
	@RequestMapping("/manageGMemberDetail/{type}/{adminId}/{memberId}")
	public ModelAndView queryGMemberDetail(@PathVariable("type") String type,@PathVariable("adminId") String adminId,@PathVariable("memberId") String memberId, Model model) {
	
		System.out.println("manageGMemberDetail==>");		
		System.out.println("    type="+type+", adminId="+adminId+", memberId="+memberId);
		
		//會員資料
		MemberStatus ms = new MemberStatus();
		ms.setStatusName("停權");
		ms.setStatusReason("被檢舉次數過多");
			
		GeneralMember member = new GeneralMember();
		member.setId(1111111);
		member.setEmail("xxx@ubn.net");
		member.setUserName("王大明");
		member.setIdentityName("一般會員");
		member.setLocation("台灣");
		member.setCreateDate("2011/11/11");
		member.setMemberStatus(ms);
		
		GsiMoney gsiMoney = new GsiMoney();
		gsiMoney.setBalance("300");
		
		Set<GsiMoney> g = new HashSet<GsiMoney>();
		g.add(gsiMoney);
		
		member.setGsiMoney(g);
		
		GsiBonus gsiBonus = new GsiBonus();
		gsiBonus.setBalance("50");
		Set<GsiBonus> b = new HashSet<GsiBonus>();
		b.add(gsiBonus);
		
		member.setGsiBonus(b);
		
		int fans = 20;
		int friends = 100;
		int albums = 5;
		int songs = 50;
		int beOffendSongs = 10;
		int beOffendAlbums = 3;
		int offense = 30;
		
		ArrayList memberDetail = new ArrayList();
		memberDetail.add(member);
		memberDetail.add(fans);
		memberDetail.add(friends);
		memberDetail.add(albums);
		memberDetail.add(songs);
		memberDetail.add(beOffendSongs);
		memberDetail.add(beOffendAlbums);
		memberDetail.add(offense);
		
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
		System.out.println("    memberId="+memberId+", adminId="+adminId+", statusName="+statusName);
		return "redirect:/manageGMemberDetail/modify/"+adminId+"/"+memberId+".do";
	}
	
	//儲存停權理由
	@RequestMapping("/saveOffenseReason")
	public ModelAndView saveOffenseReason(String adminId,String memberId,String reason, Model model) {						
		System.out.println("saveOffenseReason==>");		
		System.out.println("    memberId="+memberId+", adminId="+adminId+", reason="+reason);
		return new ModelAndView("saveOffenseReason");
	}	
	
	//管理者查詢創作人資料詳細頁
	@RequestMapping("/manageCreatorDetail/{type}/{adminId}/{memberId}")
	public ModelAndView queryCreatorDetail(@PathVariable("type") String type,@PathVariable("adminId") String adminId,@PathVariable("memberId") String memberId, Model model) {
		//會員資料
		MemberStatus ms = new MemberStatus();
		ms.setStatusName("正常");
								
		Creator creator = new Creator();
		creator.setId(1111111);
		creator.setEmail("xxx@ubn.net");
		creator.setUserName("王大明");
		creator.setIdentityName("創作人");
		creator.setLocation("台灣");
		creator.setCreateDate("2011/11/11");
		creator.setMemberStatus(ms);
		
		
		GsiMoney gsiMoney = new GsiMoney();
		gsiMoney.setBalance("300");
		
		Set<GsiMoney> g = new HashSet<GsiMoney>();
		g.add(gsiMoney);
		
		creator.setGsiMoney(g);
		
		GsiBonus gsiBonus = new GsiBonus();
		gsiBonus.setBalance("50");
		Set<GsiBonus> b = new HashSet<GsiBonus>();
		b.add(gsiBonus);
		
		creator.setGsiBonus(b);
				
		//付款資訊
		creator.setUserName("劉為駿");
		creator.setIdentityNO("N111111111");
		creator.setAddress("台北市內湖區內湖捷運站");
		creator.setCellPhone("0999999999");		
		creator.setTel("0422222222");
			
		//銀行資訊
		creator.setAccountName("劉為駿");
		creator.setBankName("中央銀行");
		creator.setBankBranch("台中分行");
		creator.setAccountNO("1234567");
		
		int fans = 20;
		int friends = 100;
		int albums = 5;
		int songs = 50;
		int beOffendSongs = 10;
		int beOffendAlbums = 3;
		int offense = 30;
		
		ArrayList creatorDetail = new ArrayList();
		creatorDetail.add(creator);
		creatorDetail.add(fans);
		creatorDetail.add(friends);
		creatorDetail.add(albums);
		creatorDetail.add(songs);
		creatorDetail.add(beOffendSongs);
		creatorDetail.add(beOffendAlbums);
		creatorDetail.add(offense);
			
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
		return "redirect:/manageCreatorDetail/modify/"+adminId+"/"+memberId+".do";
	}
	
	//創作人被檢舉清單(專輯)
		@RequestMapping("/queryOffenseAlbum")
		public ModelAndView queryOffenseAlbum(String adminId,String memberId, Model model) {						
			System.out.println("queryOffenseAlbum==>");		
			System.out.println("    memberId="+memberId+", adminId="+adminId);
			
			ArrayList a = new ArrayList();
			ArrayList b = new ArrayList();
			ArrayList c = new ArrayList();
			ArrayList d = new ArrayList();
			
			Creator creator = new Creator();
			creator.setId(1111111);
					
			Album a1 = new Album();
			a1.setId(123);
			a1.setName("peace");
			a1.setCreator(creator);
			a1.setStatus("公開");
			int number1 = 12;
			a.add(a1);
			a.add(number1);
			
			Album a2 = new Album();
			a2.setId(456);
			a2.setName("peace");
			a2.setCreator(creator);
			a2.setStatus("隱藏");
			int number2 = 33;
			b.add(a2);
			b.add(number2);
			
			Song s1 = new Song();
			s1.setId(1234);
			s1.setAlbum(a1);
			s1.setName("in your eyes");
			int number3 = 12;
			c.add(s1);
			c.add(number3);
			
			Song s2 = new Song();
			s2.setId(4567);
			s2.setAlbum(a2);
			s2.setName("happy");
			int number4 = 33;
			d.add(s2);
			d.add(number4);
			
			ArrayList offenseAlbums = new ArrayList();
			offenseAlbums.add(a);
			offenseAlbums.add(b);
			ArrayList offenseSongs = new ArrayList();
			offenseSongs.add(c);
			offenseSongs.add(d);
			
			model.addAttribute("offenseAlbums", offenseAlbums);
			model.addAttribute("offenseSongs", offenseSongs);
			return new ModelAndView("queryOffenseAlbum");
		}	
		
		//創作人被檢舉清單(歌曲)
		@RequestMapping("/queryOffenseSong")
		public ModelAndView queryOffenseSong(String adminId,String memberId, Model model) {						
			System.out.println("queryOffenseSong==>");		
			System.out.println("    memberId="+memberId+", adminId="+adminId);
			
			ArrayList a = new ArrayList();
			ArrayList b = new ArrayList();
			ArrayList c = new ArrayList();
			ArrayList d = new ArrayList();
			
			Creator creator = new Creator();
			creator.setId(1111111);
					
			Album a1 = new Album();
			a1.setId(123);
			a1.setName("peace");
			a1.setCreator(creator);
			a1.setStatus("公開");
			int number1 = 12;
			a.add(a1);
			a.add(number1);
			
			Album a2 = new Album();
			a2.setId(456);
			a2.setName("peace");
			a2.setCreator(creator);
			a2.setStatus("隱藏");
			int number2 = 33;
			b.add(a2);
			b.add(number2);
			
			Song s1 = new Song();
			s1.setId(1234);
			s1.setAlbum(a1);
			s1.setName("in your eyes");
			int number3 = 12;
			c.add(s1);
			c.add(number3);
			
			Song s2 = new Song();
			s2.setId(4567);
			s2.setAlbum(a2);
			s2.setName("happy");
			int number4 = 33;
			d.add(s2);
			d.add(number4);
			
			ArrayList offenseAlbums = new ArrayList();
			offenseAlbums.add(a);
			offenseAlbums.add(b);
			ArrayList offenseSongs = new ArrayList();
			offenseSongs.add(c);
			offenseSongs.add(d);
			
			model.addAttribute("offenseAlbums", offenseAlbums);
			model.addAttribute("offenseSongs", offenseSongs);		
			return new ModelAndView("queryOffenseSong");
		}
		
		//查詢儲值紀錄
				@RequestMapping("/queryPrePayRecord")
				public ModelAndView queryPrePayRecord() {
					ModelAndView mav = new ModelAndView("queryPrePayRecord");
					Order order = new Order();
					long id = 89123456;
					order.setId(id);
					order.setPurchaseDate("2011/03/31");
					order.setPayMethod("信用卡付款");
					order.setPayDate("2011/04/01 12:33:46");
					order.setBillStatus("已寄送");
					GsiMoney gsiMoney = new GsiMoney();
					gsiMoney.setPrepaid("300");
					gsiMoney.setMemo("test");
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setOrder(order);
					//orderDetail.setGsiMoney(gsiMoney);
					
					Order order2 = new Order();
					long id2 = 63253456;
					order2.setId(id2);
					order2.setPurchaseDate("2011/06/28");
					order2.setPayMethod("轉帳付款");
					order2.setPayDate("2011/06/29 12:33:46");
					order2.setBillStatus("未寄送");
					GsiMoney gsiMoney2 = new GsiMoney();
					gsiMoney2.setPrepaid("200");
					gsiMoney2.setMemo("test");
					OrderDetail orderDetail2 = new OrderDetail();
					orderDetail2.setOrder(order2);
					//orderDetail2.setGsiMoney(gsiMoney2);
					mav.addObject("orderDetail",orderDetail);
					mav.addObject("orderDetail2",orderDetail2);
					return mav;
				}

				//查詢訂單詳細紀錄(訂單編號的連結)
				@RequestMapping("/queryPayDetailRecord")  
				public ModelAndView queryPayDetailRecord() {
					ModelAndView mav = new ModelAndView("queryPayDetailRecord");
					String tPrice="350";
					Order order = new Order();
					long id = 89123456;
					order.setId(id);
					order.setPurchaseDate("2011/03/31");
					SongPrice songPrice = new SongPrice();
					songPrice.setPrice("20");
					Song song = new Song();
					song.setName("早安晨之美");
					song.setSongPrice(songPrice);
					SongPrice songPrice2 = new SongPrice();
					songPrice2.setPrice("25");
					Song song2 = new Song();
					song2.setName("你");
					song2.setSongPrice(songPrice2);
					Album album = new Album();
					album.setName("盧廣仲");
					Album album2 = new Album();
					album2.setName("蕭敬騰");
					ProductionCategory productionCategory = new ProductionCategory();
					//productionCategory.setSong(song);
					//productionCategory.setAlbum(album);
					ProductionCategory productionCategory2 = new ProductionCategory();
					//productionCategory2.setSong(song2);
					//productionCategory2.setAlbum(album2);
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setProductionCategory(productionCategory);
					orderDetail.setOrder(order);
					OrderDetail orderDetail2 = new OrderDetail();
					orderDetail2.setProductionCategory(productionCategory2);
					OrderDetail[] od = {orderDetail,orderDetail2};
					mav.addObject("orderDetail",od);
					mav.addObject("tPrice",tPrice);
					return mav;
				}
					
				//查詢贈送點數記錄
				@RequestMapping("/queryRewardRecord")
				public ModelAndView queryRewardRecord() {
					ModelAndView mav = new ModelAndView("queryRewardRecord");
					String waitOnBonus = "0";
					String OnBonus = "300";
					String offBonus = "0";
					Order order = new Order();
					long id = 89123456;
					order.setId(id);
					GsiBonus gsiBonus = new GsiBonus();
					gsiBonus.setOnDate("2011/03/31");
					gsiBonus.setOffDate("2011/10/31");
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setOrder(order);
					//orderDetail.setGsiBonus(gsiBonus);
					mav.addObject("orderDetail",orderDetail);
					mav.addObject("waitOnBonus",waitOnBonus);
					mav.addObject("OnBonus",OnBonus);
					mav.addObject("offBonus",offBonus);
					return mav;
				}
}

