package br.pucrio.opus.smells.tests.metrics;

import java.io.IOException;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.junit.Assert;
import org.junit.Test;

import br.pucrio.opus.smells.ast.visitors.TypeDeclarationCollector;
import br.pucrio.opus.smells.metrics.calculators.WMCCalculator;
import br.pucrio.opus.smells.tests.util.CompilationUnitLoader;

public class WMCTest {

	private Double getWMC(String dummyClassName) throws IOException {
		CompilationUnit unit = CompilationUnitLoader.getCompilationUnitDummyClass(dummyClassName);
		TypeDeclarationCollector visitor = new TypeDeclarationCollector();
		unit.accept(visitor);
		
		TypeDeclaration type = visitor.getNodesCollected().get(0);
		WMCCalculator calculator = new WMCCalculator();
		return calculator.getValue(type);
	}
	
	@Test
	public void anonymousClassTest() throws IOException {
		Double wmc = getWMC("AnonymousClass.java");
		Assert.assertEquals(new Double(5), wmc);
	}
	
	@Test
	public void emptyClassTest() throws IOException {
		Double wmc = getWMC("EmptyClass.java");
		Assert.assertEquals(new Double(0), wmc);
	}
	
	@Test
	public void ccTest() throws IOException {
		Double wmc = getWMC("CC.java");
		Assert.assertEquals(new Double(108), wmc);
	}
}
