package br.pucrio.opus.tests.visitor;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pucrio.opus.smells.ast.ASTBuilder;
import br.pucrio.opus.smells.visitors.FieldDeclarationCollector;

public class FieldDeclarationCollectorTest {
	
	private CompilationUnit compilationUnit;

	@Before
	public void setUp() throws IOException{
		File file = new File("test");
		String[] srcPaths = new String[]{file.getAbsolutePath()};
		ASTBuilder builder = new ASTBuilder(srcPaths);
		ASTParser parser = builder.create();
		
		String source = FileUtils.readFileToString(new File("test/br/pucrio/opus/tests/dummy/FieldDeclaration.java"));
		parser.setSource(source.toCharArray());
		this.compilationUnit = (CompilationUnit)parser.createAST(null);
	}
	
	@Test
	public void allFieldsCollectionCountTest() {
		FieldDeclarationCollector visitor = new FieldDeclarationCollector();
		this.compilationUnit.accept(visitor);
		List<FieldDeclaration> collectedFields = visitor.getNodesCollected();
		Assert.assertEquals(6, collectedFields.size());
	}
}
