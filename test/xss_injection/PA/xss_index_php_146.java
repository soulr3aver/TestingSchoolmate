/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xss_injection.PA;

import xss_injection.PA.*;
import xss_injection.AA.*;
import net.sourceforge.jwebunit.junit.WebTester;
import org.junit.Before;
import Support.Support_Function;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iono
 */
public class xss_index_php_146 {
    
    WebTester tester = new WebTester();
    
    @Before
    public void setUp() {
        tester.setBaseUrl("http://localhost/schoolmate");
        tester.beginAt("index.php");
        tester.setTextField("username", "parent");
        tester.setTextField("password", "parent");
        tester.submit();
    }
    
    @Test
    public void Attack_page2() {
        
        tester.clickLinkWithText("1234 elli");
        tester.clickLinkWithText("pentest");
        tester.setHiddenField("page2", "4'/> <a href=www.google.com>evil link</a> <br '");
        tester.setHiddenField("page", "5");
        Support_Function.addSubmitButton("/html//form[@name='student']", tester);
        tester.setWorkingForm("student");
        tester.submit();
        tester.assertMatch("View Announcements");
        tester.assertLinkNotPresentWithText("evil link");
    }
    
    @Test
    public void Attack_page() {
        
        
        tester.clickLinkWithText("1234 elli");
        tester.clickLinkWithText("pentest");
        tester.setHiddenField("page", "5'/> <a href=www.google.com>evil link</a> <br '");
        tester.setHiddenField("page2", "4");
        Support_Function.addSubmitButton("/html//form[@name='student']", tester);
        tester.setWorkingForm("student");
        tester.submit();
        tester.assertMatch("View Announcements");
        tester.assertLinkNotPresentWithText("evil link");
        
    }
    
    @Test
    public void Attack_onpage() {
        
        
        tester.clickLinkWithText("1234 elli");
        tester.clickLinkWithText("pentest");
        tester.clickLinkWithText("Announcements");
        tester.getElementByXPath("//form[@name='announcements']/center/a[text()='1']").setAttribute("href", "javascript:document.announcements.submit()");
        tester.setHiddenField("onpage", "1'/> <a href=www.google.com>evil link</a> <br '");
        tester.setHiddenField("page2", "4");
        tester.setHiddenField("page", "5");
        tester.clickLinkWithText("1");
        tester.assertMatch("View Announcements");
        tester.assertLinkNotPresentWithText("evil link");
        
    }
}
