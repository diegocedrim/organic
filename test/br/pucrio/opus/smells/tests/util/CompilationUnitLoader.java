package br.pucrio.opus.smells.tests.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import br.pucrio.opus.smells.ast.ASTBuilder;

public class CompilationUnitLoader {

	public static CompilationUnit getCompilationUnit(File target) throws IOException {
		File file = new File("test");
		String[] srcPaths = new String[]{file.getAbsolutePath()};
		ASTBuilder builder = new ASTBuilder(srcPaths);
		ASTParser parser = builder.create();
		
		String source = FileUtils.readFileToString(target);
		parser.setSource(source.toCharArray());
		return (CompilationUnit)parser.createAST(null);
	}
	
	public static CompilationUnit getCompilationUnitDummyClass(String name) throws IOException {
		String path = "test/br/pucrio/opus/smells/tests/dummy/";
		return getCompilationUnit(new File(path + name));
	}
}
