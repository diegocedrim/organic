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
import br.pucrio.opus.smells.metrics.calculators.MethodParameterCountCalculator;
import br.pucrio.opus.smells.tests.util.CompilationUnitLoader;

public class MethodParameterCountCalculatorTest {

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
		File file = new File("test/br/pucrio/opus/smells/tests/dummy/MethodLocality.java");
		this.compilationUnit = CompilationUnitLoader.getCompilationUnit(file);
		
		TypeDeclarationCollector typeVisitor = new TypeDeclarationCollector();
		this.compilationUnit.accept(typeVisitor);
		TypeDeclaration type = typeVisitor.getNodesCollected().get(0);
		
		MethodCollector collector = new MethodCollector();
		type.accept(collector);
		methods = collector.getNodesCollected();
	}
	
	@Test
	public void superLocalMethodTest() {
		MethodDeclaration decl = findMethodByName("superLocal");
		MethodParameterCountCalculator calculator = new MethodParameterCountCalculator();
		Assert.assertEquals(new Double(0), calculator.getValue(decl));
	}
	
	@Test
	public void superForeignMethodTest() {
		MethodDeclaration decl = findMethodByName("superForeign");
		MethodParameterCountCalculator calculator = new MethodParameterCountCalculator();
		Assert.assertEquals(new Double(1), calculator.getValue(decl));
	}
	
	@Test
	public void moreLocalMethodTest() {
		MethodDeclaration decl = findMethodByName("moreLocal");
		MethodParameterCountCalculator calculator = new MethodParameterCountCalculator();
		Assert.assertEquals(new Double(2), calculator.getValue(decl));
	}
	
	@Test
	public void moreForeignMethodTest() {
		MethodDeclaration decl = findMethodByName("moreForeign");
		MethodParameterCountCalculator calculator = new MethodParameterCountCalculator();
		Assert.assertEquals(new Double(3), calculator.getValue(decl));
	}
}
