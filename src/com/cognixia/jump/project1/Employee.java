package com.cognixia.jump.project1;

public class Employee {
	
	public String firstName, lastName, email, position;
	public static String department;
	int id, yearlySalary; //first number of id is their department number
	
	//TODO: Use Enums 
	public static enum Department {
		FIRSTN, LASTN, EMAIL, POSITION, DEPARTMENT;
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


	public String getDepartment() {
		
		return department;
	}
	
	/**
	 * Determines the department of which the employee belongs to based on their ID
	 * @param id of employee
	 * @return department of employee 
	 * 
	 */
	public static String setDepartment(int id) {
		int key = 0;
		while(id != 0) {
			key = id % 10; 
			id = id /10;
		}
		
		switch(key) {
			case 1: 
				department = "Board Member";
				break;
			case 2: 
				department = "Marketing";
				break;
			case 3: 
				department = "Finance";
				break;
			case 4: 
				department = "Human Resources";
				break;
			case 5: 
				department = "Technology";
				break;
			case 6: 
				department = "Maintenance";
				break;
			default:
				department = "Other";
				break;
		}
		
		return department;
	}
	

}
