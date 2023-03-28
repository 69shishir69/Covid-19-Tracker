package covidTracker;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import javax.swing.*;

public class Register extends JFrame implements ActionListener {
    JLabel titlelbl;
    JLabel usernamelbl;
    JLabel emaillbl;
    JLabel passwordlbl;
    JLabel cpasswordlbl;

    JTextField txtname;
    JTextField txtemail;
    JPasswordField txtpassword;
    JPasswordField txtcpassword;

    JButton registerbtn;
    JButton loginbtn;
    JButton clearbtn;

    Register() {
        setTitle("Register");
        setLayout(null);

        titlelbl = new JLabel("REGISTER");
        usernamelbl = new JLabel("Username:");
        emaillbl = new JLabel("Email:");
        passwordlbl = new JLabel("Password:");
        cpasswordlbl = new JLabel("Confirm Password:");

        txtname = new JTextField(10);
        txtemail = new JTextField(10);
        txtpassword = new JPasswordField(10);
        txtcpassword = new JPasswordField(10);

        registerbtn = new JButton("Register");
        loginbtn = new JButton("Back to Login");
        clearbtn = new JButton("Clear");

        registerbtn.addActionListener(this);
        loginbtn.addActionListener(this);
        clearbtn.addActionListener(this);

        titlelbl.setBounds(213, 29, 144, 30);
        titlelbl.setFont(new Font("Serif", Font.BOLD, 25));

        usernamelbl.setBounds(50, 71, 75, 25);
        emaillbl.setBounds(290, 71, 75, 25);
        passwordlbl.setBounds(50, 130, 75, 25);
        cpasswordlbl.setBounds(290, 130, 150, 25);

        txtname.setBounds(50, 94, 230, 25);
        txtemail.setBounds(290, 94, 230, 25);
        txtpassword.setBounds(50, 149, 230, 25);
        txtcpassword.setBounds(290, 149, 230, 25);

        registerbtn.setBounds(290, 185, 230, 25);
        clearbtn.setBounds(51, 187, 230, 25);
        loginbtn.setBounds(53, 230, 467, 25);

        add(titlelbl);
        add(usernamelbl);
        add(txtname);

        add(emaillbl);
        add(txtemail);
        add(passwordlbl);
        add(txtpassword);

        add(cpasswordlbl);
        add(txtcpassword);

        add(registerbtn);
        add(loginbtn);
        add(clearbtn);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(589, 333);
        setVisible(true);
    }


    public void reset() {
        txtname.setText("");
        txtemail.setText("");
        txtpassword.setText("");
        txtcpassword.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if (source == registerbtn) {
            String username = txtname.getText();
            String email = txtemail.getText();
            String password = txtpassword.getText();
            String cpassword = txtcpassword.getText();

            if (username.isEmpty() == false && email.isEmpty() == false && password.equals(cpassword)) {

                String userdata = username + "," + email + "," + password;
                try {
                    File f = new File("D:\\fileHandeling_java\\registerdetails.txt");
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

                JOptionPane.showMessageDialog(null, "Register Success");
                reset();
            } else {
                JOptionPane.showMessageDialog(null, "Register Failed");
            }

        } else if (source == clearbtn) {
            txtname.setText("");
            txtemail.setText("");
            txtpassword.setText("");
            txtcpassword.setText("");
        } else if (source == loginbtn) {
            Login f = new Login();
            dispose();
        }

    }
}
