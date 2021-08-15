package org.sid.pettycach.service.reports;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.sid.pettycach.entity.reports.acountwise;

public class AccountExcelExporter {
	private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<acountwise> listaccounts;
     
    public AccountExcelExporter(List<acountwise> listaccounts) {
        this.listaccounts = listaccounts;
        workbook = new XSSFWorkbook();
    }
 
 
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Accounts");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(14);
        style.setFont(font);
         
        createCell(row, 0, " ID", style);      
        createCell(row, 1, "Account", style);       
        createCell(row, 2, "Manager", style);    
        createCell(row, 3, "TotalReceipt", style);
        createCell(row, 4, "TotalExpense", style);
        createCell(row, 5, "TotalAdvance", style); 
        createCell(row, 6, "TotalBalance", style); 
         
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(12);
        style.setFont(font);
                 
        for (acountwise acw : listaccounts) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, acw.getId(), style);
            createCell(row, columnCount++, acw.getAccount().getAccountname(), style);
            createCell(row, columnCount++, acw.getManager(), style);
            createCell(row, columnCount++, acw.getTotalreceipt(), style);
            createCell(row, columnCount++, acw.getTotalexpense(), style);
            createCell(row, columnCount++, acw.getTotaladvance(), style);
            createCell(row, columnCount++, acw.getTotalbalance(), style);
             
        }
    }
     
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }

}
