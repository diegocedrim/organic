package br.pucrio.opus.smells.tests.visitor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pucrio.opus.smells.ast.visitors.ClassMethodInvocationVisitor;
import br.pucrio.opus.smells.ast.visitors.MethodCollector;
import br.pucrio.opus.smells.ast.visitors.TypeDeclarationCollector;
import br.pucrio.opus.smells.tests.util.CompilationUnitLoader;

public class ClassMethodInvocationVisitorTest {

	private CompilationUnit compilationUnit;

	private List<MethodDeclaration> methods;

	private ITypeBinding type;

	private MethodDeclaration findMethodByName(String name) {
		for (MethodDeclaration decls : methods) {
			if (decls.getName().toString().equals(name)) {
				return decls;
			}
		}
		return null;
	}
	
	private void testOutput(Map<String, Integer> correct, Map<String, Integer> obtained) {
		Assert.assertEquals(correct.size(), obtained.size());
		for (String type : correct.keySet()) {
			Assert.assertEquals(correct.get(type), obtained.get(type));
		}
	}
	
	private Map<String, Integer> convertMap(Map<ITypeBinding, Integer> toBeConverted) {
		Map<String, Integer> converted = new HashMap<>();
		for (ITypeBinding type : toBeConverted.keySet()) {
			converted.put(type.getQualifiedName(), toBeConverted.get(type));
		}
		return converted;
	}

	@Before
	public void setUp() throws IOException{
		File file = new File("test/br/pucrio/opus/smells/tests/dummy/MethodLocality.java");
		this.compilationUnit = CompilationUnitLoader.getCompilationUnit(file);

		TypeDeclarationCollector typeVisitor = new TypeDeclarationCollector();
		this.compilationUnit.accept(typeVisitor);
		type = typeVisitor.getNodesCollected().get(0).resolveBinding();

		MethodCollector collector = new MethodCollector();
		compilationUnit.accept(collector);
		methods = collector.getNodesCollected();
	}

	@Test
	public void superLocalMethodTest() {
		MethodDeclaration decl = findMethodByName("superLocal");
		ClassMethodInvocationVisitor visitor = new ClassMethodInvocationVisitor(type);
		decl.accept(visitor);
		
		Map<String, Integer> correct = new HashMap<>();
		correct.put("br.pucrio.opus.smells.tests.dummy.MethodLocality", 4);
		
		this.testOutput(correct, this.convertMap(visitor.getMethodsCalls()));
	}

	@Test
	public void superForeignMethodTest() {
		MethodDeclaration decl = findMethodByName("superForeign");
		ClassMethodInvocationVisitor visitor = new ClassMethodInvocationVisitor(type);
		decl.accept(visitor);
		Map<String, Integer> correct = new HashMap<>();
		correct.put("br.pucrio.opus.smells.tests.dummy.FieldAccessedByMethod", 1);
		correct.put("br.pucrio.opus.smells.tests.dummy.SuperDummy", 2);
		
		this.testOutput(correct, this.convertMap(visitor.getMethodsCalls()));
	}

	@Test
	public void moreLocalMethodTest() {
		MethodDeclaration decl = findMethodByName("moreLocal");
		ClassMethodInvocationVisitor visitor = new ClassMethodInvocationVisitor(type);
		decl.accept(visitor);
		
		Map<String, Integer> correct = new HashMap<>();
		correct.put("br.pucrio.opus.smells.tests.dummy.FieldAccessedByMethod", 1);
		correct.put("br.pucrio.opus.smells.tests.dummy.SuperDummy", 1);
		correct.put("br.pucrio.opus.smells.tests.dummy.MethodLocality", 4);
		correct.put("br.pucrio.opus.smells.tests.dummy.RefusedBedquestSample", 1);
		
		this.testOutput(correct, this.convertMap(visitor.getMethodsCalls()));
	}

	@Test
	public void moreForeignMethodTest() {
		MethodDeclaration decl = findMethodByName("moreForeign");
		ClassMethodInvocationVisitor visitor = new ClassMethodInvocationVisitor(type);
		decl.accept(visitor);
		
		Map<String, Integer> correct = new HashMap<>();
		correct.put("br.pucrio.opus.smells.tests.dummy.FieldAccessedByMethod", 1);
		correct.put("br.pucrio.opus.smells.tests.dummy.SuperDummy", 4);
		correct.put("br.pucrio.opus.smells.tests.dummy.MethodLocality", 2);
		correct.put("br.pucrio.opus.smells.tests.dummy.RefusedBedquestSample", 1);
		
		this.testOutput(correct, this.convertMap(visitor.getMethodsCalls()));
	}
}
