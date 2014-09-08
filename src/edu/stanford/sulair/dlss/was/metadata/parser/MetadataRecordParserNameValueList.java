package edu.stanford.sulair.dlss.was.metadata.parser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import edu.stanford.sulair.dlss.was.metadata.MetadataRepository;

public class MetadataRecordParserNameValueList extends MetadataRecordParser {

	@Override
	public Object getValue(MetadataRepository metadataRepository, Map recordMap) {
		String path = getPathValue(recordMap);
		
		if(path == null || path.length()<1){
			return null;
		}
		
		ArrayList<String> recordSeparatorList =(ArrayList<String>) recordMap.get("recordSeparator");

		if(recordSeparatorList == null )
			return null;
		
		
		String headersPayload = metadataRepository.getPayload();
		
		Queue<String> queue = new LinkedList<String>();
		queue.add(headersPayload);
		
		
		for(String recordSeparator: recordSeparatorList){
			int size = queue.size();	
			
			for(int i =0; i<size; i++){
				String line = (String) queue.poll();
				String[] payloadLines = line.split(recordSeparator);
				
				for(String s:  payloadLines){
					queue.add(s);
				}
			}
		}
		
		for(String line : queue){
			
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
