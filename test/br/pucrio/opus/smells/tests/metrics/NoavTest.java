package br.pucrio.opus.smells.tests.metrics;

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
import br.pucrio.opus.smells.metrics.calculators.NOAVCalculator;
import br.pucrio.opus.smells.tests.util.CompilationUnitLoader;

public class NoavTest {
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
		this.compilationUnit = CompilationUnitLoader.getCompilationUnitDummyClass("Noav.java");

		TypeDeclarationCollector typeVisitor = new TypeDeclarationCollector();
		this.compilationUnit.accept(typeVisitor);
		TypeDeclaration type = typeVisitor.getNodesCollected().get(0);

		MethodCollector collector = new MethodCollector();
		type.accept(collector);
		methods = collector.getNodesCollected();
	}
	
	private Double getNoav(MethodDeclaration declaration) {
		NOAVCalculator calc = new NOAVCalculator();
		return calc.getValue(declaration);
	}

	@Test
	public void noneTest() {
		MethodDeclaration decl = findMethodByName("none");
		Assert.assertEquals(new Double(0), getNoav(decl));
	}

	@Test
	public void noneWihLocalTest() {
		MethodDeclaration decl = findMethodByName("noneWihLocal");
		Assert.assertEquals(new Double(1), getNoav(decl));
	}
	
	@Test
	public void oneTest() {
		MethodDeclaration decl = findMethodByName("one");
		Assert.assertEquals(new Double(1), getNoav(decl));
	}
	
	@Test
	public void twoTest() {
		MethodDeclaration decl = findMethodByName("two");
		Assert.assertEquals(new Double(2), getNoav(decl));
	}
	
	@Test
	public void threeTest() {
		MethodDeclaration decl = findMethodByName("three");
		Assert.assertEquals(new Double(3), getNoav(decl));
	}
	
	@Test
	public void threePlusOneExternalTest() {
		MethodDeclaration decl = findMethodByName("threePlusOneExternal");
		Assert.assertEquals(new Double(5), getNoav(decl));
	}
}
