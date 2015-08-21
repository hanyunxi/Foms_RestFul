package com.cmrx.rest;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cmrx.bean.Entity.UserRoleOrgan;
import com.cmrx.bean.model.OrganBean;
import com.cmrx.bean.model.RoleAuthBean;
import com.cmrx.bean.model.Role_authority;
import com.cmrx.bean.model.UserInfoBean;
import com.cmrx.dao.DBSupport;
import com.google.gson.Gson;

@Path("/Manager")
public class Manager extends IResponse {
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

	//用户是否有权限
	@SuppressWarnings("unchecked")
	@POST
	@Path("/authorityExist")
	@Produces(MediaType.TEXT_PLAIN)
	public String AuthorityExist(@FormParam("userid") String userid,
			@FormParam("authority") String authority)
			throws UnsupportedEncodingException {
		String val = "";

		 try
	        {
	            String sql = "select authority from role_authority where roleId=(select roleid from userinfo where userid=" + userid + ")";
	            List<Role_authority> list=(List<Role_authority>) dbSupport.getSqlQuery(sql, Role_authority.class, true);
	            if (list == null || list.size() == 0){
	                val = "false";
	            }
	            else 
	            {
	                if (list.get(0).getAuthority().indexOf(authority.trim()) != -1)//存在
	                    val = "true";
	                else
	                    val = "false";
	            }
	            return val;
	        }
	        catch (Exception ex)
	        {
	            log.AddLog("E", "AuthorityExist("+userid+")" + ex.getMessage(), "");
	            return null;
	        }
    }
	
	 /// 根据用户名称判断该用户名是否已存在
		@POST
		@Path("/userexist")
		@Produces(MediaType.TEXT_PLAIN)
	    public String UserExist(@FormParam("name")String name)
	    {
	    	String val = "";
	    	String sql = "select count(*) from userinfo where username='" + name + "'";
	        int count = dbSupport.getcount2(sql);
	        if (count == 0)
	        {
	            val = "0";
	        }
	        else if (count > 0)
	        {
	            val = "1";
	        }
	        else //报错
	        {
	            val = "-1";
	        }
	        return val;
	    }
		
	    //删除角色
		@POST
		@Path("/roledel")
		@Produces(MediaType.TEXT_PLAIN)
	    public String RoleDelete(@FormParam("id")int id, @FormParam("userid")int userid) 
	    {
			 String sql = "";
	         String val = "";
	         try
	         {
	             sql = "select count(*) from userinfo where roleid=" + id;
	             int count =dbSupport.getcount2(sql);
	             if (count == 0)//没有用户采用该角色 可以删除
	             {
	                 dbSupport.DelByObject(dbSupport.GetObjectByClass(new RoleAuthBean(),id));
	                 log.AddLog("D", "删除角色",userid+"");
	                 val = "true";
	             }
	             else //有用户在使用该角色不可删除
	             {
	                 val = "false";
	             }
	         }
	         catch (Exception ex)
	         {
	             val = "error";
	             log.AddLog("E", "DeleteRole(" + id + "," + userid + ")" + ex.getMessage(), "");
	         }
	         return val;
	        
	    }

		
		/// 根据用户名称判断该角色名是否已存在
		@POST
		@Path("/roleexist")
		@Produces(MediaType.TEXT_PLAIN)
		public String RoleExist(@FormParam("name")String name)
		{
			String val = "";
			String sql = "select count(*) from role_authority where rolename='" + name + "'";
			int count = dbSupport.getcount2(sql);
			if (count == 0)
			{
				val = "0";
			}
			else if (count > 0)
			{
				val = "1";
			}
			else //报错
			{
				val = "-1";
			}
			return val;
		}


	 /// 根据角色id修改权限内容
		@POST
		@Path("/changeauthority")
		@Produces(MediaType.TEXT_PLAIN)
	    public String  ChangeAuthority(@FormParam("id")int id, 
							    		@FormParam("userid")int userid,
							    		@FormParam("authority")String authority)
	    {
	    	try
	        {
	    		RoleAuthBean rb=(RoleAuthBean) dbSupport.GetObjectByClass(new RoleAuthBean(),id);
	    		rb.setAuthority(authority);
	    		log.AddLog("U", "修改角色权限", userid+"");
	            return "true";
	        }
	        catch (Exception ex)
	        {
	            log.AddLog("E", "ChangeAuthority("+id+","+authority+","+userid+")" + ex.getMessage(), "");
	            return "false";
	        } 
	    }
	


	    /// 添加角色
		@POST
		@Path("/roleadd")
		@Produces(MediaType.TEXT_PLAIN)
	    public String RoleAdd(@FormParam("name")String name, @FormParam("userid")int userid) 
	    {
			 try
	         {
				 //oracle自增长
	             RoleAuthBean rb=new RoleAuthBean();
	             rb.setRoleName(name);
	             rb.setAuthority(" ");//必须设置否则页面点击不行
	             dbSupport.SaveByObject(rb);
	             log.AddLog("C", "角色添加", userid+"");
	             return "true";
	         }
	         catch (Exception ex)
	         {
	             log.AddLog("E", "RoleAdd(" + name + ","+userid+")" + ex.getMessage(), "");
	             return "false";
	         }
	    }
		
	    /// 查询角色列表
		@POST
		@Path("/getrole")
		@Produces(MediaType.APPLICATION_JSON)
	    public String GetRole() 
	    {
	    	 try
	         {
	             //String sql = "select * from  role_authority";
	    		String hql = "From RoleAuthBean";
	    		List list= dbSupport.GetQueryByHql(hql);
	             
	            Map<String, Object> m=new HashMap<String, Object>();
	 		    m.put("total",list.size());
	 		    m.put("rows", list);
	 		    Gson gson=new Gson();
	 			return gson.toJson(m).toString();
	         }
	         catch (Exception ex)
	         {
	             log.AddLog("E", "GetRole" + ex.getMessage(), "");
	             return null;
	         }
	    }
		
		///获取机构列表
		@POST
		@Path("/getorgan")
		@Produces(MediaType.APPLICATION_JSON)
		 public String GetOrgan() 
	     {
			  try
	          {
				  String sql = "select organid,organname from organ";
				  List<OrganBean> list= (List<OrganBean>) dbSupport.getSqlQuery(sql, OrganBean.class, true);
				  Map<String, Object> m=new HashMap<String, Object>();
				  m.put("total",list.size());//这个需要总数的
				  m.put("rows", list);
				  Gson gson=new Gson();

	  			  return gson.toJson(m).toString();
	          }
	          catch (Exception ex)
	          {
	              log.AddLog("E", "GetOrgan" + ex.getMessage(), "");
	              return null;
	          }
	     }
		
		//用户列表
		@POST
		@Path("/getuser")
		@Produces(MediaType.APPLICATION_JSON)
	    public String GetUser(@FormParam("truename")String truename, @FormParam("organid")int organid,
					    		@FormParam("count")int count,@FormParam("page")int pageindex,
					    		@FormParam("rows")int rows)
	    {
	    	int datacount=count;
	    	int endPage=pageindex * rows;
	    	int startPage=(pageindex- 1) * rows + 1;
	    	try
	         {
	    		/*
	    		String sql = "select userid,pwd,usercard,username,truename,rolename,organname from userinfo" +
                " left join role_authority on userinfo.roleId=role_authority.roleid " +
                " left join organ on userinfo.organId=organ.organId where 1=1 ";
	    		*/
	    		String innerSql="select userid,pwd,usercard,username,truename,rolename,organname from userinfo left join role_authority on userinfo.roleId=role_authority.roleid left join organ on userinfo.organId=organ.organId where 1=1 ";
	    		
	    		 if (truename != ""&&!truename.equals(""))
	             {
	    			 innerSql += " and (truename like '%" + truename + "%' or username like '%" + truename + "%')";
	             }
	             if (organid != 0)
	             {
	            	 innerSql += " and userinfo.organId=" + organid;
	             }
	             
	             if (count == -1)
	             {
	            	 datacount=dbSupport.getcount(innerSql);
	             }
	             
	             //分页
	    		String fenyeSql="select * from (select t.*,rownum rn from ("+innerSql+") t where rownum<="+endPage+") where rn>="+startPage+"";
	            
	             
	             //sql += " LIMIT " + (pageindex - 1)*rows + "," + rows;
	             @SuppressWarnings("rawtypes")
				List list= dbSupport.getSqlQuery(fenyeSql, UserRoleOrgan.class, true);
	             
	            Map<String, Object> m=new HashMap<String, Object>();
	  		    m.put("total",datacount);
	  		    m.put("rows", list);
	  		    Gson gson=new Gson();
	  			return gson.toJson(m).toString();
	         }
	         catch (Exception ex)
	         {
	        	 log.AddLog("E", "GetUser(" + truename + ","+organid+","+pageindex+","+rows+")" + ex.getMessage(), "");
	             return null;
	         }
	    }

		
		//修改用户信息
		@POST
		@Path("/useredit")
		@Produces(MediaType.TEXT_PLAIN)
		public String UserEdit(@FormParam("id")int id, @FormParam("pwd")String pwd,
					    		@FormParam("usercard")String usercard,@FormParam("truename")String truename,
					    		@FormParam("roleid")int roleid,@FormParam("organid")int organid,
					    		@FormParam("userid")int userid) 
		{
            try
            {
            	//HQL操作根据id得等到对象
            	UserInfoBean ui= (UserInfoBean) dbSupport.GetObjectByClass(new UserInfoBean(),id);
            	ui.setPwd(pwd);
            	ui.setTrueName(truename);
            	ui.setUserCard(usercard);
            	ui.setRoleId(roleid);
            	ui.setOrganId(organid);
            	//HQL更新
            	dbSupport.UpDateByObject(ui);


            	log.AddLog("U", "修改用户信息" + id, userid+"");

            	return "true";
            }
            catch (Exception ex)
            {
                log.AddLog("E", "UserEdit(" + id + "," + pwd + "," + truename + "," + roleid + "," + organid + ","+userid+")" + ex.getMessage(), "");
                return "false";
            }
		}
		
		//添加用户
		@POST
		@Path("/useradd")
		@Produces(MediaType.TEXT_PLAIN)
		public String UserAdd(@FormParam("name")String name, @FormParam("pwd")String pwd,
					    		@FormParam("usercard")String usercard,@FormParam("truename")String truename,
					    		@FormParam("roleid")int roleid,@FormParam("organid")int organid,
					    		@FormParam("userid")int userid)
		{
			try
			{
				UserInfoBean uib=new UserInfoBean();
				uib.setUserName(name);
				uib.setPwd(pwd);
				uib.setTrueName(truename);
				uib.setUserCard(usercard);
				uib.setRoleId(roleid);
				uib.setOrganId(organid);
				dbSupport.SaveByObject(uib);
				
				log.AddLog("C", "添加用户"+truename,userid+"");
				return "true";
			}
			catch (Exception ex)
			{
				log.AddLog("E", "UserAdd(" + name + ","+pwd+","+truename+","+roleid+","+organid+")" + ex.getMessage(), "");
				return "false";
			}
		}
		
		//删除用户
		@POST
		@Path("/userdel")
		@Produces(MediaType.TEXT_PLAIN)
		public String DeleteUser(@FormParam("id")int id,@FormParam("userid") String userid) 
		{
			try
			{
				UserInfoBean uib=(UserInfoBean) dbSupport.GetObjectByClass(new UserInfoBean(), id);
				dbSupport.DelByObject(uib);
				log.AddLog("D", "删除用户" + id, userid);
				return "true";
			}
			catch (Exception ex)
			{
				log.AddLog("E", "DeleteUser(" + id + ","  + userid + ")" + ex.getMessage(), "");
				return "false";
			}
		}
}


