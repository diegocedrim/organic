package br.pucrio.opus.smells.tests.metrics;

import java.io.IOException;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.junit.Assert;
import org.junit.Test;

import br.pucrio.opus.smells.ast.visitors.TypeDeclarationCollector;
import br.pucrio.opus.smells.metrics.calculators.WOCCalculator;
import br.pucrio.opus.smells.tests.util.CompilationUnitLoader;

public class WOCTest {

	private Double getWOC(String dummyClassName) throws IOException {
		CompilationUnit unit = CompilationUnitLoader.getCompilationUnitDummyClass(dummyClassName);
		TypeDeclarationCollector visitor = new TypeDeclarationCollector();
		unit.accept(visitor);
		
		TypeDeclaration type = visitor.getNodesCollected().get(0);
		WOCCalculator calculator = new WOCCalculator();
		return calculator.getValue(type);
	}
	
	@Test
	public void anonymousClassTest() throws IOException {
		Double woc = getWOC("AnonymousClass.java");
		Assert.assertEquals(new Double(1), woc);
	}
	
	@Test
	public void emptyClassTest() throws IOException {
		Double woc = getWOC("EmptyClass.java");
		Assert.assertEquals(new Double(0), woc);
	}
	
	@Test
	public void ccTest() throws IOException {
		Double woc = getWOC("CC.java");
		Assert.assertEquals(new Double(1), woc);
	}
	
	@Test
	public void assessorsTest() throws IOException {
		Double woc = getWOC("Assessors.java");
		Assert.assertEquals(new Double(2.0/11.0), woc);
	}
}
