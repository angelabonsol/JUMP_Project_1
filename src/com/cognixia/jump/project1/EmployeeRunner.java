package com.cognixia.jump.project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.BufferedReader;


public class EmployeeRunner {
	static Scanner scanner = new Scanner(System.in);
	static boolean continuous = true;
	static int id;

	public static void main(String[] args) throws IOException {
		
		readTxt();
		
		System.out.println("Welcome! You are now accessing Drop Inc. employee files.\n");
		
		while (continuous== true) {
			showMenu();
			
			
			
			
		}
		
	}
	
	private static void readTxt() {
		File file = null;
		FileReader fileReader = null; 
		BufferedReader reader = null;
		
		try {
			file = new File("resources/employees.txt");
			fileReader = new FileReader(file);
			reader = new BufferedReader(reader);
			
			String line; 
			
			while((line = reader.readLine()) != null) {
				
				
				
			}
			
		} catch(FileNotFoundException e){
			System.out.println("This file does not exist!");
		} catch(IOException e) {
			System.out.println("Could not read character!");
		} finally {
			try {
				fileReader.close();
				reader.close();
			} catch(IOException e) {
				System.out.println("Could not close reader objects!");
			}
		}

	}
		
		private static void showMenu() {
			
			try {
				System.out.println("Please select what you would like to do today: \n"
						+ "1: Show full employee list \n"
						+ "2: Show Employee information \n"
						+ "3: Add an employee \n"
						+ "4: Delete an employee \n"
						+ "5: Exit \n");
				int option = scanner.nextInt();
				
				switch(option) {
					case 1: 
						showEmployeeList();
						break;
						
					case 2: 
						showEmployee();
						break;

					case 3:
						System.out.println("What is the ID of the employee you want to add: ");
						id = scanner.nextInt();
//						if() { //if id exists 
//							throw new EmployeeAlreadyExist();
//						}
//						else {
							addEmployee(id);

//						}
						break;

					case 4:
						//throw exception if id is not there
//						throw new EmployeeDoesNotExist();
						deleteEmployee(id);
						break;
						
					case 5:
						exit();
						break;
						
					default: 
						break;

				}
//			} catch (EmployeeAlreadyExist e) {
//				
//			} catch (EmployeeDoesNotExist e) {
//				
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid menu option 1-5 only! ");
				scanner.next();
			} 
			
		}
		
		private static void showEmployeeList() {
			
		}
		
		private static void showEmployee() {

			
		}
		
		private static void addEmployee(int id) {
			
			
			
		}
		
		private static void deleteEmployee(int id) {
			
			
			
		}
		
		private static void exit() {
			String yn;
			boolean repeat = true;
			while(repeat == true) {
				try {
					System.out.println("Are you sure you would like to exit? [y/n]");		
					yn = scanner.next();
					if (yn.equals("y")){
						continuous = false;
						repeat = false;
						System.out.println("Program Closed.");
						break;
					}
					else if (yn.equals("n")) {
						continuous = true;
						showMenu(); //double check this, you want it to loop back 
					}
					else {
						throw new InputMismatchException();
					}
					
				} catch(InputMismatchException e) {
					System.out.println("Please enter y or n only! ");
				}  
			}
			
			
		}
		
	

}
