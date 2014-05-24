/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xss_injection.TA;



import Support.Support_Function;
import net.sourceforge.jwebunit.junit.*;
import org.junit.Before;
import org.junit.Test;



/**
 *
 * @author iono
 */
public class xss_index_php_76 {
    
    WebTester tester = new WebTester();
    
    public xss_index_php_76() {
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

        tester.clickLinkWithText("pentest");
        tester.assertMatch("Class Settings");
        tester.clickLinkWithText("Grades");
        tester.setWorkingForm("grades");
        tester.checkCheckbox("delete[]", "1");
        tester.selectOptionByValue("assignment", "1");
        tester.setHiddenField("page2","7'> <a href=www.google.com>malicious link</a> <br '");
        Support_Function.addSubmitButton("/html//form[@name=\"grades\"]",tester);
        tester.submit();
        tester.assertMatch("Edit Grade");
        tester.assertLinkNotPresentWithText("malicious link");
    }
    @Test
        public void Attack_page() {

        tester.clickLinkWithText("pentest");
        tester.assertMatch("Class Settings");
        tester.clickLinkWithText("Grades");
        tester.setWorkingForm("grades");
        tester.checkCheckbox("delete[]", "1");
        tester.selectOptionByValue("assignment", "1");
        tester.setHiddenField("page2", "7");
        tester.setHiddenField("page","2'> <a href=www.google.com>malicious link</a> <br '");
        Support_Function.addSubmitButton("/html//form[@name=\"grades\"]",tester);
        tester.submit();
        tester.assertMatch("Edit Grade");
        tester.assertLinkNotPresentWithText("malicious link");
    }
    
        @Test
        public void Attack_delete() {

        tester.clickLinkWithText("pentest");
        tester.assertMatch("Class Settings");
        tester.clickLinkWithText("Grades");
        tester.setWorkingForm("grades");
        tester.checkCheckbox("delete[]", "1");
        tester.selectOptionByValue("assignment", "1");
        tester.setHiddenField("page2", "7");
        tester.getElementByXPath("//form/table/tbody/tr/td/input[@type='checkbox' and @value='1']").setAttribute("value", "1' -- /> <a href=www.google.com>malicious link</a> <br '");
        Support_Function.addSubmitButton("/html//form[@name=\"grades\"]",tester);
        tester.submit();
        tester.assertMatch("Edit Grade");
        tester.assertLinkNotPresentWithText("malicious link");
    }

        @Test
        public void Attack_assignment() {

        tester.clickLinkWithText("pentest");
        tester.assertMatch("Class Settings");
        tester.clickLinkWithText("Grades");
        tester.setWorkingForm("grades");
        tester.checkCheckbox("delete[]", "1");
        tester.selectOptionByValue("assignment", "1");
        tester.setHiddenField("page2", "7");
        tester.getElementByXPath("//form/select/option[@value='1']").setAttribute("value", "1' -- /> <a href=www.google.com>malicious link</a> <br '");
        Support_Function.addSubmitButton("/html//form[@name=\"grades\"]",tester);
        tester.submit();
        tester.assertMatch("Edit Grade");
        tester.assertLinkNotPresentWithText("malicious link");
    }
        
    @Test
    public void Attack_selectclass() {

        tester.clickLinkWithText("pentest");
        tester.assertMatch("Class Settings");
        tester.clickLinkWithText("Grades");
        tester.setWorkingForm("grades");
        tester.checkCheckbox("delete[]", "1");
        tester.selectOptionByValue("assignment", "1");
        tester.setHiddenField("page2","7");
        tester.setHiddenField("selectclass", "1'> <a href=www.google.com>malicious link</a> <br '");
        Support_Function.addSubmitButton("/html//form[@name=\"grades\"]",tester);
        tester.submit();
        tester.assertMatch("Edit Grade");
        tester.assertLinkNotPresentWithText("malicious link");
    }
        
}
