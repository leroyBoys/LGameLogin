package com.crystalcg.gamedev.manage.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import com.crystalcg.gamedev.utils.AppException;


@Controller
public interface FileService{
	/**�ϴ��ļ�* @return ***/
	public String upLoad(MultipartFile file)throws Exception ;
	public List<Map<String,Object>> getResult(String tableName, String fileName)  throws IOException, AppException;
	/******ɾ���ļ�****/
	public void delFile(String fileName);
	/*********У������*********/
	public Map<String,Object> verifyData(String tableName, List<Map<String, Object>> mapList)throws AppException;
	public void createExcel(String tableName, OutputStream out)  throws AppException ;
}
