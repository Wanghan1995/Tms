package com.wang.han;

public class Teacher {
	
	private long id;
	private String name;
	private String course;
	private int office;
	

	public Teacher(){

	}
	
	public Teacher(long id,String name,String course,int office){
		this.id = id;
		this.name = name;
		this.course=course;
		this.office = office;
	}

	public void setId(long id){
		this.id = id;
	}
	public long getId(){
		return this.id;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setOffice(int office){
		this.office = office;
	}
	public int getOffice(){
		return this.office;
	}
	public void setCourse(String course){
		this.course = course;
	}
	public String getCourse(){
		return this.course;
	}
	public String toString(){
		return "Teacher [ id: "+this.id+",name: "+this.name+",course: "+this.course+",office: "+this.office+"]";
	}
}