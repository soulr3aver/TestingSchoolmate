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
public class xss_index_php_11 {
    
    WebTester tester = new WebTester();
    
    public xss_index_php_11() {
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
        tester.setHiddenField("page2","4'> <a href=www.google.com>malicious link</a> <br '");
        //testergetElementByXPath("//form[@name='assignments']/input[@value='Add']").setAttribute("onclick", "document.assignments.submit()");
        Support_Function.addSubmitButton("/html//form[@name=\"assignments\"]",tester);
        tester.submit();
        tester.assertMatch("Add New Assignment");
        tester.assertLinkNotPresentWithText("malicious link");
    }
    
    @Test
    public void Attack_selectclass(){

        tester.clickElementByXPath("//table/tbody/tr/td/a[@class='items']");
        tester.assertMatch("Class Settings");
        tester.clickElementByXPath("//form/a[3]");
        tester.assertMatch("Manage Assignments");
        tester.setWorkingForm("assignments");
        tester.setHiddenField("page2","4");
        tester.setHiddenField("selectclass","1'-- > <a href=www.google.com>malicious link</a> <br '");
        //tester.getElementByXPath("//form[@name='assignments']/input[@value='Add']").setAttribute("onclick", "document.assignments.submit()");
        
        Support_Function.addSubmitButton("/html//form[@name=\"assignments\"]",tester);
        tester.submit();
        System.out.print("fuck"+tester.getPageSource());
        tester.assertMatch("Add New Assignment");
        tester.assertLinkNotPresentWithText("malicious link");
    }
        
    @Test
    public void Attack_page(){

        tester.clickElementByXPath("//table/tbody/tr/td/a[@class='items']");
        tester.assertMatch("Class Settings");
        tester.clickElementByXPath("//form/a[3]");
        tester.assertMatch("Manage Assignments");
        tester.setWorkingForm("assignments");
        tester.setHiddenField("page2","4");
        tester.setHiddenField("page","2'> <a href=www.google.com>malicious link</a> <br  '");
        //tester.getElementByXPath("//form[@name='assignments']/input[@value='Add']").setAttribute("onclick", "document.assignments.submit()");
       
        Support_Function.addSubmitButton("/html//form[@name=\"assignments\"]",tester);
        tester.submit();
        tester.assertMatch("Add New Assignment");
        tester.assertLinkNotPresentWithText("malicious link");
    }
    
    
}
