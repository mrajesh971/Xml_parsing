package xml_parser;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import xml_parser.CommonAddressCache;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class Xmlparsing {
	public static synchronized Address[] getContent(Element element) {
		
		 Address[] a = new Address[10]; 
		 CommonAddressCache c = new CommonAddressCache();
		  int cnt=0;
		  StringBuilder builder = new StringBuilder();
		  
		  for (Iterator<Element> i = element.elementIterator("Request"); i.hasNext();) {
			  
		    Element e = i.next();
		    List<Node> list1 = e.selectNodes("//*[name() = 'PostalAddress']");
		    
		    for(Node n: list1)
		    {
		    	Element element1 = (Element) n;
		    	 Iterator<Element> iterator = element1.elementIterator("Street");
		    	 List<String> streets=new ArrayList<String>();
		            while(iterator.hasNext()) {
		               Element marks = (Element)iterator.next();
		               if(marks.getText().length()!=0)
		               {
		               streets.add(marks.getText());
		               }
		            } 
		        a[cnt] = c.createAddress(streets,n.selectSingleNode("City").getText(),n.selectSingleNode("State").getText(),n.selectSingleNode("PostalCode").getText(),n.selectSingleNode("Country").valueOf("@isoCountryCode"));
		        cnt++;
		  }
		  }
		  return a;
		}

	public static synchronized void parse() {
		System.out.println("----------------------------");
		try {
	         File inputFile = new File("/Users/rajeshnadal/Downloads/invoice.xml");
	         SAXReader reader = new SAXReader();
	         Document document = reader.read( inputFile );
	         Element classElement = document.getRootElement();
	         Address[] a = new Address[10];
	         a = getContent(classElement);
	         System.out.println(a[0].city);
	      } catch (DocumentException e) {
	         e.printStackTrace();
	      }
		
	}
}
