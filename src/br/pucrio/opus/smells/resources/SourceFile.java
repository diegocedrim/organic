package br.pucrio.opus.smells.resources;

import java.io.File;

import org.eclipse.jdt.core.dom.CompilationUnit;

public class SourceFile {

	private File file;
	
	private CompilationUnit compilationUnit;
	
	public SourceFile(File file, CompilationUnit compilationUnit) {
		this.file = file;
		this.compilationUnit = compilationUnit;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public CompilationUnit getCompilationUnit() {
		return compilationUnit;
	}

}
