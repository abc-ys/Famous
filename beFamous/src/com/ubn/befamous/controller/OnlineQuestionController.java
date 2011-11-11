package com.ubn.befamous.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes
public class OnlineQuestionController {
	
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
		return new ModelAndView("addQuestion");
	}
	
	//查詢問題清單 (base on 問題管理連結)
		@RequestMapping("/manageQuestion")
		public ModelAndView manageQuestion(Model model)
		{
			System.out.println("manageQuestion==>");
			
			String adminId="9999";
				
			Question q1 = new Question();
			q1.setQuestionId(111);
			q1.setQuestionType("下載問題");
			q1.setQuestionDate("2011-11-11 11-11-11");
			q1.setProductionType("PC");
			q1.setUserIdentity("會員");
			q1.setUserName("王大明");
			q1.setEmail("aaa@gmail.com");
			q1.setTel("0911234567");
			q1.setHandleStatus("未回覆");
			q1.setNoteContent("qqq");
				
			Question q2 = new Question();
			q2.setQuestionId(111);
			q2.setQuestionType("軟體相關");
			q2.setQuestionDate("2011-11-11 11-11-11");
			q2.setProductionType("Android");
			q2.setUserIdentity("非會員");
			q2.setUserName("李曉明");
			q2.setEmail("bbb@gmail.com");
			q2.setTel("0922234567");
			q2.setHandleStatus("未回覆");
				
			Question[] questionList = {q1,q2};
			model.addAttribute("admin", adminId);
			model.addAttribute("questionList", questionList);
			return new ModelAndView("manageQuestion");
		}	
		
		//查詢問題清單 (base on 查詢條件)
		@RequestMapping("/queryQuestionList")
		public ModelAndView queryQuestionList(String adminId, String startDate,String endDate, String productionType, String questionType, Model model)
		{
			System.out.println("queryQuestionList==>");
			System.out.println("	startDate="+startDate+", endDate="+endDate+", productionType="+productionType+", questionType="+questionType);
			
			adminId="9999";
			
			Question q1 = new Question();
			q1.setQuestionId(111);
			q1.setQuestionType("下載問題");
			q1.setQuestionDate("2011-11-11 11-11-11");
			q1.setProductionType("PC");
			q1.setUserIdentity("會員");
			q1.setUserName("王大明");
			q1.setEmail("aaa@gmail.com");
			q1.setTel("0911234567");
			q1.setHandleStatus("未回覆");
			q1.setNoteContent("qqq");
			
			Question q2 = new Question();
			q2.setQuestionId(111);
			q2.setQuestionType("軟體相關");
			q2.setQuestionDate("2011-11-11 11-11-11");
			q2.setProductionType("Android");
			q2.setUserIdentity("非會員");
			q2.setUserName("李曉明");
			q2.setEmail("bbb@gmail.com");
			q2.setTel("0922234567");
			q2.setHandleStatus("未回覆");
			
			Question[] questionList = {q1,q2};
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
			
			Question q1 = new Question();
			q1.setQuestionId(111);
			q1.setQuestionContent("XXXXXXXXXXXXXXXXXXXXXXXXXX");
			q1.setAnswerContent("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");
			q1.setAnswerDate("2011-11-12 12:12:12");
			q1.setAnswerPerson("管理者一號");
			q1.setNoteContent("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
			q1.setNoteDate("2011-11-13 13:13:13");
			q1.setNotePerson("管理者二號");
			
			model.addAttribute("admin", adminId);
			model.addAttribute("questionDetail", q1);
			return new ModelAndView("queryQuestionDetail");
		}
		//管理者回覆問題 (window open)
		@RequestMapping("/addAnswer")
		public ModelAndView addAnswer(String adminId,String qId, Model model) {
			System.out.println("addAnswer==>");		
			System.out.println("    qId="+qId+", adminId="+adminId);
			
			Admin admin = new Admin();
			admin.setAdminName("BBB");
			admin.setAdminId(9999);
			
			model.addAttribute("qId",qId);
			model.addAttribute("admin",admin);
			return new ModelAndView("addAnswer");
		}
			
		//儲存回覆內容
		@RequestMapping("/saveAnswer")
		public ModelAndView saveAnswer(String adminId,String qId,String answer, Model model) {						
			System.out.println("saveAnswer==>");		
			System.out.println("    qId="+qId+", adminId="+adminId+", answer="+answer);
			
			
			return new ModelAndView("saveAnswer");
		}
		
		//管理者新增備註 (window open)
		@RequestMapping("/addNote")
		public ModelAndView addNote(String adminId,String qId, Model model) {
			System.out.println("addNote==>");		
			System.out.println("    qId="+qId+", adminId="+adminId);
			
			Admin admin = new Admin();
			admin.setAdminName("BBB");
			admin.setAdminId(9999);
			
			model.addAttribute("qId",qId);
			model.addAttribute("admin",admin);
			return new ModelAndView("addNote");
		}
				
		//儲存備註
		@RequestMapping("/saveNote")
		public ModelAndView saveNote(String adminId,String qId,String note, Model model) {						
			System.out.println("saveNote==>");		
			System.out.println("    qId="+qId+", adminId="+adminId+", note="+note);
			return new ModelAndView("saveNote");
		}
		

}
