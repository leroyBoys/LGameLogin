package com.crystalcg.gamedev.manage.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.crystalcg.gamedev.base.action.BaseAction;
import com.crystalcg.gamedev.manage.service.FileService;
import com.crystalcg.gamedev.staticdata.service.StaticDataService;
import com.crystalcg.gamedev.utils.AppException;
import com.crystalcg.gamedev.utils.DataFormat;


@Controller
public class FileAction extends BaseAction{
	@Autowired
	private StaticDataService staticDataService;
	@Autowired
	private FileService fileService;

	@RequestMapping(value = "/upload")
	@ResponseBody
	public String upload(@RequestParam  MultipartFile file) throws Exception {
		//String path = request.getSession().getServletContext().getRealPath("static/temp");
		return fileService.upLoad(file);
	}
	@RequestMapping(value = "/delFile")
	@ResponseBody
	public Map<String,Object> delFile(String fileName) {
		fileService.delFile(fileName);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("status", "succ");
		return map;
	}
	@RequestMapping(value = "/importDb")
	public String importDb(String tableName,String fileName) throws AppException, IOException {
		List<Map<String,Object>> tableCommets = staticDataService.getCommentByTableName(tableName);
		if(tableCommets == null || tableCommets.isEmpty()){
			throw new AppException("不存在该数据库");
		}
		request.setAttribute("commentList", tableCommets);
		request.setAttribute("saveUrl","db/insertDb/"+tableName);
		request.setAttribute("clearUrl","db/clearTable/"+tableName);
		request.setAttribute("readCsv", "readCsv/"+tableName+"/"+fileName);
		request.setAttribute("verifyData", "verifyData/"+tableName);
		return "staticDb/importDb";
	}
	@RequestMapping(value = "/readCsv/{tableName}/{fileName}")
	@ResponseBody
	public Object readCsv(@PathVariable String tableName,@PathVariable String fileName) throws AppException, IOException {
		List<Map<String,Object>> resultList =  fileService.getResult(tableName,fileName);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("total", resultList.size());
		map.put("rows", resultList);
		return map;
	}
	@RequestMapping(value = "/verifyData/{tableName}")
	@ResponseBody
	public Object verifyData(@PathVariable String tableName) throws AppException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(ServletInputStream) request.getInputStream()));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		JSONArray jsonArray = JSONArray.fromObject(DataFormat.decodeString(sb.toString()));
		List<Map<String,Object>> mapList = DataFormat.fromJsonToMapList(jsonArray);
		Map<String,Object> result = fileService.verifyData(tableName,mapList);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("status", "succ");
		map.put("errorLine", result.get("error"));
		map.put("errorMsg", result.get("msg"));
		return map;
	}
	@RequestMapping(value = "/toExcel/{tableName}")
	public void toExcel(@PathVariable String tableName,HttpServletResponse response) throws AppException, IOException {
		response.setContentType("application/vnd.ms-excel");
		response.setContentType("UTF-8");
		response.addHeader("Content-Disposition", "attachment;filename="+ tableName+".xls");
		fileService.createExcel(tableName,response.getOutputStream());
	}

}
