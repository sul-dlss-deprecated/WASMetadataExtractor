package edu.stanford.sulair.dlss.was.metadata.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.stanford.sulair.dlss.was.metadata.MetadataRepository;

/** It's the basic implementation of WAReader to cover the basic information 
 * about the non-Web archiving files.
 * @author aalsum
 */
public class GeneralReader extends WAReader {

	private static final String BASIC_FILE_TYPE = "GENERAL";	

	public GeneralReader(String fileName) {
		super(fileName);
	}
	
	public GeneralReader(File fileObj) {
		super(fileObj);
	}

	@Override
	public MetadataRepository fillMetadataRepositoryFromFile()
			throws FileNotFoundException, IOException {

		MetadataRepository metadataRepository = fillBasicInformationFromFile();
		metadataRepository.setFileType(BASIC_FILE_TYPE);

		return metadataRepository;
	}
}
