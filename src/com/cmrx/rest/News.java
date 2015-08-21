package com.cmrx.rest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;

import com.cmrx.bean.SqlEnum;
import com.cmrx.bean.Entity.NewsListByType;
import com.cmrx.bean.Entity.News_user;
import com.cmrx.bean.Entity.SelNews;
import com.cmrx.bean.model.NewsBean;
import com.cmrx.dao.DBSupport;
import com.cmrx.dao.DateUtil;
import com.google.gson.Gson;

@Path("/News")
public class News {
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

	//首页展示的6条数据
	@POST
	@Path("/newsindexshow")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SelNews> Newsindexshow() {
		String sql = SqlEnum.SelNewsIndex;
		try {
			List<SelNews> list = (List<SelNews>) dbSupport.getSqlQuery(sql,SelNews.class, true);
			return list;
		} catch (Exception ex) {
			log.AddLog("E", "GetNewsIndexShow()" + ex.getMessage(), "");
			return null;
		}
	}

	//news.hmt中的
	@SuppressWarnings("unchecked")
	@POST
	@Path("/newsinformation")
	@Produces(MediaType.APPLICATION_JSON)
	public List<News_user> Newsinformation(
			@FormParam(value = "newsid") BigDecimal newsid) {
		List<News_user> list = null;
		try {
			String sql = "select newsId,newsTitle,newsContent,newstype,truename,to_char(news.createDate, 'yyyy-MM-dd hh24:mi:ss') createDate from news LEFT JOIN userinfo on news.createuserid=userinfo.userId where newsId=" + newsid;
			list = ((List<News_user>) dbSupport.getSqlQuery(sql,
					News_user.class, true));

		} catch (Exception ex) {
			ex.printStackTrace();
			// Log.AddLog("E", "NewsInformation(" + newsid + ")" +
			// ex.getMessage(), "");
			// return null;
		}
		return list;
	}
	//news.htm中,,通知通报或者最新新闻  共用这个方法
	//查出当前通报的前一条和后一条，总共是3条
	@SuppressWarnings("unchecked")
	@POST
	@Path("/newsbeforenext")
	@Produces(MediaType.APPLICATION_JSON)
	public String Newsbeforenext(
			@FormParam(value = "newsid") BigDecimal newsid,
			@FormParam(value = "newstype") String newstype) {
		try {
			Map map=new HashMap();
    		String sqlBefore=" select z.p from (select y.newsid,y.newstype,lag(y.newsid, 1, 0) over(order by y.createdate) as p from news y where newsType = '"+newstype+"') z where newsid ="+newsid;
    		String sqlAfter=" select z.p from (select y.newsid,y.newstype,lead(y.newsid, 1, 0) over(order by y.createdate) as p from news y where newsType = '"+newstype+"') z where newsid = "+newsid;
    		List<News_user> beforeUser= (List<News_user>) dbSupport.getSqlQuery(sqlBefore,News_user.class, true);
    		List<News_user> afterUser= (List<News_user>) dbSupport.getSqlQuery(sqlAfter,News_user.class, true);
    		BigDecimal beforeID=beforeUser.get(0).getP();
    		BigDecimal afterID=afterUser.get(0).getP();
    		//上一篇
    		if(beforeID.intValue()!=0){
    			String sql="SELECT newsId,newsTitle FROM news WHERE newsId="+beforeID.intValue();
    			News_user bUser=(News_user) dbSupport.getSqlQuery(sql, News_user.class,true).get(0);
    			map.put("beforeid", bUser.getNEWSID());
        		map.put("beforetitle",bUser.getNEWSTITLE());
    		}
    		//下一篇
    		if(afterID.intValue()!=0){
    			String sql="SELECT newsId,newsTitle FROM news WHERE newsId="+afterID.intValue();
    			News_user aUser=(News_user) dbSupport.getSqlQuery(sql, News_user.class,true).get(0);
    			map.put("nextid", aUser.getNEWSID());
        		map.put("nexttitle",aUser.getNEWSTITLE());
    		}
    		
    		JSONObject jsonObject = JSONObject.fromObject(map); 
    		return jsonObject.toString();
		} catch (Exception ex) {
			log.AddLog("E", "NewsInformationBefore(" + newsid + "," + newstype
					+ ")" + ex.getMessage(), "");
			return null;
		}
	}

	//newsList.htm中loadnewsTable
	@SuppressWarnings("unchecked")
	@POST
	@Path("/newsListByType")
	@Produces(MediaType.APPLICATION_JSON)
	public String GetNewsListByType(
			@FormParam(value = "newstype") String newstype,
			@FormParam(value = "count") int count,
			@FormParam(value = "page") int page,
			@FormParam(value = "rows") int rows) {
		int datacount = count;
		int endPage=page * rows;
    	int startPage=(page- 1) * rows + 1;
		if (count == -1) {
			datacount = GetNewsListByTypeCount(newstype);
		}
		try {
			String innerSql = "SELECT newsId,newsTitle,newsContent,to_char(news.createDate, 'yyyy-MM-dd hh24:mi:ss') createDate,newstype,newsimg from news where 1=1 ";
			StringBuffer sb=new StringBuffer(innerSql);
			if (newstype != "") {
				sb.append(" and newstype='" + newstype + "'");
			}
			sb.append(" ORDER BY createDate desc ");
			//分页
    		String fenyeSql="select * from (select t.*,rownum rn from ("+sb.toString()+") t where rownum<="+endPage+") where rn>="+startPage+"";
			List<NewsListByType> l = ((List<NewsListByType>) dbSupport
					.getSqlQuery(fenyeSql,
							com.cmrx.bean.Entity.NewsListByType.class, true));

			Map<String, Object> m = new HashMap<String, Object>();
			m.put("total", datacount);
			m.put("rows", l);
			Gson gson = new Gson();
			return gson.toJson(m).toString();
		} catch (Exception ex) {
			log.AddLog("E", "GetNewsListByType(" + newstype + "," + page + ","
					+ rows + ")" + ex.getMessage(), "");
			return null;
		}
	}

	// 根据type获取新闻列表总数
	public int GetNewsListByTypeCount(String newstype) {
		try {
			String sql = "select COUNT(*) from news where 1=1 ";
			if (newstype != "") {
				sql += " and newstype='" + newstype + "'";
			}
			List list = dbSupport.getSqlQuery(sql, null, false);
			return Integer.parseInt(list.get(0).toString());
		} catch (Exception ex) {
			log.AddLog(
					"E",
					"GetNewsListByTypeCount(" + newstype + ")"
							+ ex.getMessage(), "");
			return 0;
		}
	}

	// 新闻列表
	@POST
	@Path("/getNewsList")
	@Produces(MediaType.APPLICATION_JSON)
	public String GetNewsList(@FormParam("begintime") String beginTime,
			@FormParam("endtime") String endTime,
			@FormParam("count") int count, @FormParam("page") int page,
			@FormParam("rows") int rows) {
		int datacount = count;
		if (count == -1) {
			datacount = GetNewsListCount(beginTime, endTime);
		}
		List<News_user> ss = GetNewsList(beginTime, endTime, page, rows);
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("total", datacount);
		m.put("rows", ss);
		Gson gson = new Gson();
		return gson.toJson(m).toString();
	}
	//获取总数
	private int GetNewsListCount(String beginTime, String endTime) {
		try {
			String sql = "select count(*) from news LEFT JOIN userinfo on news.createuserid=userinfo.userId where 1=1 ";
			StringBuffer sb=new StringBuffer(sql);
			if (beginTime != ""&&!beginTime.equals("")) {
				sb.append(" and to_char(news.createDate, 'yyyy-MM-dd hh24:mi:ss')>='" + beginTime + " 00:00:00'");
			}
			if (endTime != ""&&!endTime.equals("")) {
				sb.append(" and to_char(news.createDate, 'yyyy-MM-dd hh24:mi:ss')<='" + endTime + " 23:59:59'");
			}
			//sql += " ORDER BY news.createDate desc ";
			return dbSupport.getcount2(sb.toString());
		} catch (Exception ex) {
			log.AddLog("E", "GetNewsListCount(" + beginTime + "," + endTime
					+ ")" + ex.getMessage(), "");
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	private List<News_user> GetNewsList(String beginTime, String endTime,
			int page, int rows) {
		int endPage=page * rows;
    	int startPage=(page- 1) * rows + 1;
		try {
			String innerSql = "select news.newsId,news.newsTitle,userinfo.truename,to_char(news.createDate, 'yyyy-MM-dd hh24:mi:ss') createDate from news LEFT JOIN userinfo on news.createuserid=userinfo.userId where 1=1 ";
			StringBuffer sb=new StringBuffer(innerSql);
			if (beginTime != ""&&!beginTime.equals("")) {
				sb.append(" and to_char(news.createDate, 'yyyy-MM-dd hh24:mi:ss')>='" + beginTime + " 00:00:00'");
			}
			if (endTime != ""&&!endTime.equals("")) {
				sb.append(" and to_char(news.createDate, 'yyyy-MM-dd hh24:mi:ss')<='" + endTime + " 23:59:59'");
			}
			sb.append(" order by news.createDate desc");
			// 获取总数
			 //分页
    		String fenyeSql="select * from (select t.*,rownum rn from ("+sb.toString()+") t where rownum<="+endPage+") where rn>="+startPage+"";
            
			List<News_user> list = (List<News_user>) dbSupport.getSqlQuery(fenyeSql,News_user.class, true);
			return list;
		} catch (Exception ex) {
			log.AddLog("E", "GetNewsList(" + beginTime + "," + endTime + ","
					+ page + "," + rows + ")" + ex.getMessage(), "");
			return null;
		}
	}

	// 删除新闻
	@POST
	@Path("/delNews")
	@Produces(MediaType.TEXT_PLAIN)
	public String DelNews(@FormParam(value = "newsid") int newsid,
			@FormParam(value = "userid") String userid) {
		String val;
		boolean bool = NewsDelete(newsid, userid);
		if (bool) {
			val = "true";
		} else {
			val = "false";
		}
		return val;
	}

	private boolean NewsDelete(int newsid, String userid) {
		boolean bol = false;
		NewsBean bean1 = new NewsBean();
		try {
			NewsBean bean = (NewsBean) dbSupport
					.GetObjectByClass(bean1, newsid);
			dbSupport.DelByObject(bean);
			log.AddLog("D", "删除新闻" + newsid, userid + "");
			bol = true;
		} catch (Exception ex) {
			log.AddLog("E",
					"(" + newsid + "," + userid + ")" + ex.getMessage(), "");
		}
		return bol;
	}

	// 查看
	@POST
	@Path("/getNewsDetail")
	@Produces(MediaType.APPLICATION_JSON)
	public String GetNewsDetail(@FormParam(value = "newsid") int newsid) {
		List<NewsListByType> newsListByTypes;
		if (newsid > 0) {
			newsListByTypes = NewsDetail(newsid);
			Gson gson = new Gson();
			gson.toJson(newsListByTypes)
					.toString()
					.substring(
							gson.toJson(newsListByTypes).toString()
									.lastIndexOf(",") + 1);
			return gson.toJson(newsListByTypes).toString();
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	private List<NewsListByType> NewsDetail(int newsid) {
		try {
			String sql = "select newsId,newsTitle,newsContent,to_char(news.createDate, 'yyyy-MM-dd hh24:mi:ss') createDate,newsimg,newstype from news where newsId="
					+ new BigDecimal(newsid);
			List<NewsListByType> newsListByTypes = ((List<NewsListByType>) dbSupport
					.getSqlQuery(sql, NewsListByType.class, true));
			return newsListByTypes;
		} catch (Exception ex) {
			log.AddLog("E", "NewsDetail(" + newsid + ")" + ex.getMessage(), "");
			return null;
		}
	}
	// 修改新闻
	@POST
	@Path("/editNews")
	@Produces(MediaType.TEXT_PLAIN)
	public String EditNews(@FormParam(value = "newsid") int newsId,
			@FormParam(value = "newstitle") String newsTitle,
			@FormParam(value = "newscontent") String newsContent,
			@FormParam(value = "newsimgsrc") String newsImgsrc,
			@FormParam(value = "userid") String userId,
			@FormParam(value = "newstype") String newsType) {
		String val;
		newsContent = newsContent.replace("\"", "'");
		newsContent = newsContent.replace("\n", "");
		boolean bool = NewsEdit(newsId, newsTitle, newsContent, userId,
				newsImgsrc, newsType);
		if (bool) {
			val = "true";
		} else {
			val = "false";
		}
		return val;
	}

	private boolean NewsEdit(int newsId, String newsTitle, String newsContent,
			String userId, String newsImgsrc, String newsType) {
		boolean bol = false;
		try {
			NewsBean newsBean=(NewsBean) dbSupport.GetObjectByClass(new NewsBean(), newsId);
			newsBean.setCreateDate(DateUtil.getCurrentDatetimeDateTime());
			newsBean.setNewsContent(newsContent);
			newsBean.setNewsimg(newsImgsrc);
			newsBean.setNewsTitle(newsTitle);
			newsBean.setNewstype(newsType);
			newsBean.setCreateuserid(userId);
			//dbSupport.SaveByObject(newsBean);
			dbSupport.UpDateByObject(newsBean);
			log.AddLog("U", "修改新闻信息" + newsId, userId + "");
			bol = true;
		} catch (Exception ex) {
			log.AddLog("E", "NewsEdit(" + newsId + "," + newsTitle + ","
					+ newsContent + "," + newsImgsrc + "," + newsType + ")"
					+ ex.getMessage(), "");
		}
		return bol;
	}

	//添加新闻
	@POST
	@Path("/addNews")
	@Produces(MediaType.TEXT_PLAIN)
	public String AddNews(@FormParam(value = "newstitle") String newsTitle,
			@FormParam(value = "newscontent") String newsContent,
			@FormParam(value = "newsimgsrc") String newsImgsrc,
			@FormParam(value = "userid") String userId,
			@FormParam(value = "newstype") String newsType) {
		String val;
		newsContent = newsContent.replace("\"", "'");
		newsContent = newsContent.replace("\n", "");
		boolean bool = NewsAdd(newsTitle, newsContent, userId, newsImgsrc,
				newsType);
		if (bool) {
			val = "true";
		} else {
			val = "false";
		}
		return val;
	}

	private boolean NewsAdd(String newsTitle, String newsContent, String userId,
			String newsImgsrc, String newsType) {
		boolean bol = false;
		try {
			NewsBean newsBean = new NewsBean();
			newsBean.setCreateDate(DateUtil.getCurrentDatetimeDateTime());
			newsBean.setNewsContent(newsContent);
			newsBean.setNewsimg(newsImgsrc);
			newsBean.setNewsTitle(newsTitle);
			newsBean.setNewstype(newsType);
			newsBean.setCreateuserid(userId);
			dbSupport.SaveByObject(newsBean);
			log.AddLog("U", "添加新闻" + newsTitle, userId + "");
			bol = true;
		} catch (Exception ex) {
			log.AddLog("E",
					"NewsAdd(" + newsTitle + "," + newsContent + ","
							+ newsImgsrc + "," + newsType + "," + userId + ")"
							+ ex.getMessage(), "");

		}
		return bol;
	}
}