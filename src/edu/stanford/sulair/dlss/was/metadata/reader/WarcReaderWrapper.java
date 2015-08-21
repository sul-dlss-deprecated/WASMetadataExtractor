/*******************************************************************************
 * 2014, All rights reserved.
 *******************************************************************************/
package edu.stanford.sulair.dlss.was.metadata.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.jwat.common.HeaderLine;
import org.jwat.warc.WarcReader;
import org.jwat.warc.WarcReaderFactory;
import org.jwat.warc.WarcRecord;

import edu.stanford.sulair.dlss.was.metadata.MetadataRepository;

/**
 * Description of WarcReaderWrapper.
 * 
 * @author aalsum
 */
public class WarcReaderWrapper extends WAReader {

	private static final String BASIC_FILE_TYPE = "WARC";
	public static String INFO_TYPE_STR = "warcinfo";

	public WarcReaderWrapper(String fileName) {
		super(fileName);
	}

	public WarcReaderWrapper(File fileObj) {
		super(fileObj);
	}

	@Override
	public MetadataRepository fillMetadataRepositoryFromFile()
			throws FileNotFoundException, IOException {
		// This function needs refactoring because it is similar to ARC
		// equivalent
		MetadataRepository metadataRepository = fillBasicInformationFromFile();
		metadataRepository.setFileType(BASIC_FILE_TYPE);

		InputStream fis = new FileInputStream(fileObj);
		WarcReader warcReder = WarcReaderFactory.getReader(fis);
		Iterator<WarcRecord> records = warcReder.iterator();

		int recordCount = 0;
		while (records.hasNext()) {
			WarcRecord wr = records.next();

			if (INFO_TYPE_STR.equalsIgnoreCase(wr.header.warcTypeStr)) {

				// Read the headers fields into the HeaderMap
				fillHeaderMap(wr, metadataRepository);

				// Read the payload in one string
				fillPayloadString(metadataRepository, wr);
			}
			recordCount++;
		}

		metadataRepository.setRecordCount(recordCount);

		if (metadataRepository.headerMap.size() == 0) {
			throw new IOException(fileObj
					+ " doesn't contain warcinfo headers.");
		}

		fis.close();
		return metadataRepository;
	}

	private void fillPayloadString(MetadataRepository metadataRepository,
			WarcRecord wr) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				wr.getPayloadContent()));
		StringBuffer payloadTempStr = new StringBuffer();
		while (reader.ready()) {
			payloadTempStr.append(reader.readLine() + "\n");
		}
		metadataRepository.setPayload(payloadTempStr.toString());
	}

	private void fillHeaderMap(WarcRecord wr,
			MetadataRepository metadataRepository) {
		for (HeaderLine s : wr.getHeaderList()) {
			metadataRepository.headerMap.put(s.name, s.value);
		}
	}
}
