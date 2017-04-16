package com.crystalcg.gamedev.staticdata.dao;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.crystalcg.gamedev.model.TableHeader;
import com.crystalcg.gamedev.utils.AppException;
@Repository
public interface StaticDataDao {
	/**
	 * 获得所有的静态表表名（带注释）
	 * @return
	 */
	List<TableHeader> getAllTables();
	/**
	 * 根据表明获得表结构信息
	 * @param tableName
	 * @return
	 */
	List<Map<String, Object>> getCommentByTableName(String tableName);
	/**
	 * 根据表明获得表信息
	 * @param tableName
	 * @return
	 */
	List<Map<String, Object>> getResultByTableName(String tableName, Map<String, Object> map);
	/**
	 * 根据道具编号获得道具详情
	 * @param tableName
	 * @return
	 */
	Map<String, Object> getItemByItemNo(String itemNo);
	/**
	 * 添加表数据
	 * @param obj
	 * @param tableName
	 */
	void addTableResult(Map<String, Object> obj, String tableName) throws AppException;
	/**
	 * 修改表数据
	 * @param obj
	 * @param tableName
	 */
	void updateTable(Map<String, Object> obj, String tableName) throws AppException;
	/**
	 * 批量处理update-savesql
	 * @param mapList
	 * @param tableName
	 */
	void updateTableForJson(List<Map<String, Object>> mapList, String tableName) throws AppException;
	/**
	 * 根据id删除数据
	 * @param id
	 * @param tableName
	 */
	void deleteResultById(int id, String tableName);
	/**
	 * 根据装备编号获得装备信息
	 * @param itemNo
	 * @return
	 */
	Map<String, Object> getEquipmentByItemNo(String itemNo);
	/**
	 * 根据材料编号获得材料信息
	 * @param itemNo
	 * @return
	 */
	Map<String, Object> getMaterialByItemNo(String itemNo);
	/**
	 * 根据任务道具编号查询任务道具详情
	 * @param string
	 * @return
	 */
	Map<String, Object> getQuestItemByItemNo(String string);
	/**
	 * 根据参数获得相应表数据
	 * @param parameterMap
	 * @return
	 */
	Map<String, Object> searchItemList(Map<String, Object> parameterMap);
	/**
	 * 清空数据表
	 * @param tableName
	 */
	void clearTable(String tableName);
	/**
	 * 插入json格式的数据到数据库
	 * @param mapList
	 * @param tableName
	 */
	void insertDb(List<Map<String, Object>> mapList, String tableName) throws AppException;

}
