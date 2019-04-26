package com.saral.reporting.DAOimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saral.reporting.DAO.ReportViwerDAO;
import com.saral.reporting.common.DaoSupport;
import com.saral.reporting.model.ApplInfo;
import com.saral.reporting.model.ApplInfoJson;
import com.saral.reporting.model.HrOrgUnits;
import com.saral.reporting.model.ReportBean;
import com.saral.reporting.repo.ApplInfoJsonRepository;
import com.saral.reporting.utils.CommonUtils;
import com.saral.reporting.utils.JsonUtils;
import com.saral.reporting.utils.StringConstants;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Service
public class ReportViwerImpl extends DaoSupport implements ReportViwerDAO {

	@Autowired
	ApplInfoJsonRepository applInfoJsonRepository;

	@Autowired
	private SessionFactory sessionFactory1;

	@Autowired
	private EntityManager manager;

	@SuppressWarnings({ "unchecked" })
	public Page<ApplInfoJson> findByCombinedJson(Long serviceId, Pageable pageable, List<Long> locationvalues,
			String commonJson) {

		// criteriaQuery=
		DetachedCriteria criteria = DetachedCriteria.forClass(ApplInfoJson.class);

		if (CommonUtils.isNotEmpty(serviceId)) {
			criteria.add(Restrictions.eq("serviceId", serviceId));
		}
		if (CommonUtils.isNotEmpty(locationvalues)) {
			criteria.add(Restrictions.in("locationValue", locationvalues));
		}
		if (CommonUtils.isNotEmpty(commonJson)) {
			criteria.add(Restrictions.sqlRestriction(commonJson));
		}

		pageable = new PageRequest(pageable.getPageNumber() - 1, 150);
		List<ApplInfoJson> results = (List<ApplInfoJson>) getHibernateTemplate().findByCriteria(criteria,
				(int) pageable.getOffset(), 150);
		// System.out.println(results);

		Page<ApplInfoJson> pageImpianto = new PageImpl<ApplInfoJson>(results, pageable, results.size());

		/*
		 * if (commonJson=="") {
		 * 
		 * return (List<ApplInfoJson>)
		 * getHibernateTemplate().find(QueryConstants.GET_REPORTVIWER_DATE,
		 * serviceId,locationvalue); } else { return (List<ApplInfoJson>)
		 * getHibernateTemplate().find(QueryConstants.GET_REPORTVIWER,
		 * serviceId,locationvalue,commonJson); }
		 */
		return (Page<ApplInfoJson>) pageImpianto;

	}

	@SuppressWarnings("unchecked")
	public Long findCount(Long serviceId, List<Long> locationvalues, String commonJson) {

		DetachedCriteria criteria = DetachedCriteria.forClass(ApplInfoJson.class);

		if (CommonUtils.isNotEmpty(serviceId)) {
			criteria.add(Restrictions.eq("serviceId", serviceId));
		}
		if (CommonUtils.isNotEmpty(locationvalues)) {
			criteria.add(Restrictions.in("locationValue", locationvalues));
		}
		if (CommonUtils.isNotEmpty(commonJson)) {
			criteria.add(Restrictions.sqlRestriction(commonJson));
		}
		criteria.setProjection(Projections.rowCount());
		List<Object> results = new ArrayList<>();
		try {
			results = (List<Object>) getHibernateTemplate().findByCriteria(criteria);
			;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (results.size() > 1) {
			return (long) results.size();
		} else if (results.size() == 1) {

			return (Long) results.get(0);
		}

		else
			return 0L;
	}

	@SuppressWarnings("unchecked")
	public List<ApplInfoJson> findByCombinedJson(Long serviceId, List<Long> locationvalues, String commonJson) {

		// criteriaQuery=
		DetachedCriteria criteria = DetachedCriteria.forClass(ApplInfoJson.class);

		if (CommonUtils.isNotEmpty(serviceId)) {
			criteria.add(Restrictions.eq("serviceId", serviceId));
		}
		if (CommonUtils.isNotEmpty(locationvalues)) {
			criteria.add(Restrictions.in("locationValue", locationvalues));
		}
		if (CommonUtils.isNotEmpty(commonJson)) {
			criteria.add(Restrictions.sqlRestriction(commonJson));
		}

		List<ApplInfoJson> results = (List<ApplInfoJson>) getHibernateTemplate().findByCriteria(criteria);

		return results;

	}

	@Override
	public Long findCountForDept(String filterString) {

		Criteria criteria = sessionFactory1.openSession().createCriteria(ApplInfo.class);

		if (CommonUtils.isNotEmpty(filterString)) {
			criteria.add(Restrictions.sqlRestriction(filterString));
		}
		criteria.setProjection(Projections.rowCount());

		Long count = (Long) criteria.uniqueResult();

		return count;
	}

	@Override
	public Long findCountForAdmin(List<Long> filterbserviceId, List<Long> filterbdisttId, List<Long> filterbdeptId,
			String abc) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ApplInfo.class);
		System.out.println("filterbdeptId" + filterbdeptId);

		if (filterbdeptId.size() > 0) {
			criteria.add(Restrictions.in("departmentId", filterbdeptId));
		}

		if (filterbserviceId.size() > 0) {
			criteria.add(Restrictions.in("serviceId", filterbserviceId));
		}

		if (CommonUtils.isNotEmpty(abc)) {
			criteria.add(Restrictions.sqlRestriction(abc));
		}
		criteria.setProjection(Projections.rowCount());

		@SuppressWarnings("unchecked")
		List<Object> results = (List<Object>) getHibernateTemplate().findByCriteria(criteria);
		if (results.size() > 1) {
			return (long) results.size();
		} else if (results.size() == 1) {

			return (Long) results.get(0);
		}

		else
			return 0L;
	}

	@SuppressWarnings("unchecked")
	public Page<ApplInfo> findByCombinedJsonAdminReport(List<Long> filterbserviceId, Pageable pageable,
			List<Long> filterbdisttId, List<Long> filterbdeptId, String abc, String orderby) {

		// criteriaQuery=
		DetachedCriteria criteria = DetachedCriteria.forClass(ApplInfo.class);

		if (filterbdeptId.size() > 0) {
			criteria.add(Restrictions.in("departmentId", filterbdeptId));
		}

		if (filterbserviceId.size() > 0) {
			criteria.add(Restrictions.in("serviceId", filterbserviceId));
		}

		if (CommonUtils.isNotEmpty(abc)) {
			criteria.add(Restrictions.sqlRestriction(abc));

		}

		pageable = new PageRequest(pageable.getPageNumber() - 1, 150);
		List<ApplInfo> results = (List<ApplInfo>) getHibernateTemplate().findByCriteria(criteria,
				(int) pageable.getOffset(), 150);

		Page<ApplInfo> pageImpianto = new PageImpl<ApplInfo>(results, pageable, results.size());
		System.out.println("pageImpianto" + pageImpianto.getSize());
		System.out.println("pageImpianto" + pageImpianto);

		/*
		 * if (commonJson=="") {
		 * 
		 * return (List<ApplInfoJson>)
		 * getHibernateTemplate().find(QueryConstants.GET_REPORTVIWER_DATE,
		 * serviceId,locationvalue); } else { return (List<ApplInfoJson>)
		 * getHibernateTemplate().find(QueryConstants.GET_REPORTVIWER,
		 * serviceId,locationvalue,commonJson); }
		 */
		return (Page<ApplInfo>) pageImpianto;

	}

	@Override
	public Object findAggregationCombinedJson(List<Long> filterbserviceId, List<Long> filterbdisttId,
			List<Long> filterbdeptId, String abc, String agr, String column) {

		// criteriaQuery=
		DetachedCriteria criteria = DetachedCriteria.forClass(ApplInfo.class);
		String col = ApplInfo.returnColumnPojoName(column);
		if (filterbdeptId.size() > 0) {
			criteria.add(Restrictions.in("departmentId", filterbdeptId));
		}

		if (filterbserviceId.size() > 0) {
			criteria.add(Restrictions.in("serviceId", filterbserviceId));
		}

		if (CommonUtils.isNotEmpty(abc)) {
			criteria.add(Restrictions.sqlRestriction(abc));

		}
		if (agr.equalsIgnoreCase("sum")) {
			criteria.setProjection(Projections.sum(col));

		}
		if (agr.equalsIgnoreCase("avg")) {
			criteria.setProjection(Projections.avg(col));

		}

		if (agr.equalsIgnoreCase("count")) {
			criteria.setProjection(Projections.count(col));

		}
		Object results = null;
		try {
			results = getHibernateTemplate().findByCriteria(criteria);
		} catch (Exception e) {
			results = 0;

		}
		System.out.println(results);
		return results;

	}

	@SuppressWarnings("unchecked")
	public List<ApplInfo> findByCombinedJsonAdminExport(List<Long> filterbserviceId, List<Long> filterbdisttId,
			List<Long> filterbdeptId, String abc) {

		// criteriaQuery=
		DetachedCriteria criteria = DetachedCriteria.forClass(ApplInfo.class);

		if (filterbdeptId.size() > 0) {
			criteria.add(Restrictions.in("departmentId", filterbdeptId));
		}

		if (filterbserviceId.size() > 0) {
			criteria.add(Restrictions.in("serviceId", filterbserviceId));
		}

		if (CommonUtils.isNotEmpty(abc)) {
			criteria.add(Restrictions.sqlRestriction(abc));

		}

		List<ApplInfo> results = (List<ApplInfo>) getHibernateTemplate().findByCriteria(criteria);
		return results;
	}

	@SuppressWarnings("unchecked")
	public JSONArray ColumnJsonbCount(String columnId, Long servID) throws JSONException {
		// columnId ="version_no";
		String Query = "select count(*),  combined_json->> '" + columnId + "' from " + StringConstants.TABLE
				+ ".r_app_json ";

		// pass Service id here
		if (servID != 0L || servID != null) {
			// String subQuery = "where combined_json @> '{ \"department_id\" : " + depId +
			// " }'";
			String subQuery = "where  service_id = '" + servID + "'";
			Query = Query.concat(subQuery);
		}

		String grpby = " group by combined_json->> '" + columnId + "'";
		Query = Query.concat(grpby);

		List<String> arrays = new ArrayList<String>();
		arrays.add("y");
		arrays.add("name");
		JSONArray arraygroupby = new JSONArray();
		List<Object[]> results = (List<Object[]>) manager.createNativeQuery(Query).getResultList();

		for (Object[] l : results) {
			JSONObject json = new JSONObject();
			for (int i = 0; i < arrays.size(); i++) {
				List<Object> list = Arrays.asList(l);
				if (list.get(i) == null) {
					try {
						json.put(arrays.get(i).replaceAll("\"", ""), "NA");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if ((list.get(i)).equals(null) || (list.get(i)).equals("null")) {
					json.put(arrays.get(i).replaceAll("\"", ""), "NA");
				} else {
					json.put(arrays.get(i).replaceAll("\"", ""), list.get(i));
				}
			}
			arraygroupby.put(json);
			// System.out.println(arraygroupby);
		}
		return arraygroupby;
	}

	@SuppressWarnings("unchecked")
	public JSONArray ColumnJsonbCountForAdmin(String columnId, Long depId) throws JSONException {
		// columnId ="version_no";
		String Query = "select count(*), " + columnId + " from " + StringConstants.TABLE + ".r_applinfo ";

		// pass department id here
		if (depId != 0L || depId != null) {
			String subQuery = "where  department_id = '" + depId + "'";
			Query = Query.concat(subQuery);
		}

		String grpby = " group by " + columnId + "";
		Query = Query.concat(grpby);

		List<String> arrays = new ArrayList<String>();
		arrays.add("y");
		arrays.add("name");
		JSONArray arraygroupby = new JSONArray();
		List<Object[]> results = (List<Object[]>) manager.createNativeQuery(Query).getResultList();
		System.out.println(results);

		for (Object[] l : results) {
			JSONObject json = new JSONObject();
			for (int i = 0; i < arrays.size(); i++) {
				List<Object> list = Arrays.asList(l);
				if (list.get(i) == null) {
					json.put(arrays.get(i).replaceAll("\"", ""), "NA");
				} else if ((list.get(i)).equals(null) || (list.get(i)).equals("null")) {
					json.put(arrays.get(i).replaceAll("\"", ""), "NA");
				} else {
					json.put(arrays.get(i).replaceAll("\"", ""), list.get(i));
				}
			}
			arraygroupby.put(json);
			// System.out.println(arraygroupby);
		}
		return arraygroupby;
	}

	@SuppressWarnings("unchecked")
	public JSONArray DeptCountForAdminDash(List<Long> depId) throws JSONException {
		// columnId ="version_no";
		String DeptList = StringUtils.join(depId, ',');

		String Query = "select department_name, count(*) from " + StringConstants.TABLE
				+ ".r_applinfo where department_id in (" + DeptList + ") group by department_name";

		List<String> arrays = new ArrayList<String>();
		arrays.add("name");
		arrays.add("y");
		JSONArray arraygroupby = new JSONArray();
		List<Object[]> results = (List<Object[]>) manager.createNativeQuery(Query).getResultList();
		System.out.println(results);

		for (Object[] l : results) {
			JSONObject json = new JSONObject();
			for (int i = 0; i < arrays.size(); i++) {
				List<Object> list = Arrays.asList(l);
				if (list.get(i) == null) {
					json.put(arrays.get(i).replaceAll("\"", ""), "NA");
				} else if ((list.get(i)).equals(null) || (list.get(i)).equals("null")) {
					json.put(arrays.get(i).replaceAll("\"", ""), "NA");
				} else {
					json.put(arrays.get(i).replaceAll("\"", ""), list.get(i));
				}
			}
			arraygroupby.put(json);
			// System.out.println(arraygroupby);
		}
		return arraygroupby;
	}

	@SuppressWarnings("unchecked")
	public JSONArray ColumnServiceCountByDeprt(String departmentId, Character c) throws JSONException {
		// columnId ="version_no";
		String Query = "select  service_name, count(*) from " + StringConstants.TABLE
				+ ".r_applinfo where department_id = " + departmentId + "  group by service_name";
		List<String> arrays = new ArrayList<String>();

		if (c.equals('L') || c == 'L') {
			/*
			 * arrays.add("label"); arrays.add("data");
			 */

			arrays.add("name");
			arrays.add("y");
		} else if (c.equals('V') || c == 'V') {
			arrays.add("y");
			arrays.add("name");
		}
		JSONArray arraygroupby = new JSONArray();
		List<Object[]> results = (List<Object[]>) manager.createNativeQuery(Query).getResultList();
		System.out.println(results);

		for (Object[] l : results) {
			JSONObject json = new JSONObject();
			for (int i = 0; i < arrays.size(); i++) {

				List<Object> list = Arrays.asList(l);
				if (list.get(i) == null) {

					json.put(arrays.get(i).replaceAll("\"", ""), "NA");

				} else if ((list.get(i)).equals(null) || (list.get(i)).equals("null")) {

					json.put(arrays.get(i).replaceAll("\"", ""), "NA");
				}

				else {
					json.put(arrays.get(i).replaceAll("\"", ""), list.get(i));
				}
			}

			arraygroupby.put(json);
			// System.out.println(arraygroupby);

		}
		return arraygroupby;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object findSumofColumn(String columnId, String departmentId, String aggr, String where,
			List<Long> locationList) {
		String Query = "";
		String whereconcat = "";
		String loc = "";
		if (!where.equals("")) {
			whereconcat = " and " + where;
		} else {
			whereconcat = "";
		}

		if (locationList.size() > 0) {
			loc = "and  location_value in(" + StringUtils.join(locationList, ',') + ")";
		}
		if (aggr.equalsIgnoreCase("sum") || aggr.equalsIgnoreCase("avg")) {

			Query = "select " + aggr + "(CASE WHEN  combined_json->>'" + columnId
					+ "'~E'^[+-]?([0-9]*[.])?[0-9]+' THEN  cast(combined_json->> '" + columnId
					+ "'as float) ELSE 0  end) from " + StringConstants.TABLE + ".r_app_json where "
					+ "combined_json @> '{ \"department_id\" : " + departmentId + " }' " + whereconcat + loc;
		} else if (aggr.equalsIgnoreCase("count")) {
			Query = "select count(combined_json ->>'" + columnId + "')  from " + StringConstants.TABLE + ".r_app_json "
					+ "where combined_json @> '{ \"department_id\" : " + departmentId + " }' " + whereconcat + loc;
		}
		List<Object> results = (List<Object>) manager.createNativeQuery(Query).getResultList();
		System.out.println(results.get(0));
		Object value = (Object) results.get(0);
		return value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object findSumofColumnForAdmin(String columnId, String departmentId, String aggr) {
		String Query = "";
		if (aggr.equalsIgnoreCase("sum") || aggr.equalsIgnoreCase("avg")) {
			Query = "select " + aggr + "(CASE WHEN  combined_json->>'" + columnId
					+ "'~E'^\\\\d+$' THEN  cast(combined_json->> '" + columnId + "'as Int) ELSE 0  end) from "
					+ StringConstants.TABLE + ".r_app_json where " + "combined_json @> '{ \"department_id\" : "
					+ departmentId + " }'";
		} else if (aggr.equalsIgnoreCase("count")) {
			Query = "select count(combined_json ->>'" + columnId + "')  from " + StringConstants.TABLE + ".r_app_json "
					+ "where combined_json @> '{ \"department_id\" : " + departmentId + " }'";
		}
		List<Object> results = (List<Object>) manager.createNativeQuery(Query).getResultList();
		System.out.println(results.get(0));
		Object value = (Object) results.get(0);
		return value;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public JSONArray selectWhereColumnsForReport(String Groupcolumn, String where, String groupby, int partitionSize,
			List<String> arrays, String aggregation, String having, Long serviceId, List<Long> locationvalues)
			throws JSONException {

		if (where.toString() == "" || where.toString().equals("")) {
			System.out.println("inside if loop of where by");
			where = "where  service_id = " + serviceId + " ";
		} else {
			where = " where " + where + " and  service_id = " + serviceId + " ";
			System.out.println("inside else loop of where by");
		}

		if (locationvalues.size() > 0) {
			String locValuesComma = StringUtils.join(locationvalues, ",");

			where = where.concat(" and location_value in  (" + locValuesComma + " ) ");
		}
		// 3 checks for string condition based on aggregation and Groupcolumn
		String select = "";
		if ((aggregation.toString() == "") && (Groupcolumn.toString() == "")) {
			select = "";
		}

		else if ((aggregation.toString() == "") && (Groupcolumn.toString() != "")) {
			select = Groupcolumn;
		}

		else if ((aggregation.toString() != "") && (Groupcolumn.toString() == "")) {
			select = aggregation;
		}

		else {

			select = aggregation + " , " + Groupcolumn;
		}

		String query = " Select " + select + " from saral1.r_app_json " + where + groupby + having;
		List<Object[]> results = manager.createNativeQuery(query).getResultList();
		JSONArray arraygroupby = new JSONArray();

		// for many columns
		if (arrays.size() > 1 && results.size() > 0) {
			for (Object[] l : results) {
				JSONObject json = new JSONObject();
				for (int i = 0; i < arrays.size(); i++) {

					List<Object> list = Arrays.asList(l);
					if (list.get(i) == null) {

						json.put(arrays.get(i).replaceAll("\"", "").replaceAll("\\s", "").replace("'", "")
								.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\:", ""), "NA");

					} else if ((list.get(i)).equals(null) || (list.get(i)).equals("null")) {

						json.put(arrays.get(i).replaceAll("\"", "").replaceAll("\\s", "").replace("'", "")
								.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\:", ""), "NA");
					}

					else {
						json.put(
								arrays.get(i).replaceAll("\"", "").replaceAll("\\s", "").replace("'", "")
										.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\:", ""),
								list.get(i));
					}
				}

				arraygroupby.put(json);
			}

			return arraygroupby;
		} else if (arrays.size() == 1 && results.size() > 0) {
			System.out.println("i am here for one column--------->" + results.size());

			for (Object l : results) {
				JSONObject json = new JSONObject();
				logger.info(l);
				if (l != null) {
					json.put(arrays.get(0).replaceAll("\"", "").replaceAll("\\s", "").replace("'", "")
							.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\:", ""), l);
				} else {
					json.put(arrays.get(0).replaceAll("\"", "").replaceAll("\\s", "").replace("'", "")
							.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\:", ""), "NA");
				}
				arraygroupby.put(json);
				logger.info(arraygroupby);
			}

			return arraygroupby;
		} else {
			JSONObject json = new JSONObject();
			json.put("No_value_Selected", "No_records_found");
			arraygroupby.put(json);
			return arraygroupby;

		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONArray selectWhereColumnsForReportAdmin(String Groupcolumn, String where, String groupby,
			int partitionSize, List<String> arrays, String aggregation, String having, List<Long> filterbserviceId,
			List<Long> filterbdisttId, List<Long> filterbdeptId) {

		logger.info("filterbserviceId ------------->" + filterbserviceId);
		logger.info("filterbdisttId ------------->" + filterbdisttId);
		logger.info("filterbdeptId ------------->" + filterbdeptId);
		if (where.toString() == "" || where.toString().equals("")) {
			System.out.println("inside if loop of where by");
			where = "";
		} else {
			where = " where " + where;
			System.out.println("inside else loop of where by");
		}
		if (filterbdeptId.size() > 0) {
			String filterbdeptidValues = StringUtils.join(filterbdeptId, ",");
			if (!where.equals("") && where.toString() != "") {

				/*
				 * 
				 * if (filterbserviceId.size() > 0) { criteria.add(Restrictions.in("serviceId",
				 * filterbserviceId)); }
				 */
				where = where.concat(" and department_id  in (" + filterbdeptidValues + ") ");
			}

			else {
				where = where.concat(" department_id  in (" + filterbdeptidValues + ") ");
			}
		}
		if (filterbserviceId.size() > 0) {

			String filterbserviceIdValues = StringUtils.join(filterbserviceId, ",");
			if (!where.equals("") && where.toString() != "") {

				/*
				 * 
				 * if (filterbserviceId.size() > 0) { criteria.add(Restrictions.in("serviceId",
				 * filterbserviceId)); }
				 */
				where = where.concat(" and service_id  in (" + filterbserviceIdValues + ") ");
			}

			else {
				where = where.concat(" service_id  in (" + filterbserviceIdValues + ") ");
			}

		}
		if (filterbdisttId.size() > 0) {

		}

		// 3 checks for string condition based on aggregation and Groupcolumn
		String select = "";
		if ((aggregation.toString() == "") && (Groupcolumn.toString() == "")) {
			select = "";
		}

		else if ((aggregation.toString() == "") && (Groupcolumn.toString() != "")) {
			select = Groupcolumn;
		}

		else if ((aggregation.toString() != "") && (Groupcolumn.toString() == "")) {
			select = aggregation;
		}

		else {

			select = aggregation + " , " + Groupcolumn;
		}

		String query = "Select " + select + " from " + StringConstants.TABLE + ".r_applinfo  this_ " + where + groupby
				+ having;
		List<Object[]> results = manager.createNativeQuery(query).getResultList();
		JSONArray arraygroupby = new JSONArray();

		// for many columns
		if (arrays.size() > 1 && results.size() > 0) {
			for (Object[] l : results) {
				JSONObject json = new JSONObject();
				for (int i = 0; i < arrays.size(); i++) {

					List<Object> list = Arrays.asList(l);
					if (list.get(i) == null) {

						try {
							json.put(arrays.get(i).replaceAll("\"", ""), "NA");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else if ((list.get(i)).equals(null) || (list.get(i)).equals("null")) {

						try {
							json.put(arrays.get(i).replaceAll("\"", ""), "NA");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					else {
						try {
							json.put(arrays.get(i).replaceAll("\"", ""), list.get(i));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				arraygroupby.put(json);
			}

			return arraygroupby;
		} else if (arrays.size() == 1 && results.size() > 0) {
			System.out.println("i am here for one column--------->" + results.size());
			for (Object l : results) {

				JSONObject json = new JSONObject();
				try {
					json.put(arrays.get(0).replaceAll("\"", ""), l);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				arraygroupby.put(json);
			}

			return arraygroupby;
		} else {
			JSONObject json = new JSONObject();
			try {
				json.put("No_value_Selected", "No_records_found");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			arraygroupby.put(json);
			return arraygroupby;

		}
	}

	@SuppressWarnings("unchecked")
	public List<HrOrgUnits> findByParentOrgUnitCode(List<Long> parentIds) {

		// criteriaQuery=
		DetachedCriteria criteria = DetachedCriteria.forClass(HrOrgUnits.class);

		if (CommonUtils.isNotEmpty(parentIds)) {
			criteria.add(Restrictions.in("parentOrgUnitCode", parentIds));
		}

		List<HrOrgUnits> results = (List<HrOrgUnits>) getHibernateTemplate().findByCriteria(criteria);

		return results;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReportBean> findByDepartmentIdAndIsAdminReportAndServiceId(Long department_id, char c,
			List<Long> userAllocatedServices) {
		// TODO Auto-generated method stub
		// return reportBeanRepository.findByDepartmentIdAndIsAdminReport(department_id,
		// c);
		DetachedCriteria criteria = DetachedCriteria.forClass(ReportBean.class);
		if (CommonUtils.isNotEmpty(department_id)) {
			criteria.add(Restrictions.eq("departmentId", department_id));
		}
		if (CommonUtils.isNotEmpty(c)) {
			criteria.add(Restrictions.eq("isAdminReport", c));
		}
		if (CommonUtils.isNotEmpty(userAllocatedServices)) {
			criteria.add(Restrictions.in("serviceId", userAllocatedServices));
		}

		List<ReportBean> reportList = (List<ReportBean>) getHibernateTemplate().findByCriteria(criteria);

		return reportList;

	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONArray partialSum(String columns, Pageable pageable) {
		// TODO Auto-generated method stub
		String value = JsonUtils.partialSumJoiner(columns);
		List<String> arrays = Arrays.asList(columns.split("\\s*,\\s*"));
		String query = " Select " + value + " from saral1.r_app_json ";
		pageable = new PageRequest(pageable.getPageNumber() - 1, 150);
		List<Object[]> results = manager.createNativeQuery(query).setMaxResults(150)
				.setFirstResult(pageable.getOffset()).getResultList();
		JSONArray arraygroupby = new JSONArray();

		// for many columns
		if (arrays.size() > 1 && results.size() > 0) {
			for (Object[] l : results) {
				JSONObject json = new JSONObject();
				for (int i = 0; i < arrays.size(); i++) {

					List<Object> list = Arrays.asList(l);
					if (list.get(i) == null) {

						try {
							json.put(arrays.get(i).replaceAll("\"", ""), "NA");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else if ((list.get(i)).equals(null) || (list.get(i)).equals("null")) {

						try {
							json.put(arrays.get(i).replaceAll("\"", ""), "NA");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					else {
						try {
							json.put(arrays.get(i).replaceAll("\"", ""), list.get(i));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				arraygroupby.put(json);
			}

			return arraygroupby;
		} else if (arrays.size() == 1 && results.size() > 0) {
			System.out.println("i am here for one column--------->" + results.size());
			for (Object l : results) {

				JSONObject json = new JSONObject();
				try {
					json.put(arrays.get(0).replaceAll("\"", ""), l);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				arraygroupby.put(json);
			}

			return arraygroupby;
		} else {
			JSONObject json = new JSONObject();
			try {
				json.put("No_value_Selected", "No_records_found");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			arraygroupby.put(json);
			return arraygroupby;
		}

	}
}