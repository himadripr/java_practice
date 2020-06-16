package org.himadri.practice.java_practice.java_examples;

/**
 * https://howtodoinjava.com/jersey/jersey-file-upload-example/
 * https://javaee.github.io/javaee-spec/javadocs/javax/ws/rs/core/Response.html
 * 
 *see the sheet-cutting-plan project.
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.glassfish.jersey.media.multipart.internal.MultiPartWriter;
import org.json.simple.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class JerseyClientPost {
	static String filePath = "//Users//abhishek//Documents//transbit//samples//RAKEZ_new_documents_for_OCR//samples//medical_certificate//abudabi.jpg";;
	static String url = "https://falcon.mytbits.com:7081/falcon-rdca/rest/rdcaService/genericOcr";
	
	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		System.out.println("Starting time : " + time);
		System.out.println("Starting time: - " + new Date());
		postFormDataCall();
		System.out.println("\ntotal time taken:- " + (System.currentTimeMillis() - time) / 1000 + "  second");
		
		
	}
	
	
	public void main() {
		long time = System.currentTimeMillis();
		System.out.println("Starting time : " + time);
		System.out.println("Starting time: - " + new Date());
		String data = "";
		File file = null;

		try {

			data = new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;

		}
		file = new File(filePath);
		if (!file.exists()) {
			System.out.println("The file does not exist.");
			return;
		}

		try {
			// ClientConfig cc = new DefaultClientConfig();
			// Client client;
			//
			// cc.getClasses().add(MultiPartWriter.class);
			// client = Client.create(cc);
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			
			Client client = Client.create(clientConfig);

			WebResource webResource = client
					.resource("https://falcon.mytbits.com:7081/falcon-rdca/rest/rdcaService/genericOcr");

			 MultivaluedMap formData = new MultivaluedMapImpl();
			 formData.add("data", data);

			

			ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).type(MediaType.MULTIPART_FORM_DATA)
					.post(ClientResponse.class, formData);

//			ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON)
//					
//					.post(ClientResponse.class, data);

			// if (response.getStatus() != 201 || response.getStatus() != 200) {
			// throw new RuntimeException("Failed : HTTP error code : "
			// + response.getStatus());
			// }

			System.out.println("\ntotal time taken:- " + (System.currentTimeMillis() - time) / 1000 + "  second");
			System.out.println("\nOutput from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);
			
			

		} catch (Exception e) {

			e.printStackTrace();

		}
		System.out.println("\nEnding time: - " + new Date());
		System.out.println("Ending time:- " + System.currentTimeMillis());

	}
	
	private static void postFormDataCall() {
		final javax.ws.rs.client.Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
		 
	    final FileDataBodyPart filePart = new FileDataBodyPart("file", new File(filePath));
	    FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
	    //final FormDataMultiPart multipart = (FormDataMultiPart) formDataMultiPart.field("foo", "bar").bodyPart(filePart);
	    final FormDataMultiPart multipart = (FormDataMultiPart) formDataMultiPart.bodyPart(filePart);
	    
	    final WebTarget target = ((javax.ws.rs.client.Client) client).target(url);
	    final Response response = target.request()
	    		.header("Authorization", "a3ZOdUI1ZE0yOGRqNm5mRDc0bmZkNU5XZDgzbVRjNg")
	    		.post(Entity.entity(multipart, multipart.getMediaType()));
	    
	    //Use response object to verify upload success
	    
	    JSONObject jsonObject = response.readEntity(JSONObject.class);
	    
	    System.out.println("\nOutput from Server .... \n");
		
	     
	    try {
			formDataMultiPart.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			multipart.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}