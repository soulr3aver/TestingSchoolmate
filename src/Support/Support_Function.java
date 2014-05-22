/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Support;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.InputElementFactory;
import net.sourceforge.jwebunit.api.IElement;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitElementImpl;
import net.sourceforge.jwebunit.junit.WebTester;
import org.xml.sax.helpers.AttributesImpl;

/**
 *
 * @author iono
 */
public class Support_Function {
    
    public static void addSubmitButton(String xpath,WebTester tester){
            IElement element = tester.getElementByXPath(xpath);
            DomElement form = ((HtmlUnitElementImpl) element).getHtmlElement();
            InputElementFactory factory = InputElementFactory.instance;
            AttributesImpl attributes = new AttributesImpl();
            attributes.addAttribute("", "", "type", "", "submit");
            HtmlElement submit = factory.createElement(form.getPage(), "input", attributes);
            form.appendChild(submit);
    }
    
}
