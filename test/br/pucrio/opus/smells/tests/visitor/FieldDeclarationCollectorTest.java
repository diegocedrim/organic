package br.pucrio.opus.smells.tests.visitor;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pucrio.opus.smells.ast.visitors.FieldDeclarationCollector;
import br.pucrio.opus.smells.tests.util.CompilationUnitLoader;

public class FieldDeclarationCollectorTest {
	
	private CompilationUnit compilationUnit;

	@Before
	public void setUp() throws IOException{
		File file = new File("test/br/pucrio/opus/smells/tests/dummy/FieldDeclaration.java");
		this.compilationUnit = CompilationUnitLoader.getCompilationUnit(file);
	}
	
	@Test
	public void allFieldsCollectionCountTest() {
		FieldDeclarationCollector visitor = new FieldDeclarationCollector();
		this.compilationUnit.accept(visitor);
		List<FieldDeclaration> collectedFields = visitor.getNodesCollected();
		Assert.assertEquals(6, collectedFields.size());
	}
}
