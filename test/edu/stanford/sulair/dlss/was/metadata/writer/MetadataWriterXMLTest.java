package edu.stanford.sulair.dlss.was.metadata.writer;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Test;

import edu.stanford.sulair.dlss.was.metadata.MetadataRepository;

public class MetadataWriterXMLTest {

	String xmlOutputFile = "tmp/xmlWriter.xml";

	@Test
	public void testPrint() throws IOException {
		String xmlOutput = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<crawlObject>\n"
				+ "<crawlId>2</crawlId>\n<collectionId>1</collectionId>\n<files>\n<file>\n<name/>\n"
				+ "<type>Test</type>\n<size>0</size>\n<recordCount>0</recordCount>\n<mimeType/>\n"
				+ "<checksumMD5/>\n<checksumSHA1/>\n</file>\n</files>\n</crawlObject>\n";
		
		MetadataRepository metadataRepo = new MetadataRepository();
		metadataRepo.setFileType("Test");
		ArrayList list = new ArrayList();
		list.add(metadataRepo);
		
		PrintStream out = new PrintStream(xmlOutputFile);
		MetadataWriterXML writer = new MetadataWriterXML(out);
		writer.setMetadataRepositoryList(list);
		writer.print("1","2");
		
		// Read the output file
		BufferedReader reader = new BufferedReader(new FileReader(xmlOutputFile));
		String actualOutput = ""; 
		while(reader.ready()){
			actualOutput += reader.readLine()+"\n";
		}
		reader.close();
		
		// Assert True the expected and actual strings are similar
		assertTrue(xmlOutput.equalsIgnoreCase(actualOutput.replaceAll("\n\\s+", "\n")));
	}

	@After
	public void tearDown(){
		File outputFile = new File(xmlOutputFile);
		if(outputFile.exists()){
			outputFile.delete();
		}
	}
}
