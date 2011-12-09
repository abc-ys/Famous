package com.ubn.befamous.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.ubn.befamous.entity.Member;
import com.ubn.befamous.entity.News;
import com.ubn.befamous.service.PersonService;

@Controller
@SessionAttributes
public class NewsController {
	
	@Autowired
	private PersonService personService;
	
	//最新消息的瀏覽最新消息細節
		@RequestMapping("/newsDetail")
		public ModelAndView queryNewsDetail(long newsId, Model model)
		{
			System.out.println("queryNewsDetail==>");			
			News news = this.personService.queryNewsDetail(newsId);
			model.addAttribute("news",news);
			return new ModelAndView("queryNewsDetail");
		}
			
			//最新消息-創作人新增最新消息
			@RequestMapping("/addNews")
			public ModelAndView addNews(long userId)
			{
				System.out.println("addNews==>");
				return new ModelAndView("addNews", "member",this.personService.queryMember(userId));
			}
			
			//最新消息-儲存創作人新增的最新消息
			@RequestMapping("/saveNews")
			public String saveNews(long userId,String newsName,String newsSouce, String content)
			{
				System.out.println("saveNews==>");
				System.out.println("    userId="+userId+", newsName="+newsName+", newsSouce="+newsSouce+", content="+content);
				String status = "2"; // 2表示為草稿
				this.personService.saveNews(userId, newsName, newsSouce, content, status);
				return "redirect:addNews.do?userId="+userId;
			}
			
			//最新消息-發佈創作人新增的最新消息
				@RequestMapping("/publishNews")
				public String publishNews(long userId,String newsName,String newsSouce, String content)
				{
					System.out.println("publishNews==>");
					System.out.println("    userId="+userId+", newsName="+newsName+", newsSouce="+newsSouce+", content="+content);
					String status = "1"; // 1表示為開放
					this.personService.saveNews(userId, newsName, newsSouce, content, status);
					return "redirect:addNews.do?userId="+userId;
				}
			
			//最新消息-預覽創作人新增的最新消息
			/*@RequestMapping("/previewNews")
			public ModelAndView previewNews(String newsName,String newsSouce, String content, Model model)
			{			
				return new ModelAndView("previewNews");
			}*/
			
			//最新消息-查詢最新消息(起始查詢)
			@RequestMapping("/queryNews")
			public ModelAndView queryNews(long userId, Model model)
			{
				String onStatus = "2";
				model.addAttribute("userId", userId);
				model.addAttribute("onStatus", onStatus);
				model.addAttribute("newsList", this.personService.queryNews(userId, onStatus));
				return new ModelAndView("queryNews");
			
			}
			
			//最新消息-查詢最新消息(根據查詢條件)
			@RequestMapping("/queryNewsData")
			public ModelAndView queryNewsData(long userId, String onStatus, Model model)
			{
				model.addAttribute("userId", userId);
				model.addAttribute("onStatus", onStatus);
				model.addAttribute("newsList", this.personService.queryNews(userId, onStatus));			
				return new ModelAndView("queryNewsData");
			}
			
			//最新消息-顯示創作人的最新消息 (window open)
			@RequestMapping("/showNews")
			public ModelAndView showNews(long newsId, Model model)
			{
				System.out.println("showNews==>");
				System.out.println("    newsId="+newsId);				
				News news = this.personService.queryNewsDetail(newsId);
				model.addAttribute("news",news);
				return new ModelAndView("showNews");
			}
			
			//最新消息-編輯創作人的最新消息 (window open)
			@RequestMapping("/modifyNews")
			public ModelAndView modifyNews(long newsId, Model model)
			{
				System.out.println("modifyNews==>");
				System.out.println("    newsId="+newsId);				
				News news = this.personService.queryNewsDetail(newsId);
				model.addAttribute("news",news);
				return new ModelAndView("modifyNews");
			}
			
			//最新消息-更新創作人修改的內容(window close)
			@RequestMapping("/updateNews")
			public ModelAndView updateNews(long newsId,String newsName,String newsSouce, String content)
			{
				System.out.println("updateNews==>");
				System.out.println("    newsId="+newsId+", newsName="+newsName+", newsSouce="+newsSouce+", content="+content);
				String onStatus = "2"; //2為儲存為草稿
				this.personService.updateNews(newsId, newsName, newsSouce, content, onStatus);
				return new ModelAndView("saveUpdateNews");
			}
				
			//最新消息-更新發佈創作人修改的內容(window close)
			@RequestMapping("/updatePublishNews")
			public ModelAndView updatePublishNews(long newsId,String newsName,String newsSouce, String content)
			{
				System.out.println("updatePublishNews==>");
				System.out.println("    newsId="+newsId+", newsName="+newsName+", newsSouce="+newsSouce+", content="+content);
				String onStatus = "1"; //1為開放
				this.personService.updateNews(newsId, newsName, newsSouce, content, onStatus);
				return new ModelAndView("saveUpdateNews");
			}
			//最新消息-儲存刪除的消息
			@RequestMapping("/delNews")
			public String delNews(long userId,String[] delList, String onStatus)
			{
				System.out.println("delNews==>");
				System.out.println("    userId="+userId+", delList="+delList+", onStatus="+onStatus);			
				for (String news:delList){
					this.personService.deleteNews(Long.parseLong(news));
				}
				return "redirect:queryNewsData.do?userId="+userId+"&onStatus="+onStatus;
			}

			//怡秀 write - 1110
			//管理者新增最新消息-頁面
			@RequestMapping("/addManagerNews")
			public ModelAndView addmanagernews(long adminId, Model model){					
				ModelAndView mav = new ModelAndView("addManagerNews");
				System.out.println("addmanagernews==>");
				model.addAttribute("adminId", adminId);				
				return mav;
			}
					
			//管理者新增最新消息-儲存
			@RequestMapping("/saveCNews")
			public String savecnews(HttpServletRequest request) throws Exception {
				long adminId= 0;
				String newsCategory = "";
				String newsName = "";
				String picture = "";
				String newsSouce = "";
				String onDate = "";
				String content = "";
				String value = "";
				String fileName = "";
				String onStatus = "";
				String tag="";
				int yourMaxMemorySize = 500 * 500* 1024;
				File yourTempDirectory = new File("/tmp");
				int yourMaxRequestSize = 500 * 500* 1024;
				boolean writeToFile = true;
				String allowedFileTypes = ".jpg .png .gif .jpeg";

				String saveDirectory = "D:/gitTest/ImageWeb/WebContent/image";  //存放的路徑

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
							String name2 = item.getFieldName();
							value = item.getString("UTF-8");
							System.out.println(name2 + "=" + value + "<br />");
							if(name2.equals("newsCategory")){newsCategory=value;}
							if(name2.equals("newsName")){newsName=value;}
							if(name2.equals("adminId")){adminId=Long.parseLong(value);}
							if(name2.equals("newsSouce")){newsSouce=value;}
							if(name2.equals("onDate")){onDate=value;}
							if(name2.equals("content")){content=value;}
						} else {
							// Process a file upload
							//processUploadedFile(item);	
							String fieldName = item.getFieldName();
							fileName = item.getName();
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
								/*
								String formatName = fileName.substring(fileName.length() - 4,fileName.length());
							    fileName = (albumID + formatName).toLowerCase();*/
								fileName = FilenameUtils.getName(fileName);
								tag="y";
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
				onStatus = "1"; //刊登中
				if(tag.equals("y")){
					picture = "image/"+fileName;
				}
				this.personService.saveManagerNews(adminId, newsCategory, newsName, picture, newsSouce, onDate, content, onStatus);
				return "redirect:addManagerNews.do?adminId="+adminId;
			}
					
			//管理者查詢最新消息-查詢(左邊tiles進入)
			@RequestMapping("/queryManagerNews")
			public ModelAndView querycnews(long adminId){
				ModelAndView mav = new ModelAndView("queryManagerNews");
				mav.addObject("adminId", adminId);
				mav.addObject("newsList", this.personService.queryFirstNewsList());
				return mav;
			}
					
			//管理者查詢最新消息-查詢(根據查詢條件)
			@RequestMapping("/queryManagerNewsData")
			public ModelAndView queryManagerNewsData(long adminId, String newsCategory, String newsName, String MOPEND, String MCLOSED, String onStatus, String newsSource){
				ModelAndView mav = new ModelAndView("queryManagerNewsData");
				mav.addObject("adminId", adminId);
				mav.addObject("newsCategory", newsCategory);
				mav.addObject("newsName", newsName);
				mav.addObject("MOPEND", MOPEND);
				mav.addObject("MCLOSED", MCLOSED);
				mav.addObject("onStatus", onStatus);
				mav.addObject("newsSource", newsSource);
				mav.addObject("newsList", this.personService.queryNewsList(newsCategory, newsName, MOPEND, MCLOSED, onStatus, newsSource));
				return mav;
			}
					
			//管理者查詢最新消息-刪除
			@RequestMapping("/deleteData")
			public String deletedata(long adminId,String[] delList){					
				System.out.println("delNews==>");		
				for (String news:delList){
					this.personService.deleteManageNews(adminId, Long.parseLong(news));
				}
			return "redirect:queryManagerNews.do?adminId="+adminId;
			}
			
			
			//管理者查詢最新消息-編輯與顯示管理者的最新消息
			@RequestMapping("/editNewsData/{type}")
			public ModelAndView editnewsdata(@PathVariable("type") String type,long newsID,long adminId){
				ModelAndView mav;
				News news = this.personService.queryNewsDetail(newsID);
				if(type.equals("edit")){
					mav = new ModelAndView("editNewsData");
					mav.addObject("adminId", adminId);
				}else{
					mav = new ModelAndView("showNewsData");
				}			
				mav.addObject("news", news);
				return mav;
			}
					
			//管理者查詢最新消息-編輯頁-儲存
			@RequestMapping("/saveEditedNews")
			public String saveeditednews(HttpServletRequest request) throws Exception 
			{
				String tag="";
				String newsCategory = "";
				String newsName = "";
				String picture = "";
				String newsSouce = "";
				String onDate = "";
				String content = "";
				String value = "";
				String fileName = "";
				long adminId = 0;
				long newsId = 0;
				int yourMaxMemorySize = 500 * 500* 1024;
				File yourTempDirectory = new File("/tmp");
				int yourMaxRequestSize = 500 * 500* 1024;
				boolean writeToFile = true;
				String allowedFileTypes = ".jpg .png .gif .jpeg";

				String saveDirectory = "D:/UBN_Area/ImageWeb/WebContent/image";  //存放的路徑

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
							String name2 = item.getFieldName();
							value = item.getString("UTF-8");
							System.out.println(name2 + "=" + value + "<br />");
							if(name2.equals("newsCategory")){newsCategory=value;}
							if(name2.equals("newsName")){newsName=value;}
							if(name2.equals("adminId")){adminId=Long.parseLong(value);}
							if(name2.equals("newsId")){newsId=Long.parseLong(value);}
							if(name2.equals("newsSouce")){newsSouce=value;}
							if(name2.equals("onDate")){onDate=value;}
							if(name2.equals("content")){content=value;}
						} else {
							// Process a file upload
							//processUploadedFile(item);	
							String fieldName = item.getFieldName();
							fileName = item.getName();
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
									/*
									String formatName = fileName.substring(fileName.length() - 4,fileName.length());
								    fileName = (albumID + formatName).toLowerCase();*/
									fileName = FilenameUtils.getName(fileName);
									tag="y";
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
				News news = this.personService.queryNewsDetail(newsId);
				if(tag.equals("y")){
					picture = "image/"+fileName;
				}else{
					picture = news.getPicture();
				}
				this.personService.updateManagerNews(adminId, newsId, newsCategory, newsName, picture, newsSouce, onDate, content);
				return "redirect:queryManagerNews.do?adminId="+adminId;
			}
	}
