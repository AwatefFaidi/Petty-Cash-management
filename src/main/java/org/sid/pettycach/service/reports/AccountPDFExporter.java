package org.sid.pettycach.service.reports;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;


import org.sid.pettycach.entity.reports.acountwise;

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

public class AccountPDFExporter {
	private List<acountwise> listaccountwise;
    
    public AccountPDFExporter(List<acountwise> listaccountwise) {
        this.listaccountwise = listaccountwise;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(3);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase(" ID", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Account", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Manager", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Total_Receipt", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Total_Expense", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Total_Advance", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Total_Balance", font));
        table.addCell(cell);
    }
     
    private void writeTableData(PdfPTable table) {
        for (acountwise acwise : listaccountwise) {
            table.addCell(String.valueOf(acwise.getId()));
            table.addCell(acwise.getAccount().getAccountname());
            table.addCell(acwise.getManager());
            		//getAccount().getUsers().toString());
                        
            table.addCell(String.valueOf(acwise.getTotalreceipt()));
            table.addCell(String.valueOf(acwise.getTotalexpense()));
            table.addCell(String.valueOf(acwise.getTotaladvance()));
            table.addCell(String.valueOf(acwise.getTotalbalance()));
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(14);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("Accountwise Report", font);
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
