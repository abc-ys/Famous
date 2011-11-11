package com.ubn.befamous.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.Ad;
import com.ubn.befamous.entity.AdType;
import com.ubn.befamous.entity.Admin;
import com.ubn.befamous.entity.GeneralMember;
import com.ubn.befamous.entity.GsiBonus;
import com.ubn.befamous.entity.GsiMoney;
import com.ubn.befamous.entity.MemberStatus;

@Controller
@SessionAttributes
public class BannerController {
	//Kevin
	String account = "kevin@ubn.net";
	//導新增Banner
	@RequestMapping(value = "/forwardAddBanner")
	public ModelAndView forwardAddBanner() {
		ModelAndView mav = new ModelAndView("forwardAddBanner");
		//取得創做人ID from session 
		
		AdType adType = new AdType();
		adType.setAdTypeName("我");
		AdType[] arAdType = {adType};
		mav.addObject("memberID", account);
		mav.addObject("arAdType", arAdType);
		return mav;
		
	}
	
	
	//處理上傳
	@RequestMapping(value = "/saveBanner")
	public ModelAndView uploadBanner(HttpServletRequest request, Ad ad) {
		
		System.out.println("活動內容0===>"+ad.getWebsite());
		System.out.println("活動內容===>"+ad.getActivityContent());
		int buffersize = 4096;
		int SizeMax = 20 * 1024 * 1024;// 1Mbyte最大檔案大小
		String allowedFileTypes = "gif|jpg|png";
		//設定上傳的目錄
		
		String saveDirectory = "D://PDFUpload//";
		//setUploadDir("/ebook/edit_tool/");
		 File imageFolder = new File(saveDirectory);
		    if (!imageFolder.exists()) imageFolder.mkdir();
        // 建立一個以disk-base的檔案物件
        DiskFileItemFactory factory = new DiskFileItemFactory();
 
        // 初始化內容
        // 傳送所用的buffer空間
        factory.setSizeThreshold(buffersize);
        // The directory in which temporary files will be located.
 
        factory.setRepository(new File(saveDirectory));
        System.out.println("factory==>"+factory);
        // 建立一個檔案上傳的物件
        ServletFileUpload upload = new ServletFileUpload(factory);
        System.out.println("upload==>"+upload);
        // 最大檔案大小
        upload.setSizeMax(SizeMax * 20);
        // 每一個Fileitem代表一個form上傳的物件內容ex input type="text"
        
     try{   
        List items = null; // 會產生 FileUploadException
        // 把資料從request取出
        try {
			items = upload.parseRequest(request);
			 System.out.println("items==>"+items);
		} catch (FileUploadException e) {

			e.printStackTrace();
		} 
		
	   
		
		
        Iterator iter = items.iterator();
       // while (iter.hasNext()) {// 先把所有參數取得而不先write to file
       
        while (iter.hasNext()) {
        	 FileItem item = (FileItem) iter.next();
        	if (item.isFormField()) {
				// Process a regular form field	
				//processFormField(item);		
				String name = item.getFieldName();
				String value = item.getString("UTF-8");
				System.out.println(name + "=" + value + "<br />");
			} else {
				String fileName = item.getName();
			}
        
         String fileName = item.getName();
         System.out.println("fileName==>"+fileName);
         
         if (fileName != null && !"".equals(fileName)) {
	        if (item.getSize() > 0) {
	        	if ((fileName.lastIndexOf("\\")) != -1)
	            	fileName = fileName.substring(fileName.lastIndexOf("\\")+1,fileName.length());
	            
	        	String extension = FilenameUtils.getExtension(fileName);
				if (allowedFileTypes.indexOf(extension.toLowerCase()) != -1) {
					File uploadedFile = new File(saveDirectory,	fileName);						
				    item.write(uploadedFile);
				} else {
					System.out.println("上傳的檔案不能是" + extension + "<br />");
				}
	              File uploadedFile = null;
				
	        }
         }
        }
     }catch(Exception e){
    	 
     }
		return new ModelAndView("saveBanner");
		
	}
	
	//
	@RequestMapping(value = "/modifyMemberId")
	public ModelAndView modifyMemberId(String memberId,Model model) {
		
		model.addAttribute("memberId",memberId);
		return new ModelAndView("modifyMemberId");
	}
	
	
	//導新增Banner
	@RequestMapping(value = "/modifyAddBanner")
	public ModelAndView modifyAddBanner(String newMemberId,Ad ad) {
		
		System.out.println("活動內容===>"+ad.getActivityContent());
		
		ModelAndView mav = new ModelAndView("forwardAddBanner");
		//取得創做人ID from session 
		System.out.println("newMemberId==>"+newMemberId);
		AdType adType = new AdType();
		adType.setAdTypeName("我");
		AdType[] arAdType = {adType};
		account = newMemberId;
		mav.addObject("memberID", newMemberId);
		mav.addObject("arAdType", arAdType);
		return mav;
		
	}
	
	
	//Lucy
	//管理者新增廣告Banner
		@RequestMapping("/addAdBanner")
		public ModelAndView addAdBanner(Model model) {
			System.out.println("addAdBanner==>");
			Admin admin = new Admin();
			admin.setAdminId(1111);
			model.addAttribute("admin", admin);
			return new ModelAndView("addAdBanner");		
		}
		//儲存管理者新增的廣告Banner
		@RequestMapping("/saveAdBanner")
		public String saveAdBanner(HttpServletRequest request) throws Exception {
			System.out.println("saveAdBanner==>");	
			
			String bannerType = "";
			String activityName = "";
			String activityStartDate = "";
			String activityEndDate = "";
			String website = "";
			String onDate = "";
			String offDate = "";
			String createDate = "";
			String creator = "";
			
			int yourMaxMemorySize = 500 * 500* 1024;
			File yourTempDirectory = new File("/tmp");
			int yourMaxRequestSize = 500 * 500* 1024;
			boolean writeToFile = true;
			String allowedFileTypes = ".gif .jpg .png";
			String saveDirectory = "D:/UBN_Area/ImageWeb/WebContent/image/memberPicture";

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
						if(name.equals("bannerType")){
							bannerType  = value;
						}else if(name.equals("activityName")){
							activityName = value;
						}else if(name.equals("activityStartDate")){
							activityStartDate = value;
						}else if(name.equals("activityEndDate")){
							activityEndDate = value;
						}else if(name.equals("website")){
							website = value;
						}else if(name.equals("onDate")){
							onDate = value;
						}else if(name.equals("offDate")){
							offDate = value;
						}else if(name.equals("createDate")){
							createDate = value;
						}else if(name.equals("creator")){
							creator = value;
						}
					} else {
						// Process a file upload
						//processUploadedFile(item);	
						String fieldName = item.getFieldName();
						String fileName = item.getName();
						String contentType = item.getContentType();
						boolean isInMemory = item.isInMemory();
						long sizeInBytes = item.getSize();
						
						System.out.println("fieldName=" + fieldName + "<br />");
						System.out.println("fileName=" + fileName + "<br />");
						System.out.println("contentType=" + contentType + "<br />");
						System.out.println("isInMemory=" + isInMemory + "<br />");
						System.out.println("sizeInBytes=" + sizeInBytes + "<br />");
						
						if (fileName != null && !"".equals(fileName)) {
							if (writeToFile) {
								// 副檔名
								 String formatName = fileName.substring(fileName.length() - 4,fileName.length());
							   // fileName = (bannerType1 + formatName).toLowerCase();
							      
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
			System.out.println(" creator="+creator+", bannerType="+bannerType+", activityName="+activityName+", activityStartDate="+activityStartDate+", activityEndDate="+activityEndDate+", website="+website+", onDate="+onDate+", offDate="+offDate+", createDate="+createDate);
			return "redirect:/addAdBanner.do";
		}
			
		//管理者查詢廣告內容
		@RequestMapping("/queryAdBanner")
		public ModelAndView queryAdBanner(String adminId, String bannerType, String activityName, String onStartDate, String onEndDate, String offStartDate, String offEndDate, Model model) {
			System.out.println("queryAdBanner==>");	
			System.out.println("  adminId="+adminId+", bannerType="+bannerType+", activityName="+activityName+", onStartDate="+onStartDate+", onEndDate="+onEndDate+", offStartDate="+offStartDate+", offEndDate="+offEndDate);	
			
			Admin admin = new Admin();
			admin.setAdminId(1111);
			model.addAttribute("admin", admin);
			
			AdType adType = new AdType();
			adType.setAdTypeName("主banner");
			
			AdType adType2 = new AdType();
			adType2.setAdTypeName("創作人主打banner");
			
			Ad a = new Ad();
			a.setAdId(1111);
			a.setActivityName("萬聖節");
			a.setActivityStartDate("2011-11-10");
			a.setActivityEndDate("2011-11-20");
			a.setAdminCreator("管理者一號");
			a.setOnDate("2011-11-10");
			a.setOffDate("2011-11-20");
			a.setOnStatus("刊登中");
			a.setAdType(adType);
			int hits=33;
			ArrayList ad = new ArrayList();
			ad.add(a);
			ad.add(hits);		
			
			Ad a2 = new Ad();
			a2.setAdId(2222);
			a2.setActivityName("聖誕節");
			a2.setActivityStartDate("2011-11-11");
			a2.setActivityEndDate("2011-11-21");
			a2.setAdminCreator("管理者二號");
			a2.setOnDate("2011-11-11");
			a2.setOffDate("2011-11-21");
			a2.setOnStatus("未刊登");
			a2.setAdType(adType2);
			int hits2=200;
			ArrayList ad2 = new ArrayList();
			ad2.add(a2);
			ad2.add(hits2);
			
			ArrayList adList = new ArrayList();
			adList.add(ad);
			adList.add(ad2);
			
			model.addAttribute("adList", adList);
			return new ModelAndView("queryAdBanner");		
		}
		
		//管理者查詢詳細廣告內容
		@RequestMapping("/queryAdDetail/{type}/{adminId}/{adId}")
		public ModelAndView queryAdDetail(HttpServletRequest request, @PathVariable("type") String type,@PathVariable("adminId") String adminId,@PathVariable("adId") String adId, Model model) {
		
			System.out.println("queryAdDetail==>");		
			System.out.println("    type="+type+", adminId="+adminId+", adId="+adId);
			
			AdType adType = new AdType();
			adType.setAdTypeName("主banner");
					
			Ad a = new Ad();
			a.setAdId(1111);
			a.setActivityName("萬聖節");
			a.setPicture("image/memberPicture/111111.jpg");
			a.setActivityStartDate("2011-11-10");
			a.setActivityEndDate("2011-11-20");
			a.setAdminCreator("管理者一號");
			a.setCreateDate("2011-11-09");
			a.setOnDate("2011-11-10");
			a.setOffDate("2011-11-20");
			a.setOnStatus("刊登中");
			a.setWebsite("www.google.com");
			a.setAdType(adType);
			int hits=33;
			ArrayList ad = new ArrayList();
			ad.add(a);
			ad.add(hits);		
			
			model.addAttribute("adDetail",ad);
			model.addAttribute("admin",adminId);
			
			ModelAndView mav = new ModelAndView();		
			if(type.equals("get")){
				mav.setViewName("queryAdDetail");
			}else{
				mav.setViewName("modifyAdDetail");
			}
			
			System.out.println("Image==="+request.getSession().getServletContext().getInitParameter("ImageWeb"));
			return mav;
						
		}
		//儲存管理者修改的廣告內容
		@RequestMapping("/saveAdDetail")
		public String saveAdDetail(String adminId,String adId,String bannerType,String activityName,String activityStartDate,String activityEndDate,String onDate,String offDate, Model model) {		
			System.out.println("saveAdDetail==>");		
			System.out.println("  adminId="+adminId+", adId="+adId+", bannerType="+bannerType+", activityName="+activityName+", onDate="+onDate+", offDate="+offDate+", activityStartDate="+activityStartDate+", activityEndDate="+activityEndDate);	
			return "redirect:/queryAdDetail/modify/"+adminId+"/"+adId+".do";
		}
		
		//管理者查詢上傳廣告內容
		@RequestMapping("/queryUploadAdBanner")
		public ModelAndView queryUploadAdBanner(String adminId, String activityStartDate,  String activityEndDate, String identity, String albumNumber, Model model) {
			System.out.println("queryUploadAdBanner==>");
			System.out.println("	adminId="+adminId+", activityStartDate="+activityStartDate+", activityEndDate="+activityEndDate+", identity="+identity+", albumNumber="+albumNumber);
			
			Admin admin = new Admin();
			admin.setAdminId(1111);
			model.addAttribute("admin", admin);
			
			AdType adType = new AdType();
			adType.setAdTypeName("主banner");
			
			AdType adType2 = new AdType();
			adType2.setAdTypeName("創作人主打banner");
			
			Member memberCreator = new Member();
			memberCreator.setEmail("aaa@gmail.com");
			memberCreator.setIdentityName("一般會員");
			memberCreator.setMemberId(11111);
			
			Member memberCreator2 = new Member();
			memberCreator2.setEmail("bbb@gmail.com");
			memberCreator2.setIdentityName("創作人");
			memberCreator2.setMemberId(22222);
			
			Ad a = new Ad();
			a.setAdId(11);
			a.setActivityName("萬聖節");
			a.setActivityStartDate("2011-11-10");
			a.setActivityEndDate("2011-11-20");
			a.setMemberCreator(memberCreator);		
			a.setCheckStatus("尚未審核");
			a.setAdType(adType);
			int albums=10;
			ArrayList ad = new ArrayList();
			ad.add(a);
			ad.add(albums);
			
			Ad a2 = new Ad();
			a2.setAdId(22);
			a2.setActivityName("聖誕節");
			a2.setActivityStartDate("2011-11-11");
			a2.setActivityEndDate("2011-11-21");
			a2.setMemberCreator(memberCreator2);
			a2.setCheckStatus("審核成功");
			a2.setAdType(adType2);
			int albums2=10;
			ArrayList ad2 = new ArrayList();
			ad2.add(a2);
			ad2.add(albums2);
			
			ArrayList adList = new ArrayList();
			adList.add(ad);
			adList.add(ad2);
			
			model.addAttribute("adList", adList);		
			return new ModelAndView("queryUploadAdBanner");		
		}
		
		//管理者查看上傳廣告詳細內容
		@RequestMapping("/queryUploadAdDetail/{adminId}/{adId}")
		public ModelAndView queryUploadAdDetail(@PathVariable("adminId") String adminId,@PathVariable("adId")String adId, Model model) {
			System.out.println("queryUploadAdDetail==>");
			System.out.println("	adminId="+adminId+", adId="+adId);
				
			Admin admin = new Admin();
			admin.setAdminId(1111);
			model.addAttribute("admin", admin);
				
			AdType adType = new AdType();
			adType.setAdTypeName("主banner");
			
			Member memberCreator = new Member();
			memberCreator.setEmail("aaa@gmail.com");
			memberCreator.setIdentityName("一般會員");
			memberCreator.setMemberId(11111);
			
			Ad a = new Ad();
			a.setAdId(11);
			a.setActivityName("萬聖節");
			a.setActivityStartDate("2011-11-10");
			a.setActivityEndDate("2011-11-20");
			a.setPicture("image/memberPicture/111111.jpg");
			a.setActivityContent("如果你是iPad用戶，可能聽過社交雜誌....");
			a.setWebsite("www.google.com");
			a.setCreateDate("2011-11-09");
			a.setMemberCreator(memberCreator);		
			a.setCheckStatus("尚未審核");
			a.setAdType(adType);
			a.setNote("如果你是iPad用戶，可能聽過");
			int albums=10;
			ArrayList adDetail = new ArrayList();
			adDetail.add(a);
			adDetail.add(albums);
			
			model.addAttribute("adDetail", adDetail);
			return new ModelAndView("queryUploadAdDetail");		
		}
		//管理者填寫審核失敗理由 (window open)
		@RequestMapping("/addCheckReason/{adminId}/{adId}")
		public ModelAndView addCheckReason(@PathVariable("adminId") String adminId,@PathVariable("adId") String adId, Model model) {
				
			System.out.println("addCheckReason==>");		
			System.out.println("    adId="+adId+", adminId="+adminId);
			model.addAttribute("adId",adId);
			model.addAttribute("admin",adminId);
			return new ModelAndView("addCheckReason");
		}
		//管理者填寫審核失敗理由 (window open)
		@RequestMapping("/saveCheckReason")
		public ModelAndView saveCheckReason(String adminId,String adId) {
					
			System.out.println("saveCheckReason==>");		
			System.out.println("    adId="+adId+", adminId="+adminId);
			return new ModelAndView("saveCheckReason");
		}
		
		//管理者發佈上傳廣告
		@RequestMapping("/publishAd")
		public String publishAd(String adId, String adminId, String checkStatus) {
			System.out.println("publishAd==>");
			System.out.println("	adminId="+adminId+", adId="+adId+", checkStatus="+checkStatus);
			return "redirect:addAdBanner.do";
		}	
}
