
package sryab.engtrain.main;

import sryab.engtrain.http.HandlerHttp;
import sryab.engtrain.http.ServerHttp;

import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpContext;
import java.io.*;

public class Main {
	
	public static void main(String[] args) {
		
		ServerHttp server = null;
		HttpContext context = null;
		
		System.out.println("The main method was invoked");
		
		
		try {
			server = new ServerHttp(new InetSocketAddress(4000));
		} catch (IOException ex) {
			ex.printStackTrace();
			server = null;
		}
		
		
		if(server != null) {
			context = server.bindHandlerToServer("/main/", new HandlerHttp("g:/EclipseWorkspace/EngTrainServer/src/main/resources/website.html"));
		}
		
		
		if(context != null) {
			System.out.println(context.getPath());
		}
		
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		System.out.println("Please, type 'exit' to leave the app:");
		do {
			try {
				line=reader.readLine();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			System.out.println("The read line is: " + line);
			//System.out.println(line.equals("exit"));
		} while(!line.equals("exit"));
		
		try {
			reader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		
		if(server != null) {
			System.out.println("Closing server...");
			server.stopServer();
			System.out.println("The server was stopped");
		}
				
	}
	
}
