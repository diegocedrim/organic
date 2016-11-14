package br.pucrio.opus.smells.tests.metrics;

import java.io.IOException;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.junit.Assert;
import org.junit.Test;

import br.pucrio.opus.smells.ast.visitors.TypeDeclarationCollector;
import br.pucrio.opus.smells.metrics.calculators.WeightedMethodCountCalculator;
import br.pucrio.opus.smells.tests.util.CompilationUnitLoader;

public class WMCTest {

	private Double getMaxNesting(String dummyClassName) throws IOException {
		CompilationUnit unit = CompilationUnitLoader.getCompilationUnitDummyClass(dummyClassName);
		TypeDeclarationCollector visitor = new TypeDeclarationCollector();
		unit.accept(visitor);
		
		TypeDeclaration type = visitor.getNodesCollected().get(0);
		WeightedMethodCountCalculator calculator = new WeightedMethodCountCalculator();
		return calculator.getValue(type);
	}
	
	@Test
	public void anonymousClassTest() throws IOException {
		Double wmc = getMaxNesting("AnonymousClass.java");
		Assert.assertEquals(new Double(5), wmc);
	}
	
	@Test
	public void emptyClassTest() throws IOException {
		Double wmc = getMaxNesting("EmptyClass.java");
		Assert.assertEquals(new Double(0), wmc);
	}
	
	@Test
	public void ccTest() throws IOException {
		Double wmc = getMaxNesting("CC.java");
		Assert.assertEquals(new Double(77), wmc);
	}
}
