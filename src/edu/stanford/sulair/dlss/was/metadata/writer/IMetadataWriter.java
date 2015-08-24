package edu.stanford.sulair.dlss.was.metadata.writer;

import java.util.ArrayList;

/**
 * The interface for the Metadata writer
 * @author aalsum
 *
 */
public interface IMetadataWriter {

	/**
	 * Initializes the writer with the arraylist that represent
	 * the Metadata repository
	 * @param list
	 */
	void setMetadataRepositoryList(ArrayList list);

	/**
	 * prints the formated metadata information to the standard 
	 * output.
	 */
	void print();

	/**
	 * prints the formated metadata information to the standard
	 * output including the collection Id value
	 * @param collectionId
	 */
	void print(String collectionId);

	/**
	 * prints the formated metadata information to the standard
	 * output including collection Id and crawl Id values
	 * @param collectionId
	 * @param crawlId
	 */
	void print(String collectionId, String crawlId);
}
