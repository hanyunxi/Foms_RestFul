package com.cmrx.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cmrx.bean.SqlEnum;
import com.cmrx.bean.Entity.InvestigationByCaseNo;
import com.cmrx.bean.Entity.InvestigationCASEId;
import com.cmrx.bean.Entity.ScenceinvistigationmodelDetails;
import com.cmrx.bean.Entity.ScenceinvistigationmodelDetailsInfo;
import com.cmrx.bean.Entity.Sercabase;
import com.cmrx.bean.Entity.Sercapic;
import com.cmrx.bean.model.DnaSercabase;
import com.cmrx.bean.model.Syntheticalcaseinfo;
import com.cmrx.dao.DBSupport;
import com.google.gson.Gson;

//导航：综合串并
@Path("/SyntheticalCase")
public class SyntheticalCase {
	public DBSupport dbSupport;
	public Log log;

	public DBSupport getDbSupport() {
		return dbSupport;
	}

	public void setDbSupport(DBSupport dbSupport) {
		this.dbSupport = dbSupport;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	// 足迹串案表
	@POST
	@Path("/getAllsercabase")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllsercabase(
			@FormParam(value = "begintime") String begintime,
			@FormParam(value = "endtime") String endtime,
			@FormParam(value = "page") int page,
			@FormParam(value = "rows") int rows) {
		try {
			String innerSql = "select SERCAID,SERCANAME,SERCAGIST,INPUTUSER,to_char(INPUTTIME,'yyyy-MM-dd hh24:mi:ss') INPUTTIME from fais_sercabase where 1=1";
			StringBuffer sb = new StringBuffer(innerSql);
			int endPage = page * rows;
			int startPage = (page - 1) * rows + 1;

			System.out.println(startPage);
			System.out.println(endPage);
			if (begintime != null && begintime != "" && begintime.length() > 0) {
				sb.append(" and to_char(inputtime, 'yyyy-MM-dd hh24:mi:ss')>='"
						+ begintime + " 00:00:00'");
			}
			if (endtime != null && endtime != "" && endtime.length() > 0) {
				sb.append(" and to_char(inputtime, 'yyyy-MM-dd hh24:mi:ss')<='"
						+ endtime + " 23:59:59'");
			}

			// 获取总数
			int count = dbSupport.getcount(sb.toString());
			sb.append(" order by inputtime desc ");

			// 分页
			String fenyeSql = "select * from (select t.*,rownum rn from ("
					+ sb.toString() + ") t where rownum<=" + endPage
					+ ") where rn>=" + startPage + "";

			@SuppressWarnings("rawtypes")
			List list = dbSupport.getSqlQuery(fenyeSql, Sercabase.class, true);

			Map<String, Object> m = new HashMap<String, Object>();
			m.put("total", count);
			m.put("rows", list);
			Gson gson = new Gson();
			return gson.toJson(m).toString();

		} catch (Exception ex) {
			log.AddLog("E", "getAllsercabase()" + ex.getMessage(), "");
			return null;
		}
	}

	// 根据SERCAID（串案号）得到 足迹串案关联表
	@POST
	@Path("/getSercapicBySercaid")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSercapicBySercaid(
			@FormParam(value = "sercaid") String sercaid) {
		try {
			String sql = "select SERCAID,SCENEID,to_char(INPUTTIME,'yyyy-MM-dd hh24:mi:ss') INPUTTIME,DEPTID,DEPTNAME,INPUTUSERNAME from fais_sercapic where sercaid='"
					+ sercaid + "'";
			List list = dbSupport.getSqlQuery(sql, Sercapic.class, true);
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("total", list.size());
			m.put("rows", list);
			Gson gson = new Gson();
			return gson.toJson(m).toString();
		} catch (Exception ex) {
			log.AddLog("E", "getSercapicBySercaid()" + ex.getMessage(), "");
			return null;
		}
	}

	// dna串案主表
	@POST
	@Path("/getAllDnaserialbase")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllDnaserialbase(
			@FormParam(value = "begintime") String begintime,
			@FormParam(value = "endtime") String endtime,
			@FormParam(value = "page") int page,
			@FormParam(value = "rows") int rows) {
		try {
			String innerSql = "select DNA_SERIAL_NO,SCASEID,SRC_LAB_REGIONALISM,SRC_LAB_NAME,to_char(CREATE_DATETIME,'yyyy-MM-dd hh24:mi:ss') CREATE_DATETIME from dna_serialbase where 1=1";
			StringBuffer sb = new StringBuffer(innerSql);
			int endPage = page * rows;
			int startPage = (page - 1) * rows + 1;

			if (begintime != "" && begintime.length() > 0) {
				sb.append(" and to_char(CREATE_DATETIME, 'yyyy-MM-dd hh24:mi:ss')>='"
						+ begintime + " 00:00:00'");
			}
			if (endtime != "" && endtime.length() > 0) {
				sb.append(" and to_char(CREATE_DATETIME, 'yyyy-MM-dd hh24:mi:ss')<='"
						+ endtime + " 23:59:59'");
			}

			// 获取总数
			int count = dbSupport.getcount(sb.toString());
			sb.append(" order by CREATE_DATETIME desc ");

			// 分页dna_serialbase
			String fenyeSql = "select * from (select t.*,rownum rn from ("
					+ sb.toString() + ") t where rownum<=" + endPage
					+ ") where rn>=" + startPage + "";

			@SuppressWarnings("rawtypes")
			List list = dbSupport.getSqlQuery(fenyeSql, DnaSercabase.class,
					true);

			Map<String, Object> m = new HashMap<String, Object>();
			m.put("total", count);
			m.put("rows", list);
			Gson gson = new Gson();
			return gson.toJson(m).toString();
		} catch (Exception ex) {
			log.AddLog("E", "getAllsercabase()" + ex.getMessage(), "");
			return null;
		}
	}

	// 根据case_no scene_investigation表 现勘现场数据表
	@POST
	@Path("/getInvestigationByCaseNo")
	@Produces(MediaType.APPLICATION_JSON)
	public String getInvestigationByCaseNo(
			@FormParam(value = "evidence_no") String evidence_no) {
		try {
			// String sql =
			// "select inv.investigation_no,inv.case_no,inv.case_name,to_char(inv.occurrence_date_from, 'yyyy-MM-dd hh24:mi:ss') occurrence_date_from,to_char(inv.occurrence_date_to, 'yyyy-MM-dd hh24:mi:ss') occurrence_date_to from scene_investigation inv where case_no in("
			// +
			// "select substr((select distinct case_id from foms.dna_case_evidence where evidence_no =match_sample_no),0,23) match_caseid from "
			// + "dna_notification where src_case_id='"
			// + evidence_no
			// + "') and case_no=substr('" + evidence_no + "',0,23)";

			String sql = "select investigation_no,case_no,case_name,to_char(occurrence_date_from, 'yyyy-MM-dd hh24:mi:ss') occurrence_date_from,to_char(occurrence_date_to, 'yyyy-MM-dd hh24:mi:ss') occurrence_date_to from foms.scene_investigation where INVESTIGATION_NO in(select inve_no from foms.dna_case_evidence where case_id='"
					+ evidence_no + "')";

			List list = dbSupport.getSqlQuery(sql, InvestigationByCaseNo.class,
					true);
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("total", list.size());
			m.put("rows", list);
			Gson gson = new Gson();
			return gson.toJson(m).toString();

		} catch (Exception ex) {
			log.AddLog("E", "getSercapicBySercaid()" + ex.getMessage(), "");
			return null;
		}
	}

	// 根据case_no scene_investigation表 现勘现场数据表
	@SuppressWarnings("unchecked")
	@POST
	@Path("/getAllScaseid")
	@Produces(MediaType.APPLICATION_JSON)
	public String GSetAllScaseid(
			@FormParam(value = "rowDataScaseid") String rowDataScaseid) {
		try {
			StringBuffer sb = new StringBuffer();
			String sansanqudata = "";
			String sql = "select CASE_ID,evidence_name from dna_case_evidence where evidence_no in (select match_sample_no from dna_notification where src_case_id='"
					+ rowDataScaseid + "')";
			List<InvestigationCASEId> list1 = (List<InvestigationCASEId>) dbSupport
					.getSqlQuery(sql, InvestigationCASEId.class, true);
			Object[] objects = list1.toArray();
			if (objects.length == 0) {
				sansanqudata = "\'\'";
			} else {
				for (int i = 0; i < list1.size(); i++) {
					sb.append("'")
							.append(list1.get(i).getCASE_ID().substring(0, 23))
							.append("'").append(",");
					objects[i] = list1.get(i).getEVIDENCE_NAME() == null ? "暂无详细信息"
							: list1.get(i)
									.getEVIDENCE_NAME()
									.substring(
											0,
											list1.get(i).getEVIDENCE_NAME()
													.length() - 1);
				}
				sansanqudata = sb.toString().substring(0,
						sb.toString().length() - 1);
			}
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("RowDataScaseid", sansanqudata);
			m.put("EvidenceName", objects);
			Gson gson = new Gson();
			return gson.toJson(m).toString();
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@POST
	@Path("/getAllzujiScaseid")
	@Produces(MediaType.APPLICATION_JSON)
	public String GetAllzujiScaseid(
			@FormParam(value = "rowDataSercaid") String rowDataSercaid) {
		try {
			StringBuffer sb = new StringBuffer();
			String sansanqudata = "";
			String sql = "select casenumber as case_id,caseplace as evidence_name from foms.fais_scenebase where  sceneid in(select sceneid from foms.fais_sercapic where sercaid='"
					+ rowDataSercaid + "')";
			List<InvestigationCASEId> list1 = (List<InvestigationCASEId>) dbSupport
					.getSqlQuery(sql, InvestigationCASEId.class, true);
			Object[] objects = list1.toArray();
			if (objects.length == 0) {
				sansanqudata = "\'\'";
			} else {
				for (int i = 0; i < list1.size(); i++) {
					sb.append("'")
							.append(list1.get(i).getCASE_ID().substring(0, 23))
							.append("'").append(",");
					objects[i] = list1.get(i).getEVIDENCE_NAME() == null ? "暂无详细信息"
							: list1.get(i).getEVIDENCE_NAME();
				}
				sansanqudata = sb.toString().substring(0,
						sb.toString().length() - 1);
			}
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("RowDataScaseid", sansanqudata);
			m.put("EvidenceName", objects);
			Gson gson = new Gson();
			return gson.toJson(m).toString();
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	// 综合串并导航中的 ---> 综合串并
	@SuppressWarnings("unchecked")
	@POST
	@Path("/getZonghecb")
	@Produces(MediaType.APPLICATION_JSON)
	public String getZonghecb(@FormParam(value = "page") int page,
			@FormParam(value = "rows") int rows)

	{
		try {
			String sql = SqlEnum.zonghe;
			String innerSql = "select rownum as id,a.* from (select syntheticalnum,decode(footnum, null, '', '足迹 ') || decode(dnanum, null, '', 'DNA ') ||"
					+ "decode(fingernum, null, '', '指纹') as cbyj,decode(footnum, null, '', footnum || ' ') || decode(dnanum, null, '', dnanum || ' ') ||"
					+ " decode(fingernum, null, '', fingernum) as cbh,to_char(updatedate, 'yyyy-MM-dd hh24:mi:ss') as updatedate from foms.syntheticalcaseinfo where (footnum is not null and dnanum is not null)"
					+ "or (footnum is not null and fingernum is not null) or (fingernum is not null and dnanum is not null) order by SYNTHETICALNUM desc) a";
			// StringBuffer sb=new StringBuffer(innerSql);

			int endPage = page * rows;
			int startPage = (page - 1) * rows + 1;
			// if (begintime != ""&&begintime.length()>0)
			// {
			// sb.append(" and to_char(updatedate, 'yyyy-MM-dd hh24:mi:ss')>='"
			// + begintime + " 00:00:00'");
			// }
			// if (endtime != ""&&endtime.length()>0)
			// {
			// sb.append(" and to_char(updatedate, 'yyyy-MM-dd hh24:mi:ss')<='"
			// + endtime + " 23:59:59'");
			// }

			// 获取总数
			int count = dbSupport.getcount2(sql);

			// sb.append(" order by updatedate desc) a ");

			String fenyeSql = "select * from (select t.*,rownum rn from ("
					+ innerSql + ") t where rownum<=" + endPage
					+ ") where rn>=" + startPage + "";

			// sql查询
			List<Syntheticalcaseinfo> list = (List<Syntheticalcaseinfo>) dbSupport
					.getSqlQuery(fenyeSql, Syntheticalcaseinfo.class, true);

			Map<String, Object> m = new HashMap<String, Object>();
			m.put("total", count);
			m.put("rows", list);
			Gson gson = new Gson();
			return gson.toJson(m).toString();
		} catch (Exception ex) {
			log.AddLog("E",
					"getZonghecb(" + page + "," + rows + ")" + ex.getMessage(),
					"");
			return null;
		}
	}

	// 综合串并---》综合串并 综串详细信息
	@POST
	@Path("/getSyntheticalCaseDetails")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String GetSyntheticalCaseDetails(
			@FormParam(value = "syntheticalNum") String syntheticalNum) {
		try {
			List<ScenceinvistigationmodelDetailsInfo> SyntheList = new ArrayList<ScenceinvistigationmodelDetailsInfo>();
			List<ScenceinvistigationmodelDetailsInfo> listFOOT = null;
			List<ScenceinvistigationmodelDetailsInfo> listDNA = null;
			List<ScenceinvistigationmodelDetailsInfo> listFINGER = null;
			String sql = "select FOOTNUM,DNANUM,FINGERNUM from foms.syntheticalcaseinfo where syntheticalnum='"
					+ syntheticalNum + "'";
			// 根据编号查出对应的dna，foot的编号
			List<Syntheticalcaseinfo> list = (List<Syntheticalcaseinfo>) dbSupport
					.getSqlQuery(sql, Syntheticalcaseinfo.class, true);

			for (Syntheticalcaseinfo syn : list) {
				if (!("").equals(syn.getFOOTNUM()) && syn.getFOOTNUM() != null) {
					String sqlFOOT = "SELECT INVESTIGATION_NO,to_char(INVESTIGATION_DATE_FROM, 'yyyy-MM-dd hh24:mi:ss') as INVESTIGATION_DATE_FROM_S,INVESTIGATION_PLACE,CASE_NAME FROM FOMS.SCENE_INVESTIGATION WHERE INVESTIGATION_NO IN(SELECT USCENEID FROM FOMS.FAIS_SCENEBASE WHERE SCENEID IN(select SCENEID from foms.Fais_Sercapic where sercaid='"
							+ syn.getFOOTNUM() + "'))";
					listFOOT = (List<ScenceinvistigationmodelDetailsInfo>) dbSupport
							.getSqlQuery(sqlFOOT,
									ScenceinvistigationmodelDetailsInfo.class,
									true);
					if (listFOOT != null)
						SyntheList.addAll(setcbyj(listFOOT, "足迹"));
				}

				if (!("").equals(syn.getDNANUM()) && syn.getDNANUM() != null) {
					// //String
					// sqlDNA="SELECT * FROM FOMS.SCENE_INVESTIGATION WHERE INVESTIGATION_NO IN ("
					// +
					// "SELECT CASE_ID FROM FOMS.DNA_CASE_EVIDENCE WHERE EVIDENCE_NO IN ("
					// +
					// "SELECT MATCH_SAMPLE_NO FROM FOMS.DNA_NOTIFICATION WHERE SRC_CASE_ID='A3702000002003090900351HHHHHHHHH'))"
					// +
					// " OR CASE_NO IN (SELECT CASE_ID FROM FOMS.DNA_CASE_EVIDENCE WHERE EVIDENCE_NO IN (SELECT MATCH_SAMPLE_NO FROM FOMS.DNA_NOTIFICATION WHERE SRC_CASE_ID='A3702000002003090900351HHHHHHHHH'))";
					// listDNA=(List<ScenceinvistigationmodelDetails>)
					// dbSupport.getSqlQuery(sqlDNA,
					// ScenceinvistigationmodelDetails.class, true);

					// String
					// sql1="SELECT * FROM FOMS.SCENE_INVESTIGATION WHERE INVESTIGATION_NO IN (SELECT CASE_ID FROM FOMS.DNA_CASE_EVIDENCE WHERE EVIDENCE_NO IN ("
					// +
					// "SELECT MATCH_SAMPLE_NO FROM FOMS.DNA_NOTIFICATION WHERE SRC_CASE_ID='A3702000002003090900351HHHHHHHHH'))";

					String sql1 = "SELECT INVESTIGATION_NO,to_char(INVESTIGATION_DATE_FROM, 'yyyy-MM-dd hh24:mi:ss') as INVESTIGATION_DATE_FROM_S,INVESTIGATION_PLACE,CASE_NAME FROM FOMS.SCENE_INVESTIGATION WHERE INVESTIGATION_NO IN ("
							+ "SELECT INVE_NO FROM FOMS.DNA_CASE_EVIDENCE WHERE CASE_ID IN "
							+ "(SELECT SCASEID FROM FOMS.DNA_SERIALBASE WHERE DNA_SERIAL_NO IN ('"
							+ syn.getDNANUM().replaceAll(",", "','") + "')))";
					// String sql1 =
					// "SELECT INVESTIGATION_NO,to_char(INVESTIGATION_DATE_FROM, 'yyyy-MM-dd hh24:mi:ss') as INVESTIGATION_DATE_FROM_S,INVESTIGATION_PLACE,CASE_NAME FROM FOMS.SCENE_INVESTIGATION WHERE INVESTIGATION_NO IN "
					// +
					// "(SELECT INVE_NO FROM FOMS.DNA_CASE_EVIDENCE WHERE EVIDENCE_NAME IN "
					// +
					// "(SELECT Src_Sample_No FROM FOMS.DNA_NOTIFICATION WHERE SRC_CASE_ID IN "
					// +
					// "(SELECT DISTINCT SCASEID FROM FOMS.DNA_SERIALBASE WHERE DNA_SERIAL_NO IN('"
					// + syn.getDNANUM().replaceAll(",", "','") + "'))))";

					// String sql2 =
					// "SELECT INVESTIGATION_NO,to_char(INVESTIGATION_DATE_FROM, 'yyyy-MM-dd hh24:mi:ss') as INVESTIGATION_DATE_FROM_S,INVESTIGATION_PLACE,CASE_NAME FROM FOMS.SCENE_INVESTIGATION WHERE INVESTIGATION_NO IN "
					// +
					// "(SELECT INVE_NO FROM FOMS.DNA_CASE_EVIDENCE WHERE EVIDENCE_NAME IN "
					// +
					// "(SELECT Src_Sample_No FROM FOMS.DNA_NOTIFICATION WHERE SRC_CASE_ID IN "
					// +
					// "(SELECT DISTINCT SCASEID FROM FOMS.DNA_SERIALBASE WHERE DNA_SERIAL_NO IN('"
					// + syn.getDNANUM().replaceAll(",", "','") + "'))))";

					// String
					// sql2="SELECT * FROM FOMS.SCENE_INVESTIGATION WHERE CASE_NO IN (SELECT CASE_ID FROM FOMS.DNA_CASE_EVIDENCE WHERE EVIDENCE_NO IN ("
					// +
					// "SELECT MATCH_SAMPLE_NO FROM FOMS.DNA_NOTIFICATION WHERE SRC_CASE_ID='A3702000002003090900351HHHHHHHHH'))";

					listDNA = (List<ScenceinvistigationmodelDetailsInfo>) dbSupport
							.getSqlQuery(sql1,
									ScenceinvistigationmodelDetailsInfo.class,
									true);
					if (listDNA != null) {
						SyntheList.addAll(setcbyj(listDNA, "DNA"));
					}
					// listDNA = (List<ScenceinvistigationmodelDetailsInfo>)
					// dbSupport
					// .getSqlQuery(sql2,
					// ScenceinvistigationmodelDetailsInfo.class,
					// true);
					// if (listDNA != null) {
					// SyntheList.addAll(setcbyj(listDNA, "DNA"));
					// }
				}
				if (!("").equals(syn.getFINGERNUM())
						&& syn.getFINGERNUM() != null) {
					String sqlFINGER = "";
					if (!("").equals(sqlFINGER) && sqlFINGER != null) {
						listFINGER = (List<ScenceinvistigationmodelDetailsInfo>) dbSupport
								.getSqlQuery(
										sqlFINGER,
										ScenceinvistigationmodelDetailsInfo.class,
										true);
						if (listFINGER != null)
							SyntheList.addAll(setcbyj(listFINGER, "指纹"));
					}
				}
			}

			Gson gson = new Gson();
			return gson.toJson(SyntheList).toString();
		} catch (Exception ex) {
			// Log.AddLog("E", "GetSyntheticalCaseDetails(" + syntheticalNum +
			// ")" + ex.getMessage(), "");
			// return null;

			throw new RuntimeException(ex.getMessage());
		}
	}

	private List<ScenceinvistigationmodelDetailsInfo> setcbyj(
			List<ScenceinvistigationmodelDetailsInfo> scenelist, String cbyj) {
		for (Object sceneinv : scenelist) {
			((ScenceinvistigationmodelDetailsInfo) sceneinv).setCBYJ(cbyj);
		}
		return scenelist;
	}

}
