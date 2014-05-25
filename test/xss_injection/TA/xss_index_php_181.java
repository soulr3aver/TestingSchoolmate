/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xss_injection.TA;

import xss_injection.TA.*;
import xss_injection.SA.*;
import xss_injection.TA.*;
import xss_injection.SA.*;
import Support.Support_Function;
import net.sourceforge.jwebunit.junit.WebTester;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author iono
 */
public class xss_index_php_181 {

    WebTester tester = new WebTester();

    @Before
    public void setUp() {
        tester.setBaseUrl("http://localhost/schoolmate/");
        tester.beginAt("index.php");
        tester.setTextField("username", "simoncelli");
        tester.setTextField("password", "1");
        tester.submit();
    }

    @Test
    public void Attack_page2() {
        tester.assertMatch("'s Classes");
        tester.clickLinkWithText("pentest");
        tester.setWorkingForm("teacher");
        Support_Function.setAll("page2", "8'/><a href=\"#\">malicious link</a><br'",tester);
        Support_Function.setAll("selectclass","5",tester);
        Support_Function.addSubmitButton("html//form[@name='teacher']", tester);
        tester.submit();
        tester.assertMatch("'s Classes");
        tester.assertLinkNotPresentWithText("malicious link");

    }
    @Test
        public void Attack_page() {
        tester.assertMatch("'s Classes");
        tester.clickLinkWithText("pentest");
        tester.setWorkingForm("teacher");
        Support_Function.setAll("page", "2'/><a href=\"#\">malicious link</a><br'",tester);
        Support_Function.setAll("selectclass","5",tester);
        Support_Function.setAll("page2","8",tester);
        Support_Function.addSubmitButton("html//form[@name='teacher']", tester);
        
        tester.submit();
        tester.assertMatch("Students");
        tester.assertLinkNotPresentWithText("malicious link");

    }
    
        @Test
        public void Attack_selectclass() {
        tester.assertMatch("'s Classes");
        tester.clickLinkWithText("pentest");
        tester.setWorkingForm("teacher");
        Support_Function.setAll("selectclass", "5' -- /><a href=\"#\">malicious link</a><br'",tester);
        Support_Function.setAll("page2","8",tester);
        Support_Function.addSubmitButton("html//form[@name='teacher']", tester);
        tester.submit();
        tester.assertMatch("Students");
        tester.assertLinkNotPresentWithText("malicious link");

    }
    
    
    
    

}
