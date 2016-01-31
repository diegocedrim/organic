package br.pucrio.opus.smells.tests.operations;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.junit.Assert;
import org.junit.Test;

import br.pucrio.opus.smells.ast.visitors.TypeDeclarationCollector;
import br.pucrio.opus.smells.tests.util.CompilationUnitLoader;

public class TypeDeclarationOperationsTest {

	@Test
	public void fqnRetrievalTest() throws IOException {
		File file = new File("test/br/pucrio/opus/smells/tests/dummy/CC.java");
		CompilationUnit compilationUnit = CompilationUnitLoader.getCompilationUnit(file);
		TypeDeclarationCollector visitor = new TypeDeclarationCollector();
		compilationUnit.accept(visitor);
		List<TypeDeclaration> types = visitor.getNodesCollected();
		Assert.assertEquals(1, types.size());
		TypeDeclaration decl = (TypeDeclaration)types.get(0);
		Assert.assertEquals("br.pucrio.opus.smells.tests.dummy.CC", decl.resolveBinding().getQualifiedName());
	}
	
	@Test
	public void fqnAnonymousRetrievalTest() throws IOException {
		File file = new File("test/br/pucrio/opus/smells/tests/dummy/AnonymousClass.java");
		CompilationUnit compilationUnit = CompilationUnitLoader.getCompilationUnit(file);
		TypeDeclarationCollector visitor = new TypeDeclarationCollector();
		compilationUnit.accept(visitor);
		List<TypeDeclaration> types = visitor.getNodesCollected();
		Assert.assertEquals(2, types.size());
		TypeDeclaration decl = (TypeDeclaration)types.get(0);
		Assert.assertEquals("br.pucrio.opus.smells.tests.dummy.AnonymousClass", decl.resolveBinding().getQualifiedName());
	}
}
