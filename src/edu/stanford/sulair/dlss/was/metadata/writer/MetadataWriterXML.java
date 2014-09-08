package edu.stanford.sulair.dlss.was.metadata.writer;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import edu.stanford.sulair.dlss.was.metadata.MetadataRepository;

public class MetadataWriterXML extends MetadataWriterAbstract {

	public MetadataWriterXML(PrintStream out) {
		super(out);
	}

	@Override
	public void print(String collectionId, String crawlId) {
		
		Document doc = null;
		try {
			doc = createDOMObject();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		Element crawlObject = doc.createElement("crawlObject");
		doc.appendChild(crawlObject);
		
		Element crawlElement = doc.createElement("crawlId");
		
		crawlElement.appendChild(doc.createTextNode(crawlId));
		crawlObject.appendChild(crawlElement);

		Element collectionElement = doc.createElement("collectionId");
		
		collectionElement.appendChild(doc.createTextNode(collectionId));
		crawlObject.appendChild(collectionElement);

		Element files = doc.createElement("files");
		crawlObject.appendChild(files);
		
		for(MetadataRepository repo: repoList){
			Element file = createFileElement(doc,repo);
			files.appendChild(file);
		}
		
		
		try {
			writeDOMObject(doc);
		} catch (TransformerException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	private Element createFileElement(Document doc, MetadataRepository repo) {
		
		Element file = doc.createElement("file");
		file.appendChild(getTextElements(doc,"name",repo.fileName ));
		file.appendChild(getTextElements(doc,"type",repo.fileType ));
		file.appendChild(getTextElements(doc, "size",String.valueOf( repo.size) ));
		file.appendChild(getTextElements(doc, "recordCount",String.valueOf(repo.recordCount) ));
		file.appendChild(getTextElements(doc,"mimeType",repo.mimeType ));
		file.appendChild(getTextElements(doc,"checksumMD5",repo.checksumMD5 ));
		file.appendChild(getTextElements(doc,"checksumSHA1",repo.checksumSHA1 ));

		Set<String> set = repo.metadataMap.keySet();
		Iterator<String> iterator = set.iterator();
		while(iterator.hasNext()){
			String key = iterator.next();
			file.appendChild(getTextElements(doc, key,(String)repo.metadataMap.get(key) ));
		}
		return file;
	}

    //utility method to create text node
    private  Node getTextElements(Document doc,  String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
	private Document createDOMObject() throws ParserConfigurationException {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder builder = null;
		
		builder = builderFactory.newDocumentBuilder();

		return builder.newDocument();
		
		
	}
	
	private void writeDOMObject(Document doc) throws TransformerException{
		//for output to file, console
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        //for pretty print
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);

        //write to console or file
        StreamResult console = new StreamResult(out);

        //write data
        transformer.transform(source, console);

	}

	public static void main(String[] args) {

		MetadataWriterXML writer = new MetadataWriterXML(System.out);
		writer.print();
	}
}
