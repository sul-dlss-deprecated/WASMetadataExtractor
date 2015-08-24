package edu.stanford.sulair.dlss.was.metadata.writer;

import java.io.PrintStream;
import java.util.ArrayList;

import edu.stanford.sulair.dlss.was.metadata.MetadataRepository;

public abstract class MetadataWriterAbstract implements IMetadataWriter {
	PrintStream out = null;

	public ArrayList<MetadataRepository> repoList = null;

	public MetadataWriterAbstract(PrintStream out) {
		this.out = out;
	}

	public void print() {
		print("", "");
	}

	public void print(String collectionId) {
		print(collectionId, "");
	}

	@Override
	abstract public void print(String collectionId, String crawlId);

	@Override
	public void setMetadataRepositoryList(ArrayList list) {
		this.repoList = list;
	}
}
