/*******************************************************************************
 * 2014, All rights reserved.
 *******************************************************************************/
package edu.stanford.sulair.dlss.was.metadata;

import java.io.File;

import edu.stanford.sulair.dlss.was.metadata.reader.ArcReaderWrapper;
import edu.stanford.sulair.dlss.was.metadata.reader.GeneralReader;
import edu.stanford.sulair.dlss.was.metadata.reader.IWAReader;
import edu.stanford.sulair.dlss.was.metadata.reader.WarcReaderWrapper;

/**
 * Chooses the extractor based on file type.
 * 
 * @author aalsum
 */
public class ExtractorFactory {
	/**
	 * The constructor.
	 */
	private ExtractorFactory() {
		super();
	}

	/**
	 * Description of the method GetExtractor.
	 * 
	 * @param type
	 * @return
	 */
	public static IWAReader getExtractor(String fileName) {
		return getExtractor(new File(fileName));
	}

	/**
	 * Description of the method GetExtractor.
	 * 
	 * @param type
	 * @return
	 */
	public static IWAReader getExtractor(File fileObj) {

		String fileName = fileObj.getName();
		if (null == fileName || fileName.length() < 1) {
			return null;
		}

		if (fileName.endsWith(".arc") || fileName.endsWith(".arc.gz")) {
			return new ArcReaderWrapper(fileObj);
		} else if (fileName.endsWith(".warc") || fileName.endsWith(".warc.gz")) {
			return new WarcReaderWrapper(fileObj);
		} else {
			return new GeneralReader(fileObj);
		}
	}

}
