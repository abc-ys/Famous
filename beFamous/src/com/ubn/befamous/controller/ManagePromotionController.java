package com.ubn.befamous.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.Album;

/**
 * 管理大力推活動
 * @author kevin
 *
 */

@Controller
@SessionAttributes
public class ManagePromotionController {

	//
	@RequestMapping(value = "/forwardPromotionActivity")
	public ModelAndView forwardPromotionActivity() {
		
		
		ModelAndView mav = new ModelAndView("forwardPromotionActivity");
	    return mav;
	
	}
	
	
	@RequestMapping(value = "/queryAlbumById")
	public ModelAndView queryAlbumById(HttpServletResponse resp) {
		
		System.out.println("打進來了沒");
		ModelAndView mav = new ModelAndView("queryAlbumById");

		Album album = new Album();
		album.setName("bird");
		album.setAlbumID(1234567);
		//album.setCreator(creator);
		album.setBrand("UBN");
		album.setCover("image/image001.png");
		album.setType("MP3");
		album.setDate("2011/10/25");
		//album.setMusicCategory(musicCategory1);
		album.setIntroduction("This is very good album!");
		
		String aaa = "[\"{name\":\\AAA\",age\":BBB}]";
		
		mav.addObject(aaa);
		
		
		PrintWriter pw;
		try {
			pw = resp.getWriter();
			//pw.println("[{ \"excuteResult\":\"successs\",\"excuteDescription\":\"111\"}]");
			pw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	    return mav;
	
	}
	
	
}
