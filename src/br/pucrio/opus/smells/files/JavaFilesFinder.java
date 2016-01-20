package br.pucrio.opus.smells.files;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.equinox.app.IApplicationContext;

public class JavaFilesFinder {

	private static final String[] EXTENSIONS = {"java"};
	
	public static final String[] ENCODINGS = {"utf-8"};
	
	private List<String> directories;
	
	private List<String> getSourcePathsFromContext(IApplicationContext context) {
		List<String> sourcePaths = new ArrayList<>();
		Object argsObj = context.getArguments().get("application.args");
		String[] args = (String[])argsObj;
		for (String arg : args) {
			if (!new File(arg).isDirectory()) {
				throw new NotSourceDirException("Not a valid source folder: " + arg);
			}
			sourcePaths.add(arg);
		}
		return sourcePaths;
	}
	
	public JavaFilesFinder() {
		this.directories = new ArrayList<>();
	}
	
	public JavaFilesFinder(IApplicationContext context) {
		this.directories = this.getSourcePathsFromContext(context);
	}
	
	public void addDir(String directory) {
		this.directories.add(directory);
	}
	
	public String[] getSourcePaths() {
		String[] sourcePathsArray = new String[this.directories.size()];
		this.directories.toArray(sourcePathsArray);
		return sourcePathsArray;
	}
	
	public List<File> findAll() {
		List<File> files = new ArrayList<>();
		for (String dir : this.directories) {
			Collection<File> tempFiles = FileUtils.listFiles(new File(dir), EXTENSIONS, true);
			files.addAll(tempFiles);
		}
		return files;
	}
	
}
