package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {

	//A common method to connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electro_grid", "root", "root");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	
	public String insertPayment(String cNumber, String CVV, String exDate, String amount)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting!."; }
	 // create a prepared statement
	 String query = " insert into payments(`paymentId`,`cNumber`,`CVV`,`exDate`,`amount`)"
	 + " values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, cNumber);
	 preparedStmt.setString(3, CVV);
	 preparedStmt.setString(4, exDate);
	 preparedStmt.setString(5, amount);
	
	 // execute the statement

	 preparedStmt.execute();
	 con.close();
	 String newPayment = readPayment();
	 output = "{\"status\":\"success\", \"data\": \"" +
	 newPayment + "\"}";
	 }
	 catch (Exception e)
	 {
	 output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	

public String readPayment()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for reading."; }
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Card Number</th><th>CVV</th>" + "<th>Expire Date</th>" + "<th>Amount</th>"
			+ "<th>Remove</th><th>Update</th></tr>";

 String query = "select * from payments";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
 while (rs.next())
 {
 String paymentId = Integer.toString(rs.getInt("paymentId"));
 String cNumber = rs.getString("cardNumber");
 String CVV = rs.getString("CVV");
 String exDate = rs.getString("expDate");
 String amount = rs.getString("amount");
 
	// Add into the html table
	output += "<tr><td>" + cNumber + "</td>";
	output += "<td>" + CVV + "</td>";
	output += "<td>" + exDate + "</td>";
	output += "<td>" + amount + "</td>";

	//buttons
	output += "<td><input name='btnUpdate' type='button' value='Update' "
	+ "class='btnUpdate btn btn-secondary' data-itemid='" + paymentId + "'></td>"
	+ "<td><input name='btnRemove' type='button' value='Remove' "
	+ "class='btnRemove btn btn-danger' data-itemid='" + paymentId + "'></td></tr>";
	}
	con.close();
	// Complete the html table
	output += "</table>";
	}
	catch (Exception e)
	{
	output = "Error while reading the nitems.";
	System.err.println(e.getMessage());
	}
	return output;
	}

public String updatePayment(String paymentId,String cNumber, String CVV, String exDate, String amount)

{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for updating."; }
// create a prepared statement
String query = "UPDATE payments SET cardNumber=?,CVV=?,expDate=?,amount=? WHERE paymentid=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values

preparedStmt.setString(1, cNumber);
preparedStmt.setString(2, CVV);
preparedStmt.setString(3, exDate);
preparedStmt.setString(4, amount);
preparedStmt.setInt(5, Integer.parseInt(paymentId));
// execute the statement
preparedStmt.execute();
con.close();
String newPayment = readPayment();
output = "{\"status\":\"success\", \"data\": \"" +
newPayment + "\"}";
}
catch (Exception e)
{
output = "{\"status\":\"error\", \"data\":\"Error while Updating the Payment.\"}";
System.err.println(e.getMessage());
}
return output;
}



public String deletePayment(String paymentId)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for deleting."; }
 // create a prepared statement
 String query = "delete from payments where paymentId=?";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(paymentId));
 // execute the statement
 preparedStmt.execute();
 con.close();
 String newPayment = readPayment();
 output = "{\"status\":\"success\", \"data\": \"" +
 newPayment + "\"}";
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\":\"Error while Deleting the Payment Details.\"}";
 System.err.println(e.getMessage());
 }
 return output;
 }
}
