package com.ucloudlink.springmongo.service.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.query.Criteria;

import com.ucloudlink.springmongo.common.dto.Condition;
import com.ucloudlink.springmongo.common.dto.PageDataResponse;

/**
 * 
 * @author xiaobin.ma
 *
 * @param <T>
 * @param <PK>
 */
public interface ICommonService<T, PK extends Serializable> {

	/**
	 * 根据主键删除
	 * 
	 * @param paramPK
	 */
	public void delete(PK paramPK);

	/**
	 * 
	 * @param id
	 * @param collectionName
	 */
	public void delete(PK id, String collectionName);

	public void delete(T entity);

	public void delete(T entity, String collectionName);

	public List<T> findByCondition(Condition condition);

	public List<T> findByCondition(Condition condition, String collectionName);

	public List<T> findByCondition(Condition condition, Criteria criteria, String collectionName);

	public T findById(PK id);

	public T findById(PK id, String collectionName);

	public PageDataResponse<T> findByPage(Condition condition);

	public PageDataResponse<T> findByPage(Condition condition, String collectionName);

	public T findByUniqueKey(String key, String value);

	public T findByUniqueKey(String key, String value, String collectionName);

	public T findOneByCondition(Condition condition);

	public T findOneByCondition(Condition condition, String collectionName);

	public void insert(T entity);

	public void insert(T entity, String collectionName);

	public void insertBatch(List<T> entities);

	public void insertBatch(List<T> entities, String collectionName);

	public void modify(String id, Map<String, Object> paramMap);

	public void modify(T entity);

	public void modify(T entity, String collectionName);

	public List<T> findByConditionNew(Condition paramCondition);

	public List<T> findByConditionNew(Condition paramCondition, String paramString);

	public PageDataResponse<T> findByPageNew(Condition paramCondition);

	public PageDataResponse<T> findByPageNew(Condition paramCondition, String paramString);
}
