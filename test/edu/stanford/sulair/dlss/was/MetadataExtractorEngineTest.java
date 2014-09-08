package edu.stanford.sulair.dlss.was;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import edu.stanford.sulair.dlss.was.metadata.MetadataExtractorEngine;

public class MetadataExtractorEngineTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testNullConstructor() {
		String f = null;
		MetadataExtractorEngine engine = new MetadataExtractorEngine(f);
	}

}
