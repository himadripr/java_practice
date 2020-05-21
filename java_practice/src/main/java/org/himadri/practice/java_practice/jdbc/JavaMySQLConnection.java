package org.himadri.practice.java_practice.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JavaMySQLConnection {
	public static void main(String args[]) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // com.mysql.jdbc.Driver is the old driver class
//			nager.getConnection("jdbc:mysql://localhost:3306/INTtech?useSSL=false","root","root");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TEST?useSSL=false", "root", "abcd1234$");
			// here TEST is database name, root is username and password is abcd1234$. Note that these setting should be placed inside the configuration file. So 
			//if the username and password is changed the program wont have to compile again
			
//			ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from emp");
			while (rs.next())
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
			
			//inserting data into the database:-
			
			String sql1 = "insert into emp values(" +30+","+"'niladri','gokul')";
			stmt.executeUpdate(sql1);
			
			//both the above and below techniques are working.
			
//			String sql = "INSERT INTO emp " +
//			        "(id, name, age)"+" VALUES (?, ?, ?);";
//			PreparedStatement preparedStatement = con.prepareStatement(sql);
//			preparedStatement.setInt(1, 25);
//			preparedStatement.setString(2, "niladri");
//			preparedStatement.setInt(3, 17);
//			preparedStatement.executeUpdate(); 
			
			String delsql = "Delete from emp where stname='surabhi'";
			stmt.executeUpdate(delsql);
			
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}