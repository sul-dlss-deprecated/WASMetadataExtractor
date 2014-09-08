/*******************************************************************************
 * 2014, All rights reserved.
 *******************************************************************************/
package edu.stanford.sulair.dlss.was.metadata.reader;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.stanford.sulair.dlss.was.metadata.MetadataRepository;

// End of user code

/**
 * Description of WAReaderInterface.
 * 
 * @author aalsum
 */
public interface IWAReader {

	public MetadataRepository fillMetadataRepositoryFromFile() throws FileNotFoundException, IOException ;

}
