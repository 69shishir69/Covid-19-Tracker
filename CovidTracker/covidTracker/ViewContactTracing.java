package covidTracker;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewContactTracing extends JFrame implements MouseListener, ActionListener {
    JLabel titlelbl;
    JLabel patientIdlbl;
    
    
    JTextField txtpatientId;
    
    JTextArea txtareaDetails;
    JScrollPane scroll;
    JScrollPane sp;
    
    
    JButton showbtn;
    JButton showVertexbtn;
    JButton backbtn;
    JButton showModerateRisk;
    JButton showHighRisk;
    
    JTable tbl;
    DefaultTableModel model;
    
    JTable tbl1;
    DefaultTableModel model1;
    
    
    
    ViewContactTracing() {
    	
        setTitle("Show Tracked Details");
        setLayout(null);

        titlelbl=new JLabel("Show Tracked Details");
        patientIdlbl = new JLabel("Covid Patient Id:");
       
        txtpatientId = new JTextField(10);
        
        txtareaDetails = new JTextArea();
        txtareaDetails.setBounds( 30, 200 ,400 ,600 );
        txtareaDetails.setEditable(false);
        txtareaDetails.setVisible(true);
        
        showbtn = new JButton("Show Patient Details");
        showVertexbtn = new JButton("Show Patient Tree");
        backbtn = new JButton("Back");
        showModerateRisk = new JButton("Show ModerateRisk");
        showHighRisk = new JButton("Show HighRisk");
        
        showbtn.addActionListener(this);
        showVertexbtn.addActionListener(this);
        backbtn.addActionListener(this);
        showHighRisk.addActionListener(this);
        showModerateRisk.addActionListener(this);
        
	     titlelbl.setBounds(450, 26, 300, 30);
	     titlelbl.setFont(new Font("Bahnschrift", Font.BOLD, 25));
	    
	     patientIdlbl.setBounds(30, 69, 120, 25);
	     
	     txtpatientId.setBounds(30, 92, 400, 25);
	     
	     showbtn.setBounds(30, 128, 200, 25);
	     backbtn.setBounds(450, 775, 100, 25);
	     showVertexbtn.setBounds(239, 128, 191, 25);
	     showHighRisk.setBounds(30,164 , 199, 25);
	     showModerateRisk.setBounds(239,164 , 191, 25);
	     
	     String [] cols= {"Patient_Id","Name","Address","Phone","Covid Date","Created Date","Contacted Date"};
	     model=new DefaultTableModel(cols,0);
	     tbl=new JTable(model);
	     
	     sp=new JScrollPane(tbl);
	     sp.setBounds(450, 84, 600, 305);
	     add(sp);
	     tbl.addMouseListener(this);
	     
	     
	     String [] cols1= {"Patient_Id","Name","Address","Phone","Covid Date","Created Date","Contacted Date"};
	     model1=new DefaultTableModel(cols1,0);
	     tbl1=new JTable(model1);
	     
	     JScrollPane sp1=new JScrollPane(tbl1);
	     sp1.setBounds(450, 400, 600, 350);
	     add(sp1);
	     tbl1.addMouseListener(this);
	     
	     
//	     Scroll bar
	     
	     
	     add(titlelbl);
	     add(patientIdlbl);
	     add(txtpatientId);
	     
	     add(txtareaDetails);
	     
	     add(showbtn);
	     add(showVertexbtn);
	     add(backbtn);
	     add(showModerateRisk);
	     add(showHighRisk);
	    
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     setSize(1100,850);
	     setVisible(true);
         
	     displayTable();
	     
    }
    
    // used for displaying patient data in a table
    public void displayTable() {
        try {
        	// reading patientDetails.txt file
        	File f = new File("D:\\fileHandeling_java\\patientDetails.txt");
        	Scanner reader = new Scanner(f);
            
            while(reader.hasNextLine()) {
            	String line = reader.nextLine();
            	String data[] = line.split(",");
                
                String id=data[0];
                String name=data[1];
                String address=data[2];
                String phone=data[3];
                String covidDate = data[4];
                String date = data[5];
                String contactedDate = data[6];
                model.addRow(new Object[] {id,name,address,phone,covidDate,date,contactedDate});
            }
            reader.close();       
        }
        catch(Exception ee) {
            ee.printStackTrace();
        }
    }
    
    // used for displaying high risk patient in a table
    public void displayHighTable() {
        	ContactTracingAlgorithm ct = new ContactTracingAlgorithm(100);
        	try {
        		// reading patientDetails.txt file
	        	File f = new File("D:\\fileHandeling_java\\patientDetails.txt");
	        	Scanner reader = new Scanner(f);
	            while(reader.hasNextLine()) {
	            	String line = reader.nextLine();
	            	String data[] = line.split(",");
	            	int id = Integer.parseInt(data[0]);
	            	// reading contactracingDetails file 
	            	File f1 = new File("D:\\fileHandeling_java\\contactTracingDetails.txt");
		        	Scanner reader1 = new Scanner(f1);
	            	while(reader1.hasNextLine()) {
	            		String line1 = reader1.nextLine();
		            	String data1[] = line1.split(",");
		            	int id1 = Integer.parseInt(data1[1]);
		            	int id2 = Integer.parseInt(data1[0]);
	            		if(id==id1) {
	            			//comparing the patientID from data list of patientDetails and contactTracingDetails with Null
		            		if(data[4].equals("Null") &&!ct.findDate(id2).equals("Null")) {
		            			 String ID=data[0];
		                         String name=data[1];
		                         String address=data[2];
		                         String phone=data[3];
		                         String covidDate = data[4];
		                         String date = data[5];
		                         String contactedDate = data[6];
		                         model1.addRow(new Object[] {ID,name,address,phone,covidDate,date,contactedDate});
		            		}
		            	}
	            		
	            	}
	            	reader1.close();
	            	
	            }
	            reader.close();  
	            
	        }
	        catch(Exception ee) {
	            ee.printStackTrace();
	        }
    }
    
    // used for displaying moderate risk patient data in a table
    public void displayModerateTable() {
        	ContactTracingAlgorithm ct = new ContactTracingAlgorithm(100);
        	try {
        		// reading patientDetails.txt file
	        	File f = new File("D:\\fileHandeling_java\\patientDetails.txt");
	        	Scanner reader = new Scanner(f);
	        	
	        	
	            while(reader.hasNextLine()) {
	            	String line = reader.nextLine();
	            	String data[] = line.split(",");
	            	int id = Integer.parseInt(data[0]);
	            	// reading contactracingDetails file 
	            	File f1 = new File("D:\\fileHandeling_java\\contactTracingDetails.txt");
		        	Scanner reader1 = new Scanner(f1);
	            	while(reader1.hasNextLine()) {
	            		String line1 = reader1.nextLine();
		            	String data1[] = line1.split(",");
		            	int id1 = Integer.parseInt(data1[1]);
		            	int id2 = Integer.parseInt(data1[0]);
	            		if(id==id1) {
	            			//comparing the patientID from data list of patientDetails and contactTracingDetails with Null
		            		if(data[4].equals("Null") &&ct.findDate(id2).equals("Null")) {
		            			 String ID=data[0];
		                         String name=data[1];
		                         String address=data[2];
		                         String phone=data[3];
		                         String covidDate = data[4];
		                         String date = data[5];
		                         String contactedDate = data[6];
		                         model1.addRow(new Object[] {ID,name,address,phone,covidDate,date,contactedDate});
		            		}
		            	}
	            	}
	            	reader1.close();
	            	
	            }
	            reader.close();  
	        }
	        catch(Exception ee) {
	            ee.printStackTrace();
	        }
    }
    
    // for removing the table
    public void remove_table() {
        for(int i=model1.getRowCount()-1;i>=0;i--) {
            model1.removeRow(i);
        }
       
   }
    

 
    // resets text field
    public void reset_txtField() {
    	txtpatientId.setText("");
        }
    
    // resets text area
    public void reset_txtArea() {
    	txtareaDetails.setText("");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();
        
        if(source==showHighRisk) {
        	reset_txtArea();
        	remove_table();
        	displayHighTable();
        }
        else if(source==showModerateRisk) {
        	reset_txtArea();
        	remove_table();
        	displayModerateTable();
        }
        
        // shows details of a person and their risks 
        else if(source==showbtn) {
        	reset_txtArea();
        	String final_detail = "";
        	String PatientId = txtpatientId.getText();
        	int data1 = Integer.parseInt(PatientId);
            if (PatientId.isEmpty()==false ) {
		    // represents the connection in 2D array by reading contacttracing txt file
                try {
                	ContactTracingAlgorithm ct = new ContactTracingAlgorithm(100);
                	try {
                    	File f = new File("D:\\fileHandeling_java\\contactTracingDetails.txt");
                    	Scanner reader = new Scanner(f);
                        
                        while(reader.hasNextLine()) {
                        	String line = reader.nextLine();
                        	String data[] = line.split(",");
                        	int int1 = Integer.parseInt(data[0]);
                        	int int2 = Integer.parseInt(data[1]);
                            ct.addEdge(int1,int2);
                        }
                        reader.close();       
                    }
                    catch(Exception ee) {
                        ee.printStackTrace();
                    }
            		String textArea = ct.printEdge(data1);
            		final_detail= final_detail + textArea;
            		List<Integer> list1 = ct.get_CovidContactList(data1);
            		// looping for 3rd degree of id
            		for(int i=0; i<list1.size();i++) {
            			String textArea21 = ct.printEdge(list1.get(i));
            			final_detail =final_detail + textArea21;
            		}
            		
            		txtareaDetails.setText(final_detail);
            		
                	
                } catch (Exception a) {
                    a.printStackTrace();
                }
                reset_txtField();
            } else {
                JOptionPane.showMessageDialog(null, "Showing Failed");
            }
        }
        // shows connected ID upto 3rd vertex by opening Viewnodes class
        else if(source == showVertexbtn) {
        	String PatientId = txtpatientId.getText();
        	int data1 = Integer.parseInt(PatientId);
//      shows error if no id is selected for viewing nodes  	
            try {
            ViewNodes k = new ViewNodes(data1);
            } catch(Exception ee) {
            	JOptionPane.showMessageDialog(null, "Select a Patient ID");
            }
        }
        else if(source==backbtn) {
            AdminDashboard f = new AdminDashboard();
            dispose();
        }

    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row=tbl.rowAtPoint(e.getPoint());
        txtpatientId.setText(model.getValueAt(row, 0).toString());
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
