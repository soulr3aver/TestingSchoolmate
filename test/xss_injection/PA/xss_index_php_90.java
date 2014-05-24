/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xss_injection.PA;

import xss_injection.SA.*;
import Support.Support_Function;
import net.sourceforge.jwebunit.junit.WebTester;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author iono
 */
public class xss_index_php_90 {

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
        tester.assertMatch("Student Name");
        tester.setWorkingForm("classes");
        tester.setHiddenField("page2", "0'/><a href=\"#\">malicious link</a><br'");
        tester.setHiddenField("page", "5");
        Support_Function.addSubmitButton("html//form[@name='classes']", tester);
        tester.setWorkingForm("classes");
        tester.submit();
        tester.assertMatch("Students of");
        tester.assertLinkNotPresentWithText("malicious link");

    }
    
    @Test
    public void Attack_page() {
        tester.assertMatch("Students of");
        tester.setWorkingForm("classes");
        tester.setHiddenField("page", "5'/><a href=\"#\">malicious link</a><br'");
        tester.setHiddenField("page2", "0");
        Support_Function.addSubmitButton("html//form[@name='classes']", tester);
        tester.setWorkingForm("classes");
        tester.submit();
        tester.assertMatch("Students of");
        tester.assertLinkNotPresentWithText("malicious link");

    }
    
}
