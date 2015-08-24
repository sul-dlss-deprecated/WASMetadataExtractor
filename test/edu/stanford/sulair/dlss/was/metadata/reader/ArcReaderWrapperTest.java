package edu.stanford.sulair.dlss.was.metadata.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import edu.stanford.sulair.dlss.was.metadata.MetadataRepository;

public class ArcReaderWrapperTest {
	String arcFileName = "data/ARC-Test.arc.gz";

	@Test
	public void testFillMetadataRepositoryFromFile() {
		ArcReaderWrapper arcReader = new ArcReaderWrapper(arcFileName);
		try {
			MetadataRepository metadataRepo =  arcReader.fillMetadataRepositoryFromFile();

			assertTrue("ARC".equalsIgnoreCase(metadataRepo.getFileType()));
			assertTrue("ARC-Test.arc.gz".equalsIgnoreCase(metadataRepo.fileName));
			assertEquals(27089,metadataRepo.recordCount);
			assertEquals(87846905,metadataRepo.size);
			assertTrue("application/octet-stream".equalsIgnoreCase(metadataRepo.mimeType));
			assertTrue("e4fd69c988b5abb5d082e4ec897a582d74dc2bbf".equalsIgnoreCase(metadataRepo.checksumSHA1));
			assertTrue("f05e6759eeebbed5e17266809872c9f3".equalsIgnoreCase(metadataRepo.checksumMD5));
			
			assertTrue("20090212171334".equalsIgnoreCase((String)metadataRepo.getHeaderMap().get("archiveDate")));
			assertTrue("ARCHIVEIT-1078-STANFORD-CRS-20090212171334-00078-crawling015.us.archive.org.arc".equalsIgnoreCase((String)metadataRepo.getHeaderMap().get("ARC-Filename")));
			assertTrue("text/plain".equalsIgnoreCase((String)metadataRepo.getHeaderMap().get("contentType")));
			assertTrue("filedesc://ARCHIVEIT-1078-STANFORD-CRS-20090212171334-00078-crawling015.us.archive.org.arc".equalsIgnoreCase((String)metadataRepo.getHeaderMap().get("url")));
			assertTrue(metadataRepo.payload.startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail("There is a problem with Arc file reading");
		} catch (IOException e) {
			e.printStackTrace();
			fail("There is a problem with Arc file reading");
		}
	}
}
