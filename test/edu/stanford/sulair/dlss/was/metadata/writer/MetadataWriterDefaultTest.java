package edu.stanford.sulair.dlss.was.metadata.writer;
import java.io.PrintStream;
import java.util.ArrayList;

import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Test;

import edu.stanford.sulair.dlss.was.metadata.MetadataRepository;

public class MetadataWriterDefaultTest extends EasyMockSupport {

	@Test
	public void testPrint() {
		
		PrintStream printStream = createMock(PrintStream.class);
		
		MetadataRepository metadataRepo = new MetadataRepository();
		metadataRepo.setFileType("Test");
		MetadataWriterDefault writer = new MetadataWriterDefault(printStream);
		ArrayList list = new ArrayList();
		list.add(metadataRepo);
		writer.setMetadataRepositoryList(list);
		
		// Expectation
		printStream.print("Collection Id:\t1");
		printStream.print("Crawl Id:\t2");
		printStream.print("MetadataRepository [\nfileName=, \nfileType=Test, \nheaderLine={}" 
				+ ", \npayload=, \nrecordCount=0, \nsize= 0, \nmimetype= , \n" + "checksum-MD5= ,"
				+ " \nchecksum-SHA1= , \nmetadataMap=\n");
		
		replayAll();
		writer.print("1","2");
		verifyAll();
	}

}