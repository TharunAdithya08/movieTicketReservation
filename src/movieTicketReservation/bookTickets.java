package movieTicketReservation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class bookTickets{
	bookTickets(Connection conn,Statement stmt,ArrayList<Integer> al,String dataArr[]){
		al.add(1);
		String query = "select * from "+dataArr[3]+";";
			
		JFrame contentPane = new JFrame("Book Tickets");
		JLabel seats[] = new JLabel[18];
		contentPane.setSize(800,400);
		contentPane.setVisible(true);
		contentPane.setLayout(null);
		try {
			int x = -29;
			ResultSet rs = stmt.executeQuery(query);
			JButton back = new JButton("<");
	        back.setBounds(20,20,60,35);
	        contentPane.add(back);
			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					successfulLogin movPage = new successfulLogin(conn,stmt,rs,dataArr);
					contentPane.dispose();
				}
			});
			JLabel heading = new JLabel("Book tickets ");
			heading.setFont(new Font("Script MT Bold", Font.BOLD, 24));
			heading.setBounds(330, 11, 142, 42);
			contentPane.add(heading);
			
			JTextField seatNum= new JTextField();
			seatNum.setBounds(208, 84, 39, 25);
			contentPane.add(seatNum);
			seatNum.setColumns(10);
			
			JLabel seatSel= new JLabel();
			seatSel.setText("Select seat number:");
			seatSel.setFont(new Font("Times New Roman", Font.BOLD, 17));
			seatSel.setBounds(40, 84, 158, 25);
			contentPane.add(seatSel);
			
			JButton bookB = new JButton("Book");
			bookB.setFont(new Font("Times New Roman", Font.BOLD, 16));
			bookB.setBounds(69, 130, 89, 23);
			contentPane.add(bookB);
			
			bookB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
						String seat = seatNum.getText();
						try {
							final ResultSet rs1 = stmt.executeQuery("select avail from "+dataArr[3]+" where seat_no like \""+seat+"\";");
							rs1.next();
							if(rs1.getBoolean("avail")==true) {
							String query = "update "+dataArr[3]+" set avail = false where seat_no like \""+seat+"\";";
							try{
								stmt.executeUpdate(query);
							}catch(Exception tempE) {
								System.out.println(tempE);
							}
				
						fileWriter receipt = new fileWriter(conn,stmt,dataArr,seat);
						bookTickets custMov = new bookTickets(conn,stmt,al,dataArr);
						contentPane.dispose();
							}
							else {
								JOptionPane.showMessageDialog(contentPane,"Please choose a valid seat");
							}
						}catch(Exception rep) {
							System.out.println(rep);
						}
				}
			});
			int size = al.size();
			float totAmnt = (float)190 * (float)(size-1);
			
			JLabel screenL = new JLabel("Screen this way");
			screenL.setBackground(new Color(128, 128, 128));
			screenL.setHorizontalAlignment(SwingConstants.CENTER);
			screenL.setFont(new Font("Times New Roman", Font.BOLD, 15));
			screenL.setForeground(new Color(0, 0, 0));
			screenL.setBounds(180, 300, 261, 25);
			contentPane.add(screenL);
			
			JLabel amnt = new JLabel();
			amnt.setFont(new Font("Times New Roman", Font.BOLD,20));
			amnt.setBackground(new Color(0, 255, 255));
			amnt.setText("Total Amount : "+totAmnt);
			amnt.setBounds(500, 55, 400, 59);
			contentPane.add(amnt);
			
			JButton payB = new JButton("Pay Amount");
			payB.setFont(new Font("Times New Roman", Font.BOLD, 16));
			payB.setBounds(510, 115, 140, 23);
			contentPane.add(payB);
			
			payB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPane.dispose();
					JFrame tq = new JFrame("Book Tickets");
					JLabel l1 = new JLabel("Booking Confirmed!!");
					l1.setBounds(300,125,300,80);
					l1.setFont(new Font("Script MT Bold", Font.BOLD, 24));
					JLabel l2 = new JLabel("Ticket Generated");
					JButton back = new JButton("HOME");
			        back.setBounds(30,40,90,35);
			        back.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							home obj1 = new home();
							obj1.main(null);
							tq.dispose();
						}
					});
			        tq.add(back);
			        tq.add(l2);
					l2.setBounds(325,300,200,80);
					tq.add(l1);
					tq.setSize(800,400);
					tq.setLayout(null);
					tq.setVisible(true);
				}
			});
			
			rs.next();
			for(int i=0;i<6;i++) {
				x += 80;
				if(rs.getBoolean("avail")==true)
					seats[i] = new JLabel(rs.getString("seat_no"));
				else
					seats[i] = new JLabel("N/A");
				seats[i].setHorizontalAlignment(SwingConstants.CENTER);
				seats[i].setFont(new Font("Times New Roman", Font.BOLD, 20));
				seats[i].setBounds(x, 153, 100, 70);
				contentPane.add(seats[i]);
				rs.next();
			}
			x = -29;
			for(int i=6;i<12;i++) {
				x += 80;
				if(rs.getBoolean("avail")==true)
					seats[i] = new JLabel(rs.getString("seat_no"));
				else
					seats[i] = new JLabel("N/A");
				seats[i].setHorizontalAlignment(SwingConstants.CENTER);
				seats[i].setFont(new Font("Times New Roman", Font.BOLD, 20));
				seats[i].setBounds(x, 189, 100, 70);
				contentPane.add(seats[i]);
				rs.next();
			}
			x = -29;
			for(int i=12;i<18;i++) {
				x += 80;
				if(rs.getBoolean("avail")==true)
					seats[i] = new JLabel(rs.getString("seat_no"));
				else
					seats[i] = new JLabel("N/A");
				seats[i].setHorizontalAlignment(SwingConstants.CENTER);
				seats[i].setFont(new Font("Times New Roman", Font.BOLD, 20));
				seats[i].setBounds(x, 225, 100, 70);
				contentPane.add(seats[i]);
				rs.next();
			}

		}catch(Exception bookE) {
			System.out.println(bookE);
		}
				
	}
	
}