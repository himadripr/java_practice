package org.himadri.practice.java_practice.string;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class ReadXmlContentAsString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filePath = "//Users//abhishek//Documents//transbit//Invoice_Processing//tmp//exp1-page-001.xml";
		StringBuilder sb = new StringBuilder();
	    try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
	    	String sCurrentLine = "";
	        while ((sCurrentLine = br.readLine()) != null) {
	            sb.append(sCurrentLine);
	            sb.append("\n");
	        }

	    } catch (Exception e) {
			// TODO: handle exception
		}
	    System.out.println(sb.toString());
	    
	    

	}

}
