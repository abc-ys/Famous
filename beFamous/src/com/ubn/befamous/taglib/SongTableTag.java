package com.ubn.befamous.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.log4j.Logger;
import com.ubn.befamous.entity.Song;

public class SongTableTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8294307498535178928L;
	Logger logger = Logger.getLogger(SongTableTag.class);

	private final static int MAX_COLUMN = 3;
	private final static int MAX_ROW = 4;
	private final static int MAX_SIZE_ONE_PAGE = MAX_COLUMN * MAX_ROW;
	private String listName = "";

	private final static String ARTIST_URL = "/artist/";
	private final static String ALBUM_URL = "/album/update/";
	private final static String LISTEN_URL = "";
	private final static String BUY_URL = "";
	private final static String LYRIC_URL="/song/lyric/";
	private final static String REPORT_URL="/song/report/";

	public int doStartTag() {
		try {

			String strNow = String.valueOf(System.currentTimeMillis());

			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			Song[] songs = (Song[]) request.getAttribute(getListName());

			String strTableId = "songTable" + strNow;
			JspWriter out = pageContext.getOut();
			StringBuilder sb = new StringBuilder();
			sb.append("<table border=\"0\" width=\"100%\" id=\"" + strTableId+ "\" class=\"songTable\" >");
			for (int j = 0; j < songs.length; j++) {
				Song song=songs[j];
				String strAlbumName =song.getAlbum().getName();
				String strAlbumId = String.valueOf(song.getAlbum().getPid());
				String strAlbumCreator = song.getAlbum().getCreator().getUserName();
				String strCreatorId = String.valueOf(song.getAlbum().getCreator().getId());
				String strSongId=String.valueOf(song.getPid());
				String strSongName=song.getName();
				sb.append("<tr>");
				sb.append("<td>"+(j+1)+"<img src=\""+request.getContextPath()+"/images/play.png\" /></td>");
				sb.append("<td>");
				sb.append("	<div>"+strSongName+"</div>");
				sb.append("	<div><a href=\"#\" onclick=\"javaScript:top.location.href='queryAlbumData.do?albumid="+strAlbumId+"'\">"+strAlbumName+"</a></div>");
				sb.append("	<div><a href=\"#\" onclick=\"javaScript:top.location.href='creatorProfile.do?creatorID="+strCreatorId+"'\">"+ strAlbumCreator + "</a></div>");
				sb.append("</td>");
				sb.append("<td>");
				sb.append("	<div>");
				sb.append("	<img src=\""+request.getContextPath()+"/images/repair.png\"  />");
				sb.append("	<ui class=\"owap\">");
				sb.append("		<li><a href=\""+request.getContextPath()+LYRIC_URL+strSongId+"\" >歌詞</a></li>");
				sb.append("		<li><a href=\"#\" onclick=\"javaScript:window.open('offense.do?userId="+strCreatorId+"&productionCategoryId="+strSongId+"','son','height=300,width=600,location=no,scrollbars=no,toolbar=no,directories=no,menubar=no,directories=no,status=no,titlebar=no')\">檢舉</a></li>");
				sb.append("	</ui>");
				sb.append("	</div>");
				sb.append("</td>");
				sb.append("<td>");
				sb.append("	<a>Like</a>");
				sb.append("</td>");
				sb.append("<td>");
				sb.append("	<a>價格1</a>");
				sb.append("</td>");
				sb.append("<td><a>價格2</a></td>");
				sb.append("</tr>");
			
			}
			sb.append("</table>");
			
			out.print(sb.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

}
