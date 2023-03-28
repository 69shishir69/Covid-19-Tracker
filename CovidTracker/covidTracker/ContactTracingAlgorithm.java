package covidTracker;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.table.DefaultTableModel;

// main algorithm for finding covid-in-contact traces

public class ContactTracingAlgorithm {
	int vertices;
	int graph [][];
	ContactTracingAlgorithm(int V){
		this.vertices=V;
		graph=new int[vertices][vertices];
	}
	
	public void addEdge(int source, int destination) {
		graph[source][destination]=1;
	}
	public void printMatrix() {
		for(int i=0;i<vertices;i++) {
			for(int j=0;j<vertices;j++) {
				System.out.print(graph[i][j]+" ");
				}
			System.out.println("");
		}
	}
	
	
	// returns the covid-date of given provided ID 
	public String findDate(int vertex) {
		String covidDate = "";
		try {
        	File f = new File("D:\\fileHandeling_java\\patientDetails.txt");
        	Scanner reader = new Scanner(f);
            // reads the lines of 
            while(reader.hasNextLine()) {
            	String line = reader.nextLine();
            	String data[] = line.split(",");
            	int id = Integer.parseInt(data[0]);
            	if(id==vertex) {
            		covidDate = data[4];
            	}
            	
            }
            reader.close();       
        }
        catch(Exception ee) {
            ee.printStackTrace();
        }
		return covidDate;
		
	}
	
	
    // returns the list of connected ID of the provided ID
	public List<Integer> get_CovidContactList(int vertex) {
		List<Integer> list=new ArrayList<Integer>(); 
		for(int j=0;j<vertices;j++) {
			if(graph[vertex][j]!=0) {
				list.add(j);
			}
		}
		return list;
	}
	
    // returns true if the given ID is present in contactTracingDetails.txt
	public boolean is_inFile(int vertex) {
		boolean condition = false;
		try {
        	File f = new File("D:\\fileHandeling_java\\contactTracingDetails.txt");
        	Scanner reader = new Scanner(f);
            
            while(reader.hasNextLine()) {
            	String line = reader.nextLine();
            	String data[] = line.split(",");
            	int id = Integer.parseInt(data[0]);
            	if(id==vertex) {
            		condition = true;
            	}
            }
            reader.close();       
        }
        catch(Exception ee) {
            ee.printStackTrace();
        }
		return condition;
	}

	// returns the string after adding details to the string variable. 
	public String printEdge(int vertex) {
		String patientDetail1 = "";
			if(is_inFile(vertex)) {
				String str1 = "\nPatient "+vertex+" is in contact With: ";
				patientDetail1 = patientDetail1+ str1;
				// checking if there is connection
				for(int j=0;j<vertices;j++) {
					if(graph[vertex][j]!=0) {
						try {
				        	File f = new File("D:\\fileHandeling_java\\patientDetails.txt");
				        	Scanner reader = new Scanner(f);
				            while(reader.hasNextLine()) {
				            	String line = reader.nextLine();
				            	String data[] = line.split(",");
				            	int id = Integer.parseInt(data[0]);
						    // compares the id of patientdetails txt file with connected id's
				            	if(id==j) {
				            		String s1 = " \nPatientid " + j + " which has patientdetails as::: \n        name:" + data[1] + " ,\n        Number: "+ data[3] + " ,\n        Covid Date: " + data[4] +  " ,\n        Contacted Date: " + data[6];
				            		// checks if the person with the provided id is in contact with the person having covid. 
				            		if(data[4].equals("Null") && !findDate(vertex).equals("Null")) {
				            			s1 = s1 + " and the person is at very high risk";
				            		}// checks if the person with the provided id is in contact with the person not having. 
							else if(data[4].equals("Null") &&findDate(vertex).equals("Null")) {
				            			s1 = s1 + " and the person is at moderate risk";
				            		} else {
				            			s1 = s1 + " and the person has covid";
				            		}
				            		patientDetail1 = patientDetail1 + s1;
				            	}
				            }
				            reader.close();       
				        }
				        catch(Exception ee) {
				            ee.printStackTrace();
				        }
					}
				}
			}
			return patientDetail1;
	}
	
	

}
