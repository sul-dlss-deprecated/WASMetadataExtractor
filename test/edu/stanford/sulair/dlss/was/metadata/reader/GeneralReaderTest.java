package edu.stanford.sulair.dlss.was.metadata.reader;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import edu.stanford.sulair.dlss.was.metadata.MetadataRepository;

public class GeneralReaderTest {
	String testFileName = "data/test.txt";

	@Test
	public void testFillMetadataRepositoryFromFileWithStringFileName() {
		GeneralReader reader = new GeneralReader(testFileName);
		try {
			MetadataRepository mr = reader.fillMetadataRepositoryFromFile();
			assertTrue("GENERAL".equalsIgnoreCase(mr.getFileType()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail("The file type General was not assigned correctly");
		} catch (IOException e) {
			e.printStackTrace();
			fail("The file type General was not assigned correctly");
		}
	}

	@Test
	public void testFillMetadataRepositoryFromFileWithFileObj() {
		File testFileObj = new File(testFileName);
		GeneralReader reader = new GeneralReader(testFileObj);
		try {
			MetadataRepository mr = reader.fillMetadataRepositoryFromFile();
			assertTrue("GENERAL".equalsIgnoreCase(mr.getFileType()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail("The file type General was not assigned correctly");
		} catch (IOException e) {
			e.printStackTrace();
			fail("The file type General was not assigned correctly");
		}
	}

}
