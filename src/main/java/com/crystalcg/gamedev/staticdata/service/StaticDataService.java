package com.crystalcg.gamedev.staticdata.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.crystalcg.gamedev.model.TableHeader;
import com.crystalcg.gamedev.utils.AppException;
@Service
public interface StaticDataService {
	/**
	 * 获得所有数据库的表结构
	 * @return
	 */
	public List<TableHeader> getAllTables();
	/**
	 * 获得对应表的数据结构
	 * @param tableName
	 * @return
	 */
	public List<Map<String, Object>> getCommentByTableName(String tableName);
	/**
	 * 获得对应表的数据
	 * @param tableName
	 * @param parameterMap
	 * @return
	 */
	public List<Map<String, Object>> getResultByTableName(String tableName, Map<String, Object> parameterMap);
	/**
	 * 更新json格式串（N条）的数据到数据库
	 * @param mapList
	 * @param tableName
	 */
	public void updateTableForJson(List<Map<String, Object>> mapList, String tableName) throws AppException;
	/**
	 * 更新对应数据库的一条数据
	 * @param obj
	 * @param tableName
	 */
	public void updateTable(Map<String, Object> obj, String tableName) throws AppException;
	/**
	 * 根据id删除数据
	 * @param id
	 * @param tableName
	 */
	public void deleteResultById(int id, String tableName);
	/**
	 * 根据表头信息查询数据表名
	 * @param tableName
	 * @return
	 */
	public String getTableNameByTitle(String tableName);
	/**
	 * 根据参数获得相应表的相应数据列表
	 * @param parameterMap
	 * @return
	 */
	public Map<String, Object> searchItemList(Map<String, Object> parameterMap);
	/**
	 * 根据物品道具类型和编号获得道具信息
	 * @param itemType
	 * @param itemNo
	 * @return
	 */
	public Map<String, Object> getItemEntity(int itemType, String itemNo);
	/**
	 * 根据强化等级获得强化数据
	 * @param level
	 * @return
	 */
	public Map<String, Integer> getEquipmentStrength(Integer level, String equipmentNo);
	/**
	 * 清空数据表
	 * @param tableName
	 */
	public void clearTable(String tableName);
	/**
	 * 插入json格式串（N条）的数据到数据库
	 * @param mapList
	 * @param tableName
	 */
	public void insertDb(List<Map<String, Object>> mapList, String tableName) throws AppException;
}
