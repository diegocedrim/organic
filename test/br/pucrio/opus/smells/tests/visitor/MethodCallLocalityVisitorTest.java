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

import br.pucrio.opus.smells.ast.visitors.MethodCallLocalityVisitor;
import br.pucrio.opus.smells.ast.visitors.MethodCollector;
import br.pucrio.opus.smells.ast.visitors.TypeDeclarationCollector;
import br.pucrio.opus.smells.tests.util.CompilationUnitLoader;

public class MethodCallLocalityVisitorTest {

	private CompilationUnit compilationUnit;

	private List<MethodDeclaration> methods;

	private TypeDeclaration type;

	private MethodDeclaration findMethodByName(String name) {
		for (MethodDeclaration decls : methods) {
			if (decls.getName().toString().equals(name)) {
				return decls;
			}
		}
		return null;
	}

	@Before
	public void setUp() throws IOException{
		File file = new File("test/br/pucrio/opus/smells/tests/dummy/MethodLocality.java");
		this.compilationUnit = CompilationUnitLoader.getCompilationUnit(file);

		TypeDeclarationCollector typeVisitor = new TypeDeclarationCollector();
		this.compilationUnit.accept(typeVisitor);
		type = typeVisitor.getNodesCollected().get(0);

		MethodCollector collector = new MethodCollector();
		compilationUnit.accept(collector);
		methods = collector.getNodesCollected();
	}

	@Test
	public void superLocalMethodTest() {
		MethodDeclaration decl = findMethodByName("superLocal");
		MethodCallLocalityVisitor visitor = new MethodCallLocalityVisitor(type);
		decl.accept(visitor);
		Assert.assertEquals(4, visitor.getLocalMethodsCallCount().intValue());
		Assert.assertEquals(0, visitor.getForeignMethodsCallCount().intValue());
	}

	@Test
	public void superForeignMethodTest() {
		MethodDeclaration decl = findMethodByName("superForeign");
		MethodCallLocalityVisitor visitor = new MethodCallLocalityVisitor(type);
		decl.accept(visitor);
		Assert.assertEquals(0, visitor.getLocalMethodsCallCount().intValue());
		Assert.assertEquals(3, visitor.getForeignMethodsCallCount().intValue());
	}

	@Test
	public void moreLocalMethodTest() {
		MethodDeclaration decl = findMethodByName("moreLocal");
		MethodCallLocalityVisitor visitor = new MethodCallLocalityVisitor(type);
		decl.accept(visitor);
		Assert.assertEquals(4, visitor.getLocalMethodsCallCount().intValue());
		Assert.assertEquals(3, visitor.getForeignMethodsCallCount().intValue());
	}

	@Test
	public void moreForeignMethodTest() {
		MethodDeclaration decl = findMethodByName("moreForeign");
		MethodCallLocalityVisitor visitor = new MethodCallLocalityVisitor(type);
		decl.accept(visitor);
		Assert.assertEquals(2, visitor.getLocalMethodsCallCount().intValue());
		Assert.assertEquals(3, visitor.getForeignMethodsCallCount().intValue());
	}
}
