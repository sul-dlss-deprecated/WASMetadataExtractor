/*******************************************************************************
 * 2014, All rights reserved.
 *******************************************************************************/
package edu.stanford.sulair.dlss.was.metadata;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Represents the list of metadata fields that can be extracted
 * from the ARC/WARC files
 * 
 * @author aalsum
 */
public class MetadataRepository {

	public long size = 0;
	public String mimeType = "";
	public String fileType = "";
	public String fileName = "";
	public HashMap<String, String> headerMap = null;
	public String payload = "";
	public int recordCount = 0;
	public HashMap<String, Object> metadataMap = null;
	public String checksumMD5 = "";
	public String checksumSHA1 = "";

	public MetadataRepository() {
		super();
		headerMap = new HashMap<String, String>();
		metadataMap = new HashMap<String, Object>();
	}

	public MetadataRepository(String fileName) {
		this();
		this.fileName = fileName;
	}

	public MetadataRepository(File fileObj) {
		this();
		this.fileName = fileObj.getName();
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public HashMap<String, String> getHeaderMap() {
		return this.headerMap;
	}

	public void setHeaderMap(HashMap<String, String> newHeaderLine) {
		this.headerMap = newHeaderLine;
	}

	public String getPayload() {
		return this.payload;
	}

	public void setPayload(String newPayload) {
		this.payload = newPayload;
	}

	public int getRecordCount() {
		return this.recordCount;
	}

	public void setRecordCount(int newRecordCount) {
		this.recordCount = newRecordCount;
	}

	public HashMap<String, Object> getMetadataMap() {
		return this.metadataMap;
	}

	public void setMetadataMap(HashMap<String, Object> newMetadataMap) {
		this.metadataMap = newMetadataMap;
	}

	@Override
	public String toString() {
		String str = "MetadataRepository [\nfileName=" + fileName + ", \n"
				+ "fileType=" + fileType + ", \n" + "headerLine=" + headerMap
				+ ", \n" + "payload=" + payload + ", \n" + "recordCount="
				+ recordCount + ", \n" + "size= " + size + ", \n"
				+ "mimetype= " + mimeType + ", \n" + "checksum-MD5= "
				+ checksumMD5 + ", \n" + "checksum-SHA1= " + checksumSHA1
				+ ", \n" + "metadataMap=\n";
		Set<String> set = metadataMap.keySet();
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			str += "\t" + key + "," + metadataMap.get(key) + "\n";
		}
		return str;
	}

	public void setChecksumMD5(String checksumMD5) {
		this.checksumMD5 = checksumMD5;
	}

	public void setChecksumSHA1(String checksumSHA1) {
		this.checksumSHA1 = checksumSHA1;
	}
}
