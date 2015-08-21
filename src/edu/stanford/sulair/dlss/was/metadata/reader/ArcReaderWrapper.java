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
import java.util.HashMap;
import java.util.Iterator;

import org.jwat.arc.ArcHeader;
import org.jwat.arc.ArcReader;
import org.jwat.arc.ArcReaderFactory;
import org.jwat.arc.ArcRecordBase;

import edu.stanford.sulair.dlss.was.metadata.MetadataRepository;

/**
 * Description of ArcReaderWrapper.
 * 
 * @author aalsum
 */
public class ArcReaderWrapper extends WAReader {

	private static final String BASIC_FILE_TYPE = "ARC";

	public ArcReaderWrapper(String fileName) {
		super(fileName);
	}

	public ArcReaderWrapper(File fileObj) {
		super(fileObj);
	}

	@Override
	public MetadataRepository fillMetadataRepositoryFromFile()
			throws FileNotFoundException, IOException {

		MetadataRepository metadataRepository = fillBasicInformationFromFile();
		metadataRepository.setFileType(BASIC_FILE_TYPE);

		InputStream fis = new FileInputStream(fileObj);
		ArcReader arcReader = ArcReaderFactory.getReader(fis);
		Iterator<ArcRecordBase> records = arcReader.iterator();

		int recordCount = 0;
		while (records.hasNext()) {
			ArcRecordBase ar = records.next();

			if (ArcRecordBase.RT_VERSION_BLOCK == ar.recordType) {

				// Transfer the header record into headerMap
				fillHeaderMap(ar, metadataRepository);

				// Read the payload in one string
				fillPayloadString(metadataRepository, ar);
			}
			recordCount++;
		}

		metadataRepository.setRecordCount(recordCount);

		if (metadataRepository.headerMap.size() == 0) {
			throw new IOException(fileObj.getName()
					+ " doesn't contain arcinfo headers.");
		}
		fis.close();
		return metadataRepository;
	}

	private void fillPayloadString(MetadataRepository metadataRepository,
			ArcRecordBase ar) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				ar.getPayloadContent()));
		StringBuffer payloadTempStr = new StringBuffer();
		while (reader.ready()) {
			payloadTempStr.append(reader.readLine() + "\n");
		}
		metadataRepository.setPayload(payloadTempStr.toString());
	}

	private void fillHeaderMap(ArcRecordBase ar,
			MetadataRepository metadataRepository) {

		HashMap<String, String> headerMap = metadataRepository.headerMap;
		ArcHeader header = ar.header;
		if (header.urlStr != null) {
			headerMap.put("url", header.urlStr);

			if (header.urlStr.startsWith("filedesc://")) {
				headerMap.put("ARC-Filename", header.urlStr.substring(11));
			}
		}
		if (header.ipAddressStr != null) {
			headerMap.put("ipAddress", header.ipAddressStr);
		}
		if (header.archiveDateStr != null) {
			headerMap.put("archiveDate", header.archiveDateStr);
		}
		if (header.contentTypeStr != null) {
			headerMap.put("contentType", header.contentTypeStr);
		}
		if (header.resultCodeStr != null) {
			headerMap.put("resultCode", header.resultCodeStr);
		}
		if (header.checksumStr != null) {
			headerMap.put("checksum", header.checksumStr);
		}
		if (header.locationStr != null) {
			headerMap.put("location", header.locationStr);
		}
		if (header.offsetStr != null) {
			headerMap.put("offset", header.offsetStr);
		}
		if (header.filenameStr != null) {
			headerMap.put("fileName", header.filenameStr);
		}
		if (header.archiveLengthStr != null) {
			headerMap.put("length", header.archiveLengthStr);
		}
	}
}
