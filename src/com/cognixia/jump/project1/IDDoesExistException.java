package com.cognixia.jump.project1;

import java.io.Serializable;

public class IDDoesExistException extends Exception implements Serializable{
	
	private static final long serialVersionUID = 4789110L;
	
	public IDDoesExistException() {
		System.out.println("The given ID already belongs to an existing Employee!");
	}
}
