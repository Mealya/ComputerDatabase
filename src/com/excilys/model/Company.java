package com.excilys.model;

public class Company {
	private long id;
	private String name;
	
        public Company() {
            
        }

	public Company(long id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	public String toString() {
		return "ID : "+ id + " Name : " + name; 
	}
	
}
