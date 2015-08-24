# WAS Metadata Extarctor
This is a jar file that is used to extract a light set of metadata from web archiving ARC and WARC files.

## Build
```mvn install```

## Configuration
WAS Metadata Extractor depends on yml file that describes the file types and the expected headers in each file. 

### Format
```
ARC:
 id:
  type: Headers
  path: "ARC-Filename"
```
Line 1. File type
Line 2. properties name
Line 3. the type of how to get this value
Line 4. the location of this value based on the proprites type

Other types can have different properties.

## Usage

List usage command
```
java -jar WASMetadataExtractor-0.0.1-SNAPSHOT-jar-with-dependencies.jar  -h
usage: WASMetadataExtractor
 -c,--config <file>                 YAML configuration file to extract the
                                    metadata.
    --collectionId <collectionId>   Collection id as it should appear in
                                    metadata files. It shouldn't have
                                    space.
    --crawlId <crawlId>             The crawl id as it should appear in
                                    metadata file.
 -d,--inputdir <directory>          The direcotyry of ARC/WARC file to be
                                    processed.
 -f,--format <XML|TXT>              The output format, TXT is the default.
 -h,--help                          Display help
 -i,--inputfile <file>              An input file that has a list of
                                    ARC/WARC files to be processed.
 -o,--outputFile <file>             The output file.
```

