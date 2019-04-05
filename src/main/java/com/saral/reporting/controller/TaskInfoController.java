package com.saral.reporting.controller;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.saral.reporting.model.AttributeMasterDataSql;
import com.saral.reporting.model.TaskInfoJson;
import com.saral.reporting.service.AttributeMasterDataSqlService;
import com.saral.reporting.service.TaskInfoJsonService;

@Transactional
@Controller
public class TaskInfoController implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    TaskInfoJsonService taskInfoJsonService;

    @Autowired
    AttributeMasterDataSqlService attributeMasterDataSqlService;
    
    @SuppressWarnings("unchecked")
    @RequestMapping(value = { "/fetchTaskInfoData" }, method = RequestMethod.GET)
    public ResponseEntity<?> fetchTaskInfoData (ModelMap model, HttpServletRequest request, @RequestParam Long applId, @RequestParam Long serviceId) throws ServletException, IOException {
        
        List<AttributeMasterDataSql> listAttributes= attributeMasterDataSqlService.findByBaseServiceID(serviceId);
        Map<String,Long> values = new LinkedHashMap<>();
        
    
        
        
        //Long applId = 18220L;
        List<TaskInfoJson> taskInfoJsonList = taskInfoJsonService.findByApplIdOrderByCurrentProcessId(applId);
        Integer count = 0;
        List<Map<String, Object>> listofMap = new ArrayList<>();
    for(TaskInfoJson temp:taskInfoJsonList ){
            
            // map applinfo in map
            // map attributes in map
            Map<String, Object> maptotal = temp.getCombinedtaskJson();
            System.out.println( "applid" + maptotal.get("appl_id") );
            
            maptotal.put("id",count+1);
            count++;
            // merging map
            Map<String, Object> mapFromString = new LinkedHashMap<>();
            mapFromString.putAll(maptotal);

            listofMap.add(mapFromString);

    }
        System.out.println(listofMap);
        System.out.println("taskInfoJsonList" + taskInfoJsonList.size());
        String jsonCol = new Gson().toJson(listofMap);
        
        for(AttributeMasterDataSql s :listAttributes) {
            
    jsonCol =        jsonCol.replaceAll("\"" + s.getAttributeID()+ "\":",
                "\"" + s.getAttributeLabel()+ "\":");
            
        }
        
        Gson gson = new Gson();
        Type listType = new TypeToken<List<HashMap<String, Object>>>(){}.getType();
        List<LinkedHashMap<String, Object>> mapFromString = 
            gson.fromJson(jsonCol.toString(), listType);
        
        for(Map<String, Object> l : mapFromString) {
            if(l.containsKey("Action")) {
                
                String value = (String) l.get("Action");
                if(value != null) {
                  String[] arrOfStr =  value.split("~"); 
                  l.put("Action", arrOfStr[1]);
        }else {
            l.put("Action","N/A");
        }
        }
            
            if(l.containsKey("task_type")) {
                Double value =  (Double) l.get("task_type");
                if(value == 14) {
                    l.put("task_type","Official Task");
                }
                else if(value == 31) 
                {    l.put("task_type","Timer Task");
                    
                }
                else if(value == 16) {
                    l.put("task_type","Applicant Task");
            }
                else {
                    l.put("task_type","Web Service");}
                }
        }
        System.out.println(mapFromString);
         ObjectMapper objectMapper = Squiggly.init(new ObjectMapper(), "id,task_type,task_name,executed_time,Action");
            String result = SquigglyUtils.stringify(objectMapper, mapFromString);

        return ResponseEntity.ok(result);
        
    }
    

}