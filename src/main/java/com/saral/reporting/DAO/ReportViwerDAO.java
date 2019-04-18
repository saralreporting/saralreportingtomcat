package com.saral.reporting.DAO;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.saral.reporting.model.ApplInfo;
import com.saral.reporting.model.ApplInfoJson;
import com.saral.reporting.model.HrOrgUnits;
import com.saral.reporting.model.ReportBean;
@Service
@Repository
public interface ReportViwerDAO {

	public Page<ApplInfoJson> findByCombinedJson(Long serviceId, Pageable pageable, List<Long> locationvalues,String commonJson);
	
	
	public Long findCount(Long serviceId, List<Long> locationvalues,String commonJson);
	
	public Object findAggregationCombinedJson(List<Long> filterbserviceId,
			List<Long> filterbdisttId, List<Long> filterbdeptId, String abc, String agr, String column);
	public List<ApplInfoJson> findByCombinedJson(Long serviceId,  List<Long> locationvalues, String commonJson);

	
	public Long findCountForDept(String filterString);

	
	public Long findCountForAdmin(List<Long> filterbserviceId, List<Long> filterbdisttId, List<Long> filterbdeptId, String abc);
	
	
	public Page<ApplInfo> findByCombinedJsonAdminReport(List<Long> filterbserviceId, Pageable pageable,
			List<Long> filterbdisttId, List<Long> filterbdeptId, String abc, String orderby);
	
	
	public List<ApplInfo> findByCombinedJsonAdminExport(List<Long> filterbserviceId, List<Long> filterbdisttId,
			List<Long> filterbdeptId, String abc);
	
	
	public JSONArray ColumnJsonbCount(String id, Long servID) throws JSONException;
	
	
	public JSONArray ColumnServiceCountByDeprt(String departmentId, Character c) throws JSONException;
	
	
	public Object findSumofColumn(String ColumnId, String departmentId, String aggregation, String abc , List<Long> locationlist);
	
	
	public JSONArray selectWhereColumnsForReport(String groupByDataColumnsForQuery, String abc, String groupByString, int size,
			List<String> groupSplit, String aggregation, String having) throws JSONException;
	
	public JSONArray selectWhereColumnsForReportAdmin(String groupByDataColumnsForQuery, String abc, String groupByString, int size,
			List<String> groupSplit, String aggregation, String having);
	
	public JSONArray DeptCountForAdminDash(List<Long> depId) throws JSONException;
	
	JSONArray ColumnJsonbCountForAdmin(String columnId, Long depId) throws JSONException;


	public List<HrOrgUnits> findByParentOrgUnitCode(List<Long> parentIds);


	Object findSumofColumnForAdmin(String columnId, String departmentId, String aggr);


	public List<ReportBean> findByDepartmentIdAndIsAdminReportAndServiceId(Long department_id, char c,
			List<Long> userAllocatedServices);
	
	
	public JSONArray partialSum(String column, Pageable pageable );
}
