package com.lgame.manage.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.lgame.manage.service.FileService;
import com.lgame.utils.AppException;
import com.lgame.utils.DataUtil;
import com.lgame.utils.WDWUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import com.lgame.staticdata.service.StaticDataService;
import com.lgame.utils.Const;
import com.lgame.utils.StringUtil;


@Controller
public class FileServiceImpl implements FileService {
	@Autowired
	private StaticDataService staticDataService;
	private String path;
	private FileServiceImpl() {
		path = System.getProperty("user.dir")+"/temp";
		File targetFile = new File(path);
		if(!targetFile.exists()){
			targetFile.mkdirs();
		}
		System.out.println("路径==+"+path);
	}

	@Override
	public String upLoad(MultipartFile file) throws Exception {
		String fileName = file.getOriginalFilename();
		UUID uuid = UUID.randomUUID();
		String newName = uuid.getLeastSignificantBits()+fileName.substring(fileName.lastIndexOf("."));
		File targetFile = new File(path, newName);
		if(!targetFile.exists()){
			targetFile.mkdirs();
		}
		file.transferTo(targetFile);
		return newName;
	}
	@Override
	public void delFile(String fileName) {
		File targetFile = new File(path, fileName);
		if(targetFile.exists()){
			targetFile.delete();
		}
	}
	@Override
	public List<Map<String, Object>> getResult(String tableName, String fileName)throws IOException, AppException {
		File targetFile = new File(path);
		String FileName = null;
		if(!targetFile.exists()){
			targetFile.mkdirs();
		}
		String[] fileNames = targetFile.list();
		for(String file:fileNames){
			if(file.startsWith(fileName)){
				FileName = file;
				break;
			}
		}
		if(FileName == null){
			throw new AppException("文件不存在");
		}
		/** 判断文件的类型，是2003还是2007 */
		File file = new File(path,FileName);
		List<Map<String,Object>> tableCommets = staticDataService.getCommentByTableName(tableName);
		if(tableCommets == null || tableCommets.isEmpty()){
			file.delete();
			throw new AppException("不存在该数据库");
		}
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		Map<String,Object> obj = null;
		InputStream inputStream = null;
		boolean isExcel2003 = true;
		if (WDWUtil.isExcel2007(FileName)) {
			isExcel2003 = false;
		}else if(!WDWUtil.isExcel2003(FileName)){
			file.delete();
			throw new AppException("文件格式错误,请选择xls或xlsx格式文件");
		}
		String error = null;
		try {
			/** 调用本类提供的根据流读取的方法 */
			inputStream = new FileInputStream(file);
			Workbook wb = isExcel2003 ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
			/** 得到第一个shell */
			Sheet sheet = wb.getSheetAt(0);
			/** 得到Excel的行数 */
			int totalRows = sheet.getPhysicalNumberOfRows();
			/** 得到Excel的列数 */
			int totalCells = 0;
			if (totalRows >= 1 && sheet.getRow(0) != null) {
				totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
			}
			/** 循环Excel的行 */
			for (int r = 0; r < totalRows; r++) {
				Row row = sheet.getRow(r);
				if (row == null) {
					continue;
				}
				obj = new HashMap<String, Object>();
				/** 循环Excel的列 */
				for (short c = 0; c < totalCells; c++) {
					Cell cell = row.getCell(c);
					String cellValue = "";
					if (cell != null) {
						/** 处理Excel的字符串 */
						switch (cell.getCellType()) {
							case HSSFCell.CELL_TYPE_STRING:
								cellValue = cell.getStringCellValue();
								break;
							case HSSFCell.CELL_TYPE_NUMERIC:
								if (HSSFDateUtil.isCellDateFormatted(cell)) {
									Date date = cell.getDateCellValue();
									if (date != null) {
										cellValue = new SimpleDateFormat("yyyy-MM-dd").format(date);
									} else {
										cellValue = "";
									}
								} else {
									cellValue = new DecimalFormat("#.##").format(cell.getNumericCellValue());
								}
								break;
							case HSSFCell.CELL_TYPE_FORMULA:
								// 导入时如果为公式生成的数据则无值
								if (HSSFDateUtil.isCellDateFormatted(cell)){
									// 如果是Date类型则，取得该Cell的Date值
									Date date = cell.getDateCellValue();
									// 把Date转换成本地格式的字符串
									cellValue =  new SimpleDateFormat("yyyy-MM-dd").format(date);
								}else {// 如果是纯数字
									// 取得当前Cell的数值
									double num = new Double((double)cell.getNumericCellValue());
									cellValue = String.valueOf(num);
								}
								break;
							case HSSFCell.CELL_TYPE_BLANK:
								break;
							case HSSFCell.CELL_TYPE_ERROR:
								cellValue = "";
								break;
							case HSSFCell.CELL_TYPE_BOOLEAN:
								cellValue = (cell.getBooleanCellValue() == true ? "Y": "N");
								break;
							default:
								cellValue = "";
						}
					}
					if(c + 1 >= tableCommets.size()){
						if(obj.get(":dataError") == null){
							obj.put(":dataError",cellValue);
						}else{
							obj.put(":dataError",obj.get(":dataError") + cellValue);
						}
						continue;
					}
					obj.put((String)tableCommets.get(c+1).get("name"), cellValue);;
				}
				/** 保存第r行的第c列 */
				result.add(obj);
			}
			inputStream.close();
		} catch (Exception ex) {
			error = "数据异常,请重新确认选择的数据表和要导入的数据一致";
			ex.printStackTrace();
		} finally {
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					inputStream = null;
					e.printStackTrace();
				}
			}
			file.delete();
		}
		/** 返回最后读取的结果 */
		if(error!=null){
			throw new AppException(error);
		}
		return result;
	}

	@Override
	public Map<String,Object> verifyData(String tableName,List<Map<String, Object>> mapList) throws AppException {
		Map<String,Object> map  = new HashMap<String, Object>();
		List<Integer> error = new ArrayList<Integer>();
		List<String> strError = new ArrayList<String>();
		map.put("error",error);
		map.put("msg",strError);
		if(mapList == null || mapList.isEmpty()){
			return map;
		}
		List<Map<String,Object>> tableCommets = staticDataService.getCommentByTableName(tableName);
		if(tableCommets == null || tableCommets.isEmpty()){
			throw new AppException("不存在该数据库");
		}
		Map<String,Map<String,Object>> commentList = new HashMap<String, Map<String,Object>>();
		for(Map<String,Object> obj:tableCommets){
			commentList.put(String.valueOf(obj.get("name")),obj);
		}

		int line = 0;
		String dataType = "";
		String value = "";
		Map<String, Object> comment = new HashMap<String, Object>();
		for(Map<String, Object> obj:mapList){
			for(String name:obj.keySet()){
				value = String.valueOf(obj.get(name));
				comment =  commentList.get(name);
				if(":dataError".equals(name) && StringUtil.isEmpty(value)){
					continue;
				}else if(comment == null){
					if(error.size() > 3){
						throw new AppException("异常数据(最后一列)超过3条,请检验选择的数据表与导入的数据是否一致");
					}
					error.add(line);
					strError.add("第"+line+"行"+name+"列在数据库中不存在");
					break;
				}
				dataType = String.valueOf(comment.get("dataType"));
				if(value.equals("null") || value.equals(Const.NULLDATA)){
					if(!(Boolean) comment.get("isNull")){
						throw new AppException("字段:"+comment.get("comment")+"("+comment.get("name")+")"+"不能为:"+value);
					}
					continue;
				}else if(!DataUtil.verifyData(dataType,String.valueOf(obj.get(name)))){
					error.add(line);
					strError.add("第"+line+"行"+name+"列:"+obj.get(name)+"("+dataType+");数据格式不正确");
					break;
				}
			}
			line++;
		}
		return map;
	}

	@Override
	public void createExcel(String tableName,OutputStream out) throws AppException {
		List<Map<String,Object>> tableCommets = staticDataService.getCommentByTableName(tableName);
		if(tableCommets == null || tableCommets.isEmpty()){
			throw new AppException("不存在该数据库");
		}
		List<Map<String, Object>> mapList = staticDataService.getResultByTableName(tableName, new HashMap<String, Object>());
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(tableName);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth(15);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 10);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		HSSFRow row  = sheet.createRow(0);;
		HSSFCell cell = null;
		int headCell = 0;
		for(Map<String,Object> comm:tableCommets){
			sheet.autoSizeColumn(1);
			cell = row.createCell(headCell);
			cell.setCellStyle(style);
			cell.setCellValue(comm.get("comment")+"("+comm.get("name")+")");
			headCell++;
		}
		int i = 1;
		for(Map<String, Object> obj:mapList){
			row = sheet.createRow(i);
			int cellSize = 0;
			for(Map<String,Object> comm:tableCommets){
				String name = (String) comm.get("name");
				if(name.equalsIgnoreCase("id")){
					continue;
				}else if(obj.get(name) != null){
					cell = row.createCell(cellSize);
					//设置单元格内容为字符串型
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(String.valueOf(obj.get(name)));
					cellSize++;
				}
			}
			i++;
		}
		try {
			workbook.write(out);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					out = null;
					e.printStackTrace();
				}
			}
		}

	}
}
