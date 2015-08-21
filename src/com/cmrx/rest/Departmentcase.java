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
import com.cmrx.bean.Entity.DepartSuspectDetails;
import com.cmrx.bean.Entity.Depart_syn;
import com.cmrx.bean.Entity.Depart_synAll;
import com.cmrx.bean.Entity.Departzhengcha;
import com.cmrx.bean.Entity.SelDepartmentCase;
import com.cmrx.bean.model.Departmentcaseinfo;
import com.cmrx.dao.DBSupport;
import com.google.gson.Gson;
//综合串并
@Path("/Departmentcase")
public class Departmentcase {

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

	@POST
	@Path("/getDepartmentCase")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public String GetDepartmentCase(@FormParam(value="accord") String accord,@FormParam(value="begintime") String begintime,
								@FormParam(value="endtime") String endtime,@FormParam(value="page") int page,
								@FormParam(value="rows") int rows) {
		try {
//SELECT departmentNum,accord,createTime,uploadUser,count(departmentNum) as dcount from Departmentcaseinfo where dataType=1";
			 int endPage=page * rows;
	    	 int startPage=(page- 1) * rows + 1;
			 String innerSql =SqlEnum.SelDepartmentCase+" and ";
			 StringBuffer sb=new StringBuffer(innerSql);
			 sb.append(accord);
			 if (begintime != ""&&begintime.length()>0)
			 {
				 sb.append(" and to_char(createTime, 'yyyy-MM-dd hh24:mi:ss')>='" + begintime + " 00:00:00'");
			 }
			 if (endtime != ""&&endtime.length()>0)
			 {
				 sb.append(" and to_char(createTime, 'yyyy-MM-dd hh24:mi:ss')<='" + endtime + " 23:59:59'");
			 }
			
			 
			// sb.append(" group by accord,departmentNum order by createTime desc ");
			 
			 int count=dbSupport.getcount(sb.toString());//放在group之后，否则查询不是我们想要的结果。。
			 
			 //分页dna_serialbase
	    	String fenyeSql="select * from (select t.*,rownum rn from ("+sb.toString()+") t where rownum<="+endPage+") where rn>="+startPage+"";
			
			//List<SelDepartmentCase> list=  (List<SelDepartmentCase>) dbSupport.getSqlQuery(fenyeSql, com.cmrx.bean.Entity.SelDepartmentCase.class,true);
	    	List<SelDepartmentCase> list= new ArrayList<SelDepartmentCase>();
			
			Map<String, Object> m=new HashMap<String, Object>();
		    m.put("total",count);
		    m.put("rows", list);
		    Gson gson=new Gson();
			return gson.toJson(m).toString();
			
		} catch (Exception ex) {
//			 Log.AddLog("E", "GetDepartmentCount(" + accord + "," + begintime
//			 + "," + endtime + "," + page + "," + rows + ")" +
//			 ex.getMessage(), "");
//			 return null;
			throw new RuntimeException(ex.getMessage());
		}
	}

	//导航栏【综合串并】中的【综合串并】
//	@POST
//	@Path("/getSyntheticalCase")
//	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//	public String GetSyntheticalCase(@FormParam(value="begintime") String begintime,@FormParam(value="endtime") String endtime,@FormParam(value="page") int page,@FormParam(value="rows") int rows) {
//		{
//			try
//			{
//				String sql = SqlEnum.zonghe;
//				if (begintime != ""&&begintime.length()>0)
//				{
//					sql += " and createDate>='" + begintime + " 00:00:00'";
//				}
//				if (endtime != ""&&endtime.length()>0)
//				{
//					sql += " and createDate<='" + endtime + " 23:59:59'";
//				}
//				
//				sql += " GROUP BY syntheticalNum order by s.createDate desc ";
//				
//				//获取总数
//				int count=dbSupport.getcount(sql);
//				
//				sql+="LIMIT " + (page - 1) * rows + "," + rows;
//				List<Depart_syn> list=  (List<Depart_syn>) dbSupport.getSqlQuery(sql, Depart_syn.class,true);
//
//				Map<String, Object> m=new HashMap<String, Object>();
//				m.put("total",count);
//				m.put("rows", list);
//				Gson gson=new Gson();
//				// System.out.println( gson.toJson(m).toString());
//				
//				return gson.toJson(m).toString();
//			}
//			catch (Exception ex)
//			{
//				log.AddLog("E", "GetSyntheticalCase(" + begintime + "," + endtime + "," + page + "," + rows + ")" + ex.getMessage(), "");
//				return null;
//
//				//throw new RuntimeException(ex.getMessage());
//			}
//		}
//
//	}
	
	 //部门串案详细信息、列表
//	@SuppressWarnings("unchecked")
//	@POST
//	@Path("/getDepartmentCaseDetails")
//	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//    public String GetDepartmentCaseDetails(@FormParam(value="departmentNum") String departmentNum,@FormParam(value="accord") String accord)
//    {
//		 try
//         {
//            // String sql = "SELECT * from departmentcaseinfo where dataType=1 and accord='" + accord + "' and departmentNum='" + departmentNum + "'";
//             String hql="From Departmentcaseinfo where dataType=1 and accord='" + accord + "' and departmentNum='" + departmentNum + "'";
//             List<Departmentcaseinfo> list=  (List<Departmentcaseinfo>) dbSupport.GetQueryByHql(hql);
//
//			Map<String, Object> m=new HashMap<String, Object>();
//			m.put("total",list.size());//这个需要总数的
//			m.put("rows", list);
//			Gson gson=new Gson();
//			// System.out.println( gson.toJson(m).toString());
//
//			return gson.toJson(m).toString();
//         }
//         catch (Exception ex)
//         {
////             Log.AddLog("E", "GetDepartmentDetails(" + accord + "," + departmentNum + ")" + ex.getMessage(), "");
////             return null;
//        	 throw new RuntimeException(ex.getMessage());
//         }
//    }
	
    // 获取嫌疑人正查
	//dataType=2【正查】
    //dataType=3【倒查】
    // </summary>
    // <returns></returns>
//	@POST
//	@Path("/getBrightSearch")
//	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//    public String GetBrightSearch(@FormParam(value="accord")String accord, @FormParam(value="begintime")String beginTime, 
//    								@FormParam(value="endtime")String endTime, @FormParam(value="page")int page, 
//    								@FormParam(value="rows")int rows, @FormParam(value="dataType")int dataType)
//    {
//        try
//        {   
//            String sql = "SELECT suspectNum,suspectSex,suspectName,suspectCardId,suspectAdd,accord from departmentcaseinfo where dataType=" + dataType + " and (" + accord + ")";
//            if (beginTime != ""&&beginTime.length()>0)
//            {
//                sql += " and createTime>='" + beginTime + " 00:00:00'";
//            }
//            if (endTime != ""&&endTime.length()>0)
//            {
//                sql += " and createTime<='" + endTime + " 23:59:59'";
//            }
//            
//            int count=dbSupport.getcount(sql);
//            
//            sql += " LIMIT " + (page - 1) * rows + "," + rows;
//            List<Departzhengcha> list=  (List<Departzhengcha>) dbSupport.getSqlQuery(sql, Departzhengcha.class, true);
//            
//            Map<String, Object> m=new HashMap<String, Object>();
//			m.put("total",count);
//			m.put("rows", list);
//			Gson gson=new Gson();
//
//			return gson.toJson(m).toString();
//        }
//        catch (Exception ex)
//        {
//        	log.AddLog("E", "GetBrightSearch(" + accord + "," + beginTime + "," + endTime + "," + page + "," + rows + "," + dataType + ")" + ex.getMessage(), "");
//            return null;
//        }
//    }
	
	
    // 获取嫌疑人详细信息[倒查和正查的]
	//dataType=2【正查】
	//dataType=3【倒查】
    // </summary>
    // <returns></returns>
//	@POST
//	@Path("/getSuspectDetails")
//	@Produces(MediaType.APPLICATION_JSON)
//    public DepartSuspectDetails GetSuspectDetails(@FormParam(value="accord")String accord,
//    											@FormParam(value="suspectNum")String suspectNum,
//												@FormParam(value="dataType")int dataType)
//    {
//        try
//        {
//            String sql = "SELECT xcky,caseNum,suspectNum,suspectAge,suspectBirthday,suspectSex,suspectName,suspectCardId,suspectAdd from departmentcaseinfo where dataType="+dataType+"  and accord='" + accord + "' and suspectNum='" + suspectNum + "'";
//            @SuppressWarnings("unchecked")
//			List<DepartSuspectDetails> list= (List<DepartSuspectDetails>) dbSupport.getSqlQuery(sql, DepartSuspectDetails.class,true);
//            return list.get(0);
//        }
//        catch (Exception ex)
//        {
//        	log.AddLog("E", "GetSuspectDetails(" + accord + "," + suspectNum + ")" + ex.getMessage(), "");
//            return null;
//        }
//    }
	
	
    /// 获取综串详细信息
    /// </summary>
    /// <returns></returns>
//	@POST
//	@Path("/getSyntheticalCaseDetails")
//	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//    public String GetSyntheticalCaseDetails(@FormParam(value="syntheticalNum")String syntheticalNum)
//    {
//        try
//        {
//            String sql = "SELECT caseNum,xcky,accord,uploadUser,uploadUnit,createTime,caseType,caseAdd,caseTime FROM departmentcaseinfo d,syntheticalcaseinfo s where ((departmentNum=s.footNum and accord='足迹') or (departmentNum=s.dnaNum and accord='DNA') or (departmentNum=s.fingerNum and accord='指纹')) and s.syntheticalNum='" + syntheticalNum + "'";
//            List<Depart_synAll> list=  (List<Depart_synAll>) dbSupport.getSqlQuery(sql, Depart_synAll.class, true);
//            
//            Map<String, Object> m=new HashMap<String, Object>();
//			m.put("total",0);
//			m.put("rows", list);
//			Gson gson=new Gson();
//			return gson.toJson(m).toString();
//        }
//        catch (Exception ex)
//        {
////            Log.AddLog("E", "GetSyntheticalCaseDetails(" + syntheticalNum + ")" + ex.getMessage(), "");
////            return null;
//        	
//        	throw new RuntimeException(ex.getMessage());
//        }
//    }
//    
    /// 获取部门串案总数
//    public int GetDepartmentCount(String accord, String begintime, String endtime)
//    {
//        try
//        {
//            String sql = "select sum(t.c) from (" +
//            		"SELECT count(DISTINCT departmentNum) c,accord from departmentcaseinfo " +
//            		"where dataType=1 and (" + accord + ")";
//            if (!begintime.equals("")) 
//            {
//                sql += " and createTime>='" + begintime + " 00:00:00'";    
//            }
//            if (!endtime.equals(""))
//            {
//                sql += " and createTime<='" + endtime + " 23:59:59'";   
//            }
//            sql += " group by accord) t";
//            List list= dbSupport.getSqlQuery(sql, null, false);
//            if(list.get(0)==null)
//            	return 11;
//            else
//                return Integer.parseInt(list.get(0).toString());
//        }
//        catch (Exception ex)
//        {
//        	log.AddLog("E", "GetDepartmentCount(" + accord + "," + begintime + "," + endtime + ")" + ex.getMessage(), "");
//            return 0;
//        }
//    }
}
