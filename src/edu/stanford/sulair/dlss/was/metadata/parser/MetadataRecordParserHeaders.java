package edu.stanford.sulair.dlss.was.metadata.parser;

import java.util.Map;

import edu.stanford.sulair.dlss.was.metadata.MetadataRepository;

public class MetadataRecordParserHeaders extends MetadataRecordParser {

	@Override
	public Object getValue(MetadataRepository metadataRepository, Map recordMap) {

		String path = getPathValue(recordMap);
		
		if(path == null || path.length()<1){
			return null;
		}
		return metadataRepository.headerMap.get(path);

	}

}
