package com.cmrx.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OracleDao {
	public ResultSet rs = null;
	public Statement stmt = null;
	public Connection conn = null;

	public OracleDao(String ip, String sid, String user, String pwd)
			throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection("jdbc:oracle:thin:@" + ip + ":1521:"
				+ sid, user, pwd);
	}

	public void CloseCon() {
		try {
			this.conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Map<String, String>> GetDBlistBySQL(String sql)
			throws Exception {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			Map<String, String> rsMap = new HashMap<String, String>();
			for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
//				if (rs.getMetaData().getColumnName(i).toString().equals("ID"))
//					continue;
				rsMap.put(rs.getMetaData().getColumnName(i), rs.getString(i));
			}
			list.add(rsMap);
		}
		rs.close();
		stmt.close();
		return list;
	}

	// 通用删除、更新方法
	public int ExecuteBySQL(String sql) throws Exception {
		stmt = conn.createStatement();
		int i = stmt.executeUpdate(sql);
		stmt.close();
		return i;
	}

}

