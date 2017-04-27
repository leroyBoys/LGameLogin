package com.lgame.manage.service;

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
public class FileServiceImpl implements FileService{
	@Autowired
	private StaticDataService staticDataService;
	private String path;
	private FileServiceImpl() {
		path = System.getProperty("user.dir")+"/temp";
		File targetFile = new File(path);
		if(!targetFile.exists()){  
	          targetFile.mkdirs();  
	    }  
		System.out.println("·��==+"+path);
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
			throw new AppException("�ļ�������");
		}
		/** �ж��ļ������ͣ���2003����2007 */
		File file = new File(path,FileName);
		List<Map<String,Object>> tableCommets = staticDataService.getCommentByTableName(tableName);
		 if(tableCommets == null || tableCommets.isEmpty()){
			 file.delete();
			 throw new AppException("�����ڸ����ݿ�");  
		 }
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		Map<String,Object> obj = null;
		InputStream inputStream = null;
		boolean isExcel2003 = true;
		if (WDWUtil.isExcel2007(FileName)) {
			isExcel2003 = false;
		}else if(!WDWUtil.isExcel2003(FileName)){
			file.delete();
			throw new AppException("�ļ���ʽ����,��ѡ��xls��xlsx��ʽ�ļ�");
		}
		String error = null;
		try {
			/** ���ñ����ṩ�ĸ�������ȡ�ķ��� */
			inputStream = new FileInputStream(file);
			Workbook wb = isExcel2003 ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
			/** �õ���һ��shell */
			Sheet sheet = wb.getSheetAt(0);
			/** �õ�Excel������ */
			int totalRows = sheet.getPhysicalNumberOfRows();
			/** �õ�Excel������ */
			int totalCells = 0;
			if (totalRows >= 1 && sheet.getRow(0) != null) {
				totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
			}
			/** ѭ��Excel���� */
			for (int r = 0; r < totalRows; r++) {
				Row row = sheet.getRow(r);
				if (row == null) {
					continue;
				}
				obj = new HashMap<String, Object>();
				/** ѭ��Excel���� */
				for (short c = 0; c < totalCells; c++) {
					Cell cell = row.getCell(c);
					String cellValue = "";
					if (cell != null) {
						/** ����Excel���ַ��� */
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
		                         // ����ʱ���Ϊ��ʽ���ɵ���������ֵ
		                    	 if (HSSFDateUtil.isCellDateFormatted(cell)){
		                            // �����Date������ȡ�ø�Cell��Dateֵ
		                            Date date = cell.getDateCellValue();
		                            // ��Dateת���ɱ��ظ�ʽ���ַ���
		                            cellValue =  new SimpleDateFormat("yyyy-MM-dd").format(date);
		                         }else {// ����Ǵ�����
		                            // ȡ�õ�ǰCell����ֵ
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
				/** �����r�еĵ�c�� */
				result.add(obj);
			}
			inputStream.close();
		} catch (Exception ex) {
			error = "�����쳣,������ȷ��ѡ������ݱ��Ҫ���������һ��";
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
		/** ��������ȡ�Ľ�� */
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
			throw new AppException("�����ڸ����ݿ�");  
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
						throw new AppException("�쳣����(���һ��)����3��,�����ѡ������ݱ��뵼��������Ƿ�һ��");
					}
					error.add(line);
					strError.add("��"+line+"��"+name+"�������ݿ��в�����");
					break;
				}
				dataType = String.valueOf(comment.get("dataType"));
				if(value.equals("null") || value.equals(Const.NULLDATA)){
					if(!(Boolean) comment.get("isNull")){
						throw new AppException("�ֶ�:"+comment.get("comment")+"("+comment.get("name")+")"+"����Ϊ:"+value);
					}
					continue;
				}else if(!DataUtil.verifyData(dataType,String.valueOf(obj.get(name)))){
					error.add(line);
					strError.add("��"+line+"��"+name+"��:"+obj.get(name)+"("+dataType+");���ݸ�ʽ����ȷ");
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
			 throw new AppException("�����ڸ����ݿ�");  
		 }
		 List<Map<String, Object>> mapList = staticDataService.getResultByTableName(tableName, new HashMap<String, Object>());
		// ����һ��������
        HSSFWorkbook workbook = new HSSFWorkbook();
        // ����һ�����
        HSSFSheet sheet = workbook.createSheet(tableName);
		// ���ñ��Ĭ���п��Ϊ15���ֽ�
        sheet.setDefaultColumnWidth(15);
        // ����һ����ʽ
        HSSFCellStyle style = workbook.createCellStyle();
        // ������Щ��ʽ
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // ����һ������
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 10);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // ������Ӧ�õ���ǰ����ʽ
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
        			//���õ�Ԫ������Ϊ�ַ�����
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
