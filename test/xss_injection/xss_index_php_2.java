/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xss_injection;

import net.sourceforge.jwebunit.junit.WebTester;
import org.junit.*;
import java.sql.*;
import org.junit.Test;

/**
 *
 * @author iono
 */
public class xss_index_php_2 {
    
    WebTester tester = new WebTester();
    
    @Before
    public void setUp(){
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found !!");
            return;
        }
            Connection connect = null;
            Statement statement = null;
            try{
            connect = DriverManager.getConnection("jdbc:mysql://localhost/schoolmate", "schoolmate", "schoolmate");
            statement= connect.createStatement();
            statement.execute("update schoolinfo set schoolname='<h1>xss</h1>'");
            }
            catch( SQLException e){
            System.out.printf(e.toString());
            }

            tester.setBaseUrl("http://localhost/schoolmate");
            tester.beginAt("index.php");
    }
    
    @Test
    public void Attack(){
        tester.assertElementNotPresentByXPath("//table/tbody/tr/td/div/h1");
        tester.assertTextPresent("School Name");
    }
   
    
    @After
   public void restore(){
               
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found !!");
            return;
        }
            Connection connect = null;
            Statement statement = null;
            try{
            connect = DriverManager.getConnection("jdbc:mysql://localhost/schoolmate", "schoolmate", "schoolmate");
            statement= connect.createStatement();
            statement.execute("update schoolinfo set schoolname='School Name'");
            }
            catch( SQLException e){
            System.out.printf(e.toString());
            } 
    }
}
