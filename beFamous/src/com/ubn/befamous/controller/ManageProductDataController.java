package com.ubn.befamous.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.MusicCategory;
import com.ubn.befamous.entity.Order;
import com.ubn.befamous.entity.ProductionCategory;
import com.ubn.befamous.entity.SDCard;
import com.ubn.befamous.entity.SDCardPrice;

@Controller
public class ManageProductDataController {

	//商品管理-新增商品資料
	@RequestMapping("/addProductData")
	public ModelAndView addproductdata(String year, String month) {
		ModelAndView mav = new ModelAndView("addProductData");
			
		System.out.println("year==>"+year);
		System.out.println("month==>"+month);
			
			
		return mav;
	}
	
	//商品管理-新增商品資料-上傳檔案
	@RequestMapping("/handleUploadFile")
	public ModelAndView handleUploadFile(HttpServletRequest request) throws Exception {
		
		int yourMaxMemorySize = 500 * 500* 1024;
		File yourTempDirectory = new File("/tmp");
		int yourMaxRequestSize = 500 * 500* 1024;
		boolean writeToFile = true;
		String allowedFileTypes = ".xls .xlsx";

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
					System.out.println("fieldName=" + fieldName + "<br />");
					System.out.println("fileName=" + fileName + "<br />");
					System.out.println("contentType=" + contentType + "<br />");
					System.out.println("isInMemory=" + isInMemory + "<br />");
					System.out.println("sizeInBytes=" + sizeInBytes + "<br />");
					
				      
					if (fileName != null && !"".equals(fileName)) {
						if (writeToFile) {
							// 副檔名
							//String formatName = fileName.substring(fileName.length() - 4,fileName.length());
							//fileName = (formatName).toLowerCase();
							fileName = FilenameUtils.getName(fileName);
						      
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
		return new ModelAndView("addProductData");
	}
	
	//商品管理-商品類別管理
	@RequestMapping("/manageProductCategory")
	public ModelAndView manageproductcategory() {
		ModelAndView mav = new ModelAndView("manageProductCategory");
		
		ProductionCategory productionCategory = new ProductionCategory();
		productionCategory.setProductionCategoryName("4G GSiSD卡");
		ProductionCategory productionCategory2 = new ProductionCategory();
		productionCategory2.setProductionCategoryName("儲值 300元");
		ProductionCategory[] pc = {productionCategory,productionCategory2};
		
		mav.addObject("productionCategory",pc);
		return mav;
	}

	//商品管理-商品類別管理-新增商品類別或子類別
	@RequestMapping("/addProductCategory")
	public String addproductcategory(String categoryName) {
		
		System.out.println("categoryName==>"+categoryName);
		
		return "redirect:manageProductCategory.do";
	}
		
	//商品管理-商品類別管理-編輯商品類別或子類別
	@RequestMapping("/editProductCategory")
	public ModelAndView editproductcategory() {
		ModelAndView mav = new ModelAndView("editProductCategory");
		return mav;
	}
		
	//商品管理-商品類別管理-關閉編輯商品類別或子類別的視窗
	@RequestMapping("/editProductCategoryClose")
	public ModelAndView editproductcategoryclose(String modifyName) {
		ModelAndView mav = new ModelAndView("editProductCategoryClose");
		
		System.out.println("modifyName==>"+modifyName);
		
		return mav;
	}
		
	//商品管理-商品類別管理-刪除商品類別或子類別
	@RequestMapping("/deleteProductCategory")
	public String deleteproductcategory() {

		System.out.println("刪除類別");
		
		return "redirect:manageProductCategory.do";
	}
	
	//商品管理-查詢商品資料
	@RequestMapping("/queryProductData")
	public ModelAndView queryproductdata(String productCategory) {
		ModelAndView mav = new ModelAndView("queryProductData");
		
		System.out.println("productCategory==>"+productCategory);
		
		SDCard sdCard = new SDCard();
		sdCard.setCompanyName("test company");
		sdCard.setAmount("60");
		sdCard.setReward("原價x0.5");
		ProductionCategory productionCategory = new ProductionCategory();
		long p =45678912;
		productionCategory.setProductionCategoryRid(p);
		productionCategory.setProductionCategoryName("4G GSiSD卡");
		productionCategory.setSdCard(sdCard);
		
		ProductionCategory[] pc = {productionCategory};
		mav.addObject("productionCategory",pc);
		return mav;
	}
	
	//商品管理-批次調整商品資料
	@RequestMapping("/allChange")
	public String allchange(String percentage, String changePrice) {
			
		System.out.println("percentage==>"+percentage);
		System.out.println("changePrice==>"+changePrice);
			
		return "redirect:queryProductData.do";
	}
	
	//商品管理-儲存修改
	@RequestMapping("/saveModify")
	public String savemodify(String onsale, String price, String price2, String price3) {
					
		System.out.println("onsale==>"+onsale);
		System.out.println("price==>"+price);
		System.out.println("price2==>"+price2);
		System.out.println("price3==>"+price3);
					
		return "redirect:queryProductData.do";
	}
	
	//商品管理-編輯商品詳細資料頁
	@RequestMapping("/productDetailData")
	public ModelAndView editdata() {
		ModelAndView mav = new ModelAndView("productDetailData");
		
		SDCardPrice sdCardPrice = new SDCardPrice();
		sdCardPrice.setpPrice("399");
		sdCardPrice.setDiscountPrice("349");
		sdCardPrice.setDiscountBonus("50");
		SDCard sdCard = new SDCard();
		sdCard.setAmount("60");
		sdCard.setReward("0.5");
		sdCard.setSdCardPrice(sdCardPrice);
		sdCard.setKeyWord("4G, SD卡");
		sdCard.setIntroduction("用來存放音樂");
		ProductionCategory productionCategory = new ProductionCategory();
		long p =45678912;
		productionCategory.setProductionCategoryRid(p);
		productionCategory.setProductionCategoryName("4G GSiSD卡");
		productionCategory.setSdCard(sdCard);
		
		
		mav.addObject("productionCategory",productionCategory);
		
		return mav;
	}
	
	//商品管理-儲存修改商品詳細資料頁
	@RequestMapping("/updateProductDetailData")
	public String updatedata(String id, String name, String category, String dealMethod, String price, String price2, String price3, String price4, String amount, String onSale, String keyword, String msg) {
			
		System.out.println("id==>"+id);
		System.out.println("name==>"+name);
		System.out.println("category==>"+category);
		System.out.println("dealMethod==>"+dealMethod);
		System.out.println("price==>"+price);
		System.out.println("price2==>"+price2);
		System.out.println("price3==>"+price3);
		System.out.println("price4==>"+price4);
		System.out.println("amount==>"+amount);
		System.out.println("onSale==>"+onSale);
		System.out.println("keyword==>"+keyword);
		System.out.println("msg==>"+msg);
		
		return "redirect:queryProductData.do";
	}
}
