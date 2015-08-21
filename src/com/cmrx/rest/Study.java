package com.cmrx.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import net.sf.json.JSONObject;
import com.cmrx.bean.Entity.DetailTree;
import com.cmrx.bean.Entity.FaisTree;
import com.cmrx.bean.Entity.Studylist;
import com.cmrx.bean.model.*;
import com.cmrx.dao.DBSupport;
import com.cmrx.dao.DateUtil;
import com.google.gson.Gson;
@Path("/Study")
public class Study {

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
	@Path("/studylist")
	@Produces(MediaType.APPLICATION_JSON)
	public String GetStudyList(@FormParam(value = "studyname") String studyname,@FormParam(value = "count") int count,
			@FormParam(value = "page") int pageindex,@FormParam(value = "rows") int rows){
		int datacount = count;
		int endPage=pageindex * rows;
    	int startPage=(pageindex- 1) * rows + 1;
		try
         {
             String innerSql = "select id,formulaname,createuser,to_char(createtime,'yyyy-MM-dd hh24:mi:ss') createtime,formularemark from ANALYZEFORMULA where 1=1 ";
             if (studyname != ""&&studyname.length()>0)
             {
            	 innerSql += " and (formulaname like '%" + studyname + "%')";
             }
             
             innerSql += " ORDER BY createtime desc ";
             
             //查询总数:
             if (count == -1)
             {
            	 //查询总数
                 datacount = dbSupport.getcount(innerSql);
             }
             
             String fenyeSql="select * from (select t.*,rownum rn from ("+innerSql+") t where rownum<="+endPage+") where rn>="+startPage+"";
            //没有用到映射文件 
            List<Studylist> list=  (List<Studylist>) dbSupport.getSqlQuery(fenyeSql,Studylist.class,true);
 			
 			Map<String, Object> m=new HashMap<String, Object>();
 		    m.put("total",datacount);
 		    m.put("rows", list);
 		    Gson gson=new Gson();
 		    
 			return gson.toJson(m).toString();
         }
         catch (Exception ex)
         {
             log.AddLog("E", "GetStudyList(" + studyname + "," + pageindex + "," + rows + ")" + ex.getMessage(), "");
             return null;
         }
	}
	
    /// 删除公式（同时还要删除结果，删除任务）
	//这个要注意事务的统一管理问题
	@POST
	@Path("/studyDelete")
	@Produces(MediaType.TEXT_PLAIN)
    public String StudyDelete(@FormParam(value = "studyid")BigDecimal studyid,@FormParam(value = "userid") int userid)
    {
		//开启事务
    	Session session=dbSupport.getHibernateSessionUtil().currentSession();
    	Transaction ts=null;
        try
        {
        	//根据公式ID查出所有对应的任务编号，然后删除结果(SQL)
			String tasknum="select distinct(t.tasknum) from ANALYZETASK t where t.formulaid="+studyid;
            //删除任务(HQL)
            String hqlTask="delete from Analyzetask t where t.FORMULAID="+studyid;
        	
        	ts=session.beginTransaction();
        	
        	//得到所有的tasknum,任务编号(原生态SQL操作)
        	List list=dbSupport.getSqlQuery2(tasknum, null, false);
        	//HQL
        	Analyzeformula sb=(Analyzeformula) dbSupport.GetObjectByClass(new Analyzeformula(),studyid);
        	//hibernate的删除  公式(HQL)
            dbSupport.DelByObjectTransaction(sb);
            //删除结果
            for(int i=0;i<list.size();i++){
            	//HQL
            	dbSupport.DelByHQLTransaction("delete from Analyzeresult aa where aa.TASKNUM='"+list.get(i)+"'");
            }
            //删除任务 HQL
            dbSupport.DelByHQLTransaction(hqlTask);
            
            ts.commit();
            return "true";
        }
        catch (Exception ex)
        {
            if(ts!=null){
            	ts.rollback();
            }
            return "false";
        }finally{
        	session.close();
        }
    }
	
	//获取现场勘验树
	@POST
	@Path("/getXckyTree")
	@Produces(MediaType.TEXT_PLAIN)
    public String GetXckyTree(@FormParam(value = "rootkey")String rootkey,@FormParam(value = "dictPy")String dictPy)
    {
        try
        {
        	//Study页面根据该类型所有字典值
        	//改写
        	String sql = "select id,parent_key,text,isparent from(" +
        			"select DICT_KEY as id,parent_key,DICT_VALUE as text,(select count(*) from SYS_DICT dict where dict.ROOT_KEY='" + rootkey + "' ";
			
            sql += "and dict.PARENT_KEY=SYS_DICT.dict_key) as isparent from SYS_DICT where ROOT_KEY='" + rootkey + "' ) t";
            List<DetailTree> selectDt=(List<DetailTree>) dbSupport.getSqlQuery(sql, DetailTree.class, true);
            String val=null;
            if (selectDt.size() > 0)
            {
                val = AddTreeNode(selectDt, rootkey, "-1"); //-1
                val = val.substring(0, val.length() - 1);
                val = "[" + val + "]";
            }
            else {
                val = "";
            }
            return val.toLowerCase();
        }
        catch (Exception ex)
        {
            log.AddLog("E", "GetStudyTree(" + rootkey + "," + dictPy + ")" + ex.getMessage(), "");
            return null;
        } 
    }
	
	//获取足迹树
	@POST
	@Path("/getFaisTree")
	@Produces(MediaType.TEXT_PLAIN)
    public String GetFaisTree(@FormParam(value = "rootkey")String rootkey,@FormParam(value = "dictPy")String dictPy)
    {
        try
        {
        	//Study页面根据该类型所有字典值
        	//改写
//        	String sql = "select id,parent_key,text,isparent from(" +
//        			"select DICT_KEY as id,parent_key,DICT_VALUE as text,(select count(*) from SYS_DICT dict where dict.ROOT_KEY='" + rootkey + "'  and " +
//        					"dict.PARENT_KEY=SYS_DICT.dict_key) as isparent from SYS_DICT where ROOT_KEY='" + rootkey + "' ) t";
//			
            //sql += "and dict.PARENT_KEY=SYS_DICT.dict_key) as isparent from SYS_DICT where ROOT_KEY='" + rootkey + "' ) t";
            
            
            
//           String sql2="select id, parent_key, text, isparent from (" +
//           		"select id,parentid as parent_key,classname as text,(select count(*) from fais_dict dict where dict.classtype = 'casetype' and dict.parentid = fais_dict.id) as isparent from fais_dict where classtype = 'casetype') t where t.parent_key='13'";

        	String sql="select id, parent_key, text, isparent from (" +
               		"select id,parentid as parent_key,classname as text,(select count(*) from fais_dict dict where dict.classtype = '"+rootkey+"' and " +
               				"dict.parentid = fais_dict.id) as isparent from fais_dict where classtype = '" + rootkey + "') t";
            
            List<FaisTree> selectDt=(List<FaisTree>) dbSupport.getSqlQuery(sql, FaisTree.class, true);
            String val=null;
            if (selectDt.size() > 0)
            {
                val = AddTreeNodeFais(selectDt, "-1", "-1"); //-1
                val = val.substring(0, val.length() - 1);
                val = "[" + val + "]";
            }
            else {
                val = "";
            }
            return val.toLowerCase();
        }
        catch (Exception ex)
        {
            //log.AddLog("E", "GetStudyTree(" + rootkey + "," + dictPy + ")" + ex.getMessage(), "");
            //return null;
        	ex.printStackTrace();
        	return null;
        } 
    }
	
	//递归获取小类 获取足迹树
		private String AddTreeNodeFais(List dt,String pid,String childcount) 
		{
			String str="";
			//pid==rootkey
			List<FaisTree> list=getFaisTreeChild(dt,pid);//获取下级节点
			try {
				int child = 0;
				for (int i = 0; i < list.size(); i++)
				{
					child++;
					if (list.get(i).getISPARENT().toString().equals("0"))//叶节点
					{
						str +=JSONObject.fromObject( list.get(i) )+",";
					}
					else //有子节点
					{
						str +=JSONObject.fromObject( list.get(i) );
						str = str.substring(0, str.length() - 1);
						str += ",\"state\":\"closed\",\"children\":[";
						str += AddTreeNodeFais(dt,list.get(i).getID().toString(), list.get(i).getISPARENT().toString());
						str += "]},";

					}
					if (String.valueOf(child).equals(childcount))//最后一个
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
		
		//是否有子类 获取足迹树
		public List<FaisTree> getFaisTreeChild(List<FaisTree> dt,String pid){
			List<FaisTree> FaisTreeChild=new ArrayList<FaisTree>();
			for(FaisTree tree:dt){
				if(tree.getPARENT_KEY()==null)
					continue;
				if(tree.getPARENT_KEY().toString().equals(pid)){//有没有等于AJLBDM
					FaisTreeChild.add(tree);
				}
			}
			return FaisTreeChild;
		}
		
		//递归获取小类   获取现场勘验树
		private String AddTreeNode(List dt,String pid,String childcount) 
		{
			String str="";
			//pid==rootkey
			List<DetailTree> list=getChild(dt,pid);//获取下级节点
			try {
				int child = 0;
				for (int i = 0; i < list.size(); i++)
				{
					child++;
					if (list.get(i).getISPARENT().toString().equals("0"))//叶节点
					{
						str +=JSONObject.fromObject( list.get(i) )+",";
					}
					else //有子节点
					{
						str +=JSONObject.fromObject( list.get(i) );
						str = str.substring(0, str.length() - 2);
						str += "\",\"state\":\"closed\",\"children\":[";
						str += AddTreeNode(dt,list.get(i).getID(), list.get(i).getISPARENT().toString());
						str += "]},";

					}
					if (String.valueOf(child).equals(childcount))//最后一个
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
		//是否有子类 获取现场勘验树
		public List<DetailTree> getChild(List<DetailTree> dt,String pid){
			List<DetailTree> Child=new ArrayList<DetailTree>();
			for(DetailTree tree:dt){
				if(tree.getPARENT_KEY()==null)
					continue;
				if(tree.getPARENT_KEY().equals(pid)){//有没有等于AJLBDM
					Child.add(tree);
				}
			}
			return Child;
		}
		
		//[点击查看] 根据ID查询公式详情
		@POST
		@Path("/studydata")
		@Produces(MediaType.APPLICATION_JSON)
		public String GetStudyData(@FormParam(value ="studyid")String studyid)
        {
			try
            {
               //String HQL="From analyzeformula where id="+new BigDecimal(studyid);
               Analyzeformula af= (Analyzeformula) dbSupport.GetObjectByClass(new Analyzeformula(), new BigDecimal(studyid));
//	  		   Clob c=af.getJSONTEXT();//得到Clob格式的数据
//	  		   //Clob格式的数据转换成String类型的
//	  		   String cc = c.getSubString(1, (int) c.length());//clob 转 String 
               return af.getJSONTEXT();
            }
            catch (Exception ex)
            {
                log.AddLog("E", "GetStudyData(" + studyid + ")" + ex.getMessage(), "");
                return null;
            }
        }
		
		//[点击查看] 根据ID查询公式详情
		@POST
		@Path("/searchtree")
		//@Produces(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String SearchStudyTree(@FormParam(value ="rootkey")String rootkey,@FormParam(value ="str")String str)
        {
			try
            {
			   String val = "";
			   String sql = "select DICT_KEY from SYS_DICT where ROOT_KEY='" + rootkey + "' and (DICT_VALUE like '%" + str + "%')";
               List<DetailTree> list=(List<DetailTree>) dbSupport.getSqlQuery(sql, DetailTree.class, true);
               
               if (list!= null && list.size()>0)
               {
            	   for(DetailTree dt:list){
            		   val+=dt.getDICT_KEY()+",";
            	   }
            	   val = val.substring(0, val.length() - 1);
               }
	  		   return val;
            }
            catch (Exception ex)
            {
                log.AddLog("E", "SearchStudyTree(" + rootkey + ")" + ex.getMessage(), "");
                return null;
            }
        }
		
		//添加公式
		@POST
		@Path("/addDrop")
		//@Produces(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String addDrop(@FormParam(value ="jsontext")String jsontext,@FormParam(value ="oldstudyname")String oldstudyname,
				@FormParam(value ="studynameDescri")String studynameDescri,@FormParam(value ="userid")String userid)
        {
			try
            {
				Analyzeformula af=new Analyzeformula();
				//String 类型的转换成Clob类型的。
				for(int i=jsontext.length();i<=2005;i++){
					jsontext+=" ";
				}
				af.setJSONTEXT(jsontext);
				af.setFSTATEMENTS("分析语句集合");
				af.setFORMULANAME(oldstudyname);
				af.setFORMULAREMARK(studynameDescri);//公式备注
				af.setFORMULASTATUS(0);
				af.setCREATETIME(DateUtil.getCurrentDatetimeDateTime());
				af.setCREATEUSER(userid);
				//af.setUPDATETIME(DateUtil.getCurrentDatetimeDateTime());
				//af.setUPDATEUSER(userid);//谁登陆，谁修改
				dbSupport.SaveByObject(af);
			  return "true";
            }
            catch (Exception ex)
            {
                log.AddLog("E", "saveDrop" + ex.getMessage(), "");
                return null;
            }
        }
		
		//更新公式
		@POST
		@Path("/saveDrop")
		//@Produces(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String saveDrop(@FormParam(value ="jsontext")String jsontext,@FormParam(value ="oldstudyname")String oldstudyname,
				@FormParam(value ="studynameDescri")String studynameDescri,@FormParam(value ="userid")String userid,
				@FormParam(value ="gongshiID")BigDecimal gongshiID)
        {
			try
            {
				/**
				 {connects:[{"ConnectionId":"con_68","PageSourceId":"state_start781250","PageTargetId":"state_start277939","SourceText":"scene_investigationCASE_TYPEblue-button-bg_ypfx.png090000渎职案案件类别:渎职案","TargetText":"scene_investigationCASE_TYPEblue-button-bg_ypfx.png100000军人违反职责案案件类别:军人违反职责案","SourceAnchor":"RightMiddle","TargetAnchor":"BottomCenter"}],
				 blocks:[{"table":"scene_investigation","clum":"CASE_TYPE","img":"blue-button-bg_ypfx.png","dict_key":"090000","dict_value":"渎职案"},{"BlockId":"state_start781250","BlockContent":"案件类别:渎职案","BlockX":148,"BlockY":130},{"table":"scene_investigation","clum":"CASE_TYPE","img":"blue-button-bg_ypfx.png","dict_key":"100000","dict_value":"军人违反职责案"},{"BlockId":"state_start277939","BlockContent":"案件类别:军人违反职责案","BlockX":665,"BlockY":240}]
				 }
				 */
				//根据id得到该公式
				Analyzeformula af=(Analyzeformula) dbSupport.GetObjectByClass(new Analyzeformula(),gongshiID);
				//String 类型的转换成Clob类型的。
				for(int i=jsontext.length();i<=2005;i++){
					jsontext+=" ";
				}
				af.setJSONTEXT(jsontext);
				af.setFSTATEMENTS("分析语句集合");
				af.setFORMULANAME(oldstudyname);
				af.setFORMULAREMARK(studynameDescri);//公式备注
				//af.setFORMULASTATUS(0);
				//af.setCREATETIME(DateUtil.getCurrentDatetimeDateTime());
				//af.setCREATEUSER("1");
				af.setUPDATETIME(DateUtil.getCurrentDatetimeDateTime());
				af.setUPDATEUSER(userid);//谁登陆，谁修改
				dbSupport.UpDateByObject(af);
			  return "true";
            }
            catch (Exception ex)
            {
                log.AddLog("E", "saveDrop" + ex.getMessage(), "");
                return null;
            }
        }
		
		//任务
		@POST
		@Path("/saveTask")
		//@Produces(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String saveTask(@FormParam(value ="taskname")String taskname,@FormParam(value ="taskremark")String taskremark,
				@FormParam(value ="userid")String userid,@FormParam(value ="gongshiID")String gongshiID)
        {
			try
            {
				//得到任务编号
				String hql="select gettasknum() from dual";
				List list=dbSupport.getSqlQuery(hql, null, false);
				String tasknum=(String) list.get(0);
				Analyzetask atask=new Analyzetask();
				atask.setTASKNAME(taskname);
				atask.setTASKREMARK(taskremark);
				atask.setTASKNUM(tasknum);
				atask.setFORMULAID(Integer.parseInt(gongshiID));
				atask.setTSTATUS(0);
				atask.setCREATETIME(DateUtil.getCurrentDatetimeDateTime());
				atask.setCREATEUSER(userid);
				atask.setENDTIME(DateUtil.getCurrentDatetimeDateTime());
				atask.setRESULTJSON(" ");
				atask.setJSONTEXT(" ");
				//HQL
				dbSupport.SaveByObject(atask);
				
				return "true";
            }
            catch (Exception ex)
            {
                log.AddLog("E", "saveDrop" + ex.getMessage(), "");
                return null;
            }
        }
		
		//获取所有的任务
		@POST
		@Path("/getAnalyzetask")
		@Produces(MediaType.APPLICATION_JSON)
		public String getAnalyzetask(@FormParam(value="begintime") String begintime,
				@FormParam(value="endtime") String endtime,@FormParam(value="page") int page,
				@FormParam(value="rows") int rows,@FormParam(value="userid") String userid)
        {
			int startPage=(page- 1) * rows + 1;
	    	int endPage=page * rows;
	    	
			try
            {
				 String innerSql ="select TASKNUM,TASKNAME,TASKREMARK,(select username from userinfo where userid=CREATEUSER) as CREATEUSERNAME,to_char(createtime,'yyyy-MM-dd hh24:mi:ss') createtime_s,decode(tstatus,0,'未开始',decode(tstatus,1,'执行中',decode(tstatus,2,'已完成'))) as STATUS from Analyzetask where CREATEUSER='"+userid+"'";
				 if (begintime != ""&&begintime.length()>0)
				 {
					 innerSql += " and to_char(createtime, 'yyyy-MM-dd hh24:mi:ss')>='" + begintime + " 00:00:00'";
				 }
				 if (endtime != ""&&endtime.length()>0)
				 {
					 innerSql += " and to_char(createtime, 'yyyy-MM-dd hh24:mi:ss')<='" + endtime + " 23:59:59'";
				 }
				
				//获取总数
				int count=dbSupport.getcount(innerSql);
				innerSql +=" order by createTime desc " ;
				
				//分页
		    	String fenyeSql="select * from (select t.*,rownum rn from ("+innerSql+") t where rownum<="+endPage+") where rn>="+startPage+"";
		    	@SuppressWarnings("rawtypes")
				List list=dbSupport.getSqlQuery(fenyeSql, Analyzetask.class,true);
		    	Map<String, Object> m=new HashMap<String, Object>();
	  		    m.put("total",count);
	  		    m.put("rows", list);
	  		    Gson gson=new Gson();
	  			return gson.toJson(m).toString();
            }
            catch (Exception ex)
            {
                throw new RuntimeException();
            }
        }
		
    //根据任务编号，查出对应的任务（任务编号是唯一的）
	@POST
	@Path("/getTaskByTaskNum")
	@Produces(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.TEXT_PLAIN)
	public String getTaskByTaskNum(@FormParam(value="tasknum") String tasknum)
    {
		try
        {
			String sql ="select TASKNUM,TASKNAME,TASKREMARK,(select username from userinfo where userid=CREATEUSER) as CREATEUSERNAME,to_char(createtime,'yyyy-MM-dd hh24:mi:ss') createtime_s,decode(tstatus,0,'未开始',decode(tstatus,1,'执行中',decode(tstatus,2,'已完成'))) as STATUS from Analyzetask where tasknum='"+tasknum+"'";
			List list=dbSupport.getSqlQuery(sql, Analyzetask.class,true);
			//list.get(0);
			JSONObject object = JSONObject.fromObject(list.get(0));
			String str=object.toString();
  			return str;
        }
        catch (Exception ex)
        {
            throw new RuntimeException();
        }
    }
}
