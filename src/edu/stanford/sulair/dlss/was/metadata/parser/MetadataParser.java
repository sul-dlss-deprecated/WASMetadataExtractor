/*******************************************************************************
 * 2014, All rights reserved.
 *******************************************************************************/
package edu.stanford.sulair.dlss.was.metadata.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;




// Start of user code (user defined imports)
import org.yaml.snakeyaml.Yaml;

import edu.stanford.sulair.dlss.was.metadata.ExtractorFactory;
import edu.stanford.sulair.dlss.was.metadata.MetadataRepository;
import edu.stanford.sulair.dlss.was.metadata.reader.IWAReader;

// End of user code

/**
 * Description of MetadataParser.
 * 
 * @author aalsum
 */
public class MetadataParser {

	private static String DEFAULT_CONFIG_FILE_PATH="config/extractor.yml";

	private String configFilePath = null;
	
	public MetadataParser(){
		this.configFilePath = DEFAULT_CONFIG_FILE_PATH;
	}

	
	public MetadataParser(String configFilePath){
		this.configFilePath = configFilePath;
	}


	public  MetadataRepository extractWAMetadata(String fileName) throws FileNotFoundException, IOException{
		return extractWAMetadata( new File(fileName)); 

	}
	public  MetadataRepository extractWAMetadata(File fileObj) throws FileNotFoundException, IOException{
		
		IWAReader waReader = ExtractorFactory.getExtractor(fileObj);
		
		if(waReader == null){
			throw new IOException(fileObj.getName()+" has a wrong file format.");
		}
		MetadataRepository metadataRepository = waReader.fillMetadataRepositoryFromFile();
		
		Yaml yaml = new Yaml();
		Map map = (Map) yaml.load(new FileInputStream(configFilePath));

		Map configMap = (Map) map.get(metadataRepository.getFileType());
		
		
		Set<String> set = configMap.keySet();
		Iterator<String> iterator = set.iterator();
		while(iterator.hasNext()){
			String key = iterator.next();

			Map recordMap = (Map)configMap.get(key);
			
			if(recordMap.containsKey("type")){
				String parserType = (String) recordMap.get("type");
				
				try {
					MetadataRecordParser metadataRecordParser = getMetadataRecordParserObject(parserType);
					
					Object value = metadataRecordParser.getValue(metadataRepository, recordMap);
					if(value != null){
						metadataRepository.metadataMap.put(key, value);
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					continue;
				} catch (InstantiationException e) {
					e.printStackTrace();
					continue;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					continue;
				}
			}
		}
		
		return metadataRepository;
		
	}
	private MetadataRecordParser getMetadataRecordParserObject(
			String parserType) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

		String packageName = this.getClass().getPackage().getName();
		String fullClassName =  packageName+".MetadataRecordParser"+parserType;
		
		Class cls = Class.forName(fullClassName);
		MetadataRecordParser obj =(MetadataRecordParser) cls.newInstance();
		
		return obj;
	}
	

}
