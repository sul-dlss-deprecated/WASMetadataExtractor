
[![Build Status](https://travis-ci.org/sul-dlss/WASMetadataExtractor.svg?branch=master)](https://travis-ci.org/sul-dlss/WASMetadataExtractor)
[![Coverage Status](https://coveralls.io/repos/github/sul-dlss/WASMetadataExtractor/badge.svg?branch=master)](https://coveralls.io/github/sul-dlss/WASMetadataExtractor?branch=master)

# WAS Metadata Extractor

Java code used to extract metadata from web archiving ARC and WARC files, used by WAS crawl preassembly robot WF.

Deployment of the jar is part of [deployment tasks in `was_robot_suite`](https://github.com/sul-dlss/was_robot_suite/blob/master/config/deploy.rb#L40-L45), via an artifact created at [jenkinsqa](https://jenkinsqa.stanford.edu/job/WAS%20Metadata%20Extractor/). The deployed `was_robot_suite` houses the `WASMetadataExtractor.jar` in the `jar` directory.

## Build
```
mvn install
```

## Configuration
WAS Metadata Extractor depends on `config/extractor.yml` which describes the file types and the expected headers in each file.

### Format of extractor.yml
```
ARC:
 id:
  type: Headers
  path: "ARC-Filename"
```
- Line 1. File type
  - Line 2. properties name
    - Line 3. the type of how to get this value
    - Line 4. the location of this value based on the properties type

Other types can have different properties.

## Usage

List usage command
```
java -jar WASMetadataExtractor-0.0.2-SNAPSHOT-jar-with-dependencies.jar  -h
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

## Run Tests
```
mvn test -B
```
