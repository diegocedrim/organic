package br.pucrio.opus.tests.metrics;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.Assert;
import org.junit.Test;

import br.pucrio.opus.smells.ast.ASTBuilder;
import br.pucrio.opus.smells.metrics.calculators.TCCMetricValueCalculator;
import br.pucrio.opus.smells.visitors.TypeDeclarationCollector;

public class TccCalculatorTest {
	
	private CompilationUnit getCompilationUnit(File target) throws IOException {
		File file = new File("test");
		String[] srcPaths = new String[]{file.getAbsolutePath()};
		ASTBuilder builder = new ASTBuilder(srcPaths);
		ASTParser parser = builder.create();
		
		String source = FileUtils.readFileToString(target);
		parser.setSource(source.toCharArray());
		return (CompilationUnit)parser.createAST(null);
	}
	
	private List<ASTNode> getTypes(File file) throws IOException {
		CompilationUnit unit = getCompilationUnit(file);
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
