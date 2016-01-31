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
		ASTBuilder builder = new ASTBuilder(loader.getSourcePaths());
		for (File file : sourceFiles) {
			ASTParser parser = builder.create();
			String source = FileUtils.readFileToString(file);
			parser.setSource(source.toCharArray());
			CompilationUnit compilationUnit = (CompilationUnit)parser.createAST(null);
			this.sourceFiles.add(new SourceFile(file, compilationUnit));
		}
	}
	
	public List<SourceFile> getLoadedSourceFiles() {
		return sourceFiles;
	}
	
}
