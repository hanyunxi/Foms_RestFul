package com.cmrx.rest;

import java.util.List;
import java.util.Map;

import com.cmrx.dao.OracleDao;
import com.google.gson.Gson;

public class MindManagerUtil {
	private String[] xckydb = new String[] { "127.0.0.1", "orcl", "foms",
			"foms" };
	private String[] dnadb = new String[] { "127.0.0.1", "orcl", "foms", "foms" };
	private String[] faisdb = new String[] { "127.0.0.1", "orcl", "foms",
			"foms" };

	public MindManagerUtil() {

	}

	public static void main(String[] args) {
		MindManagerUtil aa = new MindManagerUtil();
		System.out.println(aa.getXckyInfo(null, "K3702833100002014010001"));
		System.out.println(aa.getXckyInfo("A3702833100002014010003", null));
		System.out.println(aa.getDnaSampleByXckyno(null,
				"K3702833100002014010001"));
		System.out.println(aa.getDnaSampleByXckyno(
				"A3700000002004040800226HHHHHHHHH", null));
		System.out.println(aa.getDnaInfoBySample("W3702000002003090900352"));
		System.out.println(aa.getDnaSyntheBySample("W3700000002004040800200"));
		System.out.println(aa.getFaisSercaInfoBycaseno(
				"A3715243100002014060011", null));
		System.out.println(aa.getFaisSercaInfoBycaseno(null,
				"K3715244900002014060008"));
	}

	// 通过现勘号获取现勘数据
	public String getXckyInfo(String case_no, String invno) {
		String sql = null;
		String result = null;
		if (case_no != null)
			sql = "select * from scene_investigation where case_no='" + case_no
					+ "'";
		else if (invno != null)
			sql = "select * from scene_investigation where investigation_no='"
					+ invno + "'";
		OracleDao oracledao = null;
		try {
			oracledao = new OracleDao(xckydb[0], xckydb[1], xckydb[2],
					xckydb[3]);
			List<Map<String, String>> invlist = oracledao.GetDBlistBySQL(sql);
			Gson gson = new Gson();
			result = gson.toJson(invlist);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			oracledao.CloseCon();
		}
		return result;
	}

	// 通过现勘号或者案事件号获取DNA样品信息
	public String getDnaSampleByXckyno(String caseid, String invno) {
		String sql = null;
		String result = null;
		if (caseid != null)
			sql = "select * from dna_case_evidence where case_id='" + caseid
					+ "'";
		else if (invno != null)
			sql = "select * from dna_case_evidence where inve_no='" + invno
					+ "'";
		OracleDao oracledao = null;
		try {
			oracledao = new OracleDao(dnadb[0], dnadb[1], dnadb[2], dnadb[3]);
			List<Map<String, String>> invlist = oracledao.GetDBlistBySQL(sql);
			Gson gson = new Gson();
			result = gson.toJson(invlist);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			oracledao.CloseCon();
		}
		return result;
	}

	// 通过DNA样品编号获取串并的DNA样品编号
	public String getDnaInfoBySample(String sampleno) {
		String result = null;
		String sql = "select * from dna_notification where src_sample_no='"
				+ sampleno + "'";
		OracleDao oracledao = null;
		try {
			oracledao = new OracleDao(dnadb[0], dnadb[1], dnadb[2], dnadb[3]);
			List<Map<String, String>> invlist = oracledao.GetDBlistBySQL(sql);
			Gson gson = new Gson();
			result = gson.toJson(invlist);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			oracledao.CloseCon();
		}
		return result;
	}

	// 通过Dna样品编号获取串并的案件编号
	public String getDnaSyntheBySample(String sampleno) {
		String result = null;
		String sql = "select * from foms.dna_case_evidence where evidence_no='"
				+ sampleno + "'";
		OracleDao oracledao = null;
		try {
			oracledao = new OracleDao(dnadb[0], dnadb[1], dnadb[2], dnadb[3]);
			List<Map<String, String>> invlist = oracledao.GetDBlistBySQL(sql);
			Gson gson = new Gson();
			result = gson.toJson(invlist);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			oracledao.CloseCon();
		}
		return result;
	}

	// 通过案件编号或者现勘号获取足迹串并信息
	public String getFaisSercaInfoBycaseno(String caseid, String invno) {
		String result = null;
		String sql = null;
		if (caseid != null)
			sql = "select * from fais_scenebase where "
					+ "sceneid in(select sercaid from fais_sercapic "
					+ "where sceneid=(select sceneid from fais_scenebase "
					+ "where casenumber='" + caseid + "'))";
		else if (invno != null)
			sql = "select * from fais_scenebase where "
					+ "sceneid in(select sercaid from fais_sercapic "
					+ "where sceneid=(select sceneid from fais_scenebase "
					+ "where usceneid='" + invno + "'))";
		OracleDao oracledao = null;
		try {
			oracledao = new OracleDao(faisdb[0], faisdb[1], faisdb[2],
					faisdb[3]);
			List<Map<String, String>> invlist = oracledao.GetDBlistBySQL(sql);
			Gson gson = new Gson();
			result = gson.toJson(invlist);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			oracledao.CloseCon();
		}
		return result;
	}

}
