package br.pucrio.opus.smells.resources;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import br.pucrio.opus.smells.ast.ASTBuilder;

public class SourceFilesLoader {

	private List<SourceFile> sourceFiles;
	
	private JavaFilesFinder loader;
	
	public SourceFilesLoader(JavaFilesFinder loader) throws IOException {
		this.loader = loader;
		this.sourceFiles = new ArrayList<>();
		this.load(loader.findAll());
	}
	
	public SourceFilesLoader(JavaFilesFinder loader, List<File> files) throws IOException {
		this.loader = loader;
		this.sourceFiles = new ArrayList<>();
		this.load(files);
	}
	
	public SourceFilesLoader(JavaFilesFinder loader, File file) throws IOException {
		this.loader = loader;
		this.sourceFiles = new ArrayList<>();
		this.load(Arrays.asList(file));
	}
	
	private void load(List<File> sourceFiles) throws IOException {
//		String[] files = new String[sourceFiles.size()];
//		
		String[] sourcePaths = loader.getSourcePaths();
		ASTBuilder builder = new ASTBuilder(sourcePaths);
//		ASTParser parser = builder.create();
//		SourceFileASTRequestor requestor = new SourceFileASTRequestor();
//		parser.createASTs(files, null, null,requestor, new ConsoleProgressMonitor());
//		this.sourceFiles = requestor.getSourceFiles();
		
		System.out.println("Loading files...");
		int total = sourceFiles.size();
		int current = 0;
		for (File file : sourceFiles) {
			ASTParser parser = builder.create();
			String source = FileUtils.readFileToString(file);
			parser.setSource(source.toCharArray());
			CompilationUnit compilationUnit = (CompilationUnit)parser.createAST(null);
			this.sourceFiles.add(new SourceFile(file, compilationUnit));
			current++;
			if (current % 10 == 0) {
				System.out.println(current + " of " + total);
			}
		}
		System.out.println("All files loaded");
	}
	
	public List<SourceFile> getLoadedSourceFiles() {
		return sourceFiles;
	}
	
}
