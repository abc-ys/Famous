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

}
