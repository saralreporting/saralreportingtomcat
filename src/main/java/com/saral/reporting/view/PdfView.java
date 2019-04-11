package com.saral.reporting.view;

import com.saral.reporting.utils.JsonUtils;
import com.saral.reporting.view.AbstractPdfView;
import com.itextpdf.text.*;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Map;

public class PdfView extends AbstractPdfView {

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sign_no = (String) request.getSession().getAttribute("sign_no");
		response.setHeader("Content-Disposition", "attachment; filename="+sign_no+"_"+JsonUtils.FileNameDate()+".pdf");
		System.out.println("I am here at first line");
		Map<String,Object> map = (Map<String, Object>) model.get("applInfoJsonwithGroupByForPDF");
		JSONArray output = (JSONArray) map.get("applInfoJsonForPDF");
		//String rpName = (String) map.get("reportName");
		//String reportPurpose = (String) map.get("reportPurpose");
		//String organisationName = (String) map.get("organisationName");tableColor
		document.add(new Paragraph("Report Name : " + (String) map.get("reportName")));
		document.add(new Paragraph("Report Purpose : " + (String) map.get("reportPurpose")));
		document.add(new Paragraph("Department Name : " + (String) map.get("organisationName")));
		Long servID = (Long) request.getSession().getAttribute("service_id");
		if((servID != 0L) && (servID != 1L) && (servID != null)){
			document.add(new Paragraph("Service Name : " + (String) map.get("serviceName")));
		}
		document.add(new Paragraph("Report Generated On : " + LocalDate.now()));
		document.add(new Paragraph("Total Records = " + output.length()));

		System.out.println("I am here at second line");

		JSONObject json = output.getJSONObject(0);

		Iterator<String> keys = json.keys();
		int i = 0;
		while (keys.hasNext()) {
			keys.next();
			
			i++;
		}

		System.out.println("I am here at third line" + i);
		PdfPTable table = new PdfPTable(i);
		table.setWidthPercentage(100.0f);
		table.setSpacingBefore(10);

		// define font for table header row
		Font font = FontFactory.getFont(FontFactory.TIMES);
		font.setColor(BaseColor.WHITE);

		// define table header cell
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.DARK_GRAY);
		cell.setPadding(5);
		System.out.println("I am here at header section");
		// write table header
		Iterator<String> keys3 = json.keys();

		while (keys3.hasNext()) {
			String keyNew = keys3.next();
			cell.setPhrase(new Phrase(keyNew, font));
			table.addCell(cell);
		
		}

		System.out.println("I am here one two three output.length()");
		String tableColor = (String) map.get("tableColor");
		PdfPCell cellfordata = new PdfPCell();
		for (int j = 0; j < output.length(); j++) {
			JSONObject json2 = output.getJSONObject(j);

			Iterator<String> keys2 = json.keys();
			while (keys2.hasNext()) {
				String key2 = keys2.next();
				
				Object val = json2.get(key2);
				//System.out.println("key:"+key2 +" val:" +val);
				cellfordata.setPhrase(new Phrase(val.toString()));
				cellfordata.setBackgroundColor(WebColors.getRGBColor(tableColor));
				table.addCell(cellfordata);
				//table.addCell(val.toString());
			}

		}
		JSONArray output1 = (JSONArray) map.get("groupByDataForPDF");
	
		
		JSONObject json1 = output1.getJSONObject(0);

		Iterator<String> keys1 = json1.keys();
		int k=0;
		while (keys1.hasNext()) {
			String key = keys1.next();
			System.out.println(key);
			k++;
		}

		System.out.println("I am here at third line" + k);
		PdfPTable table1 = new PdfPTable(k);
		table1.setWidthPercentage(100.0f);
		table1.setSpacingBefore(10);

		// define font for table header row
		Font font1 = FontFactory.getFont(FontFactory.TIMES);
		font1.setColor(BaseColor.WHITE);

		// define table header cell
		PdfPCell cell1 = new PdfPCell();
		cell1.setBackgroundColor(BaseColor.DARK_GRAY);
		cell1.setPadding(5);
		System.out.println("I am here at header section");
		// write table header
		Iterator<String> keys31 = json1.keys();

		while (keys31.hasNext()) {
			String keyNew = keys31.next();
			cell1.setPhrase(new Phrase(keyNew, font1));
			table1.addCell(cell1);
		
		}

		System.out.println("I am here one two three output.length()");

		for (int j = 0; j < output1.length(); j++) {
			JSONObject json2 = output1.getJSONObject(j);

			Iterator<String> keys2 = json1.keys();
			while (keys2.hasNext()) {
				String key2 = keys2.next();
				
				Object val = json2.get(key2);
				
				table1.addCell(val.toString());
			}

		}
		document.add(table);
		document.add(new Paragraph("Group By Details"));
		document.add(new Paragraph("Generated Report " + LocalDate.now()));
		document.add(new Paragraph("Total Records = " + output1.length()));
		document.add(table1);

	}

}
