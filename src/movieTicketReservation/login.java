package movieTicketReservation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class login {
	login(Connection conn,Statement stmt,ResultSet rs){
		 
		String dataArr[] = new String[4];
		JFrame loginFrame = new JFrame("Book tickets");
		loginFrame.setSize(800,400);
		JLabel emailL = new JLabel("Enter your email ID:");
		emailL.setFont(new Font("Times New Roman", Font.BOLD, 17));
		JTextField tf7 = new JTextField();
		JLabel pwdL = new JLabel("Enter the password:");
		pwdL.setFont(new Font("Times New Roman", Font.BOLD, 17));
		JPasswordField tf8 = new JPasswordField();
		JButton loginB = new JButton("Login");
		loginB.setBounds(250,200,150,30);
		emailL.setBounds(30,70,250,30);
		tf7.setBounds(210,70,200,30);
		pwdL.setBounds(30,120,250,30);
		tf8.setBounds(210,120,200,30);
		JButton back = new JButton("<");
        back.setBounds(20,20,60,35);
        loginFrame.add(back);
		loginFrame.add(emailL);
		loginFrame.add(tf7);
		loginFrame.add(pwdL);
		loginFrame.add(tf8);
		loginFrame.add(loginB);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home obj1 = new home();
				obj1.main(null);
				loginFrame.dispose();
			}
		});
		loginB.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent e)
	            {
	            	int flagLog = 0;
	            	try{
	            		String user_id,pwd;
	            		int flag;
	            		flag =0;
	            		
		        	    dataArr[0] = user_id = tf7.getText();
		                pwd = tf8.getText();
		                if(user_id.length()!=0 && pwd.length()!=0) {
			                String query = "select email_id from validate;";
			                final ResultSet rs1 = stmt.executeQuery(query);
			                while(rs1.next()) {
			                	if(rs1.getString(1).equals(user_id))
			                		flag = 1;
			                }
			                if(flag==1) {
			                	query = "select pwd from validate where email_id like \""+ user_id +"\";";
			                	ResultSet rs2 = stmt.executeQuery(query);
			                	while(rs2.next()) {
			                		if(rs2.getString(1).equals(pwd)) {
			                			successfulLogin movPage = new successfulLogin(conn,stmt,rs,dataArr);
			                			flagLog = 1;
			                		}
			                		else {
			                			JOptionPane.showMessageDialog(loginFrame,"Wrong password");
				                		
			                		}
			                			
			                	}
			                }
			                else {
		                		JOptionPane.showMessageDialog(loginFrame,"User not found!");
		                	}
		            	}
		                else {
		                	JOptionPane.showMessageDialog(loginFrame,"Empty values not allowed!!");
		                }
	            	}catch(Exception LoginE) {
		            		System.out.println(LoginE);
		            	}
	            	if(flagLog==1)
	            		loginFrame.dispose();
	            }
	        });
		
		loginFrame.setLayout(null);
		loginFrame.setVisible(true);
		
	}
}
