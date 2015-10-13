package edu.stanford.sulair.dlss.was.metadata.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		}
		return metadataRepository;
	}

	/**
	 * Computes the MD5 hash for the file defined by file input stream
	 * @param fis input stream for the file
	 * @return
	 */
	private String computeChecksumMD5() throws java.io.IOException, NoSuchAlgorithmException{
		FileInputStream fis = new FileInputStream(fileObj);
		MessageDigest digest = MessageDigest.getInstance("MD5");
		
		byte[] bytesBuffer = new byte[1024];
		int bytesRead = -1;
		while ((bytesRead = fis.read(bytesBuffer)) != -1) {
			digest.update(bytesBuffer, 0, bytesRead);
		}
		fis.close();
		return convertByteArrayToHexString(digest.digest());
	}
	
	
	/**
	 * Converts the bytearray to String
	 * @param arrayBytes
	 * @return
	 */
	private static String convertByteArrayToHexString(byte[] arrayBytes) {
	    StringBuffer stringBuffer = new StringBuffer();
	    for (int i = 0; i < arrayBytes.length; i++) {
	        stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
	                .substring(1));
	    }
	    return stringBuffer.toString().toLowerCase();
	}

	/**
	 * @return String the checksum in MD5
	 * @throws IOException
	 * @throws NoSuchAlgorithmException 
	 */
	protected String computeChecksumSHA1() throws IOException, NoSuchAlgorithmException {
		FileInputStream fis = new FileInputStream(fileObj);
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		
		byte[] bytesBuffer = new byte[1024];
		int bytesRead = -1;
		while ((bytesRead = fis.read(bytesBuffer)) != -1) {
			digest.update(bytesBuffer, 0, bytesRead);
		}
		fis.close();
		return convertByteArrayToHexString(digest.digest());
	}
}
