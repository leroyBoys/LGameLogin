package com.lgame.staticdata.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.lgame.model.TableHeader;
import com.lgame.utils.AppException;
import com.lgame.utils.enums.MailItemType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lgame.base.dao.BaseDao;
import com.lgame.utils.Const;
import com.lgame.utils.StringUtil;
import com.lgame.utils.enums.ItemType;


@Repository
public class StaticDataDaoImpl extends BaseDao implements StaticDataDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static Logger logger = Logger.getLogger(StaticDataDaoImpl.class);

	public List<TableHeader> getAllTables(){
		return jdbcTemplate.execute("{call pr_static_all_table()}", new CallableStatementCallback<List<TableHeader>>() {
			@Override
			public List<TableHeader> doInCallableStatement(CallableStatement cs) {
				List<TableHeader> list = new LinkedList<TableHeader>();
				try {
					ResultSet rs = cs.executeQuery();
					TableHeader temp;
					while(rs.next()){
						temp = new TableHeader();
						temp.setName(rs.getString("TABLE_NAME"));
						temp.setComment(rs.getString("TABLE_COMMENT"));
						temp.setRows(rs.getInt("TABLE_ROWS"));
						list.add(temp);
					}
				} catch (Exception e) {
					logger.error("pr_static_all_table():",e);
				}
				return list;
			}
		});
	}
	/**
	 * 根据表明获得表结构信息
	 * @param tableName
	 * @return
	 */
	public List<Map<String, Object>> getCommentByTableName(final String tableName) {
		return this.getCommentByTableName(jdbcTemplate, "call pr_static_get_table_comment(?)", tableName);
	}
	/**
	 * 获得表信息
	 * @param tableName
	 * @return
	 */
	public List<Map<String,Object>> getResultByTableName(final String tableName,final Map<String,Object> map) {
		return jdbcTemplate.execute("{call pr_static_get_table_result(?,?,?,?)}", new CallableStatementCallback<List<Map<String,Object>>>() {
			@Override
			public List<Map<String,Object>> doInCallableStatement(CallableStatement cs) {
				List<Map<String,Object>> list = new LinkedList<Map<String,Object>>();
				try {
					cs.setString(1, tableName);
					cs.setString(2, (String)map.get("conditionName"));
					cs.setString(3, (String)map.get("conditionValue"));
					cs.setString(4, (String)map.get("search_type"));
					ResultSet rs = cs.executeQuery();
					ResultSetMetaData rsd = rs.getMetaData();
					Map<String,Object> colComment;
					while(rs.next()){
						colComment = new LinkedHashMap<String, Object>();
						for(int i=1;i<=rsd.getColumnCount();i++){
							colComment.put(rsd.getColumnLabel(i), rs.getString(i) == null?Const.NULLDATA:rs.getString(i));
							if(("TIMESTAMP").equals(rsd.getColumnTypeName(i))){
								colComment.put(rsd.getColumnLabel(i), rs.getString(i) == null?Const.NULLDATA:rs.getString(i).substring(0, rs.getString(i).lastIndexOf(".")));
							}
						}
						list.add(colComment);
					}
				} catch (Exception e) {
					logger.error("pr_static_get_table_result:",e);
				}
				return list;
			}
		});
	}
	@Override
	public void addTableResult(Map<String, Object> obj, String tableName) throws AppException {
		if(obj.isEmpty()){
			return;
		}
		try{
			jdbcTemplate.execute(this.getAddSql(obj, tableName));
		}catch(Exception e){
			logger.error("数据添加出错:",e);
		}
	}
	@Override
	public void updateTable(Map<String, Object> obj, String tableName) throws AppException{
		if(obj.isEmpty()){
			return;
		}
		try{
			jdbcTemplate.execute(this.getUpdateSql(obj, tableName));
		}catch(Exception e){
			logger.error("数据更新出错:"+e);
		}
	}
	@Override
	public void updateTableForJson(List<Map<String, Object>> mapList,String tableName) throws AppException {
		if(mapList == null ||mapList.isEmpty()){
			return;
		}
		String[] sqlArray = new String[mapList.size()];
		int i = 0;
		for(Map<String, Object> map:mapList){
			if(map.get("id") == null){//添加
				sqlArray[i] = this.getAddSql(map, tableName);
			}else{//修改
				sqlArray[i] = this.getUpdateSql(map, tableName);
			}
			i++;
		}
		try{
			jdbcTemplate.batchUpdate(sqlArray);
		}catch(Exception e){
			logger.error("批量更新出错:"+e);
			throw new AppException("数据格式错误!");
		}
	}
	@Override
	public void deleteResultById(int id, String tableName) {
		jdbcTemplate.execute("call pr_static_del_table_result('"+tableName+"',"+id+")");
	}
	@Override
	public Map<String, Object> getItemByItemNo(final String itemNo) {
		return this.execute(jdbcTemplate, "{call pr_get_item_by_item_no(?)}", itemNo);
	}
	@Override
	public Map<String, Object> getEquipmentByItemNo(final String itemNo) {
		return this.execute(jdbcTemplate, "{call pr_get_equipment_by_item_no(?)}", itemNo);
	}
	@Override
	public Map<String, Object> getMaterialByItemNo(final String itemNo) {
		return this.execute(jdbcTemplate, "{call pr_get_material_by_item_no(?)}", itemNo);
	}
	@Override
	public Map<String, Object> getQuestItemByItemNo(String itemNo) {
		return this.execute(jdbcTemplate, "{call pr_get_quest_item_by_item_no(?)}", itemNo);
	}
	@Override
	public Map<String, Object> searchItemList(final Map<String, Object> parameterMap) {
		final Map<String, Object> pageMap = new HashMap<String, Object>();
		final StringBuilder sqlWhere = new StringBuilder();
		sqlWhere.append(" 1 = 1 ");
		if(!StringUtil.isEmpty((String)parameterMap.get("item_name"))){
			if(MailItemType.equipment.getValue() ==  Integer.parseInt((String)parameterMap.get("itemType"))){
				sqlWhere.append(" and ").append(" equipment_name like '%").append((String)parameterMap.get("item_name")).append("%' ");
			}else if(MailItemType.material.getValue() ==  Integer.parseInt((String)parameterMap.get("itemType"))){
				sqlWhere.append(" and ").append(" material_name like '%").append((String)parameterMap.get("item_name")).append("%' ");
			}else{
				sqlWhere.append(" and ").append(" item_name like '%").append((String)parameterMap.get("item_name")).append("%' ");
			}
		}
		if(MailItemType.item.getValue() ==  Integer.parseInt((String)parameterMap.get("itemType"))){
			if("money".equals(parameterMap.get("limit"))){
				sqlWhere.append(" and ").append(" use_effect_type in (").append(ItemType.money.getValue()).append(",").append(ItemType.cash.getValue()).append(",").append(ItemType.ticket.getValue()).append(")");
			}else{
				sqlWhere.append(" and ").append(" use_effect_type not  in(").append(ItemType.money.getValue()).append(",").append(ItemType.cash.getValue()).append(",").append(ItemType.ticket.getValue()).append(")");;
			}
		}

		List<Map<String, Object>> resultMap = jdbcTemplate.execute("{call pr_get_search_pages(?,?,?,?,?)}", new CallableStatementCallback<List<Map<String,Object>>>() {
			@Override
			public List<Map<String,Object>> doInCallableStatement(CallableStatement cs) {
				List<Map<String,Object>> list = new LinkedList<Map<String,Object>>();
				try {
					cs.setString(1, (String)parameterMap.get("itemType"));
					cs.setString(2, sqlWhere.toString());
					cs.setString(3, null);
					cs.setString(4,(String)parameterMap.get("pageSize"));
					cs.setString(5,(String)parameterMap.get("page"));
					ResultSet rs = cs.executeQuery();
					Map<String,Object> colComment;
					ResultSetMetaData rsd = rs.getMetaData();
					while(rs.next()){
						colComment = new HashMap<String, Object>();
						for(int i=1;i<=rsd.getColumnCount();i++){
							colComment.put(rsd.getColumnLabel(i),rs.getString(i));
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
					logger.error("pr_get_search_pages:",e);
				}
				return list;
			}
		});
		pageMap.put("rows", resultMap);

		return pageMap;
	}
	@Override
	public void clearTable(String tableName) {
		jdbcTemplate.execute("delete from "+tableName);
	}
	@Override
	public void insertDb(List<Map<String, Object>> mapList, String tableName) throws AppException {
		if(mapList == null ||mapList.isEmpty()){
			return;
		}
		String[] sqlArray = new String[mapList.size()];
		int i = 0;
		for(Map<String, Object> map:mapList){
			if(map.get("id") == null){
				map.put("id",i+1);
			}
			sqlArray[i] = this.getAddSql(map, tableName);
			i++;
		}
		try{
			jdbcTemplate.batchUpdate(sqlArray);
		}catch(Exception e){
			logger.error("批量更新出错:"+e);
			throw new AppException("数据格式错误!");
		}
	}
}
