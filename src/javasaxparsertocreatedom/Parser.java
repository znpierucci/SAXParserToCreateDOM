/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasaxparsertocreatedom;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author zacharypierucci
 */
public class Parser {
    
    static String text = "";
    
    public static String load(File xml) throws Exception {
        
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
               
                String name = null;
                String str = null;
                boolean first = false;
                    
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {


                    name = qName;
                    str = "";
                    if(!first) {
                        first = true;
                        text = text.concat(qName + ":");
                    }
                    
                }
                
                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {

                    if(name.equals(qName)) {
                            text = text.concat("\n\t\t" + qName + ": '" + str + "'");
                        }
                }
                
                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                
                    str = new String(ch, start, length) ;
                }
            };
            
            parser.parse(xml.getAbsoluteFile(), handler);
            
        } catch (SAXException e) {
            throw e;
        }
        
      return text; 
    }
}
