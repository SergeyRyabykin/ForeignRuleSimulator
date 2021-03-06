package sryab.engtrain.http;

import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpContext;
import java.io.IOException;

public class ServerHttp {
	
	private InetSocketAddress socketAddress;
	private HttpServer server;
	
	public ServerHttp(InetSocketAddress socketAddress) throws IOException {
		this.socketAddress = socketAddress;
		server = HttpServer.create(socketAddress, 50);
		server.setExecutor(null);
		server.start();		
	}
	
	public InetSocketAddress getServerAddress() {
		return this.socketAddress;
	}
	
	public void stopServer() {
		server.stop(10);
		
	}
	
	public HttpContext bindHandlerToServer(String path, HttpHandler handler) {
		return server.createContext(path, handler);
	}
}
