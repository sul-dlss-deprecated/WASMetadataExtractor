/*******************************************************************************
 * 2014, All rights reserved.
 *******************************************************************************/
package edu.stanford.sulair.dlss.was.metadata.parser;

import java.util.Map;
import edu.stanford.sulair.dlss.was.metadata.MetadataRepository;

/**
 * It's the abstract class for reading every record
 * in the metadata based on the type. Each metadata 
 * record type will have its own class. The metadata type
 * is defined in the yml configuration file.
 * 
 * @author aalsum
 */
public abstract class MetadataRecordParser {
	static final String PATH = "path";

	/**
	 * The constructor.
	 */
	public MetadataRecordParser() {
		super();
	}

	public abstract Object getValue(MetadataRepository metadataRepository,
			Map recordMap);

	/** Extracts the metadata record path. The path value
	 * will change based on the record type.
	 * @param recordMap describes all metadata records information.
	 * @return the value of the path key or null if it's not found.
	 */
	protected String getPathValue(Map recordMap) {
		if (recordMap.containsKey(PATH)) {
			return (String) recordMap.get(PATH);
		}
		return null;
	}
}
