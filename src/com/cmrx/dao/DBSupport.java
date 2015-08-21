package com.cmrx.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import com.cmrx.bean.model.UserorgBean;

/**
 * 数据库操作类
 * 
 * @author Portungkeat
 * 
 */
public class DBSupport {
	public static String dbType_FOMS="FOMS";
	public static String dbType_Mysql="MYSQL";

	private HibernateSessionUtil HibernateSessionUtil;

	public HibernateSessionUtil getHibernateSessionUtil() {
		return HibernateSessionUtil;
	}

	public void setHibernateSessionUtil(HibernateSessionUtil hibernateSessionUtil) {
		HibernateSessionUtil = hibernateSessionUtil;
	}
	
	public void initDB(){
		
	}

	// -------------------数据库查询方法集合---------------------

	/**
	 * 通过HQL查询语句查询数据
	 * 
	 * @param HQL
	 *            HQL语句字符串
	 * @return 查询所得的数据集对象
	 * @throws Exception
	 */
	public List<?> GetQueryByHql(String HQL) throws Exception {
		try {
			Query query = HibernateSessionUtil.currentSession().createQuery(HQL);
			return query.list();
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionUtil.closeSession();
		}
	}
	
	/*
	 * 根据用户名和密码获得该用户(得到唯一的用户..只能是唯一)
	 */
	public UserorgBean GetUserorg(String name,String pwd) throws Exception {
		try {
			String HQL="From UserorgBean where username=? and pwd=?";
			Query query = HibernateSessionUtil.currentSession().createQuery(HQL);
			query.setString(0, name);
			query.setString(1, pwd);
			return (UserorgBean) query.uniqueResult();
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionUtil.closeSession();
		}
	}

	/**
	 * 通过HQL查询语句查询数据
	 * 
	 * @param HQL
	 *            HQL语句字符串
	 * @return 查询所得的数据集对象
	 * @throws Exception
	 */
	public List<?> GetQueryByHql(String HQL, int offset, int length) throws Exception {
		try {
			Query query = HibernateSessionUtil.currentSession().createQuery(HQL);
			query.setFirstResult(offset);
			query.setMaxResults(length);
			return query.list();
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionUtil.closeSession();
		}
	}

	/**
	 * 通过SQL查询语句查询数据
	 * session关闭
	 * @param SQL
	 *            SQL语句字符串
	 * @return 查询所得的数据集对象
	 * @throws Exception
	 */
	public List<?> getSqlQuery(String SQL, Class clas,boolean isTrans) throws Exception {
		try {
			SQLQuery sqlQuery = HibernateSessionUtil.currentSession().createSQLQuery(SQL);
			if (isTrans) {
				//((Object[])(sqlQuery.list().get(0)))[0].getClass().getDeclaredFields()[0];//看字段的类型，以便对应java的类型
				List list= sqlQuery.setResultTransformer(Transformers.aliasToBean(clas)).list();
				return list;
			} else {
				return sqlQuery.list();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionUtil.closeSession();
		}
	}

	
	/**
	 * 通过SQL查询语句查询数据
	 * session没有关闭
	 * @param SQL
	 *            SQL语句字符串
	 * @return 查询所得的数据集对象
	 * @throws Exception
	 */
	public List<?> getSqlQuery2(String SQL, Class clas,boolean isTrans) throws Exception {
		try {
			SQLQuery sqlQuery = HibernateSessionUtil.currentSession().createSQLQuery(SQL);
			if (isTrans) {
				List list= sqlQuery.setResultTransformer(Transformers.aliasToBean(clas)).list();
				return list;
			} else {
				return sqlQuery.list();
			}
		} catch (Exception e) {
			throw e;
		} 
	}



	/**
	 * 通过实体对象以及主键ID获取该ID对应的数据对象
	 * 
	 * @param obj
	 *            实体对象
	 * @param id
	 *            主键
	 * @return 单行数据对象
	 * @throws Exception
	 */
	public Object GetObjectByClass(Object obj, Serializable id) throws Exception {
		Object object = HibernateSessionUtil.currentSession().get(obj.getClass(), id);
		return object;
	}

	// -------------------数据库保存方法集合---------------------

	public void SaveByObject(Object obj) throws Exception {
		try {
			HibernateSessionUtil.beginTransaction();
			HibernateSessionUtil.currentSession().save(obj);
			HibernateSessionUtil.commitTransaction();
		} catch (Exception e) {
			HibernateSessionUtil.rollbackTransaction();
			throw e;
		}
	}

	public void SaveByObjectTransaction(Object obj) throws Exception {
		try {
			HibernateSessionUtil.currentSession().save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	public void SaveByObjectList(List<Object> objList) throws Exception {
		try {
			HibernateSessionUtil.beginTransaction();
			for (Object obj : objList)
				HibernateSessionUtil.currentSession().save(obj);
			HibernateSessionUtil.commitTransaction();
		} catch (Exception e) {
			HibernateSessionUtil.rollbackTransaction();
			throw e;
		}
	}

	public void SaveByObjectListTransaction(List<Object> objList) throws Exception {
		try {
			for (Object obj : objList)
				HibernateSessionUtil.currentSession().save(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	// -------------------数据库更新方法集合---------------------
	public void UpDateByObject(Object obj) throws Exception {
		try {
			HibernateSessionUtil.beginTransaction();
			HibernateSessionUtil.currentSession().update(obj);
			HibernateSessionUtil.commitTransaction();
		} catch (Exception e) {
			HibernateSessionUtil.rollbackTransaction();
			throw e;
		}
	}

	public void UpDateByObjectTransaction(Object obj) throws Exception {
		try {
			HibernateSessionUtil.currentSession().update(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	public void UpDateByObjectList(List<Object> objList) throws Exception {
		try {
			HibernateSessionUtil.beginTransaction();
			for (Object obj : objList)
				HibernateSessionUtil.currentSession().update(obj);
			HibernateSessionUtil.commitTransaction();
		} catch (Exception e) {
			HibernateSessionUtil.rollbackTransaction();
			throw e;
		}
	}

	public void UpDateByObjectListTransaction(List<Object> objList) throws Exception {
		try {
			for (Object obj : objList)
				HibernateSessionUtil.currentSession().update(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	// -------------------数据库删除方法集合---------------------

	public void DelByHQL(String HQL) throws Exception {
		try {
			HibernateSessionUtil.beginTransaction();
			HibernateSessionUtil.currentSession().createQuery(HQL).executeUpdate();
			HibernateSessionUtil.commitTransaction();
		} catch (Exception e) {
			HibernateSessionUtil.rollbackTransaction();
			throw e;
		}
	}

	public void DelByHQLTransaction(String HQL) throws Exception {
		try {
			HibernateSessionUtil.currentSession().createQuery(HQL).executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	public void DelByObject(Object obj) throws Exception {
		try {
			HibernateSessionUtil.beginTransaction();
			HibernateSessionUtil.currentSession().delete(obj);
			HibernateSessionUtil.commitTransaction();
		} catch (Exception e) {
			HibernateSessionUtil.rollbackTransaction();
			throw e;
		}
	}

	public void DelByObjectTransaction(Object obj) throws Exception {
		try {
			HibernateSessionUtil.currentSession().delete(obj);
		} catch (Exception e) {
			throw e;
		}
	}

	public void DelByObjectList(List<Object> objList) throws Exception {
		try {
			HibernateSessionUtil.beginTransaction();
			for (Object obj : objList)
				HibernateSessionUtil.currentSession().delete(obj);
			HibernateSessionUtil.commitTransaction();
		} catch (Exception e) {
			HibernateSessionUtil.rollbackTransaction();
			throw e;
		}
	}

	public void DelByObjectListTransaction(List<Object> objList) throws Exception {
		try {
			for (Object obj : objList)
				HibernateSessionUtil.currentSession().delete(obj);
		} catch (Exception e) {
			throw e;
		}
	}
	
	//把sql作为子表，然后再查询对应的总数。
	 public int getcount(String sql){
    	List list=null;
    	String sqlCount="select count(*) from ("+sql+") aa " ;
		try {
			list = getSqlQuery(sqlCount, null, false);
           if(list.get(0)==null)
           	  return 0;
           else
               return Integer.parseInt(list.get(0).toString());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	 
	//根据sql查询对应的总数或者max。
	 public int getcount2(String sql){
    	List list=null;
		try {
			list = getSqlQuery2(sql, null, false);
           if(list.get(0)==null)
           	  return 0;
           else
               return Integer.parseInt(list.get(0).toString());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
