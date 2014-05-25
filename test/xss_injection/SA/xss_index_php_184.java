/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xss_injection.SA;

import Support.Support_Function;
import net.sourceforge.jwebunit.junit.WebTester;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author iono
 */
public class xss_index_php_184 {

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
        tester.setWorkingForm("student");
        Support_Function.setAll("page2", "2'/><a href=\"#\">malicious link</a><br'",tester);
        Support_Function.setAll("selectclass","5",tester);
        Support_Function.addSubmitButton("html//form[@name='student']", tester);
        tester.submit();
        tester.assertMatch("View Assignments");
        tester.assertLinkNotPresentWithText("malicious link");

    }
    @Test
        public void Attack_page() {

        tester.clickLinkWithText("pentest");
        tester.setWorkingForm("student");
        Support_Function.setAll("page", "4'/><a href=\"#\">malicious link</a><br'",tester);
        Support_Function.setAll("selectclass","5",tester);
        Support_Function.setAll("page2","2",tester);
        Support_Function.addSubmitButton("html//form[@name='student']", tester);
        
        tester.submit();
        tester.assertMatch("View Assignments");
        tester.assertLinkNotPresentWithText("malicious link");

    }
    
        @Test
        public void Attack_selectclass() {

        tester.clickLinkWithText("pentest");
        tester.setWorkingForm("student");
        Support_Function.setAll("selectclass", "5' -- /><a href=\"#\">malicious link</a><br'",tester);
        Support_Function.setAll("page2","2",tester);
        Support_Function.addSubmitButton("html//form[@name='student']", tester);
        tester.submit();
        tester.assertMatch("View Assignments");
        tester.assertLinkNotPresentWithText("malicious link");

    }
       @Test
       public void Attack_onpage() {

        tester.clickLinkWithText("pentest");
        tester.clickLinkWithText("Assignments");
        tester.getElementByXPath("//form[@name='assignments']/center/a[text()='1']").setAttribute("href", "javascript:document.assignments.submit()");
        tester.setHiddenField("onpage", "1'> <a href=www.google.com>evil link</a> <br '");
        tester.setHiddenField("page", "4");
        tester.setHiddenField("page2", "2");
        tester.clickLinkWithText("1");
        tester.assertMatch("View Assignments");
        tester.assertLinkNotPresentWithText("evil link");
        
    }
    
    
    
    

}
