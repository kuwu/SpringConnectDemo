
package com.test.controller;

/**
 * Created by Kuwu on 7/24/17.
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.tags.form.SelectTag;

// step 1 import sql package
//import.java.sql.*;

import java.sql.*;
import java.util.ArrayList;

import static java.sql.DriverManager.getConnection;


@Controller
public class HomeController {

  @RequestMapping("/add")
  public String dbAdd(Model model) throws ClassNotFoundException, SQLException {
    Class.forName( "com.mysql.jdbc.Driver" );
    Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/northwind", );
    Statement st = con.createStatement();
//    st.executeUpdate("INSERT INTO Customers (CustomerID,CompanyName,ContactTitle,Address,City,Region,PostalCode,Country,Phone,Fax) VALUES ('pedz','Zed','Bob','123 Bad','Detroit','LA','48239','USA','777-421-9871','777-777-yeah')");
//      st.executeUpdate( "DELETE FROM Customers WHERE  CustomerID='zzzz'" );
      ResultSet rs = st.executeQuery( "SELECT * FROM customers" );

   ArrayList<String> list = new ArrayList<String>();

   while (rs.next()){

      String custID = rs.getString( 1 );

      list.add( custID );

    }
    model.addAttribute( "dbAddName", list);
    rs.close();
    con.close();
    return "/add";
  }



  @RequestMapping("/jdbc")
  // this example shows multiple rows being returned from our table
  public String dbReturn(Model model) throws ClassNotFoundException, SQLException {
    Class.forName( "com.mysql.jdbc.Driver" );
    Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/northwind", "JavaGC", "8732Japan!" );
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery( "SELECT * FROM customers" );

    ArrayList<String> list = new ArrayList<String>();


    while (rs.next()) {
      String custName = rs.getString( 1 );
      String compName = rs.getString( 2 );
      String contName = rs.getString( 3 );

      // populate data into an arrayList
      list.add( custName + ", " + compName + ", " + contName );


    }

    model.addAttribute( "dbResult", list );

    rs.close();
    con.close();
    return "jdbc";
  }

  @RequestMapping("/")
  public ModelAndView helloWorld() throws ClassNotFoundException, SQLException {
    // prep for step 3
    String url = "jdbc:mysql://localhost:3306/northwind";
    String userName = "JavaGC";
    String password = "8732Japan!";
    String query = "SELECT * FROM products";

    //step 2
    Class.forName( "com.mysql.jdbc.Driver" );

    //step 3 create connection
    Connection con = getConnection( url, userName, password );

    //step 4 create statement
    Statement st = con.createStatement();

    //step 5 result set
    ResultSet rs = st.executeQuery( query );

    //step 6 process results
    //we need to use the next() method to move past the column headers in the first row in the table data
    rs.next();

    // the get methods that are used to assign variables are referencing the table column index
    // the index starts at 1 not 0
    int productID = rs.getInt( 1 );
    String prodName = rs.getString( 2 );
    String supplierID = rs.getString( 3 );

    String printResult = productID + " " + prodName + " " + supplierID;

    // step 7 (optional) :
    rs.close();
    con.close();


    return new
        ModelAndView( "welcome", "message", printResult );
  }



}

