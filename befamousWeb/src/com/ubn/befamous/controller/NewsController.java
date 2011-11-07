package com.ubn.befamous.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

}
