package br.pucrio.opus.smells.tests.visitor;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pucrio.opus.smells.ast.visitors.CyclomaticComplexityVisitor;
import br.pucrio.opus.smells.ast.visitors.PublicMethodCollector;
import br.pucrio.opus.smells.tests.util.CompilationUnitLoader;

public class CyclomaticComplexityVisitorTest {
	
	private CompilationUnit compilationUnit;
	
	@Before
	public void setUp() throws IOException{
		File file = new File("test/br/pucrio/opus/smells/tests/dummy/CC.java");
		this.compilationUnit = CompilationUnitLoader.getCompilationUnit(file);
	}
	
	@Test
	public void computeCCAllMethodsTest() {
		PublicMethodCollector collector = new PublicMethodCollector();
		compilationUnit.accept(collector);
		List<MethodDeclaration> methods = collector.getNodesCollected();
		for (int i = 0; i < methods.size() - 1; i++) {
			CyclomaticComplexityVisitor ccVisitor = new CyclomaticComplexityVisitor();
			methods.get(i).accept(ccVisitor);
			int cc = ccVisitor.getCyclomaticComplexity().intValue();
			System.out.println(methods.get(i).getName() + " " + cc);
			Assert.assertEquals(i + 1, cc);
		}
	}
	
}