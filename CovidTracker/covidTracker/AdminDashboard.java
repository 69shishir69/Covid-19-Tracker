package covidTracker;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class AdminDashboard extends JFrame implements MouseListener, ActionListener{
       
     JButton addPatientDetailsbtn;
     JButton addContactTracingbtn;
     JButton viewContactTracingbtn;
     JButton logoutbtn;
     
     
     JTable tbl;
     DefaultTableModel model;
     JLabel titlelbl;

     AdminDashboard() {
       setTitle("Admin");
      setLayout(null);
       
       addPatientDetailsbtn=new JButton("Add Patient Details");
       addPatientDetailsbtn.setBackground(UIManager.getColor("Button.background"));
       addContactTracingbtn=new JButton("Add Contact Tracing");
       addContactTracingbtn.setBackground(UIManager.getColor("Button.background"));
       viewContactTracingbtn=new JButton("View Contact Tracing");
       viewContactTracingbtn.setBackground(UIManager.getColor("Button.background"));
       logoutbtn=new JButton("Logout");
       logoutbtn.setBackground(UIManager.getColor("Button.background"));
       
       titlelbl = new JLabel("Admin Dashboard");
       titlelbl.setBounds(41, 53, 337, 40);
       titlelbl.setFont(new Font("Bahnschrift", Font.PLAIN, 40));
       addPatientDetailsbtn.addActionListener(this);
       addContactTracingbtn.addActionListener(this);
       viewContactTracingbtn.addActionListener(this);
       logoutbtn.addActionListener(this);

       addPatientDetailsbtn.setBounds(31, 130, 170, 81);
       addContactTracingbtn.setBounds(213, 130,  170, 81);
       viewContactTracingbtn.setBounds(31, 222,  170, 81);
       logoutbtn.setBounds(213, 222,  170, 81);
       
       
      add(titlelbl);
      add(addPatientDetailsbtn);
      add(addContactTracingbtn);
      add(viewContactTracingbtn);
      add(logoutbtn);

       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setSize(425,388);
       setVisible(true); 
     }
       
            


   @Override
   public void actionPerformed(ActionEvent e) {
       Object source=e.getSource();
       
       if(source==addPatientDetailsbtn) {
           AddPatientDetails f = new AddPatientDetails();
           dispose();
       }else if (source == logoutbtn) {
           Login f = new Login();
           dispose();
       }else if (source == addContactTracingbtn) {
           AddContactTracing f = new AddContactTracing();
           dispose();
       } else if (source == viewContactTracingbtn) {
           ViewContactTracing f = new ViewContactTracing();
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
