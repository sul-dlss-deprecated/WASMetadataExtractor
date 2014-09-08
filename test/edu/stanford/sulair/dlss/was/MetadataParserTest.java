package edu.stanford.sulair.dlss.was;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import edu.stanford.sulair.dlss.was.metadata.parser.MetadataParser;

public class MetadataParserTest {
	
	String warcfileName = "data/WARC-Test.warc.gz";
	String arcfileName = "data/ARC-Test.arc.gz";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testextractWAMetadataWithWARC() throws FileNotFoundException, IOException {
		System.out.println("========TEST1=========");
		MetadataParser metadataParser = new MetadataParser();
		System.out.println(metadataParser.extractWAMetadata(warcfileName));
	}

	@Test
	public void testextractWAMetadataWithARC() throws FileNotFoundException, IOException {
		System.out.println("========TEST2=========");
		MetadataParser metadataParser = new MetadataParser();
		System.out.println(metadataParser.extractWAMetadata(arcfileName));
	}

	@Test
	public void testextractWAMetadataTwoFiles() throws FileNotFoundException, IOException {
		System.out.println("========TEST3=========");
		MetadataParser metadataParser = new MetadataParser();
		System.out.println(metadataParser.extractWAMetadata(arcfileName));
		System.out.println(metadataParser.extractWAMetadata(warcfileName));

	}
}
