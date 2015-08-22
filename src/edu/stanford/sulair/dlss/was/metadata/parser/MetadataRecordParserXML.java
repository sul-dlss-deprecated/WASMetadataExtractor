package edu.stanford.sulair.dlss.was.metadata.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import edu.stanford.sulair.dlss.was.metadata.MetadataRepository;

/** 
 * Extracts Metadata record in the XML format.
 * The extracted metadata may be like:
 * <pre>{@code
 * <arcmetadata>
 *   <arc:robots>classic</arc:robots>
 *   ...
 * </arcmetadata>
 * }</pre>
 * And the configuration may be like:
 * <pre>{@code
 * robotsPolicy:
 *    type: XML
 *     path: "/arcmetadata/robots"
 * }</pre>
 * The code snippet should be
 * <pre>{@code
 * Map map = new Map();
 * map.add("type", "XML");
 * map.add("path", "/arcmetadata/robots");
 * mrpXML = MetadataRecordParserXML.new();
 * mrpXML.getValue(mdr, map);
 * }</pre>
 * The output is 
 * <pre>{@code
 *   classic
 * }</pre>
 * @author aalsum
 */
public class MetadataRecordParserXML extends MetadataRecordParser {

	@Override
	public Object getValue(MetadataRepository metadataRepository, Map recordMap) {
		String headersPayload = metadataRepository.getPayload();
		String path = getPathValue(recordMap);
		if (headersPayload == null || headersPayload.length() < 1
				|| path == null || path.length() < 1) {
			return null;
		}
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = null;
			Document xmlDocument;

			builder = builderFactory.newDocumentBuilder();
			xmlDocument = builder.parse(new ByteArrayInputStream(headersPayload
					.getBytes()));

			XPath xPath = XPathFactory.newInstance().newXPath();
			String value = xPath.compile(path).evaluate(xmlDocument);
			return value;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return null;
	}
}
