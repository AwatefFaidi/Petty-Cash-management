package org.sid.pettycach.service.reports;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.sid.pettycach.entity.reports.Runningbalance;


public class RunningbalanceExcelExporter {
	private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Runningbalance> listrunningbalance;
    
    public RunningbalanceExcelExporter(List<Runningbalance> listrunningbalance) {
        this.listrunningbalance = listrunningbalance;
        workbook = new XSSFWorkbook();
    }
    
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Runningbalance");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(14);
        style.setFont(font);
         
        createCell(row, 0, " Sl No", style);      
        createCell(row, 1, "Date", style);       
        createCell(row, 2, "Opening Balance", style);    
        createCell(row, 3, "Received Amount", style);
        createCell(row, 4, "Expense Amount", style);
        createCell(row, 5, "Received By", style); 
        createCell(row, 6, "Balance", style); 
         
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        }else if (value instanceof Date) {
            cell.setCellValue((Date) value);
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
                 
        for (Runningbalance rb : listrunningbalance) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, rb.getId(), style);
            createCell(row, columnCount++, rb.getOp(), style);
            createCell(row, columnCount++, rb.getDate(), style);
            createCell(row, columnCount++, rb.getTotalreceipt(), style);
            createCell(row, columnCount++, rb.getTotalexpense(), style);
            createCell(row, columnCount++, rb.getReceiverby(), style);
            createCell(row, columnCount++, rb.getBalance(), style);
             
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
