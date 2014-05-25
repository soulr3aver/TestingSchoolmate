/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xss_injection.AA;

import xss_injection.TA.*;
import xss_injection.SA.*;
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
public class xss_index_php_320 {
    
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
        
        tester.setWorkingForm("classes");
        tester.setHiddenField("page2", "0'> <a href=www.google.com>evil link</a> <br '");
        tester.setHiddenField("page", "1");
        Support_Function.addSubmitButton("/html//form[@name='classes']", tester);
        
        tester.submit();
        tester.assertMatch("Manage Classes");
        tester.assertLinkNotPresentWithText("evil link");
    }
    
    @Test
    public void Attack_page() {
        
        tester.setWorkingForm("classes");
        tester.assertMatch("Manage Classes");
        tester.setHiddenField("page", "1'> <a href=www.google.com>evil link</a> <br '");
        tester.setHiddenField("page2", "0");
        Support_Function.addSubmitButton("/html//form[@name='classes']", tester);
        tester.setWorkingForm("classes");
        tester.submit();
         tester.assertMatch("Manage Classes");
        tester.assertLinkNotPresentWithText("evil link");
        
    }
    
    @Test
    public void Attack_onpage() {
        
        tester.setWorkingForm("classes");
        tester.assertMatch("Manage Classes");
        tester.getElementByXPath("//form/table[1]/tbody/tr/td/center/a[text()='1']").setAttribute("href", "javascript:document.classes.submit()");
        tester.setHiddenField("onpage", "1'> <a href=www.google.com>evil link</a> <br '");
        tester.setHiddenField("page", "1");
        tester.setHiddenField("page2", "0");
        tester.clickLinkWithText("1");
         tester.assertMatch("Manage Classes");
        tester.assertLinkNotPresentWithText("evil link");
        
    }
}
