package lsc.core;

import java.io.Serializable;

public class GgTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5755663143906038786L;
	//数据库
	private String db;
	//表
	private String table;
	//默认的id主键值，long型
	private Long id;
	//自定义字段名
	private String field;
	//field 相对于 数据记录的 value值
	private Object value;
	
	public GgTable(String db, String table) {
		super();
		this.db = db;
		this.table = table;
	}
	public GgTable() {
	}
	public String getDb() {
		return db;
	}
	public void setDb(String db) {
		this.db = db;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "ZkTable [db=" + db + ", table=" + table + ", id=" + id
				+ ", field=" + field + ", value=" + value + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((db == null) ? 0 : db.hashCode());
		result = prime * result + ((table == null) ? 0 : table.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GgTable other = (GgTable) obj;
		if (db == null) {
			if (other.db != null)
				return false;
		} else if (!db.equals(other.db))
			return false;
		if (table == null) {
			if (other.table != null)
				return false;
		} else if (!table.equals(other.table))
			return false;
		return true;
	}
}
