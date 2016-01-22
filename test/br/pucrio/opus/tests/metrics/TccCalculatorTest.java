package br.pucrio.opus.tests.metrics;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.Assert;
import org.junit.Test;

import br.pucrio.opus.smells.metrics.calculators.TCCMetricValueCalculator;
import br.pucrio.opus.smells.visitors.TypeDeclarationCollector;
import br.pucrio.opus.tests.util.CompilationUnitLoader;

public class TccCalculatorTest {
	
	private List<ASTNode> getTypes(File file) throws IOException {
		CompilationUnit unit = CompilationUnitLoader.getCompilationUnit(file);
		TypeDeclarationCollector visitor = new TypeDeclarationCollector();
		unit.accept(visitor);
		return visitor.getNodesCollected();
	}

	
	@Test
	public void tccOnFieldAccessedByMethodTest() throws IOException {
		List<ASTNode> types = getTypes(new File("test/br/pucrio/opus/tests/dummy/FieldAccessedByMethod.java"));
		ASTNode node = types.get(0);
		TCCMetricValueCalculator calc = new TCCMetricValueCalculator();
		Assert.assertEquals(new Double(2.0d/3d), calc.getValue(node));
	}
	
	@Test
	public void tccOnMiscStructuresTest() throws IOException {
		List<ASTNode> types = getTypes(new File("test/br/pucrio/opus/tests/dummy/MiscStructures.java"));
		ASTNode node = types.get(0);
		TCCMetricValueCalculator calc = new TCCMetricValueCalculator();
		Assert.assertEquals(new Double(0), calc.getValue(node));
	}
	
	@Test
	public void tccOnAnonymousClassTest() throws IOException {
		List<ASTNode> types = getTypes(new File("test/br/pucrio/opus/tests/dummy/AnonymousClass.java"));
		ASTNode node = types.get(0);
		TCCMetricValueCalculator calc = new TCCMetricValueCalculator();
		Assert.assertEquals(new Double(1.0d/3d), calc.getValue(node));
	}
}
