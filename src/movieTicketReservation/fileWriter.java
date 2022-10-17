package movieTicketReservation;

import java.io.FileWriter; 
import java.io.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;


public class fileWriter {
	
	public fileWriter(Connection conn,Statement stmt,String dataArr[],String seat) {
		try {
			
			String query = "select * from validate where email_id like \""+dataArr[0]+"\";";
			System.out.println(dataArr[0]);
			System.out.println(1);
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			String str = "-------------------------------------------------\n"
			+"Name of customer: "+rs.getString("name")
			+"\nEmail ID: "+rs.getString("email_id")
			+"\nPhone number: "+rs.getString("phone_number")
			+"\nMovie: "+dataArr[2]
			+"\nVenue: "+dataArr[3]
			+"\nSeat no: "+seat
			+"\nCity: "+dataArr[1]
			+"\n-------------------------------------------------";
			
			try(FileWriter fw = new FileWriter("D:\\SKCET\\Programs\\Java\\movieTicketReservation\\src\\movieTicketReservation\\ticketReceipt.txt", true);
				    BufferedWriter bw = new BufferedWriter(fw);
				    PrintWriter out = new PrintWriter(bw))
				{
				    out.println(str);

				    //more code
				} catch (IOException e) {
				    //exception handling left as an exercise for the reader
				}
			
		}catch(Exception E) {
			System.out.println(E);
		}
	}

}
