package edu.stanford.sulair.dlss.was.metadata.parser;

import java.util.Map;

import edu.stanford.sulair.dlss.was.metadata.MetadataRepository;

/** 
 * Extracts Metadata record in the name-value format.
 * The extracted metadata may be like:
 * <pre>{@code
 * format: WARC File Format 1.0
 * }</pre>
 * And the configuration in config/extractor.yml may be like:
 * <pre>{@code
 *  datatype:
 *    type: NameValue
 *    recordSeparator: "\n"
 *    fieldSeparator: ":" 
 *    path: format
 * }</pre>
 * The code snippet should be
 * <pre>{@code
 * Map map = new Map();
 * map.add("type", "NameValue");
 * map.add("recordSeparator", "\n");
 * map.add("fieldSeparator", ":");
 * map.add("path", "format");
 * mrpXML = MetadataRecordParserXML.new();
 * mrpXML.getValue(mdr, map);
 * }</pre>
 * The output is 
 * <pre>{@code
 *   WARC File Format 1.0
 * }</pre>
 * @author aalsum
 */
public class MetadataRecordParserNameValue extends MetadataRecordParser {

	@Override
	public Object getValue(MetadataRepository metadataRepository, Map recordMap) {

		String path = getPathValue(recordMap);
		if (path == null || path.length() < 1) {
			return null;
		}

		String headersPayload = metadataRepository.getPayload();
		String[] payloadLines = headersPayload.split((String) recordMap
				.get("recordSeparator"));

		for (String line : payloadLines) {
			if (line.indexOf(path) > -1) {
				int index = line.indexOf(
						(String) recordMap.get("fieldSeparator"),
						path.length() - 1);
				if (index > 0) {
					return line.substring(index + 1);
				}
			}
		}
		return null;
	}
}
