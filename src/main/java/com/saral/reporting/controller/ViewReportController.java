package com.saral.reporting.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;
import com.google.gson.Gson;
import com.saral.reporting.DAO.ReportViwerDAO;
import com.saral.reporting.model.ApplInfo;
import com.saral.reporting.model.ApplInfoJson;
import com.saral.reporting.model.AttributeMasterData;
import com.saral.reporting.model.AttributeMasterDataSql;
import com.saral.reporting.model.DashBoardReportData;
import com.saral.reporting.model.HrOrgLocatedAtLevels;
import com.saral.reporting.model.HrOrgUnits;
import com.saral.reporting.model.ReportBean;
import com.saral.reporting.model.ReportSelectColumn;
import com.saral.reporting.model.ServiceMaster;
import com.saral.reporting.service.ApplInfoJsonService;
import com.saral.reporting.service.AttributeMasterDataSqlService;
import com.saral.reporting.service.DashBoardReportDataService;
import com.saral.reporting.service.HrOrgLocatedAtLevelsService;
import com.saral.reporting.service.HrOrgUnitsService;
import com.saral.reporting.service.ReportBeanService;
import com.saral.reporting.service.ServiceMasterService;
import com.saral.reporting.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;

@Transactional
@Controller

@SessionAttributes({ "sign_no", "reportId", "service_id", "hm", "department_level_name", "department_id",
		"designation_id", "designation_name", "selectedOrder", "selectedOrder", "deptidwithNameSelectedBU",
		"departmentIdOfReport", "where", "nDeptIdName", "getLocationList" })
public class ViewReportController implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(ViewReportController.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	ReportBeanService reportBeanService;

	@Autowired
	ApplInfoJsonService applInfoJsonService;

	@Autowired
	HrOrgUnitsService hrOrgUnitsService;

	@Autowired
	ServiceMasterService serviceMasterService;

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	ReportViwerDAO reportViwer;

	@Autowired
	DashBoardReportDataService dashBoardReportDataService;

	@Autowired
	HrOrgLocatedAtLevelsService hrOrgLocatedAtLevelsService;
	@Autowired
	AttributeMasterDataSqlService attributeMasterDataSqlService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/fetchReportList" }, method = RequestMethod.GET)
	public String reportViewPage(ModelMap model, HttpServletRequest request) throws ServletException, IOException {
		String sign_no = (String) request.getSession().getAttribute("sign_no");

		Long department_id = (Long) request.getSession().getAttribute("department_id");
		List<ReportBean> listReport = new ArrayList<ReportBean>();

		if (department_id == 0L) {
			listReport = reportBeanService.findByIsAdminReport('Y');
		} else {
			List<Long> userAllocatedServices = (List<Long>) request.getSession().getAttribute("saralUserServiceList");
			String hmfromSession = (String) request.getSession().getAttribute("hm");
			System.out.println("Roles From the session" + hmfromSession);
			
			System.out.println("This is the list we get from the session :" + userAllocatedServices);
			if(hmfromSession.contains("State Service Definer")){
				userAllocatedServices.clear();
				System.out.println("This is the list we get from the session inside loop:" + userAllocatedServices);
			}
			listReport = reportViwer.findByDepartmentIdAndIsAdminReportAndServiceId(department_id, 'N',
					userAllocatedServices);
		}

		// List<ReportBean> listReport =
		// reportBeanService.findBySignNo(sign_no);
		PagedListHolder<ReportBean> pagedListHolder = new PagedListHolder<ReportBean>(listReport);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(3);
		model.put("pagedListHolder", pagedListHolder);
		model.put("sign_no", sign_no);
		model.put("l2", listReport);
		return "reportViewerPage";
	}

	@RequestMapping(value = { "/viewSelectedReport" }, method = RequestMethod.GET)
	public String reportSelectedReport(ModelMap model, Pageable pageable, @RequestParam String reportId,
			@RequestParam Long deptidwithNameSelected, @RequestParam String service_id, HttpServletRequest request)
			throws ServletException, IOException, ParseException {

		Long repId = Long.parseLong(reportId);
		Long servID = Long.parseLong(service_id);

		ReportBean listReport = reportBeanService.findByReportId(repId);

		model.put("reportId", repId);
		model.put("service_id", servID);
		if (servID != 0L) {
			ServiceMaster serviceMaster = serviceMasterService.findByServiceCode(servID.toString());
			Long dptIdWithNameSelectedForServ = Long.valueOf(serviceMaster.getDeptCode());
			request.getSession().setAttribute("deptidwithNameSelectedBU", dptIdWithNameSelectedForServ);
			System.out.println("Inside Service block HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH ->>>>>>>>>>>>>>"
					+ request.getSession().getAttribute("deptidwithNameSelectedBU"));
			model.put("deptidwithNameSelectedBU", dptIdWithNameSelectedForServ);
		} else if ((listReport.getDepartmentId() != 0L) && (listReport.getDepartmentId() != 1L)) {
			Long dptIdWithNameSelectedForServ = listReport.getDepartmentId();
			request.getSession().setAttribute("deptidwithNameSelectedBU", dptIdWithNameSelectedForServ);
			System.out.println("Inside Single Dept block  HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH ->>>>>>>>>>>>>>"
					+ request.getSession().getAttribute("deptidwithNameSelectedBU"));
			model.put("deptidwithNameSelectedBU", dptIdWithNameSelectedForServ);
		} else {
			request.getSession().setAttribute("deptidwithNameSelectedBU", deptidwithNameSelected);
			System.out.println("Inside All Dept block HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH ->>>>>>>>>>>>>>"
					+ request.getSession().getAttribute("deptidwithNameSelectedBU"));
			model.put("deptidwithNameSelectedBU", deptidwithNameSelected);
		}
		// request.getSession().setAttribute("selectedOrder", null);
		request.getSession().setAttribute("selectedCol", null);
		return "showReportNew";

	}

	@RequestMapping(value = { "/viewFilteredReport" }, method = RequestMethod.GET)
	public String reportFilteredData(ModelMap model, Pageable pageable, @RequestParam String selectedCol,
			Long deptidwithNameSelected, HttpServletRequest request)
			throws ServletException, IOException, ParseException {

		System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHH values are" + selectedCol);
		// model.put("selectedOrder", selectedOrder);
		model.put("selectedCol", selectedCol);
		request.getSession().setAttribute("selectedCol", selectedCol);
		// request.getSession().setAttribute("selectedOrder", selectedOrder);
		System.out.println("session" + request.getSession().getAttribute("selectedCol"));

		request.getSession().setAttribute("deptidwithNameSelectedBU", deptidwithNameSelected);
		System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH ->>>>>>>>>>>>>>>>>>>>"
				+ request.getSession().getAttribute("deptidwithNameSelectedBU"));
		model.put("deptidwithNameSelectedBU", deptidwithNameSelected);
		return "showReportNew";

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/viewSelectedReportData" }, method = RequestMethod.GET)
	public String reportSelectedReportShow(ModelMap model, Pageable pageable, HttpServletRequest request)
			throws ServletException, IOException, ParseException {

		Long repId = (Long) request.getSession().getAttribute("reportId");
		Long servID = (Long) request.getSession().getAttribute("service_id");
		// String filterString = (String)
		// request.getSession().getAttribute("filterString");
		List<Long> filterbserviceId = (List<Long>) request.getSession().getAttribute("filterbserviceId");
		List<Long> filterbdisttId = (List<Long>) request.getSession().getAttribute("filterbdisttId");
		// List<Long> filterbdeptId = (List<Long>)
		// request.getSession().getAttribute("filterbdeptId");

		Long department_id = (Long) request.getSession().getAttribute("department_id");

		Long deptidwithNameSelectedBU = (Long) request.getSession().getAttribute("deptidwithNameSelectedBU");
		List<Long> nDeptIdName = new ArrayList<>();
		nDeptIdName.add(deptidwithNameSelectedBU);

		model.put("deptidwithNameSelectedBU", deptidwithNameSelectedBU);
		model.put("nDeptIdName", nDeptIdName);

		Long locationId = (Long) request.getSession().getAttribute("location_Id");
		System.out.println("my location" + locationId);
		List<Long> getLocationList1 = new ArrayList<>();
		if (locationId != 0L) {
			getLocationList1 = getLocationList(locationId, department_id);
		}

		String abc = "";
		ReportBean listReport = reportBeanService.findByReportId(repId);
		model.put("getLocationList", getLocationList1);
		model.put("tableColor", listReport.getTableColor());
		model.put("reportHeader", listReport.getReport_header());
		model.put("reportFooter", listReport.getReport_footer());
		model.put("departmentIdOfReport", listReport.getDepartmentId());

		if ((servID == 0L) || (servID == 1L)) {
			if (listReport.getWhereCondition().length() > 2) {

				abc = JsonUtils.stringWhereForAdminReport(listReport.getWhereCondition(), deptidwithNameSelectedBU);
				System.out.println("where abc Admin " + abc);
			}
		} else {
			if (listReport.getWhereCondition().length() > 2) {

				abc = JsonUtils.stringWhere(listReport.getWhereCondition(), servID);
				System.out.println("where abc" + abc);
			}
		}

		System.out.println("Srvice Id " + servID);

		Long countMax = 0L;
		if (servID == 0L) {

			countMax = reportViwer.findCountForAdmin(filterbserviceId, filterbdisttId, nDeptIdName, abc);

		} else {
			countMax = reportViwer.findCount(servID, getLocationList1, abc);
		}
		System.out.println(repId + department_id);
		DashBoardReportData getDashBoardData = dashBoardReportDataService.findByReportIdAndDepartmentId(repId,
				department_id);

		if (getDashBoardData != null) {

			getDashBoardData.setCount(getDashBoardData.getCount() + 1L);
			dashBoardReportDataService.save(getDashBoardData);
		} else {
			DashBoardReportData dashBoardData = new DashBoardReportData();
			dashBoardData.setDepartmentId(department_id);
			dashBoardData.setReportId(repId);
			dashBoardData.setReportName(listReport.getReportName());
			dashBoardData.setCount(1L);
			dashBoardReportDataService.save(dashBoardData);
		}

		if (countMax == 0L) {

			model.put("ErrorReport", "No Record found for selected Report");
			model.put("joiner","[]");
			model.addAttribute("addresses", "\"NO Result\"");
			return "showReportNew";

		} else if ((servID != 0L) && (servID != 1L)) { // for normal user

			List<ReportSelectColumn> listCol = listReport.getReportSelectColumnList();

			String orderby = "";
			String where = "";
			if (request.getSession().getAttribute("selectedCol") != null) {

				System.out.println("inside refresh report");
				String valToBePass = (String) request.getSession().getAttribute("selectedCol");
				System.out.println("********************************** " + valToBePass);
				orderby = JsonUtils.getSortingJoinerReport(valToBePass);
				if (abc == "" || abc.equals("")) {
					abc = " service_id =" + servID + " ";
				}
				model.put("where", abc);
				where = abc.concat(orderby);
				System.out.println(where);
				System.out.println("my complete query" + abc);

			} else if ((listReport.getOrderCondition().length() > 2) && (orderby == "")) {

				orderby = JsonUtils.getSortingJoinerReport(listReport.getOrderCondition());
				if (abc == "" || abc.equals("")) {
					abc = " service_id =" + servID + " ";
				}
				model.put("where", abc);
				where = abc.concat(orderby);

				System.out.println("my complete query" + abc);
			}

			Page<ApplInfoJson> list = reportViwer.findByCombinedJson(servID, pageable, getLocationList1, where);
			System.out.println("appl_id");

			List<Map<String, Object>> listofMap = new ArrayList<>();

			List<ReportSelectColumn> L1 = listReport.getReportSelectColumnList();

			StringBuilder initCol = new StringBuilder();
			StringBuilder servCol = new StringBuilder();
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

			System.out.println("initCol" + initCol.length());
			System.out.println("servCol" + servCol.length());
			StringJoiner joiner = new StringJoiner(",");
			joiner.add("view");
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

			List<String> cols = Arrays.asList(joiner.toString().split("\\s*,\\s*"));
			// Fetch applInfoNode from List
			System.out.println(cols);
			list.forEach((temp) -> {
				// map applinfo in map
				// map attributes in map
				Map<String, Object> maptotal = temp.getCombinedJson();
				System.out.println("applid" + maptotal.get("appl_id"));
				maptotal.put("view", "<a href=javascript:void(0); onclick=showTaskInfo(" + temp.getApplId() + ","
						+ temp.getServiceId() + "," + temp.getVersionNo() + ")>View</a>");
				for (String s : cols) {

					if (!maptotal.containsKey(s)) {

						maptotal.put(s, "NA");
						// System.out.println(maptotal);
					}

				}
				if (maptotal.containsKey("submission_location")) {

					Object value = maptotal.get("submission_location");
					String[] arr = value.toString().split("~");
					maptotal.put("submission_location", arr[2]);
				}

				// merging map
				Map<String, Object> mapFromString = new LinkedHashMap<>();
				mapFromString.putAll(maptotal);

				listofMap.add(mapFromString);

			});

			System.out.println("ssssssss" + listofMap.size());
			ObjectMapper objectMapper = Squiggly.init(new ObjectMapper(), joiner.toString());
			String result = SquigglyUtils.stringify(objectMapper, listofMap);
			List<String> items = Arrays.asList(joiner.toString().split("\\s*,\\s*"));

			for (ReportSelectColumn s : L1) {

				// result = result.replace(s.getReportSelectedColumnId(),
				// s.getReportSelectedColumnName());

				result = result.replace("\"" + s.getReportSelectedColumnId() + "\":",
						"\"" + s.getReportSelectedColumnName() + "\":");
				Collections.replaceAll(items, s.getReportSelectedColumnId(), s.getReportSelectedColumnName());

			}
			System.out.println(items);

			Gson gson = new Gson();
			// convert your list to json
			String jsonCartList = gson.toJson(items);
			model.put("joiner", jsonCartList);

			Integer totalPages = (int) (countMax / 150);
			if (countMax % 150 == 0) {

			} else {
				totalPages = totalPages + 1;
			}
			long size1 = ((pageable.getPageNumber()) * 150);
			if (size1 < countMax) {
				model.addAttribute("size", size1);
			} else if (size1 >= countMax) {
				model.addAttribute("size", countMax);
			}

			int current = pageable.getPageNumber() + 1;
			int begin = Math.max(1, current - 5);
			int end = Math.min(begin + 5, totalPages);

			model.addAttribute("end", end);

			model.put("applInfoJson", result);
			model.put("reportId", repId);
			model.put("service_id", servID);
			System.out.println("Inside second loop where records are greater than 6000 ==== FINAL");

			JsonUtils.pageModel(model, list);
			long pNumber = ((pageable.getPageNumber() - 1) * 150);
			model.put("number", pNumber);
			System.out.println("number" + pNumber);
			model.addAttribute("addresses", result);
			model.addAttribute("totalElements", countMax);
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("listCol", listCol);
			return "showReportNew";

		} else {

			/*
			 * 
			 * Code for Showing Admin reports Department-wise starts here Write all Admin
			 * reports related code here
			 * 
			 * 
			 */

			model.put("ErrorReport", "");
			List<ReportSelectColumn> listCol = listReport.getReportSelectColumnList();

			String orderby = "";
			if (request.getSession().getAttribute("selectedCol") != null) {

				System.out.println("inside refresh report");
				String valToBePass = (String) request.getSession().getAttribute("selectedCol");
				System.out.println("********************************** " + valToBePass);
				orderby = JsonUtils.getSortingJoinerAdminReport(valToBePass);
				System.out.println("ABC is" + abc);
				if (abc == "" || abc.equals("")) {
					abc = "this_.department_id = this_.department_id ";
				}

				model.put("where", abc);
				abc = abc.concat(orderby);

				System.out.println("my complete query" + abc);

			} else if ((listReport.getOrderCondition().length() > 2) && (orderby == "")) {

				orderby = JsonUtils.getSortingJoinerAdminReport(listReport.getOrderCondition());
				System.out.println("ABC is" + abc);
				if (abc == "" || abc.equals("")) {
					abc = "this_.department_id = this_.department_id ";
				}
				model.put("where", abc);
				abc = abc.concat(orderby);
				System.out.println(abc + orderby);
				System.out.println("my complete query" + abc);
			}

			Page<ApplInfo> list = reportViwer.findByCombinedJsonAdminReport(filterbserviceId, pageable, filterbdisttId,
					nDeptIdName, abc, orderby);

			ObjectMapper mapper1 = new ObjectMapper();
			String jsonString = mapper1.writeValueAsString(list);

			List<ApplInfo> applList = list.getContent();
			List<ApplInfo> applListwithView = new ArrayList<>();
			for (ApplInfo l : applList) {
				// System.out.println(l);
				// l.setView("<a href=/fetchTaskInfo?applId="+ l.getApplId()
				// +"&serviceId="+l.getServiceId()+ ">View</a>");
				l.setView("<a href=javascript:void(0); onclick=showTaskInfo(" + l.getApplId() + "," + l.getServiceId()
						+ "," + l.getVersionNo() + ")>View</a>");
				applListwithView.add(l);

				String value = l.getSubmissionLocation();
				String[] arr = value.toString().split("~");
				l.setSubmissionLocation(arr[2]);

			}

			System.out.println(applListwithView);
			JSONParser parser = new JSONParser();
			JSONObject array = (JSONObject) parser.parse(jsonString);
			// System.out.println(array);
			org.json.simple.JSONArray objarray = (org.json.simple.JSONArray) array.get("content");

			System.out.println("objarray size" + objarray.size());

			List<ReportSelectColumn> L1 = listReport.getReportSelectColumnList();

			StringBuilder initCol = new StringBuilder();
			StringBuilder servCol = new StringBuilder();
			L1.forEach((temp1) -> {

				if (temp1.getStatus().equals('I')) {
					initCol.append(temp1.getReportSelectedColumnName());
					initCol.append(",");
				} else {
					servCol.append(temp1.getReportSelectedColumnId());
					servCol.append(",");
				}
			});

			System.out.println("initCol " + initCol.length());
			System.out.println("servCol " + servCol.length());
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
//joiner.add("view");
			System.out.println("merz view joiner " + joiner);
			ApplInfo applInfo = new ApplInfo();
			Map<String, Object> listofColsMap = applInfo.getColumnNamesWithPojoVariables();

			// ObjectMapper mapperForColms = new ObjectMapper();
			ObjectMapper objectMapper1 = Squiggly.init(new ObjectMapper(), joiner.toString());
			String result1 = SquigglyUtils.stringify(objectMapper1, listofColsMap);

			final ObjectMapper mapper = new ObjectMapper();
			Map<String, String> mapFromString = new LinkedHashMap<>();
			try {
				mapFromString = mapper.readValue(result1, new TypeReference<Map<String, String>>() {
				});
			} catch (IOException e) {

			}

			List<String> result4 = mapFromString.values().stream().collect(Collectors.toList());

			// String valueString = String.join(",", mapFromString.values());

			result4.add(0, "view");
			System.out.println("sasasasa" + result4);
			ObjectMapper objectMapper = Squiggly.init(new ObjectMapper(), StringUtils.join(result4, ','));
			String result = SquigglyUtils.stringify(objectMapper, applListwithView);

			for (ReportSelectColumn s : L1) {

				result = result.replace("\"" + s.getReportSelectedColumnId() + "\":",
						"\"" + s.getReportSelectedColumnName() + "\":");

			}

			Gson gson = new Gson();
			// convert your list to json
			String jsonCartList = gson.toJson(result4);
			model.put("joiner", jsonCartList);
			Integer totalPages = (int) (countMax / 150);
			if (countMax % 150 == 0) {

			} else {
				totalPages = totalPages + 1;
			}
			long size1 = ((pageable.getPageNumber()) * 150);
			if (size1 < countMax) {
				model.addAttribute("size", size1);
			} else if (size1 >= countMax) {
				model.addAttribute("size", countMax);
			}

			int current = pageable.getPageNumber() + 1;
			int begin = Math.max(1, current - 5);
			int end = Math.min(begin + 5, totalPages);

			model.addAttribute("end", end);
			model.put("applInfoJson", result);
			model.put("reportId", repId);
			model.put("service_id", servID);

			// System.out.println("Inside second loop where records are greater than 6000
			// ==== FINAL");

			JsonUtils.pageModel(model, list);
			long pNumber = ((pageable.getPageNumber() - 1) * 150);
			model.put("number", pNumber);
			System.out.println("number" + pNumber);
			model.addAttribute("addresses", result);
			model.addAttribute("totalElements", countMax);
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("listCol", listCol);
			return "showReportNew";

		}

	}

	public List<Long> getLocationList(Long locationId, Long departmentId) {
		List<Long> finalList = new ArrayList<>();
		List<HrOrgUnits> l1 = hrOrgUnitsService.findByOrgUnitCode(locationId);

		HrOrgUnits hrOrgUnitsMain = l1.get(0);
		System.out.println("list oif fe" + hrOrgUnitsMain);
		System.out.println(departmentId);
		HrOrgLocatedAtLevels hrOrgLocatedAtLevelsMain = hrOrgLocatedAtLevelsService
				.findByOlcAndOrgLocatedLevelCode(departmentId, hrOrgUnitsMain.getOrgLocatedLevelCode());
		if (hrOrgLocatedAtLevelsMain != null) {
			Long userLevel = hrOrgLocatedAtLevelsMain.getSortOrder();

			int count = hrOrgLocatedAtLevelsService.countByOlc(departmentId);

			System.out.println("Count" + count + "userLevel :" + userLevel);

			List<Long> parentIds = l1.stream().map(urEntity -> urEntity.getOrgUnitCode()).collect(Collectors.toList());

			finalList.addAll(parentIds);
			for (long i = userLevel; i < count; i++) {
				List<HrOrgUnits> childListComplete = reportViwer.findByParentOrgUnitCode(parentIds);
				List<Long> childList2 = childListComplete.stream().map(urEntity -> urEntity.getOrgUnitCode())
						.collect(Collectors.toList());
				finalList.addAll(childList2);

				parentIds.clear();
				parentIds.addAll(childList2);

			}

			return finalList;
		} else
			return finalList;
	}

	/*
	 * This function is used to show "selected column" data in Pie-Chart in View
	 * Report module
	 * 
	 */
	@RequestMapping(value = "/checkColumnCount", method = {
			RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResponseEntity<?> checkColumnCount(ModelMap model, @RequestParam String id, Long depId,
			HttpServletRequest request) throws ServletException, IOException {
		Long depIds = (Long) request.getSession().getAttribute("department_id");
		System.out.println(depIds);
		org.json.JSONArray jsonArray = null;
		Long servID = (Long) request.getSession().getAttribute("service_id");
		if (depIds != 0L && depIds != null) {
			jsonArray = reportViwer.ColumnJsonbCount(id, servID);
			System.out.println("Inside non-Admin service - column wise piechart");
			// long count = 0L;
		} else {
			if (servID != 0L) {
				jsonArray = reportViwer.ColumnJsonbCount(id, servID);
				System.out.println("Inside Admin service - column wise piechart");
			} else {
				jsonArray = reportViwer.ColumnJsonbCountForAdmin(id, depId);
				System.out.println("Inside Admin department - column wise piechart");
			}
		}

		if (jsonArray.length() > 0) {

			String jsonCol = new Gson().toJson(jsonArray);
			return ResponseEntity.ok(jsonCol);
		} else {
			String jsonCol = new Gson().toJson("No Data available.");
			return ResponseEntity.ok(jsonCol);
		}

	}

	// To get count of services for selected deparment
	@RequestMapping(value = "/checkServiceCountForGraph", method = {
			RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResponseEntity<?> checkServiceCountForGraph(ModelMap model, @RequestParam String id,
			HttpServletRequest request) throws ServletException, IOException, JSONException {
		org.json.JSONArray hm = reportViwer.ColumnServiceCountByDeprt(id, 'V');
		// long count = 0L;

		if (hm.length() > 0) {
			String jsonCol = new Gson().toJson(hm);
			return ResponseEntity.ok(jsonCol);
		} else {
			String jsonCol = new Gson().toJson("No Data available.");
			return ResponseEntity.ok(jsonCol);
		}

	}

	// To get the sum of columns with request from frontend
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/viewSum" }, method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResponseEntity<?> getSumColum(ModelMap model, @RequestParam String columnId, String departmentId,
			@RequestParam String aggregation, HttpServletRequest request)
			throws ServletException, IOException, ParseException {

		Long repId = (Long) request.getSession().getAttribute("reportId");
		Long servID = (Long) request.getSession().getAttribute("service_id");
		Long location_id = (Long) request.getSession().getAttribute("location_Id");

		System.out.println(location_id);
		String where = (String) request.getSession().getAttribute("where");
		System.out.println(where);
		System.out.println(servID);
		Object value;
		if (location_id != 0L) {
			List<Long> getlocationList = (List<Long>) request.getSession().getAttribute("getLocationList");
			System.out.println(getlocationList);
			value = reportViwer.findSumofColumn(columnId, departmentId, aggregation, where, getlocationList);
		} else {
			List<Long> filterbserviceId = (List<Long>) request.getSession().getAttribute("filterbserviceId");
			List<Long> filterbdisttId = (List<Long>) request.getSession().getAttribute("filterbdisttId");
			List<Long> filterbdeptId = (List<Long>) request.getSession().getAttribute("nDeptIdName");
			value = reportViwer.findAggregationCombinedJson(filterbserviceId, filterbdisttId, filterbdeptId, where,
					aggregation, columnId);
		}
		model.put("sum", value);

		return ResponseEntity.ok(value);

	}

	public Map<String, Long> findAttributeMap(Long baseServiceId) {

		List<AttributeMasterDataSql> data = attributeMasterDataSqlService.findByBaseServiceID(baseServiceId);
		Map<String, Long> values = new LinkedHashMap<>();

		for (AttributeMasterDataSql s : data) {
			values.put(s.getAttributeLabel(), s.getAttributeID());
		}
		return values;

	}

}
