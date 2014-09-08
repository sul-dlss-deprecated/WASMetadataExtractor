package edu.stanford.sulair.dlss.was.metadata.reader;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import edu.stanford.sulair.dlss.was.metadata.MetadataRepository;
import edu.stanford.sulair.dlss.was.metadata.reader.ArcReaderWrapper;

public class ArcReaderWrapperTest {
	String fileName = "data/ARC-Test.arc.gz";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testReadFile() {
		ArcReaderWrapper arcReader = new ArcReaderWrapper(fileName);
		try {
			MetadataRepository metadataRep =  arcReader.fillMetadataRepositoryFromFile();
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
