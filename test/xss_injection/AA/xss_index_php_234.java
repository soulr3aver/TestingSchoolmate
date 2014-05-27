/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xss_injection.AA;

import xss_injection.TA.*;
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
public class xss_index_php_234 {

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
            statement.execute("update terms set title=\"'><a>link</a>\'\" where termid=1 ");
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
        //tester.clickElementByXPath("html/body/table[2]/tbody/tr[2]/td[3]/table/tbody/tr/td/table[1]/tbody/tr[2]/td/table/tbody/tr[2]/td/a");
        tester.clickLinkWithText("Terms");
        tester.assertLinkNotPresentWithText("link");
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
            statement.execute("update terms set title='terms1' where termsid=1 ");
        } catch (SQLException e) {
            System.out.printf(e.toString());
        }
    }
}
