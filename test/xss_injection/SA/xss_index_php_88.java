/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xss_injection.SA;

import Support.Support_Function;
import net.sourceforge.jwebunit.junit.WebTester;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author iono
 */
public class xss_index_php_88 {

    WebTester tester = new WebTester();

    @Before
    public void setUp() {
        tester.setBaseUrl("http://localhost/schoolmate/");
        tester.beginAt("index.php");
        tester.setTextField("username", "1234");
        tester.setTextField("password", "1234");
        tester.submit();
    }

    @Test
    public void Attack_page2() {
        tester.assertMatch("'s Classes");
        Support_Function.setAll("page2", "1'/><a href=\"#\">malicious link</a><br'",tester);
        Support_Function.setAll("selectclass","5",tester);
        Support_Function.addSubmitButton("html//form[@name='classes']", tester);
        
        tester.setWorkingForm("classes");

        tester.submit();
        tester.assertMatch("Settings");
        tester.assertLinkNotPresentWithText("malicious link");

    }
    @Test
        public void Attack_page() {
        tester.assertMatch("'s Classes");
        Support_Function.setAll("page", "4'/><a href=\"#\">malicious link</a><br'",tester);
        Support_Function.setAll("selectclass","5",tester);
        Support_Function.setAll("page2","1",tester);
        Support_Function.addSubmitButton("html//form[@name='classes']", tester);
        
        tester.setWorkingForm("classes");

        tester.submit();
        tester.assertMatch("Settings");
        tester.assertLinkNotPresentWithText("malicious link");

    }
    
        @Test
        public void Attack_selectclass() {
        tester.assertMatch("'s Classes");
        Support_Function.setAll("selectclass", "5' -- /><a href=\"#\">malicious link</a><br'",tester);
        Support_Function.setAll("page2","1",tester);
        Support_Function.addSubmitButton("html//form[@name='classes']", tester);
        
        tester.setWorkingForm("classes");

        tester.submit();
        tester.assertMatch("Settings");
        tester.assertLinkNotPresentWithText("malicious link");

    }
    
    
    
    

}
