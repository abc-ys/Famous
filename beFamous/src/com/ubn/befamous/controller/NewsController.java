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
		public ModelAndView queryNewsDetail(String memberID, String newsID, Model model)
		{
			System.out.println("queryNewsDetail==>");		
			System.out.println("    memberID="+memberID+", newsID="+newsID);
			News news = new News();
			news.setCreateDate("2011/10/10");
			news.setPicture("http://befamous.gsi-media.com/ImageWeb/image/111111.jpg");
			news.setNewsName("2011設計大展");
			news.setContent("2011設計大展即將結束");
			news.setNewsSouce("設計展");			
			model.addAttribute("news",news);
			return new ModelAndView("queryNewsDetail");
		}
		
		//最新消息的創作人新增最新消息(傳入參數userID要新增)
		@RequestMapping("/addNews")
		public ModelAndView addNews()
		{
			System.out.println("addNews==>");
			long userID = 1;
			return new ModelAndView("addNews", "member",this.personService.queryMember(userID));
		}
		
		//儲存創作人新增的最新消息
		@RequestMapping("/saveNews")
		public String saveNews(long userID,String newsName,String newsSouce, String content)
		{
			System.out.println("saveNews==>");
			System.out.println("    userID="+userID+", newsName="+newsName+", newsSouce="+newsSouce+", content="+content);
			String status = "2"; // 2表示為草稿
			this.personService.saveNews(userID, newsName, newsSouce, content, status);
			return "redirect:addNews.do";
		}
		
		//發佈創作人新增的最新消息
			@RequestMapping("/publishNews")
			public String publishNews(long userID,String newsName,String newsSouce, String content)
			{
				System.out.println("publishNews==>");
				System.out.println("    userID="+userID+", newsName="+newsName+", newsSouce="+newsSouce+", content="+content);
				String status = "1"; // 1表示為開放
				this.personService.saveNews(userID, newsName, newsSouce, content, status);
				return "redirect:addNews.do";
			}
		
		//預覽創作人新增的最新消息
		/*@RequestMapping("/previewNews")
		public ModelAndView previewNews(String newsName,String newsSouce, String content, Model model)
		{
			System.out.println("previewNews==>");
			System.out.println("    newsName="+newsName+", newsSouce="+newsSouce+", content="+content);
			News news = new News();
			news.setCreateDate("2011/10/10");
			news.setPicture("http://befamous.gsi-media.com/ImageWeb/image/111111.jpg");
			news.setNewsName(newsName);
			news.setContent(content);
			news.setNewsSouce(newsSouce);			
			model.addAttribute("news",news);
			
			return new ModelAndView("previewNews");
		}*/
		
		//最新消息的查詢最新消息(左邊tiles進入)(傳入參數userID要新增)
		@RequestMapping("/queryNews")
		public ModelAndView queryNews(Model model)
		{
			long userID = 1;
			String onStatus = "1";
			model.addAttribute("userID", userID);
			model.addAttribute("onStatus", onStatus);
			model.addAttribute("newsList", this.personService.queryNews(userID, onStatus));
			return new ModelAndView("queryNews");
		
		}
		
		//最新消息的查詢最新消息(根據查詢條件)(傳入參數userID要新增)
		@RequestMapping("/queryNewsData")
		public ModelAndView queryNewsData(long userID, String onStatus, Model model)
		{
			model.addAttribute("userID", userID);
			model.addAttribute("onStatus", onStatus);
			model.addAttribute("newsList", this.personService.queryNews(userID, onStatus));			
			return new ModelAndView("queryNewsData");
		}
		
		//編輯創作人的最新消息 (window open)
		@RequestMapping("/modifyNews")
		public ModelAndView modifyNews(long userID,long newsID, Model model)
		{
			System.out.println("modifyNews==>");
			System.out.println("    userID="+userID+", newsID="+newsID);				
			News news = this.personService.queryNewsDetail(newsID);
			model.addAttribute("news",news);
			return new ModelAndView("modifyNews");
		}
		
		//更新創作人修改的內容(window close)
		@RequestMapping("/updateNews")
		public ModelAndView updateNews(long newsID,String newsName,String newsSouce, String content)
		{
			System.out.println("updateNews==>");
			System.out.println("    newsID="+newsID+", newsName="+newsName+", newsSouce="+newsSouce+", content="+content);
			String onStatus = "2"; //2為儲存為草稿
			this.personService.updateNews(newsID, newsName, newsSouce, content, onStatus);
			return new ModelAndView("saveUpdateNews");
		}
			
		//更新發佈創作人修改的內容(window close)
		@RequestMapping("/updatePublishNews")
		public ModelAndView updatePublishNews(long newsID,String newsName,String newsSouce, String content)
		{
			System.out.println("updatePublishNews==>");
			System.out.println("    newsID="+newsID+", newsName="+newsName+", newsSouce="+newsSouce+", content="+content);
			String onStatus = "1"; //1為開放
			this.personService.updateNews(newsID, newsName, newsSouce, content, onStatus);
			return new ModelAndView("saveUpdateNews");
		}
		//儲存刪除的消息
		@RequestMapping("/delNews")
		public String delNews(long userID,String[] delList, String onStatus)
		{
			System.out.println("delNews==>");
			System.out.println("    userID="+userID+", delList="+delList+", onStatus="+onStatus);			
			for (String news:delList){
				this.personService.deleteNews(Long.parseLong(news));
			}
			return "redirect:queryNewsData.do?userID="+userID+"&onStatus="+onStatus;
		}

		//怡秀 write - 1110
				//管理者新增最新消息-頁面
				@RequestMapping("/addManagerNews")
				public ModelAndView addmanagernews(Model model){					
					ModelAndView mav = new ModelAndView("addManagerNews");
					System.out.println("addmanagernews==>");
					long adminID = 2;
					model.addAttribute("adminID", adminID);				
					return mav;
				}
				
				//管理者新增最新消息-儲存
				@RequestMapping("/saveCNews")
				public String savecnews(HttpServletRequest request) throws Exception {
					
					String newsCategory = "";
					String newsName = "";
					String picture = "";
					String newsSouce = "";
					String onDate = "";
					String content = "";
					long adminID=0;
					String value = "";
					String fileName = "";
					String onStatus = "";
					
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
								if(name2.equals("adminID")){adminID=Long.parseLong(value);}
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
					picture = "image/"+fileName;
					this.personService.saveManagerNews(adminID, newsCategory, newsName, picture, newsSouce, onDate, content, onStatus);
					return "redirect:addManagerNews.do";
				}
				
				//管理者查詢最新消息-查詢(左邊tiles進入)
				@RequestMapping("/queryManagerNews")
				public ModelAndView querycnews(){
					return new ModelAndView("queryManagerNews", "newsList",this.personService.queryFirstNewsList());
				}
				
				//管理者查詢最新消息-查詢(根據查詢條件)
				@RequestMapping("/queryManagerNewsData")
				public ModelAndView queryManagerNewsData(String newsCategory, String newsName, String MOPEND, String MCLOSED, String onStatus, String newsSource){
					return new ModelAndView("queryManagerNewsData", "newsList",this.personService.queryNewsList(newsCategory, newsName, MOPEND, MCLOSED, onStatus, newsSource));
				}
				
			//管理者查詢最新消息-刪除
			@RequestMapping("/deleteData")
			public String deletedata(String[] delList){					
				System.out.println("delNews==>");		
				for (String news:delList){
					this.personService.deleteNews(Long.parseLong(news));
				}
			return "redirect:queryManagerNews.do";
			}
				
			//管理者查詢最新消息-編輯頁或顯示頁
			@RequestMapping("/editNewsData/{type}")
			public ModelAndView editnewsdata(@PathVariable("type") String type,long newsID){
				ModelAndView mav;
				News news = this.personService.queryNewsDetail(newsID);
				if(type.equals("edit")){
					mav = new ModelAndView("editNewsData");
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
			
				String newsCategory = "";
				String newsName = "";
				String picture = "";
				String newsSouce = "";
				String onDate = "";
				String content = "";
				String value = "";
				String fileName = "";				
				long newsID = 0;
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
							if(name2.equals("newsID")){newsID=Long.parseLong(value);}
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
				picture = "image/"+fileName;
				long adminID = 2;
				this.personService.updateManagerNews(adminID, newsID, newsCategory, newsName, picture, newsSouce, onDate, content);
				return "redirect:queryManagerNews.do";
			}
}
