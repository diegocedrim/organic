package br.pucrio.opus.smells.tests.metrics;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.junit.Assert;
import org.junit.Test;

import br.pucrio.opus.smells.ast.visitors.TypeDeclarationCollector;
import br.pucrio.opus.smells.metrics.calculators.OverrideRatioCalculator;
import br.pucrio.opus.smells.tests.util.CompilationUnitLoader;

public class OverrideRatioCalculatorTest {
	
	private List<TypeDeclaration> getTypes(File file) throws IOException {
		CompilationUnit unit = CompilationUnitLoader.getCompilationUnit(file);
		TypeDeclarationCollector visitor = new TypeDeclarationCollector();
		unit.accept(visitor);
		return visitor.getNodesCollected();
	}

	
	@Test
	public void orcOnFieldAccessedByMethodTest() throws IOException {
		List<TypeDeclaration> types = getTypes(new File("test/br/pucrio/opus/smells/tests/dummy/FieldAccessedByMethod.java"));
		TypeDeclaration node = types.get(0);
		OverrideRatioCalculator calc = new OverrideRatioCalculator();
		Assert.assertEquals(new Double(2d/6d), calc.getValue(node)); //method a and the default constructor are overridden
	}
	
	@Test
	public void orcOnMiscStructuresTest() throws IOException {
		List<TypeDeclaration> types = getTypes(new File("test/br/pucrio/opus/smells/tests/dummy/CC.java"));
		TypeDeclaration node = types.get(0);
		OverrideRatioCalculator calc = new OverrideRatioCalculator();
		Assert.assertNull(calc.getValue(node));
	}
	
	@Test
	public void tccOnAnonymousClassTest() throws IOException {
		List<TypeDeclaration> types = getTypes(new File("test/br/pucrio/opus/smells/tests/dummy/AnonymousClass.java"));
		TypeDeclaration node = types.get(0);
		OverrideRatioCalculator calc = new OverrideRatioCalculator();
		Assert.assertNull(calc.getValue(node));
	}
}
