/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xss_injection.AA;


import xss_injection.TA.*;
import Support.Support_Function;
import net.sourceforge.jwebunit.junit.*;
import org.junit.Before;
import org.junit.Test;



/**^
 *
 * @author iono
 */
public class xss_index_php_44 {
    
    WebTester tester = new WebTester();
    
    public xss_index_php_44() {
    }  
   
    @Before
    public void setUpClass() {
        tester.setBaseUrl("http://localhost/schoolmate/");
        tester.beginAt("index.php");
        tester.setTextField("username", "test");
        tester.setTextField("password", "test");
        tester.submit();
        
        
        
    }

    @Test
    public void Attack_page2() {

        tester.clickLinkWithText("Terms");
        tester.assertMatch("Manage Terms");
        tester.setWorkingForm("terms");
        tester.setHiddenField("page2","12'> <a href=www.google.com>malicious link</a> <br '");
        tester.checkCheckbox("delete[]","1");
        
        Support_Function.addSubmitButton("/html//form[@name=\"terms\"]",tester);
        tester.submit();
        tester.assertMatch("Edit Term");
        tester.assertLinkNotPresentWithText("malicious link");
    }

    @Test
    public void Attack_page(){

        tester.clickLinkWithText("Terms");
        tester.assertMatch("Manage Terms");
        tester.setWorkingForm("terms");
        tester.setHiddenField("page2","12");
        tester.checkCheckbox("delete[]","1");
        tester.setHiddenField("page","1'/><a href=www.google.com>malicious link</a> <br '");
        Support_Function.addSubmitButton("/html//form[@name=\"terms\"]",tester);
        tester.submit();
        tester.assertMatch("Edit Term");
        tester.assertLinkNotPresentWithText("malicious link");
    }
       
    @Test
    public void Attack_delete(){

        tester.clickLinkWithText("Terms");
        tester.assertMatch("Manage Terms");
        tester.setWorkingForm("terms");
        tester.checkCheckbox("delete[]","1");
        tester.getElementByXPath("//form/table/tbody/tr/td/input[@type='checkbox' and @value='1']").setAttribute("value", "1 -- '/> <a href=www.google.com>malicious link</a> <br '");
        tester.clickButtonWithText("Edit");
        tester.assertMatch("Edit Term");
        tester.assertLinkNotPresentWithText("malicious link");
    }
    @Test
        public void Attack_delete_2(){

        tester.clickLinkWithText("Terms");
        tester.assertMatch("Manage Terms");
        tester.setWorkingForm("terms");
        tester.checkCheckbox("delete[]","1");
        tester.getElementByXPath("//form/table/tbody/tr/td/input[@type='checkbox' and @value='1']").setAttribute("value", "<a href=www.google.com >malicious link</a>");
        tester.clickButtonWithText("Edit");
        tester.assertLinkNotPresentWithText("malicious link");
    }
}
