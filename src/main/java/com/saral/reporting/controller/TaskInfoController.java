package com.saral.reporting.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;
import com.google.gson.Gson;
import com.saral.reporting.model.ApplInfo;
import com.saral.reporting.model.AttributeMasterDataSql;
import com.saral.reporting.model.TaskInfoJson;
import com.saral.reporting.model.TaskMaster;
import com.saral.reporting.service.ApplInfoService;
import com.saral.reporting.service.AttributeMasterDataSqlService;
import com.saral.reporting.service.TaskInfoJsonService;
import com.saral.reporting.service.TaskInfoService;
import com.saral.reporting.service.TaskMasterService;

@Transactional
@Controller
public class TaskInfoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	ApplInfoService applInfoService;
	@Autowired
	TaskInfoJsonService taskInfoJsonService;

	@Autowired
	AttributeMasterDataSqlService attributeMasterDataSqlService;

	@Autowired
	TaskMasterService taskMappingMasterService;

	@Autowired
	TaskInfoService taskInfoService;

	@RequestMapping(value = { "/fetchTaskInfoData" }, method = RequestMethod.GET)
	public ResponseEntity<?> fetchTaskInfoData(ModelMap model, HttpServletRequest request, @RequestParam Long applId,
			@RequestParam Long serviceId, @RequestParam Long versionNo) throws ServletException, IOException {
		Integer count = 0;
		Integer count1 = 1;
		List<AttributeMasterDataSql> listAttributes = attributeMasterDataSqlService.findByBaseServiceID(serviceId);
		List<Long> taskValues = new ArrayList<>();

		// Long applId = 18220L;
		List<TaskInfoJson> taskInfoJsonList = taskInfoJsonService.findByApplIdOrderByCurrentProcessId(applId);
		ApplInfo info = applInfoService.findByApplId(applId);

		System.out.println(info);

		List<Map<String, Object>> listofMap = new ArrayList<>();
		Map<String, Object> applinfomap = new LinkedHashMap<>();
		if (info != null) {

			applinfomap.put("id", count + 1);
			applinfomap.put("task_type", 14.0);
			applinfomap.put("task_name", "Application Submission");
			applinfomap.put("executed_time", info.getSubmissionDate());
			applinfomap.put("Action", "1~Submitted");

		}

		System.out.println(applinfomap);
		listofMap.add(applinfomap);
		for (TaskInfoJson temp : taskInfoJsonList) {

			// map applinfo in map
			// map attributes in map
			Map<String, Object> maptotal = temp.getCombinedtaskJson();
			System.out.println("applid" + maptotal.get("appl_id"));

			maptotal.put("id", count1 + 1);
			count1++;
			// merging map
			Map<String, Object> mapFromString = new LinkedHashMap<>();

			mapFromString.putAll(maptotal);

			listofMap.add(mapFromString);

		}

		String jsonCol = new Gson().toJson(listofMap);

		for (AttributeMasterDataSql s : listAttributes) {

			jsonCol = jsonCol.replaceAll("\"" + s.getAttributeID() + "\":", "\"" + s.getAttributeLabel() + "\":");

		}

		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, Object>> mapFromString = new ArrayList<HashMap<String, Object>>();

		// convert JSON string to Map
		mapFromString = mapper.readValue(jsonCol, new TypeReference<List<HashMap<String, Object>>>() {
		});

		for (Map<String, Object> l : mapFromString) {
			if (l.containsKey("Action")) {

				String value = (String) l.get("Action");
				if (value != null) {
					String[] arrOfStr = value.split("~");
					l.put("Action", arrOfStr[1]);
				}
			} else {
				l.put("Action", "N.A.");
			}
			if (l.containsKey("task_id")) {
				Double d = (Double) l.get("task_id");

				taskValues.add((long) (d * 1L));

			}
			if (l.containsKey("task_type")) {
				Double value = (Double) l.get("task_type");
				if (value == 14) {
					l.put("task_type", "Official Task");
				} else if (value == 31) {
					l.put("task_type", "Timer Task");

				} else if (value == 16) {
					l.put("task_type", "Applicant Task");
				} else {
					l.put("task_type", "Web Service");
				}
			}

		}

		ObjectMapper objectMapper = Squiggly.init(new ObjectMapper(), "id,task_type,task_name,executed_time,Action");
		String result = SquigglyUtils.stringify(objectMapper, mapFromString);

		System.out.println(result);
		// for percentage v
		System.out.println("i am  here");
		Long counter = taskMappingMasterService.countByServiceIdAndVersionNo(serviceId.toString(),
				versionNo.toString());
		List<TaskMaster> taskMasterList = taskMappingMasterService.findByServiceIdAndVersionNo(serviceId.toString(),
				versionNo.toString());

		Map<Long, String> taskmap = new LinkedHashMap<>();
		for (TaskMaster master : taskMasterList) {
			if (!master.getTaskName().equals("Application Submission")) {
				taskmap.put(master.getTaskId(), master.getTaskName());
			}
		}

		System.out.println(taskmap);
		System.out.println(taskValues);
		List<TaskInfoJson> distinctElements = taskInfoJsonList.stream().filter(distinctByKey(p -> p.getTaskId()))
				.collect(Collectors.toList());
		System.out.println(distinctElements.size());
		double percentage = ((Double.valueOf(distinctElements.size() + 1) / Double.valueOf(counter)) * 100);
		Map<String, Object> data = new LinkedHashMap<>();
		data.put("data", result);
		data.put("percentage", percentage);

		System.out.println(percentage);
		Map<Integer, String> finalmapTask = new LinkedHashMap<>();
		int cnt = 1;
		// for map -------------- no values present

		taskmap.keySet().removeAll(taskValues);
		System.out.println(taskmap);
		List<String> result2 = new ArrayList<String>(taskmap.values());
		System.out.println(result2);
		for (String taskname : result2) {

			finalmapTask.put(cnt, taskname);
			cnt ++;

		}
		System.out.println(finalmapTask);
		data.put("PendingTasks", finalmapTask);

		return ResponseEntity.ok(data);

	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

}
