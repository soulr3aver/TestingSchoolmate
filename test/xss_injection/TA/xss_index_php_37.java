/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xss_injection.TA;


import Support.Support_Function;
import net.sourceforge.jwebunit.junit.*;
import org.junit.Before;
import org.junit.Test;



/**^
 *
 * @author iono
 */
public class xss_index_php_37 {
    
    WebTester tester = new WebTester();
    
    public xss_index_php_37() {
    }  
   
    @Before
    public void setUpClass() {
        tester.setBaseUrl("http://localhost/schoolmate/");
        tester.beginAt("index.php");
        tester.setTextField("username", "simoncelli");
        tester.setTextField("password", "1");
        tester.submit();
        
        
        
    }

    @Test
    public void Attack_page2() {

        tester.clickElementByXPath("//table/tbody/tr/td/a[@class='items']");
        tester.assertMatch("Class Settings");
        tester.clickElementByXPath("//form/a[3]");
        tester.assertMatch("Manage Assignments");
        tester.setWorkingForm("assignments");
        tester.setHiddenField("page2","5'> <a href=www.google.com>malicious link</a> <br '");
        tester.checkCheckbox("delete[]","1");
        
        Support_Function.addSubmitButton("/html//form[@name=\"assignments\"]",tester);
        tester.submit();
        tester.assertMatch("Edit Assignment");
        tester.assertLinkNotPresentWithText("malicious link");
    }

    @Test
    public void Attack_page(){

        tester.clickElementByXPath("//table/tbody/tr/td/a[@class='items']");
        tester.assertMatch("Class Settings");
        tester.clickElementByXPath("//form/a[3]");
        tester.assertMatch("Manage Assignments");
        tester.setWorkingForm("assignments");
        tester.setHiddenField("page2","5");
        tester.checkCheckbox("delete[]","1");
        tester.setHiddenField("page","2'/><a href=www.google.com>malicious link</a> <br '");
        Support_Function.addSubmitButton("/html//form[@name=\"assignments\"]",tester);
        tester.submit();
        tester.assertMatch("Edit Assignment");
        tester.assertLinkNotPresentWithText("malicious link");
    }
    
    @Test
    public void Attack_selectclass(){

        tester.clickElementByXPath("//table/tbody/tr/td/a[@class='items']");
        tester.assertMatch("Class Settings");
        tester.clickElementByXPath("//form/a[3]");
        tester.assertMatch("Manage Assignments");
        tester.setWorkingForm("assignments");
        tester.setHiddenField("page2","5");
        tester.checkCheckbox("delete[]","1");
        tester.setHiddenField("selectclass","1 -- ' /> <a href=www.google.com>malicious link</a> <br '");
        Support_Function.addSubmitButton("/html//form[@name=\"assignments\"]",tester);
        tester.submit();
        tester.assertMatch("Edit Assignment");
        tester.assertLinkNotPresentWithText("malicious link");
    }
    
    @Test
    public void Attack_delete(){

        tester.clickElementByXPath("//table/tbody/tr/td/a[@class='items']");
        tester.assertMatch("Class Settings");
        tester.clickElementByXPath("//form/a[3]");
        tester.assertMatch("Manage Assignments");
        tester.setWorkingForm("assignments");
        tester.checkCheckbox("delete[]","1");
        tester.getElementByXPath("//form/table/tbody/tr/td/input[@type='checkbox' and @value='1']").setAttribute("value", "1 -- '/> <a href=www.google.com>malicious link</a> <br '");
        tester.clickButtonWithText("Edit");
        tester.assertMatch("Edit Assignment");
        tester.assertLinkNotPresentWithText("malicious link");
    }
    @Test
        public void Attack_delete_2(){

        tester.clickElementByXPath("//table/tbody/tr/td/a[@class='items']");
        tester.assertMatch("Class Settings");
        tester.clickElementByXPath("//form/a[3]");
        tester.assertMatch("Manage Assignments");
        tester.setWorkingForm("assignments");
        tester.checkCheckbox("delete[]","1");
        tester.getElementByXPath("//form/table/tbody/tr/td/input[@type='checkbox' and @value='1']").setAttribute("value", "<a href=www.google.com >malicious link</a>");
        tester.clickButtonWithText("Edit");
        tester.assertLinkNotPresentWithText("malicious link");
    }
}
