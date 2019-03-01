package org.teamcalamari;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;

public class Server{

	public static Gson gson = new Gson();
	
	private static final int port = System.getenv("PORT") != null ? Integer.parseInt(System.getenv("PORT")) : 5000;
	
	public static final int numPerPage = 5;
	public static List<List<Team>> teams = new ArrayList<>();
	public static List<List<EventSimplest>> events = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		
		loadTeams();
		System.out.println("Teams Loaded");
		
		loadEvents();
		System.out.println("Events Loaded");
		
		HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
    	//sets a class to handle HTTP requests
    	server.createContext("/api/v3/teams/simple/", new TeamListHandler());
    	server.createContext("/api/v3/events/", new EventListHandler());
    	
    	//starts the server
    	server.start();
    	
		System.out.println("Server Started");
	}
	public static void loadTeams() {
		List<Team> tempTeams = new ArrayList<>();
		tempTeams.add(new Team(1, "Juggernauts"));
		tempTeams.add(new Team(16, "The Bomb Squad"));
		tempTeams.add(new Team(42, "PARTS"));
		tempTeams.add(new Team(254, "Cheesy Poofs"));
		tempTeams.add(new Team(1317, "Digital Fusion"));
		
		tempTeams.add(new Team(1530, "Vikings"));
		tempTeams.add(new Team(1547, "Where's Waldo"));
		tempTeams.add(new Team(1421, "Team Chaos"));
		tempTeams.add(new Team(1510, "Wildcats"));
		tempTeams.add(new Team(1699, "Robocats"));
		
		tempTeams.add(new Team(3120, "RoboKnights"));
		tempTeams.add(new Team(3610, "Islanders"));
		tempTeams.add(new Team(4015, "Jag"));
		tempTeams.add(new Team(4515, "Stag"));
		tempTeams.add(new Team(5616, "Green Machine"));
		
		tempTeams.add(new Team(5061, "1"));
		tempTeams.add(new Team(352, "Green Machine"));
		tempTeams.add(new Team(2063, "Green Machine"));
		tempTeams.add(new Team(6242, "Green Machine"));
		tempTeams.add(new Team(372, "RoboKnights"));
		
		/*tempTeams.add(new Team(10000, "New Team"));
		tempTeams.add(new Team(10001, "RoboKnights"));*/
		
		tempTeams.add(new Team(10002, "RoboKnights"));
		tempTeams.add(new Team(10003, "RoboKnights"));
		
		/*tempTeams.add(new Team(10004, "10004"));
		tempTeams.add(new Team(10005, "10005"));*/
		
		int i = 0;
		int pageNum = 0;
		for(Team t : tempTeams) {
			if(i == 0) {
				teams.add(new ArrayList<>(numPerPage));
			}
			
			teams.get(pageNum).add(t);
			
			if(i == numPerPage) {
				pageNum++;
				i = 0;
			}
		}
	}
	public static void loadEvents(){
		events.add(new ArrayList<>(numPerPage));
		events.get(0).add(new EventSimplest("1992cmp", "FIRST Championship", "FIRST Championship", "1992"));
		events.get(0).add(new EventSimplest("1993cmp", "FIRST Championship", "FIRST Championship", "1993"));
		events.get(0).add(new EventSimplest("1994cmp", "FIRST Championship", "FIRST Championship", "1994"));
		events.get(0).add(new EventSimplest("1995cmp", "FIRST Championship", "FIRST Championship", "1995"));
		events.get(0).add(new EventSimplest("1996cmp", "FIRST Championship", "FIRST Championship", "1996"));

		events.add(new ArrayList<>(numPerPage));
		events.get(1).add(new EventSimplest("2017ohcl", "Buckeye", "Buckeye Regional", "2017"));
		events.get(1).add(new EventSimplest("2018ohcl", "Buckeye", "Buckeye Regional", "2018"));
		events.get(1).add(new EventSimplest("2019ohcl", "Buckeye", "Buckeye Regional", "2019"));
		events.get(1).add(new EventSimplest("2019ohmv", "Miami Valley", "Miami Valley Regional", "2019"));
		events.get(1).add(new EventSimplest("2018abca", "Canadian Rockies", "Canadian Rockies Regional", "2018"));
	}
}