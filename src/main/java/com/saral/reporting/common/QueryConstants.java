package com.saral.reporting.common;

public class QueryConstants {

public static final String GET_REPORTVIWER_DATE= "from ApplInfoJson where service_id=? and location_value in (?)  order by service_id";

public static final String GET_REPORTVIWER= "from ApplInfoJson where serviceId=? and locationValue=? and (combinedJson->?) order by serviceId";
}
