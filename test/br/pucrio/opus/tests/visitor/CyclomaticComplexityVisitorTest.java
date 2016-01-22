package br.pucrio.opus.tests.visitor;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pucrio.opus.smells.visitors.CyclomaticComplexityVisitor;
import br.pucrio.opus.smells.visitors.PublicMethodCollector;
import br.pucrio.opus.tests.util.CompilationUnitLoader;

public class CyclomaticComplexityVisitorTest {
	
	private CompilationUnit compilationUnit;
	
	@Before
	public void setUp() throws IOException{
		File file = new File("test/br/pucrio/opus/tests/dummy/CC.java");
		this.compilationUnit = CompilationUnitLoader.getCompilationUnit(file);
	}
	
	@Test
	public void computeCCAllMethodsTest() {
		PublicMethodCollector collector = new PublicMethodCollector();
		compilationUnit.accept(collector);
		List<MethodDeclaration> methods = collector.getNodesCollected();
		for (int i = 0; i < methods.size(); i++) {
			CyclomaticComplexityVisitor ccVisitor = new CyclomaticComplexityVisitor();
			methods.get(i).accept(ccVisitor);
			int cc = ccVisitor.getCyclomaticComplexity().intValue();
			Assert.assertEquals(i + 1, cc);
		}
	}
	
}