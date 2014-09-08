package edu.stanford.sulair.dlss.was.metadata.parser;

import java.util.Map;

import edu.stanford.sulair.dlss.was.metadata.MetadataRepository;

public class MetadataRecordParserNameValue extends MetadataRecordParser {

	@Override
	public Object getValue(MetadataRepository metadataRepository, Map recordMap) {
		
		String path = getPathValue(recordMap);
		
		if(path == null || path.length()<1){
			return null;
		}
		
		String headersPayload = metadataRepository.getPayload();
		String[] payloadLines = headersPayload.split((String)recordMap.get("recordSeparator"));
		
		for(String line : payloadLines){
			if(line.indexOf(path)>-1){
				int index = line.indexOf((String)recordMap.get("fieldSeparator"), path.length()-1);
				if(index >0){
					return line.substring(index+1);
				}
			}
		}
		return null;
	}

}
