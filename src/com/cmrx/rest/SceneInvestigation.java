package com.cmrx.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.cmrx.bean.SqlEnum;
import com.cmrx.bean.Entity.Scenceinvistigationmodel;
import com.cmrx.bean.Entity.ScenceinvistigationmodelDetails;
import com.cmrx.bean.Entity.ScenceinvistigationmodelTimeBase;
import com.cmrx.bean.Entity.Timebase;
import com.cmrx.dao.DBSupport;
import com.google.gson.Gson;

@Path("/SceneInvestigation")
public class SceneInvestigation {
	public DBSupport dbSupport;
	public Log log;

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public DBSupport getDbSupport() {
		return dbSupport;
	}

	public void setDbSupport(DBSupport dbSupport) {
		this.dbSupport = dbSupport;
	}

	@Path("/getSceneInvestigationList")
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String GetSceneInvestigationList(
			@FormParam(value = "begintime") String begintime,
			@FormParam(value = "endtime") String endtime,
			@FormParam(value = "page") int page,
			@FormParam(value = "rows") int rows) {
		StringBuffer sb = new StringBuffer();
		try {
			String sql = SqlEnum.scenceinvestigation;

			if (begintime != "" && begintime.length() > 0) {
				sql += " investigation_date_from>='" + begintime + " 00:00:00'";
			}
			if (endtime != "" && endtime.length() > 0) {
				sql += " and investigation_date_from<='" + endtime
						+ " 23:59:59'";
			}
			sql += " order by investigation_date_from desc";
			sb.append(" select * from(select t.*,rownum rn from(").append(sql)
					.append(")t where rownum<=").append((page) * rows)
					.append(") where rn>=").append((page - 1) * rows);

			// 获取总数
			int count = dbSupport
					.getcount2("select count(*) from scene_investigation");

			@SuppressWarnings("unchecked")
			List<Scenceinvistigationmodel> list = (List<Scenceinvistigationmodel>) dbSupport
					.getSqlQuery(sb.toString(), Scenceinvistigationmodel.class,
							true);
			for (Scenceinvistigationmodel scenceinvistigationmodel : list) {
				if (scenceinvistigationmodel.getINVESTIGATION_DATE_FROM() != null)
					scenceinvistigationmodel
							.setINVESTIGATION_DATE_FROM(new SimpleDateFormat(
									"yyyy年MM月dd日")
									.format(scenceinvistigationmodel
											.getINVESTIGATION_DATE_FROM()));
				if (scenceinvistigationmodel.getOCCURRENCE_DATE_FROM() != null)
					scenceinvistigationmodel
							.setOCCURRENCE_DATE_FROM(new SimpleDateFormat(
									"yyyy年MM月dd日")
									.format(scenceinvistigationmodel
											.getOCCURRENCE_DATE_FROM()));
			}
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("total", count);
			m.put("rows", list);
			Gson gson = new Gson();
			return gson.toJson(m).toString();
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Path("/getSceneInvestigationDetails")
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String GetSceneInvestigationDetails(
			@FormParam(value = "INVESTIGATION_NO") String INVESTIGATION_NO,
			@FormParam(value = "CASE_NO") String CASE_NO) {
		StringBuffer sb = new StringBuffer();
		try {
			sb.append(
					"select * from  scene_investigation where INVESTIGATION_NO=")
					.append("'").append(INVESTIGATION_NO).append("'");
			if (!"undefined".equals(CASE_NO)) {
				sb.append(" and CASE_NO=").append("'").append(CASE_NO)
						.append("'");
			}
			@SuppressWarnings("unchecked")
			List<ScenceinvistigationmodelDetails> list = (List<ScenceinvistigationmodelDetails>) dbSupport
					.getSqlQuery(sb.toString(),
							ScenceinvistigationmodelDetails.class, true);
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("total", list.size());// 这个需要总数的
			m.put("rows", list);
			Gson gson = new Gson();
			return gson.toJson(m).toString();
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Path("/getSceneInvestigationTimeBase")
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String GetSceneInvestigationTimeBase(
			@FormParam(value = "kanchahaodata") String kanchahaodata) {
		StringBuffer sb = new StringBuffer();
		List<Timebase> list2 = new ArrayList<Timebase>();
		Map<String, Object> m = new HashMap<String, Object>();
		try {
			sb.append(SqlEnum.scenceinvestigationtimebase)
					.append(" where INVESTIGATION_NO=").append("'")
					.append(kanchahaodata).append("'");
			List<ScenceinvistigationmodelTimeBase> list = (List<ScenceinvistigationmodelTimeBase>) dbSupport
					.getSqlQuery(sb.toString(),
							ScenceinvistigationmodelTimeBase.class, true);
			if (list.get(0) == null) {
				return null;
			} else {
				ScenceinvistigationmodelTimeBase sc = list.get(0);
				list2 = gettimebase(sc);
			}
			Gson gson = new Gson();
			System.out.println(gson.toJson(list2).toString());
			return gson.toJson(list2).toString();
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	private List<Timebase> gettimebase(ScenceinvistigationmodelTimeBase sc) {
		List<Timebase> list = new LinkedList<Timebase>();
		/*
		 * RECEIVED_DATE;//接警时间 DISPATCH_DATE;//出警时间
		 * OCCURRENCE_DATE_TO;//发案时间（止) NOTE_MADE_DATE;//制作笔录时间
		 * PROTECTION_DATE;//现场保护时间 TRANSFER_DATE;//信息上报时间
		 * INVESTIGATION_DATE_FROM;//勘验开始时间
		 */
		if (sc.getOCCURRENCE_DATE_FROM() != null
				&& !"".equals(sc.getOCCURRENCE_DATE_FROM())) {
			// DISPATCH_DATE;//出警时间
			Timebase timebase8 = new Timebase();
			timebase8.setName1("发案时间(起)");
			timebase8.setValue1(sc.getOCCURRENCE_DATE_FROM());
			list.add(timebase8);
		}
		if (sc.getOCCURRENCE_DATE_TO() != null
				&& !"".equals(sc.getOCCURRENCE_DATE_TO())) {
			// OCCURRENCE_DATE_TO;//发案时间（止)
			Timebase timebase3 = new Timebase();
			timebase3.setName1("发案时间(止)");
			timebase3.setValue1(sc.getOCCURRENCE_DATE_TO());
			list.add(timebase3);
		}
		if (sc.getRECEIVED_DATE() != null && !"".equals(sc.getRECEIVED_DATE())) {
			// RECEIVED_DATE;//接警时间
			Timebase timebase2 = new Timebase();
			timebase2.setName1("接警时间");
			timebase2.setValue1(sc.getRECEIVED_DATE());
			list.add(timebase2);
		}
		if (sc.getDISPATCH_DATE() != null && !"".equals(sc.getDISPATCH_DATE())) {
			// DISPATCH_DATE;//出警时间
			Timebase timebase1 = new Timebase();
			timebase1.setName1("出警时间");
			timebase1.setValue1(sc.getDISPATCH_DATE());
			list.add(timebase1);
		}
		if (sc.getPROTECTION_DATE() != null
				&& !"".equals(sc.getPROTECTION_DATE())) {
			// PROTECTION_DATE;//现场保护时间
			Timebase timebase5 = new Timebase();
			timebase5.setName1("现场保护时间");
			timebase5.setValue1(sc.getPROTECTION_DATE());
			list.add(timebase5);
		}
		if (sc.getINVESTIGATION_DATE_FROM() != null
				&& !"".equals(sc.getINVESTIGATION_DATE_FROM())) {
			// INVESTIGATION_DATE_FROM;//勘验开始时间
			Timebase timebase7 = new Timebase();
			timebase7.setName1("勘验开始时间");
			timebase7.setValue1(sc.getINVESTIGATION_DATE_FROM());
			list.add(timebase7);
		}
		if (sc.getINVESTIGATION_DATE_TO() != null
				&& !"".equals(sc.getINVESTIGATION_DATE_TO())) {
			// DISPATCH_DATE;//出警时间
			Timebase timebase9 = new Timebase();
			timebase9.setName1("勘验结束时间");
			timebase9.setValue1(sc.getINVESTIGATION_DATE_TO());
			list.add(timebase9);
		}
		if (sc.getNOTE_MADE_DATE() != null
				&& !"".equals(sc.getNOTE_MADE_DATE())) {
			// NOTE_MADE_DATE;//制作笔录时间
			Timebase timebase4 = new Timebase();
			timebase4.setName1("制作笔录时间");
			timebase4.setValue1(sc.getNOTE_MADE_DATE());
			list.add(timebase4);
		}

		if (sc.getTRANSFER_DATE() != null && !"".equals(sc.getTRANSFER_DATE())) {
			// TRANSFER_DATE;//信息上报时间
			Timebase timebase6 = new Timebase();
			timebase6.setName1("信息上报时间");
			timebase6.setValue1(sc.getTRANSFER_DATE());
			list.add(timebase6);

		}

		return list;
	}
}
