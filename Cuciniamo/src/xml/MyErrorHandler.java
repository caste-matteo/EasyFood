package xml;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;
public class MyErrorHandler implements ErrorHandler{

	 public void warning(SAXParseException e) {
	        System.out.println(e.getMessage());
	    }

	    public void error(SAXParseException e) {
	        System.out.println(e.getMessage());
	    }

	    public void fatalError(SAXParseException e) {
	        System.out.println(e.getMessage());
	    }
}
