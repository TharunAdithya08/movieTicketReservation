package movieTicketReservation;
import java.sql.*;

public class registerUpdateInDB {

	registerUpdateInDB(String user_id,String name,String pwd,Connection conn,Statement stmt,ResultSet rs) {
		try{
			String query = "create table validate(id varchar(20) primary key not null,name varchar(30),uname varchar(20),pwd varchar(25))";
			Class.forName("com.mysql.cj.jdbc.Driver");

			 
			query = "insert into validate values(\""+user_id+"\", \""+name+"\",  \""+pwd+"\");";
			stmt.executeUpdate(query);
			System.out.println("successfully inserted");
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	void addMovieDetails(String movieName,String movId,String date,String city,String venue,Connection conn,Statement stmt,ResultSet rs) {
		String query = "insert into movieDetails values(\""+movieName+"\", \""+movId+"\", \""+date+"\", \""+city+"\", \""+venue+"\");";
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Inserted successfully");
		
	}
	
}
