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
public class xss_injection_269 {

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
        
        tester.clickButtonWithText("Add");
        Support_Function.setAll("page", "1",tester);
        Support_Function.setAll("page2", "9'/><a>evil link</a><br '",tester);
        tester.setWorkingForm("addclass");
        Support_Function.addSubmitButton("//form[@name='addclass']", tester);
        tester.submit();
        tester.assertMatch("Add New Class");
        tester.assertLinkNotPresentWithText("evil link");

    }

    @Test
    public void Attack_page() throws Exception {

        tester.clickButtonWithText("Add");
        Support_Function.setAll("page2", "9",tester);
        Support_Function.setAll("page", "1'/><a>evil link</a><br '",tester);
        tester.setWorkingForm("addclass");
        Support_Function.addSubmitButton("//form[@name='addclass']", tester);
        tester.submit();
        tester.assertMatch("Add New Class");
        tester.assertLinkNotPresentWithText("evil link");

    }
    
    
    @Test
    public void Attack_fullyear() throws Exception {

        tester.clickButtonWithText("Add");
        Support_Function.setAll("page2", "9",tester);
        Support_Function.setAll("page", "1",tester);
        Support_Function.setAll("fullyear", "1'/><a>evil link</a><br '",tester);
        tester.setWorkingForm("addclass");
        Support_Function.addSubmitButton("//form[@name='addclass']", tester);
        tester.submit();
        tester.assertMatch("Add New Class");
        tester.assertLinkNotPresentWithText("evil link");

    }
}
