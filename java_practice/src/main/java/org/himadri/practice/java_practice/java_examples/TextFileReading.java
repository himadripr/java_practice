package org.himadri.practice.java_practice.java_examples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFileReading {
	public static void main(String[] args) {
		String fileName = "//Users//abhishek//Documents//transbit//test//test.txt";
		String data = null; 
	    try {
			data = new String(Files.readAllBytes(Paths.get(fileName)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    if (data!=null){
	    	
	    	int len = data.length();
	    	char[] arr = data.toCharArray();
	    	boolean isAppend = false;
	    	StringBuilder sb = new StringBuilder();
	    	for (int i=0; i<len;i++){
	    		char ch = arr[i];
	    		if (ch==')'){
	    			isAppend = false;
	    			System.out.println(sb.toString());
	    			sb = new StringBuilder("");
	    		}
	    		if (isAppend){
	    			sb.append(ch);
	    		}
	    		if (ch=='('){
	    			isAppend = true;
	    		}
	    		
	    	}
	    	System.out.println();
	    	System.out.println();
	    	System.out.println(data);
	    }
	    
	}
}
