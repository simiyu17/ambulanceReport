package com.report.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;

public class ReportUtil {

    public static Font title = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLDITALIC);
    public static Font heading = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
    public static Font normal = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL);
    public static Font underlined = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.UNDERLINE);
    public static Font normalBold = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
    public static DecimalFormat formatter = new DecimalFormat("#,##0.00");

    public static void addValues(PdfPTable table, int alignment, boolean border, int colspan, String... values) {
        addValues(table, ReportUtil.normal, alignment, border, colspan, values);
    }
    
     public static void addValuesUnderlined(PdfPTable table, int alignment, boolean border,  int colspan, String... values) {
        addValues(table, ReportUtil.underlined, alignment, border, colspan, values);
    }

    public static void addValues(PdfPTable table, int alignment, int colspan, String... values) {
        addValues(table, ReportUtil.normal,alignment, true, colspan, values);
    }

    public static void addValues(PdfPTable table, Font font, int alignment, boolean border, int colspan, String... values) {
        PdfPCell cell;
        for (String value : values) {
            cell = new PdfPCell(new Phrase(value, font));
            cell.setHorizontalAlignment(alignment);
            cell.setColspan(colspan);
            if (!border) {
                cell.setBorder(PdfPCell.NO_BORDER);
            }else{
                cell.setBorderColor(BaseColor.DARK_GRAY);
            }
            table.addCell(cell);
        }
    }

    public static PdfPCell getCell(String text, int alignment, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setPadding(0);
        cell.setColspan(4);
        cell.setPaddingBottom(5f);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    public static PdfPCell getCell(String text, Font font, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setPadding(2);
        cell.setPaddingBottom(5f);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(alignment);
        return cell;
    }

    public static PdfPCell getCellWhiteBackgroung(String text, Font font, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setPadding(2);
        cell.setPaddingBottom(5f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setHorizontalAlignment(alignment);
        return cell;
    }
    
    
    public static PdfPCell getReportTitleCell(String text, Font font, int alignment, int size) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setPadding(2);
        cell.setPaddingBottom(5f);
        cell.setFixedHeight(size);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(alignment);
        return cell;
    }
    
    public static PdfPCell getBlankCell() {
        PdfPCell cell = new PdfPCell(new Phrase(" ", FontFactory.getFont(FontFactory.HELVETICA, 4, Font.NORMAL)));
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    public static PdfPCell getReportHeaderCell() {
        PdfPTable header = new PdfPTable(3);
        try {
             // set defaults
            header.setWidths(new int[]{2, 6, 2});
            header.setTotalWidth(527);
            header.setLockedWidth(true);
            header.getDefaultCell().setFixedHeight(60);

            // add logo
            Image logo = Image.getInstance(ReportUtil.class.getResource("/logo/logo2.png"));
            PdfPCell lg = new PdfPCell();
            lg.setFixedHeight(60);
            lg.setImage(logo);
            lg.setHorizontalAlignment(Element.ALIGN_CENTER);
            lg.setVerticalAlignment(Element.ALIGN_MIDDLE);
            lg.setBorder(Rectangle.NO_BORDER);
            header.addCell(lg);

            // add text
            PdfPCell text = new PdfPCell();
            text.setPaddingBottom(5);
            text.setPaddingLeft(20);
            text.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            text.setBorder(Rectangle.NO_BORDER);
            text.addElement(new Phrase("Salaghari Youth Club Ambulance", new Font(Font.FontFamily.HELVETICA, 12)));
            text.addElement(new Phrase("Bhaktaput- KTM, Nepal", new Font(Font.FontFamily.HELVETICA, 12)));
            text.addElement(new Phrase("Ph:00977-9841567983", new Font(Font.FontFamily.HELVETICA, 12)));
            //text.addElement(new Phrase(" ", new Font(Font.FontFamily.HELVETICA, 12)));
            text.addElement(new Phrase("For the our community since 2072", new Font(Font.FontFamily.HELVETICA, 12)));
           
            header.addCell(text);
            
            // add text
            PdfPCell empty = new PdfPCell();
            empty.setPaddingBottom(5);
            empty.setPaddingLeft(20);
            empty.setBorder(Rectangle.NO_BORDER);
            text.addElement(new Phrase(" ", new Font(Font.FontFamily.HELVETICA, 12)));
           
            header.addCell(empty);

            // write content
           // header.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());

            PdfPCell cell = new PdfPCell(header);
            cell.setBorderColor(BaseColor.LIGHT_GRAY);
            return cell;
        } catch (DocumentException de) {
            throw new ExceptionConverter(de);
        } catch (MalformedURLException e) {
            throw new ExceptionConverter(e);
        } catch (IOException e) {
            throw new ExceptionConverter(e);
        }

    }
  


}
