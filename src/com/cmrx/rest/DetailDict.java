package com.cmrx.rest;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;

import com.cmrx.bean.Entity.DetailDictAddOldapp;
import com.cmrx.bean.Entity.DetailTree;
import com.cmrx.bean.Entity.Detail_ManaRoot;
import com.cmrx.bean.model.DetailDictAddBean;
import com.cmrx.bean.model.DetailDictBean;
import com.cmrx.dao.DBSupport;
import com.cmrx.dao.HibernateSessionUtil;
import com.google.gson.Gson;


@Path("/DetailType")
//小类细分
public class DetailDict {
	public DBSupport dbSupport;
	public Log log;
	public HibernateSessionUtil hs;
	
	public HibernateSessionUtil getHs() {
		return hs;
	}
	public void setHs(HibernateSessionUtil hs) {
		this.hs = hs;
	}
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

	//获取小类细分tree
	@SuppressWarnings("unchecked")
	@POST
	@Path("/tree")
	@Produces(MediaType.TEXT_PLAIN)
	public String Tree(@FormParam(value="rootkey") String rootkey)
	{
		try
		{
			List<DetailTree> dd=GetDetailTree(rootkey);
			
			//递归开始
			String val = AddNode(dd, rootkey, "-1");

			val = val.substring(0, val.length() - 1);
			val = "[" + val + "]";
			return val.toLowerCase();
		}
		catch (Exception ex)
		{
//			log.AddLog("E", "GetDetailTree(" + rootkey + ")" + ex.getMessage(), "");
//			return null;
			ex.printStackTrace();
			return null;
		}
	}

	//递归获取小类
	private String AddNode(List dt,String pid,String childcount) 
	{
		String str="";
		List<DetailTree> list=getChild(dt,pid);
		try {
			int child = 0;
			for (int i = 0; i <list.size(); i++)
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
					str += AddNode(dt,list.get(i).getID(), list.get(i).getISPARENT().toString());
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
	
	//是否有子类
	public List<DetailTree> getChild(List<DetailTree> dt,String pid){
		List<DetailTree> Child=new ArrayList<DetailTree>();
		for(DetailTree tree:dt){
			if(tree.getPARENT_KEY()==null)
				continue;
			if(tree.getPARENT_KEY().equals(pid)){
				Child.add(tree);
			}
		}
		return Child;
	}
	
	//根据查询内容获得小类细分tree
	@SuppressWarnings("unchecked")
	@POST
	@Path("/findtree")
	@Produces(MediaType.TEXT_PLAIN)
	 public String GetDetailByParameter(@FormParam(value="rootkey")String rootkey,@FormParam(value="str") String parameter)
     {
		String val = "";
		//String sql = "select dict_key as dictkey from detail_dict where ROOT_KEY='" + rootkey + "' and (DICT_VALUE1 like '%" + parameter + "%' or DICT_PY like '%" + parameter + "%')";
		String sql = "select DICT_KEY from detail_dict where ROOT_KEY='" + rootkey + "' and (DICT_VALUE1 like '%" + parameter + "%' or DICT_PY like '%" + parameter + "%')";
        
		List<DetailTree> list;
		try {
			list = (List<DetailTree>) dbSupport.getSqlQuery(sql, DetailTree.class, true);
			 if(list!=null&&list.size()>0)
	         {
	        	 for(int i=0;i<list.size();i++){
	        		 val+=list.get(i).getPARENT_KEY()+",";
	        	 }
	             val = val.substring(0, val.length() - 1);
	         }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return val;
     }
	
    // 根据单位id获得本单位最新小类添加申请
	@POST
	@Path("/newapply")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public String GetNewApply(@FormParam(value="organid")String organid, @FormParam(value="datastatus") String datastatus,
    						@FormParam(value="count") int count,@FormParam(value="page") int pageindex,
							@FormParam(value="rows") int rows) 
    {
    	int datacount = count;
    	int startPage=(pageindex- 1) * rows + 1;
    	int endPage=pageindex * rows;
        try
        {
            String sql = "select id,createUser,createUnit,dictVal,dictpy,to_char(createdatetime,'yyyy-MM-dd hh24:mi:ss') createdatetime from detail_dict_add where ";
            StringBuffer sb=new StringBuffer(sql);
            if (datastatus.equals("1"))//分局数据 需要添加单位限制
            {
                sb.append("addStatus=1 and createUnit like '%" + organid + "%'");
            }
            else //总队审核数据 不需要添加单位限制
            {
            	sb.append("addStatus=2");
            }
            if (count ==-1)//第一查询 需查询总数返回 
            {
                datacount = dbSupport.getcount(sb.toString());
            }
            
            sb.append(" order by createdatetime desc  ");
            
            //分页
	    	String fenyeSql="select * from (select t.*,rownum rn from ("+sb.toString()+") t where rownum<="+endPage+") where rn>="+startPage+"";
	    	
            List<DetailDictAddOldapp> list=  (List<DetailDictAddOldapp>) dbSupport.getSqlQuery(fenyeSql, DetailDictAddOldapp.class, true);
            
            Map<String, Object> m=new HashMap<String, Object>();
			m.put("total",datacount);
			m.put("rows", list);
			Gson gson=new Gson();
			return gson.toJson(m).toString();
        }
        catch (Exception ex)
        {
            log.AddLog("E", "GetNewApply(" + organid + "," + datastatus + "," + pageindex + "," + rows + ")" + ex.getMessage(), "");
            return null;
        }
    }
	
	// 根据单位id获得本单位历史小类添加申请
    //<param name="datastatus">1分局 2总队</param>
	@POST
	@Path("/oldapply")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public String GetOldApply(@FormParam(value="organid")String organid, @FormParam(value="datastatus") String datastatus,
    						@FormParam(value="count") int count,@FormParam(value="page") int pageindex,
							@FormParam(value="rows") int rows,@FormParam(value="endtime") String endtime,
							@FormParam(value="begintime") String begintime,@FormParam(value="addstatus") String addstatus) 
    {
    	int datacount = count;
    	int startPage=(pageindex- 1) * rows + 1;
    	int endPage=pageindex * rows;
    	try
        {
            String sql = "select id,createUser,createUnit,dictVal,addstatus,to_char(createdatetime,'yyyy-MM-dd hh24:mi:ss') createdatetime from detail_dict_add where (" + addstatus + ") ";
            StringBuffer sb=new StringBuffer(sql);
            if (datastatus.equals("1"))//分局数据 需要添加单位限制
            {
                sb.append("and createUnit like '%" + organid + "%'");
            }
            else //总队审核数据 不需要添加单位限制
            {
            	sb.append("and manager2 !=''");
            }
            
            if (begintime != ""&&begintime.length()>0)
            {
            	sb.append(" and to_char(createdatetime,'yyyy-MM-dd hh24:mi:ss')>='" + begintime + " 00:00:00'");
            }
            if (endtime != ""&&endtime.length()>0)
            {
            	sb.append(" and to_char(createdatetime,'yyyy-MM-dd hh24:mi:ss')<='" + endtime + " 23:59:59'");
            }
            if (count ==-1)//第一查询 需查询总数返回 
            {
                datacount = dbSupport.getcount(sb.toString());
            }
            sb.append(" order by createdatetime desc ");
            
            //分页
	    	String fenyeSql="select * from (select t.*,rownum rn from ("+sb.toString()+") t where rownum<="+endPage+") where rn>="+startPage+"";
            List<DetailDictAddOldapp> list=  (List<DetailDictAddOldapp>) dbSupport.getSqlQuery(fenyeSql, DetailDictAddOldapp.class, true);
            
            Map<String, Object> m=new HashMap<String, Object>();
			m.put("total",datacount);
			m.put("rows", list);
			Gson gson=new Gson();
			return gson.toJson(m).toString();
        }
        catch (Exception ex)
        {
            log.AddLog("E", "GetOldApply(" + organid + "," + datastatus + "," + pageindex + "," + rows + "," + begintime + "," + endtime + "," + addstatus + ")" + ex.getMessage(), "");
            return null;
        }
    }
	
	//最新申请：审核
    //根据审批表id获取小类tree
	@POST
	@Path("/applytree")
	@Produces(MediaType.TEXT_PLAIN)
	public String GetDetailTreeById(@FormParam(value="id")Long id) 
    {
		//根据小类审批id获取申请rootid
		List<Detail_ManaRoot> dt = GetManageRootId(id);
       
        String rootkey = dt.get(0).getROOTKEY();
        
        //根据该类型所有字典值
        List<DetailTree> selectDt =GetDetailTree(rootkey);
        
        String val = AddNode(selectDt, rootkey, "-1");
        val = val.substring(0, val.length() - 1);
        val =dt.get(0).getPARENTKEY().toString()+"$$##[" + val + "]";//打开节点$$##tree数据
        return val;
    }
	
    // 拒绝小类添加申请
	@POST
	@Path("/applyrefuse")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public String RefuseById(@FormParam(value="id")Long id, @FormParam(value="context")String txt, 
			@FormParam(value="userid")String userid, @FormParam(value="truename")String truename, 
			@FormParam(value="datastatus")String datastatus) throws UnsupportedEncodingException
	{
		try
		{
			DetailDictAddBean dda=(DetailDictAddBean) dbSupport.GetObjectByClass(new DetailDictAddBean(),id);
			if (datastatus.equals("1"))//分局管理员
			{
				dda.setManager1(truename);
				dda.setManager1Datetime(new Date());
				dda.setAddStatus(4);
				dda.setResultContext(txt);
			}
			else //总队管理员
			{
				dda.setManager2(truename);
				dda.setManager2Datetime(new Date());
				dda.setAddStatus(4);
				dda.setResultContext(txt);
			}

			//更新对象.
			dbSupport.UpDateByObject(dda);
			log.AddLog("U", "拒绝小类申请", userid);
			return "true";
		}
		catch (Exception ex)
		{
			log.AddLog("E", "RefuseById(" + id + "," + txt + "," + userid + "," + truename + "," + datastatus + ")" + ex.getMessage(), userid);
			return "false";
		}
	}
	
    // 分局管理员同意小类申请
	@POST
	@Path("/apply803")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public String Agree803ById(@FormParam(value="id")Long id,@FormParam(value="userid") String userid, 
    							@FormParam(value="truename")String truename)
    {
        try
        {
            //使用HQL映射更新
        	DetailDictAddBean dda=(DetailDictAddBean) dbSupport.GetObjectByClass(new DetailDictAddBean(),id);
        	dda.setAddStatus(2);
        	dda.setManager1(truename);
        	dda.setManager1Datetime(new Date());
        	dbSupport.UpDateByObject(dda);
            log.AddLog("U", "分局管理员同意小类申请", userid);
            return "true";
        }
        catch (Exception ex)
        {
            log.AddLog("E", "Agree803ById(" + id + "," + userid + ")" + ex.getMessage(), userid);
            return "false";
        }
    }
	
	
	
	//测试
//	@GET
//	@Path("/applyagree11")
//	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//	public String AgreeById()
//	{
//			hs.beginTransaction();
//			String sql = "update LogBean set logTime='2015-04-08 15:01:01' where logId=2538";
//			hs.currentSession().createQuery(sql).executeUpdate();
//			hs.commitTransaction();
//			hs.closeSession();
//			
//			return null;
//		}
//	}
	
	//测试
//	@GET
//	@Path("/applyagree11")
//	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//	public String AgreeById() throws Exception
//	{
//			hs.beginTransaction();
//			LogBean lb=new LogBean();
//			lb.setLogContext("aa");
//			lb.setLogTime(new Date());
//			lb.setLogType("w");
//			lb.setLogUser("2");
//			dbSupport.SaveByObject(lb);
//			hs.commitTransaction();
//			hs.closeSession();
//			
//			return null;
//	}
	
	
	
	//总队的
    //同意小类添加
	@POST
	@Path("/applyagree")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public String AgreeById(@FormParam(value="id")Long id, @FormParam(value="context")String txt, 
			@FormParam(value="userid")String userid, @FormParam(value="truename")String truename, 
			@FormParam(value="py")String py)
	{
		String sql = "";
		try
		{
			hs.beginTransaction();//开启事务

			//修改审批表
			 sql = "update DetailDictAddBean set manager2='" + truename + "',manager2Datetime=now(),addstatus=3,dictpy='" + py + "',descript='" + txt + "' where id=" + id;//修改审批表
             hs.currentSession().createQuery(sql).executeUpdate();
			
             //
             DetailDictAddBean dda=(DetailDictAddBean) dbSupport.GetObjectByClass(new DetailDictAddBean(),id);
             
			//修改父节点状态 说明存在子节点
			sql = "update DetailDictBean set leaf_flag='" + 0 + "' where root_key='" + dda.getRootKey() + "' and dict_key='" + dda.getParentKey() + "'";
			
			//更新---------->HQL
			hs.currentSession().createQuery(sql).executeUpdate();
			
			//查询该节点下已有节点数
			sql = "select max(DICT_SORT) from detail_dict  where root_key='" + dda.getRootKey() + "' and parent_key='" + dda.getParentKey() + "'";
			
			//----查询sql
			int currentcount = dbSupport.getcount2(sql);
			int ordercount = 0;
			if (currentcount ==0)
			{
				ordercount = 1;
			}
			else
			{
				ordercount = currentcount + 1;
			}

			sql = "select dict_level from  detail_dict  where root_key='" +dda.getRootKey() + "' and dict_key='" + dda.getParentKey() + "'";//父节点dict_level
			
			//---->sql查询
			int currentlevel =Integer.parseInt(dbSupport.getSqlQuery2(sql, null, false).get(0).toString()) ;
			
			String level =String.valueOf((currentlevel+1));
			
			DetailDictBean ddb=new DetailDictBean();
			ddb.setId(UUID.randomUUID().toString());
			ddb.setDict_level(level);
			ddb.setDict_key(dda.getDictkey());
			ddb.setParent_key(dda.getParentKey());
			ddb.setRoot_key(dda.getRootKey());
			ddb.setDict_value1(dda.getDictVal());
			ddb.setLeaf_flag("1");
			ddb.setDownload_flag("1");
			ddb.setReadonly_flag("0");
			ddb.setDict_sort(new BigDecimal(ordercount));
			ddb.setDict_py(dda.getDictPY());
			ddb.setOpen_flag("1");
			ddb.setCreate_user("XLXF");
			ddb.setCreate_datetime(new Date());
			ddb.setUpdate_user("XLXF");
			ddb.setUpdate_datetime(new Date());
			ddb.setIsdetail(Byte.parseByte("1"));
			ddb.setDescript(dda.getDescript());
			
			//保存【对象】---->HQL保存
			dbSupport.SaveByObjectTransaction(ddb);

			hs.commitTransaction();
			
			return "true";
		}
		catch (Exception ex)
		{
			hs.rollbackTransaction();
			return "false";
		}finally{
			hs.closeSession();
		}
	}

	// 根据该类型所有字典值
    public List GetDetailTree(String rootkey)
    {
        try
        {
			String sql = "select id ,parent_key,text,isparent,isdetail,descript from(" +
					"select dict_key as id,parent_key,DICT_VALUE1 as text,(select count(*) from detail_dict dict where dict.ROOT_KEY='" + rootkey + "' and dict.PARENT_KEY=detail_dict.dict_key) as isparent,isdetail,descript from detail_dict where ROOT_KEY='" + rootkey + "' order by DICT_SORT) t";
			List<DetailTree> dd= (List<DetailTree>) dbSupport.getSqlQuery(sql, DetailTree.class, true); 
			return dd;
        }
        catch (Exception ex)
        {
            log.AddLog("E", "GetDetailTree(" + rootkey + ")" + ex.getMessage(), "");
            return null;
        }
    }
    
	//根据小类审批id获取申请rootid
	 public List<Detail_ManaRoot> GetManageRootId(Long id)
     {
         try
         {
         	String sql = "select rootkey,parentkey from detail_dict_add where id=" + id;
             return (List<Detail_ManaRoot>) dbSupport.getSqlQuery(sql, Detail_ManaRoot.class, true);
         }
         catch (Exception ex)
         {
             log.AddLog("E", "GetManageRootId(" + id + ")" + ex.getMessage(), "");
             return null;
         }
     }
	 
	 
	 // 根据id获得小类审批全部信息
	 @POST
	 @Path("/getapply")
	 @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	 public String GetApplyById(@FormParam(value="id")Long id)
	 {
		 try
		 {	String val="";
		 //根据主键id得到
		 DetailDictAddBean dt = (DetailDictAddBean) dbSupport.GetObjectByClass(new DetailDictAddBean(), id);

		 Map<String, Object> m=new HashMap<String, Object>();
		 if (dt != null && dt.getManager2() != "") //总队确认
		 {
			 m.put("total","3");
		 }
		 else 
		 {
			 m.put("total","2");
		 }
		 m.put("rows", dt);
		 Gson gson=new Gson();
		 val=gson.toJson(m).toString();
		 
		 return val;
		 }
		 catch (Exception ex)
		 {
			 log.AddLog("E", "GetApplyById(" + id + ")" + ex.getMessage(), "");
			 return null;
		 }
	 }
	    
	 

}
