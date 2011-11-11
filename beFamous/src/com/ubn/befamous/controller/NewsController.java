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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.News;

@Controller
@SessionAttributes
public class NewsController {
	
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
		
		//最新消息的創作人新增最新消息
		@RequestMapping("/addNews")
		public ModelAndView addNews(String memberID, Model model)
		{
			System.out.println("addNews==>");
			return new ModelAndView("addNews");
		}
		
		//儲存創作人新增的最新消息
		@RequestMapping("/saveNews")
		public ModelAndView saveNews(String memberID,String newsName,String newsSouce, String content)
		{
			System.out.println("saveNews==>");
			System.out.println("    memberID="+memberID+", newsName="+newsName+", newsSouce="+newsSouce+", content="+content);
			String status = "草稿";
			return new ModelAndView("addNews");
		}
		
		//發佈創作人新增的最新消息
			@RequestMapping("/publishNews")
			public ModelAndView publishNews(String memberID,String newsName,String newsSouce, String content)
			{
				System.out.println("publishNews==>");
				System.out.println("    memberID="+memberID+", newsName="+newsName+", newsSouce="+newsSouce+", content="+content);
				String status = "開放";
				
				return new ModelAndView("addNews");
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
		
		//最新消息的查詢最新消息
		@RequestMapping("/queryNews")
		public ModelAndView queryNews(String memberID,String status, Model model)
		{
			System.out.println("queryNews==>");
			System.out.println("    memberID="+memberID+", status="+status);
			
			ArrayList list = new ArrayList();
			ArrayList list1 = new ArrayList();
			News news = new News();
			news.setNewsId(1111);
			news.setCreateDate("2011/10/10");
			news.setPicture("http://befamous.gsi-media.com/ImageWeb/image/111111.jpg");
			news.setNewsName("2011設計大展");
			news.setContent("2011設計大展即將結束");
			news.setNewsSouce("設計展");	
			news.setOnStatus("開放");
			int click = 10;
			list.add(news);
			list.add(click);
			
			News news1 = new News();
			news1.setNewsId(2222);
			news1.setCreateDate("2011/10/11");
			news1.setPicture("http://befamous.gsi-media.com/ImageWeb/image/111111.jpg");
			news1.setNewsName("2012設計大展");
			news1.setContent("2012設計大展即將結束");
			news1.setNewsSouce("設計展");
			news1.setOnStatus("開放");
			int click1 = 20;
			list1.add(news1);
			list1.add(click1);		
			
			ArrayList[] newsList = {list, list1};
			model.addAttribute("status",status);
			model.addAttribute("newsList",newsList);
			return new ModelAndView("queryNews");
		}
		
		//編輯創作人的最新消息 (window open)
		@RequestMapping("/modifyNews")
		public ModelAndView modifyNews(String memberID,String newsID, Model model)
		{
			System.out.println("modifyNews==>");
			System.out.println("    memberID="+memberID+", newsID="+newsID);
				
			News news = new News();
			news.setNewsId(11111);
			news.setCreateDate("2011/10/10");
			news.setPicture("http://befamous.gsi-media.com/ImageWeb/image/111111.jpg");
			news.setNewsName("2011設計大展");
			news.setContent("2011設計大展即將結束");
			news.setNewsSouce("設計展");			
			model.addAttribute("news",news);
			return new ModelAndView("modifyNews");
		}
		
		//更新創作人的最新消息
		@RequestMapping("/updateNews")
		public ModelAndView updateNews(String memberID,String newsId,String newsName,String newsSouce, String content)
		{
			System.out.println("updateNews==>");
			System.out.println("    memberID="+memberID+", newsId="+newsId+", newsName="+newsName+", newsSouce="+newsSouce+", content="+content);
			return new ModelAndView("saveUpdateNews");
		}
		
		//儲存創作人更新的最新消息(window close)
		@RequestMapping("/saveUpdateNews")
		public ModelAndView saveUpdateNews(String memberID,String newsId,String newsName,String newsSouce, String content)
		{
			System.out.println("saveUpdateNews==>");
			System.out.println("    memberID="+memberID+", newsId="+newsId+", newsName="+newsName+", newsSouce="+newsSouce+", content="+content);
			return new ModelAndView("saveUpdateNews");
		}
			
		//更新發佈創作人的最新消息
		@RequestMapping("/updatePublishNews")
		public ModelAndView updatePublishNews(String memberID,String newsId,String newsName,String newsSouce, String content)
		{
			System.out.println("updatePublishNews==>");
			System.out.println("    memberID="+memberID+", newsId="+newsId+", newsName="+newsName+", newsSouce="+newsSouce+", content="+content);
			return new ModelAndView("saveUpdateNews");
		}
		//儲存刪除的消息
		@RequestMapping("/delNews")
		public String delNews(String memberID,String delList, String status)
		{
			System.out.println("delNews==>");
			System.out.println("    memberID="+memberID+", delList="+delList+", status="+status);
			return "redirect:queryNews.do?memberID="+memberID+"& status="+status;
		}

		//怡秀 write - 1110
				//管理者新增最新消息
				@RequestMapping("/addManagerNews")
				public ModelAndView addmanagernews(){
					ModelAndView mav = new ModelAndView("addManagerNews");
					
					String creatorDate = "2011-11-03";
					
					mav.addObject("creatorDate",creatorDate);
					return mav;
				}
				
				//管理者新增最新消息-儲存
				@RequestMapping("/saveCNews")
				public String savecnews(HttpServletRequest request) throws Exception {
					
					String newsCategory = "";
					String newsName = "";
					String cover = "";
					String newsSouce = "";
					String date = "";
					String createDate = "";
					String content = "";
					String value = "";
					String fileName = "";
					
					int yourMaxMemorySize = 500 * 500* 1024;
					File yourTempDirectory = new File("/tmp");
					int yourMaxRequestSize = 500 * 500* 1024;
					boolean writeToFile = true;
					String allowedFileTypes = ".jpg .png .gif .jpeg";

					String saveDirectory = "D:/gitTest/befamousWeb/WebContent/file";  //存放的路徑

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
								if(name2.equals("cover")){cover=value;}
								if(name2.equals("newsSouce")){newsSouce=value;}
								if(name2.equals("date")){date=value;}
								if(name2.equals("createDate")){createDate=value;}
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
									      
									    cover=fileName;
										
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
					

					System.out.println("newsCategory==>"+newsCategory);
					System.out.println("newsName==>"+newsName);
					System.out.println("cover==>"+cover);
					System.out.println("newsSouce==>"+newsSouce);
					System.out.println("date==>"+date);
					System.out.println("createDate==>"+createDate);
					System.out.println("content==>"+content);
					System.out.println("cover==>"+cover);
					
					
					return "redirect:addManagerNews.do";
				}
				
				//管理者查詢最新消息-查詢
				@RequestMapping("/queryManagerNews")
				public ModelAndView querycnews(String newsCategory, String newsName, String MOPEND, String MCLOSED, String status, String newsSource){
					
					System.out.println("newsCategory==>"+newsCategory);
					System.out.println("newsName==>"+newsName);
					System.out.println("MOPEND==>"+MOPEND);
					System.out.println("MCLOSED==>"+MCLOSED);
					System.out.println("status==>"+status);
					System.out.println("newsSource==>"+newsSource);
					
					ModelAndView mav = new ModelAndView("queryManagerNews");
									
					String amount = "15";
					String amount2 = "10";
					
					News news = new News();
					long p =111112;
					news.setNewsId(p);
					news.setNewsCategory("公告");
					news.setNewsName("xxxxx");
					news.setNewsSouce("大熊");
					news.setCreateDate("2011-09-30");
					news.setOnDate("2011-10-03");
					news.setOnStatus("刊登中");
					
					News news2 = new News();
					long p2 =1222112;
					news2.setNewsId(p2);
					news2.setNewsCategory("好康");
					news2.setNewsName("買一送一");
					news2.setNewsSouce("小明");
					news2.setCreateDate("2011-10-13");
					news2.setOnDate("2011-10-23");
					news2.setOnStatus("未上架");
					
					ArrayList list = new ArrayList();     //明細第一筆
					list.add(news);
					list.add(amount);
					
					ArrayList list1 = new ArrayList();    //明細第二筆
					list1.add(news2);
					list1.add(amount2);
					
					ArrayList[]  list3 = {list, list1};
					
					mav.addObject("news", list3);
					return mav;
				}
				
				//管理者查詢最新消息-刪除
				@RequestMapping("/deleteData")
				public String deletedata(String interst){
					System.out.println("newsID==>"+interst);
					return "redirect:queryManagerNews.do";
				}
				
				//管理者查詢最新消息-編輯頁或顯示頁
				@RequestMapping("/editNewsData/{type}")
				public ModelAndView editnewsdata(@PathVariable("type") String type,String newsID){
					ModelAndView mav;
					
					News news = new News();
					news.setNewsCategory("表演");
					news.setNewsName("xxxxx");
					news.setPicture("images/lucy.jpg");
					news.setNewsSouce("年代售票");
					news.setOnDate("2011-09-28");
					news.setCreateDate("2011-09-06");
					news.setContent("2012  阿妹演唱會");
					
					
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
				public String saveeditednews(HttpServletRequest request) throws Exception {
					
					String newsCategory = "";
					String newsName = "";
					String cover = "";
					String newsSouce = "";
					String date = "";
					String createDate = "";
					String content = "";
					String value = "";
					String fileName = "";
					
					int yourMaxMemorySize = 500 * 500* 1024;
					File yourTempDirectory = new File("/tmp");
					int yourMaxRequestSize = 500 * 500* 1024;
					boolean writeToFile = true;
					String allowedFileTypes = ".jpg .png .gif .jpeg";

					String saveDirectory = "D:/gitTest/befamousWeb/WebContent/file";  //存放的路徑

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
								if(name2.equals("cover")){cover=value;}
								if(name2.equals("newsSouce")){newsSouce=value;}
								if(name2.equals("date")){date=value;}
								if(name2.equals("createDate")){createDate=value;}
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
									      
									    cover=fileName;
										
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
					

					System.out.println("newsCategory==>"+newsCategory);
					System.out.println("newsName==>"+newsName);
					System.out.println("cover==>"+cover);
					System.out.println("newsSouce==>"+newsSouce);
					System.out.println("date==>"+date);
					System.out.println("createDate==>"+createDate);
					System.out.println("content==>"+content);
					System.out.println("cover==>"+cover);
					
					
					return "redirect:queryManagerNews.do";
				}
}
