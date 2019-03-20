package com.saral.reporting.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;
import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import com.itextpdf.text.DocumentException;
import com.saral.reporting.DAO.ReportViwerDAO;
import com.saral.reporting.model.ApplInfo;
import com.saral.reporting.model.ApplInfoJson;
import com.saral.reporting.model.HrOrgLocatedAtLevels;
import com.saral.reporting.model.HrOrgUnits;
import com.saral.reporting.model.ReportBean;
import com.saral.reporting.model.ReportSelectColumn;
import com.saral.reporting.service.ApplInfoJsonService;
import com.saral.reporting.service.HrOrgLocatedAtLevelsService;
import com.saral.reporting.service.HrOrgUnitsService;
import com.saral.reporting.service.ReportBeanService;
import com.saral.reporting.utils.JsonUtils;
import com.saral.reporting.utils.StringConstants;
import com.saral.reporting.view.CsvView;
import com.saral.reporting.view.ExcelViewReport;
import com.saral.reporting.view.PdfView;

@Controller
@RequestMapping(value = "/")
public class ReportExportController {

	@Autowired
	ApplInfoJsonService applInfoJsonService;

	@Autowired
	ReportBeanService reportBeanService;

	@Autowired
	FileDownloadController fileDownloadController;

	@Autowired
	HrOrgUnitsService hrOrgUnitsService;

	@Autowired
	ReportViwerDAO reportViwer;
	
	
	@Autowired
	HrOrgLocatedAtLevelsService hrOrgLocatedAtLevelsService;
	/*
	 * Code to export complete table
	 * 
	 * @RequestMapping(value="/reportExport", method=RequestMethod.GET) public
	 * ModelAndView mainListReport(HttpServletRequest res, HttpServletResponse
	 * rep){
	 * 
	 * Long repId = (Long) res.getSession().getAttribute("reportId"); Long
	 * servID = (Long) res.getSession().getAttribute("service_id");
	 * List<ApplInfoJson> applInfoJson =
	 * applInfoJsonService.findByServiceIdForExcel(servID);
	 * System.out.println("Service id and report id is:" + servID + " And " +
	 * repId ); return new ModelAndView(new ExcelViewReport(),
	 * "applInfoJsonForExcel", applInfoJson);
	 * 
	 * }
	 */

	// code for export to excel
	@RequestMapping(value = "/reportExport", method = RequestMethod.GET)
	public ModelAndView mainListReportExcel(HttpServletRequest res, HttpServletResponse rep) throws ParseException, IOException, JSONException {

		String output = CommonFunctionForExport(res, rep);
		String arr[] = output.split("===");
		JSONArray jsonArray = new JSONArray(arr[0]);
		JSONArray jsonArray1 = new JSONArray(arr[1]);
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("applInfoJsonForExcel", jsonArray);
		map.put("groupByDataForExcel", jsonArray1);

		return new ModelAndView(new ExcelViewReport(), "applInfoJsonWithGroupByForExcel", map);

	}

	// code for export to pdf
	@RequestMapping(value = "/reportExportPDF", method = RequestMethod.GET)
	public ModelAndView mainListReportPDF(HttpServletRequest res, HttpServletResponse rep) throws ParseException, IOException, JSONException {

		String output = CommonFunctionForExport(res, rep);

		String arr[] = output.split("===");
		JSONArray jsonArray = new JSONArray(arr[0]);
		JSONArray jsonArray1 = new JSONArray(arr[1]);
		Map<String, Object> map =  new LinkedHashMap<>();
		map.put("applInfoJsonForPDF", jsonArray);
		map.put("groupByDataForPDF", jsonArray1);

		return new ModelAndView(new PdfView(), "applInfoJsonwithGroupByForPDF", map);
	}

	// code for export to csv
	@RequestMapping(value = "/reportExportCSV", method = RequestMethod.GET)
	public ModelAndView mainListReportCSV(HttpServletRequest res, HttpServletResponse rep) throws ParseException, IOException, JSONException {

		String output = CommonFunctionForExport(res, rep);

		String arr[] = output.split("===");
		JSONArray jsonArray = new JSONArray(arr[0]);
		JSONArray jsonArray1 = new JSONArray(arr[1]);
		Map<String, Object> map =new LinkedHashMap<>();
		map.put("applInfoJsonForCSV", jsonArray);
		map.put("groupByDataForCSV", jsonArray1);

		return new ModelAndView(new CsvView(), "applInfoJsonWithGroupingForCSV", map);
	}

	// code for saving file to local and then send to client
	@RequestMapping(value = "/reportExportLocal", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public void mainListReportLocal(ModelMap model, HttpServletRequest res, HttpServletResponse rep)
			throws IOException, DocumentException, ParseException, JSONException {

		String sign_no = (String) res.getSession().getAttribute("sign_no");
		// response.setContentType("text/csv; charset=UTF-8");
		String output = CommonFunctionForExport(res, rep);
		
		String arr[] = output.split("===");
		JSONArray jsonArray = new JSONArray(arr[0]);

		String file_name = sign_no + "_" + JsonUtils.FileNameDate() + ".csv";
		File file = new File(StringConstants.FILE_PATH_DOWNLOAD_LOCAL + file_name);
		String csv = CDL.toString(jsonArray);

		FileUtils.write(file, csv);
		
		JSONArray jsonArray1 = new JSONArray(arr[1]);

		String file_name1 = "groupby" + "_" + JsonUtils.FileNameDate() + ".csv";
		File file1 = new File(StringConstants.FILE_PATH_DOWNLOAD_LOCAL + file_name1);
		String csv1 = CDL.toString(jsonArray1);

		FileUtils.write(file1, csv1);
		fileDownloadController.zipFiles(rep, file_name, file_name1,sign_no);

	}

	@SuppressWarnings("unchecked")
	public String CommonFunctionForExport(HttpServletRequest res, HttpServletResponse rep) throws ParseException, IOException, JSONException {
		Long department_id = (Long) res.getSession().getAttribute("department_id");
		Long repId = (Long) res.getSession().getAttribute("reportId");
		Long servID = (Long) res.getSession().getAttribute("service_id");
		List<Long> filterbserviceId = (List<Long>) res.getSession().getAttribute("filterbserviceId");
		List<Long> filterbdisttId = (List<Long>) res.getSession().getAttribute("filterbdisttId");
		//List<Long> filterbdeptId = (List<Long>) res.getSession().getAttribute("filterbdeptId");
		Long locationIdSession = (Long) res.getSession().getAttribute("location_Id");
	
		Long deptidwithNameSelectedBU = (Long) res.getSession().getAttribute("deptidwithNameSelectedBU");
		List<Long> nDeptIdName = new ArrayList<>();
		nDeptIdName.add(deptidwithNameSelectedBU);
		System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH ID inside View ->>>>>>>>>>>>>>>>>>>>" + res.getSession().getAttribute("deptidwithNameSelectedBU"));
		System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH ID inside View ->>>>>>>>>>>>>>>>>>>>" + nDeptIdName.size());
		System.out.println(servID);
		List<Long> getLocationList1 = new ArrayList<>();
		

		if ((servID != 0L) && (servID != 1L)) {
			String where = "";
			System.out.println("locaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + locationIdSession);
			if (locationIdSession != 0L) {
				getLocationList1 = getLocationList(locationIdSession, department_id);
				System.out.println(
						"Hkjsbdnfjskdbjsdbfjlsdbljsbdfjlbsdlblsb +++++++++++++++++++++++++++++++" + getLocationList1);
			}
			System.out.println("asas"+getLocationList1);
			String abc = "";
			ReportBean listReport = reportBeanService.findByReportId(repId);
			if (listReport.getWhereCondition().length() > 2) {
				System.out.println("where" + listReport.getWhereCondition());
				abc = JsonUtils.stringWhere(listReport.getWhereCondition(),servID);

			}

			String orderby = "";
			if (res.getSession().getAttribute("selectedCol") != null) {

				System.out.println("inside refresh report");
				String valToBePass = (String) res.getSession().getAttribute("selectedCol");
				System.out.println("********************************** " + valToBePass);
				orderby = JsonUtils.getSortingJoinerReport(valToBePass);
				System.out.println();
				if (abc == "" || abc.equals("")) {
					abc = " service_id ="+ servID;
				}
				System.out.println("hgeeeeeeeeeeeeeeeee"+ abc);
				where = abc.concat(orderby);
				
				System.out.println(abc + orderby);
				System.out.println("my complete query" + abc);

			} else if ((listReport.getOrderCondition().length() > 2) && (orderby == "")) {

				orderby = JsonUtils.getSortingJoinerReport(listReport.getOrderCondition());
				if (abc == "" || abc.equals("")) {
					abc = " service_id ="+ servID;
				}
				where = abc.concat(orderby);
				
				System.out.println(where);
				System.out.println("my complete query" + abc);
			}
			System.out.println("Ashish Sharma" + repId);
			System.out.println("Ashish Sharma" + servID);

			List<ApplInfoJson> list = reportViwer.findByCombinedJson(servID, getLocationList1, where);

			List<Map<String, Object>> listofMap = new ArrayList<>();

			List<ReportSelectColumn> L1 = listReport.getReportSelectColumnList();

			StringBuilder initCol = new StringBuilder();
			StringBuilder servCol = new StringBuilder();
			
			Map<String, String> mapColumns = new LinkedHashMap<>();
			L1.forEach((temp1) -> {

				if (temp1.getStatus().equals('I')) {
					// initCol.append("combined_json ->");
					mapColumns.put(temp1.getReportSelectedColumnId(), temp1.getReportSelectedColumnName());
					initCol.append(temp1.getReportSelectedColumnName());
					initCol.append(",");
				} else {
					mapColumns.put(temp1.getReportSelectedColumnId(), temp1.getReportSelectedColumnName());
					servCol.append(temp1.getReportSelectedColumnId());
					servCol.append(",");
				}
			});

			
			L1.forEach((temp1) -> {

				if (temp1.getStatus().equals('I')) {
					// initCol.append("combined_json ->");
					initCol.append(temp1.getReportSelectedColumnName());
					initCol.append(",");
				} else {
					servCol.append(temp1.getReportSelectedColumnId());
					servCol.append(",");
				}
			});


			StringJoiner joiner = new StringJoiner(",");

			if ((initCol.length() > 0) && (servCol.length() > 0)) {
				System.out.println("Inside First condition block");
				String initColL = initCol.substring(0, initCol.length() - 1);
				String servColL = servCol.substring(0, servCol.length() - 1);
				joiner.add(initColL).add(servColL);
			} else if ((initCol.length() > 0) && (servCol.length() == 0)) {
				String initColL = initCol.substring(0, initCol.length() - 1);
				joiner.add(initColL);
			} else if ((initCol.length() == 0) && (servCol.length() > 0)) {
				String servColL = servCol.substring(0, servCol.length() - 1);
				joiner.add(servColL);
			}
			// G Page<ApplInfoJson> applInfoJson =
			// applInfoJsonService.findAll(pageable);
			List<String> cols = Arrays.asList(joiner.toString().split("\\s*,\\s*"));
			
			// Fetch applInfoNode from List
			list.forEach((temp) -> {
				// map applinfo in map
				// map attributes in map
				Map<String, Object> maptotal = temp.getCombinedJson();
				//System.out.println(maptotal);
				for(String s : cols) {
				
					if(!maptotal.containsKey(s)) {
						
						maptotal.put(s, null);
					}
					
					
				}

				// merging map
				Map<String, Object> mapFromString = new LinkedHashMap<>();
				mapFromString.putAll(maptotal);

				listofMap.add(mapFromString);

			});

			System.out.println("ssssssss" + listofMap.size());
			ObjectMapper objectMapper = Squiggly.init(new ObjectMapper(), joiner.toString());
			String result = SquigglyUtils.stringify(objectMapper, listofMap);
			
			for (ReportSelectColumn s : L1) {

				result = result.replace("\""+s.getReportSelectedColumnId()+"\":", "\""+s.getReportSelectedColumnName().replaceAll("\\/", "\\_").replaceAll(" ","\\_")+"\":");
			}
			System.out.println("I am at 5th level updated record");
			JSONArray output;
			output = new JSONArray(result);
			
			/// ------------------------------------------------------------------------------------------
			// groupby columns taken from db during selection

			
			String groupbyColumns = listReport.getGrouping();

			ObjectMapper objectMapperGroupBy = Squiggly.init(new ObjectMapper(), groupbyColumns);
			String groupByData = SquigglyUtils.stringify(objectMapperGroupBy, mapColumns);

			String groupByString = JsonUtils.stringGroupby(groupByData);
			String groupByDataColumnsForQuery = JsonUtils.stringGroupbyColumns(groupByData);
			List<String> stringAggregationJoinerCoumns = JsonUtils
					.stringAggregationJoinerCoumns(listReport.getAggregationCls());
			
			List<String> groupSplit = JsonUtils.stringGroupByArray(groupByData);
			List<String> total_columns = Stream.of(stringAggregationJoinerCoumns,groupSplit).flatMap(Collection::stream).collect(Collectors.toList());
			int size = total_columns.size();
			System.out.println("data -------------------------------> " + groupByData);
			String aggregation = JsonUtils.stringAggregationJoiner(listReport.getAggregationCls());
			String having = JsonUtils.stringHavingJoiner(listReport.getHavingCls());
			JSONArray listGroupbyData = reportViwer.selectWhereColumnsForReport(groupByDataColumnsForQuery, abc, groupByString, size,
					total_columns, aggregation, having);
			
			
			return output.toString() + "===" + listGroupbyData.toString();
			
			
			//return output;
		
		} else { //code for Admin Report Export Starts Here
			String where = "";
			//Long locationId = (long) 1218758;
			// String abc="combined_json -> 'appl_id' = '726317' and
			// combined_json->'31694'='9416478067'";
			// String abc = "combined_json -> 'appl_id' = '726317'";
			String abc = "";
			ReportBean listReport = reportBeanService.findByReportId(repId);
			if (listReport.getWhereCondition().length() > 2) {
				System.out.println("where" + listReport.getWhereCondition());
				abc = JsonUtils.stringWhereForAdminReport(listReport.getWhereCondition(),deptidwithNameSelectedBU);

			}

			String orderby = "";
			
			if (res.getSession().getAttribute("selectedCol") != null) {

				System.out.println("inside refresh report");
				String valToBePass = (String) res.getSession().getAttribute("selectedCol");
				System.out.println("********************************** " + valToBePass);
				orderby = JsonUtils.getSortingJoinerAdminReport(valToBePass);
				System.out.println("ABC is" + abc);
				if (abc == "" || abc.equals("")) {
					abc = "this_.department_id = this_.department_id ";
				}
				where = abc.concat(orderby);
				

			} else if ((listReport.getOrderCondition().length() > 2) && (orderby == "")) {

				orderby = JsonUtils.getSortingJoinerAdminReport(listReport.getOrderCondition());
				
				if (abc == "" || abc.equals("")) {
					abc = "this_.department_id = this_.department_id ";
				}
				where = abc.concat(orderby);
			
			}
			

			List<ApplInfo> list = reportViwer.findByCombinedJsonAdminExport(filterbserviceId, filterbdisttId,
					nDeptIdName, where);

			String jsonString = streamContainersIntoJsonString(list);
			 JSONParser parser = new JSONParser();
			org.json.simple.JSONArray array = (org.json.simple.JSONArray ) parser.parse(jsonString);

			List<ReportSelectColumn> L1 = listReport.getReportSelectColumnList();

			
			
			StringBuilder initCol = new StringBuilder();
			StringBuilder servCol = new StringBuilder();
			
			Map<String, String> mapColumns =new LinkedHashMap<>();
			L1.forEach((temp1) -> {

				if (temp1.getStatus().equals('I')) {
					// initCol.append("combined_json ->");
					mapColumns.put(temp1.getReportSelectedColumnId(), temp1.getReportSelectedColumnName());
					initCol.append(temp1.getReportSelectedColumnName());
					initCol.append(",");
				} else {
					mapColumns.put(temp1.getReportSelectedColumnId(), temp1.getReportSelectedColumnName());
					servCol.append(temp1.getReportSelectedColumnId());
					servCol.append(",");
				}
			});
			
			L1.forEach((temp1) -> {

				if (temp1.getStatus().equals('I')) {
					initCol.append(temp1.getReportSelectedColumnName());
					initCol.append(",");
				} else {
					servCol.append(temp1.getReportSelectedColumnId());
					servCol.append(",");
				}
			});
			
			StringJoiner joiner = new StringJoiner(",");
			
			if ((initCol.length() > 0) && (servCol.length() > 0)) {
				System.out.println("Inside First condition block");
				String initColL = initCol.substring(0, initCol.length() - 1);
				String servColL = servCol.substring(0, servCol.length() - 1);
				joiner.add(initColL).add(servColL);
			} else if ((initCol.length() > 0) && (servCol.length() == 0)) {
				String initColL = initCol.substring(0, initCol.length() - 1);
				joiner.add(initColL);
			} else if ((initCol.length() == 0) && (servCol.length() > 0)) {
				String servColL = servCol.substring(0, servCol.length() - 1);
				joiner.add(servColL);
			}

			ApplInfo applInfo = new ApplInfo();
			Map<String, Object> listofColsMap = applInfo.getColumnNamesWithPojoVariables();
			
			ObjectMapper objectMapper1 = Squiggly.init(new ObjectMapper(), joiner.toString());
			String result1 = SquigglyUtils.stringify(objectMapper1, listofColsMap);
			
			

			final ObjectMapper mapper = new ObjectMapper();
			Map<String, String> mapFromString =new LinkedHashMap<>();
			try {
				mapFromString = mapper.readValue(result1, new TypeReference<Map<String, String>>() {
				});
			} catch (IOException e) {
				
			}
			
			String valueString = String.join(",",mapFromString.values()); 
			
			ObjectMapper objectMapper = Squiggly.init(new ObjectMapper(), valueString.toString());
			String result = SquigglyUtils.stringify(objectMapper,array);
			
			for (ReportSelectColumn s : L1) {

				result = result.replace(s.getReportSelectedColumnId(), s.getReportSelectedColumnName());
			}

			
			System.out.println("I am at 5th level updated record");
			JSONArray output;
			output = new JSONArray(result);
			/// ------------------------------------------------------------------------------------------
			// groupby columns taken from db during selection

			
			String groupbyColumns = listReport.getGrouping();

			ObjectMapper objectMapperGroupBy = Squiggly.init(new ObjectMapper(), groupbyColumns);
			String groupByData = SquigglyUtils.stringify(objectMapperGroupBy, mapColumns);

			String groupByString = JsonUtils.stringGroupbyForAdmin(groupByData);
			String groupByDataColumnsForQuery = JsonUtils.stringGroupbyColumnsForAdmin(groupByData);
			List<String> stringAggregationJoinerCoumns = JsonUtils
					.stringAggregationJoinerCoumns(listReport.getAggregationCls());
			
			List<String> groupSplit = JsonUtils.stringGroupByArray(groupByData);
			List<String> total_columns = Stream.of(stringAggregationJoinerCoumns,groupSplit).flatMap(Collection::stream).collect(Collectors.toList());
			int size = total_columns.size();
			//System.out.println("data -------------------------------> " + groupByData);
			String aggregation = JsonUtils.stringAggregationJoinerForAdmin(listReport.getAggregationCls());
			String having = JsonUtils.stringHavingJoinerForAdmin(listReport.getHavingCls());
			JSONArray listGroupbyData = reportViwer.selectWhereColumnsForReportAdmin(groupByDataColumnsForQuery, abc, groupByString, size,
					total_columns, aggregation, having);
			
			
			return output.toString() + "===" + listGroupbyData.toString();
		}
	}

	public List<Long> getLocationList(Long locationId, Long departmentId) {


		List<HrOrgUnits> l1 = hrOrgUnitsService.findByOrgUnitCode(locationId);
		HrOrgUnits hrOrgUnitsMain = l1.get(0);
		HrOrgLocatedAtLevels hrOrgLocatedAtLevelsMain = hrOrgLocatedAtLevelsService.findByOlcAndOrgLocatedLevelCode(departmentId, hrOrgUnitsMain.getOrgLocatedLevelCode());
		Long userLevel = hrOrgLocatedAtLevelsMain.getSortOrder();
		
		int count = hrOrgLocatedAtLevelsService.countByOlc(departmentId);
		
		System.out.println("Count" +  count + "userLevel :" + userLevel);
		
		List<Long> parentIds = l1.stream().map(urEntity -> urEntity.getOrgUnitCode()).collect(Collectors.toList());
		List<Long> finalList = new ArrayList<>();
		finalList.addAll(parentIds);
		for(long i=userLevel;i<count;i++){
			List<HrOrgUnits> childListComplete = reportViwer.findByParentOrgUnitCode(parentIds);
			List<Long> childList2 = childListComplete.stream().map(urEntity -> urEntity.getOrgUnitCode()).collect(Collectors.toList());
			finalList.addAll(childList2);
			//System.out.println("This is child List in Loop " + i + "childList2 : " + childList2);
			parentIds.clear();
			parentIds.addAll(childList2);
			//System.out.println("This is Parent List After Loop " + i + "parentIds : " + parentIds);
			//System.out.println("This is Final List After Loop " + i + "finalList : " + finalList);
		}
		
		return finalList;
	}

	public static String streamContainersIntoJsonString(List<ApplInfo> containers) {
	    try {
	        Gson gson = new Gson();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
	        writer.setIndent("  ");
	        writer.beginArray();
	        for (ApplInfo container : containers) {
	            gson.toJson(container, ApplInfo.class, writer);
	        }
	        writer.endArray();
	        writer.close();

	        return out.toString("UTF-8");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return null;
	}
	
	
	 public static <T, K> Map<K, T> 
	    replaceNullValues(Map<K, T> map, T defaultValue) 
	    { 
	  
	        // Replace the null value 
	        map = map.entrySet() 
	                  .stream() 
	                  .map(entry -> { 
	                      if (entry.getValue() == null || entry.getValue().equals(null) || entry.getValue().equals("null") || entry.getValue() =="null" || entry.getValue() == null  || entry.getValue().equals("") || entry.getValue() =="" ) 
	                          entry.setValue(defaultValue); 
	                      return entry; 
	                  }) 
	                  .collect(Collectors.toMap(Map.Entry::getKey, 
	                                            Map.Entry::getValue)); 
	      //  System.out.println(map);
	        return map; 
	    } 
	    
}