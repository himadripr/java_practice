package org.himadri.practice.java_practice.pdf;
import java.io.File; 
import java.io.IOException;  

import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.pdmodel.PDPage; 
import org.apache.pdfbox.pdmodel.PDPageContentStream; 
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;  

public class AddingTextToAPdf {  
   public static void main(String args[]) throws IOException {     
      
      //Loading an existing document 
      PDDocument doc = PDDocument.load(new File("//Users//abhishek//Documents//transbit//tbits_kitchen//documents//test.pdf"));
    //Creating PDImageXObject object  
      PDImageXObject pdImage = PDImageXObject.createFromFile("//Users//abhishek//Documents//transbit//tbits_kitchen//documents//flower.jpeg",doc); 
      
      PDPage blankPage = new PDPage();
      doc.addPage(blankPage);
      //Creating a PDF Document 
      PDPage page = doc.getPage(doc.getNumberOfPages()-1);
     
      PDPageContentStream contentStream = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND,true, true); 
    //Drawing the image in the PDF document  
      contentStream.drawImage(pdImage, 250, 300); 
      System.out.println("Image inserted successfully");
      
      //Begin the Content stream 
      contentStream.beginText(); 
      contentStream.setLeading(14.5f);  
      //Setting the font to the Content stream  
      contentStream.setFont( PDType1Font.TIMES_ROMAN, 16 ); 

      //Setting the position for the line 
      contentStream.newLineAtOffset(25, 725 ); 
      //String text = "This is an example of adding text to a page in \\nthe pdf document. we can add as many lines as we want like this using the\\n draw string metho of the ContentStream class";
      
      String text = "Hi!!! This is the multiple text content example.";  
      String Line1 = "Here, we discussed about how to add text content in the pages of the PDF document.";  
      String Line2 = "We do this by using the ShowText() method of the ContentStream class";

     
      
    //Adding text in the form of string  
      contentStream.showText(text);  
      contentStream.newLine(); 
      contentStream.showText(Line1);  
      contentStream.newLine();  
      contentStream.showText(Line2);

      //Ending the content stream 
      contentStream.endText(); 
      System.out.println("Content added");    
      
      

      //Closing the content stream 
      contentStream.close();      

      //Saving the document  
      doc.save(new File("//Users//abhishek//Documents//transbit//tbits_kitchen//documents//test_new.pdf")); 

      //Closing the document  
      doc.close();  
   }  
} 