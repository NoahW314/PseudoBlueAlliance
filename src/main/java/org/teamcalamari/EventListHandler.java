package org.teamcalamari;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class EventListHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange he) throws IOException {
		String jsonResponse = "[]";
		
		he.sendResponseHeaders(200, jsonResponse.getBytes().length);
		OutputStream out = he.getResponseBody();
		out.write(jsonResponse.getBytes());
		out.close();
		
		he.close();
	}

}
