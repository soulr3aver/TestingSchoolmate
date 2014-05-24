/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xss_injection.SA;

import Support.Support_Function;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.InputElementFactory;
import java.util.List;
import net.sourceforge.jwebunit.api.IElement;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitElementImpl;
import net.sourceforge.jwebunit.junit.WebTester;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.helpers.AttributesImpl;

/**
 *
 * @author iono
 */
public class xss_index_php_138 {

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
        Support_Function.setAll("page2", "0'/><a href=\"#\">malicious link</a><br'",tester);
        Support_Function.setAll("selectclass","5",tester);
        Support_Function.addSubmitButton("html//form[@name='classes']", tester);
        
        tester.setWorkingForm("classes");

        tester.submit();
        tester.assertMatch("'s Classes");
        tester.assertLinkNotPresentWithText("malicious link");

    }
    @Test
        public void Attack_page() {
        tester.assertMatch("'s Classes");
        Support_Function.setAll("page", "4'/><a href=\"#\">malicious link</a><br'",tester);
        Support_Function.setAll("selectclass","5",tester);
        Support_Function.setAll("page2","0",tester);
        Support_Function.addSubmitButton("html//form[@name='classes']", tester);
        
        tester.setWorkingForm("classes");

        tester.submit();
        tester.assertMatch("'s Classes");
        tester.assertLinkNotPresentWithText("malicious link");

    }
    
    
    
    

}
