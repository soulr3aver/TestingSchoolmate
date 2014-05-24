/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xss_injection.AA;

import Support.Support_Function;
import org.junit.Before;
import net.sourceforge.jwebunit.junit.WebTester;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iono
 */
public class xss_index_php_111 {

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

        tester.clickLinkWithText("Teachers");
        tester.assertMatch("Manage Teachers");
        tester.setWorkingForm("teachers");
        tester.checkCheckbox("delete[]", "1");
        tester.setHiddenField("page2", "17'> <a href=www.google.com>evil link</a> <br '");
        Support_Function.addSubmitButton("/html//form[@name='teachers']", tester);
        tester.submit();
        tester.assertMatch("Edit Teacher");
        tester.assertLinkNotPresentWithText("evil link");
    }

    @Test
    public void Attack_page() {

        tester.clickLinkWithText("Teachers");
        tester.assertMatch("Manage Teachers");
        tester.setWorkingForm("teachers");
        tester.setHiddenField("page2", "17");
        tester.checkCheckbox("delete[]", "1");
        tester.setHiddenField("page", "1'> <a href=www.google.com>evil link</a> <br '");
        Support_Function.addSubmitButton("/html//form[@name='teachers']", tester);
        tester.submit();
        tester.assertMatch("Edit Teacher");
        tester.assertLinkNotPresentWithText("evil link");
    }
    
    @Test
    public void Attack_delete() {

        tester.clickLinkWithText("Teachers");
        tester.assertMatch("Manage Teachers");
        tester.setWorkingForm("teachers");
        tester.setHiddenField("page2", "17");
        tester.setHiddenField("page", "1");
        tester.checkCheckbox("delete[]", "1");
        tester.getElementByXPath("//input[@type='checkbox' and @value='1']").setAttribute("value", "1--' > <a href=www.google.com>evil link</a> <br '");
        Support_Function.addSubmitButton("/html//form[@name='teachers']", tester);
        tester.submit();
        tester.assertMatch("Edit Teacher");
        tester.assertLinkNotPresentWithText("evil link");
    }
}
