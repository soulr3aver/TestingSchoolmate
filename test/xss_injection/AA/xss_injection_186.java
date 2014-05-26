/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xss_injection.AA;

import Support.Support_Function;
import net.sourceforge.jwebunit.junit.WebTester;
import org.junit.Before;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iono
 */
public class xss_injection_186 {

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
    public void Attack_page() throws Exception {
        tester.setWorkingForm("admin");
        tester.setHiddenField("page2", "0");
        tester.setHiddenField("page", "1'/><a>malicious link</a><<br '");
        Support_Function.addSubmitButton("//form['admin']", tester);
        tester.submit();
        tester.assertLinkNotPresentWithText("malicious link");

    }

    @Test
    public void Attack_page2() throws Exception {
        tester.setWorkingForm("admin");
        tester.setHiddenField("page", "1");
        tester.setHiddenField("page2", "0'/><a>malicious link</a><<br '");
        Support_Function.addSubmitButton("//form['admin']", tester);
        tester.submit();
        tester.assertLinkNotPresentWithText("malicious link");



    }
}
