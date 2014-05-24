/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xss_injection;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import net.sourceforge.jwebunit.junit.WebTester;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iono
 */
public class xss_index_php_30 {

       public WebTester tester = new WebTester();
    
        @Before
        public void setUp() throws SQLException{
            tester.setBaseUrl("http://localhost/schoolmate");
            tester.beginAt("index.php");
            tester.setTextField("username", "1234");
            tester.setTextField("password", "1234");
            tester.submit();
            
            try{
                Class.forName("com.mysql.jdbc.Driver");
            }
            catch(ClassNotFoundException e){}
            
            Connection connect = null;
            Statement statement = null;
            
            try{
                connect = DriverManager.getConnection("jdbc:mysql://localhost/schoolmate", "schoolmate", "schoolmate");
                statement = connect.createStatement();
                statement.execute("update courses set coursename='<a>evil link</a>' where courseid=2");
            }catch(SQLException e){
                System.out.printf(e.getSQLState());
                System.exit(-1);
            }
            
            statement.close();
            connect.close();
            
        }
        
        @Test
        public void Attack(){
            tester.clickElementByXPath("//table/tbody/tr/td/a[1]");
            tester.clickLinkWithText("Assignments");
            tester.assertLinkNotPresentWithText("evil link");
        }
        
        
        @After
        public void restore() throws SQLException{
            
         try{
                Class.forName("com.mysql.jdbc.Driver");
            }
            catch(ClassNotFoundException e){}
            
            Connection connect = null;
            Statement statement = null;
            
            try{
                connect = DriverManager.getConnection("jdbc:mysql://localhost/schoolmate", "schoolmate", "schoolmate");
                statement = connect.createStatement();
                statement.execute("update courses set coursename='pentest' where courseid=2");
            }catch(SQLException e){}
            
            statement.close();
            connect.close();
            
        }
        
        
}
