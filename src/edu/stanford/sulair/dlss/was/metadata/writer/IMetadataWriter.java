package edu.stanford.sulair.dlss.was.metadata.writer;

import java.util.ArrayList;

public interface IMetadataWriter {

	void setMetadataRepositoryList(ArrayList list);

	void print();

	void print(String collectionId);

	void print(String collectionId, String crawlId);
}
