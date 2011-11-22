package com.ubn.befamous.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.Album;
import com.ubn.befamous.entity.Creator;
import com.ubn.befamous.entity.Hidden;
import com.ubn.befamous.entity.MusicCategory;
import com.ubn.befamous.entity.Offense;
import com.ubn.befamous.entity.OrderDetail;
import com.ubn.befamous.entity.Song;
import com.ubn.befamous.entity.SongPrice;
import com.ubn.befamous.service.MusicService;


@Controller
public class UploadMusicController {
	
	@Autowired
	 MusicService musicService;
	

	//音樂管理-編輯專輯
	@RequestMapping("/editAlbum")
	public ModelAndView editalbum(long creatorId){
		ModelAndView mav = new ModelAndView("editAlbum");
		
		creatorId = 2;
		Album[] a = musicService.queryOwnAlbums(creatorId);
		mav.addObject("album", a);
		mav.addObject("creatorId", creatorId);
		return mav;
	}
	
	//音樂管理-編輯專輯
	@RequestMapping("/editAlbumContent")
	public ModelAndView editalbumcontent(long albumID, long creatorId){
		
		System.out.println("albumID==>"+albumID);
		
		ModelAndView mav = new ModelAndView("editAlbumContent");
		Album album = musicService.queryMusic(albumID);
		
		mav.addObject("album", album);
		mav.addObject("creatorId", creatorId);
		return mav;
	}
	
	//音樂管理-編輯專輯
	@RequestMapping("/changeState")
	public String changestate(String state,long albumID,long creatorId){
	//public String changestate(String state)throws UnsupportedEncodingException{
			
		//String selStr=java.net.URLDecoder.decode(state,"UTF-8"); //這行是將中文字做解碼
		musicService.changeAlbumStatus(state, albumID, creatorId);
		System.out.println("state==>"+state);
			
		return "redirect:editAlbumContent.do?albumID="+albumID+"&creatorId="+creatorId;
	}
	
	//音樂管理-編輯專輯-儲存
	@RequestMapping("/saveAlbumData")
	public String savealbumdata(long albumID, long creatorId){
				
		System.out.println("albumID==>"+albumID);
				
		return "redirect:editAlbumContent.do";
	}
	
	//音樂管理-編輯專輯-刪除歌曲
	@RequestMapping("/deleteSongData")
	public String deletesongdata(){
					
		System.out.println("刪除歌曲");
					
		return "redirect:editAlbumContent.do";
	}
	
	//音樂管理-編輯專輯-編輯歌曲資訊
	@RequestMapping("/editSongData")
	public ModelAndView editsongdata(long songID, long creatorId, String songName, String musicType, String MOPEND, String status, String price, String price2, String discount, String tag, String lyrics, String lyricist, String composer, String producer){
		
		System.out.println("songID==>"+songID);
		System.out.println("songName==>"+songName);
		System.out.println("musicType==>"+musicType);
		System.out.println("MOPEND==>"+MOPEND);
		System.out.println("status==>"+status);
		System.out.println("price==>"+price);
		System.out.println("price2==>"+price2);
		System.out.println("discount==>"+discount);
		System.out.println("tag==>"+tag);
		System.out.println("lyrics==>"+lyrics);
		System.out.println("lyricist==>"+lyricist);
		System.out.println("composer==>"+composer);
		System.out.println("producer==>"+producer);
		
		ModelAndView mav = new ModelAndView("editSongData");
						
		MusicCategory mc = new MusicCategory();
		mc.setName("古典樂");
		MusicCategory mc2 = new MusicCategory();
		mc2.setName("搖滾樂");
		MusicCategory mc3 = new MusicCategory();
		mc3.setName("兒歌");
		
		MusicCategory[] mType = {mc,mc2,mc3};
		
		Song song = musicService.querySong(songID);
		
		mav.addObject("mType",mType);
		mav.addObject("song",song);
		mav.addObject("songID", songID);
		mav.addObject("creatorId", creatorId);
		return mav;
	}
	
	//音樂管理-編輯專輯-更新歌曲資訊   
		@RequestMapping("/updateSongData")
		public String updatesongdata(long songID, long creatorId, String songName, String musicType, String MOPEND, String status, String price, String price2, String discount, String tag, String lyrics, String lyricist, String composer, String producer){
			
			System.out.println("更新");
			System.out.println("songID==>"+songID);
			System.out.println("songName==>"+songName);
			System.out.println("musicType==>"+musicType);
			System.out.println("MOPEND==>"+MOPEND);
			System.out.println("status==>"+status);
			System.out.println("price==>"+price);
			System.out.println("price2==>"+price2);
			System.out.println("discount==>"+discount);
			System.out.println("tag==>"+tag);
			System.out.println("lyrics==>"+lyrics);
			System.out.println("lyricist==>"+lyricist);
			System.out.println("composer==>"+composer);
			System.out.println("producer==>"+producer);
			
			musicService.updateSong(creatorId, songID, songName, musicType, MOPEND, status, price, price2, discount, tag, lyrics, lyricist, composer, producer);
			
			return "redirect:editSongData.do?songID="+songID+"&creatorId="+creatorId;
		}
	
	//音樂管理-編輯專輯-編輯專輯資訊
	@RequestMapping("/editAlbumData")
	public ModelAndView editalbumdata(long albumID, long creatorId){
			
		System.out.println("albumID==>"+albumID);
			
		ModelAndView mav = new ModelAndView("editAlbumData");
							
		MusicCategory mc = new MusicCategory();
		mc.setName("古典樂");
		MusicCategory mc2 = new MusicCategory();
		mc2.setName("搖滾樂");
		MusicCategory mc3 = new MusicCategory();
		mc3.setName("兒歌");
		
		MusicCategory[] mType = {mc,mc2,mc3};
		
		Album ac = new Album();
		ac.setCover("images/album.jpg");
		Album ac2 = new Album();
		ac2.setCover("images/title_01.gif");
		Album ac3 = new Album();
		ac3.setCover("images/play.png");
		Album[] a = {ac,ac2,ac3};
		
		Album album = musicService.queryAlbum(albumID);
			
		mav.addObject("albumID",albumID);
		mav.addObject("mType",mType);
		mav.addObject("cover",a);
		mav.addObject("album",album);
		mav.addObject("creatorId",creatorId);
		return mav;
	}
	
	//音樂管理-編輯專輯-編輯專輯資訊
	@RequestMapping("/saveAlbumInfo")
	public String savealbuminfo(HttpServletRequest request,long albumID, long creatorId) throws Exception {
		
		String albumType = "";
		String name = "";
		String date = "";
		String brand = "";
		String musicCategory = "";
		String tag = "";
		String cover = "";
		String cover2 = "";
		String introduction = "";
		String status = "";
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
					if(name2.equals("albumType")){albumType=value;}
					if(name2.equals("name")){name=value;}
					if(name2.equals("date")){date=value;}
					if(name2.equals("brand")){brand=value;}
					if(name2.equals("musicCategory")){musicCategory=value;}
					if(name2.equals("tag")){tag=value;}
					if(name2.equals("cover2")){cover2=value;}
					if(name2.equals("introduction")){introduction=value;}
					if(name2.equals("status")){status=value;}
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
							String formatName = fileName.substring(fileName.length() - 4,fileName.length());
						    fileName = (albumID + formatName).toLowerCase();
							//fileName = FilenameUtils.getName(fileName);
						      
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
		

		System.out.println("albumID==>"+albumID);
		System.out.println("albumType==>"+albumType);
		System.out.println("name==>"+name);
		System.out.println("date==>"+date);
		System.out.println("brand==>"+brand);
		System.out.println("musicCategory==>"+musicCategory);
		System.out.println("tag==>"+tag);
		System.out.println("cover==>"+cover);
		System.out.println("cover2==>"+cover2);
		System.out.println("introduction==>"+introduction);
		System.out.println("status==>"+status);	
		
		musicService.updateAlbum(creatorId,albumID, albumType, name, date, brand, musicCategory, tag, cover, cover2, introduction, status);
		
		return "redirect:editAlbumData.do?albumID="+albumID+"&creatorId="+creatorId;
	}
	
	
	//Lucy write - 1110
	
	//新增專輯
		@RequestMapping("/addAlbum")
		public ModelAndView addAlbum(Model model,long creatorId)
		{
			System.out.println("addAlbum==>");
			
			ArrayList coverList = new ArrayList();
			coverList.add("images/album.png");
			coverList.add("images/album.png");
			coverList.add("images/album.png");
			
			model.addAttribute("defaultCover", coverList);
			model.addAttribute("creatorId", creatorId);
			return new ModelAndView("addAlbum");
			
		}
		//儲存專輯資訊
		@RequestMapping("/saveAlbum")
		public String saveAlbum(HttpServletRequest request, long creatorId, Model model) throws Exception
		{
			
			System.out.println("saveAlbum==>");
			
			String albumType = "";
			String albumName = "";
			String albumBrand = "";
			long musicCategory = 0;
			String albumTag = "";
			String albumIntroduction = "";
			String albumStatus = "";
			String albumCover = "";
			String defaultCover = "";
			String fileName = "";
			
			int yourMaxMemorySize = 500 * 500* 1024;
			File yourTempDirectory = new File("/tmp");
			int yourMaxRequestSize = 300 * 300* 1024;
			boolean writeToFile = true;
			String allowedFileTypes = ".jpg .png .gif .jpeg";
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
						if(name.equals("albumType")){
							albumType  = value;
						}else if(name.equals("name")){
							albumName = value;
						}else if(name.equals("brand")){
							albumBrand = value;
						}else if(name.equals("musicCategory")){
							musicCategory = Long.valueOf(value);
						}else if(name.equals("tag")){
							albumTag = value;
						}else if(name.equals("introduction")){
							albumIntroduction = value;
						}else if(name.equals("status")){
							albumStatus = value;
						}else if(name.equals("cover")){
							albumCover = value;
						}else if(name.equals("defaultCover")){
							defaultCover = value;
						}
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
								String formatName = fileName.substring(fileName.length() - 4,fileName.length());
								System.out.println("formatName==" + formatName + "<br />");
							    fileName = ("album_"+creatorId + formatName).toLowerCase();
							      
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
			
			albumCover=fileName;
			System.out.println(" creatorId="+creatorId+", albumType="+albumType+", albumName="+albumName+", albumBrand="+albumBrand+", musicCategory="+musicCategory+", albumTag="+albumTag+", albumIntroduction="+albumIntroduction+", albumStatus="+albumStatus+", albumCover="+albumCover+", defaultCover="+defaultCover);
			
			long albumID = musicService.saveAlbum(creatorId,albumType,albumName,albumBrand,musicCategory,albumTag,albumIntroduction,albumStatus,albumCover,defaultCover);
			
			model.addAttribute("creatorId", creatorId);
			model.addAttribute("albumId", albumID);
			
			return "redirect:/addSong.do";
			
		}
		//新增歌曲
		@RequestMapping("/addSong")
		public ModelAndView addSong(long creatorId,long albumId, Model model)
		{
			System.out.println("addSong==>");
			System.out.println("creatorId==>"+creatorId);
			model.addAttribute("creatorId", creatorId);
			model.addAttribute("albumId", albumId);
			return new ModelAndView("addSong");		
		}
		
		//儲存歌曲檔案
		@RequestMapping("/saveSong")
		public ModelAndView saveSong(HttpServletRequest request, Model model) throws Exception
		{
			System.out.println("saveSong==>");
			//System.out.println("	creatorId="+creatorId+", albumId="+albumId);
			
			long creatorId=0;
			long albumId=0;
			
			String fileName="";
			String value="";
			boolean writeToFile = true;
			String allowedFileTypes = ".mp3";
			String saveDirectory = "D:/gitTest/befamousWeb/WebContent/file";
			// Check that we have a file upload request
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			System.out.println("isMultipart=" + isMultipart + "<br>");

			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();
				
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

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
					if(name2.equals("creatorId")){creatorId=Long.parseLong(value);}
					if(name2.equals("albumId")){albumId=Long.parseLong(value);}
				} else {
				
				// Process a file upload
				// processUploadedFile(item);	
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
						String formatName = fileName.substring(fileName.length() - 4,fileName.length());
					    fileName = ("song_"+creatorId + formatName).toLowerCase();
					      
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
			System.out.println("fileName=="+fileName);
			
			long songID = musicService.saveSong(albumId, creatorId, fileName);
			
			System.out.println("songID=="+songID);
			model.addAttribute("creatorId", creatorId);
			model.addAttribute("songID", songID);
			model.addAttribute("albumId", albumId);
		  return new ModelAndView("addSongData");		
			
		}
		
		//新增歌曲資訊
		@RequestMapping("/addSongData")
		public ModelAndView addSongData(String creatorId,String albumId, Model model)
		{
			System.out.println("addSongData==>");
			model.addAttribute("albumId", albumId);
			model.addAttribute("creatorId", creatorId);
			return new ModelAndView("addSongData");		
		}
			
		//儲存歌曲資訊
		@RequestMapping("/saveSongData")
		public ModelAndView saveSongDetail(String songId,String creatorId, String albumId, String name, String date, String musicCategory, String status, String price, String price2, String discount, String tag, Model model) throws Exception
		{
			System.out.println("saveSongData==>");
			System.out.println("songId="+songId+",	creatorId="+creatorId+", albumId="+albumId+", name="+name+", date="+date+",	musicCategory="+musicCategory+", status="+status+", price="+price+", price2="+price2+", discount="+discount+", tag="+tag);
			
			musicService.saveSongDetail(Long.parseLong(songId), Long.parseLong(creatorId), Long.parseLong(albumId), name, date, musicCategory, status, price, price2, discount, tag);

			model.addAttribute("creatorId", creatorId);
			model.addAttribute("albumId", albumId);
			return new ModelAndView("addSong");	
		}
}
