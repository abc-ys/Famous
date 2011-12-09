package com.ubn.befamous.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.dao.IBaseDao;
import com.ubn.befamous.entity.Admin;
import com.ubn.befamous.entity.Question;
import com.ubn.befamous.service.MusicService;
import com.ubn.befamous.service.PersonService;

@Controller
@SessionAttributes
public class OnlineQuestionController {
	
	@Autowired
	 PersonService personService;
	
	@RequestMapping("/addQuestion")
	public ModelAndView addQuestion()
	{
		System.out.println("addQuestion==>");
		return new ModelAndView("addQuestion");
	}
		
	//儲存新增的問題
	@RequestMapping("/saveQuestion")
	public ModelAndView saveQuestion(String productionType,String userIdentity, String userName, String email, String tel, String questionType, String questionContent)
	{
		System.out.println("saveQuestion==>");
		System.out.println("    productionType="+productionType+", userIdentity="+userIdentity+", userName="+userName+", email="+email+", tel="+tel+", questionType="+questionType+", questionContent="+questionContent);
		String questionDate = "2011/11/01";
		
		personService.saveQuestion(productionType, userIdentity, userName, email, tel, questionType, questionContent);
		return new ModelAndView("addQuestion");
	}
	
	//查詢問題清單 (base on 問題管理連結)
		@RequestMapping("/manageQuestion")
		public ModelAndView manageQuestion(Model model,String adminId)
		{
			System.out.println("manageQuestion==>");
			
			Question[] questionList = personService.queryQuestion("", "", "", "", "","");
			model.addAttribute("admin", adminId);
			model.addAttribute("questionList", questionList);
			return new ModelAndView("manageQuestion");
		}	
		
		//查詢問題清單 (base on 查詢條件)
		@RequestMapping("/queryQuestionList")
		public ModelAndView queryQuestionList(String adminId, String startDate,String endDate, String productionType, String questionType, String email,String handleStatus, Model model)
		{
			System.out.println("queryQuestionList==>");
			System.out.println("	startDate="+startDate+", endDate="+endDate+", productionType="+productionType+", questionType="+questionType);
			
			
			Question[] questionList = personService.queryQuestion(startDate, endDate, productionType, email, questionType, handleStatus);
			model.addAttribute("admin", adminId);
			model.addAttribute("questionList", questionList);
			return new ModelAndView("queryQuestionList");
		}	
		//查詢問題detail
		@RequestMapping("/queryQuestionDetail")
		public ModelAndView queryQuestionDetail(String adminId, String qId, Model model)
		{
			System.out.println("queryQuestionDetail==>");
			System.out.println("	qId="+qId);
			
			Question q = personService.queryQuestionDetail(Long.parseLong(qId));
			
			model.addAttribute("admin", adminId);
			model.addAttribute("questionDetail", q);
			return new ModelAndView("queryQuestionDetail");
		}
		//管理者回覆問題 (window open)
		@RequestMapping("/addAnswer")
		public ModelAndView addAnswer(String adminId,String qId, Model model) {
			System.out.println("addAnswer==>");		
			System.out.println("    qId="+qId+", adminId="+adminId);
			
			Admin admin = personService.queryAdminName(Long.parseLong(adminId));
			
			model.addAttribute("qId",qId);
			model.addAttribute("admin",admin);
			model.addAttribute("adminId",adminId);
			return new ModelAndView("addAnswer");
		}
			
		//儲存回覆內容
		@RequestMapping("/saveAnswer")
		public ModelAndView saveAnswer(String adminId,String qId,String answer,String adminName, Model model) {						
			System.out.println("saveAnswer==>");		
			System.out.println("    qId="+qId+", adminId="+adminId+", answer="+answer);
			
			personService.saveAnswer(Long.valueOf(qId), Long.valueOf(adminId), answer, adminName);
			
			return new ModelAndView("saveAnswer");
		}
		
		//管理者新增備註 (window open)
		@RequestMapping("/addNote")
		public ModelAndView addNote(String adminId,String qId, Model model) {
			System.out.println("addNote==>");		
			System.out.println("    qId="+qId+", adminId="+adminId);
			
			Admin admin = personService.queryAdminName(Long.parseLong(adminId));
			
			model.addAttribute("qId",qId);
			model.addAttribute("admin",admin);
			model.addAttribute("adminId",adminId);
			return new ModelAndView("addNote");
		}
				
		//儲存備註
		@RequestMapping("/saveNote")
		public ModelAndView saveNote(String adminId,String qId,String note,String adminName, Model model) {						
			System.out.println("saveNote==>");		
			System.out.println("    qId="+qId+", adminId="+adminId+", note="+note+", adminName="+adminName);
			
			personService.saveNote(Long.valueOf(qId), Long.valueOf(adminId), note, adminName);
			
			return new ModelAndView("saveNote");
		}
		

}
