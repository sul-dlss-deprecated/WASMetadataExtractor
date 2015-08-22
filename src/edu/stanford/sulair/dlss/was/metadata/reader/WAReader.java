package edu.stanford.sulair.dlss.was.metadata.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import edu.stanford.sulair.dlss.was.metadata.MetadataRepository;

/** An abstract class that provides the basic functionalities for
 * the child classes.
 * @author aalsum
 *
 */
abstract public class WAReader implements IWAReader {

	@Override
	public abstract MetadataRepository fillMetadataRepositoryFromFile()
			throws FileNotFoundException, IOException;

	public File fileObj = null;

	public WAReader(String fileName) {
		super();
		this.fileObj = new File(fileName);
	}

	public WAReader(File fileObj) {
		super();
		this.fileObj = fileObj;
	}

	/** Fills the basic information about the file such as: the size, 
	 * mimeType, and checksum.
	 * @return MetadataRepository object with the basic information
	 */
	protected MetadataRepository fillBasicInformationFromFile() {
		MetadataRepository metadataRepository = new MetadataRepository(fileObj);
		metadataRepository.setSize(fileObj.length());
		metadataRepository.setMimeType((new MimetypesFileTypeMap()
				.getContentType(fileObj)).toString());
		try {
			metadataRepository.setChecksumMD5(computeChecksumMD5());
			metadataRepository.setChecksumSHA1(computeChecksumSHA1());
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return metadataRepository;
	}

	/**
	 * @return String the checksum in MD5
	 * @throws IOException
	 */
	protected String computeChecksumMD5() throws IOException {
		FileInputStream fis = new FileInputStream(fileObj);
		String md5Hash = DigestUtils.md5Hex(IOUtils.toByteArray(fis))
				.toLowerCase();
		fis.close();

		return md5Hash;
	}

	/**
	 * @return String the checksum in MD5
	 * @throws IOException
	 */
	protected String computeChecksumSHA1() throws IOException {
		FileInputStream fis = new FileInputStream(fileObj);
		String sha1Hash = DigestUtils.sha1Hex(IOUtils.toByteArray(fis))
				.toLowerCase();
		fis.close();

		return sha1Hash;
	}
}
