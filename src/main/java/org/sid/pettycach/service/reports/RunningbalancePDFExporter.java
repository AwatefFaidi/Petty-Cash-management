package org.sid.pettycach.service.reports;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.sid.pettycach.entity.reports.Runningbalance;


import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class RunningbalancePDFExporter {
	List<Runningbalance> listrunningbalances;
	 public RunningbalancePDFExporter(List<Runningbalance> listrunningbalances) {
	        this.listrunningbalances = listrunningbalances;
	    }
	 
	    private void writeTableHeader(PdfPTable table) {
	        PdfPCell cell = new PdfPCell();
	        cell.setBackgroundColor(Color.BLUE);
	        cell.setPadding(3);
	         
	        Font font = FontFactory.getFont(FontFactory.HELVETICA);
	        font.setColor(Color.WHITE);
	         
	        cell.setPhrase(new Phrase(" Sl No", font));
	         
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Date", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Opening Balance", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Received Amount", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Expense Amount", font));
	        table.addCell(cell);
	        
	        cell.setPhrase(new Phrase("Received By", font));
	        table.addCell(cell);
	        
	        cell.setPhrase(new Phrase("Balance", font));
	        table.addCell(cell);
	    }
	     
	    private void writeTableData(PdfPTable table) {
	        for (Runningbalance bg : listrunningbalances) {
	            table.addCell(String.valueOf(bg.getId()));
	            table.addCell(String.valueOf(bg.getDate()));
	            table.addCell(String.valueOf(bg.getOp()));
	            table.addCell(String.valueOf(bg.getTotalreceipt()));
	            table.addCell(String.valueOf(bg.getTotalexpense()));
	            table.addCell(bg.getReceiverby());
	            table.addCell(String.valueOf(bg.getBalance()));
	  
	        }
	    }
	     
	    public void export(HttpServletResponse response) throws DocumentException, IOException {
	        Document document = new Document(PageSize.A4);
	        PdfWriter.getInstance(document, response.getOutputStream());
	         
	        document.open();
	        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        font.setSize(14);
	        font.setColor(Color.BLUE);
	         
	        Paragraph p = new Paragraph("Running Balance", font);
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	         
	        document.add(p);
	         
	        PdfPTable table = new PdfPTable(7);
	        table.setWidthPercentage(100f);
	        table.setWidths(new float[] {1.5f, 2.0f, 1.5f, 2.0f, 2.0f,2.0f, 2.0f});
	        table.setSpacingBefore(8);
	         
	        writeTableHeader(table);
	        writeTableData(table);
	         
	        document.add(table);
	         
	        document.close();
	         
	    }

}
