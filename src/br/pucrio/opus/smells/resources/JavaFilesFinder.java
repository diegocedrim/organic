package br.pucrio.opus.smells.resources;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class JavaFilesFinder {

	private static final String[] EXTENSIONS = {"java"};
	
	private List<String> directories;
	
	public JavaFilesFinder() {
		this.directories = new ArrayList<>();
	}
	
	public JavaFilesFinder(String sourcePath) {
		this.directories = Arrays.asList(sourcePath);
	}
	
	public JavaFilesFinder(List<String> sourcePaths) {
		this.directories = sourcePaths;
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
