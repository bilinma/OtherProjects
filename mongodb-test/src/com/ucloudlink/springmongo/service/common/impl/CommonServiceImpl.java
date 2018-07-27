package com.ucloudlink.springmongo.service.common.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.ucloudlink.springmongo.common.dto.Condition;
import com.ucloudlink.springmongo.common.dto.PageDataResponse;
import com.ucloudlink.springmongo.common.dto.QueryItem;
import com.ucloudlink.springmongo.common.utils.ReflectionUtils;
import com.ucloudlink.springmongo.service.common.ICommonService;

public class CommonServiceImpl<T, PK extends Serializable> implements ICommonService<T, PK> {
	protected static final Logger logger = Logger.getLogger(ICommonService.class);

	protected Class<T> entityClass;

	@Resource
	protected MongoTemplate mongoTemplate;

	public CommonServiceImpl() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	/**
	 * 根据查询条目创建Criteria
	 * 
	 * @param items
	 * @return Criteria对象 不为空
	 */

	public List<Criteria> createCriteriaByQueryItemList(Collection<QueryItem> items) {
		List<Criteria> listC = new ArrayList<Criteria>();
		if (CollectionUtils.isEmpty(items)) {
			return listC;
		}
		for (QueryItem item : items) {
			if (null == item) {
				continue;
			}
			switch (item.getFormula()) {
			case EQ:
				listC.add(Criteria.where(item.getParam()).is(item.getValue()));
				break;

			case LIKE:
				listC.add(Criteria.where(item.getParam())
						.regex(Pattern.compile(Pattern.quote(item.getValue().toString()), 2)));

				break;

			case IN:
				if (ObjectUtils.isArray(item.getValue())) {
					Object[] values = (Object[]) item.getValue();
					listC.add(Criteria.where(item.getParam()).in(values));
				} else if ((item.getValue() instanceof Collection)) {
					Collection<?> values = (Collection<?>) item.getValue();
					listC.add(Criteria.where(item.getParam()).in(values));
				} else {
					listC.add(Criteria.where(item.getParam()).in(new Object[] { item.getValue() }));
				}
				break;

			case GE:
				listC.add(Criteria.where(item.getParam()).gte(item.getValue()));
				break;

			case LE:
				listC.add(Criteria.where(item.getParam()).lte(item.getValue()));
				break;

			case GT:
				listC.add(Criteria.where(item.getParam()).gt(item.getValue()));
				break;

			case LT:
				listC.add(Criteria.where(item.getParam()).lt(item.getValue()));
				break;

			case NE:
				listC.add(Criteria.where(item.getParam()).ne(item.getValue()));
				break;

			case IS:
				listC.add(Criteria.where(item.getParam()).is(item.getValue()));
				break;

			case BETWEEN:
				Object params = item.getValue();
				if (ObjectUtils.isArray(params)) {
					Object[] array = (Object[]) params;
					listC.add(Criteria.where(item.getParam()).gte(array[0]).lte(array[1]));
				}
				break;

			case NEXIST:
				listC.add(Criteria.where(item.getParam()).exists(false));
				break;

			case EXIST:
				listC.add(Criteria.where(item.getParam()).exists(true));
				break;
			case NIN:
				if (ObjectUtils.isArray(item.getValue())) {
					Object[] values = (Object[]) item.getValue();
					listC.add(Criteria.where(item.getParam()).nin(values));
				} else if ((item.getValue() instanceof Collection)) {
					Collection<?> values = (Collection<?>) item.getValue();
					listC.add(Criteria.where(item.getParam()).nin(values));
				} else {
					listC.add(Criteria.where(item.getParam()).nin(new Object[] { item.getValue() }));
				}
				break;
			default:
				break;
			}
		}
		return listC;
	}

	public Query createQuery(Condition condition, Criteria otherCriteria) {
		if ((condition == null) && (otherCriteria == null)) {
			return new Query();
		}

		List<Criteria> allCriterialList = new ArrayList<Criteria>();
		if (otherCriteria != null) {
			allCriterialList.add(otherCriteria);
		}

		if (condition == null) {
			Criteria c = new Criteria();
			c.andOperator((Criteria[]) allCriterialList.toArray(new Criteria[allCriterialList.size()]));
			return new Query(c);
		}

		try {
			if (CollectionUtils.isNotEmpty(condition.getQueryItems())) {
				List<Criteria> conditionCriteriaList = createCriteriaByQueryItemList(condition.getQueryItems());
				allCriterialList.addAll(conditionCriteriaList);
			}

			Criteria criteria = new Criteria();
			if (allCriterialList.size() > 0) {
				criteria.andOperator((Criteria[]) allCriterialList.toArray(new Criteria[allCriterialList.size()]));
			}

			Map<String, Integer> fields = condition.getFields();
			Query query = null;
			if (fields == null) {
				query = new BasicQuery(criteria.getCriteriaObject());
			} else {
				query = new BasicQuery(criteria.getCriteriaObject(), new BasicDBObject(fields));
			}

			Sort sort = createSort(condition);
			if (sort != null) {
				query.with(sort);
			}

			int currentPage = condition.getCurrentPage();
			int pageSize = condition.getPageSize();
			if ((pageSize > 0) && (currentPage > 0)) {
				query.skip((currentPage - 1) * pageSize);
				query.limit(pageSize);
			}
			return query;
		} catch (Exception e) {
			logger.error("", e);
			throw e;
		}
	}

	@Override
	public void delete(PK id) {
		delete(id, null);
	}

	@Override
	public void delete(PK id, String collectionName) {
		Query query = Query.query(Criteria.where("_id").is(id));

		if (StringUtils.isBlank(collectionName)) {
			this.mongoTemplate.findAndRemove(query, this.entityClass);
		} else {
			this.mongoTemplate.findAndRemove(query, this.entityClass, collectionName);
		}
	}

	@Override
	public void delete(T entity) {
		delete(entity, null);
	}

	@Override
	public void delete(T entity, String collectionName) {
		if (StringUtils.isBlank(collectionName)) {
			this.mongoTemplate.remove(entity);
		} else {
			this.mongoTemplate.remove(entity, collectionName);
		}
	}

	public List<T> findByCondition(Condition condition) {
		return findByCondition(condition, null);
	}

	@Override
	public List<T> findByCondition(Condition condition, String collectionName) {
		logger.debug("findByCondition param: condition=" + condition + ", collectionName=" + collectionName);

		Query query = createQuery(condition, null);

		List<T> dataList = new ArrayList<T>();
		if (StringUtils.isBlank(collectionName)) {
			dataList = this.mongoTemplate.find(query, this.entityClass);
		} else {
			dataList = this.mongoTemplate.find(query, this.entityClass, collectionName);
		}

		return dataList;
	}

	@Override
	public List<T> findByCondition(Condition condition, Criteria criteria, String collectionName) {
		logger.debug("findByCondition param: condition=" + condition);

		Query query = createQuery(condition, criteria);

		List<T> dataList = new ArrayList<T>();
		if (StringUtils.isBlank(collectionName)) {
			dataList = this.mongoTemplate.find(query, this.entityClass);
		} else {
			dataList = this.mongoTemplate.find(query, this.entityClass, collectionName);
		}
		return dataList;
	}

	@Override
	public T findById(PK id) {
		return findById(id, null);
	}

	@Override
	public T findById(PK id, String collectionName) {
		if (StringUtils.isBlank(collectionName)) {
			return this.mongoTemplate.findById(id, this.entityClass);
		}
		return this.mongoTemplate.findById(id, this.entityClass, collectionName);
	}

	@Override
	public PageDataResponse<T> findByPage(Condition condition) {
		return findByPage(condition, null);
	}

	@Override
	public PageDataResponse<T> findByPage(Condition condition, String collectionName) {
		Assert.notNull(condition);
		logger.debug("findByPage param: condition=" + condition + ", collectionName=" + collectionName);

		PageDataResponse<T> respData = new PageDataResponse<T>();
		respData.setCurrentPage(Integer.valueOf(condition.getCurrentPage()));
		respData.setPerPageCount(Integer.valueOf(condition.getPageSize()));

		Query query = createQuery(condition, null);

		int totalCount = 0;
		if (StringUtils.isBlank(collectionName)) {
			totalCount = (int) this.mongoTemplate.count(query, this.entityClass);
		} else {
			totalCount = (int) this.mongoTemplate.count(query, this.entityClass, collectionName);
		}
		respData.setTotalCount(totalCount);

		List<T> dataList = new ArrayList<T>();
		if (StringUtils.isBlank(collectionName)) {
			dataList = this.mongoTemplate.find(query, this.entityClass);
		} else {
			dataList = this.mongoTemplate.find(query, this.entityClass, collectionName);
		}
		respData.setDataList(dataList);

		logger.debug("findByPage result : totalCount=" + totalCount + ", dataListSize=" + dataList.size());
		return respData;
	}

	@Override
	public T findByUniqueKey(String key, String value) {
		return findByUniqueKey(key, value, null);
	}

	@Override
	public T findByUniqueKey(String key, String value, String collectionName) {
		Criteria criteria = Criteria.where(key).is(value);

		if (StringUtils.isBlank(collectionName)) {
			return this.mongoTemplate.findOne(Query.query(criteria), this.entityClass,
					this.entityClass.getSimpleName());
		}
		return this.mongoTemplate.findOne(Query.query(criteria), this.entityClass, collectionName);
	}

	@Override
	public T findOneByCondition(Condition condition) {
		return findOneByCondition(condition, null);
	}

	@Override
	public T findOneByCondition(Condition condition, String collectionName) {
		Query query = createQuery(condition, null);
		query = query == null ? new Query() : query;

		if (StringUtils.isBlank(collectionName)) {
			return this.mongoTemplate.findOne(query, this.entityClass);
		}
		return this.mongoTemplate.findOne(query, this.entityClass, collectionName);
	}

	@Override
	public void insert(T entity) {
		insert(entity, null);
	}

	@Override
	public void insert(T entity, String collectionName) {
		if (StringUtils.isBlank(collectionName)) {
			this.mongoTemplate.save(entity);
		} else {
			this.mongoTemplate.save(entity, collectionName);
		}
	}

	@Override
	public void insertBatch(List<T> entities) {
		insertBatch(entities, null);
	}

	@Override
	public void insertBatch(List<T> entities, String collectionName) {
		if (StringUtils.isBlank(collectionName)) {
			this.mongoTemplate.insert(entities, this.entityClass);
		} else {
			this.mongoTemplate.insert(entities, collectionName);
		}
	}

	@Override
	public void modify(String id, Map<String, Object> dataMap) {
		Query query = Query.query(Criteria.where("_id").is(id));
		Update update = new Update();
		for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
			update.set((String) entry.getKey(), entry.getValue());
		}
		update.unset("tx");
		this.mongoTemplate.updateFirst(query, update, this.entityClass);
		logger.debug("modifiy " + entityClass.getName() + " id=" + id + ", field=" + dataMap);
	}

	@Override
	public void modify(T entity) {
		modify(entity, null);
	}

	@Override
	public void modify(T entity, String collectionName) {
		DBObject dbObject = resloveBean2DBObject(entity);
		Object id = dbObject.get("id");
		Query query = Query.query(Criteria.where("_id").is(id));
		dbObject.removeField("id");
		Update update = new Update();
		for (String key : dbObject.keySet()) {
			update.set(key, dbObject.get(key));
		}
		update.unset("tx");
		logger.info("update:" + id + "," + update);
		if (StringUtils.isBlank(collectionName)) {
			this.mongoTemplate.findAndModify(query, update, this.entityClass);
		} else {
			this.mongoTemplate.findAndModify(query, update, this.entityClass, collectionName);
		}
	}

	public DBObject resloveBean2DBObject(T bean) {
		return resloveBean2DBObject(bean, true);
	}

	public DBObject resloveBean2DBObject(T bean, boolean ignoreNull) {
		logger.debug("resloveBean2DBObject bean: " + bean + ", ignoreNull: " + ignoreNull);
		if (bean == null) {
			return null;
		}

		DBObject dbObject = new BasicDBObject();

		Class<?> clazz = bean.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (clazz = clazz.getSuperclass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
			fields = (Field[]) ArrayUtils.addAll(fields, clazz.getDeclaredFields());
		}
		for (Field field : fields) {
			String varName = field.getName();

			if (!Modifier.isStatic(field.getModifiers())) {
				boolean accessFlag = field.isAccessible();
				if (!accessFlag) {
					field.setAccessible(true);
				}
				Object param = null;
				try {
					param = field.get(bean);
				} catch (IllegalArgumentException e) {
					logger.error("", e);
				} catch (IllegalAccessException e) {
					logger.error("", e);
				}
				if ((!ignoreNull) || (param != null)) {
					dbObject.put(varName, param);

					field.setAccessible(accessFlag);
				}
			}
		}
		logger.debug("resloveBean2DBObject DBObject: " + dbObject);
		return dbObject;
	}

	private Sort createSort(Condition condition) {
		List<Sort.Order> orders = new ArrayList<Sort.Order>();

		Map<String, Boolean> orderkeys = condition.getOrderKeys();
		if (MapUtils.isEmpty(orderkeys)) {
			return null;
		}
		Iterator<Map.Entry<String, Boolean>> orderKeyIt = orderkeys.entrySet().iterator();
		while (orderKeyIt.hasNext()) {
			Map.Entry<String, Boolean> tempEntry = (Map.Entry<String, Boolean>) orderKeyIt.next();
			String key = (String) tempEntry.getKey();
			boolean asc = ((Boolean) tempEntry.getValue()).booleanValue();
			Sort.Order order = new Sort.Order(asc ? Sort.Direction.ASC : Sort.Direction.DESC, key);
			orders.add(order);
		}
		return new Sort(orders);
	}

	public List<T> findByConditionNew(Condition condition) {
		return findByConditionNew(condition, null);
	}

	public List<T> findByConditionNew(Condition condition, String collectionName) {

		Query query = createQueryNew(condition, null);

		List<T> dataList = new ArrayList<T>();
		if (StringUtils.isBlank(collectionName)) {
			dataList = this.mongoTemplate.find(query, this.entityClass);
		} else {
			dataList = this.mongoTemplate.find(query, this.entityClass, collectionName);
		}
		return dataList;
	}

	public PageDataResponse<T> findByPageNew(Condition condition) {
		return findByPageNew(condition, null);
	}

	public PageDataResponse<T> findByPageNew(Condition condition, String collectionName) {
		Assert.notNull(condition);

		PageDataResponse<T> respData = new PageDataResponse<T>();
		respData.setCurrentPage(Integer.valueOf(condition.getCurrentPage()));
		respData.setPerPageCount(Integer.valueOf(condition.getPageSize()));

		Query query = createQueryNew(condition, null);

		int totalCount = 0;
		if (StringUtils.isBlank(collectionName)) {
			totalCount = (int) this.mongoTemplate.count(query, this.entityClass);
		} else {
			totalCount = (int) this.mongoTemplate.count(query, this.entityClass, collectionName);
		}
		respData.setTotalCount(totalCount);

		List<T> dataList = new ArrayList<T>();
		if (StringUtils.isBlank(collectionName)) {
			dataList = this.mongoTemplate.find(query, this.entityClass);
		} else {
			dataList = this.mongoTemplate.find(query, this.entityClass, collectionName);
		}
		respData.setDataList(dataList);

		return respData;
	}

	public Query createQueryNew(Condition condition, Criteria otherCriteria) {
		if ((condition == null) && (otherCriteria == null)) {
			return new Query();
		}

		List<Criteria> allCriterialList = new ArrayList<Criteria>();
		if (otherCriteria != null) {
			allCriterialList.add(otherCriteria);
		}

		if (condition == null) {
			Criteria c = new Criteria();
			c.andOperator((Criteria[]) allCriterialList.toArray(new Criteria[allCriterialList.size()]));
			return new Query(c);
		}

		try {
			if (CollectionUtils.isNotEmpty(condition.getQueryItems())) {
				List<Criteria> conditionCriteriaList = createCriteriaByQueryItemList(condition.getQueryItems());
				allCriterialList.addAll(conditionCriteriaList);
			}

			Criteria criteria = new Criteria();
			if (allCriterialList.size() > 0) {
				criteria.andOperator((Criteria[]) allCriterialList.toArray(new Criteria[allCriterialList.size()]));
			}

			List<Criteria> allOrCriterialList = new ArrayList<Criteria>();
			if (CollectionUtils.isNotEmpty(condition.getQueryOrItems())) {
				List<Criteria> conditionCriteriaList = createCriteriaByQueryItemList(condition.getQueryOrItems());
				allOrCriterialList.addAll(conditionCriteriaList);
			}
			if (allOrCriterialList.size() > 0) {
				criteria.orOperator((Criteria[]) allOrCriterialList.toArray(new Criteria[allOrCriterialList.size()]));
			}

			Map<String, Integer> fields = condition.getFields();
			Query query = null;
			if (fields == null) {
				query = new BasicQuery(criteria.getCriteriaObject());
			} else {
				query = new BasicQuery(criteria.getCriteriaObject(), new BasicDBObject(fields));
			}

			Sort sort = createSort(condition);
			if (sort != null) {
				query.with(sort);
			}

			int currentPage = condition.getCurrentPage();
			int pageSize = condition.getPageSize();
			if ((pageSize > 0) && (currentPage > 0)) {
				query.skip((currentPage - 1) * pageSize);
				query.limit(pageSize);
			}
			return query;
		} catch (Exception e) {
			logger.error("", e);
			throw e;
		}
	}

}
