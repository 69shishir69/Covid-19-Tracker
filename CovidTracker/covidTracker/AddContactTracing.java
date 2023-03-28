package covidTracker;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AddContactTracing extends JFrame implements MouseListener, ActionListener {
    JLabel titlelbl;
    JLabel covidIdlbl;
    JLabel contactedIdlbl;
    
    
    JTextField txtcovidId;
    JTextField txtcontactedId;
    
    
    JButton addbtn;
    JButton backbtn;
    
    JTable tbl;
    DefaultTableModel model;

    
    AddContactTracing() {
    	
    	
        setTitle("Add Contact Tracing");
       setLayout(null);

        titlelbl=new JLabel("Add Contact Tracing");
        covidIdlbl = new JLabel("Covid Patient Id:");
        contactedIdlbl=new JLabel("Contacted Person Id:");
       
        txtcovidId = new JTextField(10);
        txtcontactedId=new JTextField(10);
        
        addbtn = new JButton("Add");
        backbtn = new JButton("Back");
        
        addbtn.addActionListener(this);
        backbtn.addActionListener(this);
        
	     titlelbl.setBounds(50, 43, 259, 30);
	     titlelbl.setFont(new Font("Serif", Font.BOLD, 25));
	    
	     covidIdlbl.setBounds(50, 113, 120, 25);
	     contactedIdlbl.setBounds(50, 174, 120, 25);
	     
	     
	     txtcovidId.setBounds(50, 138, 309, 25);
	     txtcontactedId.setBounds(50, 201, 309, 25);
	     
	     
	     addbtn.setBounds(50, 248, 309, 25);
	     backbtn.setBounds(50, 298, 309, 25);
	     
	     String [] cols= {"Patient_Id","Name","Address","Phone","Covid Date","Created Date","Contacted Date"};
	     model=new DefaultTableModel(cols,0);
	     tbl=new JTable(model);
	     
	     JScrollPane sp=new JScrollPane(tbl);
	     sp.setBounds(400, 50, 632, 370);
	    add(sp);
	     tbl.addMouseListener(this);
	     
	    add(titlelbl);
	    add(covidIdlbl);
	    add(txtcovidId);
	    add(contactedIdlbl);
	    add(txtcontactedId);
	     
	    add(addbtn);
	    add(backbtn);
	     
	     
	    
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     setSize(1075,500);
	     setVisible(true);
         
	     displayTable();
             
    }
    
    public void displayTable() {
	    // displays the table after reading patientdetails txt file
        try {
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
    
 
    public void reset() {
    	txtcovidId.setText("");
    	txtcontactedId.setText("");
        
        }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();
        // adds the tracing details to the file
        if(source==addbtn) {
        	String CovidId = txtcovidId.getText();
        	String contactedId = txtcontactedId.getText();

            if (CovidId.isEmpty()==false && contactedId.isEmpty() == false) {

                String userdata = CovidId + "," + contactedId ;
                try {
                    File f = new File("D:\\fileHandeling_java\\contactTracingDetails.txt");
                    if (!f.exists()) {
                        f.createNewFile();
                    }
                    FileWriter fw = new FileWriter(f, true);
                    fw.write(userdata);
                    fw.write("\n");
                    fw.flush();
                    fw.close();
                } catch (Exception a) {
                    a.printStackTrace();
                }
                
                JOptionPane.showMessageDialog(null, "Contact Added Successfully");
                reset();
            } else {
                JOptionPane.showMessageDialog(null, "Addition Failed");
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
