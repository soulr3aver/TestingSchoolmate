/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xss_injection.AA;

import Support.Support_Function;
import net.sourceforge.jwebunit.junit.WebTester;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author iono
 */
public class xss_injection_241 {

    WebTester tester = new WebTester();

    @Before
    public void setUp() {
        tester.setBaseUrl("http://localhost/schoolmate/");
        tester.beginAt("index.php");
        tester.setTextField("username", "test");
        tester.setTextField("password", "test");
        tester.submit();

    }

    @Test
    public void Attack_page2() throws Exception {

        tester.clickLinkWithText("Students");
        Support_Function.setAll("page", "1",tester);
        Support_Function.setAll("page2", "28'/><a>evil link</a><br '",tester);
        tester.setWorkingForm("students");
        Support_Function.addSubmitButton("//form[@name='students']", tester);
        tester.submit();
        tester.assertMatch("Grade Report");
        tester.assertLinkNotPresentWithText("evil link");

    }

    @Test
    public void Attack_page() throws Exception {

        tester.clickLinkWithText("Students");
        tester.setWorkingForm("students");
        Support_Function.setAll("page", "1'/><a>evil link</a><br '",tester);
        //Support_Function.setAll("page2", "27",tester);
        tester.selectOptionByValue("report", "28");
        tester.assertMatch("Grade Report");
        tester.assertLinkNotPresentWithText("evil link");



    }
}
