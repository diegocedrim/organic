package br.pucrio.opus.smells.tests.visitor;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pucrio.opus.smells.ast.visitors.MethodCollector;
import br.pucrio.opus.smells.ast.visitors.TypeDeclarationCollector;
import br.pucrio.opus.smells.tests.util.CompilationUnitLoader;

public class MethodCollectorTest {
	
	private CompilationUnit compilationUnit;

	@Before
	public void setUp() throws IOException {
		File file = new File("test/br/pucrio/opus/smells/tests/dummy/AnonymousClass.java");
		this.compilationUnit = CompilationUnitLoader.getCompilationUnit(file);
	}
	
	@Test
	public void methodsCountTest() {
		TypeDeclarationCollector typeCollector = new TypeDeclarationCollector();
		this.compilationUnit.accept(typeCollector);
		List<TypeDeclaration> types = typeCollector.getNodesCollected();
		
		MethodCollector visitor = new MethodCollector();
		types.get(0).accept(visitor);
		List<MethodDeclaration> collectedMethods = visitor.getNodesCollected();
		Assert.assertEquals(5, collectedMethods.size());
		
		visitor = new MethodCollector();
		types.get(1).accept(visitor);
		collectedMethods = visitor.getNodesCollected();
		Assert.assertEquals(2, collectedMethods.size());
	}
}
