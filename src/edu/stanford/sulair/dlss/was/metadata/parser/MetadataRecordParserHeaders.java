package edu.stanford.sulair.dlss.was.metadata.parser;

import java.util.Map;

import edu.stanford.sulair.dlss.was.metadata.MetadataRepository;

/** 
 * Extracts the metadata record from the headers
 * The extracted metadata may be like:
 * <pre>{@code
 * WARC-Date=2014-01-19T22:37:40Z
 * }</pre>
 * And the configuration in config/extractor.yml may be like:
 * <pre>{@code
 * creationDate:
 *   type: Headers
 *   path: WARC-Date
 * }</pre>
 * The code snippet should be
 * <pre>{@code
 * Map map = new Map();
 * map.add("type", "Headers");
 * map.add("path", "WARC-Date");
 * mrpXML = MetadataRecordParserXML.new();
 * mrpXML.getValue(mdr, map);
 * }</pre>
 * The output is 
 * <pre>{@code
 *   2014-01-19T22:37:40Z
 * }</pre>
 * @author aalsum
 */
public class MetadataRecordParserHeaders extends MetadataRecordParser {

	@Override
	public Object getValue(MetadataRepository metadataRepository, Map recordMap) {

		String path = getPathValue(recordMap);

		if (path == null || path.length() < 1) {
			return null;
		}
		return metadataRepository.headerMap.get(path);
	}
}
