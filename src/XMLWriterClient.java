import edu.stanford.sulair.dlss.was.metadata.writer.MetadataWriterXML;


public class XMLWriterClient {

	public static void main(String[] args) {

		MetadataWriterXML writer = new MetadataWriterXML(System.out);
		writer.print();
	}

}
