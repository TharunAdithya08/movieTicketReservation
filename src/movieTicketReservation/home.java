package movieTicketReservation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class home {
	static final String url = "jdbc:mysql://localhost:3306/jdbc";
	static final String user = "root";
	static final String pwd = "12345678";
	public static void main(String args[]) {
		Connection conn;
		Statement stmt;
		ResultSet rs;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,pwd);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from validate;");
			JFrame homeFrame = new JFrame("Book Tickets");
			homeFrame.setSize(800,400);
			JLabel homeLabel = new JLabel("ONLINE TICKET BOOKING APP");
			homeLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
			homeLabel.setBounds(210,120,400,50);
			homeFrame.add(homeLabel);
			JButton login = new JButton("Login");
			login.setBounds(150,200,150,30);
			JButton register = new JButton("Register");
			register.setBounds(450,200,150,30);
			homeFrame.add(register);
			homeFrame.add(login);
			homeFrame.setLayout(null);
			homeFrame.setVisible(true);
			register.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent e)
	            {
	            	registration newUser = new registration(conn,stmt,rs);            
	            	homeFrame.dispose();
	            }
	        });
			login.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent e)
	            {
	            	login existingUser = new login(conn,stmt,rs); 
	            	homeFrame.dispose();
	            }
	        });
			
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		
	}
}
