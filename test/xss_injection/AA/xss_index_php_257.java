/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xss_injection.AA;

import net.sourceforge.jwebunit.junit.WebTester;
import org.junit.Before;
import Support.Support_Function;
import org.junit.Test;

/**
 *
 * @author iono
 */
public class xss_index_php_257 {
    
    WebTester tester = new WebTester();
    
    @Before
    public void setUp() {
        tester.setBaseUrl("http://localhost/schoolmate");
        tester.beginAt("index.php");
        tester.setTextField("username", "test");
        tester.setTextField("password", "test");
        tester.submit();
    }
    
    @Test
    public void Attack_page2() {
        
        tester.clickLinkWithText("Announcements");
        Support_Function.setAll("page2", "4'/> <a href=www.google.com>evil link</a> <br '",tester);
        Support_Function.setAll("page", "1",tester);
        Support_Function.addSubmitButton("/html//form[@name='admin']", tester);
        tester.setWorkingForm("admin");
        tester.submit();
        tester.assertMatch("Manage Announcements");
        tester.assertLinkNotPresentWithText("evil link");
    }
    
    @Test
    public void Attack_page() {
        
        
tester.clickLinkWithText("Announcements");
        tester.setHiddenField("page", "1'/> <a href=www.google.com>evil link</a> <br '");
        tester.setHiddenField("page2", "4");
        Support_Function.addSubmitButton("/html//form[@name='admin']", tester);
        tester.setWorkingForm("admin");
        tester.submit();
        tester.assertMatch("Manage Announcements");
        tester.assertLinkNotPresentWithText("evil link");
        
    }
    
    @Test
    public void Attack_onpage() {
        
        
tester.clickLinkWithText("Announcements");
        tester.getElementByXPath("//form[@name='announcements']/center/a[text()='1']").setAttribute("href", "javascript:document.announcements.submit()");
        tester.setHiddenField("onpage", "1'/> <a href=www.google.com>evil link</a> <br '");
        tester.setHiddenField("page2", "4");
        tester.setHiddenField("page", "1");
        tester.clickLinkWithText("1");
        tester.assertMatch("Manage Announcements");
        tester.assertLinkNotPresentWithText("evil link");
        
    }
}
