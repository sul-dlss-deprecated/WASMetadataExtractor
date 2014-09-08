import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

import org.jwat.arc.ArcReader;
import org.jwat.arc.ArcReaderFactory;
import org.jwat.arc.ArcRecordBase;
import org.jwat.common.HeaderLine;
import org.jwat.warc.WarcReader;
import org.jwat.warc.WarcReaderFactory;
import org.jwat.warc.WarcRecord;
import org.yaml.snakeyaml.Yaml;

public class firstTry {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		readConfig();
		//arcTest(); 

	}

	private static void readConfig() throws IOException {

		Yaml yaml = new Yaml();
		Map map = (Map) yaml.load(new FileInputStream("config/extractor.yml"));

		for (Object s : map.keySet()) {
			System.out.println(s);
		}
	}

	private static void warcTest() throws IOException, FileNotFoundException {
		WarcReader warcReder = WarcReaderFactory.getReader(new FileInputStream(
				"data/WARC-Test.warc.gz"));

		System.out.println(warcReder.isCompressed());

		Iterator<WarcRecord> records = warcReder.iterator();
		while (records.hasNext()) {
			WarcRecord wr = records.next();

			for (HeaderLine s : wr.getHeaderList()) {
				System.out.println(s.name + "\t" + s.value);
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					wr.getPayloadContent()));
			while (reader.ready()) {
				System.out.println(reader.readLine());
			}

			break;

		}
	}

	private static void arcTest() throws IOException, FileNotFoundException {
		ArcReader arcReader = ArcReaderFactory.getReader(new FileInputStream(
				"data/ARC-Test.arc.gz"));

		System.out.println(arcReader.isCompressed());

		Iterator<ArcRecordBase> records = arcReader.iterator();

		while (records.hasNext()) {
			ArcRecordBase wr = records.next();

			if (ArcRecordBase.RT_VERSION_BLOCK == wr.recordType) {
				System.out.println(wr.header);
				System.out.println(wr.getVersion());
				//	ArcHeader arcHeader = wr.header;
				// 	
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(wr.getPayloadContent()));
				while (reader.ready()) {
					System.out.println(reader.readLine());
				}
			}

			//	 break;

		}
	}
}