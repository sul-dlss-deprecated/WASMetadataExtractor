/*******************************************************************************
 * 2014, All rights reserved.
 *******************************************************************************/
package edu.stanford.sulair.dlss.was.metadata.reader;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.stanford.sulair.dlss.was.metadata.MetadataRepository;

// End of user code

/**
 * IWAReader is the interface for the process of reading 
 * Web archiving files and returns MetadataRepository obj.
 * 
 * @author aalsum
 */
public interface IWAReader {

	/**
	 * Fills the MetadataRepository object from the file content
	 * @return MetadataRepository filled with the metadata from the file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public MetadataRepository fillMetadataRepositoryFromFile()
			throws FileNotFoundException, IOException;
}
