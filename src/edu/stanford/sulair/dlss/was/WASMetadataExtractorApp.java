package edu.stanford.sulair.dlss.was;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import edu.stanford.sulair.dlss.was.metadata.MetadataExtractorEngine;
import edu.stanford.sulair.dlss.was.metadata.writer.IMetadataWriter;
import edu.stanford.sulair.dlss.was.metadata.writer.MetadataWriterDefault;
import edu.stanford.sulair.dlss.was.metadata.writer.MetadataWriterXML;




public class WASMetadataExtractorApp {
	
	
	private static Options    	options;
    private static CommandLine	cmdLine;
	
	/** version identifier. */
	public static final String VERSION = "1.0.0";

	public static final String 	applicationName 	= "WASMetadataExtractor";
	private static final int EXIT_CODE_SUCCESS = 0;
	private static final int EXIT_CODE_FAIL = -1;

	private static String wasInputFileList 	= "";
	private static String wasInputDir  	= "";
	private static String wasOutputFile;
	private static String wasOutputFormat = "TXT";
	private static String wasConfigFile = "";
	private static String collectionId = "";
	private static String crawlId = "";	
	
	public static void main(String[] args) {
		
		
		parseCommandLineOptions(args);

		ArrayList<File> fileList = new ArrayList<File>();
		
		if(wasInputFileList.length()>0){
			//Read the input file and call the 
			try {
				fileList =  readFileListFromFile(wasInputFileList);
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("There is a problem in reading "+wasInputFileList);
				return;
			}
			
		} else if(wasInputDir.length() > 0) {
	
			fileList =  readFileListFromDirectory(wasInputDir);
		
		} else if(args.length >1){
			String wasInputFile = args[args.length-1];
			File wasInputFileObj = new File(wasInputFile);
			fileList.add(wasInputFileObj);
		}

		PrintStream out = null;
		if(wasOutputFile != null && wasOutputFile.length()>0){
			try {
			    out = new PrintStream(wasOutputFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit( EXIT_CODE_FAIL );			
			}
		} else {
			out = System.out;
		}
		
		IMetadataWriter writer = null;
		if("XML".equalsIgnoreCase(wasOutputFormat)){
			 writer = new MetadataWriterXML(out);
		} else if(wasOutputFormat.length() == 0 || "TXT".equalsIgnoreCase(wasOutputFormat)){
			 writer = new MetadataWriterDefault(out);
		} else{
			System.out.println(wasOutputFormat+" is undefined format.");
			usage(options);
			System.exit( EXIT_CODE_FAIL );	
		}
		
		MetadataExtractorEngine engine =  null;
		if(wasConfigFile != null && wasConfigFile.length()>0){
			engine = new MetadataExtractorEngine(fileList,wasConfigFile, writer);
		} else {
			engine = new MetadataExtractorEngine(fileList,writer);
		}
		engine.runExtractor();
		engine.getWriter().print(collectionId, crawlId);
		
	}

	private static ArrayList<File> readFileListFromDirectory(String directoryName) {
		

		ArrayList<File> fileList = new ArrayList<File>();

		if(directoryName == null || directoryName.length()<1){
			return fileList;
		}
		File directoryObj = new File(directoryName);

		File[] listOfFiles = directoryObj.listFiles();

		if(listOfFiles == null){
			return fileList;
		}

		for (File file : listOfFiles) {

			if (file.isDirectory()) {
				fileList.addAll(readFileListFromDirectory(file.getPath()+file.getName()));
			} else {
				fileList.add(file);
			}
		}
		return fileList;
	}

	private static ArrayList<File> readFileListFromFile(String listFileName) throws IOException {

		ArrayList<File> fileList = new ArrayList<File>();

		if(listFileName == null || listFileName.length() <1){
			return fileList;
		}
		
		File inputFileObj = new File(listFileName);
		
		BufferedReader reader = new BufferedReader(new FileReader(inputFileObj));
		
		while(reader.ready()){
			String line = reader.readLine();
			String[] fields = line.split("\t");
			
			// File name should be the first record
			String fileName = fields[0]; 
			
			fileList.add(new File(fileName));
		}
		
		reader.close();
		return fileList;
	}

	private static void parseCommandLineOptions( String[] args ) {
        addCommandLineOptions();

        CommandLineParser parser  = new GnuParser();
        
        try {
            cmdLine = parser.parse( options, args );
        } catch(ParseException e){
            System.err.println( "Command line parsing failed.  Reason:" + e.getMessage() );
        }

        // If the user isn't asking for usage help, validate the given command line options.
        if( ! cmdLine.hasOption( "help" ) )
        {
	        if ( cmdLine.hasOption( "i") ) {
	        	wasInputFileList = cmdLine.getOptionValue( "i" );
	        	System.out.println("Archive File List: " + wasInputFileList);
	        }
	        if ( cmdLine.hasOption( "d") ) {
	        	wasInputDir = cmdLine.getOptionValue( "d" );
	        	System.out.println("Archive Directory: " + wasInputDir);
	        }
	        if ( cmdLine.hasOption( "o") ) {
	        	wasOutputFile = cmdLine.getOptionValue( "o" );
	        	System.out.println("Output file: " + wasOutputFile);
	        }
	        if ( cmdLine.hasOption( "f") ) {
	        	wasOutputFormat  = cmdLine.getOptionValue( "f" );
	        	System.out.println("Output format: " + wasOutputFormat);
	        }
	        if ( cmdLine.hasOption( "c") ) {
	        	wasConfigFile   = cmdLine.getOptionValue( "c" );
	        	System.out.println("Config file: " + wasConfigFile);
	        } 
	        if ( cmdLine.hasOption( "collectionId") ) {
	        	collectionId   = cmdLine.getOptionValue( "collectionId" );
	        	System.out.println("Config file: " + wasConfigFile);
	        } 
	        if ( cmdLine.hasOption( "crawlId") ) {
	        	crawlId   = cmdLine.getOptionValue( "crawlId" );
	        	System.out.println("Config file: " + wasConfigFile);
	        } 
        } else {
	        	usage( options );
	        	System.exit( EXIT_CODE_SUCCESS );
	        }
	}
	
	private static void addCommandLineOptions() {
		options = new Options();

        options.addOption( "h","help",			false, "Display help"     );
        options.addOption(
        		OptionBuilder.withArgName( "file" )
                .withLongOpt( "inputfile" )
                .hasArg()
                .withDescription( "An input file that has a list of ARC/WARC files to be processed." )
                .create("i")
        );
        options.addOption(
        		OptionBuilder.withArgName( "directory" )
                .hasArg()
                .withDescription( "The direcotyry of ARC/WARC file to be processed." )
                .withLongOpt( "inputdir" )
                .create("d")
        );
        
        options.addOption(
        		OptionBuilder.withArgName( "file" )
                .hasArg()
                .withDescription( "The output file." )
                .withLongOpt( "outputFile" )
                .create("o")
        );
        	
        options.addOption(
        		OptionBuilder.withArgName( "XML|TXT" )
                .hasArg()
                .withDescription( "The output format, TXT is the default." )
                .withLongOpt( "format" )
                .create("f")
        );
        options.addOption(
        		OptionBuilder.withArgName( "file" )
                .hasArg()
                .withDescription( "YAML configuration file to extract the metadata." )
                .withLongOpt( "config" )
                .create("c")
        );
        options.addOption(
        		OptionBuilder.withArgName( "collectionId" )
                .hasArg()
                .withDescription( "Collection id as it should appear in metadata files. It shouldn't have space." )
                .withLongOpt( "collectionId" )
                .create()
        );
        options.addOption(
        		OptionBuilder.withArgName( "crawlId" )
                .hasArg()
                .withDescription( "The crawl id as it should appear in metadata file." )
                .withLongOpt( "crawlId" )
                .create()
        );
	}

	private static void usage( Options options ) {
        HelpFormatter hf = new HelpFormatter();
        hf.printHelp( applicationName, options );
    }
}
