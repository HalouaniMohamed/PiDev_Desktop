/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.ShoppingCartItem;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 *
 * @author ALPHA
 */
public class PdfGeneratorH {

    public File generateInvoicePdf(List<ShoppingCartItem> items) {
        try {
            // Create a new PDF document
            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);

            // Open the document
            document.open();

            // Create a table with 3 columns
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.getDefaultCell().setPadding(5);

            // Add table headers
            table.addCell("Nom du produit");
            table.addCell("Quantité");
            table.addCell("Prix");

            double totalPrice = 0;

            // Add the items to the table
            for (ShoppingCartItem item : items) {
                table.addCell(item.getProduct().getName());
                table.addCell(Integer.toString(item.getQuantity()));
                double itemPrice = item.getProduct().getPrice() * item.getQuantity();
                totalPrice += itemPrice;
                table.addCell(Double.toString(itemPrice));
            }

            // Add the table to the document
            document.add(table);

            // Add the total price to the document
            Paragraph p3 = new Paragraph("Prix totale: " + totalPrice + " tnd");
            document.add(p3);

            // Add a thank you message to the document
            Paragraph p2 = new Paragraph("Merci de nou faire confiance!");
            document.add(p2);

            // Close the document
            document.close();

            // Convert the PDF document to a byte array
            byte[] pdfBytes = baos.toByteArray();

            // Create a new file with a unique name
            File pdfFile = File.createTempFile("invoice", ".pdf");

            // Write the byte array to the file
            FileOutputStream fos = new FileOutputStream(pdfFile);
            fos.write(pdfBytes);
            fos.close();

            return pdfFile;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
