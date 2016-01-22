package br.pucrio.opus.tests.visitor;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pucrio.opus.smells.ast.ASTBuilder;
import br.pucrio.opus.smells.visitors.TypeDeclarationCollector;

public class TypeDeclarationCollectorTest {
	
	private CompilationUnit compilationUnit;

	@Before
	public void setUp() throws IOException{
		File file = new File("test");
		String[] srcPaths = new String[]{file.getAbsolutePath()};
		ASTBuilder builder = new ASTBuilder(srcPaths);
		ASTParser parser = builder.create();
		
		String source = FileUtils.readFileToString(new File("test/br/pucrio/opus/tests/dummy/AnonymousClass.java"));
		parser.setSource(source.toCharArray());
		this.compilationUnit = (CompilationUnit)parser.createAST(null);
	}
	
	@Test
	public void typeDeclCountTest() {
		TypeDeclarationCollector visitor = new TypeDeclarationCollector();
		this.compilationUnit.accept(visitor);
		Assert.assertEquals(3, visitor.getNodesCollected().size());
	}
}
