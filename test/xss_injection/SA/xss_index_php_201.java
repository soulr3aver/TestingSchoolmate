/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xss_injection.SA;

import xss_injection.SA.*;
import Support.Support_Function;
import net.sourceforge.jwebunit.junit.WebTester;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author iono
 */
public class xss_index_php_201 {

    WebTester tester = new WebTester();

    @Before
    public void setUp() {
        tester.setBaseUrl("http://localhost/schoolmate/");
        tester.beginAt("index.php");
        tester.setTextField("username", "1234");
        tester.setTextField("password", "1234");
        tester.submit();
    }

    @Test
    public void Attack_page2() {

        tester.clickLinkWithText("pentest");
        Support_Function.setAll("page2", "3'/><a href=\"#\">malicious link</a><br'",tester);
        Support_Function.setAll("selectclass","5",tester);
        Support_Function.addSubmitButton("html//form[@name='student']", tester);
        
        tester.setWorkingForm("student");

        tester.submit();
        tester.assertMatch("View Grades");
        tester.assertLinkNotPresentWithText("malicious link");

    }
    @Test
        public void Attack_page() {

        tester.clickLinkWithText("pentest");
        Support_Function.setAll("page", "4'/><a href=\"#\">malicious link</a><br'",tester);
        Support_Function.setAll("selectclass","5",tester);
        Support_Function.setAll("page2","3",tester);
        Support_Function.addSubmitButton("html//form[@name='student']", tester);
        
        tester.setWorkingForm("student");

        tester.submit();
        tester.assertMatch("View Grades");
        tester.assertLinkNotPresentWithText("malicious link");

    }
    
        @Test
        public void Attack_selectclass() {

        tester.clickLinkWithText("pentest");
        Support_Function.setAll("selectclass", "5' -- /><a href=\"#\">malicious link</a><br'",tester);
        Support_Function.setAll("page2","3",tester);
        Support_Function.addSubmitButton("html//form[@name='student']", tester);
        
        tester.setWorkingForm("student");

        tester.submit();
        tester.assertMatch("View Grades");
        tester.assertLinkNotPresentWithText("malicious link");

    }
    
    
    
    

}
