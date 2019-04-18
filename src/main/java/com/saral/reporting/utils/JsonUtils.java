package com.saral.reporting.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.ModelMap;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saral.reporting.model.ApplInfo;

public class JsonUtils {


	public static void pageModel(ModelMap model, Page<?> pages) {
		int current = pages.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		/*
		 * int end = Math.min(begin + 5, pages.getTotalPages());
		 * 
		 * model.addAttribute("end", end);
		 */
		model.addAttribute("begin", begin);
		model.addAttribute("current", current);
		/*
		 * model.addAttribute("totalPages", pages.getTotalPages());
		 * model.addAttribute("totalElements", pages.getTotalElements());
		 */
	}

	@SuppressWarnings("unchecked")
	public static JSONObject mergeJSONObjects(JSONObject obj1, JSONObject obj2) {
		try {
			

			obj1.putAll(obj2);
			System.out.print(obj1);

		} catch (Exception e) {
			throw new RuntimeException("JSON Exception" + e);
		}
		return obj1;
	}

	public static String StringUtilsAtLast(String str) {
		if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == '}') {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}

	public static String StringUtilsAtFirst(String str) {
		if (str != null && str.length() > 0) {
			str = str.substring(1);
		}
		return str;
	}

	public static Map<String, Object> getMapFromString(String str) {

		final ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> mapFromString = new LinkedHashMap<>();
		try {
			mapFromString = mapper.readValue(str, new TypeReference<Map<String, Object>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapFromString;
	}

	public static String FileNameDate() {
		String pattern = "yyyy-MM-dd-HH-mm-ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		System.out.println(date);
		return date;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> toMap(org.json.JSONObject object) throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();

		Iterator<String> keysItr = object.keys();
		while (keysItr.hasNext()) {
			String key = keysItr.next();
			Object value = object.get(key);

			if (value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if (value instanceof org.json.JSONObject) {
				value = toMap((org.json.JSONObject) value);
			}
			map.put(key, value);
		}
		return map;
	}

	public static String partialSumJoiner(String col) {
		int count = 0;
		List<String> itemCol = Arrays.asList(col.split("\\s*,\\s*"));
		StringJoiner joiner = new StringJoiner(" , ");
		String builder = "";
		for (String s : itemCol) {
			count = count + 1;
			builder = " sum(CASE WHEN  combined_json->>" + "'" + s + "'~E'!(~ | -)' and combined_json->>" + "'" + s + ""
					+ "'~E'^[+-]?([0-9]*[.])?[0-9]+'"

					+ " THEN  cast(combined_json->> " + "'" + s + "" + "'as float) ELSE 0 end)  as " + "col" + count
					+ " ";
			joiner.add(builder);

		}

		return joiner.toString();

	}

	public static List<Object> toList(org.json.JSONArray array) throws JSONException {
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < array.length(); i++) {
			Object value = array.get(i);
			if (value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if (value instanceof org.json.JSONObject) {
				value = toMap((org.json.JSONObject) value);
			}
			list.add(value);
		}
		return list;
	}

	public static String stringWhere(String str, Long ServiceId) throws ParseException {

		JSONParser parser = new JSONParser();
		JSONObject array = (JSONObject) parser.parse(str);

		org.json.simple.JSONArray objarray = (org.json.simple.JSONArray) array.get("Where");
		String and = "";
		String or = "";
		StringJoiner joinerOr = new StringJoiner(" or ");
		StringJoiner joiner1 = new StringJoiner(" , ");
		String builder = "";
		String builer = "";
		if (objarray.size() > 0) {
			for (Object o1 : objarray) {
				System.out.println(o1);

				JSONObject json = (JSONObject) o1;
				if (json.get("And Or").toString().equals("and")) {

					if ((json.get("Column").toString().equals("version_no")
							|| json.get("Column").toString().equals("sub_version")
							|| json.get("Column").toString().equals("amount")
							|| json.get("Column").toString().equals("department_id")
							|| json.get("Column").toString().equals("no_of_attachment")
							|| json.get("Column").toString().equals("service_id")
							|| json.get("Column").toString().equals("appl_id")
							|| json.get("Column").toString().equals("base_service_id"))) {
						builer = "\"" + json.get("ColumnId").toString() + "\"" + " : " + json.get("Value").toString();
						joiner1.add(builer);
					} else {
						builer = "\"" + json.get("ColumnId").toString() + "\"" + " : " + "\""
								+ json.get("Value").toString() + "\"";
						joiner1.add(builer);
					}

				}

				else if (json.get("And Or").toString().equals("or")) {
					if ((json.get("Column").toString().equals("version_no")
							|| json.get("Column").toString().equals("sub_version")
							|| json.get("Column").toString().equals("amount")
							|| json.get("Column").toString().equals("department_id")
							|| json.get("Column").toString().equals("no_of_attachment")
							|| json.get("Column").toString().equals("service_id")
							|| json.get("Column").toString().equals("appl_id")
							|| json.get("Column").toString().equals("base_service_id"))) {
						builder = "combined_json @> '{ " + "\"" + json.get("ColumnId").toString() + "\"" + " : "
								+ json.get("Value").toString() + "}'";
						joinerOr.add(builder);
					} else {
						builder = "combined_json @> '{ " + "\"" + json.get("ColumnId").toString() + "\"" + " : " + "\""
								+ json.get("Value").toString() + "\"}'";
						joinerOr.add(builder);
					}

				}
				and = "combined_json @> '{ " + joiner1 + " }'";
				System.out.println("1 am here" + and);
				or = joinerOr.toString().trim();
				System.out.println("1 am here21" + or);

			}

			String totalResult = "";
			if (and.equalsIgnoreCase("") && or.equalsIgnoreCase("")) {
				System.out.println("1 am here 1");
				totalResult = "";
			} else if (!and.equalsIgnoreCase("") && or.equalsIgnoreCase("")) {
				System.out.println();
				System.out.println("1 am here21");

				totalResult = "(" + and + ")";
			} else if (and.equalsIgnoreCase("") && !or.equalsIgnoreCase("")) {
				System.out.println("1 am here31");

				totalResult = "( service_id ='" + ServiceId + "' and (" + or + "))";
			}

			else if (!and.equalsIgnoreCase("") && !or.equalsIgnoreCase("")) {
				System.out.println("1 am here41");

				totalResult = "(" + and + ")" + " or " + "( service_id ='" + ServiceId + "' and (" + or + "))";
			}
			System.out.println(totalResult);
			return totalResult;
		}
		return "";
	}

	public static String getWhereJoinerReport(String str) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject array = (JSONObject) parser.parse(str);

		org.json.simple.JSONArray objarray = (org.json.simple.JSONArray) array.get("Where");

		System.out.println(objarray);
		StringJoiner joiner = new StringJoiner("&&");
		for (Object o1 : objarray) {

			System.out.println(o1);

			JSONObject json = (JSONObject) o1;
			String builer = " @." + json.get("ColumnId").toString() + json.get("Condition").toString()
					+ json.get("Value").toString() + " ";
			System.out.println(builer);

			joiner.add(builer);

			System.out.println(joiner);
			// joiner.add("@."+json.get("column")+ expression+ value+"));

		}
		String xyz = "[?(" + joiner.toString().trim() + ")]";

		System.out.println(xyz);
		return xyz;

	}

	public static String getSortingJoinerReport(String str) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject array = (JSONObject) parser.parse(str);

		org.json.simple.JSONArray objarray = (org.json.simple.JSONArray) array.get("Order");

		System.out.println(objarray);
		StringJoiner joiner = new StringJoiner(",");
		for (Object o1 : objarray) {

			System.out.println(o1);

			JSONObject json = (JSONObject) o1;
			String builder = "this_.combined_json-> " + "'" + json.get("ColumnId").toString() + "'" + " "
					+ json.get("Order").toString();
			System.out.println(builder);

			joiner.add(builder);

			System.out.println(joiner);
			// joiner.add("@."+json.get("column")+ expression+ value+"));

		}
		String xyz = "";
		if (joiner.toString() == "" || joiner.toString().equals("")) {
			System.out.println("inside if loop of order by");
			return xyz;
		} else {
			xyz = joiner.toString();
			System.out.println(xyz);
			System.out.println("inside else loop of order by");
			return xyz;
		}

	}

	public static String getSortingGroupingJoinerReport(String str) throws ParseException {
		List<String> values = Arrays.asList(str.toString().split("\\s*,\\s*"));
		System.out.println(values);
		StringJoiner joiner = new StringJoiner(",");
		if (values.size() > 0) {

			for (String l : values) {
				if (l.length() > 0) {

					System.out.println(l);

					String builder = "this_.combined_json-> " + "'" + l.toString() + "'" + " asc";
					System.out.println(builder);

					joiner.add(builder);

					System.out.println(joiner);
					// joiner.add("@."+json.get("column")+ expression+ value+"));

				}
			}
		}
		String xyz = "";
		if (joiner.toString() == "" || joiner.toString().equals("")) {
			System.out.println("inside if loop of order by");
			return xyz;
		} else {

			return joiner.toString();
		}

	}

	public static String stringwhereAdminReportNew(String str) {
		
		org.json.JSONObject jsonObj = new org.json.JSONObject(str);
		JSONArray jsonArr = jsonObj.getJSONArray("Where");
		
		List<org.json.JSONObject> jsonValues = new ArrayList<org.json.JSONObject>();
		for (int i = 0; i < jsonArr.length(); i++) {
			jsonValues.add(jsonArr.getJSONObject(i));
		}
		if(jsonValues.size()>0) {
		Collections.sort(jsonValues, new Comparator<org.json.JSONObject>() {
			// You can change "Name" with "ID" if you want to sort by ID
			private static final String KEY_NAME = "ColumnId";

			@Override
			public int compare(org.json.JSONObject a, org.json.JSONObject b) {
				String valA = new String();
				String valB = new String();

				try {
					valA = (String) a.get(KEY_NAME);
					valB = (String) b.get(KEY_NAME);
				} catch (JSONException e) {
					// do something
				}

				return valA.compareTo(valB);
				// if you want to change the sort order, simply use the following:
				// return -valA.compareTo(valB);
			}
		});

		StringBuffer s = new StringBuffer("");

		s.append(" (");
		for (int i = 0; i < jsonValues.size(); i++) {

			if (i == 0) {
				s.append(" (" + jsonValues.get(0).getString("ColumnId") + jsonValues.get(0).getString("Condition") + "'"
						+ jsonValues.get(0).get("Value") + "'");

			}

			if ((i > 0)) {

				if (jsonValues.get(i - 1).getString("ColumnId").equals(jsonValues.get(i).getString("ColumnId"))) {

					s.append(" or " + jsonValues.get(i).getString("ColumnId") + jsonValues.get(i).getString("Condition")
							+ "'" + jsonValues.get(i).get("Value") + "'");

				}

				else if (!(jsonValues.get(i - 1).getString("ColumnId")
						.equals(jsonValues.get(i).getString("ColumnId")))) {

					s.append(")");
					s.append(" " + jsonValues.get(i).getString("And Or") + " ("
							+ jsonValues.get(i).getString("ColumnId") + jsonValues.get(i).getString("Condition") + "'"
							+ jsonValues.get(i).get("Value") + "'");

				}

			}

		}
		s.append("))");
		return s.toString();}
		return "";
	}
	
	public static String stringWhereNew(String str) throws ParseException {
		
		 org.json.JSONObject jsonObj = new  org.json.JSONObject(str);
		 JSONArray jsonArr = jsonObj.getJSONArray("Where");
		
		List< org.json.JSONObject> jsonValues = new ArrayList< org.json.JSONObject>();
		for (int i = 0; i < jsonArr.length(); i++) {
			jsonValues.add(jsonArr.getJSONObject(i));
		}
		Collections.sort(jsonValues, new Comparator< org.json.JSONObject>() {
			// You can change "Name" with "ID" if you want to sort by ID
			private static final String KEY_NAME = "ColumnId";

			@Override
			public int compare( org.json.JSONObject a,  org.json.JSONObject b) {
				String valA = new String();
				String valB = new String();

				try {
					valA = (String) a.get(KEY_NAME);
					valB = (String) b.get(KEY_NAME);
				} catch (JSONException e) {
					// do something
				}
				System.out.println(valA);
				return valA.compareTo(valB);
				// if you want to change the sort order, simply use the following:
				// return -valA.compareTo(valB);
			}
		});

		StringBuffer s = new StringBuffer("");
	
		s.append(" (");
		if(jsonValues.size()>0) {
		for (int i = 0; i < jsonValues.size(); i++) {
			
			if (i == 0) {
				s.append("(" + "combined_json->>'"+jsonValues.get(0).getString("ColumnId")+"'" + jsonValues.get(0).getString("Condition")
						+ "'"+jsonValues.get(0).get("Value")+ "'");
				
			}

			if (( i>0)) {
				
				if (jsonValues.get(i-1).getString("ColumnId").equals(jsonValues.get(i).getString("ColumnId"))) {
					
					s.append(" or " + "combined_json->>'"+ jsonValues.get(i).getString("ColumnId") +"'"+ jsonValues.get(i).getString("Condition")
							+ "'" +jsonValues.get(i).get("Value") +"'" );
				
				}
				
				
				else if (!(jsonValues.get(i-1).getString("ColumnId").equals(jsonValues.get(i).getString("ColumnId")))) {
					
					  s.append(")");
					  s.append(" "+ jsonValues.get(i).getString("And Or")+ " ( " + "combined_json->>'"+ 
					  jsonValues.get(i).getString("ColumnId") +"'"+
					  jsonValues.get(i).getString("Condition") +"'" +jsonValues.get(i).get("Value") +"'");
					 
				}
					
			
		
			
			}
			 
		
			
		}
		s.append("))");
	
		return s.toString();
		}
		return "";

	}

	public static String stringWhereForAdminReport(String str, Long deptID) throws ParseException {

		JSONParser parser = new JSONParser();
		JSONObject array = (JSONObject) parser.parse(str);

		org.json.simple.JSONArray objarray = (org.json.simple.JSONArray) array.get("Where");
		String and = "";
		String or = "";
		Map<String, Integer> mapforCol = new LinkedHashMap<String, Integer>();
		System.out.println(objarray);
		// StringJoiner joiner = new StringJoiner(" and ");
		StringJoiner joinerOr = new StringJoiner(" or ");
		StringJoiner joiner1 = new StringJoiner(" and ");
		String builder = "";
		String builer = "";
		System.out.println(objarray.size());
		if (objarray.size() > 0) {
			for (Object o1 : objarray) {
				System.out.println(o1);

				JSONObject json = (JSONObject) o1;

				Integer count = mapforCol.get(json.get("ColumnId").toString());

				// if the map contains no mapping for the key, then
				// map the key with value of 1
				if (count == null) {
					mapforCol.put(json.get("ColumnId").toString(), 1);
				}
				// else increment the found value by 1
				else {
					mapforCol.put(json.get("ColumnId").toString(), count + 1);

				}
				System.out.println("  mapforCol  " + mapforCol);

				if (json.get("And Or").toString().equals("and")) {
					builer = "this_." + json.get("ColumnId").toString() + " = '" + json.get("Value").toString() + "'";
					System.out.println(builer);

					joiner1.add(builer);
				}

				else if (json.get("And Or").toString().equals("or")) {

					// System.out.println(json.get("version_no"));

					// System.out.println("ssss" + json.get("Column").toString());
					// System.out.println(json.get("version_no"));
					builder = "this_." + json.get("ColumnId").toString() + " = '" + json.get("Value").toString() + "'";
					System.out.println(builder);

					joinerOr.add(builder);
				}
				and = joiner1.toString();
				System.out.println("1 am here" + and);
				or = joinerOr.toString().trim();
				System.out.println("1 am here21" + or);
			}

			String totalResult = "";
			if (and.equalsIgnoreCase("") && or.equalsIgnoreCase("")) {
				System.out.println("1 am here 1");
				totalResult = "";
			} else if (!and.equalsIgnoreCase("") && or.equalsIgnoreCase("")) {
				System.out.println();
				System.out.println("1 am here21");

				totalResult = "(" + and + ")";
			} else if (and.equalsIgnoreCase("") && !or.equalsIgnoreCase("")) {
				System.out.println("1 am here31");

				totalResult = "( this_.department_id = '" + deptID + "' and (" + or + "))";
			}

			else if (!and.equalsIgnoreCase("") && !or.equalsIgnoreCase("")) {
				System.out.println("1 am here41");

				totalResult = "(" + and + ")" + " or " + "( this_.department_id = '" + deptID + "' and (" + or + "))";
			}
			return totalResult;
		}
		return "";
	}

	public static String getSortingJoinerAdminReport(String str) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject array = (JSONObject) parser.parse(str);

		org.json.simple.JSONArray objarray = (org.json.simple.JSONArray) array.get("Order");

		System.out.println(objarray);
		StringJoiner joiner = new StringJoiner(",");
		for (Object o1 : objarray) {

			System.out.println(o1);

			JSONObject json = (JSONObject) o1;
			String builder = "this_." + json.get("ColumnId").toString() + " " + json.get("Order").toString();
			System.out.println(builder);

			joiner.add(builder);

			System.out.println(joiner);
			// joiner.add("@."+json.get("column")+ expression+ value+"));

		}
		String xyz = "";
		if (joiner.toString() == "" || joiner.toString().equals("")) {
			System.out.println("inside if loop of order by");
			return xyz;
		} else {
			xyz = " " + joiner.toString();
			System.out.println(xyz);
			System.out.println("inside else loop of order by");
			return xyz;
		}
	}

	public static String getSortingGroupingJoinerReportAdmin(String str) throws ParseException {
		List<String> values = Arrays.asList(str.toString().split("\\s*,\\s*"));
		System.out.println(values + str);
		StringJoiner joiner = new StringJoiner(",");
		if (values.size() > 0) {

			for (String l : values) {
				if (l.length() > 0) {
					System.out.println(l);

					String builder = "this_." + l.toString() + " asc";
					System.out.println(builder);

					joiner.add(builder);

					System.out.println(joiner);
					// joiner.add("@."+json.get("column")+ expression+ value+"));
				}
			}
		}
		String xyz = "";
		if (joiner.toString() == "" || joiner.toString().equals("")) {
			System.out.println("inside if loop of order by");
			return xyz;
		} else {

			return joiner.toString();
		}

	}

	public static String stringHavingJoiner(String havingCls) throws ParseException {
		// TODO Auto-generated method stub
		System.out.println("Having" + havingCls);
		JSONParser parser = new JSONParser();
		JSONObject array = (JSONObject) parser.parse(havingCls);

		org.json.simple.JSONArray objarray = (org.json.simple.JSONArray) array.get("Having");
		System.out.println(objarray);
		StringJoiner joiner = new StringJoiner(" and ");
		String builder = "";
		if (objarray.size() > 0) {
			for (Object o1 : objarray) {
				System.out.println(o1);

				JSONObject json = (JSONObject) o1;
				if (json.get("Function").toString().equalsIgnoreCase("count")) {
					builder = "count(combined_json->> " + "'" + json.get("ColumnId").toString() + "" + "')"
							+ json.get("Condition") + " " + json.get("Value");
					joiner.add(builder);
				}

				if (json.get("Function").toString().equalsIgnoreCase("sum")
						|| json.get("Function").toString().equalsIgnoreCase("max")
						|| json.get("Function").toString().equalsIgnoreCase("min")) {
					builder = json.get("Function").toString() + "(CASE WHEN  combined_json->>" + "'"
							+ json.get("ColumnId").toString() + "" + "'~E'^\\\\d+$'"

							+ " THEN  cast(combined_json->> " + "'" + json.get("ColumnId").toString() + ""
							+ "'as Int) ELSE 0 end) " + " " + json.get("Condition") + " " + json.get("Value");
					;
					joiner.add(builder);
				}
			}
			String xyz = "";
			if (joiner.toString() == "" || joiner.toString().equals("")) {
				System.out.println("inside if loop of count by");
				return xyz;
			} else {
				xyz = "  having  " + joiner.toString();
				System.out.println(xyz);
				System.out.println("inside else loop of count by");
				return xyz;
			}
		}
		return "";
	}

	public static String stringHavingJoinerForAdmin(String havingCls) throws ParseException {
		// TODO Auto-generated method stub
		System.out.println("Having" + havingCls);
		JSONParser parser = new JSONParser();
		JSONObject array = (JSONObject) parser.parse(havingCls);

		org.json.simple.JSONArray objarray = (org.json.simple.JSONArray) array.get("Having");
		System.out.println(objarray);
		StringJoiner joiner = new StringJoiner(" and ");
		String builder = "";
		if (objarray.size() > 0) {
			for (Object o1 : objarray) {
				System.out.println(o1);

				JSONObject json = (JSONObject) o1;
				if (json.get("Function").toString().equalsIgnoreCase("count")) {
					builder = "count( " + "'" + json.get("ColumnId").toString() + "" + "')" + json.get("Condition")
							+ " " + json.get("Value");
					joiner.add(builder);
				}

				if (json.get("Function").toString().equalsIgnoreCase("sum")
						|| json.get("Function").toString().equalsIgnoreCase("max")
						|| json.get("Function").toString().equalsIgnoreCase("min")) {
					builder = json.get("Function").toString() + "(CASE WHEN pg_typeof(" + ""
							+ json.get("ColumnId").toString() + ")" + " = 'bigint' ::regtype "

							+ " THEN  cast( " + "" + json.get("ColumnId").toString() + "" + " as bigint) ELSE 0 end) "
							+ " " + json.get("Condition") + " " + json.get("Value");
					;
					joiner.add(builder);
				}
			}
			String xyz = "";
			if (joiner.toString() == "" || joiner.toString().equals("")) {
				System.out.println("inside if loop of count by");
				return xyz;
			} else {
				xyz = "  having  " + joiner.toString();
				System.out.println(xyz);
				System.out.println("inside else loop of count by");
				return xyz;
			}
		}
		return "";
	}

	public static List<String> stringAggregationJoinerCoumns(String aggregationCls) throws ParseException {
		// TODO Auto-generated method stub
		System.out.println(aggregationCls);
		JSONParser parser = new JSONParser();
		JSONObject array = (JSONObject) parser.parse(aggregationCls);

		org.json.simple.JSONArray objarray = (org.json.simple.JSONArray) array.get("Aggregation");

		System.out.println(objarray);

		List<String> list = new ArrayList<>();
		for (Object o1 : objarray) {
			System.out.println(o1);

			JSONObject json = (JSONObject) o1;
			if (json.get("Function").toString().equalsIgnoreCase("count")) {
				list.add(json.get("Column").toString() + "_count");

			}
			if (json.get("Function").toString().equalsIgnoreCase("sum")) {
				list.add(json.get("Column").toString() + "_sum");

			}
		}
		return list;
	}

	public static String stringAggregationJoiner(String aggregationCls) throws ParseException {
		// TODO Auto-generated method stub
		System.out.println(aggregationCls);
		JSONParser parser = new JSONParser();
		JSONObject array = (JSONObject) parser.parse(aggregationCls);

		org.json.simple.JSONArray objarray = (org.json.simple.JSONArray) array.get("Aggregation");

		System.out.println(objarray);
		StringJoiner joiner = new StringJoiner(" , ");
		String builder = "";
		if (objarray.size() > 0) {
			for (Object o1 : objarray) {
				System.out.println(o1);

				JSONObject json = (JSONObject) o1;
				if (json.get("Function").toString().equalsIgnoreCase("count")) {
					builder = "count(combined_json->> "
							+ "'" + json.get("ColumnId").toString() + "" + "')" + " as " + json.get("Column").toString()
									.replaceAll("\\s", "").replace("'", "").replaceAll("\\(", "").replaceAll("\\)", "")
							+ "_count";
					joiner.add(builder);
				}

				if (json.get("Function").toString().equalsIgnoreCase("sum")) {
					builder = " sum(CASE WHEN  combined_json->>" + "'" + json.get("ColumnId").toString() + ""
							+ "'~E'^[+-]?([0-9]*[.])?[0-9]+'"

							+ " THEN  cast(combined_json->> " + "'" + json.get("ColumnId").toString() + ""
							+ "'as float) ELSE 0 end) " + json.get("Column").toString().replaceAll("\\s", "")
									.replace("'", "").replaceAll("\\(", "").replaceAll("\\)", "")
							+ "_sum";
					joiner.add(builder);
				}
			}

			String xyz = "";
			if (joiner.toString() == "" || joiner.toString().equals("")) {
				System.out.println("inside if loop of count by");
				return xyz;
			} else {
				xyz = joiner.toString();
				System.out.println(xyz);
				System.out.println("inside else loop of count by");
				return xyz;
			}
		}
		return "";
	}

	public static String stringAggregationJoinerForAdmin(String aggregationCls) throws ParseException {
		// TODO Auto-generated method stub
		System.out.println(aggregationCls);
		JSONParser parser = new JSONParser();
		JSONObject array = (JSONObject) parser.parse(aggregationCls);

		org.json.simple.JSONArray objarray = (org.json.simple.JSONArray) array.get("Aggregation");

		System.out.println(objarray);
		StringJoiner joiner = new StringJoiner(" , ");
		String builder = "";
		if (objarray.size() > 0) {
			for (Object o1 : objarray) {
				System.out.println(o1);

				JSONObject json = (JSONObject) o1;
				if (json.get("Function").toString().equalsIgnoreCase("count")) {
					builder = "count("
							+ "'" + json.get("ColumnId").toString() + "" + "')" + " as " + json.get("Column").toString()
									.replaceAll("\\s", "").replace("'", "").replaceAll("\\(", "").replaceAll("\\)", "")
							+ "_count";
					joiner.add(builder);
				}

				if (json.get("Function").toString().equalsIgnoreCase("sum")) {
					builder = "sum(CASE WHEN pg_typeof(" + "" + json.get("ColumnId").toString() + ")"
							+ " = 'bigint' \\:\\:regtype "

							+ " THEN  cast( " + "" + json.get("ColumnId").toString() + "" + " as bigint) ELSE 0 end) "
							+ json.get("Column").toString().replaceAll("\\s", "").replace("'", "").replaceAll("\\(", "")
									.replaceAll("\\)", "")
							+ "_sum";
					joiner.add(builder);
				}
			}

			String xyz = "";
			if (joiner.toString() == "" || joiner.toString().equals("")) {
				System.out.println("inside if loop of count by");
				return xyz;
			} else {
				xyz = joiner.toString();
				System.out.println(xyz);
				System.out.println("inside else loop of count by");
				return xyz;
			}
		}
		return "";
	}

	@SuppressWarnings("unchecked")
	public static String stringGroupbyColumns(String groupByData) throws ParseException {

		JSONParser parser = new JSONParser();
		JSONObject array = (JSONObject) parser.parse(groupByData);
		StringJoiner joiner = new StringJoiner(" , ");
		System.out.println(array.size());
		if (array.size() > 0) {
			Iterator<String> iterator = array.keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				String value = (String) array.get(key);
				System.out.println(key + " dd " + value);

				String builder = "combined_json->> " + "'" + key + "'" + " as "
						+ value.replaceAll("\\s", "").replace("'", "").replaceAll("\\(", "").replaceAll("\\)", "") + "";
				joiner.add(builder);
			}
			System.out.println(joiner);
			return joiner.toString();
		}

		// TODO Auto-generated method stub
		return "";
	}

	@SuppressWarnings("unchecked")
	public static String stringGroupbyColumnsForAdmin(String groupByData) throws ParseException {

		JSONParser parser = new JSONParser();
		JSONObject array = (JSONObject) parser.parse(groupByData);
		StringJoiner joiner = new StringJoiner(" , ");
		System.out.println(array.size());
		if (array.size() > 0) {
			Iterator<String> iterator = array.keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				String value = (String) array.get(key);
				System.out.println(key + " dd " + value);

				String builder = " " + "" + key + "" + " as "
						+ value.replaceAll("\\s", "").replace("'", "").replaceAll("\\(", "").replaceAll("\\)", "") + "";
				joiner.add(builder);
			}
			System.out.println(joiner);
			return joiner.toString();
		}

		// TODO Auto-generated method stub
		return "";
	}

	@SuppressWarnings("unchecked")
	public static String stringGroupby(String groupByData) throws ParseException {

		JSONParser parser = new JSONParser();
		JSONObject array = (JSONObject) parser.parse(groupByData);
		StringJoiner joiner = new StringJoiner(" , ");
		System.out.println(array.size());
		if (array.size() > 0) {
			Iterator<String> iterator = array.keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				String value = (String) array.get(key);
				System.out.println(key + " dd " + value);

				String builder = "combined_json->> " + "'" + key + "'";
				joiner.add(builder);
			}

			String xyz = "";
			if (joiner.toString() == "" || joiner.toString().equals("")) {
				System.out.println("inside if loop of group by");
				return xyz;
			} else {
				xyz = " group by " + joiner.toString();
				System.out.println(xyz);
				System.out.println("inside else loop of group by");
				return xyz;
			}
		}

		// TODO Auto-generated method stub
		return "";
	}

	@SuppressWarnings("unchecked")
	public static String stringGroupbyForAdmin(String groupByData) throws ParseException {

		JSONParser parser = new JSONParser();
		JSONObject array = (JSONObject) parser.parse(groupByData);
		StringJoiner joiner = new StringJoiner(" , ");
		System.out.println(array.size());
		if (array.size() > 0) {
			Iterator<String> iterator = array.keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				String value = (String) array.get(key);
				System.out.println(key + " dd " + value);

				String builder = " " + "" + key + "";
				joiner.add(builder);
			}

			String xyz = "";
			if (joiner.toString() == "" || joiner.toString().equals("")) {
				System.out.println("inside if loop of group by");
				return xyz;
			} else {
				xyz = " group by " + joiner.toString();
				System.out.println(xyz);
				System.out.println("inside else loop of group by");
				return xyz;
			}
		}

		// TODO Auto-generated method stub
		return "";
	}

	public static boolean isNumeric(String strNum) {
		return strNum.matches("-?\\d+(\\.\\d+)?");
	}

	public static List<String> stringGroupByArray(String groupByData)
			throws ParseException, JsonProcessingException, IOException {
		// TODO Auto-generated method stub
		JSONParser parser = new JSONParser();
		JSONObject array = (JSONObject) parser.parse(groupByData);
		JsonFactory factory = new JsonFactory();

		ObjectMapper mapper = new ObjectMapper(factory);
		JsonNode rootNode = mapper.readTree(array.toJSONString());
		List<String> list = new ArrayList<>();
		Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
		while (fieldsIterator.hasNext()) {

			Map.Entry<String, JsonNode> field = fieldsIterator.next();
			System.out.println("Key: " + field.getKey() + "\tValue:" + field.getValue());
			list.add(field.getValue().toString());
		}
		String[] arrays = list.toArray(new String[0]);
		List<String> listofArrays = new ArrayList<String>();
		Collections.addAll(listofArrays, arrays);

		System.out.println(arrays.length);
		System.out.println(list);
		return listofArrays;

	}

	public static List<String> groupedDataColumnsAdmin(String str) {
		List<String> values = Arrays.asList(str.toString().split("\\s*,\\s*"));
		System.out.println(values);
		
		ApplInfo info = new ApplInfo();
		Map<String, Object> list = info.getColumnNamesWithPojoVariables();
		System.out.println(list);
		List<String> group = new ArrayList<>();

		for (String col : values) {
			if (list.containsKey(col)) {

				String value = (String) list.get(col);
				group.add(value);
			}
		}
		return group;

	}

}
