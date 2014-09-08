/*******************************************************************************
 * 2014, All rights reserved.
 *******************************************************************************/
package edu.stanford.sulair.dlss.was.metadata;

import java.io.File;

import edu.stanford.sulair.dlss.was.metadata.reader.ArcReaderWrapper;
import edu.stanford.sulair.dlss.was.metadata.reader.GeneralReader;
import edu.stanford.sulair.dlss.was.metadata.reader.IWAReader;
import edu.stanford.sulair.dlss.was.metadata.reader.WarcReaderWrapper;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of ExtractorFactory.
 * 
 * @author aalsum
 */
public class ExtractorFactory {
	// Start of user code (user defined attributes for ExtractorFactory)

	// End of user code

	/**
	 * The constructor.
	 */
	private ExtractorFactory() {
		// Start of user code constructor for ExtractorFactory)
		super();
		// End of user code
	}

	/**
	 * Description of the method GetExtractor.
	 * @param type 
	 * @return 
	 */
	public static IWAReader getExtractor(String fileName) {
		return getExtractor(new File(fileName));
	}
	
	/**
	 * Description of the method GetExtractor.
	 * @param type 
	 * @return 
	 */
	public static IWAReader getExtractor(File fileObj) {
		
		String fileName = fileObj.getName();
		if(null == fileName || fileName.length()<1){
			return null;
		}
		
		if(fileName.endsWith(".arc")||fileName.endsWith(".arc.gz")){
			return new ArcReaderWrapper(fileObj);
		}else if(fileName.endsWith(".warc")||fileName.endsWith(".warc.gz")){
			return new WarcReaderWrapper(fileObj);
		} else{
			return new GeneralReader(fileObj);
		}
	}

}
