/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xss_injection.PA;

import xss_injection.AA.*;
import xss_injection.PA.*;
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
public class xss_index_php_288 {
    
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
        
        tester.setHiddenField("page2", "22'/> <a href=www.google.com>evil link</a> <br '");
        tester.setHiddenField("page", "1");
        Support_Function.addSubmitButton("/html//form[@name='admin']", tester);
        tester.setWorkingForm("admin");
        tester.submit();
        tester.assertMatch("Manage Parents");
        tester.assertLinkNotPresentWithText("evil link");
    }
    
    @Test
    public void Attack_page() {
        
        

        tester.setHiddenField("page", "1'/> <a href=www.google.com>evil link</a> <br '");
        tester.setHiddenField("page2", "22");
        Support_Function.addSubmitButton("/html//form[@name='admin']", tester);
        tester.setWorkingForm("admin");
        tester.submit();
        tester.assertMatch("Manage Parents");
        tester.assertLinkNotPresentWithText("evil link");
        
    }
    
    @Test
    public void Attack_onpage() {
        
        tester.clickLinkWithText("Parents");
        tester.getElementByXPath("//form[@name='parents']/center/a[text()='1']").setAttribute("href", "javascript:document.parents.submit()");
        tester.setHiddenField("onpage", "1'> <a href=www.google.com>evil link</a> <br '");
        tester.setHiddenField("page", "1");
        tester.setHiddenField("page2", "22");
        tester.clickLinkWithText("1");
        tester.assertMatch("Manage Parents");
        tester.assertLinkNotPresentWithText("evil link");
        
    }
}
