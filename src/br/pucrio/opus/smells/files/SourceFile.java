package br.pucrio.opus.smells.files;

import java.io.File;

import org.eclipse.jdt.core.dom.CompilationUnit;

import br.pucrio.opus.smells.metrics.ClassMetrics;

public class SourceFile {

	private File file;
	
	private CompilationUnit compilationUnit;
	
	private ClassMetrics classMetrics;

	public SourceFile(File file, CompilationUnit compilationUnit) {
		this.file = file;
		this.compilationUnit = compilationUnit;
		this.classMetrics = new ClassMetrics();
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

	public void setCompilationUnit(CompilationUnit compilationUnit) {
		this.compilationUnit = compilationUnit;
	}
	
	public ClassMetrics getClassMetrics() {
		return classMetrics;
	}
}
