package movieTicketReservation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;


public class selectVenue {
	selectVenue(Connection conn,Statement stmt,String query,String dataArr[]){
		ResultSet rs1 = null;
		JFrame venFrame = new JFrame("Book tickets");
		JButton back = new JButton("<");
        back.setBounds(20,20,60,35);
        venFrame.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				successfulLogin movPage = new successfulLogin(conn,stmt,rs1,dataArr);
				venFrame.dispose();
			}
		});
		try {
			int count=0;
			int temp=0;
			String venList[];
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
				count++;
			rs = stmt.executeQuery(query);
			venList = new String[count];
			while(rs.next()) {
				venList[temp++] = rs.getString("venue");
			}
			JComboBox venSel = new JComboBox(venList);
			venSel.setBounds(50,150,150,30);
			JButton venB = new JButton("Select Venue");
			venB.setBounds(250,150,150,30);
			venB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent vene) {
					
					String ven = (String) venSel.getItemAt(venSel.getSelectedIndex());
					dataArr[3] = ven;
					ArrayList<Integer> al = new ArrayList<>();
					
					bookTickets custMov = new bookTickets(conn,stmt,al,dataArr);
					venFrame.dispose();
				}
			});
			venFrame.add(venSel);
			venFrame.add(venB);
		}catch(Exception venE) {
			System.out.println(venE);
		}
		
		venFrame.setSize(800,400);
		venFrame.setLayout(null);
		venFrame.setVisible(true);
	}
}
