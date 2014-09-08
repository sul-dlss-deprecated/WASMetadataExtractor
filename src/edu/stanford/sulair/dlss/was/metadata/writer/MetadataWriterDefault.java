package edu.stanford.sulair.dlss.was.metadata.writer;

import java.io.PrintStream;
import java.util.ArrayList;

import edu.stanford.sulair.dlss.was.metadata.MetadataRepository;

public class MetadataWriterDefault extends MetadataWriterAbstract {


	public MetadataWriterDefault(PrintStream out){
		super(out);
	}
	
	@Override
	public void print(String collectionId, String crawlId) {
		out.print("Collection Id:\t"+collectionId);
		out.print("Crawl Id:\t"+crawlId);
		
		for(MetadataRepository repo: repoList){
			out.print(repo.toString());
		}
	}

	@Override
	public void setMetadataRepositoryList(ArrayList list) {
		this.repoList = list;
	}

}

