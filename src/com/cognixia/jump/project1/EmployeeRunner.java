package com.cognixia.jump.project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;


public class EmployeeRunner {
	static Scanner scanner = new Scanner(System.in);
	static boolean continuous = true;
	static int id;
	static List<Employee> employees;

	public static void main(String[] args) throws IOException,IDDoesExistException, IDDoesNotExistException {

		employees = new ArrayList<Employee>(readFile());

		System.out.println("Welcome! You are now accessing Drop Inc. employee files.\n");

		while (continuous== true) {
			showMenu();

		}

	}

	/**
	 * Reads text file and creates an employee object for each line, inserted to an array list
	 * @return list of employees
	 */
	private static List<Employee> readFile() {
		List<Employee> eList = new ArrayList<Employee>();

		File file = null;
		FileReader fileReader = null; 
		BufferedReader reader = null;

		try {
			file = new File("resources/employees.txt");
			fileReader = new FileReader(file);
			reader = new BufferedReader(fileReader);

			String line; 

			while((line = reader.readLine()) != null) {
				String[] tokens = line.split(" ");
				Employee emp = new Employee(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3], tokens[4]);

				eList.add(emp);
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

		return eList;

	}

	/**
	 * Prints menu and interacts with user
	 * @throws IOException
	 * @throws IDDoesExistException
	 * @throws IDDoesNotExistException
	 */
	private static void showMenu() throws IOException, IDDoesExistException, IDDoesNotExistException{

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
				System.out.println("What is the ID of the employee you want to view: ");
				id = scanner.nextInt();
				showEmployee(id);
				break;

			case 3:
				System.out.println("What is the ID of the employee you want to add: ");
				id = scanner.nextInt();
				addEmployee(id);
				break;

			case 4:
				System.out.println("What is the ID of the employee you want to delete: ");
				id = scanner.nextInt();
				deleteEmployee(id);
				break;

			case 5:
				exit();
				break;

			default: 
				throw new InputMismatchException();

			}			
		} catch (InputMismatchException e) {
			System.out.println("Please enter a valid menu option 1-5 only!\n");
			showMenu();
			scanner.next();
		} 

	}

	/**
	 * Checks if ID already exits
	 * @param id
	 * @return True or False
	 */
	private static boolean IDExistence(int id){
		Employee findID = employees.stream()
				.filter(n -> (n.getId() == id))
				.findAny()
				.orElse(null);

		if(findID != null) {
			return true;

		} else {
			return false;
		}

	}

	/**
	 * Prints full Employee list 
	 */
	private static void showEmployeeList() {
		System.out.println("--- Current Employee List ---");
		System.out.println("ID      Name");
		for (Employee emp : employees) {
			System.out.println(emp.preview());
		}
		System.out.println("\n");

	}

	/**
	 * Prints full information of specific Employee based on ID
	 * @param id
	 * @throws IDDoesNotExistException
	 */
	private static void showEmployee(int id) throws IDDoesNotExistException{
		if(IDExistence(id) == true) {
			for (Employee emp : employees) {
				if(id == emp.getId()){
					System.out.println(emp.toString());
				}	
			}
		}
		else {
			throw new IDDoesNotExistException();
		}

	}

	/**
	 * Adds an employee initialized with ID. If ID already exists, throws an exception
	 * @param id
	 * @throws IOException
	 * @throws IDDoesExistException
	 */
	private static void addEmployee(int id) throws IOException, IDDoesExistException, IDDoesNotExistException{
		String yn;
		boolean repeat = true;
		Employee emp = new Employee(id);

		File file = null;
		FileWriter fileWriter = null;
		BufferedWriter buffWriter = null;
		PrintWriter printWriter = null;

		try {
			file = new File("resources/employees.txt");
			fileWriter = new FileWriter(file, true);
			buffWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(buffWriter, true);

			if(IDExistence(id) == true) {
				throw new IDDoesExistException();
			}
			else {
				while(repeat == true) {
					System.out.println("You are adding an Employee to Departmnent: " + emp.getDepartment().toString() 
							+ ", Correct? [y/n]");
					yn = scanner.next();

					if (yn.equals("y")){
						repeat = false;					
					}
					else if (yn.equals("n")) {
						repeat = false;
						System.out.println("Enter a new ID:");
						id = scanner.nextInt();
						emp = new Employee(id);
						repeat = true;

					}
					else {
						throw new InputMismatchException();
					}
				}

				System.out.println("Enter NEW Employee First Name: ");
				String fName = scanner.next();
				emp.setFirstName(fName);

				System.out.println("Enter NEW Employee Last Name: ");
				String lName = scanner.next();
				emp.setLastName(lName);

				System.out.println("Enter NEW Employee Email: ");
				String email = scanner.next();
				emp.setEmail(email);

				System.out.println("Enter NEW Employee Position(No spaces): ");
				String position = scanner.next();
				emp.setPosition(position);

				employees.add(emp);
				printWriter.println(emp.fileFormat());

			} 

		} catch(InputMismatchException e) {
			System.out.println("Please enter y or n only! ");
			addEmployee(id);

		} catch (IOException e) {
			System.out.println("File Does Not Exist!");
		} finally {
			if (buffWriter != null) {
				try {
					buffWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (printWriter != null) {
				printWriter.close();
			}
		}

	}

	/**
	 * Deletes Employee from array list and file
	 * @param id
	 * @throws IDDoesNotExistException
	 * @throws IOException, FileNotFoundException 
	 * @throws NumberFormatException 
	 */
	private static void deleteEmployee(int id) throws IDDoesNotExistException, NumberFormatException, IOException, FileNotFoundException{

		File file = null;
		File temp = null;
		FileReader fileReader = null; 
		BufferedReader reader = null;
		FileWriter fileWriter = null;
		BufferedWriter buffWriter = null;
		PrintWriter writer = null;

		try {

			if(IDExistence(id) == true) {
				file = new File("resources/employees.txt");
				temp = File.createTempFile("tmp", "");

				fileReader = new FileReader(file);
				reader = new BufferedReader(fileReader);
				fileWriter = new FileWriter(temp, true);
				buffWriter = new BufferedWriter(fileWriter);
				writer = new PrintWriter(buffWriter, true);

				String line;

				while((line = reader.readLine()) != null) {

					String[] tokens = line.split(" ");

					if(Integer.parseInt(tokens[0]) == id) {

						for (Employee emp : employees) {
							if(emp.getId() == id) {
								employees.remove(employees.indexOf(emp));
								System.out.println("Employee " + emp.getId() 
									+ " " + emp.getFirstName() 
									+ " " + emp.getLastName()
									+ " " +  " has been deleted!\n");
								break;
							}
						}

					}
					else {
						writer.println(line);
					}

				}

				File oldFile = file;
				if (oldFile.delete())
					temp.renameTo(oldFile);
			}
			else {
				throw new IDDoesNotExistException();
			}

		} catch(FileNotFoundException e){
			System.out.println("This file does not exist!");
		} catch(IOException e) {
			System.out.println("Could not read character!");
		} catch(NullPointerException e) {

		} catch(IDDoesNotExistException e) {

		}
		finally {
			try {
				fileReader.close();
				reader.close();

				if (buffWriter != null) {
					try {
						buffWriter.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				if (fileWriter != null) {
					try {
						fileWriter.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				if (writer != null) {
					writer.close();
				}

			} catch(IOException e) {
				System.out.println("Could not close reader/writer objects!");
			} catch(NullPointerException e) {

			}
		}	

	}

	/**
	 * Exits the Program
	 * @throws IOException
	 * @throws IDDoesExistException
	 * @throws IDDoesNotExistException
	 */
	private static void exit() throws IOException, IDDoesExistException, IDDoesNotExistException{
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
