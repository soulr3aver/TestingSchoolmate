/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xss_injection.AA;

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
public class xss_index_php_230 {

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
        tester.setHiddenField("page2", "25'> <a href=www.google.com>evil link</a> <br '");
        Support_Function.addSubmitButton("/html//form[@name='classes']", tester);
        tester.submit();
        tester.assertMatch("School Class Schedule");
        tester.assertLinkNotPresentWithText("evil link");
    }

    @Test
    public void Attack_page() {


        tester.setWorkingForm("classes");
        tester.setHiddenField("page2", "25");
        tester.setHiddenField("page", "1'> <a href=www.google.com>evil link</a> <br '");
        Support_Function.addSubmitButton("/html//form[@name='classes']", tester);
        tester.submit();
        tester.assertMatch("School Class Schedule");
        tester.assertLinkNotPresentWithText("evil link");
    }
}
