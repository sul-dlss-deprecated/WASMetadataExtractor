package edu.stanford.sulair.dlss.was.metadata.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import edu.stanford.sulair.dlss.was.metadata.MetadataRepository;

public class WarcReaderWrapperTest {

	String warcFileName = "data/WARC-Test.warc.gz";
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFillMetadataRepositoryFromFile() {
		WarcReaderWrapper warcReader = new WarcReaderWrapper(warcFileName);
		try {
			MetadataRepository metadataRepo =  warcReader.fillMetadataRepositoryFromFile();

			assertTrue("WARC".equalsIgnoreCase(metadataRepo.getFileType()));
			assertTrue("WARC-Test.warc.gz".equalsIgnoreCase(metadataRepo.fileName));
			System.out.println(metadataRepo);
			assertEquals(4027,metadataRepo.recordCount);
			assertEquals(6608320,metadataRepo.size);
			assertTrue("application/warc".equalsIgnoreCase(metadataRepo.mimeType));
			assertTrue("3a9f2ffac1497c70291d93a8bc86c1469547d8f8".equalsIgnoreCase(metadataRepo.checksumSHA1));
			assertTrue("c7edbde066e4697b3f2d823ac42c3692".equalsIgnoreCase(metadataRepo.checksumMD5));

			assertTrue("2014-01-19T22:37:40Z".equalsIgnoreCase((String)metadataRepo.getHeaderMap().get("WARC-Date")));
			assertTrue("ARCHIVEIT-924-QUARTERLY-31501-20140119223740943-00015-wbgrp-crawl051.us.archive.org-6441.warc.gz".equalsIgnoreCase((String)metadataRepo.getHeaderMap().get("WARC-Filename")));
			assertTrue("application/warc-fields".equalsIgnoreCase((String)metadataRepo.getHeaderMap().get("Content-Type")));
			assertTrue("<urn:uuid:d999aa6d-ebe5-4a3a-bc4b-4de7ce827f4a>".equalsIgnoreCase((String)metadataRepo.getHeaderMap().get("WARC-Record-ID")));
			assertTrue(metadataRepo.payload.startsWith("software: Heritrix/3.2.0-SNAPSHOT-20140108-2049 http://crawler.archive.org"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail("There is a problem with Warc file reading");
		} catch (IOException e) {
			e.printStackTrace();
			fail("There is a problem with Warc file reading");
		}
	}
}
