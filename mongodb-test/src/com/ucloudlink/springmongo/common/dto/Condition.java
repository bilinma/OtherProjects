package com.ucloudlink.springmongo.common.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

public class Condition implements Serializable {
	private static final long serialVersionUID = -5427500692306190991L;
	private Collection<QueryItem> queryItems = new LinkedHashSet();

	private Collection<QueryItem> queryOrItems = new LinkedHashSet();

	@Deprecated
	private String orderbyProperty;
	@Deprecated
	private boolean desc = true;

	private Map<String, Boolean> orderKeys = new LinkedHashMap();

	private ResponseData pager;

	private int currentPage;

	private int pageSize;

	private String hint;

	private Collection<String> excludeFields = new LinkedHashSet();

	private Collection<String> includeFields = new LinkedHashSet();

	private int skip = 0;

	public Collection<QueryItem> getQueryItems() {
		return this.queryItems;
	}
	
	public Collection<QueryItem> getQueryOrItems() {
		return this.queryOrItems;
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Deprecated
	public String getOrderbyProperty() {
		if (StringUtils.isNotBlank(this.orderbyProperty)) {
			return this.orderbyProperty;
		}

		if (MapUtils.isNotEmpty(this.orderKeys)) {
			return (String) ((Map.Entry) this.orderKeys.entrySet().iterator().next()).getKey();
		}
		return this.orderbyProperty;
	}

	@Deprecated
	public boolean isDesc() {
		if (StringUtils.isNotBlank(this.orderbyProperty)) {
			return this.desc;
		}
		if (MapUtils.isNotEmpty(this.orderKeys)) {
			return !((Boolean) ((Map.Entry) this.orderKeys.entrySet().iterator().next()).getValue()).booleanValue();
		}
		return this.desc;
	}

	@Deprecated
	public void setDesc(boolean desc) {
		this.desc = desc;
	}

	@Deprecated
	public void setOrderbyProperty(String orderbyProperty) {
		this.orderbyProperty = orderbyProperty;
		this.orderKeys.put(orderbyProperty, Boolean.valueOf(false));
	}

	public Condition setOrderby(String orderProperty, boolean isAsc) {
		this.orderbyProperty = orderProperty;
		this.orderKeys.put(orderProperty, Boolean.valueOf(isAsc));
		this.desc = (!isAsc);
		return this;
	}

	public Condition addItem(QueryItem item) {
		this.queryItems.add(item);
		return this;
	}

	public Condition addItem(String param, Object value, Formula formula) {
		this.queryItems.add(new QueryItem(param, value, formula));
		return this;
	}

	public Condition addItemNotNull(String param, Object value, Formula formula) {
		if (value != null) {
			if (((value instanceof String)) && (StringUtils.isEmpty(value + ""))) {
				return this;
			}
			this.queryItems.add(new QueryItem(param, value, formula));
		}
		return this;
	}

	public Condition addOrItem(QueryItem item) {
		this.queryOrItems.add(item);
		return this;
	}

	public Condition addOrItem(String param, Object value, Formula formula) {
		this.queryOrItems.add(new QueryItem(param, value, formula));
		return this;
	}
	
	public Condition addAll(Collection<QueryItem> items) {
		this.queryItems.addAll(items);
		return this;
	}

	public ResponseData getPager() {
		return this.pager;
	}

	public void setPager(ResponseData pager) {
		this.pager = pager;
	}

	public Collection<String> getExcludeFields() {
		return this.excludeFields;
	}

	public void addExcludeFields(String excludeField) {
		this.excludeFields.add(excludeField);
	}

	public void addIncludeFields(String includeField) {
		this.includeFields.add(includeField);
	}

	public Collection<String> getIncludeFields() {
		return this.includeFields;
	}

	public Map<String, Integer> getFields() {
		Map<String, Integer> includeMap = new HashMap();
		Map<String, Integer> excludeMap = new HashMap();

		boolean excludeId = false;

		if (this.excludeFields.size() > 0) {
			for (String tmp : this.excludeFields) {
				excludeMap.put(tmp, Integer.valueOf(0));
				if (("_id".equals(tmp)) || ("id".equals(tmp))) {
					excludeId = true;
				}
			}
		}

		if (this.includeFields.size() > 0) {
			for (String tmp : this.includeFields) {
				includeMap.put(tmp, Integer.valueOf(1));
			}
		}

		if (this.includeFields.size() > 0) {
			if (excludeId) {
				includeMap.put("_id", Integer.valueOf(0));
			}
			return includeMap;
		}
		if (this.excludeFields.size() > 0) {
			return excludeMap;
		}

		return null;
	}

	public String getHint() {
		return this.hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public String toString() {
		return "Condition [queryItems=" + this.queryItems + ", orderbyProperty=" + this.orderbyProperty + ", desc="
				+ this.desc + ", pager=" + this.pager + ", currentPage=" + this.currentPage + ", pageSize="
				+ this.pageSize + ", excludeFields=" + this.excludeFields + ", includeFields=" + this.includeFields
				+ "]";
	}

	public Map<String, Boolean> getOrderKeys() {
		return this.orderKeys;
	}

	public void setOrderKeys(Map<String, Boolean> orderKeys) {
		this.orderKeys = orderKeys;
	}

	public int getSkip() {
		return this.skip;
	}

	public void setSkip(int skip) {
		this.skip = skip;
	}
}
