package com.saral.reporting.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.AbstractView;

import com.itextpdf.text.Paragraph;
import com.saral.reporting.controller.FileDownloadController;
import com.saral.reporting.utils.JsonUtils;

public class ExcelViewReport extends AbstractView {
	@Autowired
	FileDownloadController fileDownloadController;

	protected void buildExcelDocument(LinkedHashMap<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"reportfile.xls\"");
		XSSFWorkbook workbook1 = new XSSFWorkbook();

		// List<ApplInfoJson> appInfJsns = (List<ApplInfoJson>)
		// model.get("applInfoJsonForExcel");

		JSONArray output = (JSONArray) model.get("applInfoJsonForExcel");
		XSSFSheet sheet = workbook1.createSheet("Report Detail");
		XSSFRow header = sheet.createRow(0);

		JSONObject json = output.getJSONObject(0);
		@SuppressWarnings("unchecked")
		Iterator<String> keys = json.keys();
		int i = 0;
		while (keys.hasNext()) {
			String key = keys.next();
			header.createCell(i).setCellValue(key);
			System.out.println(key);
			i++;
		}

		int rowCount = 1;
		System.out.println("I am here one two three");

		for (int j = 0; j < output.length(); j++) {
			XSSFRow jsnRow = sheet.createRow(rowCount++);
			JSONObject json2 = output.getJSONObject(j);
			@SuppressWarnings("unchecked")
			Iterator<String> keys2 = json2.keys();

			int k = 0;
			while (keys2.hasNext()) {
				String key2 = keys2.next();
				Object val = json2.get(key2);
				jsnRow.createCell(k).setCellValue(val.toString());
				k++;
			}
		}

		// workbook.save("FinancialKPI.pdf", SaveFileFormat.Pdf);

		/*
		 * ExcelDocument excelDoc = new ExcelDocument("input.xlsx", new
		 * ExcelConvertOptions());
		 * 
		 * // Save the document as a PDF file excelDoc.saveAsPDF("output.pdf");
		 */

	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SXSSFWorkbook workbook1 = new SXSSFWorkbook(200);

		// List<ApplInfoJson> appInfJsns = (List<ApplInfoJson>)
		// model.get("applInfoJsonForExcel");
		String sign_no = (String) request.getSession().getAttribute("sign_no");
		System.out.println(sign_no);
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) model.get("applInfoJsonWithGroupByForExcel");
		if (map.get("applInfoJsonForExcel").toString().length() > 2) {
			JSONArray output = (JSONArray) map.get("applInfoJsonForExcel");
			Sheet sheet = workbook1.createSheet("Report Detail");
			
			XSSFCellStyle style = (XSSFCellStyle) workbook1.createCellStyle();
			//style.setBorderTop((short) 1); // double lines border
			//style.setBorderBottom((short) 1); // single line border
			//style.setBorderLeft((short) 1);
			//style.setBorderRight((short) 1);
			XSSFFont font = (XSSFFont) workbook1.createFont();
			font.setFontHeightInPoints((short) 12);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);      
			style.setAlignment(HorizontalAlignment.CENTER);
			
			
			XSSFCellStyle styleHeader = (XSSFCellStyle) workbook1.createCellStyle();
			styleHeader.setBorderTop((short) 1); // double lines border
			styleHeader.setBorderBottom((short) 1); // single line border
			styleHeader.setBorderLeft((short) 1);
			styleHeader.setBorderRight((short) 1);
			XSSFFont fontHeader = (XSSFFont) workbook1.createFont();
			fontHeader.setFontHeightInPoints((short) 12);
			fontHeader.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			styleHeader.setFont(fontHeader);      
			styleHeader.setAlignment(HorizontalAlignment.CENTER);
			
			Row reportName = sheet.createRow(0);
			reportName.createCell(0).setCellValue("Report Name : " + (String) map.get("reportName"));
			//reportName.getCell(0).setCellStyle(style);
			
			Row reportPurpose = sheet.createRow(1);
			reportPurpose.createCell(0).setCellValue("Report Purpose : " + (String) map.get("reportPurpose"));
			reportPurpose.getCell(0).setCellStyle(style);

			
			Row DepartmentName = sheet.createRow(2);
			DepartmentName.createCell(0).setCellValue("Department Name : " + (String) map.get("organisationName"));
			DepartmentName.getCell(0).setCellStyle(style);

			
			Long servID = (Long) request.getSession().getAttribute("service_id");
			if((servID != 0L) && (servID != 1L) && (servID != null)){
				Row ServiceName = sheet.createRow(3);
				ServiceName.createCell(0).setCellValue("Service Name : " + (String) map.get("serviceName"));
				ServiceName.getCell(0).setCellStyle(style);
			}
			
			Row header = sheet.createRow(5);

			JSONObject json = output.getJSONObject(0);
			System.out.println(json);
			@SuppressWarnings("unchecked")
			Iterator<String> keys = json.keys();
			int i = 0;
			List<String> columns = new ArrayList<String>();
			while (keys.hasNext()) {
				String key = keys.next();
				System.out.println(key);
				header.createCell(i).setCellValue(key);
				header.getCell(i).setCellStyle(styleHeader);
				columns.add(key);
				i++;
			}
			System.out.println("collllllllllllllllllllllllllll"+ columns);
			
			Row firstRow = sheet.getRow(5);
			int colNum = firstRow.getLastCellNum();
			sheet.addMergedRegion(new CellRangeAddress(0,0,0,colNum-1));
			reportName.getCell(0).setCellStyle(style);
			sheet.addMergedRegion(new CellRangeAddress(1,1,0,colNum-1));
			sheet.addMergedRegion(new CellRangeAddress(2,2,0,colNum-1));
			sheet.addMergedRegion(new CellRangeAddress(3,3,0,colNum-1));
			int rowCount = 6;
			System.out.println("I am here one two three");

			for (int j = 0; j < output.length(); j++) {
				Row jsnRow = sheet.createRow(rowCount++);
				JSONObject json2 = output.getJSONObject(j);
				//@SuppressWarnings("unchecked")
				Iterator<String> keys2 = columns.iterator();

				int k = 0;
				while (keys2.hasNext()) {
					String key2 = keys2.next();
				
					Object val = json2.get(key2);
					
					jsnRow.createCell(k).setCellValue(val.toString());
					k++;
				}
			}
			
			
			if(map.get("groupByDataForExcel").toString().length()>2) {
				JSONArray jsonGroupArray = (JSONArray) map.get("groupByDataForExcel");
				// List<String> myList = new ArrayList<String>(Arrays.asList(list.split(",")));
logger.info("jsonGroupArray"+jsonGroupArray);
			
				Sheet sheet2 = workbook1.createSheet("Group By details");
				Row header1 = sheet2.createRow(0);

				JSONObject jsonGroup = jsonGroupArray.getJSONObject(0);
				@SuppressWarnings("unchecked")
				Iterator<String> keysGroup = jsonGroup.keys();
				int j = 0;
				List<String> columnsgroup = new ArrayList<String>();
				while (keysGroup.hasNext()) {
					String key2 = keysGroup.next();
					header1.createCell(j).setCellValue(key2);
					System.out.println(keysGroup);
					columnsgroup.add(key2);
					j++;
				}

				int rowCount1 = 1;
				System.out.println("I am here one two three");

				for (int k = 0; k < jsonGroupArray.length(); k++) {
					Row jsnRow = sheet2.createRow(rowCount1++);
					JSONObject json2 = jsonGroupArray.getJSONObject(k);
					Iterator<String> keys2 = columnsgroup.iterator();

					int f = 0;
					while (keys2.hasNext()) {
						String key2 = keys2.next();
						Object val = json2.get(key2);
						jsnRow.createCell(f).setCellValue(val.toString());
						f++;
					}
				}
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=" + sign_no+"_"+ JsonUtils.FileNameDate()+ ".xlsx");
			workbook1.write(response.getOutputStream());

			workbook1.dispose();

			// response.setHeader("Content-Disposition", "attachment;
			// filename=Report.xlsx");

		}}

	}
}
