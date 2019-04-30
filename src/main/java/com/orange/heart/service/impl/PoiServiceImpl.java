package com.orange.heart.service.impl;

import com.orange.heart.service.PoiService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * 用于Excel的各类操作
 *
 * @author allen
 */
@Service("poiService")
public class PoiServiceImpl implements PoiService {

    @Override
    public HSSFWorkbook getQueryResultExcel(List<Map<String, Object>> list, List<String> columnList,
                                            List<String> columnNameList) {
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            HSSFSheet sheet = wb.createSheet();
            int columns = columnList.size();
            int rows = list.size();
            HSSFRow headerRow = sheet.createRow(0);
            for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
                HSSFCell cell = headerRow.createCell(columnIndex);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(columnNameList.get(columnIndex));
            }
            for (int rowIndex = 1; rowIndex < rows + 1; rowIndex++) {
                Map<String, Object> t = list.get(rowIndex - 1);
                HSSFRow row = sheet.createRow(rowIndex);
                for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
                    HSSFCell cell = row.createCell(columnIndex);
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    String value = String.valueOf(t.get(columnList.get(columnIndex)));
                    if (StringUtils.isBlank(value) || StringUtils.equals("null", value)) {
                        value = "";
                    }
                    cell.setCellValue(value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return wb;
    }


    @Override
    public void writeToClient(HttpServletResponse response, HSSFWorkbook wb) {
        response.setContentType("application/vnd.ms-excel");
        response.reset();
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("utf-8");

        // 设置response的Header
        String fileName = null;
        try {
            if (null == fileName) {
                fileName = System.currentTimeMillis() + ".xls";
            }
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String(fileName.getBytes("gbk"), "iso-8859-1"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        try {
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            if (wb != null) {
                wb.write(toClient);
            }
            toClient.flush();
            toClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
