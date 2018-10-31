package com.bilin.springmongo.common.dto;

import java.io.Serializable;

public class QueryItem
  implements Serializable
{
  private static final long serialVersionUID = 3207267704417597387L;
  public static final String ITEM_TYPE_AND = "and";
  public static final String ITEM_TYPE_OR = "or";
  private String param;
  private Object value;
  private Object start;
  private Object end;
  private Formula formula;
  private boolean ignoreCase = false;
  
  private String itemType = "and";
  
  public String getItemType() {
    return this.itemType;
  }
  
  public void setItemType(String itemType) { this.itemType = itemType; }
  
  public QueryItem() {}
  
  public QueryItem(String param, Object value, Formula formula)
  {
    this.param = param;
    this.value = value;
    this.formula = formula;
  }
  
  public QueryItem(String param, Object value, Formula formula, String itemType) {
    this.param = param;
    this.value = value;
    this.formula = formula;
    this.itemType = itemType;
  }
  
  public String getParam() {
    return this.param;
  }
  
  public void setParam(String param) {
    this.param = param;
  }
  
  public Object getValue() {
    return this.value;
  }
  
  public void setValue(Object value) {
    this.value = value;
  }
  
  public Object getStart() {
    return this.start;
  }
  
  public void setStart(Object start) {
    this.start = start;
  }
  
  public Object getEnd() {
    return this.end;
  }
  
  public void setEnd(Object end) {
    this.end = end;
  }
  
  public Formula getFormula() {
    return this.formula;
  }
  
  public void setFormula(Formula formula) {
    this.formula = formula;
  }
  
  public boolean isIgnoreCase() {
    return this.ignoreCase;
  }
  
  public void setIgnoreCase(boolean ignoreCase) {
    this.ignoreCase = ignoreCase;
  }
  
  public String toString()
  {
    return "{param:" + this.param + ",formula:" + this.formula + ",value:" + this.value + "}";
  }
  
  public int hashCode() {
    int prime = 31;
    int result = 1;
    result = 31 * result + (this.end == null ? 0 : this.end.hashCode());
    result = 31 * result + (this.formula == null ? 0 : this.formula.hashCode());
    result = 31 * result + (this.ignoreCase ? 1231 : 1237);
    result = 31 * result + (this.param == null ? 0 : this.param.hashCode());
    result = 31 * result + (this.start == null ? 0 : this.start.hashCode());
    result = 31 * result + (this.value == null ? 0 : this.value.hashCode());
    return result;
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    QueryItem other = (QueryItem)obj;
    if (this.end == null) {
      if (other.end != null)
        return false;
    } else if (!this.end.equals(other.end))
      return false;
    if (this.formula != other.formula)
      return false;
    if (this.ignoreCase != other.ignoreCase)
      return false;
    if (this.param == null) {
      if (other.param != null)
        return false;
    } else if (!this.param.equals(other.param))
      return false;
    if (this.start == null) {
      if (other.start != null)
        return false;
    } else if (!this.start.equals(other.start))
      return false;
    if (this.value == null) {
      if (other.value != null)
        return false;
    } else if (!this.value.equals(other.value))
      return false;
    return true;
  }
}
