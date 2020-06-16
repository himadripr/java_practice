package org.himadri.practice.java_practice.java_examples;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;



public class DownloadFilesFromUrl {
	private static String rootFolderPath = "//Users//abhishek//Documents//transbit//test";
	
//	public static void main(String[] args) {
//		
////		File mainDir = new File(rootFolderPath);
////		if (!mainDir.exists()) {
////			mainDir.mkdir();
////		}
////		String tmpDirectory = rootFolderPath;
////		File file = new File(tmpDirectory);
////		file.mkdir();
////		tmpDirectory = tmpDirectory+"/"+System.currentTimeMillis();
////		file = new File(tmpDirectory);
////		file.mkdir();
////		String fileUrl = "https://drive.google.com/file/d/0ByPLEhffz4z9Rm5xSk5IeFUzR240cjdBSUtnRURaVlEwanNF/view?usp=sharing";
////		URL url = null;
////		try {
////			url = new URL(fileUrl);
////		} catch (MalformedURLException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		String fileBaseName = FilenameUtils.getBaseName(url.getPath());
////		String filePath = tmpDirectory+"/"+fileBaseName+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(url.getPath());
////		try {
////			FileUtils.copyURLToFile(url, new File (filePath), 1000000, 1000000);
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//	}
	
	public static void main(String[] args) {
		File mainDir = new File(rootFolderPath);
		if (!mainDir.exists()) {
			mainDir.mkdir();
		}
		String tmpDirectory = rootFolderPath;
		File file = new File(tmpDirectory);
		file.mkdir();
		tmpDirectory = tmpDirectory+"/"+System.currentTimeMillis();
		file = new File(tmpDirectory);
		file.mkdir();
		String fileUrl = "https://www.famousbirthdays.com/faces/baghla-aksh-image.jpg";
//		URL url = null;
//		System.out.println("started...");
//		try {
//			url = new URL(fileUrl);
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String fileBaseName = FilenameUtils.getBaseName(url.getPath());
//		String filePath = tmpDirectory+"/"+fileBaseName+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(url.getPath());
//		try {
//			FileUtils.copyURLToFile(url, new File (filePath), 1000000, 1000000);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		downloadoriginalFile(fileUrl, tmpDirectory);
		System.out.println("terminated...");
	}
	
	private static File downloadoriginalFile(String fileUrl, String directory) {
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, new TrustManager[] { new TrustAllX509TrustManager() }, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier( new HostnameVerifier(){
			    public boolean verify(String string,SSLSession ssls) {
			        return true;
			    }
			});
			InputStream in = new URL(fileUrl).openStream();
			File f = File.createTempFile("abc", ".jpg", new File(directory));
			Files.copy(in, Paths.get(f.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
			in.close();
			return f;
		} catch (NoSuchAlgorithmException e) {
			
		} catch (KeyManagementException e) {
			
		} catch (IOException e) {
			
		}
		
		return null;
	}
	
	public static class TrustAllX509TrustManager implements X509TrustManager {

		@Override
		public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
			
		}

		@Override
		public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
			
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			  return new X509Certificate[0];
		}

	}
}
