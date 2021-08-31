package com.cognixia.jump.project1;

import java.io.Serializable;

public class EmployeeDoesNotExist extends Exception implements Serializable{
	
	private static final long serialVersionUID = 428291L;
	public EmployeeDoesNotExist() {
		System.out.println("The given ID does not provide an existing Employee!");
	}

}
