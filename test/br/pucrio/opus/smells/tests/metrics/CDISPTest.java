package br.pucrio.opus.smells.tests.metrics;

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
import br.pucrio.opus.smells.metrics.calculators.CouplingDispersionCalculator;
import br.pucrio.opus.smells.tests.util.CompilationUnitLoader;

public class CDISPTest {
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
		this.compilationUnit = CompilationUnitLoader.getCompilationUnitDummyClass("Coupling.java");

		TypeDeclarationCollector typeVisitor = new TypeDeclarationCollector();
		this.compilationUnit.accept(typeVisitor);
		TypeDeclaration type = typeVisitor.getNodesCollected().get(0);

		MethodCollector collector = new MethodCollector();
		type.accept(collector);
		methods = collector.getNodesCollected();
	}
	
	private Double getCDISP(MethodDeclaration declaration) {
		CouplingDispersionCalculator calc = new CouplingDispersionCalculator();
		return calc.getValue(declaration);
	}

	@Test
	public void highCintTest() {
		MethodDeclaration decl = findMethodByName("highCint");
		Assert.assertEquals(new Double(2.0/5), getCDISP(decl));
	}
	
	@Test
	public void lowCintTest() {
		MethodDeclaration decl = findMethodByName("lowCint");
		Assert.assertEquals(new Double(3/3), getCDISP(decl));
	}
	
	@Test
	public void dummy1Test() {
		MethodDeclaration decl = findMethodByName("dummy1");
		Assert.assertEquals(new Double(0), getCDISP(decl));
	}
	
	@Test
	public void cint1Test() {
		MethodDeclaration decl = findMethodByName("cint1");
		Assert.assertEquals(new Double(1), getCDISP(decl));
	}

}
