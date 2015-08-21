package com.cmrx.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateSessionUtil {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// 创建线程局部变量　tLocalsess,用于操作 session
	public final ThreadLocal<Session> tLocalsess = new ThreadLocal<Session>();

	// 创建线程局部变量　tLocaltx,用于操作事务
	public final ThreadLocal<Transaction> tLocaltx = new ThreadLocal<Transaction>();

	// 取得session
	public Session currentSession() {

		// 从线程变量tLocalsess中，取得当前session
		Session session = (Session) tLocalsess.get();
		// 判断session是否为空，如果为空，将创建一个session,并付给线程变量tLocalsess
		try {
			if (session == null) {
				session = openSession();
				tLocalsess.set(session);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return session;
	}

	// 关闭当前session
	public void closeSession() {

		// 从线程变量tLocalsess中，取得当前session
		Session session = (Session) tLocalsess.get();

		// 设置线程变量tLocalsess为空
		tLocalsess.set(null);
		try {

			// 关闭session
			if (session != null && session.isOpen()) {
				session.close();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	// 开启事务
	public void beginTransaction() {

		// 从线程变量tLocaltx中取得事物管理对象Transaction
		Transaction tx = (Transaction) tLocaltx.get();
		try {
			// 如果为空就从session中创建一个tx
			if (tx == null) {
				tx = currentSession().beginTransaction();
				tLocaltx.set(tx);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	// 提交事务
	public void commitTransaction() {
		// 取得事物
		Transaction tx = (Transaction) tLocaltx.get();
		try {
			// 如果不为空就提交
			if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack())
				tx.commit();
			// 关闭当前session
			closeSession();
			tLocaltx.set(null);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	// 事物回滚
	public void rollbackTransaction() {
		// 取得tx事物
		Transaction tx = (Transaction) tLocaltx.get();
		try {
			// 将变量清空
			tLocaltx.set(null);
			if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
				// 事物回滚
				tx.rollback();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	// private 方法，只供本类使用。
	// 取得session
	private Session openSession() throws HibernateException {
		return getSessionFactory().openSession();
	}

}