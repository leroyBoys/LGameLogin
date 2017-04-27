package com.lgame.manage.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;

import com.lgame.base.action.BaseAction;
import com.lgame.model.TableHeader;
import com.lgame.utils.AppException;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgame.staticdata.service.StaticDataService;
import com.lgame.utils.DataFormat;
import com.lgame.utils.StringUtil;


@Controller
@RequestMapping("/db")
public class StaticDataAction extends BaseAction {

	@Autowired
	private StaticDataService staticDataService;

	/**
	 * 获得所有数据库的表结构
	 * @return
	 */
	@RequestMapping("/getAlltable")
	@ResponseBody
	public Object getAllTable(){
		List<TableHeader> tables = staticDataService.getAllTables();
		return tables;
	}
	/**
	 * 返回对应表的数据结构并返回前台
	 * @param tableName
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/getTable/{tableName}")
	public String getTable(@PathVariable String tableName) throws UnsupportedEncodingException{
		List<Map<String,Object>> commentList = staticDataService.getCommentByTableName(tableName);//表结构信息
		request.setAttribute("commentList",commentList);
		request.setAttribute("url","db/getTableResult/"+tableName);
		request.setAttribute("delUrl","db/deleteResult/"+tableName);
		request.setAttribute("saveUrl","db/updateTable/"+tableName);
		request.setAttribute("toExcel", "toExcel/"+tableName);
		request.setAttribute("tableName",tableName);
		return "staticDb/staticDb";
	}
	/**
	 * 获得对应表的数据
	 * @param tableName
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/getTableResult/{tableName}")
	@ResponseBody
	public Object getTableResult(@PathVariable String tableName) throws UnsupportedEncodingException{
		List<Map<String,Object>> resultList = staticDataService.getResultByTableName(tableName,DataFormat.decodeMap(this.getParameterMap("static_db_")));//表信息
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("total", resultList.size());
		map.put("rows", resultList);
		return map;
	}
	/**
	 * 更新数据库(如果id为0则插入)
	 * @param tableName
	 * @return
	 * @throws AppException
	 * @throws IOException
	 */
	@RequestMapping("/updateTable/{tableName}")
	@ResponseBody
	public Object updateTableForJson(@PathVariable String tableName) throws AppException, IOException{
		tableName = staticDataService.getTableNameByTitle(tableName);
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(ServletInputStream) request.getInputStream()));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		JSONArray jsonArray = JSONArray.fromObject(DataFormat.decodeString(sb.toString()));
		List<Map<String,Object>> mapList = DataFormat.fromJsonToMapList(jsonArray);
		if(mapList.size() >= 10){
			throw new AppException("同时修改不要超过10条数据");
		}
		staticDataService.updateTableForJson(mapList,tableName);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("status", "succ");
		return map;
	}
	/**
	 * 纯插入数据
	 * @param tableName
	 * @return
	 * @throws AppException
	 * @throws IOException
	 */
	@RequestMapping("/insertDb/{tableName}")
	@ResponseBody
	public Object insertDb(@PathVariable String tableName) throws AppException, IOException{
		tableName = staticDataService.getTableNameByTitle(tableName);
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(ServletInputStream) request.getInputStream()));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		JSONArray jsonArray = JSONArray.fromObject(DataFormat.decodeString(sb.toString()));
		List<Map<String,Object>> mapList = DataFormat.fromJsonToMapList(jsonArray);
		staticDataService.insertDb(mapList,tableName);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("status", "succ");
		return map;
	}
	/**
	 * 清空数据表
	 * @param tableName
	 * @return
	 * @throws AppException
	 * @throws IOException
	 */
	@RequestMapping("/clearTable/{tableName}")
	@ResponseBody
	public Object clearTable(@PathVariable String tableName) throws AppException, IOException{
		tableName = staticDataService.getTableNameByTitle(tableName);
		staticDataService.clearTable(tableName);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("status", "succ");
		return map;
	}
	/**
	 * 更新数据库
	 * @param tableName
	 * @return
	 * @throws AppException
	 * @throws IOException
	 */
	@RequestMapping("/deleteResult/{tableName}")
	@ResponseBody
	public Object deleteResult(@PathVariable String tableName) throws AppException, IOException{
		String id = this.getParameter("id");
		if(StringUtil.isEmpty(id)){
			throw new AppException("参数错误");
		}
		tableName = staticDataService.getTableNameByTitle(tableName);
		staticDataService.deleteResultById(Integer.parseInt(id),tableName);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("status", "succ");
		return map;
	}
	@RequestMapping("/toSearchItem/{itemType}/{limit}")
	public String searchItem(@PathVariable int itemType,@PathVariable String limit){
		request.setAttribute("url","db/searchItem/"+itemType+"/"+limit);
		return "staticDb/searchItem";
	}
	/**
	 * @return
	 * @throws AppException
	 * @throws IOException
	 */
	@RequestMapping("/searchItem/{itemType}/{limit}")
	@ResponseBody
	public Object searchItemList(@PathVariable String itemType,@PathVariable String limit) throws AppException, IOException{
		Map<String,Object> parameterMap =  DataFormat.decodeMap(this.getParameterMap("search_"));
		String rows = this.getParameter("rows");
		parameterMap.put("limit", limit);
		parameterMap.put("itemType", itemType);
		if(rows != null){
			parameterMap.put("pageSize", rows);
			parameterMap.put("page", this.getParameter("page"));
		}
		Map<String,Object> map = staticDataService.searchItemList(parameterMap);
		map.put("status", "succ");
		return map;
	}
	@RequestMapping("/getItemEntity/{itemType}/{itemNo}")
	public String getItemEntity(@PathVariable int itemType,@PathVariable String itemNo){
		Map<String,Object> map = staticDataService.getItemEntity(itemType,itemNo);
		request.setAttribute("item", map);
		return "staticDb/itemDetail";
	}
	/**
	 * @return
	 * @throws AppException
	 * @throws IOException
	 */
	@RequestMapping("/getEquipmentStrength")
	@ResponseBody
	public Object getEquipmentStrength(Integer level,String equipmentNo) throws AppException, IOException{
		Map<String, Integer>  parameterMap =  staticDataService.getEquipmentStrength(level,equipmentNo);
		return parameterMap;
	}
}