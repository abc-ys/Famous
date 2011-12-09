package com.ubn.befamous.controller;


import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hpsf.*;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ubn.befamous.entity.PrePaid;
import com.ubn.befamous.entity.PrePaidPrice;
import com.ubn.befamous.entity.ProductionClassification;
import com.ubn.befamous.entity.SDCard;
import com.ubn.befamous.entity.SDCardPrice;
import com.ubn.befamous.service.PersonService;
import com.ubn.befamous.service.TransactionRecordService;

@Controller
public class ManageProductDataController {
	@Autowired
	private TransactionRecordService transactionRecordService;
	
	@Autowired
	private PersonService personService;
	
	//商品管理-新增商品資料
		@RequestMapping("/addProductData")
		public ModelAndView addproductdata() {
			ModelAndView mav = new ModelAndView("addProductData");		
			return mav;
		}
		
		//商品管理-新增商品資料-上傳檔案
		@RequestMapping("/handleUploadFile")
		public String handleUploadFile(HttpServletRequest request) throws Exception {
			
			int yourMaxMemorySize = 500 * 500* 1024;
			File yourTempDirectory = new File("/tmp");
			int yourMaxRequestSize = 500 * 500* 1024;
			boolean writeToFile = true;
			String allowedFileTypes = ".xls .xlsx";

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
						System.out.println("fieldName=" + fieldName + "<br />");
						System.out.println("fileName=" + fileName + "<br />");

						if (fileName != null && !"".equals(fileName)) {
							
				            System.out.println("=======開始匯入Excel=========");
					        XSSFWorkbook xb ;  
					        //String filePath = "D:/23C34000.xlsx";        
					        xb = new XSSFWorkbook(fileName); 
					        XSSFSheet sheet = xb.getSheetAt(0); // 取得Excel第一個sheet(從0開始)
					        
					        for (int i = 1; i < sheet.getPhysicalNumberOfRows() ; i++){
					        	XSSFRow row = sheet.getRow(i); // 取得第 i Row 
					        	    PrePaid prepaid = new PrePaid();
							        PrePaidPrice prepaidPrice = new PrePaidPrice();
							        SDCard sdcard = new SDCard();
							        SDCardPrice sdcardPrice = new SDCardPrice();
					        	    
					        		String productName     = row.getCell(1).toString();
					        		String classification  = ""+(int)(row.getCell(2).getNumericCellValue());
					        		String companyName     = row.getCell(3).toString();
					        		String amount          = ""+(int)(row.getCell(6).getNumericCellValue());
					        		String price           = ""+(int)(row.getCell(7).getNumericCellValue());
					        		String specialprice    = ""+(int)(row.getCell(8).getNumericCellValue());
					        		String reward          = ""+(int)(row.getCell(10).getNumericCellValue());
					        		String keyword         = row.getCell(13).toString();
					        		String introduction    = row.getCell(14).toString();
					        		
					        		
					        		System.out.println("productName==>"+productName);
					        		System.out.println("classification==>"+classification);
					        		System.out.println("price==>"+price);
					        		System.out.println("specialprice==>"+specialprice);
					        		System.out.println("introduction==>"+introduction);
					        		if("1".equals(classification)){
					        			sdcard.setName(productName);
					        			sdcard.setCompanyName(companyName);
					        			sdcard.setAmount(amount);
					        			sdcard.setKeyWord(keyword);
					        			sdcard.setIntroduction(introduction);
					        			
					        			sdcardPrice.setPrice(price);
					        			sdcardPrice.setSpecialPrice(specialprice);	
					        		}else if("2".equals(classification)){
					        			prepaid.setName(productName);
					        			prepaid.setReward(reward);
					        			prepaidPrice.setPrice(price);
					        		}
		
					        		personService.saveProduction(classification, sdcard, sdcardPrice,prepaid,prepaidPrice);
					        } 
					        
					        System.out.println("=======匯入Excel結束=========");
						}
					}
				}
			} catch (FileUploadBase.SizeLimitExceededException ex1) {
				System.out.println("上傳檔案超過最大檔案允許大小" + yourMaxRequestSize / (1024 * 1024) + "MB !");
			}
			
			return "redirect:addProductData.do";
		}
		
		//商品管理-商品類別管理
		@RequestMapping("/manageProductCategory")
		public ModelAndView manageproductcategory(long adminId) {
			ModelAndView mav = new ModelAndView("manageProductCategory");
			mav.addObject("productionClassification", this.transactionRecordService.queryProductionClassification());
			mav.addObject("adminId", adminId);
			return mav;

		}

		//商品管理-商品類別管理-新增商品類別或子類別
		@RequestMapping("/addProductCategory")
		public String addproductcategory(String productionClassificationName,long adminId) {	
			this.transactionRecordService.addProductionClassification(adminId, productionClassificationName);
			return "redirect:manageProductCategory.do?adminId="+adminId;
		}
			
		//商品管理-商品類別管理-編輯商品類別或子類別
		@RequestMapping("/editProductCategory")
		public ModelAndView editproductcategory(long productionClassificationId, long adminId, Model model) {
			ModelAndView mav = new ModelAndView("editProductCategory");
			ProductionClassification productionClassification = this.transactionRecordService.queryOneProductionClassification(productionClassificationId);
			model.addAttribute("productionClassification", productionClassification);
			model.addAttribute("adminId", adminId);
			return mav;
		}
			
		//商品管理-商品類別管理-關閉編輯商品類別或子類別的視窗
		@RequestMapping("/editProductCategoryClose")
		public ModelAndView editproductcategoryclose(long adminId, long productionClassificationId, String productionClassificationName) {
			ModelAndView mav = new ModelAndView("editProductCategoryClose");
			this.transactionRecordService.editProductionClassification(adminId, productionClassificationId, productionClassificationName);
			return mav;
		}
			
		//商品管理-商品類別管理-刪除商品類別或子類別
		@RequestMapping("/deleteProductCategory")
		public String deleteproductcategory(long productionClassificationId, long adminId) {
			System.out.println("刪除類別");
			this.transactionRecordService.deleteProductionClassification(adminId, productionClassificationId);
			return "redirect:manageProductCategory.do?adminId="+adminId;
		}
		
		//商品管理-查詢商品頁面(起始查詢)
		@RequestMapping("/queryProduct")
		public ModelAndView queryproduct(long adminId) {
			System.out.println("queryproduct==>");
			ModelAndView mav = new ModelAndView("queryProduct");
			mav.addObject("productionClassification", this.transactionRecordService.queryProduct());
			mav.addObject("adminId", adminId);
			return mav;
		}
		
		//商品管理-查詢商品資料
		@RequestMapping("/queryProductData")
		public ModelAndView queryproductdata(long productionClassificationId, long adminId) {
			System.out.println("productCategory==>");
			ModelAndView mav = new ModelAndView("queryProductData");
			mav.addObject("productionClassificationId", productionClassificationId);
			mav.addObject("adminId", adminId);
			mav.addObject("productionClassification", this.transactionRecordService.queryProductInfo(productionClassificationId));
			return mav;
		}
		
		//商品管理-批次調整商品資料
		@RequestMapping("/allChange")
		public ModelAndView allchange(long adminId, long productionClassificationId, String condition, double rate) {
			System.out.println("allchange==>");
			System.out.println("productionClassificationId==>"+productionClassificationId);
			this.transactionRecordService.updateProductBatch(adminId, productionClassificationId, condition, rate);
			return this.queryproductdata(productionClassificationId,adminId);
		}
		
		//商品管理-編輯商品詳細資料頁
		@RequestMapping("/productDetailData")
		public ModelAndView editdata(long productId, long productionClassificationId,long adminId) {
			System.out.println("productDetailData==>");
			ModelAndView mav = new ModelAndView("productDetailData");
			mav.addObject("productionClassificationId", productionClassificationId);
			mav.addObject("production", this.transactionRecordService.queryProductDetail(productId));
			mav.addObject("adminId", adminId);
			return mav;
		}
		
		//商品管理-儲存修改商品詳細資料頁
		@RequestMapping("/updateProductDetailData")
		public ModelAndView updatedata(long adminId, long productId, String productName,long newProductionClassificationId, String transactionType, String realPrice,String specialPrice, String discountPrice, String discountBonus, String giveBonus, String stock, String status, String keyword, String memo, long productionClassificationId) {	
			this.transactionRecordService.updateProduct(adminId, productId, productName, newProductionClassificationId, transactionType, realPrice, specialPrice, discountPrice, discountBonus, giveBonus, stock, status, keyword, memo, productionClassificationId);
			return this.queryproductdata(productionClassificationId, adminId);
		}
}
