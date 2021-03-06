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
public class xss_index_php_16 {

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
        tester.assertMatch("Manage Announcements");
        tester.setWorkingForm("announcements");
        tester.setHiddenField("page2", "18'> <a href=www.google.com>evil link</a> <br '");
        Support_Function.addSubmitButton("/html//form[@name='announcements']", tester);
        tester.submit();
        tester.assertLinkNotPresentWithText("evil link");
    }

    @Test
    public void Attack_page() {

        tester.clickLinkWithText("Announcements");
        tester.assertMatch("Manage Announcements");
        tester.setWorkingForm("announcements");
        tester.setHiddenField("page", "1'> <a href=www.google.com>evil link</a> <br '");
        Support_Function.addSubmitButton("/html//form[@name='announcements']", tester);
        tester.submit();
        tester.assertLinkNotPresentWithText("evil link");
    }
}
