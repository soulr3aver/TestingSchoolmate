/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xss_injection.AA;

import Support.Support_Function;
import net.sourceforge.jwebunit.junit.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;

/**
 *
 * @author iono
 */
public class xss_index_php_13 {

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
        tester.clickLinkWithText("Parents");
        tester.assertMatch("Manage Parents");
        tester.setWorkingForm("parents");
        tester.setHiddenField("page2", "23'><a>Evil link</a><br '");
        Support_Function.addSubmitButton("/html//form[@name='parents']", tester);
        tester.submit();
        tester.assertMatch("Add New Parent");
        tester.assertLinkNotPresentWithText("Evil link");
    }

    @Test
    public void Attack_page() {
        tester.clickLinkWithText("Parents");
        tester.assertMatch("Manage Parents");
        tester.setWorkingForm("parents");
        tester.getElementByXPath("//form[@name='parents']/input[@name='page']").setAttribute("value", "1'><a>Evil link</a><br '");
        tester.clickButtonWithText("Add");
        tester.assertMatch("Add New Parent");
        tester.assertLinkNotPresentWithText("Evil link");
    }
}
