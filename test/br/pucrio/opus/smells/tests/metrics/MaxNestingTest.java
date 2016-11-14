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
import br.pucrio.opus.smells.metrics.calculators.MaxNestingCalculator;
import br.pucrio.opus.smells.tests.util.CompilationUnitLoader;

public class MaxNestingTest {

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
		this.compilationUnit = CompilationUnitLoader.getCompilationUnitDummyClass("CC.java");

		TypeDeclarationCollector typeVisitor = new TypeDeclarationCollector();
		this.compilationUnit.accept(typeVisitor);
		TypeDeclaration type = typeVisitor.getNodesCollected().get(0);

		MethodCollector collector = new MethodCollector();
		type.accept(collector);
		methods = collector.getNodesCollected();
	}
	
	private Double getMaxNesting(MethodDeclaration declaration) {
		MaxNestingCalculator calc = new MaxNestingCalculator();
		return calc.getValue(declaration);
	}

	@Test
	public void ccTest() {
		MethodDeclaration decl = findMethodByName("cc2");
		Assert.assertEquals(new Double(1), getMaxNesting(decl));
		
		decl = findMethodByName("cc3");
		Assert.assertEquals(new Double(2), getMaxNesting(decl));
		
		decl = findMethodByName("cc4");
		Assert.assertEquals(new Double(3), getMaxNesting(decl));
		
		decl = findMethodByName("cc5");
		Assert.assertEquals(new Double(3), getMaxNesting(decl));
		
		decl = findMethodByName("cc6");
		Assert.assertEquals(new Double(3), getMaxNesting(decl));
		
		decl = findMethodByName("cc1");
		Assert.assertEquals(new Double(0), getMaxNesting(decl));
	}

}
