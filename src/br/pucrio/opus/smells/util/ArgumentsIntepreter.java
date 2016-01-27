package br.pucrio.opus.smells.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.equinox.app.IApplicationContext;

import br.pucrio.opus.smells.resources.exceptions.NotSourceDirException;

public class ArgumentsIntepreter {
	
	private static final String SRC_OPT = "-src=";
	
	private static final String OUTPUT_OPT = "-output=";
	
	private IApplicationContext context;
	
	private List<String> sourcePaths;
	
	private File outputFile;

	public ArgumentsIntepreter(IApplicationContext context) {
		this.context = context;
		this.sourcePaths = new ArrayList<>();
		this.loadArgs();
	}
	
	private String extractValueFromArg(String arg) {
		StringTokenizer tokenizer = new StringTokenizer(arg, "=");
		tokenizer.nextToken();
		return tokenizer.nextToken();
	}
	
	private void addSourcePath(String arg) {
		String path = extractValueFromArg(arg);
		if (!new File(path).isDirectory()) {
			throw new NotSourceDirException("Not a valid source folder: " + path);
		}
		this.sourcePaths.add(path);
	}
	
	private void setOutputFile(String arg) {
		String file = extractValueFromArg(arg);
		this.outputFile = new File(file);
	}
	
	
	private void loadArgs() {
		Object argsObj = context.getArguments().get("application.args");
		String[] args = (String[])argsObj;
		for (String arg : args) {
			if (arg.contains(SRC_OPT)) {
				addSourcePath(arg);
			} else if (arg.contains(OUTPUT_OPT)) {
				setOutputFile(arg);
			}
		}
	}
	
	public List<String> getSourcePaths() {
		return this.sourcePaths;
	}
	
	public File getOutputFile() {
		return outputFile;
	}
}
