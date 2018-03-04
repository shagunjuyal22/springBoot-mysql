package com.mysql.mysqlproject.model;

public class Team {
	
	int team_id;
	int nummer;
	String name;
	int firmen_id;
	public Team(int team_id, String name,int nummer, int firmen_id) {
		super();
		this.team_id = team_id;
		this.nummer = nummer;
		this.name = name;
		this.firmen_id = firmen_id;
	}
	
	public Team() {
		
	}
	public int getTeam_id() {
		return team_id;
	}
	public int getNummer() {
		return nummer;
	}
	public String getName() {
		return name;
	}
	public int getFirmen_id() {
		return firmen_id;
	}

	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}

	public void setNummer(int nummer) {
		this.nummer = nummer;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFirmen_id(int firmen_id) {
		this.firmen_id = firmen_id;
	}
	
	

}
