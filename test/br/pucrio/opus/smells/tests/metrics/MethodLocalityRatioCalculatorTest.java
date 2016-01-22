package br.pucrio.opus.smells.tests.metrics;

import java.io.File;
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
import br.pucrio.opus.smells.metrics.calculators.MethodLocalityRatioCalculator;
import br.pucrio.opus.smells.tests.util.CompilationUnitLoader;

public class MethodLocalityRatioCalculatorTest {

private CompilationUnit compilationUnit;

	private List<MethodDeclaration> methods;
	
	private TypeDeclaration type;
	
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
		File file = new File("test/br/pucrio/opus/smells/tests/dummy/MethodLocality.java");
		this.compilationUnit = CompilationUnitLoader.getCompilationUnit(file);
		
		TypeDeclarationCollector typeVisitor = new TypeDeclarationCollector();
		this.compilationUnit.accept(typeVisitor);
		type = typeVisitor.getNodesCollected().get(0);
		
		MethodCollector collector = new MethodCollector();
		compilationUnit.accept(collector);
		methods = collector.getNodesCollected();
	}
	
	@Test
	public void superLocalMethodTest() {
		MethodDeclaration decl = findMethodByName("superLocal");
		MethodLocalityRatioCalculator calculator = new MethodLocalityRatioCalculator(type);
		Assert.assertEquals(new Double(1), calculator.getValue(decl));
	}
	
	@Test
	public void superForeignMethodTest() {
		MethodDeclaration decl = findMethodByName("superForeign");
		MethodLocalityRatioCalculator calculator = new MethodLocalityRatioCalculator(type);
		Assert.assertEquals(new Double(0), calculator.getValue(decl));
	}
	
	@Test
	public void moreLocalMethodTest() {
		MethodDeclaration decl = findMethodByName("moreLocal");
		MethodLocalityRatioCalculator calculator = new MethodLocalityRatioCalculator(type);
		Assert.assertEquals(new Double(4d/7d), calculator.getValue(decl));
	}
	
	@Test
	public void moreForeignMethodTest() {
		MethodDeclaration decl = findMethodByName("moreForeign");
		MethodLocalityRatioCalculator calculator = new MethodLocalityRatioCalculator(type);
		Assert.assertEquals(new Double(2d/5d), calculator.getValue(decl));
	}
}
