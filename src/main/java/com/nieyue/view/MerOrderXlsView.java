package com.nieyue.view;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.nieyue.bean.Acount;
import com.nieyue.bean.MerOrder;
import com.nieyue.business.AcountBusiness;
import com.nieyue.business.MerOrderBusiness;
import com.nieyue.util.DateUtil;


@Component("merOrderXlsView")
public class MerOrderXlsView extends AbstractXlsView{
	private String fileName;
	@Resource
	MerOrderBusiness merOrderBusiness;
	@Resource
	AcountBusiness acountBusiness;
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");  
        response.setContentType("application/vnd.ms-excel");  
        String fName = fileName+".xls";  
//      response.setContentType("APPLICATION/OCTET-STREAM");  
      //报头用于提供一个推荐的文件名，并强制浏览器显示保存对话框
        //attachment表示以附件方式下载。如果要在页面中打开，则改为 inline
        response.setHeader("Content-Disposition", "attachment; filename="+new String(fName.getBytes(),"iso8859-1")); 
			List<MerOrder> sheetList=(List<MerOrder>)model.get("merOrderList");
			//sheetList.forEach(System.out::println);
			Font f = workbook.createFont();      
			CellStyle dateStyle = workbook.createCellStyle();
			f.setFontHeightInPoints((short)11);;// 字号   
			f.setBoldweight(Font.BOLDWEIGHT_NORMAL);// 加粗   
			
			dateStyle.setFont(f);      
			dateStyle.setAlignment(CellStyle.ALIGN_CENTER);// 左右居中   
			dateStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 上下居中   
			dateStyle.setRotation((short) 0);;// 单元格内容的旋转的角度  
            Sheet sheet =  workbook.createSheet(fileName);
            Row headrow = sheet.createRow(0);
            Cell headrowcell0 = headrow.createCell(0);
            headrowcell0.setCellValue("序号");
            Cell headrowcell1 = headrow.createCell(1);
            headrowcell1.setCellValue("订单号");
            Cell headrowcell2 = headrow.createCell(2);
            headrowcell2.setCellValue("商品名称");
            Cell headrowcell3 = headrow.createCell(3);
            headrowcell3.setCellValue("单价");
            Cell headrowcell4 = headrow.createCell(4);
            headrowcell4.setCellValue("数量");
            Cell headrowcell5 = headrow.createCell(5);
            headrowcell5.setCellValue("总价");
            Cell headrowcell6 = headrow.createCell(6);
            headrowcell6.setCellValue("备注");
            Cell headrowcell7 = headrow.createCell(7);
            headrowcell7.setCellValue("快递公司");
            Cell headrowcell8= headrow.createCell(8);
            headrowcell8.setCellValue("快递单号");
            Cell headrowcell9 = headrow.createCell(9);
            headrowcell9.setCellValue("订单状态");
            Cell headrowcell10 = headrow.createCell(10);
            headrowcell10.setCellValue("收货人");
            Cell headrowcell11 = headrow.createCell(11);
            headrowcell11.setCellValue("收货手机号");
            Cell headrowcell12 = headrow.createCell(12);
            headrowcell12.setCellValue("收货地址");
            Cell headrowcell13 = headrow.createCell(13);
            headrowcell13.setCellValue("真实姓名");
            Cell headrowcell14 = headrow.createCell(14);
            headrowcell14.setCellValue("微信号");
            Cell headrowcell15 = headrow.createCell(15);
            headrowcell15.setCellValue("支付宝");
            Cell headrowcell16 = headrow.createCell(16);
            headrowcell16.setCellValue("创建时间");
            //设置行
            int currentRow=1;
            for (int i = 0; i <sheetList.size(); i++) {
            	MerOrder merOrder = sheetList.get(i);
                for (int j = 0; j < merOrder.getOrderMerList().size(); j++) {
                	currentRow+=j;
                	Row row = sheet.createRow(i+currentRow);
                	row.createCell(0).setCellValue(i+currentRow);
                	row.createCell(1).setCellValue(merOrder.getOrderNumber());
                	//涉及多个订单商品
                	row.createCell(2).setCellValue(merOrder.getOrderMerList().get(j).getMer().getTitle());
                	row.createCell(3).setCellValue(merOrder.getOrderMerList().get(j).getPrice());
                	row.createCell(4).setCellValue(merOrder.getOrderMerList().get(j).getNumber());
                	row.createCell(5).setCellValue(merOrder.getOrderMerList().get(j).getTotalPrice());
                	row.createCell(6).setCellValue(merOrder.getOrderMerList().get(j).getRemark()==null?"":merOrder.getOrderMerList().get(j).getRemark());
                	row.createCell(7).setCellValue(merOrderBusiness.getOrderMerCourierCompany(merOrder.getOrderMerList().get(j).getCourierCompany()));
                	row.createCell(8).setCellValue(merOrder.getOrderMerList().get(j).getCourierNumber()==null?"":merOrder.getOrderMerList().get(j).getCourierNumber());
                	row.createCell(9).setCellValue(merOrderBusiness.getOrderMerStatus(merOrder.getOrderMerList().get(j).getStatus()));
                	
                	
                	row.createCell(10).setCellValue(merOrder.getReceiptInfo().getName());
                	row.createCell(11).setCellValue(merOrder.getReceiptInfo().getPhone());
                	row.createCell(12).setCellValue(merOrder.getReceiptInfo().getAddress());
                	Acount acount = acountBusiness.getAcountByAcountId(merOrder.getAcountId());
                	row.createCell(13).setCellValue(acount.getRealname());
                	row.createCell(14).setCellValue(acount.getWechat());
                	row.createCell(15).setCellValue(acount.getAlipay());
                	row.createCell(16).setCellValue(DateUtil.dateFormatSimpleDate(merOrder.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
				}
	            }
           /* FileOutputStream output = new FileOutputStream(new File("E:/"+fName));
            workbook.write(output);
            output.close();
            response.reset(); */
	}
			


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}