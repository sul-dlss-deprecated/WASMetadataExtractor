package edu.stanford.sulair.dlss.was.metadata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.stanford.sulair.dlss.was.metadata.parser.MetadataParser;
import edu.stanford.sulair.dlss.was.metadata.writer.IMetadataWriter;
import edu.stanford.sulair.dlss.was.metadata.writer.MetadataWriterDefault;

public class MetadataExtractorEngine {
	
	private ArrayList<File> fileList = null; 
	
	private String configFile = null;
	
	private IMetadataWriter writer = null;
	public MetadataExtractorEngine(ArrayList<File> fileList){
		this.fileList =fileList;
		writer = new MetadataWriterDefault(System.out);
		
	}
	
	public MetadataExtractorEngine(ArrayList<File> fileList, IMetadataWriter writer){
		this.fileList =fileList;
		this.writer = writer;
	}
	
	public MetadataExtractorEngine(ArrayList<File> fileList, String configFile, IMetadataWriter writer){
		this.fileList =fileList;
		this.writer = writer;
		this.configFile = configFile;

	}
	/**
	 * @param file
	 */
	public MetadataExtractorEngine(File file){
		fileList =new ArrayList<File>(1);
		fileList.add(file);
	}
	
	/**
	 * @param fileName
	 */
	public MetadataExtractorEngine(String fileName){
		File file = new File(fileName);
		fileList =new ArrayList<File>(1);
		fileList.add(file);
	}
	
	public MetadataExtractorEngine(ArrayList<File> fileList, String configFile){
		this.fileList =fileList;
		this.configFile = configFile;
	}
	
	/**
	 * @param file
	 */
	public MetadataExtractorEngine(File file, String configFile){
		fileList =new ArrayList<File>(1);
		fileList.add(file);
		this.configFile = configFile;
	}
	
	/**
	 * @param fileName
	 */
	public MetadataExtractorEngine(String fileName, String configFile){
		File file = new File(fileName);
		fileList =new ArrayList<File>(1);
		fileList.add(file);
		this.configFile = configFile;
	}
	

	
	public void runExtractor(){
		
		
		ArrayList<MetadataRepository> repoList = new ArrayList<MetadataRepository>();
		MetadataParser parser = null;
		if(configFile == null){
			parser = new MetadataParser();
		} else {
			parser = new MetadataParser(configFile);
		}
		
		for(File f: fileList){
		
			try {
				 repoList.add( parser.extractWAMetadata(f));
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.exit(-1);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		
		writer.setMetadataRepositoryList(repoList);
	}
	
	public void runExtractorToFile(){
		
	}
	
	public IMetadataWriter getWriter(){
		return this.writer;
	}
	
	
	
}
