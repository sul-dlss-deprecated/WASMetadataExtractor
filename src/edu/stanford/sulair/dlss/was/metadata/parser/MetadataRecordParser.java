/*******************************************************************************
 * 2014, All rights reserved.
 *******************************************************************************/
package edu.stanford.sulair.dlss.was.metadata.parser;

import java.util.Map;

import edu.stanford.sulair.dlss.was.metadata.MetadataRepository;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of MetadataRecordParser.
 * 
 * @author aalsum
 */
public abstract class MetadataRecordParser {
	// Start of user code (user defined attributes for MetadataRecordParser)

	// End of user code
	static final String PATH = "path";
	/**
	 * The constructor.
	 */
	public MetadataRecordParser() {
		super();
	}

	public abstract Object getValue(MetadataRepository metadataRepository,Map recordMap) ;

	protected String getPathValue(Map recordMap) {
		
		if(recordMap.containsKey(PATH)){
			 return (String) recordMap.get(PATH);
		}
		return null;
	}
}
