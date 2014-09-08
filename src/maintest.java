import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import edu.stanford.sulair.dlss.was.metadata.parser.MetadataRecordParser;
import edu.stanford.sulair.dlss.was.metadata.parser.MetadataRecordParserHeaders;


public class maintest {

	public static void main(String[] args) throws Exception {
		//MetadataRecordParserHeaders a = new MetadataRecordParserHeaders();
		//Class cls = Class.forName("org.stanford.sul.webarchive.MetadataRecordParserHeaders");
		//MetadataRecordParser obj =(MetadataRecordParser) cls.newInstance();
		
		
		String xmldoc = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"+
		"<arcmetadata xmlns:dc=\"http://purl.org/dc/elements/1.1/\" xmlns:dcterms=\"http://purl.org/dc/terms/\""+
		" xmlns:arc=\"http://archive.org/arc/1.0/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://archive.org/arc/1.0/\" xsi:schemaLocation=\"http://archive.org/arc/1.0/ http://www.archive.org/arc/1.0/arc.xsd\">"+
		"<arc:hostname>crawling015.us.archive.org</arc:hostname>"+
		"<arc:ip>207.241.235.73</arc:ip>"+
		"</arcmetadata>";
		
		String path  ="/arcmetadata/hostname";


		try {

			DocumentBuilderFactory builderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = null;
			
			builder = builderFactory.newDocumentBuilder();

			Document xmlDocument;
			xmlDocument = builder.parse(new ByteArrayInputStream(xmldoc
					.getBytes()));

			XPath xPath = XPathFactory.newInstance().newXPath();

			String expression = path;

			String email = xPath.compile(expression).evaluate(xmlDocument);
			System.out.println(email);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}


		
	}

}
