package edu.stanford.sulair.dlss.was;

import org.junit.Before;
import org.junit.Test;

public class WASMetadataExtractorAppTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMain() {
		
		String[] args = {"WASMetadataExtractor","-d","data","-f","XML"};
		WASMetadataExtractorApp.main(args);
	}

	@Test
	public void testMainHelp(){
		String[] args = {"WASMetadataExtractor","-help"};
		WASMetadataExtractorApp.main(args);
	}
	
	@Test
	public void testMainExactFile(){
		String[] args = {"WASMetadataExtractor","data/WARC-Test.warc.gz"};
		WASMetadataExtractorApp.main(args);
	}
	
	@Test
	public void testMainInputFileToOutputFile(){
		String[] args = {"WASMetadataExtractor", "-o","output/warc-out.txt","-d","data"};
		WASMetadataExtractorApp.main(args);
	}
	@Test
	public void testMainExactTXTFile(){
		String[] args = {"WASMetadataExtractor","-f","XML","data/test.txt"};
		WASMetadataExtractorApp.main(args);
	}
	
}
