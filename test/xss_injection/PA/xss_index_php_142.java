/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xss_injection.PA;

import Support.Support_Function;
import net.sourceforge.jwebunit.junit.WebTester;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author iono
 */
public class xss_index_php_142 {

    WebTester tester = new WebTester();

    @Before
    public void setUp() {
        tester.setBaseUrl("http://localhost/schoolmate/");
        tester.beginAt("index.php");
        tester.setTextField("username", "parent");
        tester.setTextField("password", "parent");
        tester.submit();
    }

    @Test
    public void Attack_page2() {
        tester.setWorkingForm("classes");
        tester.assertMatch("Students of");
        Support_Function.setAll("page2", "5'/><a href=\"#\">malicious link</a><br'", tester);
        Support_Function.setAll("student", "1", tester);
        Support_Function.addSubmitButton("html//form[@name='classes']", tester);
        tester.submit();
        tester.assertMatch("s Classes");
        tester.assertLinkNotPresentWithText("malicious link");

    }

    @Test
    public void Attack_page() {

        tester.setWorkingForm("classes");
        tester.assertMatch("Students of");
        Support_Function.setAll("page", "5'/><a href=\"#\">malicious link</a><br '", tester);
        Support_Function.setAll("student", "1", tester);
        Support_Function.setAll("page2", "5", tester);
        Support_Function.addSubmitButton("html//form[@name='classes']", tester);
        tester.submit();
        tester.assertMatch("'s Classes");
        tester.assertLinkNotPresentWithText("malicious link");

    }

    @Test
    public void Attack_student() {
        
        tester.setWorkingForm("classes");
        tester.assertMatch("Students of");
        Support_Function.setAll("student", "1 -- ' /><a href=\"#\">malicious link</a><br '", tester);
        Support_Function.setAll("page2", "5", tester);
        Support_Function.addSubmitButton("html//form[@name='classes']", tester);
        tester.submit();
        tester.assertMatch("'s Classes");
        tester.assertLinkNotPresentWithText("malicious link");

    }
} 
