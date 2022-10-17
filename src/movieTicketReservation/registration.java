package movieTicketReservation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class registration {
	registration(Connection conn,Statement stmt,ResultSet rs){
		JFrame frame = new JFrame("Book Tickets");
		JLabel l1 = new JLabel("Full Name:");
		frame.setSize(800,400);
		l1.setBounds(30,60,100,30);
		JTextField tf1 = new JTextField();
		tf1.setBounds(180,60,150,30);
		JLabel l2 = new JLabel("Phone Number:");
		l2.setBounds(30,100,140,30);
		JTextField tf2 = new JTextField();
		tf2.setBounds(180,100,150,30);
		JLabel l3 = new JLabel("Email Id:");
		l3.setBounds(30,150,140,30);
		JTextField tf3 = new JTextField();
		tf3.setBounds(180,150,150,30);

		JLabel l5 = new JLabel("Password:");
		l5.setBounds(30,180,140,30);
		JPasswordField tf5 = new JPasswordField();
		tf5.setBounds(180,180,150,30);
		JLabel l6 = new JLabel("Confirm Password:");
		l6.setBounds(30,220,140,30);
		JPasswordField tf6 = new JPasswordField();
		tf6.setBounds(180,220,150,30);
		JButton b = new JButton("Register");
        b.setBounds(120,280,100,40);
        JButton back = new JButton("<");
        back.setBounds(20,20,60,35);
        frame.add(b);
        frame.add(back);
		frame.add(l6);
		frame.add(tf6);
		frame.add(l5);
		frame.add(tf5);
		
		frame.add(l3);
		frame.add(tf3);
		frame.add(l2);
		frame.add(tf2);
		frame.add(l1);
		frame.add(tf1);
		String name,pwd,user_id;
		String phone_no;
		frame.setLayout(null);
		frame.setVisible(true);
		//frame.setResizeable(true);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home obj1 = new home();
				obj1.main(null);
				frame.dispose();
			}
		});
		
		 b.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent e)
	            {
	            	String name,pwd,user_id,confirm_pwd,phone_no;
	        		int flagReg = 0;
	                name = tf1.getText();
	                phone_no = tf2.getText();
	                user_id = tf3.getText();
	                pwd = tf5.getText();
	                confirm_pwd = tf6.getText();
	                if(name.length()!=0 && phone_no.length()!=0 && user_id.length()!=0 && pwd.length()!=0 && confirm_pwd.length()!=0){
		                if(pwd.equals(confirm_pwd)) {
		                	try {
		                		String query = "insert into validate values(\""+name+"\",\""+user_id+"\",\""+phone_no+"\",\""+pwd+"\");";
		                		stmt.executeUpdate(query);
		                		System.out.println("Inserted!!");
		                		login obj = new login(conn,stmt,rs);
		                		flagReg = 1;
		                	}
		                	catch(Exception regE) {
		                		System.out.println(regE);
		                		return;
		                	}
		                }
		                else {
		                	JOptionPane.showMessageDialog(frame,"Passwords do not match!!");
		                }
		                
	                }
	                else {
	                	JOptionPane.showMessageDialog(frame,"Empty values not allowed!!");
	                }
	                if(flagReg==1)
	                	frame.dispose();
	            }
	        });

	}
	

}
