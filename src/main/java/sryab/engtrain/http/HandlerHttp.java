package sryab.engtrain.http;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.Headers;
import java.io.*;
import java.util.*;
import java.net.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.FileSystems;


public class HandlerHttp implements HttpHandler {
	
	private Headers headers;
	private InputStream request;
	private OutputStream response;
	private String requestMethod;
	private Path pathResource;
	
	public HandlerHttp (String path) {
		this.pathResource = FileSystems.getDefault().getPath(path);
	}
	
	public void handle(HttpExchange exchange) {
		
		//******************** Request ******************************
		// Get request method
		requestMethod = exchange.getRequestMethod();
		System.out.println(requestMethod);
		
		// Get headers
		headers = exchange.getRequestHeaders();
		Set<String> requestKeys = headers.keySet();		
		for(String k:requestKeys) {
			System.out.println(k + ": " + headers.getFirst(k));
		}
		
		// Get request body
		request = exchange.getRequestBody();
		try {
			
			byte[] array = request.readAllBytes();
			System.out.println("Read: " + array.length);
			
			for(int i=0; i<array.length; i++) {
				System.out.println((char)array[i]);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if(request != null) {
				try {
					request.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		
		//********************* Response ******************************************
		headers = exchange.getResponseHeaders();
		byte[] arrayResponse = null;
		try {
			arrayResponse = Files.readAllBytes(pathResource);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		// Start the beginning of transfer
		try {
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, arrayResponse.length);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		
		
		// Send data to the client
		response = exchange.getResponseBody();
		try {
			response.write(arrayResponse);
			response.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		// Close the response output stream
		if(response != null) {
			try {
				response.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
}
