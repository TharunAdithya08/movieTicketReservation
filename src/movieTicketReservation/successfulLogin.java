package movieTicketReservation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;


public class successfulLogin {
	successfulLogin(Connection conn,Statement stmt,ResultSet rs,String dataArr[]){
		
		JFrame mainFrame = new JFrame("Book tickets");
		JOptionPane.showMessageDialog(mainFrame,"Login Successful!!");
		JOptionPane.showMessageDialog(mainFrame,"Choose City!!");
		String s[] = {"Coimbatore","Chennai"};
		JComboBox cb = new JComboBox(s);
		
		cb.setBounds(50,70,150,30);
		JButton cityB = new JButton("Select City");
		cityB.setBounds(250,70,125,30);
		JButton back = new JButton("<");
        back.setBounds(20,20,60,35);
        mainFrame.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home obj1 = new home();
				obj1.main(null);
				mainFrame.dispose();
			}
		});
		mainFrame.add(cb);
		mainFrame.add(cityB);
		cityB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(mainFrame,"Choose Movie!!");
				String city = (String) cb.getItemAt(cb.getSelectedIndex());
				dataArr[1] = city;
				String query = "";
				try {
					switch(city) {
						case "Coimbatore":
							int count = 0,temp=0;
							query = "select distinct movName from moviedetails where city like \""+city+"\"";
//							movPage movpg = new movPage(conn,stmt,query);
//							mainFrame.dispose();
							ResultSet rs = stmt.executeQuery(query);
							while(rs.next()) {
								count++;
							}
							rs = stmt.executeQuery(query);
							String []movList = new String[count];
							while(rs.next()) {
								movList[temp++] = rs.getString("movName");
							}
							JComboBox movSel = new JComboBox(movList);
							movSel.setBounds(50,150,150,30);
							JButton movB = new JButton("Select Movie");
							movB.setBounds(250,150,150,30);
							mainFrame.add(movSel);
							mainFrame.add(movB);
							movB.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent ee) {
									
									String movie = (String) movSel.getItemAt(movSel.getSelectedIndex());
									dataArr[2] = movie;
									final String query1 = "select distinct venue from moviedetails where movName like \""+movie+"\" and city like \""+city+"\"";
									selectVenue ven = new selectVenue(conn,stmt,query1,dataArr);
									mainFrame.dispose();
								}
							});
							break;
						case "Chennai":
							count = 0;
							temp=0;
							query = "select distinct movName from moviedetails where city like \""+city+"\"";
//							movPage movpg = new movPage(conn,stmt,query);
//							mainFrame.dispose();
							rs = stmt.executeQuery(query);
							while(rs.next()) {
								count++;
							}
							rs = stmt.executeQuery(query);
							String []movList2 = new String[count];
							while(rs.next()) {
								movList2[temp++] = rs.getString("movName");
							}
							JComboBox movSel2 = new JComboBox(movList2);
							movSel2.setBounds(50,150,150,30);
							JButton movB2 = new JButton("Select Movie");
							movB2.setBounds(250,150,150,30);
							mainFrame.add(movSel2);
							mainFrame.add(movB2);
							movB2.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent ee) {
									
									String movie2 = (String) movSel2.getItemAt(movSel2.getSelectedIndex());
									dataArr[2] = movie2;
									final String query2 = "select distinct venue from moviedetails where movName like \""+movie2+"\" and city like \""+city+"\"";
									selectVenue ven = new selectVenue(conn,stmt,query2,dataArr);
									mainFrame.dispose();
								}
							});
							break;
						
					}
				}catch(Exception movE) {
					System.out.println(e);
				}
			}
		});
		
		
		mainFrame.setSize(800,400);
		mainFrame.setLayout(null);
		mainFrame.setVisible(true);
	}
}
