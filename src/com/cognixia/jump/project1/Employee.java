package com.cognixia.jump.project1;

import java.io.Serializable;

public class Employee implements Serializable{
	
	private static final long serialVersionUID = 4822919L;
	
	public String firstName, lastName, email, position;
	public static Department department;
	int id; //first number of id is their department number
	
	public static enum Department {
		MARKETING, TECHNOLOGY, OPERATIONS, MAINTENANCE, OTHER;
	}
	
	
	public Employee(int id, String firstName, String lastName, String email, String position) {
		super(); 
		this.id = id;
		this.firstName = firstName; 
		this.lastName = lastName; 
		this.email = email;
		this.position = position;
		
		/*---- Assigning Department through ID -----*/
		setDepartment(id);
		
	}
	
	//To add an employee by ID 
	public Employee(int id) {
		this.id = id;
		setDepartment(id);
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		
		// Use a try catch to check if ID already exists 
//		try {
//			
//		}
//		catch()
		this.id = id;
		
	}

	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public Department getDepartment() {
		
		return department;
	}
	
	/**
	 * Determines the department of which the employee belongs to based on their ID
	 * @param id of employee
	 * @return department of employee 
	 * 
	 */
	public static void setDepartment(int id) {
		int key = 0;
		while(id != 0) {
			key = id % 10; 
			id = id /10;
		}
		
		switch(key) {
			case 1: 
				department = Department.MARKETING; 
				break;
			case 2: 
				department = Department.OPERATIONS;
				break;
			case 3: 
				department = Department.TECHNOLOGY;
				break;
			case 4: 
				department = Department.MAINTENANCE;
				break;
			default:
				department = Department.OTHER;
				break;
		}
		
	}

	@Override
	public String toString() {
		return "Employee Information\n"
				+ "Identification Number:" + id + "\n"
				+ "First Name:" + firstName + "\n"
				+ "Last Name:" + lastName + "\n"
				+ "Email:" + email + "\n"
				+ "Position:" + position + "\n"
				+ "Department:" + department.toString() + "\n";
	}
	
	public String fileFormat() {
		return id + " " + firstName + " " + lastName + " "
				+ email + " " + position + " " + department.toString();
	}
	
	public String preview() {
		return id + "   " + firstName + " " + lastName;
	}

	
}
