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
    public void Attack_semester() {
        tester.clickLinkWithText("Attendance");
        tester.assertMatch("Attendance");
        tester.setWorkingForm("registration");
        tester.selectOption("semester", "1sem");
        tester.getElementByXPath("//form[@name='registration']/select[@name='semester']/option").setAttribute("value", "1'><a>Evil link</a><br '");
        tester.clickButtonWithText("Add");
        tester.assertMatch("Add New Attendance Record");
        tester.assertLinkNotPresentWithText("Evil link");
    }

    @Test
    public void Attack_student() {
        tester.clickLinkWithText("Attendance");
        tester.assertMatch("Attendance");
        tester.setWorkingForm("registration");
        tester.selectOption("student", "1234 1234");
        tester.getElementByXPath("//form[@name='registration']/select[@name='student']/option").setAttribute("value", "1'><a>Evil link</a><br '");
        tester.clickButtonWithText("Add");
        tester.assertMatch("Add New Attendance Record");
        tester.assertLinkNotPresentWithText("Evil link");
    }

    @Test
    public void Attack_page2() {
        tester.clickLinkWithText("Attendance");
        tester.assertMatch("Attendance");
        tester.setWorkingForm("registration");
        tester.setHiddenField("page2", "31'><a>Evil link</a><br '");
        Support_Function.addSubmitButton("/html//form[@name='registration']", tester);
        tester.setWorkingForm("registration");
        tester.submit();
        tester.assertMatch("Add New Attendance Record");
        tester.assertLinkNotPresentWithText("Evil link");
    }

    @Test
    public void Attack_page() {
        tester.clickLinkWithText("Attendance");
        tester.assertMatch("Attendance");
        tester.setWorkingForm("registration");
        tester.getElementByXPath("//form[@name='registration']/input[@name='page']").setAttribute("value", "1'><a>Evil link</a><br '");
        tester.clickButtonWithText("Add");
        tester.assertMatch("Add New Attendance Record");
        tester.assertLinkNotPresentWithText("Evil link");
    }
}
