package org.himadri.practice.java_practice.java_examples;

import java.io.File;

public class FileNameChanger {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String dirPath = "//Users//abhishek//Downloads//im";
		//String textToReplace = "jpg!d";
		//String textToReplaceWith = "jpg";
		File dir = new File (dirPath);
		for (File file :dir.listFiles()) {
			File newFile = new File(file.getAbsolutePath()+"1.jpg");
			file.renameTo(newFile);
		}
		System.out.println("terminated...");
	}

}
