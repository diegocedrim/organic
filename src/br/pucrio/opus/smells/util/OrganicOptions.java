package br.pucrio.opus.smells.util;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class OrganicOptions {
	
	public static final String SOURCE_FOLDER = "source-folder";
	public static final String SMELLS_FILE = "smells-file";
	public static final String AGGLOMERATIONS_FILE = "agglomeration-file";
	public static final String IGNORE_TESTS = "ignore-tests";
	public static final String ONLY_SMELLY = "only-smelly";

	private Options options;
	
	private CommandLine line;
	
	private static OrganicOptions singleton;
	
	static {
		singleton = new OrganicOptions();
	}
	
	private OrganicOptions() {
		options = new Options();
		createOptions();
	}
	
	public static OrganicOptions getInstance() {
		return singleton;
	}
	
	public boolean shouldCollectAgglomerations() {
		return line.hasOption(AGGLOMERATIONS_FILE);
	}
	
	public boolean shouldFilterSmelly() {
		return line.hasOption(ONLY_SMELLY);
	}
	
	private void createOptions() {
		Option smellsFile = Option.builder("sf")
				.longOpt(SMELLS_FILE)
				.desc("File where all the smells will be saved in JSON format")
				.hasArg()
				.argName("file")
				.required()
				.build();
		
		Option aggFile = Option.builder("af")
				.longOpt(AGGLOMERATIONS_FILE)
				.desc("File where all the agglomeration will be saved in JSON format. "
						+ "If this parameter is not used, then no agglomeration will be detected")
				.hasArg()
				.argName("file")
				.build();
		
		Option sourceFolder = Option.builder("src")
				.longOpt(SOURCE_FOLDER)
				.desc("Folder containing all Java files to be analyzed")
				.required()
				.hasArg()
				.argName("folder")
				.build();
		
		Option ignoreTests = Option.builder("it")
				.longOpt(IGNORE_TESTS)
				.desc("Ignore all the test classes")
				.build();
		
		Option ignoreNonSmelly = Option.builder("os")
				.longOpt(ONLY_SMELLY)
				.desc("If this flag is used, only classes/methods containing code smells will be part of the output")
				.build();
	
		options.addOption(smellsFile);
		options.addOption(aggFile);
		options.addOption(sourceFolder);
		options.addOption(ignoreTests);
		options.addOption(ignoreNonSmelly);
	}
	
	public String getValue(String key) {
		return this.line.getOptionValue(key);
	}
	
	public void parse(String[] args) throws ParseException {
		CommandLineParser parser = new DefaultParser();
		this.line = parser.parse(getOptions(), args);
	}
	
	public void printHelp() {
		HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("organic", new OrganicOptions().getOptions() );
	}
	
	public Options getOptions() {
		return options;
	}
}
