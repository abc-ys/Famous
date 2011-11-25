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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.Member;
import com.ubn.befamous.service.PersonService;

@Controller
@SessionAttributes
public class MemberRegisterLoginController {
	@Autowired
	private PersonService personService;
	
	
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
		
		Member m = personService.saveMember(email, userName, password, sex, birthday, location);
		String memberId = String.valueOf(m.getId());
		return "redirect:registerTwo.do?memberId="+memberId;
	}
	
	//註冊步驟二
	@RequestMapping("/registerTwo")
	public ModelAndView registerTwo(String memberId,Model model)
	{
		System.out.println("registerTwo==>"+memberId);		
		model.addAttribute("memberId", memberId);
		return new ModelAndView("registerTwo");
	}
	
	//儲存步驟二 (儲存會員上傳的圖片)
	@RequestMapping("/saveRegisterTwo")
	public String saveRegisterTwo(HttpServletRequest request,Model model) throws Exception
	{
		String memberId = "";
		String fileName="";
		String picture2 = "";
		
		System.out.println("saveRegisterTwo==>");		
		
		int yourMaxMemorySize = 500 * 500* 1024;
		File yourTempDirectory = new File("/tmp");
		int yourMaxRequestSize = 500 * 500* 1024;
		boolean writeToFile = true;
		String allowedFileTypes = ".gif .jpg .png";

		String saveDirectory = "D:/gitTest/ImageWeb/WebContent/image/memberPicture";

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
					if(name.equals("memberId")){
						memberId = value;
					}else if(name.equals("picture2")){
						picture2 = value;
					}
				} else {
					// Process a file upload
					//processUploadedFile(item);	
					String fieldName = item.getFieldName();
					fileName = item.getName();
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
		System.out.println("picture2=="+picture2);
		if(!picture2.isEmpty()){
			personService.updateMemberPicture(memberId, picture2);
		}else{
			personService.updateMemberPicture(memberId, fileName);
		}
		
		return "redirect:registerThree.do?memberId="+memberId;
	}
	
	//註冊步驟三
	@RequestMapping("/registerThree")
	public ModelAndView registerThree(String memberId,Model model)
	{
		System.out.println("registerThree==>");		
		Member member = personService.queryMember(memberId);
		System.out.println("getUserName=="+member.getUserName());
		model.addAttribute("member", member);
		model.addAttribute("memberId", memberId);
		return new ModelAndView("registerThree");
	}
	
	//登入
	@RequestMapping("/login")
	public ModelAndView login(String email,String password,Model model)
	{
		Member member = new Member();
		member.setEmail("kevin@ubn.net");
		member.setPassword("6yhn6yhn");
		if( email !=null && !"".equals(email)){
			if(!password.equals(member.getPassword()) || !email.equals(member.getEmail())){
				model.addAttribute("errorLogin", "帳號或密碼錯誤!");
				return new ModelAndView("login");
			}else{
				return new ModelAndView("forwardRecommendActivity");
			}
		}
		System.out.println("login==>");		
		System.out.println("email==>"+email);	
		System.out.println("password==>"+password);	
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
