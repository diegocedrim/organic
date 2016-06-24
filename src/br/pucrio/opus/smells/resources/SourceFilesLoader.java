package br.pucrio.opus.smells.resources;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTParser;

import br.pucrio.opus.smells.ast.ASTBuilder;
import br.pucrio.opus.smells.util.ConsoleProgressMonitor;
import br.pucrio.opus.smells.util.SourceFileASTRequestor;

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
		String[] files = new String[sourceFiles.size()];
		for (int i = 0; i < sourceFiles.size(); i++) {
			files[i] = sourceFiles.get(i).getAbsolutePath();
		}

		String[] sourcePaths = loader.getSourcePaths();
		ASTBuilder builder = new ASTBuilder(sourcePaths);
		ASTParser parser = builder.create();
		SourceFileASTRequestor requestor = new SourceFileASTRequestor();
		parser.createASTs(files, null, new String[] {},requestor, new ConsoleProgressMonitor());
		this.sourceFiles = requestor.getSourceFiles();		
	}
	
	public List<SourceFile> getLoadedSourceFiles() {
		return sourceFiles;
	}
	
}
