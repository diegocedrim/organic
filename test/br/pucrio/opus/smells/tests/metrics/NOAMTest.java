package br.pucrio.opus.smells.tests.metrics;

import java.io.IOException;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.junit.Assert;
import org.junit.Test;

import br.pucrio.opus.smells.ast.visitors.TypeDeclarationCollector;
import br.pucrio.opus.smells.metrics.calculators.NOAMCalculator;
import br.pucrio.opus.smells.tests.util.CompilationUnitLoader;

public class NOAMTest {

	private Double getNOAM(String dummyClassName) throws IOException {
		CompilationUnit unit = CompilationUnitLoader.getCompilationUnitDummyClass(dummyClassName);
		TypeDeclarationCollector visitor = new TypeDeclarationCollector();
		unit.accept(visitor);
		
		TypeDeclaration type = visitor.getNodesCollected().get(0);
		NOAMCalculator calculator = new NOAMCalculator();
		return calculator.getValue(type);
	}
	
	@Test
	public void anonymousClassTest() throws IOException {
		Double noam = getNOAM("AnonymousClass.java");
		Assert.assertEquals(new Double(0), noam);
	}
	
	@Test
	public void emptyClassTest() throws IOException {
		Double noam = getNOAM("EmptyClass.java");
		Assert.assertEquals(new Double(0), noam);
	}
	
	@Test
	public void ccTest() throws IOException {
		Double noam = getNOAM("CC.java");
		Assert.assertEquals(new Double(0), noam);
	}
	
	@Test
	public void assessorsTest() throws IOException {
		Double noam = getNOAM("Assessors.java");
		Assert.assertEquals(new Double(9), noam);
	}
}
