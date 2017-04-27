package com.lgame.base.dao;


import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.lgame.utils.AppException;
import com.lgame.utils.DataUtil;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lgame.utils.Const;

@Repository
public class BaseDao {
	private static Logger logger = Logger.getLogger(BaseDao.class);
	/**
	 * 根据存储过程或者sql获得表结构信息
	 * @param jdbcTemplate
	 * @param storedProcedure存储过程(有参数用“？”号)或者sql
	 * @param str参数
	 * @return
	 */
	public List<Map<String, Object>> getCommentByTableName(JdbcTemplate jdbcTemplate,final String storedProcedure, final Object... str) {
		return jdbcTemplate.execute(storedProcedure, new CallableStatementCallback<List<Map<String, Object>>>() {
			@Override
			public List<Map<String, Object>> doInCallableStatement(CallableStatement cs) {
				List<Map<String,Object>> list = new LinkedList<Map<String,Object>>();
				try {
					if(str != null && str.length > 0){
						for(int i = 0;i<str.length;i++){
							cs.setObject(i+1,str[i]);
						}
					}
					ResultSet rs = cs.executeQuery();
					Map<String,Object> colComment;
					while(rs.next()){
						colComment = new LinkedHashMap<String, Object>();
						colComment.put("comment", rs.getString("Comment"));
						colComment.put("name", rs.getString("Field"));
						colComment.put("type", rs.getString("Type"));
						colComment.put("isNull", rs.getString("Null").toLowerCase().equals("yes"));
						colComment.put("dataType", DataUtil.getTypeBySqlDataType(rs.getString("Type")));
						list.add(colComment);
					}
				} catch (Exception e) {
					logger.error(storedProcedure,e);
				}
				return list;
			}
		});
	}
	/**
	 * 根据条件查询某个对象(Map组装)
	 * @param jdbcTemplate
	 * @param storedProcedure
	 * @param obj
	 * @return
	 */
	public Map<String,Object> execute(JdbcTemplate jdbcTemplate,final String storedProcedure,final Object... obj){
		return jdbcTemplate.execute(storedProcedure, new CallableStatementCallback<Map<String, Object>>() {
			@Override
			public Map<String, Object> doInCallableStatement(CallableStatement cs) {
				Map<String,Object> item = new HashMap<String, Object>();
				try {
					if(obj.length > 0){
						for(int i = 0; i < obj.length;i++){
							if(obj[i] == null){
								cs.setString(i+1,null);
							}else{
								cs.setObject(i+1, obj[i]);
							}
						}
					}
					ResultSet rs = cs.executeQuery();
					ResultSetMetaData rsd = rs.getMetaData();
					if(rs.next()){
						item = new HashMap<String, Object>();
						for(int i=1;i<=rsd.getColumnCount();i++){
							item.put(rsd.getColumnLabel(i), rs.getString(i));
						}
					}
				} catch (Exception e) {
					logger.error(storedProcedure+":",e);
				}
				return item;
			}
		});
	}
	/**
	 * 根据条件查询某个对象
	 * @param jdbcTemplate
	 * @param cls
	 * @param storedProcedure
	 * @param obj
	 * @return
	 */
	public <T> T execute(JdbcTemplate jdbcTemplate,final Class<T> cls,final String storedProcedure,final Object... obj){
		return jdbcTemplate.execute(storedProcedure, new CallableStatementCallback<T>() {
			@Override
			public T doInCallableStatement(CallableStatement cs) {
				try {
					if(obj.length > 0){
						for(int i = 0; i < obj.length;i++){
							if(obj[i] == null){
								cs.setString(i+1,null);
							}else{
								cs.setObject(i+1, obj[i]);
							}
						}
					}
					ResultSet rs = cs.executeQuery();
					if(rs.next()){
						return executeResultSet(cls, rs, null);
					}
				} catch (Exception e) {
					logger.error(storedProcedure+":",e);
				}
				return null;
			}
		});
	}
	/**
	 * 根据条件返回查询结果集合
	 * @param jdbcTemplate
	 * @param storedProcedure
	 * @param obj参数数组
	 * @return
	 */
	public List<Map<String, Object>> executes(JdbcTemplate jdbcTemplate,final String storedProcedure,final Object... obj) {
		List<Map<String, Object>> resultMap = jdbcTemplate.execute(storedProcedure, new CallableStatementCallback<List<Map<String,Object>>>() {
			@Override
			public List<Map<String,Object>> doInCallableStatement(CallableStatement cs) {
				List<Map<String,Object>> list = new LinkedList<Map<String,Object>>();
				try {
					if(obj.length > 0){
						for(int i = 0; i < obj.length;i++){
							if(obj[i] == null){
								cs.setString(i+1,null);
							}else{
								cs.setObject(i+1, obj[i]);
							}
						}
					}
					ResultSet rs = cs.executeQuery();
					ResultSetMetaData rsd = rs.getMetaData();
					Map<String,Object> colComment;
					while(rs.next()){
						colComment = new HashMap<String, Object>();
						for(int i=1;i<=rsd.getColumnCount();i++){
							colComment.put(rsd.getColumnLabel(i), rs.getString(i) == null?Const.NULLDATA:rs.getString(i));
						}
						list.add(colComment);
					}
				} catch (Exception e) {
					logger.error("pr_static_get_table_result:",e);
				}
				return list;
			}
		});
		return resultMap;
	}

	/**
	 * 根据条件返回查询结果集合
	 * @param jdbcTemplate
	 * @param storedProcedure
	 * @param columMap 当前查询的字段键值对集合，类属性-对应数据库字段名
	 * @param obj参数数组
	 * @return
	 */
	public <T> List<T> executes(JdbcTemplate jdbcTemplate,final Class<T> cls,final Map<String,String> columMap,final String storedProcedure,final Object... obj) {
		List<T>  resultMap = jdbcTemplate.execute(storedProcedure, new CallableStatementCallback<List<T>>() {
			@Override
			public  List<T> doInCallableStatement(CallableStatement cs) {
				List<T> list = new LinkedList<T>();
				try {
					ResultSet rs = cs.executeQuery();
					while(rs.next()){
						T obj = executeResultSet(cls, rs,columMap);
						list.add(obj);
					}
				} catch (Exception e) {
					logger.error("pr_static_get_table_result:",e);
				}
				return list;
			}
		});
		return resultMap;
	}

	/**
	 * 通用（返回map）分页排序 （不用对page等数据处理） total-总数量,rows当前页数据
	 * @param jdbcTemplate
	 * @param tableName表名
	 * @param page当前页码
	 * @param pagesize每页记录数当小于0时表示不分页
	 * @param sqlWhere查询条件 如" id > 20"
	 * @param order排序 如: "id desc"
	 * @return
	 */
	public Map<String, Object> getPageResult(JdbcTemplate jdbcTemplate,final String tableName,final int page, final int pagesize,final String sqlWhere,final String order) {
		final Map<String, Object> pageMap = new HashMap<String, Object>();
		List<Map<String, Object>> resultMap = jdbcTemplate.execute("{call pr_get_result_pages(?,?,?,?,?)}", new CallableStatementCallback<List<Map<String,Object>>>() {
			@Override
			public List<Map<String,Object>> doInCallableStatement(CallableStatement cs) {
				List<Map<String,Object>> list = new LinkedList<Map<String,Object>>();
				try {
					cs.setString(1, tableName);
					cs.setString(2, sqlWhere==null?"":sqlWhere);
					cs.setString(3, order==null?"":order);
					cs.setInt(4,pagesize);
					cs.setInt(5,page);
					ResultSet rs = cs.executeQuery();
					ResultSetMetaData rsd = rs.getMetaData();
					Map<String,Object> colComment;
					while(rs.next()){
						colComment = new HashMap<String, Object>();
						for(int i=1;i<=rsd.getColumnCount();i++){
							colComment.put(rsd.getColumnLabel(i), rs.getString(i) == null?Const.NULLDATA:rs.getString(i));
							if(("TIMESTAMP").equals(rsd.getColumnTypeName(i))){
								colComment.put(rsd.getColumnLabel(i), rs.getString(i) == null?Const.NULLDATA:rs.getString(i).substring(0, rs.getString(i).lastIndexOf(".")));
							}
						}
						list.add(colComment);
					}
					if(cs.getMoreResults()){
						rs = cs.getResultSet();
						while (rs != null && rs.next()) {
							pageMap.put("total",rs.getInt(1));
						}
					}
				} catch (Exception e) {
					logger.error("pr_static_get_table_result:",e);
				}
				return list;
			}
		});
		pageMap.put("rows", resultMap);

		return pageMap;
	}
	/**
	 * 通用(返回对象)分页排序（不用对page等数据处理） total-总数量,rows当前页数据
	 * @param jdbcTemplate
	 * @param <T>
	 * @param columMap当前查询的字段键值对集合，类属性-对应数据库字段名
	 * @param tableName表名
	 * @param page当前页码
	 * @param pagesize每页记录数当小于0时表示不分页
	 * @param sqlWhere查询条件 如" id > 20"
	 * @param order排序 如: "id desc"
	 * @return
	 */
	public  <T> Map<String, Object>  getPageResultToObject(JdbcTemplate jdbcTemplate,final Class<T> cls,final Map<String,String> columMap ,final String tableName,final int page, final int pagesize,final String sqlWhere,final String order) {
		final Map<String, Object> pageMap = new HashMap<String, Object>();
		List<T>  resultMap = jdbcTemplate.execute("{call pr_get_result_pages(?,?,?,?,?)}", new CallableStatementCallback<List<T>>() {
			@Override
			public  List<T> doInCallableStatement(CallableStatement cs) {
				List<T> list = new LinkedList<T>();
				try {
					cs.setString(1, tableName);
					cs.setString(2, sqlWhere==null?"":sqlWhere);
					cs.setString(3, order==null?"":order);
					cs.setInt(4,pagesize);
					cs.setInt(5,page);
					ResultSet rs = cs.executeQuery();
					while(rs.next()){
						T obj = executeResultSet(cls, rs,columMap);
						list.add(obj);
					}
					if(cs.getMoreResults()){
						rs = cs.getResultSet();
						while (rs != null && rs.next()) {
							pageMap.put("total",rs.getInt(1));
						}
					}
				} catch (Exception e) {
					logger.error("pr_static_get_table_result:",e);
				}
				return list;
			}
		});
		pageMap.put("rows", resultMap);

		return pageMap;
	}

	private static <T> T executeResultSet(Class<T> cls, ResultSet rs,Map<String,String> columMap)
			throws InstantiationException, IllegalAccessException, SQLException {
		T obj = cls.newInstance();
		ResultSetMetaData rsm = rs.getMetaData();
		int columnCount = rsm.getColumnCount();
		boolean isAll = columMap == null || columMap.isEmpty();
		Field[] fields = cls.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String fieldName = field.getName();
			if(!isAll){
				if(!columMap.containsKey(fieldName)){
					continue;
				}
				fieldName = columMap.get(fieldName);
			}
			for (int j = 1; j <= columnCount; j++) {
				String columnName = rsm.getColumnLabel(j);
				if(columnName.equals(fieldName)){
					Object value = rs.getObject(j);
					field.setAccessible(true);// 调用getDeclaredField("name") 取得name属性对应的Field对象
					try {
						field.set(obj, value);
					} catch (IllegalArgumentException e) {
						try {
							field.set(obj, String.valueOf(value));
						} catch (IllegalArgumentException e1) {
							System.out.println(fieldName+"数据格式不正确");
						}
					}
					break;
				}else if(j == columnCount){
					try {
						throw new AppException("数据库中不存在字段"+fieldName);
					} catch (AppException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return obj;
	}


	/**
	 * 根据map和表名自动组装update-sql
	 * @param obj
	 * @param tableName
	 * @return
	 */
	protected String getUpdateSql(Map<String, Object> obj, String tableName){
		StringBuilder sql = new StringBuilder();
		sql.append("update ").append(tableName);
		sql.append("  set ");
		int i = 0;
		for(String str :obj.keySet()){
			if(str.equals("id")){
				continue;
			}
			if(i > 0){
				sql.append(" , ");
			}
			sql.append(str).append("=");
			if(obj.get(str).equals(Const.NULLDATA) || obj.get(str).equals("null")){
				sql.append("null");
			}else{
				sql.append("'").append(obj.get(str)).append("'");
			}
			i++;
		}
		sql.append("  where id = ").append(obj.get("id"));
		return sql.toString();
	}
	/**
	 * 根据map和表名自动组装add-sql
	 * @param obj
	 * @param tableName
	 * @return
	 */
	protected String getAddSql(Map<String, Object> obj, String tableName){
		StringBuilder sql = new StringBuilder();
		StringBuilder names = new StringBuilder();
		StringBuilder values = new StringBuilder();
		sql.append("insert into ").append(tableName);
		names.append("  ( ");
		values.append("  ( ");
		int i = 0;
		for(String str :obj.keySet()){
			if(str.equals(":dataError")){
				continue;
			}
			if(i > 0){
				names.append(" , ");
				values.append(" , ");
			}
			names.append("`").append(str).append("`");
			if(obj.get(str).equals(Const.NULLDATA) || obj.get(str).equals("null")){
				values.append("null");
			}else{
				values.append("'").append(obj.get(str)).append("'");
			}
			i++;
		}
		names.append("  ) ");
		values.append("  ) ");
		sql.append(names).append("  values ").append(values);
		return sql.toString();
	}
	/**
	 * 根据id字符串和表名自动组装del-sql
	 * @param ids(id以“,”分割的字符串)如果ids为null或者空则表示删除所有数据
	 * @param tableName
	 * @return
	 */
	protected String getDelSql(String ids, String tableName){
		StringBuilder sql = new StringBuilder();
		sql.append("delete from ").append(tableName);
		if(ids == null || ids.equals("")){
			return sql.toString();
		}
		sql.append("  where id in (");
		int i = 0;
		for(String id:ids.split(",")){
			if(id.isEmpty()){
				continue;
			}
			if(i > 0){
				sql.append(" , ");
			}
			sql.append(id);
			i++;
		}
		sql.append(")");
		return sql.toString();
	}
}