package br.pucrio.opus.smells.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import br.pucrio.opus.smells.ast.ASTBuilder;

public class SourceFilesResolver {

	private List<SourceFile> sourceFiles;
	
	private JavaFilesFinder loader;

	public SourceFilesResolver(JavaFilesFinder loader) throws IOException {
		this.loader = loader;
		this.sourceFiles = new ArrayList<>();
		this.load();
	}
	
	private void load() throws IOException {
		List<File> sourceFiles = loader.findAll();
		ASTBuilder builder = new ASTBuilder(loader.getSourcePaths());
		for (File file : sourceFiles) {
			ASTParser parser = builder.create();
			String source = FileUtils.readFileToString(file);
			parser.setSource(source.toCharArray());
			CompilationUnit compilationUnit = (CompilationUnit)parser.createAST(null);
			this.sourceFiles.add(new SourceFile(file, compilationUnit));
		}
	}
	
	public List<SourceFile> getResolvedSourceFiles() {
		return sourceFiles;
	}
	
}
