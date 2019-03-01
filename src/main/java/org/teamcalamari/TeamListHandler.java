package org.teamcalamari;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class TeamListHandler implements HttpHandler{

	@Override
	public void handle(HttpExchange he) throws IOException {
		URI uri = he.getRequestURI();
		String[] pathVars = uri.getPath().split("/");
		int page = Integer.parseInt(pathVars[pathVars.length-1]);
		
		String jsonResponse = "[]";
		if(Server.teams.size() > page) {
			jsonResponse = Server.gson.toJson(Server.teams.get(page));
		}
		
		he.sendResponseHeaders(200, jsonResponse.getBytes().length);
		OutputStream out = he.getResponseBody();
		out.write(jsonResponse.getBytes());
		out.close();
		
		he.close();
	}

}
