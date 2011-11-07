package com.ubn.befamous.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.log4j.Logger;
import com.ubn.befamous.entity.Album;

public class AlbumTableTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8294307498535178928L;
	Logger logger = Logger.getLogger(AlbumTableTag.class);

	private final static int MAX_COLUMN = 3;
	private final static int MAX_ROW = 4;
	private final static int MAX_SIZE_ONE_PAGE = MAX_COLUMN * MAX_ROW;
	private String listName = "";
	
	
	private final static String ARTIST_URL="/artist/";
	private final static String ALBUM_URL="/album/update/";
	private final static String LISTEN_URL="";
	private final static String BUY_URL="";	
	
	public int doStartTag() {
		try {

			String strNow = String.valueOf(System.currentTimeMillis());

			HttpServletRequest request = (HttpServletRequest) pageContext
					.getRequest();
			Object o = request.getAttribute(this.getListName());

				String imagePath = request.getSession().getServletContext().getInitParameter("ImageWeb");
				
				Album[] albums = (Album[]) o;
				int size = albums.length;

				int maxPage = size % MAX_SIZE_ONE_PAGE == 0 ? size
						/ MAX_SIZE_ONE_PAGE : (size / MAX_SIZE_ONE_PAGE + 1);
				System.out.println("maxPage=" + maxPage);

				int row = size % MAX_COLUMN == 0 ? size / MAX_COLUMN : (size
						/ MAX_COLUMN + 1);
				if (row > MAX_ROW) {
					row = MAX_ROW;
				}
				System.out.println(imagePath);
				String strTableId = "table" + strNow;
				JspWriter out = pageContext.getOut();
				StringBuilder sb = new StringBuilder();
				sb.append("<table border=\"0\" width=\"100%\" id=\""
						+ strTableId + "\">");
				for (int j = 0; j < row; j++) {
					sb.append("          <tr>");
					for (int i = 0; i < MAX_COLUMN; i++) {
						if (j * MAX_COLUMN + i >= size) {
							sb.append("          			<td>&nbsp;	</td>");
						} else {
							Album album=albums[j* MAX_COLUMN + i];
							String strAlbumName = album.getName();
							String strAlbumId = String.valueOf(album.getAlbumID());
							String strAlbumCreator = album.getCreator().getUserName();
							String strCreatorId = String.valueOf(album.getCreator().getMemberId());
							String strAlbumDate = album.getDate();
							String strCoverImage = album.getCover();

							sb.append("          			<td>");
							sb.append("<div>");
							sb.append("<div class=\"boxgrid captionfull\">");
							sb.append("<img src=\"/"+imagePath+"/"+ strCoverImage+ "\" reduce=\"true\" _w=\"100\" _h=\"100\"/>");
							sb.append("<div class=\"cover boxcaption\">");
							sb.append("<p><a class=\"p1\" href=\"#\" target=\"_BLANK\">試聽</a><a class=\"p2\" href=\"#\" target=\"_BLANK\">購買</a></p>");
							sb.append("</div>");
							sb.append("</div>");
							sb.append("<div class=\"labeltext\">album:<a href=\"#\" onclick=\"javaScript:top.location.href='queryAlbumData.do?albumid="+strAlbumId+"'\">"+ strAlbumName + "</a></div>");
							sb.append("<div class=\"labeltext\">artist:<a href=\"#\" onclick=\"javaScript:top.location.href='creatorProfile.do?creatorId="+strCreatorId+"'\">"+ strAlbumCreator + "</a></div>");
							sb.append("<div class=\"labeltext\">date:"+ strAlbumDate + "</a></div>");

							sb.append("</div>");

							sb.append("          			</td>");
						}
					}
					sb.append("          </tr>");
				}
				sb.append("</table>");
				for(int i=0;i<maxPage;i++){
					sb.append("<a onclick=\"javaScript:changePage("+i+");\">第"+(i+1)+"頁</a>");
				}
				
				//JavaScript Begin
				sb.append("<script>");
				sb.append(" var dataId=new Array();");
				for(int i=0;i<maxPage;i++){
					sb.append("dataId["+i+"]=["+getPageInfo(albums,i)+"];");
				}
				// 參考http://api.jquery.com/jQuery.param/
				sb.append("jQuery.ajaxSettings.traditional = true;");
				// 參考:http://www.w3school.com.cn/jquery/ajax_ajax.asp
				sb.append("		function changePage(e) { ");
				sb.append("			$.ajax({");
				sb.append("					url: 'getInfo',");
				sb.append("					data:{\"id\":dataId[e]},");
				sb.append("					dataType: 'json',");
				sb.append("					error: function(xhr) {");
				sb.append("								alert('Ajax request 發生錯誤');");
				sb.append("					},");
				sb.append("					success: function(response) {");
				sb.append("								$('#" + strTableId + "').empty();"); // 清空
				sb.append("								var newTable='';"); // 組新的table

				sb.append("								for (var x = 0; x < response.length; x++) { ");
				sb.append("								if(x%" + MAX_COLUMN
						+ "==0) {newTable+=\"<tr>\";} ");
				sb.append("								newTable+=\"<td width='33%'>\";");
				sb.append("								newTable+=\"<div>\";");
				sb.append("								newTable+=\"<div class='boxgrid captionfull'>\";");
				sb.append("                            	newTable+=\"<img src='http://localhost/ImageWeb/\"+response[x].coverImage+\"' reduce='true' _w='100' _h='100'/>\";");
				sb.append("                            	newTable+=\"<div class='cover boxcaption'>\";");
				sb.append("                            	newTable+=\"<p><a class='p1' href='' target='_BLANK'>試聽</a><a class='p2' href='購買' target='_BLANK'>購買</a></p>\";");
				sb.append("                            	newTable+=\"</div>\";");
				sb.append("                            	newTable+=\"</div>\";");
				sb.append("                            	newTable+=\"<div class='labeltext'><a href='"+request.getContextPath()+""+ALBUM_URL+"\"+response[x].albumId+\"'>\"+response[x].albumName+\"</a></div>\";");
				sb.append("                            	newTable+=\"<div class='labeltext' ><a href='#'>創作者名稱</a></div>\";");
				sb.append("                            	newTable+=\"<div class='labeltext'><a href='#'>出版日期</a></div>\";");
				sb.append("                            	newTable+=\"</div>\";");
				sb.append("								newTable+=\"</td>\";");
				sb.append("								if(x%" + MAX_COLUMN + "==("+ (MAX_COLUMN - 1) + ")) {newTable+=\"</tr>\";}");
				sb.append("								}");
				sb.append("								if(response.length%" + MAX_COLUMN + "!=0) {");
				sb.append("									var subNumber=" + MAX_COLUMN	+ "-(response.length%" + MAX_COLUMN + ");");
				sb.append("									for (var x = 0; x <subNumber; x++) { ");
				sb.append("										newTable+=\"<td  width='33%'>&nbsp;</td>\";");
				sb.append("									}");
				sb.append("									newTable+=\"</tr>\";");
				sb.append("  							}");
				sb.append("                             $('#" + strTableId+ "').html(newTable);");
				sb.append("								imageHover();");
				//
				// sb.append("                           	$('#"+strTableId+" tr  td > div ').each(function(i){");
				// sb.append("                            		alert( $(this).html());");
				// sb.append("                            		$(this).find('div:first-child').addClass('boxgrid captionfull');");
				// sb.append("                            });");
				sb.append(" 					}");
				sb.append("			});");
				sb.append("}");
				sb.append("</script>");
				out.print(sb.toString());
		

		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}
	
	/**
	 * 取出該分頁有多少的資訊
	 * @param albumList
	 * @param page
	 * @return
	 */
	private String getPageInfo(Album[] albums, int page) {
		int start = page * MAX_SIZE_ONE_PAGE; // 例如page1 start=0;
		int end = (page + 1) * MAX_SIZE_ONE_PAGE; // 例如page1
													// end=MAX_SIZE_ONE_PAGE
		if (end >albums.length) { // 假如最後比專輯Total Size還大,則以Size為主
			end = albums.length;
		}
		StringBuilder sb=new StringBuilder();
		for (int i = start; i < end; i++) {
			Album album=albums[i];
//			String strAlbumName = albumMap.get("albumName");
			String strAlbumId = String.valueOf(album.getAlbumID());
//			String strCoverImage = albumMap.get("coverImage");
			if(i!=start){
				sb.append(",");
			}
			sb.append("\"").append(strAlbumId).append("\"");
		}
		return sb.toString();
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}
	
	
}
