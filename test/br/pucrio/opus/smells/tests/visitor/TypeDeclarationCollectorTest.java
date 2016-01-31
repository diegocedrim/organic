package br.pucrio.opus.smells.tests.visitor;

import java.io.File;
import java.io.IOException;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pucrio.opus.smells.ast.visitors.TypeDeclarationCollector;
import br.pucrio.opus.smells.tests.util.CompilationUnitLoader;

public class TypeDeclarationCollectorTest {
	
	private CompilationUnit compilationUnit;

	@Before
	public void setUp() throws IOException{
		File file = new File("test/br/pucrio/opus/smells/tests/dummy/AnonymousClass.java");
		this.compilationUnit = CompilationUnitLoader.getCompilationUnit(file);
	}
	
	@Test
	public void typeDeclCountTest() {
		TypeDeclarationCollector visitor = new TypeDeclarationCollector();
		this.compilationUnit.accept(visitor);
		Assert.assertEquals(2, visitor.getNodesCollected().size());
	}
}
