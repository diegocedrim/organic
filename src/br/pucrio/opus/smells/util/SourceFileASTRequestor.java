package br.pucrio.opus.smells.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FileASTRequestor;

import br.pucrio.opus.smells.resources.SourceFile;

public class SourceFileASTRequestor extends FileASTRequestor {
	
	private List<SourceFile> sourceFiles;
	
	public SourceFileASTRequestor() {
		sourceFiles = new ArrayList<>();
	}

	@Override
	public void acceptAST(String sourceFilePath, CompilationUnit ast) {
		this.sourceFiles.add(new SourceFile(new File(sourceFilePath), ast));
	}
	
	public List<SourceFile> getSourceFiles() {
		return sourceFiles;
	}
}
