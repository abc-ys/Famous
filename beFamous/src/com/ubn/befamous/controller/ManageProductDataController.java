package com.ubn.befamous.controller;

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

import com.ubn.befamous.service.TransactionRecordService;

@Controller
public class ManageProductDataController {
	@Autowired
	private TransactionRecordService transactionRecordService;
	
	//商品管理-新增商品資料
	@RequestMapping("/addProductData")
	public ModelAndView addproductdata() {
		ModelAndView mav = new ModelAndView("addProductData");		
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

		String saveDirectory = "C:/Users/Lucy/workspace2/test";  //存放的路徑

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
		return new ModelAndView("manageProductCategory", "productionClassification",this.transactionRecordService.queryProductionClassification());

	}

	//商品管理-商品類別管理-新增商品類別或子類別
	@RequestMapping("/addProductCategory")
	public String addproductcategory(String productionClassificationName) {		
		this.transactionRecordService.addProductionClassification(productionClassificationName);
		return "redirect:manageProductCategory.do";
	}
		
	//商品管理-商品類別管理-編輯商品類別或子類別
	@RequestMapping("/editProductCategory")
	public ModelAndView editproductcategory(long productionClassificationId, Model model) {
		ModelAndView mav = new ModelAndView("editProductCategory");
		model.addAttribute("productionClassificationId", productionClassificationId);
		return mav;
	}
		
	//商品管理-商品類別管理-關閉編輯商品類別或子類別的視窗
	@RequestMapping("/editProductCategoryClose")
	public ModelAndView editproductcategoryclose(long productionClassificationId, String productionClassificationName) {
		ModelAndView mav = new ModelAndView("editProductCategoryClose");
		this.transactionRecordService.editProductionClassification(productionClassificationId, productionClassificationName);
		return mav;
	}
		
	//商品管理-商品類別管理-刪除商品類別或子類別
	@RequestMapping("/deleteProductCategory")
	public String deleteproductcategory(long productionClassificationId) {
		System.out.println("刪除類別");
		this.transactionRecordService.deleteProductionClassification(productionClassificationId);
		return "redirect:manageProductCategory.do";
	}
	
	//商品管理-查詢商品頁面
	@RequestMapping("/queryProduct")
	public ModelAndView queryproduct() {
		System.out.println("queryproduct==>");
		return new ModelAndView("queryProduct", "productionClassification",this.transactionRecordService.queryProduct());
	}
	
	//商品管理-查詢商品資料
	@RequestMapping("/queryProductData")
	public ModelAndView queryproductdata(long productionClassificationId) {
		System.out.println("productCategory==>");
		return new ModelAndView("queryProductData", "productionClassification",this.transactionRecordService.queryProductInfo(productionClassificationId));
	}
	
	//商品管理-批次調整商品資料
	@RequestMapping("/allChange")
	public ModelAndView allchange(long productionClassificationId, String condition, double rate) {
		System.out.println("allchange==>");
		this.transactionRecordService.updateProductBatch(productionClassificationId, condition, rate);
		return this.queryproductdata(productionClassificationId);
	}
	
	/*//商品管理-儲存商品頁修改
	@RequestMapping("/saveModify")
	public ModelAndView savemodify(long productId, String status, String realPrice, String specialPrice, long productionClassificationId) {
		this.transactionRecordService.saveModify(productId, realPrice, specialPrice, status, productionClassificationId);
		return this.queryproductdata(productionClassificationId);
	}*/
	
	//商品管理-編輯商品詳細資料頁
	@RequestMapping("/productDetailData")
	public ModelAndView editdata(long productId) {
		System.out.println("productDetailData==>");
		return new ModelAndView("productDetailData", "production",this.transactionRecordService.queryProductDetail(productId));
	}
	
	//商品管理-儲存修改商品詳細資料頁
	@RequestMapping("/updateProductDetailData")
	public ModelAndView updatedata(long adminId, long productId, String productName,long newProductionClassificationId, String transactionType, String realPrice,String specialPrice, String discountPrice, String discountBonus, String giveBonus, String stock, String status, String keyword, String memo, long productionClassificationId) {	
		this.transactionRecordService.updateProduct(adminId, productId, productName, newProductionClassificationId, transactionType, realPrice, specialPrice, discountPrice, discountBonus, giveBonus, stock, status, keyword, memo, productionClassificationId);
		return this.queryproductdata(productionClassificationId);
	}
}
