package com.ubn.befamous.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.hibernate.SessionFactory;

import com.ubn.befamous.dao.IBaseDao;

public class BaseDaoImpl<T, PK extends Serializable> implements IBaseDao<T, PK> {

	private Class<T> type;

	public BaseDaoImpl(Class<T> type) {
		this.type = type;
	}

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public PK save(T newInstance) {
		return (PK) this.sessionFactory.getCurrentSession().save(newInstance);
	}

	public T find(PK id) {
		T obj = (T) this.sessionFactory.getCurrentSession().get(type, id);
		if (obj == null) {
			throw new RuntimeException("" + type.getSimpleName() + "[" + id
					+ "] not found");
		}
		return obj;
	}

	public void update(T transientObject) {
		this.sessionFactory.getCurrentSession().update(transientObject);
	}

	public void delete(PK id) {
		T obj = find(id);
		try {
			BeanUtils.setProperty(obj, "dropDate", DateFormatUtils.format(new Date(), "yyyyMMddhhmmss"));
		} catch (IllegalAccessException e) {
			throw new RuntimeException("" + type.getSimpleName() + "[" + id
					+ "] delete Fail!!");
		} catch (InvocationTargetException e) {
			throw new RuntimeException("" + type.getSimpleName() + "[" + id
					+ "] delete Fail!!");
		}
		update(obj);
	}

	public T[] findAll() {
		List<T> list = (List<T>) this.sessionFactory
				.getCurrentSession()
				.createQuery(
						"From " + type.getSimpleName()
								+ " where dropDate is  null ").list();
		T[] t = (T[]) Array.newInstance(type, list.size());
		return list.toArray(t);
	}

}