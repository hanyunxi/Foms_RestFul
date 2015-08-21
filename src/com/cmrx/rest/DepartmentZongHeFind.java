package com.cmrx.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.cmrx.bean.SqlEnum;
import com.cmrx.bean.Entity.CityValue;
import com.cmrx.bean.Entity.DepartmentZongHeFindAnJianXingZhi;
import com.cmrx.bean.Entity.DepartmentZongHeFindCity;
import com.cmrx.bean.Entity.DepartmentZongHeFindDnaClass;
import com.cmrx.bean.Entity.DepartmentZongHeFindGetCheck;
import com.cmrx.bean.Entity.GetDataCityValue;
import com.cmrx.bean.Entity.OrganEntity;
import com.cmrx.bean.Entity.OrganizationData;
import com.cmrx.bean.Entity.SearchComprehensiveChartQishu;
import com.cmrx.bean.Entity.SearchGetSyntheticalCaseChuanShu;
import com.cmrx.bean.Entity.SearchGetSyntheticalCaseQishu;
import com.cmrx.bean.Entity.SearchGetSyntheticalCaseQishuMap;
import com.cmrx.dao.DBSupport;
import com.google.gson.Gson;

//导航:综合统计
@Path("/DepartmentZongHeFind")
public class DepartmentZongHeFind {

	
	/* public static DBSupport dbSupport;*/
	

	public DBSupport dbSupport;

	public DBSupport getDbSupport() {
		return dbSupport;
	}

	public void setDbSupport(DBSupport dbSupport) {
		this.dbSupport = dbSupport;
		
		
		
		
		
	}

	public Log log;

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	
/*	 @BeforeClass public static void sansan() { ApplicationContext ac = new
	 ClassPathXmlApplicationContext( "applicationContext.xml"); dbSupport =
	 (DBSupport) ac.getBean("DBSupport"); }
	 */
	@POST
	@Path("/getDetailSubdivisionCount")
	@Produces(MediaType.TEXT_PLAIN)
	public String GetDetailSubdivisionCount(
			@FormParam(value = "begintime") String beginTime,
			@FormParam(value = "endtime") String endTime) {
		try {
			String val = "";
			int count = GetDetailSubdivisionCount1();
			int passcount = GetDetailSubdivisionPassCount(beginTime, endTime);
			int nopasscount = GetDetailSubdivisionNoPassCount(beginTime,
					endTime);

			int sum = (passcount + nopasscount);
			String countbydate = String.valueOf(sum);

			// 1,2,3,4
			val = count + "," + countbydate + "," + passcount + ","
					+ nopasscount;

			return val;
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	// / 根据日期获取小类细分通过审批总数
	public int GetDetailSubdivisionCount1() {
		try {
			String sql = "select count(*) from detail_dict_add where addStatus=3 or addStatus=4";
			int i = dbSupport.getcount2(sql);
			return i;
		} catch (Exception ex) {
			log.AddLog("E",
					"GetDetailSubdivisionCount(" + ")" + ex.getMessage(), "");
			return 0;
		}
	}

	// / 根据日期获取小类细分通过审批总数
	public int GetDetailSubdivisionPassCount(String beginTime, String endTime) {
		try {
			String sql = "select count(*) from detail_dict_add where addStatus=3 ";
			if (beginTime != "" && beginTime.length() > 0) {
				sql += " and createDatetime>='" + beginTime + " 00:00:00'";
			}
			if (endTime != "" && endTime.length() > 0) {
				sql += " and createDatetime<='" + endTime + " 23:59:59'";
			}
			int i = dbSupport.getcount2(sql);
			return i;
		} catch (Exception ex) {
			log.AddLog("E", "GetDetailSubdivisionNoPassCount(" + beginTime
					+ "," + endTime + ")" + ex.getMessage(), "");
			return 0;
		}
	}

	// / 根据日期获取小类细分不通过审批总数
	public int GetDetailSubdivisionNoPassCount(String beginTime, String endTime) {
		try {
			String sql = "select count(*) from detail_dict_add where addStatus=4 ";
			if (beginTime != "" && beginTime.length() > 0) {
				sql += " and createDatetime>='" + beginTime + " 00:00:00'";
			}
			if (endTime != "" && endTime.length() > 0) {
				sql += " and createDatetime<='" + endTime + " 23:59:59'";
			}
			return dbSupport.getcount2(sql);
		} catch (Exception ex) {
			log.AddLog("E", "GetDetailSubdivisionNoPassCount(" + beginTime
					+ "," + endTime + ")" + ex.getMessage(), "");
			return 0;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@POST
	@Path("/getDetailSubdivisionDetails")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String GetDetailSubdivisionDetails(
			@FormParam(value = "begintime") String beginTime,
			@FormParam(value = "endtime") String endTime) {
		try {
			List<OrganEntity> organEntities = GetDetailSubdivisionNoPassDetails(
					beginTime, endTime);
			List<OrganEntity> organEntities2 = GetDetailSubdivisionPassDetails(
					beginTime, endTime);
			Map<String, Object> map1 = new LinkedHashMap<String, Object>();
			map1.put("结果", "拒绝数量");
			Map<String, Object> map2 = new LinkedHashMap<String, Object>();
			map2.put("结果", "通过数量");
			if (organEntities!=null) {
				for (OrganEntity organEntity : organEntities) {
					map1.put(organEntity.getShortName(), organEntity.getNook());
				}
			}
			if (organEntities2!=null) {
				for (OrganEntity organEntity : organEntities2) {
					map2.put(organEntity.getShortName(), organEntity.getNook());
				}
			}
			List list = new ArrayList();
			list.add(map1);
			list.add(map2);
			Gson gson = new Gson();
			return gson.toJson(list).toString();
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	private List<OrganEntity> GetDetailSubdivisionPassDetails(String beginTime,
			String endTime) {
		try {
			String sql = "select shortname,(select count(*) from detail_dict_add where addStatus=3 and createUnit like CONCAT('%',organ.organCode,'%')";
			if (beginTime != "" && beginTime.length() > 0) {
				sql += " and createDatetime>='" + beginTime + " 00:00:00'";
			}
			if (endTime != "" && endTime.length() > 0) {
				sql += " and createDatetime<='" + endTime + " 23:59:59'";
			}
			sql += ") as nook from organ  where organ.organId!=1";
			return (List<OrganEntity>) dbSupport.getSqlQuery(sql,
					OrganEntity.class, true);
		} catch (Exception ex) {
			log.AddLog("E", "GetDetailSubdivisionPassDetails(" + beginTime
					+ "," + endTime + ")" + ex.getMessage(), "");
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	private List<OrganEntity> GetDetailSubdivisionNoPassDetails(
			String beginTime, String endTime) {
		try {
			String sql = "select shortname ,(select count(*) from detail_dict_add where addStatus=4 and createUnit like '%'||organ.organCode||'%'";
			if (beginTime != "" && beginTime.length() > 0) {
				sql += " and createDatetime>=to_date('" + beginTime + " 00:00:00','yyyy-MM-dd HH24:mi:ss')";
			}
			if (endTime != "" && endTime.length() > 0) {
				sql += " and createDatetime<=to_date('" + endTime + " 23:59:59','yyyy-MM-dd HH24:mi:ss')";
			}
			sql += ") as nook from organ  where organ.organId!=1";
			return (List<OrganEntity>) dbSupport.getSqlQuery(sql,
					OrganEntity.class, true);
		} catch (Exception ex) {
			log.AddLog("E", "GetDetailSubdivisionNoPassDetails(" + beginTime
					+ "," + endTime + ")" + ex.getMessage(), "");
			return null;
		}
	}

	@POST
	@Path("/getDepartmentFootprint")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML })
	public String GetDepartmentFootprint(
			@FormParam(value = "departmentbegintime") String beginTime,
			@FormParam(value = "departmentendtime") String endTime) {
		String val = "";
		int sum1 = 0;
		int chuanshu = GetDepartmentFootprintChuanshu(beginTime, endTime);
		List<SearchComprehensiveChartQishu> chartQishus = GetDepartmentFootprintQishu(
				beginTime, endTime);
		for (SearchComprehensiveChartQishu sscq : chartQishus) {
			int sum = Integer.parseInt(sscq.getQishu().toString());
			sum1 += sum;
		}
		val = chuanshu + "," + sum1;
		return val;
	}

	@SuppressWarnings("unchecked")
	private List<SearchComprehensiveChartQishu> GetDepartmentFootprintQishu(
			String beginTime, String endTime) {
		try {
			String sql = "select count(departmentNum) as qishu from departmentcaseinfo where accord='足迹' and dataType=1 ";
			if (beginTime != "" && beginTime.length() > 0) {
				sql += " and createTime>=to_date('" + beginTime + " 00:00:00','yyyy-MM-dd HH24:mi:ss')";
			}
			if (endTime != "" && endTime.length() > 0) {
				sql += " and createTime<=to_date('" + endTime + " 23:59:59','yyyy-MM-dd HH24:mi:ss')";
			}
			sql += " GROUP BY departmentNum";
			List<SearchComprehensiveChartQishu> comprehensiveChartQishus = (List<SearchComprehensiveChartQishu>) dbSupport
					.getSqlQuery2(sql, SearchComprehensiveChartQishu.class,
							true);
			return comprehensiveChartQishus;
		} catch (Exception ex) {
			log.AddLog("E", "GetDepartmentFootprintQishu(" + beginTime + ","
					+ endTime + ")" + ex.getMessage(), "");
			return null;
		}
	}

	private int GetDepartmentFootprintChuanshu(String beginTime, String endTime) {
		try {
			String sql = "select COUNT(*) from (select count(departmentNum) from departmentcaseinfo where accord='足迹' and dataType=1 ";
			if (beginTime != "" && beginTime.length() > 0) {
				sql += " and createTime>=to_date('" + beginTime + " 00:00:00','yyyy-MM-dd HH24:mi:ss')";
			}
			if (endTime != "" && endTime.length() > 0) {
				sql += " and createTime<=to_date('" + endTime + " 00:00:00','yyyy-MM-dd HH24:mi:ss')";
			}
			sql += " GROUP BY departmentNum) as depart";
			return dbSupport.getcount2(sql);
		} catch (Exception ex) {
			log.AddLog("E", "GetDepartmentFootprintChuanshu(" + beginTime + ","
					+ endTime + ")" + ex.getMessage(), "");
			return 0;
		}
	}

	@POST
	@Path("/getDepartmentFingerprint")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML })
	public String GetDepartmentFingerprint(
			@FormParam(value = "departmentbegintime") String beginTime,
			@FormParam(value = "departmentendtime") String endTime) {
		String val = "";
		int sum1 = 0;
		int chuanshu = GetDepartmentFingerprintChuanshu(beginTime, endTime);
		List<SearchComprehensiveChartQishu> chartQishus = GetDepartmentFingerprintQishu(
				beginTime, endTime);
		if(chartQishus==null)
			return "";
		for (SearchComprehensiveChartQishu sscq : chartQishus) {
			int sum = Integer.parseInt(sscq.getQishu().toString());
			sum1 += sum;
		}
		val = chuanshu + "," + sum1;
		return val;
	}

	@SuppressWarnings("unchecked")
	private List<SearchComprehensiveChartQishu> GetDepartmentFingerprintQishu(
			String beginTime, String endTime) {
		try {
			String sql = "select count(departmentNum) as qishu from departmentcaseinfo where accord='指纹' and dataType=1 ";
			if (beginTime != "") {
				sql += " and createTime>=to_date('" + beginTime + " 00:00:00','yyyy-MM-dd HH24:mi:ss')";
			}
			if (endTime != "") {
				sql += " and createTime<=to_date('" + endTime + " 00:00:00','yyyy-MM-dd HH24:mi:ss')";
			}
			sql += " GROUP BY departmentNum";
			List<SearchComprehensiveChartQishu> searchComprehensiveChartQishus = ((List<SearchComprehensiveChartQishu>) dbSupport
					.getSqlQuery(sql, SearchComprehensiveChartQishu.class, true));
			return searchComprehensiveChartQishus;
		} catch (Exception ex) {
			log.AddLog("E", "GetDepartmentFingerprintQishu(" + beginTime + ","
					+ endTime + ")" + ex.getMessage(), "");
			return null;
		}
	}

	private int GetDepartmentFingerprintChuanshu(String beginTime,
			String endTime) {
		try {
			String sql = "select COUNT(*) from (select count(departmentNum) from departmentcaseinfo where accord='指纹' and dataType=1 ";
			if (beginTime != "" && beginTime.length() > 0) {
				sql += " and createTime>=to_date('" + beginTime + " 00:00:00','yyyy-MM-dd HH24:mi:ss')";
			}
			if (endTime != "" && endTime.length() > 0) {
				sql += " and createTime<=to_date('" + endTime + " 00:00:00','yyyy-MM-dd HH24:mi:ss')";
			}
			sql += " GROUP BY departmentNum) as depart";
			return dbSupport.getcount2(sql);
		} catch (Exception ex) {
			log.AddLog("E", "GetDepartmentFingerprintChuanshu(" + beginTime
					+ "," + endTime + ")" + ex.getMessage(), "");
			return 0;
		}
	}

	@POST
	@Path("/getDepartmentDNA")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML })
	public String GetDepartmentDNA(
			@FormParam(value = "departmentbegintime") String beginTime,
			@FormParam(value = "departmentendtime") String endTime) {
		String val = "";
		int sum1 = 0;
		int chuanshu = GetDepartmentDNAChuanshu(beginTime, endTime);
		List<SearchComprehensiveChartQishu> chartQishus = GetDepartmentDNAQishu(
				beginTime, endTime);
		for (SearchComprehensiveChartQishu sscq : chartQishus) {
			int sum = Integer.parseInt(sscq.getQishu().toString());
			sum1 += sum;
		}
		val = chuanshu + "," + sum1;
		return val;
	}

	@SuppressWarnings("unchecked")
	private List<SearchComprehensiveChartQishu> GetDepartmentDNAQishu(
			String beginTime, String endTime) {
		try {
			String sql = "select count(departmentNum) as qishu from departmentcaseinfo where accord='DNA' and dataType=1 ";
			if (beginTime != "" && beginTime.length() > 0) {
				sql += " and createTime>=to_date('" + beginTime + " 00:00:00','yyyy-MM-dd HH24:mi:ss')";
			}
			if (endTime != "" && endTime.length() > 0) {
				sql += " and createTime<=to_date('" + endTime + " 00:00:00','yyyy-MM-dd HH24:mi:ss')";
			}
			sql += " GROUP BY departmentNum";
			List<SearchComprehensiveChartQishu> searchComprehensiveChartQishus = ((List<SearchComprehensiveChartQishu>) dbSupport
					.getSqlQuery(sql, SearchComprehensiveChartQishu.class, true));
			return searchComprehensiveChartQishus;
		} catch (Exception ex) {
			log.AddLog("E", "GetDepartmentDNAQishu(" + beginTime + ","
					+ endTime + ")" + ex.getMessage(), "");
			return null;
		}
	}

	private int GetDepartmentDNAChuanshu(String beginTime, String endTime) {
	 
		try {
			String sql = "select COUNT(*) from (select count(departmentNum) from departmentcaseinfo where accord='DNA' and dataType=1 ";
			if (beginTime != "" && beginTime.length() > 0) {
				sql += " and createTime>=to_date('" + beginTime + " 00:00:00','yyyy-MM-dd HH24:mi:ss')";
			}
			if (endTime != "" && endTime.length() > 0) {
				sql += " and createTime<=to_date('" + endTime + " 00:00:00','yyyy-MM-dd HH24:mi:ss')";
			}
			sql += " GROUP BY departmentNum) as depart";
			return dbSupport.getcount2(sql);
		} catch (Exception ex) {
			log.AddLog("E", "GetDepartmentDNAChuanshu(" + beginTime + ","
					+ endTime + ")" + ex.getMessage(), "");
			return 0;
		}
	}

	@POST
	@Path("/getFingerprintDNAFootprintCount")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML })
	public String GetFingerprintDNAFootprintCount(
			@FormParam(value = "zonghebegintime") String beginTime,
			@FormParam(value = "zongheendtime") String endTime) {
		List<SearchGetSyntheticalCaseQishu> sccqs = GetSyntheticalCaseQishu(
				beginTime, endTime);
		if(sccqs==null||sccqs.size()==0)
			return "无值";
		List<SearchGetSyntheticalCaseQishuMap> maps = new ArrayList<SearchGetSyntheticalCaseQishuMap>();
		for (SearchGetSyntheticalCaseQishu sccq : sccqs) {
			if (sccq.getFOOTNUM().equals("") && !sccq.getDNANUM().equals("")
					&& !sccq.getFINGERNUM().equals("")) {
				SearchGetSyntheticalCaseQishuMap sgsc1 = new SearchGetSyntheticalCaseQishuMap();
				sgsc1.setName("指纹DNA");
				sgsc1.setScount(sccq.getSCOUNT() == null ? 0 : sccq.getSCOUNT());
				maps.add(sgsc1);
			} else if (!sccq.getFOOTNUM().equals("无")
					&& sccq.getDNANUM().equals("无")
					&& !sccq.getFINGERNUM().equals("无")) {
				SearchGetSyntheticalCaseQishuMap sgsc2 = new SearchGetSyntheticalCaseQishuMap();
				sgsc2.setName("指纹足迹");
				sgsc2.setScount(sccq.getSCOUNT() == null ? 0 : sccq.getSCOUNT());
				maps.add(sgsc2);
			} else if (!sccq.getFOOTNUM().equals("无")
					&& !sccq.getDNANUM().equals("无")
					&& sccq.getFINGERNUM().equals("无")) {
				SearchGetSyntheticalCaseQishuMap sgsc3 = new SearchGetSyntheticalCaseQishuMap();
				sgsc3.setName("足迹DNA");
				sgsc3.setScount(sccq.getSCOUNT() == null ? 0 : sccq.getSCOUNT());
				maps.add(sgsc3);
			} else if (!sccq.getFOOTNUM().equals("无")
					&& !sccq.getDNANUM().equals("无")
					&& !sccq.getFINGERNUM().equals("无")) {
				SearchGetSyntheticalCaseQishuMap sgsc4 = new SearchGetSyntheticalCaseQishuMap();
				sgsc4.setName("指纹DNA足迹");
				sgsc4.setScount(sccq.getSCOUNT() == null ? 0 : sccq.getSCOUNT());
				maps.add(sgsc4);
			}
		}
		if (!maps.toString().equals("[]"))
			return maps.toString();
		else
			return "无值";
	}

	/*@Test
	public void GetSyntheticalCaseQishu() {
		String beginTime="2014-01-01"+" 00:00:00";
		String endTime="2015-05-28"+" 23:59:59"; 
		try {
			String sql = "SELECT syntheticalNum,s.footNum,s.dnaNum,s.fingerNum,createDate,COUNT(*) as scount FROM departmentcaseinfo d,syntheticalcaseinfo s where ((departmentNum=s.footNum and accord='足迹') or (departmentNum=s.dnaNum and accord='DNA') or (departmentNum=s.fingerNum and accord='指纹'))";
			if (beginTime != "" && beginTime.length() > 0) {
				sql += " and createDate>=to_date('"+beginTime+"','yyyy-mm-dd hh24:mi:ss')";
			}
			if (endTime != "" && endTime.length() > 0) {
				sql += " and createDate<=to_date('"+endTime+"','yyyy-mm-dd hh24:mi:ss')";
			}
			sql += " GROUP BY syntheticalNum,s.footNum,s.dnaNum,s.fingerNum,createDate  ";
			List<SearchGetSyntheticalCaseQishu> chartQishus = (List<SearchGetSyntheticalCaseQishu>) dbSupport
					.getSqlQuery(sql, SearchGetSyntheticalCaseQishu.class, true);
		} catch (Exception ex) {
			log.AddLog("E", "GetSyntheticalCaseChuanshu(" + beginTime + ","
					+ endTime + ")" + ex.getMessage(), "");
		}
	}*/
	@SuppressWarnings("unchecked")
	private List<SearchGetSyntheticalCaseQishu> GetSyntheticalCaseQishu(
			String beginTime, String endTime) {
		if(beginTime==null || "".equals(beginTime) || beginTime.length()==0)
		{
			beginTime="2014-01-01"+" 00:00:00";
		}
		else
		{
			beginTime=beginTime+" 00:00:00";
		}
		if(endTime==null || "".equals(endTime) || endTime.length()==0)
		{
			endTime="2015-04-30"+" 23:59:59";
		}
		else
		{
			 endTime=endTime+" 23:59:59"; 
		}
		try {
			String sql = "SELECT syntheticalNum,s.footNum,s.dnaNum,s.fingerNum,createDate,COUNT(*) as scount FROM departmentcaseinfo d,syntheticalcaseinfo s where ((departmentNum=s.footNum and accord='足迹') or (departmentNum=s.dnaNum and accord='DNA') or (departmentNum=s.fingerNum and accord='指纹'))";
			if (beginTime != "" && beginTime.length() > 0) {
				sql += " and createDate>=to_date('"+beginTime+"','yyyy-mm-dd hh24:mi:ss')";
			}
			if (endTime != "" && endTime.length() > 0) {
				sql += " and createDate<=to_date('"+endTime+"','yyyy-mm-dd hh24:mi:ss')";
			}
			sql += " GROUP BY syntheticalNum,s.footNum,s.dnaNum,s.fingerNum,createDate  ";
			List<SearchGetSyntheticalCaseQishu> chartQishus = (List<SearchGetSyntheticalCaseQishu>) dbSupport
					.getSqlQuery(sql, SearchGetSyntheticalCaseQishu.class, true);
			return chartQishus;
		} catch (Exception ex) {
			log.AddLog("E", "GetSyntheticalCaseChuanshu(" + beginTime + ","
					+ endTime + ")" + ex.getMessage(), "");
			return null;
		}
	
	}

	@POST
	@Path("/getFingerprintDNAFootprintChuananCount")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML })
	public String GetFingerprintDNAFootprintChuananCount(
			@FormParam(value = "zonghebegintime") String beginTime,
			@FormParam(value = "zongheendtime") String endTime) {
		List<SearchGetSyntheticalCaseChuanShu> sccqs = GetSyntheticalCaseChuanshu(
				beginTime, endTime);
		if(sccqs.size()==0)
		    return "无值";
		List<SearchGetSyntheticalCaseQishuMap> maps = new ArrayList<SearchGetSyntheticalCaseQishuMap>();
		for (SearchGetSyntheticalCaseChuanShu sccq : sccqs) {
			if (sccq.getFOOTNUM().equals("无") && !sccq.getDNANUM().equals("无")
					&& !sccq.getFINGERNUM().equals("无")) {
				SearchGetSyntheticalCaseQishuMap sgsc1 = new SearchGetSyntheticalCaseQishuMap();
				sgsc1.setName("指纹DNA");
				sgsc1.setScount(sccq.getSCOUNT() == null ? 0 : sccq.getSCOUNT());
				maps.add(sgsc1);
			} else if (!sccq.getFOOTNUM().equals("无")
					&& sccq.getDNANUM().equals("无")
					&& !sccq.getFINGERNUM().equals("无")) {
				SearchGetSyntheticalCaseQishuMap sgsc2 = new SearchGetSyntheticalCaseQishuMap();
				sgsc2.setName("指纹足迹");
				sgsc2.setScount(sccq.getSCOUNT() == null ? 0 : sccq.getSCOUNT());
				maps.add(sgsc2);
			} else if (!sccq.getFOOTNUM().equals("无")
					&& !sccq.getDNANUM().equals("无")
					&& sccq.getFINGERNUM().equals("无")) {
				SearchGetSyntheticalCaseQishuMap sgsc3 = new SearchGetSyntheticalCaseQishuMap();
				sgsc3.setName("足迹DNA");
				sgsc3.setScount(sccq.getSCOUNT() == null ? 0 : sccq.getSCOUNT());
				maps.add(sgsc3);
			} else if (!sccq.getFOOTNUM().equals("无")
					&& !sccq.getDNANUM().equals("无")
					&& !sccq.getFINGERNUM().equals("无")) {
				SearchGetSyntheticalCaseQishuMap sgsc4 = new SearchGetSyntheticalCaseQishuMap();
				sgsc4.setName("指纹DNA足迹");
				sgsc4.setScount(sccq.getSCOUNT() == null ? 0 : sccq.getSCOUNT());
				maps.add(sgsc4);
			}
		}
		if (!maps.toString().equals("[]"))
			return maps.toString();
		else
			return "无值";
	}

	@SuppressWarnings("unchecked")
	private List<SearchGetSyntheticalCaseChuanShu> GetSyntheticalCaseChuanshu(
			String beginTime, String endTime) {
		if(beginTime==null ||  "".equals(beginTime) || beginTime.length()==0)
		{
			beginTime="2014-01-01"+" 00:00:00";
		}
		else
		{
			beginTime=beginTime+" 00:00:00";
		}
		if(endTime==null ||  "".equals(endTime) || endTime.length()==0)
		{
			endTime="2015-04-30"+" 23:59:59";
		}
		else
		{
			 endTime=endTime+" 23:59:59"; 
		}
		try {
			String sql = "select footNum,dnaNum,fingerNum,count(*) as scount from syntheticalcaseinfo where 1=1 ";
			if (beginTime != "" && beginTime.length() > 0) {
				sql += " and createDate>=to_date('"+beginTime+"','yyyy-mm-dd hh24:mi:ss')";
			}
			if (endTime != "" && endTime.length() > 0) {
				sql += " and createDate<=to_date('"+endTime+"','yyyy-mm-dd hh24:mi:ss')";
			}
			sql += "  GROUP BY footNum,dnaNum,fingerNum ";
			List<SearchGetSyntheticalCaseChuanShu> chartQishus = (List<SearchGetSyntheticalCaseChuanShu>) dbSupport
					.getSqlQuery(sql, SearchGetSyntheticalCaseChuanShu.class,
							true);
			return chartQishus;
		} catch (Exception ex) {

			log.AddLog("E", "GetSyntheticalCaseQishu(" + beginTime + ","
					+ endTime + ")" + ex.getMessage(), "");
			return null;
		}
	}

	@POST
	@Path("/getDataCity")
	@Produces(MediaType.TEXT_PLAIN)
	public String GetDataCity(@FormParam("mdata") String mdata) {
		String val = "";
		String sql = SqlEnum.DepartmentZongHeFindCity;
		String[] mdata1=null;
		try {
			@SuppressWarnings("unchecked")
			List<DepartmentZongHeFindCity> chartQishus = (List<DepartmentZongHeFindCity>) dbSupport
					.getSqlQuery(sql, DepartmentZongHeFindCity.class, true);
			List<GetDataCityValue> cityValues = new ArrayList<GetDataCityValue>();
			for (int i = 0; i < chartQishus.size(); i++) {
				GetDataCityValue cityValue = new GetDataCityValue();
				if (chartQishus.get(i).getDICT_VALUE() != null
						&& !"".equals(chartQishus.get(i).getDICT_VALUE())
						&& chartQishus.get(i).getDICT_VALUE().length() > 3) {
					cityValue
							.setText(chartQishus
									.get(i)
									.getDICT_VALUE()
									.substring(
											3,
											chartQishus.get(i).getDICT_VALUE()
													.length() - 1));
					cityValue.setTextvalue(chartQishus.get(i).getDICT_KEY());
					cityValues.add(cityValue);
				}
			}
			if(mdata!=null && !"".equals(mdata))
			{
				mdata1=mdata.split("\\]");
				for(int i=0;i<mdata1.length;i++)
				{
					cityValues=sisidata(mdata1[i],cityValues);
				}
			}
			val = "[{\"text\":\"山东省\",\"state\":\"closed\",\"children\":"+ cityValues.toString().trim() + "}]";
			  return val;
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	private List<GetDataCityValue> sisidata(String string, List<GetDataCityValue> cityValues) {
		
		for(int i=0;i<cityValues.size();i++)
		{
			if(cityValues.get(i).getText().equals(string))
			{
				cityValues.get(i).setChecked(true);
			}
		}
		return cityValues;
	}

	@POST
	@Path("/getanjianshu")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String Getanjianshu(@FormParam("nodeval") String nodeval,@FormParam("nodevalanjianxigzhi") String nodevalanjianxigzhi,@FormParam(value = "beginTime") String beginTime,
			@FormParam(value = "endTime") String endTime)
			throws Exception {
		if(beginTime==null || "".equals(beginTime) || beginTime.length()==0)
		{
			beginTime="2014-01-01 00:00:00";
		}
		else
		{
			beginTime=beginTime.substring(1, endTime.length()-1)+" 00:00:00";
		}
		if(endTime==null || "".equals(endTime) || endTime.length()==0)
		{
			endTime="2015-04-30 23:59:59";
		}
		else
		{
			 endTime=endTime.substring(1, endTime.length()-1)+" 23:59:59"; 
		}
		StringBuffer sb1=new StringBuffer();
		String sql="";
		String[] strss = new String[] {};
		String[] strssss = new String[] {};
		Stack<String> stack1 = new Stack<String>();
		Stack<String> stack2 = new Stack<String>();
		if (nodeval != null &&  !"".equals(nodeval))
		{
        if (nodeval.indexOf("[") != -1) {
			strss = nodeval.split("\\[");
			for (int i = 0; i < strss.length; i++) {
				strss[i].split("_");
				stack1.push(strss[i].split("_")[1]);
				stack2.push(strss[i].split("_")[0]);
			}
		} else {
			stack1.push(nodeval.split("_")[1]);
			stack2.push(nodeval.split("_")[0]);
		  }
		 sql= sansan(stack1.toArray());
		 sb1.append(sql);
		 if(nodevalanjianxigzhi!=null && !"".equals(nodevalanjianxigzhi) && !"undefined".equals(nodevalanjianxigzhi))
		 {
			 sb1.append(" and inv.case_type in(");
				if (nodevalanjianxigzhi.indexOf("]") == -1) {
					sb1.append("\'" + nodevalanjianxigzhi + "\'");
				} else {
					strssss = nodevalanjianxigzhi.split("\\]");
					for (int i = 0; i < strssss.length; i++) {
						sb1.append("\'" + strssss[i] + "\',");
					}
					sb1.deleteCharAt(sb1.length() - 1);
				}
				sb1.append(")");
		 }
		sql=new String(sb1);
		}
		else
		{
		 sql=getDataAll(nodevalanjianxigzhi);
		}
		StringBuffer sb2=new StringBuffer();
		sb2.append(sql);
		if (beginTime != "" && beginTime.length() > 0) {
			sb2.append(" and inv.create_datetime>=to_date('"+beginTime+"','yyyy-mm-dd hh24:mi:ss')");
		}
		if (endTime != "" && endTime.length() > 0) {
			sb2.append(" and inv.create_datetime<=to_date('"+endTime+"','yyyy-mm-dd hh24:mi:ss')");
		}
		List<?> list = dbSupport.getSqlQuery(sb2.toString(), null, false);
		Object[] bb;
		if(stack2.toArray().length==0)
		{
			bb = new Object[] { "东营", "烟台", "潍坊", "济宁", "泰安", "威海", "日照",
					"莱芜", "临沂", "德州", "聊城", "滨州", "菏泽", "济南", "青岛", "淄博", "枣庄" };
		}
		else
		{
		bb = stack2.toArray();
		}
		Object[] aa;
		if (list.get(0) != null) {
			aa = (Object[]) list.get(0);
		} else {
			aa = new Object[] { 0 };
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Object[][] s2 = new Object[aa.length][];
		for(int i=0;i<bb.length;i++)
		{
			Object[] objects=new Object[2];
			objects[0]=(String) bb[i];
			if(aa[i]==null)
			{
				objects[1]=0;
			}
			else
			{
			objects[1]=Integer.parseInt(aa[i].toString());
			}
			s2[i]=objects;
		}
		map.put("city", bb);
		map.put("cityvalue", aa);
		map.put("citypie", s2);
		return new Gson().toJson(map).toString();
	}

	private String getDataAll(String nodevalanjianxigzhi) {
		String[] stringss;
		String sql = SqlEnum.DepartmentZongHeFindCityall;
		StringBuffer sb = new StringBuffer();
		sb = sb.append(sql);
		if (nodevalanjianxigzhi != null && !"".equals(nodevalanjianxigzhi)) {
			sb.append(" and inv.case_type in(");
			if (nodevalanjianxigzhi.indexOf("]") == -1) {
				sb.append("\'" + nodevalanjianxigzhi + "\'");
			} else {
				stringss = nodevalanjianxigzhi.split("\\]");
				for (int i = 0; i < stringss.length; i++) {
					sb.append("\'" + stringss[i] + "\',");
				}
				sb.deleteCharAt(sb.length() - 1);
			}
			sb.append(")");

		}
		return new String(sb);
	}

	private String sansan(Object[] objects) {
		StringBuffer sb = new StringBuffer();
		sb.append("select ");
		for (int i = 0; i < objects.length; i++) {
			sb.append(
					"sum(decode(substr(inv.init_server_no, 0, 4), '"
							+ objects[i].toString().substring(0,
									objects[i].toString().length() - 2)
							+ "', 1, 0)) as ").append("a" + i).append(",");
		}
		sb = sb.deleteCharAt(sb.length() - 1);
		sb.append(" from foms.scene_investigation inv ");
		sb.append(" where (");
		for (int i = 0; i < objects.length; i++) {
			if (objects.length == 1) {
				sb.append(" substr(inv.init_server_no, 0, 4) = \'"
						+ objects[i].toString().substring(0,
								objects[i].toString().length() - 2) + "\'");
			} else {
				if (i == 0) {
					sb.append(" substr(inv.init_server_no, 0, 4) = \'"
							+ objects[i].toString().substring(0,
									objects[i].toString().length() - 2) + "\'");
				} else {
					sb.append(" or substr(inv.init_server_no, 0, 4) = \'"
							+ objects[i].toString().substring(0,
									objects[i].toString().length() - 2) + "\'");
				}

			}
		}
		sb.append(")");
		return new String(sb);
	}

	@POST
	@Path("/getDataAll")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getDataAll(@FormParam(value = "departmentbegintime") String beginTime,
			@FormParam(value = "departmentendtime") String endTime) throws Exception {
		StringBuffer sb=new StringBuffer();
		Object[] bb = new Object[] { "东营", "烟台", "潍坊", "济宁", "泰安", "威海", "日照",
				"莱芜", "临沂", "德州", "聊城", "滨州", "菏泽", "济南", "青岛", "淄博", "枣庄" };
		if(beginTime==null || "".equals(beginTime) || beginTime.length()==0)
		{
			beginTime="2014-01-01 00:00:00";
		}
		else
		{
			beginTime=beginTime+" 00:00:00";
		}
		if(endTime==null ||  "".equals(endTime) || endTime.length()==0)
		{
			endTime="2015-04-30 23:59:59";
		}
		else
		{
			 endTime=endTime+" 23:59:59"; 
		}
		String sql = SqlEnum.DepartmentZongHeFindCityall;
		sb.append(sql);
		if (beginTime != "" && beginTime.length() > 0) {
			sb.append(" and inv.create_datetime>=to_date('"+beginTime+"','yyyy-mm-dd hh24:mi:ss')");
		}
		if (endTime != "" && endTime.length() > 0) {
			sb.append(" and inv.create_datetime<=to_date('"+endTime+"','yyyy-mm-dd hh24:mi:ss')");
		}
		List<?> list = dbSupport.getSqlQuery(sb.toString(), null, false);
		Object[] aa;
		if (list.get(0) != null) {
			aa = (Object[]) list.get(0);
		} else {
			aa = new Object[] { 0 };
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Object[][] s2 = new Object[aa.length][];
		for(int i=0;i<bb.length;i++)
		{
			Object[] objects=new Object[2];
			objects[0]=(String) bb[i];
			objects[1]=aa[i];
			s2[i]=objects;
		}
		map.put("city", bb);
		map.put("cityvalue", aa);
		map.put("citypie", s2);
		return new Gson().toJson(map).toString();
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String AddNode(List dt, String pid, String childcount, String[] sansandata) {
		String str = "";
		List<OrganizationData> list = getChild(dt, pid);
		try {
			if(sansandata!=null && !"".equals(sansandata)  && sansandata.length!=0)
			{
				for(int i=0;i<sansandata.length;i++)
				{
				  list= bijiaoshifouxiangtong(sansandata[i],list);
				}
			}
			int child = 0;
			for (int i = 0; i < list.size(); i++) {
				child++;
				if (list.get(i).getISPARENT().toString().equals("0"))// 叶节点
				{
					str += JSONObject.fromObject(list.get(i)) + ",";
				} else // 有子节点
				{
					str += JSONObject.fromObject(list.get(i));
					str = str.substring(0, str.length() - 1);
					str += ",\"state\":\"closed\",\"children\":[";
 					str += AddNode(dt, list.get(i).getID(), list.get(i)
							.getISPARENT().toString(),sansandata);
					str += "]},";

				}
				if (String.valueOf(child).equals(childcount))// 最后一个
				{
					str = str.substring(0, str.length() - 1);
					child = 0;
				}
		}
	} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	private List<OrganizationData> bijiaoshifouxiangtong(String sansandata, List<OrganizationData> list) {
	
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getTEXT().equals(sansandata))
				list.get(i).setChecked(true);
		}
		return list;
	}

	// 是否有子类
	public List<OrganizationData> getChild(List<OrganizationData> dt, String pid) {
		List<OrganizationData> Child = new ArrayList<OrganizationData>();
		for (OrganizationData tree : dt) {
			if (tree.getPARENT_KEY() == null)
				continue;
			if (tree.getPARENT_KEY().equals(pid)) {
				Child.add(tree);
			}
		}
		return Child;
	}

	@POST 
	@Path("/getAJLBTree")
	@Produces(MediaType.TEXT_PLAIN)
	public String GetAJLBTree(@FormParam("mdata") String mdata) {
		String[] sansandata = null;
		if(mdata!=null && !"".equals(mdata))
		{
			sansandata=mdata.split("\\]");
		}
		String paratype = "AJLBDM";
		String val = "";
		List<OrganizationData> list = GetAJLBTree1(sansandata);
		if (!list.isEmpty()) {
			val = AddNode(list, paratype, "-1",sansandata);
			if ("".equals(val)) {
				return val;
			}
			val = val.substring(0, val.length() - 1);
			val = "[" + val.toLowerCase() + "]";
		}
		return val;
	}
	@SuppressWarnings("unchecked")
	private List<OrganizationData> GetAJLBTree1(String[] sansandata) {
		try {
			System.out.println();
			String sql = SqlEnum.DepartmentZongAnjainxingzhi;
			List<OrganizationData> datas = (List<OrganizationData>) dbSupport
					.getSqlQuery(sql, OrganizationData.class, true);
			return datas;
		} catch (Exception ex) {
			log.AddLog("E", "GetAJLBTree(" + sansandata + ")" + ex.getMessage(),
					"");
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	@POST
	@Path("/getAnjianxingzhiDataAll")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String GetAnjianxingzhiDataAll(@FormParam("data123") String data123,@FormParam(value = "beginTime") String beginTime,
			@FormParam(value = "endTime") String endTime) throws Exception
	{
		if(beginTime==null || "".equals(beginTime) || beginTime.length()==0)
		{
			beginTime="2014-01-01 00:00:00";
		}
		else
		{
		beginTime=beginTime.indexOf("\"")!=-1 ? beginTime.substring(1, beginTime.length()-1)+" 00:00:00" : beginTime+" 00:00:00" ;
		}
		if(endTime==null || "".equals(endTime) || endTime.length()==0)
		{
			endTime="2015-05-01 23:59:59";
		}
		else
		{
			endTime=endTime.indexOf("\"")!=-1 ? endTime.substring(1, endTime.length()-1)+" 23:59:59" : endTime+" 23:59:59" ;
		}
		String[] anjianprovince=new String[]{};
		if(data123!=null && !"".equals(data123))
		{
			if(data123.indexOf("[")!=1)
			{
				anjianprovince=data123.split("\\[");
			}
			else
			{
				anjianprovince[0]=data123;
			}
		}
		String sql=SqlEnum.DepartmentZongHeFindAnJianXingZhi;
		List<DepartmentZongHeFindAnJianXingZhi> anJianXingZhis= (List<DepartmentZongHeFindAnJianXingZhi>) dbSupport.getSqlQuery(sql, DepartmentZongHeFindAnJianXingZhi.class, true);
		anJianXingZhis=anjiangxingzhisansan(anJianXingZhis,anjianprovince,beginTime,endTime);
		Object[][] objectss=new Object[anJianXingZhis.size()][];
		for(int i=0;i<anJianXingZhis.size();i++)
	    {
	    	Object[] objects=new Object[2];
	    	objects[0]=anJianXingZhis.get(i).getDICT_VALUE();
	    	if(anJianXingZhis.get(i).getDICT_VALUE1()==null)
	    	{
	    	objects[1]=0;
	    	}
	    	else
	    	{
	    		objects[1]= anJianXingZhis.get(i).getDICT_VALUE1();
	    	}
	    	objectss[i]=objects;
	    }
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("anjianxingzhi", objectss);
		map.put("anjianxingzhiyincang", anJianXingZhis)
		;
		map.put("endTime", endTime);
		map.put("beginTime", beginTime);
		return new Gson().toJson(map).toString();
	}
	private List<DepartmentZongHeFindAnJianXingZhi> anjiangxingzhisansan(
			List<DepartmentZongHeFindAnJianXingZhi> anJianXingZhis, String[] anjianprovince, String beginTime, String endTime) throws Exception {
		String[] casetype=new String[anJianXingZhis.size()];
		for(int i=0;i<anJianXingZhis.size();i++)
		{
			casetype[i]=anJianXingZhis.get(i).getDICT_KEY();
		}
        String	sql=pinxiesql(casetype,"",false,false,false,anjianprovince);
        StringBuffer sb=new StringBuffer();
        sb.append(sql);
		if (beginTime != "" && beginTime.length() > 0) {
			sb.append(" and inv.create_datetime>=to_date('"+beginTime+"','yyyy-mm-dd hh24:mi:ss')");
		}
		if (endTime != "" && endTime.length() > 0) {
			sb.append(" and inv.create_datetime<=to_date('"+endTime+"','yyyy-mm-dd hh24:mi:ss')");
		}
        List<?> list= dbSupport.getSqlQuery(sb.toString(), null, false);
        if(list.get(0)==null)
        	return null;
        Object[] objects=(Object[]) list.get(0);
        for(int i=0;i<anJianXingZhis.size();i++)
        {
        anJianXingZhis.get(i).setDICT_VALUE1(objects[i]);
        }
        return anJianXingZhis;
	}
	private String pinxiesql(String[] casetype, String where,boolean fal,boolean tru,boolean part, String[] anjianprovince) {
		
		if(casetype.length==0)
			return null;
		StringBuffer sb=new StringBuffer();
		String sql1="";
		String sql2="";
		sb.append("select ");
		if(!part)
		{
		if(!tru)
		{
		for(int i=0;i<casetype.length;i++)
		{
			sql1+="sum(decode(substr(inv.case_type, 0, 2), '"+casetype[i].substring(0,2)+"', 1, 0)) as a"+i+",";
		}
		sb.append(sql1).deleteCharAt(sb.length()-1);
		sb.append(" from foms.scene_investigation inv where( ");
		if(anjianprovince.length!=0)
		{
			sb=pinxiestringbuffer(sb,anjianprovince);
			return new String(sb);
		}
		for(String str : casetype)
		{
		sql2+=" substr(inv.case_type, 0, 2) = '"+str.substring(0,2)+"' or";
		}
		sql2=sql2.substring(0, sql2.length()-2);
		sb.append(sql2).append(")");
		}
		else
		{
			for(int i=0;i<casetype.length;i++)
			{
				sql1+="sum(decode(substr(inv.case_type, 0, 4), '"+casetype[i].substring(0,4)+"', 1, 0)) as a"+i+",";
			}
			sb.append(sql1).deleteCharAt(sb.length()-1);
			sb.append(" from foms.scene_investigation inv where( ");
			if(anjianprovince.length!=0)
			{
				sb=pinxiestringbuffer(sb,anjianprovince);
				return new String(sb);
			}
			for(String str : casetype)
			{
			sql2+=" substr(inv.case_type, 0, 4) = '"+str.substring(0,4)+"' or";
			}
			sql2=sql2.substring(0, sql2.length()-2);
			sb.append(sql2).append(")");
		}
		}
		else
		{
			for(int i=0;i<casetype.length;i++)
			{
				sql1+="sum(decode(substr(inv.case_type, 0, 6), '"+casetype[i].substring(0,6)+"', 1, 0)) as a"+i+",";
			}
			sb.append(sql1).deleteCharAt(sb.length()-1);
			sb.append(" from foms.scene_investigation inv where( ");
			if(anjianprovince.length!=0)
			{
				sb=pinxiestringbuffer(sb,anjianprovince);
				return new String(sb);
			}
			for(String str : casetype)
			{
			sql2+=" substr(inv.case_type, 0, 6) = '"+str.substring(0,6)+"' or";
			}
			sql2=sql2.substring(0, sql2.length()-2);
			sb.append(sql2).append(")");
			}
		return new String(sb);
	}
	private StringBuffer pinxiestringbuffer(StringBuffer sb, String[] anjianprovince) {
		sb.append(" substr(inv.init_server_no, 0, 4) in(");
		for(String string : anjianprovince)
		{
			sb.append("'"+string.substring(0,4)+"',");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("))");
		return sb;
		
	}

	@SuppressWarnings({ "static-access", "unchecked" })
	@POST
	@Path("/getAnjianxingzhiDataAllPart")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String GetAnjianxingzhiDataAllPart(@FormParam("xingzhidata") String xingzhidata,@FormParam("data123part") String data123,@FormParam(value = "beginTime") String beginTime,
			@FormParam(value = "endTime") String endTime) throws Exception
	{
		String[] anjianprovince=new String[]{};
		if(data123!=null && !"".equals(data123))
		{
			if(data123.indexOf("[")!=1)
			{
				anjianprovince=data123.split("\\[");
			}
			else
			{
				anjianprovince[0]=data123;
			}
		}
	if(xingzhidata==null)
		return null;
	JSONObject jsonObject=JSONObject.fromObject(xingzhidata);
	DepartmentZongHeFindAnJianXingZhi anJianXingZhi= (DepartmentZongHeFindAnJianXingZhi) jsonObject.toBean(jsonObject,DepartmentZongHeFindAnJianXingZhi.class);
    String sql1=anJianXingZhi.getDICT_KEY();
    String sql3="select dict_value,root_key,parent_key,dict_key from foms.sys_dict sts where  substr(sts.dict_key, 0, 2) = '"+sql1.substring(0,2)+"' and substr(sts.dict_key, 5, 6) = '00' and parent_key!='AJLBDM'";
    List<DepartmentZongHeFindAnJianXingZhi> anJianXingZhis=(List<DepartmentZongHeFindAnJianXingZhi>) dbSupport.getSqlQuery(sql3, DepartmentZongHeFindAnJianXingZhi.class, true);
 String[] casetype=new String[anJianXingZhis.size()];
 for(int i=0;i<anJianXingZhis.size();i++)
 {
	 casetype[i]=anJianXingZhis.get(i).getDICT_KEY();
 }
    String sql4=pinxiesql(casetype, "", false,true,false,anjianprovince);
    StringBuffer sb=new StringBuffer();
    sb.append(sql4);
	if (beginTime != "" && beginTime.length() > 0) {
		sb.append(" and inv.create_datetime>=to_date('"+beginTime+"','yyyy-mm-dd hh24:mi:ss')");
	}
	if (endTime != "" && endTime.length() > 0) {
		sb.append(" and inv.create_datetime<=to_date('"+endTime+"','yyyy-mm-dd hh24:mi:ss')");
	}
    if(sql4==null)
    return null;
    List<?> list= dbSupport.getSqlQuery(sb.toString(), null, false);
    if(list.get(0)==null)
    	return null;
    Object[] objects=(Object[]) list.get(0);
    for(int i=0;i<anJianXingZhis.size();i++)
    {
    anJianXingZhis.get(i).setDICT_VALUE1(objects[i]);
    }
	Object[][] objectss=new Object[anJianXingZhis.size()][];
	for(int i=0;i<anJianXingZhis.size();i++)
    {
    	Object[] objectsss=new Object[2];
    	objectsss[0]=anJianXingZhis.get(i).getDICT_VALUE();
    	if(anJianXingZhis.get(i).getDICT_VALUE1()==null)
    	{
    	objectsss[1]=0;
    	}
    	else
    	{
    		objectsss[1]= anJianXingZhis.get(i).getDICT_VALUE1();
    	}
    	objectss[i]=objectsss;
    }
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("anjianxingzhipart", objectss);
	map.put("anjianxingzhiyincangpart", anJianXingZhis);
	map.put("endTime", endTime);
	map.put("beginTime", beginTime);
	return new Gson().toJson(map).toString();
	}
	@SuppressWarnings({ "static-access", "unchecked" })
	@POST
	@Path("/getAnjianxingzhiDataAllPartPart")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String GetAnjianxingzhiDataAllPartPart(@FormParam("xingzhidatapart") String xingzhidata,@FormParam("data123partpart") String data123,@FormParam(value = "beginTime") String beginTime,
			@FormParam(value = "endTime") String endTime) throws Exception
	{
		String[] anjianprovince=new String[]{};
		if(data123!=null && !"".equals(data123))
		{
			if(data123.indexOf("[")!=1)
			{
				anjianprovince=data123.split("\\[");
			}
			else
			{
				anjianprovince[0]=data123;
			}
		}
	System.out.println(xingzhidata);
	JSONObject jsonObject=JSONObject.fromObject(xingzhidata);
	DepartmentZongHeFindAnJianXingZhi anJianXingZhi= (DepartmentZongHeFindAnJianXingZhi) jsonObject.toBean(jsonObject,DepartmentZongHeFindAnJianXingZhi.class);
    String sql1=anJianXingZhi.getDICT_KEY();
    String sql3="select dict_value,root_key,parent_key,dict_key from foms.sys_dict sts where  substr(sts.dict_key, 0, 4) = '"+sql1.substring(0,4)+"' and substr(sts.dict_key, 5, 6)!= '00'";
    List<DepartmentZongHeFindAnJianXingZhi> anJianXingZhis=(List<DepartmentZongHeFindAnJianXingZhi>) dbSupport.getSqlQuery(sql3, DepartmentZongHeFindAnJianXingZhi.class, true);
 String[] casetype=new String[anJianXingZhis.size()];
 for(int i=0;i<anJianXingZhis.size();i++)
 {
	 casetype[i]=anJianXingZhis.get(i).getDICT_KEY();
 }

    String sql4=pinxiesql(casetype, "",false,false, true,anjianprovince);
    StringBuffer sb=new StringBuffer();
    sb.append(sql4);
	if (beginTime != "" && beginTime.length() > 0) {
		sb.append(" and inv.create_datetime>=to_date('"+beginTime+"','yyyy-mm-dd hh24:mi:ss')");
	}
	if (endTime != "" && endTime.length() > 0) {
		sb.append(" and inv.create_datetime<=to_date('"+endTime+"','yyyy-mm-dd hh24:mi:ss')");
	}
    if(sql4==null)
	 return null;
    List<?> list= dbSupport.getSqlQuery(sb.toString(), null, false);
    if(list.get(0)==null)
    	return null;
    Object[] objects=(Object[]) list.get(0);
    for(int i=0;i<anJianXingZhis.size();i++)
    {
    anJianXingZhis.get(i).setDICT_VALUE1(objects[i]);
    }
	Object[][] objectss=new Object[anJianXingZhis.size()][];
	for(int i=0;i<anJianXingZhis.size();i++)
    {
    	Object[] objectsss=new Object[2];
    	objectsss[0]=anJianXingZhis.get(i).getDICT_VALUE();
    	if(anJianXingZhis.get(i).getDICT_VALUE1()==null)
    	{
    	objectsss[1]=0;
    	}
    	else
    	{
    		objectsss[1]= anJianXingZhis.get(i).getDICT_VALUE1();
    	}
    	objectss[i]=objectsss;
    }
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("anjianxingzhipartpart", objectss);
	map.put("anjianxingzhiyincangpartpart", anJianXingZhis);
	return new Gson().toJson(map).toString();
	}
	@POST 
	@Path("/getcheckedhaxi")
	@Produces(MediaType.TEXT_PLAIN)
	public String Getcheckedhaxi(@FormParam("nodesss") String nodesss,@FormParam("jdata") String jdata)
	{
	    System.out.println(nodesss);
	    System.out.println(jdata);
		Object[] objectss = toArray(nodesss);
		Object[] objectss1 = toArray(jdata);
		System.out.println(objectss1.toString().indexOf("children"));
		System.out.println(objectss1.length);
		List<DepartmentZongHeFindGetCheck> checks=chaunsong(objectss);
		System.out.println(checks.toString());
		return checks.toString();
	}

	private Object[] toArray(String nodesss) {
		JSONArray js=JSONArray.fromObject(nodesss);
		Object[] objects= js.toArray();
		Object[] objectss=new Object[objects.length];
		for(int i=0;i<objects.length;i++)
		{
			objectss[i]=objects[i];
		}
		return objectss;
	}

	@SuppressWarnings("static-access")
	private List<DepartmentZongHeFindGetCheck> chaunsong(Object[] objects) {
		List<DepartmentZongHeFindGetCheck> checks=new ArrayList<DepartmentZongHeFindGetCheck>();
		for(int i=0;i<objects.length;i++)
		{
			JSONObject js1=JSONObject.fromObject(objects[i]);
			js1.remove("target");
			js1.remove("domId");
			DepartmentZongHeFindGetCheck check=(DepartmentZongHeFindGetCheck) js1.toBean(js1, DepartmentZongHeFindGetCheck.class);
		    checks.add(check);
		}
		return checks;
		
	}
	
	@SuppressWarnings("unchecked")
	@POST 
	@Path("/getZujiData")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String GetZujiData(@FormParam(value = "zujibegintime") String beginTime,
			@FormParam(value = "zujiendtime") String endTime) throws Exception
	{
		if(beginTime==null || "".equals(beginTime) || beginTime.length()==0)
		{
			beginTime="2014-01-01"+" 00:00:00";
		}
		else
		{
			beginTime=beginTime+" 00:00:00";
		}
		if(endTime==null || "".equals(endTime) || endTime.length()==0)
		{
			endTime="2015-04-30"+" 23:59:59";
		}
		else
		{
			 endTime=endTime+" 23:59:59"; 
		}
		String sql=SqlEnum.DepartmentZongHeFindCityAllData;
		List<CityValue> cityValues=(List<CityValue>) dbSupport.getSqlQuery(sql, CityValue.class, true);
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] objectsqishu=CityQishu(cityValues,beginTime,endTime);
		Object[] objectchuanshu=CityChuanshu(cityValues,beginTime,endTime);
		String[] objects=new String[cityValues.size()];
		for(int i=0;i<cityValues.size();i++)
		{
			objects[i]=cityValues.get(i).getDICT_VALUE().substring(3);
		}
		map.put("cityall", objects);
		map.put("qishu", objectsqishu);
		map.put("chuanshu", objectchuanshu);
		return new Gson().toJson(map).toString();
	}
	@SuppressWarnings("unchecked")
	@POST 
	@Path("/getDnaData")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String GetDnaData(@FormParam(value = "dnabegintime") String beginTime,
			@FormParam(value = "dnaendtime") String endTime) throws Exception
	{
		if(beginTime==null || "".equals(beginTime) || beginTime.length()==0)
		{
			beginTime="2014-01-01"+" 00:00:00";
		}
		else
		{
			beginTime=beginTime+" 00:00:00";
		}
		if(endTime==null || "".equals(endTime) || endTime.length()==0)
		{
			endTime="2015-04-30"+" 23:59:59";
		}
		else
		{
			 endTime=endTime+" 23:59:59"; 
		}
	 String sql=SqlEnum.DepartmentZongHeFindCityAllProvinceData;
	 List<DepartmentZongHeFindDnaClass> classes =(List<DepartmentZongHeFindDnaClass>) dbSupport.getSqlQuery(sql, DepartmentZongHeFindDnaClass.class, true);
	 List<DepartmentZongHeFindDnaClass> classes2=getdnaqichuanshudata(classes,beginTime,endTime);
	 for(int i=0;i<classes.size();i++)
	 {
		 if(classes2==null || classes2.size()==0)
		 {
			 break;
		 }
		 for(int j=0;j<classes2.size();j++)
		 {
			 if(classes.get(i).getDICT_KEY().equals(classes2.get(j).getDICT_KEY()))
			 {
				 classes.set(i, classes2.get(j));
			 }
		 }
	 }
	    Object[]  objects=new Object[classes.size()];
	    Object[]  objectsqishu=new Object[classes.size()];
	    Object[]  objectchuanshu=new Object[classes.size()];
	    if(classes.get(0).getDICT_VALUE()==null ||  "山东省".equals(classes.get(0).getDICT_VALUE()))
	    {
	    	classes.get(0).setDICT_VALUE("山东省山东省");
	    }
	    for(int i=0;i<classes.size();i++)
	    {
	    	
	    	objects[i]=classes.get(i).getDICT_VALUE().toString().substring(3, classes.get(i).getDICT_VALUE().toString().length());
	    	if(classes.get(i).getQISHU()==null)
	    	{
	    		objectsqishu[i]=0;
	    	}
	    	else
	    	{
	    		objectsqishu[i]=classes.get(i).getQISHU();
	    	}
	    	if(classes.get(i).getCHUANSHU()==null)
	    	{
	    		objectchuanshu[i]=0;
	    	}
	    	else
	    	{
	    		objectchuanshu[i]=classes.get(i).getCHUANSHU();
	    	}
	    }
		Map<String, Object> map = new HashMap<String, Object>();
	    map.put("cityall", objects);
		map.put("qishu", objectsqishu);
		map.put("chuanshu", objectchuanshu);
		return new Gson().toJson(map).toString();
	}
	@SuppressWarnings("unchecked")
	private List<DepartmentZongHeFindDnaClass> getdnaqichuanshudata(
			List<DepartmentZongHeFindDnaClass> classes, String beginTime, String endTime) throws Exception {
	StringBuffer sb=new StringBuffer();
	sb.append("select (select y.dict_value from (select dict_key, dict_value from foms.sys_dict where root_key = 'XZQHDM'");
	sb.append(" and parent_key = 'XZQHDM'  and substr(dict_key, 0, 6) != '370000' or parent_key = '370000') y");
	sb.append(" where dict_key = src_lab_regionalism) as DICT_VALUE,src_lab_regionalism as dict_key, count(*) chuanshu,");
	sb.append(" sum((select count(*) from foms.dna_notification where src_case_id = dna_serialbase.scaseid)) as qishu");
	sb.append(" from foms.dna_serialbase where (");
    for(int i=0;i<classes.size();i++)
    {
    	if(i==classes.size()-1)
    	{
    		sb.append("substr(src_lab_regionalism, 0, 4) = '"+classes.get(i).getDICT_KEY().toString().substring(0, 4)+"'");
    		break;
    	}
    	sb.append(" substr(src_lab_regionalism, 0, 4) = '"+classes.get(i).getDICT_KEY().toString().substring(0, 4)+"' or ");
    }
    sb.append(")");
    if (beginTime != "" && beginTime.length() > 0) {
		sb.append(" and create_datetime>=to_date('"+beginTime+"','yyyy-mm-dd hh24:mi:ss')");
	}
	if (endTime != "" && endTime.length() > 0) {
		sb.append(" and create_datetime<=to_date('"+endTime+"','yyyy-mm-dd hh24:mi:ss')");
	}
	sb.append(" group by src_lab_regionalism");
    classes=(List<DepartmentZongHeFindDnaClass>) dbSupport.getSqlQuery(sb.toString(), DepartmentZongHeFindDnaClass.class, true);
	return classes;
	}

	private Object[] CityChuanshu(List<CityValue> cityValues,
			String beginTime,String endTime) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select ");
		for(int i=0;i<cityValues.size();i++)
		{
			sb.append("sum(decode(substr(deptid,0,4),'"+cityValues.get(i).getDICT_KEY().substring(0,4)+"',counts,0)) as a").append(i).append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(" from (select base.deptid,base.inputtime,(select count(*) from foms.fais_sercapic pic where pic.sercaid=base.sercaid) counts from  foms.fais_sercabase base) base where( ");
		for(int i=0;i<cityValues.size();i++)
		{
			if(i==cityValues.size()-1)
			{
				sb.append(" substr(deptid,0,4)='"+cityValues.get(i).getDICT_KEY().substring(0,4)+"'");
				break;
			}
			sb.append(" substr(deptid,0,4)='"+cityValues.get(i).getDICT_KEY().substring(0,4)+"' or");
		}
		sb.append(")");
		if (beginTime != "" && beginTime.length() > 0) {
			sb.append(" and inputtime>=to_date('"+beginTime+"','yyyy-mm-dd hh24:mi:ss')");
		}
		if (endTime != "" && endTime.length() > 0) {
			sb.append(" and inputtime<=to_date('"+endTime+"','yyyy-mm-dd hh24:mi:ss')");
		}
	    List<?> list= dbSupport.getSqlQuery(sb.toString(), null, false);
	    Object[] objects= (Object[]) list.get(0);
	    return objects;
	}
	private Object[] CityQishu(List<CityValue> cityValues,String beginTime,String endTime) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select ");
		for(int i=0;i<cityValues.size();i++)
		{
			sb.append("sum(decode(substr(deptid,0,4),'"+cityValues.get(i).getDICT_KEY().substring(0,4)+"',1,0)) as a").append(i).append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(" from (select base.deptid,base.inputtime,(select count(*) from foms.fais_sercapic pic where pic.sercaid=base.sercaid) counts from  foms.fais_sercabase base) base where( ");
		for(int i=0;i<cityValues.size();i++)
		{
			if(i==cityValues.size()-1)
			{
				sb.append(" substr(deptid,0,4)='"+cityValues.get(i).getDICT_KEY().substring(0,4)+"'");
				break;
			}
			sb.append(" substr(deptid,0,4)='"+cityValues.get(i).getDICT_KEY().substring(0,4)+"' or");
		}
		sb.append(")");
		if (beginTime != "" && beginTime.length() > 0) {
			sb.append(" and inputtime>=to_date('"+beginTime+"','yyyy-mm-dd hh24:mi:ss')");
		}
		if (endTime != "" && endTime.length() > 0) {
			sb.append(" and inputtime<=to_date('"+endTime+"','yyyy-mm-dd hh24:mi:ss')");
		}
	    List<?> list= dbSupport.getSqlQuery(sb.toString(), null, false);
	    Object[] objects= (Object[]) list.get(0);
	    return objects;
	}
	@SuppressWarnings("unchecked")
	@POST 
	@Path("/indexZongHeTongJi")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String IndexZongHeTongJi() throws Exception
	{
	    String sqlcityvlaue=SqlEnum.DepartmentZongHeFindCityAllData;
	    String sqldnainfo=SqlEnum.DepartmentZongHeFinddnaInfo;
	    List<DepartmentZongHeFindDnaClass> cityValues=(List<DepartmentZongHeFindDnaClass>) dbSupport.getSqlQuery(sqlcityvlaue, DepartmentZongHeFindDnaClass.class, true);
	    Object[] objectCity=new Object[cityValues.size()];
	    List<DepartmentZongHeFindDnaClass> listdnainfo=(List<DepartmentZongHeFindDnaClass>) dbSupport.getSqlQuery(sqldnainfo, DepartmentZongHeFindDnaClass.class, true);
	    Object[] objectdnaqishu=new Object[cityValues.size()];
	    Object[] objectdnachuanshu=new Object[cityValues.size()];
	     for(int i=0;i<cityValues.size();i++)
	   {
		   for(DepartmentZongHeFindDnaClass class1 : listdnainfo)
		   {
			   if(cityValues.get(i).getDICT_VALUE().equals(class1.getDICT_VALUE()))
			   {
				   cityValues.get(i).setCHUANSHU(class1.getCHUANSHU());
				   cityValues.get(i).setQISHU(class1.getQISHU());
			   }
		   }
	   }
	   
	   for(int i=0;i<cityValues.size();i++)
		{
			objectCity[i]=((String) cityValues.get(i).getDICT_VALUE()).substring(3, cityValues.get(i).getDICT_VALUE().toString().length()-1);
			
			objectdnaqishu[i]=cityValues.get(i).getQISHU()==null ? 0 :cityValues.get(i).getQISHU();
			objectdnachuanshu[i]=cityValues.get(i).getCHUANSHU()==null ? 0 :cityValues.get(i).getCHUANSHU();
		/*	if(cityValues.get(i).getQISHU()==null)
			{
				objectdnaqishu[i]=0;
			}
			else
			{
				objectdnaqishu[i]=cityValues.get(i).getQISHU();

			}
			if(cityValues.get(i).getQISHU()==null)
			{
				objectdnachuanshu[i]=0;
			}
			else
			{
				
			}
			objectdnaqishu[i]=cityValues.get(i).getQISHU();
			objectdnachuanshu[i]=cityValues.get(i).getCHUANSHU();*/
		}
	    String sqlZuJiQiShu=SqlEnum.DepartmentZongHeFindZuJiQiShu;
	    String sqlZuJiChuanShu=SqlEnum.DepartmentZongHeFindZuJiChuanShu;
		List<?> listzujiqishu= dbSupport.getSqlQuery(sqlZuJiQiShu, null, false);
		List<?> listzujichuanshu= dbSupport.getSqlQuery(sqlZuJiChuanShu, null, false);
		Object[] objectZujiQiShu=(Object[]) listzujiqishu.get(0);
		Object[] objectZujiChuanShu=(Object[]) listzujichuanshu.get(0);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cityall", objectCity);
		map.put("zujiqishu", objectZujiQiShu);
		map.put("zujichuanshu", objectZujiChuanShu);
		map.put("dnaqishu", objectdnaqishu);
		map.put("dnachuanshu", objectdnachuanshu);
		return new Gson().toJson(map).toString();
	}
}
