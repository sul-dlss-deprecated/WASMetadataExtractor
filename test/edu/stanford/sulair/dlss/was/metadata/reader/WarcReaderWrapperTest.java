package edu.stanford.sulair.dlss.was.metadata.reader;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import edu.stanford.sulair.dlss.was.metadata.MetadataRepository;
import edu.stanford.sulair.dlss.was.metadata.reader.WarcReaderWrapper;

public class WarcReaderWrapperTest {

	String fileName = "data/WARC-Test.warc.gz";
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testReadFile() {
		WarcReaderWrapper warcReader = new WarcReaderWrapper(fileName);
		try {
			MetadataRepository metadataRep =  warcReader.fillMetadataRepositoryFromFile();
			System.out.println(metadataRep);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
