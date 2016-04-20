package com.excilys.model;

import java.sql.Timestamp;

public class Computer {
	private long id;
	private String name;
	private Timestamp intro;
	private Timestamp disco;
	private int compId;
	
        public Computer() {
            
        }

	public Computer(long id, String name, Timestamp intro, Timestamp disco,
			int compId) {
		super();
		this.id = id;
		this.name = name;
		this.intro = intro;
		this.disco = disco;
		this.compId = compId;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getIntro() {
		return intro;
	}
	public void setIntro(Timestamp intro) {
		this.intro = intro;
	}
	public Timestamp getDisco() {
		return disco;
	}
	public void setDisco(Timestamp disco) {
		this.disco = disco;
	}
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	public String toString() {
		return "ID : "+ id + " Name : " + name + " Introduction : " + intro + " Discontinued : " + disco + " Company ID : " + compId;
	}

}
