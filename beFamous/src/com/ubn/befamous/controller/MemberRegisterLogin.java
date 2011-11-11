package com.ubn.befamous.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.Member;

@Controller
@SessionAttributes
public class MemberRegisterLogin {
	
	//註冊步驟一
	@RequestMapping("/registerOne")
	public ModelAndView registerOne()
	{
		System.out.println("registerOne==>");		
		return new ModelAndView("registerOne");
	}
	
	//儲存步驟一
	@RequestMapping("/saveRegisterOne")
	public String saveRegisterOne(String email,String userName,String password,String sex,String birthday,String location, Model model)
	{
		System.out.println("saveRegisterOne==>");
		System.out.println("	email="+email+", userName="+userName+", password="+password+", sex="+sex+", birthday="+birthday+", location="+location);
		
		Member m = new Member();
		m.setMemberId(1111);
		
		model.addAttribute("member", m);
		return "redirect:registerTwo.do";
	}
	
	//註冊步驟二
	@RequestMapping("/registerTwo")
	public ModelAndView registerTwo()
	{
		System.out.println("registerTwo==>");		
		
		return new ModelAndView("registerTwo");
	}
	
	//儲存步驟二 (儲存會員上傳的圖片)
	@RequestMapping("/saveRegisterTwo")
	public String saveRegisterTwo(HttpServletRequest request, String memberId) throws Exception
	{
		System.out.println("saveRegisterTwo==>");		
		
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
		return "redirect:registerThree.do";
	}
	
	//註冊步驟三
	@RequestMapping("/registerThree")
	public ModelAndView registerThree(Model model)
	{
		System.out.println("registerThree==>");		
		Member member = new Member();
		member.setMemberId(111);
		member.setUserName("王大明");
		member.setEmail("aaa.@ubn.com");
		member.setPicture("images/lucy.jpg");
					
		model.addAttribute("member", member);
		return new ModelAndView("registerThree");
	}
	
	//登入
	@RequestMapping("/login")
	public ModelAndView login()
	{
		System.out.println("login==>");		
		
		return new ModelAndView("login");
	}
	//忘記密碼
	@RequestMapping("/forgetPassword")
	public ModelAndView forgetPassword()
	{
		System.out.println("forgetPassword==>");		
		
		return new ModelAndView("forgetPassword");
	}
	
}
