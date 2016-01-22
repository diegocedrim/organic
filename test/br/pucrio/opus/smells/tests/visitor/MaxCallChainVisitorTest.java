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

import br.pucrio.opus.smells.ast.visitors.MaxCallChainVisitor;
import br.pucrio.opus.smells.ast.visitors.MethodCollector;
import br.pucrio.opus.smells.ast.visitors.TypeDeclarationCollector;
import br.pucrio.opus.smells.tests.util.CompilationUnitLoader;

public class MaxCallChainVisitorTest {

	private CompilationUnit compilationUnit;

	private List<MethodDeclaration> methods;

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
		TypeDeclaration type = typeVisitor.getNodesCollected().get(0);

		MethodCollector collector = new MethodCollector();
		type.accept(collector);
		methods = collector.getNodesCollected();
	}

	@Test
	public void localAMethodTest() {
		MethodDeclaration decl = findMethodByName("localA");
		MaxCallChainVisitor visitor = new MaxCallChainVisitor();
		decl.accept(visitor);
		Assert.assertEquals(0, visitor.getMaxCallChain().intValue());
	}

	@Test
	public void localBMethodTest() {
		MethodDeclaration decl = findMethodByName("localB");
		MaxCallChainVisitor visitor = new MaxCallChainVisitor();
		decl.accept(visitor);
		Assert.assertEquals(1, visitor.getMaxCallChain().intValue());
	}

	@Test
	public void localCMethodTest() {
		MethodDeclaration decl = findMethodByName("localC");
		MaxCallChainVisitor visitor = new MaxCallChainVisitor();
		decl.accept(visitor);
		Assert.assertEquals(1, visitor.getMaxCallChain().intValue());
	}

	@Test
	public void localDMethodTest() {
		MethodDeclaration decl = findMethodByName("localD");
		MaxCallChainVisitor visitor = new MaxCallChainVisitor();
		decl.accept(visitor);
		Assert.assertEquals(6, visitor.getMaxCallChain().intValue());
	}
	
}
