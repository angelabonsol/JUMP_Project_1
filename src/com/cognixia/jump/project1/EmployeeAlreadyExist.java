package com.cognixia.jump.project1;

import java.io.Serializable;

public class EmployeeAlreadyExist extends Exception implements Serializable{
	
	private static final long serialVersionUID = 4789110L;
	
	public EmployeeAlreadyExist() {
		System.out.println("The given ID already belongs to an existing Employee!");
	}
}
