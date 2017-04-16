package com.crystalcg.gamedev.staticdata.service;


import com.crystalcg.gamedev.model.TableHeader;
import com.crystalcg.gamedev.staticdata.dao.StaticDataDao;
import com.crystalcg.gamedev.utils.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@Service
public class StaticDataServiceImpl implements StaticDataService{
	@Autowired
	private StaticDataDao StaticDataDao;
	/**
	 * 获得所有的静态表表名（带注释）
	 * @return
	 */
	public List<TableHeader> getAllTables() {
		return StaticDataDao.getAllTables();
	}
	/**
	 * 根据表明获得表结构信息
	 * @param tableName
	 * @return
	 */
	public List<Map<String, Object>> getCommentByTableName(String tableName) {

		return StaticDataDao.getCommentByTableName(tableName);
	}
	/**
	 * 根据表明获得表信息
	 * @param tableName
	 * @return
	 */
	public List<Map<String,Object>> getResultByTableName(String tableName,Map<String,Object> map) {
		return StaticDataDao.getResultByTableName(tableName,map);
	}
	@Override
	public void updateTableForJson(List<Map<String, Object>> mapList,String tableName) throws AppException {
		StaticDataDao.updateTableForJson(mapList,tableName);
	}
	public void updateTable(Map<String,Object> obj,String tableName) throws AppException{
		if(obj.get("id") == null){
			throw new AppException(tableName+"表没有id，无法进行修改");
		}
		StaticDataDao.updateTable(obj,tableName);
	}
	@Override
	public void deleteResultById(int id, String tableName) {
		StaticDataDao.deleteResultById(id,tableName);
	}
	/**
	 * 根据表头信息查询数据表名
	 * @param tableName
	 * @return
	 */
	public String getTableNameByTitle(String tableName){
		TableHeader tableByComm = null;
		for(TableHeader table:this.getAllTables()){
			if(table.getName().equals(tableName)){
				return table.getName();
			}else if(table.getComment().equals(tableName)){
				tableByComm = table;
			}
		}
		return tableByComm.getName();
	}
	@Override
	public Map<String, Object> searchItemList(Map<String, Object> parameterMap) {
		return StaticDataDao.searchItemList(parameterMap);
	}
	@Override
	public Map<String, Object> getItemEntity(int itemType, String itemNo) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("search_type", "1");
		map.put("conditionName", "item_no");
		String tableName = "static_material";

		map.put("conditionValue", itemNo);
		List<Map<String,Object>> result = getResultByTableName(tableName, map);
		if(result.isEmpty()){
			return null;
		}
		List<Map<String,Object>> commentresult = getCommentByTableName(tableName);
		Map<String,Object> detail = new LinkedHashMap<String, Object>();
		for(Map<String,Object> comm:commentresult){
			detail.put(((String)comm.get("comment")).isEmpty()?(String)comm.get("name"):(String)comm.get("comment"), result.get(0).get(comm.get("name")));
		}

		return detail;
	}
	@Override
	public Map<String, Integer> getEquipmentStrength(Integer level,String equipmentNo) {
		if(level==null || level<=0){
			return null;
		}
		Map<String,Object> equipment = StaticDataDao.getEquipmentByItemNo(equipmentNo);
		if(equipment == null || equipment.isEmpty()){
			return null;
		}
		List<Map<String,Object>> strengthList = this.getResultByTableName("static_strengthen_equipment", new HashMap<String, Object>());
		int hero_force = 0;
		int strategy = 0;
		int physique = 0;
		int agility = 0;
		Map<String,Integer> map = new HashMap<String, Integer>();
		if(equipment.get("hero_force") != null && !equipment.get("hero_force").equals("0")){
			hero_force = Integer.valueOf((String)equipment.get("hero_force"));
		}
		if(equipment.get("strategy") != null && !equipment.get("strategy").equals("0")){
			strategy = Integer.valueOf((String)equipment.get("strategy"));
		}
		if(equipment.get("physique") != null && !equipment.get("physique").equals("0")){
			physique = Integer.valueOf((String)equipment.get("physique"));
		}
		if(equipment.get("agility") != null && !equipment.get("agility").equals("0")){
			agility = Integer.valueOf((String)equipment.get("agility"));
		}
		return map;
	}
	@Override
	public void clearTable(String tableName) {
		StaticDataDao.clearTable(tableName);

	}
	@Override
	public void insertDb(List<Map<String, Object>> mapList, String tableName) throws AppException {
		StaticDataDao.insertDb(mapList,tableName);
	}
}
