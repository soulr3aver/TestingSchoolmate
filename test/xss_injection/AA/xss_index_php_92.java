/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xss_injection.AA;

import Support.Support_Function;
import xss_injection.*;
import java.sql.Connection;
import net.sourceforge.jwebunit.junit.WebTester;
import org.junit.*;
import java.sql.*;
import org.junit.Test;

/**
 *
 * @author iono
 */
public class xss_index_php_92 {

    WebTester tester = new WebTester();

    @Before
    public void setUp() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found !!");
            return;
        }
        Connection connect = null;
        Statement statement = null;
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost/schoolmate", "schoolmate", "schoolmate");
            statement = connect.createStatement();
            statement.execute("update schoolinfo set address=\"1,Street\'><a href=#>link</a>\'\"");
        } catch (SQLException e) {
            System.out.printf(e.toString());
        }

        tester.setBaseUrl("http://localhost/schoolmate");
        tester.beginAt("index.php");
        tester.setTextField("username", "test");
        tester.setTextField("password", "test");
        tester.submit();
    }

    @Test
    public void Attack() {
        tester.clickElementByXPath("//form[@name='admin']/a[text()='School']");
        tester.assertLinkNotPresentWithText("link");
    }
    
    @Test 
    public void Attack_page2(){
        //
        Support_Function.setAll("page2", "1\'><a>link1</a><br \'",tester);
        Support_Function.setAll("page", "1",tester);
        Support_Function.addSubmitButton("//form[@name='admin']", tester);
        tester.setWorkingForm("admin");
        tester.submit();
        tester.assertLinkNotPresentWithText("link1");
    }

    @Test 
    public void Attack_page(){
        tester.setWorkingForm("admin");
        Support_Function.setAll("page2", "1",tester);
        Support_Function.setAll("page", "1\'><a>link1</a><br \'",tester);
        Support_Function.addSubmitButton("//form[@name='admin']", tester);
        tester.submit();
        tester.assertLinkNotPresentWithText("link1");
    }
    

   @After
    public void restore() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found !!");
            return;
        }
        Connection connect = null;
        Statement statement = null;
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost/schoolmate", "schoolmate", "schoolmate");
            statement = connect.createStatement();
            statement.execute("update schoolinfo set address='1,Street'");
        } catch (SQLException e) {
            System.out.printf(e.toString());
        }
    }
}
