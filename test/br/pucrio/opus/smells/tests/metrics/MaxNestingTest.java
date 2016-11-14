package br.pucrio.opus.smells.tests.metrics;

import java.io.IOException;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.junit.Assert;
import org.junit.Test;

import br.pucrio.opus.smells.ast.visitors.TypeDeclarationCollector;
import br.pucrio.opus.smells.metrics.calculators.MaxNestingCalculator;
import br.pucrio.opus.smells.tests.util.CompilationUnitLoader;

public class MaxNestingTest {

	private Double getMaxNesting(String dummyClassName) throws IOException {
		CompilationUnit unit = CompilationUnitLoader.getCompilationUnitDummyClass(dummyClassName);
		TypeDeclarationCollector visitor = new TypeDeclarationCollector();
		unit.accept(visitor);
		
		TypeDeclaration type = visitor.getNodesCollected().get(0);
		MaxNestingCalculator calculator = new MaxNestingCalculator();
		return calculator.getValue(type);
	}
	
	@Test
	public void anonymousClassTest() throws IOException {
		Double maxNesting = getMaxNesting("AnonymousClass.java");
		Assert.assertEquals(new Double(1), maxNesting);
	}
	
	@Test
	public void emptyClassTest() throws IOException {
		Double maxNesting = getMaxNesting("EmptyClass.java");
		Assert.assertEquals(new Double(0), maxNesting);
	}
	
	@Test
	public void ccTest() throws IOException {
		Double maxNesting = getMaxNesting("CC.java");
		Assert.assertEquals(new Double(11), maxNesting);
	}
}
