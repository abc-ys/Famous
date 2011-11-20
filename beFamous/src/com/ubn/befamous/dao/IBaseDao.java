package com.ubn.befamous.dao;

import java.io.Serializable;

public interface IBaseDao<T, PK extends Serializable> {
	PK save(T newInstance);

	T find(PK oid);

	void update(T transientObject);

	void delete(PK oid);

	T[] findAll();
}
