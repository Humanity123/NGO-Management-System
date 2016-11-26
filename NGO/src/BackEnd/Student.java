package BackEnd;

import java.util.Date;

public class Student {
	private String name, school, id;
	private Date dob;
	private int Class;
	private Help help;
	private Gender gender;
	private int income;
	
	public Student(String name, String school, String id, Date dob, int Class, Help help, Gender gender){
		this.name = name;
		this.school = school;
		this.id = id;
		this.dob = dob;
		this.Class = Class;
		this.help = help;
		this.gender = gender;
	}
	public void setSchool(String school){
		this.school = school;
	}
	public void setClass(int Class){
		this.Class = Class;
	}
	public void setParentalIncome(){
		this.income = income;
	}
}
